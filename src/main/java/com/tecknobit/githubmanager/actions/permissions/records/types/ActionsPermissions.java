package com.tecknobit.githubmanager.actions.permissions.records.types;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.AllowedActions.all;

public class ActionsPermissions extends GitHubResponse {

    private final AllowedActions allowedActions;
    private final String selectedActionsUrl;

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

    public AllowedActions getAllowedActions() {
        return allowedActions;
    }

    public String getSelectedActionsUrl() {
        return selectedActionsUrl;
    }

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
