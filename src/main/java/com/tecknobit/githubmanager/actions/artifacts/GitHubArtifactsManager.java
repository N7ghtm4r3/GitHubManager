package com.tecknobit.githubmanager.actions.artifacts;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.artifacts.records.Artifact;
import com.tecknobit.githubmanager.actions.artifacts.records.ArtifactsList;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

public class GitHubArtifactsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to GitHub
     **/
    public GitHubArtifactsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         : personal access token for authentication to GitHub
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubArtifactsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    : personal access token for authentication to GitHub
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubArtifactsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         : personal access token for authentication to GitHub
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      : custom timeout for request
     **/
    public GitHubArtifactsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubArtifactsManager() {
        super();
    }

    public ArtifactsList getArtifactsList(String owner, String repo) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, "artifacts"), LIBRARY_OBJECT);
    }

    public <T> T getArtifactsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, "artifacts"), format);
    }

    public ArtifactsList getArtifactsList(String owner, String repo, Params queryParams) throws IOException {
        return getArtifactsList(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getArtifactsList(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, "artifacts" + queryParams.createQueryString()),
                format);
    }

    private <T> T returnArtifactsList(String artifactsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(artifactsListResponse);
            case LIBRARY_OBJECT:
                return (T) new ArtifactsList(new JSONObject(artifactsListResponse));
            default:
                return (T) artifactsListResponse;
        }
    }

    public Artifact getArtifact(String owner, String repo, long artifactId) throws IOException {
        return getArtifact(owner, repo, artifactId, LIBRARY_OBJECT);
    }

    public <T> T getArtifact(String owner, String repo, long artifactId, ReturnFormat format) throws IOException {
        String artifactResponse = sendGetRequest(owner, repo, "artifacts/" + artifactId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(artifactResponse);
            case LIBRARY_OBJECT:
                return (T) new Artifact(new JSONObject(artifactResponse));
            default:
                return (T) artifactResponse;
        }
    }

    public String sendGetRequest(String owner, String repo, String endpoint) throws IOException {
        return super.sendGetRequest("repos/" + owner + "/" + repo + "/actions/" + endpoint);
    }

}
