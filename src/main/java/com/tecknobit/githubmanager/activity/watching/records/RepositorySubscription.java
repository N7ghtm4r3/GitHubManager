package com.tecknobit.githubmanager.activity.watching.records;

import com.tecknobit.githubmanager.activity.records.Subscription;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code RepositorySubscription} class is useful to format a GitHub's repository subscription
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
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
 * @see Subscription
 **/
public class RepositorySubscription extends Subscription {

    /**
     * {@code repositoryUrl}  repository url value
     **/
    private final String repositoryUrl;

    /**
     * Constructor to init a {@link RepositorySubscription}
     *
     * @param subscribed     : whether is subscribed
     * @param ignored        :    whether is to ignore
     * @param createdAt      :  created at value
     * @param url            :        url value
     * @param repositoryUrl: repository url value
     **/
    public RepositorySubscription(boolean subscribed, boolean ignored, String createdAt, String url, String repositoryUrl) {
        super(subscribed, ignored, createdAt, url);
        this.repositoryUrl = repositoryUrl;
    }

    /**
     * Constructor to init a {@link RepositorySubscription}
     *
     * @param jSubscription : subscription details as {@link JSONObject}
     **/
    public RepositorySubscription(JSONObject jSubscription) {
        super(jSubscription);
        repositoryUrl = hResponse.getString("repository_url");
    }

    /**
     * Method to get {@link #repositoryUrl} instance <br>
     * Any params required
     *
     * @return {@link #repositoryUrl} instance as {@link String}
     **/
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

}
