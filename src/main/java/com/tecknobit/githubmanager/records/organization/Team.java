package com.tecknobit.githubmanager.records.organization;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code Team} class is useful to format a GitHub's team
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Team extends BaseResponseDetails {

    /**
     * {@code nodeId} identifier of the node value
     **/
    private final String nodeId;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code slug} slug value
     **/
    private final String slug;

    /**
     * {@code description} description value
     **/
    private final String description;

    /**
     * {@code privacy} privacy value
     **/
    private final String privacy;

    /**
     * {@code permission} permission value
     **/
    private final String permission;

    /**
     * {@code membersUrl} members url value
     **/
    private final String membersUrl;

    /**
     * {@code repositoriesUrl} repositories url value
     **/
    private final String repositoriesUrl;

    /**
     * {@code parent} groups of organization members that gives permissions on specified repositories
     **/
    private final Team parent;

    /**
     * Constructor to init a {@link Team}
     *
     * @param id   : identifier value
     * @param name : the name of the team
     * @param url: url value
     * @param nodeId   : identifier of the node value
     * @param htmlUrl : html url value
     * @param slug: slug value
     * @param description   : description value
     * @param privacy : privacy value
     * @param permission: permission value
     * @param membersUrl   : members url value
     * @param repositoriesUrl : repositories url value
     * @param parent: groups of organization members that gives permissions on specified repositories
     **/
    public Team(long id, String name, String url, String nodeId, String htmlUrl, String slug, String description,
                String privacy, String permission, String membersUrl, String repositoriesUrl, Team parent) {
        super(id, name, url);
        this.nodeId = nodeId;
        this.htmlUrl = htmlUrl;
        this.slug = slug;
        this.description = description;
        this.privacy = privacy;
        this.permission = permission;
        this.membersUrl = membersUrl;
        this.repositoriesUrl = repositoriesUrl;
        this.parent = parent;
    }

    /**
     * Constructor to init a {@link Team}
     *
     * @param jTeam : team details as {@link JSONObject}
     **/
    public Team(JSONObject jTeam) {
        super(jTeam);
        nodeId = hResponse.getString("node_id");
        htmlUrl = hResponse.getString("html_url");
        slug = hResponse.getString("slug");
        description = hResponse.getString("description");
        privacy = hResponse.getString("privacy");
        permission = hResponse.getString("permission");
        membersUrl = hResponse.getString("members_url");
        repositoriesUrl = hResponse.getString("repositories_url");
        JSONObject jParent = hResponse.getJSONObject("parent");
        if (jParent != null)
            parent = new Team(jParent);
        else
            parent = null;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * Any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #slug} instance <br>
     * Any params required
     *
     * @return {@link #slug} instance as {@link String}
     **/
    public String getSlug() {
        return slug;
    }

    /**
     * Method to get {@link #description} instance <br>
     * Any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #privacy} instance <br>
     * Any params required
     *
     * @return {@link #privacy} instance as {@link String}
     **/
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Method to get {@link #permission} instance <br>
     * Any params required
     *
     * @return {@link #permission} instance as {@link String}
     **/
    public String getPermission() {
        return permission;
    }

    /**
     * Method to get {@link #membersUrl} instance <br>
     * Any params required
     *
     * @return {@link #membersUrl} instance as {@link String}
     **/
    public String getMembersUrl() {
        return membersUrl;
    }

    /**
     * Method to get {@link #repositoriesUrl} instance <br>
     * Any params required
     *
     * @return {@link #repositoriesUrl} instance as {@link String}
     **/
    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    /**
     * Method to get {@link #parent} instance <br>
     * Any params required
     *
     * @return {@link #parent} instance as {@link Team}
     **/
    public Team getParent() {
        return parent;
    }

    /**
     * Method to create a teams list
     *
     * @param teamsResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnTeamsList(String teamsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(teamsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Team> teams = new ArrayList<>();
                JSONArray jTeams = new JSONArray(teamsResponse);
                for (int j = 0; j < jTeams.length(); j++)
                    teams.add(new Team(jTeams.getJSONObject(j)));
                return (T) teams;
            default:
                return (T) teamsResponse;
        }
    }

}
