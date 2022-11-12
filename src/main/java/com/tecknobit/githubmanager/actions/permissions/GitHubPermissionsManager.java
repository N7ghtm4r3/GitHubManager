package com.tecknobit.githubmanager.actions.permissions;

import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.permissions.records.AARW;
import com.tecknobit.githubmanager.actions.permissions.records.DefaultWorkflowPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.actions.ActionsPermissions.EnabledItems;
import com.tecknobit.githubmanager.actions.permissions.records.actions.EnterpriseActionsPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.actions.OrganizationActionsPermissions;
import com.tecknobit.githubmanager.actions.permissions.records.actions.RepositoryActionsPermissions;
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
import static com.tecknobit.githubmanager.actions.permissions.records.actions.ActionsPermissions.AllowedActions;
import static com.tecknobit.githubmanager.records.basics.GitHubResponse.INSTANTIATED_WITH_ERROR_KEY;

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
     * {@code ACTIONS_PERMISSIONS_ACCESS_PATH} constant for {@code "/actions/permissions/access"}
     * path
     **/
    public static final String ACTIONS_PERMISSIONS_ACCESS_PATH = ACTIONS_PERMISSIONS_PATH + "/access";

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

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for organizations and allowed actions and reusable
     * workflows in an enterprise.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enterprise actions permissions as {@link EnterpriseActionsPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
     * Get GitHub Actions permissions for an enterprise</a>
     **/
    public EnterpriseActionsPermissions getEnterpriseActionsPermissions(String enterprise) throws IOException {
        return getEnterpriseActionsPermissions(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for organizations and allowed actions and reusable
     * workflows in an enterprise.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise actions permissions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
     * Get GitHub Actions permissions for an enterprise</a>
     **/
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

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for organizations and allowed actions and reusable
     * workflows in an enterprise.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise:           the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param enabledOrganizations: the policy that controls the organizations in the enterprise that are allowed to run {@code "GitHub Actions"}
     *                              Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-enterprise">
     * Set GitHub Actions permissions for an enterprise</a>
     **/
    public boolean setEnterpriseActionsPermissions(String enterprise, EnabledItems enabledOrganizations) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH, enabledOrganizations,
                null);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for organizations and allowed actions and reusable
     * workflows in an enterprise.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise:            the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param enterprisePermissions: enterprise actions permissions to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-enterprise">
     * Set GitHub Actions permissions for an enterprise</a>
     **/
    public boolean setEnterpriseActionsPermissions(String enterprise, EnterpriseActionsPermissions enterprisePermissions) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH,
                enterprisePermissions.getEnabledOrganizations(), enterprisePermissions.getAllowedActions());
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for organizations and allowed actions and reusable
     * workflows in an enterprise.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise:           the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param enabledOrganizations: the policy that controls the organizations in the enterprise that are allowed to run {@code "GitHub Actions"}
     *                              Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * @param allowedActions:       the permissions policy that controls the actions and reusable workflows that are allowed to run.
     *                              Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-enterprise">
     * Set GitHub Actions permissions for an enterprise</a>
     **/
    @WrappedRequest
    public boolean setEnterpriseActionsPermissions(String enterprise, EnabledItems enabledOrganizations,
                                                   AllowedActions allowedActions) {
        return setActionsPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_PATH,
                enabledOrganizations, allowedActions);
    }

    /**
     * Method to get a list of the organizations that are selected to have {@code "GitHub Actions"} enabled in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enabled organizations list for an enterprise as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * List selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public OrganizationsList getEnabledEnterpriseOrganizations(String enterprise) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the organizations that are selected to have {@code "GitHub Actions"} enabled in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enabled organizations list for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * List selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public <T> T getEnabledEnterpriseOrganizations(String enterprise, ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH), format);
    }

    /**
     * Method to get a list of the organizations that are selected to have {@code "GitHub Actions"} enabled in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return enabled organizations list for an enterprise as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * List selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public OrganizationsList getEnabledEnterpriseOrganizations(String enterprise, Params queryParams) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + queryParams), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the organizations that are selected to have {@code "GitHub Actions"} enabled in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
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
     * @return enabled organizations list for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * List selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public <T> T getEnabledEnterpriseOrganizations(String enterprise, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:               the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param selectedOrganizationsIds: list of organization IDs to enable for {@code "GitHub Actions"} in {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * Set selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Collection<Long> selectedOrganizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH,
                "selected_organization_ids", selectedOrganizationsIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:            the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param selectedOrganizations: list of organization to enable for {@code "GitHub Actions"} in {@link OrganizationsList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * Set selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    @WrappedRequest
    public boolean enableSelectedEnterpriseOrganizations(String enterprise, OrganizationsList selectedOrganizations) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Organization organization : selectedOrganizations.getOrganizations())
            ids.add(organization.getId());
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH,
                "selected_organization_ids", ids.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:               the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param selectedOrganizationsIds: list of organization IDs to enable for {@code "GitHub Actions"} in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-organizations-enabled-for-github-actions-in-an-enterprise">
     * Set selected organizations enabled for GitHub Actions in an enterprise</a>
     **/
    public boolean enableSelectedEnterpriseOrganizations(String enterprise, Long[] selectedOrganizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_PATH,
                "selected_organization_ids", selectedOrganizationsIds);
    }

    /**
     * Method to adds an organization to the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:           the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param organizationToEnable: organization to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-organization-for-github-actions-in-an-enterprise">
     * Enable a selected organization for GitHub Actions in an enterprise</a>
     **/
    @WrappedRequest
    public boolean enableSelectedEnterpriseOrganization(String enterprise, Organization organizationToEnable) {
        return enableSelectedEnterpriseOrganization(enterprise, organizationToEnable.getId());
    }

    /**
     * Method to adds an organization to the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param orgId:      the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-organization-for-github-actions-in-an-enterprise">
     * Enable a selected organization for GitHub Actions in an enterprise</a>
     **/
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

    /**
     * Method to remove an organization to the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:            the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param organizationToDisable: organization to disable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-organization-for-github-actions-in-an-enterprise">
     * Disable a selected organization for GitHub Actions in an enterprise</a>
     **/
    @WrappedRequest
    public boolean disableSelectedEnterpriseOrganization(String enterprise, Organization organizationToDisable) {
        return disableSelectedEnterpriseOrganization(enterprise, organizationToDisable.getId());
    }

    /**
     * Method to remove an organization to the list of selected organizations that are enabled for {@code "GitHub Actions"} in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "enabled_organizations"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param orgId:      the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-organization-for-github-actions-in-an-enterprise">
     * Disable a selected organization for GitHub Actions in an enterprise</a>
     **/
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

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return allowed actions and reusable workflows for an enterprise as {@link AARW} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-enterprise">
     * Get allowed actions and reusable workflows for an enterprise</a>
     **/
    public AARW getEnterpriseAARW(String enterprise) throws IOException {
        return returnAARW(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an enterprise.
     * To use this endpoint, the enterprise permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return allowed actions and reusable workflows for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-enterprise">
     * Get allowed actions and reusable workflows for an enterprise</a>
     **/
    public <T> T getEnterpriseAARW(String enterprise, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH), format);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an enterprise.
     * To use this endpoint, the enterprise permission policy for allowed_actions must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param aarw:       allowed actions and reusable workflows for an organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-enterprise">
     * Set allowed actions and reusable workflows for an enterprise</a>
     **/
    @WrappedRequest
    public boolean setEnterpriseAARW(String enterprise, AARW aarw) {
        return setAARW(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH,
                aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an enterprise.
     * To use this endpoint, the enterprise permission policy for allowed_actions must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param aarw:       allowed actions and reusable workflows params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                           this includes the actions in the actions organization - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                           creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                           verified creators - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                           and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                           For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                       </li>
     *                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-enterprise">
     * Set allowed actions and reusable workflows for an enterprise</a>
     **/
    public boolean setEnterpriseAARW(String enterprise, Params aarw) {
        return setAARW(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_ORGANIZATIONS_SELECTED_ACTIONS_PATH,
                aarw);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an enterprise,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Enforcing a policy for workflow permissions in your enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "enterprise_administration:write"} permission to use this endpoint
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return default workflow permissions for an enterprise as {@link DefaultWorkflowPermissions} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-enterprise">
     * Get default workflow permissions for an enterprise</a>
     **/
    public DefaultWorkflowPermissions getDefaultEnterpriseWorkflowPermissions(String enterprise) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an enterprise,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Enforcing a policy for workflow permissions in your enterprise."
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "enterprise_administration:write"} permission to use this endpoint
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-enterprise">
     * Get default workflow permissions for an enterprise</a>
     **/
    public <T> T getDefaultEnterpriseWorkflowPermissions(String enterprise, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ENTERPRISES_PATH + enterprise +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    /**
     * Method to set the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an enterprise,
     * and sets whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Enforcing a policy for workflow permissions in your enterprise."
     * You must authenticate using an access token with the admin:enterprise scope to use this endpoint.
     * {@code "GitHub Apps"} must have the {@code "enterprise_administration:write"} permission to use this endpoint
     *
     * @param enterprise:                 the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param defaultWorkflowPermissions: the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-enterprise">
     * Set default workflow permissions for an enterprise</a>
     **/
    @WrappedRequest
    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, DefaultWorkflowPermissions
            defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to set the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an enterprise,
     * and sets whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Enforcing a policy for workflow permissions in your enterprise."
     * You must authenticate using an access token with the admin:enterprise scope to use this endpoint.
     * {@code "GitHub Apps"} must have the {@code "enterprise_administration:write"} permission to use this endpoint
     *
     * @param enterprise:                 the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param defaultWorkflowPermissions: default workflow permissions params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "default_workflow_permissions"} -> the default workflow permissions granted to
     *                                                 the {@code "GITHUB_TOKEN"} when running workflows - [string]
     *                                       </li>
     *                                       <li>
     *                                           {@code "can_approve_pull_request_reviews"} -> whether {@code "GitHub Actions"} can approve
     *                                                 pull requests. Enabling this can be a security risk - [boolean]
     *                                       </li>
     *                                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-enterprise">
     * Set default workflow permissions for an enterprise</a>
     **/
    public boolean setDefaultEnterpriseWorkflowPermissions(String enterprise, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ENTERPRISES_PATH + enterprise + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization from fetch actions permissions
     * @return organization actions permissions as {@link OrganizationActionsPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     * Get GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public OrganizationActionsPermissions getOrganizationActionsPermissions(Organization org) throws IOException {
        return getOrganizationActionsPermissions(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization from fetch actions permissions
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization actions permissions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     * Get GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationActionsPermissions(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationActionsPermissions(org.getLogin(), format);
    }

    /**
     * Method to the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization actions permissions as {@link OrganizationActionsPermissions} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     * Get GitHub Actions permissions for an organization</a>
     **/
    public OrganizationActionsPermissions getOrganizationActionsPermissions(String org) throws IOException {
        return getOrganizationActionsPermissions(org, LIBRARY_OBJECT);
    }

    /**
     * Method to the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization actions permissions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     * Get GitHub Actions permissions for an organization</a>
     **/
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

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization to set actions permissions
     * @param enabledRepositories: the policy that controls the repositories in the organization that are allowed to run {@code "GitHub Actions"} ->
     *                             Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationActionsPermissions(Organization org, EnabledItems enabledRepositories) {
        return setActionsPermissions(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_PATH, enabledRepositories,
                null);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization name. The name is not case-sensitive
     * @param enabledRepositories: the policy that controls the repositories in the organization that are allowed to run {@code "GitHub Actions"} ->
     *                             Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    public boolean setOrganizationActionsPermissions(String org, EnabledItems enabledRepositories) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH, enabledRepositories, null);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization to set actions permissions
     * @param organizationPermissions: organization actions permissions to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationActionsPermissions(Organization org, OrganizationActionsPermissions organizationPermissions) {
        return setActionsPermissions(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_PATH,
                organizationPermissions.getEnabledRepositories(), organizationPermissions.getAllowedActions());
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization name. The name is not case-sensitive
     * @param organizationPermissions: organization actions permissions to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationActionsPermissions(String org, OrganizationActionsPermissions organizationPermissions) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH,
                organizationPermissions.getEnabledRepositories(), organizationPermissions.getAllowedActions());
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization to set actions permissions
     * @param enabledRepositories: the policy that controls the repositories in the organization that are allowed to run {@code "GitHub Actions"} ->
     *                             Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * @param allowedActions:      the permissions policy that controls the actions and reusable workflows that are allowed to run ->
     *                             Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationActionsPermissions(Organization org, EnabledItems enabledRepositories,
                                                     AllowedActions allowedActions) {
        return setActionsPermissions(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_PATH, enabledRepositories,
                allowedActions);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for repositories and allowed actions and reusable workflows in an organization.
     * If the organization belongs to an enterprise that has set restrictive permissions at the enterprise level, such as
     * {@code "allowed_actions"} to selected actions and reusable workflows, then you cannot override them for the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization name. The name is not case-sensitive
     * @param enabledRepositories: the policy that controls the repositories in the organization that are allowed to run {@code "GitHub Actions"} ->
     *                             Can be one of: {@code "all"}, {@code "none"}, {@code "selected"}
     * @param allowedActions:      the permissions policy that controls the actions and reusable workflows that are allowed to run ->
     *                             Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
     * Set GitHub Actions permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationActionsPermissions(String org, EnabledItems enabledRepositories,
                                                     AllowedActions allowedActions) {
        return setActionsPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_PATH, enabledRepositories, allowedActions);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization from get repositories
     * @return enabled repositories list as {@link OrganizationRepositoriesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public OrganizationRepositoriesList getEnabledOrganizationRepositories(Organization org) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization from get repositories
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return enabled repositories list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public <T> T getEnabledOrganizationRepositories(Organization org, ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), format);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return enabled repositories list as {@link OrganizationRepositoriesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public OrganizationRepositoriesList getEnabledOrganizationRepositories(String org) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return enabled repositories list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public <T> T getEnabledOrganizationRepositories(String org, ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH), format);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:         the organization from get repositories
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return enabled repositories list as {@link OrganizationRepositoriesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public OrganizationRepositoriesList getEnabledOrganizationRepositories(Organization org,
                                                                           Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:         the organization from get repositories
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
     * @return enabled repositories list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public <T> T getEnabledOrganizationRepositories(Organization org, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return enabled repositories list as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public OrganizationRepositoriesList getEnabledOrganizationRepositories(String org, Params queryParams) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:         the organization name. The name is not case-sensitive
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
     * @return enabled repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
     * List selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public <T> T getEnabledOrganizationRepositories(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization to set repositories
     * @param selectedRepositoriesIds: list of repository IDs to enable for {@code "GitHub Actions"} in {@link Collection}
     *                                 of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepositories(Organization org, Collection<Long> selectedRepositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", selectedRepositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization name. The name is not case-sensitive
     * @param selectedRepositoriesIds: list of repository IDs to enable for {@code "GitHub Actions"} in {@link Collection}
     *                                 of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public boolean enableSelectedOrganizationRepositories(String org, Collection<Long> selectedRepositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", selectedRepositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                  the organization to set repositories
     * @param selectedRepositories: list of repository to enable for {@code "GitHub Actions"} in {@link OrganizationRepositoriesList}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepositories(Organization org, OrganizationRepositoriesList selectedRepositories) {
        return enableSelectedOrganizationRepositories(org.getLogin(), selectedRepositories);
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                  the organization name. The name is not case-sensitive
     * @param selectedRepositories: list of repository to enable for {@code "GitHub Actions"} in {@link OrganizationRepositoriesList}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepositories(String org, OrganizationRepositoriesList selectedRepositories) {
        ArrayList<Long> ids = new ArrayList<>();
        for (CompletedRepository completedRepository : selectedRepositories.getRepositories())
            ids.add(completedRepository.getId());
        return setItems(ORGS_PATH + org + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", ids.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization to set repositories
     * @param selectedRepositoriesIds: list of repository IDs to enable for {@code "GitHub Actions"} in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepositories(Organization org, Long[] selectedRepositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", selectedRepositoriesIds);
    }

    /**
     * Method to replace the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for enabled_repositories must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the admin:org scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                     the organization name. The name is not case-sensitive
     * @param selectedRepositoriesIds: list of repository IDs to enable for {@code "GitHub Actions"} in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-selected-repositories-enabled-for-github-actions-in-an-organization">
     * Set selected repositories enabled for GitHub Actions in an organization</a>
     **/
    public boolean enableSelectedOrganizationRepositories(String org, Long[] selectedRepositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_PERMISSIONS_REPOSITORIES_PATH,
                "selected_repository_ids", selectedRepositoriesIds);
    }

    /**
     * Method to add a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                the organization to set repository
     * @param repositoryToEnable: repository to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     * Enable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepository(Organization org, CompletedRepository repositoryToEnable) {
        return enableSelectedOrganizationRepository(org.getLogin(), repositoryToEnable.getId());
    }

    /**
     * Method to add a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param repositoryToEnable: repository to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     * Enable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepository(String org, CompletedRepository repositoryToEnable) {
        return enableSelectedOrganizationRepository(org, repositoryToEnable.getId());
    }

    /**
     * Method to add a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:          the organization to set repository
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     * Enable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean enableSelectedOrganizationRepository(Organization org, long repositoryId) {
        return enableSelectedOrganizationRepository(org.getLogin(), repositoryId);
    }

    /**
     * Method to add a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     * Enable a selected repository for GitHub Actions in an organization</a>
     **/
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

    /**
     * Method to remove a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization from disable repositories
     * @param repositoryToDisable: repository to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-repository-for-github-actions-in-an-organization">
     * Disable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean disableSelectedOrganizationRepositories(Organization org, CompletedRepository repositoryToDisable) {
        return disableSelectedOrganizationRepositories(org.getLogin(), repositoryToDisable.getId());
    }

    /**
     * Method to remove a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                 the organization name. The name is not case-sensitive
     * @param repositoryToDisable: repository to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-repository-for-github-actions-in-an-organization">
     * Disable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean disableSelectedOrganizationRepositories(String org, CompletedRepository repositoryToDisable) {
        return disableSelectedOrganizationRepositories(org, repositoryToDisable.getId());
    }

    /**
     * Method to remove a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:          the organization from disable repositories
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-repository-for-github-actions-in-an-organization">
     * Disable a selected repository for GitHub Actions in an organization</a>
     **/
    @WrappedRequest
    public boolean disableSelectedOrganizationRepositories(Organization org, long repositoryId) {
        return disableSelectedOrganizationRepositories(org.getLogin(), repositoryId);
    }

    /**
     * Method to remove a repository to the list of selected repositories that are enabled for {@code "GitHub Actions"} in an organization.
     * To use this endpoint, the organization permission policy for {@code "enabled_repositories"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-repository-for-github-actions-in-an-organization">
     * Disable a selected repository for GitHub Actions in an organization</a>
     **/
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

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an organization.
     * To use this endpoint, the organization permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: organization from get allowed actions and reusable workflows
     * @return allowed actions and reusable workflows as {@link AARW} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-organization">
     * Get allowed actions and reusable workflows for an organization</a>
     **/
    @WrappedRequest
    public AARW getOrganizationAARW(Organization org) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an organization.
     * To use this endpoint, the organization permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    organization from get allowed actions and reusable workflows
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return actions and reusable workflows object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-organization">
     * Get allowed actions and reusable workflows for an organization</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationAARW(Organization org, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH),
                format);
    }

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an organization.
     * To use this endpoint, the organization permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return allowed actions and reusable workflows as {@link AARW} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-organization">
     * Get allowed actions and reusable workflows for an organization</a>
     **/
    public AARW getOrganizationAARW(String org) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the selected actions and reusable workflows that are allowed in an organization.
     * To use this endpoint, the organization permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for an organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return actions and reusable workflows object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-organization">
     * Get allowed actions and reusable workflows for an organization</a>
     **/
    public <T> T getOrganizationAARW(String org, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), format);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:  the organization to set allowed actions and reusable workflows
     * @param aarw: allowed actions and reusable workflows for an organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-organization">
     * Set allowed actions and reusable workflows for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationAARW(Organization org, AARW aarw) {
        return setAARW(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param aarw: allowed actions and reusable workflows for an organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-organization">
     * Set allowed actions and reusable workflows for an organization</a>
     **/
    @WrappedRequest
    public boolean setOrganizationAARW(String org, AARW aarw) {
        return setAARW(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:  the organization to set allowed actions and reusable workflows
     * @param aarw: allowed actions and reusable workflows params not mandatory, keys accepted are:
     *              <ul>
     *                 <li>
     *                     {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                     this includes the actions in the actions organization - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                     creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                     verified creators - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                     and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                     For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                 </li>
     *              </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-organization">
     * Set allowed actions and reusable workflows for an organization</a>
     **/
    public boolean setOrganizationAARW(Organization org, Params aarw) {
        return setAARW(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param aarw: allowed actions and reusable workflows params not mandatory, keys accepted are:
     *              <ul>
     *                 <li>
     *                     {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                     this includes the actions in the actions organization - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                     creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                     verified creators - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                     and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                     For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                 </li>
     *              </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-organization">
     * Set allowed actions and reusable workflows for an organization</a>
     **/
    public boolean setOrganizationAARW(String org, Params aarw) {
        return setAARW(ORGS_PATH + org + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an organization,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information, see
     * "Setting the permissions of the {@code "GITHUB_TOKEN"} for your organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the organization from get default workflows permissions
     * @return default workflow permissions for an organization as {@link DefaultWorkflowPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
     * Get default workflow permissions for an organization</a>
     **/
    @WrappedRequest
    public DefaultWorkflowPermissions getDefaultOrganizationWorkflowPermissions(Organization org) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an organization,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information, see
     * "Setting the permissions of the {@code "GITHUB_TOKEN"} for your organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the organization from get default workflows permissions
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions for an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
     * Get default workflow permissions for an organization</a>
     **/
    @WrappedRequest
    public <T> T getDefaultOrganizationWorkflowPermissions(Organization org, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an organization,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information, see
     * "Setting the permissions of the {@code "GITHUB_TOKEN"} for your organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return default workflow permissions for an organization as {@link DefaultWorkflowPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
     * Get default workflow permissions for an organization</a>
     **/
    public DefaultWorkflowPermissions getDefaultOrganizationWorkflowPermissions(String org) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in an organization,
     * as well as whether {@code "GitHub Actions"} can submit approving pull request reviews. For more information, see
     * "Setting the permissions of the {@code "GITHUB_TOKEN"} for your organization."
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions for an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
     * Get default workflow permissions for an organization</a>
     **/
    public <T> T getDefaultOrganizationWorkflowPermissions(String org, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(ORGS_PATH + org +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                    the organization to set default workflows permissions
     * @param defWorkflowPermissions: the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
     * Set default workflow permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setDefaultOrganizationWorkflowPermissions(Organization org, DefaultWorkflowPermissions defWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defWorkflowPermissions);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param defWorkflowPermissions: the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
     * Set default workflow permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setDefaultOrganizationWorkflowPermissions(String org, DefaultWorkflowPermissions defWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defWorkflowPermissions);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                        the organization to set default workflows permissions
     * @param defaultWorkflowPermissions: default workflow permissions params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "default_workflow_permissions"} -> the default workflow permissions granted to
     *                                                 the {@code "GITHUB_TOKEN"} when running workflows - [string]
     *                                       </li>
     *                                       <li>
     *                                           {@code "can_approve_pull_request_reviews"} -> whether {@code "GitHub Actions"} can approve
     *                                                 pull requests. Enabling this can be a security risk - [boolean]
     *                                       </li>
     *                                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
     * Set default workflow permissions for an organization</a>
     **/
    @WrappedRequest
    public boolean setDefaultOrganizationWorkflowPermissions(Organization org, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org.getLogin() + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in an organization. To use this endpoint,
     * the organization permission policy for allowed_actions must be configured to selected. For more information,
     * see "Set GitHub Actions permissions for an organization."
     * If the organization belongs to an enterprise that has selected actions and reusable workflows set at the enterprise
     * level, then you cannot override any of the enterprise's allowed actions and reusable workflows settings.
     * To use the patterns_allowed setting for private repositories, the organization must belong to an enterprise.
     * If the organization does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to
     * public repositories in the organization.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration organization permission to use this API
     *
     * @param org:                        the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param defaultWorkflowPermissions: default workflow permissions params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "default_workflow_permissions"} -> the default workflow permissions granted to
     *                                                 the {@code "GITHUB_TOKEN"} when running workflows - [string]
     *                                       </li>
     *                                       <li>
     *                                           {@code "can_approve_pull_request_reviews"} -> whether {@code "GitHub Actions"} can approve
     *                                                 pull requests. Enabling this can be a security risk - [boolean]
     *                                       </li>
     *                                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
     * Set default workflow permissions for an organization</a>
     **/
    public boolean setDefaultOrganizationWorkflowPermissions(String org, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(ORGS_PATH + org + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for a repository, including whether {@code "GitHub Actions"}
     * is enabled and the actions and reusable workflows allowed to run in the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository from get actions permissions
     * @return repository actions permissions as {@link RepositoryActionsPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     * Get GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public RepositoryActionsPermissions getRepositoryActionsPermissions(Repository repository) throws IOException {
        return getRepositoryActionsPermissions(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for a repository, including whether {@code "GitHub Actions"}
     * is enabled and the actions and reusable workflows allowed to run in the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository from get actions permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository actions permissions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     * Get GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryActionsPermissions(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryActionsPermissions(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for a repository, including whether {@code "GitHub Actions"}
     * is enabled and the actions and reusable workflows allowed to run in the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository actions permissions as {@link RepositoryActionsPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     * Get GitHub Actions permissions for a repository</a>
     **/
    public RepositoryActionsPermissions getRepositoryActionsPermissions(String owner, String repo) throws IOException {
        return getRepositoryActionsPermissions(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the {@code "GitHub Actions"} permissions policy for a repository, including whether {@code "GitHub Actions"}
     * is enabled and the actions and reusable workflows allowed to run in the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository actions permissions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     * Get GitHub Actions permissions for a repository</a>
     **/
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

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository to set action permissions
     * @param enable:     whether {@code "GitHub Actions"} is enabled on the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryActionsPermissions(Repository repository, boolean enable) {
        return setActionsPermissions(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_PATH, enable, null);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param enable: whether {@code "GitHub Actions"} is enabled on the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    public boolean setRepositoryActionsPermissions(String owner, String repo, boolean enable) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH, enable,
                null);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository:      the repository to set action permissions
     * @param repoPermissions: repository permissions to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryActionsPermissions(Repository repository, RepositoryActionsPermissions repoPermissions) {
        return setActionsPermissions(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_PATH, repoPermissions.isEnabled(), repoPermissions.getAllowedActions());
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param repoPermissions: repository permissions to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryActionsPermissions(String owner, String repo, RepositoryActionsPermissions repoPermissions) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH,
                repoPermissions.isEnabled(), repoPermissions.getAllowedActions());
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository:     the repository to set action permissions
     * @param enable:         whether {@code "GitHub Actions"} is enabled on the repository
     * @param allowedActions: the permissions policy that controls the actions and reusable workflows that are allowed to run ->
     *                        Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryActionsPermissions(Repository repository, boolean enable, AllowedActions allowedActions) {
        return setActionsPermissions(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_PATH, enable, allowedActions);
    }

    /**
     * Method to set the {@code "GitHub Actions"} permissions policy for enabling {@code "GitHub Actions"} and allowed actions
     * and reusable workflows in the repository. If the repository belongs to an organization or enterprise that has set
     * restrictive permissions at the organization or enterprise levels, such as {@code "allowed_actions"} to selected
     * actions and reusable workflows, then you cannot override them for the repository.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param enable:         whether {@code "GitHub Actions"} is enabled on the repository
     * @param allowedActions: the permissions policy that controls the actions and reusable workflows that are allowed to run ->
     *                        Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
     * Set GitHub Actions permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryActionsPermissions(String owner, String repo, boolean enable, AllowedActions allowedActions) {
        return setActionsPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_PATH, enable,
                allowedActions);
    }

    /**
     * Method to set the actions permissions
     *
     * @param endpoint:       endpoint to do the request
     * @param enabledItem:    item to enable
     * @param allowedActions: the permissions policy that controls the actions and reusable workflows that are allowed to run.
     *                        Can be one of: {@code "all"}, {@code "local_only"}, {@code "selected"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
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

    /**
     * Method to get the level of access that workflows outside the repository have to actions and reusable workflows in
     * the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param repository: the repository from get the level outside
     * @return access level outside repository as {@link String}
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-the-level-of-access-for-workflows-outside-of-the-repository">
     * Get the level of access for workflows outside of the repository</a>
     **/
    @WrappedRequest
    public String getAccessLevelOutsideRepository(Repository repository) {
        return getAccessLevelOutsideRepository(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the level of access that workflows outside the repository have to actions and reusable workflows in
     * the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return access level outside repository as {@link String}
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-the-level-of-access-for-workflows-outside-of-the-repository">
     * Get the level of access for workflows outside of the repository</a>
     **/
    public String getAccessLevelOutsideRepository(String owner, String repo) {
        return getAccessLevelOutsideRepository(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the level of access that workflows outside the repository have to actions and reusable workflows in
     * the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param repository: the repository from get the level outside
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return access level outside repository as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-the-level-of-access-for-workflows-outside-of-the-repository">
     * Get the level of access for workflows outside of the repository</a>
     * @apiNote in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@link String} value fetched from the {@code "JSON"} response
     **/
    @WrappedRequest
    public <T> T getAccessLevelOutsideRepository(Repository repository, ReturnFormat format) {
        return getAccessLevelOutsideRepository(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the level of access that workflows outside the repository have to actions and reusable workflows in
     * the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return access level outside repository as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-the-level-of-access-for-workflows-outside-of-the-repository">
     * Get the level of access for workflows outside of the repository</a>
     * @apiNote in this case {@link ReturnFormat#LIBRARY_OBJECT} will return the {@link String} value fetched from the {@code "JSON"} response
     **/
    public <T> T getAccessLevelOutsideRepository(String owner, String repo, ReturnFormat format) {
        try {
            JSONObject levelAccessResponse = new JSONObject(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                    ACTIONS_PERMISSIONS_ACCESS_PATH));
            switch (format) {
                case JSON:
                    return (T) levelAccessResponse;
                case LIBRARY_OBJECT:
                    return (T) levelAccessResponse.getString("access_level");
                default:
                    return (T) levelAccessResponse.toString();
            }
        } catch (Exception e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to set the level of access that workflows outside the repository have to actions and reusable workflows
     * in the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param repository:  the repository to set the level outside
     * @param accessLevel: defines the level of access that workflows outside the repository have to actions and reusable
     *                     workflows within the repository. none means access is only possible from workflows in this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-the-level-of-access-for-workflows-outside-of-the-repository">
     * Set the level of access for workflows outside of the repository</a>
     **/
    @WrappedRequest
    public boolean setAccessLevelOutsideRepository(Repository repository, AccessLevel accessLevel) {
        return setAccessLevelOutsideRepository(repository.getOwner().getLogin(), repository.getName(), accessLevel);
    }

    /**
     * Method to set the level of access that workflows outside the repository have to actions and reusable workflows
     * in the repository. This endpoint only applies to internal repositories.
     * For more information, see "Managing GitHub Actions settings for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param accessLevel: defines the level of access that workflows outside the repository have to actions and reusable
     *                     workflows within the repository. none means access is only possible from workflows in this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-the-level-of-access-for-workflows-outside-of-the-repository">
     * Set the level of access for workflows outside of the repository</a>
     **/
    public boolean setAccessLevelOutsideRepository(String owner, String repo, AccessLevel accessLevel) {
        Params params = new Params();
        params.addParam("access_level", accessLevel);
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_ACCESS_PATH, params);
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

    /**
     * Method to get the settings for selected actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository from get allowed actions and reusable workflows
     * @return allowed actions and reusable workflows as {@link AARW} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-a-repository">
     * Get allowed actions and reusable workflows for a repository</a>
     **/
    @WrappedRequest
    public AARW getRepositoryAARW(Repository repository) throws IOException {
        return returnAARW(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the settings for selected actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository from get allowed actions and reusable workflows
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return actions and reusable workflows object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-a-repository">
     * Get allowed actions and reusable workflows for a repository</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryAARW(Repository repository, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName()
                + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), format);
    }

    /**
     * Method to get the settings for selected actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return allowed actions and reusable workflows as {@link AARW} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-a-repository">
     * Get allowed actions and reusable workflows for a repository</a>
     **/
    public AARW getRepositoryAARW(String owner, String repo) throws IOException {
        return returnAARW(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the settings for selected actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return actions and reusable workflows object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-a-repository">
     * Get allowed actions and reusable workflows for a repository</a>
     **/
    public <T> T getRepositoryAARW(String owner, String repo, ReturnFormat format) throws IOException {
        return returnAARW(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH), format);
    }

    /**
     * Method to create an actions and reusable workflows object
     *
     * @param aarwResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return actions and reusable workflows object as {@code "format"} defines
     **/
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

    /**
     * Method to set the actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * If the repository belongs to an organization or enterprise that has selected actions and reusable workflows set
     * at the organization or enterprise levels, then you cannot override any of the allowed actions and reusable workflows settings.
     * To use the {@code "patterns_allowed"} setting for private repositories, the repository must belong to an enterprise.
     * If the repository does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to public repositories.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository to set allowed actions and reusable workflows
     * @param aarw:       allowed actions and reusable workflows for a repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-a-repository">
     * Set allowed actions and reusable workflows for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryAARW(Repository repository, AARW aarw) {
        return setAARW(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * If the repository belongs to an organization or enterprise that has selected actions and reusable workflows set
     * at the organization or enterprise levels, then you cannot override any of the allowed actions and reusable workflows settings.
     * To use the {@code "patterns_allowed"} setting for private repositories, the repository must belong to an enterprise.
     * If the repository does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to public repositories.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param aarw:  allowed actions and reusable workflows for a repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-a-repository">
     * Set allowed actions and reusable workflows for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryAARW(String owner, String repo, AARW aarw) {
        return setAARW(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * If the repository belongs to an organization or enterprise that has selected actions and reusable workflows set
     * at the organization or enterprise levels, then you cannot override any of the allowed actions and reusable workflows settings.
     * To use the {@code "patterns_allowed"} setting for private repositories, the repository must belong to an enterprise.
     * If the repository does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to public repositories.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param repository: the repository to set allowed actions and reusable workflows
     * @param aarw:       allowed actions and reusable workflows params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                           this includes the actions in the actions organization - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                           creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                           verified creators - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                           and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                           For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                       </li>
     *                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-a-repository">
     * Set allowed actions and reusable workflows for a repository</a>
     **/
    @WrappedRequest
    public boolean setRepositoryAARW(Repository repository, Params aarw) {
        return setAARW(REPOS_PATH + repository.getOwner().getLogin() + "/" + repository.getName() +
                ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the actions and reusable workflows that are allowed in a repository.
     * To use this endpoint, the repository permission policy for {@code "allowed_actions"} must be configured to selected.
     * For more information, see "Set GitHub Actions permissions for a repository."
     * If the repository belongs to an organization or enterprise that has selected actions and reusable workflows set
     * at the organization or enterprise levels, then you cannot override any of the allowed actions and reusable workflows settings.
     * To use the {@code "patterns_allowed"} setting for private repositories, the repository must belong to an enterprise.
     * If the repository does not belong to an enterprise, then the {@code "patterns_allowed"} setting only applies to public repositories.
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the administration repository permission to use this API
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param aarw: allowed actions and reusable workflows params not mandatory, keys accepted are:
     *              <ul>
     *                 <li>
     *                     {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                     this includes the actions in the actions organization - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                     creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                     verified creators - [boolean]
     *                 </li>
     *                 <li>
     *                     {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                     and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                     For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                 </li>
     *              </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-a-repository">
     * Set allowed actions and reusable workflows for a repository</a>
     **/
    public boolean setRepositoryAARW(String owner, String repo, Params aarw) {
        return setAARW(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_SELECTED_ACTIONS_PATH, aarw);
    }

    /**
     * Method to set the allowed actions and reusable workflows
     *
     * @param endpoint: endpoint to do the request
     * @param aarw:     allowed actions and reusable workflows
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
    private boolean setAARW(String endpoint, AARW aarw) {
        Params params = new Params();
        JSONObject aarwSource = new JSONObject(aarw);
        aarwSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : aarwSource.keySet())
            params.addParam(key, aarwSource.get(key));
        return setAARW(endpoint, params);
    }

    /**
     * Method to set the allowed actions and reusable workflows
     *
     * @param endpoint: endpoint to do the request
     * @param aarw:     allowed actions and reusable workflows params not mandatory, keys accepted are:
     *                  <ul>
     *                     <li>
     *                         {@code "github_owned_allowed"} -> whether {@code "GitHub-owned"} actions are allowed. For example,
     *                         this includes the actions in the actions organization - [boolean]
     *                     </li>
     *                     <li>
     *                         {@code "verified_allowed"} -> whether actions from {@code "GitHub Marketplace"} verified
     *                         creators are allowed. Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"}
     *                         verified creators - [boolean]
     *                     </li>
     *                     <li>
     *                         {@code "patterns_allowed"} -> specifies a list of string-matching patterns to allow specific action(s)
     *                         and reusable workflow(s). Wildcards, tags, and SHAs are allowed.
     *                         For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*. - [array of {@link String}]
     *                     </li>
     *                  </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
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

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * as well as if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param repository: the repository from get default workflows permissions
     * @return default workflow permissions as {@link DefaultWorkflowPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
     * Get default workflow permissions for a repository</a>
     **/
    @WrappedRequest
    public DefaultWorkflowPermissions getDefaultRepositoryWorkflowPermissions(Repository repository) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() +
                "/" + repository.getName() + ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * as well as if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param repository: the repository from get default workflows permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
     * Get default workflow permissions for a repository</a>
     **/
    @WrappedRequest
    public <T> T getDefaultRepositoryWorkflowPermissions(Repository repository, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() +
                "/" + repository.getName() + ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * as well as if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return default workflow permissions as {@link DefaultWorkflowPermissions} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
     * Get default workflow permissions for a repository</a>
     **/
    public DefaultWorkflowPermissions getDefaultRepositoryWorkflowPermissions(String owner, String repo) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * as well as if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions object as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
     * Get default workflow permissions for a repository</a>
     **/
    public <T> T getDefaultRepositoryWorkflowPermissions(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDefaultWorkflowPermissions(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_PERMISSIONS_WORKFLOW_PATH), format);
    }

    /**
     * Method to create a default workflow permissions object
     *
     * @param defWorkflowPermissionsResponse: obtained from GitHub's response
     * @param format:                         return type formatter -> {@link ReturnFormat}
     * @return default workflow permissions object as {@code "format"} defines
     **/
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

    /**
     * Method to set  the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * and sets if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param repository:                 the repository to set default workflows permissions
     * @param defaultWorkflowPermissions: default workflow permissions for a repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
     * Set default workflow permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setDefaultRepositoryWorkflowPermissions(Repository repository,
                                                           DefaultWorkflowPermissions defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_PERMISSIONS_WORKFLOW_PATH, defaultWorkflowPermissions);
    }

    /**
     * Method to set  the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * and sets if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param defaultWorkflowPermissions: default workflow permissions for a repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
     * Set default workflow permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setDefaultRepositoryWorkflowPermissions(String owner, String repo,
                                                           DefaultWorkflowPermissions defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to set  the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * and sets if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     *
     * @param repository:                 the repository to set default workflows permissions
     * @param defaultWorkflowPermissions: default workflow permissions params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "default_workflow_permissions"} -> the default workflow permissions granted to
     *                                                 the {@code "GITHUB_TOKEN"} when running workflows - [string]
     *                                       </li>
     *                                       <li>
     *                                           {@code "can_approve_pull_request_reviews"} -> whether {@code "GitHub Actions"} can approve
     *                                                 pull requests. Enabling this can be a security risk - [boolean]
     *                                       </li>
     *                                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
     * Set default workflow permissions for a repository</a>
     **/
    @WrappedRequest
    public boolean setDefaultRepositoryWorkflowPermissions(Repository repository, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_PERMISSIONS_WORKFLOW_PATH, defaultWorkflowPermissions);
    }

    /**
     * Method to set  the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows in a repository,
     * and sets if {@code "GitHub Actions"} can submit approving pull request reviews. For more information,
     * see "Setting the permissions of the GITHUB_TOKEN for your repository."
     * You must authenticate using an access token with the repo scope to use this endpoint ->
     * <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the repository administration permission to use this API
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param defaultWorkflowPermissions: default workflow permissions params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "default_workflow_permissions"} -> the default workflow permissions granted to
     *                                                 the {@code "GITHUB_TOKEN"} when running workflows - [string]
     *                                       </li>
     *                                       <li>
     *                                           {@code "can_approve_pull_request_reviews"} -> whether {@code "GitHub Actions"} can approve
     *                                                 pull requests. Enabling this can be a security risk - [boolean]
     *                                       </li>
     *                                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
     * Set default workflow permissions for a repository</a>
     **/
    public boolean setDefaultRepositoryWorkflowPermissions(String owner, String repo, Params defaultWorkflowPermissions) {
        return setDefaultWorkflowPermissions(REPOS_PATH + owner + "/" + repo + ACTIONS_PERMISSIONS_WORKFLOW_PATH,
                defaultWorkflowPermissions);
    }

    /**
     * Method to set the default workflow permissions
     *
     * @param endpoint:                   endpoint to do the request
     * @param defaultWorkflowPermissions: default workflow permissions
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
    @WrappedRequest
    private boolean setDefaultWorkflowPermissions(String endpoint, DefaultWorkflowPermissions defaultWorkflowPermissions) {
        Params params = new Params();
        JSONObject defWorkflowPermissionsSource = new JSONObject(defaultWorkflowPermissions);
        defWorkflowPermissionsSource.remove(INSTANTIATED_WITH_ERROR_KEY);
        for (String key : defWorkflowPermissionsSource.keySet())
            params.addParam(key, defWorkflowPermissionsSource.get(key));
        return setDefaultWorkflowPermissions(endpoint, params);
    }

    /**
     * Method to set the default workflow permissions
     *
     * @param endpoint:                   endpoint to do the request
     * @param defaultWorkflowPermissions: the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
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

    /**
     * {@code AccessLevel} defines the level of access that workflows outside the repository have to actions and reusable
     * workflows within the repository. none means access is only possible from workflows in this repository
     **/
    public enum AccessLevel {

        /**
         * {@code none} is the constant for none access level
         **/
        none,

        /**
         * {@code organization} is the constant for organization access level
         **/
        organization,

        /**
         * {@code enterprise} is the constant for enterprise access level
         **/
        enterprise

    }

}
