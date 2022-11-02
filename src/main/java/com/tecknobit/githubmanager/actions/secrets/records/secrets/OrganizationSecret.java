package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.secrets.records.secrets.Secret.SecretVisibility.vPrivate;
import static com.tecknobit.githubmanager.actions.secrets.records.secrets.Secret.SecretVisibility.valueOf;

public class OrganizationSecret extends Secret {

    private final SecretVisibility visibility;
    private final String selectedRepositoriesUrl;

    public OrganizationSecret(String name, String createdAt, String updatedAt, SecretVisibility visibility,
                              String selectedRepositoriesUrl) {
        super(name, createdAt, updatedAt);
        this.visibility = visibility;
        this.selectedRepositoriesUrl = selectedRepositoriesUrl;
    }

    public OrganizationSecret(JSONObject jSecret) {
        super(jSecret);
        visibility = valueOf(hResponse.getString("visibility", vPrivate.name()));
        selectedRepositoriesUrl = hResponse.getString("selected_repositories_url");
    }

    public SecretVisibility getVisibility() {
        return visibility;
    }

    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }

}
