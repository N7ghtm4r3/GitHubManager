package com.tecknobit.githubmanager.commits.commitcomments.records;

import com.tecknobit.githubmanager.records.generic.GitHubComment;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
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
    private final CommitCommentReactions reactions;

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
                         AuthorAssociation authorAssociation, CommitCommentReactions reactions) {
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
        reactions = new CommitCommentReactions(hResponse.getJSONObject("reactions", new JSONObject()));
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
     * @return {@link #reactions} instance as {@link CommitCommentReactions}
     **/
    public CommitCommentReactions getReactions() {
        return reactions;
    }

    /**
     * The {@code CommitCommentReactions} class is useful to format a GitHub's reactions for {@link CommitComment}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class CommitCommentReactions extends InnerClassItem {

        /**
         * {@code url} of the reactions
         **/
        private final String url;

        /**
         * {@code totalCount} of the reactions
         **/
        private final int totalCount;

        /**
         * {@code plusOne} plus one reaction
         **/
        private final int plusOne;

        /**
         * {@code minusOne} minus one reaction
         **/
        private final int minusOne;

        /**
         * {@code laugh} reaction
         **/
        private final int laugh;

        /**
         * {@code confused} reaction
         **/
        private final int confused;

        /**
         * {@code heart} reaction
         **/
        private final int heart;

        /**
         * {@code hooray} reaction
         **/
        private final int hooray;

        /**
         * {@code eyes} reaction
         **/
        private final int eyes;

        /**
         * {@code rocket} reaction
         **/
        private final int rocket;

        /**
         * Constructor to init a {@link CommitCommentReactions}
         *
         * @param url        : url of the reactions
         * @param totalCount :  total count of the reactions
         * @param plusOne    :  plus one reaction
         * @param minusOne   :  minus one reaction
         * @param laugh      :  laugh reaction
         * @param confused   :  confused reaction
         * @param heart      :  heart reaction
         * @param hooray     :  hooray reaction
         * @param eyes       :  eyes reaction
         * @param rocket     : rocket reaction
         **/
        public CommitCommentReactions(String url, int totalCount, int plusOne, int minusOne, int laugh, int confused,
                                      int heart, int hooray, int eyes, int rocket) {
            super(null);
            this.url = url;
            this.totalCount = totalCount;
            this.plusOne = plusOne;
            this.minusOne = minusOne;
            this.laugh = laugh;
            this.confused = confused;
            this.heart = heart;
            this.hooray = hooray;
            this.eyes = eyes;
            this.rocket = rocket;
        }

        /**
         * Constructor to init a {@link CommitCommentReactions}
         *
         * @param jReactions : reactions details as {@link JSONObject}
         **/
        public CommitCommentReactions(JSONObject jReactions) {
            super(jReactions);
            url = hItem.getString("url");
            totalCount = hItem.getInt("total_count", 0);
            plusOne = hItem.getInt("+1", 0);
            minusOne = hItem.getInt("-1", 0);
            laugh = hItem.getInt("laugh", 0);
            confused = hItem.getInt("confused", 0);
            heart = hItem.getInt("heart", 0);
            hooray = hItem.getInt("hooray", 0);
            eyes = hItem.getInt("eyes", 0);
            rocket = hItem.getInt("rocket", 0);
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
         * Method to get {@link #totalCount} instance <br>
         * No-any params required
         *
         * @return {@link #totalCount} instance as int
         **/
        public int getTotalCount() {
            return totalCount;
        }

        /**
         * Method to get {@link #plusOne} instance <br>
         * No-any params required
         *
         * @return {@link #plusOne} instance as int
         **/
        public int getPlusOne() {
            return plusOne;
        }

        /**
         * Method to get {@link #minusOne} instance <br>
         * No-any params required
         *
         * @return {@link #minusOne} instance as int
         **/
        public int getMinusOne() {
            return minusOne;
        }

        /**
         * Method to get {@link #laugh} instance <br>
         * No-any params required
         *
         * @return {@link #laugh} instance as int
         **/
        public int getLaugh() {
            return laugh;
        }

        /**
         * Method to get {@link #confused} instance <br>
         * No-any params required
         *
         * @return {@link #confused} instance as int
         **/
        public int getConfused() {
            return confused;
        }

        /**
         * Method to get {@link #heart} instance <br>
         * No-any params required
         *
         * @return {@link #heart} instance as int
         **/
        public int getHeart() {
            return heart;
        }

        /**
         * Method to get {@link #hooray} instance <br>
         * No-any params required
         *
         * @return {@link #hooray} instance as int
         **/
        public int getHooray() {
            return hooray;
        }

        /**
         * Method to get {@link #eyes} instance <br>
         * No-any params required
         *
         * @return {@link #eyes} instance as int
         **/
        public int getEyes() {
            return eyes;
        }

        /**
         * Method to get {@link #rocket} instance <br>
         * No-any params required
         *
         * @return {@link #rocket} instance as int
         **/
        public int getRocket() {
            return rocket;
        }

    }

}
