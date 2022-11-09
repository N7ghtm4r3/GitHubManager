package com.tecknobit.githubmanager.records.organization;

import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import org.json.JSONObject;

public class Team extends BaseResponseDetails {

    private final String nodeId;
    private final String htmlUrl;
    private final String slug;
    private final String description;
    private final String privacy;
    private final String permission;
    private final String membersUrl;
    private final String repositoriesUrl;

    private final Team parent;

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

    public String getNodeId() {
        return nodeId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getPermission() {
        return permission;
    }

    public String getMembersUrl() {
        return membersUrl;
    }

    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    public Team getParent() {
        return parent;
    }

}
