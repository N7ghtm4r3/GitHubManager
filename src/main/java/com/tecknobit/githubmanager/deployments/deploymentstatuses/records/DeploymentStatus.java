package com.tecknobit.githubmanager.deployments.deploymentstatuses.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.deployments.records.DeploymentStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

/**
 * The {@code DeploymentStatus} class is useful to format a GitHub's deployment status
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/statuses#list-deployment-statuses">
 *             List deployment statuses</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/statuses#create-a-deployment-status">
 *             Create a deployment status</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/statuses#get-a-deployment-status">
 *             Get a deployment status</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see DeploymentStructure
 **/
public class DeploymentStatus extends DeploymentStructure {

    /**
     * {@code DeploymentState} list of available deployment states
     **/
    public enum DeploymentState {

        /**
         * {@code error} deployment state
         **/
        error,

        /**
         * {@code failure} deployment state
         **/
        failure,

        /**
         * {@code inactive} deployment state
         **/
        inactive,

        /**
         * {@code pending} deployment state
         **/
        pending,

        /**
         * {@code success} deployment state
         **/
        success,

        /**
         * {@code queued} deployment state
         **/
        queued,

        /**
         * {@code in_progress} deployment state
         **/
        in_progress

    }

    /**
     * {@code state} the state of the status
     **/
    private final DeploymentState state;

    /**
     * {@code deploymentUrl} the URL for accessing your deployment
     **/
    private final String deploymentUrl;

    /**
     * {@code environmentUrl} the URL for accessing your environment
     **/
    private final String environmentUrl;

    /**
     * {@code logUrl} the URL to associate with this status
     **/
    private final String logUrl;

    /**
     * Constructor to init a {@link DeploymentStatus}
     *
     * @param url                   :                   url of the deployment
     * @param id                    :                    deployment details as {@link JSONObject}
     * @param nodeId                :                node id of the deployment
     * @param environment           :           environment of the deployment
     * @param description           :           name for the target deployment environment
     * @param creator               :               creator of the deployment
     * @param createdAt             :             creation time of the deployment
     * @param updatedAt             :             update time of the deployment
     * @param repositoryUrl         :         repository url of the deployment
     * @param performedViaGitHubApp : GitHub app of the deployment
     * @param state                 :        the state of the status
     * @param deploymentUrl         :           the URL for accessing your deployment
     * @param environmentUrl        :         the URL for accessing your environment
     * @param logUrl                : the URL to associate with this status
     **/
    public DeploymentStatus(String url, long id, String nodeId, String environment, String description, User creator,
                            String createdAt, String updatedAt, String repositoryUrl, GitHubApp performedViaGitHubApp,
                            DeploymentState state, String deploymentUrl, String environmentUrl, String logUrl) {
        super(url, id, nodeId, environment, description, creator, createdAt, updatedAt, repositoryUrl, performedViaGitHubApp);
        this.state = state;
        this.deploymentUrl = deploymentUrl;
        this.environmentUrl = environmentUrl;
        this.logUrl = logUrl;
    }

    /**
     * Constructor to init a {@link DeploymentStatus}
     *
     * @param jDeploymentStatus : deployment status details as {@link JSONObject}
     **/
    public DeploymentStatus(JSONObject jDeploymentStatus) throws Exception {
        super(jDeploymentStatus);
        state = DeploymentState.valueOf(hResponse.getString("state"));
        deploymentUrl = hResponse.getString("deployment_url");
        environmentUrl = hResponse.getString("environment_url");
        logUrl = hResponse.getString("log_url");
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link DeploymentState}
     **/
    public DeploymentState getState() {
        return state;
    }

    /**
     * Method to get {@link #deploymentUrl} instance <br>
     * No-any params required
     *
     * @return {@link #deploymentUrl} instance as {@link String}
     **/
    public String getDeploymentUrl() {
        return deploymentUrl;
    }

    /**
     * Method to get {@link #environmentUrl} instance <br>
     * No-any params required
     *
     * @return {@link #environmentUrl} instance as {@link String}
     **/
    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    /**
     * Method to get {@link #logUrl} instance <br>
     * No-any params required
     *
     * @return {@link #logUrl} instance as {@link String}
     **/
    public String getLogUrl() {
        return logUrl;
    }

}
