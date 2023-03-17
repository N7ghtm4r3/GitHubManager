package com.tecknobit.githubmanager.secretscanning;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlert;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlert.SecretScanningAlertState;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlertLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubSecretScanningManager} class is useful to manage all GitHub's secret scanning endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning">
 * Secret scanning</a>
 * @see GitHubManager
 **/
public class GitHubSecretScanningManager extends GitHubManager {

    /**
     * {@code SECRET_SCANNING_ALERTS_PATH} constant for {@code "/secret-scanning/alerts"} path
     **/
    public static final String SECRET_SCANNING_ALERTS_PATH = "/secret-scanning/alerts";

    /**
     * {@code LOCATIONS_PATH} constant for {@code "/locations"} path
     **/
    public static final String LOCATIONS_PATH = "/locations";

    /**
     * Constructor to init a {@link GitHubSecretScanningManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSecretScanningManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSecretScanningManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSecretScanningManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSecretScanningManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSecretScanningManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSecretScanningManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSecretScanningManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSecretScanningManager} <br>
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
    public GitHubSecretScanningManager() {
        super();
    }

    public ArrayList<SecretScanningAlert> getEnterpriseSecretScanningAlerts(String enterprise) throws IOException {
        return getEnterpriseSecretScanningAlerts(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseSecretScanningAlerts(String enterprise, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise
                + SECRET_SCANNING_ALERTS_PATH), format);
    }

    public ArrayList<SecretScanningAlert> getEnterpriseSecretScanningAlerts(String enterprise,
                                                                            Params queryParams) throws IOException {
        return getEnterpriseSecretScanningAlerts(enterprise, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseSecretScanningAlerts(String enterprise, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise
                + SECRET_SCANNING_ALERTS_PATH + queryParams.createQueryString()), format);
    }

    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(Organization org) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretScanningAlerts(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), format);
    }

    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(String org) throws IOException {
        return getOrganizationSecretScanningAlerts(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretScanningAlerts(String org, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ORGS_PATH + org + SECRET_SCANNING_ALERTS_PATH), format);
    }

    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(Organization org,
                                                                              Params queryParams) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretScanningAlerts(Organization org, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), queryParams, format);
    }

    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(String org,
                                                                              Params queryParams) throws IOException {
        return getOrganizationSecretScanningAlerts(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationSecretScanningAlerts(String org, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ORGS_PATH + org + SECRET_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(Repository repository) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretScanningAlerts(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(String owner, String repo) throws IOException {
        return getRepositorySecretScanningAlerts(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretScanningAlerts(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH), format);
    }

    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(Repository repository,
                                                                            Params queryParams) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretScanningAlerts(Repository repository, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                format);
    }

    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(String owner, String repo,
                                                                            Params queryParams) throws IOException {
        return getRepositorySecretScanningAlerts(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositorySecretScanningAlerts(String owner, String repo, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                SECRET_SCANNING_ALERTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a secret scanning alerts list
     *
     * @param secretScanningAlertsResponse: obtained from GitHub's response
     * @param format:                       return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSecretScanningAlerts(String secretScanningAlertsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(secretScanningAlertsResponse);
            case LIBRARY_OBJECT:
                ArrayList<SecretScanningAlert> secretScanningAlerts = new ArrayList<>();
                JSONArray jSecretScanningAlerts = new JSONArray(secretScanningAlertsResponse);
                for (int j = 0; j < jSecretScanningAlerts.length(); j++)
                    secretScanningAlerts.add(new SecretScanningAlert(jSecretScanningAlerts.getJSONObject(j)));
                return (T) secretScanningAlerts;
            default:
                return (T) secretScanningAlertsResponse;
        }
    }

    public SecretScanningAlert getSecretScanningAlert(Repository repository, long alertNumber) throws IOException {
        return getSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlert(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    public SecretScanningAlert getSecretScanningAlert(String owner, String repo, long alertNumber) throws IOException {
        return getSecretScanningAlert(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlert(String owner, String repo, long alertNumber, ReturnFormat format) throws IOException {
        return returnSecretScanningAlert(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber), format);
    }

    public SecretScanningAlert updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                           SecretScanningAlertState state, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                format);
    }

    public SecretScanningAlert updateSecretScanningAlert(Repository repository, long alertNumber,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(Repository repository, long alertNumber, SecretScanningAlertState state,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                format);
    }

    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                           SecretScanningAlertState state, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, format);
    }

    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, long alertNumber,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(String owner, String repo, long alertNumber, SecretScanningAlertState state,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, null, format);
    }

    public SecretScanningAlert updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                                         SecretScanningAlertState state,
                                                         Params bodyParams) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                           SecretScanningAlertState state, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, format);
    }

    public SecretScanningAlert updateSecretScanningAlert(Repository repository, long alertNumber,
                                                         SecretScanningAlertState state,
                                                         Params bodyParams) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(Repository repository, long alertNumber, SecretScanningAlertState state,
                                           Params bodyParams, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, format);
    }

    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                                         SecretScanningAlertState state, Params bodyParams) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                           SecretScanningAlertState state, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, bodyParams, format);
    }

    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, long alertNumber,
                                                         SecretScanningAlertState state, Params bodyParams) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateSecretScanningAlert(String owner, String repo, long alertNumber, SecretScanningAlertState state,
                                           Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("state", state);
        return returnSecretScanningAlert(sendPatchRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber, bodyParams), format);
    }

    /**
     * Method to create a secret scanning alert
     *
     * @param secretScanningAlertResponse: obtained from GitHub's response
     * @param format:                      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSecretScanningAlert(String secretScanningAlertResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretScanningAlertResponse);
            case LIBRARY_OBJECT:
                return (T) new SecretScanningAlert(new JSONObject(secretScanningAlertResponse));
            default:
                return (T) secretScanningAlertResponse;
        }
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  SecretScanningAlert alert) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(Repository repository, SecretScanningAlert alert,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  long alertNumber) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(Repository repository, long alertNumber,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  SecretScanningAlert alert) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(String owner, String repo, SecretScanningAlert alert,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  long alertNumber) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(String owner, String repo, long alertNumber,
                                                 ReturnFormat format) throws IOException {
        return returnSecretScanningAlertLocations(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber + LOCATIONS_PATH), format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  SecretScanningAlert alert,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(Repository repository, SecretScanningAlert alert, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository, long alertNumber,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                queryParams, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(Repository repository, long alertNumber, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                queryParams, format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  SecretScanningAlert alert,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(String owner, String repo, SecretScanningAlert alert, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), queryParams, format);
    }

    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  long alertNumber,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alertNumber, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getSecretScanningAlertLocations(String owner, String repo, long alertNumber, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnSecretScanningAlertLocations(sendGetRequest(REPOS_PATH + owner + "/" + repo +
                        SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber + LOCATIONS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a secret scanning alert locations list
     *
     * @param secretScanningAlertLocationsResponse: obtained from GitHub's response
     * @param format:                               return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert locations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSecretScanningAlertLocations(String secretScanningAlertLocationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(secretScanningAlertLocationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<SecretScanningAlertLocation> locations = new ArrayList<>();
                JSONArray jLocations = new JSONArray(secretScanningAlertLocationsResponse);
                for (int j = 0; j < jLocations.length(); j++)
                    locations.add(new SecretScanningAlertLocation(jLocations.getJSONObject(j)));
                return (T) locations;
            default:
                return (T) secretScanningAlertLocationsResponse;
        }
    }

}
