package com.tecknobit.githubmanager.actions.selfhosted.runnergroups;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroup;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroupsList;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.ACTIONS_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ENTERPRISES_PATH;

/**
 * The {@code GitHubRunnerGroupsManager} class is useful to manage all GitHub's runner groups endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups">
 * About the Self-hosted runner groups API</a>
 * @see GitHubManager
 **/
// TODO: 03/11/2022 TEST JSON PAYLOAD WHEN FIXED
public class GitHubRunnerGroupsManager extends GitHubManager {

    /**
     * {@code ACTIONS_RUNNER_GROUPS_PATH} constant for {@code "/actions/runner-groups"} path
     **/
    public static final String ACTIONS_RUNNER_GROUPS_PATH = ACTIONS_PATH + "runner-groups";

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRunnerGroupsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRunnerGroupsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRunnerGroupsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRunnerGroupsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubRunnerGroupsManager}'s manager without re-insert
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
    public GitHubRunnerGroupsManager() {
        super();
    }

    public RunnerGroupsList getEnterpriseRunnerGroupsList(String enterprise) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnerGroupsList(String enterprise, ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH),
                format);
    }

    public RunnerGroupsList getEnterpriseRunnerGroupsList(String enterprise, Params queryParams) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnerGroupsList(String enterprise, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), format);
    }

    private <T> T returnRunnerGroupsList(String runnerGroupsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerGroupsResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnerGroupsList(new JSONObject(runnerGroupsResponse));
            default:
                return (T) runnerGroupsResponse;
        }
    }

    public RunnerGroup createEnterpriseRunnerGroup(String enterprise, String name) throws IOException {
        return createEnterpriseRunnerGroup(enterprise, name, LIBRARY_OBJECT);
    }

    public <T> T createEnterpriseRunnerGroup(String enterprise, String name, ReturnFormat format) throws IOException {
        Params bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH,
                bodyParams), format);
    }

    public RunnerGroup createEnterpriseRunnerGroup(String enterprise, String name,
                                                   Params runnerGroupDetails) throws IOException {
        return createEnterpriseRunnerGroup(enterprise, name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    public <T> T createEnterpriseRunnerGroup(String enterprise, String name, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        runnerGroupDetails.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH,
                runnerGroupDetails), format);
    }

    public RunnerGroup getEnterpriseRunnerGroup(String enterprise, long runnerGroupId) throws IOException {
        return returnRunnerGroup(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnerGroup(String enterprise, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), format);
    }

    public RunnerGroup updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId,
                                                   Params runnerGroupDetails) throws IOException {
        return updateEnterpriseRunnerGroup(enterprise, runnerGroupId, runnerGroupDetails, LIBRARY_OBJECT);
    }

    public <T> T updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), format);
    }

    private <T> T returnRunnerGroup(String runnerGroupResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerGroupResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnerGroup(new JSONObject(runnerGroupResponse));
            default:
                return (T) runnerGroupResponse;
        }
    }

    public boolean deleteEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(enterprise, runnerGroup.getId());
    }

    public boolean deleteEnterpriseRunnerGroup(String enterprise, long runnerGroupId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId);
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
