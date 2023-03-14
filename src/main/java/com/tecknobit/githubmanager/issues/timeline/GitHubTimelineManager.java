package com.tecknobit.githubmanager.issues.timeline;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.events.records.IssueEvent;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.issues.events.records.IssueEvent.returnIssueEventsList;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;

/**
 * The {@code GitHubTimelineManager} class is useful to manage all GitHub's timeline endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline">
 * Timeline events</a>
 * @see GitHubManager
 **/
public class GitHubTimelineManager extends GitHubManager {

    /**
     * {@code TIMELINE_PATH} constant for {@code "/timeline"} path
     **/
    public static final String TIMELINE_PATH = "/timeline";

    /**
     * Constructor to init a {@link GitHubTimelineManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTimelineManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTimelineManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTimelineManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTimelineManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTimelineManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTimelineManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTimelineManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTimelineManager} <br>
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
    public GitHubTimelineManager() {
        super();
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(Repository repository, Issue issue) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(Repository repository, Issue issue, ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(String owner, String repo, Issue issue) throws Exception {
        return getIssueTimelineEvents(owner, repo, issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(String owner, String repo, Issue issue, ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(owner, repo, issue.getNumber(), format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(Repository repository, long issueNumber) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(Repository repository, long issueNumber, ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(String owner, String repo, long issueNumber) throws Exception {
        return getIssueTimelineEvents(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(String owner, String repo, long issueNumber, ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + TIMELINE_PATH), format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(Repository repository, Issue issue,
                                                        Params queryParams) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(Repository repository, Issue issue, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(String owner, String repo, Issue issue,
                                                        Params queryParams) throws Exception {
        return getIssueTimelineEvents(owner, repo, issue.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(String owner, String repo, Issue issue, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(owner, repo, issue.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(Repository repository, long issueNumber,
                                                        Params queryParams) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(Repository repository, long issueNumber, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return getIssueTimelineEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return timeline events as {@link ArrayList} of {@link IssueEvent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public ArrayList<IssueEvent> getIssueTimelineEvents(String owner, String repo, long issueNumber,
                                                        Params queryParams) throws Exception {
        return getIssueTimelineEvents(owner, repo, issueNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of timeline events for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/timeline#list-timeline-events-for-an-issue">
     * List timeline events for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/timeline")
    public <T> T getIssueTimelineEvents(String owner, String repo, long issueNumber, Params queryParams,
                                        ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + TIMELINE_PATH + queryParams.createQueryString()), format);
    }

}
