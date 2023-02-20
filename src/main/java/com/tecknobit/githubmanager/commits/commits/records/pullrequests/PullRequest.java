package com.tecknobit.githubmanager.commits.commits.records.pullrequests;

import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.*;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

/**
 * The {@code Commit} class is useful to format a GitHub's commit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commith">
 * List pull requests associated with a commit</a>
 * @see GitHubResponse
 * @see GitHubOperationBaseStructure
 * @see GitHubOperation
 **/
public class PullRequest extends GitHubOperation {

    /**
     * {@code diffUrl} diff url of the pull request
     **/
    private final String diffUrl;

    /**
     * {@code patchUrl} patch url of the pull request
     **/
    private final String patchUrl;

    /**
     * {@code issueUrl} issue rl of the pull request
     **/
    private final String issueUrl;

    /**
     * {@code commitsUrl} commits url of the pull request
     **/
    private final String commitsUrl;

    /**
     * {@code reviewCommentsUrl} review comments url of the pull request
     **/
    private final String reviewCommentsUrl;

    /**
     * {@code reviewCommentUrl} review comment url of the pull request
     **/
    private final String reviewCommentUrl;

    /**
     * {@code commentsUrl} comments url of the pull request
     **/
    private final String commentsUrl;

    /**
     * {@code statusesUrl} statuses url of the pull request
     **/
    private final String statusesUrl;

    /**
     * {@code mergedAt} merged time of the pull request
     **/
    private final String mergedAt;

    /**
     * {@code mergeCommitSha} merge commit sha of the pull request
     **/
    private final String mergeCommitSha;

    /**
     * {@code requestedReviewers} requested reviewers of the pull request
     **/
    private final ArrayList<User> requestedReviewers;

    /**
     * {@code requestedTeams} requested teams of the pull request
     **/
    private final ArrayList<Team> requestedTeams;

    /**
     * {@code head} of the pull request
     **/
    private final PullRequestPart head;

    /**
     * {@code base} of the pull request
     **/
    private final PullRequestPart base;

    /**
     * {@code _links} links of the pull request
     **/
    private final Links _links;

    /**
     * {@code autoMerge} the status of auto merging a pull request
     **/
    private final AutoMerge autoMerge;

    /**
     * {@code draft} indicates whether the pull request is a draft
     **/
    private final boolean draft;

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param url                : url of the pull request
     * @param htmlUrl            : html url of the pull request
     * @param id                 : id of the pull request
     * @param nodeId             : node identifier of the pull request
     * @param number             : number of the pull request
     * @param state              : state of the pull request
     * @param title              : title of the pull request
     * @param createdAt          : creation time of the pull request
     * @param updatedAt          : update time of the pull request
     * @param closedAt           :  close time of the pull request
     * @param diffUrl            : diff url of the pull request
     * @param patchUrl           : patch url of the pull request
     * @param issueUrl           : issue url of the pull request
     * @param commitsUrl         : commits url of the pull request
     * @param reviewCommentsUrl  : review comments url of the pull request
     * @param reviewCommentUrl   : review comment url of the pull request
     * @param commentsUrl        : comments url of the pull request
     * @param statusesUrl        : statuses url of the pull request
     * @param locked             : whether the pull request is locked
     * @param user               : user of the pull request
     * @param body               :  body of the pull request
     * @param labels             : labels the pull request
     * @param milestone          : milestone of the pull request
     * @param activeLockReason   : active lock reason of the pull request
     * @param assignee           : assignee of the pull request
     * @param assignees          : assignees of the pull request
     * @param requestedReviewers : requested reviewers of the pull request
     * @param requestedTeams     : requested teams of the pull request
     * @param head               :  head of the pull request
     * @param base               : base of the pull request
     * @param _links             : how the author is associated with the repository
     * @param authorAssociation  : id of the pull request
     * @param draft              : indicates whether the pull request is a draft
     **/
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason, User assignee,
                       ArrayList<User> assignees, ArrayList<User> requestedReviewers, ArrayList<Team> requestedTeams,
                       PullRequestPart head, PullRequestPart base, Links _links, AuthorAssociation authorAssociation,
                       boolean draft) {
        this(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt, diffUrl, patchUrl,
                issueUrl, commitsUrl, reviewCommentsUrl, reviewCommentUrl, commentsUrl, statusesUrl, locked, user, body,
                labels, milestone, activeLockReason, null, null, assignee, assignees, requestedReviewers,
                requestedTeams, head, base, _links, authorAssociation, null, draft);
    }

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param url                : url of the pull request
     * @param htmlUrl            : html url of the pull request
     * @param id                 : id of the pull request
     * @param nodeId             : node identifier of the pull request
     * @param number             : number of the pull request
     * @param state              : state of the pull request
     * @param title              : title of the pull request
     * @param createdAt          : creation time of the pull request
     * @param updatedAt          : update time of the pull request
     * @param closedAt           :  close time of the pull request
     * @param diffUrl            : diff url of the pull request
     * @param patchUrl           : patch url of the pull request
     * @param issueUrl           : issue url of the pull request
     * @param commitsUrl         : commits url of the pull request
     * @param reviewCommentsUrl  : review comments url of the pull request
     * @param reviewCommentUrl   : review comment url of the pull request
     * @param commentsUrl        : comments url of the pull request
     * @param statusesUrl        : statuses url of the pull request
     * @param locked             : whether the pull request is locked
     * @param user               : user of the pull request
     * @param body               :  body of the pull request
     * @param labels             : labels the pull request
     * @param milestone          : milestone of the pull request
     * @param activeLockReason   : active lock reason of the pull request
     * @param mergedAt:          merged time of the pull request
     * @param mergeCommitSha     : merge commit sha of the pull request
     * @param assignee           : assignee of the pull request
     * @param assignees          : assignees of the pull request
     * @param requestedReviewers : requested reviewers of the pull request
     * @param requestedTeams     : requested teams of the pull request
     * @param head               :  head of the pull request
     * @param base               : base of the pull request
     * @param _links             : how the author is associated with the repository
     * @param authorAssociation  : id of the pull request
     * @param draft              : indicates whether the pull request is a draft
     **/
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason, String mergedAt,
                       String mergeCommitSha, User assignee, ArrayList<User> assignees, ArrayList<User> requestedReviewers,
                       ArrayList<Team> requestedTeams, PullRequestPart head, PullRequestPart base, Links _links,
                       AuthorAssociation authorAssociation, boolean draft) {
        this(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt, diffUrl, patchUrl,
                issueUrl, commitsUrl, reviewCommentsUrl, reviewCommentUrl, commentsUrl, statusesUrl, locked, user, body,
                labels, milestone, activeLockReason, mergedAt, mergeCommitSha, assignee, assignees, requestedReviewers,
                requestedTeams, head, base, _links, authorAssociation, null, draft);
    }

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param url                : url of the pull request
     * @param htmlUrl            : html url of the pull request
     * @param id                 : id of the pull request
     * @param nodeId             : node identifier of the pull request
     * @param number             : number of the pull request
     * @param state              : state of the pull request
     * @param title              : title of the pull request
     * @param createdAt          : creation time of the pull request
     * @param updatedAt          : update time of the pull request
     * @param closedAt           :  close time of the pull request
     * @param diffUrl            : diff url of the pull request
     * @param patchUrl           : patch url of the pull request
     * @param issueUrl           : issue url of the pull request
     * @param commitsUrl         : commits url of the pull request
     * @param reviewCommentsUrl  : review comments url of the pull request
     * @param reviewCommentUrl   : review comment url of the pull request
     * @param commentsUrl        : comments url of the pull request
     * @param statusesUrl        : statuses url of the pull request
     * @param locked             : whether the pull request is locked
     * @param user               : user of the pull request
     * @param body               :  body of the pull request
     * @param labels             : labels the pull request
     * @param milestone          : milestone of the pull request
     * @param activeLockReason   : active lock reason of the pull request
     * @param assignee           : assignee of the pull request
     * @param assignees          : assignees of the pull request
     * @param requestedReviewers : requested reviewers of the pull request
     * @param requestedTeams     : requested teams of the pull request
     * @param head               :  head of the pull request
     * @param base               : base of the pull request
     * @param _links             : how the author is associated with the repository
     * @param authorAssociation  : id of the pull request
     * @param autoMerge:         the status of auto merging a pull request
     * @param draft              : indicates whether the pull request is a draft
     **/
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason, User assignee,
                       ArrayList<User> assignees, ArrayList<User> requestedReviewers, ArrayList<Team> requestedTeams,
                       PullRequestPart head, PullRequestPart base, Links _links, AuthorAssociation authorAssociation,
                       AutoMerge autoMerge, boolean draft) {
        this(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt, diffUrl, patchUrl,
                issueUrl, commitsUrl, reviewCommentsUrl, reviewCommentUrl, commentsUrl, statusesUrl, locked, user, body,
                labels, milestone, activeLockReason, null, null, assignee, assignees, requestedReviewers,
                requestedTeams, head, base, _links, authorAssociation, autoMerge, draft);
    }

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param url                : url of the pull request
     * @param htmlUrl            : html url of the pull request
     * @param id                 : id of the pull request
     * @param nodeId             : node identifier of the pull request
     * @param number             : number of the pull request
     * @param state              : state of the pull request
     * @param title              : title of the pull request
     * @param createdAt          : creation time of the pull request
     * @param updatedAt          : update time of the pull request
     * @param closedAt           :  close time of the pull request
     * @param diffUrl            : diff url of the pull request
     * @param patchUrl           : patch url of the pull request
     * @param issueUrl           : issue url of the pull request
     * @param commitsUrl         : commits url of the pull request
     * @param reviewCommentsUrl  : review comments url of the pull request
     * @param reviewCommentUrl   : review comment url of the pull request
     * @param commentsUrl        : comments url of the pull request
     * @param statusesUrl        : statuses url of the pull request
     * @param locked             : whether the pull request is locked
     * @param user               : user of the pull request
     * @param body               :  body of the pull request
     * @param labels             : labels the pull request
     * @param milestone          : milestone of the pull request
     * @param activeLockReason   : active lock reason of the pull request
     * @param mergedAt:          merged time of the pull request
     * @param mergeCommitSha     : merge commit sha of the pull request
     * @param assignee           : assignee of the pull request
     * @param assignees          : assignees of the pull request
     * @param requestedReviewers : requested reviewers of the pull request
     * @param requestedTeams     : requested teams of the pull request
     * @param head               :  head of the pull request
     * @param base               : base of the pull request
     * @param _links             : how the author is associated with the repository
     * @param authorAssociation  : id of the pull request
     * @param autoMerge:         the status of auto merging a pull request
     * @param draft              : indicates whether the pull request is a draft
     **/
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason, String mergedAt,
                       String mergeCommitSha, User assignee, ArrayList<User> assignees, ArrayList<User> requestedReviewers,
                       ArrayList<Team> requestedTeams, PullRequestPart head, PullRequestPart base, Links _links,
                       AuthorAssociation authorAssociation, AutoMerge autoMerge, boolean draft) {
        super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt, locked, user, body, labels,
                milestone, activeLockReason, assignee, assignees, authorAssociation);
        this.diffUrl = diffUrl;
        this.patchUrl = patchUrl;
        this.issueUrl = issueUrl;
        this.commitsUrl = commitsUrl;
        this.reviewCommentsUrl = reviewCommentsUrl;
        this.reviewCommentUrl = reviewCommentUrl;
        this.commentsUrl = commentsUrl;
        this.statusesUrl = statusesUrl;
        this.mergedAt = mergedAt;
        this.mergeCommitSha = mergeCommitSha;
        this.requestedReviewers = requestedReviewers;
        this.requestedTeams = requestedTeams;
        this.head = head;
        this.base = base;
        this._links = _links;
        this.autoMerge = autoMerge;
        this.draft = draft;
    }

    /**
     * Constructor to init a {@link PullRequest}
     *
     * @param jPullRequest:pull request details as {@link JSONObject}
     **/
    public PullRequest(JSONObject jPullRequest) {
        super(jPullRequest);
        diffUrl = hResponse.getString("diff_url");
        patchUrl = hResponse.getString("patch_url");
        issueUrl = hResponse.getString("issue_url");
        commitsUrl = hResponse.getString("commits_url");
        reviewCommentsUrl = hResponse.getString("review_comments_url");
        reviewCommentUrl = hResponse.getString("review_comment_url");
        commentsUrl = hResponse.getString("comments_url");
        statusesUrl = hResponse.getString("statuses_url");
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new Label(jLabels.getJSONObject(j)));
        mergedAt = hResponse.getString("merged_at");
        mergeCommitSha = hResponse.getString("merge_commit_sha");
        requestedReviewers = returnUsersList(hResponse.getJSONArray("requested_reviewers"));
        requestedTeams = new ArrayList<>();
        JSONArray jRequestedTeams = hResponse.getJSONArray("requested_teams", new JSONArray());
        for (int j = 0; j < jRequestedTeams.length(); j++)
            requestedTeams.add(new Team(jRequestedTeams.getJSONObject(j)));
        head = new PullRequestPart(hResponse.getJSONObject("head", new JSONObject()));
        base = new PullRequestPart(hResponse.getJSONObject("base", new JSONObject()));
        _links = new Links(hResponse.getJSONObject("_links", new JSONObject()));
        autoMerge = new AutoMerge(hResponse.getJSONObject("auto_merge", new JSONObject()));
        draft = hResponse.getBoolean("draft");
    }

    /**
     * Method to get {@link #diffUrl} instance <br>
     * No-any params required
     *
     * @return {@link #diffUrl} instance as {@link String}
     **/
    public String getDiffUrl() {
        return diffUrl;
    }

    /**
     * Method to get {@link #patchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #patchUrl} instance as {@link String}
     **/
    public String getPatchUrl() {
        return patchUrl;
    }

    /**
     * Method to get {@link #issueUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issueUrl} instance as {@link String}
     **/
    public String getIssueUrl() {
        return issueUrl;
    }

    /**
     * Method to get {@link #commitsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitsUrl} instance as {@link String}
     **/
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Method to get {@link #reviewCommentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #reviewCommentsUrl} instance as {@link String}
     **/
    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    /**
     * Method to get {@link #reviewCommentUrl} instance <br>
     * No-any params required
     *
     * @return {@link #reviewCommentUrl} instance as {@link String}
     **/
    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    /**
     * Method to get {@link #commentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commentsUrl} instance as {@link String}
     **/
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Method to get {@link #statusesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #statusesUrl} instance as {@link String}
     **/
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * Method to get {@link #mergedAt} instance <br>
     * No-any params required
     *
     * @return {@link #mergedAt} instance as {@link String}
     **/
    public String getMergedAt() {
        return mergedAt;
    }

    /**
     * Method to get {@link #mergedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #mergedAt} timestamp as long
     **/
    public long getMergedAtTimestamp() {
        return getDateTimestamp(mergedAt);
    }

    /**
     * Method to get {@link #mergeCommitSha} instance <br>
     * No-any params required
     *
     * @return {@link #mergeCommitSha} instance as {@link String}
     **/
    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    /**
     * Method to get {@link #requestedReviewers} instance <br>
     * No-any params required
     *
     * @return {@link #requestedReviewers} instance as {@link Collection} of {@link User}
     **/
    public Collection<User> getRequestedReviewers() {
        return requestedReviewers;
    }

    /**
     * Method to get {@link #requestedTeams} instance <br>
     * No-any params required
     *
     * @return {@link #requestedTeams} instance as {@link Collection} of {@link Team}
     **/
    public Collection<Team> getRequestedTeams() {
        return requestedTeams;
    }

    /**
     * Method to get {@link #head} instance <br>
     * No-any params required
     *
     * @return {@link #head} instance as {@link PullRequestPart}
     **/
    public PullRequestPart getHead() {
        return head;
    }

    /**
     * Method to get {@link #base} instance <br>
     * No-any params required
     *
     * @return {@link #base} instance as {@link PullRequestPart}
     **/
    public PullRequestPart getBase() {
        return base;
    }

    /**
     * Method to get {@link #_links} instance <br>
     * No-any params required
     *
     * @return {@link #_links} instance as {@link Links}
     **/
    public Links getLinks() {
        return _links;
    }

    /**
     * Method to get {@link #autoMerge} instance <br>
     * No-any params required
     *
     * @return {@link #autoMerge} instance as {@link AutoMerge}
     **/
    public AutoMerge getAutoMerge() {
        return autoMerge;
    }

    /**
     * Method to get {@link #draft} instance <br>
     * No-any params required
     *
     * @return {@link #draft} instance as boolean
     **/
    public boolean isDraft() {
        return draft;
    }

    /**
     * The {@code PullRequestPart} class is useful to format a GitHub's pull request part of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class PullRequestPart extends InnerClassItem {

        /**
         * {@code label} of the pull request part
         **/
        private final String label;

        /**
         * {@code ref} of the pull request part
         **/
        private final String ref;

        /**
         * {@code sha} of the pull request part
         **/
        private final String sha;

        /**
         * {@code user} of the pull request part
         **/
        private final User user;

        /**
         * {@code repo} of the pull request part
         **/
        private final CompleteRepository repo;

        /**
         * Constructor to init a {@link PullRequestPart}
         *
         * @param label : label of the pull request
         * @param ref   : ref of the pull request
         * @param sha   : sha of the pull request
         * @param user  : user of the pull request
         * @param repo  : repo the pull request part
         **/
        public PullRequestPart(String label, String ref, String sha, User user, CompleteRepository repo) {
            super(null);
            this.label = label;
            this.ref = ref;
            this.sha = sha;
            this.user = user;
            this.repo = repo;
        }

        /**
         * Constructor to init a {@link PullRequestPart}
         *
         * @param jPullRequestPart : pull request part as details as {@link JSONObject}
         **/
        public PullRequestPart(JSONObject jPullRequestPart) {
            super(jPullRequestPart);
            label = hItem.getString("label");
            ref = hItem.getString("ref");
            sha = hItem.getString("sha");
            user = new User(hItem.getJSONObject("user", new JSONObject()));
            repo = new CompleteRepository(hItem.getJSONObject("repo", new JSONObject()));
        }

        /**
         * Method to get {@link #label} instance <br>
         * No-any params required
         *
         * @return {@link #label} instance as {@link String}
         **/
        public String getLabel() {
            return label;
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
         * Method to get {@link #sha} instance <br>
         * No-any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #user} instance <br>
         * No-any params required
         *
         * @return {@link #user} instance as {@link User}
         **/
        public User getUser() {
            return user;
        }

        /**
         * Method to get {@link #repo} instance <br>
         * No-any params required
         *
         * @return {@link #repo} instance as {@link CompleteRepository}
         **/
        public CompleteRepository getRepo() {
            return repo;
        }

    }

    /**
     * The {@code Links} class is useful to format a GitHub's links of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Links extends InnerClassItem {

        /**
         * {@code self} link
         **/
        private final String self;

        /**
         * {@code html} link
         **/
        private final String html;

        /**
         * {@code issue} link
         **/
        private final String issue;

        /**
         * {@code comments} link
         **/
        private final String comments;

        /**
         * {@code reviewComments} review comments link
         **/
        private final String reviewComments;

        /**
         * {@code reviewComment} review comment link
         **/
        private final String reviewComment;

        /**
         * {@code commits} link
         **/
        private final String commits;

        /**
         * {@code statuses} link
         **/
        private final String statuses;

        /**
         * Constructor to init a {@link Links}
         *
         * @param self           : self link
         * @param html           : html link
         * @param issue          : issue link
         * @param comments       : comments link
         * @param reviewComments : review comments link
         * @param reviewComment  : review comment link
         * @param commits        :commits link
         * @param statuses       : statuses link
         **/
        public Links(String self, String html, String issue, String comments, String reviewComments, String reviewComment,
                     String commits, String statuses) {
            super(null);
            this.self = self;
            this.html = html;
            this.issue = issue;
            this.comments = comments;
            this.reviewComments = reviewComments;
            this.reviewComment = reviewComment;
            this.commits = commits;
            this.statuses = statuses;
        }

        /**
         * Constructor to init a {@link Links}
         *
         * @param jLinks : link details as {@link JSONObject}
         **/
        public Links(JSONObject jLinks) {
            super(jLinks);
            self = hItem.getJsonHelper("self").getString("href");
            html = hItem.getJsonHelper("html").getString("href");
            issue = hItem.getJsonHelper("issue").getString("href");
            comments = hItem.getJsonHelper("comments").getString("href");
            reviewComments = hItem.getJsonHelper("review_comments").getString("href");
            reviewComment = hItem.getJsonHelper("review_comment").getString("href");
            commits = hItem.getJsonHelper("commits").getString("href");
            statuses = hItem.getJsonHelper("statuses").getString("href");
        }

        /**
         * Method to get {@link #self} instance <br>
         * No-any params required
         *
         * @return {@link #self} instance as {@link String}
         **/
        public String getSelf() {
            return self;
        }

        /**
         * Method to get {@link #html} instance <br>
         * No-any params required
         *
         * @return {@link #html} instance as {@link String}
         **/
        public String getHtml() {
            return html;
        }

        /**
         * Method to get {@link #issue} instance <br>
         * No-any params required
         *
         * @return {@link #issue} instance as {@link String}
         **/
        public String getIssue() {
            return issue;
        }

        /**
         * Method to get {@link #comments} instance <br>
         * No-any params required
         *
         * @return {@link #comments} instance as {@link String}
         **/
        public String getComments() {
            return comments;
        }

        /**
         * Method to get {@link #reviewComments} instance <br>
         * No-any params required
         *
         * @return {@link #reviewComments} instance as {@link String}
         **/
        public String getReviewComments() {
            return reviewComments;
        }

        /**
         * Method to get {@link #reviewComment} instance <br>
         * No-any params required
         *
         * @return {@link #reviewComment} instance as {@link String}
         **/
        public String getReviewComment() {
            return reviewComment;
        }

        /**
         * Method to get {@link #commits} instance <br>
         * No-any params required
         *
         * @return {@link #commits} instance as {@link String}
         **/
        public String getCommits() {
            return commits;
        }

        /**
         * Method to get {@link #statuses} instance <br>
         * No-any params required
         *
         * @return {@link #statuses} instance as {@link String}
         **/
        public String getStatuses() {
            return statuses;
        }

    }

    /**
     * The {@code AutoMerge} class is useful to format a GitHub's auto merge of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class AutoMerge extends InnerClassItem {

        /**
         * {@code enabledBy} who enabled the auto merge
         **/
        private final User enabledBy;

        /**
         * {@code mergeMethod} the merge method to use
         **/
        private final MergeMethod mergeMethod;

        /**
         * {@code commitTitle} title for the merge commit message
         **/
        private final String commitTitle;

        /**
         * {@code commitMessage} commit message for the merge commit
         **/
        private final String commitMessage;

        /**
         * Constructor to init a {@link AutoMerge}
         *
         * @param enabledBy     : who enabled the auto merge
         * @param mergeMethod   : merge the merge method to use
         * @param commitTitle   : title for the merge commit message
         * @param commitMessage : commit message for the merge commit
         **/
        public AutoMerge(User enabledBy, MergeMethod mergeMethod, String commitTitle, String commitMessage) {
            super(null);
            this.enabledBy = enabledBy;
            this.mergeMethod = mergeMethod;
            this.commitTitle = commitTitle;
            this.commitMessage = commitMessage;
        }

        /**
         * Constructor to init a {@link AutoMerge}
         *
         * @param jAutoMerge : auto merge details as {@link JSONObject}
         **/
        public AutoMerge(JSONObject jAutoMerge) {
            super(jAutoMerge);
            enabledBy = new User(hItem.getJSONObject("enabled_by", new JSONObject()));
            mergeMethod = MergeMethod.valueOf(hItem.getString("merge_method", MergeMethod.merge.name()));
            commitTitle = hItem.getString("commit_title");
            commitMessage = hItem.getString("commit_message");
        }

        /**
         * Method to get {@link #enabledBy} instance <br>
         * No-any params required
         *
         * @return {@link #enabledBy} instance as {@link User}
         **/
        public User getEnabledBy() {
            return enabledBy;
        }

        /**
         * Method to get {@link #mergeMethod} instance <br>
         * No-any params required
         *
         * @return {@link #mergeMethod} instance as {@link MergeMethod}
         **/
        public MergeMethod getMergeMethod() {
            return mergeMethod;
        }

        /**
         * Method to get {@link #commitTitle} instance <br>
         * No-any params required
         *
         * @return {@link #commitTitle} instance as {@link String}
         **/
        public String getCommitTitle() {
            return commitTitle;
        }

        /**
         * Method to get {@link #commitMessage} instance <br>
         * No-any params required
         *
         * @return {@link #commitMessage} instance as {@link String}
         **/
        public String getCommitMessage() {
            return commitMessage;
        }

        /**
         * {@code MergeMethod} list of available merge methods
         **/
        public enum MergeMethod {

            /**
             * {@code merge} merge method
             **/
            merge,

            /**
             * {@code squash} merge method
             **/
            squash,

            /**
             * {@code rebase} merge method
             **/
            rebase

        }

    }

}
