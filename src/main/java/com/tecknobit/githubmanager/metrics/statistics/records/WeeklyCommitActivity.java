package com.tecknobit.githubmanager.metrics.statistics.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getStringDate;

/**
 * The {@code WeeklyCommitActivity} class is useful to format a GitHub's weekly commit activity
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-activity">
 * Get the weekly commit activity</a>
 * @see GitHubResponse
 **/
public class WeeklyCommitActivity extends GitHubResponse {

    /**
     * {@code pushedAt} pushed time of the weekly commit activity
     **/
    private final long pushedAt;

    /**
     * {@code additions} of the weekly commit activity
     **/
    private final int additions;

    /**
     * {@code deletions} of the weekly commit activity
     **/
    private final int deletions;

    /**
     * Constructor to init a {@link WeeklyCommitActivity}
     *
     * @param pushedAt:  pushed time of the weekly commit activity
     * @param additions: additions of the weekly commit activity
     * @param deletions: deletions of the weekly commit activity
     **/
    public WeeklyCommitActivity(long pushedAt, int additions, int deletions) {
        super(null);
        this.pushedAt = pushedAt;
        this.additions = additions;
        this.deletions = deletions;
    }

    /**
     * Constructor to init a {@link WeeklyCommitActivity}
     *
     * @param jWeeklyCommitActivity: weekly commit activity details as {@link JSONObject}
     **/
    public WeeklyCommitActivity(JSONArray jWeeklyCommitActivity) {
        super(new JSONObject().put("response", jWeeklyCommitActivity));
        hResponse.setJSONArraySource(jWeeklyCommitActivity);
        pushedAt = hResponse.getInt(0, 0);
        additions = hResponse.getInt(1, 0);
        deletions = hResponse.getInt(2, 0);
    }

    /**
     * Method to get {@link #pushedAt} instance <br>
     * No-any params required
     *
     * @return {@link #pushedAt} instance as long
     **/
    public long getPushedAt() {
        return pushedAt;
    }

    /**
     * Method to get {@link #pushedAt} instance <br>
     * No-any params required
     *
     * @return {@link #pushedAt} instance as {@link String}
     **/
    public String getPushedAtDate() {
        return getStringDate(pushedAt);
    }

    /**
     * Method to get {@link #additions} instance <br>
     * No-any params required
     *
     * @return {@link #additions} instance as int
     **/
    public int getAdditions() {
        return additions;
    }

    /**
     * Method to get {@link #deletions} instance <br>
     * No-any params required
     *
     * @return {@link #deletions} instance as int
     **/
    public int getDeletions() {
        return deletions;
    }

}
