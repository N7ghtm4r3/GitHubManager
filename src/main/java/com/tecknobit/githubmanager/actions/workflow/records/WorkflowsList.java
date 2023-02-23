package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code WorkflowsList} class is useful to format a GitHub's workflows list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflows#list-repository-workflows">
 * List repository workflows</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class WorkflowsList extends GitHubList {

    /**
     * {@code workflows} workflow list
     **/
    private final ArrayList<Workflow> workflows;

    /**
     * Constructor to init a {@link WorkflowsList}
     *
     * @param workflows: workflow list
     **/
    public WorkflowsList(ArrayList<Workflow> workflows) {
        super(workflows.size());
        this.workflows = workflows;
    }

    /**
     * Constructor to init an {@link WorkflowsList}
     *
     * @param totalCount : total number of the workflows in the list
     * @param workflows: workflow list
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

    /**
     * Method to get {@link #workflows} instance <br>
     * No-any params required
     *
     * @return {@link #workflows} instance as {@link ArrayList} of {@link Workflow}
     **/
    public ArrayList<Workflow> getWorkflows() {
        return workflows;
    }

}
