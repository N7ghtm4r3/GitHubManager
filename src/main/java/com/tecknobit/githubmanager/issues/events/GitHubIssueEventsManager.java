package com.tecknobit.githubmanager.issues.events;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.events.records.IssueEvent;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.activity.events.GitHubEventsManager.EVENTS_PATH;
import static com.tecknobit.githubmanager.issues.events.records.IssueEvent.returnIssueEventsList;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;

/**
 * The {@code GitHubIssueEventsManager} class is useful to manage all GitHub's issue events endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events">
 * Issue events</a>
 * @see GitHubManager
 **/
public class GitHubIssueEventsManager extends GitHubManager {

    /**
     * {@code ISSUES_EVENTS_PATH} constant for {@code "issues/events"} path
     **/
    public static final String ISSUES_EVENTS_PATH = "/" + ISSUES_PATH + "/" + EVENTS_PATH;

    /**
     * Constructor to init a {@link GitHubIssueEventsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubIssueEventsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubIssueEventsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubIssueEventsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubIssueEventsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubIssueEventsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueEventsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubIssueEventsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueEventsManager} <br>
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
    public GitHubIssueEventsManager() {
        super();
    }

    /**
     * Method to get the list of the issue events for a repository
     *
     * @param repository: the repository from fetch the list
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public ArrayList<IssueEvent> getRepositoryIssueEvents(Repository repository) throws Exception {
        return getRepositoryIssueEvents(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events for a repository
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public <T> T getRepositoryIssueEvents(Repository repository, ReturnFormat format) throws Exception {
        return getRepositoryIssueEvents(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the issue events for a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public ArrayList<IssueEvent> getRepositoryIssueEvents(String owner, String repo) throws Exception {
        return getRepositoryIssueEvents(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events for a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public <T> T getRepositoryIssueEvents(String owner, String repo, ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_EVENTS_PATH), format);
    }

    /**
     * Method to get the list of the issue events for a repository
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public ArrayList<IssueEvent> getRepositoryIssueEvents(Repository repository, Params queryParams) throws Exception {
        return getRepositoryIssueEvents(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events for a repository
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
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public <T> T getRepositoryIssueEvents(Repository repository, Params queryParams, ReturnFormat format) throws Exception {
        return getRepositoryIssueEvents(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the issue events for a repository
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public ArrayList<IssueEvent> getRepositoryIssueEvents(String owner, String repo, Params queryParams) throws Exception {
        return getRepositoryIssueEvents(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events for a repository
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
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
     * List issue events for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events")
    public <T> T getRepositoryIssueEvents(String owner, String repo, Params queryParams,
                                          ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_EVENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get an issue event
     *
     * @param repository: the repository from fetch the issue event
     * @param eventId:    the identifier of the event to fetch
     * @return issue event as {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#get-an-issue-event">
     * Get an issue event</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events/{event_id}")
    public IssueEvent getIssueEvent(Repository repository, long eventId) throws Exception {
        return getIssueEvent(repository.getOwner().getLogin(), repository.getName(), eventId, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue event
     *
     * @param repository: the repository from fetch the issue event
     * @param eventId:    the identifier of the event to fetch
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue event as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#get-an-issue-event">
     * Get an issue event</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events/{event_id}")
    public <T> T getIssueEvent(Repository repository, long eventId, ReturnFormat format) throws Exception {
        return getIssueEvent(repository.getOwner().getLogin(), repository.getName(), eventId, format);
    }

    /**
     * Method to get an issue event
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param eventId: the identifier of the event to fetch
     * @return issue event as {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#get-an-issue-event">
     * Get an issue event</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events/{event_id}")
    public IssueEvent getIssueEvent(String owner, String repo, long eventId) throws Exception {
        return getIssueEvent(owner, repo, eventId, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue event
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param eventId: the identifier of the event to fetch
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return issue event as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#get-an-issue-event">
     * Get an issue event</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/events/{event_id}")
    public <T> T getIssueEvent(String owner, String repo, long eventId, ReturnFormat format) throws Exception {
        String issueEventResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_EVENTS_PATH + "/"
                + eventId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(issueEventResponse);
            case LIBRARY_OBJECT:
                return (T) new IssueEvent(new JSONObject(issueEventResponse));
            default:
                return (T) issueEventResponse;
        }
    }

    /**
     * Method to get the list of the issue events
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(Repository repository, Issue issue) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(Repository repository, Issue issue, ReturnFormat format) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(String owner, String repo, Issue issue) throws Exception {
        return getIssueEvents(owner, repo, issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(String owner, String repo, Issue issue, ReturnFormat format) throws Exception {
        return getIssueEvents(owner, repo, issue.getNumber(), format);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(Repository repository, long issueNumber) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(Repository repository, long issueNumber, ReturnFormat format) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(String owner, String repo, long issueNumber) throws Exception {
        return getIssueEvents(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(String owner, String repo, long issueNumber, ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_EVENTS_PATH
                + "/" + issueNumber), format);
    }

    /**
     * Method to get the list of the issue events
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(Repository repository, Issue issue, Params queryParams) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(Repository repository, Issue issue, Params queryParams, ReturnFormat format) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), queryParams,
                format);
    }

    /**
     * Method to get the list of the issue events
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(String owner, String repo, Issue issue, Params queryParams) throws Exception {
        return getIssueEvents(owner, repo, issue.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(String owner, String repo, Issue issue, Params queryParams,
                                ReturnFormat format) throws Exception {
        return getIssueEvents(owner, repo, issue.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the issue events
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(Repository repository, long issueNumber,
                                                Params queryParams) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(Repository repository, long issueNumber, Params queryParams,
                                ReturnFormat format) throws Exception {
        return getIssueEvents(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams, format);
    }

    /**
     * Method to get the list of the issue events
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
     * @return issue events list as {@link ArrayList} of {@link IssueEvent} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public ArrayList<IssueEvent> getIssueEvents(String owner, String repo, long issueNumber,
                                                Params queryParams) throws Exception {
        return getIssueEvents(owner, repo, issueNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue events
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
     * List issue events</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/events")
    public <T> T getIssueEvents(String owner, String repo, long issueNumber, Params queryParams,
                                ReturnFormat format) throws Exception {
        return returnIssueEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_EVENTS_PATH
                + queryParams.createQueryString()), format);
    }

}
