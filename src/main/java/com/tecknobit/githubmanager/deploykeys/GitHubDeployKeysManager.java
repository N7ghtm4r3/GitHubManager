package com.tecknobit.githubmanager.deploykeys;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.deploykeys.records.DeployKey;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubDeployKeysManager} class is useful to manage all GitHub's dependency deploy keys endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys">
 * Deploy keys</a>
 * @see GitHubManager
 **/
public class GitHubDeployKeysManager extends GitHubManager {

    /**
     * {@code KEYS_PATH} constant for {@code "/keys"} path
     **/
    public static final String KEYS_PATH = "/keys";

    /**
     * Constructor to init a {@link GitHubDeployKeysManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDeployKeysManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDeployKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDeployKeysManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDeployKeysManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDeployKeysManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeployKeysManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDeployKeysManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDeployKeysManager} <br>
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
    public GitHubDeployKeysManager() {
        super();
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param repository: the repository from fetch the list
     * @return deployment keys as {@link ArrayList} of {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public ArrayList<DeployKey> getDeployKeys(Repository repository) throws IOException {
        return getDeployKeys(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public <T> T getDeployKeys(Repository repository, ReturnFormat format) throws IOException {
        return getDeployKeys(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return deployment keys as {@link ArrayList} of {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public ArrayList<DeployKey> getDeployKeys(String owner, String repo) throws IOException {
        return getDeployKeys(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return deployment keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public <T> T getDeployKeys(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDeployKeys(sendGetRequest(REPOS_PATH + owner + "/" + repo + KEYS_PATH), format);
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return deployment keys as {@link ArrayList} of {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public ArrayList<DeployKey> getDeployKeys(Repository repository, Params queryParams) throws IOException {
        return getDeployKeys(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment keys
     *
     * @param repository:  the repository from fetch the list
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
     * @return deployment keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public <T> T getDeployKeys(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getDeployKeys(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the deployment keys
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
     * @return deployment keys as {@link ArrayList} of {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public ArrayList<DeployKey> getDeployKeys(String owner, String repo, Params queryParams) throws IOException {
        return getDeployKeys(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the deployment keys
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
     * @return deployment keys list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#list-deploy-keys">
     * List deploy keys</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys")
    public <T> T getDeployKeys(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnDeployKeys(sendGetRequest(REPOS_PATH + owner + "/" + repo + KEYS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a deployment keys
     *
     * @param deployKeysResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return deployment keys list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeployKeys(String deployKeysResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(deployKeysResponse);
            case LIBRARY_OBJECT:
                ArrayList<DeployKey> deployKeys = new ArrayList<>();
                JSONArray jDeployKeys = new JSONArray(deployKeysResponse);
                for (int j = 0; j < jDeployKeys.length(); j++)
                    deployKeys.add(new DeployKey(jDeployKeys.getJSONObject(j)));
                return (T) deployKeys;
            default:
                return (T) deployKeysResponse;
        }
    }

    /**
     * Method to create a deployment key
     *
     * @param repository: the repository where create the key
     * @param key:        the contents of the key (OpenSSH key type)
     * @param readOnly:   if {@code "true"}, the key will only be able to read repository contents.
     *                    Otherwise, the key will be able to read and write.
     *                    Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                    or a collaborator on a personal repository
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public DeployKey createDeployKey(Repository repository, String key, boolean readOnly) throws IOException {
        return createDeployKey(repository.getOwner().getLogin(), repository.getName(), key, readOnly, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment key
     *
     * @param repository: the repository where create the key
     * @param key:        the contents of the key (OpenSSH key type)
     * @param readOnly:   if {@code "true"}, the key will only be able to read repository contents.
     *                    Otherwise, the key will be able to read and write.
     *                    Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                    or a collaborator on a personal repository
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public <T> T createDeployKey(Repository repository, String key, boolean readOnly, ReturnFormat format) throws IOException {
        return createDeployKey(repository.getOwner().getLogin(), repository.getName(), key, readOnly, format);
    }

    /**
     * Method to create a deployment key
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param key:      the contents of the key (OpenSSH key type)
     * @param readOnly: if {@code "true"}, the key will only be able to read repository contents.
     *                  Otherwise, the key will be able to read and write.
     *                  Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                  or a collaborator on a personal repository
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public DeployKey createDeployKey(String owner, String repo, String key, boolean readOnly) throws IOException {
        return createDeployKey(owner, repo, key, readOnly, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment key
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param key:      the contents of the key (OpenSSH key type)
     * @param readOnly: if {@code "true"}, the key will only be able to read repository contents.
     *                  Otherwise, the key will be able to read and write.
     *                  Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                  or a collaborator on a personal repository
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public <T> T createDeployKey(String owner, String repo, String key, boolean readOnly,
                                 ReturnFormat format) throws IOException {
        return createDeployKey(owner, repo, key, readOnly, null, format);
    }

    /**
     * Method to create a deployment key
     *
     * @param repository: the repository where create the key
     * @param key:        the contents of the key (OpenSSH key type)
     * @param readOnly:   if {@code "true"}, the key will only be able to read repository contents.
     *                    Otherwise, the key will be able to read and write.
     *                    Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                    or a collaborator on a personal repository
     * @param title:      a name for the key
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public DeployKey createDeployKey(Repository repository, String key, boolean readOnly, String title) throws IOException {
        return createDeployKey(repository.getOwner().getLogin(), repository.getName(), key, readOnly, title,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment key
     *
     * @param repository: the repository where create the key
     * @param key:        the contents of the key (OpenSSH key type)
     * @param readOnly:   if {@code "true"}, the key will only be able to read repository contents.
     *                    Otherwise, the key will be able to read and write.
     *                    Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                    or a collaborator on a personal repository
     * @param title:      a name for the key
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public <T> T createDeployKey(Repository repository, String key, boolean readOnly, String title,
                                 ReturnFormat format) throws IOException {
        return createDeployKey(repository.getOwner().getLogin(), repository.getName(), key, readOnly, title, format);
    }

    /**
     * Method to create a deployment key
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param key:      the contents of the key (OpenSSH key type)
     * @param readOnly: if {@code "true"}, the key will only be able to read repository contents.
     *                  Otherwise, the key will be able to read and write.
     *                  Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                  or a collaborator on a personal repository
     * @param title:    a name for the key
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public DeployKey createDeployKey(String owner, String repo, String key, boolean readOnly, String title) throws IOException {
        return createDeployKey(owner, repo, key, readOnly, title, LIBRARY_OBJECT);
    }

    /**
     * Method to create a deployment key
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param key:      the contents of the key (OpenSSH key type)
     * @param readOnly: if {@code "true"}, the key will only be able to read repository contents.
     *                  Otherwise, the key will be able to read and write.
     *                  Deploy keys with write access can perform the same actions as an organization member with admin access,
     *                  or a collaborator on a personal repository
     * @param title:    a name for the key
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#create-a-deploy-key">
     * Create a deploy key</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/keys")
    public <T> T createDeployKey(String owner, String repo, String key, boolean readOnly, String title,
                                 ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("key", key);
        payload.addParam("read_only", readOnly);
        payload.addParam("title", title);
        return returnDeployKey(sendPostRequest(REPOS_PATH + owner + "/" + repo + KEYS_PATH, payload), format);
    }

    /**
     * Method to get a deployment key
     *
     * @param repository: the repository from fetch the key
     * @param keyId:      the unique identifier of the key
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#get-a-deploy-key">
     * Get a deploy key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public DeployKey getDeployKey(Repository repository, long keyId) throws IOException {
        return getDeployKey(repository.getOwner().getLogin(), repository.getName(), keyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment key
     *
     * @param repository: the repository from fetch the key
     * @param keyId:      the unique identifier of the key
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#get-a-deploy-key">
     * Get a deploy key</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public <T> T getDeployKey(Repository repository, long keyId, ReturnFormat format) throws IOException {
        return getDeployKey(repository.getOwner().getLogin(), repository.getName(), keyId, format);
    }

    /**
     * Method to get a deployment key
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param keyId: the unique identifier of the key
     * @return deployment key as {@link DeployKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#get-a-deploy-key">
     * Get a deploy key</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public DeployKey getDeployKey(String owner, String repo, long keyId) throws IOException {
        return getDeployKey(owner, repo, keyId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a deployment key
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param keyId:  the unique identifier of the key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#get-a-deploy-key">
     * Get a deploy key</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public <T> T getDeployKey(String owner, String repo, long keyId, ReturnFormat format) throws IOException {
        return returnDeployKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + KEYS_PATH + "/" + keyId), format);
    }

    /**
     * Method to create a deployment key
     *
     * @param deployKeyResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return deployment key as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeployKey(String deployKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(deployKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new DeployKey(new JSONObject(deployKeyResponse));
            default:
                return (T) deployKeyResponse;
        }
    }

    /**
     * Method to delete a deployment key
     *
     * @param repository: the repository where delete the key
     * @param key:        the key to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#delete-a-deploy-key">
     * Delete a deploy key</a>
     * @implNote Deploy keys are immutable. If you need to update a key, remove the key and create a new one instead
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public boolean deleteDeployKey(Repository repository, DeployKey key) {
        return deleteDeployKey(repository.getOwner().getLogin(), repository.getName(), key.getId());
    }

    /**
     * Method to delete a deployment key
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param key:   the key to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#delete-a-deploy-key">
     * Delete a deploy key</a>
     * @implNote Deploy keys are immutable. If you need to update a key, remove the key and create a new one instead
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public boolean deleteDeployKey(String owner, String repo, DeployKey key) {
        return deleteDeployKey(owner, repo, key.getId());
    }

    /**
     * Method to delete a deployment key
     *
     * @param repository: the repository where delete the key
     * @param keyId:      the unique identifier of the key
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#delete-a-deploy-key">
     * Delete a deploy key</a>
     * @implNote Deploy keys are immutable. If you need to update a key, remove the key and create a new one instead
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public boolean deleteDeployKey(Repository repository, long keyId) {
        return deleteDeployKey(repository.getOwner().getLogin(), repository.getName(), keyId);
    }

    /**
     * Method to delete a deployment key
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param keyId: the unique identifier of the key
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deploy-keys#delete-a-deploy-key">
     * Delete a deploy key</a>
     * @implNote Deploy keys are immutable. If you need to update a key, remove the key and create a new one instead
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/keys/{key_id}")
    public boolean deleteDeployKey(String owner, String repo, long keyId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + KEYS_PATH + "/" + keyId);
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
