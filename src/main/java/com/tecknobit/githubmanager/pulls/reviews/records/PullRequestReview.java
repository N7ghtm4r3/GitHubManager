package com.tecknobit.githubmanager.pulls.reviews.records;

import com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment.ReviewCommentLinks;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code PullRequestReview} class is useful to format a GitHub's pull request review
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
 *             List reviews for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
 *             Create a review for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
 *             Get a review for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
 *             Update a review for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
 *             Delete a pending review for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
 *             Dismiss a review for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
 *             Submit a review for a pull request</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class PullRequestReview extends GitHubResponse {

    /**
     * {@code ReviewEvent} list of available review events
     **/
    public enum ReviewEvent {

        /**
         * {@code APPROVE} review event
         **/
        APPROVE,

        /**
         * {@code REQUEST_CHANGES} review event
         **/
        REQUEST_CHANGES,

        /**
         * {@code COMMENT} review event
         **/
        COMMENT

    }

    /**
     * {@code id} unique identifier of the review
     **/
    private final long id;

    /**
     * {@code nodeId} node id of the pull request review
     **/
    private final String nodeId;

    /**
     * {@code user} of the pull request review
     **/
    private final User user;

    /**
     * {@code body} the text of the review
     **/
    private final String body;

    /**
     * {@code state} of the pull request review
     **/
    private final String state;

    /**
     * {@code htmlUrl} html url of the pull request review
     **/
    private final String htmlUrl;

    /**
     * {@code pullRequestUrl} pull request url of the pull request review
     **/
    private final String pullRequestUrl;

    /**
     * {@code links} of the pull request review
     **/
    private final ReviewCommentLinks links;

    /**
     * {@code submittedAt} submit date of the pull request review
     **/
    private final String submittedAt;

    /**
     * {@code commitId} a commit SHA for the review. If the commit object was garbage collected or forcibly deleted, then
     * it no longer exists in Git and this value will be {@code "null"}
     **/
    private final String commitId;

    /**
     * {@code bodyHtml} body html of the pull request review
     **/
    private final String bodyHtml;

    /**
     * {@code bodyText} body text of the pull request review
     **/
    private final String bodyText;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    private final AuthorAssociation authorAssociation;

    /**
     * Constructor to init a {@link PullRequestReview}
     *
     * @param id                 : unique identifier of the review
     * @param nodeId             : node id of the pull request review
     * @param user               : user of the pull request review
     * @param body               : the text of the review
     * @param state              : state of the pull request review
     * @param htmlUrl            : html url of the pull request review
     * @param pullRequestUrl:    pull request url of the pull request review
     * @param links:             links of the pull request review
     * @param submittedAt:       submit date of the pull request review
     * @param commitId:          a commit SHA for the review. If the commit object was garbage collected or forcibly deleted, then
     *                           it no longer exists in Git and this value will be {@code "null"}
     * @param bodyHtml           : body html of the pull request review
     * @param bodyText           : body text of the pull request review
     * @param authorAssociation: how the author is associated with the repository
     **/
    public PullRequestReview(long id, String nodeId, User user, String body, String state, String htmlUrl,
                             String pullRequestUrl, ReviewCommentLinks links, String submittedAt, String commitId,
                             String bodyHtml, String bodyText, AuthorAssociation authorAssociation) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.user = user;
        this.body = body;
        this.state = state;
        this.htmlUrl = htmlUrl;
        this.pullRequestUrl = pullRequestUrl;
        this.links = links;
        this.submittedAt = submittedAt;
        this.commitId = commitId;
        this.bodyHtml = bodyHtml;
        this.bodyText = bodyText;
        this.authorAssociation = authorAssociation;
    }

    /**
     * Constructor to init a {@link PullRequestReview}
     *
     * @param jPullRequestReview: pull request review details as {@link JSONObject}
     **/
    public PullRequestReview(JSONObject jPullRequestReview) {
        super(jPullRequestReview);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        user = new User(hResponse.getJSONObject("user"));
        body = hResponse.getString("body");
        state = hResponse.getString("state");
        htmlUrl = hResponse.getString("html_url");
        pullRequestUrl = hResponse.getString("pull_request_url");
        links = new ReviewCommentLinks(hResponse.getJSONObject("_links"));
        submittedAt = hResponse.getString("submitted_at");
        commitId = hResponse.getString("commit_id");
        bodyHtml = hResponse.getString("body_html");
        bodyText = hResponse.getString("body_text");
        authorAssociation = AuthorAssociation.valueOf(hResponse.getString("author_association"));
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
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
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
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link String}
     **/
    public String getState() {
        return state;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #pullRequestUrl} instance <br>
     * No-any params required
     *
     * @return {@link #pullRequestUrl} instance as {@link String}
     **/
    public String getPullRequestUrl() {
        return pullRequestUrl;
    }

    /**
     * Method to get {@link #links} instance <br>
     * No-any params required
     *
     * @return {@link #links} instance as {@link ReviewCommentLinks}
     **/
    public ReviewCommentLinks getLinks() {
        return links;
    }

    /**
     * Method to get {@link #submittedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #submittedAt} timestamp as {@link String}
     **/
    public String getSubmittedAt() {
        return submittedAt;
    }

    /**
     * Method to get {@link #submittedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #submittedAt} timestamp as long
     **/
    public long getSubmittedAtTimestamp() {
        return getDateTimestamp(submittedAt);
    }

    /**
     * Method to get {@link #commitId} instance <br>
     * No-any params required
     *
     * @return {@link #commitId} instance as {@link String}
     **/
    public String getCommitId() {
        return commitId;
    }

    /**
     * Method to get {@link #bodyHtml} instance <br>
     * No-any params required
     *
     * @return {@link #bodyHtml} instance as {@link String}
     **/
    public String getBodyHtml() {
        return bodyHtml;
    }

    /**
     * Method to get {@link #bodyText} instance <br>
     * No-any params required
     *
     * @return {@link #bodyText} instance as {@link String}
     **/
    public String getBodyText() {
        return bodyText;
    }

    /**
     * Method to get {@link #authorAssociation} instance <br>
     * No-any params required
     *
     * @return {@link #authorAssociation} instance as {@link AuthorAssociation}
     **/
    public AuthorAssociation getAuthorAssociation() {
        return authorAssociation;
    }

}
