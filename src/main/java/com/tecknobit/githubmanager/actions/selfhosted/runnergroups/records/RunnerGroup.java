package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.Visibility.vPrivate;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.valueOf;

public class RunnerGroup extends GitHubResponse {

    private final long id;
    private final String name;
    private final GitHubManager.Visibility visibility;
    private final boolean isDefault;
    private final String runnersUrl;
    private final boolean allowsPublicRepositories;
    private final boolean restrictedToWorkflows;
    private final ArrayList<String> selectedWorkFlows;
    private final boolean workflowRestrictionsReadOnly;

    public RunnerGroup(long id, String name, GitHubManager.Visibility visibility, boolean isDefault, String runnersUrl,
                       boolean allowsPublicRepositories, boolean restrictedToWorkflows, ArrayList<String> selectedWorkFlows,
                       boolean workflowRestrictionsReadOnly) {
        super(null);
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.isDefault = isDefault;
        this.runnersUrl = runnersUrl;
        this.allowsPublicRepositories = allowsPublicRepositories;
        this.restrictedToWorkflows = restrictedToWorkflows;
        this.selectedWorkFlows = selectedWorkFlows;
        this.workflowRestrictionsReadOnly = workflowRestrictionsReadOnly;
    }

    public RunnerGroup(JSONObject jRunnerGroup) {
        super(jRunnerGroup);
        id = hResponse.getLong("id", 0);
        name = hResponse.getString("name");
        String visibilityKey = hResponse.getString("visibility", vPrivate.name());
        if (visibilityKey.equals(vPrivate.toString()))
            visibility = vPrivate;
        else
            visibility = valueOf(visibilityKey);
        isDefault = hResponse.getBoolean("default");
        runnersUrl = hResponse.getString("runners_url");
        allowsPublicRepositories = hResponse.getBoolean("allows_public_repositories");
        restrictedToWorkflows = hResponse.getBoolean("restricted_to_workflows");
        selectedWorkFlows = new ArrayList<>();
        JSONArray jWorkflows = hResponse.getJSONArray("selected_workflows", new JSONArray());
        for (int j = 0; j < jWorkflows.length(); j++)
            selectedWorkFlows.add(jWorkflows.getString(j));
        workflowRestrictionsReadOnly = hResponse.getBoolean("workflow_restrictions_read_only");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GitHubManager.Visibility getVisibility() {
        return visibility;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getRunnersUrl() {
        return runnersUrl;
    }

    public boolean isAllowsPublicRepositories() {
        return allowsPublicRepositories;
    }

    public boolean isRestrictedToWorkflows() {
        return restrictedToWorkflows;
    }

    public ArrayList<String> getSelectedWorkFlows() {
        return selectedWorkFlows;
    }

    public boolean isWorkflowRestrictionsReadOnly() {
        return workflowRestrictionsReadOnly;
    }

}
