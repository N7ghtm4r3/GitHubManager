package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code DefaultWorkflowPermissions} class is useful to format a GitHub's default workflow permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-enterprise">
 *             Get default workflow permissions for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-enterprise">
 *             Set default workflow permissions for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
 *             Get default workflow permissions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
 *             Set default workflow permissions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
 *             Get default workflow permissions for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
 *             Set default workflow permissions for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class DefaultWorkflowPermissions extends GitHubResponse {

    /**
     * {@code defaultWorkflowPermissions} the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     **/
    private final String defaultWorkflowPermissions;

    /**
     * {@code canApprovePullRequestReviews} whether {@code "GitHub Actions"} can approve pull requests. Enabling this can be a security risk
     **/
    private final boolean canApprovePullRequestReviews;

    /**
     * Constructor to init a {@link DefaultWorkflowPermissions}
     *
     * @param workflowPermissions:          the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     * @param canApprovePullRequestReviews: whether {@code "GitHub Actions"} can approve pull requests. Enabling this can be a security risk
     **/
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

    /**
     * Method to get {@link #defaultWorkflowPermissions} instance <br>
     * Any params required
     *
     * @return {@link #defaultWorkflowPermissions} instance as {@link String}
     **/
    public String getDefaultWorkflowPermissions() {
        return defaultWorkflowPermissions;
    }

    /**
     * Method to get {@link #canApprovePullRequestReviews} instance <br>
     * Any params required
     *
     * @return {@link #canApprovePullRequestReviews} instance as boolean
     **/
    public boolean isCanApprovePullRequestReviews() {
        return canApprovePullRequestReviews;
    }

    /**
     * {@code WorkflowPermissions} the default workflow permissions granted to the {@code "GITHUB_TOKEN"} when running workflows
     *
     * @apiNote see the official documentation at:
     * @apiNote see the official documentation at:
     * <ul>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-enterprise">
     *             Get default workflow permissions for an enterprise</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-enterprise">
     *             Set default workflow permissions for an enterprise</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-an-organization">
     *             Get default workflow permissions for an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-an-organization">
     *             Set default workflow permissions for an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#get-default-workflow-permissions-for-a-repository">
     *             Get default workflow permissions for a repository</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#set-default-workflow-permissions-for-a-repository">
     *             Set default workflow permissions for a repository</a>
     *     </li>
     * </ul>
     **/
    public enum WorkflowPermissions {

        /**
         * {@code "read"} permission
         **/
        read,

        /**
         * {@code "write"} permission
         **/
        write

    }

}
