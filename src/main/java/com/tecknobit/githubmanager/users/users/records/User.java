package com.tecknobit.githubmanager.users.users.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code User} class is useful to format a GitHub's user
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public class User extends GitHubResponse {

    /**
     * {@code login} login value
     **/
    protected final String login;

    /**
     * {@code id} identifier value
     **/
    protected final long id;

    /**
     * {@code nodeId} identifier of the node value
     **/
    protected final String nodeId;

    /**
     * {@code avatarUrl} avatar url value
     **/
    protected final String avatarUrl;

    /**
     * {@code gravatarId} gravatar url value
     **/
    protected final String gravatarId;

    /**
     * {@code "url"} value
     **/
    protected final String url;

    /**
     * {@code htmlUrl} html url value
     **/
    protected final String htmlUrl;

    /**
     * {@code followersUrl} followers url value
     **/
    protected final String followersUrl;

    /**
     * {@code followingUrl} following url value
     **/
    protected final String followingUrl;

    /**
     * {@code gistsUrl} gists url value
     **/
    protected final String gistsUrl;

    /**
     * {@code starredUrl} starred url value
     **/
    protected final String starredUrl;

    /**
     * {@code subscriptionsUrl} subscriptions url value
     **/
    protected final String subscriptionsUrl;

    /**
     * {@code organizationsUrl} organizations url value
     **/
    protected final String organizationsUrl;

    /**
     * {@code reposUrl} repos url value
     **/
    protected final String reposUrl;

    /**
     * {@code eventsUrl} events url value
     **/
    protected final String eventsUrl;

    /**
     * {@code receivedEventsUrl} received events url value
     **/
    protected final String receivedEventsUrl;

    /**
     * {@code type} type value
     **/
    protected final String type;

    /**
     * {@code siteAdmin} site admin value
     **/
    protected final boolean siteAdmin;

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
        super(null);
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
     * @param jUser: user details as {@link JSONObject}
     **/
    public User(JSONObject jUser) {
        super(jUser);
        login = hResponse.getString("login");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        avatarUrl = hResponse.getString("avatar_url");
        gravatarId = hResponse.getString("gravatar_id");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        followersUrl = hResponse.getString("followers_url");
        followingUrl = hResponse.getString("following_url");
        gistsUrl = hResponse.getString("gists_url");
        starredUrl = hResponse.getString("starred_url");
        subscriptionsUrl = hResponse.getString("subscriptions_url");
        organizationsUrl = hResponse.getString("organizations_url");
        reposUrl = hResponse.getString("repos_url");
        eventsUrl = hResponse.getString("events_url");
        receivedEventsUrl = hResponse.getString("received_events_url");
        type = hResponse.getString("type");
        siteAdmin = hResponse.getBoolean("site_admin");
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
     * Method to get {@link #avatarUrl} instance <br>
     * No-any params required
     *
     * @return {@link #avatarUrl} instance as {@link String}
     **/
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Method to get {@link #gravatarId} instance <br>
     * No-any params required
     *
     * @return {@link #gravatarId} instance as {@link String}
     **/
    public String getGravatarId() {
        return gravatarId;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #followersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #followersUrl} instance as {@link String}
     **/
    public String getFollowersUrl() {
        return followersUrl;
    }

    /**
     * Method to get {@link #followingUrl} instance <br>
     * No-any params required
     *
     * @return {@link #followingUrl} instance as {@link String}
     **/
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * Method to get {@link #gistsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gistsUrl} instance as {@link String}
     **/
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * Method to get {@link #starredUrl} instance <br>
     * No-any params required
     *
     * @return {@link #starredUrl} instance as {@link String}
     **/
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * Method to get {@link #subscriptionsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #subscriptionsUrl} instance as {@link String}
     **/
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * Method to get {@link #organizationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #organizationsUrl} instance as {@link String}
     **/
    public String getOrganizationsUrl() {
        return organizationsUrl;
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
     * Method to get {@link #receivedEventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #receivedEventsUrl} instance as {@link String}
     **/
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

    /**
     * Method to get {@link #siteAdmin} instance <br>
     * No-any params required
     *
     * @return {@link #siteAdmin} instance as boolean
     **/
    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    /**
     * Method to create a users list
     *
     * @param jUsers: obtained from GitHub's response
     * @return users list as {@link ArrayList} of {@link User}
     **/
    @Returner
    public static ArrayList<User> returnUsersList(JSONArray jUsers) {
        ArrayList<User> users = new ArrayList<>();
        if (jUsers != null)
            for (int j = 0; j < jUsers.length(); j++)
                users.add(new User(jUsers.getJSONObject(j)));
        return users;
    }

    /**
     * Method to create a users list
     *
     * @param usersResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnUsersList(String usersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(usersResponse);
            case LIBRARY_OBJECT:
                return (T) returnUsersList(new JSONArray(usersResponse));
            default:
                return (T) usersResponse;
        }
    }

}
