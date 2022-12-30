package com.tecknobit.githubmanager.activity.watching;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.activity.watching.records.RepositorySubscription;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;
import static com.tecknobit.githubmanager.records.repository.CompleteRepository.returnCompleteRepositoriesList;

/**
 * The {@code GitHubWatchingManager} class is useful to manage all GitHub's watching endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/watching">
 * Watching</a>
 * @see GitHubManager
 **/
public class GitHubWatchingManager extends GitHubManager {

    /**
     * {@code SUBSCRIBERS_PATH} constant for {@code "/subscribers"} path
     **/
    public static final String SUBSCRIBERS_PATH = "/subscribers";

    /**
     * {@code SUBSCRIPTIONS_PATH} constant for {@code "/subscriptions"} path
     **/
    public static final String SUBSCRIPTIONS_PATH = "/subscriptions";

    /**
     * {@code USER_SUBSCRIPTIONS_PATH} constant for {@code "user/subscriptions"} path
     **/
    public static final String USER_SUBSCRIPTIONS_PATH = USER_PATH + SUBSCRIPTIONS_PATH;

    /**
     * Constructor to init a {@link GitHubWatchingManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWatchingManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubWatchingManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWatchingManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubWatchingManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWatchingManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWatchingManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWatchingManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWatchingManager} <br>
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
    public GitHubWatchingManager() {
        super();
    }

    /**
     * Method to get the people watching the specified repository
     *
     * @param repository: repository from fetch the list
     * @return watchers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public Collection<User> getWatchers(Repository repository) throws IOException {
        return getWatchers(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the people watching the specified repository
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return watchers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public <T> T getWatchers(Repository repository, ReturnFormat format) throws IOException {
        return getWatchers(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the people watching the specified repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return watchers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public Collection<User> getWatchers(String owner, String repo) throws IOException {
        return getWatchers(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people watching the specified repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return watchers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public <T> T getWatchers(String owner, String repo, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIBERS_PATH), format);
    }

    /**
     * Method to get the people watching the specified repository
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
     * @return watchers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public Collection<User> getWatchers(Repository repository, Params queryParams) throws IOException {
        return getWatchers(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people watching the specified repository
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
     * @return watchers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public <T> T getWatchers(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getWatchers(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the people watching the specified repository
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
     * @return watchers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public Collection<User> getWatchers(String owner, String repo, Params queryParams) throws IOException {
        return getWatchers(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people watching the specified repository
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
     * @return watchers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-watchers">
     * List watchers</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscribers")
    public <T> T getWatchers(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIBERS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a repository subscription
     *
     * @param repository: repository from fetch the subscription
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#get-a-repository-subscription">
     * Get a repository subscription</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription getRepositorySubscription(Repository repository) throws IOException {
        return getRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a repository subscription
     *
     * @param repository: repository from fetch the subscription
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#get-a-repository-subscription">
     * Get a repository subscription</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscription")
    public <T> T getRepositorySubscription(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a repository subscription
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#get-a-repository-subscription">
     * Get a repository subscription</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription getRepositorySubscription(String owner, String repo) throws IOException {
        return getRepositorySubscription(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a repository subscription
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#get-a-repository-subscription">
     * Get a repository subscription</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/subscription")
    public <T> T getRepositorySubscription(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositorySubscription(sendGetRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIPTION_PATH),
                format);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param repository: repository to set the subscription
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription setRepositorySubscription(Repository repository) throws IOException {
        return setRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param repository: repository to set the subscription
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public <T> T setRepositorySubscription(Repository repository, ReturnFormat format) throws IOException {
        return setRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription setRepositorySubscription(String owner, String repo) throws IOException {
        return setRepositorySubscription(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public <T> T setRepositorySubscription(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositorySubscription(sendPutRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIPTION_PATH,
                null), format);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param repository: repository to set the subscription
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "subscribed"} -> determines if notifications should be received
     *                            from this repository - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "ignored"} -> determines if all notifications should be blocked 
     *                            from this repository - [boolean]
     *                        </li>
     *                     </ul>
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription setRepositorySubscription(Repository repository, Params bodyParams) throws IOException {
        return setRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param repository: repository to set the subscription
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "subscribed"} -> determines if notifications should be received
     *                           from this repository - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "ignored"} -> determines if all notifications should be blocked
     *                           from this repository - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public <T> T setRepositorySubscription(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return setRepositorySubscription(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "subscribed"} -> determines if notifications should be received
     *                           from this repository - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "ignored"} -> determines if all notifications should be blocked
     *                           from this repository - [boolean]
     *                       </li>
     *                    </ul>
     * @return repository subscription as {@link RepositorySubscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public RepositorySubscription setRepositorySubscription(String owner, String repo, Params bodyParams) throws IOException {
        return setRepositorySubscription(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to set a repository subscription <br>
     * If you would like to watch a repository, set subscribed to true. <br>
     * If you would like to ignore notifications made within a repository, set ignored to true. <br>
     * If you would like to stop watching a repository, delete the repository's subscription completely
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "subscribed"} -> determines if notifications should be received
     *                           from this repository - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "ignored"} -> determines if all notifications should be blocked
     *                           from this repository - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#set-a-repository-subscription">
     * Set a repository subscription</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/subscription")
    public <T> T setRepositorySubscription(String owner, String repo, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return returnRepositorySubscription(sendPutRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIPTION_PATH,
                bodyParams), format);
    }

    /**
     * Method to create a repository subscription object
     *
     * @param subscriptionResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repository subscription as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositorySubscription(String subscriptionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(subscriptionResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositorySubscription(new JSONObject(subscriptionResponse));
            default:
                return (T) subscriptionResponse;
        }
    }

    /**
     * Method to delete a repository subscription <br>
     * This endpoint should only be used to stop watching a repository. To control whether you wish to receive notifications
     * from a repository, set the repository's subscription manually with {@link #setRepositorySubscription(String, String)}
     * method
     *
     * @param repository: repository from delete the subscription
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#delete-a-repository-subscription">
     * Delete a repository subscription</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/subscription")
    public boolean deleteRepositorySubscription(Repository repository) {
        return deleteRepositorySubscription(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to delete a repository subscription <br>
     * This endpoint should only be used to stop watching a repository. To control whether you wish to receive notifications
     * from a repository, set the repository's subscription manually with {@link #setRepositorySubscription(String, String)}
     * method
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#delete-a-repository-subscription">
     * Delete a repository subscription</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/subscription")
    public boolean deleteRepositorySubscription(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + SUBSCRIPTION_PATH);
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
     * Method to get the list of the repositories watched by the authenticated user <br>
     * Any params required
     *
     * @return repositories list as {@link Collection} of {@link CompleteRepository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-the-authenticated-user">
     * List repositories watched by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/subscriptions")
    public Collection<CompleteRepository> getAuthenticatedUserRepositoriesWatched() throws IOException {
        return getAuthenticatedUserRepositoriesWatched(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories watched by the authenticated user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-the-authenticated-user">
     * List repositories watched by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/subscriptions")
    public <T> T getAuthenticatedUserRepositoriesWatched(ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_SUBSCRIPTIONS_PATH), format);
    }

    /**
     * Method to get the list of the repositories watched by the authenticated user
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
     * @return repositories list as {@link Collection} of {@link CompleteRepository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-the-authenticated-user">
     * List repositories watched by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/subscriptions")
    public Collection<CompleteRepository> getAuthenticatedUserRepositoriesWatched(Params queryParams) throws IOException {
        return getAuthenticatedUserRepositoriesWatched(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories watched by the authenticated user
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-the-authenticated-user">
     * List repositories watched by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/subscriptions")
    public <T> T getAuthenticatedUserRepositoriesWatched(Params queryParams, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_SUBSCRIPTIONS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to get the list of the repositories a user is watching
     *
     * @param username: the handle for the GitHub user account
     * @return repositories list as {@link Collection} of {@link CompleteRepository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-a-user">
     * List repositories watched by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/subscriptions")
    public Collection<CompleteRepository> getUserRepositoriesWatched(String username) throws IOException {
        return getUserRepositoriesWatched(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories a user is watching
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-a-user">
     * List repositories watched by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/subscriptions")
    public <T> T getUserRepositoriesWatched(String username, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USERS_PATH + username + SUBSCRIPTIONS_PATH), format);
    }

    /**
     * Method to get the list of the repositories a user is watching
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
     * @return repositories list as {@link Collection} of {@link CompleteRepository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-a-user">
     * List repositories watched by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/subscriptions")
    public Collection<CompleteRepository> getUserRepositoriesWatched(String username, Params queryParams) throws IOException {
        return getUserRepositoriesWatched(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories a user is watching
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/watching#list-repositories-watched-by-a-user">
     * List repositories watched by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/subscriptions")
    public <T> T getUserRepositoriesWatched(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USERS_PATH + username + SUBSCRIPTIONS_PATH +
                queryParams.createQueryString()), format);
    }

}

