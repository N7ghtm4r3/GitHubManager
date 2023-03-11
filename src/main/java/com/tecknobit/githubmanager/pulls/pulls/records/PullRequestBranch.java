package com.tecknobit.githubmanager.pulls.pulls.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code PullRequestBranch} class is useful to format a GitHub's pull request branch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
 * Update a pull request branch</a>
 * @see GitHubResponse
 **/
public class PullRequestBranch extends GitHubResponse {

    /**
     * {@code url} of the pull request branch
     **/
    private final String url;

    /**
     * Constructor to init a {@link PullRequestBranch}
     *
     * @param url: url of the pull request branch
     **/
    public PullRequestBranch(String url) {
        super(null);
        this.url = url;
    }

    /**
     * Constructor to init a {@link PullRequestBranch}
     *
     * @param jPullRequestBranch: pull request branch details as {@link JSONObject}
     **/
    public PullRequestBranch(JSONObject jPullRequestBranch) {
        super(jPullRequestBranch);
        url = hResponse.getString("url");
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

}
