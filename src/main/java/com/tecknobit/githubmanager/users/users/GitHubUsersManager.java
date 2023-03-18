package com.tecknobit.githubmanager.users.users;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.users.records.ContextualInformation;
import com.tecknobit.githubmanager.users.users.records.User;
import com.tecknobit.githubmanager.users.users.records.User.SubjectType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PATCH;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubUsersManager} class is useful to manage all GitHub's users endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users">
 * Users</a>
 * @see GitHubManager
 **/
public class GitHubUsersManager extends GitHubManager {

    /**
     * {@code USERS_ROOT_PATH} constant for {@code "users"} path
     **/
    public static final String USERS_ROOT_PATH = "users";

    /**
     * {@code HOVERCARD_PATH} constant for {@code "/hovercard"} path
     **/
    public static final String HOVERCARD_PATH = "/hovercard";

    /**
     * Constructor to init a {@link GitHubUsersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubUsersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubUsersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubUsersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubUsersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUsersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubUsersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUsersManager} <br>
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
    public GitHubUsersManager() {
        super();
    }

    /**
     * Method to get the authenticated user <br>
     * No-any params required
     *
     * @return user {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-the-authenticated-user">
     * Get the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user")
    public User getAuthenticatedUser() throws IOException {
        return getAuthenticatedUser(LIBRARY_OBJECT);
    }

    /**
     * Method to get the authenticated user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return user as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-the-authenticated-user">
     * Get the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user")
    public <T> T getAuthenticatedUser(ReturnFormat format) throws IOException {
        return returnUser(sendGetRequest(USER_PATH), format);
    }

    /**
     * Method to update the authenticated user
     *
     * @param bodyParams: extra query params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the new name of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> the new blog URL of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the new Twitter username of the user - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the new company of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the new location of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "hireable"} -> the new hiring availability of the user - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "bio"} -> the new short biography of the user - [string]
     *                       </li>
     *                    </ul>
     * @return user {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#update-the-authenticated-user">
     * Update the authenticated user</a>
     * @implNote If your email is set to private, and you send an email parameter as part of this request to update your
     * profile, your privacy settings are still enforced: the email address will not be displayed on your public profile
     * or via the API
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/user")
    public User updateAuthenticatedUser(Params bodyParams) throws IOException {
        return updateAuthenticatedUser(bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the authenticated user
     *
     * @param bodyParams: extra query params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the new name of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> the new blog URL of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the new Twitter username of the user - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the new company of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the new location of the user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "hireable"} -> the new hiring availability of the user - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "bio"} -> the new short biography of the user - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return user as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#update-the-authenticated-user">
     * Update the authenticated user</a>
     * @implNote If your email is set to private, and you send an email parameter as part of this request to update your
     * profile, your privacy settings are still enforced: the email address will not be displayed on your public profile
     * or via the API
     **/
    @RequestPath(method = PATCH, path = "/user")
    public <T> T updateAuthenticatedUser(Params bodyParams, ReturnFormat format) throws IOException {
        return returnUser(sendPatchRequest(USER_PATH, bodyParams), format);
    }

    /**
     * Method to get the list of the all users, in the order that they signed up on GitHub. This list includes personal
     * user accounts and organization accounts <br>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#list-users">
     * List users</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users")
    public ArrayList<User> getUsers() throws IOException {
        return getUsers(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users, in the order that they signed up on GitHub. This list includes personal
     * user accounts and organization accounts
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#list-users">
     * List users</a>
     **/
    @RequestPath(method = GET, path = "/users")
    public <T> T getUsers(ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_ROOT_PATH), format);
    }

    /**
     * Method to get the list of the all users, in the order that they signed up on GitHub. This list includes personal
     * user accounts and organization accounts
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> a user ID. Only return users with an ID greater than this ID
     *                            - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#list-users">
     * List users</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users")
    public ArrayList<User> getUsers(Params queryParams) throws IOException {
        return getUsers(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users, in the order that they signed up on GitHub. This list includes personal
     * user accounts and organization accounts
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> a user ID. Only return users with an ID greater than this ID
     *                            - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#list-users">
     * List users</a>
     **/
    @RequestPath(method = GET, path = "/users")
    public <T> T getUsers(Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(USERS_ROOT_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a user
     *
     * @param username: the handle for the GitHub user account
     * @return user {@link User} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-a-user">
     * Get a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}")
    public User getUser(String username) throws IOException {
        return getUser(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a user
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return user as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-a-user">
     * Get a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}")
    public <T> T getUser(String username, ReturnFormat format) throws IOException {
        return returnUser(sendGetRequest(USERS_PATH + username), format);
    }

    /**
     * Method to create a user
     *
     * @param userResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return user as {@code "format"} defines
     **/
    @Returner
    private <T> T returnUser(String userResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(userResponse);
            case LIBRARY_OBJECT:
                return (T) new User(new JSONObject(userResponse));
            default:
                return (T) userResponse;
        }
    }

    /**
     * Method to provides hovercard information when authenticated through basic auth or OAuth with the repo scope.
     * You can find out more about someone in relation to their pull requests, issues, repositories, and organizations
     *
     * @param username: the handle for the GitHub user account
     * @return contextual information as {@link ArrayList} of {@link ContextualInformation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-contextual-information-for-a-user">
     * Get contextual information for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/hovercard")
    public ArrayList<ContextualInformation> getUserContextualInformation(String username) throws IOException {
        return getUserContextualInformation(username, LIBRARY_OBJECT);
    }

    /**
     * Method to provides hovercard information when authenticated through basic auth or OAuth with the repo scope.
     * You can find out more about someone in relation to their pull requests, issues, repositories, and organizations
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return contextual information as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-contextual-information-for-a-user">
     * Get contextual information for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/hovercard")
    public <T> T getUserContextualInformation(String username, ReturnFormat format) throws IOException {
        return returnContextualInformation(sendGetRequest(USERS_PATH + username + HOVERCARD_PATH), format);
    }

    /**
     * Method to provides hovercard information when authenticated through basic auth or OAuth with the repo scope.
     * You can find out more about someone in relation to their pull requests, issues, repositories, and organizations
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "subject_type"} -> identifies which additional information you'd like to receive
     *                            about the person's hovercard, constants available {@link SubjectType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "subject_id"} -> uses the ID for the subject_type you specified.
     *                            <b>Required when using {@code "subject_type"} </b> - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return contextual information as {@link ArrayList} of {@link ContextualInformation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-contextual-information-for-a-user">
     * Get contextual information for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/hovercard")
    public ArrayList<ContextualInformation> getUserContextualInformation(String username,
                                                                         Params queryParams) throws IOException {
        return getUserContextualInformation(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to provides hovercard information when authenticated through basic auth or OAuth with the repo scope.
     * You can find out more about someone in relation to their pull requests, issues, repositories, and organizations
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "subject_type"} -> identifies which additional information you'd like to receive
     *                            about the person's hovercard, constants available {@link SubjectType} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "subject_id"} -> uses the ID for the subject_type you specified.
     *                            <b>Required when using {@code "subject_type"} </b> - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return contextual information as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-contextual-information-for-a-user">
     * Get contextual information for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/hovercard")
    public <T> T getUserContextualInformation(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnContextualInformation(sendGetRequest(USERS_PATH + username + HOVERCARD_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a contextual information
     *
     * @param contextualInformationResponse: obtained from GitHub's response
     * @param format:                        return type formatter -> {@link ReturnFormat}
     * @return contextual information as {@code "format"} defines
     **/
    @Returner
    private <T> T returnContextualInformation(String contextualInformationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(contextualInformationResponse);
            case LIBRARY_OBJECT:
                ArrayList<ContextualInformation> contextualInformation = new ArrayList<>();
                JSONArray jContextualInformation = new JSONObject(contextualInformationResponse).getJSONArray("contexts");
                if (jContextualInformation != null)
                    for (int j = 0; j < jContextualInformation.length(); j++)
                        contextualInformation.add(new ContextualInformation(jContextualInformation.getJSONObject(j)));
                return (T) contextualInformation;
            default:
                return (T) contextualInformationResponse;
        }
    }

}
