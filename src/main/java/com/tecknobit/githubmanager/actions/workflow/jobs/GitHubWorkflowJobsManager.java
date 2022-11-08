package com.tecknobit.githubmanager.actions.workflow.jobs;

import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.JobsList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ACTIONS_JOBS_PATH;

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

    /**
     * Method to get a specific job in a workflow run. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param jobId:      the unique identifier of the job
     * @return job in a workflow run as {@link Job} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
     * Get a job for a workflow run</a>
     **/
    @WrappedRequest
    public Job getWorkflowRunJob(Repository repository, long jobId) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName()
                + ACTIONS_JOBS_PATH + "/" + jobId), LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific job in a workflow run. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param jobId:      the unique identifier of the job
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return job in a workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
     * Get a job for a workflow run</a>
     **/
    @WrappedRequest
    public <T> T getWorkflowRunJob(Repository repository, long jobId, ReturnFormat format) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName()
                + ACTIONS_JOBS_PATH + "/" + jobId), format);
    }

    /**
     * Method to get a specific job in a workflow run. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param jobId: the unique identifier of the job
     * @return job in a workflow run as {@link Job} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
     * Get a job for a workflow run</a>
     **/
    public Job getWorkflowRunJob(String owner, String repo, long jobId) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific job in a workflow run. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param jobId:  the unique identifier of the job
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return job in a workflow run as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
     * Get a job for a workflow run</a>
     **/
    public <T> T getWorkflowRunJob(String owner, String repo, long jobId, ReturnFormat format) throws IOException {
        return returnJob(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId),
                format);
    }

    /**
     * Method to create a job object
     *
     * @param jobResponse: obtained from GitHub's response
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return job object as {@code "format"} defines
     **/
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

    /**
     * Method to get a redirect URL to download a plain text file of logs for a workflow job. This link expires after 1 minute.
     * Look for {@code "Location:"} in the response header to find the URL for the download. -> <b> this step is automatically made
     * by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from download the logs list
     * @param jobId:      the unique identifier of the job
     * @param pathName:   path name for the file, this must include also the suffix es. -> download.json
     * @param save:       flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                    that will be deleted on exit
     * @return jobs logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#download-job-logs-for-a-workflow-run">
     * Download job logs for a workflow run</a>
     * @apiNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    public File downloadJobLogs(Repository repository, long jobId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadJobLogs(repository.getOwner().getLogin(), repository.getName(), jobId), pathName, save);
    }

    /**
     * Method to get a redirect URL to download a plain text file of logs for a workflow job. This link expires after 1 minute.
     * Look for {@code "Location:"} in the response header to find the URL for the download. -> <b> this step is automatically made
     * by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param jobId:    the unique identifier of the job
     * @param pathName: path name for the file, this must include also the suffix es. -> download.json
     * @param save:     flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                  that will be deleted on exit
     * @return jobs logs as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#download-job-logs-for-a-workflow-run">
     * Download job logs for a workflow run</a>
     * @apiNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    public File downloadJobLogs(String owner, String repo, long jobId, String pathName, boolean save) throws IOException {
        return downloadFile(downloadJobLogs(owner, repo, jobId), pathName, save);
    }

    /**
     * Method to get a redirect URL to download a plain text file of logs for a workflow job. This link expires after 1 minute.
     * Look for {@code "Location:"} in the response header to find the URL for the download. -> <b> this step is automatically made
     * by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from download the logs list
     * @param jobId:      the unique identifier of the job
     * @return jobs logs url download as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#download-job-logs-for-a-workflow-run">
     * Download job logs for a workflow run</a>
     * @apiNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    public String downloadJobLogs(Repository repository, long jobId) throws IOException {
        return downloadJobLogs(repository.getOwner().getLogin(), repository.getName(), jobId);
    }

    /**
     * Method to get a redirect URL to download a plain text file of logs for a workflow job. This link expires after 1 minute.
     * Look for {@code "Location:"} in the response header to find the URL for the download. -> <b> this step is automatically made
     * by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param jobId: the unique identifier of the job
     * @return jobs logs url download as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#download-job-logs-for-a-workflow-run">
     * Download job logs for a workflow run</a>
     * @apiNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    public String downloadJobLogs(String owner, String repo, long jobId) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_JOBS_PATH + "/" + jobId + LOGS_PATH);
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:    the repository from fetch the list
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    @WrappedRequest
    public JobsList getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber + JOBS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:    the repository from fetch the list
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    @WrappedRequest
    public <T> T getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                        repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber + JOBS_PATH),
                format);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:    the repository from fetch the list
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    @WrappedRequest
    public JobsList getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber,
                                                Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:    the repository from fetch the list
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    @WrappedRequest
    public <T> T getWorkflowAttemptsJobsList(Repository repository, long runId, int attemptNumber, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + ATTEMPTS_PATH + attemptNumber +
                JOBS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    public JobsList getWorkflowAttemptsJobsList(String owner, String repo, long runId,
                                                int attemptNumber) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    public <T> T getWorkflowAttemptsJobsList(String owner, String repo, long runId, int attemptNumber,
                                             ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH), format);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    public JobsList getWorkflowAttemptsJobsList(String owner, String repo, long runId,
                                                int attemptNumber, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a specific workflow run attempt.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param runId:         the unique identifier of the workflow run
     * @param attemptNumber: the attempt number of the workflow run
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
     * List jobs for a workflow run attempt</a>
     **/
    public <T> T getWorkflowAttemptsJobsList(String owner, String repo, long runId, int attemptNumber,
                                             Params queryParams, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                ATTEMPTS_PATH + attemptNumber + JOBS_PATH + queryParams), format);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier of the workflow run
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    @WrappedRequest
    public JobsList getWorkflowJobsList(Repository repository, long runId) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository: the repository from fetch the list
     * @param runId:      the unique identifier of the workflow run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    @WrappedRequest
    public <T> T getWorkflowJobsList(Repository repository, long runId, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH), format);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:  the repository from fetch the list
     * @param runId:       the unique identifier of the workflow run
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    @WrappedRequest
    public JobsList getWorkflowJobsList(Repository repository, long runId, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param repository:  the repository from fetch the list
     * @param runId:       the unique identifier of the workflow run
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
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    @WrappedRequest
    public <T> T getWorkflowJobsList(Repository repository, long runId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_RUNS_PATH + "/" + runId + JOBS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier of the workflow run
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    public JobsList getWorkflowJobsList(String owner, String repo, long runId) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runId:  the unique identifier of the workflow run
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    public <T> T getWorkflowJobsList(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH), format);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param runId:       the unique identifier of the workflow run
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return jobs list as {@link JobsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    public JobsList getWorkflowJobsList(String owner, String repo, long runId, Params queryParams) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a jobs list for a workflow run.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:read"} permission to use this endpoint <br>
     * You can use parameters to narrow the list of results. For more information about using parameters, see Parameters.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param runId:       the unique identifier of the workflow run
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
     * @return jobs list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
     * List jobs for a workflow run</a>
     **/
    public <T> T getWorkflowJobsList(String owner, String repo, long runId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnJobsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNS_PATH + "/" + runId +
                JOBS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a jobs list
     *
     * @param jobsListResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return jobs list as {@code "format"} defines
     **/
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
