package com.tecknobit.githubmanager.actions.permissions;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.permissions.records.DefaultWorkflowPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.EnterpriseAARW;
import com.tecknobit.githubmanager.actions.permissions.records.EnterpriseEnabledOrganizations;
import com.tecknobit.githubmanager.actions.permissions.records.EnterpriseEnabledOrganizations.Organization;
import com.tecknobit.githubmanager.actions.permissions.records.types.EnterprisePermissions;
import com.tecknobit.githubmanager.actions.permissions.records.types.OrganizationPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.EnabledItems;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.ACTIONS_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ENTERPRISES_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ORGS_PATH;
import static com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.AllowedActions;
import static com.tecknobit.githubmanager.records.GitHubResponse.INSTANTIATED_WITH_ERROR_KEY;

/**
 * The {@code GitHubPermissionsManager} class is useful to manage all GitHub's permissions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions">
 * About the Permissions API</a>
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

    public EnterprisePermissions getEnterprisePermissions(String enterprise) throws IOException {
        return getEnterprisePermissions(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getEnterprisePermissions(String enterprise, ReturnFormat format) throws IOException {
        String enterprisePermissionsResponse = sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(enterprisePermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new EnterprisePermissions(new JSONObject(enterprisePermissionsResponse));
            default:
                return (T) enterprisePermissionsResponse;
        }
    }

    public boolean setEnterprisePermissions(String enterprise, EnabledItems enabledOrganizations) {
        return setEnterprisePermissions(enterprise, enabledOrganizations, null);
    }

    public boolean setEnterprisePermissions(String enterprise, EnterprisePermissions enterprisePermissions) {
        return setEnterprisePermissions(enterprise, enterprisePermissions.getEnabledOrganizations(),
                enterprisePermissions.getAllowedActions());
    }

    public boolean setEnterprisePermissions(String enterprise, EnabledItems enabledOrganizations,
                                            AllowedActions allowedActions) {
        Params params = new Params();
        params.addParam("enabled_organizations", enabledOrganizations);
        if (allowedActions != null)
            params.addParam("allowed_actions", allowedActions);
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH, params);
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

    public EnterpriseEnabledOrganizations getEnabledEnterpriseOrganizations(String enterprise) throws IOException {
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
                return (T) new EnterpriseEnabledOrganizations(new JSONObject(enabledOrganizationsResponse));
            default:
                return (T) enabledOrganizationsResponse;
        }
    }

    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Collection<Long> selectedOrganizationsIds) {
        return enableSelectedEnterpriseOrganizations(enterprise, selectedOrganizationsIds.toArray(new Long[0]));
    }

    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Long[] selectedOrganizationsIds) {
        Params params = new Params();
        params.addParam("selected_organization_ids", Arrays.stream(selectedOrganizationsIds).toList());
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH, params);
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

    public boolean enableSelectedEnterpriseOrganization(String enterprise, Organization organizationToEnable) {
        return enableSelectedEnterpriseOrganization(enterprise, organizationToEnable.getId());
    }

    public boolean enableSelectedEnterpriseOrganization(String enterprise, long orgId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + "/org_id=" +
                    orgId, null);
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
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + "/org_id="
                    + orgId);
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

    public EnterpriseAARW getEnterpriseAllowedActionsAndReusableWorkflows(String enterprise) throws IOException {
        return getEnterpriseAllowedActionsAndReusableWorkflows(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseAllowedActionsAndReusableWorkflows(String enterprise, ReturnFormat format) throws IOException {
        String aarwResponse = sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(aarwResponse);
            case LIBRARY_OBJECT:
                return (T) new EnterpriseAARW(new JSONObject(aarwResponse));
            default:
                return (T) aarwResponse;
        }
    }

    public boolean setEnterpriseAllowedActionsAndReusableWorkflows(String enterprise) {
        return setEnterpriseAllowedActionsAndReusableWorkflows(enterprise, (Params) null);
    }

    public boolean setEnterpriseAllowedActionsAndReusableWorkflows(String enterprise, EnterpriseAARW aarw) {
        Params params = new Params();
        JSONObject aarwSource = new JSONObject(aarw);
        aarwSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : aarwSource.keySet())
            params.addParam(key, aarwSource.get(key));
        return setEnterpriseAllowedActionsAndReusableWorkflows(enterprise, params);
    }

    public boolean setEnterpriseAllowedActionsAndReusableWorkflows(String enterprise, Params aarw) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH,
                    aarw);
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

    public DefaultWorkflowPermissions getDefaultEnterpriseWorkflowPermissions(String enterprise) throws IOException {
        return getDefaultEnterpriseWorkflowPermissions(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getDefaultEnterpriseWorkflowPermissions(String enterprise, ReturnFormat format) throws IOException {
        String defaultWorkflowPermissionsResponse = sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(defaultWorkflowPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new DefaultWorkflowPermissions(new JSONObject(defaultWorkflowPermissionsResponse));
            default:
                return (T) defaultWorkflowPermissionsResponse;
        }
    }

    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise) {
        return setDefaultEnterpriseWorkflowPermissions(enterprise, (Params) null);
    }

    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, DefaultWorkflowPermissions
            defaultWorkflowPermissions) {
        Params params = new Params();
        JSONObject defWorkflowPermissionsSource = new JSONObject(defaultWorkflowPermissions);
        defWorkflowPermissionsSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : defWorkflowPermissionsSource.keySet())
            params.addParam(key, defWorkflowPermissionsSource.get(key));
        return setDefaultEnterpriseWorkflowPermissions(enterprise, params);
    }

    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, Params defaultEnterpriseWorkflowPermissions) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                    defaultEnterpriseWorkflowPermissions);
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

    public OrganizationPermissions getOrganizationPermissions(String org) throws IOException {
        return getOrganizationPermissions(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationPermissions(String org, ReturnFormat format) throws IOException {
        String orgPermissionsResponse = sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(orgPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationPermissions(new JSONObject(orgPermissionsResponse));
            default:
                return (T) orgPermissionsResponse;
        }
    }

    public boolean setOrganizationPermissions(String org, EnabledItems enabledRepositories) {
        return setEnterprisePermissions(org, enabledRepositories, null);
    }

    public boolean setOrganizationPermissions(String org, OrganizationPermissions organizationPermissions) {
        return setEnterprisePermissions(org, organizationPermissions.getEnabledRepositories(),
                organizationPermissions.getAllowedActions());
    }

    public boolean setOrganizationPermissions(String org, EnabledItems enabledRepositories, AllowedActions allowedActions) {
        Params params = new Params();
        params.addParam("enabled_repositories", enabledRepositories);
        if (allowedActions != null)
            params.addParam("allowed_actions", allowedActions);
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH, params);
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
