package com.tecknobit.githubmanager.branches.protectedbranches;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.branches.branches.records.Branch;
import com.tecknobit.githubmanager.branches.records.BranchProtection;
import com.tecknobit.githubmanager.branches.records.BranchProtection.ProtectionItem;
import com.tecknobit.githubmanager.branches.records.BranchProtection.RequiredPullRequestReviews;
import com.tecknobit.githubmanager.branches.records.BranchProtection.RequiredStatusCheck;
import com.tecknobit.githubmanager.branches.records.BranchProtection.RequiredStatusCheck.Check;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.apps.records.GitHubApp.returnAppsList;
import static com.tecknobit.githubmanager.branches.records.BranchProtection.Restrictions;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;
import static com.tecknobit.githubmanager.records.parents.GitHubResponse.returnStringsList;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

/**
 * The {@code GitHubProtectedBranchesManager} class is useful to manage all GitHub's protected branches endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection">
 * Protected branches</a>
 * @see GitHubManager
 **/
public class GitHubProtectedBranchesManager extends GitHubManager {

    /**
     * {@code BRANCHES_QUERY_PATH} constant for {@code "/branches/"} path
     **/
    public static final String BRANCHES_QUERY_PATH = "/branches/";

    /**
     * {@code PROTECTION_PATH} constant for {@code "/protection"} path
     **/
    public static final String PROTECTION_PATH = "/protection";

    /**
     * {@code ENFORCE_ADMINS_PATH} constant for {@code "/protection/enforce_admins"} path
     **/
    public static final String PROTECTION_ENFORCE_ADMINS_PATH = PROTECTION_PATH + "/enforce_admins";

    /**
     * {@code PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH} constant for {@code "/protection/required_pull_request_reviews"} path
     **/
    public static final String PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH = PROTECTION_PATH + "/required_pull_request_reviews";

    /**
     * {@code PROTECTION_REQUIRED_SIGNATURES_PATH} constant for {@code "/protection/required_signatures"} path
     **/
    public static final String PROTECTION_REQUIRED_SIGNATURES_PATH = PROTECTION_PATH + "/required_signatures";

    /**
     * {@code PROTECTION_REQUIRED_STATUS_CHECKS_PATH} constant for {@code "/protection/required_status_checks"} path
     **/
    public static final String PROTECTION_REQUIRED_STATUS_CHECKS_PATH = PROTECTION_PATH + "/required_status_checks";

    /**
     * {@code PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH} constant for {@code "/protection/required_status_checks/contexts"} path
     **/
    public static final String PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH = PROTECTION_REQUIRED_STATUS_CHECKS_PATH +
            "/contexts";

    /**
     * {@code PROTECTION_RESTRICTIONS_PATH} constant for {@code "/protection/restrictions"} path
     **/
    public static final String PROTECTION_RESTRICTIONS_PATH = PROTECTION_PATH + "/restrictions";

    /**
     * {@code PROTECTION_RESTRICTIONS_APPS_PATH} constant for {@code "/protection/restrictions/apps"} path
     **/
    public static final String PROTECTION_RESTRICTIONS_APPS_PATH = PROTECTION_RESTRICTIONS_PATH + "/apps";

    /**
     * {@code PROTECTION_RESTRICTIONS_TEAMS_PATH} constant for {@code "/protection/restrictions/teams"} path
     **/
    public static final String PROTECTION_RESTRICTIONS_TEAMS_PATH = PROTECTION_RESTRICTIONS_PATH + "/teams";

    /**
     * {@code PROTECTION_RESTRICTIONS_USERS_PATH} constant for {@code "/protection/restrictions/users"} path
     **/
    public static final String PROTECTION_RESTRICTIONS_USERS_PATH = PROTECTION_RESTRICTIONS_PATH + "/users";

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubProtectedBranchesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubProtectedBranchesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubProtectedBranchesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubProtectedBranchesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
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
    public GitHubProtectedBranchesManager() {
        super();
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the branch protection
     * @param branch:     branch from fetch the branch protection
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection getBranchProtection(Repository repository, Branch branch) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the branch protection
     * @param branch:     branch from fetch the branch protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T getBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the branch protection
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection getBranchProtection(String owner, String repo, Branch branch) throws Exception {
        return getBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the branch protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T getBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getBranchProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection getBranchProtection(Repository repository, String branch) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T getBranchProtection(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection getBranchProtection(String owner, String repo, String branch) throws Exception {
        return getBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
     * Get branch protection</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T getBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        return returnBranchProtection(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_PATH), format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(Repository repository, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(Repository repository, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(String owner, String repo, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(String owner, String repo, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(Repository repository, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(Repository repository, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(String owner, String repo, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(String owner, String repo, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, null, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(Repository repository, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(Repository repository, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(String owner, String repo, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     branch from fetch the branch protection
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(String owner, String repo, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(Repository repository, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param repository:                 repository from fetch the branch protection
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(Repository repository, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @return branch protection as {@link BranchProtection} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public BranchProtection updateBranchProtection(String owner, String repo, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Protecting a branch requires admin or owner permissions to the repository. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values. <br>
     * <b>Note:</b> The list of users, apps, and teams in total is limited to 100 items
     *
     * @param owner:                      the account owner of the repository. The name is not case-sensitive
     * @param repo:                       the name of the repository. The name is not case-sensitive
     * @param branch:                     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param requiredStatusCheck:        require status checks to pass before merging. Set to null to disable
     * @param enforceAdmins:              enforce all configured restrictions for administrators. Set to true to enforce required
     *                                    status checks for repository administrators. Set to null to disable
     * @param requiredPullRequestReviews: require at least one approving review on a pull request, before merging.
     *                                    Set to null to disable
     * @param restrictions:               restrict who can push to the protected branch. User, app, and team restrictions are only available
     *                                    for organization-owned repositories. Set to null to disable
     * @param bodyParams:                 extra body params not mandatory, keys accepted are:
     *                                    <ul>
     *                                       <li>
     *                                           {@code "required_linear_history"} -> enforces a linear commit Git history,
     *                                           which prevents anyone from pushing merge commits to a branch. Set to true to enforce a
     *                                           linear commit history. Set to false to disable a linear commit Git history.
     *                                           Your repository must allow squash merging or rebase merging before you can enable a
     *                                           linear commit history - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_force_pushes"} -> permits force pushes to the protected branch by anyone
     *                                           with write access to the repository.  Set to true to allow force pushes.
     *                                           Set to false or null to block force pushes - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_deletions"} -> allows deletion of the protected branch by anyone with
     *                                           write access to the repository. Set to false to prevent deletion of the protected
     *                                           branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "block_creations"} -> if set to true, the restrictions branch protection settings
     *                                           which limits who can push will also block pushes which create new branches, unless the
     *                                           push is initiated by a user, team, or app which has the ability to push. Set to true
     *                                           to restrict new branch creation - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "required_conversation_resolution"} -> requires all conversations on code to be
     *                                           resolved before a pull request can be merged into a branch that matches this rule.
     *                                           Set to false to disable - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "lock_branch"} -> whether to set the branch as read-only. If this is true, users
     *                                           will not be able to push to the branch - [boolean, default false]
     *                                       </li>
     *                                       <li>
     *                                           {@code "allow_fork_syncing"} -> whether users can pull changes from upstream when the
     *                                           branch is locked. Set to true to allow fork syncing. Set to false to prevent
     *                                           fork syncing - [boolean, default false]
     *                                       </li>
     *                                    </ul>
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
     * Update branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public <T> T updateBranchProtection(String owner, String repo, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        Params payload = new Params(bodyParams);
        if (requiredStatusCheck != null)
            payload.addParam("required_status_checks", requiredStatusCheck);
        if (enforceAdmins != null)
            payload.addParam("enforce_admins", enforceAdmins);
        if (requiredPullRequestReviews != null)
            payload.addParam("required_pull_request_reviews", requiredPullRequestReviews);
        if (restrictions != null)
            payload.addParam("restrictions", restrictions);
        return returnBranchProtection(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_PATH, payload), format);
    }

    /**
     * Method to create a branch protection object
     *
     * @param branchProtectionResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return branch protection as {@code "format"} defines
     **/
    @Returner
    private <T> T returnBranchProtection(String branchProtectionResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(branchProtectionResponse);
            case LIBRARY_OBJECT:
                return (T) new BranchProtection(new JSONObject(branchProtectionResponse));
            default:
                return (T) branchProtectionResponse;
        }
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from delete the branch protection
     * @param branch:     the branch from delete the branch protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-branch-protection">
     * Delete branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public boolean deleteBranchProtection(Repository repository, Branch branch) {
        return deleteBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from delete the branch protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-branch-protection">
     * Delete branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public boolean deleteBranchProtection(String owner, String repo, Branch branch) {
        return deleteBranchProtection(owner, repo, branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from delete the branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-branch-protection">
     * Delete branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public boolean deleteBranchProtection(Repository repository, String branch) {
        return deleteBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-branch-protection">
     * Delete branch protection</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection")
    public boolean deleteBranchProtection(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch + PROTECTION_PATH);
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
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the admin branch protection
     * @param branch:     branch from fetch the admin branch protection
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem getAdminBranchProtection(Repository repository, Branch branch) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the admin branch protection
     * @param branch:     branch from fetch the admin branch protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T getAdminBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the admin branch protection
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem getAdminBranchProtection(String owner, String repo, Branch branch) throws IOException {
        return getAdminBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the admin branch protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T getAdminBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the admin branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem getAdminBranchProtection(Repository repository, String branch) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the admin branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T getAdminBranchProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem getAdminBranchProtection(String owner, String repo, String branch) throws IOException {
        return getAdminBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-admin-branch-protection">
     * Get admin branch protection</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T getAdminBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_ENFORCE_ADMINS_PATH), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: repository where set the admin branch protection
     * @param branch:     branch where set the admin branch protection
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem setAdminBranchProtection(Repository repository, Branch branch) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: repository where set the admin branch protection
     * @param branch:     branch where set the admin branch protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T setAdminBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch where set the admin branch protection
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem setAdminBranchProtection(String owner, String repo, Branch branch) throws IOException {
        return setAdminBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch where set the admin branch protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T setAdminBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: repository where set the admin branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem setAdminBranchProtection(Repository repository, String branch) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: repository where set the admin branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T setAdminBranchProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return admin branch protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public ProtectionItem setAdminBranchProtection(String owner, String repo, String branch) throws IOException {
        return setAdminBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation. <br>
     * Adding admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return admin branch protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-admin-branch-protection">
     * Set admin branch protection</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public <T> T setAdminBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_ENFORCE_ADMINS_PATH, null), format);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository from delete the admin branch protection
     * @param branch:     the branch from delete the admin branch protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-admin-branch-protection">
     * Delete admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public boolean deleteAdminBranchProtection(Repository repository, Branch branch) {
        return deleteAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from delete the admin branch protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-admin-branch-protection">
     * Delete admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public boolean deleteAdminBranchProtection(String owner, String repo, Branch branch) {
        return deleteAdminBranchProtection(owner, repo, branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository from delete the admin branch protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-admin-branch-protection">
     * Delete admin branch protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public boolean deleteAdminBranchProtection(Repository repository, String branch) {
        return deleteAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-admin-branch-protection">
     * Delete admin branch protection</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/enforce_admins")
    public boolean deleteAdminBranchProtection(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                    + PROTECTION_ENFORCE_ADMINS_PATH);
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
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     branch from fetch the pull request review protection
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(Repository repository,
                                                                             Branch branch) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     branch from fetch the pull request review protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T getPullRequestReviewProtection(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the pull request review protection
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(String owner, String repo,
                                                                             Branch branch) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the pull request review protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T getPullRequestReviewProtection(String owner, String repo, Branch branch,
                                                ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(Repository repository,
                                                                             String branch) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T getPullRequestReviewProtection(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(String owner, String repo,
                                                                             String branch) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-pull-request-review-protection">
     * Get pull request review protection</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T getPullRequestReviewProtection(String owner, String repo, String branch,
                                                ReturnFormat format) throws Exception {
        return returnPullRequestReview(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     branch from fetch the pull request review protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(Repository repository, Branch branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     branch from fetch the pull request review protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T updatePullRequestReviewProtection(Repository repository, Branch branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     branch from fetch the pull request review protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(String owner, String repo, Branch branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     branch from fetch the pull request review protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T updatePullRequestReviewProtection(String owner, String repo, Branch branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch.getName(), bodyParams, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(Repository repository, String branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param repository: repository from fetch the pull request review protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T updatePullRequestReviewProtection(Repository repository, String branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @return pull request review protection as {@link RequiredPullRequestReviews} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(String owner, String repo, String branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating pull request review enforcement requires admin or owner permissions to the repository and branch protection to be enabled. <br>
     * <b>Note:</b> Passing new arrays of users and teams replaces their previous values
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissal_restrictions"} -> specify which users, teams, and apps can dismiss pull
     *                           request reviews. Pass an empty {@code "dismissal_restrictions"} object to disable. User and team
     *                           {@code "dismissal_restrictions"} are only available for organization-owned repositories.
     *                           Omit this parameter for personal repositories - [object]
     *                       </li>
     *                       <li>
     *                           {@code "dismiss_stale_reviews"} -> set to true if you want to automatically dismiss
     *                           approving reviews when someone pushes a new commit - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "require_code_owner_reviews"} -> blocks merging pull requests until code owners
     *                           have reviewed - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "required_approving_review_count"} -> specifies the number of reviewers required
     *                           to approve pull requests. Use a number between 1 and 6 or 0 to not require reviewers
     *                           - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "require_last_push_approval"} -> whether the most recent push must be approved
     *                           by someone other than the person who pushed it - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "bypass_pull_request_allowances"} -> allow specific users, teams, or apps to bypass
     *                           pull request requirements - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-pull-request-review-protection">
     * Update pull request review protection</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public <T> T updatePullRequestReviewProtection(String owner, String repo, String branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return returnPullRequestReview(sendPatchRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH, bodyParams), format);
    }

    /**
     * Method to create a pull request review object
     *
     * @param pullRequestReviewResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPullRequestReview(String pullRequestReviewResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(pullRequestReviewResponse);
            case LIBRARY_OBJECT:
                return (T) new RequiredPullRequestReviews<>(new JSONObject(pullRequestReviewResponse));
            default:
                return (T) pullRequestReviewResponse;
        }
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository from delete pull request review
     * @param branch:     the branch from delete the pull request review
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-pull-request-review-protection">
     * Delete pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public boolean deletePullRequestReviewProtection(Repository repository, Branch branch) {
        return deletePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from delete the pull request review
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-pull-request-review-protection">
     * Delete pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public boolean deletePullRequestReviewProtection(String owner, String repo, Branch branch) {
        return deletePullRequestReviewProtection(owner, repo, branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository from delete the pull request review
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-pull-request-review-protection">
     * Delete pull request review protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public boolean deletePullRequestReviewProtection(Repository repository, String branch) {
        return deletePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Removing admin enforcement requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-pull-request-review-protection">
     * Delete pull request review protection</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_pull_request_reviews")
    public boolean deletePullRequestReviewProtection(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                    PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH);
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
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param repository: repository from fetch the commit signature protection
     * @param branch:     branch from fetch the commit signature protection
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem getCommitSignatureProtection(Repository repository, Branch branch) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param repository: repository from fetch the commit signature protection
     * @param branch:     branch from fetch the commit signature protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T getCommitSignatureProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the commit signature protection
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem getCommitSignatureProtection(String owner, String repo, Branch branch) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the commit signature protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T getCommitSignatureProtection(String owner, String repo, Branch branch,
                                              ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param repository: repository from fetch the commit signature protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem getCommitSignatureProtection(Repository repository, String branch) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param repository: repository from fetch the commit signature protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T getCommitSignatureProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem getCommitSignatureProtection(String owner, String repo, String branch) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help. <br>
     * <b>Note:</b> You must enable branch protection to require signed commit
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-commit-signature-protection">
     * Get commit signature protection</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T getCommitSignatureProtection(String owner, String repo, String branch,
                                              ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_SIGNATURES_PATH), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: repository where create the commit signature protection
     * @param branch:     branch where create the commit signature protection
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem createCommitSignatureProtection(Repository repository, Branch branch) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: repository where create the commit signature protection
     * @param branch:     branch where create the commit signature protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T createCommitSignatureProtection(Repository repository, Branch branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch where create the commit signature protection
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem createCommitSignatureProtection(String owner, String repo, Branch branch) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch where create the commit signature protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T createCommitSignatureProtection(String owner, String repo, Branch branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: repository where create the commit signature protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem createCommitSignatureProtection(Repository repository, String branch) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: repository where create the commit signature protection
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T createCommitSignatureProtection(Repository repository, String branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return commit signature protection as {@link ProtectionItem} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public ProtectionItem createCommitSignatureProtection(String owner, String repo, String branch) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit signature protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#create-commit-signature-protection">
     * Create commit signature protection</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public <T> T createCommitSignatureProtection(String owner, String repo, String branch,
                                                 ReturnFormat format) throws IOException {
        return returnProtectionItem(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_REQUIRED_SIGNATURES_PATH, null), format);
    }

    /**
     * Method to create a protection item object
     *
     * @param enforceAdminsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return protection item as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProtectionItem(String enforceAdminsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(enforceAdminsResponse);
            case LIBRARY_OBJECT:
                return (T) new ProtectionItem(new JSONObject(enforceAdminsResponse));
            default:
                return (T) enforceAdminsResponse;
        }
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: the repository from delete the commit signature protection
     * @param branch:     the branch from delete the commit signature protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-commit-signature-protection">
     * Delete commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public boolean deleteCommitSignatureProtection(Repository repository, Branch branch) {
        return deleteCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from delete the commit signature protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-commit-signature-protection">
     * Delete commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public boolean deleteCommitSignatureProtection(String owner, String repo, Branch branch) {
        return deleteCommitSignatureProtection(owner, repo, branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param repository: the repository from delete the commit signature protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-commit-signature-protection">
     * Delete commit signature protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public boolean deleteCommitSignatureProtection(Repository repository, String branch) {
        return deleteCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * When authenticated with admin or owner permissions to the repository, you can use this endpoint to check whether
     * a branch requires signed commits. An enabled status of true indicates you must sign commits on this branch.
     * For more information, see Signing commits with GPG in GitHub Help
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-commit-signature-protection">
     * Delete commit signature protection</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_signatures")
    public boolean deleteCommitSignatureProtection(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                    PROTECTION_REQUIRED_SIGNATURES_PATH);
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
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the status checks protection
     * @param branch:     branch from fetch the status checks protection
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck getStatusChecksProtection(Repository repository, Branch branch) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the status checks protection
     * @param branch:     branch from fetch the status checks protection
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T getStatusChecksProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the status checks protection
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck getStatusChecksProtection(String owner, String repo, Branch branch) throws IOException {
        return getStatusChecksProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: branch from fetch the status checks protection
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T getStatusChecksProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the status checks protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck getStatusChecksProtection(Repository repository, String branch) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the status checks protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T getStatusChecksProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck getStatusChecksProtection(String owner, String repo, String branch) throws IOException {
        return getStatusChecksProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-status-checks-protection">
     * Get status checks protection</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T getStatusChecksProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_STATUS_CHECKS_PATH), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository where update the status checks protection
     * @param branch:     branch where update the status checks protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck updateStatusChecksProtection(Repository repository, Branch branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository where update the status checks protection
     * @param branch:     branch where update the status checks protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T updateStatusChecksProtection(Repository repository, Branch branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     branch where update the status checks protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck updateStatusChecksProtection(String owner, String repo, Branch branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     branch where update the status checks protection
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T updateStatusChecksProtection(String owner, String repo, Branch branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch.getName(), bodyParams, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository: the repository where update the status checks protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck updateStatusChecksProtection(Repository repository, String branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param repository:  the repository where update the status checks protection
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @param format:                        return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T updateStatusChecksProtection(Repository repository, String branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @return status checks protection as {@link RequiredStatusCheck} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public RequiredStatusCheck updateStatusChecksProtection(String owner, String repo, String branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Updating required status checks requires admin or owner permissions to the repository and branch protection to be enabled
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "strict"} -> require branches to be up to date before merging - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "checks"} -> the list of status checks to require in order to merge into this branch
     *                           - [array of objects, you can use {@link Check} to create this list]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#update-status-check-protection">
     * Update status check protection</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public <T> T updateStatusChecksProtection(String owner, String repo, String branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return returnProtectionItem(sendPatchRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_STATUS_CHECKS_PATH, bodyParams), format);
    }

    /**
     * Method to create a status check protection object
     *
     * @param statusCheckProtectionResponse: obtained from GitHub's response
     * @param format:                        return type formatter -> {@link ReturnFormat}
     * @return status check protection as {@code "format"} defines
     **/
    @Returner
    private <T> T returnStatusCheckProtection(String statusCheckProtectionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(statusCheckProtectionResponse);
            case LIBRARY_OBJECT:
                return (T) new RequiredStatusCheck(new JSONObject(statusCheckProtectionResponse));
            default:
                return (T) statusCheckProtectionResponse;
        }
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove status check protection
     * @param branch:     the branch from remove status check protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-protection">
     * Remove status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public boolean removeStatusCheckProtection(Repository repository, Branch branch) {
        return removeStatusCheckProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from remove status check protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-protection">
     * Remove status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public boolean removeStatusCheckProtection(String owner, String repo, Branch branch) {
        return removeStatusCheckProtection(owner, repo, branch.getName());
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove status check protection
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-protection">
     * Remove status check protection</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public boolean removeStatusCheckProtection(Repository repository, String branch) {
        return removeStatusCheckProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Method to delete protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-protection">
     * Remove status check protection</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks")
    public boolean removeStatusCheckProtection(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                    + PROTECTION_REQUIRED_STATUS_CHECKS_PATH);
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
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the branch  from fetch the list
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> getAllStatusCheckContexts(Repository repository, Branch branch) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the branch  from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T getAllStatusCheckContexts(Repository repository, Branch branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch  from fetch the list
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> getAllStatusCheckContexts(String owner, String repo, Branch branch) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch  from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T getAllStatusCheckContexts(String owner, String repo, Branch branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch.getName(), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> getAllStatusCheckContexts(Repository repository, String branch) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T getAllStatusCheckContexts(Repository repository, String branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> getAllStatusCheckContexts(String owner, String repo, String branch) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-all-status-check-contexts">
     * Get all status check contexts</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T getAllStatusCheckContexts(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnContexts(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the branch to add the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(Repository repository, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the branch to add the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to add the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(String owner, String repo, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to add the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(Repository repository, String branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(String owner, String repo, String branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the branch to add the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(Repository repository, Branch branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the branch to add the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to add the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(String owner, String repo, Branch branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(Repository repository, String branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> addStatusCheckContexts(String owner, String repo, String branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-status-check-contexts">
     * Add status check contexts</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T addStatusCheckContexts(String owner, String repo, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("contexts", contexts);
        return returnContexts(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH, payload), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(Repository repository, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(String owner, String repo, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(Repository repository, String branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(String owner, String repo, String branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(Repository repository, Branch branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(String owner, String repo, Branch branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(Repository repository, String branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository to set the list
     * @param branch:     the branch to set the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> setStatusCheckContexts(String owner, String repo, String branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch to set the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-status-check-contexts">
     * Set status check contexts</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T setStatusCheckContexts(String owner, String repo, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("contexts", contexts);
        return returnContexts(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH, payload), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(Repository repository, Branch branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(String owner, String repo, Branch branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(Repository repository, String branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(String owner, String repo, String branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(Repository repository, Branch branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(String owner, String repo, Branch branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository:  the repository from remove the list
     * @param branch: the branch from remove the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(Repository repository, String branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch from remove the list
     * @param contexts:   the name of the status checks as array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @return contexts list as {@link Collection} of {@link String}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public Collection<String> removeStatusCheckContexts(String owner, String repo, String branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    /**
     * Method to get protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param branch:   the branch from remove the list
     * @param contexts: the name of the status checks as array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-status-check-contexts">
     * Remove status check contexts</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/required_status_checks/contexts")
    public <T> T removeStatusCheckContexts(String owner, String repo, String branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("contexts", contexts);
        HashMap<String, Object> response = sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH, payload);
        String success = (String) response.get("success");
        if (success != null)
            return returnContexts(success, format);
        else
            throw new IOException((String) response.get("error"));
    }

    /**
     * Method to create a contexts list
     *
     * @param contextsResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return contexts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnContexts(String contextsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(contextsResponse);
            case LIBRARY_OBJECT:
                return (T) returnStringsList(new JSONArray(contextsResponse));
            default:
                return (T) contextsResponse;
        }
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param repository: the branch from fetch the access restrictions
     * @param branch:     the branch from fetch the access restrictions
     * @return access restrictions as {@link Restrictions} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public Restrictions<Object> getAccessRestrictions(Repository repository, Branch branch) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param repository: the branch from fetch the access restrictions
     * @param branch:     the branch from fetch the access restrictions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return access restrictions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public <T> T getAccessRestrictions(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from fetch the access restrictions
     * @return access restrictions as {@link Restrictions} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public Restrictions<Object> getAccessRestrictions(String owner, String repo, Branch branch) throws Exception {
        return getAccessRestrictions(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from fetch the access restrictions
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return access restrictions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public <T> T getAccessRestrictions(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(owner, repo, branch.getName(), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param repository: the branch from fetch the access restrictions
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @return access restrictions as {@link Restrictions} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public Restrictions<Object> getAccessRestrictions(Repository repository, String branch) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param repository: the branch from fetch the access restrictions
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return access restrictions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public <T> T getAccessRestrictions(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @return access restrictions as {@link Restrictions} of {@link Object} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public Restrictions<Object> getAccessRestrictions(String owner, String repo, String branch) throws Exception {
        return getAccessRestrictions(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list who has access to this protected branch. <br>
     * <b>Note:</b> Users, apps, and teams restrictions are only available for organization-owned repositories
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return access restrictions as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-access-restrictions">
     * Get access restrictions</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public <T> T getAccessRestrictions(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        String restrictionsResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_RESTRICTIONS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(restrictionsResponse);
            case LIBRARY_OBJECT:
                return (T) new Restrictions<>(new JSONObject(restrictionsResponse));
            default:
                return (T) restrictionsResponse;
        }
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to disable the ability to restrict who can push to this branch
     *
     * @param repository: the repository from delete access restrictions
     * @param branch:     the branch from delete access restrictions
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-access-restrictions">
     * Delete access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public boolean deleteAccessRestrictions(Repository repository, Branch branch) {
        return deleteAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to disable the ability to restrict who can push to this branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from delete access restrictions
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-access-restrictions">
     * Delete access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public boolean deleteAccessRestrictions(String owner, String repo, Branch branch) {
        return deleteAccessRestrictions(owner, repo, branch.getName());
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to disable the ability to restrict who can push to this branch
     *
     * @param repository: the repository from delete access restrictions
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-access-restrictions">
     * Delete access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public boolean deleteAccessRestrictions(Repository repository, String branch) {
        return deleteAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for
     * organizations, and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud,
     * and GitHub Enterprise Server. For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to disable the ability to restrict who can push to this branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#delete-access-restrictions">
     * Delete access restrictions</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions")
    public boolean deleteAccessRestrictions(String owner, String repo, String branch) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                    + PROTECTION_RESTRICTIONS_PATH);
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
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the branch from fetch the list
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> getAppsWithTheAccess(Repository repository, Branch branch) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the branch from fetch the list
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T getAppsWithTheAccess(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from fetch the list
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> getAppsWithTheAccess(String owner, String repo, Branch branch) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch from fetch the list
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T getAppsWithTheAccess(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch.getName(), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> getAppsWithTheAccess(Repository repository, String branch) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from fetch the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T getAppsWithTheAccess(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> getAppsWithTheAccess(String owner, String repo, String branch) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to get the list of the GitHub Apps that have push access to this branch. Only installed GitHub Apps with write access
     * to the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#get-apps-with-access-to-the-protected-branch">
     * Get apps with access to the protected branch</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T getAppsWithTheAccess(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        return returnAppsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the branch where add the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the branch where add the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where add the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where add
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, String branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, String branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name.
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the branch where add the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, Branch branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the branch where add the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where add the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where add the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, String branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where add the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, String branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to grant the specified apps push access for this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#add-app-access-restrictions">
     * Add app access restrictions</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T addAppAccessRestrictions(String owner, String repo, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("apps", apps);
        return returnAppsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH, payload), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the branch where set the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the branch where set the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where set the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where set the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, String branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, String branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the branch where set the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, Branch branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the branch where set the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where set the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where set the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, String branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository where set the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, String branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to replaces the list of apps that have push access to this branch. This removes all apps that previously
     * had push access and grants push access to the new list of apps. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#set-app-access-restrictions">
     * Set app access restrictions</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T setAppAccessRestrictions(String owner, String repo, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("apps", apps);
        return returnAppsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH, payload), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch where from remove the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, Branch branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch where from remove the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where from remove the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, Branch branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where from remove the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, String branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, String branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch where from remove the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, Branch branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch:     the branch where from remove the list
     * @param apps:       the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                    Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format      :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where from remove the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, Branch branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the branch where from remove the list
     * @param apps:   the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *                Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format  :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps: the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *            Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, String branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param repository: the repository from remove the list
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps: the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *            Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format       :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps: the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *            Note: The list of users, apps, and teams in total is limited to 100 items
     * @return apps list as {@link Collection} of {@link GitHubApp}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, String branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    /**
     * Protected branches are available in public repositories with GitHub Free and GitHub Free for organizations,
     * and in public and private repositories with GitHub Pro, GitHub Team, GitHub Enterprise Cloud, and GitHub Enterprise Server.
     * For more information, see GitHub's products in the GitHub Help documentation <br>
     * Method to removes the ability of an app to push to this branch. Only installed GitHub Apps with write access to
     * the contents permission can be added as authorized actors on a protected branch
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param branch: the name of the branch. Cannot contain wildcard characters. To use wildcard characters in branch names, use the GraphQL API.
     * @param apps: the GitHub Apps that have push access to this branch. Use the slugified version of the app name
     *            Note: The list of users, apps, and teams in total is limited to 100 items
     * @param format       :        return type formatter -> {@link ReturnFormat}
     * @return apps list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branch-protection#remove-app-access-restrictions">
     * Remove app access restrictions</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/branches/{branch}/protection/restrictions/apps")
    public <T> T removeAppAccessRestrictions(String owner, String repo, String branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("apps", apps);
        HashMap<String, Object> response = sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH
                + branch + PROTECTION_RESTRICTIONS_APPS_PATH, payload);
        String success = (String) response.get("success");
        if (success != null)
            return returnAppsList(success, format);
        else
            throw new IOException((String) response.get("error"));
    }

    public Collection<Team> getTeamsWithTheAccess(Repository repository, Branch branch) throws IOException {
        return getTeamsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getTeamsWithTheAccess(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getTeamsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public Collection<Team> getTeamsWithTheAccess(String owner, String repo, Branch branch) throws IOException {
        return getTeamsWithTheAccess(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getTeamsWithTheAccess(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getTeamsWithTheAccess(owner, repo, branch.getName(), format);
    }

    public Collection<Team> getTeamsWithTheAccess(Repository repository, String branch) throws IOException {
        return getTeamsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getTeamsWithTheAccess(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getTeamsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public Collection<Team> getTeamsWithTheAccess(String owner, String repo, String branch) throws IOException {
        return getTeamsWithTheAccess(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getTeamsWithTheAccess(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_TEAMS_PATH), format);
    }

    public Collection<Team> addTeamAccessRestrictions(Repository repository, Branch branch,
                                                      Collection<String> teams) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(Repository repository, Branch branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                format);
    }

    public Collection<Team> addTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                      Collection<String> teams) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(String owner, String repo, Branch branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> addTeamAccessRestrictions(Repository repository, String branch,
                                                      Collection<String> teams) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(Repository repository, String branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> addTeamAccessRestrictions(String owner, String repo, String branch,
                                                      Collection<String> teams) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(String owner, String repo, String branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch, teams.toArray(new String[0]), format);
    }

    public Collection<Team> addTeamAccessRestrictions(Repository repository, Branch branch,
                                                      String[] teams) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(Repository repository, Branch branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                format);
    }

    public Collection<Team> addTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                      String[] teams) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(String owner, String repo, Branch branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> addTeamAccessRestrictions(Repository repository, String branch,
                                                      String[] teams) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(Repository repository, String branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return addTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> addTeamAccessRestrictions(String owner, String repo, String branch,
                                                      String[] teams) throws IOException {
        return addTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T addTeamAccessRestrictions(String owner, String repo, String branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("teams", teams);
        return returnTeamsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_TEAMS_PATH, payload), format);
    }

    public Collection<Team> setTeamAccessRestrictions(Repository repository, Branch branch,
                                                      Collection<String> teams) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(Repository repository, Branch branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                format);
    }

    public Collection<Team> setTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                      Collection<String> teams) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(String owner, String repo, Branch branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> setTeamAccessRestrictions(Repository repository, String branch,
                                                      Collection<String> teams) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(Repository repository, String branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> setTeamAccessRestrictions(String owner, String repo, String branch,
                                                      Collection<String> teams) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(String owner, String repo, String branch, Collection<String> teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch, teams.toArray(new String[0]), format);
    }

    public Collection<Team> setTeamAccessRestrictions(Repository repository, Branch branch,
                                                      String[] teams) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(Repository repository, Branch branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                format);
    }

    public Collection<Team> setTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                      String[] teams) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(String owner, String repo, Branch branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> setTeamAccessRestrictions(Repository repository, String branch,
                                                      String[] teams) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(Repository repository, String branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        return setTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> setTeamAccessRestrictions(String owner, String repo, String branch,
                                                      String[] teams) throws IOException {
        return setTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T setTeamAccessRestrictions(String owner, String repo, String branch, String[] teams,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("teams", teams);
        return returnTeamsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_TEAMS_PATH, payload), format);
    }

    public Collection<Team> removeTeamAccessRestrictions(Repository repository, Branch branch,
                                                         Collection<String> teams) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(Repository repository, Branch branch, Collection<String> teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), teams,
                format);
    }

    public Collection<Team> removeTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                         Collection<String> teams) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(String owner, String repo, Branch branch, Collection<String> teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> removeTeamAccessRestrictions(Repository repository, String branch,
                                                         Collection<String> teams) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(Repository repository, String branch, Collection<String> teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> removeTeamAccessRestrictions(String owner, String repo, String branch,
                                                         Collection<String> teams) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(String owner, String repo, String branch, Collection<String> teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch, teams.toArray(new String[0]), format);
    }

    public Collection<Team> removeTeamAccessRestrictions(Repository repository, Branch branch,
                                                         String[] teams) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                teams, LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(Repository repository, Branch branch, String[] teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                teams, format);
    }

    public Collection<Team> removeTeamAccessRestrictions(String owner, String repo, Branch branch,
                                                         String[] teams) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch.getName(), teams, LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(String owner, String repo, Branch branch, String[] teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch.getName(), teams, format);
    }

    public Collection<Team> removeTeamAccessRestrictions(Repository repository, String branch,
                                                         String[] teams) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams,
                LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(Repository repository, String branch, String[] teams,
                                              ReturnFormat format) throws IOException {
        return removeTeamAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, teams, format);
    }

    public Collection<Team> removeTeamAccessRestrictions(String owner, String repo, String branch,
                                                         String[] teams) throws IOException {
        return removeTeamAccessRestrictions(owner, repo, branch, teams, LIBRARY_OBJECT);
    }

    public <T> T removeTeamAccessRestrictions(String owner, String repo, String branch, String[] teams,
                                              ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("teams", teams);
        HashMap<String, Object> response = sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH
                + branch + PROTECTION_RESTRICTIONS_TEAMS_PATH, payload);
        String success = (String) response.get("success");
        if (success != null)
            return returnTeamsList(success, format);
        else
            throw new IOException((String) response.get("error"));
    }

    public Collection<User> getUsersWithTheAccess(Repository repository, Branch branch) throws IOException {
        return getUsersWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getUsersWithTheAccess(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getUsersWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public Collection<User> getUsersWithTheAccess(String owner, String repo, Branch branch) throws IOException {
        return getUsersWithTheAccess(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getUsersWithTheAccess(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getUsersWithTheAccess(owner, repo, branch.getName(), format);
    }

    public Collection<User> getUsersWithTheAccess(Repository repository, String branch) throws IOException {
        return getUsersWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getUsersWithTheAccess(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getUsersWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public Collection<User> getUsersWithTheAccess(String owner, String repo, String branch) throws IOException {
        return getUsersWithTheAccess(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getUsersWithTheAccess(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_USERS_PATH), format);
    }

    public Collection<User> addUserAccessRestrictions(Repository repository, Branch branch,
                                                      Collection<String> users) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(Repository repository, Branch branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                format);
    }

    public Collection<User> addUserAccessRestrictions(String owner, String repo, Branch branch,
                                                      Collection<String> users) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(String owner, String repo, Branch branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> addUserAccessRestrictions(Repository repository, String branch,
                                                      Collection<String> users) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(Repository repository, String branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> addUserAccessRestrictions(String owner, String repo, String branch,
                                                      Collection<String> users) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(String owner, String repo, String branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch, users.toArray(new String[0]), format);
    }

    public Collection<User> addUserAccessRestrictions(Repository repository, Branch branch,
                                                      String[] users) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(Repository repository, Branch branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                format);
    }

    public Collection<User> addUserAccessRestrictions(String owner, String repo, Branch branch,
                                                      String[] users) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(String owner, String repo, Branch branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> addUserAccessRestrictions(Repository repository, String branch,
                                                      String[] users) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(Repository repository, String branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return addUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> addUserAccessRestrictions(String owner, String repo, String branch,
                                                      String[] users) throws IOException {
        return addUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T addUserAccessRestrictions(String owner, String repo, String branch, String[] users,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("users", users);
        return returnUsersList(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_USERS_PATH, payload), format);
    }

    public Collection<User> setUserAccessRestrictions(Repository repository, Branch branch,
                                                      Collection<String> users) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(Repository repository, Branch branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                format);
    }

    public Collection<User> setUserAccessRestrictions(String owner, String repo, Branch branch,
                                                      Collection<String> users) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(String owner, String repo, Branch branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> setUserAccessRestrictions(Repository repository, String branch,
                                                      Collection<String> users) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(Repository repository, String branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> setUserAccessRestrictions(String owner, String repo, String branch,
                                                      Collection<String> users) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(String owner, String repo, String branch, Collection<String> users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch, users.toArray(new String[0]), format);
    }

    public Collection<User> setUserAccessRestrictions(Repository repository, Branch branch,
                                                      String[] users) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(Repository repository, Branch branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), users,
                format);
    }

    public Collection<User> setUserAccessRestrictions(String owner, String repo, Branch branch,
                                                      String[] users) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(String owner, String repo, Branch branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> setUserAccessRestrictions(Repository repository, String branch,
                                                      String[] users) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(Repository repository, String branch, String[] users,
                                           ReturnFormat format) throws IOException {
        return setUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> setUserAccessRestrictions(String owner, String repo, String branch,
                                                      String[] users) throws IOException {
        return setUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T setUserAccessRestrictions(String owner, String repo, String branch, String[] users,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("users", users);
        return returnUsersList(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_USERS_PATH, payload), format);
    }

    public Collection<User> removeUserAccessRestrictions(Repository repository, Branch branch,
                                                         Collection<String> users) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(Repository repository, Branch branch, Collection<String> users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                users, format);
    }

    public Collection<User> removeUserAccessRestrictions(String owner, String repo, Branch branch,
                                                         Collection<String> users) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(String owner, String repo, Branch branch, Collection<String> users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> removeUserAccessRestrictions(Repository repository, String branch,
                                                         Collection<String> users) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(Repository repository, String branch, Collection<String> users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> removeUserAccessRestrictions(String owner, String repo, String branch,
                                                         Collection<String> users) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(String owner, String repo, String branch, Collection<String> users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch, users.toArray(new String[0]), format);
    }

    public Collection<User> removeUserAccessRestrictions(Repository repository, Branch branch,
                                                         String[] users) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(Repository repository, Branch branch, String[] users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                users, format);
    }

    public Collection<User> removeUserAccessRestrictions(String owner, String repo, Branch branch,
                                                         String[] users) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch.getName(), users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(String owner, String repo, Branch branch, String[] users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch.getName(), users, format);
    }

    public Collection<User> removeUserAccessRestrictions(Repository repository, String branch,
                                                         String[] users) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users,
                LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(Repository repository, String branch, String[] users,
                                              ReturnFormat format) throws IOException {
        return removeUserAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, users, format);
    }

    public Collection<User> removeUserAccessRestrictions(String owner, String repo, String branch,
                                                         String[] users) throws IOException {
        return removeUserAccessRestrictions(owner, repo, branch, users, LIBRARY_OBJECT);
    }

    public <T> T removeUserAccessRestrictions(String owner, String repo, String branch, String[] users,
                                              ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("users", users);
        HashMap<String, Object> response = sendDeleteRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH
                + branch + PROTECTION_RESTRICTIONS_USERS_PATH, payload);
        String success = (String) response.get("success");
        if (success != null)
            return returnUsersList(success, format);
        else
            throw new IOException((String) response.get("error"));
    }

}
