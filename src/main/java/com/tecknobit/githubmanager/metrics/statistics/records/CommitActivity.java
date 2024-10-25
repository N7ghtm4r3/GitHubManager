package com.tecknobit.githubmanager.metrics.statistics.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code CommitActivity} class is useful to format a GitHub's commit activity
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity">
 * Get the last year of commit activity</a>
 * @see GitHubResponse
 **/
public class CommitActivity extends GitHubResponse {

    /**
     * {@code days} list of days
     **/
    private final ArrayList<Integer> days;

    /**
     * {@code total} of the commits
     **/
    private final int total;

    /**
     * {@code week} of the commits
     **/
    private final long week;

    /**
     * Constructor to init a {@link CommitActivity}
     *
     * @param days:  list of days
     * @param total: total of the commits
     * @param week:  week of the commits
     **/
    public CommitActivity(ArrayList<Integer> days, int total, long week) {
        super(null);
        this.days = days;
        this.total = total;
        this.week = week;
    }

    /**
     * Constructor to init a {@link CommitActivity}
     *
     * @param jCommitActivity: commit activity details as {@link JSONObject}
     **/
    public CommitActivity(JSONObject jCommitActivity) {
        super(jCommitActivity);
        days = returnIntegersList(hResponse.getJSONArray("days"));
        total = hResponse.getInt("total", 0);
        week = hResponse.getLong("week", 0);
    }

    /**
     * Method to get {@link #days} instance <br>
     * No-any params required
     *
     * @return {@link #days} instance as {@link ArrayList} of {@link Integer}
     **/
    public ArrayList<Integer> getDays() {
        return days;
    }

    /**
     * Method to get {@link #total} instance <br>
     * No-any params required
     *
     * @return {@link #total} instance as int
     **/
    public int getTotal() {
        return total;
    }

    /**
     * Method to get {@link #week} instance <br>
     * No-any params required
     *
     * @return {@link #week} instance as long
     **/
    public long getWeek() {
        return week;
    }

    /**
     * Method to get {@link #week} instance <br>
     * No-any params required
     *
     * @return {@link #week} instance as {@link String}
     **/
    public String getWeekDate() {
        return timeFormatter.formatAsString(week);
    }

}
