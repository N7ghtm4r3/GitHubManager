package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState;
import com.tecknobit.githubmanager.actions.workflow.records.Workflow;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState.approved;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState.valueOf;

/**
 * The {@code Review} class is useful to format a GitHub's review
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/actions/workflow-runs#get-the-review-history-for-a-workflow-run>
 * Get the review history for a workflow run</a>
 * @see GitHubResponse
 **/
public class Review extends GitHubResponse {

    /**
     * {@code state} whether deployment to the environment(s) was approved or rejected
     **/
    private final ApprovalState state;

    /**
     * {@code "comment"} value
     **/
    private final String comment;

    /**
     * {@code environments} environments list
     **/
    private final ArrayList<Environment> environments;

    /**
     * {@code "user"} value
     **/
    private final User user;

    /**
     * Constructor to init a {@link Review}
     *
     * @param state:        whether deployment to the environment(s) was approved or rejected
     * @param comment:      comment value
     * @param environments: environments list
     * @param user:         user value
     **/
    public Review(ApprovalState state, String comment, ArrayList<Environment> environments,
                  User user) {
        super(null);
        this.state = state;
        this.comment = comment;
        this.environments = environments;
        this.user = user;
    }

    /**
     * Constructor to init a {@link Review}
     *
     * @param jReview : review details as {@link JSONObject}
     **/
    public Review(JSONObject jReview) {
        super(jReview);
        state = valueOf(hResponse.getString("state", approved.toString()));
        comment = hResponse.getString("comment");
        environments = new ArrayList<>();
        JSONArray jEnvironments = hResponse.getJSONArray("environments", new JSONArray());
        for (int j = 0; j < jEnvironments.length(); j++)
            environments.add(new Environment(jEnvironments.getJSONObject(j)));
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link ApprovalState}
     **/
    public ApprovalState getState() {
        return state;
    }

    /**
     * Method to get {@link #comment} instance <br>
     * No-any params required
     *
     * @return {@link #comment} instance as {@link String}
     **/
    public String getComment() {
        return comment;
    }

    /**
     * Method to get {@link #environments} instance <br>
     * No-any params required
     *
     * @return {@link #environments} instance as {@link ArrayList} of {@link Environment}
     **/
    public ArrayList<Environment> getEnvironments() {
        return environments;
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
     * The {@code Environment} class is useful to format a GitHub's environment
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see BaseResponseDetails
     **/
    public static class Environment extends BaseResponseDetails {

        /**
         * {@code nodeId} identifier of the node value
         **/
        protected final String nodeId;

        /**
         * {@code htmlUrl} html url value
         **/
        protected final String htmlUrl;

        /**
         * {@code createdAt} created at value
         **/
        protected final String createdAt;

        /**
         * {@code updatedAt} updated at value
         **/
        protected final String updatedAt;

        /**
         * Constructor to init a {@link Workflow}
         *
         * @param id:        identifier of the workflow
         * @param name:      the name of the workflow
         * @param url:       url value
         * @param nodeId:    identifier of the node value
         * @param htmlUrl:   html url value
         * @param createdAt: created at value
         * @param updatedAt: updated at value
         **/
        public Environment(long id, String name, String url, String nodeId, String htmlUrl, String createdAt,
                           String updatedAt) {
            super(id, name, url);
            this.nodeId = nodeId;
            this.htmlUrl = htmlUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        /**
         * Constructor to init a {@link Environment}
         *
         * @param jEnvironment : environment details as {@link JSONObject}
         **/
        public Environment(JSONObject jEnvironment) {
            super(jEnvironment);
            nodeId = hResponse.getString("node_id");
            htmlUrl = hResponse.getString("html_url");
            createdAt = hResponse.getString("created_at");
            updatedAt = hResponse.getString("updated_at");
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
         * Method to get {@link #htmlUrl} instance <br>
         * No-any params required
         *
         * @return {@link #htmlUrl} instance as {@link String}
         **/
        public String getHtmlUrl() {
            return htmlUrl;
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

    }

}
