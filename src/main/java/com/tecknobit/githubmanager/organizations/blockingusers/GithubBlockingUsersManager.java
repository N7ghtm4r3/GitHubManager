package com.tecknobit.githubmanager.organizations.blockingusers;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.users.users.records.User;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GithubBlockingUsersManager} class is useful to manage all GitHub's blocking users endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking">
 * Blocking users</a>
 * @see GitHubManager
 **/
public class GithubBlockingUsersManager extends GitHubManager {

    /**
     * {@code BLOCKS_PATH} constant for {@code "/blocks"} path
     **/
    public static final String BLOCKS_PATH = "/blocks";

    /**
     * Constructor to init a {@link GithubBlockingUsersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GithubBlockingUsersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GithubBlockingUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GithubBlockingUsersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GithubBlockingUsersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GithubBlockingUsersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubBlockingUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GithubBlockingUsersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubBlockingUsersManager} <br>
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
    public GithubBlockingUsersManager() {
        super();
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org: the organization from fetch the list
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public ArrayList<User> getOrganizationBlockedUsers(Organization org) throws IOException {
        return getOrganizationBlockedUsers(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public <T> T getOrganizationBlockedUsers(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationBlockedUsers(org.getLogin(), format);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public ArrayList<User> getOrganizationBlockedUsers(String org) throws IOException {
        return getOrganizationBlockedUsers(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public <T> T getOrganizationBlockedUsers(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + BLOCKS_PATH), format);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public ArrayList<User> getOrganizationBlockedUsers(Organization org, Params queryParams) throws IOException {
        return getOrganizationBlockedUsers(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:         the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public <T> T getOrganizationBlockedUsers(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationBlockedUsers(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public ArrayList<User> getOrganizationBlockedUsers(String org, Params queryParams) throws IOException {
        return getOrganizationBlockedUsers(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the users blocked by an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#list-users-blocked-by-an-organization">
     * List users blocked by an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/blocks")
    public <T> T getOrganizationBlockedUsers(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + BLOCKS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to check if a user is blocked by an organization
     *
     * @param org:      the organization where check if the user is blocked
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#check-if-a-user-is-blocked-by-an-organization">
     * Check if a user is blocked by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/blocks/{username}")
    public boolean checkIfUserBlockedByOrganization(Organization org, String username) {
        return checkIfUserBlockedByOrganization(org.getLogin(), username);
    }

    /**
     * Method to check if a user is blocked by an organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#check-if-a-user-is-blocked-by-an-organization">
     * Check if a user is blocked by an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/blocks/{username}")
    public boolean checkIfUserBlockedByOrganization(String org, String username) {
        try {
            sendGetRequest(ORGS_PATH + org + BLOCKS_PATH + "/" + username);
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
     * Method to block a user from an organization
     *
     * @param org:      the organization where block the user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#block-a-user-from-an-organization">
     * Block a user from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/blocks/{username}")
    public boolean blockUserFromOrganization(Organization org, String username) {
        return blockUserFromOrganization(org.getLogin(), username);
    }

    /**
     * Method to block a user from an organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#block-a-user-from-an-organization">
     * Block a user from an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/blocks/{username}")
    public boolean blockUserFromOrganization(String org, String username) {
        try {
            sendPutRequest(ORGS_PATH + org + BLOCKS_PATH + "/" + username, null);
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
     * Method to unblock a user from an organization
     *
     * @param org:      the organization where unblock the user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#unblock-a-user-from-an-organization">
     * Unblock a user from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/blocks/{username}")
    public boolean unblockUserFromOrganization(Organization org, String username) {
        return blockUserFromOrganization(org.getLogin(), username);
    }

    /**
     * Method to unblock a user from an organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/blocking#unblock-a-user-from-an-organization">
     * Unblock a user from an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/blocks/{username}")
    public boolean unblockUserFromOrganization(String org, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + BLOCKS_PATH + "/" + username);
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
