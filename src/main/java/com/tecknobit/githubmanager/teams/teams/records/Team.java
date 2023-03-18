package com.tecknobit.githubmanager.teams.teams.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code Team} class is useful to format a GitHub's team
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 * @see BaseItemStructure
 **/
public class Team extends BaseItemStructure {

    /**
     * {@code Privacy} list of available privacy
     **/
    public enum Privacy {

        /**
         * {@code secret} privacy
         **/
        secret,

        /**
         * {@code closed} privacy
         **/
        closed

    }

    /**
     * {@code name} of the team
     **/
    private final String name;

    /**
     * {@code htmlUrl} html url of the team
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
     * {@code membersCount} members count of the team
     **/
    private final int membersCount;

    /**
     * {@code reposCount} repost count of the team
     **/
    private final int reposCount;

    /**
     * {@code organization} of the team
     **/
    private final Organization organization;

    /**
     * {@code ldapDn} distinguished Name (DN) that team maps to within LDAP environment
     **/
    private final String ldapDn;

    /**
     * Constructor to init a {@link Team}
     *
     * @param url:            url value
     * @param id              : identifier value
     * @param nodeId          : identifier of the node value
     * @param htmlUrl         : html url value
     * @param createdAt       : the creation time of the team
     * @param updatedAt       : the updated time of the team
     * @param name            : the name of the team
     * @param slug:           slug value
     * @param description     : description value
     * @param privacy         : privacy value
     * @param permission:     permission value
     * @param membersUrl      : members url value
     * @param repositoriesUrl : repositories url value
     * @param parent:         groups of organization members that gives permissions on specified repositories
     * @param membersCount:   members count of the team
     * @param reposCount      : repost count of the team
     * @param organization    : organization of the team
     * @param ldapDn:         distinguished Name (DN) that team maps to within LDAP environment
     **/
    public Team(String url, long id, String nodeId, String htmlUrl, String createdAt, String updatedAt, String name,
                String slug, String description, String privacy, String permission, String membersUrl,
                String repositoriesUrl, Team parent, int membersCount, int reposCount, Organization organization,
                String ldapDn) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.slug = slug;
        this.description = description;
        this.privacy = privacy;
        this.permission = permission;
        this.membersUrl = membersUrl;
        this.repositoriesUrl = repositoriesUrl;
        this.parent = parent;
        this.membersCount = membersCount;
        this.reposCount = reposCount;
        this.organization = organization;
        this.ldapDn = ldapDn;
    }

    /**
     * Constructor to init a {@link Team}
     *
     * @param jTeam : team details as {@link JSONObject}
     **/
    public Team(JSONObject jTeam) {
        super(jTeam);
        name = hResponse.getString("name");
        slug = hResponse.getString("slug");
        htmlUrl = hResponse.getString("html_url");
        description = hResponse.getString("description");
        privacy = hResponse.getString("privacy");
        permission = hResponse.getString("permission");
        membersUrl = hResponse.getString("members_url");
        repositoriesUrl = hResponse.getString("repositories_url");
        JSONObject jItem = hResponse.getJSONObject("parent");
        if (jItem != null)
            parent = new Team(jItem);
        else
            parent = null;
        membersCount = hResponse.getInt("members_count", 0);
        reposCount = hResponse.getInt("repos_count", 0);
        jItem = hResponse.getJSONObject("organization");
        if (jItem != null)
            organization = new Organization(jItem);
        else
            organization = null;
        ldapDn = hResponse.getString("ldap_dn");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link Long}
     **/
    @Override
    public Long getId() {
        return super.getId();
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #slug} instance <br>
     * No-any params required
     *
     * @return {@link #slug} instance as {@link String}
     **/
    public String getSlug() {
        return slug;
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
     * Method to get {@link #privacy} instance <br>
     * No-any params required
     *
     * @return {@link #privacy} instance as {@link String}
     **/
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Method to get {@link #permission} instance <br>
     * No-any params required
     *
     * @return {@link #permission} instance as {@link String}
     **/
    public String getPermission() {
        return permission;
    }

    /**
     * Method to get {@link #membersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #membersUrl} instance as {@link String}
     **/
    public String getMembersUrl() {
        return membersUrl;
    }

    /**
     * Method to get {@link #repositoriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositoriesUrl} instance as {@link String}
     **/
    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    /**
     * Method to get {@link #parent} instance <br>
     * No-any params required
     *
     * @return {@link #parent} instance as {@link Team}
     **/
    public Team getParent() {
        return parent;
    }

    /**
     * Method to get {@link #membersCount} instance <br>
     * No-any params required
     *
     * @return {@link #membersCount} instance as int
     **/
    public int getMembersCount() {
        return membersCount;
    }

    /**
     * Method to get {@link #reposCount} instance <br>
     * No-any params required
     *
     * @return {@link #reposCount} instance as int
     **/
    public int getReposCount() {
        return reposCount;
    }

    /**
     * Method to get {@link #organization} instance <br>
     * No-any params required
     *
     * @return {@link #organization} instance as {@link Organization}
     **/
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Method to get {@link #ldapDn} instance <br>
     * No-any params required
     *
     * @return {@link #ldapDn} instance as {@link String}
     **/
    public String getLdapDn() {
        return ldapDn;
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
