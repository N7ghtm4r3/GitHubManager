package com.tecknobit.githubmanager.deployments.deployments.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.deployments.records.DeploymentStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

/**
 * The {@code Deployment} class is useful to format a GitHub's deployment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/deployments#list-deployments">
 *             List deployments</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/deployments#create-a-deployment">
 *             Create a deployment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/deployments#get-a-deployment">
 *             Get a deployment</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see DeploymentStructure
 **/
public class Deployment extends DeploymentStructure {

    /**
     * {@code sha} of the deployment
     **/
    private final String sha;

    /**
     * {@code ref} the ref to deploy. This can be a branch, tag, or sha
     **/
    private final String ref;

    /**
     * {@code task} parameter to specify a task to execute
     **/
    private final String task;

    /**
     * {@code payload} of the deployment
     **/
    private final Object payload;

    /**
     * {@code originalEnvironment} original environment of the deployment
     **/
    private final String originalEnvironment;

    /**
     * {@code statusesUrl} statuses url of the deployment
     **/
    private final String statusesUrl;

    /**
     * {@code transientEnvironment} specifies if the given environment is will no longer exist at some point in the future
     **/
    private final boolean transientEnvironment;

    /**
     * {@code productionEnvironment} specifies if the given environment is one that end-users directly interact with
     **/
    private final boolean productionEnvironment;

    /**
     * Constructor to init a {@link Deployment}
     *
     * @param url:                   url of the deployment
     * @param id:                    deployment details as {@link JSONObject}
     * @param nodeId:                node id of the deployment
     * @param sha:                   sha of the deployment
     * @param ref:                   the ref to deploy. This can be a branch, tag, or sha
     * @param task:                  parameter to specify a task to execute
     * @param payload:               payload of the deployment
     * @param originalEnvironment:   original environment of the deployment
     * @param environment:           environment of the deployment
     * @param description:           name for the target deployment environment
     * @param creator:               creator of the deployment
     * @param createdAt:             creation time of the deployment
     * @param updatedAt:             update time of the deployment
     * @param statusesUrl:           statuses url of the deployment
     * @param repositoryUrl:         repository url of the deployment
     * @param transientEnvironment:  specifies if the given environment is will no longer exist at some point in the future
     * @param productionEnvironment: specifies if the given environment is one that end-users directly interact with
     * @param performedViaGitHubApp: GitHub app of the deployment
     **/
    public Deployment(String url, long id, String nodeId, String sha, String ref, String task, Object payload,
                      String originalEnvironment, String environment, String description, User creator, String createdAt,
                      String updatedAt, String statusesUrl, String repositoryUrl, boolean transientEnvironment,
                      boolean productionEnvironment, GitHubApp performedViaGitHubApp) {
        super(url, id, nodeId, environment, description, creator, createdAt, updatedAt, repositoryUrl,
                performedViaGitHubApp);
        this.sha = sha;
        this.ref = ref;
        this.task = task;
        this.payload = payload;
        this.originalEnvironment = originalEnvironment;
        this.statusesUrl = statusesUrl;
        this.transientEnvironment = transientEnvironment;
        this.productionEnvironment = productionEnvironment;
    }

    /**
     * Constructor to init a {@link Deployment}
     *
     * @param jDeployment: deployment details as {@link JSONObject}
     **/
    public Deployment(JSONObject jDeployment) throws Exception {
        super(jDeployment);
        sha = hResponse.getString("sha");
        ref = hResponse.getString("ref");
        task = hResponse.getString("task");
        payload = hResponse.get("payload");
        originalEnvironment = hResponse.getString("original_environment");
        statusesUrl = hResponse.getString("statuses_url");
        transientEnvironment = hResponse.getBoolean("transient_environment");
        productionEnvironment = hResponse.getBoolean("production_environment");
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
    }

    /**
     * Method to get {@link #ref} instance <br>
     * No-any params required
     *
     * @return {@link #ref} instance as {@link String}
     **/
    public String getRef() {
        return ref;
    }

    /**
     * Method to get {@link #task} instance <br>
     * No-any params required
     *
     * @return {@link #task} instance as {@link String}
     **/
    public String getTask() {
        return task;
    }

    /**
     * Method to get {@link #payload} instance <br>
     * No-any params required
     *
     * @return {@link #payload} instance as {@link Object}
     **/
    public Object getPayload() {
        return payload;
    }

    /**
     * Method to get {@link #originalEnvironment} instance <br>
     * No-any params required
     *
     * @return {@link #originalEnvironment} instance as {@link String}
     **/
    public String getOriginalEnvironment() {
        return originalEnvironment;
    }

    /**
     * Method to get {@link #statusesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #statusesUrl} instance as {@link String}
     **/
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * Method to get {@link #transientEnvironment} instance <br>
     * No-any params required
     *
     * @return {@link #transientEnvironment} instance as boolean
     **/
    public boolean isTransientEnvironment() {
        return transientEnvironment;
    }

    /**
     * Method to get {@link #productionEnvironment} instance <br>
     * No-any params required
     *
     * @return {@link #productionEnvironment} instance as boolean
     **/
    public boolean isProductionEnvironment() {
        return productionEnvironment;
    }

}
