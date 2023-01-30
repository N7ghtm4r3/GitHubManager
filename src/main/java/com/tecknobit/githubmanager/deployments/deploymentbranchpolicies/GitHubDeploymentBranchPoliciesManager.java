package com.tecknobit.githubmanager.deployments.deploymentbranchpolicies;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.deployments.deploymentbranchpolicies.records.DeploymentBranchPoliciesList;
import com.tecknobit.githubmanager.deployments.deploymentbranchpolicies.records.DeploymentBranchPolicy;
import com.tecknobit.githubmanager.deployments.enviroments.records.Environment;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.secrets.GitHubSecretsManager.ENVIRONMENTS_PATH;

/**
 * The {@code GitHubDeploymentBranchPoliciesManager} class is useful to manage all GitHub's deployment branch policies endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies">
 * Deployment branch policies</a>
 * @see GitHubManager
 **/
public class GitHubDeploymentBranchPoliciesManager extends GitHubManager {

    /**
     * {@code DEPLOYMENT_BRANCH_POLICIES_PATH} constant for {@code "/deployment-branch-policies"} path
     **/
    public static final String DEPLOYMENT_BRANCH_POLICIES_PATH = "/deployment-branch-policies";

    /**
     * Constructor to init a {@link GitHubDeploymentBranchPoliciesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDeploymentBranchPoliciesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentBranchPoliciesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDeploymentBranchPoliciesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentBranchPoliciesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDeploymentBranchPoliciesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentBranchPoliciesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDeploymentBranchPoliciesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentBranchPoliciesManager} <br>
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
    public GitHubDeploymentBranchPoliciesManager() {
        super();
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param environment: the environment from fetch the list
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(Repository repository,
                                                                    Environment environment) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param environment: the environment from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(Repository repository, Environment environment,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the list
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(String owner, String repo,
                                                                    Environment environment) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environment.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(String owner, String repo, Environment environment,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environment.getName(), format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the list
     * @param environmentName: the name of the environment
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(Repository repository,
                                                                    String environmentName) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environmentName,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the list
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(Repository repository, String environmentName,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environmentName,
                format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(String owner, String repo,
                                                                    String environmentName) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environmentName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(String owner, String repo, String environmentName,
                                             ReturnFormat format) throws IOException {
        return returnBranchPolicies(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + environmentName + DEPLOYMENT_BRANCH_POLICIES_PATH), format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param environment: the environment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(Repository repository, Environment environment,
                                                                    Params queryParams) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param environment: the environment from fetch the list
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
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(Repository repository, Environment environment, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                queryParams, format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(String owner, String repo, Environment environment,
                                                                    Params queryParams) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environment.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the list
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
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(String owner, String repo, Environment environment, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environment.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the list
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(Repository repository, String environmentName,
                                                                    Params queryParams) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environmentName,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the list
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(Repository repository, String environmentName, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicies(repository.getOwner().getLogin(), repository.getName(), environmentName,
                queryParams, format);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return deployment branch policies as {@link DeploymentBranchPoliciesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPoliciesList getDeploymentBranchPolicies(String owner, String repo, String environmentName,
                                                                    Params queryParams) throws IOException {
        return getDeploymentBranchPolicies(owner, repo, environmentName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment branch policies for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
     * List deployment branch policies</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T getDeploymentBranchPolicies(String owner, String repo, String environmentName, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnBranchPolicies(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + environmentName + DEPLOYMENT_BRANCH_POLICIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a branch policies list
     *
     * @param branchPoliciesResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return branch policies list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnBranchPolicies(String branchPoliciesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(branchPoliciesResponse);
            case LIBRARY_OBJECT:
                return (T) new DeploymentBranchPoliciesList(new JSONObject(branchPoliciesResponse));
            default:
                return (T) branchPoliciesResponse;
        }
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param repository:  the repository where create the branch policy
     * @param environment: the environment where create the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPolicy createDeploymentBranchPolicy(Repository repository, Environment environment,
                                                               String name) throws IOException {
        return createDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param repository:  the repository where create the branch policy
     * @param environment: the environment where create the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T createDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                              ReturnFormat format) throws IOException {
        return createDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, format);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment where create the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPolicy createDeploymentBranchPolicy(String owner, String repo, Environment environment,
                                                               String name) throws IOException {
        return createDeploymentBranchPolicy(owner, repo, environment.getName(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment where create the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T createDeploymentBranchPolicy(String owner, String repo, Environment environment, String name,
                                              ReturnFormat format) throws IOException {
        return createDeploymentBranchPolicy(owner, repo, environment.getName(), name, format);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param repository:      the repository where create the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPolicy createDeploymentBranchPolicy(Repository repository, String environmentName,
                                                               String name) throws IOException {
        return createDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param repository:      the repository where create the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T createDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                              ReturnFormat format) throws IOException {
        return createDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                format);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public DeploymentBranchPolicy createDeploymentBranchPolicy(String owner, String repo, String environmentName,
                                                               String name) throws IOException {
        return createDeploymentBranchPolicy(owner, repo, environmentName, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment branch policy for an environment.
     * You must authenticate using an access token with the repo scope to use this endpoint.
     * GitHub Apps must have the {@code "administration:write"} permission for the repository to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
     * Create a deployment branch policy</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies")
    public <T> T createDeploymentBranchPolicy(String owner, String repo, String environmentName, String name,
                                              ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("name", name);
        return returnBranchPolicy(sendPostRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + environmentName + DEPLOYMENT_BRANCH_POLICIES_PATH, payload), format);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the branch policy
     * @param environment: the environment from fetch the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy getDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                                            long branchPolicyId) throws IOException {
        return getDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:  the repository from fetch the branch policy
     * @param environment: the environment from fetch the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T getDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                           long branchPolicyId, ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicyId, format);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy getDeploymentBranchPolicy(String owner, String repo, Environment environment,
                                                            String name, long branchPolicyId) throws IOException {
        return getDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param environment: the environment from fetch the branch policy
     * @param name:        the name pattern that branches must match in order to deploy to the environment.
     *                     Wildcard characters will not match /.
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T getDeploymentBranchPolicy(String owner, String repo, Environment environment, String name,
                                           long branchPolicyId, ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicyId, format);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy getDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                                            long branchPolicyId) throws IOException {
        return getDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository from fetch the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T getDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                           long branchPolicyId, ReturnFormat format) throws IOException {
        return getDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicyId, format);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy getDeploymentBranchPolicy(String owner, String repo, String environmentName,
                                                            String name, long branchPolicyId) throws IOException {
        return getDeploymentBranchPolicy(owner, repo, environmentName, name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
     * Get a deployment branch policy</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T getDeploymentBranchPolicy(String owner, String repo, String environmentName, String name,
                                           long branchPolicyId, ReturnFormat format) throws IOException {
        return returnBranchPolicy(sendGetRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + environmentName + DEPLOYMENT_BRANCH_POLICIES_PATH + "/" + branchPolicyId), format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:   the repository where update the branch policy
     * @param environment:  the environment where update the branch policy
     * @param name:         the name pattern that branches must match in order to deploy to the environment.
     *                      Wildcard characters will not match /.
     * @param branchPolicy: the branch policy to update
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                                               DeploymentBranchPolicy branchPolicy) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicy.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:   the repository where update the branch policy
     * @param environment:  the environment where update the branch policy
     * @param name:         the name pattern that branches must match in order to deploy to the environment.
     *                      Wildcard characters will not match /.
     * @param branchPolicy: the branch policy to update
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                              DeploymentBranchPolicy branchPolicy, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicy.getId(), format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:     the repository where update the branch policy
     * @param environment:    the environment where update the branch policy
     * @param name:           the name pattern that branches must match in order to deploy to the environment.
     *                        Wildcard characters will not match /.
     * @param branchPolicyId: the unique identifier of the branch policy
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(Repository repository, Environment environment,
                                                               String name, long branchPolicyId) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:     the repository where update the branch policy
     * @param environment:    the environment where update the branch policy
     * @param name:           the name pattern that branches must match in order to deploy to the environment.
     *                        Wildcard characters will not match /.
     * @param branchPolicyId: the unique identifier of the branch policy
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(Repository repository, Environment environment, String name,
                                              long branchPolicyId, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                name, branchPolicyId, format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param environment:  the environment where update the branch policy
     * @param name:         the name pattern that branches must match in order to deploy to the environment.
     *                      Wildcard characters will not match /.
     * @param branchPolicy: the branch policy to update
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(String owner, String repo, Environment environment,
                                                               String name,
                                                               DeploymentBranchPolicy branchPolicy) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicy.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param environment:  the environment where update the branch policy
     * @param name:         the name pattern that branches must match in order to deploy to the environment.
     *                      Wildcard characters will not match /.
     * @param branchPolicy: the branch policy to update
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(String owner, String repo, Environment environment, String name,
                                              DeploymentBranchPolicy branchPolicy, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicy.getId(), format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param environment:    the environment where update the branch policy
     * @param name:           the name pattern that branches must match in order to deploy to the environment.
     *                        Wildcard characters will not match /.
     * @param branchPolicyId: the unique identifier of the branch policy
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(String owner, String repo, Environment environment,
                                                               String name, long branchPolicyId) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param environment:    the environment where update the branch policy
     * @param name:           the name pattern that branches must match in order to deploy to the environment.
     *                        Wildcard characters will not match /.
     * @param branchPolicyId: the unique identifier of the branch policy
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(String owner, String repo, Environment environment, String name,
                                              long branchPolicyId, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environment.getName(), name, branchPolicyId, format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where update the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicy:    the branch policy to update
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                                               DeploymentBranchPolicy branchPolicy) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicy.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where update the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicy:    the branch policy to update
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                              DeploymentBranchPolicy branchPolicy, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicy.getId(), format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where update the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                                               long branchPolicyId) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where update the branch policy
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(Repository repository, String environmentName, String name,
                                              long branchPolicyId, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName, name,
                branchPolicyId, format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicy:    the branch policy to update
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(String owner, String repo, String environmentName,
                                                               String name,
                                                               DeploymentBranchPolicy branchPolicy) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environmentName, name, branchPolicy.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicy:    the branch policy to update
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(String owner, String repo, String environmentName, String name,
                                              DeploymentBranchPolicy branchPolicy, ReturnFormat format) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environmentName, name, branchPolicy.getId(), format);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @return deployment branch policy as {@link DeploymentBranchPolicy} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public DeploymentBranchPolicy updateDeploymentBranchPolicy(String owner, String repo, String environmentName,
                                                               String name, long branchPolicyId) throws IOException {
        return updateDeploymentBranchPolicy(owner, repo, environmentName, name, branchPolicyId, LIBRARY_OBJECT);
    }

    /**
     * Method to update a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param name:            the name pattern that branches must match in order to deploy to the environment.
     *                         Wildcard characters will not match /.
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
     * Update a deployment branch policy</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public <T> T updateDeploymentBranchPolicy(String owner, String repo, String environmentName, String name,
                                              long branchPolicyId, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("name", name);
        return returnBranchPolicy(sendPutRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH
                + environmentName + DEPLOYMENT_BRANCH_POLICIES_PATH + "/" + branchPolicyId, payload), format);
    }

    /**
     * Method to create a branch policy
     *
     * @param branchPolicyResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return branch policy as {@code "format"} defines
     **/
    @Returner
    private <T> T returnBranchPolicy(String branchPolicyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(branchPolicyResponse);
            case LIBRARY_OBJECT:
                return (T) new DeploymentBranchPolicy(new JSONObject(branchPolicyResponse));
            default:
                return (T) branchPolicyResponse;
        }
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:   the repository where delete the branch policy for an environment
     * @param environment:  the environment where delete the branch policy
     * @param branchPolicy: the branch policy to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(Repository repository, Environment environment,
                                                DeploymentBranchPolicy branchPolicy) {
        return deleteDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                branchPolicy.getId());
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where delete the branch policy for an environment
     * @param environmentName: the name of the environment
     * @param branchPolicy:    the branch policy to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(Repository repository, String environmentName,
                                                DeploymentBranchPolicy branchPolicy) {
        return deleteDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName,
                branchPolicy.getId());
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param branchPolicy:    the branch policy to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(String owner, String repo, String environmentName,
                                                DeploymentBranchPolicy branchPolicy) {
        return deleteDeploymentBranchPolicy(owner, repo, environmentName, branchPolicy.getId());
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param environment:  the environment where delete the branch policy
     * @param branchPolicy: the branch policy to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(String owner, String repo, Environment environment,
                                                DeploymentBranchPolicy branchPolicy) {
        return deleteDeploymentBranchPolicy(owner, repo, environment.getName(), branchPolicy.getId());
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param environment:    the environment where delete the branch policy
     * @param branchPolicyId: the unique identifier of the branch policy
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(String owner, String repo, Environment environment, long branchPolicyId) {
        return deleteDeploymentBranchPolicy(owner, repo, environment.getName(), branchPolicyId);
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:     the repository where delete the branch policy for an environment
     * @param environment:    the environment where delete the branch policy
     * @param branchPolicyId: the unique identifier of the branch policy
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(Repository repository, Environment environment, long branchPolicyId) {
        return deleteDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environment.getName(),
                branchPolicyId);
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:      the repository where delete the branch policy for an environment
     * @param environmentName: the name of the environment
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(Repository repository, String environmentName, long branchPolicyId) {
        return deleteDeploymentBranchPolicy(repository.getOwner().getLogin(), repository.getName(), environmentName,
                branchPolicyId);
    }

    /**
     * Method to delete a deployment branch policy for an environment <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private, you must use an access token with the repo scope. <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param environmentName: the name of the environment
     * @param branchPolicyId:  the unique identifier of the branch policy
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#delete-a-deployment-branch-policy">
     * Delete a deployment branch policy</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/environments/{environment_name}/deployment-branch-policies/{branch_policy_id}")
    public boolean deleteDeploymentBranchPolicy(String owner, String repo, String environmentName, long branchPolicyId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ENVIRONMENTS_PATH + environmentName
                    + DEPLOYMENT_BRANCH_POLICIES_PATH + "/" + branchPolicyId);
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
