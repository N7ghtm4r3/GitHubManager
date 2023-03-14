package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

/**
 * The {@code GitHubComment} class is useful to format a GitHub's comment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
 *             List commit comments for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/comments#get-a-commit-comment">
 *             Get a commit comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
 *             Update a commit comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
 *             List commit comments</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
 *             Create a commit comment</a>
 *     </li>
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
 * @see BaseItemStructure
 **/
public class GitHubComment extends BaseItemStructure {

    /**
     * {@code htmlUrl} html url of the comment
     **/
    protected final String htmlUrl;

    /**
     * {@code user} of the comment
     **/
    protected final User user;

    /**
     * {@code body} of the comment
     **/
    protected final String body;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    protected final AuthorAssociation authorAssociation;

    /**
     * Constructor to init a {@link GitHubComment}
     *
     * @param htmlUrl           : html url of the comment
     * @param url               : url of the comment
     * @param id                : id of the comment
     * @param nodeId            : node id of the comment
     * @param body              : body of the comment
     * @param user              : user of the comment
     * @param createdAt         : creation time of the comment
     * @param updatedAt         : update time of the comment
     * @param authorAssociation : how the author is associated with the repository
     **/
    public GitHubComment(String htmlUrl, String url, long id, String nodeId, String body, User user, String createdAt,
                         String updatedAt, AuthorAssociation authorAssociation) {
        super(null);
        this.htmlUrl = htmlUrl;
        this.body = body;
        this.user = user;
        this.authorAssociation = authorAssociation;
    }

    /**
     * Constructor to init a {@link GitHubComment}
     *
     * @param jGitHubComment : comment details as {@link JSONObject}
     **/
    public GitHubComment(JSONObject jGitHubComment) {
        super(jGitHubComment);
        htmlUrl = hResponse.getString("html_url");
        body = hResponse.getString("body");
        JSONObject jUser = hResponse.getJSONObject("user");
        if (jUser != null)
            user = new User(jUser);
        else
            user = null;
        authorAssociation = AuthorAssociation.valueOf(hResponse.getString("author_association"));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link Long}
     **/
    @Override
    public Long getId() {
        return super.getId();
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
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
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
     * Method to get {@link #authorAssociation} instance <br>
     * No-any params required
     *
     * @return {@link #authorAssociation} instance as {@link AuthorAssociation}
     **/
    public AuthorAssociation getAuthorAssociation() {
        return authorAssociation;
    }

}
