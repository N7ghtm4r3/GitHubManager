package com.tecknobit.githubmanager.dependabot.secrets;

import com.goterl.lazysodium.exceptions.SodiumException;
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
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey.returnPublicKey;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.createSecretPayload;
import static com.tecknobit.githubmanager.actions.secrets.records.Secret.returnSecret;
import static com.tecknobit.githubmanager.actions.secrets.records.SecretsList.returnSecretsList;
import static com.tecknobit.githubmanager.records.repository.RepositoriesList.returnRepositoriesList;

/**
 * The {@code GitHubDependabotSecretsManager} class is useful to manage all GitHub's dependabot secrets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets">
 * Dependabot secrets</a>
 * @see GitHubManager
 **/
public class GitHubDependabotSecretsManager extends GitHubManager {

    /**
     * {@code DEPENDABOT_SECRETS_PATH} constant for {@code "/dependabot/secrets"} path
     **/
    public static final String DEPENDABOT_SECRETS_PATH = "/dependabot" + SECRETS_PATH;

    /**
     * Constructor to init  {@link GitHubDependabotSecretsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDependabotSecretsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init  {@link GitHubDependabotSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDependabotSecretsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init  {@link GitHubDependabotSecretsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDependabotSecretsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init  {@link GitHubDependabotSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDependabotSecretsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init  {@link GitHubDependabotSecretsManager} <br>
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
    public GitHubDependabotSecretsManager() {
        super();
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public SecretsList getOrganizationSecrets(Organization org) throws IOException {
        return getOrganizationSecrets(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public <T> T getOrganizationSecrets(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSecrets(org.getLogin(), format);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public SecretsList getOrganizationSecrets(String org) throws IOException {
        return getOrganizationSecrets(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public <T> T getOrganizationSecrets(String org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH), format);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public SecretsList getOrganizationSecrets(Organization org, Params queryParams) throws IOException {
        return getOrganizationSecrets(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public <T> T getOrganizationSecrets(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationSecrets(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public SecretsList getOrganizationSecrets(String org, Params queryParams) throws IOException {
        return getOrganizationSecrets(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets")
    public <T> T getOrganizationSecrets(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/public-key")
    public GitHubPublicKey getOrganizationPublicKey(Organization org) throws IOException {
        return getOrganizationPublicKey(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/public-key")
    public <T> T getOrganizationPublicKey(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationPublicKey(org.getLogin(), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/public-key")
    public GitHubPublicKey getOrganizationPublicKey(String org) throws IOException {
        return getOrganizationPublicKey(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/public-key")
    public <T> T getOrganizationPublicKey(String org, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + PUBLIC_KEY_PATH),
                format);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public Secret getOrganizationSecret(Organization org, String secretName) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public <T> T getOrganizationSecret(Organization org, String secretName, ReturnFormat format) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, format);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public Secret getOrganizationSecret(String org, String secretName) throws IOException {
        return getOrganizationSecret(org, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public <T> T getOrganizationSecret(String org, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName), format);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:         organization where create or update the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @param visibility:  which type of organization repositories have access to the organization secret. {@code "selected"}
     *                     means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, String secretValue, Visibility visibility) {
        return workWithOrganizationSecret(org.getLogin(), secretName, secretValue, visibility, (Long[]) null);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @param visibility:  which type of organization repositories have access to the organization secret. {@code "selected"}
     *                     means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, String secretValue, Visibility visibility) {
        return workWithOrganizationSecret(org, secretName, secretValue, visibility, (Long[]) null);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where create or update the secret
     * @param secretName:   the name of the secret
     * @param secretValue:  the value for your secret
     * @param visibility:   which type of organization repositories have access to the organization secret. {@code "selected"}
     *                      means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositories: list of repositories that can access the organization secret as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, String secretValue,
                                              Visibility visibility, RepositoriesList repositories) {
        return workWithOrganizationSecret(org.getLogin(), secretName, secretValue, visibility, repositories.getIds());
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param secretValue:  the value for your secret
     * @param visibility:   which type of organization repositories have access to the organization secret. {@code "selected"}
     *                      means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositories: list of repositories that can access the organization secret as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, String secretValue,
                                              Visibility visibility, RepositoriesList repositories) {
        return workWithOrganizationSecret(org, secretName, secretValue, visibility, repositories.getIds());
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:           organization where create or update the secret
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositoryIds: list of repositories that can access the organization secret as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, String secretValue,
                                              Visibility visibility, ArrayList<Long> repositoryIds) {
        return workWithOrganizationSecret(org.getLogin(), secretName, secretValue, visibility,
                repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositoryIds: list of repositories that can access the organization secret as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, String secretValue,
                                              Visibility visibility, ArrayList<Long> repositoryIds) {
        return workWithOrganizationSecret(org, secretName, secretValue, visibility, repositoryIds.toArray(new Long[0]));
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:           organization where create or update the secret
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositoryIds: list of repositories that can access the organization secret as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(Organization org, String secretName, String secretValue,
                                              Visibility visibility, Long[] repositoryIds) {
        return workWithOrganizationSecret(org.getLogin(), secretName, secretValue, visibility, repositoryIds);
    }

    /**
     * Method to create or update an organization secret with an encrypted value <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param secretName:    the name of the secret
     * @param secretValue:   the value for your secret
     * @param visibility:    which type of organization repositories have access to the organization secret. {@code "selected"}
     *                       means only the repositories specified by {@code "selected_repository_ids"} can access the secret
     * @param repositoryIds: list of repositories that can access the organization secret as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean workWithOrganizationSecret(String org, String secretName, String secretValue,
                                              Visibility visibility, Long[] repositoryIds) {
        try {
            Params payload = new Params();
            payload.addParam("visibility", visibility.toString());
            if (repositoryIds != null)
                payload.addParam("selected_repository_ids", repositoryIds);
            sendPutRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName,
                    createSecretPayload(secretValue, getOrganizationPublicKey(org), payload));
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
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:    organization where delete the secret
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(Organization org, Secret secret) {
        return deleteOrganizationSecret(org.getLogin(), secret.getName());
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(String org, Secret secret) {
        return deleteOrganizationSecret(org, secret.getName());
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        organization where delete the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(Organization org, String secretName) {
        return deleteOrganizationSecret(org.getLogin(), secretName);
    }

    /**
     * Method to delete an organization secret using the secret name<br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-an-organization-secret">
     * Delete an organization secret</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}")
    public boolean deleteOrganizationSecret(String org, String secretName) {
        try {
            sendDeleteRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName);
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
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(Organization org, Secret secret) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secret.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(Organization org, Secret secret, ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secret.getName(), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(String org, Secret secret) throws IOException {
        return getOrganizationSecretRepositories(org, secret.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(String org, Secret secret, ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org, secret.getName(), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(Organization org, String secretName) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(Organization org, String secretName, ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secretName, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(String org, String secretName) throws IOException {
        return getOrganizationSecretRepositories(org, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(String org, String secretName, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/"
                + secretName + REPOSITORIES_PATH), format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(Organization org, Secret secret,
                                                              Params queryParams) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secret.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(Organization org, Secret secret, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secret.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(String org, Secret secret,
                                                              Params queryParams) throws IOException {
        return getOrganizationSecretRepositories(org, secret.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(String org, Secret secret, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org, secret.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(Organization org, String secretName,
                                                              Params queryParams) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(Organization org, String secretName, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositories(org.getLogin(), secretName, queryParams, format);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public RepositoriesList getOrganizationSecretRepositories(String org, String secretName,
                                                              Params queryParams) throws IOException {
        return getOrganizationSecretRepositories(org, secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all repositories that have been selected when the visibility for repository access to a
     * secret is set to selected <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public <T> T getOrganizationSecretRepositories(String org, String secretName, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/"
                + secretName + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where set the list
     * @param secret:       the secret where set the list
     * @param repositories: the repositories list to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, Secret secret, RepositoriesList repositories) {
        return setOrganizationSecretRepositories(org.getLogin(), secret.getName(), repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where set the list
     * @param secretName:   the name of the secret
     * @param repositories: the repositories list to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, String secretName, RepositoriesList repositories) {
        return setOrganizationSecretRepositories(org.getLogin(), secretName, repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret where set the list
     * @param repositories: the repositories list to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, Secret secret, RepositoriesList repositories) {
        return setOrganizationSecretRepositories(org, secret.getName(), repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositories: the repositories list to set as {@link RepositoriesList} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, String secretName, RepositoriesList repositories) {
        return setOrganizationSecretRepositories(org, secretName, repositories.getIds());
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             organization where set the list
     * @param secret:          the secret where set the list
     * @param repositoriesIds: the repositories list to set as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, Secret secret, ArrayList<Long> repositoriesIds) {
        return setOrganizationSecretRepositories(org.getLogin(), secret.getName(), repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             organization where set the list
     * @param secretName:      the name of the secret
     * @param repositoriesIds: the repositories list to set as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, String secretName, ArrayList<Long> repositoriesIds) {
        return setOrganizationSecretRepositories(org.getLogin(), secretName, repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param secret:          the secret where set the list
     * @param repositoriesIds: the repositories list to set as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, Secret secret, ArrayList<Long> repositoriesIds) {
        return setOrganizationSecretRepositories(org, secret.getName(), repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param secretName:      the name of the secret
     * @param repositoriesIds: the repositories list to set as {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, String secretName, ArrayList<Long> repositoriesIds) {
        return setOrganizationSecretRepositories(org, secretName, repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             organization where set the list
     * @param secret:          the secret where set the list
     * @param repositoriesIds: the repositories list to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, Secret secret, Long[] repositoriesIds) {
        return setOrganizationSecretRepositories(org.getLogin(), secret.getName(), repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             organization where set the list
     * @param secretName:      the name of the secret
     * @param repositoriesIds: the repositories list to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(Organization org, String secretName, Long[] repositoriesIds) {
        return setOrganizationSecretRepositories(org.getLogin(), secretName, repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param secret:          the secret where set the list
     * @param repositoriesIds: the repositories list to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, Secret secret, Long[] repositoriesIds) {
        return setOrganizationSecretRepositories(org, secret.getName(), repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set
     * to selected. The visibility is set when you Create or update an organization secret <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param secretName:      the name of the secret
     * @param repositoriesIds: the repositories list to set as array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories")
    public boolean setOrganizationSecretRepositories(String org, String secretName, Long[] repositoriesIds) {
        try {
            Params payload = new Params();
            payload.addParam("selected_repository_ids", repositoriesIds);
            sendPutRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH,
                    payload);
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
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        organization where add the repository
     * @param secret:     the secret where add the repository
     * @param repository: the repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(Organization org, Secret secret, Repository repository) {
        return addOrganizationSecretRepository(org.getLogin(), secret.getName(), repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        organization where add the repository
     * @param secretName: the name of the secret
     * @param repository: the repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(Organization org, String secretName, Repository repository) {
        return addOrganizationSecretRepository(org.getLogin(), secretName, repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param repository: the repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(String org, String secretName, Repository repository) {
        return addOrganizationSecretRepository(org, secretName, repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secret:     the secret where add the repository
     * @param repository: the repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(String org, Secret secret, Repository repository) {
        return addOrganizationSecretRepository(org, secret.getName(), repository.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret where add the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(String org, Secret secret, long repositoryId) {
        return addOrganizationSecretRepository(org, secret.getName(), repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where add the repository
     * @param secret:       the secret where add the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(Organization org, Secret secret, long repositoryId) {
        return addOrganizationSecretRepository(org.getLogin(), secret.getName(), repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where add the repository
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return addOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean addOrganizationSecretRepository(String org, String secretName, long repositoryId) {
        try {
            sendPutRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH
                    + "/" + repositoryId, null);
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
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        organization where remove the repository
     * @param secret:     the secret where remove the repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(Organization org, Secret secret, Repository repository) {
        return removeOrganizationSecretRepository(org.getLogin(), secret.getName(), repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        organization where remove the repository
     * @param secretName: the name of the secret
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(Organization org, String secretName, Repository repository) {
        return removeOrganizationSecretRepository(org.getLogin(), secretName, repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(String org, String secretName, Repository repository) {
        return removeOrganizationSecretRepository(org, secretName, repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secret:     the secret where remove the repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(String org, Secret secret, Repository repository) {
        return removeOrganizationSecretRepository(org, secret.getName(), repository.getId());
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secret:       the secret where remove the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(String org, Secret secret, long repositoryId) {
        return removeOrganizationSecretRepository(org, secret.getName(), repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where remove the repository
     * @param secret:       the secret where remove the repository
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(Organization org, Secret secret, long repositoryId) {
        return removeOrganizationSecretRepository(org.getLogin(), secret.getName(), repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          organization where remove the repository
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return removeOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to remove a repository from an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: the repository identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/dependabot/secrets/{secret_name}/repositories/{repository_id}")
    public boolean removeOrganizationSecretRepository(String org, String secretName, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + DEPENDABOT_SECRETS_PATH + "/" + secretName + REPOSITORIES_PATH
                    + "/" + repositoryId);
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
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public SecretsList getRepositorySecrets(Repository repository) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public <T> T getRepositorySecrets(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public SecretsList getRepositorySecrets(String owner, String repo) throws IOException {
        return getRepositorySecrets(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public <T> T getRepositorySecrets(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH), format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public SecretsList getRepositorySecrets(Repository repository, Params queryParams) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public <T> T getRepositorySecrets(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositorySecrets(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public SecretsList getRepositorySecrets(String owner, String repo, Params queryParams) throws IOException {
        return getRepositorySecrets(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all secrets available in a repository without revealing their encrypted values. <br>
     * You must authenticate using an access token with the repo scope to use this endpoint. <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets")
    public <T> T getRepositorySecrets(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can create
     * or update secrets. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/public-key")
    public GitHubPublicKey getRepositoryPublicKey(Repository repository) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can create
     * or update secrets. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/public-key")
    public <T> T getRepositoryPublicKey(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can create
     * or update secrets. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/public-key")
    public GitHubPublicKey getRepositoryPublicKey(String owner, String repo) throws IOException {
        return getRepositoryPublicKey(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets. You need to encrypt a secret before you can create
     * or update secrets. Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/public-key")
    public <T> T getRepositoryPublicKey(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH
                + PUBLIC_KEY_PATH), format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.<br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public Secret getRepositorySecret(Repository repository, String secretName) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.<br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public <T> T getRepositorySecret(Repository repository, String secretName, ReturnFormat format) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.<br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public Secret getRepositorySecret(String owner, String repo, String secretName) throws IOException {
        return getRepositorySecret(owner, repo, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.<br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public <T> T getRepositorySecret(String owner, String repo, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH
                + "/" + secretName), format);
    }

    /**
     * Method to create or update a repository secret with an encrypted value <br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param repository:  the repository where create the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean workWithRepositorySecret(Repository repository, String secretName, String secretValue) {
        return workWithRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue);
    }

    /**
     * Method to create or update a repository secret with an encrypted value <br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value for your secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean workWithRepositorySecret(String owner, String repo, String secretName, String secretValue) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH + "/" + secretName,
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
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository where delete the secret
     * @param secret:     the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean deleteRepositorySecret(Repository repository, Secret secret) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secret.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param secret: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean deleteRepositorySecret(String owner, String repo, Secret secret) {
        return deleteRepositorySecret(owner, repo, secret.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param repository: the repository where delete the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean deleteRepositorySecret(Repository repository, String secretName) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName);
    }

    /**
     * Method to delete a secret in a repository using the secret name <br>
     * You must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * GitHub Apps must have the {@code "dependabot_secrets"} repository permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/dependabot/secrets/{secret_name}")
    public boolean deleteRepositorySecret(String owner, String repo, String secretName) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_SECRETS_PATH + "/" + secretName);
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
