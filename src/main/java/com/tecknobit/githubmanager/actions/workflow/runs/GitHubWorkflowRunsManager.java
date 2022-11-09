package com.tecknobit.githubmanager.actions.workflow.runs;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job;
import com.tecknobit.githubmanager.actions.workflow.records.Workflow;
import com.tecknobit.githubmanager.actions.workflow.runs.records.*;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
// TODO: 03/11/2022 TEST JSON PAYLOAD WHEN FIXED
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
     * {@code TIMING_PATH} constant for {@code "/timing"} path
     **/
    public static final String TIMING_PATH = "/timing";

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

    public boolean rerunWorkflowJob(Repository repository, Job job, boolean enableDebugLogging) {
        return rerunWorkflowJob(repository.getOwner().getLogin(), repository.getName(), job.getId(), enableDebugLogging);
    }

    public boolean rerunWorkflowJob(Repository repository, long jobId, boolean enableDebugLogging) {
        return rerunWorkflowJob(repository.getOwner().getLogin(), repository.getName(), jobId, enableDebugLogging);
    }

    public boolean rerunWorkflowJob(String owner, String repo, Job job, boolean enableDebugLogging) {
        return rerunWorkflowJob(owner, repo, job.getId(), enableDebugLogging);
    }

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

    public WorkflowRunsList getRepositoryWorkflowRunsList(Repository repository) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflowRunsList(Repository repository, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH), format);
    }

    public WorkflowRunsList getRepositoryWorkflowRunsList(Repository repository, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflowRunsList(Repository repository, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getRepositoryWorkflowRunsList(String owner, String repo) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflowRunsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH),
                format);
    }

    public WorkflowRunsList getRepositoryWorkflowRunsList(String owner, String repo, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryWorkflowRunsList(String owner, String repo, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    public WorkflowRun getWorkflowRun(Repository repository, long runId) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRun(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId), format);
    }

    public WorkflowRun getWorkflowRun(Repository repository, long runId, Params queryParams) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + queryParams.createQueryString()),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRun(Repository repository, long runId, Params queryParams, ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + queryParams.createQueryString()), format);
    }

    public WorkflowRun getWorkflowRun(String owner, String repo, long runId) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRun(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId),
                format);
    }

    public WorkflowRun getWorkflowRun(String owner, String repo, long runId, Params queryParams) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRun(String owner, String repo, long runId, Params queryParams,
                                ReturnFormat format) throws IOException {
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + queryParams.createQueryString()), format);
    }

    public boolean deleteWorkflowRun(Repository repository, WorkflowRun run) {
        return deleteWorkflowRun(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    public boolean deleteWorkflowRun(Repository repository, long runId) {
        return deleteWorkflowRun(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    public boolean deleteWorkflowRun(String owner, String repo, WorkflowRun run) {
        return deleteWorkflowRun(owner, repo, run.getId());
    }

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

    public Collection<Review> getReviewsHistory(Repository repository, WorkflowRun run) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getReviewsHistory(Repository repository, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + APPROVALS_PATH), format);
    }

    public Collection<Review> getReviewsHistory(Repository repository, long runId) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getReviewsHistory(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + APPROVALS_PATH), format);
    }

    public Collection<Review> getReviewsHistory(String owner, String repo, WorkflowRun run) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getReviewsHistory(String owner, String repo, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + APPROVALS_PATH), format);
    }

    public Collection<Review> getReviewsHistory(String owner, String repo, long runId) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + APPROVALS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getReviewsHistory(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnReviewsHistory(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + APPROVALS_PATH), format);
    }

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

    public boolean approvePullRequestFork(Repository repository, WorkflowRun run) {
        return approvePullRequestFork(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    public boolean approvePullRequestFork(Repository repository, long runId) {
        return approvePullRequestFork(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    public boolean approvePullRequestFork(String owner, String repo, WorkflowRun run) {
        return approvePullRequestFork(owner, repo, run.getId());
    }

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

    public WorkflowRun getWorkflowRunAttempt(Repository repository, WorkflowRun run, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(repository, run, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunAttempt(Repository repository, WorkflowRun run, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + ATTEMPTS_PATH + attemptNumber +
                queryParams.createQueryString()), format);
    }

    public WorkflowRun getWorkflowRunAttempt(Repository repository, long runId, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(repository, runId, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunAttempt(Repository repository, long runId, int attemptNumber, boolean excludePullRequests,
                                       ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                queryParams.createQueryString()), format);
    }

    public WorkflowRun getWorkflowRunAttempt(String owner, String repo, WorkflowRun run, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(owner, repo, run, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunAttempt(String owner, String repo, WorkflowRun run, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + ATTEMPTS_PATH + attemptNumber + queryParams.createQueryString()), format);
    }

    public WorkflowRun getWorkflowRunAttempt(String owner, String repo, long runId, int attemptNumber,
                                             boolean excludePullRequests) throws IOException {
        return getWorkflowRunAttempt(owner, repo, runId, attemptNumber, excludePullRequests, LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunAttempt(String owner, String repo, long runId, int attemptNumber,
                                       boolean excludePullRequests, ReturnFormat format) throws IOException {
        Params queryParams = new Params();
        queryParams.addParam("exclude_pull_requests", excludePullRequests);
        return returnWorkflowRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + ATTEMPTS_PATH + attemptNumber + queryParams.createQueryString()), format);
    }

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

    public File downloadWorkflowAttemptLogs(Repository repository, WorkflowRun run, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(),
                run.getId(), attemptNumber), pathName, save);
    }

    public File downloadWorkflowAttemptLogs(Repository repository, long runId, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), runId,
                attemptNumber), pathName, save);
    }

    public File downloadWorkflowAttemptLogs(String owner, String repo, WorkflowRun run, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(owner, repo, run.getId(), attemptNumber), pathName, save);
    }

    public File downloadWorkflowAttemptLogs(String owner, String repo, long runId, int attemptNumber, String pathName,
                                            boolean save) throws IOException {
        return downloadFile(downloadWorkflowAttemptLogs(owner, repo, runId, attemptNumber), pathName, save);
    }

    public String downloadWorkflowAttemptLogs(Repository repository, WorkflowRun run, int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                attemptNumber);
    }

    public String downloadWorkflowAttemptLogs(Repository repository, long runId, int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(repository.getOwner().getLogin(), repository.getName(), runId, attemptNumber);
    }

    public String downloadWorkflowAttemptLogs(String owner, String repo, WorkflowRun run,
                                              int attemptNumber) throws IOException {
        return downloadWorkflowAttemptLogs(owner, repo, run.getId(), attemptNumber);
    }

    public String downloadWorkflowAttemptLogs(String owner, String repo, long runId, int attemptNumber) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH +
                attemptNumber + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    public boolean cancelWorkflowRun(Repository repository, WorkflowRun run) {
        return cancelWorkflowRun(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    public boolean cancelWorkflowRun(Repository repository, long runId) {
        return cancelWorkflowRun(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    public boolean cancelWorkflowRun(String owner, String repo, WorkflowRun run) {
        return cancelWorkflowRun(owner, repo, run.getId());
    }

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

    public File downloadWorkflowLogs(Repository repository, WorkflowRun run, String pathName,
                                     boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId()),
                pathName, save);
    }

    public File downloadWorkflowLogs(Repository repository, long runId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId),
                pathName, save);
    }

    public File downloadWorkflowLogs(String owner, String repo, WorkflowRun run, String pathName,
                                     boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(owner, repo, run.getId()), pathName, save);
    }

    public File downloadWorkflowLogs(String owner, String repo, long runId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadWorkflowLogs(owner, repo, runId), pathName, save);
    }

    public String downloadWorkflowLogs(Repository repository, WorkflowRun run) throws IOException {
        return downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    public String downloadWorkflowLogs(Repository repository, long runId) throws IOException {
        return downloadWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    public String downloadWorkflowLogs(String owner, String repo, WorkflowRun run) throws IOException {
        return downloadWorkflowLogs(owner, repo, run.getId());
    }

    public String downloadWorkflowLogs(String owner, String repo, long runId) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    public boolean deleteWorkflowLogs(Repository repository, WorkflowRun run) {
        return deleteWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), run.getId());
    }

    public boolean deleteWorkflowLogs(Repository repository, long runId) {
        return deleteWorkflowLogs(repository.getOwner().getLogin(), repository.getName(), runId);
    }

    public boolean deleteWorkflowLogs(String owner, String repo, WorkflowRun run) {
        return deleteWorkflowLogs(owner, repo, run.getId());
    }

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

    public Collection<Deployment> getWorkflowRunPendingDeployments(Repository repository,
                                                                   WorkflowRun run) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                        + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + PENDING_DEPLOYMENTS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunPendingDeployments(Repository repository, WorkflowRun run,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + PENDING_DEPLOYMENTS_PATH), format);
    }

    public Collection<Deployment> getWorkflowRunPendingDeployments(Repository repository, long runId) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunPendingDeployments(Repository repository, long runId,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + PENDING_DEPLOYMENTS_PATH), format);
    }

    public Collection<Deployment> getWorkflowRunPendingDeployments(String owner, String repo,
                                                                   WorkflowRun run) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunPendingDeployments(String owner, String repo, WorkflowRun run,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + PENDING_DEPLOYMENTS_PATH), format);
    }

    public Collection<Deployment> getWorkflowRunPendingDeployments(String owner, String repo,
                                                                   long runId) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + PENDING_DEPLOYMENTS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunPendingDeployments(String owner, String repo, long runId,
                                                  ReturnFormat format) throws IOException {
        return returnDeployments(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + PENDING_DEPLOYMENTS_PATH), format);
    }

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

    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, WorkflowRun run,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(Repository repository, WorkflowRun run, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, WorkflowRun run,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(String owner, String repo, WorkflowRun run, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, long runId,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(Repository repository, long runId, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, long runId,
                                                                 Collection<Long> environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, runId, environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(String owner, String repo, long runId, Collection<Long> environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("environment_ids", environmentsIds.stream().toList());
        payload.addParam("state", state);
        payload.addParam("comment", comment);
        return returnDeploymentsReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                runId + PENDING_DEPLOYMENTS_PATH, payload), format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, WorkflowRun run,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(Repository repository, WorkflowRun run, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), run.getId(),
                environmentsIds, state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, WorkflowRun run,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(String owner, String repo, WorkflowRun run, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(owner, repo, run.getId(), environmentsIds, state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(Repository repository, long runId,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(Repository repository, long runId, Long[] environmentsIds, ApprovalState state,
                                          String comment, ReturnFormat format) throws IOException {
        return reviewPendingDeployments(repository.getOwner().getLogin(), repository.getName(), runId, environmentsIds,
                state, comment, format);
    }

    public Collection<DeploymentReview> reviewPendingDeployments(String owner, String repo, long runId,
                                                                 Long[] environmentsIds, ApprovalState state,
                                                                 String comment) throws IOException {
        return reviewPendingDeployments(owner, repo, runId, environmentsIds, state, comment, LIBRARY_OBJECT);
    }

    public <T> T reviewPendingDeployments(String owner, String repo, long runId, Long[] environmentsIds,
                                          ApprovalState state, String comment, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("environment_ids", Arrays.stream(environmentsIds).toList());
        payload.addParam("state", state);
        payload.addParam("comment", comment);
        return returnDeploymentsReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH +
                runId + PENDING_DEPLOYMENTS_PATH, payload), format);
    }

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

    public boolean rerunWorkflow(Repository repository, WorkflowRun run, boolean enableDebugLogging) {
        return rerunWorkflow(repository.getOwner().getLogin(), repository.getName(), run.getId(), enableDebugLogging);
    }

    public boolean rerunWorkflow(Repository repository, long runId, boolean enableDebugLogging) {
        return rerunWorkflow(repository.getOwner().getLogin(), repository.getName(), runId, enableDebugLogging);
    }

    public boolean rerunWorkflow(String owner, String repo, WorkflowRun run, boolean enableDebugLogging) {
        return rerunWorkflow(owner, repo, run.getId(), enableDebugLogging);
    }

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

    public boolean rerunFailedWorkflowJob(Repository repository, WorkflowRun run, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(repository.getOwner().getLogin(), repository.getName(), run.getId(), enableDebugLogging);
    }

    public boolean rerunFailedWorkflowJob(Repository repository, long runId, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(repository.getOwner().getLogin(), repository.getName(), runId, enableDebugLogging);
    }

    public boolean rerunFailedWorkflowJob(String owner, String repo, WorkflowRun run, boolean enableDebugLogging) {
        return rerunFailedWorkflowJob(owner, repo, run.getId(), enableDebugLogging);
    }

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

    public WorkflowRunUsage getWorkflowRunUsage(Repository repository, WorkflowRun run) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + TIMING_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunUsage(Repository repository, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + run.getId() + TIMING_PATH), format);
    }

    public WorkflowRunUsage getWorkflowRunUsage(String owner, String repo, WorkflowRun run) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + TIMING_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunUsage(String owner, String repo, WorkflowRun run, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + run.getId() + TIMING_PATH), format);
    }

    public WorkflowRunUsage getWorkflowRunUsage(Repository repository, long runId) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + TIMING_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunUsage(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + TIMING_PATH), format);
    }

    public WorkflowRunUsage getWorkflowRunUsage(String owner, String repo, long runId) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + TIMING_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunUsage(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnWorkflowRunUsage(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/"
                + runId + TIMING_PATH), format);
    }

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

    public WorkflowRunsList getWorkflowRunsList(Repository repository, Workflow workflow) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, Workflow workflow, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(Repository repository, long workflowId) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(Repository repository, Workflow workflow,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH
                + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, Workflow workflow, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflow.getId() + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getWorkflowRunsList(Repository repository, long workflowId, Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH + queryParams.createQueryString()),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, long workflowId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowId + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, Workflow workflow) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, Workflow workflow, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, long workflowId) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, long workflowId, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, Workflow workflow,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, Workflow workflow, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflow.getId() + RUNS_PATH + queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, long workflowId,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, long workflowId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowId + RUNS_PATH + queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getWorkflowRunsList(Repository repository, String workflowName) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(Repository repository, String workflowName,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(Repository repository, String workflowName, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_WORKFLOWS_PATH + "/" + workflowName + RUNS_PATH +
                queryParams.createQueryString()), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, String workflowName) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, String workflowName, ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH), format);
    }

    public WorkflowRunsList getWorkflowRunsList(String owner, String repo, String workflowName,
                                                Params queryParams) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunsList(String owner, String repo, String workflowName, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnWorkflowRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_WORKFLOWS_PATH
                + "/" + workflowName + RUNS_PATH + queryParams.createQueryString()), format);
    }

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
