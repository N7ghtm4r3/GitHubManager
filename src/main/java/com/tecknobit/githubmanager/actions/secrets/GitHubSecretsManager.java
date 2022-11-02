package com.tecknobit.githubmanager.actions.secrets;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.permissions.records.OrganizationsList.Organization;
import com.tecknobit.githubmanager.actions.secrets.records.GitHubPublicKey;
import com.tecknobit.githubmanager.actions.secrets.records.RepositoriesList;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.OrganizationSecret;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.Secret;
import com.tecknobit.githubmanager.actions.secrets.records.secrets.SecretsList;
import com.tecknobit.githubmanager.records.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.goterl.lazysodium.utils.Key.fromBase64String;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.ACTIONS_PATH;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.REPOS_PATH;
import static com.tecknobit.githubmanager.actions.cache.GitHubCacheManager.ORGS_PATH;
import static com.tecknobit.githubmanager.actions.secrets.records.secrets.Secret.SecretVisibility;
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

    public SecretsList getOrganizationSecretsList(Organization org) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretsList(Organization org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH), format);
    }

    public SecretsList getOrganizationSecretsList(String org) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretsList(String org, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH), format);
    }

    public SecretsList getOrganizationSecretsList(Organization org, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretsList(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    public SecretsList getOrganizationSecretsList(String org, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretsList(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    public GitHubPublicKey getOrganizationPublicKey(Organization org) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                LIBRARY_OBJECT);
    }

    public GitHubPublicKey getOrganizationPublicKey(String org) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PUBLIC_KEY_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationPublicKey(String org, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PUBLIC_KEY_PATH), format);
    }

    public OrganizationSecret getOrganizationSecret(Organization org, String secretName) throws IOException {
        return getOrganizationSecret(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    public OrganizationSecret getOrganizationSecret(String org, String secretName) throws IOException {
        return getOrganizationSecret(org, secretName, LIBRARY_OBJECT);
    }

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

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue, publicKey);
    }

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility, String secretValue,
                                            Long[] repositoriesIds, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]), secretValue,
                publicKey);
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, null, secretValue,
                publicKey);
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue, Long[] repositoriesIds,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, publicKey);
    }

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(org));
    }

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(org));
    }

    public boolean createOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(org));
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(name));
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(name));
    }

    public boolean createOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(name));
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue, publicKey);
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility, String secretValue,
                                            Long[] repositoriesIds, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]), secretValue,
                publicKey);
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, null, secretValue,
                publicKey);
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue, Long[] repositoriesIds,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds, secretValue, publicKey);
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue,
                                            GitHubPublicKey publicKey) throws Exception {
        return workWithOrganizationSecret(org.getLogin(), secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, publicKey);
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(org));
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(org));
    }

    public boolean updateOrganizationSecret(String org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        return workWithOrganizationSecret(org, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(org));
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, null, secretValue,
                getOrganizationPublicKey(name));
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Long[] repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds,
                secretValue, getOrganizationPublicKey(name));
    }

    public boolean updateOrganizationSecret(Organization org, String secretName, SecretVisibility visibility,
                                            Collection<Long> repositoriesIds, String secretValue) throws Exception {
        String name = org.getLogin();
        return workWithOrganizationSecret(name, secretName, visibility, repositoriesIds.toArray(new Long[0]),
                secretValue, getOrganizationPublicKey(name));
    }

    // TODO: 02/11/2022 TEST WHEN PAYLOAD IN JSON HAS BEEN FIXED
    private boolean workWithOrganizationSecret(String org, String secretName, SecretVisibility visibility,
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

    public boolean deleteOrganizationSecret(Organization org, OrganizationSecret secretToDelete) {
        return deleteOrganizationSecret(org.getLogin(), secretToDelete.getName());
    }

    public boolean deleteOrganizationSecret(String org, OrganizationSecret secretToDelete) {
        return deleteOrganizationSecret(org, secretToDelete.getName());
    }

    public boolean deleteOrganizationSecret(Organization org, String secretName) {
        return deleteOrganizationSecret(org.getLogin(), secretName);
    }

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

    public RepositoriesList getOrganizationSecretRepositoriesList(Organization org, String secretName) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretRepositoriesList(Organization org, String secretName,
                                                       ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, format);
    }

    public RepositoriesList getOrganizationSecretRepositoriesList(String org, String secretName) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretRepositoriesList(String org, String secretName, ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH), format);
    }

    public RepositoriesList getOrganizationSecretRepositoriesList(Organization org, String secretName,
                                                                  Params queryParams) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretRepositoriesList(Organization org, String secretName, Params queryParams,
                                                       ReturnFormat format) throws IOException {
        return getOrganizationSecretRepositoriesList(org.getLogin(), secretName, queryParams, format);
    }

    public RepositoriesList getOrganizationSecretRepositoriesList(String org, String secretName,
                                                                  Params queryParams) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretRepositoriesList(String org, String secretName, Params queryParams,
                                                       ReturnFormat format) throws IOException {
        return returnRepositoriesList(sendGetRequest(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

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

    public boolean enableSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                                Collection<Long> repositoriesIds) {
        return enableSelectedItems(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean enableSelectedOrganizationSecretRepositories(String org, String secretName,
                                                                Collection<Long> repositoriesIds) {
        return enableSelectedItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds.toArray(new Long[0]));
    }

    public boolean enableSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                                RepositoriesList repositories) {
        return enableSelectedOrganizationSecretRepositories(org.getLogin(), secretName, repositories);
    }

    public boolean enableSelectedOrganizationSecretRepositories(String org, String secretName,
                                                                RepositoriesList repositories) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Repository repository : repositories.getRepositories())
            ids.add(repository.getId());
        return enableSelectedItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", ids.toArray(new Long[0]));
    }

    public boolean enableSelectedOrganizationSecretRepositories(Organization org, String secretName,
                                                                Long[] repositoriesIds) {
        return enableSelectedItems(ORGS_PATH + org.getLogin() + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean enableSelectedOrganizationSecretRepositories(String org, String secretName, Long[] repositoriesIds) {
        return enableSelectedItems(ORGS_PATH + org + ACTIONS_SECRETS_PATH + secretName +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    public boolean addSelectedOrganizationSecretRepository(Organization org, String secretName,
                                                           Repository repositoryToEnable) {
        return addSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryToEnable.getId());
    }

    public boolean addSelectedOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return addSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    public boolean addSelectedOrganizationSecretRepository(String org, String secretName, Repository repositoryToEnable) {
        return addSelectedOrganizationSecretRepository(org, secretName, repositoryToEnable.getId());
    }

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

    public boolean removeSelectedOrganizationSecretRepository(Organization org, String secretName,
                                                              Repository repositoryToRemove) {
        return removeSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryToRemove.getId());
    }

    public boolean removeSelectedOrganizationSecretRepository(Organization org, String secretName, long repositoryId) {
        return removeSelectedOrganizationSecretRepository(org.getLogin(), secretName, repositoryId);
    }

    public boolean removeSelectedOrganizationSecretRepository(String org, String secretName, Repository repositoryToRemove) {
        return removeSelectedOrganizationSecretRepository(org, secretName, repositoryToRemove.getId());
    }

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

    public SecretsList getRepositorySecretsList(Repository repository) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretsList(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public SecretsList getRepositorySecretsList(String owner, String repo) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH),
                format);
    }

    public SecretsList getRepositorySecretsList(Repository repository, Params queryParams) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretsList(Repository repository, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getRepositorySecretsList(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public SecretsList getRepositorySecretsList(String owner, String repo, Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretsList(String owner, String repo, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH +
                queryParams.createQueryString()), format);
    }

    public GitHubPublicKey getRepositoryPublicKey(Repository repository) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryPublicKey(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryPublicKey(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public GitHubPublicKey getRepositoryPublicKey(String owner, String repo) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryPublicKey(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PUBLIC_KEY_PATH),
                format);
    }

    public Secret getRepositorySecret(Repository repository, String secretName) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecret(Repository repository, String secretName, ReturnFormat format) throws IOException {
        return getRepositorySecret(repository.getOwner().getLogin(), repository.getName(), secretName, format);
    }

    public Secret getRepositorySecret(String owner, String repo, String secretName) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" +
                secretName), LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecret(String owner, String repo, String secretName, ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" +
                secretName), format);
    }

    public boolean createRepositorySecret(String owner, String repo, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, publicKey);
    }

    public boolean createRepositorySecret(String owner, String repo, String secretName,
                                          String secretValue) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, getRepositoryPublicKey(owner, repo));
    }

    public boolean createRepositorySecret(Repository repository, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue, publicKey);
    }

    public boolean createRepositorySecret(Repository repository, String secretName, String secretValue) throws Exception {
        String owner = repository.getOwner().getLogin();
        String name = repository.getName();
        return workWithSecret(owner, name, secretName, secretValue, getRepositoryPublicKey(owner, name));
    }

    public boolean updateRepositorySecret(String owner, String repo, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, publicKey);
    }

    public boolean updateRepositorySecret(String owner, String repo, String secretName,
                                          String secretValue) throws Exception {
        return workWithSecret(owner, repo, secretName, secretValue, getRepositoryPublicKey(owner, repo));
    }

    public boolean updateRepositorySecret(Repository repository, String secretName, String secretValue,
                                          GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getOwner().getLogin(), repository.getName(), secretName, secretValue, publicKey);
    }

    public boolean updateRepositorySecret(Repository repository, String secretName, String secretValue) throws Exception {
        String owner = repository.getOwner().getLogin();
        String name = repository.getName();
        return workWithSecret(owner, name, secretName, secretValue, getRepositoryPublicKey(owner, name));
    }

    // TODO: 02/11/2022 TEST WHEN PAYLOAD IN JSON HAS BEEN FIXED
    private boolean workWithSecret(String owner, String repo, String secretName, String secretValue,
                                   GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(REPOS_PATH + owner + "/" + repo + ACTIONS_SECRETS_PATH + "/" + secretName,
                secretName, publicKey);
    }

    public boolean deleteRepositorySecret(String owner, String repo, Secret secretToDelete) {
        return deleteRepositorySecret(owner, repo, secretToDelete.getName());
    }

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


    public SecretsList getEnvironmentsSecretsList(Repository repository, String environmentName) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentsSecretsList(Repository repository, String environmentName,
                                            ReturnFormat format) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, format);
    }

    public SecretsList getEnvironmentsSecretsList(long repositoryId, String environmentName) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentsSecretsList(long repositoryId, String environmentName,
                                            ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH), format);
    }

    public SecretsList getEnvironmentsSecretsList(Repository repository, String environmentName,
                                                  Params queryParams) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentsSecretsList(Repository repository, String environmentName, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getEnvironmentsSecretsList(repository.getId(), environmentName, queryParams, format);
    }

    public SecretsList getEnvironmentsSecretsList(long repositoryId, String environmentName,
                                                  Params queryParams) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentsSecretsList(long repositoryId, String environmentName, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnSecretsList(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + queryParams.createQueryString()), format);
    }

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

    public GitHubPublicKey getEnvironmentPublicKey(Repository repository, String environmentName) throws IOException {
        return getEnvironmentPublicKey(repository.getId(), environmentName, LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentPublicKey(Repository repository, String environmentName,
                                         ReturnFormat format) throws IOException {
        return getEnvironmentPublicKey(repository.getId(), environmentName, format);
    }

    public GitHubPublicKey getEnvironmentPublicKey(long repositoryId, String environmentName) throws IOException {
        return returnPublicKey(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + ACTIONS_SECRETS_PUBLIC_KEY_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentPublicKey(long repositoryId, String environmentName, ReturnFormat format) throws IOException {
        return returnPublicKey(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + ACTIONS_SECRETS_PUBLIC_KEY_PATH), format);
    }

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

    public Secret getEnvironmentSecret(Repository repository, String environmentName, String secretName) throws IOException {
        return getEnvironmentSecret(repository.getId(), environmentName, secretName, LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                      ReturnFormat format) throws IOException {
        return getEnvironmentSecret(repository.getId(), environmentName, secretName, format);
    }

    public Secret getEnvironmentSecret(long repositoryId, String environmentName, String secretName) throws IOException {
        return returnSecret(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName), LIBRARY_OBJECT);
    }

    public <T> T getEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                      ReturnFormat format) throws IOException {
        return returnSecret(sendGetRequest(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName), format);
    }

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

    public boolean createEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getId(), environmentName, secretName, secretValue, publicKey);
    }

    public boolean createEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        long id = repository.getId();
        return workWithSecret(id, environmentName, secretName, secretValue, getEnvironmentPublicKey(id, environmentName));
    }

    public boolean createEnvironmentSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                           GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, publicKey);
    }

    public boolean createEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, getEnvironmentPublicKey(repositoryId,
                environmentName));
    }

    public boolean updateEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue, GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repository.getId(), environmentName, secretName, secretValue, publicKey);
    }

    public boolean updateEnvironmentSecret(Repository repository, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        long id = repository.getId();
        return workWithSecret(id, environmentName, secretName, secretValue, getEnvironmentPublicKey(id, environmentName));
    }

    public boolean updateEnvironmentSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                           GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, publicKey);
    }

    public boolean updateEnvironmentSecret(long repositoryId, String environmentName, String secretName,
                                           String secretValue) throws Exception {
        return workWithSecret(repositoryId, environmentName, secretName, secretValue, getEnvironmentPublicKey(repositoryId,
                environmentName));
    }

    private boolean workWithSecret(long repositoryId, String environmentName, String secretName, String secretValue,
                                   GitHubPublicKey publicKey) throws Exception {
        return workWithSecret(REPOSITORIES_QUERY_PATH + repositoryId + ENVIRONMENTS_PATH +
                environmentName + SECRETS_PATH + "/" + secretName, secretValue, publicKey);
    }

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

    public boolean deleteEnvironmentSecret(Repository repository, String environmentName, String secretName) {
        return deleteEnvironmentSecret(repository.getId(), environmentName, secretName);
    }

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
