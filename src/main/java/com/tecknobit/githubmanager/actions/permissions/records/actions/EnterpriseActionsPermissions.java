package com.tecknobit.githubmanager.actions.permissions.records.actions;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.actions.ActionsPermissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.actions.ActionsPermissions.EnabledItems.valueOf;

/**
 * The {@code EnterpriseActionsPermissions} class is useful to format a GitHub's enterprise actions permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
 *             Get GitHub Actions permissions for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-enterprise">
 *             Set GitHub Actions permissions for an enterprise</a>
 *     </li>
 * </ul>
 * @see ActionsPermissions
 * @see GitHubResponse
 **/
public class EnterpriseActionsPermissions extends ActionsPermissions {

    /**
     * {@code enabledOrganizations} the policy that controls the organizations in the enterprise that are allowed to run {@code "GitHub Actions"}
     **/
    private final EnabledItems enabledOrganizations;

    /**
     * Constructor to init a {@link EnterpriseActionsPermissions}
     *
     * @param allowedActions:       the permissions policy that controls the actions and reusable workflows that are allowed to run
     * @param selectedActionsUrl:   selected actions url
     * @param enabledOrganizations: the policy that controls the organizations in the enterprise that are allowed to run {@code "GitHub Actions"}
     **/
    public EnterpriseActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl, EnabledItems enabledOrganizations) {
        super(allowedActions, selectedActionsUrl);
        this.enabledOrganizations = enabledOrganizations;
    }

    /**
     * Constructor to init a {@link EnterpriseActionsPermissions}
     *
     * @param jEnterprisePermissions : enterprise actions permissions details as {@link JSONObject}
     **/
    public EnterpriseActionsPermissions(JSONObject jEnterprisePermissions) {
        super(jEnterprisePermissions);
        enabledOrganizations = valueOf(hResponse.getString("enabled_organizations", none.name()));
    }

    /**
     * Method to get {@link #enabledOrganizations} instance <br>
     * Any params required
     *
     * @return {@link #enabledOrganizations} instance as {@link EnabledItems}
     **/
    public EnabledItems getEnabledOrganizations() {
        return enabledOrganizations;
    }

}
