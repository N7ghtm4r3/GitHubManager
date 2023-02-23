package com.tecknobit.githubmanager.issues.issues;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.issues.issues.records.Issue.IssueFilter;
import com.tecknobit.githubmanager.issues.issues.records.Issue.IssueSort;
import com.tecknobit.githubmanager.issues.issues.records.Issue.StateReason;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.LockReason;
import com.tecknobit.githubmanager.records.parents.GitHubOperationBaseStructure.OperationState;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.issues.issues.records.Issue.returnIssue;

/**
 * The {@code GitHubIssuesManager} class is useful to manage all GitHub's issues endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues">
 * Issues</a>
 * @see GitHubManager
 **/
public class GitHubIssuesManager extends GitHubManager {

    /**
     * {@code ISSUES_PATH} constant for {@code "issues"} path
     **/
    public static final String ISSUES_PATH = "issues";

    /**
     * {@code LOCK_PATH} constant for {@code "/lock"} path
     **/
    public static final String LOCK_PATH = "/lock";

    /**
     * {@code USER_ISSUES_PATH} constant for {@code "user/issues"} path
     **/
    public static final String USER_ISSUES_PATH = USER_PATH + "/" + ISSUES_PATH;

    /**
     * Constructor to init a {@link GitHubIssuesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubIssuesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubIssuesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubIssuesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubIssuesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubIssuesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssuesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubIssuesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssuesManager} <br>
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
    public GitHubIssuesManager() {
        super();
    }

    /**
     * Method to get the list of the issues assigned to the authenticated user across all visible repositories including owned
     * repositories, member repositories, and organization repositories. You can use the filter query parameter to fetch
     * issues that are not necessarily assigned to you <br>
     * No-any params required
     *
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user">
     * List issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/issues")
    public ArrayList<Issue> getUserAssignedIssues() throws IOException {
        return getUserAssignedIssues(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues assigned to the authenticated user across all visible repositories including owned
     * repositories, member repositories, and organization repositories. You can use the filter query parameter to fetch
     * issues that are not necessarily assigned to you
     *
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user">
     * List issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/issues")
    public <T> T getUserAssignedIssues(ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(ISSUES_PATH), format);
    }

    /**
     * Method to get the list of the issues assigned to the authenticated user across all visible repositories including owned
     * repositories, member repositories, and organization repositories. You can use the filter query parameter to fetch
     * issues that are not necessarily assigned to you
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "collab"} -> whether include the collaborations in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "orgs"} -> whether include the organizations in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "owned"} -> whether include the owned repositories in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "pulls"} -> whether include the pulls in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user">
     * List issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/issues")
    public ArrayList<Issue> getUserAssignedIssues(Params queryParams) throws IOException {
        return getUserAssignedIssues(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues assigned to the authenticated user across all visible repositories including owned
     * repositories, member repositories, and organization repositories. You can use the filter query parameter to fetch
     * issues that are not necessarily assigned to you
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "collab"} -> whether include the collaborations in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "orgs"} -> whether include the organizations in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "owned"} -> whether include the owned repositories in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "pulls"} -> whether include the pulls in the list - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user">
     * List issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/issues")
    public <T> T getUserAssignedIssues(Params queryParams, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(ISSUES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org: the organization from fetch the list
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public ArrayList<Issue> getOrganizationAssignedIssues(Organization org) throws IOException {
        return getOrganizationAssignedIssues(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:   the organization from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public <T> T getOrganizationAssignedIssues(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationAssignedIssues(org.getLogin(), format);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public ArrayList<Issue> getOrganizationAssignedIssues(String org) throws IOException {
        return getOrganizationAssignedIssues(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:   the organization name. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public <T> T getOrganizationAssignedIssues(String org, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(ORGS_PATH + org + "/" + ISSUES_PATH), format);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public ArrayList<Issue> getOrganizationAssignedIssues(Organization org, Params queryParams) throws IOException {
        return getOrganizationAssignedIssues(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public <T> T getOrganizationAssignedIssues(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationAssignedIssues(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public ArrayList<Issue> getOrganizationAssignedIssues(String org, Params queryParams) throws IOException {
        return getOrganizationAssignedIssues(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in an organization assigned to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
     * List organization issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/issues")
    public <T> T getOrganizationAssignedIssues(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(ORGS_PATH + org + "/" + ISSUES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param repository: the repository from fetch the list
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public ArrayList<Issue> getRepositoryIssues(Repository repository) throws IOException {
        return getRepositoryIssues(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public <T> T getRepositoryIssues(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryIssues(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public ArrayList<Issue> getRepositoryIssues(String owner, String repo) throws IOException {
        return getRepositoryIssues(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public <T> T getRepositoryIssues(String owner, String repo, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH), format);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "milestone"} -> if an integer is passed, it should refer to a milestone by its
     *                            number field. If the string * is passed, issues with any milestone are accepted.
     *                            If the string none is passed, issues without milestones are returned - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "assignee"} -> can be the name of a user. Pass in none for issues with no assigned
     *                            user, and * for issues assigned to any user - [string]
     *                        </li>
     *                        <li>
     *                            {@code "creator"} -> the user that created the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "mentioned"} -> a user that's mentioned in the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public ArrayList<Issue> getRepositoryIssues(Repository repository, Params queryParams) throws IOException {
        return getRepositoryIssues(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "milestone"} -> if an integer is passed, it should refer to a milestone by its
     *                            number field. If the string * is passed, issues with any milestone are accepted.
     *                            If the string none is passed, issues without milestones are returned - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "assignee"} -> can be the name of a user. Pass in none for issues with no assigned
     *                            user, and * for issues assigned to any user - [string]
     *                        </li>
     *                        <li>
     *                            {@code "creator"} -> the user that created the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "mentioned"} -> a user that's mentioned in the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public <T> T getRepositoryIssues(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryIssues(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "milestone"} -> if an integer is passed, it should refer to a milestone by its
     *                            number field. If the string * is passed, issues with any milestone are accepted.
     *                            If the string none is passed, issues without milestones are returned - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "assignee"} -> can be the name of a user. Pass in none for issues with no assigned
     *                            user, and * for issues assigned to any user - [string]
     *                        </li>
     *                        <li>
     *                            {@code "creator"} -> the user that created the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "mentioned"} -> a user that's mentioned in the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public ArrayList<Issue> getRepositoryIssues(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryIssues(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues in a repository. Only open issues will be listed.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "milestone"} -> if an integer is passed, it should refer to a milestone by its
     *                            number field. If the string * is passed, issues with any milestone are accepted.
     *                            If the string none is passed, issues without milestones are returned - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "assignee"} -> can be the name of a user. Pass in none for issues with no assigned
     *                            user, and * for issues assigned to any user - [string]
     *                        </li>
     *                        <li>
     *                            {@code "creator"} -> the user that created the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "mentioned"} -> a user that's mentioned in the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
     * List repository issues</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues")
    public <T> T getRepositoryIssues(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a new issue<br>
     * ny user with pull access to a repository can create an issue. If issues are disabled in the repository, the API
     * returns a 410 Gone status. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate limiting.
     * See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where create the issue
     * @param title:      the title of the issue
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with.
     *                           NOTE: Only users with push access can set the milestone for new issues.
     *                           The milestone is silently dropped otherwise - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. NOTE: Only users with push access
     *                           can set labels for new issues. Labels are silently dropped otherwise - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> logins for Users to assign to this issue.
     *                           NOTE: Only users with push access can set assignees for new issues.
     *                           Assignees are silently dropped otherwise - [array of strings]
     *                       </li>
     *                    </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#create-an-issue">
     * Create an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues")
    public Issue createIssue(Repository repository, String title, Params bodyParams) throws IOException {
        return createIssue(repository.getOwner().getLogin(), repository.getName(), title, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new issue<br>
     * ny user with pull access to a repository can create an issue. If issues are disabled in the repository, the API
     * returns a 410 Gone status. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate limiting.
     * See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where create the issue
     * @param title:      the title of the issue
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with.
     *                           NOTE: Only users with push access can set the milestone for new issues.
     *                           The milestone is silently dropped otherwise - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. NOTE: Only users with push access
     *                           can set labels for new issues. Labels are silently dropped otherwise - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> logins for Users to assign to this issue.
     *                           NOTE: Only users with push access can set assignees for new issues.
     *                           Assignees are silently dropped otherwise - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#create-an-issue">
     * Create an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues")
    public <T> T createIssue(Repository repository, String title, Params bodyParams, ReturnFormat format) throws IOException {
        return createIssue(repository.getOwner().getLogin(), repository.getName(), title, bodyParams, format);
    }

    /**
     * Method to create a new issue<br>
     * ny user with pull access to a repository can create an issue. If issues are disabled in the repository, the API
     * returns a 410 Gone status. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate limiting.
     * See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param title:      the title of the issue
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with.
     *                           NOTE: Only users with push access can set the milestone for new issues.
     *                           The milestone is silently dropped otherwise - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. NOTE: Only users with push access
     *                           can set labels for new issues. Labels are silently dropped otherwise - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> logins for Users to assign to this issue.
     *                           NOTE: Only users with push access can set assignees for new issues.
     *                           Assignees are silently dropped otherwise - [array of strings]
     *                       </li>
     *                    </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#create-an-issue">
     * Create an issue</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues")
    public Issue createIssue(String owner, String repo, String title, Params bodyParams) throws IOException {
        return createIssue(owner, repo, title, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new issue<br>
     * ny user with pull access to a repository can create an issue. If issues are disabled in the repository, the API
     * returns a 410 Gone status. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate limiting.
     * See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param title:      the title of the issue
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with.
     *                           NOTE: Only users with push access can set the milestone for new issues.
     *                           The milestone is silently dropped otherwise - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. NOTE: Only users with push access
     *                           can set labels for new issues. Labels are silently dropped otherwise - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> logins for Users to assign to this issue.
     *                           NOTE: Only users with push access can set assignees for new issues.
     *                           Assignees are silently dropped otherwise - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#create-an-issue">
     * Create an issue</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues")
    public <T> T createIssue(String owner, String repo, String title, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("title", title);
        return returnIssue(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH, bodyParams),
                format);
    }

    /**
     * Method to get an issue <br>
     * The API returns a 301 Moved Permanently status if the issue was transferred to another repository.
     * If the issue was transferred to or deleted from a repository where the authenticated user lacks read access,
     * the API returns a 404 Not Found status. If the issue was deleted from a repository where the authenticated
     * user has read access, the API returns a 410 Gone status. To receive webhook events for transferred and deleted
     * issues, subscribe to the issues webhook
     *
     * @param repository:  the repository from fetch the issue
     * @param issueNumber: the number that identifies the issue
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#get-an-issue">
     * Get an issue</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}")
    public Issue getIssue(Repository repository, long issueNumber) throws IOException {
        return getIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue <br>
     * The API returns a 301 Moved Permanently status if the issue was transferred to another repository.
     * If the issue was transferred to or deleted from a repository where the authenticated user lacks read access,
     * the API returns a 404 Not Found status. If the issue was deleted from a repository where the authenticated
     * user has read access, the API returns a 410 Gone status. To receive webhook events for transferred and deleted
     * issues, subscribe to the issues webhook
     *
     * @param repository:  the repository from fetch the issue
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#get-an-issue">
     * Get an issue</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}")
    public <T> T getIssue(Repository repository, long issueNumber, ReturnFormat format) throws IOException {
        return getIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get an issue <br>
     * The API returns a 301 Moved Permanently status if the issue was transferred to another repository.
     * If the issue was transferred to or deleted from a repository where the authenticated user lacks read access,
     * the API returns a 404 Not Found status. If the issue was deleted from a repository where the authenticated
     * user has read access, the API returns a 410 Gone status. To receive webhook events for transferred and deleted
     * issues, subscribe to the issues webhook
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#get-an-issue">
     * Get an issue</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}")
    public Issue getIssue(String owner, String repo, long issueNumber) throws IOException {
        return getIssue(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue <br>
     * The API returns a 301 Moved Permanently status if the issue was transferred to another repository.
     * If the issue was transferred to or deleted from a repository where the authenticated user lacks read access,
     * the API returns a 404 Not Found status. If the issue was deleted from a repository where the authenticated
     * user has read access, the API returns a 410 Gone status. To receive webhook events for transferred and deleted
     * issues, subscribe to the issues webhook
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#get-an-issue">
     * Get an issue</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}")
    public <T> T getIssue(String owner, String repo, long issueNumber, ReturnFormat format) throws IOException {
        return returnIssue(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber),
                format);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param repository: the repository where create the issue
     * @param issue:      the issue to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                           {@link StateReason} - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                           null to remove the current milestone. Only users with push access can set the milestone
     *                           for issues. Without push access to the repository, milestone changes
     *                           are silently dropped - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                           replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                           from the issue. Only users with push access can set labels for issues.
     *                           Without push access to the repository, label changes are silently dropped - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                           to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                           all assignees from the issue. Only users with push access can set assignees for
     *                           new issues. Without push access to the repository, assignee changes are silently
     *                           dropped - [array of strings]
     *                       </li>
     *                    </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public Issue updateIssue(Repository repository, Issue issue, Params bodyParams) throws IOException {
        return updateIssue(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param repository: the repository where create the issue
     * @param issue:      the issue to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                           {@link StateReason} - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                           null to remove the current milestone. Only users with push access can set the milestone
     *                           for issues. Without push access to the repository, milestone changes
     *                           are silently dropped - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                           replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                           from the issue. Only users with push access can set labels for issues.
     *                           Without push access to the repository, label changes are silently dropped - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                           to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                           all assignees from the issue. Only users with push access can set assignees for
     *                           new issues. Without push access to the repository, assignee changes are silently
     *                           dropped - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public <T> T updateIssue(Repository repository, Issue issue, Params bodyParams,
                             ReturnFormat format) throws IOException {
        return updateIssue(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), bodyParams, format);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param repository:  the repository where create the issue
     * @param issueNumber: the number that identifies the issue
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                            {@link StateReason} - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                            null to remove the current milestone. Only users with push access can set the milestone
     *                            for issues. Without push access to the repository, milestone changes
     *                            are silently dropped - [null or string or integer or string or integer]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                            replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                            from the issue. Only users with push access can set labels for issues.
     *                            Without push access to the repository, label changes are silently dropped - [array]
     *                        </li>
     *                        <li>
     *                            {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                            to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                            all assignees from the issue. Only users with push access can set assignees for
     *                            new issues. Without push access to the repository, assignee changes are silently
     *                            dropped - [array of strings]
     *                        </li>
     *                     </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public Issue updateIssue(Repository repository, long issueNumber, Params bodyParams) throws IOException {
        return updateIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param repository:  the repository where create the issue
     * @param issueNumber: the number that identifies the issue
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                            {@link StateReason} - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                            null to remove the current milestone. Only users with push access can set the milestone
     *                            for issues. Without push access to the repository, milestone changes
     *                            are silently dropped - [null or string or integer or string or integer]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                            replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                            from the issue. Only users with push access can set labels for issues.
     *                            Without push access to the repository, label changes are silently dropped - [array]
     *                        </li>
     *                        <li>
     *                            {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                            to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                            all assignees from the issue. Only users with push access can set assignees for
     *                            new issues. Without push access to the repository, assignee changes are silently
     *                            dropped - [array of strings]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public <T> T updateIssue(Repository repository, long issueNumber, Params bodyParams,
                             ReturnFormat format) throws IOException {
        return updateIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber, bodyParams, format);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param issue:      the issue to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                           {@link StateReason} - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                           null to remove the current milestone. Only users with push access can set the milestone
     *                           for issues. Without push access to the repository, milestone changes
     *                           are silently dropped - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                           replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                           from the issue. Only users with push access can set labels for issues.
     *                           Without push access to the repository, label changes are silently dropped - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                           to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                           all assignees from the issue. Only users with push access can set assignees for
     *                           new issues. Without push access to the repository, assignee changes are silently
     *                           dropped - [array of strings]
     *                       </li>
     *                    </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public Issue updateIssue(String owner, String repo, Issue issue, Params bodyParams) throws IOException {
        return updateIssue(owner, repo, issue.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param issue:      the issue to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> the contents of the issue - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                           {@link StateReason} - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                           null to remove the current milestone. Only users with push access can set the milestone
     *                           for issues. Without push access to the repository, milestone changes
     *                           are silently dropped - [null or string or integer or string or integer]
     *                       </li>
     *                       <li>
     *                           {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                           replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                           from the issue. Only users with push access can set labels for issues.
     *                           Without push access to the repository, label changes are silently dropped - [array]
     *                       </li>
     *                       <li>
     *                           {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                           to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                           all assignees from the issue. Only users with push access can set assignees for
     *                           new issues. Without push access to the repository, assignee changes are silently
     *                           dropped - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public <T> T updateIssue(String owner, String repo, Issue issue, Params bodyParams,
                             ReturnFormat format) throws IOException {
        return updateIssue(owner, repo, issue.getNumber(), bodyParams, format);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                            {@link StateReason} - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                            null to remove the current milestone. Only users with push access can set the milestone
     *                            for issues. Without push access to the repository, milestone changes
     *                            are silently dropped - [null or string or integer or string or integer]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                            replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                            from the issue. Only users with push access can set labels for issues.
     *                            Without push access to the repository, label changes are silently dropped - [array]
     *                        </li>
     *                        <li>
     *                            {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                            to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                            all assignees from the issue. Only users with push access can set assignees for
     *                            new issues. Without push access to the repository, assignee changes are silently
     *                            dropped - [array of strings]
     *                        </li>
     *                     </ul>
     * @return issue as {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public Issue updateIssue(String owner, String repo, long issueNumber, Params bodyParams) throws IOException {
        return updateIssue(owner, repo, issueNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue <br>
     * Issue owners and users with push access can edit an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "title"} -> the title of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> the contents of the issue - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the open or closed state of the issue {@link OperationState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "state_reason"} -> the reason for the state change. Ignored unless state is changed
     *                            {@link StateReason} - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "milestone"} -> the number of the milestone to associate this issue with or use
     *                            null to remove the current milestone. Only users with push access can set the milestone
     *                            for issues. Without push access to the repository, milestone changes
     *                            are silently dropped - [null or string or integer or string or integer]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> labels to associate with this issue. Pass one or more labels to
     *                            replace the set of labels on this issue. Send an empty array ([]) to clear all labels
     *                            from the issue. Only users with push access can set labels for issues.
     *                            Without push access to the repository, label changes are silently dropped - [array]
     *                        </li>
     *                        <li>
     *                            {@code "assignees"} -> usernames to assign to this issue. Pass one or more user logins
     *                            to replace the set of assignees on this issue. Send an empty array ([]) to clear
     *                            all assignees from the issue. Only users with push access can set assignees for
     *                            new issues. Without push access to the repository, assignee changes are silently
     *                            dropped - [array of strings]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
     * Update an issue</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues")
    public <T> T updateIssue(String owner, String repo, long issueNumber, Params bodyParams,
                             ReturnFormat format) throws IOException {
        return returnIssue(sendPatchRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + "/" + issueNumber, bodyParams), format);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param repository: the repository where lock the issue
     * @param issue:      the issue to lock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(Repository repository, Issue issue) {
        return lockIssue(repository.getOwner().getLogin(), repository.getName(), issue.getNumber());
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param repository:  the repository where lock the issue
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(Repository repository, long issueNumber) {
        return lockIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue to lock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(String owner, String repo, Issue issue) {
        return lockIssue(owner, repo, issue.getNumber());
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(String owner, String repo, long issueNumber) {
        return lockIssue(owner, repo, issueNumber, null);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param repository: the repository where lock the issue
     * @param issue:      the issue to lock
     * @param lockReason: the reason for locking the issue or pull request conversation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(Repository repository, Issue issue, LockReason lockReason) {
        return lockIssue(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), lockReason);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param repository:  the repository where lock the issue
     * @param issueNumber: the number that identifies the issue
     * @param lockReason:  the reason for locking the issue or pull request conversation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(Repository repository, long issueNumber, LockReason lockReason) {
        return lockIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber, lockReason);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param issue:      the issue to lock
     * @param lockReason: the reason for locking the issue or pull request conversation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(String owner, String repo, Issue issue, LockReason lockReason) {
        return lockIssue(owner, repo, issue.getNumber(), lockReason);
    }

    /**
     * Method to lock an issue <br>
     * Users with push access can lock an issue or pull request's conversation.
     * Note that, if you choose not to pass any parameters, you'll need to set Content-Length to zero when calling out
     * to this endpoint. For more information, see "HTTP verbs."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param lockReason:  the reason for locking the issue or pull request conversation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#lock-an-issue">
     * Lock an issue</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean lockIssue(String owner, String repo, long issueNumber, LockReason lockReason) {
        try {
            Params payload;
            if (lockReason != null) {
                payload = new Params();
                payload.addParam("lock_reason", lockReason.toString());
            } else
                payload = null;
            sendPutRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber + LOCK_PATH,
                    payload);
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
     * Method to unlock an issue <br>
     * Users with push access can lock an issue or pull request's conversation
     *
     * @param repository: the repository where unlock the issue
     * @param issue:      the issue to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#unlock-an-issue">
     * Unlock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean unlockIssue(Repository repository, Issue issue) {
        return unlockIssue(repository.getOwner().getLogin(), repository.getName(), issue.getNumber());
    }

    /**
     * Method to unlock an issue <br>
     * Users with push access can lock an issue or pull request's conversation
     *
     * @param repository:  the repository where unlock the issue
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#unlock-an-issue">
     * Unlock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean unlockIssue(Repository repository, long issueNumber) {
        return unlockIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber);
    }

    /**
     * Method to unlock an issue <br>
     * Users with push access can lock an issue or pull request's conversation
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#unlock-an-issue">
     * Unlock an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean unlockIssue(String owner, String repo, Issue issue) {
        return unlockIssue(owner, repo, issue.getNumber());
    }

    /**
     * Method to unlock an issue <br>
     * Users with push access can lock an issue or pull request's conversation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#unlock-an-issue">
     * Unlock an issue</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/lock")
    public boolean unlockIssue(String owner, String repo, long issueNumber) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber
                    + LOCK_PATH);
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
     * Method to get the list of the issues across owned and member repositories assigned to the authenticated user <br>
     * No-any params required
     *
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-user-account-issues-assigned-to-the-authenticated-user">
     * List user account issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/issues")
    public ArrayList<Issue> getUserAccountAssignedIssues() throws IOException {
        return getUserAccountAssignedIssues(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues across owned and member repositories assigned to the authenticated user
     *
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-user-account-issues-assigned-to-the-authenticated-user">
     * List user account issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/user/issues")
    public <T> T getUserAccountAssignedIssues(ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(USER_ISSUES_PATH), format);
    }

    /**
     * Method to get the list of the issues across owned and member repositories assigned to the authenticated user
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issues list as {@link ArrayList} of {@link Issue} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-user-account-issues-assigned-to-the-authenticated-user">
     * List user account issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/issues")
    public ArrayList<Issue> getUserAccountAssignedIssues(Params queryParams) throws IOException {
        return getUserAccountAssignedIssues(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issues across owned and member repositories assigned to the authenticated user
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> indicates which sorts of issues to return. assigned means issues
     *                            assigned to you. created means issues created by you. mentioned means issues mentioning you.
     *                            subscribed means issues you're subscribed to updates for. all or repos means all issues
     *                            you can see, regardless of participation or creation, constants available
     *                            {@link IssueFilter} - [string, default assigned]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "labels"} -> a list of comma separated label names. Example: bug,ui,@high - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/issues#list-user-account-issues-assigned-to-the-authenticated-user">
     * List user account issues assigned to the authenticated user</a>
     * @implNote GitHub's REST API considers every pull request an issue, but not every issue is a pull request.
     * For this reason, "Issues" endpoints may return both issues and pull requests in the response.
     * You can identify pull requests by the pull_request key. Be aware that the id of a pull request returned from
     * "Issues" endpoints will be an issue id. To find out the pull request id, use the "List pull requests" endpoint
     **/
    @RequestPath(method = GET, path = "/user/issues")
    public <T> T getUserAccountAssignedIssues(Params queryParams, ReturnFormat format) throws IOException {
        return returnIssuesList(sendGetRequest(USER_ISSUES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an issues list
     *
     * @param issuesListResponse : obtained from GitHub's response
     * @param format             :              return type formatter -> {@link ReturnFormat}
     * @return issues list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnIssuesList(String issuesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(issuesListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Issue> issues = new ArrayList<>();
                JSONArray jIssues = new JSONArray(issuesListResponse);
                for (int j = 0; j < jIssues.length(); j++)
                    issues.add(new Issue(jIssues.getJSONObject(j)));
                return (T) issues;
            default:
                return (T) issuesListResponse;
        }
    }

}
