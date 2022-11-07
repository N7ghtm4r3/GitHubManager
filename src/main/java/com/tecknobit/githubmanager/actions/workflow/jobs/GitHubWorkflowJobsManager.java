package com.tecknobit.githubmanager.actions.workflow.jobs;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.JobsList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubWorkflowJobsManager} class is useful to manage all GitHub's workflows-jobs endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs">
 * About the Job jobs API</a>
 * @see GitHubManager
 **/
public class GitHubWorkflowJobsManager extends GitHubManager {

    /**
     * {@code ACTIONS_JOBS_PATH} constant for {@code "/actions/jobs"} path
     **/
    public static final String ACTIONS_JOBS_PATH = "/actions/jobs";

    /**
     * {@code ACTIONS_RUNS_PATH} constant for {@code "/actions/runs"} path
     **/
    public static final String ACTIONS_RUNS_PATH = "/actions/runs";

    /**
     * {@code LOGS_PATH} constant for {@code "/logs"} path
     **/
    public static final String LOGS_PATH = "/logs";

    /**
     * {@code ATTEMPTS_PATH} constant for {@code "/actions/runs"} path
     **/
    public static final String ATTEMPTS_PATH = "/attempts/";

    /**
     * {@code JOBS_PATH} constant for {@code "/jobs"} path
     **/
    public static final String JOBS_PATH = "/jobs";

    /**
     * Constructor to init a {@link GitHubWorkflowJobsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWorkflowJobsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowJobsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWorkflowJobsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowJobsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWorkflowJobsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowJobsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWorkflowJobsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWorkflowJobsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubWorkflowJobsManager}'s manager without re-insert
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
    public GitHubWorkflowJobsManager() {
        super();
    }

    public Job getWorkflowRunJob(Repository repository, long jobId) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName()
                + ACTIONS_JOBS_PATH + "/" + jobId), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunJob(Repository repository, long jobId, ReturnFormat format) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName()
                + ACTIONS_JOBS_PATH + "/" + jobId), format);
    }

    public Job getWorkflowRunJob(String owner, String repo, long jobId) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowRunJob(String owner, String repo, long jobId, ReturnFormat format) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId),
                format);
    }

    private <T> T returnJob(String jobResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(jobResponse);
            case LIBRARY_OBJECT:
                return (T) new Job(new JSONObject(jobResponse));
            default:
                return (T) jobResponse;
        }
    }

    public File downloadJobLogs(Repository repository, long jobId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadJobLogs(repository.getOwner().getLogin(), repository.getName(), jobId), pathName, save);
    }
    
    public File downloadJobLogs(String owner, String repo, long jobId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadJobLogs(owner, repo, jobId), pathName, save);
    }

    public String downloadJobLogs(Repository repository, long jobId) throws IOException {
        return downloadJobLogs(repository.getOwner().getLogin(), repository.getName(), jobId);
    }

    public String downloadJobLogs(String owner, String repo, long jobId) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    public JobsList getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber + JOBS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber + JOBS_PATH),
                format);
    }

    public JobsList getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber,
                                                Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                JOBS_PATH + queryParams.createQueryString()), format);
    }

    public JobsList getWorkflowAttemptsJobsList(String owner, String repo, long runId,
                                                int attemptNumber) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowAttemptsJobsList(String owner, String repo, long runId, int attemptNumber,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH), format);
    }

    public JobsList getWorkflowAttemptsJobsList(String owner, String repo, long runId,
                                                int attemptNumber, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowAttemptsJobsList(String owner, String repo, long runId, int attemptNumber,
                                             Params queryParams, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH + queryParams), format);
    }

    public JobsList getWorkflowJobsList(Repository repository, long runId) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowJobsList(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH), format);
    }

    public JobsList getWorkflowJobsList(Repository repository, long runId, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowJobsList(Repository repository, long runId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH +
                queryParams.createQueryString()), format);
    }

    public JobsList getWorkflowJobsList(String owner, String repo, long runId) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowJobsList(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH), format);
    }

    public JobsList getWorkflowJobsList(String owner, String repo, long runId, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getWorkflowJobsList(String owner, String repo, long runId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH + queryParams.createQueryString()), format);
    }

    private <T> T returnJobsList(String jobsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(jobsListResponse);
            case LIBRARY_OBJECT:
                return (T) new JobsList(new JSONObject(jobsListResponse));
            default:
                return (T) jobsListResponse;
        }
    }

}
