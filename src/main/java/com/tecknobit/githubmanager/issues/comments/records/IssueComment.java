package com.tecknobit.githubmanager.issues.comments.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment.Reactions;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

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
 **/
public class IssueComment extends GitHubResponse {

    /**
     * {@code id} unique identifier of the issue comment
     **/
    private final long id;

    /**
     * {@code nodeId} node id of the issue comment
     **/
    private final String nodeId;

    /**
     * {@code url} for the issue comment
     **/
    private final String url;

    /**
     * {@code body} contents of the issue comment
     **/
    private final String body;

    /**
     * {@code bodyText} body text of the issue comment
     **/
    private final String bodyText;

    /**
     * {@code bodyHtml} body html of the issue comment
     **/
    private final String bodyHtml;

    /**
     * {@code htmlUrl} html url of the issue comment
     **/
    private final String htmlUrl;

    /**
     * {@code user} of the issue comment
     **/
    private final User user;

    /**
     * {@code createdAt} creation date of the issue comment
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update time of the issue comment
     **/
    private final String updatedAt;

    /**
     * {@code issueUrl} issue url of the issue comment
     **/
    private final String issueUrl;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    private final AuthorAssociation authorAssociation;

    /**
     * {@code performedViaGitHubApp} GitHub app attached to the issue comment
     **/
    private final GitHubApp performedViaGitHubApp;

    /**
     * {@code reactions} reactions attached to the issue comment
     **/
    private final Reactions reactions;

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
                        GitHubApp performedViaGitHubApp, Reactions reactions) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.body = body;
        this.bodyText = bodyText;
        this.bodyHtml = bodyHtml;
        this.htmlUrl = htmlUrl;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.issueUrl = issueUrl;
        this.authorAssociation = authorAssociation;
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
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        body = hResponse.getString("body");
        bodyText = hResponse.getString("body_text");
        bodyHtml = hResponse.getString("body_html");
        htmlUrl = hResponse.getString("html_url");
        user = new User(hResponse.getJSONObject("user"));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        issueUrl = hResponse.getString("issue_url");
        authorAssociation = AuthorAssociation.valueOf(hResponse.getString("author_association"));
        JSONObject jItem = hResponse.getJSONObject("performed_via_github_app");
        if (jItem != null)
            performedViaGitHubApp = new GitHubApp(jItem);
        else
            performedViaGitHubApp = null;
        jItem = hResponse.getJSONObject("reactions");
        if (jItem != null)
            reactions = new Reactions(jItem);
        else
            reactions = null;
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
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
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
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
     * Method to get {@link #authorAssociation} instance <br>
     * No-any params required
     *
     * @return {@link #authorAssociation} instance as {@link AuthorAssociation}
     **/
    public AuthorAssociation getAuthorAssociation() {
        return authorAssociation;
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
     * @return {@link #reactions} instance as {@link Reactions}
     **/
    public Reactions getReactions() {
        return reactions;
    }

}
