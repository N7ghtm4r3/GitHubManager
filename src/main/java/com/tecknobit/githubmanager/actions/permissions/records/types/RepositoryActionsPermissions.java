package com.tecknobit.githubmanager.actions.permissions.records.types;

import org.json.JSONObject;

public class RepositoryActionsPermissions extends ActionsPermissions {

    private final boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

}
