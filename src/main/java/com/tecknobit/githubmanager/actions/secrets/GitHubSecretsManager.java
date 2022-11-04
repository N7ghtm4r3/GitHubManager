package com.tecknobit.githubmanager.actions.secrets;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.OrganizationSecret;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.Secret;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.SecretsList;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.goterl.lazysodium.utils.Key.fromBase64String;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

/**
 * The {@code GitHubPermissionsManager} class is useful to manage all GitHub's secrets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets">
 * About the Secrets API</a>
 * @see GitHubManager
 **/
// TODO: 02/11/2022 SET JSON BODY PAYLOAD
public class GitHubSecretsManager extends GitHubManager {

    /**
     * {@code SECRETS_PATH} constant for {@code "/secrets"} path
     **/
    public static final String SECRETS_PATH = "/secrets";

    /**
     * {@code ACTIONS_SECRETS_PATH} constant for {@code "/actions/secrets"} path
     **/
    public static final String ACTIONS_SECRETS_PATH = ACTIONS_PATH + "secrets";

    /**
     * {@code ACTIONS_SECRETS_PUBLIC_KEY_PATH} constant for {@code "/actions/secrets/public-key"} path
     **/
    public static final String ACTIONS_SECRETS_PUBLIC_KEY_PATH = ACTIONS_SECRETS_PATH + "/public-key";

    /**
     * {@code REPOSITORIES_PATH} constant for {@code "/actions/repositories"} path
     **/
    public static final String REPOSITORIES_PATH = "/repositories";

    /**
     * {@code REPOSITORIES_QUERY_PATH} constant for {@code "repositories/"} path
     **/
    public static final String REPOSITORIES_QUERY_PATH = "repositories/";

    /**
     * {@code ENVIRONMENTS_PATH} constant for {@code "/environments/"} path
     **/
    public static final String ENVIRONMENTS_PATH = "/environments/";

    /**
     * Constructor to init a {@link GitHubSecretsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSecretsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSecretsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSecretsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSecretsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSecretsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSecretsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSecretsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubSecretsManager}'s manager without re-insert
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
    public GitHubSecretsManager() {
        super();
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org: the organization from fetch secrets list
     * @return all the secrets available in an organization as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    public SecretsList getOrganizationSecretsList(Organization org) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:    the organization from fetch secrets list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationSecretsList(Organization org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH), format);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return all the secrets available in an organization as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    public SecretsList getOrganizationSecretsList(String org) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    public <T> T getOrganizationSecretsList(String org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH), format);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization from fetch secrets list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return all the secrets available in an organization as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    public SecretsList getOrganizationSecretsList(Organization org, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization from fetch secrets list
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
     * @return all the secrets available in an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationSecretsList(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @return all the secrets available in an organization as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    public SecretsList getOrganizationSecretsList(String org, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get all the secrets available in an organization without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @return all the secrets available in an organization as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-organization-secrets">
     * List organization secrets</a>
     **/
    public <T> T getOrganizationSecretsList(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * You must authenticate using an access token with the admin:org scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org: the organization from fetch the public key
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @WrappedRequest
    public GitHubPublicKey getOrganizationPublicKey(Organization org) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * You must authenticate using an access token with the admin:org scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:    the organization from fetch the public key
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationPublicKey(Organization org, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * You must authenticate using an access token with the admin:org scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    public GitHubPublicKey getOrganizationPublicKey(String org) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PUBLIC_KEY_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * You must authenticate using an access token with the admin:org scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-public-key">
     * Get an organization public key</a>
     **/
    public <T> T getOrganizationPublicKey(String org, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PUBLIC_KEY_PATH), format);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization from fetch the secret
     * @param secretName: the name of the secret
     * @return organization secret as {@link OrganizationSecret} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @WrappedRequest
    public OrganizationSecret getOrganizationSecret(Organization org, String secretName) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization from fetch the secret
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization secret as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationSecret(Organization org, String secretName, ReturnFormat format) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, format);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return organization secret as {@link OrganizationSecret} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    public OrganizationSecret getOrganizationSecret(String org, String secretName) throws IOException {
        return getOrganizationSecret(org, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single organization secret without revealing its encrypted value.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization secret as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
     * Get an organization secret</a>
     **/
    public <T> T getOrganizationSecret(String org, String secretName, ReturnFormat format) throws IOException {
        String secretResponse = sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + "/" + secretName);
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationSecret(new JSONObject(secretResponse));
            default:
                return (T) secretResponse;
        }
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization to create the secret
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @param publicKey:   public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, null, secretValue,
                publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue, Long[] repositoriesIds,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @param publicKey:   public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue, publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                            Long[] repositoriesIds, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]), secretValue,
                publicKey);
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization to create the secret
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(name));
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(name));
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean createOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(name));
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility,
                                            String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(org));
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(org));
    }

    /**
     * Method to create an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean createOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(org));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization to create the secret
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @param publicKey:   public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, null, secretValue,
                publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue, Long[] repositoriesIds,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @param publicKey:   public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue, publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility, String secretValue,
                                            Long[] repositoriesIds, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]), secretValue,
                publicKey);
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization to create the secret
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(name));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(name));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to create the secret
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    @WrappedRequest
    public boolean updateOrganizationSecret(Organization org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(name));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param visibility:  which type of organization repositories have access to the organization secret.
     *                     {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                     can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility,
                                            String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(org));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(org));
    }

    /**
     * Method to update an organization secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium  -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param secretValue:       the value of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-organization-secret">
     * Create or update an organization secret</a>
     **/
    public boolean updateOrganizationSecret(String org, String secretName, Visibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(org));
    }

    /**
     * Method to create or update an organization secret with an encrypted value
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param visibility:        which type of organization repositories have access to the organization secret.
     *                           {@code "selected"} means only the repositories specified by {@code "selected_repository_ids"}
     *                           can access the secret. Can be one of: {@code "all"}, {@code "private"}, {@code "selected"}
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can manage the list of selected repositories using the List selected repositories for
     *                           an organization secret, Set selected repositories for an organization secret,
     *                           and Remove selected repository from an organization secret endpoints. -> array of {@link Long} format
     * @param secretValue:       the value of the secret
     * @param publicKey:         public key to use
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    // TODO: 02/11/2022 TEST WHEN PAYLOAD IN JSON HAS BEEN FIXED
    private boolean workWithOrganizationSecret(String org, String secretName, Visibility visibility,
                                               Long[] repositoriesIds, String secretValue,
                                               GitHubPublicKey publicKey) throws Exception {
        LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava(), UTF_8);
        String ciphertext = lazySodium.cryptoBoxSealEasy(secretValue, fromBase64String(publicKey.getKey()));
        Params params = new Params();
        params.addParam("visibility", visibility.toString());
        params.addParam("encrypted_value", getEncoder().encodeToString(ciphertext.getBytes(UTF_8)));
        params.addParam("key_id", publicKey.getKeyId());
        if (repositoriesIds != null)
            params.addParam("selected_repository_ids", Arrays.stream(repositoriesIds).toList());
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + "/" + secretName, params);
            int statusCode = apiRequest.getResponseStatusCode();
            if (statusCode != 201 && statusCode != 204) {
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
     * Method to delete a secret in an organization using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:            the organization from delete the secret
     * @param secretToDelete: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
     * Delete an organization secrete</a>
     **/
    @WrappedRequest
    public boolean deleteOrganizationSecret(Organization org, OrganizationSecret secretToDelete) {
        return deleteOrganizationSecret(org.getLogin(), secretToDelete.getName());
    }

    /**
     * Method to delete a secret in an organization using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:            the organization name. The name is not case-sensitive
     * @param secretToDelete: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
     * Delete an organization secrete</a>
     **/
    @WrappedRequest
    public boolean deleteOrganizationSecret(String org, OrganizationSecret secretToDelete) {
        return deleteOrganizationSecret(org, secretToDelete.getName());
    }

    /**
     * Method to delete a secret in an organization using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization from delete the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
     * Delete an organization secrete</a>
     **/
    @WrappedRequest
    public boolean deleteOrganizationSecret(Organization org, String secretName) {
        return deleteOrganizationSecret(org.getLogin(), secretName);
    }

    /**
     * Method to delete a secret in an organization using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
     * Delete an organization secrete</a>
     **/
    public boolean deleteOrganizationSecret(String org, String secretName) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + "/" + secretName);
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
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization from fetch the selected repositories for an organization secret
     * @param secretName: the name of the secret
     * @return selected repositories for an organization secret as {@link RepositoriesList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public RepositoriesList getOrganizationSecretRepositoriesList(Organization org, String secretName) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization from fetch the selected repositories for an organization secret
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return selected repositories for an organization secret as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationSecretRepositoriesList(Organization org, String secretName,
                                                       ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, format);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return selected repositories for an organization secret as {@link RepositoriesList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    public RepositoriesList getOrganizationSecretRepositoriesList(String org, String secretName) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return selected repositories for an organization secret as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    public <T> T getOrganizationSecretRepositoriesList(String org, String secretName, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH), format);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization from fetch the selected repositories for an organization secret
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
     * @return selected repositories for an organization secret as {@link RepositoriesList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public RepositoriesList getOrganizationSecretRepositoriesList(Organization org, String secretName,
                                                                  Params queryParams) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:         the organization from fetch the selected repositories for an organization secret
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
     * @return selected repositories for an organization secret as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationSecretRepositoriesList(Organization org, String secretName, Params queryParams,
                                                       ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, queryParams, format);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @return selected repositories for an organization secret as {@link RepositoriesList} custom object
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    public RepositoriesList getOrganizationSecretRepositoriesList(String org, String secretName,
                                                                  Params queryParams) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get all repositories that have been selected when the visibility for repository access to a secret is set to selected.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
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
     * @return selected repositories for an organization secret as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
     * List selected repositories for an organization secret</a>
     **/
    public <T> T getOrganizationSecretRepositoriesList(String org, String secretName, Params queryParams,
                                                       ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a selected repositories for an organization secret list
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return selected repositories for an organization secret list as {@code "format"} defines
     **/
    private <T> T returnRepositoriesList(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoriesList(new JSONObject(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to set the organization secret repositories
     * @param secretName:        the name of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can add and remove individual repositories using the Add selected repository to
     *                           an organization secret and Remove selected repository from an organization
     *                           secret endpoints in {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public boolean setSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                             Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can add and remove individual repositories using the Add selected repository to
     *                           an organization secret and Remove selected repository from an organization
     *                           secret endpoints in {@link Collection} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    public boolean setSelectedOrganizationSecretRepositories(String org, String secretName,
                                                             Collection<Long> repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:                      the organization to set the organization secret repositories
     * @param secretName:               the name of the secret
     * @param repositories:repositories list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public boolean setSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                             RepositoriesList repositories) {
        return setSelectedOrganizationSecretRepositories(org.getLogin(), secretName, repositories);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:                      the organization name. The name is not case-sensitive
     * @param secretName:               the name of the secret
     * @param repositories:repositories list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    public boolean setSelectedOrganizationSecretRepositories(String org, String secretName,
                                                             RepositoriesList repositories) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Repository repository : repositories.getRepositories())
            ids.add(repository.getId());
        return setItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", ids.toArray(new Long[0]));
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization to set the organization secret repositories
     * @param secretName:        the name of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can add and remove individual repositories using the Add selected repository to
     *                           an organization secret and Remove selected repository from an organization
     *                           secret endpoints in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    @WrappedRequest
    public boolean setSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                             Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    /**
     * Method to replace all repositories for an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param secretName:        the name of the secret
     * @param repositoriesIds:an array of repository ids that can access the organization secret.
     *                           You can only provide a list of repository ids when the visibility is set to selected.
     *                           You can add and remove individual repositories using the Add selected repository to
     *                           an organization secret and Remove selected repository from an organization
     *                           secret endpoints in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#set-selected-repositories-for-an-organization-secret">
     * Set selected repositories for an organization secret</a>
     **/
    public boolean setSelectedOrganizationSecretRepositories(String org, String secretName, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:             the organization to add the organization secret repository
     * @param secretName:      the name of the secret
     * @param repositoryToAdd: repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    public boolean addSelectedOrganizationSecretRepository(Organization org, String secretName,
                                                           Repository repositoryToAdd) {
        return addSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryToAdd.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:          the organization to add the organization secret repository
     * @param secretName:   the name of the secret
     * @param repositoryId: identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    public boolean addSelectedOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return addSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param secretName:      the name of the secret
     * @param repositoryToAdd: repository to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    @WrappedRequest
    public boolean addSelectedOrganizationSecretRepository(String org, String secretName, Repository repositoryToAdd) {
        return addSelectedOrganizationSecretRepository(org, secretName, repositoryToAdd.getId());
    }

    /**
     * Method to add a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#add-selected-repository-to-an-organization-secret">
     * Add selected repository to an organization secret</a>
     **/
    public boolean addSelectedOrganizationSecretRepository(String org, String secretName, long repositoryId) {
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName + REPOSITORIES_PATH + "/" +
                    repositoryId, null);
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
     * Method to remove a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:                the organization from remove the organization secret repository
     * @param secretName:         the name of the secret
     * @param repositoryToRemove: repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    public boolean removeSelectedOrganizationSecretRepository(Organization org, String secretName,
                                                              Repository repositoryToRemove) {
        return removeSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryToRemove.getId());
    }

    /**
     * Method to remove a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:          the organization from remove the organization secret repository
     * @param secretName:   the name of the secret
     * @param repositoryId: identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    public boolean removeSelectedOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return removeSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    /**
     * Method to remove a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param secretName:         the name of the secret
     * @param repositoryToRemove: repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    @WrappedRequest
    public boolean removeSelectedOrganizationSecretRepository(String org, String secretName, Repository repositoryToRemove) {
        return removeSelectedOrganizationSecretRepository(org, secretName, repositoryToRemove.getId());
    }

    /**
     * Method to remove a repository to an organization secret when the visibility for repository access is set to selected.
     * The visibility is set when you Create or update an organization secret.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param secretName:   the name of the secret
     * @param repositoryId: identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#remove-selected-repository-from-an-organization-secret">
     * Remove selected repository from an organization secret</a>
     **/
    public boolean removeSelectedOrganizationSecretRepository(String org, String secretName, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName + REPOSITORIES_PATH + "/" +
                    repositoryId);
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
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository: the repository from fetch secrets list
     * @return all the secrets available in a repository as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    public SecretsList getRepositorySecretsList(Repository repository) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository: the repository from fetch secrets list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in a repository as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    public <T> T getRepositorySecretsList(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return all the secrets available in a repository as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    public SecretsList getRepositorySecretsList(String owner, String repo) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in a repository as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    public <T> T getRepositorySecretsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH),
                format);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository from fetch secrets list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return all the secrets available in a repository as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    public SecretsList getRepositorySecretsList(Repository repository, Params queryParams) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository from fetch secrets list
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
     * @return all the secrets available in a repository as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    @WrappedRequest
    public <T> T getRepositorySecretsList(Repository repository, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @return all the secrets available in a repository as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    public SecretsList getRepositorySecretsList(String owner, String repo, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in a repository without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @return all the secrets available in a repository as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-repository-secrets">
     * List repository secrets</a>
     **/
    public <T> T getRepositorySecretsList(String owner, String repo, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository: the repository from fetch secrets list
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @WrappedRequest
    public GitHubPublicKey getRepositoryPublicKey(Repository repository) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository: the repository from fetch secrets list
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryPublicKey(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    public GitHubPublicKey getRepositoryPublicKey(String owner, String repo) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-public-key">
     * Get a repository public key</a>
     **/
    public <T> T getRepositoryPublicKey(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @WrappedRequest
    public Secret getRepositorySecret(Repository repository, String secretName) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    @WrappedRequest
    public <T> T getRepositorySecret(Repository repository, String secretName, ReturnFormat format) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, format);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    public Secret getRepositorySecret(String owner, String repo, String secretName) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" +
                secretName), LIBRARY_OBJECT);
    }

    /**
     * Method to get a single repository secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
     * Get a repository secret</a>
     **/
    public <T> T getRepositorySecret(String owner, String repo, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" +
                secretName), format);
    }

    /**
     * Method to create a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository to create the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    public boolean createRepositorySecret(Repository repository, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue, publicKey);
    }

    /**
     * Method to create a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    public boolean createRepositorySecret(String owner, String repo, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, publicKey);
    }

    /**
     * Method to create a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository to create the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    public boolean createRepositorySecret(Repository repository, String secretName, String secretValue) throws Exception {
        String owner = repository.getOwner().getLogin();
        String name = repository.getName();
        return workWithSecret(owner, name, secretName, secretValue, getRepositoryPublicKey(owner, name));
    }

    /**
     * Method to create a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    public boolean createRepositorySecret(String owner, String repo, String secretName,
                                          String secretValue) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, getRepositoryPublicKey(owner, repo));
    }

    /**
     * Method to update a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository to create the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    public boolean updateRepositorySecret(Repository repository, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue, publicKey);
    }

    /**
     * Method to update a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    public boolean updateRepositorySecret(String owner, String repo, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, publicKey);
    }

    /**
     * Method to update a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:  the repository to create the secret
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    @WrappedRequest
    public boolean updateRepositorySecret(Repository repository, String secretName, String secretValue) throws Exception {
        String owner = repository.getOwner().getLogin();
        String name = repository.getName();
        return workWithSecret(owner, name, secretName, secretValue, getRepositoryPublicKey(owner, name));
    }

    /**
     * Method to update a repository secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-a-repository-secret">
     * Create or update a repository secret</a>
     **/
    public boolean updateRepositorySecret(String owner, String repo, String secretName,
                                          String secretValue) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, getRepositoryPublicKey(owner, repo));
    }

    /**
     * Method to create or update a repository secret with an encrypted value
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param secretName:  the name of the secret
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    // TODO: 02/11/2022 TEST WHEN PAYLOAD IN JSON HAS BEEN FIXED
    private boolean workWithSecret(String owner, String repo, String secretName, String secretValue,
                                   GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" + secretName,
                secretName, publicKey);
    }

    /**
     * Method to delete a secret in a repository using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param repository:     the repository from delete the secret
     * @param secretToDelete: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    public boolean deleteRepositorySecret(Repository repository, Secret secretToDelete) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretToDelete.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param secretToDelete: the secret to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    public boolean deleteRepositorySecret(String owner, String repo, Secret secretToDelete) {
        return deleteRepositorySecret(owner, repo, secretToDelete.getName());
    }

    /**
     * Method to delete a secret in a repository using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param repository: the repository from delete the secret
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    @WrappedRequest
    public boolean deleteRepositorySecret(Repository repository, String secretName) {
        return deleteRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName);
    }

    /**
     * Method to delete a secret in a repository using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
     * Delete a repository secret</a>
     **/
    public boolean deleteRepositorySecret(String owner, String repo, String secretName) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" + secretName);
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
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secrets list
     * @param environmentName: the name of the environment
     * @return all the secrets available in an environment as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    @WrappedRequest
    public SecretsList getEnvironmentsSecretsList(Repository repository, String environmentName) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secrets list
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an environment as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    @WrappedRequest
    public <T> T getEnvironmentsSecretsList(Repository repository, String environmentName,
                                            ReturnFormat format) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, format);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
     * @return all the secrets available in an environment as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    public SecretsList getEnvironmentsSecretsList(long repositoryId, String environmentName) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an environment as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    public <T> T getEnvironmentsSecretsList(long repositoryId, String environmentName,
                                            ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH), format);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secrets list
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return all the secrets available in an environment as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    @WrappedRequest
    public SecretsList getEnvironmentsSecretsList(Repository repository, String environmentName,
                                                  Params queryParams) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secrets list
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an environment as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    @WrappedRequest
    public <T> T getEnvironmentsSecretsList(Repository repository, String environmentName, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, queryParams, format);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return all the secrets available in an environment as {@link SecretsList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    public SecretsList getEnvironmentsSecretsList(long repositoryId, String environmentName,
                                                  Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get all secrets available in an environment without revealing their encrypted values.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return all the secrets available in an environment as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-environment-secrets">
     * List environment secrets</a>
     **/
    public <T> T getEnvironmentsSecretsList(long repositoryId, String environmentName, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a secrets list
     *
     * @param secretsListResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return all the secrets available list as {@code "format"} defines
     **/
    private <T> T returnSecretsList(String secretsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretsListResponse);
            case LIBRARY_OBJECT:
                return (T) new SecretsList(new JSONObject(secretsListResponse));
            default:
                return (T) secretsListResponse;
        }
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch public key
     * @param environmentName: the name of the environment
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-public-key">
     * Get an environment public key</a>
     **/
    @WrappedRequest
    public GitHubPublicKey getEnvironmentPublicKey(Repository repository, String environmentName) throws IOException {
        return getEnvironmentPublicKey(repository.getId(), environmentName, LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch public key
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-public-key">
     * Get an environment public key</a>
     **/
    @WrappedRequest
    public <T> T getEnvironmentPublicKey(Repository repository, String environmentName,
                                         ReturnFormat format) throws IOException {
        return getEnvironmentPublicKey(repository.getId(), environmentName, format);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-public-key">
     * Get an environment public key</a>
     **/
    public GitHubPublicKey getEnvironmentPublicKey(long repositoryId, String environmentName) throws IOException {
        return returnPublicKey(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + ACTIONS_SECRETS_PUBLIC_KEY_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get your public key, which you need to encrypt secrets.
     * You need to encrypt a secret before you can create or update secrets.
     * Anyone with read access to the repository can use this endpoint.
     * If the repository is private you must use an access token with the repo scope -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository identifier
     * @param environmentName: the name of the environment
     * @param format:          return type formatter -> {@link ReturnFormat}
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-public-key">
     * Get an environment public key</a>
     **/
    public <T> T getEnvironmentPublicKey(long repositoryId, String environmentName, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + ACTIONS_SECRETS_PUBLIC_KEY_PATH), format);
    }

    /**
     * Method to create a public key
     *
     * @param publicKeyResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return public key as {@code "format"} defines
     **/
    private <T> T returnPublicKey(String publicKeyResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(publicKeyResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubPublicKey(new JSONObject(publicKeyResponse));
            default:
                return (T) publicKeyResponse;
        }
    }

    /**
     * Method to get a single environment secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
     * Get an environment secret</a>
     **/
    @WrappedRequest
    public Secret getEnvironmentSecret(Repository repository, String environmentName, String secretName) throws IOException {
        return getEnvironmentSecret(repository.getId(), environmentName, secretName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single environment secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository from fetch secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment secret as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
     * Get an environment secret</a>
     **/
    @WrappedRequest
    public <T> T getEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                      ReturnFormat format) throws IOException {
        return getEnvironmentSecret(repository.getId(), environmentName, secretName, format);
    }

    /**
     * Method to get a single environment secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the identifier of the repository
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
     * Get an environment secret</a>
     **/
    public Secret getEnvironmentSecret(long repositoryId, String environmentName, String secretName) throws IOException {
        return returnSecret(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName), LIBRARY_OBJECT);
    }

    /**
     * Method to get a single environment secret without revealing its encrypted value.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the identifier of the repository
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return environment secret as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
     * Get an environment secret</a>
     **/
    public <T> T getEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                      ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName), format);
    }

    /**
     * Method to create a secret
     *
     * @param secretResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return secret as {@code "format"} defines
     **/
    private <T> T returnSecret(String secretResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretResponse);
            case LIBRARY_OBJECT:
                return (T) new Secret(new JSONObject(secretResponse));
            default:
                return (T) secretResponse;
        }
    }

    /**
     * Method to create an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @param publicKey:       public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    @WrappedRequest
    public boolean createEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getId(), environmentName, secretName, secretValue, publicKey);
    }

    /**
     * Method to create an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @param publicKey:       public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    public boolean createEnvironmentSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                           GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, publicKey);
    }

    /**
     * Method to create an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    @WrappedRequest
    public boolean createEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        long id = repository.getId();
        return workWithSecret(id, environmentName, secretName, secretValue, getEnvironmentPublicKey(id, environmentName));
    }

    /**
     * Method to create an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    public boolean createEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, getEnvironmentPublicKey(repositoryId,
                environmentName));
    }

    /**
     * Method to update an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @param publicKey:       public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    @WrappedRequest
    public boolean updateEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getId(), environmentName, secretName, secretValue, publicKey);
    }

    /**
     * Method to update an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @param publicKey:       public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    public boolean updateEnvironmentSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                           GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, publicKey);
    }

    /**
     * Method to update an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repository:      the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    @WrappedRequest
    public boolean updateEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        long id = repository.getId();
        return workWithSecret(id, environmentName, secretName, secretValue, getEnvironmentPublicKey(id, environmentName));
    }

    /**
     * Method to update an environment secret with an encrypted value. <br>
     * Encrypt your secret using LibSodium. -> <b> this step is automatically made by this library. </b> <br>
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets repository permission to use this endpoint
     *
     * @param repositoryId:    the repository to create secret
     * @param environmentName: the name of the environment
     * @param secretName:      the name of the secret
     * @param secretValue:     the value of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
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
     * @apiNote this method get the public key autonomously
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#create-or-update-an-environment-secret">
     * Create or update an environment secret</a>
     **/
    public boolean updateEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, getEnvironmentPublicKey(repositoryId,
                environmentName));
    }

    /**
     * Method to create or update an environment secret
     *
     * @param repositoryId:    identifier of the repository
     * @param environmentName: the name of the environment
     * @param secretValue:     the value of the secret
     * @param publicKey:       public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    private boolean workWithSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                   GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName, secretValue, publicKey);
    }

    /**
     * Method to create or update a secret
     *
     * @param endpoint:    the account owner of the repository. The name is not case-sensitive
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    // TODO: 03/11/2022 TEST WITH JSON PAYLOAD FIXED
    private boolean workWithSecret(String endpoint, String secretValue, GitHubPublicKey publicKey) throws Exception {
        LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava(), UTF_8);
        String ciphertext = lazySodium.cryptoBoxSealEasy(secretValue, fromBase64String(publicKey.getKey()));
        Params params = new Params();
        params.addParam("encrypted_value", getEncoder().encodeToString(ciphertext.getBytes(UTF_8)));
        params.addParam("key_id", publicKey.getKeyId());
        try {
            sendPutRequest(endpoint, params);
            int statusCode = apiRequest.getResponseStatusCode();
            if (statusCode != 201 && statusCode != 204) {
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
     * Method to delete a secret in an environment using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param repository: the repository from delete the secret
     * @param environmentName: the name of the environment
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-environment-secret">
     * Delete an environment secret</a>
     **/
    @WrappedRequest
    public boolean deleteEnvironmentSecret(Repository repository, String environmentName, String secretName) {
        return deleteEnvironmentSecret(repository.getId(), environmentName, secretName);
    }

    /**
     * Method to delete a secret in an environment using the secret name.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the secrets organization permission to use this endpoint
     *
     * @param repositoryId: identifier of the repository
     * @param environmentName: the name of the environment
     * @param secretName: the name of the secret
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-environment-secret">
     * Delete an environment secret</a>
     **/
    public boolean deleteEnvironmentSecret(long repositoryId, String environmentName, String secretName) {
        try {
            sendDeleteRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH + environmentName +
                    SECRETS_PATH + "/" + secretName);
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
