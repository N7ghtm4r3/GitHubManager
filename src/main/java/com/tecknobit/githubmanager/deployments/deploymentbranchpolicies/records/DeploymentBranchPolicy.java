package com.tecknobit.githubmanager.deployments.deploymentbranchpolicies.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code DeploymentBranchPolicy} class is useful to format a GitHub's deployment branch policy
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/branch-policies#create-a-deployment-branch-policy">
 *             Create a deployment branch policy</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/branch-policies#get-a-deployment-branch-policy">
 *             Get a deployment branch policy</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/branch-policies#update-a-deployment-branch-policy">
 *            Update a deployment branch policy</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class DeploymentBranchPolicy extends GitHubResponse {

    /**
     * {@code id} the unique identifier of the branch policy
     **/
    private final long id;

    /**
     * {@code nodeId} the node identifier of the branch policy
     **/
    private final String nodeId;

    /**
     * {@code name} the name pattern that branches must match in order to deploy to the environment
     **/
    private final String name;

    /**
     * Constructor to init a {@link DeploymentBranchPolicy}
     *
     * @param id     : the unique identifier of the branch policy
     * @param nodeId : the node identifier of the branch policy
     * @param name   : the name pattern that branches must match in order to deploy to the environment
     **/
    public DeploymentBranchPolicy(long id, String nodeId, String name) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.name = name;
    }

    /**
     * Constructor to init a {@link DeploymentBranchPolicy}
     *
     * @param jDeploymentBranchPolicy : deployment branch policy details as {@link JSONObject}
     **/
    public DeploymentBranchPolicy(JSONObject jDeploymentBranchPolicy) {
        super(jDeploymentBranchPolicy);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        name = hResponse.getString("name");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

}
