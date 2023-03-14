package com.tecknobit.githubmanager.codespaces.repositorysecrets;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey;
import com.tecknobit.githubmanager.actions.secrets.records.Secret;
import com.tecknobit.githubmanager.actions.secrets.records.SecretsList;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey.returnPublicKey;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.createSecretPayload;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.returnSecret;
import static com.tecknobit.githubmanager.actions.secrets.records.SecretsList.returnSecretsList;

/**
 * The {@code GitHubRepositorySecretsManager} class is useful to manage all GitHub's repository secrets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets">
 * Codespaces repository secrets</a>
 * @see GitHubManager
 **/
public class GitHubRepositorySecretsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubRepositorySecretsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepositorySecretsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepositorySecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepositorySecretsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepositorySecretsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepositorySecretsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositorySecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepositorySecretsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositorySecretsManager} <br>
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
    public GitHubRepositorySecretsManager() {
        super();
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @return secrets list as {@link SecretsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public SecretsList getRepositorySecrets(Repository repository) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secrets list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public <T> T getRepositorySecrets(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return secrets list as {@link SecretsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public SecretsList getRepositorySecrets(String owner, String repo) throws IOException {
        return getRepositorySecrets(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secrets list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public <T> T getRepositorySecrets(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH),
                format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
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
     * @return secrets list as {@link SecretsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public SecretsList getRepositorySecrets(Repository repository, Params queryParams) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
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
     * @return secrets list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public <T> T getRepositorySecrets(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
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
     * @return secrets list as {@link SecretsList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public SecretsList getRepositorySecrets(String owner, String repo, Params queryParams) throws IOException {
        return getRepositorySecrets(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
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
     * @return secrets list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets")
    public <T> T getRepositorySecrets(String owner, String repo, Params queryParams,
                                      ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. <br>
     * You need to encrypt a secret before you can create or update secrets. <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private you must use an access token with the repo scope. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the key
     * @return public key as {@link GitHubPublicKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/public-key")
    public GitHubPublicKey getRepositoryPublicKey(Repository repository) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. <br>
     * You need to encrypt a secret before you can create or update secrets. <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private you must use an access token with the repo scope. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the key
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return public key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/public-key")
    public <T> T getRepositoryPublicKey(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. <br>
     * You need to encrypt a secret before you can create or update secrets. <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private you must use an access token with the repo scope. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return public key as {@link GitHubPublicKey} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/public-key")
    public GitHubPublicKey getRepositoryPublicKey(String owner, String repo) throws IOException {
        return getRepositoryPublicKey(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. <br>
     * You need to encrypt a secret before you can create or update secrets. <br>
     * Anyone with read access to the repository can use this endpoint. <br>
     * If the repository is private you must use an access token with the repo scope. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return public key as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/public-key")
    public <T> T getRepositoryPublicKey(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH
                + PUBLIC_KEY_PATH), format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the secret
     * @param secretName: the name of the secret
     * @return secret as {@link Secret} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public Secret getRepositorySecret(Repository repository, String secretName) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the secret
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public <T> T getRepositorySecret(Repository repository, String secretName, ReturnFormat format) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return secret as {@link Secret} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public Secret getRepositorySecret(String owner, String repo, String secretName) throws IOException {
        return getRepositorySecret(owner, repo, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public <T> T getRepositorySecret(String owner, String repo, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH + "/"
                + secretName), format);
    }

    /**
     * Method to create or update a repository secret with an encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository:  repository where create or update the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean workWithRepositorySecret(Repository repository, String secretName, String secretValue) {
        return workWithRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue);
    }

    /**
     * Method to create or update a repository secret with an encrypted value. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean workWithRepositorySecret(String owner, String repo, String secretName, String secretValue) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH + "/" + secretName,
                    createSecretPayload(secretValue, getRepositoryPublicKey(owner, repo), null));
            if (apiRequest.getResponseStatusCode() != 201) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException | SodiumException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: repository where create or update the secret
     * @param secret:     the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean deleteRepositorySecret(Repository repository, Secret secret) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secret.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param repository: repository where create or update the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean deleteRepositorySecret(Repository repository, String secretName) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName);
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean deleteRepositorySecret(String owner, String repo, Secret secret) {
        return deleteRepositorySecret(owner, repo, secret.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_secrets"} repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/codespaces/secrets/{secret_name}")
    public boolean deleteRepositorySecret(String owner, String repo, String secretName) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_SECRETS_PATH + "/" + secretName);
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

