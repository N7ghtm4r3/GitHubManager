package com.tecknobit.githubmanager.users.blockingusers;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.users.records.User;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.organizations.blockingusers.GithubBlockingUsersManager.BLOCKS_PATH;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubBlockingUsersManager} class is useful to manage all GitHub's blocking users endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking">
 * Blocking users</a>
 * @see GitHubManager
 **/
public class GitHubBlockingUsersManager extends GitHubManager {

    /**
     * {@code USER_BLOCKS_PATH} constant for {@code "user/blocks"} path
     **/
    public static final String USER_BLOCKS_PATH = USER_PATH + BLOCKS_PATH;

    /**
     * Constructor to init a {@link GitHubBlockingUsersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubBlockingUsersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubBlockingUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubBlockingUsersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubBlockingUsersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubBlockingUsersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBlockingUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubBlockingUsersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBlockingUsersManager} <br>
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
    public GitHubBlockingUsersManager() {
        super();
    }

    /**
     * Method to get the list of the users you've blocked on your personal account <br>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#list-users-blocked-by-the-authenticated-user">
     * List users blocked by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/blocks")
    public ArrayList<User> getBlockedUsers() throws IOException {
        return getBlockedUsers(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users you've blocked on your personal account
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#list-users-blocked-by-the-authenticated-user">
     * List users blocked by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/blocks")
    public <T> T getBlockedUsers(ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_BLOCKS_PATH), format);
    }

    /**
     * Method to get the list of the users you've blocked on your personal account
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#list-users-blocked-by-the-authenticated-user">
     * List users blocked by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/blocks")
    public ArrayList<User> getBlockedUsers(Params queryParams) throws IOException {
        return getBlockedUsers(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users you've blocked on your personal account
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#list-users-blocked-by-the-authenticated-user">
     * List users blocked by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/blocks")
    public <T> T getBlockedUsers(Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USER_BLOCKS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to check if a user is blocked by the authenticated user
     *
     * @param user: the user to check
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#check-if-a-user-is-blocked-by-the-authenticated-user">
     * Check if a user is blocked by the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/blocks/{username}")
    public boolean checkIfUserBlocked(User user) {
        return checkIfUserBlocked(user.getName());
    }

    /**
     * Method to check if a user is blocked by the authenticated user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#check-if-a-user-is-blocked-by-the-authenticated-user">
     * Check if a user is blocked by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/blocks/{username}")
    public boolean checkIfUserBlocked(String username) {
        try {
            sendGetRequest(USER_BLOCKS_PATH + "/" + username);
            return apiRequest.getResponseStatusCode() == 204;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to block a user
     *
     * @param user: the user to block
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#block-a-user">
     * Block a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/blocks/{username}")
    public boolean blockUser(User user) {
        return blockUser(user.getName());
    }

    /**
     * Method to block a user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#block-a-user">
     * Block a user</a>
     **/
    @RequestPath(method = PUT, path = "/user/blocks/{username}")
    public boolean blockUser(String username) {
        try {
            sendPutRequest(USER_BLOCKS_PATH + "/" + username, null);
            return apiRequest.getResponseStatusCode() == 204;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to unblock a user
     *
     * @param user: the user to unblock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#unblock-a-user">
     * Unblock a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/blocks/{username}")
    public boolean unblockUser(User user) {
        return unblockUser(user.getName());
    }

    /**
     * Method to unblock a user
     *
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/blocking#unblock-a-user">
     * Unblock a user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/blocks/{username}")
    public boolean unblockUser(String username) {
        try {
            sendDeleteRequest(USER_BLOCKS_PATH + "/" + username);
            return apiRequest.getResponseStatusCode() == 204;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

}
