package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.User;
import org.json.JSONObject;

import java.text.ParseException;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class DeploymentReview {

    private final String url;
    private final long id;
    private final String nodeId;
    private final String sha;
    private final String ref;
    private final String task;
    private final Object payload;
    private final String originalEnvironment;
    private final String environment;
    private final String description;
    private final User creator;
    private final String createdAt;
    private final String updatedAt;
    private final String statusesUrl;
    private final String repositoryUrl;
    private final boolean transientEnvironment;
    private final boolean productionEnvironment;

    public DeploymentReview(String url, long id, String nodeId, String sha, String ref, String task, Object payload,
                            String originalEnvironment, String environment, String description, User creator, String createdAt,
                            String updatedAt, String statusesUrl, String repositoryUrl, boolean transientEnvironment,
                            boolean productionEnvironment) {
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

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getSha() {
        return sha;
    }

    public String getRef() {
        return ref;
    }

    public String getTask() {
        return task;
    }

    public <T> T getPayload() {
        return (T) payload;
    }

    public String getOriginalEnvironment() {
        return originalEnvironment;
    }

    public String getDescription() {
        return description;
    }

    public User getCreator() {
        return creator;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        try {
            return dateFormatter.parse(createdAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        try {
            return dateFormatter.parse(updatedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public boolean isTransientEnvironment() {
        return transientEnvironment;
    }

    public boolean isProductionEnvironment() {
        return productionEnvironment;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
