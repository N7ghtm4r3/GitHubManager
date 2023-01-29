package com.tecknobit.githubmanager.deployments.deployments.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

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
 **/
public class Deployment extends GitHubResponse {

    /**
     * {@code url} of the deployment
     **/
    private final String url;

    /**
     * {@code id} unique identifier of the deployment
     **/
    private final long id;

    /**
     * {@code nodeId} node id of the deployment
     **/
    private final String nodeId;

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
     * {@code environment} of the deployment
     **/
    private final String environment;

    /**
     * {@code description} name for the target deployment environment
     **/
    private final String description;

    /**
     * {@code creator} of the deployment
     **/
    private final User creator;

    /**
     * {@code createdAt creation time of the deployment
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update time of the deployment
     **/
    private final String updatedAt;

    /**
     * {@code statusesUrl} statuses url of the deployment
     **/
    private final String statusesUrl;

    /**
     * {@code repositoryUrl} repository url of the deployment
     **/
    private final String repositoryUrl;

    /**
     * {@code transientEnvironment} specifies if the given environment is will no longer exist at some point in the future
     **/
    private final boolean transientEnvironment;

    /**
     * {@code productionEnvironment} specifies if the given environment is one that end-users directly interact with
     **/
    private final boolean productionEnvironment;

    /**
     * {@code performedViaGitHubApp} GitHub app of the deployment
     **/
    private final GitHubApp performedViaGitHubApp;

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
        super(null);
        this.url = url;
        this.id = id;
        this.nodeId = nodeId;
        this.sha = sha;
        this.ref = ref;
        this.task = task;
        this.payload = payload;
        this.originalEnvironment = originalEnvironment;
        this.environment = environment;
        this.description = description;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.statusesUrl = statusesUrl;
        this.repositoryUrl = repositoryUrl;
        this.transientEnvironment = transientEnvironment;
        this.productionEnvironment = productionEnvironment;
        this.performedViaGitHubApp = performedViaGitHubApp;
    }

    /**
     * Constructor to init a {@link Deployment}
     *
     * @param jDeployment: deployment details as {@link JSONObject}
     **/
    public Deployment(JSONObject jDeployment) throws Exception {
        super(jDeployment);
        url = hResponse.getString("url");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        sha = hResponse.getString("sha");
        ref = hResponse.getString("ref");
        task = hResponse.getString("task");
        payload = hResponse.get("payload");
        originalEnvironment = hResponse.getString("original_environment");
        environment = hResponse.getString("environment");
        description = hResponse.getString("description");
        creator = new User(hResponse.getJSONObject("creator"));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        statusesUrl = hResponse.getString("statuses_url");
        repositoryUrl = hResponse.getString("repository_url");
        transientEnvironment = hResponse.getBoolean("transient_environment");
        productionEnvironment = hResponse.getBoolean("production_environment");
        JSONObject jApp = hResponse.getJSONObject("performed_via_github_app");
        if (jApp != null)
            performedViaGitHubApp = new GitHubApp(jApp);
        else
            performedViaGitHubApp = null;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #environment} instance <br>
     * No-any params required
     *
     * @return {@link #environment} instance as {@link String}
     **/
    public String getEnvironment() {
        return environment;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #creator} instance <br>
     * No-any params required
     *
     * @return {@link #creator} instance as {@link User}
     **/
    public User getCreator() {
        return creator;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
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
     * Method to get {@link #repositoryUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryUrl} instance as {@link String}
     **/
    public String getRepositoryUrl() {
        return repositoryUrl;
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

    /**
     * Method to get {@link #performedViaGitHubApp} instance <br>
     * No-any params required
     *
     * @return {@link #performedViaGitHubApp} instance as {@link GitHubApp}
     **/
    public GitHubApp getPerformedViaGitHubApp() {
        return performedViaGitHubApp;
    }

}
