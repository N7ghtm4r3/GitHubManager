package com.tecknobit.githubmanager.checks.runs;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.checks.records.Check.CheckStatus;
import com.tecknobit.githubmanager.checks.runs.records.Action;
import com.tecknobit.githubmanager.checks.runs.records.CheckRun;
import com.tecknobit.githubmanager.checks.runs.records.CheckRun.CheckRunConclusion;
import com.tecknobit.githubmanager.checks.runs.records.CheckRun.CheckRunFilter;
import com.tecknobit.githubmanager.checks.runs.records.CheckRun.Output;
import com.tecknobit.githubmanager.checks.runs.records.CheckRunAnnotation;
import com.tecknobit.githubmanager.checks.runs.records.CheckRunsList;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuite;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.checks.suites.GitHubCheckSuitesManager.CHECK_SUITES_PATH;
import static com.tecknobit.githubmanager.commits.commits.GitHubCommitsManager.COMMITS_QUERY_PATH;

/**
 * The {@code GitHubCheckRunsManager} class is useful to manage all GitHub's check runs endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs">
 * Check Runs</a>
 * @see GitHubManager
 **/
public class GitHubCheckRunsManager extends GitHubManager {

    /**
     * {@code CHECK_RUNS_PATH} constant for {@code "/check-runs"} path
     **/
    public static final String CHECK_RUNS_PATH = "/check-runs";

    /**
     * {@code ANNOTATIONS_PATH} constant for {@code "/annotations"} path
     **/
    public static final String ANNOTATIONS_PATH = "/annotations";

    /**
     * {@code REREQUEST_PATH} constant for {@code "/rerequest"} path
     **/
    public static final String REREQUEST_PATH = "/rerequest";

    /**
     * Constructor to init a {@link GitHubCheckRunsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCheckRunsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCheckRunsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCheckRunsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCheckRunsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCheckRunsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckRunsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCheckRunsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckRunsManager} <br>
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
    public GitHubCheckRunsManager() {
        super();
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param repository: repository where create the check run
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public CheckRun createCheckRun(Repository repository, String name, String headSha) throws Exception {
        return createCheckRun(repository.getOwner().getLogin(), repository.getName(), name, headSha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param repository: repository where create the check run
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public <T> T createCheckRun(Repository repository, String name, String headSha, ReturnFormat format) throws Exception {
        return createCheckRun(repository.getOwner().getLogin(), repository.getName(), name, headSha, format);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param name:    the name of the check. For example, "code-coverage"
     * @param headSha: the SHA of the commit
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public CheckRun createCheckRun(String owner, String repo, String name, String headSha) throws Exception {
        return createCheckRun(owner, repo, name, headSha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param name:    the name of the check. For example, "code-coverage"
     * @param headSha: the SHA of the commit
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public <T> T createCheckRun(String owner, String repo, String name, String headSha, ReturnFormat format) throws Exception {
        return createCheckRun(owner, repo, name, headSha, new Params(), format);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param repository: repository where create the check run
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public CheckRun createCheckRun(Repository repository, String name, String headSha, Params bodyParams) throws Exception {
        return createCheckRun(repository.getOwner().getLogin(), repository.getName(), name, headSha, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param repository: repository where create the check run
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public <T> T createCheckRun(Repository repository, String name, String headSha, Params bodyParams,
                                ReturnFormat format) throws Exception {
        return createCheckRun(repository.getOwner().getLogin(), repository.getName(), name, headSha, bodyParams, format);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public CheckRun createCheckRun(String owner, String repo, String name, String headSha, Params bodyParams) throws Exception {
        return createCheckRun(owner, repo, name, headSha, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check run for a specific commit in a repository. <br>
     * Your GitHub App must have the {@code "checks:write"} permission to create check runs. <br>
     * In a check suite, GitHub limits the number of check runs with the same name to 1000. Once these check runs exceed 1000,
     * GitHub will start to automatically delete older check runs
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the check. For example, "code-coverage"
     * @param headSha:    the SHA of the commit
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
     * Create a check run</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs")
    public <T> T createCheckRun(String owner, String repo, String name, String headSha, Params bodyParams,
                                ReturnFormat format) throws Exception {
        bodyParams.addParam("name", name);
        bodyParams.addParam("head_sha", headSha);
        return returnCheckRun(sendPostRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH, bodyParams),
                format);
    }

    /**
     * Method to get a single check run using its id. GitHub Apps must have the {@code "checks:read"} permission on a private
     * repository or pull access to a public repository to get check runs. OAuth Apps and authenticated users must have the
     * repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the check run
     * @param checkRunId: the unique identifier of the check run
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public CheckRun getCheckRun(Repository repository, long checkRunId) throws Exception {
        return getCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRunId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single check run using its id. GitHub Apps must have the {@code "checks:read"} permission on a private
     * repository or pull access to a public repository to get check runs. OAuth Apps and authenticated users must have the
     * repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the check run
     * @param checkRunId: the unique identifier of the check run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public <T> T getCheckRun(Repository repository, long checkRunId, ReturnFormat format) throws Exception {
        return getCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRunId, format);
    }

    /**
     * Method to get a single check run using its id. GitHub Apps must have the {@code "checks:read"} permission on a private
     * repository or pull access to a public repository to get check runs. OAuth Apps and authenticated users must have the
     * repo scope to get check runs in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public CheckRun getCheckRun(String owner, String repo, long checkRunId) throws Exception {
        return getCheckRun(owner, repo, checkRunId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single check run using its id. GitHub Apps must have the {@code "checks:read"} permission on a private
     * repository or pull access to a public repository to get check runs. OAuth Apps and authenticated users must have the
     * repo scope to get check runs in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public <T> T getCheckRun(String owner, String repo, long checkRunId, ReturnFormat format) throws Exception {
        return returnCheckRun(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH + "/" + checkRunId),
                format);
    }

    /**
     * Method to update a check run for a specific commit in a repository.
     * Your GitHub App must have the {@code "checks:write"} permission to edit check runs
     *
     * @param repository: repository from fetch the check run
     * @param checkRunId: the unique identifier of the check run
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public CheckRun updateCheckRun(Repository repository, long checkRunId, Params bodyParams) throws Exception {
        return updateCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRunId, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a check run for a specific commit in a repository.
     * Your GitHub App must have the {@code "checks:write"} permission to edit check runs
     *
     * @param repository: repository from fetch the check run
     * @param checkRunId: the unique identifier of the check run
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public <T> T updateCheckRun(Repository repository, long checkRunId, Params bodyParams,
                                ReturnFormat format) throws Exception {
        return updateCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRunId, bodyParams, format);
    }

    /**
     * Method to update a check run for a specific commit in a repository.
     * Your GitHub App must have the {@code "checks:write"} permission to edit check runs
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @return check run as {@link CheckRun} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public CheckRun updateCheckRun(String owner, String repo, long checkRunId, Params bodyParams) throws Exception {
        return updateCheckRun(owner, repo, checkRunId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a check run for a specific commit in a repository.
     * Your GitHub App must have the {@code "checks:write"} permission to edit check runs
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "details_url"} -> the URL of the integrator's site that has the full details of the check.
     *                           If the integrator does not provide this, then the homepage of the GitHub app is used - [string]
     *                       </li>
     *                       <li>
     *                           {@code "external_id"} -> a reference for the run on the integrator's system - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "status"} -> the current status, constants available at {@link CheckStatus} -
     *                           [string, default queued]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the check run began.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "conclusion"} -> <b>Required if you provide completed_at or a status of completed.</b>
     *                           The final conclusion of the check. Note: Providing conclusion will automatically set
     *                           the status parameter to completed. You cannot change a check run conclusion to stale,
     *                           only GitHub can set this, constants available at {@link CheckRunConclusion} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "completed_at"} -> the time the check completed.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "output"} -> check runs can accept a variety of data in the output object,
     *                           including a title and summary and can optionally provide descriptive details about
     *                           the run, you can directly use the {@link Output} object and will be automatically
     *                           formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "actions"} -> displays a button on GitHub that can be clicked to alert your app
     *                           to do additional tasks. For example, a code linting app can display a button that automatically
     *                           fixes detected errors. The button created in this object is displayed after the check run completes.
     *                           When a user clicks the button, GitHub sends the check_run.requested_action webhook to your app.
     *                           Each action includes a label, identifier and description. A maximum of three actions are accepted.
     *                           To learn more about check runs and requested actions, see "Check runs and requested actions",
     *                           you can directly use an array or list of {@link Action} object and will be automatically formatted for
     *                           the request - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
     * Get a check run</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}")
    public <T> T updateCheckRun(String owner, String repo, long checkRunId, Params bodyParams,
                                ReturnFormat format) throws Exception {
        return returnCheckRun(sendPatchRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH + "/" +
                checkRunId, bodyParams), format);
    }

    /**
     * Method to create a check run object
     *
     * @param checkRunResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return check run as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCheckRun(String checkRunResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(checkRunResponse);
            case LIBRARY_OBJECT:
                return (T) new CheckRun(new JSONObject(checkRunResponse));
            default:
                return (T) checkRunResponse;
        }
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkRun:   check run from fetch the list
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(Repository repository, CheckRun checkRun) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRun.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkRun:   check run from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(Repository repository, CheckRun checkRun, ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRun.getId(), format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkRunId: the unique identifier of the check run
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(Repository repository, long checkRunId) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRunId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkRunId: the unique identifier of the check run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(Repository repository, long checkRunId, ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRunId, format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param checkRun: check run from fetch the list
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(String owner, String repo,
                                                                 CheckRun checkRun) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRun.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param checkRun: check run from fetch the list
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(String owner, String repo, CheckRun checkRun,
                                        ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRun.getId(), format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(String owner, String repo,
                                                                 long checkRunId) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRunId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(String owner, String repo, long checkRunId, ReturnFormat format) throws IOException {
        return returnCheckRunAnnotations(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH + "/" +
                checkRunId + ANNOTATIONS_PATH), format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkRun:    check run from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(Repository repository, CheckRun checkRun,
                                                                 Params queryParams) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRun.getId(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkRun:    check run from fetch the list
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
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(Repository repository, CheckRun checkRun, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRun.getId(),
                queryParams, format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkRunId:  the unique identifier of the check run
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(Repository repository, long checkRunId,
                                                                 Params queryParams) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRunId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkRunId:  the unique identifier of the check run
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
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(Repository repository, long checkRunId, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(repository.getOwner().getLogin(), repository.getName(), checkRunId, queryParams,
                format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkRun:    check run from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(String owner, String repo, CheckRun checkRun,
                                                                 Params queryParams) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRun.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkRun:    check run from fetch the list
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
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(String owner, String repo, CheckRun checkRun, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRun.getId(), queryParams, format);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkRunId:  the unique identifier of the check run
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run annotations as {@link Collection} of {@link CheckRunAnnotation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public Collection<CheckRunAnnotation> getCheckRunAnnotations(String owner, String repo, long checkRunId,
                                                                 Params queryParams) throws IOException {
        return getCheckRunAnnotations(owner, repo, checkRunId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of annotations for a check run using the annotation id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public repository to
     * get annotations for a check run. OAuth Apps and authenticated users must have the repo scope to get annotations
     * for a check run in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkRunId:  the unique identifier of the check run
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
     * @return check run annotations as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
     * List check run annotations</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/annotations")
    public <T> T getCheckRunAnnotations(String owner, String repo, long checkRunId, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return returnCheckRunAnnotations(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH + "/" +
                checkRunId + ANNOTATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a check run annotations list
     *
     * @param checkRunAnnotationsResponse: obtained from GitHub's response
     * @param format:                      return type formatter -> {@link ReturnFormat}
     * @return check run annotations as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCheckRunAnnotations(String checkRunAnnotationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(checkRunAnnotationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<CheckRunAnnotation> annotations = new ArrayList<>();
                JSONArray jAnnotations = new JSONArray(checkRunAnnotationsResponse);
                for (int j = 0; j < jAnnotations.length(); j++)
                    annotations.add(new CheckRunAnnotation(jAnnotations.getJSONObject(j)));
                return (T) annotations;
            default:
                return (T) checkRunAnnotationsResponse;
        }
    }

    /**
     * Method to trigger GitHub to rerequest an existing check run, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_run"} webhook event with the action rerequested.
     * When a check run is rerequested, its status is reset to queued and the conclusion is cleared.
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param repository: the repository from rerequest the check run
     * @param checkRun:   the check run to rerequest
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#rerequest-a-check-run">
     * Rerequest a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/rerequest")
    public boolean rerequestCheckRun(Repository repository, CheckRun checkRun) {
        return rerequestCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRun.getId());
    }

    /**
     * Method to trigger GitHub to rerequest an existing check run, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_run"} webhook event with the action rerequested.
     * When a check run is rerequested, its status is reset to queued and the conclusion is cleared.
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param repository: the repository from rerequest the check run
     * @param checkRunId: the unique identifier of the check run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#rerequest-a-check-run">
     * Rerequest a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/rerequest")
    public boolean rerequestCheckRun(Repository repository, long checkRunId) {
        return rerequestCheckRun(repository.getOwner().getLogin(), repository.getName(), checkRunId);
    }

    /**
     * Method to trigger GitHub to rerequest an existing check run, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_run"} webhook event with the action rerequested.
     * When a check run is rerequested, its status is reset to queued and the conclusion is cleared.
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param checkRun: the check run to rerequest
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#rerequest-a-check-run">
     * Rerequest a check run</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/rerequest")
    public boolean rerequestCheckRun(String owner, String repo, CheckRun checkRun) {
        return rerequestCheckRun(owner, repo, checkRun.getId());
    }

    /**
     * Method to trigger GitHub to rerequest an existing check run, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_run"} webhook event with the action rerequested.
     * When a check run is rerequested, its status is reset to queued and the conclusion is cleared.
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkRunId: the unique identifier of the check run
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#rerequest-a-check-run">
     * Rerequest a check run</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-runs/{check_run_id}/rerequest")
    public boolean rerequestCheckRun(String owner, String repo, long checkRunId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + CHECK_RUNS_PATH + "/" + checkRunId +
                    REREQUEST_PATH, null);
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
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkSuite: check suite from fetch the list
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(Repository repository, CheckSuite checkSuite) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuite.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the list
     * @param checkSuite: check suite from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(Repository repository, CheckSuite checkSuite, ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuite.getId(), format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:   repository from fetch the list
     * @param checkSuiteId: the unique identifier of the check suite
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(Repository repository, long checkSuiteId) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuiteId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:   repository from fetch the list
     * @param checkSuiteId: the unique identifier of the check suite
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(Repository repository, long checkSuiteId, ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuiteId, format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkSuite: check suite from fetch the list
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(String owner, String repo, CheckSuite checkSuite) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuite.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkSuite: check suite from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(String owner, String repo, CheckSuite checkSuite,
                                        ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuite.getId(), format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(String owner, String repo, long checkSuiteId) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuiteId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(String owner, String repo, long checkSuiteId, ReturnFormat format) throws Exception {
        return returnCheckRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PATH + "/" +
                checkSuiteId + CHECK_RUNS_PATH), format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkSuite:  check suite from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(Repository repository, CheckSuite checkSuite,
                                                Params queryParams) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuite.getId(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param checkSuite:  check suite from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(Repository repository, CheckSuite checkSuite, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuite.getId(),
                queryParams, format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:   repository from fetch the list
     * @param checkSuiteId: the unique identifier of the check suite
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "check_name"} -> returns check runs with the specified name - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns check runs with the specified status,
     *                             constants available at {@link CheckStatus} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                             latest returns the most recent check runs,
     *                             constants available at {@link CheckRunFilter} - [string, default latest]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(Repository repository, long checkSuiteId,
                                                Params queryParams) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuiteId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:   repository from fetch the list
     * @param checkSuiteId: the unique identifier of the check suite
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "check_name"} -> returns check runs with the specified name - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns check runs with the specified status,
     *                             constants available at {@link CheckStatus} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                             latest returns the most recent check runs,
     *                             constants available at {@link CheckRunFilter} - [string, default latest]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(Repository repository, long checkSuiteId, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(repository.getOwner().getLogin(), repository.getName(), checkSuiteId, queryParams,
                format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkSuite:  check suite from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(String owner, String repo, CheckSuite checkSuite,
                                                Params queryParams) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuite.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param checkSuite:  check suite from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(String owner, String repo, CheckSuite checkSuite, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuite.getId(), queryParams, format);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "check_name"} -> returns check runs with the specified name - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns check runs with the specified status,
     *                             constants available at {@link CheckStatus} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                             latest returns the most recent check runs,
     *                             constants available at {@link CheckRunFilter} - [string, default latest]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public CheckRunsList getCheckSuiteCheckRuns(String owner, String repo, long checkSuiteId,
                                                Params queryParams) throws Exception {
        return getCheckSuiteCheckRuns(owner, repo, checkSuiteId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a check suite using its id.
     * GitHub Apps must have the {@code "checks:read"} permission on a private repository or pull access to a public
     * repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "check_name"} -> returns check runs with the specified name - [string]
     *                         </li>
     *                         <li>
     *                             {@code "status"} -> returns check runs with the specified status,
     *                             constants available at {@link CheckStatus} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                             latest returns the most recent check runs,
     *                             constants available at {@link CheckRunFilter} - [string, default latest]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
     * List check runs in a check suite</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/check-runs")
    public <T> T getCheckSuiteCheckRuns(String owner, String repo, long checkSuiteId, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return returnCheckRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PATH + "/" +
                checkSuiteId + CHECK_RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the list
     * @param ref:        ref parameter
     * @return check run list as {@link CheckRunsList} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public CheckRunsList getGitReferenceCheckRuns(Repository repository, String ref) throws Exception {
        return getGitReferenceCheckRuns(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository: repository from fetch the list
     * @param ref:        ref parameter
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public <T> T getGitReferenceCheckRuns(Repository repository, String ref, ReturnFormat format) throws Exception {
        return getGitReferenceCheckRuns(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return check run list as {@link CheckRunsList} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public CheckRunsList getGitReferenceCheckRuns(String owner, String repo, String ref) throws Exception {
        return getGitReferenceCheckRuns(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param ref:    ref parameter
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public <T> T getGitReferenceCheckRuns(String owner, String repo, String ref, ReturnFormat format) throws Exception {
        return returnCheckRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH + ref +
                CHECK_RUNS_PATH), format);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public CheckRunsList getGitReferenceCheckRuns(Repository repository, String ref, Params queryParams) throws Exception {
        return getGitReferenceCheckRuns(repository.getOwner().getLogin(), repository.getName(), ref, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public <T> T getGitReferenceCheckRuns(Repository repository, String ref, Params queryParams,
                                          ReturnFormat format) throws Exception {
        return getGitReferenceCheckRuns(repository.getOwner().getLogin(), repository.getName(), ref, queryParams,
                format);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check run list as {@link CheckRunsList} custom object
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public CheckRunsList getGitReferenceCheckRuns(String owner, String repo, String ref, Params queryParams) throws Exception {
        return getGitReferenceCheckRuns(owner, repo, ref, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of check runs for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"}
     * permission on a private repository or pull access to a public repository to get check runs.
     * OAuth Apps and authenticated users must have the repo scope to get check runs in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "status"} -> returns check runs with the specified status,
     *                            constants available at {@link CheckStatus} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "filter"} -> filters check runs by their completed_at timestamp.
     *                            latest returns the most recent check runs,
     *                            constants available at {@link CheckRunFilter} - [string, default latest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
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
     * @implNote the Checks API only looks for pushes in the repository where the check suite or check run were created.
     * Pushes to a branch in a forked repository are not detected and return an empty {@code "pull_requests"} array
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
     * List check runs for a Git reference</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-runs")
    public <T> T getGitReferenceCheckRuns(String owner, String repo, String ref, Params queryParams,
                                          ReturnFormat format) throws Exception {
        return returnCheckRunsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH + ref +
                CHECK_RUNS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a check runs list
     *
     * @param checkRunsListResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return check runs list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCheckRunsList(String checkRunsListResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(checkRunsListResponse);
            case LIBRARY_OBJECT:
                return (T) new CheckRunsList(new JSONObject(checkRunsListResponse));
            default:
                return (T) checkRunsListResponse;
        }
    }

}
