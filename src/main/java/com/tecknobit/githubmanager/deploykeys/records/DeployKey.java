package com.tecknobit.githubmanager.deploykeys.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;



/**
 * The {@code DeployKey} class is useful to format a GitHub's deployment key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
 *             List deploy keys</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
 *             Create a deploy key</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deploy-keys#get-a-deploy-key">
 *             Get a deploy key</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class DeployKey extends GitHubResponse {

    /**
     * {@code id} of the deployment key
     **/
    private final long id;

    /**
     * {@code key} value of the deployment key (OpenSSH key type)
     **/
    private final String key;

    /**
     * {@code url} of the deployment key
     **/
    private final String url;

    /**
     * {@code title} of the deployment key
     **/
    private final String title;

    /**
     * {@code verified} whether the deployment key is verified
     **/
    private final boolean verified;

    /**
     * {@code createdAt} creation time of the deployment key
     **/
    private final String createdAt;

    /**
     * {@code readOnly} whether the deployment key is in read only mode
     **/
    private final boolean readOnly;

    /**
     * {@code addedBy} who added the deployment key
     **/
    private final String addedBy;

    /**
     * {@code lastUsed} last time the deployment key was used
     **/
    private final String lastUsed;

    /**
     * Constructor to init a {@link DeployKey}
     *
     * @param id:        id of the deployment key
     * @param key:       key value of the deployment key (OpenSSH key type)
     * @param url:       url of the deployment key
     * @param title:     title of the deployment key
     * @param verified:  whether the deployment key is verified
     * @param createdAt: creation time of the deployment key
     * @param readOnly:  whether the deployment key is in read only mode
     * @param addedBy:   who added the deployment key
     * @param lastUsed:  last time the deployment key was used
     **/
    public DeployKey(long id, String key, String url, String title, boolean verified, String createdAt, boolean readOnly,
                     String addedBy, String lastUsed) {
        super(null);
        this.id = id;
        this.key = key;
        this.url = url;
        this.title = title;
        this.verified = verified;
        this.createdAt = createdAt;
        this.readOnly = readOnly;
        this.addedBy = addedBy;
        this.lastUsed = lastUsed;
    }

    /**
     * Constructor to init a {@link DeployKey}
     *
     * @param jDeployKey: deployment key details as {@link JSONObject}
     **/
    public DeployKey(JSONObject jDeployKey) {
        super(jDeployKey);
        id = hResponse.getLong("id", 0);
        key = hResponse.getString("key");
        url = hResponse.getString("url");
        title = hResponse.getString("title");
        verified = hResponse.getBoolean("verified");
        createdAt = hResponse.getString("created_at");
        readOnly = hResponse.getBoolean("read_only");
        addedBy = hResponse.getString("added_by");
        lastUsed = hResponse.getString("last_used");
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
     * Method to get {@link #key} instance <br>
     * No-any params required
     *
     * @return {@link #key} instance as {@link String}
     **/
    public String getKey() {
        return key;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #verified} instance <br>
     * No-any params required
     *
     * @return {@link #verified} instance as boolean
     **/
    public boolean isVerified() {
        return verified;
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

    /**
     * Method to get {@link #readOnly} instance <br>
     * No-any params required
     *
     * @return {@link #readOnly} instance as boolean
     **/
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Method to get {@link #addedBy} instance <br>
     * No-any params required
     *
     * @return {@link #addedBy} instance as {@link String}
     **/
    public String getAddedBy() {
        return addedBy;
    }

    /**
     * Method to get {@link #lastUsed} instance <br>
     * No-any params required
     *
     * @return {@link #lastUsed} instance as {@link String}
     **/
    public String getLastUsed() {
        return lastUsed;
    }

    /**
     * Method to get {@link #lastUsed} timestamp <br>
     * No-any params required
     *
     * @return {@link #lastUsed} timestamp as long
     **/
    public long getLastUsedTimestamp() {
        return timeFormatter.formatAsTimestamp(lastUsed);
    }

}
