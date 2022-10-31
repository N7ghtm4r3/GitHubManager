package com.tecknobit.githubmanager.actions.permissions.records.types;

import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.none;
import static com.tecknobit.githubmanager.actions.permissions.records.types.ActionsPermissions.EnabledItems.valueOf;

public class EnterpriseActionsPermissions extends ActionsPermissions {

    private final EnabledItems enabledOrganizations;

    public EnterpriseActionsPermissions(AllowedActions allowedActions, String selectedActionsUrl, EnabledItems enabledOrganizations) {
        super(allowedActions, selectedActionsUrl);
        this.enabledOrganizations = enabledOrganizations;
    }

    /**
     * Constructor to init a {@link ActionsPermissions}
     *
     * @param jEnterprisePermissions : enterprise actions permissions details as {@link JSONObject}
     **/
    public EnterpriseActionsPermissions(JSONObject jEnterprisePermissions) {
        super(jEnterprisePermissions);
        enabledOrganizations = valueOf(hResponse.getString("enabled_organizations", none.name()));
    }

    public EnabledItems getEnabledOrganizations() {
        return enabledOrganizations;
    }

}
