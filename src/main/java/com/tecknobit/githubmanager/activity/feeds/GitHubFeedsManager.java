package com.tecknobit.githubmanager.activity.feeds;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.activity.feeds.records.Feed;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubFeedsManager} class is useful to manage all GitHub's feeds endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/feeds">
 * Feeds</a>
 * @see GitHubManager
 **/
public class GitHubFeedsManager extends GitHubManager {

    /**
     * {@code FEEDS_PATH} constant for {@code "feeds"} path
     **/
    public static final String FEEDS_PATH = "feeds";

    /**
     * Constructor to init a {@link GitHubFeedsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubFeedsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubFeedsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubFeedsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubFeedsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubFeedsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubFeedsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubFeedsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubFeedsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubFeedsManager() {
        super();
    }

    /**
     * Method to get feeds <br>
     * GitHub provides several timeline resources in Atom format. <br>
     * The Feeds API lists all the feeds available to the authenticated user: <br>
     * <ul>
     *     <li>
     *         <b>Timeline</b>: The GitHub global public timeline
     *     </li>
     *     <li>
     *         <b>User</b>: The public timeline for any user, using URI template
     *     </li>
     *     <li>
     *         <b>Current user public</b>: The public timeline for the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user</b>: The private timeline for the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user actor</b>: The private timeline for activity created by the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user organizations</b>: The private timeline for the organizations the authenticated user is a member of.
     *     </li>
     *     <li>
     *         <b>Security advisories</b>: A collection of public announcements that provide information about security-related
     *         vulnerabilities in software on GitHub.
     *     </li>
     * </ul>
     * Note: Private feeds are only returned when authenticating via Basic Auth since current feed URIs use the older, non-revocable auth tokens<br>
     * Any params required
     *
     * @return feeds as {@link Feed} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/feeds#get-feeds">
     * Get feeds</a>
     **/
    @RequestPath(path = "/feeds")
    public Feed getFeeds() throws IOException {
        return getFeeds(LIBRARY_OBJECT);
    }

    /**
     * Method to get feeds <br>
     * GitHub provides several timeline resources in Atom format. <br>
     * The Feeds API lists all the feeds available to the authenticated user: <br>
     * <ul>
     *     <li>
     *         <b>Timeline</b>: The GitHub global public timeline
     *     </li>
     *     <li>
     *         <b>User</b>: The public timeline for any user, using URI template
     *     </li>
     *     <li>
     *         <b>Current user public</b>: The public timeline for the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user</b>: The private timeline for the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user actor</b>: The private timeline for activity created by the authenticated user
     *     </li>
     *     <li>
     *         <b>Current user organizations</b>: The private timeline for the organizations the authenticated user is a member of.
     *     </li>
     *     <li>
     *         <b>Security advisories</b>: A collection of public announcements that provide information about security-related
     *         vulnerabilities in software on GitHub.
     *     </li>
     * </ul>
     * Note: Private feeds are only returned when authenticating via Basic Auth since current feed URIs use the older, non-revocable auth tokens
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return feeds as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/feeds#get-feeds">
     * Get feeds</a>
     **/
    @Returner
    @RequestPath(path = "/feeds")
    public <T> T getFeeds(ReturnFormat format) throws IOException {
        String feedsResponse = sendGetRequest(FEEDS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(feedsResponse);
            case LIBRARY_OBJECT:
                return (T) new Feed(new JSONObject(feedsResponse));
            default:
                return (T) feedsResponse;
        }
    }

}
