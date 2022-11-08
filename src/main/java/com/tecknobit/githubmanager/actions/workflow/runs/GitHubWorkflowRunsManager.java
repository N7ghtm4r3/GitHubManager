package com.tecknobit.githubmanager.actions.workflow.runs;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job;
import com.tecknobit.githubmanager.records.repository.Repository;

import java.io.IOException;

import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ACTIONS_JOBS_PATH;

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

}
