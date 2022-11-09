package com.tecknobit.githubmanager.records.basics;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

/**
 * The {@code User} class is useful to format a GitHub's user
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class User {

    /**
     * {@code login} login value
     **/
    private final String login;

    /**
     * {@code id} identifier value
     **/
    private final long id;

    /**
     * {@code nodeId} identifier of the node value
     **/
    private final String nodeId;

    /**
     * {@code avatarUrl} avatar url value
     **/
    private final String avatarUrl;

    /**
     * {@code gravatarId} gravatar url value
     **/
    private final String gravatarId;

    /**
     * {@code "url"} value
     **/
    private final String url;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code followersUrl} followers url value
     **/
    private final String followersUrl;

    /**
     * {@code followingUrl} following url value
     **/
    private final String followingUrl;

    /**
     * {@code gistsUrl} gists url value
     **/
    private final String gistsUrl;

    /**
     * {@code starredUrl} starred url value
     **/
    private final String starredUrl;

    /**
     * {@code subscriptionsUrl} subscriptions url value
     **/
    private final String subscriptionsUrl;

    /**
     * {@code organizationsUrl} organizations url value
     **/
    private final String organizationsUrl;

    /**
     * {@code reposUrl} repos url value
     **/
    private final String reposUrl;

    /**
     * {@code eventsUrl} events url value
     **/
    private final String eventsUrl;

    /**
     * {@code receivedEventsUrl} received events url value
     **/
    private final String receivedEventsUrl;

    /**
     * {@code type} type value
     **/
    private final String type;

    /**
     * {@code siteAdmin} site admin value
     **/
    private final boolean siteAdmin;

    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, boolean siteAdmin) {
        this(login, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }


    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param id:        identifier value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, long id, boolean siteAdmin) {
        this(login, id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param url:       url value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, String url, boolean siteAdmin) {
        this(login, 0, null, null, null, url, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param id:        identifier value
     * @param url:       url value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, long id, String url, boolean siteAdmin) {
        this(login, id, null, null, null, url, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param nodeId:    identifier of the node value
     * @param url:       url value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, String nodeId, String url, boolean siteAdmin) {
        this(login, 0, nodeId, null, null, url, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param login:     login value
     * @param id:        identifier value
     * @param nodeId:    identifier of the node value
     * @param url:       url value
     * @param siteAdmin: site admin value
     * @apiNote this constructor is useful to contains only the basics details about an {@link User}
     **/
    public User(String login, long id, String nodeId, String url, boolean siteAdmin) {
        this(login, id, nodeId, null, null, url, null, null, null, null, null, null, null, null, null, null, null,
                siteAdmin);
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param login:             login value
     * @param id:                identifier value
     * @param nodeId:            identifier of the node value
     * @param avatarUrl:         avatar url value
     * @param gravatarId:        gravatar url value
     * @param url:               url value
     * @param htmlUrl:           html url value
     * @param followersUrl:      followers url value
     * @param followingUrl:      following url value
     * @param gistsUrl:          gists url value
     * @param starredUrl:        starred url value
     * @param subscriptionsUrl:  subscriptions url value
     * @param organizationsUrl:  organizations url value
     * @param reposUrl:          repos url value
     * @param eventsUrl:         events url value
     * @param receivedEventsUrl: received events url value
     * @param type:              type value
     * @param siteAdmin:         site admin value
     **/
    public User(String login, long id, String nodeId, String avatarUrl, String gravatarId, String url,
                String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
                String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
                String receivedEventsUrl, String type, boolean siteAdmin) {
        this.login = login;
        this.id = id;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param jOwner: owner details as {@link JSONObject}
     **/
    public User(JSONObject jOwner) {
        JsonHelper hOwner = new JsonHelper(jOwner);
        login = hOwner.getString("login");
        id = hOwner.getLong("id", 0);
        nodeId = hOwner.getString("node_id");
        avatarUrl = hOwner.getString("avatar_url");
        gravatarId = hOwner.getString("gravatar_id");
        url = hOwner.getString("url");
        htmlUrl = hOwner.getString("html_url");
        followersUrl = hOwner.getString("followers_url");
        followingUrl = hOwner.getString("following_url");
        gistsUrl = hOwner.getString("gists_url");
        starredUrl = hOwner.getString("starred_url");
        subscriptionsUrl = hOwner.getString("subscriptions_url");
        organizationsUrl = hOwner.getString("organizations_url");
        reposUrl = hOwner.getString("repos_url");
        eventsUrl = hOwner.getString("events_url");
        receivedEventsUrl = hOwner.getString("received_events_url");
        type = hOwner.getString("type");
        siteAdmin = hOwner.getBoolean("site_admin");
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
     * Method to get {@link #avatarUrl} instance <br>
     * Any params required
     *
     * @return {@link #avatarUrl} instance as {@link String}
     **/
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Method to get {@link #gravatarId} instance <br>
     * Any params required
     *
     * @return {@link #gravatarId} instance as {@link String}
     **/
    public String getGravatarId() {
        return gravatarId;
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
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #followersUrl} instance <br>
     * Any params required
     *
     * @return {@link #followersUrl} instance as {@link String}
     **/
    public String getFollowersUrl() {
        return followersUrl;
    }

    /**
     * Method to get {@link #followingUrl} instance <br>
     * Any params required
     *
     * @return {@link #followingUrl} instance as {@link String}
     **/
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * Method to get {@link #gistsUrl} instance <br>
     * Any params required
     *
     * @return {@link #gistsUrl} instance as {@link String}
     **/
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * Method to get {@link #starredUrl} instance <br>
     * Any params required
     *
     * @return {@link #starredUrl} instance as {@link String}
     **/
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * Method to get {@link #subscriptionsUrl} instance <br>
     * Any params required
     *
     * @return {@link #subscriptionsUrl} instance as {@link String}
     **/
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * Method to get {@link #organizationsUrl} instance <br>
     * Any params required
     *
     * @return {@link #organizationsUrl} instance as {@link String}
     **/
    public String getOrganizationsUrl() {
        return organizationsUrl;
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
     * Method to get {@link #receivedEventsUrl} instance <br>
     * Any params required
     *
     * @return {@link #receivedEventsUrl} instance as {@link String}
     **/
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

    /**
     * Method to get {@link #siteAdmin} instance <br>
     * Any params required
     *
     * @return {@link #siteAdmin} instance as boolean
     **/
    public boolean isSiteAdmin() {
        return siteAdmin;
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
