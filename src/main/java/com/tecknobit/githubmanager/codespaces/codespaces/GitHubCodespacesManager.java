package com.tecknobit.githubmanager.codespaces.codespaces;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codespaces.codespaces.records.CodespaceExportDetails;
import com.tecknobit.githubmanager.codespaces.codespaces.records.DevContainersList;
import com.tecknobit.githubmanager.codespaces.records.Codespace;
import com.tecknobit.githubmanager.codespaces.records.CodespacesList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codespaces.records.Codespace.returnCodespace;
import static com.tecknobit.githubmanager.codespaces.records.CodespacesList.returnCodespacesList;

/**
 * The {@code GitHubCodespacesManager} class is useful to manage all GitHub's codespaces endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces">
 * Codespaces</a>
 * @see GitHubManager
 **/
public class GitHubCodespacesManager extends GitHubManager {

    /**
     * {@code CODESPACES_PATH} constant for {@code "/codespaces"} path
     **/
    public static final String CODESPACES_PATH = "/codespaces";

    /**
     * {@code CODESPACES_DEVCONTAINERS_PATH} constant for {@code "/codespaces/devcontainers"} path
     **/
    public static final String CODESPACES_DEVCONTAINERS_PATH = CODESPACES_PATH + "/devcontainers";

    /**
     * {@code CODESPACES_NEW_PATH} constant for {@code "/codespaces/new"} path
     **/
    public static final String CODESPACES_NEW_PATH = CODESPACES_PATH + "/new";

    /**
     * {@code PULLS_QUERY_PATH} constant for {@code "/pulls/"} path
     **/
    public static final String PULLS_QUERY_PATH = PULLS_PATH + "/";

    /**
     * {@code EXPORTS_PATH} constant for {@code "/exports"} path
     **/
    public static final String EXPORTS_PATH = "/exports";

    /**
     * {@code START_PATH} constant for {@code "/start"} path
     **/
    public static final String START_PATH = "/start";

    /**
     * {@code STOP_PATH} constant for {@code "/stop"} path
     **/
    public static final String STOP_PATH = "/stop";

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCodespacesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCodespacesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCodespacesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCodespacesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager} <br>
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
    public GitHubCodespacesManager() {
        super();
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public CodespacesList getRepositoryCodespaces(Repository repository) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T getRepositoryCodespaces(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public CodespacesList getRepositoryCodespaces(String owner, String repo) throws IOException {
        return getRepositoryCodespaces(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T getRepositoryCodespaces(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH), format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public CodespacesList getRepositoryCodespaces(Repository repository, Params queryParams) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
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
     * @return codespaces list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T getRepositoryCodespaces(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public CodespacesList getRepositoryCodespaces(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryCodespaces(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified repository and the authenticated user. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @return codespaces list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T getRepositoryCodespaces(String owner, String repo, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a codespace owned by the authenticated user in the specified repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-in-a-repository">
     * Create a codespace in a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/codespaces")
    public Codespace createRepositoryCodespace(Repository repository, Params bodyParams) throws IOException {
        return createRepositoryCodespace(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user in the specified repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-in-a-repository">
     * Create a codespace in a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T createRepositoryCodespace(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createRepositoryCodespace(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to create a codespace owned by the authenticated user in the specified repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-in-a-repository">
     * Create a codespace in a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/codespaces")
    public Codespace createRepositoryCodespace(String owner, String repo, Params bodyParams) throws IOException {
        return createRepositoryCodespace(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user in the specified repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-in-a-repository">
     * Create a codespace in a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/codespaces")
    public <T> T createRepositoryCodespace(String owner, String repo, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH, bodyParams),
                format);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public DevContainersList getRepositoryDevContainers(Repository repository) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public <T> T getRepositoryDevContainers(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public DevContainersList getRepositoryDevContainers(String owner, String repo) throws IOException {
        return getRepositoryDevContainers(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public <T> T getRepositoryDevContainers(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_DEVCONTAINERS_PATH),
                format);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public DevContainersList getRepositoryDevContainers(Repository repository, Params queryParams) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
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
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public <T> T getRepositoryDevContainers(Repository repository, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public DevContainersList getRepositoryDevContainers(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryDevContainers(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the devcontainer.json files associated with a specified repository and the authenticated user.
     * These files specify launchpoint configurations for codespaces created within the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
     * List devcontainer configurations in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/devcontainers")
    public <T> T getRepositoryDevContainers(String owner, String repo, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_DEVCONTAINERS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public DevContainersList getDefaultCodespaceAttributes(Repository repository) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public <T> T getDefaultCodespaceAttributes(Repository repository, ReturnFormat format) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public DevContainersList getDefaultCodespaceAttributes(String owner, String repo) throws IOException {
        return getDefaultCodespaceAttributes(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public <T> T getDefaultCodespaceAttributes(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_NEW_PATH),
                format);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "ref"} -> the branch or commit to check for a default devcontainer path. If not
     *                            specified, the default branch will be checked - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> an alternative IP for default location auto-detection, such as
     *                            when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public DevContainersList getDefaultCodespaceAttributes(Repository repository, Params queryParams) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "ref"} -> the branch or commit to check for a default devcontainer path. If not
     *                            specified, the default branch will be checked - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> an alternative IP for default location auto-detection, such as
     *                            when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public <T> T getDefaultCodespaceAttributes(Repository repository, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "ref"} -> the branch or commit to check for a default devcontainer path. If not
     *                            specified, the default branch will be checked - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> an alternative IP for default location auto-detection, such as
     *                            when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @return devcontainers list as {@link DevContainersList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public DevContainersList getDefaultCodespaceAttributes(String owner, String repo, Params queryParams) throws IOException {
        return getDefaultCodespaceAttributes(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the default attributes for codespaces created by the user with the repository. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "ref"} -> the branch or commit to check for a default devcontainer path. If not
     *                            specified, the default branch will be checked - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> an alternative IP for default location auto-detection, such as
     *                            when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
     * Get default attributes for a codespace</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/new")
    public <T> T getDefaultCodespaceAttributes(String owner, String repo, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_NEW_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a dev containers list
     *
     * @param devContainersResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDevContainers(String devContainersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(devContainersResponse);
            case LIBRARY_OBJECT:
                return (T) new DevContainersList(new JSONObject(devContainersResponse));
            default:
                return (T) devContainersResponse;
        }
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
     * Create a codespace from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/codespaces")
    public Codespace createCodespaceFromPullRequest(Repository repository, long pullNumber,
                                                    Params bodyParams) throws IOException {
        return createCodespaceFromPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
     * Create a codespace from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/codespaces")
    public <T> T createCodespaceFromPullRequest(Repository repository, long pullNumber, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return createCodespaceFromPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, format);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
     * Create a codespace from a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/codespaces")
    public Codespace createCodespaceFromPullRequest(String owner, String repo, long pullNumber,
                                                    Params bodyParams) throws IOException {
        return createCodespaceFromPullRequest(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
     * Create a codespace from a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/codespaces")
    public <T> T createCodespaceFromPullRequest(String owner, String repo, long pullNumber, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_QUERY_PATH + pullNumber
                + CODESPACES_PATH, bodyParams), format);
    }

    /**
     * Method to get the list of the authenticated user's codespaces <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint <br>
     * Any params required
     *
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-for-the-authenticated-user">
     * List codespaces for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces")
    public CodespacesList getUserCodespaces() throws IOException {
        return getUserCodespaces(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the authenticated user's codespaces <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-for-the-authenticated-user">
     * List codespaces for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces")
    public <T> T getUserCodespaces(ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(USER_CODESPACES_PATH), format);
    }

    /**
     * Method to get the list of the authenticated user's codespaces <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
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
     * @return codespaces list as {@link CodespacesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-for-the-authenticated-user">
     * List codespaces for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces")
    public CodespacesList getUserCodespaces(Params queryParams) throws IOException {
        return getUserCodespaces(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the authenticated user's codespaces <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
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
     * @return codespaces list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-for-the-authenticated-user">
     * List codespaces for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces")
    public <T> T getUserCodespaces(Params queryParams, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(USER_CODESPACES_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-for-the-authenticated-user">
     * Create a codespace for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces")
    public Codespace createUserCodespace(Repository repository, Params bodyParams) throws IOException {
        return createUserCodespace(repository.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repository: repository where create the codespace
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "machine"} -> machine type to use for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                           from devcontainer.json - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "working_directory"} -> working directory for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                           in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-for-the-authenticated-user">
     * Create a codespace for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces")
    public <T> T createUserCodespace(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createUserCodespace(repository.getId(), bodyParams, format);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repositoryId: repository id for this codespace
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                             - [string]
     *                         </li>
     *                         <li>
     *                             {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                         </li>
     *                         <li>
     *                             {@code "machine"} -> machine type to use for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                             - [string]
     *                         </li>
     *                         <li>
     *                             {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                             from devcontainer.json - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "working_directory"} -> working directory for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                             - [integer]
     *                         </li>
     *                         <li>
     *                             {@code "display_name"} -> display name for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                             in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                         </li>
     *                      </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-for-the-authenticated-user">
     * Create a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/codespaces")
    public Codespace createUserCodespace(long repositoryId, Params bodyParams) throws IOException {
        return createUserCodespace(repositoryId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param repositoryId: repository id for this codespace
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "ref"} -> Git ref (typically a branch name) for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                             - [string]
     *                         </li>
     *                         <li>
     *                             {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                         </li>
     *                         <li>
     *                             {@code "machine"} -> machine type to use for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "devcontainer_path"} -> path to devcontainer.json config to use for this codespace
     *                             - [string]
     *                         </li>
     *                         <li>
     *                             {@code "multi_repo_permissions_opt_out"} -> whether to authorize requested permissions
     *                             from devcontainer.json - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "working_directory"} -> working directory for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "idle_timeout_minutes"} -> time in minutes before codespace stops from inactivity
     *                             - [integer]
     *                         </li>
     *                         <li>
     *                             {@code "display_name"} -> display name for this codespace - [string]
     *                         </li>
     *                         <li>
     *                             {@code "retention_period_minutes"} -> duration in minutes after codespace has gone idle
     *                             in which it will be deleted. Must be integer minutes between 0 and 43200 (30 days) - [integer]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-for-the-authenticated-user">
     * Create a codespace for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/codespaces")
    public <T> T createUserCodespace(long repositoryId, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("repository_id", repositoryId);
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH, bodyParams), format);
    }

    /**
     * Method to get information about a user's codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-a-codespace-for-the-authenticated-user">
     * Get a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}")
    public Codespace getUserCodespace(String codespaceName) throws IOException {
        return getUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a user's codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-a-codespace-for-the-authenticated-user">
     * Get a codespace for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}")
    public <T> T getUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendGetRequest(USER_CODESPACES_PATH + "/" + codespaceName), format);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespace:  the codespace update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "machine"} -> a valid machine to transition this codespace to - [string]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "recent_folders"} -> recently opened folders inside the codespace. It is currently
     *                           used by the clients to determine the folder path to load the codespace in - [array of strings]
     *                       </li>
     *                    </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#update-a-codespace-for-the-authenticated-user">
     * Update a codespace for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/user/codespaces")
    public Codespace updateUserCodespace(Codespace codespace, Params bodyParams) throws IOException {
        return updateUserCodespace(codespace.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespace:  the codespace update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "machine"} -> a valid machine to transition this codespace to - [string]
     *                       </li>
     *                       <li>
     *                           {@code "display_name"} -> display name for this codespace - [string]
     *                       </li>
     *                       <li>
     *                           {@code "recent_folders"} -> recently opened folders inside the codespace. It is currently
     *                           used by the clients to determine the folder path to load the codespace in - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#update-a-codespace-for-the-authenticated-user">
     * Update a codespace for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/user/codespaces")
    public <T> T updateUserCodespace(Codespace codespace, Params bodyParams, ReturnFormat format) throws IOException {
        return updateUserCodespace(codespace.getName(), bodyParams, format);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param bodyParams:    extra body params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "machine"} -> a valid machine to transition this codespace to - [string]
     *                          </li>
     *                          <li>
     *                              {@code "display_name"} -> display name for this codespace - [string]
     *                          </li>
     *                          <li>
     *                              {@code "recent_folders"} -> recently opened folders inside the codespace. It is currently
     *                              used by the clients to determine the folder path to load the codespace in - [array of strings]
     *                          </li>
     *                       </ul>
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#update-a-codespace-for-the-authenticated-user">
     * Update a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/user/codespaces")
    public Codespace updateUserCodespace(String codespaceName, Params bodyParams) throws IOException {
        return updateUserCodespace(codespaceName, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a codespace owned by the authenticated user for the specified pull request. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param bodyParams:    extra body params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "machine"} -> a valid machine to transition this codespace to - [string]
     *                          </li>
     *                          <li>
     *                              {@code "display_name"} -> display name for this codespace - [string]
     *                          </li>
     *                          <li>
     *                              {@code "recent_folders"} -> recently opened folders inside the codespace. It is currently
     *                              used by the clients to determine the folder path to load the codespace in - [array of strings]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#update-a-codespace-for-the-authenticated-user">
     * Update a codespace for the authenticated user</a>
     **/
    @RequestPath(method = PATCH, path = "/user/codespaces")
    public <T> T updateUserCodespace(String codespaceName, Params bodyParams, ReturnFormat format) throws IOException {
        return returnCodespace(sendPatchRequest(USER_CODESPACES_PATH + "/" + codespaceName, bodyParams), format);
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespace: codespace to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#delete-a-codespace-for-the-authenticated-user">
     * Delete a codespace for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/codespaces/{codespace_name}")
    public boolean deleteUserCodespace(Codespace codespace) {
        return deleteUserCodespace(codespace.getName());
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have read access to the codespaces repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#delete-a-codespace-for-the-authenticated-user">
     * Delete a codespace for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/codespaces/{codespace_name}")
    public boolean deleteUserCodespace(String codespaceName) {
        try {
            sendDeleteRequest(USER_CODESPACES_PATH + "/" + codespaceName);
            if (apiRequest.getResponseStatusCode() != 202) {
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
     * Method to triggers an export of the specified codespace and returns a URL and ID where the status of the export can be monitored. <br>
     * If changes cannot be pushed to the codespace's repository, they will be pushed to a new or previously-existing fork instead. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to export
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#export-a-codespace-for-the-authenticated-user">
     * Export a codespace for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/exports")
    public CodespaceExportDetails exportUserCodespace(Codespace codespace) throws IOException {
        return exportUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to triggers an export of the specified codespace and returns a URL and ID where the status of the export can be monitored. <br>
     * If changes cannot be pushed to the codespace's repository, they will be pushed to a new or previously-existing fork instead. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to export
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#export-a-codespace-for-the-authenticated-user">
     * Export a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/exports")
    public <T> T exportUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return exportUserCodespace(codespace.getName(), format);
    }

    /**
     * Method to triggers an export of the specified codespace and returns a URL and ID where the status of the export can be monitored. <br>
     * If changes cannot be pushed to the codespace's repository, they will be pushed to a new or previously-existing fork instead. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#export-a-codespace-for-the-authenticated-user">
     * Export a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/exports")
    public CodespaceExportDetails exportUserCodespace(String codespaceName) throws IOException {
        return exportUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to triggers an export of the specified codespace and returns a URL and ID where the status of the export can be monitored. <br>
     * If changes cannot be pushed to the codespace's repository, they will be pushed to a new or previously-existing fork instead. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#export-a-codespace-for-the-authenticated-user">
     * Export a codespace for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/exports")
    public <T> T exportUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespaceExportDetails(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName
                + EXPORTS_PATH, null), format);
    }

    /**
     * Method to get information about an export of a codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to get export information
     * @param exportId:  the ID of the export operation, or latest. Currently only latest is currently supported
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-details-about-a-codespace-export">
     * Get details about a codespace export</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/exports/{export_id}")
    public CodespaceExportDetails getUserCodespaceExportDetails(Codespace codespace, long exportId) throws IOException {
        return getUserCodespaceExportDetails(codespace.getName(), exportId, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about an export of a codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to get export information
     * @param exportId:  the ID of the export operation, or latest. Currently only latest is currently supported
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-details-about-a-codespace-export">
     * Get details about a codespace export</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/exports/{export_id}")
    public <T> T getUserCodespaceExportDetails(Codespace codespace, long exportId, ReturnFormat format) throws IOException {
        return getUserCodespaceExportDetails(codespace.getName(), exportId, format);
    }

    /**
     * Method to get information about an export of a codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param exportId:      the ID of the export operation, or latest. Currently only latest is currently supported
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-details-about-a-codespace-export">
     * Get details about a codespace export</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/exports/{export_id}")
    public CodespaceExportDetails getUserCodespaceExportDetails(String codespaceName, long exportId) throws IOException {
        return getUserCodespaceExportDetails(codespaceName, exportId, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about an export of a codespace <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param exportId:      the ID of the export operation, or latest. Currently only latest is currently supported
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-details-about-a-codespace-export">
     * Get details about a codespace export</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/exports/{export_id}")
    public <T> T getUserCodespaceExportDetails(String codespaceName, long exportId, ReturnFormat format) throws IOException {
        return returnCodespaceExportDetails(sendGetRequest(USER_CODESPACES_PATH + "/" + codespaceName
                + EXPORTS_PATH + "/" + exportId), format);
    }

    /**
     * Method to create a codespace export details
     *
     * @param codespaceExportDetailsResponse: obtained from GitHub's response
     * @param format:                         return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCodespaceExportDetails(String codespaceExportDetailsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespaceExportDetailsResponse);
            case LIBRARY_OBJECT:
                return (T) new CodespaceExportDetails(new JSONObject(codespaceExportDetailsResponse));
            default:
                return (T) codespaceExportDetailsResponse;
        }
    }

    /**
     * Method to start a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to start
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#start-a-codespace-for-the-authenticated-user">
     * Start a codespace for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/start")
    public Codespace startUserCodespace(Codespace codespace) throws IOException {
        return startUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to start a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to start
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#start-a-codespace-for-the-authenticated-user">
     * Start a codespace for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/start")
    public <T> T startUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return startUserCodespace(codespace.getName(), format);
    }

    /**
     * Method to start a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#start-a-codespace-for-the-authenticated-user">
     * Start a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/start")
    public Codespace startUserCodespace(String codespaceName) throws IOException {
        return startUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to start a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#start-a-codespace-for-the-authenticated-user">
     * Start a codespace for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/start")
    public <T> T startUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName + START_PATH, null),
                format);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to stop
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#stop-a-codespace-for-the-authenticated-user">
     * Stop a codespace for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/stop")
    public Codespace stopUserCodespace(Codespace codespace) throws IOException {
        return stopUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespace: codespace to stop
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#stop-a-codespace-for-the-authenticated-user">
     * Stop a codespace for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/stop")
    public <T> T stopUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return stopUserCodespace(codespace.getName(), format);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#stop-a-codespace-for-the-authenticated-user">
     * Stop a codespace for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/stop")
    public Codespace stopUserCodespace(String codespaceName) throws IOException {
        return stopUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_lifecycle_admin"} repository permission to use this
     * endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#stop-a-codespace-for-the-authenticated-user">
     * Stop a codespace for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/codespaces/{codespace_name}/stop")
    public <T> T stopUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName + STOP_PATH, null),
                format);
    }

}
