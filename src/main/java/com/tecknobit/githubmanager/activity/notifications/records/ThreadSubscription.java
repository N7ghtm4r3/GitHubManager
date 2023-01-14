package com.tecknobit.githubmanager.activity.notifications.records;

import com.tecknobit.githubmanager.activity.records.Subscription;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Subscription} class is useful to format a GitHub's thread subscription
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
 * </ul>
 * @see GitHubResponse
 * @see Subscription
 **/
public class ThreadSubscription extends Subscription {

    /**
     * {@code threadUrl} thread url value
     **/
    private final String threadUrl;

    /**
     * Constructor to init a {@link ThreadSubscription}
     *
     * @param subscribed: whether is subscribed
     * @param ignored:    whether is to ignore
     * @param createdAt:  created at value
     * @param url:        url value
     * @param threadUrl:  thread url value
     **/
    public ThreadSubscription(boolean subscribed, boolean ignored, String createdAt, String url, String threadUrl) {
        super(subscribed, ignored, createdAt, url);
        this.threadUrl = threadUrl;
    }

    /**
     * Constructor to init a {@link ThreadSubscription}
     *
     * @param jSubscription: subscription details as {@link JSONObject}
     **/
    public ThreadSubscription(JSONObject jSubscription) {
        super(jSubscription);
        threadUrl = hResponse.getString("thread_url");
    }

    /**
     * Method to get {@link #threadUrl} instance <br>
     * Any params required
     *
     * @return {@link #threadUrl} instance as {@link String}
     **/
    public String getThreadUrl() {
        return threadUrl;
    }

}
