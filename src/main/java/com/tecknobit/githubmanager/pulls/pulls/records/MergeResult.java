package com.tecknobit.githubmanager.pulls.pulls.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code MergeResult} class is useful to format a GitHub's merge result
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
 * Merge a pull request</a>
 * @see GitHubResponse
 **/
public class MergeResult extends GitHubResponse {

    /**
     * {@code sha} of the merge
     **/
    private final String sha;

    /**
     * {@code merged} whether the merge has been successful
     **/
    private final boolean merged;

    /**
     * Constructor to init a {@link MergeResult}
     *
     * @param sha    : sha of the merge
     * @param merged : whether the merge has been successful
     **/
    public MergeResult(String sha, boolean merged) {
        super(null);
        this.sha = sha;
        this.merged = merged;
    }

    /**
     * Constructor to init a {@link MergeResult}
     *
     * @param jMergeResult: merge result details as {@link JSONObject}
     **/
    public MergeResult(JSONObject jMergeResult) {
        super(jMergeResult);
        sha = hResponse.getString("sha");
        merged = hResponse.getBoolean("merged");
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
    }

    /**
     * Method to get {@link #merged} instance <br>
     * No-any params required
     *
     * @return {@link #merged} instance as boolean
     **/
    public boolean isMerged() {
        return merged;
    }

}
