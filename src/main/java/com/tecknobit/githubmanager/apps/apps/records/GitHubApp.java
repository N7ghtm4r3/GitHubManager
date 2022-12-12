package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class GitHubApp extends GitHubResponse {

    private final long id;
    private final String slug;
    private final String nodeId;
    private final User owner;
    private final String name;
    private final String description;
    private final String externalUrl;
    private final String htmlUrl;
    private final String createdAt;
    private final String updatedAt;
    private final AppPermissions permissions;
    private final ArrayList<String> events;
    private final String clientId;
    private final String clientSecret;
    private final String webhookSecret;
    private final String pem;

    public GitHubApp(long id, String slug, String nodeId, User owner, String name, String description,
                     String externalUrl, String htmlUrl, String createdAt, String updatedAt,
                     AppPermissions permissions, ArrayList<String> events) {
        this(id, slug, nodeId, owner, name, description, externalUrl, htmlUrl, createdAt, updatedAt, permissions,
                events, null, null, null, null);
    }

    public GitHubApp(long id, String slug, String nodeId, User owner, String name, String description,
                     String externalUrl, String htmlUrl, String createdAt, String updatedAt,
                     AppPermissions permissions, ArrayList<String> events, String clientId, String clientSecret,
                     String webhookSecret, String pem) {
        super(null);
        this.id = id;
        this.slug = slug;
        this.nodeId = nodeId;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.externalUrl = externalUrl;
        this.htmlUrl = htmlUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.permissions = permissions;
        this.events = events;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.webhookSecret = webhookSecret;
        this.pem = pem;
    }

    /**
     * Constructor to init a {@link GitHubApp}
     *
     * @param jAuthenticatedApp : authenticated app details as {@link JSONObject}
     **/
    public GitHubApp(JSONObject jAuthenticatedApp) throws Exception {
        super(jAuthenticatedApp);
        id = hResponse.getLong("id", 0);
        slug = hResponse.getString("slug");
        nodeId = hResponse.getString("node_id");
        owner = new User(hResponse.getJSONObject("owner", new JSONObject()));
        name = hResponse.getString("name");
        description = hResponse.getString("description");
        externalUrl = hResponse.getString("external_url");
        htmlUrl = hResponse.getString("html_url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        permissions = new AppPermissions(hResponse.getJSONObject("permissions", new JSONObject()));
        events = new ArrayList<>();
        JSONArray jEvents = hResponse.getJSONArray("events", new JSONArray());
        for (int j = 0; j < jEvents.length(); j++)
            events.add(jEvents.getString(j));
        clientId = hResponse.getString("client_id");
        clientSecret = hResponse.getString("client_secret");
        webhookSecret = hResponse.getString("webhook_secret");
        pem = hResponse.getString("pem");
    }

    public long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getNodeId() {
        return nodeId;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public AppPermissions getPermissions() {
        return permissions;
    }

    public Collection<String> getEvents() {
        return events;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public String getPem() {
        return pem;
    }

}
