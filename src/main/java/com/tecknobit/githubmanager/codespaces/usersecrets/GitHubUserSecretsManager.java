package com.tecknobit.githubmanager.codespaces.usersecrets;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey;
import com.tecknobit.githubmanager.actions.secrets.records.Secret;
import com.tecknobit.githubmanager.actions.secrets.records.SecretsList;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey.returnPublicKey;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.createSecretPayload;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.returnSecret;
import static com.tecknobit.githubmanager.actions.secrets.records.SecretsList.returnSecretsList;
import static com.tecknobit.githubmanager.records.repository.RepositoriesList.returnRepositoriesList;

/**
 * The {@code GitHubUserSecretsManager} class is useful to manage all GitHub's user secrets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets">
 * Codespaces user secrets</a>
 * @see GitHubManager
 **/
public class GitHubUserSecretsManager extends GitHubManager {

    /**
     * {@code USER_CODESPACES_SECRETS_PATH} constant for {@code "user/codespaces/secrets"} path
     **/
    public static final String USER_CODESPACES_SECRETS_PATH = USER_CODESPACES_PATH + SECRETS_PATH;

    /**
     * Constructor to init a {@link GitHubUserSecretsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubUserSecretsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubUserSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubUserSecretsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubUserSecretsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubUserSecretsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUserSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubUserSecretsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUserSecretsManager} <br>
     * Any params required
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
    public GitHubUserSecretsManager() {
        super();
    }

    /**
     * Method to get the list all secrets available for a user's Codespaces without revealing their encrypted values. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     * Any params required
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/secrets")
    public SecretsList getUserSecrets() throws IOException {
        return getUserSecrets(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all secrets available for a user's Codespaces without revealing their encrypted values. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/secrets")
    public <T> T getUserSecrets(ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(USER_CODESPACES_SECRETS_PATH), format);
    }

    /**
     * Method to get the list all secrets available for a user's Codespaces without revealing their encrypted values. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/secrets")
    public SecretsList getUserSecrets(Params queryParams) throws IOException {
        return getUserSecrets(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all secrets available for a user's Codespaces without revealing their encrypted values. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/secrets")
    public <T> T getUserSecrets(Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(USER_CODESPACES_SECRETS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can
     * create or update secrets. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     * Any params required
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#get-public-key-for-the-authenticated-user">
     * Get public key for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/secrets/public-key")
    public GitHubPublicKey getUserPublicKey() throws IOException {
        return getUserPublicKey(LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can
     * create or update secrets. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#get-public-key-for-the-authenticated-user">
     * Get public key for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/secrets/public-key")
    public <T> T getUserPublicKey(ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(USER_CODESPACES_SECRETS_PATH + PUBLIC_KEY_PATH), format);
    }

    /**
     * Method to get a secret available to a user's codespaces without revealing its encrypted value <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#get-a-secret-for-the-authenticated-user">
     * Get a secret for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}")
    public Secret getUserSecret(String secretName) throws IOException {
        return getUserSecret(secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a secret available to a user's codespaces without revealing its encrypted value <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#get-a-secret-for-the-authenticated-user">
     * Get a secret for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}")
    public <T> T getUserSecret(String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName), format);
    }

    /**
     * Method to create or update a secret for a user's codespace with an encrypted value. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#create-or-update-a-secret-for-the-authenticated-user">
     * Create or update a secret for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}")
    public boolean workWithUserSecret(String secretName, String secretValue) {
        return workWithUserSecret(secretName, secretValue, (Long[]) null);
    }

    /**
     * Method to create or update a secret for a user's codespace with an encrypted value. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:   the name of the secret
     * @param secretValue:  the value for your secret
     * @param repositories: an array of repository ids that can access the user secret as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#create-or-update-a-secret-for-the-authenticated-user">
     * Create or update a secret for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}")
    public boolean workWithUserSecret(String secretName, String secretValue, RepositoriesList repositories) {
        return workWithUserSecret(secretName, secretValue, repositories.getIds());
    }

    /**
     * Method to create or update a secret for a user's codespace with an encrypted value. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: an array of repository ids that can access the user secret as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#create-or-update-a-secret-for-the-authenticated-user">
     * Create or update a secret for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}")
    public boolean workWithUserSecret(String secretName, String secretValue, Collection<Long> repositoryIds) {
        return workWithUserSecret(secretName, secretValue, repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to create or update a secret for a user's codespace with an encrypted value. <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: an array of repository ids that can access the user secret as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#create-or-update-a-secret-for-the-authenticated-user">
     * Create or update a secret for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}")
    public boolean workWithUserSecret(String secretName, String secretValue, Long[] repositoryIds) {
        Params payload = new Params();
        if (repositoryIds != null)
            payload.addParam("selected_repository_ids", repositoryIds);
        try {
            sendPutRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName, createSecretPayload(secretValue,
                    getUserPublicKey(), payload));
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
     * Method to delete a secret from a user's codespaces using the secret name. Deleting the secret will remove access
     * from all codespaces that were allowed to access the secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret: the secrete to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#delete-a-secret-for-the-authenticated-user">
     * Delete a secret for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}")
    public boolean deleteUserSecret(Secret secret) {
        return deleteUserSecret(secret.getName());
    }

    /**
     * Method to delete a secret from a user's codespaces using the secret name. Deleting the secret will remove access
     * from all codespaces that were allowed to access the secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#delete-a-secret-for-the-authenticated-user">
     * Delete a secret for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}")
    public boolean deleteUserSecret(String secretName) {
        try {
            sendDeleteRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName);
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
     * Method to get the list of the repositories that have been granted the ability to use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     *
     * @param secret: the secret from fetch the list
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getUserSecretSelectedRepositories(Secret secret) throws IOException {
        return getUserSecretSelectedRepositories(secret.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories that have been granted the ability to use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     *
     * @param secret: the secret from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public <T> T getUserSecretSelectedRepositories(Secret secret, ReturnFormat format) throws IOException {
        return getUserSecretSelectedRepositories(secret.getName(), format);
    }

    /**
     * Method to get the list of the repositories that have been granted the ability to use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     *
     * @param secretName: the name of the secret
     * @return repositories list as {@link RepositoriesList} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getUserSecretSelectedRepositories(String secretName) throws IOException {
        return getUserSecretSelectedRepositories(secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the repositories that have been granted the ability to use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this
     * endpoint. <br>
     * User must have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have read access to the {@code "codespaces_user_secrets"} user permission to use this endpoint <br>
     *
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#list-secrets-for-the-authenticated-user">
     * List secrets for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public <T> T getUserSecretSelectedRepositories(String secretName, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName
                + REPOSITORIES_PATH), format);
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:       the secret where set the list
     * @param repositories: the repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(Secret secret, RepositoriesList repositories) {
        return setUserSecretSelectedRepositories(secret.getName(), repositories.getIds());
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:   the name of the secret
     * @param repositories: the repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(String secretName, RepositoriesList repositories) {
        return setUserSecretSelectedRepositories(secretName, repositories.getIds());
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:        the secret where set the list
     * @param repositoryIds: the repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(Secret secret, Collection<Long> repositoryIds) {
        return setUserSecretSelectedRepositories(secret.getName(), repositoryIds);
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:    the name of the secret
     * @param repositoryIds: the repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(String secretName, Collection<Long> repositoryIds) {
        return setUserSecretSelectedRepositories(secretName, repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:        the secret where set the list
     * @param repositoryIds: the repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(Secret secret, Long[] repositoryIds) {
        return setUserSecretSelectedRepositories(secret.getName(), repositoryIds);
    }

    /**
     * Method to select the repositories that will use a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:    the name of the secret
     * @param repositoryIds: the repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#set-selected-repositories-for-a-user-secret">
     * Set selected repositories for a user secret</a>
     **/
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories")
    public boolean setUserSecretSelectedRepositories(String secretName, Long[] repositoryIds) {
        Params payload = new Params();
        payload.addParam("selected_repository_ids", repositoryIds);
        try {
            sendPutRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH, payload);
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
     * Method to add a repository to the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:     the secret where set the repository
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#add-a-selected-repository-to-a-user-secret">
     * Add a selected repository to a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Secret secret, Repository repository) {
        return addSelectedRepository(secret.getName(), repository.getId());
    }

    /**
     * Method to add a repository to the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName: the name of the secret
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#add-a-selected-repository-to-a-user-secret">
     * Add a selected repository to a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String secretName, Repository repository) {
        return addSelectedRepository(secretName, repository.getId());
    }

    /**
     * Method to add a repository to the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:       the secret where set the repository
     * @param repositoryId: the repository identifier to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#add-a-selected-repository-to-a-user-secret">
     * Add a selected repository to a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Secret secret, long repositoryId) {
        return addSelectedRepository(secret.getName(), repositoryId);
    }

    /**
     * Method to add a repository to the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#add-a-selected-repository-to-a-user-secret">
     * Add a selected repository to a user secret</a>
     **/
    @RequestPath(method = PUT, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String secretName, long repositoryId) {
        try {
            sendPutRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH + "/"
                    + repositoryId, null);
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
     * Method to remove a repository from the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:     the secret from remove the repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#remove-a-selected-repository-from-a-user-secret">
     * Remove a selected repository from a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Secret secret, Repository repository) {
        return removeSelectedRepository(secret.getName(), repository.getId());
    }

    /**
     * Method to remove a repository from the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName: the name of the secret
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#remove-a-selected-repository-from-a-user-secret">
     * Remove a selected repository from a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String secretName, Repository repository) {
        return removeSelectedRepository(secretName, repository.getId());
    }

    /**
     * Method to remove a repository from the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secret:       the secret from remove the repository
     * @param repositoryId: the repository identifier to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#remove-a-selected-repository-from-a-user-secret">
     * Remove a selected repository from a user secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Secret secret, long repositoryId) {
        return removeSelectedRepository(secret.getName(), repositoryId);
    }

    /**
     * Method to remove a repository from the selected repositories for a user's codespace secret <br>
     * You must authenticate using an access token with the codespace or {@code "codespace:secrets"} scope to use this endpoint. <br>
     * User must also have Codespaces access to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_user_secrets"} user permission and {@code "codespaces_secrets"}
     * repository permission on all referenced repositories to use this endpoint
     *
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/secrets#remove-a-selected-repository-from-a-user-secret">
     * Remove a selected repository from a user secret</a>
     **/
    @RequestPath(method = DELETE, path = "/user/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String secretName, long repositoryId) {
        try {
            sendDeleteRequest(USER_CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH + "/"
                    + repositoryId);
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
