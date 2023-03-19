package com.tecknobit.githubmanager.users.sshsigningkeys;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.gitsshkeys.records.GitHubSSHKey;
import com.tecknobit.githubmanager.users.sshsigningkeys.records.GitHubSSHSigningKey;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubSSHSigningKeysManager} class is useful to manage all GitHub's SSH signing keys endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys">
 * SSH signing keys</a>
 * @see GitHubManager
 **/
public class GitHubSSHSigningKeysManager extends GitHubManager {

    /**
     * {@code SSH_SIGNING_KEYS_PATH} constant for {@code "ssh_signing_keys"} path
     **/
    public static final String SSH_SIGNING_KEYS_PATH = "/ssh_signing_keys";

    /**
     * {@code USER_PATH} constant for {@code "user/ssh_signing_keys"} path
     **/
    public static final String USE_SSH_SIGNING_KEYS_PATH = USER_PATH + SSH_SIGNING_KEYS_PATH;

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSSHSigningKeysManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSSHSigningKeysManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSSHSigningKeysManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSSHSigningKeysManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager} <br>
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
    public GitHubSSHSigningKeysManager() {
        super();
    }

    /**
     * Method to get the list of the SSH signing keys for the authenticated user's GitHub account. You must authenticate
     * with Basic Authentication, or you must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope.
     * For more information, see Understanding scopes for OAuth apps. <br>
     * No-any params required
     *
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-the-authenticated-user">
     * List SSH signing keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getPublicSSHSigningKeys() throws IOException {
        return getPublicSSHSigningKeys(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for the authenticated user's GitHub account. You must authenticate
     * with Basic Authentication, or you must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope.
     * For more information, see Understanding scopes for OAuth apps.
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-the-authenticated-user">
     * List SSH signing keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getPublicSSHSigningKeys(ReturnFormat format) throws IOException {
        return returnSSHSigningKeys(sendGetRequest(USE_SSH_SIGNING_KEYS_PATH), format);
    }

    /**
     * Method to get the list of the SSH signing keys for the authenticated user's GitHub account. You must authenticate
     * with Basic Authentication, or you must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope.
     * For more information, see Understanding scopes for OAuth apps.
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
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-the-authenticated-user">
     * List SSH signing keys for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getPublicSSHSigningKeys(Params queryParams) throws IOException {
        return getPublicSSHSigningKeys(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for the authenticated user's GitHub account. You must authenticate
     * with Basic Authentication, or you must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope.
     * For more information, see Understanding scopes for OAuth apps.
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
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#list-ssh-signing-keys-for-the-authenticated-user">
     * List SSH signing keys for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getPublicSSHSigningKeys(Params queryParams, ReturnFormat format) throws IOException {
        return returnSSHSigningKeys(sendGetRequest(USE_SSH_SIGNING_KEYS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to creates an SSH signing key for the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "write:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#create-a-ssh-signing-key-for-the-authenticated-user">
     * Create a SSH signing key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/ssh_signing_keys")
    public GitHubSSHSigningKey createPublicSSHSigningKey(String key) throws IOException {
        return createPublicSSHSigningKey(key, LIBRARY_OBJECT);
    }

    /**
     * Method to creates an SSH signing key for the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "write:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
     *
     * @param key:    the public SSH key to add to your GitHub account
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH signing key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#create-a-ssh-signing-key-for-the-authenticated-user">
     * Create a SSH signing key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/ssh_signing_keys")
    public <T> T createPublicSSHSigningKey(String key, ReturnFormat format) throws IOException {
        return createPublicSSHSigningKey(key, null, format);
    }

    /**
     * Method to creates an SSH signing key for the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "write:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#create-a-ssh-signing-key-for-the-authenticated-user">
     * Create a SSH signing key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/ssh_signing_keys")
    public GitHubSSHSigningKey createPublicSSHSigningKey(String key, String title) throws IOException {
        return createPublicSSHSigningKey(key, title, LIBRARY_OBJECT);
    }

    /**
     * Method to creates an SSH signing key for the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "write:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
     *
     * @param key:    the public SSH key to add to your GitHub account
     * @param title:  descriptive name for the new key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH signing key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#create-a-ssh-signing-key-for-the-authenticated-user">
     * Create a SSH signing key for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/ssh_signing_keys")
    public <T> T createPublicSSHSigningKey(String key, String title, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("key", key);
        if (title != null)
            payload.addParam("title", title);
        return returnSSHSigningKey(sendPostRequest(USE_SSH_SIGNING_KEYS_PATH, payload), format);
    }

    /**
     * Method to get extended details for an SSH signing key. You must authenticate with Basic Authentication, or you
     * must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope. For more information, see
     * "Understanding scopes for OAuth apps."
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#get-an-ssh-signing-key-for-the-authenticated-user">
     * Get an SSH signing key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys/{ssh_signing_key_id}")
    public GitHubSSHSigningKey getPublicSSHSigningKey(long keyId) throws IOException {
        return getPublicSSHSigningKey(keyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get extended details for an SSH signing key. You must authenticate with Basic Authentication, or you
     * must authenticate with OAuth with at least {@code "read:ssh_signing_key"} scope. For more information, see
     * "Understanding scopes for OAuth apps."
     *
     * @param keyId:  the unique identifier of the key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH signing key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#get-an-ssh-signing-key-for-the-authenticated-user">
     * Get an SSH signing key for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/ssh_signing_keys/{ssh_signing_key_id}")
    public <T> T getPublicSSHSigningKey(long keyId, ReturnFormat format) throws IOException {
        return returnSSHSigningKey(sendGetRequest(USE_SSH_SIGNING_KEYS_PATH + "/" + keyId), format);
    }

    /**
     * Method to create an SSH signing key
     *
     * @param SSHSigningKeyResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return SSH signing key as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSSHSigningKey(String SSHSigningKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(SSHSigningKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubSSHSigningKey(new JSONObject(SSHSigningKeyResponse));
            default:
                return (T) SSHSigningKeyResponse;
        }
    }

    /**
     * Method to delete an SSH signing key from the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "admin:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
     *
     * @param key: the key to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#delete-an-ssh-signing-key-for-the-authenticated-user">
     * Delete an SSH signing key for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/ssh_signing_keys/{ssh_signing_key_id}")
    public boolean deletePublicSSHSigningKey(GitHubSSHSigningKey key) {
        return deletePublicSSHSigningKey(key.getId());
    }

    /**
     * Method to delete an SSH signing key from the authenticated user's GitHub account. You must authenticate with
     * Basic Authentication, or you must authenticate with OAuth with at least {@code "admin:ssh_signing_key"} scope.
     * For more information, see "Understanding scopes for OAuth apps."
     *
     * @param keyId: the unique identifier of the key
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys#delete-an-ssh-signing-key-for-the-authenticated-user">
     * Delete an SSH signing key for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/ssh_signing_keys/{ssh_signing_key_id}")
    public boolean deletePublicSSHSigningKey(long keyId) {
        try {
            sendDeleteRequest(USE_SSH_SIGNING_KEYS_PATH + "/" + keyId);
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
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
     *
     * @param user: the user from fetch the list
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getUserPublicSSHSigningKeys(User user) throws IOException {
        return getUserPublicSSHSigningKeys(user.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
     *
     * @param user:   the user from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getUserPublicSSHSigningKeys(User user, ReturnFormat format) throws IOException {
        return getUserPublicSSHSigningKeys(user.getName(), format);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getUserPublicSSHSigningKeys(String username) throws IOException {
        return getUserPublicSSHSigningKeys(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getUserPublicSSHSigningKeys(String username, ReturnFormat format) throws IOException {
        return returnSSHSigningKeys(sendGetRequest(USERS_PATH + username + SSH_SIGNING_KEYS_PATH), format);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
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
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getUserPublicSSHSigningKeys(User user, Params queryParams) throws IOException {
        return getUserPublicSSHSigningKeys(user.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
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
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getUserPublicSSHSigningKeys(User user, Params queryParams, ReturnFormat format) throws IOException {
        return getUserPublicSSHSigningKeys(user.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
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
     * @return SSH keys signing list as {@link ArrayList} of {@link GitHubSSHSigningKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public ArrayList<GitHubSSHSigningKey> getUserPublicSSHSigningKeys(String username, Params queryParams) throws IOException {
        return getUserPublicSSHSigningKeys(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the SSH signing keys for a user. This operation is accessible by anyone
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
     * @return SSH signing keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/ssh-signing-keys?apiVersion=2022-11-28#list-ssh-signing-keys-for-a-user">
     * List SSH signing keys for a user</a>
     **/
    @RequestPath(method = GET, path = "/user/ssh_signing_keys")
    public <T> T getUserPublicSSHSigningKeys(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnSSHSigningKeys(sendGetRequest(USERS_PATH + username + SSH_SIGNING_KEYS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an SSH signing keys list
     *
     * @param SSHSigningKeysResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return SSH signing keys list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSSHSigningKeys(String SSHSigningKeysResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(SSHSigningKeysResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubSSHSigningKey> keys = new ArrayList<>();
                JSONArray jKeys = new JSONArray(SSHSigningKeysResponse);
                for (int j = 0; j < jKeys.length(); j++)
                    keys.add(new GitHubSSHSigningKey(jKeys.getJSONObject(j)));
                return (T) keys;
            default:
                return (T) SSHSigningKeysResponse;
        }
    }

}
