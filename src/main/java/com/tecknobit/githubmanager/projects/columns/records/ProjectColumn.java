package com.tecknobit.githubmanager.projects.columns.records;

import com.tecknobit.githubmanager.projects.records.ProjectItem;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code ProjectColumn} class is useful to format a GitHub's project column
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/columns#get-a-project-column">
 *             Get a project column</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/columns#update-an-existing-project-column">
 *             Update an existing project column</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
 *             List project columns</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/projects/columns#create-a-project-column">
 *             Create a project column</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ProjectItem
 **/
public class ProjectColumn extends BaseResponseDetails {

    /**
     * {@code nodeId} node id of the project column
     **/
    private final String nodeId;

    /**
     * {@code projectUrl} project url of the project column
     **/
    private final String projectUrl;

    /**
     * {@code cardsUrl} cards url of the project column
     **/
    private final String cardsUrl;

    /**
     * {@code createdAt} creation date of the project column
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated date of the project column
     **/
    private final String updatedAt;

    /**
     * Constructor to init a {@link ProjectColumn}
     *
     * @param id         : identifier value
     * @param name       : the name of the item
     * @param url        : url value
     * @param nodeId     : node id of the project column
     * @param projectUrl : cards url of the project column
     * @param cardsUrl   : the name of the item
     * @param createdAt  : creation date of the project column
     * @param updatedAt  : updated date of the project column
     **/
    public ProjectColumn(long id, String name, String url, String nodeId, String projectUrl, String cardsUrl,
                         String createdAt, String updatedAt) {
        super(id, name, url);
        this.nodeId = nodeId;
        this.projectUrl = projectUrl;
        this.cardsUrl = cardsUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link ProjectColumn}
     *
     * @param jProjectColumn : project column details as {@link JSONObject}
     **/
    public ProjectColumn(JSONObject jProjectColumn) {
        super(jProjectColumn);
        nodeId = hResponse.getString("node_id");
        projectUrl = hResponse.getString("project_url");
        cardsUrl = hResponse.getString("cards_url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
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
     * Method to get {@link #projectUrl} instance <br>
     * No-any params required
     *
     * @return {@link #projectUrl} instance as {@link String}
     **/
    public String getProjectUrl() {
        return projectUrl;
    }

    /**
     * Method to get {@link #cardsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #cardsUrl} instance as {@link String}
     **/
    public String getCardsUrl() {
        return cardsUrl;
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
