package com.tecknobit.githubmanager.pulls.reviewcomments.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment;
import com.tecknobit.githubmanager.records.generic.GitHubComment;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import static com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment.CommitCommentReactions;

/**
 * The {@code ReviewComment} class is useful to format a GitHub's review comment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-in-a-repository">
 *             List review comments in a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#get-a-review-comment-for-a-pull-request">
 *             Get a review comment for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
 *             Update a review comment for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
 *             List review comments on a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
 *             Create a review comment for a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/comments#create-a-reply-for-a-review-comment">
 *             Create a reply for a review comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
 *             List comments for a pull request review</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubComment
 **/
public class ReviewComment extends GitHubComment {

    /**
     * {@code Side} list of available sides
     **/
    public enum Side {

        /**
         * {@code LEFT} side
         **/
        LEFT,

        /**
         * {@code RIGHT} side
         **/
        RIGHT

    }

    /**
     * {@code pullRequestReviewId} the ID of the pull request review to which the comment belongs
     **/
    private final long pullRequestReviewId;

    /**
     * {@code diffHunk} the diff of the line that the comment refers to
     **/
    private final String diffHunk;

    /**
     * {@code path} the relative path of the file to which the comment applies
     **/
    private final String path;

    /**
     * {@code commitId} the SHA of the commit to which the comment applies
     **/
    private final String commitId;

    /**
     * {@code originalCommitId} the SHA of the original commit to which the comment applies
     **/
    private final String originalCommitId;

    /**
     * {@code inReplyToId} the comment ID to reply to
     **/
    private final long inReplyToId;

    /**
     * {@code htmlUrl} HTML URL for the pull request review comment
     **/
    private final String htmlUrl;

    /**
     * {@code pullRequestUrl} URL for the pull request that the review comment belongs to
     **/
    private final String pullRequestUrl;

    /**
     * {@code links} of the review comment
     **/
    private final ReviewCommentLinks links;

    /**
     * {@code startLine} the first line of the range for a multi-line comment
     **/
    private final int startLine;

    /**
     * {@code originalStartLine} the first line of the range for a multi-line comment
     **/
    private final int originalStartLine;

    /**
     * {@code startSide} the side of the first line of the range for a multi-line comment
     **/
    private final Side startSide;

    /**
     * {@code line} the line of the blob to which the comment applies. The last line of the range for a multi-line comment
     **/
    private final int line;

    /**
     * {@code originalLine} the line of the blob to which the comment applies. The last line of the range for a multi-line comment
     **/
    private final int originalLine;

    /**
     * {@code side} the side of the diff to which the comment applies. The side of the last line of the range for a multi-line comment
     **/
    private final Side side;

    /**
     * {@code reactions} of the review comment
     **/
    private final CommitCommentReactions reactions;

    /**
     * {@code bodyHtml} body html of the review comment
     **/
    private final String bodyHtml;

    /**
     * {@code bodyText} body text of the review comment
     **/
    private final String bodyText;

    /**
     * Constructor to init a {@link ReviewComment}
     *
     * @param path : the relative path of the file to which the comment applies
     * @param body : the text of the comment
     * @apiNote this constructor is useful to create a new review comment
     **/
    public ReviewComment(String path, String body) {
        this(null, -1, -1, null, null, path, null, null, -1, null, body, null, null, null, null, null, null,
                0, -1, null, 0, -1, null, null, null, null);
    }

    /**
     * Constructor to init a {@link ReviewComment}
     *
     * @param path      : the relative path of the file to which the comment applies
     * @param body      : the text of the comment
     * @param startLine : the first line of the range for a multi-line comment
     * @param startSide : the side of the first line of the range for a multi-line comment
     * @param line      : the line of the blob to which the comment applies. The last line of the range for a multi-line comment
     * @param side      : base of the pull request
     * @apiNote this constructor is useful to create a new review comment
     **/
    public ReviewComment(String path, String body, int startLine, Side startSide, int line, Side side) {
        this(null, -1, -1, null, null, path, null, null, -1, null, body, null, null, null, null, null, null,
                startLine, -1, startSide, line, -1, side, null, null, null);
    }

    /**
     * Constructor to init a {@link ReviewComment}
     *
     * @param url                 : URL for the pull request review comment
     * @param pullRequestReviewId : the ID of the pull request review to which the comment belongs
     * @param id                  :  the ID of the pull request review comment
     * @param nodeId              : the node ID of the pull request review comment
     * @param diffHunk            : the diff of the line that the comment refers to
     * @param path                : the relative path of the file to which the comment applies
     * @param commitId            : the SHA of the commit to which the comment applies
     * @param originalCommitId    : the SHA of the original commit to which the comment applies
     * @param inReplyToId         :the comment ID to reply to
     * @param user                : user of the review comment
     * @param body                : the text of the comment
     * @param createdAt           : creation date of the review comment
     * @param updatedAt           : update date of the review comment
     * @param htmlUrl             :  HTML URL for the pull request review comment
     * @param pullRequestUrl      : URL for the pull request that the review comment belongs to
     * @param authorAssociation   : how the author is associated with the repository
     * @param links               : links of the review comment
     * @param startLine           : the first line of the range for a multi-line comment
     * @param originalStartLine   : the first line of the range for a multi-line comment
     * @param startSide           : the side of the first line of the range for a multi-line comment
     * @param line                : the line of the blob to which the comment applies. The last line of the range for a multi-line comment
     * @param originalLine:       the side of the diff to which the comment applies. The side of the last line of the range for a multi-line comment
     * @param side                : base of the pull request
     * @param reactions           : reactions
     * @param bodyHtml            : body html of the review comment
     * @param bodyText:body       text of the review comment
     **/
    public ReviewComment(String url, long pullRequestReviewId, long id, String nodeId, String diffHunk, String path,
                         String commitId, String originalCommitId, long inReplyToId, User user, String body,
                         String createdAt, String updatedAt, String htmlUrl, String pullRequestUrl,
                         AuthorAssociation authorAssociation, ReviewCommentLinks links, int startLine,
                         int originalStartLine, Side startSide, int line, int originalLine, Side side, CommitComment.CommitCommentReactions reactions,
                         String bodyHtml, String bodyText) {
        super(htmlUrl, url, id, nodeId, body, user, createdAt, updatedAt, authorAssociation);
        this.pullRequestReviewId = pullRequestReviewId;
        this.diffHunk = diffHunk;
        this.path = path;
        this.commitId = commitId;
        this.originalCommitId = originalCommitId;
        this.inReplyToId = inReplyToId;
        this.htmlUrl = htmlUrl;
        this.pullRequestUrl = pullRequestUrl;
        this.links = links;
        this.startLine = startLine;
        this.originalStartLine = originalStartLine;
        this.startSide = startSide;
        this.line = line;
        this.originalLine = originalLine;
        this.side = side;
        this.reactions = reactions;
        this.bodyHtml = bodyHtml;
        this.bodyText = bodyText;
    }

    /**
     * Constructor to init a {@link ReviewComment}
     *
     * @param jReviewComment: review comment details as {@link JSONObject}
     **/
    public ReviewComment(JSONObject jReviewComment) {
        super(jReviewComment);
        pullRequestReviewId = hResponse.getLong("pull_request_review_id", 0);
        diffHunk = hResponse.getString("diff_hunk");
        path = hResponse.getString("path");
        commitId = hResponse.getString("commit_id");
        originalCommitId = hResponse.getString("original_commit_id");
        inReplyToId = hResponse.getLong("in_reply_to_id", 0);
        htmlUrl = hResponse.getString("html_url");
        pullRequestUrl = hResponse.getString("pull_request_url");
        links = new ReviewCommentLinks(hResponse.getJSONObject("_links"));
        startLine = hResponse.getInt("start_line", 0);
        originalStartLine = hResponse.getInt("original_start_line", 0);
        String sSide = hResponse.getString("start_side");
        if (sSide != null)
            startSide = Side.valueOf(sSide);
        else
            startSide = null;
        line = hResponse.getInt("line", 0);
        originalLine = hResponse.getInt("original_line", 0);
        sSide = hResponse.getString("side");
        if (sSide != null)
            side = Side.valueOf(sSide);
        else
            side = null;
        JSONObject jReactions = hResponse.getJSONObject("reactions");
        if (jReactions != null)
            reactions = new CommitCommentReactions(jReactions);
        else
            reactions = null;
        bodyHtml = hResponse.getString("body_html");
        bodyText = hResponse.getString("body_text");
    }

    /**
     * Method to get {@link #pullRequestReviewId} instance <br>
     * No-any params required
     *
     * @return {@link #pullRequestReviewId} instance as long
     **/
    public long getPullRequestReviewId() {
        return pullRequestReviewId;
    }

    /**
     * Method to get {@link #diffHunk} instance <br>
     * No-any params required
     *
     * @return {@link #diffHunk} instance as {@link String}
     **/
    public String getDiffHunk() {
        return diffHunk;
    }

    /**
     * Method to get {@link #path} instance <br>
     * No-any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
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
     * Method to get {@link #originalCommitId} instance <br>
     * No-any params required
     *
     * @return {@link #originalCommitId} instance as {@link String}
     **/
    public String getOriginalCommitId() {
        return originalCommitId;
    }

    /**
     * Method to get {@link #inReplyToId} instance <br>
     * No-any params required
     *
     * @return {@link #inReplyToId} instance as long
     **/
    public long getInReplyToId() {
        return inReplyToId;
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
     * Method to get {@link #startLine} instance <br>
     * No-any params required
     *
     * @return {@link #startLine} instance as int
     **/
    public int getStartLine() {
        return startLine;
    }

    /**
     * Method to get {@link #originalStartLine} instance <br>
     * No-any params required
     *
     * @return {@link #originalStartLine} instance as int
     **/
    public int getOriginalStartLine() {
        return originalStartLine;
    }

    /**
     * Method to get {@link #startSide} instance <br>
     * No-any params required
     *
     * @return {@link #startSide} instance as {@link Side}
     **/
    public Side getStartSide() {
        return startSide;
    }

    /**
     * Method to get {@link #line} instance <br>
     * No-any params required
     *
     * @return {@link #line} instance as int
     **/
    public int getLine() {
        return line;
    }

    /**
     * Method to get {@link #originalLine} instance <br>
     * No-any params required
     *
     * @return {@link #originalLine} instance as int
     **/
    public int getOriginalLine() {
        return originalLine;
    }

    /**
     * Method to get {@link #side} instance <br>
     * No-any params required
     *
     * @return {@link #side} instance as {@link Side}
     **/
    public Side getSide() {
        return side;
    }

    /**
     * Method to get {@link #reactions} instance <br>
     * No-any params required
     *
     * @return {@link #reactions} instance as {@link String}
     **/
    public CommitCommentReactions getReactions() {
        return reactions;
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
     * Method to create a review comments list
     *
     * @param reviewCommentsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnReviewComments(String reviewCommentsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(reviewCommentsResponse);
            case LIBRARY_OBJECT:
                ArrayList<ReviewComment> reviewComments = new ArrayList<>();
                JSONArray jReviewComments = new JSONArray(reviewCommentsResponse);
                for (int j = 0; j < jReviewComments.length(); j++)
                    reviewComments.add(new ReviewComment(jReviewComments.getJSONObject(j)));
                return (T) reviewComments;
            default:
                return (T) reviewCommentsResponse;
        }
    }

    /**
     * The {@code ReviewCommentLinks} class is useful to format a GitHub's review comment links
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ReviewCommentLinks extends InnerClassItem {

        /**
         * {@code self} review comment link
         **/
        private final String self;

        /**
         * {@code html} review comment link
         **/
        private final String html;

        /**
         * {@code pullRequest} review comment link
         **/
        private final String pullRequest;

        /**
         * Constructor to init a {@link ReviewCommentLinks}
         *
         * @param self:        review comment link
         * @param html:        review comment link
         * @param pullRequest: review comment link
         **/
        public ReviewCommentLinks(String self, String html, String pullRequest) {
            super(null);
            this.self = self;
            this.html = html;
            this.pullRequest = pullRequest;
        }

        /**
         * Constructor to init a {@link ReviewCommentLinks}
         *
         * @param jReviewCommentLink: review comment link details as {@link JSONObject}
         **/
        public ReviewCommentLinks(JSONObject jReviewCommentLink) {
            super(jReviewCommentLink);
            html = hItem.getJSONObject("html").getString("href");
            pullRequest = hItem.getJSONObject("pull_request").getString("href");
            hItem.setJSONObjectSource(hItem.getJSONObject("self", new JSONObject()));
            self = hItem.getString("href");
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
         * Method to get {@link #pullRequest} instance <br>
         * No-any params required
         *
         * @return {@link #pullRequest} instance as {@link String}
         **/
        public String getPullRequest() {
            return pullRequest;
        }

    }

}
