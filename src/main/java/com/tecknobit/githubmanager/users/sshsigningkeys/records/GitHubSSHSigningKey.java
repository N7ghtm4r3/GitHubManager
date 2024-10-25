package com.tecknobit.githubmanager.users.sshsigningkeys.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;



/**
 * The {@code GitHubSSHSigningKey} class is useful to format a GitHub's SSH signing key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-the-authenticated-user">
 *             List SSH signing keys for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#create-a-ssh-signing-key-for-the-authenticated-user">
 *             Create a SSH signing key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#get-an-ssh-signing-key-for-the-authenticated-user">
 *             Get an SSH signing key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-a-user">
 *             List SSH signing keys for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubSSHSigningKey extends GitHubResponse {

    /**
     * {@code key} value
     **/
    protected final String key;

    /**
     * {@code id} of the key
     **/
    protected final long id;

    /**
     * {@code title} of the key
     **/
    protected final String title;

    /**
     * {@code createdAt} creation date of the key
     **/
    protected final String createdAt;

    /**
     * Constructor to init a {@link GitHubSSHSigningKey}
     *
     * @param key       : key value
     * @param id        : id of the key
     * @param title     : title of the key
     * @param createdAt : creation date of the key
     **/
    public GitHubSSHSigningKey(String key, long id, String title, String createdAt) {
        super(null);
        this.key = key;
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    /**
     * Constructor to init a {@link GitHubSSHSigningKey}
     *
     * @param jGitHubSSHSigningKey : ssh signing key details as {@link JSONObject}
     **/
    public GitHubSSHSigningKey(JSONObject jGitHubSSHSigningKey) {
        super(jGitHubSSHSigningKey);
        key = hResponse.getString("key");
        id = hResponse.getLong("id", 0);
        title = hResponse.getString("title");
        createdAt = hResponse.getString("created_at");
    }

    /**
     * Method to get {@link #key} instance <br>
     * No-any params required
     *
     * @return {@link #key} instance as {@link String}
     **/
    public String getKey() {
        return key;
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #title} instance <br>
     * No-any params required
     *
     * @return {@link #title} instance as {@link String}
     **/
    public String getTitle() {
        return title;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(createdAt);
    }

}
