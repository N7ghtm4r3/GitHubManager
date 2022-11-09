package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.githubmanager.actions.workflow.runs.records.Review;
import org.json.JSONObject;

public class Workflow extends Review.Environment {

    private final String path;
    private final WorkflowState state;
    private final String badgeUrl;

    public Workflow(long id, String name, String url, String nodeId, String htmlUrl, String createdAt, String updatedAt,
                    String path, WorkflowState state, String badgeUrl) {
        super(id, name, url, nodeId, htmlUrl, createdAt, updatedAt);
        this.path = path;
        this.state = state;
        this.badgeUrl = badgeUrl;
    }

    /**
     * Constructor to init a {@link Workflow}
     *
     * @param jWorkflow : workflow details as {@link JSONObject}
     **/
    public Workflow(JSONObject jWorkflow) {
        super(jWorkflow);
        path = hResponse.getString("path");
        state = WorkflowState.valueOf(hResponse.getString("state", WorkflowState.deleted.toString()));
        badgeUrl = hResponse.getString("badge_url");
    }

    public String getPath() {
        return path;
    }

    public WorkflowState getState() {
        return state;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public enum WorkflowState {

        active,
        deleted,
        disabled_fork,
        disabled_inactivity,
        disabled_manually

    }

}
