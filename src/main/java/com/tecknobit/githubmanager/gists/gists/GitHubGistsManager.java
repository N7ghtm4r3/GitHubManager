package com.tecknobit.githubmanager.gists.gists;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gists.gists.records.Gist;
import com.tecknobit.githubmanager.gists.gists.records.Gist.GistFile;
import com.tecknobit.githubmanager.gists.gists.records.GistCommit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.activity.starring.GitHubStarringManager.STARRED_PATH;

/**
 * The {@code GitHubGistsManager} class is useful to manage all GitHub's gists endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists">
 * Gists</a>
 * @see GitHubManager
 **/
public class GitHubGistsManager extends GitHubManager {

    /**
     * {@code GISTS_PATH} constant for {@code "gists"} path
     **/
    public static final String GISTS_PATH = "gists";

    /**
     * {@code GISTS_PUBLIC_PATH} constant for {@code "gists/public"} path
     **/
    public static final String GISTS_PUBLIC_PATH = "gists/public";

    /**
     * {@code GISTS_STARRED_PATH} constant for {@code "gists/starred"} path
     **/
    public static final String GISTS_STARRED_PATH = "gists" + STARRED_PATH;

    /**
     * {@code FORKS_PATH} constant for {@code "/forks"} path
     **/
    public static final String FORKS_PATH = "/forks";

    /**
     * {@code STAR_PATH} constant for {@code "/star"} path
     **/
    public static final String STAR_PATH = "/star";

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGistsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGistsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGistsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGistsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager} <br>
     * No-any params required
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
    public GitHubGistsManager() {
        super();
    }

    public Collection<Gist> getAuthenticatedUserGists() throws IOException {
        return getAuthenticatedUserGists(LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH), format);
    }

    public Collection<Gist> getAuthenticatedUserGists(Params queryParams) throws IOException {
        return getAuthenticatedUserGists(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + queryParams.createQueryString()), format);
    }

    public Gist createGist(String key, String content) throws IOException {
        return createGist(key, content, LIBRARY_OBJECT);
    }

    public <T> T createGist(String key, String content, ReturnFormat format) throws IOException {
        return createGist(key, content, null, format);
    }

    public Gist createGist(String key, String content, Params bodyParams) throws IOException {
        return createGist(key, content, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createGist(String key, String content, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("files", new JSONObject().put(key, new JSONObject().put("content", content)));
        return returnGist(sendPostRequest(GISTS_PATH, bodyParams), format);
    }

    public Collection<Gist> getPublicGists() throws IOException {
        return getPublicGists(LIBRARY_OBJECT);
    }

    public <T> T getPublicGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PUBLIC_PATH), format);
    }

    public Collection<Gist> getPublicGists(Params queryParams) throws IOException {
        return getPublicGists(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getPublicGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PUBLIC_PATH + queryParams.createQueryString()), format);
    }

    public Collection<Gist> getStarredGists() throws IOException {
        return getStarredGists(LIBRARY_OBJECT);
    }

    public <T> T getStarredGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_STARRED_PATH), format);
    }

    public Collection<Gist> getStarredGists(Params queryParams) throws IOException {
        return getStarredGists(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getStarredGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_STARRED_PATH + queryParams.createQueryString()), format);
    }

    public Gist getGist(String gistId) throws IOException {
        return getGist(gistId, LIBRARY_OBJECT);
    }

    public <T> T getGist(String gistId, ReturnFormat format) throws IOException {
        return returnGist(sendGetRequest(GISTS_PATH + "/" + gistId), format);
    }

    public Gist updateGist(Gist gist, String key, GistFile gistFile) throws IOException {
        return updateGist(gist.getId(), key, gistFile, LIBRARY_OBJECT);
    }

    public <T> T updateGist(Gist gist, String key, GistFile gistFile, ReturnFormat format) throws IOException {
        return updateGist(gist.getId(), key, gistFile, format);
    }

    public Gist updateGist(String gistId, String key, GistFile gistFile) throws IOException {
        return updateGist(gistId, key, gistFile, LIBRARY_OBJECT);
    }

    public <T> T updateGist(String gistId, String key, GistFile gistFile, ReturnFormat format) throws IOException {
        return updateGist(gistId, key, gistFile, null, format);
    }

    public Gist updateGist(Gist gist, String key, GistFile gistFile, String description) throws IOException {
        return updateGist(gist.getId(), key, gistFile, description, LIBRARY_OBJECT);
    }

    public <T> T updateGist(Gist gist, String key, GistFile gistFile, String description,
                            ReturnFormat format) throws IOException {
        return updateGist(gist.getId(), key, gistFile, description, format);
    }

    public Gist updateGist(String gistId, String key, GistFile gistFile, String description) throws IOException {
        return updateGist(gistId, key, gistFile, description, LIBRARY_OBJECT);
    }

    public <T> T updateGist(String gistId, String key, GistFile gistFile, String description,
                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        JSONObject file = new JSONObject().put(key, new JSONObject()
                .put("content", gistFile.getContent())
                .put("fileName", gistFile.getFileName()));
        payload.addParam("files", file);
        if (description != null)
            payload.addParam("description", description);
        return returnGist(sendPatchRequest(GISTS_PATH + "/" + gistId, payload), format);
    }

    public boolean deleteGist(Gist gist) {
        return deleteGist(gist.getId());
    }

    public boolean deleteGist(String gistId) {
        try {
            sendDeleteRequest(GISTS_PATH + "/" + gistId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public Collection<GistCommit> getGistCommits(Gist gist) throws IOException {
        return getGistCommits(gist.getId(), LIBRARY_OBJECT);
    }

    public <T> T getGistCommits(Gist gist, ReturnFormat format) throws IOException {
        return getGistCommits(gist.getId(), format);
    }

    public Collection<GistCommit> getGistCommits(String gistId) throws IOException {
        return getGistCommits(gistId, LIBRARY_OBJECT);
    }

    public <T> T getGistCommits(String gistId, ReturnFormat format) throws IOException {
        return returnGistCommits(sendGetRequest(GISTS_PATH + "/" + gistId + COMMITS_PATH), format);
    }

    public Collection<GistCommit> getGistCommits(Gist gist, Params queryParams) throws IOException {
        return getGistCommits(gist.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getGistCommits(Gist gist, Params queryParams, ReturnFormat format) throws IOException {
        return getGistCommits(gist.getId(), queryParams, format);
    }

    public Collection<GistCommit> getGistCommits(String gistId, Params queryParams) throws IOException {
        return getGistCommits(gistId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getGistCommits(String gistId, Params queryParams, ReturnFormat format) throws IOException {
        return returnGistCommits(sendGetRequest(GISTS_PATH + "/" + gistId + COMMITS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a gist commits
     *
     * @param gistCommitsResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return gist commits list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGistCommits(String gistCommitsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(gistCommitsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GistCommit> gistCommits = new ArrayList<>();
                JSONArray jGistCommits = new JSONArray(gistCommitsResponse);
                for (int j = 0; j < jGistCommits.length(); j++)
                    gistCommits.add(new GistCommit(jGistCommits.getJSONObject(j)));
                return (T) gistCommits;
            default:
                return (T) gistCommitsResponse;
        }
    }

    public Collection<GistCommit> getGistForks(Gist gist) throws IOException {
        return getGistForks(gist.getId(), LIBRARY_OBJECT);
    }

    public <T> T getGistForks(Gist gist, ReturnFormat format) throws IOException {
        return getGistForks(gist.getId(), format);
    }

    public Collection<GistCommit> getGistForks(String gistId) throws IOException {
        return getGistForks(gistId, LIBRARY_OBJECT);
    }

    public <T> T getGistForks(String gistId, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + "/" + gistId + FORKS_PATH), format);
    }

    public Collection<GistCommit> getGistForks(Gist gist, Params queryParams) throws IOException {
        return getGistForks(gist.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getGistForks(Gist gist, Params queryParams, ReturnFormat format) throws IOException {
        return getGistForks(gist.getId(), queryParams, format);
    }

    public Collection<GistCommit> getGistForks(String gistId, Params queryParams) throws IOException {
        return getGistForks(gistId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getGistForks(String gistId, Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + "/" + gistId + FORKS_PATH
                + queryParams.createQueryString()), format);
    }

    public Gist forkGist(Gist gist) throws IOException {
        return forkGist(gist.getId(), LIBRARY_OBJECT);
    }

    public <T> T forkGist(Gist gist, ReturnFormat format) throws IOException {
        return forkGist(gist.getId(), format);
    }

    public Gist forkGist(String gistId) throws IOException {
        return forkGist(gistId, LIBRARY_OBJECT);
    }

    public <T> T forkGist(String gistId, ReturnFormat format) throws IOException {
        return returnGist(sendPostRequest(GISTS_PATH + "/" + gistId + FORKS_PATH, null), format);
    }

    /**
     * Method to create a gist
     *
     * @param gistResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGist(String gistResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(gistResponse);
            case LIBRARY_OBJECT:
                return (T) new Gist(new JSONObject(gistResponse));
            default:
                return (T) gistResponse;
        }
    }

    public boolean checkIfGistIsStarred(Gist gist) {
        return checkIfGistIsStarred(gist.getId());
    }

    public boolean checkIfGistIsStarred(String gistId) {
        try {
            sendGetRequest(GISTS_PATH + "/" + gistId + STAR_PATH);
            return apiRequest.getResponseStatusCode() == 204;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean starGist(Gist gist) {
        return starGist(gist.getId());
    }

    public boolean starGist(String gistId) {
        try {
            sendPutRequest(GISTS_PATH + "/" + gistId + STAR_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public boolean unstarGist(Gist gist) {
        return unstarGist(gist.getId());
    }

    public boolean unstarGist(String gistId) {
        try {
            sendDeleteRequest(GISTS_PATH + "/" + gistId + STAR_PATH);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public GistCommit getGistRevision(Gist gist, String sha) throws IOException {
        return getGistRevision(gist.getId(), sha, LIBRARY_OBJECT);
    }

    public <T> T getGistRevision(Gist gist, String sha, ReturnFormat format) throws IOException {
        return getGistRevision(gist.getId(), sha, format);
    }

    public GistCommit getGistRevision(String gistId, String sha) throws IOException {
        return getGistRevision(gistId, sha, LIBRARY_OBJECT);
    }

    public <T> T getGistRevision(String gistId, String sha, ReturnFormat format) throws IOException {
        String gistCommitResponse = sendGetRequest(GISTS_PATH + "/" + gistId + "/" + sha);
        switch (format) {
            case JSON:
                return (T) new JSONObject(gistCommitResponse);
            case LIBRARY_OBJECT:
                return (T) new Gist(new JSONObject(gistCommitResponse));
            default:
                return (T) gistCommitResponse;
        }
    }

    public Collection<Gist> getUserGists(String username) throws IOException {
        return getUserGists(username, LIBRARY_OBJECT);
    }

    public <T> T getUserGists(String username, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(USERS_PATH + username + "/" + GISTS_PATH), format);
    }

    public Collection<Gist> getUserGists(String username, Params queryParams) throws IOException {
        return getUserGists(username, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getUserGists(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(USERS_PATH + username + "/" + GISTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a gists
     *
     * @param gistsResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGists(String gistsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(gistsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Gist> gists = new ArrayList<>();
                JSONArray jGists = new JSONArray(gistsResponse);
                for (int j = 0; j < jGists.length(); j++)
                    gists.add(new Gist(jGists.getJSONObject(j)));
                return (T) gists;
            default:
                return (T) gistsResponse;
        }
    }

}
