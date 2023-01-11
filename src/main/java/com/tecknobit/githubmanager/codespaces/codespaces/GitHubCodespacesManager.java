package com.tecknobit.githubmanager.codespaces.codespaces;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codespaces.codespaces.records.Codespace;
import com.tecknobit.githubmanager.codespaces.codespaces.records.CodespaceExportDetails;
import com.tecknobit.githubmanager.codespaces.codespaces.records.CodespacesList;
import com.tecknobit.githubmanager.codespaces.codespaces.records.DevContainersList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubCodespacesManager} class is useful to manage all GitHub's codespaces endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces">
 * Codespaces</a>
 * @see GitHubManager
 **/
public class GitHubCodespacesManager extends GitHubManager {

    /**
     * {@code CODESPACES_PATH} constant for {@code "/codespaces"} path
     **/
    public static final String CODESPACES_PATH = "/codespaces";

    /**
     * {@code CODESPACES_DEVCONTAINERS_PATH} constant for {@code "/codespaces/devcontainers"} path
     **/
    public static final String CODESPACES_DEVCONTAINERS_PATH = CODESPACES_PATH + "/devcontainers";

    /**
     * {@code CODESPACES_NEW_PATH} constant for {@code "/codespaces/new"} path
     **/
    public static final String CODESPACES_NEW_PATH = CODESPACES_PATH + "/new";

    /**
     * {@code PULLS_PATH} constant for {@code "/pulls/"} path
     **/
    public static final String PULLS_PATH = "/pulls/";

    /**
     * {@code USER_CODESPACES_PATH} constant for {@code "user/codespaces"} path
     **/
    public static final String USER_CODESPACES_PATH = USER_PATH + CODESPACES_PATH;

    /**
     * {@code EXPORTS_PATH} constant for {@code "/exports"} path
     **/
    public static final String EXPORTS_PATH = "/exports";

    /**
     * {@code START_PATH} constant for {@code "/start"} path
     **/
    public static final String START_PATH = "/start";

    /**
     * {@code STOP_PATH} constant for {@code "/stop"} path
     **/
    public static final String STOP_PATH = "/stop";

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCodespacesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCodespacesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCodespacesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCodespacesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodespacesManager} <br>
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
    public GitHubCodespacesManager() {
        super();
    }

    public CodespacesList getRepositoryCodespaces(Repository repository) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryCodespaces(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public CodespacesList getRepositoryCodespaces(String owner, String repo) throws IOException {
        return getRepositoryCodespaces(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryCodespaces(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH), format);
    }

    public CodespacesList getRepositoryCodespaces(Repository repository, Params queryParams) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryCodespaces(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryCodespaces(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public CodespacesList getRepositoryCodespaces(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryCodespaces(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryCodespaces(String owner, String repo, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH
                + queryParams.createQueryString()), format);
    }

    public Codespace createRepositoryCodespace(Repository repository, Params bodyParams) throws IOException {
        return createRepositoryCodespace(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createRepositoryCodespace(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createRepositoryCodespace(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    public Codespace createRepositoryCodespace(String owner, String repo, Params bodyParams) throws IOException {
        return createRepositoryCodespace(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createRepositoryCodespace(String owner, String repo, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH, bodyParams),
                format);
    }

    public DevContainersList getRepositoryDevContainers(Repository repository) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryDevContainers(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public DevContainersList getRepositoryDevContainers(String owner, String repo) throws IOException {
        return getRepositoryDevContainers(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryDevContainers(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_DEVCONTAINERS_PATH),
                format);
    }

    public DevContainersList getRepositoryDevContainers(Repository repository, Params queryParams) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryDevContainers(Repository repository, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getRepositoryDevContainers(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public DevContainersList getRepositoryDevContainers(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryDevContainers(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryDevContainers(String owner, String repo, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_DEVCONTAINERS_PATH
                + queryParams.createQueryString()), format);
    }

    public DevContainersList getDefaultCodespaceAttributes(Repository repository) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getDefaultCodespaceAttributes(Repository repository, ReturnFormat format) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public DevContainersList getDefaultCodespaceAttributes(String owner, String repo) throws IOException {
        return getDefaultCodespaceAttributes(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getDefaultCodespaceAttributes(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_NEW_PATH),
                format);
    }

    public DevContainersList getDefaultCodespaceAttributes(Repository repository, Params queryParams) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getDefaultCodespaceAttributes(Repository repository, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getDefaultCodespaceAttributes(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public DevContainersList getDefaultCodespaceAttributes(String owner, String repo, Params queryParams) throws IOException {
        return getDefaultCodespaceAttributes(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getDefaultCodespaceAttributes(String owner, String repo, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnDevContainers(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_NEW_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a dev containers list
     *
     * @param devContainersResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return dev containers list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDevContainers(String devContainersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(devContainersResponse);
            case LIBRARY_OBJECT:
                return (T) new DevContainersList(new JSONObject(devContainersResponse));
            default:
                return (T) devContainersResponse;
        }
    }

    public Codespace createCodespaceFromPullRequest(Repository repository, long pullNumber,
                                                    Params bodyParams) throws IOException {
        return createCodespaceFromPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createCodespaceFromPullRequest(Repository repository, long pullNumber, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return createCodespaceFromPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, format);
    }

    public Codespace createCodespaceFromPullRequest(String owner, String repo, long pullNumber,
                                                    Params bodyParams) throws IOException {
        return createCodespaceFromPullRequest(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createCodespaceFromPullRequest(String owner, String repo, long pullNumber, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + pullNumber
                + CODESPACES_PATH, bodyParams), format);
    }

    public CodespacesList getUserCodespaces() throws IOException {
        return getUserCodespaces(LIBRARY_OBJECT);
    }

    public <T> T getUserCodespaces(ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(USER_CODESPACES_PATH), format);
    }

    public CodespacesList getUserCodespaces(Params queryParams) throws IOException {
        return getUserCodespaces(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getUserCodespaces(Params queryParams, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(USER_CODESPACES_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a codespaces list
     *
     * @param codespacesListResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCodespacesList(String codespacesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespacesListResponse);
            case LIBRARY_OBJECT:
                return (T) new CodespacesList(new JSONObject(codespacesListResponse));
            default:
                return (T) codespacesListResponse;
        }
    }

    public Codespace createUserCodespace(Repository repository, Params bodyParams) throws IOException {
        return createUserCodespace(repository.getId(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createUserCodespace(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createUserCodespace(repository.getId(), bodyParams, format);
    }

    public Codespace createUserCodespace(long repositoryId, Params bodyParams) throws IOException {
        return createUserCodespace(repositoryId, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createUserCodespace(long repositoryId, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("repository_id", repositoryId);
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH, bodyParams), format);
    }

    public Codespace getUserCodespace(String codespaceName) throws IOException {
        return getUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    public <T> T getUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendGetRequest(USER_CODESPACES_PATH + "/" + codespaceName), format);
    }

    public Codespace updateUserCodespace(Codespace codespace, Params bodyParams) throws IOException {
        return updateUserCodespace(codespace.getName(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateUserCodespace(Codespace codespace, Params bodyParams, ReturnFormat format) throws IOException {
        return updateUserCodespace(codespace.getName(), bodyParams, format);
    }

    public Codespace updateUserCodespace(String codespaceName, Params bodyParams) throws IOException {
        return updateUserCodespace(codespaceName, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateUserCodespace(String codespaceName, Params bodyParams, ReturnFormat format) throws IOException {
        return returnCodespace(sendPatchRequest(USER_CODESPACES_PATH + "/" + codespaceName, bodyParams), format);
    }

    public boolean deleteUserCodespace(Codespace codespace) {
        return deleteUserCodespace(codespace.getName());
    }

    public boolean deleteUserCodespace(String codespaceName) {
        try {
            sendDeleteRequest(USER_CODESPACES_PATH + "/" + codespaceName);
            if (apiRequest.getResponseStatusCode() != 202) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    public CodespaceExportDetails exportUserCodespace(Codespace codespace) throws IOException {
        return exportUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    public <T> T exportUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return exportUserCodespace(codespace.getName(), format);
    }

    public CodespaceExportDetails exportUserCodespace(String codespaceName) throws IOException {
        return exportUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    public <T> T exportUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespaceExportDetails(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName
                + EXPORTS_PATH, null), format);
    }

    public CodespaceExportDetails getUserCodespaceExportDetails(Codespace codespace, long exportId) throws IOException {
        return getUserCodespaceExportDetails(codespace.getName(), exportId, LIBRARY_OBJECT);
    }

    public <T> T getUserCodespaceExportDetails(Codespace codespace, long exportId, ReturnFormat format) throws IOException {
        return getUserCodespaceExportDetails(codespace.getName(), exportId, format);
    }

    public CodespaceExportDetails getUserCodespaceExportDetails(String codespaceName, long exportId) throws IOException {
        return getUserCodespaceExportDetails(codespaceName, exportId, LIBRARY_OBJECT);
    }

    public <T> T getUserCodespaceExportDetails(String codespaceName, long exportId, ReturnFormat format) throws IOException {
        return returnCodespaceExportDetails(sendGetRequest(USER_CODESPACES_PATH + "/" + codespaceName
                + EXPORTS_PATH + "/" + exportId), format);
    }

    /**
     * Method to create a codespace export details
     *
     * @param codespaceExportDetailsResponse: obtained from GitHub's response
     * @param format:                         return type formatter -> {@link ReturnFormat}
     * @return codespace export details as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCodespaceExportDetails(String codespaceExportDetailsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespaceExportDetailsResponse);
            case LIBRARY_OBJECT:
                return (T) new CodespaceExportDetails(new JSONObject(codespaceExportDetailsResponse));
            default:
                return (T) codespaceExportDetailsResponse;
        }
    }

    public Codespace startUserCodespace(Codespace codespace) throws IOException {
        return startUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    public <T> T startUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return startUserCodespace(codespace.getName(), format);
    }

    public Codespace startUserCodespace(String codespaceName) throws IOException {
        return startUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    public <T> T startUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName + START_PATH, null),
                format);
    }

    public Codespace stopUserCodespace(Codespace codespace) throws IOException {
        return stopUserCodespace(codespace.getName(), LIBRARY_OBJECT);
    }

    public <T> T stopUserCodespace(Codespace codespace, ReturnFormat format) throws IOException {
        return stopUserCodespace(codespace.getName(), format);
    }

    public Codespace stopUserCodespace(String codespaceName) throws IOException {
        return stopUserCodespace(codespaceName, LIBRARY_OBJECT);
    }

    public <T> T stopUserCodespace(String codespaceName, ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(USER_CODESPACES_PATH + "/" + codespaceName + STOP_PATH, null),
                format);
    }

    /**
     * Method to create a codespace
     *
     * @param codespaceResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return codespace as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCodespace(String codespaceResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespaceResponse);
            case LIBRARY_OBJECT:
                return (T) new Codespace(new JSONObject(codespaceResponse));
            default:
                return (T) codespaceResponse;
        }
    }

}
