package com.tecknobit.githubmanager.metrics.statistics.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The {@code HourlyCommitCount} class is useful to format a GitHub's hourly commit count
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-hourly-commit-count-for-each-day">
 * Get the hourly commit count for each day</a>
 * @see GitHubResponse
 **/
public class HourlyCommitCount extends GitHubResponse {

    /**
     * {@code day} of the commit
     **/
    private final int day;

    /**
     * {@code hour} of the commit
     **/
    private final int hour;

    /**
     * {@code totalCommits} total commits value
     **/
    private final int totalCommits;

    /**
     * Constructor to init a {@link HourlyCommitCount}
     *
     * @param day:          day of the commit
     * @param hour:         hour of the commit
     * @param totalCommits: total commits value
     **/
    public HourlyCommitCount(int day, int hour, int totalCommits) {
        super(null);
        this.day = day;
        this.hour = hour;
        this.totalCommits = totalCommits;
    }

    /**
     * Constructor to init a {@link HourlyCommitCount}
     *
     * @param jHourlyCommitCount: hourly commit count details as {@link JSONObject}
     **/
    public HourlyCommitCount(JSONArray jHourlyCommitCount) {
        super(new JSONObject().put("response", jHourlyCommitCount));
        hResponse.setJSONArraySource(jHourlyCommitCount);
        day = hResponse.getInt(0, 0);
        hour = hResponse.getInt(1, 0);
        totalCommits = hResponse.getInt(2, 0);
    }

    /**
     * Method to get {@link #day} instance <br>
     * No-any params required
     *
     * @return {@link #day} instance as int
     **/
    public int getDay() {
        return day;
    }

    /**
     * Method to get {@link #hour} instance <br>
     * No-any params required
     *
     * @return {@link #hour} instance as int
     **/
    public int getHour() {
        return hour;
    }

    /**
     * Method to get {@link #totalCommits} instance <br>
     * No-any params required
     *
     * @return {@link #totalCommits} instance as int
     **/
    public int getTotalCommits() {
        return totalCommits;
    }

}
