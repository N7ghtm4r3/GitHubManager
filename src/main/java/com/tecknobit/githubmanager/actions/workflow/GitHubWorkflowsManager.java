package com.tecknobit.githubmanager.actions.workflow;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.records.Workflow;
import com.tecknobit.githubmanager.actions.workflow.records.WorkflowUsage;
import com.tecknobit.githubmanager.actions.workflow.records.WorkflowsList;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubWorkflowsManager} class is useful to manage all GitHub's workflows endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows">
 * About the Workflows API</a>
 * @see GitHubManager
 **/
public class GitHubWorkflowsManager extends GitHubManager {

    /**
     * {@code ATTEMPTS_PATH} constant for {@code "/attempts/"} path
     **/
    public static final String ATTEMPTS_PATH = "/attempts/";

    /**
     * {@code ACTIONS_JOBS_PATH} constant for {@code "/actions/jobs"} path
     **/
    public static final String ACTIONS_JOBS_PATH = "/actions/jobs";

    /**
     * {@code ACTIONS_RUNS_PATH} constant for {@code "/actions/runs"} path
     **/
    public static final String ACTIONS_RUNS_PATH = ACTIONS_PATH + "runs";

    /**
     * {@code ACTIONS_WORKFLOWS_PATH} constant for {@code "/actions/workflows/"} path
     **/
    public static final String ACTIONS_WORKFLOWS_PATH = ACTIONS_PATH + "workflows";

    /**
     * {@code DISABLE_PATH} constant for {@code "/disable} path
     **/
    public static final String DISABLE_PATH = "/disable";

    /**
     * {@code DISPATCHES_PATH} constant for {@code "/dispatches} path
     **/
    public static final String DISPATCHES_PATH = "/dispatches";

    /**
     * {@code ENABLE_PATH} constant for {@code "/enable} path
     **/
    public static final String ENABLE_PATH = "/enable";

    /**
     * {@code TIMING_PATH} constant for {@code "/timing"} path
     **/
    public static final String TIMING_PATH = "/timing";

    /**
     * Constructor to init a {@link GitHubWorkflowsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWorkflowsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWorkflowsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWorkflowsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWorkflowsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowsManager} <br>
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
    public GitHubWorkflowsManager() {
        super();
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the list
     * @return workflow list as {@link WorkflowsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public WorkflowsList getRepositoryWorkflows(Repository repository) throws IOException {
        return getRepositoryWorkflows(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public <T> T getRepositoryWorkflows(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryWorkflows(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return workflow list as {@link WorkflowsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public WorkflowsList getRepositoryWorkflows(String owner, String repo) throws IOException {
        return getRepositoryWorkflows(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return workflow list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public <T> T getRepositoryWorkflows(String owner, String repo, ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH),
                format);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
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
     * @return workflow list as {@link WorkflowsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public WorkflowsList getRepositoryWorkflows(Repository repository, Params queryParams) throws IOException {
        return getRepositoryWorkflows(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
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
     * @return workflow list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public <T> T getRepositoryWorkflows(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryWorkflows(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
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
     * @return workflow list as {@link WorkflowsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public WorkflowsList getRepositoryWorkflows(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryWorkflows(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the workflows in a repository
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
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
     * @return workflow list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
     * List repository workflows</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows")
    public <T> T getRepositoryWorkflows(String owner, String repo, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create a workflow list
     *
     * @param workflowsListResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return workflow list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflowsList(String workflowsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowsListResponse);
            case LIBRARY_OBJECT:
                return (T) new WorkflowsList(new JSONObject(workflowsListResponse));
            default:
                return (T) workflowsListResponse;
        }
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow as {@link Workflow} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public Workflow getWorkflow(Repository repository, long workflowId) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public <T> T getWorkflow(Repository repository, long workflowId, ReturnFormat format) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId, format);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow as {@link Workflow} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public Workflow getWorkflow(String owner, String repo, long workflowId) throws IOException {
        return getWorkflow(owner, repo, workflowId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public <T> T getWorkflow(String owner, String repo, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflow(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId), format);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the repository from fetch the workflow
     * @param workflowName: the workflow file name
     * @return workflow as {@link Workflow} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public Workflow getWorkflow(Repository repository, String workflowName) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the repository from fetch the workflow
     * @param workflowName: the workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public <T> T getWorkflow(Repository repository, String workflowName, ReturnFormat format) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowName, format);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @return workflow as {@link Workflow} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public Workflow getWorkflow(String owner, String repo, String workflowName) throws IOException {
        return getWorkflow(owner, repo, workflowName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
     * Get a workflow</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}")
    public <T> T getWorkflow(String owner, String repo, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflow(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName), format);
    }

    /**
     * Method to create a workflow object
     *
     * @param workflowResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return workflow object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflow(String workflowResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowResponse);
            case LIBRARY_OBJECT:
                return (T) new Workflow(new JSONObject(workflowResponse));
            default:
                return (T) workflowResponse;
        }
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from disable thw workflow
     * @param workflow:   the workflow to disable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(Repository repository, Workflow workflow) {
        return performWorkflowDeactivation(repository.getOwner().getLogin(), repository.getName(), workflow.getId());
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow to disable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(String owner, String repo, Workflow workflow) {
        return performWorkflowDeactivation(owner, repo, workflow.getId());
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from disable thw workflow
     * @param workflowId: the {@code "ID"} of the workflow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(Repository repository, long workflowId) {
        return performWorkflowDeactivation(repository.getOwner().getLogin(), repository.getName(), workflowId);
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(String owner, String repo, long workflowId) {
        return performWorkflowDeactivation(owner, repo, workflowId);
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:   repository from disable thw workflow
     * @param workflowName: the workflow file name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(Repository repository, String workflowName) {
        return performWorkflowDeactivation(repository.getOwner().getLogin(), repository.getName(), workflowName);
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/disable")
    public boolean disableWorkflow(String owner, String repo, String workflowName) {
        return performWorkflowDeactivation(owner, repo, workflowName);
    }

    /**
     * Method to get a workflow and sets the state of the workflow to disabled_manually.
     * You can replace {@code "workflow_id"} with the workflow file name.
     * For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the {@code "ID"} of the workflow. You can also pass the workflow file name as a string
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#disable-a-workflow">
     * Disable a workflow</a>
     **/
    private <T> boolean performWorkflowDeactivation(String owner, String repo, T workflow) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflow +
                    DISABLE_PATH, null);
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
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to create the workflow dispatch event
     * @param workflow:   the workflow to create the workflow dispatch event
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, Workflow workflow, String ref) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(),
                workflow.getId(), ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow to create the workflow dispatch event
     * @param ref:      the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, Workflow workflow, String ref) {
        return performWorkflowDispatchEventCreation(owner, repo, workflow.getId(), ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to create the workflow dispatch event
     * @param workflowId: the {@code "ID"} of the workflow
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, long workflowId, String ref) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(), workflowId,
                ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, long workflowId, String ref) {
        return performWorkflowDispatchEventCreation(owner, repo, workflowId, ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:   the repository to create the workflow dispatch event
     * @param workflowName: the workflow file name
     * @param ref:          the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, String workflowName, String ref) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(), workflowName,
                ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @param ref:          the git reference for the workflow. The reference can be a branch or tag name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, String workflowName, String ref) {
        return performWorkflowDispatchEventCreation(owner, repo, workflowName, ref, null);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to create the workflow dispatch even
     * @param workflow:   the workflow to create the workflow dispatch event
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:     input keys and values configured in the workflow file.
     *                    The maximum number of properties is 10.
     *                    Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, Workflow workflow, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(),
                workflow.getId(), ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow to create the workflow dispatch event
     * @param ref:      the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:   input keys and values configured in the workflow file.
     *                  The maximum number of properties is 10.
     *                  Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, Workflow workflow, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(owner, repo, workflow.getId(), ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to create the workflow dispatch event
     * @param workflowId: the {@code "ID"} of the workflow
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:     input keys and values configured in the workflow file.
     *                    The maximum number of properties is 10.
     *                    Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, long workflowId, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(), workflowId,
                ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @param ref:        the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:     input keys and values configured in the workflow file.
     *                    The maximum number of properties is 10.
     *                    Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, long workflowId, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(owner, repo, workflowId, ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:   the repository to create the workflow dispatch event
     * @param workflowName: the workflow file name
     * @param ref:          the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:       input keys and values configured in the workflow file.
     *                      The maximum number of properties is 10.
     *                      Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(Repository repository, String workflowName, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(repository.getOwner().getLogin(), repository.getName(), workflowName,
                ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @param ref:          the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:       input keys and values configured in the workflow file.
     *                      The maximum number of properties is 10.
     *                      Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/dispatches")
    public boolean createWorkflowDispatchEvent(String owner, String repo, String workflowName, String ref, Params inputs) {
        return performWorkflowDispatchEventCreation(owner, repo, workflowName, ref, inputs);
    }

    /**
     * Method to manually trigger a {@code "GitHub Actions"} workflow run.
     * You must configure your GitHub Actions workflow to run when the {@code "workflow_dispatch"} webhook event occurs.
     * The inputs are configured in the workflow file.
     * For more information about how to configure the workflow_dispatch event in the workflow file, see Events that trigger workflows.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the {@code "ID"} of the workflow. You can also pass the workflow file name as a string
     * @param ref:      the git reference for the workflow. The reference can be a branch or tag name
     * @param inputs:   input keys and values configured in the workflow file.
     *                  The maximum number of properties is 10.
     *                  Any default properties configured in the workflow file will be used when inputs are omitted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#create-a-workflow-dispatch-event">
     * Create a workflow dispatch event</a>
     **/
    private <T> boolean performWorkflowDispatchEventCreation(String owner, String repo, T workflow, String ref,
                                                             Params inputs) {
        if (inputs == null)
            inputs = new Params();
        inputs.addParam("ref", ref);
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflow +
                    DISPATCHES_PATH, inputs);
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
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to enable the workflow
     * @param workflow:   the workflow to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(Repository repository, Workflow workflow) {
        return performWorkflowActivation(repository.getOwner().getLogin(), repository.getName(), workflow.getId());
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(String owner, String repo, Workflow workflow) {
        return performWorkflowActivation(owner, repo, workflow.getId());
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository to enable the workflow
     * @param workflowId: the {@code "ID"} of the workflow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(Repository repository, long workflowId) {
        return performWorkflowActivation(repository.getOwner().getLogin(), repository.getName(), workflowId);
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(String owner, String repo, long workflowId) {
        return performWorkflowActivation(owner, repo, workflowId);
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:   the repository to enable the workflow
     * @param workflowName: the workflow file name as a string
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(Repository repository, String workflowName) {
        return performWorkflowActivation(repository.getOwner().getLogin(), repository.getName(), workflowName);
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name as a string
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/enable")
    public boolean enableWorkflow(String owner, String repo, String workflowName) {
        return performWorkflowActivation(owner, repo, workflowName);
    }

    /**
     * Method to enables a workflow and sets the state of the workflow to active.
     * You can replace {@code "workflow_id"} with the workflow file name. For example, you could use main.yaml
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the {@code "ID"} of the workflow. You can also pass the workflow file name as a string
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#enable-a-workflow">
     * Enable a workflow</a>
     **/
    private <T> boolean performWorkflowActivation(String owner, String repo, T workflow) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflow +
                    ENABLE_PATH, null);
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
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow usage
     * @param workflow:   the workflow from fetch the workflow usage
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(Repository repository, Workflow workflow) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflow.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow usage
     * @param workflow:   the workflow from fetch the workflow usage
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(Repository repository, Workflow workflow, ReturnFormat format) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflow.getId(), format);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow from fetch the workflow usage
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(String owner, String repo, Workflow workflow) throws IOException {
        return getWorkflowUsage(owner, repo, workflow.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the workflow from fetch the workflow usage
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(String owner, String repo, Workflow workflow, ReturnFormat format) throws IOException {
        return getWorkflowUsage(owner, repo, workflow.getId(), format);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow usage
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(Repository repository, long workflowId) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflowId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the repository from fetch the workflow usage
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(Repository repository, long workflowId, ReturnFormat format) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflowId, format);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(String owner, String repo, long workflowId) throws IOException {
        return getWorkflowUsage(owner, repo, workflowId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(String owner, String repo, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflowUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/"
                + workflowId + TIMING_PATH), format);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the repository from fetch the workflow usage
     * @param workflowName: the workflow file name
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(Repository repository, String workflowName) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflowName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the repository from fetch the workflow usage
     * @param workflowName: the workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(Repository repository, String workflowName, ReturnFormat format) throws IOException {
        return getWorkflowUsage(repository.getOwner().getLogin(), repository.getName(), workflowName, format);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @return workflow usage as {@link WorkflowUsage} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public WorkflowUsage getWorkflowUsage(String owner, String repo, String workflowName) throws IOException {
        return getWorkflowUsage(owner, repo, workflowName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes used by a specific workflow during the current billing cycle.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage.
     * The usage does not include the multiplier for macOS and Windows runners and is not rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions"
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: the workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
     * Get workflow usage</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/timing")
    public <T> T getWorkflowUsage(String owner, String repo, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflowUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/"
                + workflowName + TIMING_PATH), format);
    }

    /**
     * Method to create a workflow usage object
     *
     * @param workflowUsageResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return workflow usage object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflowUsage(String workflowUsageResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowUsageResponse);
            case LIBRARY_OBJECT:
                return (T) new WorkflowUsage(new JSONObject(workflowUsageResponse));
            default:
                return (T) workflowUsageResponse;
        }
    }

    /**
     * {@code ApprovalState} approval states list
     **/
    public enum ApprovalState {

        /**
         * {@code "approved"} approval state
         **/
        approved,

        /**
         * {@code "rejected"} approval state
         **/
        rejected

    }

}
