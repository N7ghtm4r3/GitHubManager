package com.tecknobit.githubmanager.records.organization;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Organization} class is useful to format a GitHub's organization
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
 *             List selected organizations enabled for GitHub Actions in an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-organization-for-github-actions-in-an-enterprise">
 *             Enable a selected organization for GitHub Actions in an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-organization-for-github-actions-in-an-enterprise">
 *             Disable a selected organization for GitHub Actions in an enterprise</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Organization extends GitHubResponse {

    /**
     * {@code login} login value
     **/
    private final String login;

    /**
     * {@code id} identifier value
     **/
    private final long id;

    /**
     * {@code nodeId} identifier of the node
     **/
    private final String nodeId;

    /**
     * {@code "url"} value
     **/
    private final String url;

    /**
     * {@code reposUrl} repos url value
     **/
    private final String reposUrl;

    /**
     * {@code eventsUrl} events url value
     **/
    private final String eventsUrl;

    /**
     * {@code hooksUrl} hooks url value
     **/
    private final String hooksUrl;

    /**
     * {@code issuesUrl} issues url value
     **/
    private final String issuesUrl;

    /**
     * {@code membersUrl} members url value
     **/
    private final String membersUrl;

    /**
     * {@code publicMembersUrl} public members url value
     **/
    private final String publicMembersUrl;

    /**
     * {@code avatarUrl} avatar url value
     **/
    private final String avatarUrl;

    /**
     * {@code description} description value
     **/
    private final String description;

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param id:                     identifier of the organization
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, long id, String description) {
        this(login, id, null, null, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param url:                    url value
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, String url, String description) {
        this(login, 0, null, url, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param id:                     identifier of the organization
     * @param url:                    url value
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, long id, String url, String description) {
        this(login, id, null, url, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, String description) {
        this(login, 0, null, null, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param id:                     identifier of the organization
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(long id, String login, String description) {
        this(login, id, null, null, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param nodeId:                 identifier of the node
     * @param url:                    url value
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, String nodeId, String url, String description) {
        this(login, 0, nodeId, url, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param id:                     identifier of the organization
     * @param nodeId:                 identifier of the node
     * @param url:                    url value
     * @param description:description value
     * @apiNote this constructor is useful to contains only the basics details about an {@link Organization}
     **/
    public Organization(String login, long id, String nodeId, String url, String description) {
        this(login, id, nodeId, url, null, null, null, null, null, null, null, description);
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param login:                  login value
     * @param id:                     identifier of the organization
     * @param nodeId:                 identifier of the node
     * @param url:                    url value
     * @param reposUrl:               repos url value
     * @param eventsUrl:              events url value
     * @param hooksUrl:               hooks url value
     * @param issuesUrl:              issues url value
     * @param membersUrl:             members url value
     * @param publicMembersUrl:       public members url value
     * @param avatarUrl:              avatar url value
     * @param description:description value
     **/
    public Organization(String login, long id, String nodeId, String url, String reposUrl, String eventsUrl,
                        String hooksUrl, String issuesUrl, String membersUrl, String publicMembersUrl,
                        String avatarUrl, String description) {
        super(null);
        this.login = login;
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.hooksUrl = hooksUrl;
        this.issuesUrl = issuesUrl;
        this.membersUrl = membersUrl;
        this.publicMembersUrl = publicMembersUrl;
        this.avatarUrl = avatarUrl;
        this.description = description;
    }

    /**
     * Constructor to init a {@link Organization}
     *
     * @param jOrganization: organization details as {@link JSONObject}
     **/
    public Organization(JSONObject jOrganization) {
        super(jOrganization);
        login = hResponse.getString("login");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        reposUrl = hResponse.getString("repos_url");
        eventsUrl = hResponse.getString("events_url");
        hooksUrl = hResponse.getString("hooks_url");
        issuesUrl = hResponse.getString("issues_url");
        membersUrl = hResponse.getString("members_url");
        publicMembersUrl = hResponse.getString("public_members_url");
        avatarUrl = hResponse.getString("avatar_url");
        description = hResponse.getString("description");
    }

    /**
     * Method to get {@link #login} instance <br>
     * No-any params required
     *
     * @return {@link #login} instance as {@link String}
     **/
    public String getLogin() {
        return login;
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #reposUrl} instance <br>
     * No-any params required
     *
     * @return {@link #reposUrl} instance as {@link String}
     **/
    public String getReposUrl() {
        return reposUrl;
    }

    /**
     * Method to get {@link #eventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #eventsUrl} instance as {@link String}
     **/
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * Method to get {@link #hooksUrl} instance <br>
     * No-any params required
     *
     * @return {@link #hooksUrl} instance as {@link String}
     **/
    public String getHooksUrl() {
        return hooksUrl;
    }

    /**
     * Method to get {@link #issuesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issuesUrl} instance as {@link String}
     **/
    public String getIssuesUrl() {
        return issuesUrl;
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
     * Method to get {@link #publicMembersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #publicMembersUrl} instance as {@link String}
     **/
    public String getPublicMembersUrl() {
        return publicMembersUrl;
    }

    /**
     * Method to get {@link #avatarUrl} instance <br>
     * No-any params required
     *
     * @return {@link #avatarUrl} instance as {@link String}
     **/
    public String getAvatarUrl() {
        return avatarUrl;
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
     * {@code OrganizationVisibility} list of available organization visibilities
     **/
    public enum OrganizationVisibility {

        /**
         * {@code disabled}  organization visibility
         **/
        disabled,

        /**
         * {@code selected_members}  organization visibility
         **/
        selected_members,

        /**
         * {@code all_members}  organization visibility
         **/
        all_members,

        /**
         * {@code all_members_and_outside_collaborators}  organization visibility
         **/
        all_members_and_outside_collaborators

    }

}
