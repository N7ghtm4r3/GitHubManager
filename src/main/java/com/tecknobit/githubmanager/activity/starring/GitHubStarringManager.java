package com.tecknobit.githubmanager.activity.starring;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.basics.User;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.Repository;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.basics.User.returnUsersList;
import static com.tecknobit.githubmanager.records.repository.CompleteRepository.returnCompleteRepositoriesList;

/**
 * The {@code GitHubStarringManager} class is useful to manage all GitHub's starring endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/starring">
 * Starring</a>
 * @see GitHubManager
 **/
public class GitHubStarringManager extends GitHubManager {

    /**
     * {@code STARGAZERS_PATH} constant for {@code "/stargazers"} path
     **/
    public static final String STARGAZERS_PATH = "/stargazers";

    /**
     * {@code STARRED_PATH} constant for {@code "/starred"} path
     **/
    public static final String STARRED_PATH = "/starred";

    /**
     * {@code USER_STARRED_PATH} constant for {@code "user/starred"} path
     **/
    public static final String USER_STARRED_PATH = USER_PATH + STARRED_PATH;

    /**
     * Constructor to init a {@link GitHubStarringManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubStarringManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubStarringManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubStarringManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubStarringManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubStarringManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubStarringManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubStarringManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubStarringManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubStarringManager}'s manager without re-insert
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
    public GitHubStarringManager() {
        super();
    }

    /**
     * Method to get the people that have starred the repository
     *
     * @param repository: repository from fetch the list
     * @return stargazers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public Collection<User> getStargazers(Repository repository) throws IOException {
        return getStargazers(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the people that have starred the repository
     *
     * @param repository: repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return stargazers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public <T> T getStargazers(Repository repository, ReturnFormat format) throws IOException {
        return getStargazers(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the people that have starred the repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return stargazers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public Collection<User> getStargazers(String owner, String repo) throws IOException {
        return getStargazers(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people that have starred the repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return stargazers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public <T> T getStargazers(String owner, String repo, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + STARGAZERS_PATH), format);
    }

    /**
     * Method to get the people that have starred the repository
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
     * @return stargazers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public Collection<User> getStargazers(Repository repository, Params queryParams) throws IOException {
        return getStargazers(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people that have starred the repository
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
     * @return stargazers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public <T> T getStargazers(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getStargazers(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the people that have starred the repository
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
     * @return stargazers list as {@link Collection} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public Collection<User> getStargazers(String owner, String repo, Params queryParams) throws IOException {
        return getStargazers(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the people that have starred the repository
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
     * @return stargazers list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-stargazers">
     * List stargazers</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stargazers")
    public <T> T getStargazers(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + STARGAZERS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the repositories the authenticated user has starred <br>
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-the-authenticated-user">
     * List repositories starred by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/starred")
    public Collection<CompleteRepository> getAuthenticatedUserStarredRepositories() throws IOException {
        return getAuthenticatedUserStarredRepositories(LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories the authenticated user has starred <br>
     * Any params required
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-the-authenticated-user">
     * List repositories starred by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/starred")
    public <T> T getAuthenticatedUserStarredRepositories(ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_STARRED_PATH), format);
    }

    /**
     * Method to get the repositories the authenticated user has starred
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the repository
     *                            was starred. updated means when the repository was last pushed to,
     *                            contents available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available {@link Directions}
     *                            - [string, default desc]
     *                        </li>
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-the-authenticated-user">
     * List repositories starred by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/starred")
    public Collection<CompleteRepository> getAuthenticatedUserStarredRepositories(Params queryParams) throws IOException {
        return getAuthenticatedUserStarredRepositories(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories the authenticated user has starred
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the repository
     *                            was starred. updated means when the repository was last pushed to,
     *                            contents available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available {@link Directions}
     *                            - [string, default desc]
     *                        </li>
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-the-authenticated-user">
     * List repositories starred by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/starred")
    public <T> T getAuthenticatedUserStarredRepositories(Params queryParams, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_STARRED_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to check if a repository is starred by the authenticated user
     *
     * @param repository: repository to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#check-if-a-repository-is-starred-by-the-authenticated-user">
     * Check if a repository is starred by the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/starred/{owner}/{repo}")
    public boolean checkStarredRepository(Repository repository) {
        return checkStarredRepository(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to check if a repository is starred by the authenticated user
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#check-if-a-repository-is-starred-by-the-authenticated-user">
     * Check if a repository is starred by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/starred/{owner}/{repo}")
    public boolean checkStarredRepository(String owner, String repo) {
        try {
            sendGetRequest(USER_STARRED_PATH + "/" + owner + "/" + repo);
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
     * Method to star a repository for the authenticated user
     *
     * @param repository: repository to star
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#star-a-repository-for-the-authenticated-user">
     * Star a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/starred/{owner}/{repo}")
    public boolean starRepository(Repository repository) {
        return starRepository(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to star a repository for the authenticated user
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#star-a-repository-for-the-authenticated-user">
     * Star a repository for the authenticated user</a>
     **/
    @RequestPath(method = PUT, path = "/user/starred/{owner}/{repo}")
    public boolean starRepository(String owner, String repo) {
        try {
            sendPutRequest(USER_STARRED_PATH + "/" + owner + "/" + repo, null);
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
     * Method to unstar a repository for the authenticated user
     *
     * @param repository: repository to ustar
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#unstar-a-repository-for-the-authenticated-user">
     * Unstar a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/starred/{owner}/{repo}")
    public boolean unstarRepository(Repository repository) {
        return starRepository(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to unstar a repository for the authenticated user
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#unstar-a-repository-for-the-authenticated-user">
     * Unstar a repository for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/starred/{owner}/{repo}")
    public boolean unstarRepository(String owner, String repo) {
        try {
            sendDeleteRequest(USER_STARRED_PATH + "/" + owner + "/" + repo);
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
     * Method to get the list of the repositories starred by a user
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-a-user">
     * List repositories starred by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/starred")
    public Collection<CompleteRepository> getStarredRepositories(String username) throws IOException {
        return getStarredRepositories(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories starred by a user
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-a-user">
     * List repositories starred by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/starred")
    public <T> T getStarredRepositories(String username, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USERS_PATH + username + STARRED_PATH), format);
    }

    /**
     * Method to get the list of the repositories starred by a user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the repository
     *                            was starred. updated means when the repository was last pushed to,
     *                            contents available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available {@link Directions}
     *                            - [string, default desc]
     *                        </li>
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-a-user">
     * List repositories starred by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/starred")
    public Collection<CompleteRepository> getStarredRepositories(String username, Params queryParams) throws IOException {
        return getStarredRepositories(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories starred by a user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the repository
     *                            was starred. updated means when the repository was last pushed to,
     *                            contents available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available {@link Directions}
     *                            - [string, default desc]
     *                        </li>
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
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/starring#list-repositories-starred-by-a-user">
     * List repositories starred by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/starred")
    public <T> T getStarredRepositories(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USERS_PATH + username + STARRED_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * {@code Sort} is a list for the sorts available
     **/
    public enum Sort {

        /**
         * {@code "created"} sort
         **/
        created,

        /**
         * {@code "updated"} sort
         **/
        updated

    }

}
