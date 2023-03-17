package com.tecknobit.githubmanager.records.parents;

import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubOperationBaseStructure} class is useful to format a GitHub's operation base structure
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public abstract class GitHubOperationBaseStructure extends GitHubResponse {

    /**
     * {@code url} of the operation
     **/
    protected final String url;

    /**
     * {@code htmlUrl} html url of the operation
     **/
    protected final String htmlUrl;

    /**
     * {@code id} id of the operation
     **/
    protected final long id;

    /**
     * {@code nodeId} node identifier of the operation
     **/
    protected final String nodeId;

    /**
     * {@code number} of the operation
     **/
    protected final long number;

    /**
     * {@code state} of the operation
     **/
    protected final OperationState state;

    /**
     * {@code title} of the operation
     **/
    protected final String title;

    /**
     * {@code createdAt} creation time of the operation
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} update time of the operation
     **/
    protected final String updatedAt;

    /**
     * {@code closedAt} close time of the operation
     **/
    protected final String closedAt;

    /**
     * Constructor to init a {@link GitHubOperationBaseStructure}
     *
     * @param url       : url of the operation
     * @param htmlUrl   : html url of the operation
     * @param id        : id of the operation
     * @param nodeId    : node identifier of the operation
     * @param number    : number of the operation
     * @param state     : state of the operation
     * @param title     : title of the operation
     * @param createdAt : creation time of the operation
     * @param updatedAt : update time of the operation
     * @param closedAt  :  close time of the operation
     **/
    public GitHubOperationBaseStructure(String url, String htmlUrl, long id, String nodeId, long number,
                                        OperationState state, String title, String createdAt, String updatedAt,
                                        String closedAt) {
        super(null);
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.id = id;
        this.nodeId = nodeId;
        this.number = number;
        this.state = state;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
    }

    /**
     * Constructor to init a {@link GitHubOperationBaseStructure}
     *
     * @param jBaseStructure : base structure details as {@link JSONObject}
     **/
    public GitHubOperationBaseStructure(JSONObject jBaseStructure) {
        super(jBaseStructure);
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        number = hResponse.getLong("number", 0);
        String sState = hResponse.getString("state");
        if (sState != null)
            state = OperationState.valueOf(sState);
        else
            state = null;
        title = hResponse.getString("title");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        closedAt = hResponse.getString("closed_at");
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
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
     * Method to get {@link #number} instance <br>
     * No-any params required
     *
     * @return {@link #number} instance as long
     **/
    public long getNumber() {
        return number;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link OperationState}
     **/
    public OperationState getState() {
        return state;
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
        return getDateTimestamp(createdAt);
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
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #closedAt} instance <br>
     * No-any params required
     *
     * @return {@link #closedAt} instance as {@link String}
     **/
    public String getClosedAt() {
        return closedAt;
    }

    /**
     * Method to get {@link #closedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #closedAt} timestamp as long
     **/
    public long getClosedAtTimestamp() {
        return getDateTimestamp(closedAt);
    }

    /**
     * {@code OperationState} list of available operation states
     **/
    public enum OperationState {

        /**
         * {@code open} operation status
         **/
        open,

        /**
         * {@code closed} operation status
         **/
        closed,

        /**
         * {@code all} operation status
         **/
        all

    }

}
