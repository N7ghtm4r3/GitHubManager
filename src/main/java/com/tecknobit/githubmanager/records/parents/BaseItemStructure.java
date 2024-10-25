package com.tecknobit.githubmanager.records.parents;

import com.tecknobit.apimanager.formatters.TimeFormatter;
import org.json.JSONObject;

/**
 * The {@code BaseItemStructure} class is useful to format a GitHub's base item structure
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public abstract class BaseItemStructure extends GitHubResponse {

    /**
     * {@code timeFormatter} timeFormatter the formatter used to format the timestamp values
     */
    protected final TimeFormatter timeFormatter = TimeFormatter.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * {@code id} the id of the item
     **/
    protected final Object id;

    /**
     * {@code nodeId} the node id of the item
     **/
    protected final String nodeId;

    /**
     * {@code url} the url of the item
     **/
    protected final String url;

    /**
     * {@code createdAt} the creation time of the item
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} the updated time of the item
     **/
    protected final String updatedAt;

    /**
     * Constructor to init a {@link BaseItemStructure}
     *
     * @param url       : the url of the item
     * @param id        : the id of the item
     * @param nodeId    : the node id of the item
     * @param createdAt : the creation time of the item
     * @param updatedAt : the updated time of the item
     **/
    public <T> BaseItemStructure(String url, T id, String nodeId, String createdAt, String updatedAt) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link BaseItemStructure}
     *
     * @param jBaseItemStructure : base item structure details as {@link JSONObject}
     **/
    public BaseItemStructure(JSONObject jBaseItemStructure) {
        super(jBaseItemStructure);
        Object tmpId;
        try {
            tmpId = hResponse.getLong("id");
        } catch (NumberFormatException e) {
            tmpId = hResponse.getString("id");
        }
        id = tmpId;
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link T}
     **/
    public <T> T getId() {
        return (T) id;
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(createdAt);
    }

}
