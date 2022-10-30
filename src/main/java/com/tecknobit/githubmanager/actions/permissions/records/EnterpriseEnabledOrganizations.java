package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class EnterpriseEnabledOrganizations extends GitHubList {

    private final ArrayList<Organization> organizations;

    public EnterpriseEnabledOrganizations(int totalCount, ArrayList<Organization> organizations) {
        super(totalCount);
        this.organizations = organizations;
    }

    /**
     * Constructor to init a {@link EnterpriseEnabledOrganizations}
     *
     * @param jEnabledOrganizations : enabled organizations details as {@link JSONObject}
     **/
    public EnterpriseEnabledOrganizations(JSONObject jEnabledOrganizations) {
        super(jEnabledOrganizations);
        organizations = new ArrayList<>();
        JSONArray jOrganizations = hResponse.getJSONArray("organizations", new JSONArray());
        for (int j = 0; j < jOrganizations.length(); j++)
            organizations.add(new Organization(jOrganizations.getJSONObject(j)));
    }

    public Collection<Organization> getOrganizations() {
        return organizations;
    }

    public static class Organization {

        private final String login;
        private final long id;
        private final String nodeId;
        private final String url;
        private final String reposUrl;
        private final String eventsUrl;
        private final String hooksUrl;
        private final String issuesUrl;
        private final String membersUrl;
        private final String publicMembersUrl;
        private final String avatarUrl;
        private final String description;

        public Organization(String login, long id, String nodeId, String url, String reposUrl, String eventsUrl,
                            String hooksUrl, String issuesUrl, String membersUrl, String publicMembersUrl, String avatarUrl,
                            String description) {
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

        public String getLogin() {
            return login;
        }

        public long getId() {
            return id;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getUrl() {
            return url;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public String getHooksUrl() {
            return hooksUrl;
        }

        public String getIssuesUrl() {
            return issuesUrl;
        }

        public String getMembersUrl() {
            return membersUrl;
        }

        public String getPublicMembersUrl() {
            return publicMembersUrl;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
