package com.tecknobit.githubmanager.reactions.records;

import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.reactions.records.Reaction.ReactionContent.reachEnumConstant;

/**
 * The {@code Reaction} class is useful to format a GitHub's reaction
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
 *             List reactions for a team discussion comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
 *             Create reaction for a team discussion comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
 *             List reactions for a team discussion</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
 *             Create reaction for a team discussion</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
 *             List reactions for a commit comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
 *             Create reaction for a commit comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
 *             List reactions for an issue comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
 *             Create reaction for an issue comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
 *             List reactions for an issue</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
 *             Create reaction for an issue</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
 *             List reactions for a pull request review comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
 *             Create reaction for a pull request review comment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
 *             List reactions for a release</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
 *             Create reaction for a release</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseItemStructure
 **/
public class Reaction extends GitHubResponse {

    /**
     * {@code ReactionContent} list of available reaction contents
     **/
    public enum ReactionContent {

        /**
         * {@code +1} reaction content
         **/
        p1("+1"),

        /**
         * {@code -1} reaction content
         **/
        m1("-1"),

        /**
         * {@code laugh} reaction content
         **/
        laugh("laugh"),

        /**
         * {@code confused} reaction content
         **/
        confused("confused"),

        /**
         * {@code heart} reaction content
         **/
        heart("heart"),

        /**
         * {@code hooray} reaction content
         **/
        hooray("hooray"),

        /**
         * {@code rocket} reaction content
         **/
        rocket("rocket"),

        /**
         * {@code eyes} reaction content
         **/
        eyes("eyes");

        /**
         * {@code content} value
         **/
        private final String content;

        /**
         * Constructor to init a {@link ReactionContent}
         *
         * @param content: content value
         **/
        ReactionContent(String content) {
            this.content = content;
        }

        /**
         * Method to reach a {@link ReactionContent} value
         *
         * @param target: target of the {@link ReactionContent} to reach
         * @return content as {@link ReactionContent}
         **/
        public static ReactionContent reachEnumConstant(String target) {
            if (target.contains("+"))
                return p1;
            else if (target.contains("-"))
                return m1;
            else
                return ReactionContent.valueOf(target);
        }

        /**
         * Method to get {@link #content} instance <br>
         * No-any params required
         *
         * @return {@link #content} instance as {@link String}
         **/
        @Override
        public String toString() {
            return content;
        }

    }

    /**
     * {@code id} of the reaction
     **/
    private final long id;

    /**
     * {@code nodeId} node id of the reaction
     **/
    private final String nodeId;

    /**
     * {@code user} of the reaction
     **/
    private final User user;

    /**
     * {@code content} the reaction to use
     **/
    private final ReactionContent content;

    /**
     * {@code createdAt} creation time of the reaction
     **/
    private final String createdAt;

    /**
     * Constructor to init a {@link Reaction}
     *
     * @param id:        id of the reaction
     * @param nodeId:    node id of the reaction
     * @param user:      user of the reaction
     * @param content:   the reaction to use
     * @param createdAt: creation time of the reaction
     **/
    public Reaction(long id, String nodeId, User user, ReactionContent content, String createdAt) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
    }

    /**
     * Constructor to init a {@link Reaction}
     *
     * @param jReaction: reaction details as {@link JSONObject}
     **/
    public Reaction(JSONObject jReaction) {
        super(jReaction);
        id = hResponse.getLong("id");
        nodeId = hResponse.getString("node_id");
        user = new User(hResponse.getJSONObject("user"));
        content = reachEnumConstant(hResponse.getString("content"));
        createdAt = hResponse.getString("created_at");
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
     * Method to get {@link #content} instance <br>
     * No-any params required
     *
     * @return {@link #content} instance as {@link ReactionContent}
     **/
    public ReactionContent getContent() {
        return content;
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

}
