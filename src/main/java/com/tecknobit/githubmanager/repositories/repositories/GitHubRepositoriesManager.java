package com.tecknobit.githubmanager.repositories.repositories;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.collaborators.records.Collaborator.Affiliation;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.CodeOwnersError;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository.RepoVisibility;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository.RepositorySort;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository.RepositoryType;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository.SecurityAnalysis;
import com.tecknobit.githubmanager.repositories.repositories.records.RepositoryLanguages;
import com.tecknobit.githubmanager.repositories.repositories.records.RepositoryTag;
import com.tecknobit.githubmanager.teams.teams.records.Team;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.DISPATCHES_PATH;
import static com.tecknobit.githubmanager.records.parents.GitHubResponse.returnStringsList;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepositories;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepository;
import static com.tecknobit.githubmanager.teams.teams.records.Team.returnTeamsList;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubRepositoriesManager} class is useful to manage all GitHub's repositories endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos">
 * Repositories</a>
 * @see GitHubManager
 **/
public class GitHubRepositoriesManager extends GitHubManager {

    /**
     * {@code REPOS_QUERY_PATH} constant for {@code "/repos"} path
     **/
    public static final String REPOS_QUERY_PATH = "/repos";

    /**
     * {@code AUTOMATED_SECURITY_FIXES_PATH} constant for {@code "/automated-security-fixes"} path
     **/
    public static final String AUTOMATED_SECURITY_FIXES_PATH = "/automated-security-fixes";

    /**
     * {@code CODEOWNERS_ERRORS_PATH} constant for {@code "/codeowners/errors"} path
     **/
    public static final String CODEOWNERS_ERRORS_PATH = "/codeowners/errors";

    /**
     * {@code CONTRIBUTORS_PATH} constant for {@code "/contributors"} path
     **/
    public static final String CONTRIBUTORS_PATH = "/contributors";

    /**
     * {@code LANGUAGES_PATH} constant for {@code "/languages"} path
     **/
    public static final String LANGUAGES_PATH = "/languages";

    /**
     * {@code TOPICS_PATH} constant for {@code "/topics"} path
     **/
    public static final String TOPICS_PATH = "/topics";

    /**
     * {@code TRANSFER_PATH} constant for {@code "/transfer"} path
     **/
    public static final String TRANSFER_PATH = "/transfer";

    /**
     * {@code VULNERABILITY_ALERTS_PATH} constant for {@code "/vulnerability-alerts"} path
     **/
    public static final String VULNERABILITY_ALERTS_PATH = "/vulnerability-alerts";

    /**
     * {@code GENERATE_PATH} constant for {@code "/generate"} path
     **/
    public static final String GENERATE_PATH = "/generate";

    /**
     * {@code USER_REPOS_PATH} constant for {@code "user/repos"} path
     **/
    public static final String USER_REPOS_PATH = USER_PATH + REPOS_QUERY_PATH;

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepositoriesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepositoriesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepositoriesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepositoriesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager} <br>
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
    public GitHubRepositoriesManager() {
        super();
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org: the organization from fetch the list
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public ArrayList<Repository> getOrganizationRepositories(Organization org) throws IOException {
        return getOrganizationRepositories(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public <T> T getOrganizationRepositories(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationRepositories(org.getLogin(), format);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public ArrayList<Repository> getOrganizationRepositories(String org) throws IOException {
        return getOrganizationRepositories(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public <T> T getOrganizationRepositories(String org, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + REPOS_QUERY_PATH), format);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:         the organization where create the repository
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public ArrayList<Repository> getOrganizationRepositories(Organization org, Params queryParams) throws IOException {
        return getOrganizationRepositories(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:         the organization where create the repository
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public <T> T getOrganizationRepositories(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationRepositories(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public ArrayList<Repository> getOrganizationRepositories(String org, Params queryParams) throws IOException {
        return getOrganizationRepositories(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories for the specified organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
     * List organization repositories</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/repos")
    public <T> T getOrganizationRepositories(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + REPOS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:  the organization where create the repository
     * @param name: the name of the repository
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public Repository createOrganizationRepository(Organization org, String name) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:    the organization where create the repository
     * @param name:   the name of the repository
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public <T> T createOrganizationRepository(Organization org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, format);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param name: the name of the repository
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public Repository createOrganizationRepository(String org, String name) throws IOException {
        return createOrganizationRepository(org, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   the name of the repository
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public <T> T createOrganizationRepository(String org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRepository(org, name, null, format);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:        the organization where create the repository
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public Repository createOrganizationRepository(Organization org, String name, Params bodyParams) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:        the organization where create the repository
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public <T> T createOrganizationRepository(Organization org, String name, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, bodyParams, format);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public Repository createOrganizationRepository(String org, String name, Params bodyParams) throws IOException {
        return createOrganizationRepository(org, name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository in the specified organization.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Create an organization repository</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/repos")
    public <T> T createOrganizationRepository(String org, String name, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(ORGS_PATH + org + REPOS_QUERY_PATH, bodyParams), format);
    }

    /**
     * Method to get a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-a-repository">
     * Get a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}")
    public Repository getRepository(String owner, String repo) throws IOException {
        return getRepository(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-a-repository">
     * Get a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}")
    public <T> T getRepository(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepository(sendGetRequest(REPOS_PATH + owner + "/" + repo), format);
    }

    /**
     * Method to update a repository
     *
     * @param repository: the repository to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "security_and_analysis"} -> specify which security and analysis features to
     *                           enable or disable for the repository, you can use {@link SecurityAnalysis}
     *                           object - [object or null]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch"} -> updates the default branch for this repository
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether to archive this repository. false will unarchive a
     *                           previously archived repository - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "allow_forking"} -> either true to allow private forks, or false to prevent
     *                           private forks - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> either true to require contributors to sign
     *                           off on web-based commits, or false to not require contributors to sign off on web-based
     *                           commits - [boolean, default false]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Update a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}")
    public Repository updateRepository(Repository repository, Params bodyParams) throws IOException {
        return updateRepository(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a repository
     *
     * @param repository: the repository to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "security_and_analysis"} -> specify which security and analysis features to
     *                           enable or disable for the repository, you can use {@link SecurityAnalysis}
     *                           object - [object or null]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch"} -> updates the default branch for this repository
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether to archive this repository. false will unarchive a
     *                           previously archived repository - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "allow_forking"} -> either true to allow private forks, or false to prevent
     *                           private forks - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> either true to require contributors to sign
     *                           off on web-based commits, or false to not require contributors to sign off on web-based
     *                           commits - [boolean, default false]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Update a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}")
    public <T> T updateRepository(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return updateRepository(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to update a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "security_and_analysis"} -> specify which security and analysis features to
     *                           enable or disable for the repository, you can use {@link SecurityAnalysis}
     *                           object - [object or null]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch"} -> updates the default branch for this repository
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether to archive this repository. false will unarchive a
     *                           previously archived repository - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "allow_forking"} -> either true to allow private forks, or false to prevent
     *                           private forks - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> either true to require contributors to sign
     *                           off on web-based commits, or false to not require contributors to sign off on web-based
     *                           commits - [boolean, default false]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Update a repository</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}")
    public Repository updateRepository(String owner, String repo, Params bodyParams) throws IOException {
        return updateRepository(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "security_and_analysis"} -> specify which security and analysis features to
     *                           enable or disable for the repository, you can use {@link SecurityAnalysis}
     *                           object - [object or null]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch"} -> updates the default branch for this repository
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether to archive this repository. false will unarchive a
     *                           previously archived repository - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "allow_forking"} -> either true to allow private forks, or false to prevent
     *                           private forks - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> either true to require contributors to sign
     *                           off on web-based commits, or false to not require contributors to sign off on web-based
     *                           commits - [boolean, default false]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
     * Update a repository</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}")
    public <T> T updateRepository(String owner, String repo, Params bodyParams, ReturnFormat format) throws IOException {
        return returnRepository(sendPatchRequest(REPOS_PATH + owner + "/" + repo, bodyParams), format);
    }

    /**
     * Method to delete a repository <br>
     * Deleting a repository requires admin access. If OAuth is used, the delete_repo scope is required.
     * If an organization owner has configured the organization to prevent members from deleting
     * organization-owned repositories, you will get a 403 Forbidden response
     *
     * @param repository: the repository to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#delete-a-repository">
     * Delete a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}")
    public boolean deleteRepository(Repository repository) {
        return deleteRepository(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to delete a repository <br>
     * Deleting a repository requires admin access. If OAuth is used, the delete_repo scope is required.
     * If an organization owner has configured the organization to prevent members from deleting
     * organization-owned repositories, you will get a 403 Forbidden response
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#delete-a-repository">
     * Delete a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}")
    public boolean deleteRepository(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo);
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
     * Method to enable automated security fixes for a repository. The authenticated user must have admin access to the
     * repository. For more information, see "Configuring automated security fixes".
     *
     * @param repository: the repository where enable the automated security fixes
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#enable-automated-security-fixes">
     * Enable automated security fixes</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/automated-security-fixes")
    public boolean enableAutomatedSecurityFixes(Repository repository) {
        return enableAutomatedSecurityFixes(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to enable automated security fixes for a repository. The authenticated user must have admin access to the
     * repository. For more information, see "Configuring automated security fixes".
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#enable-automated-security-fixes">
     * Enable automated security fixes</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/automated-security-fixes")
    public boolean enableAutomatedSecurityFixes(String owner, String repo) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + AUTOMATED_SECURITY_FIXES_PATH, null);
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
     * Method to disable automated security fixes for a repository. The authenticated user must have admin access to the
     * repository. For more information, see "Configuring automated security fixes".
     *
     * @param repository: the repository where disable the automated security fixes
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#disable-automated-security-fixes">
     * Disable automated security fixes</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/automated-security-fixes")
    public boolean disableAutomatedSecurityFixes(Repository repository) {
        return disableAutomatedSecurityFixes(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to disable automated security fixes for a repository. The authenticated user must have admin access to the
     * repository. For more information, see "Configuring automated security fixes".
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#disable-automated-security-fixes">
     * Disable automated security fixes</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/automated-security-fixes")
    public boolean disableAutomatedSecurityFixes(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + AUTOMATED_SECURITY_FIXES_PATH);
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
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param repository: the repository from fetch the list
     * @return syntax errors as {@link ArrayList} of {@link CodeOwnersError} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public ArrayList<CodeOwnersError> getCodeOwnersErrors(Repository repository) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return syntax errors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public <T> T getCodeOwnersErrors(Repository repository, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return syntax errors as {@link ArrayList} of {@link CodeOwnersError} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public ArrayList<CodeOwnersError> getCodeOwnersErrors(String owner, String repo) throws IOException {
        return getCodeOwnersErrors(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return syntax errors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public <T> T getCodeOwnersErrors(String owner, String repo, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(owner, repo, null, format);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param repository: the repository from fetch the list
     * @param ref:        a branch, tag or commit name used to determine which version of the CODEOWNERS file to use. Default:
     *                    the repository's default branch (e.g. main)
     * @return syntax errors as {@link ArrayList} of {@link CodeOwnersError} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public ArrayList<CodeOwnersError> getCodeOwnersErrors(Repository repository, String ref) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param repository: the repository from fetch the list
     * @param ref:        a branch, tag or commit name used to determine which version of the CODEOWNERS file to use. Default:
     *                    the repository's default branch (e.g. main)
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return syntax errors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public <T> T getCodeOwnersErrors(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   a branch, tag or commit name used to determine which version of the CODEOWNERS file to use. Default:
     *               the repository's default branch (e.g. main)
     * @return syntax errors as {@link ArrayList} of {@link CodeOwnersError} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public ArrayList<CodeOwnersError> getCodeOwnersErrors(String owner, String repo, String ref) throws IOException {
        return getCodeOwnersErrors(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of any syntax errors that are detected in the CODEOWNERS file
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param ref:    a branch, tag or commit name used to determine which version of the CODEOWNERS file to use. Default:
     *                the repository's default branch (e.g. main)
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return syntax errors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
     * List CODEOWNERS errors</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codeowners/errors")
    public <T> T getCodeOwnersErrors(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + CODEOWNERS_ERRORS_PATH;
        if (ref != null)
            reqUrl += "?ref=" + ref;
        String codeOwnersErrorsResponse = sendGetRequest(reqUrl);
        switch (format) {
            case JSON:
                return (T) new JSONObject(codeOwnersErrorsResponse);
            case LIBRARY_OBJECT:
                ArrayList<CodeOwnersError> codeOwnersErrors = new ArrayList<>();
                JSONArray jCodeOwnersErrors = new JSONObject(codeOwnersErrorsResponse).getJSONArray("errors");
                for (int j = 0; j < jCodeOwnersErrors.length(); j++)
                    codeOwnersErrors.add(new CodeOwnersError(jCodeOwnersErrors.getJSONObject(j)));
                return (T) codeOwnersErrors;
            default:
                return (T) codeOwnersErrorsResponse;
        }
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param repository: the repository from fetch the list
     * @return syntax errors as {@link ArrayList} of {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public ArrayList<User> getRepositoryContributors(Repository repository) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public <T> T getRepositoryContributors(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return syntax errors as {@link ArrayList} of {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public ArrayList<User> getRepositoryContributors(String owner, String repo) throws IOException {
        return getRepositoryContributors(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public <T> T getRepositoryContributors(String owner, String repo, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CONTRIBUTORS_PATH), format);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "anon"} -> set to 1 or true to include anonymous contributors in results - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return syntax errors as {@link ArrayList} of {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public ArrayList<User> getRepositoryContributors(Repository repository, Params queryParams) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "anon"} -> set to 1 or true to include anonymous contributors in results - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public <T> T getRepositoryContributors(Repository repository, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "anon"} -> set to 1 or true to include anonymous contributors in results - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return syntax errors as {@link ArrayList} of {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public ArrayList<User> getRepositoryContributors(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryContributors(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list contributors to the specified repository and sorts them by the number of commits per
     * contributor in descending order. This endpoint may return information that is a few hours old because the
     * GitHub REST API caches contributor data to improve performance.
     * GitHub identifies contributors by author email address. This endpoint groups contribution counts by GitHub user,
     * which includes all associated email addresses. To improve performance, only the first 500 author email addresses
     * in the repository link to GitHub users. The rest will appear as anonymous contributors without associated GitHub
     * user information
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "anon"} -> set to 1 or true to include anonymous contributors in results - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-contributors">
     * List repository contributors</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contributors")
    public <T> T getRepositoryContributors(String owner, String repo, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CONTRIBUTORS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repository dispatch event
     *
     * @param repository: the repository where create the dispatch event
     * @param eventType:  a custom webhook event name. Must be 100 characters or fewer
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-dispatch-event">
     * Create a repository dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dispatches")
    public boolean createRepositoryDispatchEvent(Repository repository, String eventType) {
        return createRepositoryDispatchEvent(repository.getOwner().getLogin(), repository.getName(), eventType);
    }

    /**
     * Method to create a repository dispatch event
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param eventType: a custom webhook event name. Must be 100 characters or fewer
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-dispatch-event">
     * Create a repository dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dispatches")
    public boolean createRepositoryDispatchEvent(String owner, String repo, String eventType) {
        return createRepositoryDispatchEvent(owner, repo, eventType, null);
    }

    /**
     * Method to create a repository dispatch event
     *
     * @param repository:    the repository where create the dispatch event
     * @param eventType:     a custom webhook event name. Must be 100 characters or fewer
     * @param clientPayload: JSON payload with extra information about the webhook event that your action or workflow
     *                       may use. The maximum number of top-level properties is 10
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-dispatch-event">
     * Create a repository dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dispatches")
    public <T> boolean createRepositoryDispatchEvent(Repository repository, String eventType, T clientPayload) {
        return createRepositoryDispatchEvent(repository.getOwner().getLogin(), repository.getName(), eventType,
                clientPayload);
    }

    /**
     * Method to create a repository dispatch event
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param eventType:     a custom webhook event name. Must be 100 characters or fewer
     * @param clientPayload: JSON payload with extra information about the webhook event that your action or workflow
     *                       may use. The maximum number of top-level properties is 10
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-dispatch-event">
     * Create a repository dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dispatches")
    public <T> boolean createRepositoryDispatchEvent(String owner, String repo, String eventType, T clientPayload) {
        try {
            Params payload = new Params();
            payload.addParam("event_type", eventType);
            if (clientPayload != null)
                payload.addParam("client_payload", clientPayload);
            sendPostRequest(REPOS_PATH + owner + "/" + repo + DISPATCHES_PATH, payload);
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
     * Method to get the list languages for the specified repository. The value shown for each language is the number
     * of bytes of code written in that language
     *
     * @param repository: the repository where fetch the list of languages
     * @return repository languages as {@link RepositoryLanguages} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-languages">
     * List repository languages</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/languages")
    public RepositoryLanguages getRepositoryLanguages(Repository repository) throws IOException {
        return getRepositoryLanguages(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list languages for the specified repository. The value shown for each language is the number
     * of bytes of code written in that language
     *
     * @param repository: the repository where fetch the list of languages
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository languages as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-languages">
     * List repository languages</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/languages")
    public <T> T getRepositoryLanguages(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryLanguages(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list languages for the specified repository. The value shown for each language is the number
     * of bytes of code written in that language
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository languages as {@link RepositoryLanguages} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-languages">
     * List repository languages</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/languages")
    public RepositoryLanguages getRepositoryLanguages(String owner, String repo) throws IOException {
        return getRepositoryLanguages(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list languages for the specified repository. The value shown for each language is the number
     * of bytes of code written in that language
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository languages as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-languages">
     * List repository languages</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/languages")
    public <T> T getRepositoryLanguages(String owner, String repo, ReturnFormat format) throws IOException {
        String repositoryLanguagesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + LANGUAGES_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryLanguagesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryLanguages(new JSONObject(repositoryLanguagesResponse));
            default:
                return (T) repositoryLanguagesResponse;
        }
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param repository: the repository from fetch the list
     * @return repository tags as {@link ArrayList} of {@link RepositoryTag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public ArrayList<RepositoryTag> getRepositoryTags(Repository repository) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository tags list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public <T> T getRepositoryTags(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository tags as {@link ArrayList} of {@link RepositoryTag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public ArrayList<RepositoryTag> getRepositoryTags(String owner, String repo) throws IOException {
        return getRepositoryTags(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository tags list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public <T> T getRepositoryTags(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryTags(sendGetRequest(REPOS_PATH + owner + "/" + repo + TAGS_PATH), format);
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repository tags as {@link ArrayList} of {@link RepositoryTag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public ArrayList<RepositoryTag> getRepositoryTags(Repository repository, Params queryParams) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository tags
     *
     * @param repository:  the repository from fetch the list
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
     * @return repository tags list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public <T> T getRepositoryTags(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the repository tags
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
     * @return repository tags as {@link ArrayList} of {@link RepositoryTag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public ArrayList<RepositoryTag> getRepositoryTags(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryTags(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository tags
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
     * @return repository tags list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
     * List repository tags</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags")
    public <T> T getRepositoryTags(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoryTags(sendGetRequest(REPOS_PATH + owner + "/" + repo + TAGS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repository tags list
     *
     * @param repositoryTagsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return repository tags list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryTags(String repositoryTagsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoryTagsResponse);
            case LIBRARY_OBJECT:
                ArrayList<RepositoryTag> repositoryTags = new ArrayList<>();
                JSONArray jRepositoryTags = new JSONArray(repositoryTagsResponse);
                for (int j = 0; j < jRepositoryTags.length(); j++)
                    repositoryTags.add(new RepositoryTag(jRepositoryTags.getJSONObject(j)));
                return (T) repositoryTags;
            default:
                return (T) repositoryTagsResponse;
        }
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param repository: the repository from fetch the list
     * @return teams list as {@link ArrayList} of {@link Team} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public ArrayList<Team> getRepositoryTeams(Repository repository) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public <T> T getRepositoryTeams(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return teams list as {@link ArrayList} of {@link Team} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public ArrayList<Team> getRepositoryTeams(String owner, String repo) throws IOException {
        return getRepositoryTeams(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public <T> T getRepositoryTeams(String owner, String repo, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + TEAMS_PATH), format);
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return teams list as {@link ArrayList} of {@link Team} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public ArrayList<Team> getRepositoryTeams(Repository repository, Params queryParams) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository teams
     *
     * @param repository:  the repository from fetch the list
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
     * @return teams list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public <T> T getRepositoryTeams(Repository repository, Params queryParams,
                                    ReturnFormat format) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the repository teams
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
     * @return teams list as {@link ArrayList} of {@link Team} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public ArrayList<Team> getRepositoryTeams(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryTeams(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repository teams
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
     * @return teams list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-teams">
     * List repository teams</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/teams")
    public <T> T getRepositoryTeams(String owner, String repo, Params queryParams,
                                    ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + TEAMS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param repository: the repository from fetch the list
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> getAllRepositoryTopics(Repository repository) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public <T> T getAllRepositoryTopics(Repository repository, ReturnFormat format) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> getAllRepositoryTopics(String owner, String repo) throws IOException {
        return getAllRepositoryTopics(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public <T> T getAllRepositoryTopics(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryTopics(sendGetRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH), format);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> getAllRepositoryTopics(Repository repository, Params queryParams) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all repository topics
     *
     * @param repository:  the repository from fetch the list
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
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public <T> T getAllRepositoryTopics(Repository repository, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the all repository topics
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
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> getAllRepositoryTopics(String owner, String repo, Params queryParams) throws IOException {
        return getAllRepositoryTopics(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all repository topics
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
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#get-all-repository-topics">
     * Get all repository topics</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/topics")
    public <T> T getAllRepositoryTopics(String owner, String repo, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return returnRepositoryTopics(sendGetRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace all repository topics
     *
     * @param repository: the repository from fetch the list
     * @param names:      an array of topics to add to the repository. Pass one or more topics to replace the set of
     *                    existing topics. Send an empty array ([]) to clear all topics from the repository.
     *                    Note: Topic names cannot contain uppercase letters
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#replace-all-repository-topics">
     * Replace all repository topics</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> replaceAllRepositoryTopics(Repository repository, String... names) throws IOException {
        return replaceAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT,
                names);
    }

    /**
     * Method to replace all repository topics
     *
     * @param repository: the repository from fetch the list
     * @param names:      an array of topics to add to the repository. Pass one or more topics to replace the set of
     *                    existing topics. Send an empty array ([]) to clear all topics from the repository.
     *                    Note: Topic names cannot contain uppercase letters
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#replace-all-repository-topics">
     * Replace all repository topics</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/topics")
    public <T> T replaceAllRepositoryTopics(Repository repository, ReturnFormat format, String... names) throws IOException {
        return replaceAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), format, names);
    }

    /**
     * Method to replace all repository topics
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param names: an array of topics to add to the repository. Pass one or more topics to replace the set of
     *               existing topics. Send an empty array ([]) to clear all topics from the repository.
     *               Note: Topic names cannot contain uppercase letters
     * @return repository topics list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#replace-all-repository-topics">
     * Replace all repository topics</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/topics")
    public ArrayList<String> replaceAllRepositoryTopics(String owner, String repo, String... names) throws IOException {
        return replaceAllRepositoryTopics(owner, repo, LIBRARY_OBJECT, names);
    }

    /**
     * Method to replace all repository topics
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param names:  an array of topics to add to the repository. Pass one or more topics to replace the set of
     *                existing topics. Send an empty array ([]) to clear all topics from the repository.
     *                Note: Topic names cannot contain uppercase letters
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#replace-all-repository-topics">
     * Replace all repository topics</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/topics")
    public <T> T replaceAllRepositoryTopics(String owner, String repo, ReturnFormat format,
                                            String... names) throws IOException {
        Params payload = new Params();
        payload.addParam("names", names);
        return returnRepositoryTopics(sendPutRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH, payload),
                format);
    }

    /**
     * Method to create a repository topics list
     *
     * @param repositoryTopicsResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryTopics(String repositoryTopicsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoryTopicsResponse);
            case LIBRARY_OBJECT:
                return (T) returnStringsList(new JSONObject(repositoryTopicsResponse).getJSONArray("names"));
            default:
                return (T) repositoryTopicsResponse;
        }
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to 
     * another user. The response will contain the original owner, and the transfer will continue asynchronously. 
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param repository: the repository to transfer
     * @param newOwner: the username or organization name the repository will be transferred to
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public Repository transferRepository(Repository repository, String newOwner) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, LIBRARY_OBJECT);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param repository: the repository to transfer
     * @param newOwner:   the username or organization name the repository will be transferred to
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public <T> T transferRepository(Repository repository, String newOwner, ReturnFormat format) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, format);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param newOwner: the username or organization name the repository will be transferred to
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public Repository transferRepository(String owner, String repo, String newOwner) throws IOException {
        return transferRepository(owner, repo, newOwner, LIBRARY_OBJECT);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param newOwner: the username or organization name the repository will be transferred to
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public <T> T transferRepository(String owner, String repo, String newOwner, ReturnFormat format) throws IOException {
        return transferRepository(owner, repo, newOwner, null, format);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param repository: the repository to transfer
     * @param newOwner:   the username or organization name the repository will be transferred to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name to be given to the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> ID of the team or teams to add to the repository. Teams can only
     *                           be added to organization-owned repositories - [array of integers]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public Repository transferRepository(Repository repository, String newOwner, Params bodyParams) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param repository: the repository to transfer
     * @param newOwner:   the username or organization name the repository will be transferred to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name to be given to the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> ID of the team or teams to add to the repository. Teams can only
     *                           be added to organization-owned repositories - [array of integers]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public <T> T transferRepository(Repository repository, String newOwner, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, bodyParams, format);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param newOwner:   the username or organization name the repository will be transferred to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name to be given to the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> ID of the team or teams to add to the repository. Teams can only
     *                           be added to organization-owned repositories - [array of integers]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public Repository transferRepository(String owner, String repo, String newOwner, Params bodyParams) throws IOException {
        return transferRepository(owner, repo, newOwner, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to transfer request will need to be accepted by the new owner when transferring a personal repository to
     * another user. The response will contain the original owner, and the transfer will continue asynchronously.
     * For more details on the requirements to transfer personal and organization-owned repositories, see about
     * repository transfers.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param newOwner:   the username or organization name the repository will be transferred to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name to be given to the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> ID of the team or teams to add to the repository. Teams can only
     *                           be added to organization-owned repositories - [array of integers]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
     * Transfer a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/transfer")
    public <T> T transferRepository(String owner, String repo, String newOwner, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("new_owner", newOwner);
        return returnRepository(sendPostRequest(REPOS_PATH + owner + "/" + repo + TRANSFER_PATH, bodyParams),
                format);
    }

    /**
     * Method to show whether dependency alerts are enabled or disabled for a repository. The authenticated user must
     * have admin read access to the repository. For more information, see "About security alerts for vulnerable
     * dependencies"
     *
     * @param repository: the repository where check if vulnerability alerts are enabled
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#check-if-vulnerability-alerts-are-enabled-for-a-repository">
     * Check if vulnerability alerts are enabled for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean checkIfVulnerabilityAlertsAreEnabled(Repository repository) {
        return checkIfVulnerabilityAlertsAreEnabled(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to show whether dependency alerts are enabled or disabled for a repository. The authenticated user must
     * have admin read access to the repository. For more information, see "About security alerts for vulnerable
     * dependencies"
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#check-if-vulnerability-alerts-are-enabled-for-a-repository">
     * Check if vulnerability alerts are enabled for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean checkIfVulnerabilityAlertsAreEnabled(String owner, String repo) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH);
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
     * Method to enable dependency alerts and the dependency graph for a repository. The authenticated user must have
     * admin access to the repository. For more information, see "About security alerts for vulnerable dependencies"
     *
     * @param repository: the repository where enable the vulnerability alerts
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#enable-vulnerability-alerts">
     * Enable vulnerability alerts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean enableVulnerabilityAlerts(Repository repository) {
        return enableVulnerabilityAlerts(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to enable dependency alerts and the dependency graph for a repository. The authenticated user must have
     * admin access to the repository. For more information, see "About security alerts for vulnerable dependencies"
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#enable-vulnerability-alerts">
     * Enable vulnerability alerts</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean enableVulnerabilityAlerts(String owner, String repo) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH, null);
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
     * Method to disable dependency alerts and the dependency graph for a repository. The authenticated user must have
     * admin access to the repository. For more information, see "About security alerts for vulnerable dependencies".
     *
     * @param repository: the repository where disable the vulnerability alerts
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#disable-vulnerability-alerts">
     * Disable vulnerability alerts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean disableVulnerabilityAlerts(Repository repository) {
        return disableVulnerabilityAlerts(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to disable dependency alerts and the dependency graph for a repository. The authenticated user must have
     * admin access to the repository. For more information, see "About security alerts for vulnerable dependencies".
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#disable-vulnerability-alerts">
     * Disable vulnerability alerts</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/vulnerability-alerts")
    public boolean disableVulnerabilityAlerts(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH);
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
     * Method to create a new repository using a repository template
     *
     * @param templateOwner: the owner of the repository
     * @param templateRepo:  the name of the repository
     * @param name:          the name of the new repository
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-using-a-template">
     * Create a repository using a template</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{template_owner}/{template_repo}/generate")
    public Repository createRepositoryUsingTemplate(String templateOwner, String templateRepo,
                                                    String name) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository using a repository template
     *
     * @param templateOwner: the owner of the repository
     * @param templateRepo:  the name of the repository
     * @param name:          the name of the new repository
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-using-a-template">
     * Create a repository using a template</a>
     **/
    @RequestPath(method = POST, path = "/repos/{template_owner}/{template_repo}/generate")
    public <T> T createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name,
                                               ReturnFormat format) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, null, format);
    }

    /**
     * Method to create a new repository using a repository template
     *
     * @param templateOwner: the owner of the repository
     * @param templateRepo:  the name of the repository
     * @param name:          the name of the new repository
     * @param bodyParams:    extra body params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "owner"} -> the organization or person who will own the new repository. To
     *                              create a new repository in an organization, the authenticated user must be a member
     *                              of the specified organization - [string]
     *                          </li>
     *                          <li>
     *                              {@code "description"} -> a short description of the new repository - [string]
     *                          </li>
     *                          <li>
     *                              {@code "include_all_branches"} -> set to true to include the directory structure and
     *                              files from all branches in the template repository, and not just the default
     *                              branch - [boolean, default false]
     *                          </li>
     *                          <li>
     *                              {@code "private"} -> whether the repository is private - [boolean, default false]
     *                          </li>
     *                       </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-using-a-template">
     * Create a repository using a template</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{template_owner}/{template_repo}/generate")
    public Repository createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name,
                                                    Params bodyParams) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository using a repository template
     *
     * @param templateOwner: the owner of the repository
     * @param templateRepo:  the name of the repository
     * @param name:          the name of the new repository
     * @param bodyParams:    extra body params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "owner"} -> the organization or person who will own the new repository. To
     *                              create a new repository in an organization, the authenticated user must be a member
     *                              of the specified organization - [string]
     *                          </li>
     *                          <li>
     *                              {@code "description"} -> a short description of the new repository - [string]
     *                          </li>
     *                          <li>
     *                              {@code "include_all_branches"} -> set to true to include the directory structure and
     *                              files from all branches in the template repository, and not just the default
     *                              branch - [boolean, default false]
     *                          </li>
     *                          <li>
     *                              {@code "private"} -> whether the repository is private - [boolean, default false]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-using-a-template">
     * Create a repository using a template</a>
     **/
    @RequestPath(method = POST, path = "/repos/{template_owner}/{template_repo}/generate")
    public <T> T createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name, Params bodyParams,
                                               ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(REPOS_PATH + templateOwner + "/" + templateRepo + GENERATE_PATH,
                bodyParams), format);
    }

    /**
     * Method to get the list all public repositories in the order that they were created <br>
     * No-any params required
     *
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-public-repositories">
     * List public repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repositories")
    public ArrayList<Repository> getPublicRepositories() throws IOException {
        return getPublicRepositories(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all public repositories in the order that they were created
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-public-repositories">
     * List public repositories</a>
     **/
    @RequestPath(method = GET, path = "/repositories")
    public <T> T getPublicRepositories(ReturnFormat format) throws IOException {
        return getPublicRepositories(-1, format);
    }

    /**
     * Method to get the list all public repositories in the order that they were created
     *
     * @param since: a repository ID. Only return repositories with an ID greater than this ID
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-public-repositories">
     * List public repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repositories")
    public ArrayList<Repository> getPublicRepositories(long since) throws IOException {
        return getPublicRepositories(since, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all public repositories in the order that they were created
     *
     * @param since:  a repository ID. Only return repositories with an ID greater than this ID
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-public-repositories">
     * List public repositories</a>
     **/
    @RequestPath(method = GET, path = "/repositories")
    public <T> T getPublicRepositories(long since, ReturnFormat format) throws IOException {
        String reqUrl = "repositories";
        if (since != -1)
            reqUrl += "?since=" + since;
        return returnRepositories(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to get the list of the repositories that the authenticated user has explicit permission (:read, :write, or :admin)
     * to access. The authenticated user has explicit permission to access repositories they own, repositories where
     * they are a collaborator, and repositories that they can access through an organization membership <br>
     * No-any params required
     *
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-the-authenticated-user">
     * List repositories for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/repos")
    public ArrayList<Repository> getAuthenticatedUserRepositories() throws IOException {
        return getAuthenticatedUserRepositories(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories that the authenticated user has explicit permission (:read, :write, or :admin)
     * to access. The authenticated user has explicit permission to access repositories they own, repositories where
     * they are a collaborator, and repositories that they can access through an organization membership <br>
     * No-any params required
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-the-authenticated-user">
     * List repositories for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/repos")
    public <T> T getAuthenticatedUserRepositories(ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USER_REPOS_PATH), format);
    }

    /**
     * Method to get the list of the repositories that the authenticated user has explicit permission (:read, :write, or :admin)
     * to access. The authenticated user has explicit permission to access repositories they own, repositories where
     * they are a collaborator, and repositories that they can access through an organization membership
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the visibility of the repository, constants
     *                            available {@link RepoVisibility} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "affiliation"} -> filter repositories returned by their affiliation,
     *                            constants available {@link Affiliation} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show repositories updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show repositories updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-the-authenticated-user">
     * List repositories for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/repos")
    public ArrayList<Repository> getAuthenticatedUserRepositories(Params queryParams) throws IOException {
        return getAuthenticatedUserRepositories(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories that the authenticated user has explicit permission (:read, :write, or :admin)
     * to access. The authenticated user has explicit permission to access repositories they own, repositories where
     * they are a collaborator, and repositories that they can access through an organization membership
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the visibility of the repository, constants
     *                            available {@link RepoVisibility} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "affiliation"} -> filter repositories returned by their affiliation,
     *                            constants available {@link Affiliation} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show repositories updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show repositories updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-the-authenticated-user">
     * List repositories for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/repos")
    public <T> T getAuthenticatedUserRepositories(Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USER_REPOS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a new repository for the authenticated user.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param name: the name of the repository
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-for-the-authenticated-user">
     * Create a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/repos")
    public Repository createAuthenticatedUserRepository(String name) throws IOException {
        return createAuthenticatedUserRepository(name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository for the authenticated user.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param name:   the name of the repository
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-for-the-authenticated-user">
     * Create a repository for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/repos")
    public <T> T createAuthenticatedUserRepository(String name, ReturnFormat format) throws IOException {
        return createAuthenticatedUserRepository(name, null, format);
    }

    /**
     * Method to create a new repository for the authenticated user.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_discussions"} -> whether discussions are enabled - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-for-the-authenticated-user">
     * Create a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/repos")
    public Repository createAuthenticatedUserRepository(String name, Params bodyParams) throws IOException {
        return createAuthenticatedUserRepository(name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new repository for the authenticated user.
     * The authenticated user must be a member of the organization <br>
     * When using OAuth, authorizations must include:
     * <ul>
     *     <li>
     *          {@code "public_repo"} scope or repo scope to create a public repository. Note: For GitHub AE, use repo
     *          scope to create an internal repository.
     *     </li>
     *     <li>
     *          repo scope to create a private repository
     *     </li>
     * </ul>
     *
     * @param name:       the name of the repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> a short description of the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "homepage"} -> a URL with more information about the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether the repository is private - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "visibility"} -> the visibility of the repository, constants
     *                           available {@link RepoVisibility} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_issues"} -> either true to enable issues for this repository or false to
     *                           disable them - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_projects"} -> either true to enable projects for this repository or false
     *                           to disable them. Note: If you're creating a repository in an organization that has
     *                           disabled repository projects, the default is false, and if you pass true, the API
     *                           returns an error - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_wiki"} -> either true to enable the wiki for this repository or false
     *                           to disable it - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "has_discussions"} -> whether discussions are enabled - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "has_downloads"} -> whether downloads are enabled - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "is_template"} -> either true to make this repo available as a template
     *                           repository or false to prevent it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "team_id"} -> the id of the team that will be granted access to this repository.
     *                           This is only valid when creating a repository in an organization - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "auto_init"} -> pass true to create an initial commit with empty
     *                           README - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "gitignore_template"} -> desired language or platform .gitignore template to apply.
     *                           Use the name of the template without the extension. For example, "Haskell" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "license_template"} -> choose an open source license template that best suits
     *                           your needs, and then use the license keyword as the {@code "license_template"} string.
     *                           For example, "mit" or "mpl-2.0" - [string]
     *                       </li>
     *                       <li>
     *                           {@code "allow_squash_merge"} -> either true to allow squash-merging pull requests, or
     *                           false to prevent squash-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_merge_commit"} -> either true to allow merging pull requests with a
     *                           merge commit, or false to prevent merging pull requests with merge
     *                           commits - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_rebase_merge"} -> either true to allow rebase-merging pull requests, or
     *                           false to prevent rebase-merging - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "allow_auto_merge"} -> either true to allow auto-merge on pull requests, or
     *                           false to disallow auto-merge - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "delete_branch_on_merge"} -> either true to allow automatically deleting head
     *                           branches when pull requests are merged, or false to prevent automatic deletion.
     *                           <b>The authenticated user must be an organization owner to set this property to
     *                           true</b> - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_title"} -> the default value for a squash merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "COMMIT_OR_PR_TITLE"} - default to the commit's title (if only one commit) or the
     *                             pull request's title (when more than one commit) </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "squash_merge_commit_message"} -> the default value for a merge commit
     *                           title:
     *                           <ul>
     *                             <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                             <li> {@code "COMMIT_MESSAGES"} - default to the branch's commit messages </li>
     *                             <li> {@code "BLANK"} - default to a blank commit message </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_title"} -> the default value for a merge commit title:
     *                           <ul>
     *                             <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                             <li> {@code "MERGE_MESSAGE"} - default to the classic title for a merge message (e.g.,
     *                             Merge pull request #123 from branch-name</li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_commit_message"} -> the default value for a merge commit message
     *                           <ul>
     *                               <li> {@code "PR_TITLE"} - default to the pull request's title </li>
     *                               <li> {@code "PR_BODY"} - default to the pull request's body </li>
     *                               <li> {@code "BLANK"} - default to a blank commit message </li>
     *                             </ul> - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-for-the-authenticated-user">
     * Create a repository for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/repos")
    public <T> T createAuthenticatedUserRepository(String name, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(USER_REPOS_PATH, bodyParams), format);
    }

    /**
     * Method to get the list of the public repositories for the specified user. Note: For GitHub AE, this endpoint will
     * list internal repositories for the specified user
     * @param username: the handle for the GitHub user account
     *
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user">
     * List repositories for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/repos")
    public ArrayList<Repository> getUserRepositories(String username) throws IOException {
        return getUserRepositories(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public repositories for the specified user. Note: For GitHub AE, this endpoint will
     * list internal repositories for the specified user
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user">
     * List repositories for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/repos")
    public <T> T getUserRepositories(String username, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USERS_PATH + username + REPOS_QUERY_PATH), format);
    }

    /**
     * Method to get the list of the public repositories for the specified user. Note: For GitHub AE, this endpoint will
     * list internal repositories for the specified user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user">
     * List repositories for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/repos")
    public ArrayList<Repository> getUserRepositories(String username, Params queryParams) throws IOException {
        return getUserRepositories(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public repositories for the specified user. Note: For GitHub AE, this endpoint will
     * list internal repositories for the specified user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "type"} -> specifies the types of repositories you want returned, constants
     *                            available {@link RepositoryType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by, constants available
     *                            {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the order to sort by. Default: asc when using full_name,
     *                            otherwise desc, constants available {@link Directions} - [string]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user">
     * List repositories for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/repos")
    public <T> T getUserRepositories(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USERS_PATH + username + REPOS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

}
