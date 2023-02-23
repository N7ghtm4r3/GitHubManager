package com.tecknobit.githubmanager.metrics.traffic.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code TopReferralSource} class is useful to format a GitHub's top referral source
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
 * Get top referral sources</a>
 * @see GitHubResponse
 * @see TrafficItem
 **/
public class TopReferralSource extends TrafficItem {

    /**
     * {@code referrer} value
     **/
    private final String referrer;

    /**
     * Constructor to init a {@link TopReferralSource}
     *
     * @param count:    count value
     * @param uniques:  uniques value
     * @param referrer: referrer value
     **/
    public TopReferralSource(int count, int uniques, String referrer) {
        super(count, uniques);
        this.referrer = referrer;
    }

    /**
     * Constructor to init a {@link TopReferralSource}
     *
     * @param jTopReferralSource: top referral source details as {@link JSONObject}
     **/
    public TopReferralSource(JSONObject jTopReferralSource) {
        super(jTopReferralSource);
        referrer = hResponse.getString("referrer");
    }

    /**
     * Method to get {@link #referrer} instance <br>
     * No-any params required
     *
     * @return {@link #referrer} instance as {@link String}
     **/
    public String getReferrer() {
        return referrer;
    }

}
