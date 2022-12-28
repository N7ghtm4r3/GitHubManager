package com.tecknobit.githubmanager.branches.protectedbranches;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.branches.branches.records.Branch;
import com.tecknobit.githubmanager.branches.records.BranchProtection;
import com.tecknobit.githubmanager.branches.records.BranchProtection.ProtectionItem;
import com.tecknobit.githubmanager.branches.records.BranchProtection.RequiredPullRequestReviews;
import com.tecknobit.githubmanager.branches.records.BranchProtection.RequiredStatusCheck;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

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

    public BranchProtection getBranchProtection(Repository repository, Branch branch) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public BranchProtection getBranchProtection(String owner, String repo, Branch branch) throws Exception {
        return getBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getBranchProtection(owner, repo, branch.getName(), format);
    }

    public BranchProtection getBranchProtection(Repository repository, String branch) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getBranchProtection(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public BranchProtection getBranchProtection(String owner, String repo, String branch) throws Exception {
        return getBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        return returnBranchProtection(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH +
                branch + PROTECTION_PATH), format);
    }

    public BranchProtection updateBranchProtection(Repository repository, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(Repository repository, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, format);
    }

    public BranchProtection updateBranchProtection(String owner, String repo, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(String owner, String repo, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, format);
    }

    public BranchProtection updateBranchProtection(Repository repository, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(Repository repository, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, format);
    }

    public BranchProtection updateBranchProtection(String owner, String repo, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(String owner, String repo, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, null, format);
    }

    public BranchProtection updateBranchProtection(Repository repository, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(Repository repository, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                requiredStatusCheck, enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    public BranchProtection updateBranchProtection(String owner, String repo, Branch branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(String owner, String repo, Branch branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(owner, repo, branch.getName(), requiredStatusCheck, enforceAdmins,
                requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    public BranchProtection updateBranchProtection(Repository repository, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateBranchProtection(Repository repository, String branch, RequiredStatusCheck requiredStatusCheck,
                                        ProtectionItem enforceAdmins, RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                        Restrictions<String> restrictions, Params bodyParams,
                                        ReturnFormat format) throws Exception {
        return updateBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, requiredStatusCheck,
                enforceAdmins, requiredPullRequestReviews, restrictions, bodyParams, format);
    }

    public BranchProtection updateBranchProtection(String owner, String repo, String branch,
                                                   RequiredStatusCheck requiredStatusCheck, ProtectionItem enforceAdmins,
                                                   RequiredPullRequestReviews<String> requiredPullRequestReviews,
                                                   Restrictions<String> restrictions, Params bodyParams) throws Exception {
        return updateBranchProtection(owner, repo, branch, requiredStatusCheck, enforceAdmins, requiredPullRequestReviews,
                restrictions, bodyParams, LIBRARY_OBJECT);
    }

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

    public boolean deleteBranchProtection(Repository repository, Branch branch) {
        return deleteBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean deleteBranchProtection(String owner, String repo, Branch branch) {
        return deleteBranchProtection(owner, repo, branch.getName());
    }

    public boolean deleteBranchProtection(Repository repository, String branch) {
        return deleteBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public ProtectionItem getAdminBranchProtection(Repository repository, Branch branch) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getAdminBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public ProtectionItem getAdminBranchProtection(String owner, String repo, Branch branch) throws IOException {
        return getAdminBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getAdminBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(owner, repo, branch.getName(), format);
    }

    public ProtectionItem getAdminBranchProtection(Repository repository, String branch) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getAdminBranchProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public ProtectionItem getAdminBranchProtection(String owner, String repo, String branch) throws IOException {
        return getAdminBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getAdminBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_ENFORCE_ADMINS_PATH), format);
    }

    public ProtectionItem setAdminBranchProtection(Repository repository, Branch branch) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T setAdminBranchProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public ProtectionItem setAdminBranchProtection(String owner, String repo, Branch branch) throws IOException {
        return setAdminBranchProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T setAdminBranchProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(owner, repo, branch.getName(), format);
    }

    public ProtectionItem setAdminBranchProtection(Repository repository, String branch) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T setAdminBranchProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return setAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public ProtectionItem setAdminBranchProtection(String owner, String repo, String branch) throws IOException {
        return setAdminBranchProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T setAdminBranchProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_ENFORCE_ADMINS_PATH, null), format);
    }

    public boolean deleteAdminBranchProtection(Repository repository, Branch branch) {
        return deleteAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean deleteAdminBranchProtection(String owner, String repo, Branch branch) {
        return deleteAdminBranchProtection(owner, repo, branch.getName());
    }

    public boolean deleteAdminBranchProtection(Repository repository, String branch) {
        return deleteAdminBranchProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(Repository repository,
                                                                             Branch branch) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getPullRequestReviewProtection(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(String owner, String repo,
                                                                             Branch branch) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getPullRequestReviewProtection(String owner, String repo, Branch branch,
                                                ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch.getName(), format);
    }

    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(Repository repository,
                                                                             String branch) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    public <T> T getPullRequestReviewProtection(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getPullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public RequiredPullRequestReviews<Object> getPullRequestReviewProtection(String owner, String repo,
                                                                             String branch) throws Exception {
        return getPullRequestReviewProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getPullRequestReviewProtection(String owner, String repo, String branch,
                                                ReturnFormat format) throws Exception {
        return returnPullRequestReview(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_PULL_REQUEST_REVIEWS_PATH), format);
    }

    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(Repository repository, Branch branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updatePullRequestReviewProtection(Repository repository, Branch branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, format);
    }

    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(String owner, String repo, Branch branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch.getName(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updatePullRequestReviewProtection(String owner, String repo, Branch branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch.getName(), bodyParams, format);
    }

    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(Repository repository, String branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updatePullRequestReviewProtection(Repository repository, String branch, Params bodyParams,
                                                   ReturnFormat format) throws Exception {
        return updatePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                format);
    }

    public RequiredPullRequestReviews<Object> updatePullRequestReviewProtection(String owner, String repo, String branch,
                                                                                Params bodyParams) throws Exception {
        return updatePullRequestReviewProtection(owner, repo, branch, bodyParams, LIBRARY_OBJECT);
    }

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

    public boolean deletePullRequestReviewProtection(Repository repository, Branch branch) {
        return deletePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean deletePullRequestReviewProtection(String owner, String repo, Branch branch) {
        return deletePullRequestReviewProtection(owner, repo, branch.getName());
    }

    public boolean deletePullRequestReviewProtection(Repository repository, String branch) {
        return deletePullRequestReviewProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public ProtectionItem getCommitSignatureProtection(Repository repository, Branch branch) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getCommitSignatureProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public ProtectionItem getCommitSignatureProtection(String owner, String repo, Branch branch) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getCommitSignatureProtection(String owner, String repo, Branch branch,
                                              ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch.getName(), format);
    }

    public ProtectionItem getCommitSignatureProtection(Repository repository, String branch) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getCommitSignatureProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public ProtectionItem getCommitSignatureProtection(String owner, String repo, String branch) throws IOException {
        return getCommitSignatureProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getCommitSignatureProtection(String owner, String repo, String branch,
                                              ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_SIGNATURES_PATH), format);
    }

    public ProtectionItem createCommitSignatureProtection(Repository repository, Branch branch) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T createCommitSignatureProtection(Repository repository, Branch branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    public ProtectionItem createCommitSignatureProtection(String owner, String repo, Branch branch) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T createCommitSignatureProtection(String owner, String repo, Branch branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch.getName(), format);
    }

    public ProtectionItem createCommitSignatureProtection(Repository repository, String branch) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    public <T> T createCommitSignatureProtection(Repository repository, String branch,
                                                 ReturnFormat format) throws IOException {
        return createCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public ProtectionItem createCommitSignatureProtection(String owner, String repo, String branch) throws IOException {
        return createCommitSignatureProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

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

    public boolean deleteCommitSignatureProtection(Repository repository, Branch branch) {
        return deleteCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean deleteCommitSignatureProtection(String owner, String repo, Branch branch) {
        return deleteCommitSignatureProtection(owner, repo, branch.getName());
    }

    public boolean deleteCommitSignatureProtection(Repository repository, String branch) {
        return deleteCommitSignatureProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public RequiredStatusCheck getStatusChecksProtection(Repository repository, Branch branch) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getStatusChecksProtection(Repository repository, Branch branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public RequiredStatusCheck getStatusChecksProtection(String owner, String repo, Branch branch) throws IOException {
        return getStatusChecksProtection(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getStatusChecksProtection(String owner, String repo, Branch branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(owner, repo, branch.getName(), format);
    }

    public RequiredStatusCheck getStatusChecksProtection(Repository repository, String branch) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getStatusChecksProtection(Repository repository, String branch, ReturnFormat format) throws IOException {
        return getStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public RequiredStatusCheck getStatusChecksProtection(String owner, String repo, String branch) throws IOException {
        return getStatusChecksProtection(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getStatusChecksProtection(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnProtectionItem(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch
                + PROTECTION_REQUIRED_STATUS_CHECKS_PATH), format);
    }

    public RequiredStatusCheck updateStatusChecksProtection(Repository repository, Branch branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateStatusChecksProtection(Repository repository, Branch branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                bodyParams, format);
    }

    public RequiredStatusCheck updateStatusChecksProtection(String owner, String repo, Branch branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch.getName(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateStatusChecksProtection(String owner, String repo, Branch branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch.getName(), bodyParams, format);
    }

    public RequiredStatusCheck updateStatusChecksProtection(Repository repository, String branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                LIBRARY_OBJECT);
    }

    public <T> T updateStatusChecksProtection(Repository repository, String branch, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return updateStatusChecksProtection(repository.getOwner().getLogin(), repository.getName(), branch, bodyParams,
                format);
    }

    public RequiredStatusCheck updateStatusChecksProtection(String owner, String repo, String branch,
                                                            Params bodyParams) throws IOException {
        return updateStatusChecksProtection(owner, repo, branch, bodyParams, LIBRARY_OBJECT);
    }

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

    public boolean removeStatusCheckProtection(Repository repository, Branch branch) {
        return removeStatusCheckProtection(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean removeStatusCheckProtection(String owner, String repo, Branch branch) {
        return removeStatusCheckProtection(owner, repo, branch.getName());
    }

    public boolean removeStatusCheckProtection(Repository repository, String branch) {
        return removeStatusCheckProtection(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public Collection<String> getAllStatusCheckContexts(Repository repository, Branch branch) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getAllStatusCheckContexts(Repository repository, Branch branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                format);
    }

    public Collection<String> getAllStatusCheckContexts(String owner, String repo, Branch branch) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getAllStatusCheckContexts(String owner, String repo, Branch branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch.getName(), format);
    }

    public Collection<String> getAllStatusCheckContexts(Repository repository, String branch) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch,
                LIBRARY_OBJECT);
    }

    public <T> T getAllStatusCheckContexts(Repository repository, String branch,
                                           ReturnFormat format) throws IOException {
        return getAllStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public Collection<String> getAllStatusCheckContexts(String owner, String repo, String branch) throws IOException {
        return getAllStatusCheckContexts(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getAllStatusCheckContexts(String owner, String repo, String branch, ReturnFormat format) throws IOException {
        return returnContexts(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH), format);
    }

    public Collection<String> addStatusCheckContexts(Repository repository, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> addStatusCheckContexts(String owner, String repo, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> addStatusCheckContexts(Repository repository, String branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> addStatusCheckContexts(String owner, String repo, String branch,
                                                     Collection<String> contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    public Collection<String> addStatusCheckContexts(Repository repository, Branch branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> addStatusCheckContexts(String owner, String repo, Branch branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> addStatusCheckContexts(Repository repository, String branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return addStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> addStatusCheckContexts(String owner, String repo, String branch,
                                                     String[] contexts) throws IOException {
        return addStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    public <T> T addStatusCheckContexts(String owner, String repo, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("contexts", contexts);
        return returnContexts(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH, payload), format);
    }

    public Collection<String> setStatusCheckContexts(Repository repository, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> setStatusCheckContexts(String owner, String repo, Branch branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> setStatusCheckContexts(Repository repository, String branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> setStatusCheckContexts(String owner, String repo, String branch,
                                                     Collection<String> contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    public Collection<String> setStatusCheckContexts(Repository repository, Branch branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> setStatusCheckContexts(String owner, String repo, Branch branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> setStatusCheckContexts(Repository repository, String branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        return setStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> setStatusCheckContexts(String owner, String repo, String branch,
                                                     String[] contexts) throws IOException {
        return setStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    public <T> T setStatusCheckContexts(String owner, String repo, String branch, String[] contexts,
                                        ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("contexts", contexts);
        return returnContexts(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_REQUIRED_STATUS_CHECKS_CONTEXTS_PATH, payload), format);
    }

    public Collection<String> removeStatusCheckContexts(Repository repository, Branch branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(Repository repository, Branch branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> removeStatusCheckContexts(String owner, String repo, Branch branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(String owner, String repo, Branch branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> removeStatusCheckContexts(Repository repository, String branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(Repository repository, String branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> removeStatusCheckContexts(String owner, String repo, String branch,
                                                        Collection<String> contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(String owner, String repo, String branch, Collection<String> contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts.toArray(new String[0]), format);
    }

    public Collection<String> removeStatusCheckContexts(Repository repository, Branch branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(Repository repository, Branch branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch.getName(), contexts,
                format);
    }

    public Collection<String> removeStatusCheckContexts(String owner, String repo, Branch branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(String owner, String repo, Branch branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch.getName(), contexts, format);
    }

    public Collection<String> removeStatusCheckContexts(Repository repository, String branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts,
                LIBRARY_OBJECT);
    }

    public <T> T removeStatusCheckContexts(Repository repository, String branch, String[] contexts,
                                           ReturnFormat format) throws IOException {
        return removeStatusCheckContexts(repository.getOwner().getLogin(), repository.getName(), branch, contexts, format);
    }

    public Collection<String> removeStatusCheckContexts(String owner, String repo, String branch,
                                                        String[] contexts) throws IOException {
        return removeStatusCheckContexts(owner, repo, branch, contexts, LIBRARY_OBJECT);
    }

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

    public Restrictions<Object> getAccessRestrictions(Repository repository, Branch branch) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getAccessRestrictions(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public Restrictions<Object> getAccessRestrictions(String owner, String repo, Branch branch) throws Exception {
        return getAccessRestrictions(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getAccessRestrictions(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(owner, repo, branch.getName(), format);
    }

    public Restrictions<Object> getAccessRestrictions(Repository repository, String branch) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getAccessRestrictions(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public Restrictions<Object> getAccessRestrictions(String owner, String repo, String branch) throws Exception {
        return getAccessRestrictions(owner, repo, branch, LIBRARY_OBJECT);
    }

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

    public boolean deleteAccessRestrictions(Repository repository, Branch branch) {
        return deleteAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName());
    }

    public boolean deleteAccessRestrictions(String owner, String repo, Branch branch) {
        return deleteAccessRestrictions(owner, repo, branch.getName());
    }

    public boolean deleteAccessRestrictions(Repository repository, String branch) {
        return deleteAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch);
    }

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

    public Collection<GitHubApp> getAppsWithTheAccess(Repository repository, Branch branch) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(),
                LIBRARY_OBJECT);
    }

    public <T> T getAppsWithTheAccess(Repository repository, Branch branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch.getName(), format);
    }

    public Collection<GitHubApp> getAppsWithTheAccess(String owner, String repo, Branch branch) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch.getName(), LIBRARY_OBJECT);
    }

    public <T> T getAppsWithTheAccess(String owner, String repo, Branch branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch.getName(), format);
    }

    public Collection<GitHubApp> getAppsWithTheAccess(Repository repository, String branch) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, LIBRARY_OBJECT);
    }

    public <T> T getAppsWithTheAccess(Repository repository, String branch, ReturnFormat format) throws Exception {
        return getAppsWithTheAccess(repository.getOwner().getLogin(), repository.getName(), branch, format);
    }

    public Collection<GitHubApp> getAppsWithTheAccess(String owner, String repo, String branch) throws Exception {
        return getAppsWithTheAccess(owner, repo, branch, LIBRARY_OBJECT);
    }

    public <T> T getAppsWithTheAccess(String owner, String repo, String branch, ReturnFormat format) throws Exception {
        return returnAppsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH), format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, String branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, String branch,
                                                          Collection<String> apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, Branch branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(Repository repository, String branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return addAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> addAppAccessRestrictions(String owner, String repo, String branch,
                                                          String[] apps) throws Exception {
        return addAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    public <T> T addAppAccessRestrictions(String owner, String repo, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("apps", apps);
        return returnAppsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH, payload), format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, String branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, String branch,
                                                          Collection<String> apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, Branch branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, Branch branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(Repository repository, String branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        return setAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> setAppAccessRestrictions(String owner, String repo, String branch,
                                                          String[] apps) throws Exception {
        return setAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    public <T> T setAppAccessRestrictions(String owner, String repo, String branch, String[] apps,
                                          ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("apps", apps);
        return returnAppsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + BRANCHES_QUERY_PATH + branch +
                PROTECTION_RESTRICTIONS_APPS_PATH, payload), format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, Branch branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(Repository repository, Branch branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, Branch branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(String owner, String repo, Branch branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, String branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(Repository repository, String branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, String branch,
                                                             Collection<String> apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(String owner, String repo, String branch, Collection<String> apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps.toArray(new String[0]), format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, Branch branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(Repository repository, Branch branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch.getName(), apps,
                format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, Branch branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(String owner, String repo, Branch branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch.getName(), apps, format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(Repository repository, String branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps,
                LIBRARY_OBJECT);
    }

    public <T> T removeAppAccessRestrictions(Repository repository, String branch, String[] apps,
                                             ReturnFormat format) throws Exception {
        return removeAppAccessRestrictions(repository.getOwner().getLogin(), repository.getName(), branch, apps, format);
    }

    public Collection<GitHubApp> removeAppAccessRestrictions(String owner, String repo, String branch,
                                                             String[] apps) throws Exception {
        return removeAppAccessRestrictions(owner, repo, branch, apps, LIBRARY_OBJECT);
    }

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
