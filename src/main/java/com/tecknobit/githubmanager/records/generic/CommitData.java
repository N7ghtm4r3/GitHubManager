package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.apimanager.formatters.TimeFormatter;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

/**
 * The {@code CommitData} class is useful to format a GitHub's commit data
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public abstract class CommitData extends GitHubResponse {

    /**
     * {@code author} of the commit
     **/
    protected final CommitProfile author;

    /**
     * {@code committer} of the commit
     **/
    protected final CommitProfile committer;

    /**
     * {@code tree} of the commit
     **/
    protected final ShaItem tree;

    /**
     * {@code url} of the commit
     **/
    protected final String url;

    /**
     * {@code verification} of the commit
     **/
    protected final Verification verification;

    /**
     * Constructor to init a {@link CommitData}
     *
     * @param author       : author of the commit
     * @param committer    : committer of the commit
     * @param message      : message of the commit
     * @param tree         : tree of the commit
     * @param url          : url of the commit
     * @param verification : verification of the commit
     **/
    public CommitData(CommitProfile author, CommitProfile committer, String message, ShaItem tree, String url,
                      Verification verification) {
        super(null);
        this.author = author;
        this.committer = committer;
        this.message = message;
        this.tree = tree;
        this.url = url;
        this.verification = verification;
    }

    /**
     * Constructor to init a {@link CommitData}
     *
     * @param jCommitData : commit details as {@link JSONObject}
     **/
    public CommitData(JSONObject jCommitData) {
        super(jCommitData);
        author = new CommitProfile(hResponse.getJSONObject("author", new JSONObject()));
        committer = new CommitProfile(hResponse.getJSONObject("committer", new JSONObject()));
        tree = new ShaItem(hResponse.getJSONObject("tree", new JSONObject()));
        url = hResponse.getString("url");
        verification = new Verification(hResponse.getJSONObject("verification", new JSONObject()));
    }

    /**
     * Method to get {@link #author} instance <br>
     * No-any params required
     *
     * @return {@link #author} instance as {@link CommitProfile}
     **/
    public CommitProfile getAuthor() {
        return author;
    }

    /**
     * Method to get {@link #committer} instance <br>
     * No-any params required
     *
     * @return {@link #committer} instance as {@link CommitProfile}
     **/
    public CommitProfile getCommitter() {
        return committer;
    }

    /**
     * Method to get {@link #tree} instance <br>
     * No-any params required
     *
     * @return {@link #tree} instance as {@link ShaItem}
     **/
    public ShaItem getShaItem() {
        return tree;
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
     * Method to get {@link #verification} instance <br>
     * No-any params required
     *
     * @return {@link #verification} instance as {@link Verification}
     **/
    public Verification getVerification() {
        return verification;
    }

    /**
     * The {@code CommitProfile} class is useful to format a GitHub's commit profile for {@link CommitData}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class CommitProfile extends InnerClassItem {

        /**
         * {@code name} of the profile
         **/
        protected final String name;

        /**
         * {@code email} of the profile
         **/
        protected final String email;

        /**
         * {@code date} of the committing
         **/
        protected final String date;

        /**
         * Constructor to init a {@link CommitProfile}
         *
         * @param name  : name of the profile
         * @param email : email of the profile
         * @param date  : date of the committing
         **/
        public CommitProfile(String name, String email, String date) {
            super(null);
            this.name = name;
            this.email = email;
            this.date = date;
        }

        /**
         * Constructor to init a {@link CommitProfile}
         *
         * @param jCommitProfile : commit profile as {@link JSONObject}
         **/
        public CommitProfile(JSONObject jCommitProfile) {
            super(jCommitProfile);
            name = hItem.getString("name");
            email = hItem.getString("email");
            date = hItem.getString("date");
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
         * Method to get {@link #email} instance <br>
         * No-any params required
         *
         * @return {@link #email} instance as {@link String}
         **/
        public String getEmail() {
            return email;
        }

        /**
         * Method to get {@link #date} instance <br>
         * No-any params required
         *
         * @return {@link #date} instance as {@link String}
         **/
        public String getDate() {
            return date;
        }

        /**
         * Method to get {@link #date} timestamp <br>
         * No-any params required
         *
         * @return {@link #date} timestamp as long
         **/
        public long getDateTimestamp() {
            return TimeFormatter.getDateTimestamp(date);
        }

    }

    /**
     * The {@code Verification} class is useful to format a GitHub's verification for {@link CommitData}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Verification extends InnerClassItem {

        /**
         * {@code verified} whether the commit is verified
         **/
        protected final boolean verified;

        /**
         * {@code reason} of the verification
         **/
        protected final String reason;

        /**
         * {@code signature} of the verification
         **/
        protected final String signature;

        /**
         * {@code payload} of the verification
         **/
        protected final String payload;

        /**
         * Constructor to init a {@link Verification}
         *
         * @param verified  : verified of the verification
         * @param reason    : reason of the verification
         * @param signature : signature of the verification
         * @param payload   : payload of the verification
         **/
        public Verification(boolean verified, String reason, String signature, String payload) {
            super(null);
            this.verified = verified;
            this.reason = reason;
            this.signature = signature;
            this.payload = payload;
        }

        /**
         * Constructor to init a {@link Verification}
         *
         * @param jVerification : verification details as {@link JSONObject}
         **/
        public Verification(JSONObject jVerification) {
            super(jVerification);
            verified = hItem.getBoolean("verified");
            reason = hItem.getString("reason");
            signature = hItem.getString("signature");
            payload = hItem.getString("payload");
        }

        /**
         * Method to get {@link #verified} instance <br>
         * No-any params required
         *
         * @return {@link #verified} instance as boolean
         **/
        public boolean isVerified() {
            return verified;
        }

        /**
         * Method to get {@link #reason} instance <br>
         * No-any params required
         *
         * @return {@link #reason} instance as {@link String}
         **/
        public String getReason() {
            return reason;
        }

        /**
         * Method to get {@link #signature} instance <br>
         * No-any params required
         *
         * @return {@link #signature} instance as {@link String}
         **/
        public String getSignature() {
            return signature;
        }

        /**
         * Method to get {@link #payload} instance <br>
         * No-any params required
         *
         * @return {@link #payload} instance as {@link String}
         **/
        public String getPayload() {
            return payload;
        }

    }

}