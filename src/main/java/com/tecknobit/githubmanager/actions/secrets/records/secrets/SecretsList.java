package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code SecretsList} class is useful to format a GitHub's secrets list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote you can see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
 *             List organization secrets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
 *             List repository secrets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
 *             List environment secrets</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class SecretsList extends GitHubList {

    /**
     * {@code secrets} secrets list
     **/
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
     * @param totalCount : total number of the secrets in the list
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

    /**
     * Method to get {@link #secrets} instance <br>
     * Any params required
     *
     * @return {@link #secrets} instance as {@link Collection} of {@link Secret}
     * @apiNote this method will return a {@link Collection} that could include also {@link OrganizationSecret}
     * objects if exist
     **/
    public Collection<Secret> getSecrets() {
        return secrets;
    }

    /**
     * Method to get a secrets collection <br>
     * Any params required
     *
     * @return secrets collection as {@link Collection} of {@link Secret}
     * @apiNote this method will return a {@link Collection} that could include only {@link Secret}
     * objects if exist
     **/
    public Collection<Secret> extractSimpleSecrets() {
        ArrayList<Secret> secrets = new ArrayList<>();
        for (Secret secret : this.secrets)
            if (secret.getClass() == Secret.class)
                secrets.add(secret);
        return secrets;
    }

    /**
     * Method to get an organization secrets collection <br>
     * Any params required
     *
     * @return organization secrets collection as {@link Collection} of {@link OrganizationSecret}
     * @apiNote this method will return a {@link Collection} that could include only {@link OrganizationSecret}
     * objects if exist
     **/
    public Collection<OrganizationSecret> extractOrganizationSecrets() {
        ArrayList<OrganizationSecret> organizationSecrets = new ArrayList<>();
        for (Secret secret : secrets)
            if (secret instanceof OrganizationSecret)
                organizationSecrets.add((OrganizationSecret) secret);
        return organizationSecrets;
    }

}
