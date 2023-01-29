package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code SecretsList} class is useful to format a GitHub's secrets list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
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
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
 *             List organization secrets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
 *             List repository secrets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
 *             List secrets for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
 *             List organization secrets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
 *             List repository secrets</a>
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
        for (int j = 0; j < jSecrets.length(); j++)
            secrets.add(new Secret(jSecrets.getJSONObject(j)));
    }

    /**
     * Method to get {@link #secrets} instance <br>
     * No-any params required
     *
     * @return {@link #secrets} instance as {@link Collection} of {@link Secret}
     **/
    public Collection<Secret> getSecrets() {
        return secrets;
    }

    /**
     * Method to create a secrets list
     *
     * @param secretsListResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return secrets list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnSecretsList(String secretsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretsListResponse);
            case LIBRARY_OBJECT:
                return (T) new SecretsList(new JSONObject(secretsListResponse));
            default:
                return (T) secretsListResponse;
        }
    }

}
