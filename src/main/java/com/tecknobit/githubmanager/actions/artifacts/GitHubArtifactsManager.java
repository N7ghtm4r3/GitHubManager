package com.tecknobit.githubmanager.actions.artifacts;

import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.artifacts.records.Artifact;
import com.tecknobit.githubmanager.actions.artifacts.records.ArtifactsList;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubArtifactsManager} class is useful to manage all GitHub's artifacts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#about-the-artifacts-api">
 * About the Artifacts API</a>
 * @see GitHubManager
 **/
public class GitHubArtifactsManager extends GitHubManager {

    /**
     * {@code REPOS_PATH} constant for {@code "repos/"} path
     **/
    public static final String REPOS_PATH = "repos/";

    /**
     * {@code ARTIFACTS_PATH} constant for {@code "artifacts/"} path
     **/
    public static final String ARTIFACTS_PATH = "artifacts/";

    /**
     * {@code QUERY_ARTIFACTS_PATH} constant for {@code "/artifacts"} path
     **/
    public static final String QUERY_ARTIFACTS_PATH = "/artifacts";

    /**
     * {@code ACTIONS_PATH} constant for {@code "/actions/"} path
     **/
    public static final String ACTIONS_PATH = "/actions/";

    /**
     * {@code RUNS_PATH} constant for {@code "/runs/"} path
     **/
    public static final String RUNS_PATH = "/runs/";

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubArtifactsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         : personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubArtifactsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    : personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubArtifactsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         : personal access token for authentication to {@code "GitHub"}
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

    /**
     * Method to get a list of all artifacts for a repository. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return artifacts list as {@link ArtifactsList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
     * List artifacts for a repository</a>
     **/
    public ArtifactsList getArtifactsList(String owner, String repo) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, ARTIFACTS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all artifacts for a repository. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return artifacts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
     * List artifacts for a repository</a>
     **/
    public <T> T getArtifactsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, ARTIFACTS_PATH), format);
    }

    /**
     * Method to get a list of all artifacts for a repository. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "name"} -> filters artifacts by exact match on their name field
     *                        </li>
     *                     </ul>
     * @return artifacts list as {@link ArtifactsList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
     * List artifacts for a repository</a>
     **/
    public ArtifactsList getArtifactsList(String owner, String repo, Params queryParams) throws IOException {
        return getArtifactsList(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all artifacts for a repository. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "name"} -> filters artifacts by exact match on their name field
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return artifacts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
     * List artifacts for a repository</a>
     **/
    public <T> T getArtifactsList(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, ARTIFACTS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to get a specific artifact for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param artifactId: the unique identifier of the artifact
     * @return artifact requested as {@link Artifact} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#get-an-artifact">
     * Get an artifact</a>
     **/
    public Artifact getArtifact(String owner, String repo, long artifactId) throws IOException {
        return getArtifact(owner, repo, artifactId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific artifact for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope. -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param artifactId: the unique identifier of the artifact
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return artifact requested as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#get-an-artifact">
     * Get an artifact</a>
     **/
    public <T> T getArtifact(String owner, String repo, long artifactId, ReturnFormat format) throws IOException {
        String artifactResponse = sendGetRequest(owner, repo, ARTIFACTS_PATH + artifactId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(artifactResponse);
            case LIBRARY_OBJECT:
                return (T) new Artifact(new JSONObject(artifactResponse));
            default:
                return (T) artifactResponse;
        }
    }

    /**
     * Method to delete an artifact for a workflow run. You must authenticate using an access token with the repo scope
     * to use this endpoint -> <b> this step is automatically made by this library, </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:            the account owner of the repository. The name is not case-sensitive
     * @param repo:             the name of the repository. The name is not case-sensitive
     * @param artifactToDelete: artifact to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#delete-an-artifact">
     * Delete an artifact</a>
     **/
    @WrappedRequest
    public boolean deleteArtifact(String owner, String repo, Artifact artifactToDelete) throws IOException {
        return deleteArtifact(owner, repo, artifactToDelete.getId());
    }

    /**
     * Method to delete an artifact for a workflow run. You must authenticate using an access token with the repo scope
     * to use this endpoint -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param artifactId: the unique identifier of the artifact
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#delete-an-artifact">
     * Delete an artifact</a>
     **/
    public boolean deleteArtifact(String owner, String repo, long artifactId) throws IOException {
        sendGetRequest(owner, repo, ARTIFACTS_PATH + artifactId);
        return apiRequest.getResponseStatusCode() == 204;
    }

    /**
     * Method to get a redirect URL to download an archive for a repository. This URL expires after 1 minute. Look for
     * {@code "Location:"} in the response header to find the URL for the download -> <b> this step is automatically made by this library. </b> <br>
     * The {@code ":archive_format"} must be zip -> <b> this step is automatically made by this library. </b> <br>
     * Anyone with read access to the repository can use this endpoint. If the repository is private you must use an access
     * token with the repo scope. -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param artifactId: the unique identifier of the artifact
     * @return redirect URL to download an archive for a repository as {@link String}
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#download-an-artifact">
     * Download an artifact</a>
     * @apiNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    public String downloadArtifact(String owner, String repo, long artifactId) throws IOException {
        sendGetRequest(owner, repo, ARTIFACTS_PATH + artifactId + "/zip");
        return new JsonHelper((JSONObject) apiRequest.getJSONResponse()).getString("Location");
    }

    /**
     * Method to get a list of the artifacts for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param runId: the unique identifier of the workflow run
     * @return list of the artifacts as {@link ArtifactsList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
     * List workflow run artifacts</a>
     **/
    public ArtifactsList getWorkflowRunArtifactsList(String owner, String repo, long runId) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, RUNS_PATH + runId + QUERY_ARTIFACTS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the artifacts for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint.
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runId:  the unique identifier of the workflow run
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return artifacts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
     * List workflow run artifacts</a>
     **/
    public <T> T getWorkflowRunArtifactsList(String owner, String repo, long runId, ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, RUNS_PATH + runId + QUERY_ARTIFACTS_PATH), format);
    }

    /**
     * Method to get a list of the artifacts for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param runId:       the unique identifier of the workflow run
     * @return list of the artifacts as {@link ArtifactsList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
     * List workflow run artifacts</a>
     **/
    public ArtifactsList getWorkflowRunArtifactsList(String owner, String repo, long runId,
                                                     Params queryParams) throws IOException {
        return getWorkflowRunArtifactsList(owner, repo, runId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the artifacts for a workflow run. Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param runId:       the unique identifier of the workflow run
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return artifacts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
     * List workflow run artifacts</a>
     **/
    public <T> T getWorkflowRunArtifactsList(String owner, String repo, long runId, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnArtifactsList(sendGetRequest(owner, repo, RUNS_PATH + runId + QUERY_ARTIFACTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create an artifacts list
     *
     * @param artifactsListResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return artifacts list as {@code "format"} defines
     **/
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

    /**
     * Method to send a {@code "GET"} request to {@code "GitHub"}
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @apiNote is adapted to {@link GitHubArtifactsManager}
     **/
    public String sendGetRequest(String owner, String repo, String endpoint) throws IOException {
        return super.sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_PATH + endpoint);
    }

}
