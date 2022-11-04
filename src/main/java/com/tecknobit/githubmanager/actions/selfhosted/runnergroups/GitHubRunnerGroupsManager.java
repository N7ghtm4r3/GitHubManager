package com.tecknobit.githubmanager.actions.selfhosted.runnergroups;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.Runner;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroup;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroupsList;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnersList;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.organization.OrganizationsList;
import com.tecknobit.githubmanager.records.repository.OrganizationRepositoriesList;
import com.tecknobit.githubmanager.records.repository.OrganizationRepositoriesList.CompletedRepository;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

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
     * {@code RUNNERS_PATH} constant for {@code "/runners"} path
     **/
    public static final String RUNNERS_PATH = "/runners";

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

    public RunnerGroup updateEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup,
                                                   Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), format);
    }

    public RunnerGroup updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId,
                                                   Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), format);
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

    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + ORGANIZATIONS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + ORGANIZATIONS_PATH), format);
    }

    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, long runnerGroupId) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedOrganizationsList(String enterprise, long runnerGroupId,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_PATH), format);
    }

    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup,
                                                            Params queryParams) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + ORGANIZATIONS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + ORGANIZATIONS_PATH + queryParams.createQueryString()), format);
    }

    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, long runnerGroupId,
                                                            Params queryParams) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedOrganizationsList(String enterprise, long runnerGroupId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_PATH + queryParams.createQueryString()), format);
    }

    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, OrganizationsList organizationsList) {
        return authorizeOrganizationsList(enterprise, runnerGroup.getId(), organizationsList);
    }

    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, OrganizationsList organizationsList) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Organization organization : organizationsList.getOrganizations())
            ids.add(organization.getId());
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                ORGANIZATIONS_PATH, "selected_organization_ids", ids.toArray(new Long[0]));
    }

    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, Collection<Long> organizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                        runnerGroup.getId() + ORGANIZATIONS_PATH, "selected_organization_ids",
                organizationsIds.toArray(new Long[0]));
    }

    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, Long[] organizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + ORGANIZATIONS_PATH, "selected_organization_ids", organizationsIds);
    }

    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, Collection<Long> organizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                ORGANIZATIONS_PATH, "selected_organization_ids", organizationsIds.toArray(new Long[0]));
    }

    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, Long[] organizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                ORGANIZATIONS_PATH, "selected_organization_ids", organizationsIds);
    }

    public boolean authorizeOrganization(String enterprise, RunnerGroup runnerGroup, Organization orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId.getId());
    }

    public boolean authorizeOrganization(String enterprise, RunnerGroup runnerGroup, long orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId);
    }

    public boolean authorizeOrganization(String enterprise, long runnerGroupId, Organization orgId) {
        return authorizeOrganization(enterprise, runnerGroupId, orgId.getId());
    }

    public boolean authorizeOrganization(String enterprise, long runnerGroupId, long orgId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    ORGANIZATIONS_PATH + "/" + orgId, null);
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

    public boolean removeAuthorizedOrganization(String enterprise, RunnerGroup runnerGroup, Organization orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId.getId());
    }

    public boolean removeAuthorizedOrganization(String enterprise, RunnerGroup runnerGroup, long orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId);
    }

    public boolean removeAuthorizedOrganization(String enterprise, long runnerGroupId, Organization orgId) {
        return authorizeOrganization(enterprise, runnerGroupId, orgId.getId());
    }

    public boolean removeAuthorizedOrganization(String enterprise, long runnerGroupId, long orgId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    ORGANIZATIONS_PATH + "/" + orgId);
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

    public RunnersList getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public RunnersList getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH), format);
    }

    public RunnersList getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup,
                                                     Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    public RunnersList getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId,
                                                     Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, RunnersList runners) {
        return setEnterpriseRunnersList(enterprise, runnerGroup.getId(), runners);
    }

    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, RunnersList runners) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Runner runner : runners.getRunners())
            ids.add(runner.getId());
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", ids.toArray(new Long[0]));
    }

    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, Collection<Long> runnersIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, Collection<Long> runnersIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, Long[] runnersIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean addEnterpriseRunnerToGroup(String enterprise, RunnerGroup runnerGroup, Runner runner) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroup.getId(), runner.getId());
    }

    public boolean addEnterpriseRunnerToGroup(String enterprise, long runnerGroupId, Runner runner) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroupId, runner.getId());
    }

    public boolean addEnterpriseRunnerToGroup(String enterprise, RunnerGroup runnerGroup, long runnerId) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroup.getId(), runnerId);
    }

    public boolean addEnterpriseRunnerToGroup(String enterprise, long runnerGroupId, long runnerId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId, null);
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

    public boolean removeEnterpriseRunnerFromGroup(String enterprise, RunnerGroup runnerGroup, Runner runner) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroup.getId(), runner.getId());
    }

    public boolean removeEnterpriseRunnerFromGroup(String enterprise, long runnerGroupId, Runner runner) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroupId, runner.getId());
    }

    public boolean removeEnterpriseRunnerFromGroup(String enterprise, RunnerGroup runnerGroup, long runnerId) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroup.getId(), runnerId);
    }

    public boolean removeEnterpriseRunnerFromGroup(String enterprise, long runnerGroupId, long runnerId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId);
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

    public RunnerGroupsList getOrganizationRunnerGroupsList(Organization org) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroupsList(Organization org, ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH),
                format);
    }

    public RunnerGroupsList getOrganizationRunnerGroupsList(String org) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroupsList(String org, ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH),
                format);
    }

    public RunnerGroupsList getOrganizationRunnerGroupsList(Organization org, Params queryParams) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroupsList(Organization org, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), format);
    }

    public RunnerGroupsList getOrganizationRunnerGroupsList(String org, Params queryParams) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroupsList(String org, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
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

    public RunnerGroup createOrganizationRunnerGroup(Organization org, String name) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRunnerGroup(Organization org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, format);
    }

    public RunnerGroup createOrganizationRunnerGroup(String org, String name) throws IOException {
        return createOrganizationRunnerGroup(org, name, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRunnerGroup(String org, String name, ReturnFormat format) throws IOException {
        Params bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH,
                bodyParams), format);
    }

    public RunnerGroup createOrganizationRunnerGroup(Organization org, String name,
                                                     Params runnerGroupDetails) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRunnerGroup(Organization org, String name, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, runnerGroupDetails, format);
    }

    public RunnerGroup createOrganizationRunnerGroup(String org, String name,
                                                     Params runnerGroupDetails) throws IOException {
        return createOrganizationRunnerGroup(org, name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRunnerGroup(String org, String name, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        runnerGroupDetails.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH,
                runnerGroupDetails), format);
    }

    public RunnerGroup getOrganizationRunnerGroup(Organization org, long runnerGroupId) throws IOException {
        return returnRunnerGroup(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroup(Organization org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), format);
    }

    public RunnerGroup getOrganizationRunnerGroup(String org, long runnerGroupId) throws IOException {
        return returnRunnerGroup(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnerGroup(String org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), format);
    }

    public RunnerGroup updateOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup,
                                                     Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), format);
    }

    public RunnerGroup updateOrganizationRunnerGroup(Organization org, long runnerGroupId,
                                                     Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateOrganizationRunnerGroup(Organization org, long runnerGroupId, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), format);
    }

    public RunnerGroup updateOrganizationRunnerGroup(String org, RunnerGroup runnerGroup,
                                                     Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateOrganizationRunnerGroup(String org, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroup.getId(), runnerGroupDetails), format);
    }

    public RunnerGroup updateOrganizationRunnerGroup(String org, long runnerGroupId,
                                                     Params runnerGroupDetails) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), LIBRARY_OBJECT);
    }

    public <T> T updateOrganizationRunnerGroup(String org, long runnerGroupId, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
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

    public boolean deleteOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(org.getLogin(), runnerGroup.getId());
    }

    public boolean deleteOrganizationRunnerGroup(Organization org, long runnerGroupId) {
        return deleteEnterpriseRunnerGroup(org.getLogin(), runnerGroupId);
    }

    public boolean deleteOrganizationRunnerGroup(String org, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(org, runnerGroup.getId());
    }

    public boolean deleteOrganizationRunnerGroup(String org, long runnerGroupId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId);
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

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org,
                                                                      RunnerGroup runnerGroup) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org,
                                                                      long runnerGroupId) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(Organization org, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, long runnerGroupId) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(String org, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                                                      Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup,
                                                                      Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroup.getId() + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org, long runnerGroupId,
                                                                      Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(Organization org, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, long runnerGroupId,
                                                                      Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getAuthorizedRepositoriesList(String org, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroup.getId(), repositoriesIds);
    }

    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org, runnerGroup.getId(), repositoriesIds);
    }

    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroupId, repositoriesIds);
    }

    public boolean authorizeRepositoriesList(String org, long runnerGroupId, OrganizationRepositoriesList repositoriesIds) {
        ArrayList<Long> ids = new ArrayList<>();
        for (CompletedRepository repository : repositoriesIds.getRepositories())
            ids.add(repository.getId());
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId + REPOSITORIES_PATH,
                "selected_repository_ids", ids.toArray(new Long[0]));
    }

    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup, Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup, Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId, Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean authorizeRepositoriesList(String org, long runnerGroupId, Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean authorizeRepositoriesList(String org, long runnerGroupId, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean removeAuthorizedRepository(Organization org, RunnerGroup runnerGroup, Repository repositoryId) {
        return authorizeOrganization(org.getLogin(), runnerGroup.getId(), repositoryId.getId());
    }

    public boolean removeAuthorizedRepository(String org, RunnerGroup runnerGroup, Repository repositoryId) {
        return authorizeOrganization(org, runnerGroup.getId(), repositoryId.getId());
    }

    public boolean removeAuthorizedRepository(Organization org, RunnerGroup runnerGroup, long repositoryId) {
        return authorizeOrganization(org.getLogin(), runnerGroup.getId(), repositoryId);
    }

    public boolean removeAuthorizedRepository(String org, RunnerGroup runnerGroup, long repositoryId) {
        return authorizeOrganization(org, runnerGroup.getId(), repositoryId);
    }

    public boolean removeAuthorizedRepository(Organization org, long runnerGroupId, Repository repositoryId) {
        return authorizeOrganization(org.getLogin(), runnerGroupId, repositoryId.getId());
    }

    public boolean removeAuthorizedRepository(String org, long runnerGroupId, Repository repositoryId) {
        return authorizeOrganization(org, runnerGroupId, repositoryId.getId());
    }

    public boolean removeAuthorizedRepository(Organization org, long runnerGroupId, long repositoryId) {
        return removeAuthorizedRepository(org.getLogin(), runnerGroupId, repositoryId);
    }

    public boolean removeAuthorizedRepository(String org, long runnerGroupId, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    REPOSITORIES_PATH + "/" + repositoryId);
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

    public RunnersList getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public RunnersList getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public RunnersList getOrganizationRunnersGroupList(Organization org, long runnerGroupId) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(Organization org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH), format);
    }

    public RunnersList getOrganizationRunnersGroupList(String org, long runnerGroupId) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(String org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH), format);
    }

    public RunnersList getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup,
                                                       Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    public RunnersList getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup,
                                                       Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroup.getId() + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    public RunnersList getOrganizationRunnersGroupList(Organization org, long runnerGroupId,
                                                       Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(Organization org, long runnerGroupId, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    public RunnersList getOrganizationRunnersGroupList(String org, long runnerGroupId,
                                                       Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRunnersGroupList(String org, long runnerGroupId, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    private <T> T returnRunnersList(String runnersGroupResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnersGroupResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnersList(new JSONObject(runnersGroupResponse));
            default:
                return (T) runnersGroupResponse;
        }
    }

    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, RunnersList runners) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroup.getId(), runners);
    }

    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, RunnersList runners) {
        return setOrganizationRunnersList(org, runnerGroup.getId(), runners);
    }

    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, RunnersList runners) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroupId, runners);
    }

    public boolean setOrganizationRunnersList(String org, long runnerGroupId, RunnersList runners) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Runner runner : runners.getRunners())
            ids.add(runner.getId());
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", ids.toArray(new Long[0]));
    }

    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, Collection<Long> runnersIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, Collection<Long> runnersIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroup.getId() +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, Collection<Long> runnersIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setOrganizationRunnersList(String org, long runnerGroupId, Collection<Long> runnersIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds.toArray(new Long[0]));
    }

    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, Long[] runnersIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean setOrganizationRunnersList(String org, long runnerGroupId, Long[] runnersIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds);
    }

    public boolean addOrganizationRunnerToGroup(Organization org, RunnerGroup runnerGroup, Runner runner) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroup.getId(), runner.getId());
    }

    public boolean addOrganizationRunnerToGroup(String org, RunnerGroup runnerGroup, Runner runner) {
        return addOrganizationRunnerToGroup(org, runnerGroup.getId(), runner.getId());
    }

    public boolean addOrganizationRunnerToGroup(Organization org, long runnerGroupId, Runner runner) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroupId, runner.getId());
    }

    public boolean addOrganizationRunnerToGroup(String org, long runnerGroupId, Runner runner) {
        return addOrganizationRunnerToGroup(org, runnerGroupId, runner.getId());
    }

    public boolean addOrganizationRunnerToGroup(Organization org, RunnerGroup runnerGroup, long runnerId) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroup.getId(), runnerId);
    }

    public boolean addOrganizationRunnerToGroup(String org, RunnerGroup runnerGroup, long runnerId) {
        return addOrganizationRunnerToGroup(org, runnerGroup.getId(), runnerId);
    }

    public boolean addOrganizationRunnerToGroup(Organization org, long runnerGroupId, long runnerId) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroupId, runnerId);
    }

    public boolean addOrganizationRunnerToGroup(String org, long runnerGroupId, long runnerId) {
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId, null);
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

    public boolean removeOrganizationRunnerFromGroup(Organization org, RunnerGroup runnerGroup, Runner runner) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroup.getId(), runner.getId());
    }

    public boolean removeOrganizationRunnerFromGroup(String org, RunnerGroup runnerGroup, Runner runner) {
        return removeOrganizationRunnerFromGroup(org, runnerGroup.getId(), runner.getId());
    }

    public boolean removeOrganizationRunnerFromGroup(Organization org, long runnerGroupId, Runner runner) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroupId, runner.getId());
    }

    public boolean removeOrganizationRunnerFromGroup(String org, long runnerGroupId, Runner runner) {
        return removeOrganizationRunnerFromGroup(org, runnerGroupId, runner.getId());
    }

    public boolean removeOrganizationRunnerFromGroup(Organization org, RunnerGroup runnerGroup, long runnerId) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroup.getId(), runnerId);
    }

    public boolean removeOrganizationRunnerFromGroup(String org, RunnerGroup runnerGroup, long runnerId) {
        return removeOrganizationRunnerFromGroup(org, runnerGroup.getId(), runnerId);
    }

    public boolean removeOrganizationRunnerFromGroup(Organization org, long runnerGroupId, long runnerId) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroupId, runnerId);
    }

    public boolean removeOrganizationRunnerFromGroup(String org, long runnerGroupId, long runnerId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId);
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
