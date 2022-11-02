package com.tecknobit.githubmanager.actions.permissions.records.actions;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code RepositoryActionsPermissions} class is useful to format a GitHub's repository actions permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
 *             Get GitHub Actions permissions for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-github-actions-permissions-for-a-repository">
 *             Set GitHub Actions permissions for a repository</a>
 *     </li>
 * </ul>
 * @see ActionsPermissions
 * @see GitHubResponse
 **/
public class RepositoryActionsPermissions extends ActionsPermissions {

    /**
     * {@code enabled} whether {@code "GitHub Actions"} is enabled on the repository
     **/
    private final boolean enabled;

    /**
     * Constructor to init a {@link RepositoryActionsPermissions}
     *
     * @param allowedActions:     the permissions policy that controls the actions and reusable workflows that are allowed to run
     * @param selectedActionsUrl: selected actions url
     * @param enabled:whether     {@code "GitHub Actions"} is enabled on the repository
     **/
    public RepositoryActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl, boolean enabled) {
        super(allowedActions, selectedActionsUrl);
        this.enabled = enabled;
    }

    /**
     * Constructor to init a {@link RepositoryActionsPermissions}
     *
     * @param jRepositoryPermissions : repository actions permissions details as {@link JSONObject}
     **/
    public RepositoryActionsPermissions(JSONObject jRepositoryPermissions) {
        super(jRepositoryPermissions);
        enabled = hResponse.getBoolean("enabled");
    }

    /**
     * Method to get {@link #enabled} instance <br>
     * Any params required
     *
     * @return {@link #enabled} instance as boolean
     **/
    public boolean isEnabled() {
        return enabled;
    }

}
