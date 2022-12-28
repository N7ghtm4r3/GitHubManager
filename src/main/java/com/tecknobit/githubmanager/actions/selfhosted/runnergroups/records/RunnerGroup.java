package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.githubmanager.GitHubManager.Visibility;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.Visibility.vPrivate;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.valueOf;

/**
 * The {@code RunnerGroup} class is useful to format a GitHub's runner group
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-enterprise">
 *             Create a self-hosted runner group for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-enterprise">
 *             Get a self-hosted runner group for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-enterprise">
 *             Update a self-hosted runner group for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
 *             Create a self-hosted runner group for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-organization">
 *             Get a self-hosted runner group for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
 *             Update a self-hosted runner group for an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class RunnerGroup extends GitHubResponse {

    /**
     * {@code id} the id of the runner group
     **/
    private final long id;

    /**
     * {@code name} the name of the runner group
     **/
    private final String name;

    /**
     * {@code visibility} the visibility of the runner group
     **/
    private final Visibility visibility;

    /**
     * {@code isDefault} flag to indicate if this runner group is the default
     **/
    private final boolean isDefault;

    /**
     * {@code runnersUrl} runners url value
     **/
    private final String runnersUrl;

    /**
     * {@code allowsPublicRepositories} flag to indicate if this runner group allows public repositories
     **/
    private final boolean allowsPublicRepositories;

    /**
     * {@code restrictedToWorkflows} if {@code "true"}, the runner group will be restricted to running only the workflows
     * specified in the {@code "selected_workflows"} array
     **/
    private final boolean restrictedToWorkflows;

    /**
     * {@code selectedWorkFlows} list of workflows the runner group should be allowed to run
     **/
    private final ArrayList<String> selectedWorkFlows;

    /**
     * {@code workflowRestrictionsReadOnly} if {@code "true"}, the {@code "restricted_to_workflows"} and {@code "selected_workflows"}
     * fields cannot be modified
     **/
    private final boolean workflowRestrictionsReadOnly;

    /**
     * Constructor to init a {@link RunnerGroup}
     *
     * @param id:the                        id of the runner group
     * @param name:                         the name of the runner group
     * @param visibility:                   the visibility of the runner group
     * @param isDefault:                    flag to indicate if this runner group is the default
     * @param runnersUrl:                   runners url value
     * @param allowsPublicRepositories:     flag to indicate if this runner group allows public repositories
     * @param restrictedToWorkflows:if      {@code "true"}, the runner group will be restricted to running only the workflows
     *                                      specified in the {@code "selected_workflows"} array
     * @param selectedWorkFlows:            list of workflows the runner group should be allowed to run
     * @param workflowRestrictionsReadOnly: if {@code "true"}, the {@code "restricted_to_workflows"} and {@code "selected_workflows"}
     *                                      fields cannot be modified
     **/
    public RunnerGroup(long id, String name, Visibility visibility, boolean isDefault, String runnersUrl,
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

    /**
     * Constructor to init a {@link RunnerGroup}
     *
     * @param jRunnerGroup: runner group details as {@link JSONObject}
     **/
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

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * Any params required
     *
     * @return {@link #visibility} instance as {@link Visibility}
     **/
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * Method to get {@link #isDefault} instance <br>
     * Any params required
     *
     * @return {@link #isDefault} instance as boolean
     **/
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Method to get {@link #runnersUrl} instance <br>
     * Any params required
     *
     * @return {@link #runnersUrl} instance as {@link String}
     **/
    public String getRunnersUrl() {
        return runnersUrl;
    }

    /**
     * Method to get {@link #allowsPublicRepositories} instance <br>
     * Any params required
     *
     * @return {@link #allowsPublicRepositories} instance as boolean
     **/
    public boolean areAllowedPublicRepositories() {
        return allowsPublicRepositories;
    }

    /**
     * Method to get {@link #restrictedToWorkflows} instance <br>
     * Any params required
     *
     * @return {@link #restrictedToWorkflows} instance as boolean
     **/
    public boolean isRestrictedToWorkflows() {
        return restrictedToWorkflows;
    }

    /**
     * Method to get {@link #selectedWorkFlows} instance <br>
     * Any params required
     *
     * @return {@link #selectedWorkFlows} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getSelectedWorkFlows() {
        return selectedWorkFlows;
    }

    /**
     * Method to get {@link #workflowRestrictionsReadOnly} instance <br>
     * Any params required
     *
     * @return {@link #workflowRestrictionsReadOnly} instance as boolean
     **/
    public boolean isWorkflowRestrictionsReadOnly() {
        return workflowRestrictionsReadOnly;
    }

}
