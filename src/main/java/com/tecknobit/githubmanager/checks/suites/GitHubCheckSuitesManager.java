package com.tecknobit.githubmanager.checks.suites;

import com.tecknobit.githubmanager.GitHubManager;

/**
 * The {@code GitHubCheckSuitesManager} class is useful to manage all GitHub's check suites endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites">
 * Check Suites</a>
 * @see GitHubManager
 **/
public class GitHubCheckSuitesManager extends GitHubManager {

    /**
     * {@code CHECK_SUITES_PATH} constant for {@code "/check-suites"} path
     **/
    public static final String CHECK_SUITES_PATH = "/check-suites";

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCheckSuitesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCheckSuitesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCheckSuitesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCheckSuitesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCheckSuitesManager} <br>
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
    public GitHubCheckSuitesManager() {
        super();
    }

}
