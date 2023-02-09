package com.tecknobit.githubmanager.gists.comments.records;

import com.tecknobit.githubmanager.gists.records.GistStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.commits.commits.records.pullrequests.PullRequest.AuthorAssociation;

/**
 * The {@code GistComment} class is useful to format a GitHub's gist comment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
 *             List gist comments</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
 *             Create a gist comment</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
 *              Get a gist comment</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
 *              Update a gist comment</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GistStructure
 **/
public class GistComment extends GistStructure {

    /**
     * {@code body} the comment text
     **/
    private final String body;

    /**
     * {@code user} the user of the gist comment
     **/
    private final User user;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    private final AuthorAssociation authorAssociation;

    /**
     * Constructor to init a {@link GistComment}
     *
     * @param url               : the url of the gist
     * @param id                : the id of the gist
     * @param nodeId            : the node id of the gist
     * @param createdAt         : the creation time of the gist
     * @param updatedAt         : the updated time of the gist
     * @param body              : the comment text
     * @param user              : the user of the gist comment
     * @param authorAssociation : how the author is associated with the repository
     **/
    public GistComment(String url, String id, String nodeId, String createdAt, String updatedAt, String body, User user,
                       AuthorAssociation authorAssociation) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.body = body;
        this.user = user;
        this.authorAssociation = authorAssociation;
    }

    /**
     * Constructor to init a {@link GistComment}
     *
     * @param jGistComment : gist details as {@link JSONObject}
     **/
    public GistComment(JSONObject jGistComment) {
        super(jGistComment);
        body = hResponse.getString("body");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        authorAssociation = AuthorAssociation.valueOf(hResponse.getString("author_association"));
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
