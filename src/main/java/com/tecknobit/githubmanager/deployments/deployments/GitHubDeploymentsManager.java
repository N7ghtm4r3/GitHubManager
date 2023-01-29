package com.tecknobit.githubmanager.deployments.deployments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.deployments.deployments.records.Deployment;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubDeploymentsManager} class is useful to manage all GitHub's deployments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments">
 * Deployments</a>
 * @see GitHubManager
 **/
public class GitHubDeploymentsManager extends GitHubManager {

    /**
     * {@code DEPLOYMENTS_PATH} constant for {@code "/deployments"} path
     **/
    public static final String DEPLOYMENTS_PATH = "/deployments";

    /**
     * Constructor to init a {@link GitHubDeploymentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDeploymentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDeploymentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDeploymentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDeploymentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeploymentsManager} <br>
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
    public GitHubDeploymentsManager() {
        super();
    }

    /**
     * Method to get the list of the deployments
     *
     * @param repository: the repository from fetch the list
     * @return deployments as {@link Collection} of {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public Collection<Deployment> getDeployments(Repository repository) throws Exception {
        return getDeployments(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public <T> T getDeployments(Repository repository, ReturnFormat format) throws Exception {
        return getDeployments(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return deployments as {@link Collection} of {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public Collection<Deployment> getDeployments(String owner, String repo) throws Exception {
        return getDeployments(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public <T> T getDeployments(String owner, String repo, ReturnFormat format) throws Exception {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH), format);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> the SHA recorded at creation time - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the name of the ref. This can be a branch, tag, or SHA - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                            - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "environment"} -> the name of the environment that was deployed to
     *                            (e.g., staging or production) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployments as {@link Collection} of {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public Collection<Deployment> getDeployments(Repository repository, Params queryParams) throws Exception {
        return getDeployments(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> the SHA recorded at creation time - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the name of the ref. This can be a branch, tag, or SHA - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                            - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "environment"} -> the name of the environment that was deployed to
     *                            (e.g., staging or production) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public <T> T getDeployments(Repository repository, Params queryParams, ReturnFormat format) throws Exception {
        return getDeployments(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> the SHA recorded at creation time - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the name of the ref. This can be a branch, tag, or SHA - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                            - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "environment"} -> the name of the environment that was deployed to
     *                            (e.g., staging or production) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployments as {@link Collection} of {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public Collection<Deployment> getDeployments(String owner, String repo, Params queryParams) throws Exception {
        return getDeployments(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployments
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> the SHA recorded at creation time - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the name of the ref. This can be a branch, tag, or SHA - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                            - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "environment"} -> the name of the environment that was deployed to
     *                            (e.g., staging or production) - [string, default none]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
     * List deployments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments")
    public <T> T getDeployments(String owner, String repo, Params queryParams, ReturnFormat format) throws Exception {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a deployments list
     *
     * @param deploymentsResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeployments(String deploymentsResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(deploymentsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Deployment> deployments = new ArrayList<>();
                JSONArray jDeployments = new JSONArray(deploymentsResponse);
                for (int j = 0; j < jDeployments.length(); j++)
                    deployments.add(new Deployment(jDeployments.getJSONObject(j)));
                return (T) deployments;
            default:
                return (T) deploymentsResponse;
        }
    }

    /**
     * Method to create a new deployment
     *
     * @param repository: the repository where create the deployment
     * @param ref:        the ref to deploy. This can be a branch, tag, or SHA
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                           - [string, default deploy]
     *                       </li>
     *                       <li>
     *                           {@code "auto_merge"} -> attempts to automatically merge the default branch into the
     *                           requested ref, if it's behind the default branch- [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "required_contexts"} -> the status contexts to verify against commit status checks.
     *                           If you omit this parameter, GitHub verifies all unique contexts before creating a deployment.
     *                           To bypass checking entirely, pass an empty array. Defaults to all unique contexts -
     *                           [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "payload"} -> JSON payload with extra information about the deployment
     *                           - [object or string]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment (e.g., production,
     *                           staging, qa) - [string, default production]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> short description of the deployment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "transient_environment"} -> specifies if the given environment is specific to
     *                           the deployment and will no longer exist at some point in the future
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "production_environment"} ->  specifies if the given environment is one that
     *                           end-users directly interact with. Default: true when environment is production - [boolean]
     *                       </li>
     *                    </ul>
     * @return deployment as {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#create-a-deployment">
     * Create a deployment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments")
    public Deployment createDeployment(Repository repository, String ref, Params bodyParams) throws Exception {
        return createDeployment(repository.getOwner().getLogin(), repository.getName(), ref, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new deployment
     *
     * @param repository: the repository where create the deployment
     * @param ref:        the ref to deploy. This can be a branch, tag, or SHA
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                           - [string, default deploy]
     *                       </li>
     *                       <li>
     *                           {@code "auto_merge"} -> attempts to automatically merge the default branch into the
     *                           requested ref, if it's behind the default branch- [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "required_contexts"} -> the status contexts to verify against commit status checks.
     *                           If you omit this parameter, GitHub verifies all unique contexts before creating a deployment.
     *                           To bypass checking entirely, pass an empty array. Defaults to all unique contexts -
     *                           [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "payload"} -> JSON payload with extra information about the deployment
     *                           - [object or string]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment (e.g., production,
     *                           staging, qa) - [string, default production]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> short description of the deployment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "transient_environment"} -> specifies if the given environment is specific to
     *                           the deployment and will no longer exist at some point in the future
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "production_environment"} ->  specifies if the given environment is one that
     *                           end-users directly interact with. Default: true when environment is production - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#create-a-deployment">
     * Create a deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments")
    public <T> T createDeployment(Repository repository, String ref, Params bodyParams, ReturnFormat format) throws Exception {
        return createDeployment(repository.getOwner().getLogin(), repository.getName(), ref, bodyParams, format);
    }

    /**
     * Method to create a new deployment
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param ref:        the ref to deploy. This can be a branch, tag, or SHA
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                           - [string, default deploy]
     *                       </li>
     *                       <li>
     *                           {@code "auto_merge"} -> attempts to automatically merge the default branch into the
     *                           requested ref, if it's behind the default branch- [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "required_contexts"} -> the status contexts to verify against commit status checks.
     *                           If you omit this parameter, GitHub verifies all unique contexts before creating a deployment.
     *                           To bypass checking entirely, pass an empty array. Defaults to all unique contexts -
     *                           [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "payload"} -> JSON payload with extra information about the deployment
     *                           - [object or string]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment (e.g., production,
     *                           staging, qa) - [string, default production]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> short description of the deployment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "transient_environment"} -> specifies if the given environment is specific to
     *                           the deployment and will no longer exist at some point in the future
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "production_environment"} ->  specifies if the given environment is one that
     *                           end-users directly interact with. Default: true when environment is production - [boolean]
     *                       </li>
     *                    </ul>
     * @return deployment as {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#create-a-deployment">
     * Create a deployment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments")
    public Deployment createDeployment(String owner, String repo, String ref, Params bodyParams) throws Exception {
        return createDeployment(owner, repo, ref, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new deployment
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param ref:        the ref to deploy. This can be a branch, tag, or SHA
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "task"} -> the name of the task for the deployment (e.g., deploy or deploy:migrations)
     *                           - [string, default deploy]
     *                       </li>
     *                       <li>
     *                           {@code "auto_merge"} -> attempts to automatically merge the default branch into the
     *                           requested ref, if it's behind the default branch- [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "required_contexts"} -> the status contexts to verify against commit status checks.
     *                           If you omit this parameter, GitHub verifies all unique contexts before creating a deployment.
     *                           To bypass checking entirely, pass an empty array. Defaults to all unique contexts -
     *                           [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "payload"} -> JSON payload with extra information about the deployment
     *                           - [object or string]
     *                       </li>
     *                       <li>
     *                           {@code "environment"} -> name for the target deployment environment (e.g., production,
     *                           staging, qa) - [string, default production]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> short description of the deployment - [string, default ""]
     *                       </li>
     *                       <li>
     *                           {@code "transient_environment"} -> specifies if the given environment is specific to
     *                           the deployment and will no longer exist at some point in the future
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "production_environment"} ->  specifies if the given environment is one that
     *                           end-users directly interact with. Default: true when environment is production - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#create-a-deployment">
     * Create a deployment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/deployments")
    public <T> T createDeployment(String owner, String repo, String ref, Params bodyParams,
                                  ReturnFormat format) throws Exception {
        return returnDeployment(sendPostRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH, bodyParams),
                format);
    }

    /**
     * Method to get a deployment
     *
     * @param repository:   the repository from fetch the deployment
     * @param deploymentId: deployment_id parameter
     * @return deployment as {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#get-a-deployment">
     * Get a deployment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public Deployment getDeployment(Repository repository, long deploymentId) throws Exception {
        return getDeployment(repository.getOwner().getLogin(), repository.getName(), deploymentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment
     *
     * @param repository:   the repository from fetch the deployment
     * @param deploymentId: deployment_id parameter
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#get-a-deployment">
     * Get a deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public <T> T getDeployment(Repository repository, long deploymentId, ReturnFormat format) throws Exception {
        return getDeployment(repository.getOwner().getLogin(), repository.getName(), deploymentId, format);
    }

    /**
     * Method to get a deployment
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @return deployment as {@link Deployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#get-a-deployment">
     * Get a deployment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public Deployment getDeployment(String owner, String repo, long deploymentId) throws Exception {
        return getDeployment(owner, repo, deploymentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#get-a-deployment">
     * Get a deployment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public <T> T getDeployment(String owner, String repo, long deploymentId, ReturnFormat format) throws Exception {
        return returnDeployment(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH
                + "/" + deploymentId), format);
    }

    /**
     * Method to create a deployment
     *
     * @param deploymentResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return deployment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeployment(String deploymentResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(deploymentResponse);
            case LIBRARY_OBJECT:
                return (T) new Deployment(new JSONObject(deploymentResponse));
            default:
                return (T) deploymentResponse;
        }
    }

    /**
     * Method to delete a deployment <br>
     * If the repository only has one deployment, you can delete the deployment regardless of its status.
     * If the repository has more than one deployment, you can only delete inactive deployments.
     * This ensures that repositories with multiple deployments will always have an active deployment.
     * Anyone with repo or {@code "repo_deployment"} scopes can delete a deployment.
     * To set a deployment as inactive, you must:
     * <ul>
     *     <li>
     *         Create a new deployment that is active so that the system has a record of the current state, then delete the previously active deployment.
     *     </li>
     *     <li>
     *         Mark the active deployment as inactive by adding any non-successful deployment status.
     *     </li>
     * </ul>
     * For more information, see "Create a deployment" and "Create a deployment status."
     *
     * @param repository: the repository where delete the deployment
     * @param deployment: the deployment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#delete-a-deployment">
     * Delete a deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public boolean deleteDeployment(Repository repository, Deployment deployment) {
        return deleteDeployment(repository.getOwner().getLogin(), repository.getName(), deployment.getId());
    }

    /**
     * Method to delete a deployment <br>
     * If the repository only has one deployment, you can delete the deployment regardless of its status.
     * If the repository has more than one deployment, you can only delete inactive deployments.
     * This ensures that repositories with multiple deployments will always have an active deployment.
     * Anyone with repo or {@code "repo_deployment"} scopes can delete a deployment.
     * To set a deployment as inactive, you must:
     * <ul>
     *     <li>
     *         Create a new deployment that is active so that the system has a record of the current state, then delete the previously active deployment.
     *     </li>
     *     <li>
     *         Mark the active deployment as inactive by adding any non-successful deployment status.
     *     </li>
     * </ul>
     * For more information, see "Create a deployment" and "Create a deployment status."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param deployment: the deployment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#delete-a-deployment">
     * Delete a deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public boolean deleteDeployment(String owner, String repo, Deployment deployment) {
        return deleteDeployment(owner, repo, deployment.getId());
    }

    /**
     * Method to delete a deployment <br>
     * If the repository only has one deployment, you can delete the deployment regardless of its status.
     * If the repository has more than one deployment, you can only delete inactive deployments.
     * This ensures that repositories with multiple deployments will always have an active deployment.
     * Anyone with repo or {@code "repo_deployment"} scopes can delete a deployment.
     * To set a deployment as inactive, you must:
     * <ul>
     *     <li>
     *         Create a new deployment that is active so that the system has a record of the current state, then delete the previously active deployment.
     *     </li>
     *     <li>
     *         Mark the active deployment as inactive by adding any non-successful deployment status.
     *     </li>
     * </ul>
     * For more information, see "Create a deployment" and "Create a deployment status."
     *
     * @param repository:   the repository where delete the deployment
     * @param deploymentId: deployment_id parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#delete-a-deployment">
     * Delete a deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public boolean deleteDeployment(Repository repository, long deploymentId) {
        return deleteDeployment(repository.getOwner().getLogin(), repository.getName(), deploymentId);
    }

    /**
     * Method to delete a deployment <br>
     * If the repository only has one deployment, you can delete the deployment regardless of its status.
     * If the repository has more than one deployment, you can only delete inactive deployments.
     * This ensures that repositories with multiple deployments will always have an active deployment.
     * Anyone with repo or {@code "repo_deployment"} scopes can delete a deployment.
     * To set a deployment as inactive, you must:
     * <ul>
     *     <li>
     *         Create a new deployment that is active so that the system has a record of the current state, then delete the previously active deployment.
     *     </li>
     *     <li>
     *         Mark the active deployment as inactive by adding any non-successful deployment status.
     *     </li>
     * </ul>
     * For more information, see "Create a deployment" and "Create a deployment status."
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param deploymentId: deployment_id parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/deployments#delete-a-deployment">
     * Delete a deployment</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/deployments/{deployment_id}")
    public boolean deleteDeployment(String owner, String repo, long deploymentId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + DEPLOYMENTS_PATH + "/" + deploymentId);
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
