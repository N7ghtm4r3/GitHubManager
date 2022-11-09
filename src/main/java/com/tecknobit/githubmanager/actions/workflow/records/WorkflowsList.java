package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorkflowsList extends GitHubList {

    private final ArrayList<Workflow> workflows;

    public WorkflowsList(ArrayList<Workflow> workflows) {
        super(workflows.size());
        this.workflows = workflows;
    }

    /**
     * Constructor to init an {@link WorkflowsList}
     *
     * @param totalCount : total number of the workflows in the list
     **/
    public WorkflowsList(int totalCount, ArrayList<Workflow> workflows) {
        super(totalCount);
        this.workflows = workflows;
    }

    /**
     * Constructor to init a {@link WorkflowsList}
     *
     * @param jWorkflowsList : workflows list details as {@link JSONObject}
     **/
    public WorkflowsList(JSONObject jWorkflowsList) {
        super(jWorkflowsList);
        workflows = new ArrayList<>();
        JSONArray jWorkflows = hResponse.getJSONArray("workflows", new JSONArray());
        for (int j = 0; j < jWorkflows.length(); j++)
            workflows.add(new Workflow(jWorkflows.getJSONObject(j)));
    }

    public Collection<Workflow> getWorkflows() {
        return workflows;
    }

}
