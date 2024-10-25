package com.tecknobit.githubmanager.metrics.statistics.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code ContributorActivity} class is useful to format a GitHub's contributor activity
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-all-contributor-commit-activity">
 * Get all contributor commit activity</a>
 * @see GitHubResponse
 **/
public class ContributorActivity extends GitHubResponse {

    /**
     * {@code author} of the contribution
     **/
    private final User author;

    /**
     * {@code total} contributions
     **/
    private final int total;

    /**
     * {@code weeks} of the contributions
     **/
    private final ArrayList<Week> weeks;

    /**
     * Constructor to init a {@link ContributorActivity}
     *
     * @param author: author of the contribution
     * @param total:  total of the contributions
     * @param weeks:  weeks of the contributions
     **/
    public ContributorActivity(User author, int total, ArrayList<Week> weeks) {
        super(null);
        this.author = author;
        this.total = total;
        this.weeks = weeks;
    }

    /**
     * Constructor to init a {@link ContributorActivity}
     *
     * @param jContributorActivity: contributor activity details as {@link JSONObject}
     **/
    public ContributorActivity(JSONObject jContributorActivity) {
        super(jContributorActivity);
        author = new User(hResponse.getJSONObject("author"));
        total = hResponse.getInt("total");
        weeks = new ArrayList<>();
        JSONArray jWeeks = hResponse.getJSONArray("weeks", new JSONArray());
        for (int j = 0; j < jWeeks.length(); j++)
            weeks.add(new Week(jWeeks.getJSONObject(j)));
    }

    /**
     * Method to get {@link #author} instance <br>
     * No-any params required
     *
     * @return {@link #author} instance as {@link User}
     **/
    public User getAuthor() {
        return author;
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
     * Method to get {@link #weeks} instance <br>
     * No-any params required
     *
     * @return {@link #weeks} instance as {@link ArrayList} of {@link Week}
     **/
    public ArrayList<Week> getWeeks() {
        return weeks;
    }

    /**
     * The {@code Week} class is useful to format a GitHub's week
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Week extends InnerClassItem {

        /**
         * {@code w} start of the week, given as a Unix timestamp
         **/
        private final long w;

        /**
         * {@code a} number of additions
         **/
        private final int a;

        /**
         * {@code d} number of deletions
         **/
        private final int d;

        /**
         * {@code c} number of commits
         **/
        private final int c;

        /**
         * Constructor to init a {@link Week}
         *
         * @param w: start of the week, given as a Unix timestamp
         * @param a: number of additions
         * @param d: number of deletions
         * @param c: number of commits
         **/
        public Week(long w, int a, int d, int c) {
            super(null);
            this.w = w;
            this.a = a;
            this.d = d;
            this.c = c;
        }

        /**
         * Constructor to init a {@link Week}
         *
         * @param jWeek: week details as {@link JSONObject}
         **/
        public Week(JSONObject jWeek) {
            super(jWeek);
            w = hItem.getLong("w", 0);
            a = hItem.getInt("a", 0);
            d = hItem.getInt("d", 0);
            c = hItem.getInt("c", 0);
        }

        /**
         * Method to get {@link #w} instance <br>
         * No-any params required
         *
         * @return {@link #w} instance as long
         **/
        public long getW() {
            return w;
        }

        /**
         * Method to get {@link #w} instance <br>
         * No-any params required
         *
         * @return {@link #w} instance as {@link String}
         **/
        public String getWDate() {
            return timeFormatter.formatAsString(w);
        }

        /**
         * Method to get {@link #a} instance <br>
         * No-any params required
         *
         * @return {@link #a} instance as int
         **/
        public int getA() {
            return a;
        }

        /**
         * Method to get {@link #d} instance <br>
         * No-any params required
         *
         * @return {@link #d} instance as int
         **/
        public int getD() {
            return d;
        }

        /**
         * Method to get {@link #c} instance <br>
         * No-any params required
         *
         * @return {@link #c} instance as int
         **/
        public int getC() {
            return c;
        }

    }

}
