package com.tecknobit.githubmanager.actions.permissions.records.types;

import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.types.Permissions.EnabledItems.valueOf;

public class EnterprisePermissions extends Permissions {

    private final EnabledItems enabledOrganizations;

    public EnterprisePermissions(AllowedActions allowedActions, String selectedActionsUrl, EnabledItems enabledOrganizations) {
        super(allowedActions, selectedActionsUrl);
        this.enabledOrganizations = enabledOrganizations;
    }

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param jEnterprisePermissions : enterprise permissions details as {@link JSONObject}
     **/
    public EnterprisePermissions(JSONObject jEnterprisePermissions) {
        super(jEnterprisePermissions);
        enabledOrganizations = valueOf(hResponse.getString("enabled_organizations", none.name()));
    }

    public EnabledItems getEnabledOrganizations() {
        return enabledOrganizations;
    }

}
