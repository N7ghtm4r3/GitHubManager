package com.tecknobit.githubmanager.actions.permissions;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.permissions.records.AARW;
import com.tecknobit.githubmanager.actions.permissions.records.DefaultWorkflowPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.OrganizationsList;
import com.tecknobit.githubmanager.actions.permissions.records.OrganizationsList.Organization;
import com.tecknobit.githubmanager.actions.permissions.records.RepositoriesList;
import com.tecknobit.githubmanager.actions.permissions.records.RepositoriesList.Repository;
import com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems;
import com.tecknobit.githubmanager.actions.permissions.records.types.EnterpriseActionsPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.types.OrganizationActionsPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.types.RepositoryActionsPermissions;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.ACTIONS_PATH;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.REPOS_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ENTERPRISES_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ORGS_PATH;
import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.AllowedActions;
import static com.tecknobit.githubmanager.records.GitHubResponse.INSTANTIATED_WITH_ERROR_KEY;

/**
 * The {@code GitHubPermissionsManager} class is useful to manage all GitHub's permissions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions">
 * About the ActionsPermissions API</a>
 * @see GitHubManager
 **/
public class GitHubPermissionsManager extends GitHubManager {

    /**
     * {@code ACTIONS_PERMISSIONS_PATH} constant for {@code "/actions/permissions"} path
     **/
    public static final String ACTIONS_PERMISSIONS_PATH = ACTIONS_PATH + "permissions";

    /**
     * {@code ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH} constant for {@code "/actions/permissions/organizations"} path
     **/
    public static final String ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH = ACTIONS_PATH + "permissions/organizations";

    /**
     * {@code ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH} constant for {@code "/actions/permissions/organizations/selected-actions"}
     * path
     **/
    public static final String ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH = ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH +
            "/selected-actions";

    /**
     * {@code ACTIONS_PERMISSIONS_WORKFLOW_PATH} constant for {@code "/actions/permissions/workflow"} path
     **/
    public static final String ACTIONS_PERMISSIONS_WORKFLOW_PATH = ACTIONS_PERMISSIONS_PATH + "/workflow";

    /**
     * {@code ACTIONS_PERMISSIONS_REPOSITORIES_PATH} constant for {@code "/actions/permissions/repositories"} path
     **/
    public static final String ACTIONS_PERMISSIONS_REPOSITORIES_PATH = ACTIONS_PERMISSIONS_PATH + "/repositories";

    /**
     * {@code ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH} constant for {@code "/actions/permissions/selected-actions"}
     * path
     **/
    public static final String ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH = ACTIONS_PERMISSIONS_PATH + "/selected-actions";

    /**
     * Constructor to init a {@link GitHubPermissionsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubPermissionsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubPermissionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubPermissionsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubPermissionsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubPermissionsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPermissionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubPermissionsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPermissionsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubPermissionsManager}'s manager without re-insert
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
    public GitHubPermissionsManager() {
        super();
    }

    public EnterpriseActionsPermissions getEnterpriseActionsPermissions(String enterprise) throws IOException {
        return getEnterpriseActionsPermissions(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseActionsPermissions(String enterprise, ReturnFormat format) throws IOException {
        String enterprisePermissionsResponse = sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(enterprisePermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new EnterpriseActionsPermissions(new JSONObject(enterprisePermissionsResponse));
            default:
                return (T) enterprisePermissionsResponse;
        }
    }

    public boolean setEnterpriseActionsPermissions(String enterprise, EnabledItems enabledOrganizations) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH, enabledOrganizations,
                null);
    }

    public boolean setEnterpriseActionsPermissions(String enterprise, EnterpriseActionsPermissions enterprisePermissions) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH,
                enterprisePermissions.getEnabledOrganizations(), enterprisePermissions.getAllowedActions());
    }

    public boolean setEnterpriseActionsPermissions(String enterprise, EnabledItems enabledOrganizations,
                                                   AllowedActions allowedActions) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH, enabledOrganizations,
                allowedActions);
    }

    public OrganizationsList getEnabledEnterpriseOrganizations(String enterprise) throws IOException {
        return returnEnterpriseEnabledOrganizations(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnabledEnterpriseOrganizations(String enterprise, ReturnFormat format) throws IOException {
        return returnEnterpriseEnabledOrganizations(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH), format);
    }

    private <T> T returnEnterpriseEnabledOrganizations(String enabledOrganizationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(enabledOrganizationsResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationsList(new JSONObject(enabledOrganizationsResponse));
            default:
                return (T) enabledOrganizationsResponse;
        }
    }

    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Collection<Long> selectedOrganizationsIds) {
        return enableSelectedEnterpriseOrganizations(enterprise, selectedOrganizationsIds.toArray(new Long[0]));
    }

    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Long[] selectedOrganizationsIds) {
        return enableSelectedItems(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH,
                "selected_organization_ids", selectedOrganizationsIds);
    }

    public boolean enableSelectedEnterpriseOrganization(String enterprise, Organization organizationToEnable) {
        return enableSelectedEnterpriseOrganization(enterprise, organizationToEnable.getId());
    }

    public boolean enableSelectedEnterpriseOrganization(String enterprise, long orgId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + orgId,
                    null);
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

    public boolean disableSelectedEnterpriseOrganization(String enterprise, Organization organizationToDisable) {
        return disableSelectedEnterpriseOrganization(enterprise, organizationToDisable.getId());
    }

    public boolean disableSelectedEnterpriseOrganization(String enterprise, long orgId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + orgId);
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

    public AARW getEnterpriseAARW(String enterprise) throws IOException {
        return returnAARW(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseAARW(String enterprise, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH), format);
    }

    public boolean setEnterpriseAARW(String enterprise, AARW aarw) {
        return setAARW(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH,
                aarw);
    }

    public boolean setEnterpriseAARW(String enterprise, Params aarw) {
        return setAARW(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH,
                aarw);
    }

    public DefaultWorkflowPermissions getDefaultEnterpriseWorkflowPermissions(String enterprise) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    public <T> T getDefaultEnterpriseWorkflowPermissions(String enterprise, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, DefaultWorkflowPermissions
            defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    public OrganizationActionsPermissions getOrganizationActionsPermissions(String org) throws IOException {
        return getOrganizationActionsPermissions(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationActionsPermissions(String org, ReturnFormat format) throws IOException {
        String orgPermissionsResponse = sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(orgPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationActionsPermissions(new JSONObject(orgPermissionsResponse));
            default:
                return (T) orgPermissionsResponse;
        }
    }

    public boolean setOrganizationActionsPermissions(String org, EnabledItems enabledRepositories) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH, enabledRepositories, null);
    }

    public boolean setOrganizationActionsPermissions(String org, OrganizationActionsPermissions organizationPermissions) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH,
                organizationPermissions.getEnabledRepositories(), organizationPermissions.getAllowedActions());
    }

    public boolean setOrganizationActionsPermissions(String org, EnabledItems enabledRepositories,
                                                     AllowedActions allowedActions) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH, enabledRepositories, allowedActions);
    }

    public RepositoriesList getEnabledOrganizationRepositories(String org) throws IOException {
        return returnEnabledOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnabledOrganizationRepositories(String org, ReturnFormat format) throws IOException {
        return returnEnabledOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), format);
    }

    public RepositoriesList getEnabledOrganizationRepositories(String org, Params queryParams) throws IOException {
        return returnEnabledOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnabledOrganizationRepositories(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnEnabledOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    private <T> T returnEnabledOrganizationRepositories(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoriesList(new JSONObject(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

    public boolean enableSelectedOrganizationRepositories(String org, Collection<Long> selectedRepositoriesIds) {
        return enableSelectedOrganizationRepositories(org, selectedRepositoriesIds.toArray(new Long[0]));
    }

    public boolean enableSelectedOrganizationRepositories(String org, Long[] selectedRepositoriesIds) {
        return enableSelectedItems(ORGS_PATH + org + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", selectedRepositoriesIds);
    }

    private boolean enableSelectedItems(String endpoint, String key, Long[] ids) {
        Params params = new Params();
        params.addParam(key, Arrays.stream(ids).toList());
        try {
            sendPutRequest(endpoint, params);
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

    public boolean enableSelectedOrganizationRepository(String org, Repository repositoryToEnable) {
        return enableSelectedOrganizationRepository(org, repositoryToEnable.getId());
    }

    public boolean enableSelectedOrganizationRepository(String org, long repositoryId) {
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + repositoryId, null);
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

    public boolean disableSelectedOrganizationRepositories(String org, Repository repositoryToDisable) {
        return disableSelectedOrganizationRepositories(org, repositoryToDisable.getId());
    }

    public boolean disableSelectedOrganizationRepositories(String org, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_REPOSITORIES_PATH + repositoryId);
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

    public AARW getOrganizationAARW(String org) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationAARW(String org, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), format);
    }

    private <T> T returnAARW(String aarwResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(aarwResponse);
            case LIBRARY_OBJECT:
                return (T) new AARW(new JSONObject(aarwResponse));
            default:
                return (T) aarwResponse;
        }
    }

    public boolean setOrganizationAARW(String org, AARW aarw) {
        return setAARW(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    public boolean setOrganizationAARW(String org, Params aarw) {
        return setAARW(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    private boolean setAARW(String endpoint, AARW aarw) {
        Params params = new Params();
        JSONObject aarwSource = new JSONObject(aarw);
        aarwSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : aarwSource.keySet())
            params.addParam(key, aarwSource.get(key));
        return setAARW(endpoint, params);
    }

    private boolean setAARW(String endpoint, Params aarw) {
        try {
            sendPutRequest(endpoint, aarw);
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

    public DefaultWorkflowPermissions getDefaultOrganizationWorkflowPermissions(String org) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    public <T> T getDefaultOrganizationWorkflowPermissions(String org, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    private <T> T returnDefaultWorkflowPermissions(String defWorkflowPermissionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(defWorkflowPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new DefaultWorkflowPermissions(new JSONObject(defWorkflowPermissionsResponse));
            default:
                return (T) defWorkflowPermissionsResponse;
        }
    }

    public boolean setDefaultOrganizationWorkflowPermissions(String org, DefaultWorkflowPermissions defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    public boolean setDefaultOrganizationWorkflowPermissions(String org, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    private boolean setDefaultWorkflowPermissions(String endpoint, DefaultWorkflowPermissions defaultWorkflowPermissions) {
        Params params = new Params();
        JSONObject defWorkflowPermissionsSource = new JSONObject(defaultWorkflowPermissions);
        defWorkflowPermissionsSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : defWorkflowPermissionsSource.keySet())
            params.addParam(key, defWorkflowPermissionsSource.get(key));
        return setDefaultWorkflowPermissions(endpoint, params);
    }

    private boolean setDefaultWorkflowPermissions(String endpoint, Params defaultWorkflowPermissions) {
        try {
            sendPutRequest(endpoint, defaultWorkflowPermissions);
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

    public RepositoryActionsPermissions getRepositoryActionsPermissions(String owner, String repo) throws IOException {
        return getRepositoryActionsPermissions(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryActionsPermissions(String owner, String repo, ReturnFormat format) throws IOException {
        String orgPermissionsResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(orgPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryActionsPermissions(new JSONObject(orgPermissionsResponse));
            default:
                return (T) orgPermissionsResponse;
        }
    }

    public boolean setRepositoryActionsPermissions(String owner, String repo, boolean enable) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH, enable,
                null);
    }

    public boolean setRepositoryActionsPermissions(String owner, String repo,
                                                   RepositoryActionsPermissions repositoryPermissions) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH,
                repositoryPermissions.isEnabled(), repositoryPermissions.getAllowedActions());
    }

    public boolean setRepositoryActionsPermissions(String owner, String repo, boolean enable,
                                                   AllowedActions allowedActions) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH, enable,
                allowedActions);
    }

    private <T> boolean setActionsPermissions(String endpoint, T enabledItem, AllowedActions allowedActions) {
        String key = "enabled_organizations";
        if (endpoint.contains(ORGS_PATH))
            key = "enabled_repositories";
        else if (endpoint.contains(REPOS_PATH))
            key = "enabled";
        Params params = new Params();
        params.addParam(key, enabledItem);
        if (allowedActions != null)
            params.addParam("allowed_actions", allowedActions);
        try {
            sendPutRequest(endpoint, params);
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

    public String getLevelAccessOutsideRepository(String owner, String repo) throws IOException {
        return getLevelAccessOutsideRepository(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getLevelAccessOutsideRepository(String owner, String repo, ReturnFormat format) throws IOException {
        JSONObject levelAccessResponse = new JSONObject(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_PERMISSIONS_PATH + "/access"));
        switch (format) {
            case JSON:
                return (T) levelAccessResponse;
            case LIBRARY_OBJECT:
                return (T) levelAccessResponse.getString("access_level");
            default:
                return (T) levelAccessResponse.toString();
        }
    }

}
