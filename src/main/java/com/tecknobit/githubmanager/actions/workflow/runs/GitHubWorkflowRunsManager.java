package com.tecknobit.githubmanager.actions.workflow.runs;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job;
import com.tecknobit.githubmanager.actions.workflow.records.Workflow;
import com.tecknobit.githubmanager.actions.workflow.runs.records.*;
import com.tecknobit.githubmanager.actions.workflow.runs.records.WorkflowRun.WorkflowRunStatus;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.downloadFile;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.*;
import static com.tecknobit.githubmanager.actions.workflow.jobs.GitHubWorkflowJobsManager.LOGS_PATH;

/**
 * The {@code GitHubWorkflowRunsManager} class is useful to manage all GitHub's workflows-runs endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs">
 * About the Job runs API</a>
 * @see GitHubManager
 **/
public class GitHubWorkflowRunsManager extends GitHubManager {

    /**
     * {@code RERUN_PATH} constant for {@code "/rerun"} path
     **/
    public static final String RERUN_PATH = "/rerun";

    /**
     * {@code APPROVALS_PATH} constant for {@code "/approvals"} path
     **/
    public static final String APPROVALS_PATH = "/approvals";

    /**
     * {@code APPROVE_PATH} constant for {@code "/approve"} path
     **/
    public static final String APPROVE_PATH = "/approve";

    /**
     * {@code CANCEL_PATH} constant for {@code "/cancel"} path
     **/
    public static final String CANCEL_PATH = "/cancel";

    /**
     * {@code PENDING_DEPLOYMENTS_PATH} constant for {@code "/pending_deployments"} path
     **/
    public static final String PENDING_DEPLOYMENTS_PATH = "/pending_deployments";

    /**
     * {@code RERUN_FAILED_JOBS_PATH} constant for {@code "/rerun-failed-jobs"} path
     **/
    public static final String RERUN_FAILED_JOBS_PATH = "/rerun-failed-jobs";

    /**
     * Constructor to init a {@link GitHubWorkflowRunsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWorkflowRunsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowRunsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWorkflowRunsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowRunsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWorkflowRunsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowRunsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWorkflowRunsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowRunsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubWorkflowRunsManager}'s manager without re-insert
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
    public GitHubWorkflowRunsManager() {
        super();
    }

    /**
     * Method to Re-run a job and its dependent jobs in a workflow run.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository to rerun the workflow job
     * @param job:                job to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-job-from-a-workflow-run">
     * Re-run a job from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/jobs/{job_id}/rerun")
    public boolean rerunWorkflowJob(Repository repository, Job job, boolean enableDebugLogging) {
        return rerunWorkflowJob(repository.getOwner().getLogin(), repository.getName(), job.getId(), enableDebugLogging);
    }

    /**
     * Method to Re-run a job and its dependent jobs in a workflow run.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository to rerun the workflow job
     * @param jobId:              the unique identifier of the job
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-job-from-a-workflow-run">
     * Re-run a job from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/jobs/{job_id}/rerun")
    public boolean rerunWorkflowJob(Repository repository, long jobId, boolean enableDebugLogging) {
        return rerunWorkflowJob(repository.getOwner().getLogin(), repository.getName(), jobId, enableDebugLogging);
    }

    /**
     * Method to Re-run a job and its dependent jobs in a workflow run.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param job:                job to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-job-from-a-workflow-run">
     * Re-run a job from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/jobs/{job_id}/rerun")
    public boolean rerunWorkflowJob(String owner, String repo, Job job, boolean enableDebugLogging) {
        return rerunWorkflowJob(owner, repo, job.getId(), enableDebugLogging);
    }

    /**
     * Method to Re-run a job and its dependent jobs in a workflow run.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param jobId:              the unique identifier of the job
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-job-from-a-workflow-run">
     * Re-run a job from a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/jobs/{job_id}/rerun")
    public boolean rerunWorkflowJob(String owner, String repo, long jobId, boolean enableDebugLogging) {
        Params params = new Params();
        if (enableDebugLogging)
            params.addParam("enable_debug_logging", true);
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId + RERUN_PATH, params);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository: the repository to fetch the list
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public WorkflowRunsList getRepositoryWorkflowRunsList(Repository repository) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository: the repository to fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public <T> T getRepositoryWorkflowRunsList(Repository repository, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:  the repository to fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public WorkflowRunsList getRepositoryWorkflowRunsList(Repository repository, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:  the repository to fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public <T> T getRepositoryWorkflowRunsList(Repository repository, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public WorkflowRunsList getRepositoryWorkflowRunsList(String owner, String repo) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public <T> T getRepositoryWorkflowRunsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH),
                format);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public WorkflowRunsList getRepositoryWorkflowRunsList(String owner, String repo, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a repository. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
     * List workflow runs for a repository</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs")
    public <T> T getRepositoryWorkflowRunsList(String owner, String repo, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow rum
     * @param runId:               the unique identifier of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run">
     * Get a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public WorkflowRun getWorkflowRun(Repository repository, long runId, boolean excludePullRequests) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + "?exclude_pull_requests=" + excludePullRequests),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow rum
     * @param runId:               the unique identifier of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run">
     * Get a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public <T> T getWorkflowRun(Repository repository, long runId, boolean excludePullRequests,
                                ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + "?exclude_pull_requests=" + excludePullRequests),
                format);
    }

    /**
     * Method to get a specific workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param runId:               the unique identifier of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run">
     * Get a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public WorkflowRun getWorkflowRun(String owner, String repo, long runId, boolean excludePullRequests) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId
                + "?exclude_pull_requests=" + excludePullRequests), LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param runId:               the unique identifier of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run">
     * Get a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public <T> T getWorkflowRun(String owner, String repo, long runId, boolean excludePullRequests,
                                ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                "?exclude_pull_requests=" + excludePullRequests), format);
    }

    /**
     * Method to delete a specific workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from remove the workflow run
     * @param run:        workflow run to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-a-workflow-run">
     * Delete a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public boolean deleteWorkflowRun(Repository repository, WorkflowRun run) {
        return deleteWorkflowRun(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    /**
     * Method to delete a specific workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from remove the workflow run
     * @param runId:      the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-a-workflow-run">
     * Delete a workflow run</a>
     **/
    @WrappedRequest
    public boolean deleteWorkflowRun(Repository repository, long runId) {
        return deleteWorkflowRun(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    /**
     * Method to delete a specific workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   workflow run to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-a-workflow-run">
     * Delete a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public boolean deleteWorkflowRun(String owner, String repo, WorkflowRun run) {
        return deleteWorkflowRun(owner, repo, run.getId());
    }

    /**
     * Method to delete a specific workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-a-workflow-run">
     * Delete a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}")
    public boolean deleteWorkflowRun(String owner, String repo, long runId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId);
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
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param run:        the workflow run from fetch the list
     * @return reviews history as {@link Collection} of {@link Review} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public Collection<Review> getReviewsHistory(Repository repository, WorkflowRun run) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param run:        the workflow run from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reviews history as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public <T> T getReviewsHistory(Repository repository, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + APPROVALS_PATH), format);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier workflow run
     * @return reviews history as {@link Collection} of {@link Review} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public Collection<Review> getReviewsHistory(Repository repository, long runId) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier workflow run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reviews history as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public <T> T getReviewsHistory(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + APPROVALS_PATH), format);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the list
     * @return reviews history as {@link Collection} of {@link Review} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public Collection<Review> getReviewsHistory(String owner, String repo, WorkflowRun run) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param run:    the workflow run from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return reviews history as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public <T> T getReviewsHistory(String owner, String repo, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + APPROVALS_PATH), format);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return reviews history as {@link Collection} of {@link Review} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public Collection<Review> getReviewsHistory(String owner, String repo, long runId) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a reviews history
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runId:  the unique identifier workflow run
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return reviews history as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run">
     * Get the review history for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approvals")
    public <T> T getReviewsHistory(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + APPROVALS_PATH), format);
    }

    /**
     * Method to create a reviews history
     *
     * @param reviewsHistoryResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return reviews history as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReviewsHistory(String reviewsHistoryResponse, ReturnFormat format) {
        try {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(reviewsHistoryResponse);
                case LIBRARY_OBJECT:
                    JSONArray jReviews = new JSONArray(reviewsHistoryResponse);
                    ArrayList<Review> reviews = new ArrayList<>();
                    for (int j = 0; j < jReviews.length(); j++)
                        reviews.add(new Review(jReviews.getJSONObject(j)));
                    return (T) reviews;
                default:
                    return (T) reviewsHistoryResponse;
            }
        } catch (JSONException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to approve a workflow run for a pull request from a public fork of a first time contributor.
     * For more information, see Approving workflow runs from public forks.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from approve pull request fork
     * @param run:        the workflow run from fetch the list
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#approve-a-workflow-run-for-a-fork-pull-request">
     * Approve a workflow run for a fork pull request</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approve")
    public boolean approvePullRequestFork(Repository repository, WorkflowRun run) {
        return approvePullRequestFork(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    /**
     * Method to approve a workflow run for a pull request from a public fork of a first time contributor.
     * For more information, see Approving workflow runs from public forks.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from approve pull request fork
     * @param runId:      the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#approve-a-workflow-run-for-a-fork-pull-request">
     * Approve a workflow run for a fork pull request</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approve")
    public boolean approvePullRequestFork(Repository repository, long runId) {
        return approvePullRequestFork(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    /**
     * Method to approve a workflow run for a pull request from a public fork of a first time contributor.
     * For more information, see Approving workflow runs from public forks.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the list
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#approve-a-workflow-run-for-a-fork-pull-request">
     * Approve a workflow run for a fork pull request</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approve")
    public boolean approvePullRequestFork(String owner, String repo, WorkflowRun run) {
        return approvePullRequestFork(owner, repo, run.getId());
    }

    /**
     * Method to approve a workflow run for a pull request from a public fork of a first time contributor.
     * For more information, see Approving workflow runs from public forks.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#approve-a-workflow-run-for-a-fork-pull-request">
     * Approve a workflow run for a fork pull request</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/approve")
    public boolean approvePullRequestFork(String owner, String repo, long runId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + APPROVE_PATH, null);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow run attempt
     * @param run:                 the workflow run from fetch the workflow run attempt
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public WorkflowRun getWorkflowRunAttempt(Repository repository, WorkflowRun run, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(repository, run, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow run attempt
     * @param run:                 the workflow run from fetch the workflow run attempt
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public <T> T getWorkflowRunAttempt(Repository repository, WorkflowRun run, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + ATTEMPTS_PATH + attemptNumber +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow run attempt
     * @param runId:               the unique identifier workflow run
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public WorkflowRun getWorkflowRunAttempt(Repository repository, long runId, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(repository, runId, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:          the repository from fetch the workflow run attempt
     * @param runId:               the unique identifier workflow run
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public <T> T getWorkflowRunAttempt(Repository repository, long runId, int attemptNumber, boolean excludePullRequests,
                                       ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param run:                 the workflow run from fetch the workflow run attempt
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public WorkflowRun getWorkflowRunAttempt(String owner, String repo, WorkflowRun run, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(owner, repo, run, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param run:                 the workflow run from fetch the workflow run attempt
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public <T> T getWorkflowRunAttempt(String owner, String repo, WorkflowRun run, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + ATTEMPTS_PATH + attemptNumber + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param runId:               the unique identifier workflow run
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @return workflow run as {@link WorkflowRun} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public WorkflowRun getWorkflowRunAttempt(String owner, String repo, long runId, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(owner, repo, runId, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific workflow run attempt
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param runId:               the unique identifier workflow run
     * @param attemptNumber:       the attempt number of the workflow run
     * @param excludePullRequests: if {@code "true"} pull requests are omitted from the response (empty array)
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
     * Get a workflow run attempt</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}")
    public <T> T getWorkflowRunAttempt(String owner, String repo, long runId, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + ATTEMPTS_PATH + attemptNumber + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a workflow run object
     *
     * @param workflowRunResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return workflow run object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflowRun(String workflowRunResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowRunResponse);
            case LIBRARY_OBJECT:
                return (T) new WorkflowRun(new JSONObject(workflowRunResponse));
            default:
                return (T) workflowRunResponse;
        }
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:    the repository from fetch the workflow run attempt logs
     * @param run:           the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @param pathName:      path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:          flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                       that will be deleted on exit
     * @return workflow run attempt logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public File downloadWorkflowAttemptLogs(Repository repository, WorkflowRun run, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(),
                run.getId(), attemptNumber), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:    the repository from fetch the workflow run attempt logs
     * @param runId:         the unique identifier workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param pathName:      path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:          flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                       that will be deleted on exit
     * @return workflow run attempt logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public File downloadWorkflowAttemptLogs(Repository repository, long runId, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), runId,
                attemptNumber), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param run:           the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @param pathName:      path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:          flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                       that will be deleted on exit
     * @return workflow run attempt logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public File downloadWorkflowAttemptLogs(String owner, String repo, WorkflowRun run, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(owner, repo, run.getId(), attemptNumber), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the unique identifier workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param pathName:      path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:          flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                       that will be deleted on exit
     * @return workflow run attempt logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public File downloadWorkflowAttemptLogs(String owner, String repo, long runId, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(owner, repo, runId, attemptNumber), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:    the repository from fetch the workflow run attempt logs
     * @param run:           the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @return workflow run attempt logs download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public String downloadWorkflowAttemptLogs(Repository repository, WorkflowRun run, int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                attemptNumber);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository:    the repository from fetch the workflow run attempt logs
     * @param runId:         the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @return workflow run attempt logs download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public String downloadWorkflowAttemptLogs(Repository repository, long runId, int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), runId, attemptNumber);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param run:           the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @return workflow run attempt logs download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public String downloadWorkflowAttemptLogs(String owner, String repo, WorkflowRun run,
                                              int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(owner, repo, run.getId(), attemptNumber);
    }

    /**
     * Method to download an archive of log files for a specific workflow run attempt.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the workflow run from fetch the workflow run attempt logs
     * @param attemptNumber: the attempt number of the workflow run
     * @return workflow run attempt logs download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-attempt-logs>
     * Download workflow run attempt logs</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/attempts/{attempt_number}/logs")
    public String downloadWorkflowAttemptLogs(String owner, String repo, long runId, int attemptNumber) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH +
                attemptNumber + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    /**
     * Method to cancel a workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from cancel workflow run
     * @param run:        the workflow run from fetch the workflow run attempt logs
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#cancel-a-workflow-run">
     * Cancel a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/cancel")
    public boolean cancelWorkflowRun(Repository repository, WorkflowRun run) {
        return cancelWorkflowRun(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    /**
     * Method to cancel a workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from cancel workflow run
     * @param runId:      the workflow run from fetch the workflow run attempt logs
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#cancel-a-workflow-run">
     * Cancel a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/cancel")
    public boolean cancelWorkflowRun(Repository repository, long runId) {
        return cancelWorkflowRun(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    /**
     * Method to cancel a workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the workflow run attempt logs
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#cancel-a-workflow-run">
     * Cancel a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/cancel")
    public boolean cancelWorkflowRun(String owner, String repo, WorkflowRun run) {
        return cancelWorkflowRun(owner, repo, run.getId());
    }

    /**
     * Method to cancel a workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#cancel-a-workflow-run">
     * Cancel a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/cancel")
    public boolean cancelWorkflowRun(String owner, String repo, long runId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + CANCEL_PATH);
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
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the workflow run logs
     * @param run:        the workflow run from fetch the workflow run logs
     * @param pathName:   path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:       flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                    that will be deleted on exit
     * @return workflow run as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public File downloadWorkflowLogs(Repository repository, WorkflowRun run, String pathName,
                                     boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId()),
                pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the workflow run logs
     * @param runId:      the unique identifier workflow run
     * @param pathName:   path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:       flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                    that will be deleted on exit
     * @return workflow run as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public File downloadWorkflowLogs(Repository repository, long runId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId),
                pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param run:      the workflow run from fetch the workflow run logs
     * @param pathName: path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:     flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                  that will be deleted on exit
     * @return workflow run as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public File downloadWorkflowLogs(String owner, String repo, WorkflowRun run, String pathName,
                                     boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(owner, repo, run.getId()), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runId:    the unique identifier workflow run
     * @param pathName: path name for the file, this must include also the .zip suffix es. -> download.zip
     * @param save:     flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                  that will be deleted on exit
     * @return workflow run as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public File downloadWorkflowLogs(String owner, String repo, long runId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(owner, repo, runId), pathName, save);
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the workflow run logs
     * @param run:        the workflow run from fetch the workflow run logs
     * @return workflow run download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public String downloadWorkflowLogs(Repository repository, WorkflowRun run) throws IOException {
        return downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the workflow run logs
     * @param runId:      the unique identifier workflow run
     * @return workflow run download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public String downloadWorkflowLogs(Repository repository, long runId) throws IOException {
        return downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the workflow run logs
     * @return workflow run download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public String downloadWorkflowLogs(String owner, String repo, WorkflowRun run) throws IOException {
        return downloadWorkflowLogs(owner, repo, run.getId());
    }

    /**
     * Method to download an archive of log files for a specific workflow run.
     * This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return workflow run download url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#download-workflow-run-logs>
     * Download workflow run logs</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public String downloadWorkflowLogs(String owner, String repo, long runId) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    /**
     * Method to delete all logs for a workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from delete the workflow logs
     * @param run:        the workflow run from fetch the workflow run logs
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-workflow-run-logs">
     * Delete workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public boolean deleteWorkflowLogs(Repository repository, WorkflowRun run) {
        return deleteWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    /**
     * Method to delete all logs for a workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository from delete the workflow logs
     * @param runId:      the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-workflow-run-logs">
     * Delete workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public boolean deleteWorkflowLogs(Repository repository, long runId) {
        return deleteWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    /**
     * Method to delete all logs for a workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the workflow run logs
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-workflow-run-logs">
     * Delete workflow run logs</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public boolean deleteWorkflowLogs(String owner, String repo, WorkflowRun run) {
        return deleteWorkflowLogs(owner, repo, run.getId());
    }

    /**
     * Method to delete all logs for a workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#delete-workflow-run-logs">
     * Delete workflow run logs</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/logs")
    public boolean deleteWorkflowLogs(String owner, String repo, long runId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + LOGS_PATH);
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
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param run:        the workflow run from fetch the list
     * @return pending deployments as {@link Collection} of {@link Deployment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<Deployment> getWorkflowRunPendingDeployments(Repository repository,
                                                                   WorkflowRun run) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                        + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + PENDING_DEPLOYMENTS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param run:        the workflow run from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pending deployments as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T getWorkflowRunPendingDeployments(Repository repository, WorkflowRun run,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + PENDING_DEPLOYMENTS_PATH), format);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier workflow run
     * @return pending deployments as {@link Collection} of {@link Deployment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<Deployment> getWorkflowRunPendingDeployments(Repository repository, long runId) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier workflow run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pending deployments as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T getWorkflowRunPendingDeployments(Repository repository, long runId,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + PENDING_DEPLOYMENTS_PATH), format);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the workflow run from fetch the list
     * @return pending deployments as {@link Collection} of {@link Deployment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<Deployment> getWorkflowRunPendingDeployments(String owner, String repo,
                                                                   WorkflowRun run) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param run:    the workflow run from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pending deployments as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T getWorkflowRunPendingDeployments(String owner, String repo, WorkflowRun run,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + PENDING_DEPLOYMENTS_PATH), format);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return pending deployments as {@link Collection} of {@link Deployment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<Deployment> getWorkflowRunPendingDeployments(String owner, String repo,
                                                                   long runId) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all deployment environments for a workflow run that are waiting for protection rules to pass
     * Anyone with read access to the repository can use this endpoint
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runId:  the unique identifier workflow run
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pending deployments as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     * Get pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T getWorkflowRunPendingDeployments(String owner, String repo, long runId,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + PENDING_DEPLOYMENTS_PATH), format);
    }

    /**
     * Method to create a deployments list
     *
     * @param deploymentsResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return deployments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeployments(String deploymentsResponse, ReturnFormat format) {
        try {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(deploymentsResponse);
                case LIBRARY_OBJECT:
                    JSONArray jDeployments = new JSONArray(deploymentsResponse);
                    ArrayList<Deployment> deployments = new ArrayList<>();
                    for (int j = 0; j < jDeployments.length(); j++)
                        deployments.add(new Deployment(jDeployments.getJSONObject(j)));
                    return (T) deployments;
                default:
                    return (T) deploymentsResponse;
            }
        } catch (JSONException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, WorkflowRun run,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(Repository repository, WorkflowRun run, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, WorkflowRun run,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(String owner, String repo, WorkflowRun run, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, long runId,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(Repository repository, long runId, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, long runId,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, runId, environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in {@link Collection} of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(String owner, String repo, long runId, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("environment_ids", environmentsIds.stream().toList());
        payload.addParam("state", state);
        payload.addParam("comment", comment);
        return returnDeploymentsReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                runId + PENDING_DEPLOYMENTS_PATH, payload), format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, WorkflowRun run,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(Repository repository, WorkflowRun run, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, WorkflowRun run,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param run:             the workflow run from approve the deployment
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(String owner, String repo, WorkflowRun run, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, long runId,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:      the repository from approve the deployment
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(Repository repository, long runId, Long[] environmentsIds, ApprovalState state,
                                          String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, format);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @return pending deployments as {@link Collection} of {@link DeploymentReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, long runId,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, runId, environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to approve or reject pending deployments that are waiting on approval by a required reviewer.
     * Required reviewers with read access to the repository contents and deployments can use this endpoint.
     * Required reviewers must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param runId:           the unique identifier workflow run
     * @param environmentsIds: the list of environment ids to approve or reject in array of {@link Long} format
     * @param state:           whether to approve or reject deployment to the specified environments
     * @param comment:         a comment to accompany the deployment review
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return deployments reviewed list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
     * Review pending deployments for a workflow run</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/pending_deployments")
    public <T> T reviewPendingDeployments(String owner, String repo, long runId, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("environment_ids", Arrays.stream(environmentsIds).toList());
        payload.addParam("state", state);
        payload.addParam("comment", comment);
        return returnDeploymentsReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                runId + PENDING_DEPLOYMENTS_PATH, payload), format);
    }

    /**
     * Method to create a deployments review list
     *
     * @param deploymentsReviewResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return deployments review list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeploymentsReview(String deploymentsReviewResponse, ReturnFormat format) {
        try {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(deploymentsReviewResponse);
                case LIBRARY_OBJECT:
                    JSONArray jDeploymentsReview = new JSONArray(deploymentsReviewResponse);
                    ArrayList<DeploymentReview> deployments = new ArrayList<>();
                    for (int j = 0; j < jDeploymentsReview.length(); j++)
                        deployments.add(new DeploymentReview(jDeploymentsReview.getJSONObject(j)));
                    return (T) deployments;
                default:
                    return (T) deploymentsReviewResponse;
            }
        } catch (JSONException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to re-run your workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository from rerun the workflow
     * @param run:                the workflow to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-workflow">
     * Re-run a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun")
    public boolean rerunWorkflow(Repository repository, WorkflowRun run, boolean enableDebugLogging) {
        return rerunWorkflow(repository.getOwner().getLogin(), repository.getName(), run.getId(), enableDebugLogging);
    }

    /**
     * Method to re-run your workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository from rerun the workflow
     * @param runId:              the unique identifier of the workflow run
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-workflow">
     * Re-run a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun")
    public boolean rerunWorkflow(Repository repository, long runId, boolean enableDebugLogging) {
        return rerunWorkflow(repository.getOwner().getLogin(), repository.getName(), runId, enableDebugLogging);
    }

    /**
     * Method to re-run your workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param run:                the workflow to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-workflow">
     * Re-run a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun")
    public boolean rerunWorkflow(String owner, String repo, WorkflowRun run, boolean enableDebugLogging) {
        return rerunWorkflow(owner, repo, run.getId(), enableDebugLogging);
    }

    /**
     * Method to re-run your workflow run using its id
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param runId:              the unique identifier of the workflow run
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-a-workflow">
     * Re-run a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun")
    public boolean rerunWorkflow(String owner, String repo, long runId, boolean enableDebugLogging) {
        Params params = new Params();
        if (enableDebugLogging)
            params.addParam("enable_debug_logging", true);
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + RERUN_PATH, params);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to re-run all the failed jobs and their dependent jobs in a workflow run using the id of the workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository from rerun the workflow
     * @param run:                the workflow failed to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-failed-jobs-from-a-workflow-run">
     * Re-run failed jobs from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun-failed-jobs")
    public boolean rerunFailedWorkflowJob(Repository repository, WorkflowRun run, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(repository.getOwner().getLogin(), repository.getName(), run.getId(), enableDebugLogging);
    }

    /**
     * Method to re-run all the failed jobs and their dependent jobs in a workflow run using the id of the workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:         the repository from rerun the workflow
     * @param runId:              the unique identifier of the workflow run
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-failed-jobs-from-a-workflow-run">
     * Re-run failed jobs from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun-failed-jobs")
    public boolean rerunFailedWorkflowJob(Repository repository, long runId, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(repository.getOwner().getLogin(), repository.getName(), runId, enableDebugLogging);
    }

    /**
     * Method to re-run all the failed jobs and their dependent jobs in a workflow run using the id of the workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param run:                the workflow failed to rerun
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-failed-jobs-from-a-workflow-run">
     * Re-run failed jobs from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun-failed-jobs")
    public boolean rerunFailedWorkflowJob(String owner, String repo, WorkflowRun run, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(owner, repo, run.getId(), enableDebugLogging);
    }

    /**
     * Method to re-run all the failed jobs and their dependent jobs in a workflow run using the id of the workflow run
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:              the account owner of the repository. The name is not case-sensitive
     * @param repo:               the name of the repository. The name is not case-sensitive
     * @param runId:              the unique identifier of the workflow run
     * @param enableDebugLogging: whether to enable debug logging for the re-run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#re-run-failed-jobs-from-a-workflow-run">
     * Re-run failed jobs from a workflow run</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/rerun-failed-jobs")
    public boolean rerunFailedWorkflowJob(String owner, String repo, long runId, boolean enableDebugLogging) {
        Params params = new Params();
        if (enableDebugLogging)
            params.addParam("enable_debug_logging", true);
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                    RERUN_FAILED_JOBS_PATH, params);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param run:        the  workflow run from fetch the workflow run usage
     * @return workflow run usage as {@link WorkflowRunUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public WorkflowRunUsage getWorkflowRunUsage(Repository repository, WorkflowRun run) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + TIMING_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param run:        the  workflow run from fetch the workflow run usage
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow run usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public <T> T getWorkflowRunUsage(Repository repository, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + TIMING_PATH), format);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param run:   the  workflow run from fetch the workflow run usage
     * @return workflow run usage as {@link WorkflowRunUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public WorkflowRunUsage getWorkflowRunUsage(String owner, String repo, WorkflowRun run) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + TIMING_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param run:    the  workflow run from fetch the workflow run usage
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return workflow run usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public <T> T getWorkflowRunUsage(String owner, String repo, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + TIMING_PATH), format);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param runId:      the unique identifier of the workflow run
     * @return workflow run usage as {@link WorkflowRunUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public WorkflowRunUsage getWorkflowRunUsage(Repository repository, long runId) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + TIMING_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param runId:      the unique identifier of the workflow run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow run usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public <T> T getWorkflowRunUsage(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + TIMING_PATH), format);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"} to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier workflow run
     * @return workflow run usage as {@link WorkflowRunUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public WorkflowRunUsage getWorkflowRunUsage(String owner, String repo, long runId) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + TIMING_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the number of billable minutes and total run time for a specific workflow run.
     * Billable minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Usage is listed for each GitHub-hosted runner operating system in milliseconds.
     * Any job re-runs are also included in the usage. The usage does not include the multiplier for macOS and Windows
     * runners and is not rounded up to the nearest whole minute. For more information, see Managing billing for GitHub Actions
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"} to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runId:  the unique identifier workflow run
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return workflow run usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
     * Get workflow run usage</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/runs/{run_id}/timing")
    public <T> T getWorkflowRunUsage(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + TIMING_PATH), format);
    }

    /**
     * Method to create a workflow run usage object
     *
     * @param workflowRunUsage: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return workflow run usage object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflowRunUsage(String workflowRunUsage, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowRunUsage);
            case LIBRARY_OBJECT:
                return (T) new WorkflowRunUsage(new JSONObject(workflowRunUsage));
            default:
                return (T) workflowRunUsage;
        }
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param workflow:   the  workflow from fetch the workflow runs list
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, Workflow workflow) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param workflow:   the  workflow from fetch the workflow runs list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, Workflow workflow, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, long workflowId) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository: the  repository from fetch the workflow run usage
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:  the  repository from fetch the workflow run usage
     * @param workflow:    the  workflow from fetch the workflow runs list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, Workflow workflow,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH
                + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:  the  repository from fetch the workflow run usage
     * @param workflow:    the  workflow from fetch the workflow runs list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, Workflow workflow, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:  the  repository from fetch the workflow run usage
     * @param workflowId:  the {@code "ID"} of the workflow
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, long workflowId, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:  the  repository from fetch the workflow run usage
     * @param workflowId:  the {@code "ID"} of the workflow
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, long workflowId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the  workflow from fetch the workflow runs list
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, Workflow workflow) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param workflow: the  workflow from fetch the workflow runs list
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, Workflow workflow, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, long workflowId) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param workflowId: the {@code "ID"} of the workflow
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param workflow:    the  workflow from fetch the workflow runs list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, Workflow workflow,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param workflow:    the  workflow from fetch the workflow runs list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, Workflow workflow, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param workflowId:  the {@code "ID"} of the workflow
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, long workflowId,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param workflowId:  the {@code "ID"} of the workflow
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, long workflowId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the  repository from fetch the workflow run usage
     * @param workflowName: workflow file name
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, String workflowName) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the  repository from fetch the workflow run usage
     * @param workflowName: workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the  repository from fetch the workflow run usage
     * @param workflowName: workflow file name
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                             the push associated with the check suite or workflow run - [string]
     *                         </li>
     *                         <li>
     *                             {@code "branch"} -> returns workflow runs associated with a branch.
     *                             Use the name of the branch of the {@code "push"} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "event"} -> returns workflow run triggered by the event you specify.
     *                             For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                             For more information, see Events that trigger workflows. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                             For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                             Only GitHub can set a status of waiting or requested
     *                             Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                             {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                             {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                              - constants available at {@link WorkflowRunStatus}
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                         <li>
     *                             {@code "created"} -> returns workflow runs created within the given date-time range.
     *                             For more information on the syntax, see Understanding the search syntax. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                             (empty array) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                             that you specify - [string]
     *                         </li>
     *                         <li>
     *                             {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                             {@code "head_sha"} - [string]
     *                         </li>
     *                      </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(Repository repository, String workflowName,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * <p>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param repository:   the  repository from fetch the workflow run usage
     * @param workflowName: workflow file name
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                             the push associated with the check suite or workflow run - [string]
     *                         </li>
     *                         <li>
     *                             {@code "branch"} -> returns workflow runs associated with a branch.
     *                             Use the name of the branch of the {@code "push"} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "event"} -> returns workflow run triggered by the event you specify.
     *                             For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                             For more information, see Events that trigger workflows. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                             For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                             Only GitHub can set a status of waiting or requested
     *                             Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                             {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                             {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                              - constants available at {@link WorkflowRunStatus}
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                         <li>
     *                             {@code "created"} -> returns workflow runs created within the given date-time range.
     *                             For more information on the syntax, see Understanding the search syntax. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                             (empty array) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                             that you specify - [string]
     *                         </li>
     *                         <li>
     *                             {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                             {@code "head_sha"} - [string]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(Repository repository, String workflowName, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: workflow file name
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, String workflowName) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: workflow file name
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH), format);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param workflowName: workflow file name
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                             the push associated with the check suite or workflow run - [string]
     *                         </li>
     *                         <li>
     *                             {@code "branch"} -> returns workflow runs associated with a branch.
     *                             Use the name of the branch of the {@code "push"} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "event"} -> returns workflow run triggered by the event you specify.
     *                             For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                             For more information, see Events that trigger workflows. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                             For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                             Only GitHub can set a status of waiting or requested
     *                             Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                             {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                             {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                              - constants available at {@link WorkflowRunStatus}
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                         <li>
     *                             {@code "created"} -> returns workflow runs created within the given date-time range.
     *                             For more information on the syntax, see Understanding the search syntax. - [string]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                             (empty array) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                             that you specify - [string]
     *                         </li>
     *                         <li>
     *                             {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                             {@code "head_sha"} - [string]
     *                         </li>
     *                      </ul>
     * @return workflow runs list as {@link WorkflowRunsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, String workflowName,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all workflow runs for a workflow. You can replace workflow_id with the workflow file name.
     * For example, you could use main.yaml. You can use parameters to narrow the list of results.
     * For more information about using parameters, see Parameters
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope  -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read permission"}
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param workflowName: workflow file name
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "actor"} -> returns someone's workflow runs. Use the login for the user who created
     *                            the push associated with the check suite or workflow run - [string]
     *                        </li>
     *                        <li>
     *                            {@code "branch"} -> returns workflow runs associated with a branch.
     *                            Use the name of the branch of the {@code "push"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> returns workflow run triggered by the event you specify.
     *                            For example, {@code "push"}, {@code "pull_request"} or {@code "issues"}.
     *                            For more information, see Events that trigger workflows. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns workflow runs with the check run status or conclusion that you specify.
     *                            For example, a conclusion can be success or a status can be {@code "in_progress"}.
     *                            Only GitHub can set a status of waiting or requested
     *                            Can be one of: {@code "completed"}, {@code "action_required"}, {@code "cancelled"}, {@code "failure"},
     *                            {@code "neutral"}, {@code "skipped"}, {@code "stale"}, {@code "success"}, {@code "timed_out"},
     *                            {@code "in_progress"}, {@code "queued"}, {@code "requested"}, {@code "waiting"} - [string]
     *                             - constants available at {@link WorkflowRunStatus}
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "created"} -> returns workflow runs created within the given date-time range.
     *                            For more information on the syntax, see Understanding the search syntax. - [string]
     *                        </li>
     *                        <li>
     *                            {@code "exclude_pull_requests"} -> if true pull requests are omitted from the response
     *                            (empty array) - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "check_suite_id"} -> returns workflow runs with the {@code "check_suite_id"}
     *                            that you specify - [string]
     *                        </li>
     *                        <li>
     *                            {@code "head_sha"} -> only returns workflow runs that are associated with the specified
     *                            {@code "head_sha"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
     * List workflow runs for a workflow</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/actions/workflows/{workflow_id}/runs")
    public <T> T getWorkflowRunsList(String owner, String repo, String workflowName, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a workflow runs list
     *
     * @param workflowRunsResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return workflow runs list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWorkflowRunsList(String workflowRunsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(workflowRunsResponse);
            case LIBRARY_OBJECT:
                return (T) new WorkflowRunsList(new JSONObject(workflowRunsResponse));
            default:
                return (T) workflowRunsResponse;
        }
    }

}
