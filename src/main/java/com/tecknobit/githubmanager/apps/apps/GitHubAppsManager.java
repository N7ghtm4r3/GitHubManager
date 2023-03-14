package com.tecknobit.githubmanager.apps.apps;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.AppPermissions;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.apps.apps.records.InstallationAccessToken;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
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
     * {@code INSTALLATIONS_PATH} constant for {@code "/installations"} path
     **/
    public static final String INSTALLATIONS_PATH = "/installations";

    /**
     * {@code APP_INSTALLATIONS_PATH} constant for {@code "app/installations"} path
     **/
    public static final String APP_INSTALLATIONS_PATH = APP_PATH + INSTALLATIONS_PATH;

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
     * No-any params required
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

    /**
     * Method to return the GitHub App associated with the authentication credentials used
     * For more details about your app's installations, see the "List installations for the authenticated app" endpoint.
     * You must use a JWT to access this endpoint <br>
     * No-any params required
     *
     * @return GitHub app as {@link GitHubApp} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-the-authenticated-app">
     * Get the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app")
    public GitHubApp getAuthenticatedApp() throws Exception {
        return getAuthenticatedApp(LIBRARY_OBJECT);
    }

    /**
     * Method to return the GitHub App associated with the authentication credentials used
     * For more details about your app's installations, see the "List installations for the authenticated app" endpoint.
     * You must use a JWT to access this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return GitHub app as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-the-authenticated-app">
     * Get the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/app")
    public <T> T getAuthenticatedApp(ReturnFormat format) throws Exception {
        return returnGitHubApp(sendGetRequest(APP_PATH), format);
    }

    /**
     * Method to complete the handshake necessary when implementing the GitHub App Manifest flow. When you create a
     * GitHub App with the manifest flow, you receive a temporary code used to retrieve the GitHub App's id,
     * pem (private key), and webhook_secret
     *
     * @param code: code to use in the handshake operation
     * @return GitHub app as {@link GitHubApp} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-a-github-app-from-a-manifest">
     * Create a GitHub App from a manifest</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/app-manifests/{code}/conversions")
    public GitHubApp createAuthenticatedAppFromManifest(String code) throws Exception {
        return createAuthenticatedAppFromManifest(code, LIBRARY_OBJECT);
    }

    /**
     * Method to complete the handshake necessary when implementing the GitHub App Manifest flow. When you create a
     * GitHub App with the manifest flow, you receive a temporary code used to retrieve the GitHub App's id,
     * pem (private key), and webhook_secret
     *
     * @param code:   code to use in the handshake operation
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return GitHub app as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-a-github-app-from-a-manifest">
     * Create a GitHub App from a manifest</a>
     **/
    @RequestPath(method = POST, path = "/app-manifests/{code}/conversions")
    public <T> T createAuthenticatedAppFromManifest(String code, ReturnFormat format) throws Exception {
        return returnGitHubApp(sendPostRequest(APP_MANIFESTS_PATH + code + CONVERSIONS_PATH, null),
                format);
    }

    /**
     * Method to get an installations list for the authenticated app <br>
     * You must use a JWT to access this endpoint. <br>
     * The permissions the installation has are included under the permissions key <br>
     * No-any params required
     *
     * @return installations list as {@link ArrayList} of {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#list-installations-for-the-authenticated-app">
     * List installations for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/installations")
    public ArrayList<Installation> getInstallationsList() throws Exception {
        return getInstallationsList(LIBRARY_OBJECT);
    }

    /**
     * Method to get an installations list for the authenticated app <br>
     * You must use a JWT to access this endpoint. <br>
     * The permissions the installation has are included under the permissions key
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#list-installations-for-the-authenticated-app">
     * List installations for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/app/installations")
    public <T> T getInstallationsList(ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(APP_INSTALLATIONS_PATH), format);
    }

    /**
     * Method to get an installations list for the authenticated app <br>
     * You must use a JWT to access this endpoint. <br>
     * The permissions the installation has are included under the permissions key
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "outdated"} -> outdated value - [string]
     *                        </li>
     *                     </ul>
     * @return installations list as {@link ArrayList} of {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#list-installations-for-the-authenticated-app">
     * List installations for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/installations")
    public ArrayList<Installation> getInstallationsList(Params queryParams) throws Exception {
        return getInstallationsList(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an installations list for the authenticated app <br>
     * You must use a JWT to access this endpoint. <br>
     * The permissions the installation has are included under the permissions key
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "outdated"} -> outdated value - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#list-installations-for-the-authenticated-app">
     * List installations for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/app/installations")
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

    /**
     * Method to enable an authenticated GitHub App to find an installation's information using the installation id. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-installation-for-the-authenticated-app">
     * Get an installation for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/installations/{installation_id}")
    public Installation getInstallation(long installationId) throws Exception {
        return getInstallation(installationId, LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find an installation's information using the installation id. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-installation-for-the-authenticated-app">
     * Get an installation for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/app/installations/{installation_id}")
    public <T> T getInstallation(long installationId, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(APP_INSTALLATIONS_PATH + "/" + installationId), format);
    }

    /**
     * Method to uninstall a GitHub App on a user, organization, or business account.
     * If you prefer to temporarily suspend an app's access to your account's resources, then we recommend the
     * {@link #suspendAppInstallation(Installation)} method <br>
     * You must use a JWT to access this endpoint
     *
     * @param installation: installation to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#delete-an-installation-for-the-authenticated-app">
     * Delete an installation for the authenticated app</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/app/installations/{installation_id}")
    public boolean deleteInstallation(Installation installation) {
        return deleteInstallation(installation.getId());
    }

    /**
     * Method to uninstall a GitHub App on a user, organization, or business account.
     * If you prefer to temporarily suspend an app's access to your account's resources, then we recommend the
     * {@link #suspendAppInstallation(Installation)} method <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#delete-an-installation-for-the-authenticated-app">
     * Delete an installation for the authenticated app</a>
     **/
    @RequestPath(method = DELETE, path = "/app/installations/{installation_id}")
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

    /**
     * Method to create an installation access token that enables a GitHub App to make authenticated API requests
     * for the app's installation on an organization or individual account.
     * Installation tokens expire one hour from the time you create them.
     * Using an expired token produces a status code of 401 - Unauthorized, and requires creating a new installation token.
     * By default, the installation token has access to all repositories that the installation can access.
     * To restrict the access to specific repositories, you can provide the repository_ids when creating the token.
     * When you omit {@code "repository_ids"}, the response does not contain the repositories key. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installation: installation from create the access token
     * @param bodyParams:   extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "repositories"} -> list of repository names that the token should have access to
     *                             - [array of strings]
     *                         </li>
     *                         <li>
     *                             {@code "repository_ids"} -> list of repository IDs that the token should have access to
     *                             - [array of integers]
     *                         </li>
     *                         <li>
     *                             {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                             you can use the {@link AppPermissions} custom object to create the permissions and
     *                             pass it directly as argument and will be automatically formatted for the request - [object]
     *                         </li>
     *                      </ul>
     * @return installation access token as {@link InstallationAccessToken} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
     * Create an installation access token for an app</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/app/installations/{installation_id}/access_tokens")
    public InstallationAccessToken createInstallationAccessToken(Installation installation,
                                                                 Params bodyParams) throws Exception {
        return createInstallationAccessToken(installation.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create an installation access token that enables a GitHub App to make authenticated API requests
     * for the app's installation on an organization or individual account.
     * Installation tokens expire one hour from the time you create them.
     * Using an expired token produces a status code of 401 - Unauthorized, and requires creating a new installation token.
     * By default, the installation token has access to all repositories that the installation can access.
     * To restrict the access to specific repositories, you can provide the repository_ids when creating the token.
     * When you omit {@code "repository_ids"}, the response does not contain the repositories key. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installation: installation from create the access token
     * @param bodyParams:   extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "repositories"} -> list of repository names that the token should have access to
     *                             - [array of strings]
     *                         </li>
     *                         <li>
     *                             {@code "repository_ids"} -> list of repository IDs that the token should have access to
     *                             - [array of integers]
     *                         </li>
     *                         <li>
     *                             {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                             you can use the {@link AppPermissions} custom object to create the permissions and
     *                             pass it directly as argument and will be automatically formatted for the request - [object]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return installation access token as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
     * Create an installation access token for an app</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/app/installations/{installation_id}/access_tokens")
    public <T> T createInstallationAccessToken(Installation installation, Params bodyParams,
                                               ReturnFormat format) throws Exception {
        return createInstallationAccessToken(installation.getId(), bodyParams, format);
    }

    /**
     * Method to create an installation access token that enables a GitHub App to make authenticated API requests
     * for the app's installation on an organization or individual account.
     * Installation tokens expire one hour from the time you create them.
     * Using an expired token produces a status code of 401 - Unauthorized, and requires creating a new installation token.
     * By default, the installation token has access to all repositories that the installation can access.
     * To restrict the access to specific repositories, you can provide the repository_ids when creating the token.
     * When you omit {@code "repository_ids"}, the response does not contain the repositories key. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param bodyParams:     extra query params not mandatory, keys accepted are:
     *                        <ul>
     *                           <li>
     *                               {@code "repositories"} -> list of repository names that the token should have access to
     *                               - [array of strings]
     *                           </li>
     *                           <li>
     *                               {@code "repository_ids"} -> list of repository IDs that the token should have access to
     *                               - [array of integers]
     *                           </li>
     *                           <li>
     *                               {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                               you can use the {@link AppPermissions} custom object to create the permissions and
     *                               pass it directly as argument and will be automatically formatted for the request - [object]
     *                           </li>
     *                        </ul>
     * @return installation access token as {@link InstallationAccessToken} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
     * Create an installation access token for an app</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/app/installations/{installation_id}/access_tokens")
    public InstallationAccessToken createInstallationAccessToken(long installationId, Params bodyParams) throws Exception {
        return createInstallationAccessToken(installationId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create an installation access token that enables a GitHub App to make authenticated API requests
     * for the app's installation on an organization or individual account.
     * Installation tokens expire one hour from the time you create them.
     * Using an expired token produces a status code of 401 - Unauthorized, and requires creating a new installation token.
     * By default, the installation token has access to all repositories that the installation can access.
     * To restrict the access to specific repositories, you can provide the repository_ids when creating the token.
     * When you omit {@code "repository_ids"}, the response does not contain the repositories key. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param bodyParams:     extra query params not mandatory, keys accepted are:
     *                        <ul>
     *                           <li>
     *                               {@code "repositories"} -> list of repository names that the token should have access to
     *                               - [array of strings]
     *                           </li>
     *                           <li>
     *                               {@code "repository_ids"} -> list of repository IDs that the token should have access to
     *                               - [array of integers]
     *                           </li>
     *                           <li>
     *                               {@code "permissions"} -> the permissions granted to the user-to-server access token,
     *                               you can use the {@link AppPermissions} custom object to create the permissions and
     *                               pass it directly as argument and will be automatically formatted for the request - [object]
     *                           </li>
     *                        </ul>
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return installation access token as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
     * Create an installation access token for an app</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/app/installations/{installation_id}/access_tokens")
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

    /**
     * Method to suspend a GitHub App on a user, organization, or business account, which blocks the app from accessing
     * the account's resources. When a GitHub App is suspended, the app's access to the GitHub API or webhook events
     * is blocked for that account. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installation: installation to suspend
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#suspend-an-app-installation">
     * Suspend an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/app/installations/{installation_id}/suspended")
    public boolean suspendAppInstallation(Installation installation) {
        return suspendAppInstallation(installation.getId());
    }

    /**
     * Method to suspend a GitHub App on a user, organization, or business account, which blocks the app from accessing
     * the account's resources. When a GitHub App is suspended, the app's access to the GitHub API or webhook events
     * is blocked for that account. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#suspend-an-app-installation">
     * Suspend an app installation</a>
     **/
    @RequestPath(method = PUT, path = "/app/installations/{installation_id}/suspended")
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

    /**
     * Method to removes a GitHub App installation suspension. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installation: installation to unsuspend
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#unsuspend-an-app-installation">
     * Suspend an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/app/installations/{installation_id}/suspended")
    public boolean unsuspendAppInstallation(Installation installation) {
        return unsuspendAppInstallation(installation.getId());
    }

    /**
     * Method to removes a GitHub App installation suspension. <br>
     * You must use a JWT to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#unsuspend-an-app-installation">
     * Suspend an app installation</a>
     **/
    @RequestPath(method = DELETE, path = "/app/installations/{installation_id}/suspended")
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

    /**
     * Method to get an app
     *
     * @param appSlug: the app slug of the app
     * @return GitHub app as {@link GitHubApp} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-app">
     * Get an app</a>
     * @implSpec The {@code ":app_slug"} is just the URL-friendly name of your GitHub App.
     * You can find this on the settings page for your GitHub App (e.g., <a href="https://github.com/settings/apps/:app_slug">app_slug</a>).
     * If the GitHub App you specify is public, you can access this endpoint without authenticating.
     * If the GitHub App you specify is private, you must authenticate with a personal access token or
     * an installation access token to access this endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/apps/{app_slug}")
    public GitHubApp getApp(String appSlug) throws Exception {
        return getApp(appSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get an app
     *
     * @param appSlug: the app slug of the app
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return GitHub app as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-app">
     * Get an app</a>
     * @implSpec The {@code ":app_slug"} is just the URL-friendly name of your GitHub App.
     * You can find this on the settings page for your GitHub App (e.g., <a href="https://github.com/settings/apps/:app_slug">app_slug</a>).
     * If the GitHub App you specify is public, you can access this endpoint without authenticating.
     * If the GitHub App you specify is private, you must authenticate with a personal access token or
     * an installation access token to access this endpoint
     **/
    @RequestPath(method = GET, path = "/apps/{app_slug}")
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

    /**
     * Method to enable an authenticated GitHub App to find the organization's installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param org: the organization from fetch the installation
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-organization-installation-for-the-authenticated-app">
     * Get an organization installation for the authenticated app</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installation")
    public Installation getOrganizationInstallation(Organization org) throws Exception {
        return getOrganizationInstallation(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find the organization's installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param org:    the organization from fetch the installation
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-organization-installation-for-the-authenticated-app">
     * Get an organization installation for the authenticated app</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installation")
    public <T> T getOrganizationInstallation(Organization org, ReturnFormat format) throws Exception {
        return getOrganizationInstallation(org.getLogin(), format);
    }

    /**
     * Method to enable an authenticated GitHub App to find the organization's installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-organization-installation-for-the-authenticated-app">
     * Get an organization installation for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/installation")
    public Installation getOrganizationInstallation(String org) throws Exception {
        return getOrganizationInstallation(org, LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find the organization's installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-an-organization-installation-for-the-authenticated-app">
     * Get an organization installation for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/installation")
    public <T> T getOrganizationInstallation(String org, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(ORGS_PATH + org + INSTALLATION_PATH), format);
    }

    /**
     * Method to enable an authenticated GitHub App to find the repository's installation information.
     * The installation's account type will be either an organization or a user account,
     * depending on which account the repository belongs to <br>
     * You must use a JWT to access this endpoint
     *
     * @param repository: the repository from fetch the installation
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-repository-installation-for-the-authenticated-app">
     * Get a repository installation for the authenticated app</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/installation")
    public Installation getRepositoryInstallation(Repository repository) throws Exception {
        return getRepositoryInstallation(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find the repository's installation information.
     * The installation's account type will be either an organization or a user account,
     * depending on which account the repository belongs to <br>
     * You must use a JWT to access this endpoint
     *
     * @param repository: the repository from fetch the installation
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-repository-installation-for-the-authenticated-app">
     * Get a repository installation for the authenticated app</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/installation")
    public <T> T getRepositoryInstallation(Repository repository, ReturnFormat format) throws Exception {
        return getRepositoryInstallation(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to enable an authenticated GitHub App to find the repository's installation information.
     * The installation's account type will be either an organization or a user account,
     * depending on which account the repository belongs to <br>
     * You must use a JWT to access this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-repository-installation-for-the-authenticated-app">
     * Get a repository installation for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/installation")
    public Installation getRepositoryInstallation(String owner, String repo) throws Exception {
        return getRepositoryInstallation(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find the repository's installation information.
     * The installation's account type will be either an organization or a user account,
     * depending on which account the repository belongs to <br>
     * You must use a JWT to access this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-repository-installation-for-the-authenticated-app">
     * Get a repository installation for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/installation")
    public <T> T getRepositoryInstallation(String owner, String repo, ReturnFormat format) throws Exception {
        return returnInstallation(sendGetRequest(REPOS_PATH + owner + "/" + repo + INSTALLATION_PATH), format);
    }

    /**
     * Method to enable an authenticated GitHub App to find the user’s installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param username: the handle for the GitHub user account
     * @return installation as {@link Installation} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-user-installation-for-the-authenticated-app">
     * Get a user installation for the authenticated app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/installation")
    public Installation getUserInstallation(String username) throws Exception {
        return getUserInstallation(username, LIBRARY_OBJECT);
    }

    /**
     * Method to enable an authenticated GitHub App to find the user’s installation information <br>
     * You must use a JWT to access this endpoint
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return installation as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#get-a-user-installation-for-the-authenticated-app">
     * Get a user installation for the authenticated app</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/installation")
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
