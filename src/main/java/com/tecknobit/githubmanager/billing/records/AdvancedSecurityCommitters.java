package com.tecknobit.githubmanager.billing.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * The {@code AdvancedSecurityCommitters} class is useful to format a GitHub's advanced security committers
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
 * Get GitHub Advanced Security active committers for an organization</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class AdvancedSecurityCommitters extends GitHubList {

    /**
     * {@code totalAdvancedSecurityCommitters} total advanced security committers
     **/
    private final int totalAdvancedSecurityCommitters;

    /**
     * {@code repositories} repositories list
     **/
    private final ArrayList<SecurityRepository> repositories;

    /**
     * Constructor to init an {@link AdvancedSecurityCommitters}
     *
     * @param totalAdvancedSecurityCommitters: total advanced security committers
     * @param repositories:                    repositories list
     **/
    public AdvancedSecurityCommitters(int totalAdvancedSecurityCommitters,
                                      ArrayList<SecurityRepository> repositories) {
        this(repositories.size(), totalAdvancedSecurityCommitters, repositories);
    }

    /**
     * Constructor to init an {@link AdvancedSecurityCommitters}
     *
     * @param totalCount                       : total number of the items in the list
     * @param totalAdvancedSecurityCommitters: total advanced security committers
     * @param repositories:                    repositories list
     **/
    public AdvancedSecurityCommitters(int totalCount, int totalAdvancedSecurityCommitters,
                                      ArrayList<SecurityRepository> repositories) {
        super(totalCount);
        this.totalAdvancedSecurityCommitters = totalAdvancedSecurityCommitters;
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link AdvancedSecurityCommitters}
     *
     * @param jCommitters : advanced security committers details as {@link JSONObject}
     **/
    public AdvancedSecurityCommitters(JSONObject jCommitters) {
        super(jCommitters);
        totalAdvancedSecurityCommitters = hResponse.getInt("total_advanced_security_committers", 0);
        repositories = new ArrayList<>();
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new SecurityRepository(jRepositories.getJSONObject(j)));
    }

    /**
     * Method to get {@link #totalAdvancedSecurityCommitters} instance <br>
     * No-any params required
     *
     * @return {@link #totalAdvancedSecurityCommitters} instance as int
     **/
    public int getTotalAdvancedSecurityCommitters() {
        return totalAdvancedSecurityCommitters;
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * No-any params required
     *
     * @return {@link #repositories} instance as {@link ArrayList} of {@link SecurityRepository}
     **/
    public ArrayList<SecurityRepository> getRepositories() {
        return repositories;
    }

    /**
     * The {@code SecurityRepository} class is useful to format a GitHub's security repository
     * for {@link AdvancedSecurityCommitters}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SecurityRepository extends InnerClassItem {

        /**
         * {@code name} name of the security repository
         **/
        private final String name;

        /**
         * {@code advancedSecurityCommitters} advanced security committers of the security repository
         **/
        private final int advancedSecurityCommitters;

        /**
         * {@code advancedSecurityCommittersBreakdown} list of advanced security committers breakdown
         **/
        private final ArrayList<AdvancedSecurityCommitterBreakdown> advancedSecurityCommittersBreakdown;

        /**
         * Constructor to init an {@link SecurityRepository}
         *
         * @param name                                 : name of the security repository
         * @param advancedSecurityCommitters:          advanced security committers of the security repository
         * @param advancedSecurityCommittersBreakdown: list of advanced security committers breakdown
         **/
        public SecurityRepository(String name, int advancedSecurityCommitters,
                                  ArrayList<AdvancedSecurityCommitterBreakdown> advancedSecurityCommittersBreakdown) {
            super(null);
            this.name = name;
            this.advancedSecurityCommitters = advancedSecurityCommitters;
            this.advancedSecurityCommittersBreakdown = advancedSecurityCommittersBreakdown;
        }

        /**
         * Constructor to init an {@link SecurityRepository}
         *
         * @param jSecurityRepository: security repository details as {@link JSONObject}
         **/
        public SecurityRepository(JSONObject jSecurityRepository) {
            super(jSecurityRepository);
            name = hItem.getString("name");
            advancedSecurityCommitters = hItem.getInt("advanced_security_committers", 0);
            advancedSecurityCommittersBreakdown = new ArrayList<>();
            JSONArray jCommitters = hItem.getJSONArray("advanced_security_committers_breakdown",
                    new JSONArray());
            for (int j = 0; j < jCommitters.length(); j++)
                advancedSecurityCommittersBreakdown.add(new AdvancedSecurityCommitterBreakdown(jCommitters.getJSONObject(j)));
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #advancedSecurityCommitters} instance <br>
         * No-any params required
         *
         * @return {@link #advancedSecurityCommitters} instance as int
         **/
        public int getAdvancedSecurityCommitters() {
            return advancedSecurityCommitters;
        }

        /**
         * Method to get {@link #advancedSecurityCommittersBreakdown} instance <br>
         * No-any params required
         *
         * @return {@link #advancedSecurityCommittersBreakdown} instance as {@link ArrayList} of {@link AdvancedSecurityCommitterBreakdown}
         **/
        public ArrayList<AdvancedSecurityCommitterBreakdown> getAdvancedSecurityCommittersBreakdown() {
            return advancedSecurityCommittersBreakdown;
        }

        /**
         * The {@code AdvancedSecurityCommitterBreakdown} class is useful to format a GitHub's advanced security
         * committer breakdown for {@link SecurityRepository}
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class AdvancedSecurityCommitterBreakdown extends InnerClassItem {

            /**
             * {@code userLogin} user login of the advanced security committer breakdown
             **/
            private final String userLogin;

            /**
             * {@code lastPushedDate} last pushed date of the advanced security committer breakdown
             **/
            private final String lastPushedDate;

            /**
             * Constructor to init an {@link AdvancedSecurityCommitterBreakdown}
             *
             * @param userLogin:      user login of the advanced security committer breakdown
             * @param lastPushedDate: last pushed date of the advanced security committer breakdown
             **/
            public AdvancedSecurityCommitterBreakdown(String userLogin, String lastPushedDate) {
                super(null);
                this.userLogin = userLogin;
                this.lastPushedDate = lastPushedDate;
            }

            /**
             * Constructor to init an {@link AdvancedSecurityCommitterBreakdown}
             *
             * @param jCommitter: committer details as {@link JSONObject}
             **/
            public AdvancedSecurityCommitterBreakdown(JSONObject jCommitter) {
                super(jCommitter);
                userLogin = hItem.getString("user_login");
                lastPushedDate = hItem.getString("last_pushed_date");
            }

            /**
             * Method to get {@link #userLogin} instance <br>
             * No-any params required
             *
             * @return {@link #userLogin} instance as {@link String}
             **/
            public String getUserLogin() {
                return userLogin;
            }

            /**
             * Method to get {@link #lastPushedDate} instance <br>
             * No-any params required
             *
             * @return {@link #lastPushedDate} instance as {@link String}
             **/
            public String getLastPushedDate() {
                return lastPushedDate;
            }

            /**
             * Method to get {@link #lastPushedDate} timestamp <br>
             * No-any params required
             *
             * @return {@link #lastPushedDate} timestamp as long
             **/
            public long getLastPushedDateTimestamp() {
                return timeFormatter.formatAsTimestamp(lastPushedDate, "yyyy-MM-dd");
            }

        }

    }

}
