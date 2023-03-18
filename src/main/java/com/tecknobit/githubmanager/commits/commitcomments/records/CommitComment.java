package com.tecknobit.githubmanager.commits.commitcomments.records;

import com.tecknobit.githubmanager.reactions.records.Reactions;
import com.tecknobit.githubmanager.records.generic.GitHubComment;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

/**
 * The {@code CommitComment} class is useful to format a GitHub's commit comment
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
 * </ul>
 * @see GitHubResponse
 * @see GitHubComment
 **/
public class CommitComment extends GitHubComment {

    /**
     * {@code path} of the commit comment
     **/
    private final String path;

    /**
     * {@code position} of the commit comment
     **/
    private final int position;

    /**
     * {@code line} of the commit comment
     **/
    private final int line;

    /**
     * {@code commitId} commit id of the commit comment
     **/
    private final String commitId;

    /**
     * {@code reactions} of the commit comment
     **/
    private final Reactions reactions;

    /**
     * Constructor to init a {@link CommitComment}
     *
     * @param body     : body of the commit comment
     * @param path     : path of the commit comment
     * @param position : position of the commit comment
     * @apiNote this constructor is useful when you need to create a new {@link CommitComment}
     **/
    public CommitComment(String body, String path, int position) {
        this(null, null, 0, null, body, path, position, 0, null, null, null, null, null, null);
    }

    /**
     * Constructor to init a {@link CommitComment}
     *
     * @param htmlUrl           : html url of the commit comment
     * @param url               : url of the commit comment
     * @param id                : id of the commit comment
     * @param nodeId            : node id of the commit comment
     * @param body              : body of the commit comment
     * @param path              : path of the commit comment
     * @param position          : position of the commit comment
     * @param line              : line of the commit comment
     * @param commitId          : commit id of the commit comment
     * @param user              : user of the commit comment
     * @param createdAt         : creation time of the commit comment
     * @param updatedAt         : update time of the commit comment
     * @param authorAssociation : how the author is associated with the repository
     * @param reactions         : reactions of the commit comment
     **/
    public CommitComment(String htmlUrl, String url, long id, String nodeId, String body, String path, int position,
                         int line, String commitId, User user, String createdAt, String updatedAt,
                         AuthorAssociation authorAssociation, Reactions reactions) {
        super(htmlUrl, url, id, nodeId, body, user, createdAt, updatedAt, authorAssociation);
        this.path = path;
        this.position = position;
        this.line = line;
        this.commitId = commitId;
        this.reactions = reactions;
    }

    /**
     * Constructor to init a {@link CommitComment}
     *
     * @param jCommitComment : commit comment details as {@link JSONObject}
     **/
    public CommitComment(JSONObject jCommitComment) {
        super(jCommitComment);
        path = hResponse.getString("path");
        position = hResponse.getInt("position", 0);
        line = hResponse.getInt("line", 0);
        commitId = hResponse.getString("commit_id");
        reactions = new Reactions(hResponse.getJSONObject("reactions", new JSONObject()));
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
     * Method to get {@link #position} instance <br>
     * No-any params required
     *
     * @return {@link #position} instance as int
     **/
    public int getPosition() {
        return position;
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
     * Method to get {@link #commitId} instance <br>
     * No-any params required
     *
     * @return {@link #commitId} instance as {@link String}
     **/
    public String getCommitId() {
        return commitId;
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
