package com.tecknobit.githubmanager.projects.boards.records;

import com.tecknobit.githubmanager.projects.records.ProjectItem;
import com.tecknobit.githubmanager.records.parents.GitHubOperationBaseStructure.OperationState;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

/**
 * The {@code Project} class is useful to format a GitHub's project
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
 * </ul>
 * @see GitHubResponse
 * @see ProjectItem
 **/
public class Project extends ProjectItem {

    /**
     * {@code OrganizationPermission} list of available organization permissions
     **/
    public enum OrganizationPermission {

        /**
         * {@code read} organization permission
         **/
        read,

        /**
         * {@code write} organization permission
         **/
        write,

        /**
         * {@code admin} organization permission
         **/
        admin,

        /**
         * {@code none} organization permission
         **/
        none

    }

    /**
     * {@code name} of the project
     **/
    private final String name;

    /**
     * {@code ownerUrl} owner url of the project
     **/
    private final String ownerUrl;

    /**
     * {@code htmlUrl} html url of the project
     **/
    private final String htmlUrl;

    /**
     * {@code columnsUrl} columns url of the project
     **/
    private final String columnsUrl;

    /**
     * {@code body} of the project
     **/
    private final String body;

    /**
     * {@code number} of the project
     **/
    private final int number;

    /**
     * {@code state} of the project
     **/
    private final OperationState state;

    /**
     * {@code organizationPermission} the baseline permission that all organization members have on this project. Only present if owner is
     * an organization
     **/
    private final OrganizationPermission organizationPermission;

    /**
     * {@code isPrivate} whether or not this project can be seen by everyone. Only present if owner is an organization
     **/
    private final boolean isPrivate;

    /**
     * Constructor to init a {@link Project}
     *
     * @param id                     : identifier value
     * @param url                    : url value
     * @param nodeId                 : node id of the project
     * @param creator                : creator of the project
     * @param createdAt              : creation date of the project
     * @param updatedAt              : update date of the project
     * @param name:                  name of the project
     * @param ownerUrl               : owner url of the project
     * @param htmlUrl                : html url of the project
     * @param columnsUrl             : columns url of the project
     * @param body                   : body of the project
     * @param number                 : number of the project
     * @param state                  : state of the project
     * @param organizationPermission : the baseline permission that all organization members have on this project. Only
     *                               present if owner is an organization
     * @param isPrivate              : whether this project can be seen by everyone. Only present if owner is an organization
     **/
    public Project(long id, String url, String nodeId, User creator, String createdAt, String updatedAt, String name,
                   String ownerUrl, String htmlUrl, String columnsUrl, String body, int number, OperationState state,
                   OrganizationPermission organizationPermission, boolean isPrivate) {
        super(id, url, nodeId, creator, createdAt, updatedAt);
        this.name = name;
        this.ownerUrl = ownerUrl;
        this.htmlUrl = htmlUrl;
        this.columnsUrl = columnsUrl;
        this.body = body;
        this.number = number;
        this.state = state;
        this.organizationPermission = organizationPermission;
        this.isPrivate = isPrivate;
    }

    /**
     * Constructor to init a {@link Project}
     *
     * @param jProject : project details as {@link JSONObject}
     **/
    public Project(JSONObject jProject) {
        super(jProject);
        name = hResponse.getString("name");
        ownerUrl = hResponse.getString("owner_url");
        htmlUrl = hResponse.getString("html_url");
        columnsUrl = hResponse.getString("columns_url");
        body = hResponse.getString("body");
        number = hResponse.getInt("number", 0);
        state = OperationState.valueOf(hResponse.getString("state"));
        String sPermission = hResponse.getString("organization_permission");
        if (sPermission != null)
            organizationPermission = OrganizationPermission.valueOf(sPermission);
        else
            organizationPermission = null;
        isPrivate = hResponse.getBoolean("private");
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #ownerUrl} instance <br>
     * No-any params required
     *
     * @return {@link #ownerUrl} instance as {@link String}
     **/
    public String getOwnerUrl() {
        return ownerUrl;
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
     * Method to get {@link #columnsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #columnsUrl} instance as {@link String}
     **/
    public String getColumnsUrl() {
        return columnsUrl;
    }

    /**
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #number} instance <br>
     * No-any params required
     *
     * @return {@link #number} instance as number
     **/
    public int getNumber() {
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
     * Method to get {@link #organizationPermission} instance <br>
     * No-any params required
     *
     * @return {@link #organizationPermission} instance as {@link OrganizationPermission}
     **/
    public OrganizationPermission getOrganizationPermission() {
        return organizationPermission;
    }

    /**
     * Method to get {@link #isPrivate} instance <br>
     * No-any params required
     *
     * @return {@link #isPrivate} instance as boolean
     **/
    public boolean isPrivate() {
        return isPrivate;
    }

}
