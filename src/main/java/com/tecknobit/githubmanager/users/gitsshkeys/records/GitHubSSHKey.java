package com.tecknobit.githubmanager.users.gitsshkeys.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.sshsigningkeys.records.GitHubSSHSigningKey;
import org.json.JSONObject;

/**
 * The {@code GitHubSSHKey} class is useful to format a GitHub's SSH key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/keys#list-public-ssh-keys-for-the-authenticated-user">
 *             List public SSH keys for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
 *             Create a public SSH key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/keys#get-a-public-ssh-key-for-the-authenticated-user">
 *             Get a public SSH key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
 *             List public keys for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubSSHKey extends GitHubSSHSigningKey {

    /**
     * {@code verified} whether the key is verified
     **/
    private final boolean verified;

    /**
     * {@code readOnly} whether the key is read only
     **/
    private final boolean readOnly;

    /**
     * Constructor to init a {@link GitHubSSHKey}
     *
     * @param key       : key value
     * @param id        : id of the key
     * @param title     : title of the key
     * @param createdAt : creation date of the key
     * @param verified  : whether the key is verified
     * @param readOnly  : whether the key is read only
     **/
    public GitHubSSHKey(String key, long id, String title, String createdAt, boolean verified, boolean readOnly) {
        super(key, id, title, createdAt);
        this.verified = verified;
        this.readOnly = readOnly;
    }

    /**
     * Constructor to init a {@link GitHubSSHKey}
     *
     * @param jGitHubSSHKey : ssh key details as {@link JSONObject}
     **/
    public GitHubSSHKey(JSONObject jGitHubSSHKey) {
        super(jGitHubSSHKey);
        verified = hResponse.getBoolean("verified");
        readOnly = hResponse.getBoolean("read_only");
    }

    /**
     * Method to get {@link #verified} instance <br>
     * No-any params required
     *
     * @return {@link #verified} instance as boolean
     **/
    public boolean isVerified() {
        return verified;
    }

    /**
     * Method to get {@link #readOnly} instance <br>
     * No-any params required
     *
     * @return {@link #readOnly} instance as boolean
     **/
    public boolean isReadOnly() {
        return readOnly;
    }

}
