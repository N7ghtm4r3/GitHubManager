package com.tecknobit.githubmanager.secretscanning;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlert;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlert.Resolution;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlert.SecretScanningAlertState;
import com.tecknobit.githubmanager.secretscanning.records.SecretScanningAlertLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PATCH;
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

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an enterprise, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-enterprise">
     * List secret scanning alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getEnterpriseSecretScanningAlerts(String enterprise) throws IOException {
        return getEnterpriseSecretScanningAlerts(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an enterprise, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-enterprise">
     * List secret scanning alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/secret-scanning/alerts")
    public <T> T getEnterpriseSecretScanningAlerts(String enterprise, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise
                + SECRET_SCANNING_ALERTS_PATH), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an enterprise, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-enterprise">
     * List secret scanning alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getEnterpriseSecretScanningAlerts(String enterprise,
                                                                            Params queryParams) throws IOException {
        return getEnterpriseSecretScanningAlerts(enterprise, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an enterprise, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-enterprise">
     * List secret scanning alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/secret-scanning/alerts")
    public <T> T getEnterpriseSecretScanningAlerts(String enterprise, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise
                + SECRET_SCANNING_ALERTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org: the organization from fetch the list
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(Organization org) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public <T> T getOrganizationSecretScanningAlerts(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(String org) throws IOException {
        return getOrganizationSecretScanningAlerts(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public <T> T getOrganizationSecretScanningAlerts(String org, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ORGS_PATH + org + SECRET_SCANNING_ALERTS_PATH), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(Organization org,
                                                                              Params queryParams) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public <T> T getOrganizationSecretScanningAlerts(Organization org, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return getOrganizationSecretScanningAlerts(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getOrganizationSecretScanningAlerts(String org,
                                                                              Params queryParams) throws IOException {
        return getOrganizationSecretScanningAlerts(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repositories in an organization, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
     * List secret scanning alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/secret-scanning/alerts")
    public <T> T getOrganizationSecretScanningAlerts(String org, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(ORGS_PATH + org + SECRET_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository from fetch the list
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(Repository repository) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public <T> T getRepositorySecretScanningAlerts(Repository repository, ReturnFormat format) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(String owner, String repo) throws IOException {
        return getRepositorySecretScanningAlerts(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public <T> T getRepositorySecretScanningAlerts(String owner, String repo, ReturnFormat format) throws IOException {
        return returnSecretScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH), format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query
     *                            only searches for events before this cursor. To receive an initial cursor on your
     *                            first request, include an empty "before" query string- [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for events after this cursor. To receive an initial cursor on your first request,
     *                            include an empty "after" query string- [string]
     *                        </li>
     *                     </ul>
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(Repository repository,
                                                                            Params queryParams) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query
     *                            only searches for events before this cursor. To receive an initial cursor on your
     *                            first request, include an empty "before" query string- [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for events after this cursor. To receive an initial cursor on your first request,
     *                            include an empty "after" query string- [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public <T> T getRepositorySecretScanningAlerts(Repository repository, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getRepositorySecretScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                format);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query
     *                            only searches for events before this cursor. To receive an initial cursor on your
     *                            first request, include an empty "before" query string- [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for events after this cursor. To receive an initial cursor on your first request,
     *                            include an empty "after" query string- [string]
     *                        </li>
     *                     </ul>
     * @return secret scanning alerts as {@link ArrayList} of {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
    public ArrayList<SecretScanningAlert> getRepositorySecretScanningAlerts(String owner, String repo,
                                                                            Params queryParams) throws IOException {
        return getRepositorySecretScanningAlerts(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the secret scanning alerts for eligible repository, from newest to oldest.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> set to open or resolved to only list secret scanning alerts in a
     *                            specific state, constants available {@link SecretScanningAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "secret_type"} -> a comma-separated list of secret types to return. By default
     *                            all secret types are returned. See "Secret scanning patterns" for a complete list of
     *                            secret types - [string]
     *                        </li>
     *                        <li>
     *                            {@code "resolution"} -> a comma-separated list of resolutions. Only secret scanning
     *                            alerts with one of these resolutions are listed, constants
     *                            available {@link Resolution} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. created means when the alert
     *                            was created. updated means when the alert was updated or resolved, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants
     *                            available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query
     *                            only searches for events before this cursor. To receive an initial cursor on your
     *                            first request, include an empty "before" query string- [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for events after this cursor. To receive an initial cursor on your first request,
     *                            include an empty "after" query string- [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
     * List secret scanning alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts")
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

    /**
     * Method to get a single secret scanning alert detected in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository from fetch the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#get-a-secret-scanning-alert">
     * Get a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert getSecretScanningAlert(Repository repository, long alertNumber) throws IOException {
        return getSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single secret scanning alert detected in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository from fetch the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#get-a-secret-scanning-alert">
     * Get a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T getSecretScanningAlert(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    /**
     * Method to get a single secret scanning alert detected in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#get-a-secret-scanning-alert">
     * Get a secret scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert getSecretScanningAlert(String owner, String repo, long alertNumber) throws IOException {
        return getSecretScanningAlert(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single secret scanning alert detected in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#get-a-secret-scanning-alert">
     * Get a secret scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T getSecretScanningAlert(String owner, String repo, long alertNumber, ReturnFormat format) throws IOException {
        return returnSecretScanningAlert(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber), format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository where update the secret scanning alert
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository where update the secret scanning alert
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                           SecretScanningAlertState state, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository where update the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(Repository repository, long alertNumber,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository where update the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(Repository repository, long alertNumber, SecretScanningAlertState state,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param alert: the alert to update
     * @param state: sets the state of the secret scanning alert
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param alert:  the alert to update
     * @param state:  sets the state of the secret scanning alert
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                           SecretScanningAlertState state, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, long alertNumber,
                                                         SecretScanningAlertState state) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(String owner, String repo, long alertNumber, SecretScanningAlertState state,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, null, format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository where update the secret scanning alert
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                           resolving the alert - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                           updated or deleted. Must be null when changing state to open - [string or null]
     *                       </li>
     *                    </ul>
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                                         SecretScanningAlertState state,
                                                         Params bodyParams) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository: the repository where update the secret scanning alert
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                           resolving the alert - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                           updated or deleted. Must be null when changing state to open - [string or null]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(Repository repository, SecretScanningAlert alert,
                                           SecretScanningAlertState state, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository where update the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                            resolving the alert - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                            updated or deleted. Must be null when changing state to open - [string or null]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(Repository repository, long alertNumber,
                                                         SecretScanningAlertState state,
                                                         Params bodyParams) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param repository:  the repository where update the secret scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                            resolving the alert - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                            updated or deleted. Must be null when changing state to open - [string or null]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(Repository repository, long alertNumber, SecretScanningAlertState state,
                                           Params bodyParams, ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                           resolving the alert - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                           updated or deleted. Must be null when changing state to open - [string or null]
     *                       </li>
     *                    </ul>
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                                         SecretScanningAlertState state, Params bodyParams) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param alert:      the alert to update
     * @param state:      sets the state of the secret scanning alert
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                           resolving the alert - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                           updated or deleted. Must be null when changing state to open - [string or null]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public <T> T updateSecretScanningAlert(String owner, String repo, SecretScanningAlert alert,
                                           SecretScanningAlertState state, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateSecretScanningAlert(owner, repo, alert.getNumber(), state, bodyParams, format);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                            resolving the alert - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                            updated or deleted. Must be null when changing state to open - [string or null]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert as {@link SecretScanningAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
    public SecretScanningAlert updateSecretScanningAlert(String owner, String repo, long alertNumber,
                                                         SecretScanningAlertState state, Params bodyParams) throws IOException {
        return updateSecretScanningAlert(owner, repo, alertNumber, state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a secret scanning alert in an eligible repository.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the secret scanning alert
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "resolution"} -> <b>required when the state is resolved</b>. The reason for
     *                            resolving the alert - [string or null]
     *                        </li>
     *                        <li>
     *                            {@code "resolution_comment"} -> an optional comment when closing an alert. Cannot be
     *                            updated or deleted. Must be null when changing state to open - [string or null]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
     * Update a secret scanning alert</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}")
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

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param alert:      the alert from fetch the list
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  SecretScanningAlert alert) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param alert:      the alert from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(Repository repository, SecretScanningAlert alert,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  long alertNumber) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(Repository repository, long alertNumber,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param alert: the alert from fetch the list
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  SecretScanningAlert alert) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param alert:  the alert from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(String owner, String repo, SecretScanningAlert alert,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  long alertNumber) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(String owner, String repo, long alertNumber,
                                                 ReturnFormat format) throws IOException {
        return returnSecretScanningAlertLocations(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + SECRET_SCANNING_ALERTS_PATH + "/" + alertNumber + LOCATIONS_PATH), format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alert:       the alert from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository,
                                                                                  SecretScanningAlert alert,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alert:       the alert from fetch the list
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
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(Repository repository, SecretScanningAlert alert, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(Repository repository, long alertNumber,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
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
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(Repository repository, long alertNumber, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alert:       the alert from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  SecretScanningAlert alert,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alert:       the alert from fetch the list
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
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public <T> T getSecretScanningAlertLocations(String owner, String repo, SecretScanningAlert alert, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alert.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return secret scanning alert locations as {@link ArrayList} of {@link SecretScanningAlertLocation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
    public ArrayList<SecretScanningAlertLocation> getSecretScanningAlertLocations(String owner, String repo,
                                                                                  long alertNumber,
                                                                                  Params queryParams) throws IOException {
        return getSecretScanningAlertLocations(owner, repo, alertNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all locations for a given secret scanning alert for an eligible repository
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are an
     * organization owner or a security manager <br>
     * GitHub Apps must have the secret_scanning_alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code
     *                     scanning alert within GitHub, and in the number field in the response from the
     *                     GET /repos/{owner}/{repo}/code-scanning/alerts operation
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
     * @return secret scanning alert locations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
     * List locations for a secret scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/secret-scanning/alerts/{alert_number}/locations")
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
