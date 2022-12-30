package com.tecknobit.githubmanager.apps.installations;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.apps.installations.records.InstallationsList;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.repository.RepositoriesList.returnRepositoriesList;

/**
 * The {@code GitHubInstallationsManager} class is useful to manage all GitHub's installations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations">
 * GitHub App installations</a>
 * @see GitHubManager
 **/
public class GitHubInstallationsManager extends GitHubManager {

    /**
     * {@code INSTALLATION_QUERY_PATH} constant for {@code "installation/"} path
     **/
    public static final String INSTALLATION_QUERY_PATH = "installation/";

    /**
     * {@code INSTALLATION_REPOSITORIES_PATH} constant for {@code "installation/repositories"} path
     **/
    public static final String INSTALLATION_REPOSITORIES_PATH = INSTALLATION_QUERY_PATH + "repositories";

    /**
     * {@code INSTALLATION_TOKEN_PATH} constant for {@code "installation/token"} path
     **/
    public static final String INSTALLATION_TOKEN_PATH = INSTALLATION_QUERY_PATH + "token";

    /**
     * {@code USER_INSTALLATIONS_PATH} constant for {@code "user/installations"} path
     **/
    public static final String USER_INSTALLATIONS_PATH = "user/installations";

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubInstallationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubInstallationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubInstallationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubInstallationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager} <br>
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
    public GitHubInstallationsManager() {
        super();
    }

    /**
     * Method to list repositories that an app installation can access. <br>
     * You must use an installation access token to access this endpoint <br>
     * Any params required
     *
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-app-installation">
     * List repositories accessible to the app installation</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/installation/repositories")
    public RepositoriesList getRepositoriesAppAccessible() throws IOException {
        return getRepositoriesAppAccessible(LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that an app installation can access. <br>
     * You must use an installation access token to access this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-app-installation">
     * List repositories accessible to the app installation</a>
     **/
    @RequestPath(method = GET, path = "/installation/repositories")
    public <T> T getRepositoriesAppAccessible(ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(INSTALLATION_REPOSITORIES_PATH), format);
    }

    /**
     * Method to list repositories that an app installation can access. <br>
     * You must use an installation access token to access this endpoint
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-app-installation">
     * List repositories accessible to the app installation</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/installation/repositories")
    public RepositoriesList getRepositoriesAppAccessible(Params queryParams) throws IOException {
        return getRepositoriesAppAccessible(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that an app installation can access. <br>
     * You must use an installation access token to access this endpoint
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-app-installation">
     * List repositories accessible to the app installation</a>
     **/
    @RequestPath(method = GET, path = "/installation/repositories")
    public <T> T getRepositoriesAppAccessible(Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(INSTALLATION_REPOSITORIES_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to revoke the installation token you're using to authenticate as an installation and access this endpoint.
     * Once an installation token is revoked, the token is invalidated and cannot be used.
     * Other endpoints that require the revoked installation token must have a new installation token to work. You can create a new token using the "Create an installation access token for an app" endpoint.
     * You must use an installation access token to access this endpoint
     * Any params required
     *
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#revoke-an-installation-access-token">
     * Revoke an installation access token</a>
     **/
    @RequestPath(method = DELETE, path = "/installation/token")
    public boolean revokeInstallationAccessToken() {
        try {
            sendDeleteRequest(INSTALLATION_TOKEN_PATH);
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
     * Method to lists installations of your GitHub App that the authenticated user has explicit permission
     * ({@code ":read"}, {@code ":write"}, or {@code ":admin"}) to access.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator,
     * and repositories that they can access through an organization membership.
     * You can find the permissions for the installation under the permissions key <br>
     * Any params required
     *
     * @return installations list as {@link InstallationsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-app-installations-accessible-to-the-user-access-token">
     * List app installations accessible to the user access token</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/installations")
    public InstallationsList getAppInstallationsTokenAccessible() throws Exception {
        return getAppInstallationsTokenAccessible(LIBRARY_OBJECT);
    }

    /**
     * Method to lists installations of your GitHub App that the authenticated user has explicit permission
     * ({@code ":read"}, {@code ":write"}, or {@code ":admin"}) to access.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator,
     * and repositories that they can access through an organization membership.
     * You can find the permissions for the installation under the permissions key
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-app-installations-accessible-to-the-user-access-token">
     * List app installations accessible to the user access token</a>
     **/
    @RequestPath(method = GET, path = "/user/installations")
    public <T> T getAppInstallationsTokenAccessible(ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(USER_INSTALLATIONS_PATH), format);
    }

    /**
     * Method to lists installations of your GitHub App that the authenticated user has explicit permission
     * ({@code ":read"}, {@code ":write"}, or {@code ":admin"}) to access.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator,
     * and repositories that they can access through an organization membership.
     * You can find the permissions for the installation under the permissions key
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return installations list as {@link InstallationsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-app-installations-accessible-to-the-user-access-token">
     * List app installations accessible to the user access token</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/installations")
    public InstallationsList getAppInstallationsTokenAccessible(Params queryParams) throws Exception {
        return getAppInstallationsTokenAccessible(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to lists installations of your GitHub App that the authenticated user has explicit permission
     * ({@code ":read"}, {@code ":write"}, or {@code ":admin"}) to access.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator,
     * and repositories that they can access through an organization membership.
     * You can find the permissions for the installation under the permissions key
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-app-installations-accessible-to-the-user-access-token">
     * List app installations accessible to the user access token</a>
     **/
    @RequestPath(method = GET, path = "/user/installations")
    public <T> T getAppInstallationsTokenAccessible(Params queryParams, ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(USER_INSTALLATIONS_PATH + queryParams.createQueryString()),
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
                return (T) new JSONObject(installationsResponse);
            case LIBRARY_OBJECT:
                return (T) new InstallationsList(new JSONObject(installationsResponse));
            default:
                return (T) installationsResponse;
        }
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installation: installation from fetch the list
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public RepositoriesList getRepositoriesUserTokenAccessible(Installation installation) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installation: installation from fetch the list
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public <T> T getRepositoriesUserTokenAccessible(Installation installation, ReturnFormat format) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), format);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installationId: the unique identifier of the installation
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public RepositoriesList getRepositoriesUserTokenAccessible(long installationId) throws IOException {
        return getRepositoriesUserTokenAccessible(installationId, LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installationId: the unique identifier of the installation
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public <T> T getRepositoriesUserTokenAccessible(long installationId, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(USER_INSTALLATIONS_PATH + "/" + installationId +
                REPOSITORIES_PATH), format);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installation: installation from fetch the list
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public RepositoriesList getRepositoriesUserTokenAccessible(Installation installation,
                                                               Params queryParams) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installation: installation from fetch the list
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public <T> T getRepositoriesUserTokenAccessible(Installation installation, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), queryParams, format);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installationId: the unique identifier of the installation
     * @param queryParams:    extra query params not mandatory, keys accepted are:
     *                        <ul>
     *                           <li>
     *                               {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                           </li>
     *                           <li>
     *                               {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                           </li>
     *                        </ul>
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public RepositoriesList getRepositoriesUserTokenAccessible(long installationId, Params queryParams) throws IOException {
        return getRepositoriesUserTokenAccessible(installationId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to list repositories that the authenticated user has explicit permission ({@code ":read"}, {@code ":write"},
     * or {@code ":admin"}) to access for an installation.
     * The authenticated user has explicit permission to access repositories they own, repositories where they are a collaborator, and repositories that they can access through an organization membership.
     * You must use a user-to-server OAuth access token, created for a user who has authorized your GitHub App, to access this endpoint.
     * The access the user has to each repository is included in the hash under the permissions key
     *
     * @param installationId: the unique identifier of the installation
     * @param queryParams:    extra query params not mandatory, keys accepted are:
     *                        <ul>
     *                           <li>
     *                               {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                           </li>
     *                           <li>
     *                               {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                           </li>
     *                        </ul>
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
     * List repositories accessible to the user access token</a>
     **/
    @RequestPath(method = GET, path = "/user/installations/{installation_id}/repositories")
    public <T> T getRepositoriesUserTokenAccessible(long installationId, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(USER_INSTALLATIONS_PATH + "/" + installationId +
                REPOSITORIES_PATH), format);
    }

    /**
     * Method to add a single repository to an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installation: installation to add the repository
     * @param repository:   repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#add-a-repository-to-an-app-installation">
     * Add a repository to an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean addRepositoryToAppInstallation(Installation installation, Repository repository) {
        return addRepositoryToAppInstallation(installation.getId(), repository.getId());
    }

    /**
     * Method to add a single repository to an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param repository:     repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#add-a-repository-to-an-app-installation">
     * Add a repository to an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean addRepositoryToAppInstallation(long installationId, Repository repository) {
        return addRepositoryToAppInstallation(installationId, repository.getId());
    }

    /**
     * Method to add a single repository to an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installation: installation to add the repository
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#add-a-repository-to-an-app-installation">
     * Add a repository to an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean addRepositoryToAppInstallation(Installation installation, long repositoryId) {
        return addRepositoryToAppInstallation(installation.getId(), repositoryId);
    }

    /**
     * Method to add a single repository to an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param repositoryId:   the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#add-a-repository-to-an-app-installation">
     * Add a repository to an app installation</a>
     **/
    @RequestPath(method = PUT, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean addRepositoryToAppInstallation(long installationId, long repositoryId) {
        try {
            sendPutRequest(USER_INSTALLATIONS_PATH + "/" + installationId + REPOSITORIES_PATH + "/" +
                    repositoryId, null);
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
     * Method to remove a single repository from an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installation: installation from remove the repository
     * @param repository:   repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#remove-a-repository-from-an-app-installation">
     * Remove a repository from an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean removeRepositoryFromAppInstallation(Installation installation, Repository repository) {
        return removeRepositoryFromAppInstallation(installation.getId(), repository.getId());
    }

    /**
     * Method to remove a single repository from an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param repository:     repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#remove-a-repository-from-an-app-installation">
     * Remove a repository from an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean removeRepositoryFromAppInstallation(long installationId, Repository repository) {
        return removeRepositoryFromAppInstallation(installationId, repository.getId());
    }

    /**
     * Method to remove a single repository from an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installation: installation from remove the repository
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#remove-a-repository-from-an-app-installation">
     * Remove a repository from an app installation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean removeRepositoryFromAppInstallation(Installation installation, long repositoryId) {
        return removeRepositoryFromAppInstallation(installation.getId(), repositoryId);
    }

    /**
     * Method to remove a single repository from an installation. The authenticated user must have admin access to the repository.
     * You must use a personal access token (which you can create via the command line or Basic Authentication) to access this endpoint
     *
     * @param installationId: the unique identifier of the installation
     * @param repositoryId:   the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#remove-a-repository-from-an-app-installation">
     * Remove a repository from an app installation</a>
     **/
    @RequestPath(method = DELETE, path = "/user/installations/{installation_id}/repositories/{repository_id}")
    public boolean removeRepositoryFromAppInstallation(long installationId, long repositoryId) {
        try {
            sendDeleteRequest(USER_INSTALLATIONS_PATH + "/" + installationId + REPOSITORIES_PATH + "/" +
                    repositoryId);
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

}
