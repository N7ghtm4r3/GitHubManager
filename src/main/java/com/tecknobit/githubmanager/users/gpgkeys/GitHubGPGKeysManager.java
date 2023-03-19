package com.tecknobit.githubmanager.users.gpgkeys;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.gpgkeys.records.GitHubGPGKey;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubGPGKeysManager} class is useful to manage all GitHub's GPG keys endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys">
 * GPG Keys</a>
 * @see GitHubManager
 **/
public class GitHubGPGKeysManager extends GitHubManager {

    /**
     * {@code GPG_KEYS_PATH} constant for {@code "gpg_keys"} path
     **/
    public static final String GPG_KEYS_PATH = "/gpg_keys";

    /**
     * {@code USER_GPG_KEYS_PATH} constant for {@code "user/gpg_keys"} path
     **/
    public static final String USER_GPG_KEYS_PATH = USER_PATH + GPG_KEYS_PATH;

    /**
     * Constructor to init a {@link GitHubGPGKeysManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGPGKeysManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubGPGKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGPGKeysManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubGPGKeysManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGPGKeysManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGPGKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGPGKeysManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGPGKeysManager} <br>
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
    public GitHubGPGKeysManager() {
        super();
    }

    /**
     * Method to get the list of the current user's GPG keys. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope <br>
     * No-any params required
     *
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-the-authenticated-user">
     * List GPG keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/gpg_keys")
    public ArrayList<GitHubGPGKey> getGPGKeys() throws IOException {
        return getGPGKeys(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the current user's GPG keys. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-the-authenticated-user">
     * List GPG keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/gpg_keys")
    public <T> T getGPGKeys(ReturnFormat format) throws IOException {
        return returnGPGKeys(sendGetRequest(USER_GPG_KEYS_PATH), format);
    }

    /**
     * Method to get the list of the current user's GPG keys. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope
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
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-the-authenticated-user">
     * List GPG keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/gpg_keys")
    public ArrayList<GitHubGPGKey> getGPGKeys(Params queryParams) throws IOException {
        return getGPGKeys(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the current user's GPG keys. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope
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
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-the-authenticated-user">
     * List GPG keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/gpg_keys")
    public <T> T getGPGKeys(Params queryParams, ReturnFormat format) throws IOException {
        return returnGPGKeys(sendGetRequest(USER_GPG_KEYS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to add a GPG key to the authenticated user's GitHub account. Requires that you are authenticated via Basic
     * Auth, or OAuth with at least {@code "write:gpg_key"} scope
     *
     * @param armoredPublicKey: GPG key in ASCII-armored format
     * @return GPG key as {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#create-a-gpg-key-for-the-authenticated-user">
     * Create a GPG key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/gpg_keys")
    public GitHubGPGKey createGPGKey(String armoredPublicKey) throws IOException {
        return createGPGKey(armoredPublicKey, LIBRARY_OBJECT);
    }

    /**
     * Method to add a GPG key to the authenticated user's GitHub account. Requires that you are authenticated via Basic
     * Auth, or OAuth with at least {@code "write:gpg_key"} scope
     *
     * @param armoredPublicKey: GPG key in ASCII-armored format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return GPG key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#create-a-gpg-key-for-the-authenticated-user">
     * Create a GPG key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/gpg_keys")
    public <T> T createGPGKey(String armoredPublicKey, ReturnFormat format) throws IOException {
        return createGPGKey(armoredPublicKey, null, format);
    }

    /**
     * Method to add a GPG key to the authenticated user's GitHub account. Requires that you are authenticated via Basic
     * Auth, or OAuth with at least {@code "write:gpg_key"} scope
     *
     * @param armoredPublicKey: GPG key in ASCII-armored format
     * @param name:             descriptive name for the new key
     * @return GPG key as {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#create-a-gpg-key-for-the-authenticated-user">
     * Create a GPG key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/gpg_keys")
    public GitHubGPGKey createGPGKey(String armoredPublicKey, String name) throws IOException {
        return createGPGKey(armoredPublicKey, name, LIBRARY_OBJECT);
    }

    /**
     * Method to add a GPG key to the authenticated user's GitHub account. Requires that you are authenticated via Basic
     * Auth, or OAuth with at least {@code "write:gpg_key"} scope
     *
     * @param armoredPublicKey: GPG key in ASCII-armored format
     * @param name:             descriptive name for the new key
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return GPG key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#create-a-gpg-key-for-the-authenticated-user">
     * Create a GPG key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/gpg_keys")
    public <T> T createGPGKey(String armoredPublicKey, String name, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("armored_public_key", armoredPublicKey);
        if (name != null)
            payload.addParam("name", name);
        return returnGPGKey(sendPostRequest(USER_GPG_KEYS_PATH, payload), format);
    }

    /**
     * Method to view extended details for a single GPG key. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope
     *
     * @param GPGKeyId: the unique identifier of the GPG key
     * @return GPG key as {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-#get-a-gpg-key-for-the-authenticated-user">
     * Get a GPG key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/gpg_keys/{gpg_key_id}")
    public GitHubGPGKey getGPGKey(long GPGKeyId) throws IOException {
        return getGPGKey(GPGKeyId, LIBRARY_OBJECT);
    }

    /**
     * Method to view extended details for a single GPG key. Requires that you are authenticated via Basic Auth or via
     * OAuth with at least {@code "read:gpg_key"} scope
     *
     * @param GPGKeyId: the unique identifier of the GPG key
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return GPG key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-#get-a-gpg-key-for-the-authenticated-user">
     * Get a GPG key for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/gpg_keys/{gpg_key_id}")
    public <T> T getGPGKey(long GPGKeyId, ReturnFormat format) throws IOException {
        return returnGPGKey(sendGetRequest(USER_GPG_KEYS_PATH + "/" + GPGKeyId), format);
    }

    /**
     * Method to create an GPG key
     *
     * @param GPGKeyResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return GPG key as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGPGKey(String GPGKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(GPGKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubGPGKey(new JSONObject(GPGKeyResponse));
            default:
                return (T) GPGKeyResponse;
        }
    }

    /**
     * Method to remove a GPG key from the authenticated user's GitHub account. Requires that you are authenticated via
     * Basic Auth or via OAuth with at least {@code "admin:gpg_key"} scope
     *
     * @param gpgKey: the key to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#delete-a-gpg-key-for-the-authenticated-user">
     * Delete a GPG key for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/gpg_keys/{gpg_key_id}")
    public boolean deleteGPGKey(GitHubGPGKey gpgKey) {
        return deleteGPGKey(gpgKey.getId());
    }

    /**
     * Method to remove a GPG key from the authenticated user's GitHub account. Requires that you are authenticated via
     * Basic Auth or via OAuth with at least {@code "admin:gpg_key"} scope
     *
     * @param GPGKeyId: the unique identifier of the GPG key
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#delete-a-gpg-key-for-the-authenticated-user">
     * Delete a GPG key for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/gpg_keys/{gpg_key_id}")
    public boolean deleteGPGKey(long GPGKeyId) {
        try {
            sendDeleteRequest(USER_GPG_KEYS_PATH + "/" + GPGKeyId);
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
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
     *
     * @param user: the user from fetch the list
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public ArrayList<GitHubGPGKey> getUserGPGKeys(User user) throws IOException {
        return getUserGPGKeys(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public <T> T getUserGPGKeys(User user, ReturnFormat format) throws IOException {
        return getUserGPGKeys(user.getName(), format);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public ArrayList<GitHubGPGKey> getUserGPGKeys(String username) throws IOException {
        return getUserGPGKeys(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public <T> T getUserGPGKeys(String username, ReturnFormat format) throws IOException {
        return returnGPGKeys(sendGetRequest(USERS_PATH + username + GPG_KEYS_PATH), format);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
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
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public ArrayList<GitHubGPGKey> getUserGPGKeys(User user, Params queryParams) throws IOException {
        return getUserGPGKeys(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
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
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public <T> T getUserGPGKeys(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserGPGKeys(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
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
     * @return GPG keys list as {@link ArrayList} of {@link GitHubGPGKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public ArrayList<GitHubGPGKey> getUserGPGKeys(String username, Params queryParams) throws IOException {
        return getUserGPGKeys(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GPG keys for a user. This information is accessible by anyone
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
     * @return GPG keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
     * List GPG keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/gpg_keys")
    public <T> T getUserGPGKeys(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnGPGKeys(sendGetRequest(USERS_PATH + username + GPG_KEYS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an GPG keys list
     *
     * @param GPGKeysResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return GPG keys list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGPGKeys(String GPGKeysResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(GPGKeysResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubGPGKey> keys = new ArrayList<>();
                JSONArray jKeys = new JSONArray(GPGKeysResponse);
                for (int j = 0; j < jKeys.length(); j++)
                    keys.add(new GitHubGPGKey(jKeys.getJSONObject(j)));
                return (T) keys;
            default:
                return (T) GPGKeysResponse;
        }
    }

}
