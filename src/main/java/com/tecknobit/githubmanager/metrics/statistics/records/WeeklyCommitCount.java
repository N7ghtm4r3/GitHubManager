package com.tecknobit.githubmanager.metrics.statistics.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code WeeklyCommitCount} class is useful to format a GitHub's weekly commit count
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-count">
 * Get the weekly commit count</a>
 * @see GitHubResponse
 **/
public class WeeklyCommitCount extends GitHubResponse {

    /**
     * {@code all} list
     **/
    private final ArrayList<Integer> all;

    /**
     * {@code owner} list
     **/
    private final ArrayList<Integer> owner;

    /**
     * Constructor to init a {@link WeeklyCommitCount}
     *
     * @param all:   all list
     * @param owner: owner list
     **/
    public WeeklyCommitCount(ArrayList<Integer> all, ArrayList<Integer> owner) {
        super(null);
        this.all = all;
        this.owner = owner;
    }

    /**
     * Constructor to init a {@link WeeklyCommitCount}
     *
     * @param jWeeklyCommitCount: weekly commit count details as {@link JSONObject}
     **/
    public WeeklyCommitCount(JSONObject jWeeklyCommitCount) {
        super(jWeeklyCommitCount);
        all = returnIntegersList(hResponse.getJSONArray("all"));
        owner = returnIntegersList(hResponse.getJSONArray("owner"));
    }

    /**
     * Method to get {@link #all} instance <br>
     * No-any params required
     *
     * @return {@link #all} instance as {@link ArrayList} of {@link Integer}
     **/
    public ArrayList<Integer> getAll() {
        return all;
    }

    /**
     * Method to get {@link #owner} instance <br>
     * No-any params required
     *
     * @return {@link #owner} instance as {@link ArrayList} of {@link Integer}
     **/
    public ArrayList<Integer> getOwner() {
        return owner;
    }

}
