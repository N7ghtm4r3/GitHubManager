package com.tecknobit.githubmanager.apps.installations;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.apps.installations.records.InstallationsList;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.repository.RepositoriesList.returnRepositoriesList;

/**
 * The {@code GitHubInstallationsManager} class is useful to manage all GitHub's installations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations">
 * GitHub App installations</a>
 * @see GitHubManager
 **/
public class GitHubInstallationsManager extends GitHubManager {

    /**
     * {@code INSTALLATION_QUERY_PATH} constant for {@code "installation/"} path
     **/
    public static final String INSTALLATION_QUERY_PATH = "installation/";

    /**
     * {@code INSTALLATION_REPOSITORIES_PATH} constant for {@code "installation/repositories"} path
     **/
    public static final String INSTALLATION_REPOSITORIES_PATH = INSTALLATION_QUERY_PATH + "repositories";

    /**
     * {@code INSTALLATION_TOKEN_PATH} constant for {@code "installation/token"} path
     **/
    public static final String INSTALLATION_TOKEN_PATH = INSTALLATION_QUERY_PATH + "token";

    /**
     * {@code USER_INSTALLATIONS_PATH} constant for {@code "user/installations"} path
     **/
    public static final String USER_INSTALLATIONS_PATH = "user/installations";

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubInstallationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubInstallationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubInstallationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubInstallationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInstallationsManager} <br>
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
    public GitHubInstallationsManager() {
        super();
    }

    public RepositoriesList getRepositoriesAppAccessible() throws IOException {
        return getRepositoriesAppAccessible(LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesAppAccessible(ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(INSTALLATION_REPOSITORIES_PATH), format);
    }

    public RepositoriesList getRepositoriesAppAccessible(Params queryParams) throws IOException {
        return getRepositoriesAppAccessible(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesAppAccessible(Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(INSTALLATION_REPOSITORIES_PATH + queryParams.createQueryString()),
                format);
    }

    public boolean revokeInstallationAccessToken() {
        try {
            sendDeleteRequest(INSTALLATION_TOKEN_PATH);
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

    public InstallationsList getAppInstallationsTokenAccessible() throws Exception {
        return getAppInstallationsTokenAccessible(LIBRARY_OBJECT);
    }

    public <T> T getAppInstallationsTokenAccessible(ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(USER_INSTALLATIONS_PATH), format);
    }

    public InstallationsList getAppInstallationsTokenAccessible(Params queryParams) throws Exception {
        return getAppInstallationsTokenAccessible(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getAppInstallationsTokenAccessible(Params queryParams, ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(USER_INSTALLATIONS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create an installations list
     *
     * @param installationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnInstallationsList(String installationsResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(installationsResponse);
            case LIBRARY_OBJECT:
                return (T) new InstallationsList(new JSONObject(installationsResponse));
            default:
                return (T) installationsResponse;
        }
    }

    public RepositoriesList getRepositoriesUserTokenAccessible(Installation installation) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesUserTokenAccessible(Installation installation, ReturnFormat format) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), format);
    }

    public RepositoriesList getRepositoriesUserTokenAccessible(long installationId) throws IOException {
        return getRepositoriesUserTokenAccessible(installationId, LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesUserTokenAccessible(long installationId, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(USER_INSTALLATIONS_PATH + "/" + installationId +
                REPOSITORIES_PATH), format);
    }

    public RepositoriesList getRepositoriesUserTokenAccessible(Installation installation,
                                                               Params queryParams) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesUserTokenAccessible(Installation installation, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return getRepositoriesUserTokenAccessible(installation.getId(), queryParams, format);
    }

    public RepositoriesList getRepositoriesUserTokenAccessible(long installationId, Params queryParams) throws IOException {
        return getRepositoriesUserTokenAccessible(installationId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoriesUserTokenAccessible(long installationId, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(USER_INSTALLATIONS_PATH + "/" + installationId +
                REPOSITORIES_PATH), format);
    }

    public boolean addRepositoryToAppInstallation(Installation installation, Repository repository) {
        return addRepositoryToAppInstallation(installation.getId(), repository.getId());
    }

    public boolean addRepositoryToAppInstallation(long installationId, Repository repository) {
        return addRepositoryToAppInstallation(installationId, repository.getId());
    }

    public boolean addRepositoryToAppInstallation(Installation installation, long repositoryId) {
        return addRepositoryToAppInstallation(installation.getId(), repositoryId);
    }

    public boolean addRepositoryToAppInstallation(long installationId, long repositoryId) {
        try {
            sendPutRequest(USER_INSTALLATIONS_PATH + "/" + installationId + REPOSITORIES_PATH + "/" +
                    repositoryId, null);
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

    public boolean removeRepositoryFromAppInstallation(Installation installation, Repository repository) {
        return removeRepositoryFromAppInstallation(installation.getId(), repository.getId());
    }

    public boolean removeRepositoryFromAppInstallation(long installationId, Repository repository) {
        return removeRepositoryFromAppInstallation(installationId, repository.getId());
    }

    public boolean removeRepositoryFromAppInstallation(Installation installation, long repositoryId) {
        return removeRepositoryFromAppInstallation(installation.getId(), repositoryId);
    }

    public boolean removeRepositoryFromAppInstallation(long installationId, long repositoryId) {
        try {
            sendDeleteRequest(USER_INSTALLATIONS_PATH + "/" + installationId + REPOSITORIES_PATH + "/" +
                    repositoryId);
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

}
