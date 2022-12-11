package com.tecknobit.githubmanager.activity.events;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.activity.events.records.Event;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubEventsManager} class is useful to manage all GitHub's events endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events">
 * Events</a>
 * @see GitHubManager
 **/
public class GitHubEventsManager extends GitHubManager {

    /**
     * {@code EVENTS_PATH} constant for {@code "events"} path
     **/
    public static final String EVENTS_PATH = "events";

    /**
     * {@code NETWORKS_PATH} constant for {@code "networks/"} path
     **/
    public static final String NETWORKS_PATH = "networks/";

    /**
     * {@code PUBLIC_PATH} constant for {@code "/public"} path
     **/
    public static final String PUBLIC_PATH = "/public";

    /**
     * {@code RECEIVED_EVENTS_PATH} constant for {@code "/received_events"} path
     **/
    public static final String RECEIVED_EVENTS_PATH = "/received_events";

    /**
     * Constructor to init a {@link GitHubEventsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubEventsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubEventsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubEventsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubEventsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubEventsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEventsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubEventsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEventsManager} <br>
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
    public GitHubEventsManager() {
        super();
    }

    /**
     * Method to get an events list <br>
     * There is a delay of the public events feed by five minutes, which means the most recent event returned by the public events
     * API actually occurred at least five minutes ago <br>
     * <p>
     * Any params required
     *
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events">
     * List public events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/events")
    public Collection<Event> getPublicEvents() throws IOException {
        return getPublicEvents(LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * There is a delay of the public events feed by five minutes, which means the most recent event returned by the public events
     * API actually occurred at least five minutes ago <br>
     * <p>
     * Any params required
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events">
     * List public events</a>
     **/
    @RequestPath(method = GET, path = "/events")
    public <T> T getPublicEvents(ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(EVENTS_PATH), format);
    }

    /**
     * Method to get an events list <br>
     * There is a delay of the public events feed by five minutes, which means the most recent event returned by the public events
     * API actually occurred at least five minutes ago
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events">
     * List public events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/events")
    public Collection<Event> getPublicEvents(Params queryParams) throws IOException {
        return getPublicEvents(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * There is a delay of the public events feed by five minutes, which means the most recent event returned by the public events
     * API actually occurred at least five minutes ago
     *
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events">
     * List public events</a>
     **/
    @RequestPath(method = GET, path = "/events")
    public <T> T getPublicEvents(Params queryParams, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(EVENTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list
     *
     * @param repository: repository from fetch the list
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public Collection<Event> getRepositoriesPublicEvents(Repository repository) throws IOException {
        return getRepositoriesPublicEvents(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public <T> T getRepositoriesPublicEvents(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoriesPublicEvents(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get an events list
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public Collection<Event> getRepositoriesPublicEvents(String owner, String repo) throws IOException {
        return getRepositoriesPublicEvents(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public <T> T getRepositoriesPublicEvents(String owner, String repo, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(NETWORKS_PATH + owner + "/" + repo + "/" + EVENTS_PATH), format);
    }

    /**
     * Method to get an events list
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public Collection<Event> getRepositoriesPublicEvents(Repository repository, Params queryParams) throws IOException {
        return getRepositoriesPublicEvents(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param repository:  repository from fetch the list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public <T> T getRepositoriesPublicEvents(Repository repository, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getRepositoriesPublicEvents(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get an events list
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
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public Collection<Event> getRepositoriesPublicEvents(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoriesPublicEvents(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
     * List public events for a network of repositories</a>
     **/
    @RequestPath(method = GET, path = "/networks/{owner}/{repo}/events")
    public <T> T getRepositoriesPublicEvents(String owner, String repo, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(NETWORKS_PATH + owner + "/" + repo + "/" + EVENTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list
     *
     * @param org: organization from fetch the list
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public Collection<Event> getOrganizationPublicEvents(Organization org) throws IOException {
        return getOrganizationPublicEvents(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param org:    organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public <T> T getOrganizationPublicEvents(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationPublicEvents(org.getLogin(), format);
    }

    /**
     * Method to get an events list
     *
     * @param org: organization from fetch the list
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public Collection<Event> getOrganizationPublicEvents(String org) throws IOException {
        return getOrganizationPublicEvents(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param org:    organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public <T> T getOrganizationPublicEvents(String org, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(ORGS_PATH + org + "/" + EVENTS_PATH), format);
    }

    /**
     * Method to get an events list
     *
     * @param org:         organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public Collection<Event> getOrganizationPublicEvents(Organization org, Params queryParams) throws IOException {
        return getOrganizationPublicEvents(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param org:         organization from fetch the list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public <T> T getOrganizationPublicEvents(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationPublicEvents(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get an events list
     *
     * @param org:         organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public Collection<Event> getOrganizationPublicEvents(String org, Params queryParams) throws IOException {
        return getOrganizationPublicEvents(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param org:         organization from fetch the list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
     * List public organization events</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/events")
    public <T> T getOrganizationPublicEvents(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(ORGS_PATH + org + "/" + EVENTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list
     *
     * @param repository: repository from fetch the list
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public Collection<Event> getRepositoryEvents(Repository repository) throws IOException {
        return getRepositoryEvents(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public <T> T getRepositoryEvents(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryEvents(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get an events list
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public Collection<Event> getRepositoryEvents(String owner, String repo) throws IOException {
        return getRepositoryEvents(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public <T> T getRepositoryEvents(String owner, String repo, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + EVENTS_PATH), format);
    }

    /**
     * Method to get an events list
     *
     * @param repository:  repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public Collection<Event> getRepositoryEvents(Repository repository, Params queryParams) throws IOException {
        return getRepositoryEvents(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param repository:  repository from fetch the list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public <T> T getRepositoryEvents(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryEvents(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get an events list
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
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public Collection<Event> getRepositoryEvents(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryEvents(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
     * List repository events</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/events")
    public <T> T getRepositoryEvents(String owner, String repo, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + EVENTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list, if you are authenticated as the given user, you will see your private events.
     * Otherwise, you'll only see public events
     *
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-for-the-authenticated-user">
     * List events for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events")
    public Collection<Event> getAuthenticatedUserEvents(String username) throws IOException {
        return getAuthenticatedUserEvents(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list, if you are authenticated as the given user, you will see your private events.
     * Otherwise, you'll only see public events
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-for-the-authenticated-user">
     * List events for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events")
    public <T> T getAuthenticatedUserEvents(String username, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH), format);
    }

    /**
     * Method to get an events list, if you are authenticated as the given user, you will see your private events.
     * Otherwise, you'll only see public events
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-for-the-authenticated-user">
     * List events for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events")
    public Collection<Event> getAuthenticatedUserEvents(String username, Params queryParams) throws IOException {
        return getAuthenticatedUserEvents(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list, if you are authenticated as the given user, you will see your private events.
     * Otherwise, you'll only see public events
     *
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-for-the-authenticated-user">
     * List events for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events")
    public <T> T getAuthenticatedUserEvents(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public Collection<Event> getAuthenticatedUserOrganizationEvents(Organization org, String username) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org.getLogin(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationEvents(Organization org, String username,
                                                        ReturnFormat format) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org.getLogin(), username, format);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public Collection<Event> getAuthenticatedUserOrganizationEvents(String org, String username) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationEvents(String org, String username,
                                                        ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH + "/" + ORGS_PATH + org),
                format);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:         organization from fetch the list
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public Collection<Event> getAuthenticatedUserOrganizationEvents(Organization org, String username,
                                                                    Params queryParams) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org.getLogin(), username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:         organization from fetch the list
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationEvents(Organization org, String username, Params queryParams,
                                                        ReturnFormat format) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org.getLogin(), username, queryParams, format);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:         organization from fetch the list
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public Collection<Event> getAuthenticatedUserOrganizationEvents(String org, String username,
                                                                    Params queryParams) throws IOException {
        return getAuthenticatedUserOrganizationEvents(org, username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * This is the user's organization dashboard. You must be authenticated as the user to view this
     *
     * @param org:         organization from fetch the list
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
     * List organization events for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationEvents(String org, String username, Params queryParams,
                                                        ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH + "/" + ORGS_PATH + org
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list
     *
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-user">
     * List public events for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events/public")
    public Collection<Event> getUserPublicEvents(String username) throws IOException {
        return getUserPublicEvents(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-user">
     * List public events for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events/public")
    public <T> T getUserPublicEvents(String username, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH + PUBLIC_PATH), format);
    }

    /**
     * Method to get an events list
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-user">
     * List public events for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/events/public")
    public Collection<Event> getUserPublicEvents(String username, Params queryParams) throws IOException {
        return getUserPublicEvents(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-user">
     * List public events for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/events/public")
    public <T> T getUserPublicEvents(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + "/" + EVENTS_PATH + PUBLIC_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list <br>
     * These are events that you've received by watching repos and following users. If you are authenticated as the given user,
     * you will see private events. Otherwise, you'll only see public events
     *
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-received-by-the-authenticated-user">
     * List events received by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/received_events")
    public Collection<Event> getAuthenticatedUserReceivedEvents(String username) throws IOException {
        return getAuthenticatedUserReceivedEvents(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * These are events that you've received by watching repos and following users. If you are authenticated as the given user,
     * you will see private events. Otherwise, you'll only see public events
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-received-by-the-authenticated-user">
     * List events received by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/received_events")
    public <T> T getAuthenticatedUserReceivedEvents(String username, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + RECEIVED_EVENTS_PATH), format);
    }

    /**
     * Method to get an events list <br>
     * These are events that you've received by watching repos and following users. If you are authenticated as the given user,
     * you will see private events. Otherwise, you'll only see public events
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-received-by-the-authenticated-user">
     * List events received by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/received_events")
    public Collection<Event> getAuthenticatedUserReceivedEvents(String username, Params queryParams) throws IOException {
        return getAuthenticatedUserReceivedEvents(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list <br>
     * These are events that you've received by watching repos and following users. If you are authenticated as the given user,
     * you will see private events. Otherwise, you'll only see public events
     *
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-events-received-by-the-authenticated-user">
     * List events received by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/received_events")
    public <T> T getAuthenticatedUserReceivedEvents(String username, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + RECEIVED_EVENTS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get an events list
     *
     * @param username: the handle for the GitHub user account
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-received-by-a-user">
     * List public events received by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/received_events/public")
    public Collection<Event> getUserReceivedPublicEvents(String username) throws IOException {
        return getUserReceivedPublicEvents(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-received-by-a-user">
     * List public events received by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/received_events/public")
    public <T> T getUserReceivedPublicEvents(String username, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + RECEIVED_EVENTS_PATH + PUBLIC_PATH),
                format);
    }

    /**
     * Method to get an events list
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return events list as {@link Collection} of {@link Event} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-received-by-a-user">
     * List public events received by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/received_events/public")
    public Collection<Event> getUserReceivedPublicEvents(String username, Params queryParams) throws IOException {
        return getUserReceivedPublicEvents(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get an events list
     *
     * @param username:    the handle for the GitHub user account
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
     * @return events list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/events#list-public-events-received-by-a-user">
     * List public events received by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/received_events/public")
    public <T> T getUserReceivedPublicEvents(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnEventsList(sendGetRequest(USERS_PATH + username + RECEIVED_EVENTS_PATH + PUBLIC_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create an events list
     *
     * @param eventsResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return events list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnEventsList(String eventsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(eventsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Event> events = new ArrayList<>();
                JSONArray jEvents = new JSONArray(eventsResponse);
                for (int j = 0; j < jEvents.length(); j++)
                    events.add(new Event(jEvents.getJSONObject(j)));
                return (T) events;
            default:
                return (T) eventsResponse;
        }
    }

}
