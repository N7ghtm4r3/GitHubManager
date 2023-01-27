package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code GitHubPublicKey} class is useful to format a GitHub's public key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
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
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-public-key">
 *             Get an organization public key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-public-key">
 *             Get a repository public key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/secrets#get-public-key-for-the-authenticated-user">
 *             Get public key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-public-key">
 *             Get an organization public key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-public-key">
 *             Get a repository public key</a>
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

    /**
     * Method to create a public key
     *
     * @param publicKeyResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return public key as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnPublicKey(String publicKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(publicKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubPublicKey(new JSONObject(publicKeyResponse));
            default:
                return (T) publicKeyResponse;
        }
    }

}
