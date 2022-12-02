package com.tecknobit.githubmanager.records.basics;

import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Subscription} class is useful to format a GitHub's subscription
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread-subscription-for-the-authenticated-user">
 *             Get a thread subscription for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/notifications#set-a-thread-subscription">
 *             Set a thread subscription</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/watching#get-a-repository-subscription">
 *             Get a repository subscription</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
 *             Set a repository subscription</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Subscription extends GitHubResponse {

    /**
     * {@code subscribed} whether is subscribed
     **/
    protected final boolean subscribed;

    /**
     * {@code ignored} whether is to ignore
     **/
    protected final boolean ignored;

    /**
     * {@code createdAt} created at value
     **/
    protected final String createdAt;

    /**
     * {@code url} value
     **/
    protected final String url;

    /**
     * Constructor to init a {@link Subscription}
     *
     * @param subscribed: whether is subscribed
     * @param ignored:    whether is to ignore
     * @param createdAt:  created at value
     * @param url:        url value
     **/
    public Subscription(boolean subscribed, boolean ignored, String createdAt, String url) {
        super(null);
        this.subscribed = subscribed;
        this.ignored = ignored;
        this.createdAt = createdAt;
        this.url = url;
    }

    /**
     * Constructor to init a {@link Subscription}
     *
     * @param jSubscription: subscription details as {@link JSONObject}
     **/
    public Subscription(JSONObject jSubscription) {
        super(jSubscription);
        subscribed = hResponse.getBoolean("subscribed");
        ignored = hResponse.getBoolean("ignored");
        createdAt = hResponse.getString("created_at");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #subscribed} instance <br>
     * Any params required
     *
     * @return {@link #subscribed} instance as boolean
     **/
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * Method to get {@link #ignored} instance <br>
     * Any params required
     *
     * @return {@link #ignored} instance as boolean
     **/
    public boolean isIgnored() {
        return ignored;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
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

}
