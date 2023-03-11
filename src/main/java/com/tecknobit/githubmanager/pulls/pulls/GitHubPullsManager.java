package com.tecknobit.githubmanager.pulls.pulls;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.commits.commits.records.Commit.CommitFile;
import com.tecknobit.githubmanager.pulls.pulls.records.MergeResult;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest.AutoMerge.MergeMethod;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest.PullRequestSort;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequestBranch;
import com.tecknobit.githubmanager.records.parents.GitHubOperationBaseStructure.OperationState;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commits.records.Commit.returnCommitsList;
import static com.tecknobit.githubmanager.pulls.pulls.records.PullRequest.returnPullRequest;
import static com.tecknobit.githubmanager.pulls.pulls.records.PullRequest.returnPullRequestsList;

/**
 * The {@code GitHubPullsManager} class is useful to manage all GitHub's pulls endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls">
 * Pulls</a>
 * @see GitHubManager
 **/
public class GitHubPullsManager extends GitHubManager {

    /**
     * {@code FILES_PATH} constant for {@code "/files"} path
     **/
    public static final String FILES_PATH = "/files";

    /**
     * {@code MERGE_PATH} constant for {@code "/merge"} path
     **/
    public static final String MERGE_PATH = "/merge";

    /**
     * {@code UPDATE_BRANCH_PATH} constant for {@code "/update-branch"} path
     **/
    public static final String UPDATE_BRANCH_PATH = "/update-branch";

    /**
     * Constructor to init a {@link GitHubPullsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubPullsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubPullsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubPullsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubPullsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubPullsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPullsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubPullsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPullsManager} <br>
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
    public GitHubPullsManager() {
        super();
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
     * @return pull requests list as {@link ArrayList} of {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public ArrayList<PullRequest> getPullRequests(Repository repository) throws IOException {
        return getPullRequests(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public <T> T getPullRequests(Repository repository, ReturnFormat format) throws IOException {
        return getPullRequests(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pull requests list as {@link ArrayList} of {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public ArrayList<PullRequest> getPullRequests(String owner, String repo) throws IOException {
        return getPullRequests(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public <T> T getPullRequests(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPullRequestsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH), format);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "head"} -> filter pulls by head user or head organization and branch name in the
     *                            format of {@code "user:ref-name"} or {@code "organization:ref-name"}.
     *                            For example: {@code "github:new-script-format"} or {@code "octocat:test-branch"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by. popularity will sort by the number of comments.
     *                            long-running will sort by date created and will limit the results to pull requests
     *                            that have been open for more than a month and have had activity within the past
     *                            month, constants available {@link PullRequestSort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, when sort is created or
     *                            sort is not specified, default "asc", constants available {@link Directions}
     *                            - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link ArrayList} of {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public ArrayList<PullRequest> getPullRequests(Repository repository, Params queryParams) throws IOException {
        return getPullRequests(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "head"} -> filter pulls by head user or head organization and branch name in the
     *                            format of {@code "user:ref-name"} or {@code "organization:ref-name"}.
     *                            For example: {@code "github:new-script-format"} or {@code "octocat:test-branch"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by. popularity will sort by the number of comments.
     *                            long-running will sort by date created and will limit the results to pull requests
     *                            that have been open for more than a month and have had activity within the past
     *                            month, constants available {@link PullRequestSort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, when sort is created or
     *                            sort is not specified, default "asc", constants available {@link Directions}
     *                            - [string, default "desc"]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public <T> T getPullRequests(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequests(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "head"} -> filter pulls by head user or head organization and branch name in the
     *                            format of {@code "user:ref-name"} or {@code "organization:ref-name"}.
     *                            For example: {@code "github:new-script-format"} or {@code "octocat:test-branch"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by. popularity will sort by the number of comments.
     *                            long-running will sort by date created and will limit the results to pull requests
     *                            that have been open for more than a month and have had activity within the past
     *                            month, constants available {@link PullRequestSort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, when sort is created or
     *                            sort is not specified, default "asc", constants available {@link Directions}
     *                            - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests list as {@link ArrayList} of {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public ArrayList<PullRequest> getPullRequests(String owner, String repo, Params queryParams) throws IOException {
        return getPullRequests(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to draft pull requests are available in public repositories with GitHub Free and GitHub Free for organizations,
     * GitHub Pro, and legacy per-repository billing plans, and in public and private repositories with GitHub Team and
     * GitHub Enterprise Cloud. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "head"} -> filter pulls by head user or head organization and branch name in the
     *                            format of {@code "user:ref-name"} or {@code "organization:ref-name"}.
     *                            For example: {@code "github:new-script-format"} or {@code "octocat:test-branch"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by. popularity will sort by the number of comments.
     *                            long-running will sort by date created and will limit the results to pull requests
     *                            that have been open for more than a month and have had activity within the past
     *                            month, constants available {@link PullRequestSort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, when sort is created or
     *                            sort is not specified, default "asc", constants available {@link Directions}
     *                            - [string, default "desc"]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List pull requests</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls")
    public <T> T getPullRequests(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnPullRequestsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository: the repository where create the pull request
     * @param head:       the name of the branch where your changes are implemented. For cross-repository pull requests in the
     *                    same network, namespace head with a user like this: {@code "username:branch"}
     * @param base:       the name of the branch you want the changes pulled into. This should be an existing branch on the current
     *                    repository. You cannot submit a pull request to one repository that requests a merge to a base of another
     *                    repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "head_repo"} -> the name of the repository where the changes in the pull request
     *                           were made. This field is required for cross-repository pull requests if both repositories
     *                           are owned by the same organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> indicates whether the pull request is a draft. See "Draft Pull
     *                           Requests" in the GitHub Help documentation to learn more - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "issue"} -> an issue in the repository to convert to a pull request. The issue
     *                           title, body, and comments will become the title, body, and comments on the new pull
     *                           request. Required unless title is specified - [integer]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#create-a-pull-request">
     * Create a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls")
    public PullRequest createPullRequest(Repository repository, String head, String base,
                                         Params bodyParams) throws IOException {
        return createPullRequest(repository.getOwner().getLogin(), repository.getName(), head, base, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository: the repository where create the pull request
     * @param head:       the name of the branch where your changes are implemented. For cross-repository pull requests in the
     *                    same network, namespace head with a user like this: {@code "username:branch"}
     * @param base:       the name of the branch you want the changes pulled into. This should be an existing branch on the current
     *                    repository. You cannot submit a pull request to one repository that requests a merge to a base of another
     *                    repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "head_repo"} -> the name of the repository where the changes in the pull request
     *                           were made. This field is required for cross-repository pull requests if both repositories
     *                           are owned by the same organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> indicates whether the pull request is a draft. See "Draft Pull
     *                           Requests" in the GitHub Help documentation to learn more - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "issue"} -> an issue in the repository to convert to a pull request. The issue
     *                           title, body, and comments will become the title, body, and comments on the new pull
     *                           request. Required unless title is specified - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#create-a-pull-request">
     * Create a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls")
    public <T> T createPullRequest(Repository repository, String head, String base, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return createPullRequest(repository.getOwner().getLogin(), repository.getName(), head, base, bodyParams, format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param head:       the name of the branch where your changes are implemented. For cross-repository pull requests in the
     *                    same network, namespace head with a user like this: {@code "username:branch"}
     * @param base:       the name of the branch you want the changes pulled into. This should be an existing branch on the current
     *                    repository. You cannot submit a pull request to one repository that requests a merge to a base of another
     *                    repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "head_repo"} -> the name of the repository where the changes in the pull request
     *                           were made. This field is required for cross-repository pull requests if both repositories
     *                           are owned by the same organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> indicates whether the pull request is a draft. See "Draft Pull
     *                           Requests" in the GitHub Help documentation to learn more - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "issue"} -> an issue in the repository to convert to a pull request. The issue
     *                           title, body, and comments will become the title, body, and comments on the new pull
     *                           request. Required unless title is specified - [integer]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#create-a-pull-request">
     * Create a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls")
    public PullRequest createPullRequest(String owner, String repo, String head, String base,
                                         Params bodyParams) throws IOException {
        return createPullRequest(owner, repo, head, base, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param head:       the name of the branch where your changes are implemented. For cross-repository pull requests in the
     *                    same network, namespace head with a user like this: {@code "username:branch"}
     * @param base:       the name of the branch you want the changes pulled into. This should be an existing branch on the current
     *                    repository. You cannot submit a pull request to one repository that requests a merge to a base of another
     *                    repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "head_repo"} -> the name of the repository where the changes in the pull request
     *                           were made. This field is required for cross-repository pull requests if both repositories
     *                           are owned by the same organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> indicates whether the pull request is a draft. See "Draft Pull
     *                           Requests" in the GitHub Help documentation to learn more - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "issue"} -> an issue in the repository to convert to a pull request. The issue
     *                           title, body, and comments will become the title, body, and comments on the new pull
     *                           request. Required unless title is specified - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#create-a-pull-request">
     * Create a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls")
    public <T> T createPullRequest(String owner, String repo, String head, String base, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("head", head);
        bodyParams.addParam("base", base);
        return returnPullRequest(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH, bodyParams),
                format);
    }

    /**
     * Method to get the details of a pull request by providing its number
     *
     * @param repository: the repository from fetch the pull request
     * @param pullNumber: the number that identifies the pull request
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#get-a-pull-request">
     * Get a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest getPullRequest(Repository repository, long pullNumber) throws IOException {
        return getPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the details of a pull request by providing its number
     *
     * @param repository: the repository from fetch the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#get-a-pull-request">
     * Get a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T getPullRequest(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return getPullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to get the details of a pull request by providing its number
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#get-a-pull-request">
     * Get a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest getPullRequest(String owner, String repo, long pullNumber) throws IOException {
        return getPullRequest(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the details of a pull request by providing its number
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#get-a-pull-request">
     * Get a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T getPullRequest(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return returnPullRequest(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber),
                format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository:  the repository where create the pull request
     * @param pullRequest: pull request to update
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the new pull request. Required unless issue is
     *                            specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the pull request - [string]
     *                        </li>
     *                        <li>
     *                            {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                            request - [boolean]
     *                        </li>
     *                     </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest updatePullRequest(Repository repository, PullRequest pullRequest, Params bodyParams) throws IOException {
        return updatePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository:  the repository where create the pull request
     * @param pullRequest: pull request to update
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the new pull request. Required unless issue is
     *                            specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the pull request - [string]
     *                        </li>
     *                        <li>
     *                            {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                            request - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T updatePullRequest(Repository repository, PullRequest pullRequest, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return updatePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                bodyParams, format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository: the repository where create the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> either open, closed, or all to filter by state,
     *                            constants available {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest updatePullRequest(Repository repository, long pullNumber, Params bodyParams) throws IOException {
        return updatePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param repository: the repository where create the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> either open, closed, or all to filter by state,
     *                            constants available {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T updatePullRequest(Repository repository, long pullNumber, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return updatePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams, format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: pull request to update
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the new pull request. Required unless issue is
     *                            specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the pull request - [string]
     *                        </li>
     *                        <li>
     *                            {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                            request - [boolean]
     *                        </li>
     *                     </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest updatePullRequest(String owner, String repo, PullRequest pullRequest,
                                         Params bodyParams) throws IOException {
        return updatePullRequest(owner, repo, pullRequest.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: pull request to update
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the new pull request. Required unless issue is
     *                            specified - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> either open, closed, or all to filter by state,
     *                             constants available {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the pull request - [string]
     *                        </li>
     *                        <li>
     *                            {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                            request - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T updatePullRequest(String owner, String repo, PullRequest pullRequest, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return updatePullRequest(owner, repo, pullRequest.getNumber(), bodyParams, format);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> either open, closed, or all to filter by state,
     *                            constants available {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public PullRequest updatePullRequest(String owner, String repo, long pullNumber, Params bodyParams) throws IOException {
        return updatePullRequest(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to open or update a pull request in a public repository, you must have write access to the head or the
     * source branch. For organization-owned repositories, you must be a member of the organization that owns the
     * repository to open or update a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the new pull request. Required unless issue is
     *                           specified - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> either open, closed, or all to filter by state,
     *                            constants available {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "base"} -> filter pulls by base branch name. Example: {@code "gh-pages"}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the pull request - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainer_can_modify"} -> indicates whether maintainers can modify the pull
     *                           request - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request">
     * Update a pull request</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/{pull_number}")
    public <T> T updatePullRequest(String owner, String repo, long pullNumber, Params bodyParams,
                                   ReturnFormat format) throws IOException {
        return returnPullRequest(sendPatchRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber, bodyParams), format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(Repository repository, PullRequest pullRequest) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(Repository repository, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(String owner, String repo, PullRequest pullRequest) throws IOException {
        return getPullRequestCommits(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(String owner, String repo, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return getPullRequestCommits(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(Repository repository, long pullNumber) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(String owner, String repo, long pullNumber) throws IOException {
        return getPullRequestCommits(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + COMMITS_PATH), format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(Repository repository, PullRequest pullRequest,
                                                   Params queryParams) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(Repository repository, PullRequest pullRequest, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(String owner, String repo, PullRequest pullRequest,
                                                   Params queryParams) throws IOException {
        return getPullRequestCommits(owner, repo, pullRequest.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(String owner, String repo, PullRequest pullRequest, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestCommits(owner, repo, pullRequest.getNumber(), queryParams, format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(Repository repository, long pullNumber,
                                                   Params queryParams) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(Repository repository, long pullNumber, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestCommits(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                format);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commits list as {@link ArrayList} of {@link Commit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public ArrayList<Commit> getPullRequestCommits(String owner, String repo, long pullNumber,
                                                   Params queryParams) throws IOException {
        return getPullRequestCommits(owner, repo, pullNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of a maximum of 250 commits for a pull request. To receive a complete commit list for pull
     * requests with more than 250 commits, use the List commits endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
     * List commits on a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    public <T> T getPullRequestCommits(String owner, String repo, long pullNumber, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + COMMITS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(Repository repository, PullRequest pullRequest) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(Repository repository, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(String owner, String repo, PullRequest pullRequest) throws IOException {
        return getPullRequestsFiles(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(String owner, String repo, PullRequest pullRequest,
                                      ReturnFormat format) throws IOException {
        return getPullRequestsFiles(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(Repository repository, long pullNumber) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(String owner, String repo, long pullNumber) throws IOException {
        return getPullRequestsFiles(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + FILES_PATH), format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(Repository repository, PullRequest pullRequest,
                                                      Params queryParams) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(Repository repository, PullRequest pullRequest, Params queryParams,
                                      ReturnFormat format) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(String owner, String repo, PullRequest pullRequest,
                                                      Params queryParams) throws IOException {
        return getPullRequestsFiles(owner, repo, pullRequest.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(String owner, String repo, PullRequest pullRequest, Params queryParams,
                                      ReturnFormat format) throws IOException {
        return getPullRequestsFiles(owner, repo, pullRequest.getNumber(), queryParams, format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(Repository repository, long pullNumber,
                                                      Params queryParams) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(Repository repository, long pullNumber, Params queryParams,
                                      ReturnFormat format) throws IOException {
        return getPullRequestsFiles(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                format);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull requests files list as {@link ArrayList} of {@link CommitFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public ArrayList<CommitFile> getPullRequestsFiles(String owner, String repo, long pullNumber,
                                                      Params queryParams) throws IOException {
        return getPullRequestsFiles(owner, repo, pullNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the pull requests files
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests-files">
     * List pull requests files</a>
     * @implNote Responses include a maximum of 3000 files. The paginated response returns 30 files per page by default
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/files")
    public <T> T getPullRequestsFiles(String owner, String repo, long pullNumber, Params queryParams,
                                      ReturnFormat format) throws IOException {
        return returnCommitsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + FILES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to check if a pull request has been merged
     *
     * @param repository:  the repository where check if a pull request has been merged
     * @param pullRequest: the pullRequest to check if has been merged
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#check-if-a-pull-request-has-been-merged">
     * Check if a pull request has been merged</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public boolean checkIfPullRequestMerged(Repository repository, PullRequest pullRequest) {
        return checkIfPullRequestMerged(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber());
    }

    /**
     * Method to check if a pull request has been merged
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pullRequest to check if has been merged
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#check-if-a-pull-request-has-been-merged">
     * Check if a pull request has been merged</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public boolean checkIfPullRequestMerged(String owner, String repo, PullRequest pullRequest) {
        return checkIfPullRequestMerged(owner, repo, pullRequest.getNumber());
    }

    /**
     * Method to check if a pull request has been merged
     *
     * @param repository: the repository where check if a pull request has been merged
     * @param pullNumber: the number that identifies the pull request
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#check-if-a-pull-request-has-been-merged">
     * Check if a pull request has been merged</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public boolean checkIfPullRequestMerged(Repository repository, long pullNumber) {
        return checkIfPullRequestMerged(repository.getOwner().getLogin(), repository.getName(), pullNumber);
    }

    /**
     * Method to check if a pull request has been merged
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#check-if-a-pull-request-has-been-merged">
     * Check if a pull request has been merged</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public boolean checkIfPullRequestMerged(String owner, String repo, long pullNumber) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber + MERGE_PATH);
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

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where merge the pull request
     * @param pullRequest: the pull request to merge
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(Repository repository, PullRequest pullRequest) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where merge the pull request
     * @param pullRequest: the pull request to merge
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(Repository repository, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request to merge
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(String owner, String repo, PullRequest pullRequest) throws IOException {
        return mergePullRequest(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request to merge
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(String owner, String repo, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return mergePullRequest(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where merge the pull request
     * @param pullNumber: the number that identifies the pull request
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(Repository repository, long pullNumber) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where merge the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(String owner, String repo, long pullNumber) throws IOException {
        return mergePullRequest(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return mergePullRequest(owner, repo, pullNumber, null, format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where merge the pull request
     * @param pullRequest: the pull request to merge
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_title"} -> title for the automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                        </li>
     *                        <li>
     *                            {@code "merge_method"} -> the merge method to use, constants available
     *                            {@link MergeMethod} - [string]
     *                        </li>
     *                     </ul>
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(Repository repository, PullRequest pullRequest, Params bodyParams) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where merge the pull request
     * @param pullRequest: the pull request to merge
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_title"} -> title for the automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                        </li>
     *                        <li>
     *                            {@code "merge_method"} -> the merge method to use, constants available
     *                            {@link MergeMethod} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(Repository repository, PullRequest pullRequest, Params bodyParams,
                                  ReturnFormat format) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), bodyParams,
                format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request to merge
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_title"} -> title for the automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                        </li>
     *                        <li>
     *                            {@code "merge_method"} -> the merge method to use, constants available
     *                            {@link MergeMethod} - [string]
     *                        </li>
     *                     </ul>
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(String owner, String repo, PullRequest pullRequest, Params bodyParams) throws IOException {
        return mergePullRequest(owner, repo, pullRequest.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request to merge
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_title"} -> title for the automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                        </li>
     *                        <li>
     *                            {@code "merge_method"} -> the merge method to use, constants available
     *                            {@link MergeMethod} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(String owner, String repo, PullRequest pullRequest, Params bodyParams,
                                  ReturnFormat format) throws IOException {
        return mergePullRequest(owner, repo, pullRequest.getNumber(), bodyParams, format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where merge the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_title"} -> title for the automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_method"} -> the merge method to use, constants available
     *                           {@link MergeMethod} - [string]
     *                       </li>
     *                    </ul>
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(Repository repository, long pullNumber, Params bodyParams) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where merge the pull request
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_title"} -> title for the automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_method"} -> the merge method to use, constants available
     *                           {@link MergeMethod} - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(Repository repository, long pullNumber, Params bodyParams,
                                  ReturnFormat format) throws IOException {
        return mergePullRequest(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams,
                format);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_title"} -> title for the automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_method"} -> the merge method to use, constants available
     *                           {@link MergeMethod} - [string]
     *                       </li>
     *                    </ul>
     * @return merge result as {@link MergeResult} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public MergeResult mergePullRequest(String owner, String repo, long pullNumber, Params bodyParams) throws IOException {
        return mergePullRequest(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_title"} -> title for the automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "commit_message"} -> extra detail to append to automatic commit message - [string]
     *                       </li>
     *                       <li>
     *                           {@code "sha"} -> SHA that pull request head must match to allow merge - [string]
     *                       </li>
     *                       <li>
     *                           {@code "merge_method"} -> the merge method to use, constants available
     *                           {@link MergeMethod} - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return merge result as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#merge-a-pull-request">
     * Merge a pull request</a>
     **/
    @Returner
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/merge")
    public <T> T mergePullRequest(String owner, String repo, long pullNumber, Params bodyParams,
                                  ReturnFormat format) throws IOException {
        String mergeResultResponse = sendPutRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + MERGE_PATH, bodyParams);
        switch (format) {
            case JSON:
                return (T) new JSONObject(mergeResultResponse);
            case LIBRARY_OBJECT:
                return (T) new MergeResult(new JSONObject(mergeResultResponse));
            default:
                return (T) mergeResultResponse;
        }
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:  the repository where update the pull request branch
     * @param pullRequest: the pull request where update the pull request branch
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(Repository repository, PullRequest pullRequest) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:  the repository where update the pull request branch
     * @param pullRequest: the pull request where update the pull request branch
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(Repository repository, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(), format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the pull request branch
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(String owner, String repo, PullRequest pullRequest) throws IOException {
        return updatePullRequestBranch(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the pull request branch
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(String owner, String repo, PullRequest pullRequest,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestBranch(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository: the repository where update the pull request branch
     * @param pullNumber: the number that identifies the pull request
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(Repository repository, long pullNumber) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository: the repository where update the pull request branch
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(String owner, String repo, long pullNumber) throws IOException {
        return updatePullRequestBranch(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return updatePullRequestBranch(owner, repo, pullNumber, null, format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:      the repository where update the pull request branch
     * @param pullRequest:     the pull request where update the pull request branch
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(Repository repository, PullRequest pullRequest,
                                                     String expectedHeadSha) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                expectedHeadSha, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:      the repository where update the pull request branch
     * @param pullRequest:     the pull request where update the pull request branch
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(Repository repository, PullRequest pullRequest, String expectedHeadSha,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                expectedHeadSha, format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param pullRequest:     the pull request where update the pull request branch
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(String owner, String repo, PullRequest pullRequest,
                                                     String expectedHeadSha) throws IOException {
        return updatePullRequestBranch(owner, repo, pullRequest.getNumber(), expectedHeadSha, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param pullRequest:     the pull request where update the pull request branch
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(String owner, String repo, PullRequest pullRequest, String expectedHeadSha,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestBranch(owner, repo, pullRequest.getNumber(), expectedHeadSha, format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:      the repository where update the pull request branch
     * @param pullNumber:      the number that identifies the pull request
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(Repository repository, long pullNumber,
                                                     String expectedHeadSha) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                expectedHeadSha, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param repository:      the repository where update the pull request branch
     * @param pullNumber:      the number that identifies the pull request
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(Repository repository, long pullNumber, String expectedHeadSha,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestBranch(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                expectedHeadSha, format);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param pullNumber:      the number that identifies the pull request
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @return pull request branch as {@link PullRequestBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public PullRequestBranch updatePullRequestBranch(String owner, String repo, long pullNumber,
                                                     String expectedHeadSha) throws IOException {
        return updatePullRequestBranch(owner, repo, pullNumber, expectedHeadSha, LIBRARY_OBJECT);
    }

    /**
     * Method to update the pull request branch with the latest upstream changes by merging HEAD from the base branch
     * into the pull request branch
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param pullNumber:      the number that identifies the pull request
     * @param expectedHeadSha: the expected SHA of the pull request's HEAD ref. This is the most recent commit on the
     *                         pull request's branch. If the expected SHA does not match the pull request's HEAD, you
     *                         will receive a 422 Unprocessable Entity status. You can use the "List commits" endpoint to
     *                         find the most recent commit SHA. Default: SHA of the pull request's current HEAD ref
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return pull request branch list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#update-a-pull-request-branch">
     * Update a pull request branch</a>
     **/
    @Returner
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/update-branch")
    public <T> T updatePullRequestBranch(String owner, String repo, long pullNumber, String expectedHeadSha,
                                         ReturnFormat format) throws IOException {
        Params bodyParams = null;
        if (expectedHeadSha != null) {
            bodyParams = new Params();
            bodyParams.addParam("expected_head_sha", expectedHeadSha);
        }
        String updatedBranchResponse = sendPutRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + UPDATE_BRANCH_PATH, bodyParams);
        switch (format) {
            case JSON:
                return (T) new JSONObject(updatedBranchResponse);
            case LIBRARY_OBJECT:
                return (T) new PullRequestBranch(new JSONObject(updatedBranchResponse));
            default:
                return (T) updatedBranchResponse;
        }
    }

}
