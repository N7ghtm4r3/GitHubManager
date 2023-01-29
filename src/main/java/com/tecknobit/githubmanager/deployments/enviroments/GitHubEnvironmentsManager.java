package com.tecknobit.githubmanager.deployments.enviroments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.deployments.enviroments.records.Environment;
import com.tecknobit.githubmanager.deployments.enviroments.records.Environment.DeploymentBranchPolicy;
import com.tecknobit.githubmanager.deployments.enviroments.records.EnvironmentsList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubEnvironmentsManager} class is useful to manage all GitHub's environments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments">
 * Deployment environments</a>
 * @see GitHubManager
 **/
public class GitHubEnvironmentsManager extends GitHubManager {

    /**
     * {@code ENVIRONMENTS_PATH} constant for {@code "/environments"} path
     **/
    public static final String ENVIRONMENTS_PATH = "/environments";

    /**
     * Constructor to init a {@link GitHubEnvironmentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubEnvironmentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubEnvironmentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubEnvironmentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubEnvironmentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubEnvironmentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEnvironmentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubEnvironmentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEnvironmentsManager} <br>
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
    public GitHubEnvironmentsManager() {
        super();
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @return environments list as {@link EnvironmentsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public EnvironmentsList getEnvironments(Repository repository) throws IOException {
        return getEnvironments(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return environments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public <T> T getEnvironments(Repository repository, ReturnFormat format) throws IOException {
        return getEnvironments(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return environments list as {@link EnvironmentsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public EnvironmentsList getEnvironments(String owner, String repo) throws IOException {
        return getEnvironments(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return environments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public <T> T getEnvironments(String owner, String repo, ReturnFormat format) throws IOException {
        return returnEnvironments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH), format);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
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
     * @return environments list as {@link EnvironmentsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public EnvironmentsList getEnvironments(Repository repository, Params queryParams) throws IOException {
        return getEnvironments(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
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
     * @return environments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public <T> T getEnvironments(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getEnvironments(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
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
     * @return environments list as {@link EnvironmentsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public EnvironmentsList getEnvironments(String owner, String repo, Params queryParams) throws IOException {
        return getEnvironments(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the environments <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
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
     * @return environments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
     * List environments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments")
    public <T> T getEnvironments(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnEnvironments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an environments list
     *
     * @param environmentsResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return environments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnEnvironments(String environmentsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(environmentsResponse);
            case LIBRARY_OBJECT:
                return (T) new EnvironmentsList(new JSONObject(environmentsResponse));
            default:
                return (T) environmentsResponse;
        }
    }

    /**
     * Method to get the environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the environment
     * @param environmentName: the name of the environment
     * @return environment as {@link Environment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#get-an-environment">
     * Get an environment</a>
     * @implNote To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public Environment getEnvironment(Repository repository, String environmentName) throws IOException {
        return getEnvironment(repository.getOwner().getLogin(), repository.getName(), environmentName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the environment
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#get-an-environment">
     * Get an environment</a>
     * @implNote To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public <T> T getEnvironment(Repository repository, String environmentName, ReturnFormat format) throws IOException {
        return getEnvironment(repository.getOwner().getLogin(), repository.getName(), environmentName, format);
    }

    /**
     * Method to get the environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @return environment as {@link Environment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#get-an-environment">
     * Get an environment</a>
     * @implNote To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public Environment getEnvironment(String owner, String repo, String environmentName) throws IOException {
        return getEnvironment(owner, repo, environmentName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#get-an-environment">
     * Get an environment</a>
     * @implNote To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public <T> T getEnvironment(String owner, String repo, String environmentName, ReturnFormat format) throws IOException {
        return returnEnvironment(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH +
                "/" + environmentName), format);
    }

    /**
     * Method to create or update an environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where create or update an environment
     * @param environmentName: the name of the environment
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "wait_timer"} -> the amount of time to delay a job after the job is initially triggered.
     *                                The time (in minutes) must be an integer between 0 and 43,200 (30 days) - [integer]
     *                            </li>
     *                            <li>
     *                                {@code "reviewers"} -> the people or teams that may review jobs that reference the environment.
     *                                You can list up to six users or teams as reviewers.
     *                                The reviewers must have at least read access to the repository.
     *                                Only one of the required reviewers needs to approve the job for it to proceed
     *                                - [array of objects or null]
     *                            </li>
     *                            <li>
     *                                {@code "deployment_branch_policy"} -> the type of deployment branch policy for this environment.
     *                                To allow all branches to deploy, set to null, you can use {@link DeploymentBranchPolicy}
     *                                custom object - [object or null]
     *                            </li>
     *                         </ul>
     * @return environment as {@link Environment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#create-or-update-an-environment">
     * Create or update an environment</a>
     * @implNote <ul>
     * <li>
     * To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     * </li>
     * <li>
     * To create or update secrets for an environment, see "Secrets."
     * </li>
     * </ul>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public Environment createEnvironment(Repository repository, String environmentName, Params bodyParams) throws IOException {
        return createEnvironment(repository.getOwner().getLogin(), repository.getName(), environmentName, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create or update an environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where create or update an environment
     * @param environmentName: the name of the environment
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "wait_timer"} -> the amount of time to delay a job after the job is initially triggered.
     *                                The time (in minutes) must be an integer between 0 and 43,200 (30 days) - [integer]
     *                            </li>
     *                            <li>
     *                                {@code "reviewers"} -> the people or teams that may review jobs that reference the environment.
     *                                You can list up to six users or teams as reviewers.
     *                                The reviewers must have at least read access to the repository.
     *                                Only one of the required reviewers needs to approve the job for it to proceed
     *                                - [array of objects or null]
     *                            </li>
     *                            <li>
     *                                {@code "deployment_branch_policy"} -> the type of deployment branch policy for this environment.
     *                                To allow all branches to deploy, set to null, you can use {@link DeploymentBranchPolicy}
     *                                custom object - [object or null]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#create-or-update-an-environment">
     * Create or update an environment</a>
     * @implNote <ul>
     * <li>
     * To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     * </li>
     * <li>
     * To create or update secrets for an environment, see "Secrets."
     * </li>
     * </ul>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public <T> T createEnvironment(Repository repository, String environmentName, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return createEnvironment(repository.getOwner().getLogin(), repository.getName(), environmentName, bodyParams,
                format);
    }

    /**
     * Method to create or update an environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "wait_timer"} -> the amount of time to delay a job after the job is initially triggered.
     *                                The time (in minutes) must be an integer between 0 and 43,200 (30 days) - [integer]
     *                            </li>
     *                            <li>
     *                                {@code "reviewers"} -> the people or teams that may review jobs that reference the environment.
     *                                You can list up to six users or teams as reviewers.
     *                                The reviewers must have at least read access to the repository.
     *                                Only one of the required reviewers needs to approve the job for it to proceed
     *                                - [array of objects or null]
     *                            </li>
     *                            <li>
     *                                {@code "deployment_branch_policy"} -> the type of deployment branch policy for this environment.
     *                                To allow all branches to deploy, set to null, you can use {@link DeploymentBranchPolicy}
     *                                custom object - [object or null]
     *                            </li>
     *                         </ul>
     * @return environment as {@link Environment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#create-or-update-an-environment">
     * Create or update an environment</a>
     * @implNote <ul>
     * <li>
     * To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     * </li>
     * <li>
     * To create or update secrets for an environment, see "Secrets."
     * </li>
     * </ul>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public Environment createEnvironment(String owner, String repo, String environmentName,
                                         Params bodyParams) throws IOException {
        return createEnvironment(owner, repo, environmentName, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create or update an environment <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private, you must use an
     * access token with the repo scope. GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "wait_timer"} -> the amount of time to delay a job after the job is initially triggered.
     *                                The time (in minutes) must be an integer between 0 and 43,200 (30 days) - [integer]
     *                            </li>
     *                            <li>
     *                                {@code "reviewers"} -> the people or teams that may review jobs that reference the environment.
     *                                You can list up to six users or teams as reviewers.
     *                                The reviewers must have at least read access to the repository.
     *                                Only one of the required reviewers needs to approve the job for it to proceed
     *                                - [array of objects or null]
     *                            </li>
     *                            <li>
     *                                {@code "deployment_branch_policy"} -> the type of deployment branch policy for this environment.
     *                                To allow all branches to deploy, set to null, you can use {@link DeploymentBranchPolicy}
     *                                custom object - [object or null]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#create-or-update-an-environment">
     * Create or update an environment</a>
     * @implNote <ul>
     * <li>
     * To get information about name patterns that branches must match in order to deploy to this environment,
     * see "Get a deployment branch policy."
     * </li>
     * <li>
     * To create or update secrets for an environment, see "Secrets."
     * </li>
     * </ul>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public <T> T createEnvironment(String owner, String repo, String environmentName, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return returnEnvironment(sendPutRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH +
                "/" + environmentName, bodyParams), format);
    }

    /**
     * Method to create an environment
     *
     * @param environmentResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return environment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnEnvironment(String environmentResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(environmentResponse);
            case LIBRARY_OBJECT:
                return (T) new Environment(new JSONObject(environmentResponse));
            default:
                return (T) environmentResponse;
        }
    }

    /**
     * Method to delete an environment <br>
     *
     * @param repository:  the repository where delete the environment
     * @param environment: the environment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#delete-an-environment">
     * Delete an environment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public boolean deleteEnvironment(Repository repository, Environment environment) {
        return deleteEnvironment(repository.getOwner().getLogin(), repository.getName(), environment.getName());
    }

    /**
     * Method to delete an environment <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#delete-an-environment">
     * Delete an environment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public boolean deleteEnvironment(String owner, String repo, Environment environment) {
        return deleteEnvironment(owner, repo, environment.getName());
    }

    /**
     * Method to delete an environment <br>
     *
     * @param repository:      the repository where delete the environment
     * @param environmentName: the name of the environment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#delete-an-environment">
     * Delete an environment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public boolean deleteEnvironment(Repository repository, String environmentName) {
        return deleteEnvironment(repository.getOwner().getLogin(), repository.getName(), environmentName);
    }

    /**
     * Method to delete an environment <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#delete-an-environment">
     * Delete an environment</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}")
    public boolean deleteEnvironment(String owner, String repo, String environmentName) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH + "/" + environmentName);
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
