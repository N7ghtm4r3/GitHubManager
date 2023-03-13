package com.tecknobit.githubmanager.issues.comments.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment.CommitCommentReactions;
import com.tecknobit.githubmanager.records.generic.GitHubComment;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

/**
 * The {@code IssueComment} class is useful to format a GitHub's issue comment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
 *             List issue comments for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/comments#get-an-issue-comment">
 *             Get an issue comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
 *             Update an issue comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/comments#delete-an-issue-comment">
 *             List issue comments</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
 *             Create an issue comment</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubComment
 **/
public class IssueComment extends GitHubComment {

    /**
     * {@code bodyText} body text of the issue comment
     **/
    private final String bodyText;

    /**
     * {@code bodyHtml} body html of the issue comment
     **/
    private final String bodyHtml;

    /**
     * {@code issueUrl} issue url of the issue comment
     **/
    private final String issueUrl;

    /**
     * {@code performedViaGitHubApp} GitHub app attached to the issue comment
     **/
    private final GitHubApp performedViaGitHubApp;

    /**
     * {@code reactions} reactions attached to the issue comment
     **/
    private final CommitCommentReactions reactions;

    /**
     * Constructor to init a {@link IssueComment}
     *
     * @param id                    : unique identifier of the issue comment
     * @param nodeId                : node id of the issue comment
     * @param url                   : url for the issue comment
     * @param body                  : contents of the issue comment
     * @param bodyText              : body text of the issue comment
     * @param bodyHtml              : body html of the issue comment
     * @param htmlUrl               : html url of the issue comment
     * @param user                  : user of the issue comment
     * @param createdAt             :  creation date of the issue comment
     * @param updatedAt             : update time of the issue comment
     * @param issueUrl              : issue url of the issue comment
     * @param authorAssociation     : how the author is associated with the repository
     * @param performedViaGitHubApp : GitHub app attached to the issue comment
     * @param reactions             : reactions attached to the issue comment
     **/
    public IssueComment(long id, String nodeId, String url, String body, String bodyText, String bodyHtml, String htmlUrl,
                        User user, String createdAt, String updatedAt, String issueUrl, AuthorAssociation authorAssociation,
                        GitHubApp performedViaGitHubApp, CommitCommentReactions reactions) {
        super(htmlUrl, url, id, nodeId, body, user, createdAt, updatedAt, authorAssociation);
        this.bodyText = bodyText;
        this.bodyHtml = bodyHtml;
        this.issueUrl = issueUrl;
        this.performedViaGitHubApp = performedViaGitHubApp;
        this.reactions = reactions;
    }

    /**
     * Constructor to init a {@link IssueComment}
     *
     * @param jIssueComment: issue comment details as {@link JSONObject}
     **/
    public IssueComment(JSONObject jIssueComment) throws Exception {
        super(jIssueComment);
        bodyText = hResponse.getString("body_text");
        bodyHtml = hResponse.getString("body_html");
        issueUrl = hResponse.getString("issue_url");
        JSONObject jItem = hResponse.getJSONObject("performed_via_github_app");
        if (jItem != null)
            performedViaGitHubApp = new GitHubApp(jItem);
        else
            performedViaGitHubApp = null;
        jItem = hResponse.getJSONObject("reactions");
        if (jItem != null)
            reactions = new CommitCommentReactions(jItem);
        else
            reactions = null;
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
     * Method to get {@link #bodyHtml} instance <br>
     * No-any params required
     *
     * @return {@link #bodyHtml} instance as {@link String}
     **/
    public String getBodyHtml() {
        return bodyHtml;
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
     * Method to get {@link #performedViaGitHubApp} instance <br>
     * No-any params required
     *
     * @return {@link #performedViaGitHubApp} instance as {@link GitHubApp}
     **/
    public GitHubApp getPerformedViaGitHubApp() {
        return performedViaGitHubApp;
    }

    /**
     * Method to get {@link #reactions} instance <br>
     * No-any params required
     *
     * @return {@link #reactions} instance as {@link CommitCommentReactions}
     **/
    public CommitCommentReactions getReactions() {
        return reactions;
    }

}
