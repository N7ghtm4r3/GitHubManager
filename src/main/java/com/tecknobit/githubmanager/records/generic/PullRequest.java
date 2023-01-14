package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import org.json.JSONObject;

/**
 * The {@code PullRequest} class is useful to format a GitHub's pull request
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class PullRequest {

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
    private final PullRequestPart head;

    /**
     * {@code "base"} value
     **/
    private final PullRequestPart base;

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param id     : identifier value
     * @param number : number value
     * @param url:   url value
     * @param head:  head value
     * @param base:  base value
     **/
    public PullRequest(long id, int number, String url, PullRequestPart head, PullRequestPart base) {
        this.id = id;
        this.number = number;
        this.url = url;
        this.head = head;
        this.base = base;
    }

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param jPullRequest : pull request details as {@link JSONObject}
     **/
    public PullRequest(JSONObject jPullRequest) {
        JsonHelper hPullRequest = new JsonHelper(jPullRequest);
        id = hPullRequest.getLong("id", 0);
        number = hPullRequest.getInt("number", 0);
        url = hPullRequest.getString("url");
        head = new PullRequestPart(hPullRequest.getJSONObject("head", new JSONObject()));
        base = new PullRequestPart(hPullRequest.getJSONObject("base", new JSONObject()));
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #number} instance <br>
     * Any params required
     *
     * @return {@link #number} instance as int
     **/
    public int getNumber() {
        return number;
    }

    /**
     * Method to get {@link #url} instance <br>
     * Any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #head} instance <br>
     * Any params required
     *
     * @return {@link #head} instance as {@link PullRequestPart}
     **/
    public PullRequestPart getHead() {
        return head;
    }

    /**
     * Method to get {@link #base} instance <br>
     * Any params required
     *
     * @return {@link #base} instance as {@link PullRequestPart}
     **/
    public PullRequestPart getBase() {
        return base;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    /**
     * The {@code PullRequest} class is useful to format a GitHub's pull request part
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class PullRequestPart {

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
         * Constructor to init a {@link PullRequestPart}
         *
         * @param sha   : sha value
         * @param ref   : ref value
         * @param repo: repo value
         **/
        public PullRequestPart(String sha, String ref, BaseResponseDetails repo) {
            this.sha = sha;
            this.ref = ref;
            this.repo = repo;
        }

        /**
         * Constructor to init a {@link PullRequestPart}
         *
         * @param jPullRequestPart: pull request part details as {@link JSONObject}
         **/
        public PullRequestPart(JSONObject jPullRequestPart) {
            JsonHelper hHead = new JsonHelper(jPullRequestPart);
            sha = hHead.getString("sha");
            ref = hHead.getString("ref");
            repo = new BaseResponseDetails(hHead.getJSONObject("repo", new JSONObject()));
        }

        /**
         * Method to get {@link #sha} instance <br>
         * Any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #ref} instance <br>
         * Any params required
         *
         * @return {@link #ref} instance as {@link String}
         **/
        public String getRef() {
            return ref;
        }

        /**
         * Method to get {@link #repo} instance <br>
         * Any params required
         *
         * @return {@link #repo} instance as {@link BaseResponseDetails}
         **/
        public BaseResponseDetails getRepo() {
            return repo;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}