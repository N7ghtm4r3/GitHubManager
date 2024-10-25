package com.tecknobit.githubmanager.commits.commitstatuses.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;



/**
 * The {@code CommitStatus} class is useful to format a GitHub's commit status
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
 *             List commit statuses for a reference</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
 *             Create a commit status</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class CommitStatus extends GitHubResponse {

    /**
     * {@code url} of the commit status
     **/
    private final String url;
    /**
     * {@code id} of the commit status
     **/
    private final long id;
    /**
     * {@code nodeId} node id of the commit status
     **/
    private final String nodeId;
    /**
     * {@code state} of the commit status
     **/
    private final CommitStatusState state;
    /**
     * {@code description} of the commit status
     **/
    private final String description;
    /**
     * {@code targetUrl} target url of the commit status
     **/
    private final String targetUrl;
    /**
     * {@code context} of the commit status
     **/
    private final String context;
    /**
     * {@code createdAt} created time of the commit status
     **/
    private final String createdAt;
    /**
     * {@code updatedAt} update time of the commit status
     **/
    private final String updatedAt;
    /**
     * {@code creator} of the commit status
     **/
    private final User creator;

    /**
     * Constructor to init a {@link CommitStatus}
     *
     * @param state:       state of the commit status
     * @param description: description    of the commit status
     * @param targetUrl    :   target url  of the commit status
     * @param context      :  context of the commit status
     * @apiNote this constructor is useful when you need to create a new {@link CommitStatus}
     **/
    public CommitStatus(CommitStatusState state, String targetUrl, String description, String context) {
        this(null, 0, null, state, description, targetUrl, context, null, null, null);
    }

    /**
     * Constructor to init a {@link CommitStatus}
     *
     * @param url          :    url of the commit status
     * @param id           :     id   of the commit status
     * @param nodeId       : node id of the commit status
     * @param state:       state of the commit status
     * @param description: description    of the commit status
     * @param targetUrl    :   target url  of the commit status
     * @param context      :  context of the commit status
     * @param createdAt    : creation time of the commit status
     * @param updatedAt:   update time of the commit status
     * @param creator:     creator  of the commit status
     **/
    public CommitStatus(String url, long id, String nodeId, CommitStatusState state, String description, String targetUrl,
                        String context, String createdAt, String updatedAt, User creator) {
        super(null);
        this.url = url;
        this.id = id;
        this.nodeId = nodeId;
        this.state = state;
        this.description = description;
        this.targetUrl = targetUrl;
        this.context = context;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creator = creator;
    }

    /**
     * Constructor to init a {@link CommitStatus}
     *
     * @param jCommitStatus: commit status details as {@link JSONObject}
     **/
    public CommitStatus(JSONObject jCommitStatus) {
        super(jCommitStatus);
        url = hResponse.getString("url");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        state = CommitStatusState.valueOf(hResponse.getString("state"));
        description = hResponse.getString("");
        targetUrl = hResponse.getString("target_url");
        context = hResponse.getString("context");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        creator = new User(hResponse.getJSONObject("creator", new JSONObject()));
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
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link CommitStatusState}
     **/
    public CommitStatusState getState() {
        return state;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #targetUrl} instance <br>
     * No-any params required
     *
     * @return {@link #targetUrl} instance as {@link String}
     **/
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * Method to get {@link #context} instance <br>
     * No-any params required
     *
     * @return {@link #context} instance as {@link String}
     **/
    public String getContext() {
        return context;
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
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #creator} instance <br>
     * No-any params required
     *
     * @return {@link #creator} instance as {@link User}
     **/
    public User getCreator() {
        return creator;
    }

    /**
     * {@code CommitStatusState} list of available commit status states
     **/
    public enum CommitStatusState {

        /**
         * {@code error} commit status state
         **/
        error,

        /**
         * {@code failure} commit status state
         **/
        failure,

        /**
         * {@code pending} commit status state
         **/
        pending,

        /**
         * {@code success} commit status state
         **/
        success

    }

}
