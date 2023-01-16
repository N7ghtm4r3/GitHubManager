package com.tecknobit.githubmanager.commits.commits.records.pullrequests;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
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
 * @see PullRequestStructure
 **/
public class PullRequest extends PullRequestStructure {

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
     * {@code locked} whether the pull request is locked
     **/
    private final boolean locked;

    /**
     * {@code user} of the pull request
     **/
    private final User user;

    /**
     * {@code body} of the pull request
     **/
    private final String body;

    /**
     * {@code labels} of the pull request
     **/
    private final ArrayList<Label> labels;

    /**
     * {@code milestone} of the pull request
     **/
    private final Milestone milestone;

    /**
     * {@code activeLockReason} active lock reason of the pull request
     **/
    private final String activeLockReason;

    /**
     * {@code mergedAt} merged time of the pull request
     **/
    private final String mergedAt;

    /**
     * {@code mergeCommitSha} merge commit sha of the pull request
     **/
    private final String mergeCommitSha;

    /**
     * {@code assignee} of the pull request
     **/
    private final User assignee;

    /**
     * {@code assignees} of the pull request
     **/
    private final ArrayList<User> assignees;

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
     * {@code authorAssociation} how the author is associated with the repository
     **/
    private final AuthorAssociation authorAssociation;

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
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, String activeLockReason, User assignee,
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
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, String activeLockReason, String mergedAt,
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
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, String activeLockReason, User assignee,
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
    public PullRequest(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                       String title, String createdAt, String updatedAt, String closedAt, String diffUrl, String patchUrl,
                       String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl,
                       String commentsUrl, String statusesUrl, boolean locked, User user, String body,
                       ArrayList<Label> labels, Milestone milestone, String activeLockReason, String mergedAt,
                       String mergeCommitSha, User assignee, ArrayList<User> assignees, ArrayList<User> requestedReviewers,
                       ArrayList<Team> requestedTeams, PullRequestPart head, PullRequestPart base, Links _links,
                       AuthorAssociation authorAssociation, AutoMerge autoMerge, boolean draft) {
        super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt);
        this.diffUrl = diffUrl;
        this.patchUrl = patchUrl;
        this.issueUrl = issueUrl;
        this.commitsUrl = commitsUrl;
        this.reviewCommentsUrl = reviewCommentsUrl;
        this.reviewCommentUrl = reviewCommentUrl;
        this.commentsUrl = commentsUrl;
        this.statusesUrl = statusesUrl;
        this.locked = locked;
        this.user = user;
        this.body = body;
        this.labels = labels;
        this.milestone = milestone;
        this.activeLockReason = activeLockReason;
        this.mergedAt = mergedAt;
        this.mergeCommitSha = mergeCommitSha;
        this.assignee = assignee;
        this.assignees = assignees;
        this.requestedReviewers = requestedReviewers;
        this.requestedTeams = requestedTeams;
        this.head = head;
        this.base = base;
        this._links = _links;
        this.authorAssociation = authorAssociation;
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
        locked = hResponse.getBoolean("locked");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        body = hResponse.getString("body");
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new Label(jLabels.getJSONObject(j)));
        milestone = new Milestone(hResponse.getJSONObject("milestone", new JSONObject()));
        activeLockReason = hResponse.getString("active_lock_reason");
        mergedAt = hResponse.getString("merged_at");
        mergeCommitSha = hResponse.getString("merge_commit_sha");
        assignee = new User(hResponse.getJSONObject("assignee", new JSONObject()));
        assignees = returnUsersList(hResponse.getJSONArray("assignees"));
        requestedReviewers = returnUsersList(hResponse.getJSONArray("requested_reviewers"));
        requestedTeams = new ArrayList<>();
        JSONArray jRequestedTeams = hResponse.getJSONArray("requested_teams", new JSONArray());
        for (int j = 0; j < jRequestedTeams.length(); j++)
            requestedTeams.add(new Team(jRequestedTeams.getJSONObject(j)));
        head = new PullRequestPart(hResponse.getJSONObject("head", new JSONObject()));
        base = new PullRequestPart(hResponse.getJSONObject("base", new JSONObject()));
        _links = new Links(hResponse.getJSONObject("_links", new JSONObject()));
        authorAssociation = AuthorAssociation.valueOf(hResponse.getString("author_association"));
        autoMerge = new AutoMerge(hResponse.getJSONObject("", new JSONObject()));
        draft = hResponse.getBoolean("draft");
    }

    /**
     * Method to get {@link #diffUrl} instance <br>
     * Any params required
     *
     * @return {@link #diffUrl} instance as {@link String}
     **/
    public String getDiffUrl() {
        return diffUrl;
    }

    /**
     * Method to get {@link #patchUrl} instance <br>
     * Any params required
     *
     * @return {@link #patchUrl} instance as {@link String}
     **/
    public String getPatchUrl() {
        return patchUrl;
    }

    /**
     * Method to get {@link #issueUrl} instance <br>
     * Any params required
     *
     * @return {@link #issueUrl} instance as {@link String}
     **/
    public String getIssueUrl() {
        return issueUrl;
    }

    /**
     * Method to get {@link #commitsUrl} instance <br>
     * Any params required
     *
     * @return {@link #commitsUrl} instance as {@link String}
     **/
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Method to get {@link #reviewCommentsUrl} instance <br>
     * Any params required
     *
     * @return {@link #reviewCommentsUrl} instance as {@link String}
     **/
    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    /**
     * Method to get {@link #reviewCommentUrl} instance <br>
     * Any params required
     *
     * @return {@link #reviewCommentUrl} instance as {@link String}
     **/
    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    /**
     * Method to get {@link #commentsUrl} instance <br>
     * Any params required
     *
     * @return {@link #commentsUrl} instance as {@link String}
     **/
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Method to get {@link #statusesUrl} instance <br>
     * Any params required
     *
     * @return {@link #statusesUrl} instance as {@link String}
     **/
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * Method to get {@link #locked} instance <br>
     * Any params required
     *
     * @return {@link #locked} instance as boolean
     **/
    public boolean isLocked() {
        return locked;
    }

    /**
     * Method to get {@link #user} instance <br>
     * Any params required
     *
     * @return {@link #user} instance as {@link User}
     **/
    public User getUser() {
        return user;
    }

    /**
     * Method to get {@link #body} instance <br>
     * Any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #labels} instance <br>
     * Any params required
     *
     * @return {@link #labels} instance as {@link Collection} of {@link Label}
     **/
    public Collection<Label> getLabels() {
        return labels;
    }

    /**
     * Method to get {@link #milestone} instance <br>
     * Any params required
     *
     * @return {@link #milestone} instance as {@link Milestone}
     **/
    public Milestone getMilestone() {
        return milestone;
    }

    /**
     * Method to get {@link #activeLockReason} instance <br>
     * Any params required
     *
     * @return {@link #activeLockReason} instance as {@link String}
     **/
    public String getActiveLockReason() {
        return activeLockReason;
    }

    /**
     * Method to get {@link #mergedAt} instance <br>
     * Any params required
     *
     * @return {@link #mergedAt} instance as {@link String}
     **/
    public String getMergedAt() {
        return mergedAt;
    }

    /**
     * Method to get {@link #mergedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #mergedAt} timestamp as long
     **/
    public long getMergedAtTimestamp() {
        return getDateTimestamp(mergedAt);
    }

    /**
     * Method to get {@link #mergeCommitSha} instance <br>
     * Any params required
     *
     * @return {@link #mergeCommitSha} instance as {@link String}
     **/
    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    /**
     * Method to get {@link #assignee} instance <br>
     * Any params required
     *
     * @return {@link #assignee} instance as {@link User}
     **/
    public User getAssignee() {
        return assignee;
    }

    /**
     * Method to get {@link #assignees} instance <br>
     * Any params required
     *
     * @return {@link #assignees} instance as {@link Collection} of {@link User}
     **/
    public Collection<User> getAssignees() {
        return assignees;
    }

    /**
     * Method to get {@link #requestedReviewers} instance <br>
     * Any params required
     *
     * @return {@link #requestedReviewers} instance as {@link Collection} of {@link User}
     **/
    public Collection<User> getRequestedReviewers() {
        return requestedReviewers;
    }

    /**
     * Method to get {@link #requestedTeams} instance <br>
     * Any params required
     *
     * @return {@link #requestedTeams} instance as {@link Collection} of {@link Team}
     **/
    public Collection<Team> getRequestedTeams() {
        return requestedTeams;
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
     * Method to get {@link #_links} instance <br>
     * Any params required
     *
     * @return {@link #_links} instance as {@link Links}
     **/
    public Links getLinks() {
        return _links;
    }

    /**
     * Method to get {@link #authorAssociation} instance <br>
     * Any params required
     *
     * @return {@link #authorAssociation} instance as {@link AuthorAssociation}
     **/
    public AuthorAssociation getAuthorAssociation() {
        return authorAssociation;
    }

    /**
     * Method to get {@link #autoMerge} instance <br>
     * Any params required
     *
     * @return {@link #autoMerge} instance as {@link AutoMerge}
     **/
    public AutoMerge getAutoMerge() {
        return autoMerge;
    }

    /**
     * Method to get {@link #draft} instance <br>
     * Any params required
     *
     * @return {@link #draft} instance as boolean
     **/
    public boolean isDraft() {
        return draft;
    }

    /**
     * {@code AuthorAssociation} list of available author associations
     **/
    public enum AuthorAssociation {

        /**
         * {@code COLLABORATOR} author association
         **/
        COLLABORATOR,

        /**
         * {@code CONTRIBUTOR} author association
         **/
        CONTRIBUTOR,

        /**
         * {@code FIRST_TIMER} author association
         **/
        FIRST_TIMER,

        /**
         * {@code FIRST_TIME_CONTRIBUTOR} author association
         **/
        FIRST_TIME_CONTRIBUTOR,

        /**
         * {@code MANNEQUIN} author association
         **/
        MANNEQUIN,

        /**
         * {@code MEMBER} author association
         **/
        MEMBER,

        /**
         * {@code NONE} author association
         **/
        NONE,

        /**
         * {@code OWNER} author association
         **/
        OWNER

    }

    /**
     * The {@code Label} class is useful to format a GitHub's label
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see BaseResponseDetails
     **/
    public static class Label extends BaseResponseDetails {

        /**
         * {@code nodeId} node identifier of the label
         **/
        private final String nodeId;

        /**
         * {@code description} of the label
         **/
        private final String description;

        /**
         * {@code color} of the label
         **/
        private final String color;

        /**
         * {@code isDefault} whether the label is a default label
         **/
        private final boolean isDefault;

        /**
         * Constructor to init a {@link Label}
         *
         * @param id          : identifier value
         * @param name        : the name of the item
         * @param url         : url value
         * @param nodeId      : node identifier value
         * @param description : description of the label
         * @param color       : color of the label
         * @param isDefault   : whether the label is a default label
         **/
        public Label(long id, String name, String url, String nodeId, String description, String color, boolean isDefault) {
            super(id, name, url);
            this.nodeId = nodeId;
            this.description = description;
            this.color = color;
            this.isDefault = isDefault;
        }

        /**
         * Constructor to init a {@link Label}
         *
         * @param jLabel : label details as {@link JSONObject}
         **/
        public Label(JSONObject jLabel) {
            super(jLabel);
            nodeId = hResponse.getString("node_id");
            description = hResponse.getString("description");
            color = hResponse.getString("color");
            isDefault = hResponse.getBoolean("default");
        }

        /**
         * Method to get {@link #nodeId} instance <br>
         * Any params required
         *
         * @return {@link #nodeId} instance as {@link String}
         **/
        public String getNodeId() {
            return nodeId;
        }

        /**
         * Method to get {@link #description} instance <br>
         * Any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #color} instance <br>
         * Any params required
         *
         * @return {@link #color} instance as {@link String}
         **/
        public String getColor() {
            return color;
        }

        /**
         * Method to get {@link #isDefault} instance <br>
         * Any params required
         *
         * @return {@link #isDefault} instance as {@link String}
         **/
        public boolean isDefault() {
            return isDefault;
        }

    }

    /**
     * The {@code Milestone} class is useful to format a GitHub's milestone
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see PullRequestStructure
     **/
    public static class Milestone extends PullRequestStructure {

        /**
         * {@code labelsUrl} labels url of the milestone
         **/
        private final String labelsUrl;

        /**
         * {@code description} of the milestone
         **/
        private final String description;

        /**
         * {@code creator} of the milestone
         **/
        private final User creator;

        /**
         * {@code openIssues} open issues
         **/
        private final int openIssues;

        /**
         * {@code openIssues} closed issues
         **/
        private final int closedIssues;

        /**
         * {@code dueOn} due on date of the milestone
         **/
        private final String dueOn;

        /**
         * Constructor to init a {@link Milestone}
         *
         * @param url          : url of the pull request
         * @param htmlUrl      : html url of the pull request
         * @param id           : id of the pull request
         * @param nodeId       : node identifier of the pull request
         * @param number       : number of the pull request
         * @param state        : state of the pull request
         * @param title        : title of the pull request
         * @param createdAt    : creation time of the pull request
         * @param updatedAt    : update time of the pull request
         * @param closedAt     :  close time of the pull request
         * @param labelsUrl    : labels url of the milestone
         * @param description  : description of the milestone
         * @param creator      : creator of the milestone
         * @param openIssues   : open issues
         * @param closedIssues : closed issues
         * @param dueOn        : due on date of the milestone
         **/
        public Milestone(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                         String title, String createdAt, String updatedAt, String closedAt, String labelsUrl,
                         String description, User creator, int openIssues, int closedIssues, String dueOn) {
            super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt);
            this.labelsUrl = labelsUrl;
            this.description = description;
            this.creator = creator;
            this.openIssues = openIssues;
            this.closedIssues = closedIssues;
            this.dueOn = dueOn;
        }

        /**
         * Constructor to init a {@link Milestone}
         *
         * @param jMilestone : milestone details as {@link JSONObject}
         **/
        public Milestone(JSONObject jMilestone) {
            super(jMilestone);
            labelsUrl = hResponse.getString("labels_url");
            description = hResponse.getString("description");
            creator = new User(hResponse.getJSONObject("creator", new JSONObject()));
            openIssues = hResponse.getInt("open_issues", 0);
            closedIssues = hResponse.getInt("closed_issues", 0);
            dueOn = hResponse.getString("due_on");
        }

        /**
         * Method to get {@link #labelsUrl} instance <br>
         * Any params required
         *
         * @return {@link #labelsUrl} instance as {@link String}
         **/
        public String getLabelsUrl() {
            return labelsUrl;
        }

        /**
         * Method to get {@link #description} instance <br>
         * Any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #creator} instance <br>
         * Any params required
         *
         * @return {@link #creator} instance as {@link User}
         **/
        public User getCreator() {
            return creator;
        }

        /**
         * Method to get {@link #openIssues} instance <br>
         * Any params required
         *
         * @return {@link #openIssues} instance as int
         **/
        public int getOpenIssues() {
            return openIssues;
        }

        /**
         * Method to get {@link #closedIssues} instance <br>
         * Any params required
         *
         * @return {@link #closedIssues} instance as int
         **/
        public int getClosedIssues() {
            return closedIssues;
        }

        /**
         * Method to get {@link #dueOn} instance <br>
         * Any params required
         *
         * @return {@link #dueOn} instance as {@link String}
         **/
        public String getDueOn() {
            return dueOn;
        }

        /**
         * Method to get {@link #dueOn} timestamp <br>
         * Any params required
         *
         * @return {@link #dueOn} timestamp as long
         **/
        public long getDueOnTimestamp() {
            return getDateTimestamp(dueOn);
        }

    }

    /**
     * The {@code PullRequestPart} class is useful to format a GitHub's pull request part of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class PullRequestPart {

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
            JsonHelper hPullRequestPart = new JsonHelper(jPullRequestPart);
            label = hPullRequestPart.getString("label");
            ref = hPullRequestPart.getString("ref");
            sha = hPullRequestPart.getString("sha");
            user = new User(hPullRequestPart.getJSONObject("user", new JSONObject()));
            repo = new CompleteRepository(hPullRequestPart.getJSONObject("repo", new JSONObject()));
        }

        /**
         * Method to get {@link #label} instance <br>
         * Any params required
         *
         * @return {@link #label} instance as {@link String}
         **/
        public String getLabel() {
            return label;
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
         * Method to get {@link #sha} instance <br>
         * Any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #user} instance <br>
         * Any params required
         *
         * @return {@link #user} instance as {@link User}
         **/
        public User getUser() {
            return user;
        }

        /**
         * Method to get {@link #repo} instance <br>
         * Any params required
         *
         * @return {@link #repo} instance as {@link CompleteRepository}
         **/
        public CompleteRepository getRepo() {
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

    /**
     * The {@code Links} class is useful to format a GitHub's links of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Links {

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
            JsonHelper hLinks = new JsonHelper(jLinks);
            self = hLinks.getJsonHelper("self").getString("href");
            html = hLinks.getJsonHelper("html").getString("href");
            issue = hLinks.getJsonHelper("issue").getString("href");
            comments = hLinks.getJsonHelper("comments").getString("href");
            reviewComments = hLinks.getJsonHelper("review_comments").getString("href");
            reviewComment = hLinks.getJsonHelper("review_comment").getString("href");
            commits = hLinks.getJsonHelper("commits").getString("href");
            statuses = hLinks.getJsonHelper("statuses").getString("href");
        }

        /**
         * Method to get {@link #self} instance <br>
         * Any params required
         *
         * @return {@link #self} instance as {@link String}
         **/
        public String getSelf() {
            return self;
        }

        /**
         * Method to get {@link #html} instance <br>
         * Any params required
         *
         * @return {@link #html} instance as {@link String}
         **/
        public String getHtml() {
            return html;
        }

        /**
         * Method to get {@link #issue} instance <br>
         * Any params required
         *
         * @return {@link #issue} instance as {@link String}
         **/
        public String getIssue() {
            return issue;
        }

        /**
         * Method to get {@link #comments} instance <br>
         * Any params required
         *
         * @return {@link #comments} instance as {@link String}
         **/
        public String getComments() {
            return comments;
        }

        /**
         * Method to get {@link #reviewComments} instance <br>
         * Any params required
         *
         * @return {@link #reviewComments} instance as {@link String}
         **/
        public String getReviewComments() {
            return reviewComments;
        }

        /**
         * Method to get {@link #reviewComment} instance <br>
         * Any params required
         *
         * @return {@link #reviewComment} instance as {@link String}
         **/
        public String getReviewComment() {
            return reviewComment;
        }

        /**
         * Method to get {@link #commits} instance <br>
         * Any params required
         *
         * @return {@link #commits} instance as {@link String}
         **/
        public String getCommits() {
            return commits;
        }

        /**
         * Method to get {@link #statuses} instance <br>
         * Any params required
         *
         * @return {@link #statuses} instance as {@link String}
         **/
        public String getStatuses() {
            return statuses;
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

    /**
     * The {@code AutoMerge} class is useful to format a GitHub's auto merge of a {@link PullRequest}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class AutoMerge {

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
            JsonHelper hAutoMerge = new JsonHelper(jAutoMerge);
            enabledBy = new User(hAutoMerge.getJSONObject("enabled_by", new JSONObject()));
            mergeMethod = MergeMethod.valueOf(hAutoMerge.getString("merge_method"));
            commitTitle = hAutoMerge.getString("commit_title");
            commitMessage = hAutoMerge.getString("commit_message");
        }

        /**
         * Method to get {@link #enabledBy} instance <br>
         * Any params required
         *
         * @return {@link #enabledBy} instance as {@link User}
         **/
        public User getEnabledBy() {
            return enabledBy;
        }

        /**
         * Method to get {@link #mergeMethod} instance <br>
         * Any params required
         *
         * @return {@link #mergeMethod} instance as {@link MergeMethod}
         **/
        public MergeMethod getMergeMethod() {
            return mergeMethod;
        }

        /**
         * Method to get {@link #commitTitle} instance <br>
         * Any params required
         *
         * @return {@link #commitTitle} instance as {@link String}
         **/
        public String getCommitTitle() {
            return commitTitle;
        }

        /**
         * Method to get {@link #commitMessage} instance <br>
         * Any params required
         *
         * @return {@link #commitMessage} instance as {@link String}
         **/
        public String getCommitMessage() {
            return commitMessage;
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
