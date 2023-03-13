package com.tecknobit.githubmanager.ratelimit;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.ratelimit.records.RateOverview;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubRateLimitManager} class is useful to manage all GitHub's rate limit endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/rate-limit">
 * Rate limit</a>
 * @see GitHubManager
 **/
public class GitHubRateLimitManager extends GitHubManager {

    /**
     * {@code RATE_LIMIT_PATH} constant for {@code "rate_limit"} path
     **/
    public static final String RATE_LIMIT_PATH = "rate_limit";

    /**
     * Constructor to init a {@link GitHubRateLimitManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRateLimitManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRateLimitManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRateLimitManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRateLimitManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRateLimitManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRateLimitManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRateLimitManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRateLimitManager} <br>
     * No-any params required
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
    public GitHubRateLimitManager() {
        super();
    }

    /**
     * Method to get the rate limit status for the authenticated user <br>
     * No-any params required
     *
     * @return rate limit status as {@link RateOverview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/rate-limit#get-rate-limit-status-for-the-authenticated-user">
     * Get rate limit status for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/rate_limit")
    public RateOverview getRateLimitStatus() throws IOException {
        return getRateLimitStatus(LIBRARY_OBJECT);
    }

    /**
     * Method to get the rate limit status for the authenticated user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return rate limit status as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/rate-limit#get-rate-limit-status-for-the-authenticated-user">
     * Get rate limit status for the authenticated user</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/rate_limit")
    public <T> T getRateLimitStatus(ReturnFormat format) throws IOException {
        String rateOverviewResponse = sendGetRequest(RATE_LIMIT_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(rateOverviewResponse);
            case LIBRARY_OBJECT:
                return (T) new RateOverview(new JSONObject(rateOverviewResponse));
            default:
                return (T) rateOverviewResponse;
        }
    }

}
