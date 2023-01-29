package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.githubmanager.actions.workflow.runs.records.Review.Environment;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Workflow} class is useful to format a GitHub's workflow
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/actions/workflows#get-a-workflow">
 * Get a workflow</a>
 * @see GitHubResponse
 * @see Environment
 **/
public class Workflow extends Environment {

    /**
     * {@code "path"} value
     **/
    private final String path;

    /**
     * {@code "state"} of the workflow
     **/
    private final WorkflowState state;

    /**
     * {@code badgeUrl} badge url value
     **/
    private final String badgeUrl;

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
     * @param path:      path value
     * @param state:     state of the workflow
     * @param badgeUrl:  badge url value
     **/
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
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link WorkflowState}
     **/
    public WorkflowState getState() {
        return state;
    }

    /**
     * Method to get {@link #badgeUrl} instance <br>
     * No-any params required
     *
     * @return {@link #badgeUrl} instance as {@link String}
     **/
    public String getBadgeUrl() {
        return badgeUrl;
    }

    /**
     * {@code "WorkflowState"} list of states for a workflow
     **/
    public enum WorkflowState {

        /**
         * {@code "active"} state
         **/
        active,

        /**
         * {@code "deleted"} state
         **/
        deleted,

        /**
         * {@code "disabled_fork"} state
         **/
        disabled_fork,

        /**
         * {@code "disabled_inactivity"} state
         **/
        disabled_inactivity,

        /**
         * {@code "disabled_manually"} state
         **/
        disabled_manually

    }

}
