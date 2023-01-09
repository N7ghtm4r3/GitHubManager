package com.tecknobit.githubmanager.codescanning;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codescanning.records.ScanningAlert;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codescanning.records.ScanningAlert.State;

/**
 * The {@code GithubCodeScanningManager} class is useful to manage all GitHub's code scanning endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning">
 * Code Scanning</a>
 * @see GitHubManager
 **/
public class GithubCodeScanningManager extends GitHubManager {

    /**
     * {@code CODE_SCANNING_PATH} constant for {@code "/code-scanning/"} path
     **/
    public static final String CODE_SCANNING_PATH = "/code-scanning/";

    /**
     * {@code CODE_SCANNING_ALERTS_PATH} constant for {@code "/code-scanning/alerts"} path
     **/
    public static final String CODE_SCANNING_ALERTS_PATH = CODE_SCANNING_PATH + "alerts";

    /**
     * Constructor to init a {@link GithubCodeScanningManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GithubCodeScanningManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GithubCodeScanningManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GithubCodeScanningManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GithubCodeScanningManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GithubCodeScanningManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubCodeScanningManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GithubCodeScanningManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubCodeScanningManager} <br>
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
    public GithubCodeScanningManager() {
        super();
    }

    public Collection<ScanningAlert> getEnterpriseScanningAlerts(String enterprise) throws IOException {
        return getEnterpriseScanningAlerts(enterprise, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseScanningAlerts(String enterprise, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    public Collection<ScanningAlert> getEnterpriseScanningAlerts(String enterprise,
                                                                 Params queryParams) throws IOException {
        return getEnterpriseScanningAlerts(enterprise, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseScanningAlerts(String enterprise, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise + CODE_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    public Collection<ScanningAlert> getOrganizationScanningAlerts(Organization org) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationScanningAlerts(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), format);
    }

    public Collection<ScanningAlert> getOrganizationScanningAlerts(String org) throws IOException {
        return getOrganizationScanningAlerts(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationScanningAlerts(String org, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ORGS_PATH + org + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    public Collection<ScanningAlert> getOrganizationScanningAlerts(Organization org, Params queryParams) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationScanningAlerts(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), queryParams, format);
    }

    public Collection<ScanningAlert> getOrganizationScanningAlerts(String org, Params queryParams) throws IOException {
        return getOrganizationScanningAlerts(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationScanningAlerts(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ORGS_PATH + org + CODE_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    public Collection<ScanningAlert> getRepositoryScanningAlerts(Repository repository) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryScanningAlerts(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public Collection<ScanningAlert> getRepositoryScanningAlerts(String owner, String repo) throws IOException {
        return getRepositoryScanningAlerts(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryScanningAlerts(String owner, String repo, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    public Collection<ScanningAlert> getRepositoryScanningAlerts(Repository repository,
                                                                 Params queryParams) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryScanningAlerts(Repository repository, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public Collection<ScanningAlert> getRepositoryScanningAlerts(String owner, String repo,
                                                                 Params queryParams) throws IOException {
        return getRepositoryScanningAlerts(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryScanningAlerts(String owner, String repo, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a scanning alerts list
     *
     * @param scanningAlertsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnScanningAlerts(String scanningAlertsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(scanningAlertsResponse);
            case LIBRARY_OBJECT:
                ArrayList<ScanningAlert> alerts = new ArrayList<>();
                JSONArray jAlerts = new JSONArray(scanningAlertsResponse);
                for (int j = 0; j < jAlerts.length(); j++)
                    alerts.add(new ScanningAlert(jAlerts.getJSONObject(j)));
                return (T) alerts;
            default:
                return (T) scanningAlertsResponse;
        }
    }

    public ScanningAlert getScanningAlert(Repository repository, long alertNumber) throws IOException {
        return getScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getScanningAlert(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    public ScanningAlert getScanningAlert(String owner, String repo, long alertNumber) throws IOException {
        return getScanningAlert(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getScanningAlert(String owner, String repo, long alertNumber, ReturnFormat format) throws IOException {
        return returnScanningAlert(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber), format);
    }

    public ScanningAlert updateScanningAlert(Repository repository, long alertNumber, State state) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                LIBRARY_OBJECT);
    }

    public <T> T updateScanningAlert(Repository repository, long alertNumber, State state,
                                     ReturnFormat format) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state, format);
    }

    public ScanningAlert updateScanningAlert(String owner, String repo, long alertNumber, State state) throws IOException {
        return updateScanningAlert(owner, repo, alertNumber, state, LIBRARY_OBJECT);
    }

    public <T> T updateScanningAlert(String owner, String repo, long alertNumber, State state,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("state", state);
        return returnScanningAlert(sendPatchRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber, payload), format);
    }

    public ScanningAlert updateScanningAlert(Repository repository, long alertNumber, State state,
                                             Params bodyParams) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateScanningAlert(Repository repository, long alertNumber, State state, Params bodyParams,
                                     ReturnFormat format) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, format);
    }

    public ScanningAlert updateScanningAlert(String owner, String repo, long alertNumber, State state,
                                             Params bodyParams) throws IOException {
        return updateScanningAlert(owner, repo, alertNumber, state, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateScanningAlert(String owner, String repo, long alertNumber, State state, Params bodyParams,
                                     ReturnFormat format) throws IOException {
        bodyParams.addParam("state", state);
        return returnScanningAlert(sendPatchRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber, bodyParams), format);
    }

    /**
     * Method to create a scanning alert
     *
     * @param scanningAlertResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     **/
    @Returner
    private <T> T returnScanningAlert(String scanningAlertResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(scanningAlertResponse);
            case LIBRARY_OBJECT:
                return (T) new ScanningAlert(new JSONObject(scanningAlertResponse));
            default:
                return (T) scanningAlertResponse;
        }
    }

}
