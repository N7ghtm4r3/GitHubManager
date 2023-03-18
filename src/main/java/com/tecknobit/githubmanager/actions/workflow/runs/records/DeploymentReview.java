package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code DeploymentReview} class is useful to format a GitHub's deployment review
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/actions/workflow-runs#review-pending-deployments-for-a-workflow-run">
 * Review pending deployments for a workflow run</a>
 * @see GitHubResponse
 * @see Review.Environment
 **/
public class DeploymentReview {

    /**
     * {@code "url"} value
     **/
    private final String url;

    /**
     * {@code id} unique identifier of the deployment
     **/
    private final long id;

    /**
     * {@code nodeId} identifier of the node value
     **/
    private final String nodeId;

    /**
     * {@code "sha"} value
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
     * {@code "payload"} value
     **/
    private final Object payload;

    /**
     * {@code originalEnvironment} original environment value
     **/
    private final String originalEnvironment;

    /**
     * {@code environment} name for the target deployment environment
     **/
    private final String environment;

    /**
     * {@code "description"} value
     **/
    private final String description;

    /**
     * {@code "creator"} value
     **/
    private final User creator;

    /**
     * {@code "createdAt"} value
     **/
    private final String createdAt;

    /**
     * {@code "updatedAt"} value
     **/
    private final String updatedAt;

    /**
     * {@code statusesUrl} statuses url value
     **/
    private final String statusesUrl;

    /**
     * {@code repositoryUrl} repository url value
     **/
    private final String repositoryUrl;

    /**
     * {@code transientEnvironment} specifies if the given environment is will no longer exist at some point in the future. Default: {@code "false"}
     **/
    private final boolean transientEnvironment;

    /**
     * {@code productionEnvironment} specifies if the given environment is one that end-users directly interact with. Default: {@code "false"}
     **/
    private final boolean productionEnvironment;

    /**
     * Constructor to init a {@link DeploymentReview}
     *
     * @param url:                   url value
     * @param id:                    unique identifier of the deployment
     * @param nodeId:                identifier of the node value
     * @param sha:                   sha value
     * @param ref:                   the ref to deploy. This can be a branch, tag, or sha
     * @param task:                  parameter to specify a task to execute
     * @param payload:               payload value
     * @param originalEnvironment:   original environment value
     * @param environment:           name for the target deployment environment
     * @param description:           description value
     * @param creator:               creator value
     * @param createdAt:             created at value
     * @param updatedAt:             updated at value
     * @param statusesUrl:           statuses url value
     * @param repositoryUrl:         repository url value
     * @param transientEnvironment:  specifies if the given environment is will no longer exist at some point in the future. Default: {@code "false"}
     * @param productionEnvironment: specifies if the given environment is one that end-users directly interact with. Default: {@code "false"}
     **/
    public DeploymentReview(String url, long id, String nodeId, String sha, String ref, String task, Object payload,
                            String originalEnvironment, String environment, String description, User creator,
                            String createdAt, String updatedAt, String statusesUrl, String repositoryUrl,
                            boolean transientEnvironment, boolean productionEnvironment) {
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
    }

    /**
     * Constructor to init a {@link DeploymentReview}
     *
     * @param jDeploymentReview: deployment review details as {@link JSONObject}
     **/
    public DeploymentReview(JSONObject jDeploymentReview) {
        JsonHelper hDeploymentReview = new JsonHelper(jDeploymentReview);
        url = hDeploymentReview.getString("url");
        id = hDeploymentReview.getLong("id", 0);
        nodeId = hDeploymentReview.getString("node_id");
        sha = hDeploymentReview.getString("sha");
        ref = hDeploymentReview.getString("ref");
        task = hDeploymentReview.getString("task");
        payload = hDeploymentReview.get("payload");
        originalEnvironment = hDeploymentReview.getString("original_environment");
        environment = hDeploymentReview.getString("environment");
        description = hDeploymentReview.getString("description");
        creator = new User(hDeploymentReview.getJSONObject("creator", new JSONObject()));
        createdAt = hDeploymentReview.getString("created_at");
        updatedAt = hDeploymentReview.getString("updated_at");
        statusesUrl = hDeploymentReview.getString("statuses_url");
        repositoryUrl = hDeploymentReview.getString("repository_url");
        transientEnvironment = hDeploymentReview.getBoolean("transient_environment");
        productionEnvironment = hDeploymentReview.getBoolean("production_environment");
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
     * @return {@link #payload} instance as {@link T}
     **/
    public <T> T getPayload() {
        return (T) payload;
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
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
