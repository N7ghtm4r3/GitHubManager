package com.tecknobit.githubmanager.commits.commits;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.branches.branches.records.ShortBranch;
import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.commits.commits.records.CommitsComparison;
import com.tecknobit.githubmanager.commits.commits.records.pullrequests.PullRequest;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubCommitsManager} class is useful to manage all GitHub's commits endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits">
 * Commits</a>
 * @see GitHubManager
 **/
public class GitHubCommitsManager extends GitHubManager {

    /**
     * {@code COMMITS_QUERY_PATH} constant for {@code "/commits/"} path
     **/
    public static final String COMMITS_QUERY_PATH = COMMITS_PATH + "/";

    /**
     * {@code BRANCHES_WHERE_HEAD_PATH} constant for {@code "/branches-where-head"} path
     **/
    public static final String BRANCHES_WHERE_HEAD_PATH = "/branches-where-head";

    /**
     * {@code COMPARE_PATH} constant for {@code "/compare/"} path
     **/
    public static final String COMPARE_PATH = "/compare/";

    /**
     * Constructor to init a {@link GitHubCommitsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCommitsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCommitsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCommitsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCommitsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCommitsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCommitsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitsManager} <br>
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
    public GitHubCommitsManager() {
        super();
    }

    /**
     * Method to get the commits list
     *
     * @param repository: the repository from fetch the list
     * @return commits list as {@link Collection} of {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public Collection<Commit> getCommits(Repository repository) throws IOException {
        return getCommits(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the commits list
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public <T> T getCommits(Repository repository, ReturnFormat format) throws IOException {
        return getCommits(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the commits list
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return commits list as {@link Collection} of {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public Collection<Commit> getCommits(String owner, String repo) throws IOException {
        return getCommits(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commits list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public <T> T getCommits(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_PATH), format);
    }

    /**
     * Method to get the commits list
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> SHA or branch to start listing commits from. Default: the repository’s
     *                            default branch (usually main) - [string]
     *                        </li>
     *                        <li>
     *                            {@code "path"} -> only commits containing this file path will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "author"} -> GitHub login or email address by which to filter by commit author
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} ->only show notifications updated after the given time. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "until"} -> only commits before this date will be returned. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link Collection} of {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public Collection<Commit> getCommits(Repository repository, Params queryParams) throws IOException {
        return getCommits(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commits list
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> SHA or branch to start listing commits from. Default: the repository’s
     *                            default branch (usually main) - [string]
     *                        </li>
     *                        <li>
     *                            {@code "path"} -> only commits containing this file path will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "author"} -> GitHub login or email address by which to filter by commit author
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} ->only show notifications updated after the given time. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "until"} -> only commits before this date will be returned. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public <T> T getCommits(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getCommits(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the commits list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> SHA or branch to start listing commits from. Default: the repository’s
     *                            default branch (usually main) - [string]
     *                        </li>
     *                        <li>
     *                            {@code "path"} -> only commits containing this file path will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "author"} -> GitHub login or email address by which to filter by commit author
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} ->only show notifications updated after the given time. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "until"} -> only commits before this date will be returned. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link Collection} of {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public Collection<Commit> getCommits(String owner, String repo, Params queryParams) throws IOException {
        return getCommits(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commits list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sha"} -> SHA or branch to start listing commits from. Default: the repository’s
     *                            default branch (usually main) - [string]
     *                        </li>
     *                        <li>
     *                            {@code "path"} -> only commits containing this file path will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "author"} -> GitHub login or email address by which to filter by commit author
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "since"} ->only show notifications updated after the given time. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "until"} -> only commits before this date will be returned. This is a timestamp
     *                            in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
     * List commits</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits")
    public <T> T getCommits(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a commits list
     *
     * @param commitsListResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitsList(String commitsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commitsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Commit> commits = new ArrayList<>();
                JSONArray jCommits = new JSONArray(commitsListResponse);
                for (int j = 0; j < jCommits.length(); j++)
                    commits.add(new Commit(jCommits.getJSONObject(j)));
                return (T) commits;
            default:
                return (T) commitsListResponse;
        }
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @return short branches list as {@link Collection} of {@link ShortBranch} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public Collection<ShortBranch> getHeadCommitBranches(Repository repository, Commit commit) throws IOException {
        return getHeadCommitBranches(repository.getOwner().getLogin(), repository.getName(), commit.getSha(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return short branches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public <T> T getHeadCommitBranches(Repository repository, Commit commit, ReturnFormat format) throws IOException {
        return getHeadCommitBranches(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), format);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @return short branches list as {@link Collection} of {@link ShortBranch} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public Collection<ShortBranch> getHeadCommitBranches(String owner, String repo, Commit commit) throws IOException {
        return getHeadCommitBranches(owner, repo, commit.getSha(), LIBRARY_OBJECT);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return short branches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public <T> T getHeadCommitBranches(String owner, String repo, Commit commit, ReturnFormat format) throws IOException {
        return getHeadCommitBranches(owner, repo, commit.getSha(), format);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @return short branches list as {@link Collection} of {@link ShortBranch} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public Collection<ShortBranch> getHeadCommitBranches(Repository repository, String commitSha) throws IOException {
        return getHeadCommitBranches(repository.getOwner().getLogin(), repository.getName(), commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return short branches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public <T> T getHeadCommitBranches(Repository repository, String commitSha, ReturnFormat format) throws IOException {
        return getHeadCommitBranches(repository.getOwner().getLogin(), repository.getName(), commitSha, format);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @return short branches list as {@link Collection} of {@link ShortBranch} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public Collection<ShortBranch> getHeadCommitBranches(String owner, String repo, String commitSha) throws IOException {
        return getHeadCommitBranches(owner, repo, commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to return all branches where the given commit SHA is the HEAD, or latest commit for the branch
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return short branches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
     * List branches for HEAD commit</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/branches-where-head")
    public <T> T getHeadCommitBranches(String owner, String repo, String commitSha, ReturnFormat format) throws IOException {
        String branchesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + commitSha + BRANCHES_WHERE_HEAD_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(branchesResponse);
            case LIBRARY_OBJECT:
                ArrayList<ShortBranch> branches = new ArrayList<>();
                JSONArray jBranches = new JSONArray(branchesResponse);
                for (int j = 0; j < jBranches.length(); j++)
                    branches.add(new ShortBranch(jBranches.getJSONObject(j)));
                return (T) branches;
            default:
                return (T) branchesResponse;
        }
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(Repository repository, Commit commit) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commit.getSha(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(Repository repository, Commit commit, ReturnFormat format) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(Repository repository, String commitSha) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(Repository repository, String commitSha, ReturnFormat format) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commitSha, format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(String owner, String repo, Commit commit) throws IOException {
        return getCommitPullRequests(owner, repo, commit.getSha(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(String owner, String repo, Commit commit, ReturnFormat format) throws IOException {
        return getCommitPullRequests(owner, repo, commit.getSha(), format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(String owner, String repo, String commitSha) throws IOException {
        return getCommitPullRequests(owner, repo, commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(String owner, String repo, String commitSha, ReturnFormat format) throws IOException {
        return returnPullRequestsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + commitSha + PULLS_PATH), format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository:  the repository from fetch the list
     * @param commit:      the commit from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(Repository repository, Commit commit,
                                                         Params queryParams) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository:  the repository from fetch the list
     * @param commit:      the commit from fetch the list
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
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(Repository repository, Commit commit, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), queryParams,
                format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository:  the repository from fetch the list
     * @param commitSha:   the SHA of the commit
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(Repository repository, String commitSha,
                                                         Params queryParams) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commitSha, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param repository:  the repository from fetch the list
     * @param commitSha:   the SHA of the commit
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
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(Repository repository, String commitSha, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getCommitPullRequests(repository.getOwner().getLogin(), repository.getName(), commitSha, queryParams,
                format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commit:      the commit from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(String owner, String repo, Commit commit,
                                                         Params queryParams) throws IOException {
        return getCommitPullRequests(owner, repo, commit.getSha(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commit:      the commit from fetch the list
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
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(String owner, String repo, Commit commit, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getCommitPullRequests(owner, repo, commit.getSha(), queryParams, format);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commitSha:   the SHA of the commit
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link Collection} of {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public Collection<PullRequest> getCommitPullRequests(String owner, String repo, String commitSha,
                                                         Params queryParams) throws IOException {
        return getCommitPullRequests(owner, repo, commitSha, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the merged pull request that introduced the commit to the repository. If the commit is not
     * present in the default branch, will only return open pull requests associated with the commit
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commitSha:   the SHA of the commit
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
     * @return pull requests list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commit">
     * List pull requests associated with a commit</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/pulls")
    public <T> T getCommitPullRequests(String owner, String repo, String commitSha, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return returnPullRequestsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + commitSha + PULLS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a pull requests list
     *
     * @param pullRequestsListResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return pull requests list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPullRequestsList(String pullRequestsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(pullRequestsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<PullRequest> pullRequests = new ArrayList<>();
                JSONArray jPullRequests = new JSONArray(pullRequestsListResponse);
                for (int j = 0; j < jPullRequests.length(); j++)
                    pullRequests.add(new PullRequest(jPullRequests.getJSONObject(j)));
                return (T) pullRequests;
            default:
                return (T) pullRequestsListResponse;
        }
    }

    /**
     * Method to return the contents of a single commit reference. You must have read access for the repository to use this endpoint
     *
     * @param repository: the repository from fetch the commit
     * @param ref:        ref parameter
     * @return commit as {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#get-a-commit">
     * Get a commit</a>
     * @implNote If there are more than 300 files in the commit diff, the response will include pagination link headers
     * for the remaining files, up to a limit of 3000 files. Each page contains the static commit information, and the
     * only changes are to the file listing
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}")
    public Commit getCommit(Repository repository, String ref) throws IOException {
        return getCommit(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to return the contents of a single commit reference. You must have read access for the repository to use this endpoint
     *
     * @param repository: the repository from fetch the commit
     * @param ref:        ref parameter
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#get-a-commit">
     * Get a commit</a>
     * @implNote If there are more than 300 files in the commit diff, the response will include pagination link headers
     * for the remaining files, up to a limit of 3000 files. Each page contains the static commit information, and the
     * only changes are to the file listing
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}")
    public <T> T getCommit(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getCommit(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to return the contents of a single commit reference. You must have read access for the repository to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return commit as {@link Commit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#get-a-commit">
     * Get a commit</a>
     * @implNote If there are more than 300 files in the commit diff, the response will include pagination link headers
     * for the remaining files, up to a limit of 3000 files. Each page contains the static commit information, and the
     * only changes are to the file listing
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}")
    public Commit getCommit(String owner, String repo, String ref) throws IOException {
        return getCommit(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to return the contents of a single commit reference. You must have read access for the repository to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param ref:    ref parameter
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#get-a-commit">
     * Get a commit</a>
     * @implNote If there are more than 300 files in the commit diff, the response will include pagination link headers
     * for the remaining files, up to a limit of 3000 files. Each page contains the static commit information, and the
     * only changes are to the file listing
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}")
    public <T> T getCommit(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        String commitResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH + ref);
        switch (format) {
            case JSON:
                return (T) new JSONObject(commitResponse);
            case LIBRARY_OBJECT:
                return (T) new Commit(new JSONObject(commitResponse));
            default:
                return (T) commitResponse;
        }
    }

    /**
     * Method to compare two commits against one another. You can compare branches in the same repository, or you can
     * compare branches that exist in different repositories within the same repository network, including fork branches
     *
     * @param repository: the repository where compare commits
     * @param basehead:   the base branch and head branch to compare. This parameter expects the format BASE...HEAD. Both
     *                    must be branch names in repo. To compare with a branch that exists in a different repository in the
     *                    same network as repo, the basehead parameter expects the format USERNAME:BASE...USERNAME:HEAD
     * @return commits comparison as {@link CommitsComparison} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#compare-two-commits">
     * Compare two commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/compare/{basehead}")
    public CommitsComparison compareTwoCommits(Repository repository, String basehead) throws IOException {
        return compareTwoCommits(repository.getOwner().getLogin(), repository.getName(), basehead, LIBRARY_OBJECT);
    }

    /**
     * Method to compare two commits against one another. You can compare branches in the same repository, or you can
     * compare branches that exist in different repositories within the same repository network, including fork branches
     *
     * @param repository: the repository where compare commits
     * @param basehead:   the base branch and head branch to compare. This parameter expects the format BASE...HEAD. Both
     *                    must be branch names in repo. To compare with a branch that exists in a different repository in the
     *                    same network as repo, the basehead parameter expects the format USERNAME:BASE...USERNAME:HEAD
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commits comparison as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#compare-two-commits">
     * Compare two commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/compare/{basehead}")
    public <T> T compareTwoCommits(Repository repository, String basehead, ReturnFormat format) throws IOException {
        return compareTwoCommits(repository.getOwner().getLogin(), repository.getName(), basehead, LIBRARY_OBJECT);
    }

    /**
     * Method to compare two commits against one another. You can compare branches in the same repository, or you can
     * compare branches that exist in different repositories within the same repository network, including fork branches
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base branch and head branch to compare. This parameter expects the format BASE...HEAD. Both
     *                  must be branch names in repo. To compare with a branch that exists in a different repository in the
     *                  same network as repo, the basehead parameter expects the format USERNAME:BASE...USERNAME:HEAD
     * @return commits comparison as {@link CommitsComparison} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#compare-two-commits">
     * Compare two commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/compare/{basehead}")
    public CommitsComparison compareTwoCommits(String owner, String repo, String basehead) throws IOException {
        return compareTwoCommits(owner, repo, basehead, LIBRARY_OBJECT);
    }

    /**
     * Method to compare two commits against one another. You can compare branches in the same repository, or you can
     * compare branches that exist in different repositories within the same repository network, including fork branches
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base branch and head branch to compare. This parameter expects the format BASE...HEAD. Both
     *                  must be branch names in repo. To compare with a branch that exists in a different repository in the
     *                  same network as repo, the basehead parameter expects the format USERNAME:BASE...USERNAME:HEAD
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return commits comparison as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#compare-two-commits">
     * Compare two commits</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/compare/{basehead}")
    public <T> T compareTwoCommits(String owner, String repo, String basehead, ReturnFormat format) throws IOException {
        String comparisonResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + COMPARE_PATH + basehead);
        switch (format) {
            case JSON:
                return (T) new JSONObject(comparisonResponse);
            case LIBRARY_OBJECT:
                return (T) new CommitsComparison(new JSONObject(comparisonResponse));
            default:
                return (T) comparisonResponse;
        }
    }

}
