package com.tecknobit.githubmanager.deployments.deploymentbranchpolicies.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code DeploymentBranchPoliciesList} class is useful to format a GitHub's deployment branch policies
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/branch-policies#list-deployment-branch-policies">
 * List deployment branch policies</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class DeploymentBranchPoliciesList extends GitHubList {

    /**
     * {@code deploymentBranchPolicies} list of {@link DeploymentBranchPolicy}
     **/
    private final ArrayList<DeploymentBranchPolicy> deploymentBranchPolicies;

    /**
     * Constructor to init an {@link DeploymentBranchPoliciesList}
     *
     * @param deploymentBranchPolicies: list of {@link DeploymentBranchPolicy}
     **/
    public DeploymentBranchPoliciesList(ArrayList<DeploymentBranchPolicy> deploymentBranchPolicies) {
        super(deploymentBranchPolicies.size());
        this.deploymentBranchPolicies = deploymentBranchPolicies;
    }

    /**
     * Constructor to init an {@link DeploymentBranchPoliciesList}
     *
     * @param totalCount                : total number of the items in the list
     * @param deploymentBranchPolicies: list of {@link DeploymentBranchPolicy}
     **/
    public DeploymentBranchPoliciesList(int totalCount, ArrayList<DeploymentBranchPolicy> deploymentBranchPolicies) {
        super(totalCount);
        this.deploymentBranchPolicies = deploymentBranchPolicies;
    }

    /**
     * Constructor to init a {@link DeploymentBranchPoliciesList}
     *
     * @param jDeploymentBranchPolicies : deployment branch policies as {@link JSONObject}
     **/
    public DeploymentBranchPoliciesList(JSONObject jDeploymentBranchPolicies) {
        super(jDeploymentBranchPolicies);
        deploymentBranchPolicies = new ArrayList<>();
        JSONArray jPolicies = hResponse.getJSONArray("branch_policies", new JSONArray());
        for (int j = 0; j < jPolicies.length(); j++)
            deploymentBranchPolicies.add(new DeploymentBranchPolicy(jPolicies.getJSONObject(j)));
    }

    /**
     * Method to get {@link #deploymentBranchPolicies} instance <br>
     * No-any params required
     *
     * @return {@link #deploymentBranchPolicies} instance as {@link ArrayList} of {@link DeploymentBranchPolicy}
     **/
    public ArrayList<DeploymentBranchPolicy> getDeploymentBranchPolicies() {
        return deploymentBranchPolicies;
    }

}
