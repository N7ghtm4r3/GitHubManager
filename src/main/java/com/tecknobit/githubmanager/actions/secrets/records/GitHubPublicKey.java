package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code GitHubPublicKey} class is useful to format a GitHub's public key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote you can see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-public-key">
 *             Get an organization public key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-public-key">
 *             Get a repository public key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-public-key">
 *             Get an environment public key</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubPublicKey extends GitHubResponse {

    /**
     * {@code keyId} the identifier for the key
     **/
    private final long keyId;

    /**
     * {@code key} the {@code "Base64"} encoded public key
     **/
    private final String key;

    /**
     * Constructor to init a {@link GitHubPublicKey}
     *
     * @param keyId: the identifier for the key
     * @param key:   the {@code "Base64"} encoded public key
     **/
    public GitHubPublicKey(long keyId, String key) {
        super(null);
        this.keyId = keyId;
        this.key = key;
    }

    /**
     * Constructor to init a {@link GitHubPublicKey}
     *
     * @param jGitHubPublicKey : public key details as {@link JSONObject}
     **/
    public GitHubPublicKey(JSONObject jGitHubPublicKey) {
        super(jGitHubPublicKey);
        keyId = hResponse.getLong("key_id");
        key = hResponse.getString("key");
    }

    /**
     * Method to get {@link #keyId} instance <br>
     * Any params required
     *
     * @return {@link #keyId} instance as long
     **/
    public long getKeyId() {
        return keyId;
    }

    /**
     * Method to get {@link #key} instance <br>
     * Any params required
     *
     * @return {@link #key} instance as {@link String}
     **/
    public String getKey() {
        return key;
    }

}
