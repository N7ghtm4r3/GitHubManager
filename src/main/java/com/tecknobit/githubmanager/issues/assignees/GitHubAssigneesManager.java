package com.tecknobit.githubmanager.issues.assignees;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.users.users.records.User;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;
import static com.tecknobit.githubmanager.issues.issues.records.Issue.returnIssue;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubAssigneesManager} class is useful to manage all GitHub's assignees endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees">
 * Issue assignees</a>
 * @see GitHubManager
 **/
public class GitHubAssigneesManager extends GitHubManager {

    /**
     * {@code ASSIGNEES_PATH} constant for {@code "/assignees"} path
     **/
    public static final String ASSIGNEES_PATH = "/assignees";

    /**
     * Constructor to init a {@link GitHubAssigneesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubAssigneesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubAssigneesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubAssigneesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubAssigneesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubAssigneesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAssigneesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubAssigneesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAssigneesManager} <br>
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
    public GitHubAssigneesManager() {
        super();
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param repository: the repository from fetch the list
     * @return assignees list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public ArrayList<User> getAssignees(Repository repository) throws IOException {
        return getAssignees(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return assignees list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public <T> T getAssignees(Repository repository, ReturnFormat format) throws IOException {
        return getAssignees(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return assignees list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public ArrayList<User> getAssignees(String owner, String repo) throws IOException {
        return getAssignees(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return assignees list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public <T> T getAssignees(String owner, String repo, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ASSIGNEES_PATH), format);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return assignees list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public ArrayList<User> getAssignees(Repository repository, Params queryParams) throws IOException {
        return getAssignees(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param repository:  the repository from fetch the list
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
     * @return assignees list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public <T> T getAssignees(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getAssignees(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return assignees list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public ArrayList<User> getAssignees(String owner, String repo, Params queryParams) throws IOException {
        return getAssignees(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the available assignees for issues in a repository <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @return assignees list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#list-assignees">
     * List assignees</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees")
    public <T> T getAssignees(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ASSIGNEES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to check if a user has permission to be assigned to an issue in this repository. <br>
     * If the assignee can be assigned to issues in the repository, a 204 header with no content is returned. <br>
     * Otherwise a 404 status code is returned
     *
     * @param repository: the repository where check if a user can be assigned
     * @param assignee:   the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned">
     * Check if a user can be assigned</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssigned(Repository repository, User assignee) {
        return checkIfAUserCanBeAssigned(repository.getOwner().getLogin(), repository.getName(), assignee.getLogin());
    }

    /**
     * Method to check if a user has permission to be assigned to an issue in this repository. <br>
     * If the assignee can be assigned to issues in the repository, a 204 header with no content is returned. <br>
     * Otherwise a 404 status code is returned
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param assignee: the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned">
     * Check if a user can be assigned</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssigned(String owner, String repo, User assignee) {
        return checkIfAUserCanBeAssigned(owner, repo, assignee.getLogin());
    }

    /**
     * Method to check if a user has permission to be assigned to an issue in this repository. <br>
     * If the assignee can be assigned to issues in the repository, a 204 header with no content is returned. <br>
     * Otherwise a 404 status code is returned
     *
     * @param repository: the repository where check if a user can be assigned
     * @param assignee:   the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned">
     * Check if a user can be assigned</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssigned(Repository repository, String assignee) {
        return checkIfAUserCanBeAssigned(repository.getOwner().getLogin(), repository.getName(), assignee);
    }

    /**
     * Method to check if a user has permission to be assigned to an issue in this repository. <br>
     * If the assignee can be assigned to issues in the repository, a 204 header with no content is returned. <br>
     * Otherwise a 404 status code is returned
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param assignee: the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned">
     * Check if a user can be assigned</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssigned(String owner, String repo, String assignee) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + ASSIGNEES_PATH + "/" + assignee);
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
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param repository: the repository where add the assignees
     * @param issue:      the issue where add the assignees
     * @param assignees:  the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                    to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue addIssueAssignees(Repository repository, Issue issue, String... assignees) throws IOException {
        return addIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param repository: the repository where add the assignees
     * @param issue:      the issue where add the assignees
     * @param assignees:  the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                    to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T addIssueAssignees(Repository repository, Issue issue, ReturnFormat format,
                                   String... assignees) throws IOException {
        return addIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format,
                assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param issue:     the issue where add the assignees
     * @param assignees: the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                   to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue addIssueAssignees(String owner, String repo, Issue issue, String... assignees) throws IOException {
        return addIssueAssignees(owner, repo, issue.getNumber(), LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param issue:     the issue where add the assignees
     * @param assignees: the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                   to an issue. Assignees are silently ignored otherwise
     * @param format     :              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T addIssueAssignees(String owner, String repo, Issue issue, ReturnFormat format,
                                   String... assignees) throws IOException {
        return addIssueAssignees(owner, repo, issue.getNumber(), format, assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param repository:  the repository where add the assignees
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue addIssueAssignees(Repository repository, long issueNumber, String... assignees) throws IOException {
        return addIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT,
                assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param repository:  the repository where add the assignees
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T addIssueAssignees(Repository repository, long issueNumber, ReturnFormat format,
                                   String... assignees) throws IOException {
        return addIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issueNumber, format, assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue addIssueAssignees(String owner, String repo, long issueNumber, String... assignees) throws IOException {
        return addIssueAssignees(owner, repo, issueNumber, LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to add up to 10 assignees to an issue. Users already assigned to an issue are not replaced
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#add-assignees-to-an-issue">
     * Add assignees to an issue</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T addIssueAssignees(String owner, String repo, long issueNumber, ReturnFormat format,
                                   String... assignees) throws IOException {
        Params payload = new Params();
        payload.addParam("assignees", assignees);
        return returnIssue(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + ASSIGNEES_PATH, payload), format);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param repository: the repository from remove the assignees
     * @param issue:      the issue from remove the assignees
     * @param assignees:  the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                    to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue removeIssueAssignees(Repository repository, Issue issue, String... assignees) throws IOException {
        return removeIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param repository: the repository from remove the assignees
     * @param issue:      the issue from remove the assignees
     * @param assignees:  the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                    to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T removeIssueAssignees(Repository repository, Issue issue, ReturnFormat format,
                                      String... assignees) throws IOException {
        return removeIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format,
                assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param issue:     the issue from remove the assignees
     * @param assignees: the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                   to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue removeIssueAssignees(String owner, String repo, Issue issue, String... assignees) throws IOException {
        return removeIssueAssignees(owner, repo, issue.getNumber(), LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param issue:     the issue from remove the assignees
     * @param assignees: the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                   to an issue. Assignees are silently ignored otherwise
     * @param format     :              return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T removeIssueAssignees(String owner, String repo, Issue issue, ReturnFormat format,
                                      String... assignees) throws IOException {
        return removeIssueAssignees(owner, repo, issue.getNumber(), format, assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param repository:  the repository from remove the assignees
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue removeIssueAssignees(Repository repository, long issueNumber, String... assignees) throws IOException {
        return removeIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT,
                assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param repository:  the repository from remove the assignees
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T removeIssueAssignees(Repository repository, long issueNumber, ReturnFormat format,
                                      String... assignees) throws IOException {
        return removeIssueAssignees(repository.getOwner().getLogin(), repository.getName(), issueNumber, format,
                assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public Issue removeIssueAssignees(String owner, String repo, long issueNumber, String... assignees) throws IOException {
        return removeIssueAssignees(owner, repo, issueNumber, LIBRARY_OBJECT, assignees);
    }

    /**
     * Method to remove one or more assignees from an issue.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignees:   the usernames of people to assign this issue to. NOTE: Only users with push access can add assignees
     *                     to an issue. Assignees are silently ignored otherwise
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#remove-assignees-from-an-issue">
     * Remove assignees from an issue</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees")
    public <T> T removeIssueAssignees(String owner, String repo, long issueNumber, ReturnFormat format,
                                      String... assignees) throws IOException {
        Params payload = new Params();
        payload.addParam("assignees", assignees);
        return returnIssue(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + ASSIGNEES_PATH, payload).get("success").toString(), format);
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param repository: the repository where check if a user can be assigned
     * @param issue:      the issue where check if a user can be assigned
     * @param assignee:   the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(Repository repository, Issue issue, User assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(repository.getOwner().getLogin(), repository.getName(),
                issue.getNumber(), assignee.getLogin());
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param repository:  the repository where check if a user can be assigned
     * @param issueNumber: the number that identifies the issue
     * @param assignee:    the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(Repository repository, long issueNumber, User assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber,
                assignee.getLogin());
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param issue:    the issue where check if a user can be assigned
     * @param assignee: the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(String owner, String repo, Issue issue, User assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(owner, repo, issue.getNumber(), assignee.getLogin());
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignee:    the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(String owner, String repo, long issueNumber, User assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(owner, repo, issueNumber, assignee.getLogin());
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param repository: the repository where check if a user can be assigned
     * @param issue:      the issue where check if a user can be assigned
     * @param assignee:   the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(Repository repository, Issue issue, String assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(repository.getOwner().getLogin(), repository.getName(),
                issue.getNumber(), assignee);
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param repository:  the repository where check if a user can be assigned
     * @param issueNumber: the number that identifies the issue
     * @param assignee:    the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(Repository repository, long issueNumber, String assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(repository.getOwner().getLogin(), repository.getName(), issueNumber,
                assignee);
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param issue:    the issue where check if a user can be assigned
     * @param assignee: the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(String owner, String repo, Issue issue, String assignee) {
        return checkIfAUserCanBeAssignedToAnIssue(owner, repo, issue.getNumber(), assignee);
    }

    /**
     * Method to checks if a user has permission to be assigned to a specific issue. <br>
     * If the assignee can be assigned to this issue, a 204 status code with no content is returned. <br>
     * Otherwise a 404 status code is returned.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param assignee:    the assignee to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/assignees#check-if-a-user-can-be-assigned-to-a-issue">
     * Check if a user can be assigned to a issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/assignees/{assignee}")
    public boolean checkIfAUserCanBeAssignedToAnIssue(String owner, String repo, long issueNumber, String assignee) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber
                    + ASSIGNEES_PATH + "/" + assignee);
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
