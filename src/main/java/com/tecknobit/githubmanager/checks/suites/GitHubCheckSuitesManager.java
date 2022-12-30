package com.tecknobit.githubmanager.checks.suites;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuite;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuitesList;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuitesPreferences;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuitesPreferences.SuitesPreferences.AutoTriggerCheck;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.checks.runs.GitHubCheckRunsManager.COMMITS_PATH;
import static com.tecknobit.githubmanager.checks.runs.GitHubCheckRunsManager.REREQUEST_PATH;

/**
 * The {@code GitHubCheckSuitesManager} class is useful to manage all GitHub's check suites endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites">
 * Check Suites</a>
 * @see GitHubManager
 **/
public class GitHubCheckSuitesManager extends GitHubManager {

    /**
     * {@code CHECK_SUITES_PATH} constant for {@code "/check-suites"} path
     **/
    public static final String CHECK_SUITES_PATH = "/check-suites";

    /**
     * {@code CHECK_SUITES_PREFERENCES_PATH} constant for {@code "/check-suites/preferences"} path
     **/
    public static final String CHECK_SUITES_PREFERENCES_PATH = CHECK_SUITES_PATH + "/preferences";

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCheckSuitesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCheckSuitesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCheckSuitesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCheckSuitesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager} <br>
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
    public GitHubCheckSuitesManager() {
        super();
    }

    /**
     * Method to create a new check suite for a specific commit in a repository. <br>
     * By default, check suites are automatically created when you create a check run.
     * You only need to use this endpoint for manually creating check suites when you've disabled automatic creation using
     * "Update repository preferences for check suites".
     * Your GitHub App must have the {@code "checks:write"} permission to create check suites
     *
     * @param repository: repository where create the check suite
     * @param headSha:    the SHA of the commit
     * @return check suite as {@link CheckSuite} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#create-a-check-suite">
     * Create a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites")
    public CheckSuite createCheckSuite(Repository repository, String headSha) throws Exception {
        return createCheckSuite(repository.getOwner().getLogin(), repository.getName(), headSha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check suite for a specific commit in a repository. <br>
     * By default, check suites are automatically created when you create a check run.
     * You only need to use this endpoint for manually creating check suites when you've disabled automatic creation using
     * "Update repository preferences for check suites".
     * Your GitHub App must have the {@code "checks:write"} permission to create check suites
     *
     * @param repository: repository where create the check suite
     * @param headSha:    the SHA of the commit
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check suite as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#create-a-check-suite">
     * Create a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites")
    public <T> T createCheckSuite(Repository repository, String headSha, ReturnFormat format) throws Exception {
        return createCheckSuite(repository.getOwner().getLogin(), repository.getName(), headSha, format);
    }

    /**
     * Method to create a new check suite for a specific commit in a repository. <br>
     * By default, check suites are automatically created when you create a check run.
     * You only need to use this endpoint for manually creating check suites when you've disabled automatic creation using
     * "Update repository preferences for check suites".
     * Your GitHub App must have the {@code "checks:write"} permission to create check suites
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param headSha: the SHA of the commit
     * @return check suite as {@link CheckSuite} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#create-a-check-suite">
     * Create a check suite</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites")
    public CheckSuite createCheckSuite(String owner, String repo, String headSha) throws Exception {
        return createCheckSuite(owner, repo, headSha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new check suite for a specific commit in a repository. <br>
     * By default, check suites are automatically created when you create a check run.
     * You only need to use this endpoint for manually creating check suites when you've disabled automatic creation using
     * "Update repository preferences for check suites".
     * Your GitHub App must have the {@code "checks:write"} permission to create check suites
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param headSha: the SHA of the commit
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return check suite as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#create-a-check-suite">
     * Create a check suite</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites")
    public <T> T createCheckSuite(String owner, String repo, String headSha, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("head_sha", headSha);
        return returnCheckSuite(sendPostRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PATH, payload),
                format);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param repository:        repository where update the check suite preferences
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as {@link Collection} of {@link AutoTriggerCheck} format
     * @return check suite preferences as {@link CheckSuitesPreferences} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public CheckSuitesPreferences updateSuitesRepositoryPreferences(Repository repository,
                                                                    Collection<AutoTriggerCheck> autoTriggerChecks) throws Exception {
        return updateSuitesRepositoryPreferences(repository.getOwner().getLogin(), repository.getName(), autoTriggerChecks,
                LIBRARY_OBJECT);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param repository:        repository where update the check suite preferences
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as {@link Collection} of {@link AutoTriggerCheck} format
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return check suite preferences as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public <T> T updateSuitesRepositoryPreferences(Repository repository, Collection<AutoTriggerCheck> autoTriggerChecks,
                                                   ReturnFormat format) throws Exception {
        return updateSuitesRepositoryPreferences(repository.getOwner().getLogin(), repository.getName(), autoTriggerChecks,
                format);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as {@link Collection} of {@link AutoTriggerCheck} format
     * @return check suite preferences as {@link CheckSuitesPreferences} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public CheckSuitesPreferences updateSuitesRepositoryPreferences(String owner, String repo,
                                                                    Collection<AutoTriggerCheck> autoTriggerChecks) throws Exception {
        return updateSuitesRepositoryPreferences(owner, repo, autoTriggerChecks, LIBRARY_OBJECT);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as {@link Collection} of {@link AutoTriggerCheck} format
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return check suite preferences as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public <T> T updateSuitesRepositoryPreferences(String owner, String repo, Collection<AutoTriggerCheck> autoTriggerChecks,
                                                   ReturnFormat format) throws Exception {
        return updateSuitesRepositoryPreferences(owner, repo, autoTriggerChecks.toArray(new AutoTriggerCheck[0]), format);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param repository:        repository where update the check suite preferences
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as array of {@link AutoTriggerCheck} format
     * @return check suite preferences as {@link CheckSuitesPreferences} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public CheckSuitesPreferences updateSuitesRepositoryPreferences(Repository repository,
                                                                    AutoTriggerCheck[] autoTriggerChecks) throws Exception {
        return updateSuitesRepositoryPreferences(repository.getOwner().getLogin(), repository.getName(), autoTriggerChecks,
                LIBRARY_OBJECT);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param repository:        repository where update the check suite preferences
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as array of {@link AutoTriggerCheck} format
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return check suite preferences as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public <T> T updateSuitesRepositoryPreferences(Repository repository, AutoTriggerCheck[] autoTriggerChecks,
                                                   ReturnFormat format) throws Exception {
        return updateSuitesRepositoryPreferences(repository.getOwner().getLogin(), repository.getName(), autoTriggerChecks,
                format);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as array of {@link AutoTriggerCheck} format
     * @return check suite preferences as {@link CheckSuitesPreferences} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public CheckSuitesPreferences updateSuitesRepositoryPreferences(String owner, String repo,
                                                                    AutoTriggerCheck[] autoTriggerChecks) throws Exception {
        return updateSuitesRepositoryPreferences(owner, repo, autoTriggerChecks, LIBRARY_OBJECT);
    }

    /**
     * Method to change the default automatic flow when creating check suites. By default, a check suite is automatically
     * created each time code is pushed to a repository.
     * When you disable the automatic creation of check suites, you can manually Create a check suite.
     * You must have admin permissions in the repository to set preferences for check suites
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param autoTriggerChecks: enables or disables automatic creation of CheckSuite events upon pushes to the repository.
     *                           Enabled by default as array of {@link AutoTriggerCheck} format
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return check suite preferences as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
     * Update repository preferences for check suites</a>
     **/
    @Returner
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/check-suites/preferences")
    public <T> T updateSuitesRepositoryPreferences(String owner, String repo, AutoTriggerCheck[] autoTriggerChecks,
                                                   ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("auto_trigger_checks", autoTriggerChecks);
        String preferencesResponse = sendPatchRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PREFERENCES_PATH,
                payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(preferencesResponse);
            case LIBRARY_OBJECT:
                return (T) new CheckSuitesPreferences(new JSONObject(preferencesResponse));
            default:
                return (T) preferencesResponse;
        }
    }

    /**
     * Method to get a single check suite using its id. GitHub Apps must have the checks:read permission on a private repository
     * or pull access to a public repository to get check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository:   repository from fetch the check suite
     * @param checkSuiteId: the unique identifier of the check suite
     * @return check suite as {@link CheckSuite} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#get-a-check-suite">
     * Get a check suite</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}")
    public CheckSuite getCheckSuite(Repository repository, long checkSuiteId) throws Exception {
        return getCheckSuite(repository.getOwner().getLogin(), repository.getName(), checkSuiteId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single check suite using its id. GitHub Apps must have the checks:read permission on a private repository
     * or pull access to a public repository to get check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository:   repository from fetch the check suite
     * @param checkSuiteId: the unique identifier of the check suite
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check suite as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#get-a-check-suite">
     * Get a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}")
    public <T> T getCheckSuite(Repository repository, long checkSuiteId, ReturnFormat format) throws Exception {
        return getCheckSuite(repository.getOwner().getLogin(), repository.getName(), checkSuiteId, format);
    }

    /**
     * Method to get a single check suite using its id. GitHub Apps must have the checks:read permission on a private repository
     * or pull access to a public repository to get check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @return check suite as {@link CheckSuite} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#get-a-check-suite">
     * Get a check suite</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}")
    public CheckSuite getCheckSuite(String owner, String repo, long checkSuiteId) throws Exception {
        return getCheckSuite(owner, repo, checkSuiteId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single check suite using its id. GitHub Apps must have the checks:read permission on a private repository
     * or pull access to a public repository to get check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return check suite as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#get-a-check-suite">
     * Get a check suite</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}")
    public <T> T getCheckSuite(String owner, String repo, long checkSuiteId, ReturnFormat format) throws Exception {
        return returnCheckSuite(sendGetRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PATH + "/" +
                checkSuiteId), format);
    }

    /**
     * Method to create a check suite
     *
     * @param checkSuiteResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return check suite as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCheckSuite(String checkSuiteResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(checkSuiteResponse);
            case LIBRARY_OBJECT:
                return (T) new CheckSuite(new JSONObject(checkSuiteResponse));
            default:
                return (T) checkSuiteResponse;
        }
    }

    /**
     * Method to trigger GitHub to rerequest an existing check suite, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_suite"} webhook event with the action rerequested.
     * When a check suite is rerequested, its status is reset to queued and the conclusion is cleared
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param repository: the repository from rerequest the check suite
     * @param checkSuite: the check suite to rerequest
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#rerequest-a-check-suite">
     * Rerequest a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/rerequest")
    public boolean rerequestCheckSuite(Repository repository, CheckSuite checkSuite) {
        return rerequestCheckSuite(repository.getOwner().getLogin(), repository.getName(), checkSuite.getId());
    }

    /**
     * Method to trigger GitHub to rerequest an existing check suite, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_suite"} webhook event with the action rerequested.
     * When a check suite is rerequested, its status is reset to queued and the conclusion is cleared
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param repository:   the repository from rerequest the check suite
     * @param checkSuiteId: the unique identifier of the check suite
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#rerequest-a-check-suite">
     * Rerequest a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/rerequest")
    public boolean rerequestCheckSuite(Repository repository, long checkSuiteId) {
        return rerequestCheckSuite(repository.getOwner().getLogin(), repository.getName(), checkSuiteId);
    }

    /**
     * Method to trigger GitHub to rerequest an existing check suite, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_suite"} webhook event with the action rerequested.
     * When a check suite is rerequested, its status is reset to queued and the conclusion is cleared
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param checkSuite: the check suite to rerequest
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#rerequest-a-check-suite">
     * Rerequest a check suite</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/rerequest")
    public boolean rerequestCheckSuite(String owner, String repo, CheckSuite checkSuite) {
        return rerequestCheckSuite(owner, repo, checkSuite.getId());
    }

    /**
     * Method to trigger GitHub to rerequest an existing check suite, without pushing new code to a repository.
     * This endpoint will trigger the {@code "check_suite"} webhook event with the action rerequested.
     * When a check suite is rerequested, its status is reset to queued and the conclusion is cleared
     * To rerequest a check run, your GitHub App must have the {@code "checks:read"} permission on a private repository
     * or pull access to a public repository
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param checkSuiteId: the unique identifier of the check suite
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#rerequest-a-check-suite">
     * Rerequest a check suite</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/check-suites/{check_suite_id}/rerequest")
    public boolean rerequestCheckSuite(String owner, String repo, long checkSuiteId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + CHECK_SUITES_PATH + "/" + checkSuiteId +
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
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository: repository where create the check suite
     * @param ref:        ref parameter
     * @return check suites list as {@link CheckSuitesList} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public CheckSuitesList getGitReferenceCheckSuites(Repository repository, String ref) throws Exception {
        return getGitReferenceCheckSuites(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository: repository from fetch the list
     * @param ref:        ref parameter
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return check suites list as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public <T> T getGitReferenceCheckSuites(Repository repository, String ref, ReturnFormat format) throws Exception {
        return getGitReferenceCheckSuites(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return check suites list as {@link CheckSuitesList} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public CheckSuitesList getGitReferenceCheckSuites(String owner, String repo, String ref) throws Exception {
        return getGitReferenceCheckSuites(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param ref:    ref parameter
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return check suites list as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public <T> T getGitReferenceCheckSuites(String owner, String repo, String ref, ReturnFormat format) throws Exception {
        return returnCheckSuitesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_PATH + ref +
                CHECK_SUITES_PATH), format);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "app_id"} -> filters check suites by GitHub App id - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check suites list as {@link CheckSuitesList} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public CheckSuitesList getGitReferenceCheckSuites(Repository repository, String ref, Params queryParams) throws Exception {
        return getGitReferenceCheckSuites(repository.getOwner().getLogin(), repository.getName(), ref, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param repository:  repository from fetch the list
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "app_id"} -> filters check suites by GitHub App id - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check suites list as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public <T> T getGitReferenceCheckSuites(Repository repository, String ref, Params queryParams,
                                            ReturnFormat format) throws Exception {
        return getGitReferenceCheckSuites(repository.getOwner().getLogin(), repository.getName(), ref, queryParams, format);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "app_id"} -> filters check suites by GitHub App id - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return check suites list as {@link CheckSuitesList} custom object
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public CheckSuitesList getGitReferenceCheckSuites(String owner, String repo, String ref,
                                                      Params queryParams) throws Exception {
        return getGitReferenceCheckSuites(owner, repo, ref, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the check suites for a commit ref.
     * The ref can be an SHA, branch name, or a tag name. GitHub Apps must have the {@code "checks:read"} permission on
     * a private repository or pull access to a public repository to list check suites.
     * OAuth Apps and authenticated users must have the repo scope to get check suites in a private repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "app_id"} -> filters check suites by GitHub App id - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "check_name"} -> returns check runs with the specified name - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return check suites list as {@code "format"} defines
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
     * and a null value for {@code "head_branch"}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
     * List check suites for a Git reference</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/check-suites")
    public <T> T getGitReferenceCheckSuites(String owner, String repo, String ref, Params queryParams,
                                            ReturnFormat format) throws Exception {
        return returnCheckSuitesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_PATH + ref +
                CHECK_SUITES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a check suites list
     *
     * @param checkSuitesListResponse: obtained from GitHub's response
     * @param format:                  return type formatter -> {@link ReturnFormat}
     * @return check suites list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCheckSuitesList(String checkSuitesListResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(checkSuitesListResponse);
            case LIBRARY_OBJECT:
                return (T) new CheckSuitesList(new JSONObject(checkSuitesListResponse));
            default:
                return (T) checkSuitesListResponse;
        }
    }

}
