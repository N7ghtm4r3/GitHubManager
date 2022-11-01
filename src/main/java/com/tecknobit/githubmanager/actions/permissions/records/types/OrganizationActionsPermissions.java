package com.tecknobit.githubmanager.actions.permissions.records.types;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.valueOf;

/**
 * The {@code OrganizationActionsPermissions} class is useful to format a GitHub's organization actions permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
 *             Get GitHub Actions permissions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-an-organization">
 *             Set GitHub Actions permissions for an organization</a>
 *     </li>
 * </ul>
 * @see ActionsPermissions
 * @see GitHubResponse
 **/
public class OrganizationActionsPermissions extends ActionsPermissions {

    /**
     * {@code enabledOrganizations} the policy that controls the repositories in the organization that are allowed to run {@code "GitHub Actions"}
     **/
    private final EnabledItems enabledRepositories;

    /**
     * Constructor to init a {@link OrganizationActionsPermissions}
     *
     * @param allowedActions:      the permissions policy that controls the actions and reusable workflows that are allowed to run
     * @param selectedActionsUrl:  selected actions url
     * @param enabledRepositories: the policy that controls the organizations in the enterprise that are allowed to run {@code "GitHub Actions"}
     **/
    public OrganizationActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl,
                                          EnabledItems enabledRepositories) {
        super(allowedActions, selectedActionsUrl);
        this.enabledRepositories = enabledRepositories;
    }

    /**
     * Constructor to init a {@link OrganizationActionsPermissions}
     *
     * @param jOrganizationPermissions : organization permissions details as {@link JSONObject}
     **/
    public OrganizationActionsPermissions(JSONObject jOrganizationPermissions) {
        super(jOrganizationPermissions);
        enabledRepositories = valueOf(hResponse.getString("enabled_repositories", none.name()));
    }

    /**
     * Method to get {@link #enabledRepositories} instance <br>
     * Any params required
     *
     * @return {@link #enabledRepositories} instance as {@link EnabledItems}
     **/
    public EnabledItems getEnabledRepositories() {
        return enabledRepositories;
    }

}
