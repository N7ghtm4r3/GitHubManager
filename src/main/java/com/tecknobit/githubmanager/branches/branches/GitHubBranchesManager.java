package com.tecknobit.githubmanager.branches.branches;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.branches.branches.records.Branch;
import com.tecknobit.githubmanager.branches.branches.records.Branch.BranchCommit;
import com.tecknobit.githubmanager.branches.branches.records.ForkBranch;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubBranchesManager} class is useful to manage all GitHub's branches endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches">
 * Branches</a>
 * @see GitHubManager
 **/
public class GitHubBranchesManager extends GitHubManager {

    /**
     * {@code BRANCHES_PATH} constant for {@code "/branches"} path
     **/
    public static final String BRANCHES_PATH = "/branches";

    /**
     * {@code RENAME_PATH} constant for {@code "/rename"} path
     **/
    public static final String RENAME_PATH = "/rename";

    /**
     * {@code MERGE_UPSTREAM_PATH} constant for {@code "/merge-upstream"} path
     **/
    public static final String MERGE_UPSTREAM_PATH = "/merge-upstream";

    /**
     * {@code MERGES_PATH} constant for {@code "/merges"} path
     **/
    public static final String MERGES_PATH = "/merges";

    /**
     * Constructor to init a {@link GitHubBranchesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubBranchesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubBranchesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubBranchesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubBranchesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubBranchesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBranchesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubBranchesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBranchesManager} <br>
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
    public GitHubBranchesManager() {
        super();
    }

    /**
     * Method to get a list of branches
     *
     * @param repository: repository from fetch the list
     * @return branches list as {@link Collection} of {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public Collection<Branch> getBranches(Repository repository) throws Exception {
        return getBranches(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of branches
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branches list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public <T> T getBranches(Repository repository, ReturnFormat format) throws Exception {
        return getBranches(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of branches
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return branches list as {@link Collection} of {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public Collection<Branch> getBranches(String owner, String repo) throws Exception {
        return getBranches(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of branches
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return branches list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public <T> T getBranches(String owner, String repo, ReturnFormat format) throws Exception {
        return returnBranchesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_PATH), format);
    }

    /**
     * Method to get a list of branches
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "protected"} -> setting to true returns only protected branches.
     *                            When set to false, only unprotected branches are returned.
     *                            Omitting this parameter returns all branches - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return branches list as {@link Collection} of {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public Collection<Branch> getBranches(Repository repository, Params queryParams) throws Exception {
        return getBranches(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of branches
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "protected"} -> setting to true returns only protected branches.
     *                            When set to false, only unprotected branches are returned.
     *                            Omitting this parameter returns all branches - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branches list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public <T> T getBranches(Repository repository, Params queryParams, ReturnFormat format) throws Exception {
        return getBranches(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get a list of branches
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "protected"} -> setting to true returns only protected branches.
     *                            When set to false, only unprotected branches are returned.
     *                            Omitting this parameter returns all branches - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return branches list as {@link Collection} of {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public Collection<Branch> getBranches(String owner, String repo, Params queryParams) throws Exception {
        return getBranches(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of branches
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "protected"} -> setting to true returns only protected branches.
     *                            When set to false, only unprotected branches are returned.
     *                            Omitting this parameter returns all branches - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return branches list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
     * List branches</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches")
    public <T> T getBranches(String owner, String repo, Params queryParams, ReturnFormat format) throws Exception {
        return returnBranchesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create a branches list
     *
     * @param branchesResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return branches list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnBranchesList(String branchesResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(branchesResponse);
            case LIBRARY_OBJECT:
                ArrayList<Branch> branches = new ArrayList<>();
                JSONArray jBranches = new JSONArray(branchesResponse);
                for (int j = 0; j < jBranches.length(); j++)
                    branches.add(new Branch(jBranches.getJSONObject(j)));
                return (T) branches;
            default:
                return (T) branchesResponse;
        }
    }

    /**
     * Method to get a branch
     *
     * @param repository: repository from fetch the branch
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
     * Get a branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}")
    public Branch getBranch(Repository repository, String branch) throws Exception {
        return getBranch(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get a branch
     *
     * @param repository: repository from fetch the branch
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
     * Get a branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}")
    public <T> T getBranch(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getBranch(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get a branch
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
     * Get a branch</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}")
    public Branch getBranch(String owner, String repo, String branch) throws Exception {
        return getBranch(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get a branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
     * Get a branch</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}")
    public <T> T getBranch(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        return returnBranch(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_PATH + "/" + branch),
                format);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param repository: repository from fetch the branch
     * @param branch:     branch to rename
     * @param newName:    the new name of the branch
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public Branch renameBranch(Repository repository, Branch branch, String newName) throws Exception {
        return renameBranch(repository.getOwner().getLogin(), repository.getName(), branch.getName(), newName, LIBRARY_OBJECT);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param repository: repository from fetch the branch
     * @param branch:     branch to rename
     * @param newName:    the new name of the branch
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public <T> T renameBranch(Repository repository, Branch branch, String newName, ReturnFormat format) throws Exception {
        return renameBranch(repository.getOwner().getLogin(), repository.getName(), branch.getName(), newName, format);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param repository: repository from fetch the branch
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param newName:    the new name of the branch
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public Branch renameBranch(Repository repository, String branch, String newName) throws Exception {
        return renameBranch(repository.getOwner().getLogin(), repository.getName(), branch, newName, LIBRARY_OBJECT);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param repository: repository from fetch the branch
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param newName:    the new name of the branch
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public <T> T renameBranch(Repository repository, String branch, String newName, ReturnFormat format) throws Exception {
        return renameBranch(repository.getOwner().getLogin(), repository.getName(), branch, newName, format);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param branch:  branch to rename
     * @param newName: the new name of the branch
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public Branch renameBranch(String owner, String repo, Branch branch, String newName) throws Exception {
        return renameBranch(owner, repo, branch.getName(), newName, LIBRARY_OBJECT);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param branch:  branch to rename
     * @param newName: the new name of the branch
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public <T> T renameBranch(String owner, String repo, Branch branch, String newName,
                              ReturnFormat format) throws Exception {
        return renameBranch(owner, repo, branch.getName(), newName, format);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param branch:  the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param newName: the new name of the branch
     * @return branch as {@link Branch} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public Branch renameBranch(String owner, String repo, String branch, String newName) throws Exception {
        return renameBranch(owner, repo, branch, newName, LIBRARY_OBJECT);
    }

    /**
     * Method to renames a branch in a repository. <br>
     * Note: Although the API responds immediately, the branch rename process might take some extra time to complete in the background.
     * You won't be able to push to the old branch name while the rename process is in progress. For more information, see "Renaming a branch".
     * The permissions required to use this endpoint depends on whether you are renaming the default branch.
     * <ul>
     *     <li>
     *         To rename a non-default branch:
     *         <ul>
     *             <li>
     *                 Users must have push access.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the contents:write repository permission.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         To rename the default branch:
     *         <ul>
     *             <li>
     *                 Users must have admin or owner permissions.
     *             </li>
     *             <li>
     *                 GitHub Apps must have the {@code "administration:write"} repository permission
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param branch:  the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param newName: the new name of the branch
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
     * Rename a branch</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/rename")
    public <T> T renameBranch(String owner, String repo, String branch, String newName,
                              ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("new_name", newName);
        return returnBranch(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_PATH + "/" + branch +
                RENAME_PATH, payload), format);
    }

    /**
     * Method to create a branch object
     *
     * @param branchResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return branch as {@code "format"} defines
     **/
    @Returner
    private <T> T returnBranch(String branchResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(branchResponse);
            case LIBRARY_OBJECT:
                return (T) new Branch(new JSONObject(branchResponse));
            default:
                return (T) branchResponse;
        }
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param repository:upstream repository where merge the forked branch
     * @param branch:             the name of the branch which should be updated to match upstream
     * @return forked branch as {@link ForkBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public ForkBranch syncForkBranch(Repository repository, Branch branch) throws IOException {
        return syncForkBranch(repository.getOwner().getLogin(), repository.getName(), branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param repository:upstream repository where merge the forked branch
     * @param branch:             the name of the branch which should be updated to match upstream
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return forked branch as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public <T> T syncForkBranch(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return syncForkBranch(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param repository:upstream repository where merge the forked branch
     * @param branch:             the name of the branch which should be updated to match upstream
     * @return forked branch as {@link ForkBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public ForkBranch syncForkBranch(Repository repository, String branch) throws IOException {
        return syncForkBranch(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param repository:upstream repository where merge the forked branch
     * @param branch:             the name of the branch which should be updated to match upstream
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return forked branch as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public <T> T syncForkBranch(Repository repository, String branch, ReturnFormat format) throws IOException {
        return syncForkBranch(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch which should be updated to match upstream
     * @return forked branch as {@link ForkBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public ForkBranch syncForkBranch(String owner, String repo, Branch branch) throws IOException {
        return syncForkBranch(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch which should be updated to match upstream
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return forked branch as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public <T> T syncForkBranch(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return syncForkBranch(owner, repo, branch.getName(), format);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch which should be updated to match upstream
     * @return forked branch as {@link ForkBranch} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public ForkBranch syncForkBranch(String owner, String repo, String branch) throws IOException {
        return syncForkBranch(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to sync a branch of a forked repository to keep it up-to-date with the upstream repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch which should be updated to match upstream
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return forked branch as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merge-upstream")
    public <T> T syncForkBranch(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("branch", branch);
        String forkBranchResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo + MERGE_UPSTREAM_PATH, payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(forkBranchResponse);
            case LIBRARY_OBJECT:
                return (T) new ForkBranch(new JSONObject(forkBranchResponse));
            default:
                return (T) forkBranchResponse;
        }
    }

    /**
     * Method to merge a branch
     *
     * @param repository: repository where merge the branch
     * @param base:       the name of the base branch that the head will be merged into
     * @param head:       the head to merge. This can be a branch name or a commit SHA1
     * @return merge branch as {@link BranchCommit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public BranchCommit mergeBranch(Repository repository, String base, String head) throws IOException {
        return mergeBranch(repository.getOwner().getLogin(), repository.getName(), base, head, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a branch
     *
     * @param repository: repository where merge the branch
     * @param base:       the name of the base branch that the head will be merged into
     * @param head:       the head to merge. This can be a branch name or a commit SHA1
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch merged as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public <T> T mergeBranch(Repository repository, String base, String head, ReturnFormat format) throws IOException {
        return mergeBranch(repository.getOwner().getLogin(), repository.getName(), base, head, format);
    }

    /**
     * Method to merge a branch
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param base:  the name of the base branch that the head will be merged into
     * @param head:  the head to merge. This can be a branch name or a commit SHA1
     * @return merge branch as {@link BranchCommit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public BranchCommit mergeBranch(String owner, String repo, String base, String head) throws IOException {
        return mergeBranch(owner, repo, base, head, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param base:   the name of the base branch that the head will be merged into
     * @param head:   the head to merge. This can be a branch name or a commit SHA1
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return branch merged as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public <T> T mergeBranch(String owner, String repo, String base, String head, ReturnFormat format) throws IOException {
        return mergeBranch(owner, repo, base, head, null, format);
    }

    /**
     * Method to merge a branch
     *
     * @param repository:    repository where merge the branch
     * @param base:          the name of the base branch that the head will be merged into
     * @param head:          the head to merge. This can be a branch name or a commit SHA1
     * @param commitMessage: commit message to use for the merge commit. If omitted, a default message will be used
     * @return merge branch as {@link BranchCommit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public BranchCommit mergeBranch(Repository repository, String base, String head, String commitMessage) throws IOException {
        return mergeBranch(repository.getOwner().getLogin(), repository.getName(), base, head, commitMessage,
                LIBRARY_OBJECT);
    }

    /**
     * Method to merge a branch
     *
     * @param repository:    repository where merge the branch
     * @param base:          the name of the base branch that the head will be merged into
     * @param head:          the head to merge. This can be a branch name or a commit SHA1
     * @param commitMessage: commit message to use for the merge commit. If omitted, a default message will be used
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return branch merged as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public <T> T mergeBranch(Repository repository, String base, String head, String commitMessage,
                             ReturnFormat format) throws IOException {
        return mergeBranch(repository.getOwner().getLogin(), repository.getName(), base, head, commitMessage, format);
    }

    /**
     * Method to merge a branch
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param base:          the name of the base branch that the head will be merged into
     * @param head:          the head to merge. This can be a branch name or a commit SHA1
     * @param commitMessage: commit message to use for the merge commit. If omitted, a default message will be used
     * @return merge branch as {@link BranchCommit} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public BranchCommit mergeBranch(String owner, String repo, String base, String head,
                                    String commitMessage) throws IOException {
        return mergeBranch(owner, repo, base, head, commitMessage, LIBRARY_OBJECT);
    }

    /**
     * Method to merge a branch
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param base:          the name of the base branch that the head will be merged into
     * @param head:          the head to merge. This can be a branch name or a commit SHA1
     * @param commitMessage: commit message to use for the merge commit. If omitted, a default message will be used
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return branch merged as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
     * Sync a fork branch with the upstream repository</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/merges")
    public <T> T mergeBranch(String owner, String repo, String base, String head, String commitMessage,
                             ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("base", base);
        payload.addParam("head", head);
        if (commitMessage != null)
            payload.addParam("commit_message", commitMessage);
        String commitResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo + MERGES_PATH, payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(commitResponse);
            case LIBRARY_OBJECT:
                return (T) new BranchCommit(new JSONObject(commitResponse));
            default:
                return (T) commitResponse;
        }
    }

}
