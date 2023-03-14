package com.tecknobit.githubmanager.repositories.gitlfs;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.DELETE;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PUT;

/**
 * The {@code GitHubLFSManager} class is useful to manage all GitHub's Git LFS endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/lfs">
 * Git LFS</a>
 * @see GitHubManager
 **/
public class GitHubLFSManager extends GitHubManager {

    /**
     * {@code LFS_PATH} constant for {@code "/lfs"} path
     **/
    public static final String LFS_PATH = "/lfs";

    /**
     * Constructor to init a {@link GitHubLFSManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubLFSManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubLFSManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubLFSManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubLFSManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubLFSManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubLFSManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubLFSManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubLFSManager} <br>
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
    public GitHubLFSManager() {
        super();
    }

    /**
     * Method to enable Git LFS for a repository. Access tokens must have the {@code "admin:enterprise"} scope.
     *
     * @param repository: the repository where enable GIT LFS
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/lfs#enable-git-lfs-for-a-repository">
     * Enable Git LFS for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/lfs")
    public boolean enableGitLFS(Repository repository) {
        return enableGitLFS(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to enable Git LFS for a repository. Access tokens must have the {@code "admin:enterprise"} scope.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/lfs#enable-git-lfs-for-a-repository">
     * Enable Git LFS for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/lfs")
    public boolean enableGitLFS(String owner, String repo) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + LFS_PATH, null);
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

    /**
     * Method to disable Git LFS for a repository. Access tokens must have the {@code "admin:enterprise"} scope.
     *
     * @param repository: the repository where disable GIT LFS
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/lfs#disable-git-lfs-for-a-repository">
     * Disable Git LFS for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/lfs")
    public boolean disableGitLFS(Repository repository) {
        return enableGitLFS(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to disable Git LFS for a repository. Access tokens must have the {@code "admin:enterprise"} scope.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/lfs#disable-git-lfs-for-a-repository">
     * Disable Git LFS for a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/lfs")
    public boolean disableGitLFS(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + LFS_PATH);
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
