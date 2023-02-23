package com.tecknobit.githubmanager.metrics.traffic.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code TopReferralPath} class is useful to format a GitHub's top referral path
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
 * Get top referral paths</a>
 * @see GitHubResponse
 * @see TrafficItem
 **/
public class TopReferralPath extends TrafficItem {

    /**
     * {@code path} value
     **/
    private final String path;

    /**
     * {@code title} value
     **/
    private final String title;

    /**
     * Constructor to init a {@link TopReferralPath}
     *
     * @param count:   count value
     * @param uniques: uniques value
     * @param path:    path value
     * @param title:   title value
     **/
    public TopReferralPath(int count, int uniques, String path, String title) {
        super(count, uniques);
        this.path = path;
        this.title = title;
    }

    /**
     * Constructor to init a {@link TopReferralPath}
     *
     * @param jTopReferralPath: top referral path details as {@link JSONObject}
     **/
    public TopReferralPath(JSONObject jTopReferralPath) {
        super(jTopReferralPath);
        path = hResponse.getString("path");
        title = hResponse.getString("title");
    }

    /**
     * Method to get {@link #path} instance <br>
     * No-any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
    }

    /**
     * Method to get {@link #title} instance <br>
     * No-any params required
     *
     * @return {@link #title} instance as {@link String}
     **/
    public String getTitle() {
        return title;
    }

}
