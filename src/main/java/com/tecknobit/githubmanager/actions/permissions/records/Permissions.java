package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

public class Permissions extends GitHubManager.GitHubResponse {

    private final String enabledOrganizations;
    private final String allowedActions;
    private final String selectedActionsUrl;

    public Permissions(String enabledOrganizations, String allowedActions, String selectedActionsUrl) {
        super(null);
        this.enabledOrganizations = enabledOrganizations;
        this.allowedActions = allowedActions;
        this.selectedActionsUrl = selectedActionsUrl;
    }

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param jPermissions : permissions details as {@link JSONObject}
     **/
    public Permissions(JSONObject jPermissions) {
        super(jPermissions);
        enabledOrganizations = hResponse.getString("enabled_organizations");
        allowedActions = hResponse.getString("allowed_actions");
        selectedActionsUrl = hResponse.getString("selected_actions_url");
    }

    public String getEnabledOrganizations() {
        return enabledOrganizations;
    }

    public String getAllowedActions() {
        return allowedActions;
    }

    public String getSelectedActionsUrl() {
        return selectedActionsUrl;
    }

    public enum EnabledOrganizations {

        /**
         * {@code all} is the constant for all type for enabled organizations
         **/
        all,

        /**
         * {@code none} is the constant for all type for enabled organizations
         **/
        none,

        /**
         * {@code selected} is the constant for all type for enabled organizations
         **/
        selected

    }

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
