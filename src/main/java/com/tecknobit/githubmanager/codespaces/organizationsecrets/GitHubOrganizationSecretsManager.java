package com.tecknobit.githubmanager.codespaces.organizationsecrets;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey;
import com.tecknobit.githubmanager.actions.secrets.records.Secret;
import com.tecknobit.githubmanager.actions.secrets.records.SecretsList;
import com.tecknobit.githubmanager.records.organization.Organization;
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
 * The {@code GitHubOrganizationSecretsManager} class is useful to manage all GitHub's organizations secrets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets">
 * Codespaces organization secrets</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationSecretsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubOrganizationSecretsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationSecretsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationSecretsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecretsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationSecretsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationSecretsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecretsManager} <br>
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
    public GitHubOrganizationSecretsManager() {
        super();
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public SecretsList getOrganizationSecrets(Organization org) throws IOException {
        return getOrganizationSecrets(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public <T> T getOrganizationSecrets(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSecrets(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public SecretsList getOrganizationSecrets(String org) throws IOException {
        return getOrganizationSecrets(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public <T> T getOrganizationSecrets(String org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH), format);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public SecretsList getOrganizationSecrets(Organization org, Params queryParams) throws IOException {
        return getOrganizationSecrets(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public <T> T getOrganizationSecrets(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationSecrets(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public SecretsList getOrganizationSecrets(String org, Params queryParams) throws IOException {
        return getOrganizationSecrets(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all Codespaces secrets available at the organization-level without revealing their
     * encrypted values. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets")
    public <T> T getOrganizationSecrets(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a public key for an organization, which is required in order to encrypt secrets. <br>
     * You need to encrypt the value of a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: the organization from fetch the key
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/public-key")
    public GitHubPublicKey getOrganizationPublicKey(Organization org) throws IOException {
        return getOrganizationPublicKey(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a public key for an organization, which is required in order to encrypt secrets. <br>
     * You need to encrypt the value of a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization from fetch the key
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/public-key")
    public <T> T getOrganizationPublicKey(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationPublicKey(org.getLogin(), format);
    }

    /**
     * Method to get a public key for an organization, which is required in order to encrypt secrets. <br>
     * You need to encrypt the value of a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/public-key")
    public GitHubPublicKey getOrganizationPublicKey(String org) throws IOException {
        return getOrganizationPublicKey(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get a public key for an organization, which is required in order to encrypt secrets. <br>
     * You need to encrypt the value of a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/public-key")
    public <T> T getOrganizationPublicKey(String org, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + PUBLIC_KEY_PATH),
                format);
    }

    /**
     * Method to get an organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from fetch the secret
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public Secret getOrganizationSecret(Organization org, String secretName) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from fetch the secret
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public <T> T getOrganizationSecret(Organization org, String secretName, ReturnFormat format) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public Secret getOrganizationSecret(String org, String secretName) throws IOException {
        return getOrganizationSecret(org, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public <T> T getOrganizationSecret(String org, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName), format);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         organization where create or update the secret
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret. {@code "selected"}
     *                     means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                              String secretValue) throws IOException {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, secretValue, (Long[]) null);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret. {@code "selected"}
     *                     means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, Visibility visibility,
                                              String secretValue) throws IOException {
        return workWithOrganizationSecret(org, secretName, visibility, secretValue, (Long[]) null);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          organization where create or update the secret
     * @param secretName:   the name of the secret
     * @param visibility:   which type of organization repositories have access to the organization secret. {@code "selected"}
     *                      means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:  the value for your secret
     * @param repositories: list of repositories that can access the organization secret as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                              String secretValue, RepositoriesList repositories) throws IOException {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, secretValue, repositories.getIds());
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param visibility:   which type of organization repositories have access to the organization secret. {@code "selected"}
     *                      means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:  the value for your secret
     * @param repositories: list of repositories that can access the organization secret as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                              RepositoriesList repositories) throws IOException {
        return workWithOrganizationSecret(org, secretName, visibility, secretValue, repositories.getIds());
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           organization where create or update the secret
     * @param secretName:    the name of the secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: list of repositories that can access the organization secret as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                              String secretValue, Collection<Long> repositoryIds) throws IOException {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, secretValue, repositoryIds);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: list of repositories that can access the organization secret as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                              Collection<Long> repositoryIds) throws IOException {
        return workWithOrganizationSecret(org, secretName, visibility, secretValue, repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           organization where create or update the secret
     * @param secretName:    the name of the secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: list of repositories that can access the organization secret as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                              String secretValue, Long[] repositoryIds) throws IOException {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, secretValue, repositoryIds);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param secretValue:   the value for your secret
     * @param repositoryIds: list of repositories that can access the organization secret as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                              Long[] repositoryIds) throws IOException {
        GitHubPublicKey key = getOrganizationPublicKey(org);
        Params payload = new Params();
        payload.addParam("visibility", visibility.toString());
        if (repositoryIds != null)
            payload.addParam("selected_repository_ids", repositoryIds);
        try {
            sendPutRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName,
                    createSecretPayload(secretValue, key, payload));
            if (apiRequest.getResponseStatusCode() != 201) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (Exception e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    organization where delete the secret
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(Organization org, Secret secret) {
        return deleteOrganizationSecret(org.getLogin(), secret.getName());
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        organization where delete the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(Organization org, String secretName) {
        return deleteOrganizationSecret(org.getLogin(), secretName);
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(String org, Secret secret) {
        return deleteOrganizationSecret(org, secret.getName());
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(String org, String secretName) {
        try {
            sendDeleteRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (Exception e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(Organization org, Secret secret) throws IOException {
        return getSelectedRepositories(org.getLogin(), secret.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(Organization org, Secret secret, ReturnFormat format) throws IOException {
        return getSelectedRepositories(org.getLogin(), secret.getName(), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(String org, Secret secret) throws IOException {
        return getSelectedRepositories(org, secret.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(String org, Secret secret, ReturnFormat format) throws IOException {
        return getSelectedRepositories(org, secret.getName(), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(Organization org, String secretName) throws IOException {
        return getSelectedRepositories(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(Organization org, String secretName, ReturnFormat format) throws IOException {
        return getSelectedRepositories(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(String org, String secretName) throws IOException {
        return getSelectedRepositories(org, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(String org, String secretName, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" +
                secretName + REPOSITORIES_PATH), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param secret:      the secret from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(Organization org, Secret secret, Params queryParams) throws IOException {
        return getSelectedRepositories(org.getLogin(), secret.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param secret:      the secret from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(Organization org, Secret secret, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return getSelectedRepositories(org.getLogin(), secret.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secret:      the secret from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(String org, Secret secret, Params queryParams) throws IOException {
        return getSelectedRepositories(org, secret.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secret:      the secret from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(String org, Secret secret, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return getSelectedRepositories(org, secret.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param secretName:  the name of the secret
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(Organization org, String secretName,
                                                    Params queryParams) throws IOException {
        return getSelectedRepositories(org.getLogin(), secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param secretName:  the name of the secret
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(Organization org, String secretName, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return getSelectedRepositories(org.getLogin(), secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public RepositoriesList getSelectedRepositories(String org, String secretName, Params queryParams) throws IOException {
        return getSelectedRepositories(org, secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public <T> T getSelectedRepositories(String org, String secretName, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" +
                secretName + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization where set the list
     * @param secret:       the secret where set the list
     * @param repositories: repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, Secret secret, RepositoriesList repositories) {
        return setSelectedRepositories(org.getLogin(), secret.getName(), repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret where set the list
     * @param repositories: repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, Secret secret, RepositoriesList repositories) {
        return setSelectedRepositories(org, secret.getName(), repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization where set the list
     * @param secretName:   the name of the secret
     * @param repositories: repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, String secretName, RepositoriesList repositories) {
        return setSelectedRepositories(org.getLogin(), secretName, repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositories: repositories to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, String secretName, RepositoriesList repositories) {
        return setSelectedRepositories(org, secretName, repositories.getIds());
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization where set the list
     * @param secret:        the secret where set the list
     * @param repositoryIds: repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, Secret secret, Collection<Long> repositoryIds) {
        return setSelectedRepositories(org.getLogin(), secret.getName(), repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secret:        the secret where set the list
     * @param repositoryIds: repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, Secret secret, Collection<Long> repositoryIds) {
        return setSelectedRepositories(org, secret.getName(), repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization where set the list
     * @param secretName:    the name of the secret
     * @param repositoryIds: repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, String secretName, Collection<Long> repositoryIds) {
        return setSelectedRepositories(org.getLogin(), secretName, repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param repositoryIds: repositories to set as {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, String secretName, Collection<Long> repositoryIds) {
        return setSelectedRepositories(org, secretName, repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization where set the list
     * @param secret:        the secret where set the list
     * @param repositoryIds: repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, Secret secret, Long[] repositoryIds) {
        return setSelectedRepositories(org.getLogin(), secret.getName(), repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secret:        the secret where set the list
     * @param repositoryIds: repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, Secret secret, Long[] repositoryIds) {
        return setSelectedRepositories(org, secret.getName(), repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization where set the list
     * @param secretName:    the name of the secret
     * @param repositoryIds: repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(Organization org, String secretName, Long[] repositoryIds) {
        return setSelectedRepositories(org.getLogin(), secretName, repositoryIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param repositoryIds: repositories to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories")
    public boolean setSelectedRepositories(String org, String secretName, Long[] repositoryIds) {
        Params payload = new Params();
        payload.addParam("selected_repository_ids", repositoryIds);
        try {
            sendPutRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH,
                    payload);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (Exception e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization where set the repository
     * @param secret:     the secret where set the repository
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Organization org, Secret secret, Repository repository) {
        return addSelectedRepository(org.getLogin(), secret.getName(), repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization where set the repository
     * @param secretName: the name of the secret
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Organization org, String secretName, Repository repository) {
        return addSelectedRepository(org.getLogin(), secretName, repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String org, String secretName, Repository repository) {
        return addSelectedRepository(org, secretName, repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secret:     the secret where set the repository
     * @param repository: the repository to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String org, Secret secret, Repository repository) {
        return addSelectedRepository(org, secret.getName(), repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret where set the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String org, Secret secret, long repositoryId) {
        return addSelectedRepository(org, secret.getName(), repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization where set the repository
     * @param secret:       the secret where set the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Organization org, Secret secret, long repositoryId) {
        return addSelectedRepository(org.getLogin(), secret.getName(), repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization where set the repository
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(Organization org, String secretName, long repositoryId) {
        return addSelectedRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addSelectedRepository(String org, String secretName, long repositoryId) {
        try {
            sendPutRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH
                    + "/" + repositoryId, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (Exception e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from remove the repository
     * @param secret:     the secret from remove the repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Organization org, Secret secret, Repository repository) {
        return removeSelectedRepository(org.getLogin(), secret.getName(), repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization from remove the repository
     * @param secretName: the name of the secret
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Organization org, String secretName, Repository repository) {
        return removeSelectedRepository(org.getLogin(), secretName, repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String org, String secretName, Repository repository) {
        return removeSelectedRepository(org, secretName, repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secret:     the secret from remove the repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String org, Secret secret, Repository repository) {
        return removeSelectedRepository(org, secret.getName(), repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret from remove the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String org, Secret secret, long repositoryId) {
        return removeSelectedRepository(org, secret.getName(), repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization from remove the repository
     * @param secret:       the secret from remove the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Organization org, Secret secret, long repositoryId) {
        return removeSelectedRepository(org.getLogin(), secret.getName(), repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization from remove the repository
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(Organization org, String secretName, long repositoryId) {
        return removeSelectedRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected. <br>
     * The visibility is set when you Create or update an organization secret<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/codespaces/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeSelectedRepository(String org, String secretName, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + CODESPACES_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH
                    + "/" + repositoryId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (Exception e) {
            printErrorResponse();
            return false;
        }
    }

}
