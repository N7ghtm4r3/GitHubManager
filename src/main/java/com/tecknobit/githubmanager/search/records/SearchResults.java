package com.tecknobit.githubmanager.search.records;

import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code SearchResults} class is useful to format a GitHub's search results
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-code">
 *              Search code</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-commits">
 *              Search commits</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-issues-and-pull-requests">
 *              Search issues and pull requests</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-labels">
 *              Search labels</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-repositories">
 *              Search repositories</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-topics">
 *              Search topics</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/search#search-users">
 *              Search users</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class SearchResults extends GitHubResponse {

    /**
     * {@code CommitSort} list of available commit sorters
     **/
    public enum CommitSort {

        /**
         * {@code author_date} commit sorter
         **/
        author_date("author-date"),

        /**
         * {@code committer_date} commit sorter
         **/
        committer_date("committer-date");

        /**
         * {@code sort} value
         **/
        private final String sort;

        /**
         * Constructor to init a {@link CommitSort}
         *
         * @param sort: sort value
         **/
        CommitSort(String sort) {
            this.sort = sort;
        }

        /**
         * Method to get {@link #sort} instance <br>
         * No-any params required
         *
         * @return {@link #sort} instance as {@link String}
         **/
        @Override
        public String toString() {
            return sort;
        }

    }

    /**
     * {@code IssueSort} list of available issue sorters
     **/
    public enum IssueSort {

        /**
         * {@code comments} issue sorter
         **/
        comments("comments"),

        /**
         * {@code reactions} issue sorter
         **/
        reactions("reactions"),

        /**
         * {@code reactions_p1} issue sorter
         **/
        reactions_p1("reactions-+1"),

        /**
         * {@code reactions_m1} issue sorter
         **/
        reactions_m1("reactions--1"),

        /**
         * {@code reactions_smile} issue sorter
         **/
        reactions_smile("reactions-smile"),

        /**
         * {@code reactions_thinking_face} issue sorter
         **/
        reactions_thinking_face("reactions-thinking_face"),

        /**
         * {@code reactions_heart} issue sorter
         **/
        reactions_heart("reactions-heart"),

        /**
         * {@code reactions_tada} issue sorter
         **/
        reactions_tada("reactions-tada"),

        /**
         * {@code interactions} issue sorter
         **/
        interactions("interactions"),

        /**
         * {@code created} issue sorter
         **/
        created("created"),

        /**
         * {@code updated} issue sorter
         **/
        updated("updated");

        /**
         * {@code sort} value
         **/
        private final String sort;

        /**
         * Constructor to init a {@link IssueSort}
         *
         * @param sort: sort value
         **/
        IssueSort(String sort) {
            this.sort = sort;
        }

        /**
         * Method to get {@link #sort} instance <br>
         * No-any params required
         *
         * @return {@link #sort} instance as {@link String}
         **/
        @Override
        public String toString() {
            return sort;
        }

    }

    /**
     * {@code RepositorySort} list of available repository sorters
     **/
    public enum RepositorySort {

        /**
         * {@code stars} repository sorter
         **/
        stars("stars"),

        /**
         * {@code forks} repository sorter
         **/
        forks("forks"),

        /**
         * {@code help_wanted_issues} repository sorter
         **/
        help_wanted_issues("help-wanted-issues"),

        /**
         * {@code updated} repository sorter
         **/
        updated("updated");

        /**
         * {@code sort} value
         **/
        private final String sort;

        /**
         * Constructor to init a {@link RepositorySort}
         *
         * @param sort: sort value
         **/
        RepositorySort(String sort) {
            this.sort = sort;
        }

        /**
         * Method to get {@link #sort} instance <br>
         * No-any params required
         *
         * @return {@link #sort} instance as {@link String}
         **/
        @Override
        public String toString() {
            return sort;
        }

    }

    /**
     * {@code UserSort} list of available user sorters
     **/
    public enum UserSort {

        /**
         * {@code followers} user sorter
         **/
        followers,

        /**
         * {@code repositories} user sorter
         **/
        repositories,

        /**
         * {@code joined} user sorter
         **/
        joined

    }

    /**
     * {@code totalCount} total count of the results
     **/
    private final int totalCount;

    /**
     * {@code incompleteResults} whether the results are incomplete
     **/
    private final boolean incompleteResults;

    /**
     * {@code items} of the results
     **/
    private final ArrayList<SearchItem> items;

    /**
     * Constructor to init a {@link SearchResults}
     *
     * @param totalCount:       total count of the results
     * @param incompleteResults : whether the results are incomplete
     * @param items             : items of the results
     **/
    public SearchResults(int totalCount, boolean incompleteResults, ArrayList<SearchItem> items) {
        super(null);
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    /**
     * Constructor to init a {@link SearchResults}
     *
     * @param itemType:      type of the result item to fetch
     * @param jSearchResults : search results details as {@link JSONObject}
     **/
    public SearchResults(Class itemType, JSONObject jSearchResults) {
        super(jSearchResults);
        totalCount = hResponse.getInt("total_count", 0);
        incompleteResults = hResponse.getBoolean("incomplete_results");
        items = new ArrayList<>();
        JSONArray jItems = hResponse.getJSONArray("items", new JSONArray());
        for (int j = 0; j < jItems.length(); j++)
            items.add(new SearchItem(itemType, jItems.getJSONObject(j)));
    }

    /**
     * Method to get {@link #totalCount} instance <br>
     * No-any params required
     *
     * @return {@link #totalCount} instance as int
     **/
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Method to get {@link #incompleteResults} instance <br>
     * No-any params required
     *
     * @return {@link #incompleteResults} instance as boolean
     **/
    public boolean areIncompleteResults() {
        return incompleteResults;
    }

    /**
     * Method to get {@link #items} instance <br>
     * No-any params required
     *
     * @return {@link #items} instance as {@link ArrayList} of {@link SearchItem}
     **/
    public ArrayList<SearchItem> getItems() {
        return items;
    }

    /**
     * The {@code SearchItem} class is useful to format a GitHub's search item
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SearchItem extends InnerClassItem {

        /**
         * {@code item} of the search item
         **/
        private final Object item;

        /**
         * {@code score} of the search item
         **/
        private final double score;

        /**
         * {@code textMatches} text matches of the search item
         **/
        private final ArrayList<TextMatch> textMatches;

        /**
         * Constructor to init a {@link SearchItem}
         *
         * @param item:       item of the search item
         * @param score       : score of the search item
         * @param textMatches : text matches of the search item
         **/
        public SearchItem(Object item, double score, ArrayList<TextMatch> textMatches) {
            super(null);
            this.item = item;
            this.score = score;
            this.textMatches = textMatches;
        }

        /**
         * Constructor to init a {@link SearchItem}
         *
         * @param itemType:   type of the result item to fetch
         * @param jSearchItem : search item details as {@link JSONObject}
         **/
        public SearchItem(Class itemType, JSONObject jSearchItem) {
            super(jSearchItem);
            score = hItem.getDouble("score", 0);
            textMatches = new ArrayList<>();
            JSONArray jTextMatches = hItem.getJSONArray("text_matches", new JSONArray());
            for (int j = 0; j < jTextMatches.length(); j++)
                textMatches.add(new TextMatch(jTextMatches.getJSONObject(j)));
            jSearchItem.remove("score");
            jSearchItem.remove("text_matches");
            String descriptor = itemType.descriptorString();
            if (descriptor.contains("Code"))
                item = new Code(jSearchItem);
            else if (descriptor.contains("GitCommit"))
                item = new GitCommit(jSearchItem);
            else if (descriptor.contains("PullRequest"))
                item = new PullRequest(jSearchItem);
            else if (descriptor.contains("Label"))
                item = new Label(jSearchItem);
            else if (descriptor.contains("Repository"))
                item = new Repository(jSearchItem);
            else if (descriptor.contains("Topic"))
                item = new Topic(jSearchItem);
            else if (descriptor.contains("User"))
                item = new User(jSearchItem);
            else
                item = null;
        }

        /**
         * Method to get {@link #item} instance <br>
         * No-any params required
         *
         * @return {@link #item} instance as {@link T}
         **/
        public <T> T getItem() {
            return (T) item;
        }

        /**
         * Method to get {@link #score} instance <br>
         * No-any params required
         *
         * @return {@link #score} instance as double
         **/
        public double getScore() {
            return score;
        }

        /**
         * Method to get {@link #score} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #score} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getScore(int decimals) {
            return roundValue(score, decimals);
        }

        /**
         * Method to get {@link #textMatches} instance <br>
         * No-any params required
         *
         * @return {@link #textMatches} instance as {@link ArrayList} of {@link TextMatch}
         **/
        public ArrayList<TextMatch> getTextMatches() {
            return textMatches;
        }

        /**
         * The {@code TextMatch} class is useful to format a GitHub's text match
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class TextMatch extends InnerClassItem {

            /**
             * {@code objectUrl} object url of the text match
             **/
            private final String objectUrl;

            /**
             * {@code objectType} object type of the text match
             **/
            private final String objectType;

            /**
             * {@code property} of the text match
             **/
            private final String property;

            /**
             * {@code fragment} of the text match
             **/
            private final String fragment;

            /**
             * {@code matches} of the text match
             **/
            private final ArrayList<Match> matches;

            /**
             * Constructor to init a {@link TextMatch}
             *
             * @param objectUrl  : object url of the text match
             * @param objectType : object type of the text match
             * @param property   : property of the text match
             * @param fragment   : fragment of the text match
             * @param matches    : matches of the text match
             **/
            public TextMatch(String objectUrl, String objectType, String property, String fragment,
                             ArrayList<Match> matches) {
                super(null);
                this.objectUrl = objectUrl;
                this.objectType = objectType;
                this.property = property;
                this.fragment = fragment;
                this.matches = matches;
            }

            /**
             * Constructor to init a {@link TextMatch}
             *
             * @param jTextMatch : text match details as {@link JSONObject}
             **/
            public TextMatch(JSONObject jTextMatch) {
                super(jTextMatch);
                objectUrl = hItem.getString("object_url");
                objectType = hItem.getString("object_type");
                property = hItem.getString("property");
                fragment = hItem.getString("fragment");
                matches = new ArrayList<>();
                JSONArray jMatches = hItem.getJSONArray("matches", new JSONArray());
                for (int j = 0; j < jMatches.length(); j++)
                    matches.add(new Match(jMatches.getJSONObject(j)));
            }

            /**
             * Method to get {@link #objectUrl} instance <br>
             * No-any params required
             *
             * @return {@link #objectUrl} instance as {@link String}
             **/
            public String getObjectUrl() {
                return objectUrl;
            }

            /**
             * Method to get {@link #objectType} instance <br>
             * No-any params required
             *
             * @return {@link #objectType} instance as {@link String}
             **/
            public String getObjectType() {
                return objectType;
            }

            /**
             * Method to get {@link #property} instance <br>
             * No-any params required
             *
             * @return {@link #property} instance as {@link String}
             **/
            public String getProperty() {
                return property;
            }

            /**
             * Method to get {@link #fragment} instance <br>
             * No-any params required
             *
             * @return {@link #fragment} instance as {@link String}
             **/
            public String getFragment() {
                return fragment;
            }

            /**
             * Method to get {@link #matches} instance <br>
             * No-any params required
             *
             * @return {@link #matches} instance as {@link ArrayList} of {@link Match}
             **/
            public ArrayList<Match> getMatches() {
                return matches;
            }

            /**
             * The {@code Match} class is useful to format a GitHub's match
             *
             * @author N7ghtm4r3 - Tecknobit
             * @see InnerClassItem
             **/
            public static class Match extends InnerClassItem {

                /**
                 * {@code text} of the match
                 **/
                private final String text;

                /**
                 * {@code indices} of the match
                 **/
                private final ArrayList<Integer> indices;

                /**
                 * Constructor to init a {@link Match}
                 *
                 * @param text    : text of the match
                 * @param indices : indices of the match
                 **/
                public Match(String text, ArrayList<Integer> indices) {
                    super(null);
                    this.text = text;
                    this.indices = indices;
                }

                /**
                 * Constructor to init a {@link Match}
                 *
                 * @param jMatch : match details as {@link JSONObject}
                 **/
                public Match(JSONObject jMatch) {
                    super(jMatch);
                    text = hItem.getString("text");
                    indices = new ArrayList<>();
                    JSONArray jIndices = hItem.getJSONArray("indices", new JSONArray());
                    for (int j = 0; j < jIndices.length(); j++)
                        indices.add(jIndices.getInt(j));
                }

                /**
                 * Method to get {@link #text} instance <br>
                 * No-any params required
                 *
                 * @return {@link #text} instance as {@link String}
                 **/
                public String getText() {
                    return text;
                }

                /**
                 * Method to get {@link #indices} instance <br>
                 * No-any params required
                 *
                 * @return {@link #indices} instance as {@link ArrayList} of {@link Integer}
                 **/
                public ArrayList<Integer> getIndices() {
                    return indices;
                }

            }

        }

    }

}
