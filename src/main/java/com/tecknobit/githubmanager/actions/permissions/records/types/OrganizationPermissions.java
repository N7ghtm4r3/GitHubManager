package com.tecknobit.githubmanager.actions.permissions.records.types;

import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.EnabledItems.valueOf;

public class OrganizationPermissions extends Permissions {

    private final EnabledItems enabledRepositories;

    public OrganizationPermissions(AllowedActions allowedActions, String selectedActionsUrl,
                                   EnabledItems enabledRepositories) {
        super(allowedActions, selectedActionsUrl);
        this.enabledRepositories = enabledRepositories;
    }

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param jOrganizationPermissions : organization permissions details as {@link JSONObject}
     **/
    public OrganizationPermissions(JSONObject jOrganizationPermissions) {
        super(jOrganizationPermissions);
        enabledRepositories = valueOf(hResponse.getString("enabled_repositories", none.name()));
    }

    public EnabledItems getEnabledRepositories() {
        return enabledRepositories;
    }

}
