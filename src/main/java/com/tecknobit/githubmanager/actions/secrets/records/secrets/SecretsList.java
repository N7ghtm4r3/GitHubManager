package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import com.tecknobit.githubmanager.records.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class SecretsList extends GitHubList {

    private final ArrayList<Secret> secrets;

    /**
     * Constructor to init an {@link SecretsList}
     *
     * @param secrets: secrets list
     **/
    public SecretsList(ArrayList<Secret> secrets) {
        super(secrets.size());
        this.secrets = secrets;
    }

    /**
     * Constructor to init an {@link SecretsList}
     *
     * @param totalCount : total number of the items in the list
     * @param secrets:   secrets list
     **/
    public SecretsList(int totalCount, ArrayList<Secret> secrets) {
        super(totalCount);
        this.secrets = secrets;
    }

    /**
     * Constructor to init a {@link SecretsList}
     *
     * @param jSecretsList : secrets list details as {@link JSONObject}
     **/
    public SecretsList(JSONObject jSecretsList) {
        super(jSecretsList);
        secrets = new ArrayList<>();
        JSONArray jSecrets = hResponse.getJSONArray("secrets", new JSONArray());
        for (int j = 0; j < jSecrets.length(); j++) {
            JSONObject jSecret = jSecrets.getJSONObject(j);
            if (jSecret.has("selected_repositories_url"))
                secrets.add(new OrganizationSecret(jSecrets.getJSONObject(j)));
            else
                secrets.add(new Secret(jSecrets.getJSONObject(j)));
        }
    }

    // TODO: 02/11/2022 WARNER USE WITH DIFFERENT SECRETS TYPE
    public Collection<Secret> getSecrets() {
        return secrets;
    }

    // TODO: 02/11/2022 WARNER USE EVANTUALLU SOME SECRETS METHODS
    public Collection<Secret> extractSimpleSecrets() {
        ArrayList<Secret> secrets = new ArrayList<>();
        for (Secret secret : this.secrets)
            if (secret.getClass() == Secret.class)
                secrets.add(secret);
        return secrets;
    }

    // TODO: 02/11/2022 WARNER USE EVANTUALLU SOME SECRETS METHODS
    public Collection<OrganizationSecret> extractOrganizationSecrets() {
        ArrayList<OrganizationSecret> organizationSecrets = new ArrayList<>();
        for (Secret secret : secrets)
            if (secret instanceof OrganizationSecret)
                organizationSecrets.add((OrganizationSecret) secret);
        return organizationSecrets;
    }

}
