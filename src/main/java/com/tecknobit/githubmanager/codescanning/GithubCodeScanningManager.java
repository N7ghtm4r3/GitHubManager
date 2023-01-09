package com.tecknobit.githubmanager.codescanning;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codescanning.records.*;
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
     * {@code INSTANCES_PATH} constant for {@code "/instances"} path
     **/
    public static final String INSTANCES_PATH = "/instances";

    /**
     * {@code CODE_SCANNING_ALERTS_PATH} constant for {@code "/code-scanning/alerts"} path
     **/
    public static final String CODE_SCANNING_ANALYSES_PATH = CODE_SCANNING_PATH + "analyses";

    /**
     * {@code CODE_SCANNING_CODEQL_DATABASES_PATH} constant for {@code "/code-scanning/codeql/databases"} path
     **/
    public static final String CODE_SCANNING_CODEQL_DATABASES_PATH = CODE_SCANNING_PATH + "codeql/databases";

    /**
     * {@code CODE_SCANNING_SARIFS_PATH} constant for {@code "/code-scanning/sarifs"} path
     **/
    public static final String CODE_SCANNING_SARIFS_PATH = CODE_SCANNING_PATH + "sarifs";

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

    public Collection<Instance> getCodeScanningInstances(Repository repository, ScanningAlert alert) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(Repository repository, ScanningAlert alert,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                format);
    }

    public Collection<Instance> getCodeScanningInstances(String owner, String repo, ScanningAlert alert) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(String owner, String repo, ScanningAlert alert,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), format);
    }

    public Collection<Instance> getCodeScanningInstances(Repository repository, long alertNumber) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    public Collection<Instance> getCodeScanningInstances(String owner, String repo, long alertNumber) throws IOException {
        return getCodeScanningInstances(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(String owner, String repo, long alertNumber,
                                          ReturnFormat format) throws IOException {
        return returnInstances(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber + INSTANCES_PATH), format);
    }

    public Collection<Instance> getCodeScanningInstances(Repository repository, ScanningAlert alert,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(Repository repository, ScanningAlert alert, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, format);
    }

    public Collection<Instance> getCodeScanningInstances(String owner, String repo, ScanningAlert alert,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(String owner, String repo, ScanningAlert alert, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), queryParams, format);
    }

    public Collection<Instance> getCodeScanningInstances(Repository repository, long alertNumber,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(Repository repository, long alertNumber, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, queryParams,
                format);
    }

    public Collection<Instance> getCodeScanningInstances(String owner, String repo, long alertNumber,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(owner, repo, alertNumber, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningInstances(String owner, String repo, long alertNumber, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnInstances(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber + INSTANCES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an instances list
     *
     * @param instancesResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnInstances(String instancesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(instancesResponse);
            case LIBRARY_OBJECT:
                ArrayList<Instance> instances = new ArrayList<>();
                JSONArray jInstances = new JSONArray(instancesResponse);
                for (int j = 0; j < jInstances.length(); j++)
                    instances.add(new Instance(jInstances.getJSONObject(j)));
                return (T) instances;
            default:
                return (T) instancesResponse;
        }
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalyses(Repository repository) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalyses(Repository repository, ReturnFormat format) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalyses(String owner, String repo) throws IOException {
        return getCodeScanningAnalyses(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalyses(String owner, String repo, ReturnFormat format) throws IOException {
        return returnAnalyses(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ANALYSES_PATH),
                format);
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalyses(Repository repository,
                                                                Params queryParams) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalyses(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalyses(String owner, String repo,
                                                                Params queryParams) throws IOException {
        return getCodeScanningAnalyses(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalyses(String owner, String repo, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return returnAnalyses(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ANALYSES_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create an analyses list
     *
     * @param analysesResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return analyses list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnAnalyses(String analysesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(analysesResponse);
            case LIBRARY_OBJECT:
                ArrayList<ScanningAnalysis> analyses = new ArrayList<>();
                JSONArray jAnalyses = new JSONArray(analysesResponse);
                for (int j = 0; j < jAnalyses.length(); j++)
                    analyses.add(new ScanningAnalysis(jAnalyses.getJSONObject(j)));
                return (T) analyses;
            default:
                return (T) analysesResponse;
        }
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalysis(Repository repository, long analysisId) throws IOException {
        return getCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalysis(Repository repository, long analysisId, ReturnFormat format) throws IOException {
        return getCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId, format);
    }

    public Collection<ScanningAnalysis> getCodeScanningAnalysis(String owner, String repo,
                                                                long analysisId) throws IOException {
        return getCodeScanningAnalysis(owner, repo, analysisId, LIBRARY_OBJECT);
    }

    public <T> T getCodeScanningAnalysis(String owner, String repo, long analysisId,
                                         ReturnFormat format) throws IOException {
        String analysisResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ANALYSES_PATH
                + "/" + analysisId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(analysisResponse);
            case LIBRARY_OBJECT:
                return (T) new ScanningAnalysis(new JSONObject(analysisResponse));
            default:
                return (T) analysisResponse;
        }
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository,
                                                               ScanningAnalysis analysis) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo,
                                                               ScanningAnalysis analysis) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, long analysisId) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(Repository repository, long analysisId, ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId, format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo,
                                                               long analysisId) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysisId, LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(String owner, String repo, long analysisId,
                                            ReturnFormat format) throws IOException {
        return returnScanningAnalysisDeletion(sendDeleteRequest(REPOS_PATH + owner + "/" + repo
                + CODE_SCANNING_ANALYSES_PATH + "/" + analysisId), format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                confirmDelete, LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                confirmDelete, format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), confirmDelete, LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), confirmDelete, format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, long analysisId,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                confirmDelete, LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(Repository repository, long analysisId, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                confirmDelete, format);
    }

    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo, long analysisId,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysisId, confirmDelete, LIBRARY_OBJECT);
    }

    public <T> T deleteCodeScanningAnalysis(String owner, String repo, long analysisId, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return returnScanningAnalysisDeletion(sendDeleteRequest(REPOS_PATH + owner + "/" + repo
                + CODE_SCANNING_ANALYSES_PATH + "/" + analysisId), format);
    }

    /**
     * Method to create an analysis deletion
     *
     * @param analysisDeletionResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     **/
    @Returner
    private <T> T returnScanningAnalysisDeletion(String analysisDeletionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(analysisDeletionResponse);
            case LIBRARY_OBJECT:
                return (T) new ScanningAnalysisDeletion(new JSONObject(analysisDeletionResponse));
            default:
                return (T) analysisDeletionResponse;
        }
    }

    public Collection<CodeQL> getCodeQLDatabases(Repository repository) throws IOException {
        return getCodeQLDatabases(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getCodeQLDatabases(Repository repository, ReturnFormat format) throws IOException {
        return getCodeQLDatabases(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public Collection<CodeQL> getCodeQLDatabases(String owner, String repo) throws IOException {
        return getCodeQLDatabases(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getCodeQLDatabases(String owner, String repo, ReturnFormat format) throws IOException {
        String codeQLDatabasesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo +
                CODE_SCANNING_CODEQL_DATABASES_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(codeQLDatabasesResponse);
            case LIBRARY_OBJECT:
                ArrayList<CodeQL> databases = new ArrayList<>();
                JSONArray jDatabases = new JSONArray(codeQLDatabasesResponse);
                for (int j = 0; j < jDatabases.length(); j++)
                    databases.add(new CodeQL(jDatabases.getJSONObject(j)));
                return (T) databases;
            default:
                return (T) codeQLDatabasesResponse;
        }
    }

    public CodeQL getCodeQLDatabase(Repository repository, String language) throws IOException {
        return getCodeQLDatabase(repository.getOwner().getLogin(), repository.getName(), language, LIBRARY_OBJECT);
    }

    public <T> T getCodeQLDatabase(Repository repository, String language, ReturnFormat format) throws IOException {
        return getCodeQLDatabase(repository.getOwner().getLogin(), repository.getName(), language, format);
    }

    public CodeQL getCodeQLDatabase(String owner, String repo, String language) throws IOException {
        return getCodeQLDatabase(owner, repo, language, LIBRARY_OBJECT);
    }

    public <T> T getCodeQLDatabase(String owner, String repo, String language, ReturnFormat format) throws IOException {
        String codeQLDatabaseResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo +
                CODE_SCANNING_CODEQL_DATABASES_PATH + "/" + language);
        switch (format) {
            case JSON:
                return (T) new JSONObject(codeQLDatabaseResponse);
            case LIBRARY_OBJECT:
                return (T) new CodeQL(new JSONObject(codeQLDatabaseResponse));
            default:
                return (T) codeQLDatabaseResponse;
        }
    }


    // TODO: 09/01/2023 UPLOAD SARIF METHODS 


    public SARIFUpload getSARIFUploadInformation(Repository repository, SARIFData SARIF) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIF.getId(),
                LIBRARY_OBJECT);
    }

    public <T> T getSARIFUploadInformation(Repository repository, SARIFData SARIF, ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIF.getId(), format);
    }

    public SARIFUpload getSARIFUploadInformation(String owner, String repo, SARIFData SARIF) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIF.getId(), LIBRARY_OBJECT);
    }

    public <T> T getSARIFUploadInformation(String owner, String repo, SARIFData SARIF,
                                           ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIF.getId(), format);
    }

    public SARIFUpload getSARIFUploadInformation(Repository repository, String SARIFId) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIFId, LIBRARY_OBJECT);
    }

    public <T> T getSARIFUploadInformation(Repository repository, String SARIFId, ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIFId, format);
    }

    public SARIFUpload getSARIFUploadInformation(String owner, String repo, String SARIFId) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIFId, LIBRARY_OBJECT);
    }

    public <T> T getSARIFUploadInformation(String owner, String repo, String SARIFId,
                                           ReturnFormat format) throws IOException {
        String SARIFResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_SARIFS_PATH + "/"
                + SARIFId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(SARIFResponse);
            case LIBRARY_OBJECT:
                return (T) new SARIFUpload(new JSONObject(SARIFResponse));
            default:
                return (T) SARIFResponse;
        }
    }

}
