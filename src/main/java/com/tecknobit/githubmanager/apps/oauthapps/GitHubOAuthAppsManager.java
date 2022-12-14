package com.tecknobit.githubmanager.apps.oauthapps;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.oauthapps.records.ScopedAccessToken;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.DELETE;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The {@code GitHubOAuthAppsManager} class is useful to manage all GitHub's marketplace endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications">
 * OAuth Apps</a>
 * @see GitHubManager
 **/
public class GitHubOAuthAppsManager extends GitHubManager {

    /**
     * {@code APPLICATIONS_PATH} constant for {@code "applications/"} path
     **/
    public static final String APPLICATIONS_PATH = "applications/";

    /**
     * {@code GRANT_PATH} constant for {@code "/grant"} path
     **/
    public static final String GRANT_PATH = "/grant";

    /**
     * {@code TOKEN_PATH} constant for {@code "/token"} path
     **/
    public static final String TOKEN_PATH = "/token";

    /**
     * {@code SCOPED_PATH} constant for {@code "/scoped"} path
     **/
    public static final String SCOPED_PATH = "/scoped";

    /**
     * Constructor to init a {@link GitHubOAuthAppsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOAuthAppsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOAuthAppsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOAuthAppsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOAuthAppsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOAuthAppsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOAuthAppsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOAuthAppsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOAuthAppsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubOAuthAppsManager() {
        super();
    }

    public boolean deleteAppAuthorization(ScopedAccessToken accessToken) {
        return deleteAppAuthorization(accessToken.getApp().getClientId(), accessToken.getToken());
    }

    public boolean deleteAppAuthorization(String clientId, String accessToken) {
        return sendDeletePayloadedRequest(BASE_ENDPOINT + APPLICATIONS_PATH + clientId + GRANT_PATH, accessToken);
    }

    public ScopedAccessToken checkToken(ScopedAccessToken accessToken) throws Exception {
        return checkToken(accessToken.getApp().getClientId(), accessToken.getToken(), LIBRARY_OBJECT);
    }

    public <T> T checkToken(ScopedAccessToken accessToken, ReturnFormat format) throws Exception {
        return checkToken(accessToken.getApp().getClientId(), accessToken.getToken(), format);
    }

    public ScopedAccessToken checkToken(String clientId, String accessToken) throws Exception {
        return checkToken(clientId, accessToken, LIBRARY_OBJECT);
    }

    public <T> T checkToken(String clientId, String accessToken, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("access_token", accessToken);
        return returnScopedAccessToken(sendPostRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, payload),
                format);
    }

    public ScopedAccessToken resetToken(ScopedAccessToken accessToken) throws Exception {
        return resetToken(accessToken.getApp().getClientId(), accessToken.getToken(), LIBRARY_OBJECT);
    }

    public <T> T resetToken(ScopedAccessToken accessToken, ReturnFormat format) throws Exception {
        return resetToken(accessToken.getApp().getClientId(), accessToken.getToken(), format);
    }

    public ScopedAccessToken resetToken(String clientId, String accessToken) throws Exception {
        return resetToken(clientId, accessToken, LIBRARY_OBJECT);
    }

    public <T> T resetToken(String clientId, String accessToken, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("access_token", accessToken);
        return returnScopedAccessToken(sendPatchRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, payload),
                format);
    }

    public boolean deleteAppToken(ScopedAccessToken accessToken) {
        return deleteAppToken(accessToken.getApp().getClientId(), accessToken.getToken());
    }

    public boolean deleteAppToken(String clientId, String accessToken) {
        return sendDeletePayloadedRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, accessToken);
    }

    private boolean sendDeletePayloadedRequest(String url, String accessToken) {
        try {
            Params payload = new Params();
            payload.addParam("access_token", accessToken);
            HttpURLConnection request = (HttpURLConnection) new URL(BASE_ENDPOINT + url).openConnection();
            request.setRequestMethod(DELETE.name());
            request.setRequestProperty("authorization", " token " + this.accessToken);
            request.setRequestProperty("accept", "application/vnd.github+json");
            request.setDoOutput(true);
            byte[] tBytes = payload.createJSONPayload().toString().getBytes(UTF_8);
            request.getOutputStream().write(tBytes, 0, tBytes.length);
            request.connect();
            if (request.getResponseCode() != 204) {
                System.out.println(new BufferedReader(new InputStreamReader(request.getErrorStream())).readLine());
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ScopedAccessToken createScopedAccessToken(String clientId, String accessToken, Params bodyParams) throws Exception {
        return createScopedAccessToken(clientId, accessToken, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createScopedAccessToken(String clientId, String accessToken, Params bodyParams,
                                         ReturnFormat format) throws Exception {
        bodyParams.addParam("access_token", accessToken);
        return returnScopedAccessToken(sendPostRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH + SCOPED_PATH,
                bodyParams), format);
    }

    /**
     * Method to create a scoped access token
     *
     * @param tokenResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     **/
    @Returner
    private <T> T returnScopedAccessToken(String tokenResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tokenResponse);
            case LIBRARY_OBJECT:
                return (T) new ScopedAccessToken(new JSONObject(tokenResponse));
            default:
                return (T) tokenResponse;
        }
    }

}
