package com.tecknobit.githubmanager.actions.permissions.records.types;

import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.valueOf;

public class OrganizationActionsPermissions extends ActionsPermissions {

    private final EnabledItems enabledRepositories;

    public OrganizationActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl,
                                          EnabledItems enabledRepositories) {
        super(allowedActions, selectedActionsUrl);
        this.enabledRepositories = enabledRepositories;
    }

    /**
     * Constructor to init a {@link ActionsPermissions}
     *
     * @param jOrganizationPermissions : organization permissions details as {@link JSONObject}
     **/
    public OrganizationActionsPermissions(JSONObject jOrganizationPermissions) {
        super(jOrganizationPermissions);
        enabledRepositories = valueOf(hResponse.getString("enabled_repositories", none.name()));
    }

    public EnabledItems getEnabledRepositories() {
        return enabledRepositories;
    }

}
