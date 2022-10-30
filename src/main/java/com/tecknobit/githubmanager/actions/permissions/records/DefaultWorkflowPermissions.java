package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

public class DefaultWorkflowPermissions extends GitHubManager.GitHubResponse {

    private final String defaultWorkflowPermissions;
    private final boolean canApprovePullRequestReviews;

    public DefaultWorkflowPermissions(WorkflowPermissions workflowPermissions, boolean canApprovePullRequestReviews) {
        super(null);
        this.defaultWorkflowPermissions = workflowPermissions.name();
        this.canApprovePullRequestReviews = canApprovePullRequestReviews;
    }

    /**
     * Constructor to init a {@link DefaultWorkflowPermissions}
     *
     * @param jDefaultWorkflowPermissions : default workflow permissions details as {@link JSONObject}
     **/
    public DefaultWorkflowPermissions(JSONObject jDefaultWorkflowPermissions) {
        super(jDefaultWorkflowPermissions);
        defaultWorkflowPermissions = hResponse.getString("default_workflow_permissions");
        canApprovePullRequestReviews = hResponse.getBoolean("can_approve_pull_request_reviews");
    }

    public String getDefaultWorkflowPermissions() {
        return defaultWorkflowPermissions;
    }

    public boolean isCanApprovePullRequestReviews() {
        return canApprovePullRequestReviews;
    }

    public enum WorkflowPermissions {
        read,
        write
    }

}
