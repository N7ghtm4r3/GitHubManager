package com.tecknobit.githubmanager.users.followers;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.users.records.User;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubFollowersManager} class is useful to manage all GitHub's followers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers">
 * Followers</a>
 * @see GitHubManager
 **/
public class GitHubFollowersManager extends GitHubManager {

    /**
     * {@code FOLLOWERS_PATH} constant for {@code "/followers"} path
     **/
    public static final String FOLLOWERS_PATH = "/followers";

    /**
     * {@code FOLLOWING_PATH} constant for {@code "/following"} path
     **/
    public static final String FOLLOWING_PATH = "/following";

    /**
     * {@code USER_FOLLOWERS_PATH} constant for {@code "user/followers"} path
     **/
    public static final String USER_FOLLOWERS_PATH = USER_PATH + FOLLOWERS_PATH;

    /**
     * {@code USER_FOLLOWING_PATH} constant for {@code "user/following"} path
     **/
    public static final String USER_FOLLOWING_PATH = USER_PATH + FOLLOWING_PATH;

    /**
     * Constructor to init a {@link GitHubFollowersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubFollowersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubFollowersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubFollowersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubFollowersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubFollowersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubFollowersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubFollowersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubFollowersManager} <br>
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
    public GitHubFollowersManager() {
        super();
    }

    /**
     * Method to get the list of the people following the authenticated user <br>
     * No-any params required
     *
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-the-authenticated-user">
     * List followers of the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/followers")
    public ArrayList<User> getFollowers() throws IOException {
        return getFollowers(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the authenticated user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-the-authenticated-user">
     * List followers of the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/followers")
    public <T> T getFollowers(ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_FOLLOWERS_PATH), format);
    }

    /**
     * Method to get the list of the people following the authenticated user
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
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-the-authenticated-user">
     * List followers of the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/followers")
    public ArrayList<User> getFollowers(Params queryParams) throws IOException {
        return getFollowers(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the authenticated user
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
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-the-authenticated-user">
     * List followers of the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/followers")
    public <T> T getFollowers(Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_FOLLOWERS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the people who the authenticated user follows <br>
     * No-any params required
     *
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-the-people-the-authenticated-user-follows">
     * List the people the authenticated user follows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/following")
    public ArrayList<User> getFollowing() throws IOException {
        return getFollowing(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the authenticated user follows
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-the-people-the-authenticated-user-follows">
     * List the people the authenticated user follows</a>
     **/
    @RequestPath(method = GET, path = "/user/following")
    public <T> T getFollowing(ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_FOLLOWING_PATH), format);
    }

    /**
     * Method to get the list of the people who the authenticated user follows
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
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-the-people-the-authenticated-user-follows">
     * List the people the authenticated user follows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/following")
    public ArrayList<User> getFollowing(Params queryParams) throws IOException {
        return getFollowing(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the authenticated user follows
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
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-the-people-the-authenticated-user-follows">
     * List the people the authenticated user follows</a>
     **/
    @RequestPath(method = GET, path = "/user/following")
    public <T> T getFollowing(Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_FOLLOWING_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to check if a person is followed by the authenticated user
     *
     * @param user: the user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-person-is-followed-by-the-authenticated-user">
     * Check if a person is followed by the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/following/{username}")
    public boolean checkIfPersonIsFollowed(User user) {
        return checkIfPersonIsFollowed(user.getName());
    }

    /**
     * Method to check if a person is followed by the authenticated user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-person-is-followed-by-the-authenticated-user">
     * Check if a person is followed by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/following/{username}")
    public boolean checkIfPersonIsFollowed(String username) {
        try {
            sendGetRequest(USER_FOLLOWING_PATH + "/" + username);
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
     * Method to follow a user
     *
     * @param user: the user to follow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#follow-a-user">
     * Follow a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/following/{username}")
    public boolean followUser(User user) {
        return followUser(user.getName());
    }

    /**
     * Method to follow a user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#follow-a-user">
     * Follow a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/following/{username}")
    public boolean followUser(String username) {
        try {
            sendPutRequest(USER_FOLLOWING_PATH + "/" + username, null);
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
     * Method to unfollow a user
     *
     * @param user: the user to unfollow
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#unfollow-a-user">
     * Unfollow a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/following/{username}")
    public boolean unfollowUser(User user) {
        return unfollowUser(user.getName());
    }

    /**
     * Method to unfollow a user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#unfollow-a-user">
     * Unfollow a user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/following/{username}")
    public boolean unfollowUser(String username) {
        try {
            sendDeleteRequest(USER_FOLLOWING_PATH + "/" + username);
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
     * Method to get the list of the people following the specified user <br>
     *
     * @param user: the user from fetch the list
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public ArrayList<User> getUserFollowers(User user) throws IOException {
        return getUserFollowers(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the specified user <br>
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public <T> T getUserFollowers(User user, ReturnFormat format) throws IOException {
        return getUserFollowers(user.getName(), format);
    }

    /**
     * Method to get the list of the people following the specified user
     *
     * @param username: the handle for the GitHub user account
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public ArrayList<User> getUserFollowers(String username) throws IOException {
        return getUserFollowers(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the specified user
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public <T> T getUserFollowers(String username, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_PATH + username + FOLLOWERS_PATH), format);
    }

    /**
     * Method to get the list of the people following the specified user
     *
     * @param user:        the user from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public ArrayList<User> getUserFollowers(User user, Params queryParams) throws IOException {
        return getUserFollowers(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the specified user
     *
     * @param user:        the user from fetch the list
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
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public <T> T getUserFollowers(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserFollowers(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the people following the specified user
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
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public ArrayList<User> getUserFollowers(String username, Params queryParams) throws IOException {
        return getUserFollowers(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people following the specified user
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
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#list-followers-of-a-user">
     * List followers of a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/followers")
    public <T> T getUserFollowers(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_PATH + username + FOLLOWERS_PATH), format);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param user: the user from fetch the list
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following")
    public ArrayList<User> getUserFollowing(User user) throws IOException {
        return getUserFollowing(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following")
    public <T> T getUserFollowing(User user, ReturnFormat format) throws IOException {
        return getUserFollowing(user.getName(), format);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param username: the handle for the GitHub user account
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/following")
    public ArrayList<User> getUserFollowing(String username) throws IOException {
        return getUserFollowing(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/following")
    public <T> T getUserFollowing(String username, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_PATH + username + FOLLOWING_PATH), format);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param user:        the user from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following")
    public ArrayList<User> getUserFollowing(User user, Params queryParams) throws IOException {
        return getUserFollowing(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the specified user follows
     *
     * @param user:        the user from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following")
    public <T> T getUserFollowing(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserFollowing(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the people who the specified user follows
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
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/following")
    public ArrayList<User> getUserFollowing(String username, Params queryParams) throws IOException {
        return getUserFollowing(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the people who the specified user follows
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/follower#list-the-people-a-user-follows">
     * List the people a user follows</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/following")
    public <T> T getUserFollowing(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_PATH + username + FOLLOWING_PATH), format);
    }

    /**
     * Method to check if a user follows another user
     *
     * @param user:   the user to check
     * @param target: the target user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-user-follows-another-user">
     * Check if a user follows another user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following/{target_user}")
    public boolean checkIfUserFollowsAnotherUser(User user, User target) {
        return checkIfUserFollowsAnotherUser(user.getName(), target.getName());
    }

    /**
     * Method to check if a user follows another user
     *
     * @param username: the handle for the GitHub user account
     * @param target:   the target user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-user-follows-another-user">
     * Check if a user follows another user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following/{target_user}")
    public boolean checkIfUserFollowsAnotherUser(String username, User target) {
        return checkIfUserFollowsAnotherUser(username, target.getName());
    }

    /**
     * Method to check if a user follows another user
     *
     * @param user:       the user to check
     * @param targetUser: the target user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-user-follows-another-user">
     * Check if a user follows another user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/following/{target_user}")
    public boolean checkIfUserFollowsAnotherUser(User user, String targetUser) {
        return checkIfUserFollowsAnotherUser(user.getName(), targetUser);
    }

    /**
     * Method to check if a user follows another user
     *
     * @param username:   the handle for the GitHub user account
     * @param targetUser: the target user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/followers#check-if-a-user-follows-another-user">
     * Check if a user follows another user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/following/{target_user}")
    public boolean checkIfUserFollowsAnotherUser(String username, String targetUser) {
        try {
            sendGetRequest(USERS_PATH + username + FOLLOWING_PATH + "/" + targetUser);
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
