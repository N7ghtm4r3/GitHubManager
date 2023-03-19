package com.tecknobit.githubmanager.users.gitsshkeys;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.gitsshkeys.records.GitHubSSHKey;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubSSHKeysManager} class is useful to manage all GitHub's git SSH keys endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys">
 * Git SSH Keys</a>
 * @see GitHubManager
 **/
public class GitHubSSHKeysManager extends GitHubManager {

    /**
     * {@code KEYS_PATH} constant for {@code "keys"} path
     **/
    public static final String KEYS_PATH = "/keys";

    /**
     * {@code USER_PATH} constant for {@code "user/keys"} path
     **/
    public static final String USE_KEYS_PATH = USER_PATH + KEYS_PATH;

    /**
     * Constructor to init a {@link GitHubSSHKeysManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSSHKeysManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSSHKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSSHKeysManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSSHKeysManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSSHKeysManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSSHKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSSHKeysManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSSHKeysManager} <br>
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
    public GitHubSSHKeysManager() {
        super();
    }

    /**
     * Method to get the list of the public SSH keys for the authenticated user's GitHub account. Requires that you are
     * authenticated via Basic Auth or via OAuth with at least {@code "read:public_key"} scope <br>
     * No-any params required
     *
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-ssh-keys-for-the-authenticated-user">
     * List public SSH keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/keys")
    public ArrayList<GitHubSSHKey> getPublicSSHKeys() throws IOException {
        return getPublicSSHKeys(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public SSH keys for the authenticated user's GitHub account. Requires that you are
     * authenticated via Basic Auth or via OAuth with at least {@code "read:public_key"} scope
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-ssh-keys-for-the-authenticated-user">
     * List public SSH keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/keys")
    public <T> T getPublicSSHKeys(ReturnFormat format) throws IOException {
        return returnSSHKeys(sendGetRequest(USE_KEYS_PATH), format);
    }

    /**
     * Method to get the list of the public SSH keys for the authenticated user's GitHub account. Requires that you are
     * authenticated via Basic Auth or via OAuth with at least {@code "read:public_key"} scope
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
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-ssh-keys-for-the-authenticated-user">
     * List public SSH keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/keys")
    public ArrayList<GitHubSSHKey> getPublicSSHKeys(Params queryParams) throws IOException {
        return getPublicSSHKeys(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public SSH keys for the authenticated user's GitHub account. Requires that you are
     * authenticated via Basic Auth or via OAuth with at least {@code "read:public_key"} scope
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
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-ssh-keys-for-the-authenticated-user">
     * List public SSH keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/keys")
    public <T> T getPublicSSHKeys(Params queryParams, ReturnFormat format) throws IOException {
        return returnSSHKeys(sendGetRequest(USE_KEYS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to add a public SSH key to the authenticated user's GitHub account. Requires that you are authenticated
     * via Basic Auth, or OAuth with at least {@code "write:public_key"} scope.
     *
     * @param key: the public SSH key to add to your GitHub account
     * @return SSH key as {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Create a public SSH key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/keys")
    public GitHubSSHKey createPublicSSHKey(String key) throws IOException {
        return createPublicSSHKey(key, LIBRARY_OBJECT);
    }

    /**
     * Method to add a public SSH key to the authenticated user's GitHub account. Requires that you are authenticated
     * via Basic Auth, or OAuth with at least {@code "write:public_key"} scope.
     *
     * @param key:    the public SSH key to add to your GitHub account
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Create a public SSH key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/keys")
    public <T> T createPublicSSHKey(String key, ReturnFormat format) throws IOException {
        return createPublicSSHKey(key, null, format);
    }

    /**
     * Method to add a public SSH key to the authenticated user's GitHub account. Requires that you are authenticated
     * via Basic Auth, or OAuth with at least {@code "write:public_key"} scope.
     *
     * @param key:   the public SSH key to add to your GitHub account
     * @param title: descriptive name for the new key
     * @return SSH key as {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Create a public SSH key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/keys")
    public GitHubSSHKey createPublicSSHKey(String key, String title) throws IOException {
        return createPublicSSHKey(key, title, LIBRARY_OBJECT);
    }

    /**
     * Method to add a public SSH key to the authenticated user's GitHub account. Requires that you are authenticated
     * via Basic Auth, or OAuth with at least {@code "write:public_key"} scope.
     *
     * @param key:    the public SSH key to add to your GitHub account
     * @param title:  descriptive name for the new key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Create a public SSH key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/keys")
    public <T> T createPublicSSHKey(String key, String title, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("key", key);
        if (title != null)
            payload.addParam("title", title);
        return returnSSHKey(sendPostRequest(USE_KEYS_PATH, payload), format);
    }

    /**
     * Method to view extended details for a single public SSH key. Requires that you are authenticated via Basic Auth
     * or via OAuth with at least {@code "read:public_key"} scope.
     *
     * @param keyId: the unique identifier of the key
     * @return SSH key as {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Get a public SSH key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/keys/{key_id}")
    public GitHubSSHKey getPublicSSHKey(long keyId) throws IOException {
        return getPublicSSHKey(keyId, LIBRARY_OBJECT);
    }

    /**
     * Method to view extended details for a single public SSH key. Requires that you are authenticated via Basic Auth
     * or via OAuth with at least {@code "read:public_key"} scope.
     *
     * @param keyId:  the unique identifier of the key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#create-a-public-ssh-key-for-the-authenticated-user">
     * Get a public SSH key for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/keys/{key_id}")
    public <T> T getPublicSSHKey(long keyId, ReturnFormat format) throws IOException {
        return returnSSHKey(sendGetRequest(USE_KEYS_PATH + "/" + keyId), format);
    }

    /**
     * Method to create an SSH key
     *
     * @param SSHKeyResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return SSH key as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSSHKey(String SSHKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(SSHKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubSSHKey(new JSONObject(SSHKeyResponse));
            default:
                return (T) SSHKeyResponse;
        }
    }

    /**
     * Method to remove a public SSH key from the authenticated user's GitHub account.
     * Requires that you are authenticated via Basic Auth or via OAuth with at least {@code "admin:gpg_key"} scope
     *
     * @param key: the key to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#delete-a-public-ssh-key-for-the-authenticated-user">
     * Delete a public SSH key for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/keys/{key_id}")
    public boolean deletePublicSSHKey(GitHubSSHKey key) {
        return deletePublicSSHKey(key.getId());
    }

    /**
     * Method to remove a public SSH key from the authenticated user's GitHub account.
     * Requires that you are authenticated via Basic Auth or via OAuth with at least {@code "admin:gpg_key"} scope
     *
     * @param keyId: the unique identifier of the key
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#delete-a-public-ssh-key-for-the-authenticated-user">
     * Delete a public SSH key for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/keys/{key_id}")
    public boolean deletePublicSSHKey(long keyId) {
        try {
            sendDeleteRequest(USE_KEYS_PATH + "/" + keyId);
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
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
     *
     * @param user: the user from fetch the list
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public ArrayList<GitHubSSHKey> getUserPublicSSHKeys(User user) throws IOException {
        return getUserPublicSSHKeys(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public <T> T getUserPublicSSHKeys(User user, ReturnFormat format) throws IOException {
        return getUserPublicSSHKeys(user.getName(), format);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public ArrayList<GitHubSSHKey> getUserPublicSSHKeys(String username) throws IOException {
        return getUserPublicSSHKeys(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public <T> T getUserPublicSSHKeys(String username, ReturnFormat format) throws IOException {
        return returnSSHKeys(sendGetRequest(USERS_PATH + username + KEYS_PATH), format);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
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
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public ArrayList<GitHubSSHKey> getUserPublicSSHKeys(User user, Params queryParams) throws IOException {
        return getUserPublicSSHKeys(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
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
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public <T> T getUserPublicSSHKeys(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserPublicSSHKeys(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
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
     * @return SSH keys list as {@link ArrayList} of {@link GitHubSSHKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public ArrayList<GitHubSSHKey> getUserPublicSSHKeys(String username, Params queryParams) throws IOException {
        return getUserPublicSSHKeys(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the verified public SSH keys for a user. This is accessible by anyone
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
     * @return SSH keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/keys#list-public-keys-for-a-user">
     * List public keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/keys")
    public <T> T getUserPublicSSHKeys(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnSSHKeys(sendGetRequest(USERS_PATH + username + KEYS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create an SSH keys list
     *
     * @param SSHKeysResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return SSH keys list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSSHKeys(String SSHKeysResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(SSHKeysResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubSSHKey> keys = new ArrayList<>();
                JSONArray jKeys = new JSONArray(SSHKeysResponse);
                for (int j = 0; j < jKeys.length(); j++)
                    keys.add(new GitHubSSHKey(jKeys.getJSONObject(j)));
                return (T) keys;
            default:
                return (T) SSHKeysResponse;
        }
    }

}
