package com.tecknobit.githubmanager.deployments.deploymentstatuses;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.deployments.deployments.records.Deployment;
import com.tecknobit.githubmanager.deployments.deploymentstatuses.records.DeploymentStatus;
import com.tecknobit.githubmanager.deployments.deploymentstatuses.records.DeploymentStatus.DeploymentState;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commitstatuses.GitHubCommitStatusesManager.STATUSES_PATH;
import static com.tecknobit.githubmanager.deployments.deployments.GitHubDeploymentsManager.DEPLOYMENTS_PATH;

/**
 * The {@code GitHubDeploymentStatusesManager} class is useful to manage all GitHub's deployment statuses endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses">
 * Deployment statuses</a>
 * @see GitHubManager
 **/
public class GitHubDeploymentStatusesManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubDeploymentStatusesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDeploymentStatusesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentStatusesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDeploymentStatusesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentStatusesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDeploymentStatusesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentStatusesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDeploymentStatusesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentStatusesManager} <br>
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
    public GitHubDeploymentStatusesManager() {
        super();
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository: the repository from fetch the list
     * @param deployment: the deployment from fetch the list
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(Repository repository, Deployment deployment) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deployment.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository: the repository from fetch the list
     * @param deployment: the deployment from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(Repository repository, Deployment deployment, ReturnFormat format) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deployment.getId(), format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment from fetch the list
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(String owner, String repo,
                                                             Deployment deployment) throws Exception {
        return getDeploymentStatuses(owner, repo, deployment.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(String owner, String repo, Deployment deployment, ReturnFormat format) throws Exception {
        return getDeploymentStatuses(owner, repo, deployment.getId(), format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:   the repository from fetch the list
     * @param deploymentId: deployment_id parameter
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(Repository repository, long deploymentId) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deploymentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:   the repository from fetch the list
     * @param deploymentId: deployment_id parameter
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(Repository repository, long deploymentId, ReturnFormat format) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deploymentId, format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(String owner, String repo, long deploymentId) throws Exception {
        return getDeploymentStatuses(owner, repo, deploymentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(String owner, String repo, long deploymentId, ReturnFormat format) throws Exception {
        return returnDeploymentStatuses(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH + "/"
                + deploymentId + STATUSES_PATH), format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:  the repository from fetch the list
     * @param deployment:  the deployment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(Repository repository, Deployment deployment,
                                                             Params queryParams) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deployment.getId(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:  the repository from fetch the list
     * @param deployment:  the deployment from fetch the list
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
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(Repository repository, Deployment deployment, Params queryParams,
                                       ReturnFormat format) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deployment.getId(),
                queryParams, format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param deployment:  the deployment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(String owner, String repo, Deployment deployment,
                                                             Params queryParams) throws Exception {
        return getDeploymentStatuses(owner, repo, deployment.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param deployment:  the deployment from fetch the list
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
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(String owner, String repo, Deployment deployment, Params queryParams,
                                       ReturnFormat format) throws Exception {
        return getDeploymentStatuses(owner, repo, deployment.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:   the repository from fetch the list
     * @param deploymentId: deployment_id parameter
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(Repository repository, long deploymentId,
                                                             Params queryParams) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deploymentId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param repository:   the repository from fetch the list
     * @param deploymentId: deployment_id parameter
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
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(Repository repository, long deploymentId, Params queryParams,
                                       ReturnFormat format) throws Exception {
        return getDeploymentStatuses(repository.getOwner().getLogin(), repository.getName(), deploymentId, queryParams,
                format);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return deployment statuses as {@link ArrayList} of {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public ArrayList<DeploymentStatus> getDeploymentStatuses(String owner, String repo, long deploymentId,
                                                             Params queryParams) throws Exception {
        return getDeploymentStatuses(owner, repo, deploymentId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments statuses
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
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
     * @return deployment statuses list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
     * List deployment statuses</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T getDeploymentStatuses(String owner, String repo, long deploymentId, Params queryParams,
                                       ReturnFormat format) throws Exception {
        return returnDeploymentStatuses(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH + "/"
                + deploymentId + STATUSES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a deployment statuses list
     *
     * @param deploymentStatusesResponse: obtained from GitHub's response
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return deployment statuses list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeploymentStatuses(String deploymentStatusesResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(deploymentStatusesResponse);
            case LIBRARY_OBJECT:
                ArrayList<DeploymentStatus> statuses = new ArrayList<>();
                JSONArray jStatuses = new JSONArray(deploymentStatusesResponse);
                for (int j = 0; j < jStatuses.length(); j++)
                    statuses.add(new DeploymentStatus(jStatuses.getJSONObject(j)));
                return (T) statuses;
            default:
                return (T) deploymentStatusesResponse;
        }
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param repository: the repository where create the deployment status
     * @param deployment: the deployment where create the deployment status
     * @param state:      the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                    shown as destroyed in GitHub
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the status. The maximum description
     *                           length is 140 characters - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                           Can be one of: production, staging, or qa- [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                           non-production environment deployments with the same repository and environment name as
     *                           the created status's deployment. An inactive status is only added to deployments that
     *                           had a success state - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public DeploymentStatus createDeploymentStatus(Repository repository, Deployment deployment, DeploymentState state,
                                                   Params bodyParams) throws Exception {
        return createDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deployment.getId(), state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param repository: the repository where create the deployment status
     * @param deployment: the deployment where create the deployment status
     * @param state:      the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                    shown as destroyed in GitHub
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the status. The maximum description
     *                           length is 140 characters - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                           Can be one of: production, staging, or qa- [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                           non-production environment deployments with the same repository and environment name as
     *                           the created status's deployment. An inactive status is only added to deployments that
     *                           had a success state - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T createDeploymentStatus(Repository repository, Deployment deployment, DeploymentState state, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return createDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deployment.getId(), state,
                bodyParams, format);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment where create the deployment status
     * @param state:      the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                    shown as destroyed in GitHub
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the status. The maximum description
     *                           length is 140 characters - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                           Can be one of: production, staging, or qa- [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                           non-production environment deployments with the same repository and environment name as
     *                           the created status's deployment. An inactive status is only added to deployments that
     *                           had a success state - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public DeploymentStatus createDeploymentStatus(String owner, String repo, Deployment deployment, DeploymentState state,
                                                   Params bodyParams) throws Exception {
        return createDeploymentStatus(owner, repo, deployment.getId(), state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment where create the deployment status
     * @param state:      the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                    shown as destroyed in GitHub
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the status. The maximum description
     *                           length is 140 characters - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                           Can be one of: production, staging, or qa- [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                           non-production environment deployments with the same repository and environment name as
     *                           the created status's deployment. An inactive status is only added to deployments that
     *                           had a success state - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T createDeploymentStatus(String owner, String repo, Deployment deployment, DeploymentState state,
                                        Params bodyParams, ReturnFormat format) throws Exception {
        return createDeploymentStatus(owner, repo, deployment.getId(), state, bodyParams, format);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param repository:   the repository where create the deployment status
     * @param deploymentId: deployment_id parameter
     * @param state:        the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                      shown as destroyed in GitHub
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "description"} -> a short description of the status. The maximum description
     *                             length is 140 characters - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                             Can be one of: production, staging, or qa- [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                             non-production environment deployments with the same repository and environment name as
     *                             the created status's deployment. An inactive status is only added to deployments that
     *                             had a success state - [boolean, default true]
     *                         </li>
     *                      </ul>
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public DeploymentStatus createDeploymentStatus(Repository repository, long deploymentId, DeploymentState state,
                                                   Params bodyParams) throws Exception {
        return createDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deploymentId, state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param repository:   the repository where create the deployment status
     * @param deploymentId: deployment_id parameter
     * @param state:        the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                      shown as destroyed in GitHub
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "description"} -> a short description of the status. The maximum description
     *                             length is 140 characters - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                             Can be one of: production, staging, or qa- [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                             non-production environment deployments with the same repository and environment name as
     *                             the created status's deployment. An inactive status is only added to deployments that
     *                             had a success state - [boolean, default true]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T createDeploymentStatus(Repository repository, long deploymentId, DeploymentState state, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return createDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deploymentId, state, bodyParams,
                format);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param state:        the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                      shown as destroyed in GitHub
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "description"} -> a short description of the status. The maximum description
     *                             length is 140 characters - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                             Can be one of: production, staging, or qa- [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                             non-production environment deployments with the same repository and environment name as
     *                             the created status's deployment. An inactive status is only added to deployments that
     *                             had a success state - [boolean, default true]
     *                         </li>
     *                      </ul>
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public DeploymentStatus createDeploymentStatus(String owner, String repo, long deploymentId, DeploymentState state,
                                                   Params bodyParams) throws Exception {
        return createDeploymentStatus(owner, repo, deploymentId, state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment status <br>
     * Users with push access can create deployment statuses for a given deployment.
     * GitHub Apps require read & write access to "Deployments" and read-only access to "Repo contents" (for private repos).
     * OAuth Apps require the {@code "repo_deployment"} scope
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param state:        the state of the status. When you set a transient deployment to inactive, the deployment will be
     *                      shown as destroyed in GitHub
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "log_url"} -> the full URL of the deployment's output - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "description"} -> a short description of the status. The maximum description
     *                             length is 140 characters - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment"} -> name for the target deployment environment, which can be changed when setting a deploy status.
     *                             Can be one of: production, staging, or qa- [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "environment_url"} -> sets the URL for accessing your environment - [string, default ""]
     *                         </li>
     *                         <li>
     *                             {@code "auto_inactive"} -> adds a new inactive status to all prior non-transient,
     *                             non-production environment deployments with the same repository and environment name as
     *                             the created status's deployment. An inactive status is only added to deployments that
     *                             had a success state - [boolean, default true]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
     * Create a deployment status</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses")
    public <T> T createDeploymentStatus(String owner, String repo, long deploymentId, DeploymentState state,
                                        Params bodyParams, ReturnFormat format) throws Exception {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("state", state);
        return returnDeploymentStatus(sendPostRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH + "/"
                + deploymentId + STATUSES_PATH, bodyParams), format);
    }

    /**
     * Method to get a deployment status
     *
     * @param repository: the repository from fetch the deployment status
     * @param deployment: the deployment from fetch the deployment status
     * @param statusId:   status identifier
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public DeploymentStatus getDeploymentStatus(Repository repository, Deployment deployment, long statusId) throws Exception {
        return getDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deployment.getId(), statusId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment status
     *
     * @param repository: the repository from fetch the deployment status
     * @param deployment: the deployment from fetch the deployment status
     * @param statusId:   status identifier
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public <T> T getDeploymentStatus(Repository repository, Deployment deployment, long statusId,
                                     ReturnFormat format) throws Exception {
        return getDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deployment.getId(), statusId,
                format);
    }

    /**
     * Method to get a deployment status
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment from fetch the deployment status
     * @param statusId:   status identifier
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public DeploymentStatus getDeploymentStatus(String owner, String repo, Deployment deployment,
                                                long statusId) throws Exception {
        return getDeploymentStatus(owner, repo, deployment.getId(), statusId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment status
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment from fetch the deployment status
     * @param statusId:   status identifier
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public <T> T getDeploymentStatus(String owner, String repo, Deployment deployment, long statusId,
                                     ReturnFormat format) throws Exception {
        return getDeploymentStatus(owner, repo, deployment.getId(), statusId, format);
    }

    /**
     * Method to get a deployment status
     *
     * @param repository:   the repository from fetch the deployment status
     * @param deploymentId: deployment_id parameter
     * @param statusId:     status identifier
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public DeploymentStatus getDeploymentStatus(Repository repository, long deploymentId, long statusId) throws Exception {
        return getDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deploymentId, statusId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment status
     *
     * @param repository:   the repository from fetch the deployment status
     * @param deploymentId: deployment_id parameter
     * @param statusId:     status identifier
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public <T> T getDeploymentStatus(Repository repository, long deploymentId, long statusId,
                                     ReturnFormat format) throws Exception {
        return getDeploymentStatus(repository.getOwner().getLogin(), repository.getName(), deploymentId, statusId, format);
    }

    /**
     * Method to get a deployment status
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param statusId:     status identifier
     * @return deployment status as {@link DeploymentStatus} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public DeploymentStatus getDeploymentStatus(String owner, String repo, long deploymentId, long statusId) throws Exception {
        return getDeploymentStatus(owner, repo, deploymentId, statusId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment status
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param statusId:     status identifier
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
     * Get a deployment status</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}/statuses/{status_id}")
    public <T> T getDeploymentStatus(String owner, String repo, long deploymentId, long statusId,
                                     ReturnFormat format) throws Exception {
        return returnDeploymentStatus(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH + "/"
                + deploymentId + STATUSES_PATH + "/" + statusId), format);
    }

    /**
     * Method to create a deployment status
     *
     * @param deploymentStatusResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return deployment status as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeploymentStatus(String deploymentStatusResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(deploymentStatusResponse);
            case LIBRARY_OBJECT:
                return (T) new DeploymentStatus(new JSONObject(deploymentStatusResponse));
            default:
                return (T) deploymentStatusResponse;
        }
    }

}
