package com.tecknobit.githubmanager.actions.permissions.records.actions;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.actions.ActionsPermissions.AllowedActions.all;

/**
 * The {@code ActionsPermissions} class is useful to format a GitHub's actions permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
 *             Get GitHub Actions permissions for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
 *             Get GitHub Actions permissions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
 *             Get GitHub Actions permissions for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class ActionsPermissions extends GitHubResponse {

    /**
     * {@code allowedActions} the permissions policy that controls the actions and reusable workflows that are allowed to run
     **/
    protected final AllowedActions allowedActions;

    /**
     * {@code selectedActionsUrl} selected actions url
     **/
    protected final String selectedActionsUrl;

    /**
     * Constructor to init a {@link ActionsPermissions}
     *
     * @param allowedActions:     the permissions policy that controls the actions and reusable workflows that are allowed to run
     * @param selectedActionsUrl: selected actions url
     **/
    public ActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl) {
        super(null);
        this.allowedActions = allowedActions;
        this.selectedActionsUrl = selectedActionsUrl;
    }

    /**
     * Constructor to init a {@link ActionsPermissions}
     *
     * @param jPermissions : permissions details as {@link JSONObject}
     **/
    public ActionsPermissions(JSONObject jPermissions) {
        super(jPermissions);
        allowedActions = AllowedActions.valueOf(hResponse.getString("allowed_actions", all.name()));
        selectedActionsUrl = hResponse.getString("selected_actions_url");
    }

    /**
     * Method to get {@link #allowedActions} instance <br>
     * Any params required
     *
     * @return {@link #allowedActions} instance as {@link AllowedActions}
     **/
    public AllowedActions getAllowedActions() {
        return allowedActions;
    }

    /**
     * Method to get {@link #selectedActionsUrl} instance <br>
     * Any params required
     *
     * @return {@link #selectedActionsUrl} instance as {@link String}
     **/
    public String getSelectedActionsUrl() {
        return selectedActionsUrl;
    }

    /**
     * {@code EnabledItems} the policies that controls if some items are allowed to run GitHub Actions
     *
     * @apiNote see the official documentation at:
     * <ul>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
     *             Get GitHub Actions permissions for an enterprise</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     *             Get GitHub Actions permissions for an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     *             Get GitHub Actions permissions for a repository</a>
     *     </li>
     * </ul>
     **/
    public enum EnabledItems {

        /**
         * {@code all} is the constant for all type for enabled organizations or repositories
         **/
        all,

        /**
         * {@code none} is the constant for all type for enabled organizations or repositories
         **/
        none,

        /**
         * {@code selected} is the constant for all type for enabled organizations or repositories
         **/
        selected

    }

    /**
     * {@code AllowedActions} the permissions policy that controls the actions and reusable workflows that are allowed to run
     *
     * @apiNote see the official documentation at:
     * <ul>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-enterprise">
     *             Get GitHub Actions permissions for an enterprise</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-an-organization">
     *             Get GitHub Actions permissions for an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-github-actions-permissions-for-a-repository">
     *             Get GitHub Actions permissions for a repository</a>
     *     </li>
     * </ul>
     **/
    public enum AllowedActions {

        /**
         * {@code all} is the constant for all type for allowed actions
         **/
        all,

        /**
         * {@code local_only} is the constant for local only type for allowed actions
         **/
        local_only,

        /**
         * {@code selected} is the constant for selected type for allowed actions
         **/
        selected

    }

}
