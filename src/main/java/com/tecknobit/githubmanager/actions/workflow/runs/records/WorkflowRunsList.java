package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code WorkflowRunsList} class is useful to format a GitHub's workflows runs list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-repository">
 *              List workflow runs for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-runs#list-workflow-runs-for-a-workflow">
 *             List workflow runs for a workflow</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class WorkflowRunsList extends GitHubList {

    /**
     * {@code workflowRuns} workflow runs list
     **/
    private final ArrayList<WorkflowRun> workflowRuns;

    /**
     * Constructor to init a {@link WorkflowRunsList}
     *
     * @param workflowRuns: workflow runs list
     **/
    public WorkflowRunsList(ArrayList<WorkflowRun> workflowRuns) {
        super(workflowRuns.size());
        this.workflowRuns = workflowRuns;
    }

    /**
     * Constructor to init an {@link WorkflowRunsList}
     *
     * @param totalCount    : total number of the workflow runs in the list
     * @param workflowRuns: workflow runs list
     **/
    public WorkflowRunsList(int totalCount, ArrayList<WorkflowRun> workflowRuns) {
        super(totalCount);
        this.workflowRuns = workflowRuns;
    }

    /**
     * Constructor to init a {@link WorkflowRunsList}
     *
     * @param jWorkflowRunsList : workflow runs list as {@link JSONObject}
     **/
    public WorkflowRunsList(JSONObject jWorkflowRunsList) {
        super(jWorkflowRunsList);
        workflowRuns = new ArrayList<>();
        JSONArray jWorkflowRuns = hResponse.getJSONArray("workflow_runs", new JSONArray());
        for (int j = 0; j < jWorkflowRuns.length(); j++)
            workflowRuns.add(new WorkflowRun(jWorkflowRuns.getJSONObject(j)));
    }

    /**
     * Method to get {@link #workflowRuns} instance <br>
     * Any params required
     *
     * @return {@link #workflowRuns} instance as {@link Collection} of {@link WorkflowRun}
     **/
    public Collection<WorkflowRun> getWorkflowRuns() {
        return workflowRuns;
    }

}
