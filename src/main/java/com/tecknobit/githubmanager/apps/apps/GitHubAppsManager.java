package com.tecknobit.githubmanager.apps.apps;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.apps.apps.records.InstallationAccessToken;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubAppsManager} class is useful to manage all GitHub's apps endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps">
 * GitHub Apps</a>
 * @see GitHubManager
 **/
public class GitHubAppsManager extends GitHubManager {

    /**
     * {@code APP_PATH} constant for {@code "app"} path
     **/
    public static final String APP_PATH = "app";

    /**
     * {@code APP_MANIFESTS_PATH} constant for {@code "app-manifests/"} path
     **/
    public static final String APP_MANIFESTS_PATH = "app-manifests/";

    /**
     * {@code CONVERSIONS_PATH} constant for {@code "/conversions"} path
     **/
    public static final String CONVERSIONS_PATH = "/conversions";

    /**
     * {@code APP_INSTALLATIONS_PATH} constant for {@code "app/installations"} path
     **/
    public static final String APP_INSTALLATIONS_PATH = APP_PATH + "/installations";

    /**
     * {@code ACCESS_TOKENS_PATH} constant for {@code "/access_tokens"} path
     **/
    public static final String ACCESS_TOKENS_PATH = "/access_tokens";

    /**
     * {@code SUSPENDED_PATH} constant for {@code "/suspended"} path
     **/
    public static final String SUSPENDED_PATH = "/suspended";

    /**
     * {@code APPS_PATH} constant for {@code "apps/"} path
     **/
    public static final String APPS_PATH = "apps/";

    /**
     * {@code INSTALLATION_PATH} constant for {@code "/installation"} path
     **/
    public static final String INSTALLATION_PATH = "/installation";

    /**
     * Constructor to init a {@link GitHubAppsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubAppsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubAppsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubAppsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubAppsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubAppsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAppsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubAppsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAppsManager} <br>
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
    public GitHubAppsManager() {
        super();
    }

    public GitHubApp getAuthenticatedApp() throws Exception {
        return getAuthenticatedApp(LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedApp(ReturnFormat format) throws Exception {
        return returnGitHubApp(sendGetRequest(APP_PATH), format);
    }

    public GitHubApp createAuthenticatedAppFromManifest(String code) throws Exception {
        return createAuthenticatedAppFromManifest(code, LIBRARY_OBJECT);
    }

    public <T> T createAuthenticatedAppFromManifest(String code, ReturnFormat format) throws Exception {
        return returnGitHubApp(sendPostRequest(APP_MANIFESTS_PATH + code + CONVERSIONS_PATH, null),
                format);
    }

    public Collection<Installation> getInstallationsList() throws Exception {
        return getInstallationsList(LIBRARY_OBJECT);
    }

    public <T> T getInstallationsList(ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(APP_INSTALLATIONS_PATH), format);
    }

    public Collection<Installation> getInstallationsList(Params queryParams) throws Exception {
        return getInstallationsList(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getInstallationsList(Params queryParams, ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(APP_INSTALLATIONS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create an installations list
     *
     * @param installationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnInstallationsList(String installationsResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(installationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Installation> installations = new ArrayList<>();
                JSONArray jInstallations = new JSONArray(installationsResponse);
                for (int j = 0; j < jInstallations.length(); j++)
                    installations.add(new Installation(jInstallations.getJSONObject(j)));
                return (T) installations;
            default:
                return (T) installationsResponse;
        }
    }

    public Installation getInstallation(long installationId) throws Exception {
        return getInstallation(installationId, LIBRARY_OBJECT);
    }

    public <T> T getInstallation(long installationId, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(APP_INSTALLATIONS_PATH + "/" + installationId), format);
    }

    public boolean deleteInstallation(Installation installation) {
        return deleteInstallation(installation.getId());
    }

    public boolean deleteInstallation(long installationId) {
        try {
            sendDeleteRequest(APP_INSTALLATIONS_PATH + "/" + installationId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public InstallationAccessToken createInstallationAccessToken(Installation installation,
                                                                 Params bodyParams) throws Exception {
        return createInstallationAccessToken(installation.getId(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createInstallationAccessToken(Installation installation, Params bodyParams,
                                               ReturnFormat format) throws Exception {
        return createInstallationAccessToken(installation.getId(), bodyParams, format);
    }

    public InstallationAccessToken createInstallationAccessToken(long installationId, Params bodyParams) throws Exception {
        return createInstallationAccessToken(installationId, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createInstallationAccessToken(long installationId, Params bodyParams, ReturnFormat format) throws Exception {
        String installationTokenResponse = sendPostRequest(APP_INSTALLATIONS_PATH + "/" + installationId +
                ACCESS_TOKENS_PATH, bodyParams);
        switch (format) {
            case JSON:
                return (T) new JSONObject(installationTokenResponse);
            case LIBRARY_OBJECT:
                return (T) new InstallationAccessToken(new JSONObject(installationTokenResponse));
            default:
                return (T) installationTokenResponse;
        }
    }

    public boolean suspendAppInstallation(Installation installation) {
        return suspendAppInstallation(installation.getId());
    }

    public boolean suspendAppInstallation(long installationId) {
        try {
            sendPutRequest(APP_INSTALLATIONS_PATH + "/" + installationId + SUSPENDED_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public boolean unsuspendAppInstallation(Installation installation) {
        return unsuspendAppInstallation(installation.getId());
    }

    public boolean unsuspendAppInstallation(long installationId) {
        try {
            sendDeleteRequest(APP_INSTALLATIONS_PATH + "/" + installationId + SUSPENDED_PATH);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public GitHubApp getApp(String appSlug) throws Exception {
        return getApp(appSlug, LIBRARY_OBJECT);
    }

    public <T> T getApp(String appSlug, ReturnFormat format) throws Exception {
        return returnGitHubApp(sendGetRequest(APPS_PATH + appSlug), format);
    }

    /**
     * Method to create a GitHub app
     *
     * @param appResponse: obtained from GitHub's response
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return GitHub app as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGitHubApp(String appResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(appResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubApp(new JSONObject(appResponse));
            default:
                return (T) appResponse;
        }
    }

    public Installation getOrganizationInstallation(Organization org) throws Exception {
        return getOrganizationInstallation(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInstallation(Organization org, ReturnFormat format) throws Exception {
        return getOrganizationInstallation(org.getLogin(), format);
    }

    public Installation getOrganizationInstallation(String org) throws Exception {
        return getOrganizationInstallation(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInstallation(String org, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(ORGS_PATH + org + INSTALLATION_PATH), format);
    }

    public Installation getRepositoryInstallation(Repository repository) throws Exception {
        return getRepositoryInstallation(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryInstallation(Repository repository, ReturnFormat format) throws Exception {
        return getRepositoryInstallation(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public Installation getRepositoryInstallation(String owner, String repo) throws Exception {
        return getRepositoryInstallation(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryInstallation(String owner, String repo, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(REPOS_PATH + owner + "/" + repo + INSTALLATION_PATH), format);
    }

    public Installation getUserInstallation(String username) throws Exception {
        return getUserInstallation(username, LIBRARY_OBJECT);
    }

    public <T> T getUserInstallation(String username, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(USERS_PATH + username + INSTALLATION_PATH), format);
    }

    /**
     * Method to create an installation
     *
     * @param installationResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     **/
    @Returner
    private <T> T returnInstallation(String installationResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(installationResponse);
            case LIBRARY_OBJECT:
                return (T) new Installation(new JSONObject(installationResponse));
            default:
                return (T) installationResponse;
        }
    }

}
