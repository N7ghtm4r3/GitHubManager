package com.tecknobit.githubmanager.projects.cards.records;

import com.tecknobit.githubmanager.projects.records.ProjectItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

/**
 * The {@code ProjectCard} class is useful to format a GitHub's project card
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
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
 * @see ProjectItem
 **/
public class ProjectCard extends ProjectItem {

    /**
     * {@code ArchivedState} list of available archived states
     **/
    public enum ArchivedState {

        /**
         * {@code all} archived state
         **/
        all,

        /**
         * {@code archived} state
         **/
        archived,

        /**
         * {@code not_archived} archived state
         **/
        not_archived

    }

    /**
     * {@code note} of the project card
     **/
    private final String note;

    /**
     * {@code not_archived} whether or not the card is archived
     **/
    private final boolean archived;

    /**
     * {@code columnName} column name of the project card
     **/
    private final String columnName;

    /**
     * {@code projectId} project id of the project card
     **/
    private final long projectId;

    /**
     * {@code columnUrl} column url of the project card
     **/
    private final String columnUrl;

    /**
     * {@code contentUrl} content url of the project card
     **/
    private final String contentUrl;

    /**
     * {@code projectUrl} project url of the project card
     **/
    private final String projectUrl;

    /**
     * Constructor to init a {@link ProjectCard}
     *
     * @param id         : identifier value
     * @param url        : url value
     * @param nodeId     : node id of the project card
     * @param creator    : creator of the project card
     * @param createdAt  : creation date of the project card
     * @param updatedAt  : update date of the project card
     * @param note       : note of the project card
     * @param archived   : whether the card is archived
     * @param columnName : column name of the project card
     * @param projectId  : project id of the project card
     * @param columnUrl  : column url of the project card
     * @param contentUrl : content url of the project card
     * @param projectUrl : project url of the project card
     **/
    public ProjectCard(long id, String url, String nodeId, User creator, String createdAt, String updatedAt, String note,
                       boolean archived, String columnName, long projectId, String columnUrl, String contentUrl,
                       String projectUrl) {
        super(id, url, nodeId, creator, createdAt, updatedAt);
        this.note = note;
        this.archived = archived;
        this.columnName = columnName;
        this.projectId = projectId;
        this.columnUrl = columnUrl;
        this.contentUrl = contentUrl;
        this.projectUrl = projectUrl;
    }

    /**
     * Constructor to init a {@link ProjectCard}
     *
     * @param jProjectCard : project card details as {@link JSONObject}
     **/
    public ProjectCard(JSONObject jProjectCard) {
        super(jProjectCard);
        note = hResponse.getString("note");
        archived = hResponse.getBoolean("archived");
        columnName = hResponse.getString("column_name");
        projectId = hResponse.getLong("project_id", 0);
        columnUrl = hResponse.getString("column_url");
        contentUrl = hResponse.getString("content_url");
        projectUrl = hResponse.getString("project_url");
    }

    /**
     * Method to get {@link #note} instance <br>
     * No-any params required
     *
     * @return {@link #note} instance as {@link String}
     **/
    public String getNote() {
        return note;
    }

    /**
     * Method to get {@link #archived} instance <br>
     * No-any params required
     *
     * @return {@link #archived} instance as boolean
     **/
    public boolean isArchived() {
        return archived;
    }

    /**
     * Method to get {@link #columnName} instance <br>
     * No-any params required
     *
     * @return {@link #columnName} instance as {@link String}
     **/
    public String getColumnName() {
        return columnName;
    }

    /**
     * Method to get {@link #projectId} instance <br>
     * No-any params required
     *
     * @return {@link #projectId} instance as long
     **/
    public long getProjectId() {
        return projectId;
    }

    /**
     * Method to get {@link #columnUrl} instance <br>
     * No-any params required
     *
     * @return {@link #columnUrl} instance as {@link String}
     **/
    public String getColumnUrl() {
        return columnUrl;
    }

    /**
     * Method to get {@link #contentUrl} instance <br>
     * No-any params required
     *
     * @return {@link #contentUrl} instance as {@link String}
     **/
    public String getContentUrl() {
        return contentUrl;
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

}
