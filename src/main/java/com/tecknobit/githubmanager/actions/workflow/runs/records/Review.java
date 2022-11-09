package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState;
import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState.approved;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.ApprovalState.valueOf;

public class Review extends GitHubResponse {

    private final ApprovalState state;
    private final String comment;
    private final ArrayList<Environment> environments;
    private final User user;

    public Review(JSONObject jResponse, ApprovalState state, String comment, ArrayList<Environment> environments,
                  User user) {
        super(jResponse);
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

    public ApprovalState getState() {
        return state;
    }

    public String getComment() {
        return comment;
    }

    public ArrayList<Environment> getEnvironments() {
        return environments;
    }

    public User getUser() {
        return user;
    }

    public static class Environment extends BaseResponseDetails {

        protected final String nodeId;
        protected final String htmlUrl;
        protected final String createdAt;
        protected final String updatedAt;

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

        public String getNodeId() {
            return nodeId;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        /**
         * Method to get {@link #createdAt} instance <br>
         * Any params required
         *
         * @return {@link #createdAt} instance as {@link String}
         **/
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         * Method to get {@link #createdAt} timestamp <br>
         * Any params required
         *
         * @return {@link #createdAt} timestamp as long
         **/
        public long getCreatedAtTimestamp() {
            try {
                return dateFormatter.parse(createdAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        /**
         * Method to get {@link #updatedAt} instance <br>
         * Any params required
         *
         * @return {@link #updatedAt} instance as {@link String}
         **/
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         * Method to get {@link #updatedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #updatedAt} timestamp as long
         **/
        public long getUpdatedAtTimestamp() {
            try {
                return dateFormatter.parse(updatedAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
