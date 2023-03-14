package com.tecknobit.githubmanager.dependabot.alerts;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.DependabotAlertState;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.DependabotDismissedReason;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.Dependency.Scope;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.Ecosystem;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PATCH;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubDependabotAlertsManager} class is useful to manage all GitHub's alerts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts">
 * Dependabot alerts</a>
 * @see GitHubManager
 **/
public class GitHubDependabotAlertsManager extends GitHubManager {

    /**
     * {@code DEPENDABOT_ALERTS_PATH} constant for {@code "/dependabot/alerts"} path
     **/
    public static final String DEPENDABOT_ALERTS_PATH = "/dependabot/alerts";

    /**
     * Constructor to init a {@link GitHubDependabotAlertsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDependabotAlertsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDependabotAlertsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDependabotAlertsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDependabotAlertsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDependabotAlertsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependabotAlertsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDependabotAlertsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependabotAlertsManager} <br>
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
    public GitHubDependabotAlertsManager() {
        super();
    }

    /**
     * Method to get the list of the dependabot alerts for repositories that are owned by the specified enterprise.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo
     * scope or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are
     * an organization owner or a security manager. For more information about security managers,
     * see "Managing security managers in your organization."
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
     * List Dependabot alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/dependabot/alerts")
    public ArrayList<DependabotAlert> getEnterpriseDependabotAlerts(String enterprise) throws IOException {
        return getEnterpriseDependabotAlerts(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for repositories that are owned by the specified enterprise.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo
     * scope or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are
     * an organization owner or a security manager. For more information about security managers,
     * see "Managing security managers in your organization."
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
     * List Dependabot alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/dependabot/alerts")
    public <T> T getEnterpriseDependabotAlerts(String enterprise, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(ENTERPRISES_PATH + enterprise + DEPENDABOT_ALERTS_PATH), format);
    }

    /**
     * Method to get the list of the dependabot alerts for repositories that are owned by the specified enterprise.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo
     * scope or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are
     * an organization owner or a security manager. For more information about security managers,
     * see "Managing security managers in your organization."
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
     * List Dependabot alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/dependabot/alerts")
    public ArrayList<DependabotAlert> getEnterpriseDependabotAlerts(String enterprise, Params queryParams) throws IOException {
        return getEnterpriseDependabotAlerts(enterprise, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for repositories that are owned by the specified enterprise.
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo
     * scope or {@code "security_events"} scope. Alerts are only returned for organizations in the enterprise for which you are
     * an organization owner or a security manager. For more information about security managers,
     * see "Managing security managers in your organization."
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
     * List Dependabot alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/dependabot/alerts")
    public <T> T getEnterpriseDependabotAlerts(String enterprise, Params queryParams, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(ENTERPRISES_PATH + enterprise + DEPENDABOT_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org: the organization from fetch the list
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public ArrayList<DependabotAlert> getOrganizationDependabotAlerts(Organization org) throws IOException {
        return getOrganizationDependabotAlerts(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public <T> T getOrganizationDependabotAlerts(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationDependabotAlerts(org.getLogin(), format);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public ArrayList<DependabotAlert> getOrganizationDependabotAlerts(String org) throws IOException {
        return getOrganizationDependabotAlerts(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public <T> T getOrganizationDependabotAlerts(String org, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_ALERTS_PATH), format);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public ArrayList<DependabotAlert> getOrganizationDependabotAlerts(Organization org, Params queryParams) throws IOException {
        return getOrganizationDependabotAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public <T> T getOrganizationDependabotAlerts(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationDependabotAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public ArrayList<DependabotAlert> getOrganizationDependabotAlerts(String org, Params queryParams) throws IOException {
        return getOrganizationDependabotAlerts(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for an organization. <br>
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope. <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope. <br>
     * GitHub Apps must have Dependabot alerts read permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
     * List Dependabot alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/dependabot/alerts")
    public <T> T getOrganizationDependabotAlerts(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(ORGS_PATH + org + DEPENDABOT_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public ArrayList<DependabotAlert> getRepositoryDependabotAlerts(Repository repository) throws IOException {
        return getRepositoryDependabotAlerts(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public <T> T getRepositoryDependabotAlerts(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryDependabotAlerts(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public ArrayList<DependabotAlert> getRepositoryDependabotAlerts(String owner, String repo) throws IOException {
        return getRepositoryDependabotAlerts(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public <T> T getRepositoryDependabotAlerts(String owner, String repo, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_ALERTS_PATH),
                format);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public ArrayList<DependabotAlert> getRepositoryDependabotAlerts(Repository repository,
                                                                    Params queryParams) throws IOException {
        return getRepositoryDependabotAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public <T> T getRepositoryDependabotAlerts(Repository repository, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getRepositoryDependabotAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return dependabot alerts as {@link ArrayList} of {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public ArrayList<DependabotAlert> getRepositoryDependabotAlerts(String owner, String repo,
                                                                    Params queryParams) throws IOException {
        return getRepositoryDependabotAlerts(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the dependabot alerts for a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> comma-separated list of states. If specified, only alerts with these
     *                            states will be returned, constants available {@link DependabotAlertState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> a comma-separated list of severities. If specified, only alerts
     *                            with these severities will be returned, constants available {@link SeverityLevel} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "ecosystem"} -> a comma-separated list of ecosystems. If specified, only alerts for
     *                            these ecosystems will be returned, constants available {@link Ecosystem}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "package"} -> a comma-separated list of package names. If specified, only alerts for
     *                            these packages will be returned - [string]
     *                        </li>
     *                        <li>
     *                            {@code "scope"} -> the scope of the vulnerable dependency. If specified, only alerts
     *                            with this scope will be returned, constants available {@link Scope} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results. created means when the alert
     *                            was created. updated means when the alert's state last changed,
     *                            constants available {@link Sort}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by,
     *                            constants available {@link Directions}- [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
     * List Dependabot alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts")
    public <T> T getRepositoryDependabotAlerts(String owner, String repo, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDABOT_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a dependabot alerts list
     *
     * @param alertsListResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return dependabot alerts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDependabotAlertsList(String alertsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(alertsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<DependabotAlert> alerts = new ArrayList<>();
                JSONArray jAlerts = new JSONArray(alertsListResponse);
                for (int j = 0; j < jAlerts.length(); j++)
                    alerts.add(new DependabotAlert(jAlerts.getJSONObject(j)));
                return (T) alerts;
            default:
                return (T) alertsListResponse;
        }
    }

    /**
     * Method to get a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
     * Get a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert getDependabotAlert(Repository repository, long alertNumber) throws IOException {
        return getDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
     * Get a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T getDependabotAlert(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    /**
     * Method to get a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
     * Get a Dependabot alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert getDependabotAlert(String owner, String repo, long alertNumber) throws IOException {
        return getDependabotAlert(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
     * Get a Dependabot alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T getDependabotAlert(String owner, String repo, long alertNumber, ReturnFormat format) throws IOException {
        return returnDependabotAlertsList(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + DEPENDABOT_ALERTS_PATH + "/" + alertNumber), format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository where update the alert
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(Repository repository, DependabotAlert alert,
                                                 DependabotAlertState state) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository where update the alert
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(Repository repository, DependabotAlert alert, DependabotAlertState state,
                                       ReturnFormat format) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository where update the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(Repository repository, long alertNumber,
                                                 DependabotAlertState state) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository where update the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(Repository repository, long alertNumber, DependabotAlertState state,
                                       ReturnFormat format) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param alert: the alert to update
     * @param state: the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(String owner, String repo, DependabotAlert alert,
                                                 DependabotAlertState state) throws IOException {
        return updateDependabotAlert(owner, repo, alert.getNumber(), state, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param alert:  the alert to update
     * @param state:  the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(String owner, String repo, DependabotAlert alert, DependabotAlertState state,
                                       ReturnFormat format) throws IOException {
        return updateDependabotAlert(owner, repo, alert.getNumber(), state, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(String owner, String repo, long alertNumber,
                                                 DependabotAlertState state) throws IOException {
        return updateDependabotAlert(owner, repo, alertNumber, state, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(String owner, String repo, long alertNumber, DependabotAlertState state,
                                       ReturnFormat format) throws IOException {
        return updateDependabotAlert(owner, repo, alertNumber, state, null, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository where update the alert
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                           A reason for dismissing the alert, constants available
     *                           {@link DependabotDismissedReason} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                           alert - [string]
     *                       </li>
     *                    </ul>
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(Repository repository, DependabotAlert alert, DependabotAlertState state,
                                                 Params bodyParams) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository: the repository where update the alert
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                           A reason for dismissing the alert, constants available
     *                           {@link DependabotDismissedReason} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                           alert - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(Repository repository, DependabotAlert alert, DependabotAlertState state,
                                       Params bodyParams, ReturnFormat format) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(), state,
                bodyParams, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository where update the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                            A reason for dismissing the alert, constants available
     *                            {@link DependabotDismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(Repository repository, long alertNumber, DependabotAlertState state,
                                                 Params bodyParams) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param repository:  the repository where update the alert
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                            A reason for dismissing the alert, constants available
     *                            {@link DependabotDismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(Repository repository, long alertNumber, DependabotAlertState state,
                                       Params bodyParams, ReturnFormat format) throws IOException {
        return updateDependabotAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                           A reason for dismissing the alert, constants available
     *                           {@link DependabotDismissedReason} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                           alert - [string]
     *                       </li>
     *                    </ul>
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(String owner, String repo, DependabotAlert alert,
                                                 DependabotAlertState state, Params bodyParams) throws IOException {
        return updateDependabotAlert(owner, repo, alert.getNumber(), state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param alert:      the alert to update
     * @param state:      the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                           A reason for dismissing the alert, constants available
     *                           {@link DependabotDismissedReason} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                           alert - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(String owner, String repo, DependabotAlert alert, DependabotAlertState state,
                                       Params bodyParams, ReturnFormat format) throws IOException {
        return updateDependabotAlert(owner, repo, alert.getNumber(), state, bodyParams, format);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                            A reason for dismissing the alert, constants available
     *                            {@link DependabotDismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @return dependabot alert as {@link DependabotAlert} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public DependabotAlert updateDependabotAlert(String owner, String repo, long alertNumber, DependabotAlertState state,
                                                 Params bodyParams) throws IOException {
        return updateDependabotAlert(owner, repo, alertNumber, state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a dependabot alert for a repository
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only. GitHub Apps must have Dependabot
     * alerts read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies a Dependabot alert in its repository. You can find this at the end
     *                     of the URL for a Dependabot alert within GitHub, or in number fields in the response from the
     *                     GET /repos/{owner}/{repo}/dependabot/alerts operation
     * @param state:       the state of the Dependabot alert. A dismissed_reason must be provided when setting the state to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>required when state is dismissed</b>.
     *                            A reason for dismissing the alert, constants available
     *                            {@link DependabotDismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> an optional comment associated with dismissing the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
     * Update a Dependabot alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/dependabot/alerts/{alert_number}")
    public <T> T updateDependabotAlert(String owner, String repo, long alertNumber, DependabotAlertState state,
                                       Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("state", state);
        return returnDependabotAlertsList(sendPatchRequest(REPOS_PATH + owner + "/" + repo
                + DEPENDABOT_ALERTS_PATH + "/" + alertNumber, bodyParams), format);
    }

    /**
     * Method to create a dependabot alert
     *
     * @param alertResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return dependabot alert as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDependabotAlert(String alertResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(alertResponse);
            case LIBRARY_OBJECT:
                return (T) new DependabotAlert(new JSONObject(alertResponse));
            default:
                return (T) alertResponse;
        }
    }

}
