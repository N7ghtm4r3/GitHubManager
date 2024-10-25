package com.tecknobit.githubmanager.deployments.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;



/**
 * The {@code DeploymentStructure} class is useful to format a GitHub's deployment item structure
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
 **/
public class DeploymentStructure extends GitHubResponse {

    /**
     * {@code url} of the deployment
     **/
    protected final String url;

    /**
     * {@code id} unique identifier of the deployment
     **/
    protected final long id;

    /**
     * {@code nodeId} node id of the deployment
     **/
    protected final String nodeId;

    /**
     * {@code creator} of the deployment
     **/
    protected final User creator;

    /**
     * {@code description} name for the target deployment environment
     **/
    protected final String description;

    /**
     * {@code environment} of the deployment
     **/
    protected final String environment;

    /**
     * {@code createdAt creation time of the deployment
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} update time of the deployment
     **/
    protected final String updatedAt;

    /**
     * {@code repositoryUrl} repository url of the deployment
     **/
    protected final String repositoryUrl;

    /**
     * {@code performedViaGitHubApp} GitHub app of the deployment
     **/
    protected final GitHubApp performedViaGitHubApp;

    /**
     * Constructor to init a {@link DeploymentStructure}
     *
     * @param url:                   url of the deployment
     * @param id:                    deployment details as {@link JSONObject}
     * @param nodeId:                node id of the deployment
     * @param environment:           environment of the deployment
     * @param description:           name for the target deployment environment
     * @param creator:               creator of the deployment
     * @param createdAt:             creation time of the deployment
     * @param updatedAt:             update time of the deployment
     * @param repositoryUrl:         repository url of the deployment
     * @param performedViaGitHubApp: GitHub app of the deployment
     **/
    public DeploymentStructure(String url, long id, String nodeId, String environment, String description, User creator,
                               String createdAt, String updatedAt, String repositoryUrl, GitHubApp performedViaGitHubApp) {
        super(null);
        this.url = url;
        this.id = id;
        this.nodeId = nodeId;
        this.environment = environment;
        this.description = description;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.repositoryUrl = repositoryUrl;
        this.performedViaGitHubApp = performedViaGitHubApp;
    }

    /**
     * Constructor to init a {@link DeploymentStructure}
     *
     * @param jDeploymentStructure : deployment structure details as {@link JSONObject}
     **/
    public DeploymentStructure(JSONObject jDeploymentStructure) throws Exception {
        super(jDeploymentStructure);
        url = hResponse.getString("url");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        environment = hResponse.getString("environment");
        description = hResponse.getString("description");
        creator = new User(hResponse.getJSONObject("creator"));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        repositoryUrl = hResponse.getString("repository_url");
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
        return timeFormatter.formatAsTimestamp(createdAt);
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
        return timeFormatter.formatAsTimestamp(updatedAt);
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
     * Method to get {@link #performedViaGitHubApp} instance <br>
     * No-any params required
     *
     * @return {@link #performedViaGitHubApp} instance as {@link GitHubApp}
     **/
    public GitHubApp getPerformedViaGitHubApp() {
        return performedViaGitHubApp;
    }

}
