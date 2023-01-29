package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubApp} class is useful to format a GitHub's app
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-the-authenticated-app">
 *             Get the authenticated app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#create-a-github-app-from-a-manifest">
 *             Create a GitHub App from a manifest</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-an-app">
 *             Get an app</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubApp extends GitHubResponse {

    /**
     * {@code id} the ID of the app
     **/
    private final long id;

    /**
     * {@code slug} the slug of the app
     **/
    private final String slug;

    /**
     * {@code nodeId} the node ID of the app
     **/
    private final String nodeId;

    /**
     * {@code owner} the owner of the app
     **/
    private final User owner;

    /**
     * {@code name} the name of the app
     **/
    private final String name;

    /**
     * {@code description} the description of the app
     **/
    private final String description;

    /**
     * {@code externalUrl} the external url of the app
     **/
    private final String externalUrl;

    /**
     * {@code htmlUrl} the html url of the app
     **/
    private final String htmlUrl;

    /**
     * {@code createdAt} the creation time of the app
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} the updated time of the app
     **/
    private final String updatedAt;

    /**
     * {@code permissions} the permissions of the app
     **/
    private final AppPermissions permissions;

    /**
     * {@code events} the list of events of the app
     **/
    private final ArrayList<String> events;

    /**
     * {@code clientId} the client ID of the app
     **/
    private final String clientId;

    /**
     * {@code clientSecret} the client secret of the app
     **/
    private final String clientSecret;

    /**
     * {@code webhookSecret} the webhook secret of the app
     **/
    private final String webhookSecret;

    /**
     * {@code pem} the pem of the app
     **/
    private final String pem;

    /**
     * Constructor to init a {@link GitHubApp}
     *
     * @param id:          the id of the app
     * @param slug:        the slug of the app
     * @param nodeId:      the node id of the app
     * @param owner:       the owner of the app
     * @param name:        the name of the app
     * @param description: the description of the app
     * @param externalUrl: the external url of the app
     * @param htmlUrl:     the html url of the app
     * @param createdAt:   the creation time of the app
     * @param updatedAt:   the updated time of the app
     * @param permissions: the permissions of the app
     * @param events:      the list of events of the app
     **/
    public GitHubApp(long id, String slug, String nodeId, User owner, String name, String description,
                     String externalUrl, String htmlUrl, String createdAt, String updatedAt,
                     AppPermissions permissions, ArrayList<String> events) {
        this(id, slug, nodeId, owner, name, description, externalUrl, htmlUrl, createdAt, updatedAt, permissions,
                events, null, null, null, null);
    }

    /**
     * Constructor to init a {@link GitHubApp}
     *
     * @param id:            the id of the app
     * @param slug:          the slug of the app
     * @param nodeId:        the node id of the app
     * @param owner:         the owner of the app
     * @param name:          the name of the app
     * @param description:   the description of the app
     * @param externalUrl:   the external url of the app
     * @param htmlUrl:       the html url of the app
     * @param createdAt:     the creation time of the app
     * @param updatedAt:     the updated time of the app
     * @param permissions:   the permissions of the app
     * @param events:        the list of events of the app
     * @param clientId:      the client id of the app
     * @param clientSecret:  the client secret of the app
     * @param webhookSecret: the webhook secret of the app
     * @param pem:           the pem of the app
     **/
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
     * Method to get {@link #slug} instance <br>
     * No-any params required
     *
     * @return {@link #slug} instance as {@link String}
     **/
    public String getSlug() {
        return slug;
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
     * Method to get {@link #owner} instance <br>
     * No-any params required
     *
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
        return owner;
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
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #externalUrl} instance <br>
     * No-any params required
     *
     * @return {@link #externalUrl} instance as {@link String}
     **/
    public String getExternalUrl() {
        return externalUrl;
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

    /**
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link AppPermissions}
     **/
    public AppPermissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #events} instance <br>
     * No-any params required
     *
     * @return {@link #events} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getEvents() {
        return events;
    }

    /**
     * Method to get {@link #clientId} instance <br>
     * No-any params required
     *
     * @return {@link #clientId} instance as {@link String}
     **/
    public String getClientId() {
        return clientId;
    }

    /**
     * Method to get {@link #clientSecret} instance <br>
     * No-any params required
     *
     * @return {@link #clientSecret} instance as {@link String}
     **/
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Method to get {@link #webhookSecret} instance <br>
     * No-any params required
     *
     * @return {@link #webhookSecret} instance as {@link String}
     **/
    public String getWebhookSecret() {
        return webhookSecret;
    }

    /**
     * Method to get {@link #pem} instance <br>
     * No-any params required
     *
     * @return {@link #pem} instance as {@link String}
     **/
    public String getPem() {
        return pem;
    }

    /**
     * Method to create an apps list
     *
     * @param appsResponse : obtained from GitHub's response
     * @param format       :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnAppsList(String appsResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(appsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubApp> apps = new ArrayList<>();
                JSONArray jApps = new JSONArray(appsResponse);
                for (int j = 0; j < jApps.length(); j++)
                    apps.add(new GitHubApp(jApps.getJSONObject(j)));
                return (T) apps;
            default:
                return (T) appsResponse;
        }
    }

}
