package com.tecknobit.githubmanager.actions.workflow;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.records.Workflow;
import com.tecknobit.githubmanager.actions.workflow.records.WorkflowsList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubWorkflowsManager} class is useful to manage all GitHub's workflows endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows">
 * About the Workflows API</a>
 * @see GitHubManager
 **/
// TODO: 03/11/2022 TEST JSON PAYLOAD WHEN FIXED
public class GitHubWorkflowsManager extends GitHubManager {

    /**
     * {@code ATTEMPTS_PATH} constant for {@code "/actions/runs"} path
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

    public WorkflowsList getRepositoryWorkflows(Repository repository) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWorkflowsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWorkflowsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWorkflowsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWorkflowsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager} <br>
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
    public GitHubWorkflowsManager() {
        super();
    }

    public <T> T getRepositoryWorkflows(Repository repository, ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH), format);
    }

    public WorkflowsList getRepositoryWorkflows(String owner, String repo) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflows(String owner, String repo, ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH),
                format);
    }

    public WorkflowsList getRepositoryWorkflows(Repository repository, Params queryParams) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflows(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + queryParams.createQueryString()), format);
    }

    public WorkflowsList getRepositoryWorkflows(String owner, String repo, Params queryParams) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflows(String owner, String repo, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return returnWorkflowsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH +
                queryParams.createQueryString()), format);
    }

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

    public Workflow getWorkflow(Repository repository, long workflowId) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId, LIBRARY_OBJECT);
    }

    public <T> T getWorkflow(Repository repository, long workflowId, ReturnFormat format) throws IOException {
        return getWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId, format);
    }

    public Workflow getWorkflow(String owner, String repo, long workflowId) throws IOException {
        return getWorkflow(owner, repo, workflowId, LIBRARY_OBJECT);
    }

    public <T> T getWorkflow(String owner, String repo, long workflowId, ReturnFormat format) throws IOException {
        String workflowResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowResponse);
            case LIBRARY_OBJECT:
                return (T) new Workflow(new JSONObject(workflowResponse));
            default:
                return (T) workflowResponse;
        }
    }

    public boolean disableWorkflow(Repository repository, Workflow workflow) {
        return disableWorkflow(repository.getOwner().getLogin(), repository.getName(), workflow.getId());
    }

    public boolean disableWorkflow(String owner, String repo, Workflow workflow) {
        return disableWorkflow(owner, repo, workflow.getId());
    }

    public boolean disableWorkflow(Repository repository, long workflowId) {
        return disableWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId);
    }

    public boolean disableWorkflow(String owner, String repo, long workflowId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflowId +
                    DISABLE_PATH);
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

    public boolean createWorkflowDispatchEvent(Repository repository, Workflow workflow, String ref) {
        return createWorkflowDispatchEvent(repository.getOwner().getLogin(), repository.getName(), workflow.getId(),
                ref, null);
    }

    public boolean createWorkflowDispatchEvent(String owner, String repo, Workflow workflow, String ref) {
        return createWorkflowDispatchEvent(owner, repo, workflow.getId(), ref, null);
    }

    public boolean createWorkflowDispatchEvent(Repository repository, long workflowId, String ref) {
        return createWorkflowDispatchEvent(repository.getOwner().getLogin(), repository.getName(), workflowId, ref, null);
    }

    public boolean createWorkflowDispatchEvent(String owner, String repo, long workflowId, String ref) {
        return createWorkflowDispatchEvent(owner, repo, workflowId, ref, null);
    }

    public boolean createWorkflowDispatchEvent(Repository repository, Workflow workflow, String ref, Params inputs) {
        return createWorkflowDispatchEvent(repository.getOwner().getLogin(), repository.getName(), workflow.getId(), ref,
                inputs);
    }

    public boolean createWorkflowDispatchEvent(String owner, String repo, Workflow workflow, String ref, Params inputs) {
        return createWorkflowDispatchEvent(owner, repo, workflow.getId(), ref, inputs);
    }

    public boolean createWorkflowDispatchEvent(Repository repository, long workflowId, String ref, Params inputs) {
        return createWorkflowDispatchEvent(repository.getOwner().getLogin(), repository.getName(), workflowId, ref,
                inputs);
    }

    public boolean createWorkflowDispatchEvent(String owner, String repo, long workflowId, String ref, Params inputs) {
        if (inputs == null)
            inputs = new Params();
        inputs.addParam("ref", ref);
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflowId +
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

    public boolean enableWorkflow(Repository repository, Workflow workflow) {
        return enableWorkflow(repository.getOwner().getLogin(), repository.getName(), workflow.getId());
    }

    public boolean enableWorkflow(String owner, String repo, Workflow workflow) {
        return enableWorkflow(owner, repo, workflow.getId());
    }

    public boolean enableWorkflow(Repository repository, long workflowId) {
        return enableWorkflow(repository.getOwner().getLogin(), repository.getName(), workflowId);
    }

    public boolean enableWorkflow(String owner, String repo, long workflowId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH + "/" + workflowId +
                    ENABLE_PATH);
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

    public enum ApprovalState {

        approved,
        rejected

    }

    
}
