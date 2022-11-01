package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.GitHubList;
import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code OrganizationsList} class is useful to format a GitHub's organizations list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
 * List selected organizations enabled for GitHub Actions in an enterprise</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class OrganizationsList extends GitHubList {

    /**
     * {@code organizations} organizations list
     **/
    private final ArrayList<Organization> organizations;

    /**
     * Constructor to init a {@link OrganizationsList}
     *
     * @param totalCount:    total number of organizations
     * @param organizations: organizations list
     **/
    public OrganizationsList(int totalCount, ArrayList<Organization> organizations) {
        super(totalCount);
        this.organizations = organizations;
    }

    /**
     * Constructor to init a {@link OrganizationsList}
     *
     * @param jEnabledOrganizations : enabled organizations details as {@link JSONObject}
     **/
    public OrganizationsList(JSONObject jEnabledOrganizations) {
        super(jEnabledOrganizations);
        organizations = new ArrayList<>();
        JSONArray jOrganizations = hResponse.getJSONArray("organizations", new JSONArray());
        for (int j = 0; j < jOrganizations.length(); j++)
            organizations.add(new Organization(jOrganizations.getJSONObject(j)));
    }

    /**
     * Method to get {@link #organizations} instance <br>
     * Any params required
     *
     * @return {@link #organizations} instance as {@link Collection} of {@link Organization}
     **/
    public Collection<Organization> getOrganizations() {
        return organizations;
    }

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
     **/
    public static class Organization {

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
         * @param login:                  identifier value
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
            JsonHelper hOrganization = new JsonHelper(jOrganization);
            login = hOrganization.getString("login");
            id = hOrganization.getLong("id", 0);
            nodeId = hOrganization.getString("node_id");
            url = hOrganization.getString("url");
            reposUrl = hOrganization.getString("repos_url");
            eventsUrl = hOrganization.getString("events_url");
            hooksUrl = hOrganization.getString("hooks_url");
            issuesUrl = hOrganization.getString("issues_url");
            membersUrl = hOrganization.getString("members_url");
            publicMembersUrl = hOrganization.getString("public_members_url");
            avatarUrl = hOrganization.getString("avatar_url");
            description = hOrganization.getString("description");
        }

        /**
         * Method to get {@link #login} instance <br>
         * Any params required
         *
         * @return {@link #login} instance as {@link String}
         **/
        public String getLogin() {
            return login;
        }

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
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
         * Method to get {@link #url} instance <br>
         * Any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
        }

        /**
         * Method to get {@link #reposUrl} instance <br>
         * Any params required
         *
         * @return {@link #reposUrl} instance as {@link String}
         **/
        public String getReposUrl() {
            return reposUrl;
        }

        /**
         * Method to get {@link #eventsUrl} instance <br>
         * Any params required
         *
         * @return {@link #eventsUrl} instance as {@link String}
         **/
        public String getEventsUrl() {
            return eventsUrl;
        }

        /**
         * Method to get {@link #hooksUrl} instance <br>
         * Any params required
         *
         * @return {@link #hooksUrl} instance as {@link String}
         **/
        public String getHooksUrl() {
            return hooksUrl;
        }

        /**
         * Method to get {@link #issuesUrl} instance <br>
         * Any params required
         *
         * @return {@link #issuesUrl} instance as {@link String}
         **/
        public String getIssuesUrl() {
            return issuesUrl;
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
         * Method to get {@link #publicMembersUrl} instance <br>
         * Any params required
         *
         * @return {@link #publicMembersUrl} instance as {@link String}
         **/
        public String getPublicMembersUrl() {
            return publicMembersUrl;
        }

        /**
         * Method to get {@link #avatarUrl} instance <br>
         * Any params required
         *
         * @return {@link #avatarUrl} instance as {@link String}
         **/
        public String getAvatarUrl() {
            return avatarUrl;
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
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
