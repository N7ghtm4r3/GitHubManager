package com.tecknobit.githubmanager.projects.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code ProjectItem} class is useful to format a GitHub's project item
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
 *             List organization projects</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
 *             Create an organization project</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#get-a-project">
 *             Get a project</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#update-a-project">
 *             Update a project</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
 *             List repository projects</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
 *             Create a repository project</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#create-a-user-project">
 *             Create a user project</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/projects#list-user-projects">
 *             List user projects</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/cards#get-a-project-card">
 *             Get a project card</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/cards#update-an-existing-project-card">
 *             Update an existing project card</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/cards#list-project-cards">
 *             List project cards</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/cards#create-a-project-card">
 *             Create a project card</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public abstract class ProjectItem extends GitHubResponse {

    /**
     * {@code id} identifier value
     **/
    protected final long id;

    /**
     * {@code url} value
     **/
    protected final String url;

    /**
     * {@code nodeId} node id of the project item
     **/
    protected final String nodeId;

    /**
     * {@code creator} of the project item
     **/
    protected final User creator;

    /**
     * {@code createdAt} creation date of the project item
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} update date of the project item
     **/
    protected final String updatedAt;

    /**
     * Constructor to init a {@link ProjectItem}
     *
     * @param id        : identifier value
     * @param url       : url value
     * @param nodeId    : node id of the project item
     * @param creator   : creator of the project item
     * @param createdAt : creation date of the project item
     * @param updatedAt : update date of the project item
     **/
    public ProjectItem(long id, String url, String nodeId, User creator, String createdAt, String updatedAt) {
        super(null);
        this.id = id;
        this.url = url;
        this.nodeId = nodeId;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link ProjectItem}
     *
     * @param jProject : project item details as {@link JSONObject}
     **/
    public ProjectItem(JSONObject jProject) {
        super(jProject);
        id = hResponse.getLong("id", 0);
        url = hResponse.getString("url");
        nodeId = hResponse.getString("node_id");
        creator = new User(hResponse.getJSONObject("creator"));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #creator} instance <br>
     * No-any params required
     *
     * @return {@link #creator} instance as {@link User}
     **/
    public User getCreator() {
        return creator;
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

}
