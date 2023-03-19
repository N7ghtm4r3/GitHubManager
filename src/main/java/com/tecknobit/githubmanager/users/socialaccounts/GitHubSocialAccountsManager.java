package com.tecknobit.githubmanager.users.socialaccounts;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.socialaccounts.records.SocialAccount;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubSocialAccountsManager} class is useful to manage all GitHub's social accounts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts">
 * Social accounts</a>
 * @see GitHubManager
 **/
public class GitHubSocialAccountsManager extends GitHubManager {

    /**
     * {@code SOCIAL_ACCOUNTS_PATH} constant for {@code "/social_accounts"} path
     **/
    public static final String SOCIAL_ACCOUNTS_PATH = "/social_accounts";

    /**
     * {@code USER_SOCIAL_ACCOUNTS_PATH} constant for {@code "user/social_accounts"} path
     **/
    public static final String USER_SOCIAL_ACCOUNTS_PATH = USER_PATH + "/social_accounts";

    /**
     * Constructor to init a {@link GitHubSocialAccountsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSocialAccountsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSocialAccountsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSocialAccountsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSocialAccountsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSocialAccountsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSocialAccountsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSocialAccountsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSocialAccountsManager} <br>
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
    public GitHubSocialAccountsManager() {
        super();
    }

    /**
     * Method to get the list of the all of your social accounts <br>
     * No-any params required
     *
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-the-authenticated-user">
     * List social accounts for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/social_accounts")
    public ArrayList<SocialAccount> getSocialAccounts() throws IOException {
        return getSocialAccounts(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all of your social accounts
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-the-authenticated-user">
     * List social accounts for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/social_accounts")
    public <T> T getSocialAccounts(ReturnFormat format) throws IOException {
        return returnSocialAccounts(sendGetRequest(USER_SOCIAL_ACCOUNTS_PATH), format);
    }

    /**
     * Method to get the list of the all of your social accounts
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
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-the-authenticated-user">
     * List social accounts for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/social_accounts")
    public ArrayList<SocialAccount> getSocialAccounts(Params queryParams) throws IOException {
        return getSocialAccounts(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all of your social accounts
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
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-the-authenticated-user">
     * List social accounts for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/social_accounts")
    public <T> T getSocialAccounts(Params queryParams, ReturnFormat format) throws IOException {
        return returnSocialAccounts(sendGetRequest(USER_SOCIAL_ACCOUNTS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to add one or more social accounts to the authenticated user's profile. This endpoint is accessible with
     * the user scope
     *
     * @param accountsUrl: full URLs for the social media profiles to add
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#add-social-accounts-for-the-authenticated-user">
     * Add social accounts for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/social_accounts")
    public ArrayList<SocialAccount> addSocialAccounts(String... accountsUrl) throws IOException {
        return addSocialAccounts(LIBRARY_OBJECT, accountsUrl);
    }

    /**
     * Method to add one or more social accounts to the authenticated user's profile. This endpoint is accessible with
     * the user scope
     *
     * @param accountsUrl: full URLs for the social media profiles to add
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#add-social-accounts-for-the-authenticated-user">
     * Add social accounts for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/social_accounts")
    public <T> T addSocialAccounts(ReturnFormat format, String... accountsUrl) throws IOException {
        Params payload = new Params();
        payload.addParam("account_urls", accountsUrl);
        return returnSocialAccounts(sendPostRequest(USER_SOCIAL_ACCOUNTS_PATH, payload), format);
    }

    /**
     * Method to delete one or more social accounts from the authenticated user's profile. This endpoint is accessible
     * with the user scope
     *
     * @param accountsUrl: full URLs for the social media profiles to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} method if not successful
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#delete-social-accounts-for-the-authenticated-user">
     * Delete social accounts for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/social_accounts")
    public boolean deleteEmailAddress(String... accountsUrl) throws IOException {
        Params payload = new Params();
        payload.addParam("account_urls", accountsUrl);
        HashMap<String, Object> result = sendDeleteRequest(USER_SOCIAL_ACCOUNTS_PATH, payload);
        Object success = result.get("success");
        if (success != null)
            return true;
        else
            throw new IOException(result.get("error").toString());
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
     *
     * @param user: the user from fetch the list
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public ArrayList<SocialAccount> getUserSocialAccounts(User user) throws IOException {
        return getUserSocialAccounts(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public <T> T getUserSocialAccounts(User user, ReturnFormat format) throws IOException {
        return getUserSocialAccounts(user.getName(), format);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public ArrayList<SocialAccount> getUserSocialAccounts(String username) throws IOException {
        return getUserSocialAccounts(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public <T> T getUserSocialAccounts(String username, ReturnFormat format) throws IOException {
        return returnSocialAccounts(sendGetRequest(USERS_PATH + username + SOCIAL_ACCOUNTS_PATH), format);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
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
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public ArrayList<SocialAccount> getUserSocialAccounts(User user, Params queryParams) throws IOException {
        return getUserSocialAccounts(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
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
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public <T> T getUserSocialAccounts(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserSocialAccounts(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
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
     * @return social accounts list as {@link ArrayList} of {@link SocialAccount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public ArrayList<SocialAccount> getUserSocialAccounts(String username, Params queryParams) throws IOException {
        return getUserSocialAccounts(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the social media accounts for a user. This endpoint is accessible by anyone
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
     * @return social accounts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
     * List social accounts for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/social_accounts")
    public <T> T getUserSocialAccounts(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnSocialAccounts(sendGetRequest(USERS_PATH + username + SOCIAL_ACCOUNTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a social accounts list
     *
     * @param socialAccountsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return social accounts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSocialAccounts(String socialAccountsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(socialAccountsResponse);
            case LIBRARY_OBJECT:
                ArrayList<SocialAccount> socialAccounts = new ArrayList<>();
                JSONArray jSocialAccounts = new JSONArray(socialAccountsResponse);
                for (int j = 0; j < jSocialAccounts.length(); j++)
                    socialAccounts.add(new SocialAccount(jSocialAccounts.getJSONObject(j)));
                return (T) socialAccounts;
            default:
                return (T) socialAccountsResponse;
        }
    }

}
