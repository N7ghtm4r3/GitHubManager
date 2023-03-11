package com.tecknobit.githubmanager.pulls.pulls.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

/**
 * The {@code MinimalPullRequest} class is useful to format a GitHub's minimal pull request
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class MinimalPullRequest {

    /**
     * {@code id} identifier value
     **/
    private final long id;

    /**
     * {@code number} number value
     **/
    private final int number;

    /**
     * {@code "url"} value
     **/
    private final String url;

    /**
     * {@code "head"} value
     **/
    private final MinimalPullRequestPart head;

    /**
     * {@code "base"} value
     **/
    private final MinimalPullRequestPart base;

    /**
     * Constructor to init a {@link MinimalPullRequest}
     *
     * @param id     : identifier value
     * @param number : number value
     * @param url:   url value
     * @param head:  head value
     * @param base:  base value
     **/
    public MinimalPullRequest(long id, int number, String url, MinimalPullRequestPart head, MinimalPullRequestPart base) {
        this.id = id;
        this.number = number;
        this.url = url;
        this.head = head;
        this.base = base;
    }

    /**
     * Constructor to init a {@link MinimalPullRequest}
     *
     * @param jPullRequest : pull request details as {@link JSONObject}
     **/
    public MinimalPullRequest(JSONObject jPullRequest) {
        JsonHelper hPullRequest = new JsonHelper(jPullRequest);
        id = hPullRequest.getLong("id", 0);
        number = hPullRequest.getInt("number", 0);
        url = hPullRequest.getString("url");
        head = new MinimalPullRequestPart(hPullRequest.getJSONObject("head", new JSONObject()));
        base = new MinimalPullRequestPart(hPullRequest.getJSONObject("base", new JSONObject()));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #number} instance <br>
     * No-any params required
     *
     * @return {@link #number} instance as int
     **/
    public int getNumber() {
        return number;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #head} instance <br>
     * No-any params required
     *
     * @return {@link #head} instance as {@link MinimalPullRequestPart}
     **/
    public MinimalPullRequestPart getHead() {
        return head;
    }

    /**
     * Method to get {@link #base} instance <br>
     * No-any params required
     *
     * @return {@link #base} instance as {@link MinimalPullRequestPart}
     **/
    public MinimalPullRequestPart getBase() {
        return base;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    /**
     * The {@code MinimalPullRequest} class is useful to format a GitHub's pull request part
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class MinimalPullRequestPart extends InnerClassItem {

        /**
         * {@code "sha"} value
         **/
        private final String sha;

        /**
         * {@code "ref"} value
         **/
        private final String ref;

        /**
         * {@code "repo"} value
         **/
        private final BaseResponseDetails repo;

        /**
         * Constructor to init a {@link MinimalPullRequestPart}
         *
         * @param sha   : sha value
         * @param ref   : ref value
         * @param repo: repo value
         **/
        public MinimalPullRequestPart(String sha, String ref, BaseResponseDetails repo) {
            super(null);
            this.sha = sha;
            this.ref = ref;
            this.repo = repo;
        }

        /**
         * Constructor to init a {@link MinimalPullRequestPart}
         *
         * @param jPullRequestPart: pull request part details as {@link JSONObject}
         **/
        public MinimalPullRequestPart(JSONObject jPullRequestPart) {
            super(jPullRequestPart);
            sha = hItem.getString("sha");
            ref = hItem.getString("ref");
            repo = new BaseResponseDetails(hItem.getJSONObject("repo", new JSONObject()));
        }

        /**
         * Method to get {@link #sha} instance <br>
         * No-any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #ref} instance <br>
         * No-any params required
         *
         * @return {@link #ref} instance as {@link String}
         **/
        public String getRef() {
            return ref;
        }

        /**
         * Method to get {@link #repo} instance <br>
         * No-any params required
         *
         * @return {@link #repo} instance as {@link BaseResponseDetails}
         **/
        public BaseResponseDetails getRepo() {
            return repo;
        }

    }

}
