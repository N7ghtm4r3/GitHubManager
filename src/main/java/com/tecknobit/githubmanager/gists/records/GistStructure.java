package com.tecknobit.githubmanager.gists.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GistStructure} class is useful to format a GitHub's gist base structure
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
 *             List gists for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
 *             Create a gist</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
 *              List public gists</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
 *              List starred gists</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist">
 *             Get a gist</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
 *             Update a gist</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
 *              List gists for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
 *             List gist comments</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
 *             Create a gist comment</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
 *              Get a gist comment</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
 *              Update a gist comment</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public abstract class GistStructure extends GitHubResponse {

    /**
     * {@code id} the id of the gist structure
     **/
    protected final String id;

    /**
     * {@code nodeId} the node id of the gist structure
     **/
    protected final String nodeId;

    /**
     * {@code url} the url of the gist structure
     **/
    protected final String url;

    /**
     * {@code createdAt} the creation time of the gist structure
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} the updated time of the gist structure
     **/
    protected final String updatedAt;

    /**
     * Constructor to init a {@link GistStructure}
     *
     * @param url       : the url of the gist structure
     * @param id        : the id of the gist structure
     * @param nodeId    : the node id of the gist structure
     * @param createdAt : the creation time of the gist structure
     * @param updatedAt : the updated time of the gist structure
     **/
    public GistStructure(String url, String id, String nodeId, String createdAt, String updatedAt) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link GistStructure}
     *
     * @param jGistStructure : gist structure details as {@link JSONObject}
     **/
    public GistStructure(JSONObject jGistStructure) {
        super(jGistStructure);
        id = hResponse.getString("id");
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    public String getId() {
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
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

}
