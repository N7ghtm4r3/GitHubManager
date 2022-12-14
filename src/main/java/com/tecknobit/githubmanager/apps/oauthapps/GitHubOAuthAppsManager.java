package com.tecknobit.githubmanager.apps.oauthapps;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.AppPermissions;
import com.tecknobit.githubmanager.apps.oauthapps.records.ScopedAccessToken;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
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

    /**
     * OAuth's applications application owners can revoke a grant for their OAuth application and a specific user.
     * You must use Basic Authentication when accessing this endpoint, using the OAuth application's {@code "client_id"}
     * and {@code "client_secret"} as the username and password.
     * You must also provide a valid OAuth {@code "access_token"} as an input parameter and the grant for the token's owner will be deleted.
     * Deleting an OAuth application's grant will also delete all OAuth tokens associated with the application for the user.
     * Once deleted, the application will have no access to the user's account and will no longer be listed on the application
     * authorizations settings screen within GitHub
     *
     * @param accessToken: access token where revoke the authorization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#delete-an-app-authorization">
     * Delete an app authorization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/applications/{client_id}/grant")
    public boolean deleteAppAuthorization(ScopedAccessToken accessToken) {
        return deleteAppAuthorization(accessToken.getApp().getClientId(), accessToken.getToken());
    }

    /**
     * OAuth's applications application owners can revoke a grant for their OAuth application and a specific user.
     * You must use Basic Authentication when accessing this endpoint, using the OAuth application's {@code "client_id"}
     * and {@code "client_secret"} as the username and password.
     * You must also provide a valid OAuth {@code "access_token"} as an input parameter and the grant for the token's owner will be deleted.
     * Deleting an OAuth application's grant will also delete all OAuth tokens associated with the application for the user.
     * Once deleted, the application will have no access to the user's account and will no longer be listed on the application
     * authorizations settings screen within GitHub
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#delete-an-app-authorization">
     * Delete an app authorization</a>
     **/
    @RequestPath(method = DELETE, path = "/applications/{client_id}/grant")
    public boolean deleteAppAuthorization(String clientId, String accessToken) {
        return sendDeletePayloadedRequest(BASE_ENDPOINT + APPLICATIONS_PATH + clientId + GRANT_PATH, accessToken);
    }

    /**
     * OAuth's applications can use a special API method for checking OAuth token validity without exceeding the
     * normal rate limits for failed login attempts. Authentication works differently with this particular endpoint.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param accessToken: access token to check
     * @return scoped access token as {@link ScopedAccessToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#check-a-token">
     * Check a token</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/applications/{client_id}/token")
    public ScopedAccessToken checkToken(ScopedAccessToken accessToken) throws Exception {
        return checkToken(accessToken.getApp().getClientId(), accessToken.getToken(), LIBRARY_OBJECT);
    }

    /**
     * OAuth's applications can use a special API method for checking OAuth token validity without exceeding the
     * normal rate limits for failed login attempts. Authentication works differently with this particular endpoint.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param accessToken: access token to check
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#check-a-token">
     * Check a token</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/applications/{client_id}/token")
    public <T> T checkToken(ScopedAccessToken accessToken, ReturnFormat format) throws Exception {
        return checkToken(accessToken.getApp().getClientId(), accessToken.getToken(), format);
    }

    /**
     * OAuth's applications can use a special API method for checking OAuth token validity without exceeding the
     * normal rate limits for failed login attempts. Authentication works differently with this particular endpoint.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @return scoped access token as {@link ScopedAccessToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#check-a-token">
     * Check a token</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/applications/{client_id}/token")
    public ScopedAccessToken checkToken(String clientId, String accessToken) throws Exception {
        return checkToken(clientId, accessToken, LIBRARY_OBJECT);
    }

    /**
     * OAuth's applications can use a special API method for checking OAuth token validity without exceeding the
     * normal rate limits for failed login attempts. Authentication works differently with this particular endpoint.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#check-a-token">
     * Check a token</a>
     **/
    @RequestPath(method = POST, path = "/applications/{client_id}/token")
    public <T> T checkToken(String clientId, String accessToken, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("access_token", accessToken);
        return returnScopedAccessToken(sendPostRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, payload),
                format);
    }

    /**
     * OAuth's applications can use this API method to reset a valid OAuth token without end-user involvement.
     * Applications must save the "token" property in the response because changes take effect immediately.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param accessToken: access token to reset
     * @return scoped access token as {@link ScopedAccessToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#reset-a-token">
     * Reset a token</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/applications/{client_id}/token")
    public ScopedAccessToken resetToken(ScopedAccessToken accessToken) throws Exception {
        return resetToken(accessToken.getApp().getClientId(), accessToken.getToken(), LIBRARY_OBJECT);
    }

    /**
     * OAuth's applications can use this API method to reset a valid OAuth token without end-user involvement.
     * Applications must save the "token" property in the response because changes take effect immediately.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param accessToken: access token to reset
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#reset-a-token">
     * Reset a token</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/applications/{client_id}/token")
    public <T> T resetToken(ScopedAccessToken accessToken, ReturnFormat format) throws Exception {
        return resetToken(accessToken.getApp().getClientId(), accessToken.getToken(), format);
    }

    /**
     * OAuth's applications can use this API method to reset a valid OAuth token without end-user involvement.
     * Applications must save the "token" property in the response because changes take effect immediately.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @return scoped access token as {@link ScopedAccessToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#reset-a-token">
     * Reset a token</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/applications/{client_id}/token")
    public ScopedAccessToken resetToken(String clientId, String accessToken) throws Exception {
        return resetToken(clientId, accessToken, LIBRARY_OBJECT);
    }

    /**
     * OAuth's applications can use this API method to reset a valid OAuth token without end-user involvement.
     * Applications must save the "token" property in the response because changes take effect immediately.
     * You must use Basic Authentication to use this endpoint, where the username is the OAuth application {@link "client_id"}
     * and the password is its {@code "client_secret"}. Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#reset-a-token">
     * Reset a token</a>
     **/
    @RequestPath(method = PATCH, path = "/applications/{client_id}/token")
    public <T> T resetToken(String clientId, String accessToken, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("access_token", accessToken);
        return returnScopedAccessToken(sendPatchRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, payload),
                format);
    }

    /**
     * OAuth's application owners can revoke a single token for an OAuth application.
     * You must use Basic Authentication when accessing this endpoint, using the OAuth application's {@code "client_id"}
     * and {@code "client_secret"} as the username and password.
     *
     * @param accessToken: access token where revoke a token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#delete-an-app-token">
     * Delete an app token</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/applications/{client_id}/token")
    public boolean deleteAppToken(ScopedAccessToken accessToken) {
        return deleteAppToken(accessToken.getApp().getClientId(), accessToken.getToken());
    }

    /**
     * OAuth's application owners can revoke a single token for an OAuth application.
     * You must use Basic Authentication when accessing this endpoint, using the OAuth application's {@code "client_id"}
     * and {@code "client_secret"} as the username and password.
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#delete-an-app-token">
     * Delete an app token</a>
     **/
    @RequestPath(method = DELETE, path = "/applications/{client_id}/token")
    public boolean deleteAppToken(String clientId, String accessToken) {
        return sendDeletePayloadedRequest(APPLICATIONS_PATH + clientId + TOKEN_PATH, accessToken);
    }

    /**
     * Method to send a delete request with a content payload
     *
     * @param url:         the client ID of the GitHub app
     * @param accessToken: access token used in the request
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
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

    /**
     * Method to use a non-scoped user-to-server OAuth access token to create a repository scoped and/or permission
     * scoped user-to-server OAuth access token. You can specify which repositories the token can access and which
     * permissions are granted to the token. You must use Basic Authentication when accessing this endpoint,
     * using the OAuth application's {@code "client_id"} and {@code "client_secret"} as the username and password.
     * Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @param bodyParams:  extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "target"} -> the name of the user or organization to scope the user-to-server access token to. <br>
     *                            <strong>Required</strong> unless {@code "target_id"} is specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "target_id"} -> the ID of the user or organization to scope the user-to-server
     *                            access token to. <br>
     *                            <strong>Required</strong> unless {@code "target"} is specified - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "repositories"} -> the list of repository names to scope the user-to-server access token to.
     *                            {@code "repositories"} may not be specified if {@code "repository_ids"} is specified - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "repository_ids"} -> the list of repository IDs to scope the user-to-server access token to.
     *                            {@code "repository_ids"} may not be specified if {@code "repositories"} is specified - [array of integers]
     *                        </li>
     *                        <li>
     *                            {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                            you can use the {@link AppPermissions} custom object to create the permissions and
     *                            pass it directly as argument and will be automatically formatted for the request - [object]
     *                        </li>
     *                     </ul>
     * @return scoped access token as {@link ScopedAccessToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#create-a-scoped-access-token">
     * Create a scoped access token</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/applications/{client_id}/token/scoped")
    public ScopedAccessToken createScopedAccessToken(String clientId, String accessToken, Params bodyParams) throws Exception {
        return createScopedAccessToken(clientId, accessToken, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to use a non-scoped user-to-server OAuth access token to create a repository scoped and/or permission
     * scoped user-to-server OAuth access token. You can specify which repositories the token can access and which
     * permissions are granted to the token. You must use Basic Authentication when accessing this endpoint,
     * using the OAuth application's {@code "client_id"} and {@code "client_secret"} as the username and password.
     * Invalid tokens will return 404 NOT FOUND
     *
     * @param clientId:    the client ID of the GitHub app
     * @param accessToken: the OAuth access token used to authenticate to the GitHub API
     * @param bodyParams:  extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "target"} -> the name of the user or organization to scope the user-to-server access token to. <br>
     *                            <strong>Required</strong> unless {@code "target_id"} is specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "target_id"} -> the ID of the user or organization to scope the user-to-server
     *                            access token to. <br>
     *                            <strong>Required</strong> unless {@code "target"} is specified - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "repositories"} -> the list of repository names to scope the user-to-server access token to.
     *                            {@code "repositories"} may not be specified if {@code "repository_ids"} is specified - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "repository_ids"} -> the list of repository IDs to scope the user-to-server access token to.
     *                            {@code "repository_ids"} may not be specified if {@code "repositories"} is specified - [array of integers]
     *                        </li>
     *                        <li>
     *                            {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                            you can use the {@link AppPermissions} custom object to create the permissions and
     *                            pass it directly as argument and will be automatically formatted for the request - [object]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scoped access token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/oauth-applications#create-a-scoped-access-token">
     * Create a scoped access token</a>
     **/
    @RequestPath(method = POST, path = "/applications/{client_id}/token/scoped")
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
