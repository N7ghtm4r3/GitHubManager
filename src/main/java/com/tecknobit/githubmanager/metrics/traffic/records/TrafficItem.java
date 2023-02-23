package com.tecknobit.githubmanager.metrics.traffic.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code TrafficItem} class is useful to format a GitHub's traffic item
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
 *             Get repository clones</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
 *             Get top referral paths</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
 *             Get top referral sources</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
 *             Get page views</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public abstract class TrafficItem extends GitHubResponse {

    /**
     * {@code count} value
     **/
    protected final int count;

    /**
     * {@code uniques} value
     **/
    protected final int uniques;

    /**
     * Constructor to init a {@link TrafficItem}
     *
     * @param count:   count value
     * @param uniques: uniques value
     **/
    public TrafficItem(int count, int uniques) {
        super(null);
        this.count = count;
        this.uniques = uniques;
    }

    /**
     * Constructor to init a {@link TrafficItem}
     *
     * @param jTrafficItem: traffic item details as {@link JSONObject}
     **/
    public TrafficItem(JSONObject jTrafficItem) {
        super(jTrafficItem);
        count = hResponse.getInt("count", 0);
        uniques = hResponse.getInt("uniques", 0);
    }

    /**
     * Method to get {@link #count} instance <br>
     * No-any params required
     *
     * @return {@link #count} instance as int
     **/
    public int getCount() {
        return count;
    }

    /**
     * Method to get {@link #uniques} instance <br>
     * No-any params required
     *
     * @return {@link #uniques} instance as int
     **/
    public int getUniques() {
        return uniques;
    }

    /**
     * The {@code TrafficItem} class is useful to format a GitHub's traffic list item
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see TrafficItem
     **/
    public static class TrafficListItem extends TrafficItem {

        /**
         * {@code Per} list of available pers
         **/
        public enum Per {

            /**
             * {@code day} per
             **/
            day,

            /**
             * {@code week} per
             **/
            week

        }

        /**
         * {@code timestamp} timestamp of the action of the item
         **/
        private final String timestamp;

        /**
         * Constructor to init a {@link TrafficListItem}
         *
         * @param count:     count value
         * @param uniques:   uniques value
         * @param timestamp: timestamp of the action of the item
         **/
        public TrafficListItem(int count, int uniques, String timestamp) {
            super(count, uniques);
            this.timestamp = timestamp;
        }

        /**
         * Constructor to init a {@link TrafficListItem}
         *
         * @param jTrafficListItem: traffic list item details as {@link JSONObject}
         **/
        public TrafficListItem(JSONObject jTrafficListItem) {
            super(jTrafficListItem);
            timestamp = hResponse.getString("timestamp");
        }

        /**
         * Method to get {@link #timestamp} instance <br>
         * No-any params required
         *
         * @return {@link #timestamp} instance as {@link String}
         **/
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * Method to get {@link #timestamp} timestamp <br>
         * No-any params required
         *
         * @return {@link #timestamp} timestamp as long
         **/
        public long getLongTimestamp() {
            return getDateTimestamp(timestamp);
        }

    }

}
