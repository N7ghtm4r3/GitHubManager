package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorkflowRunsList extends GitHubList {

    private final ArrayList<WorkflowRun> workflowRuns;

    public WorkflowRunsList(ArrayList<WorkflowRun> workflowRuns) {
        super(workflowRuns.size());
        this.workflowRuns = workflowRuns;
    }

    /**
     * Constructor to init an {@link WorkflowRunsList}
     *
     * @param totalCount : total number of the workflow runs in the list
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

    public Collection<WorkflowRun> getWorkflowRuns() {
        return workflowRuns;
    }

}
