package com.tecknobit.githubmanager.codescanning;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codescanning.records.*;
import com.tecknobit.githubmanager.codescanning.records.ScanningAlert.DismissedReason;
import com.tecknobit.githubmanager.codescanning.records.ScanningAlert.Rule.Severity;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Scanner;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codescanning.records.ScanningAlert.State;
import static java.nio.charset.StandardCharsets.UTF_8;

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
    public GithubCodeScanningManager() {
        super();
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an enterprise.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
     * List code scanning alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/code-scanning/alerts")
    public Collection<ScanningAlert> getEnterpriseScanningAlerts(String enterprise) throws IOException {
        return getEnterpriseScanningAlerts(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an enterprise.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
     * List code scanning alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/code-scanning/alerts")
    public <T> T getEnterpriseScanningAlerts(String enterprise, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an enterprise.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
     * List code scanning alerts for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/code-scanning/alerts")
    public Collection<ScanningAlert> getEnterpriseScanningAlerts(String enterprise,
                                                                 Params queryParams) throws IOException {
        return getEnterpriseScanningAlerts(enterprise, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an enterprise.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be a member of the enterprise, and you must use an access token with the repo scope
     * or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
     * List code scanning alerts for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/code-scanning/alerts")
    public <T> T getEnterpriseScanningAlerts(String enterprise, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ENTERPRISES_PATH + enterprise + CODE_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org: the organization from fetch the list
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public Collection<ScanningAlert> getOrganizationScanningAlerts(Organization org) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public <T> T getOrganizationScanningAlerts(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), format);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public Collection<ScanningAlert> getOrganizationScanningAlerts(String org) throws IOException {
        return getOrganizationScanningAlerts(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public <T> T getOrganizationScanningAlerts(String org, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ORGS_PATH + org + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public Collection<ScanningAlert> getOrganizationScanningAlerts(Organization org, Params queryParams) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public <T> T getOrganizationScanningAlerts(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationScanningAlerts(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public Collection<ScanningAlert> getOrganizationScanningAlerts(String org, Params queryParams) throws IOException {
        return getOrganizationScanningAlerts(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts for the default branch for all eligible repositories in an organization.
     * Eligible repositories are repositories that are owned by organizations that you own or for which you are a security manager.
     * For more information, see "Managing security managers in your organization."
     * To use this endpoint, you must be an owner or security manager for the organization, and you must use an access
     * token with the repo scope or {@code "security_events"} scope -> <b> this step is automatically made by this library. </b> <br>
     * For public repositories, you may instead use the {@code "public_repo"} scope.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
     * List code scanning alerts for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/code-scanning/alerts")
    public <T> T getOrganizationScanningAlerts(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(ORGS_PATH + org + CODE_SCANNING_ALERTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param repository: the repository from fetch the list
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public Collection<ScanningAlert> getRepositoryScanningAlerts(Repository repository) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public <T> T getRepositoryScanningAlerts(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public Collection<ScanningAlert> getRepositoryScanningAlerts(String owner, String repo) throws IOException {
        return getRepositoryScanningAlerts(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public <T> T getRepositoryScanningAlerts(String owner, String repo, ReturnFormat format) throws IOException {
        return returnScanningAlerts(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH),
                format);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public Collection<ScanningAlert> getRepositoryScanningAlerts(Repository repository,
                                                                 Params queryParams) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public <T> T getRepositoryScanningAlerts(Repository repository, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getRepositoryScanningAlerts(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alerts list as {@link Collection} of {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
    public Collection<ScanningAlert> getRepositoryScanningAlerts(String owner, String repo,
                                                                 Params queryParams) throws IOException {
        return getRepositoryScanningAlerts(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of code scanning alerts. <br>
     * To use this endpoint, you must use an access token with the {@code "security_events"} scope or, for alerts from public
     * repositories only, an access token with the {@code "public_repo"} scope -> <b> this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint.
     * The response includes a {@code "most_recent_instance"} object. This provides details of the most recent instance
     * of this alert for the default branch (or for the specified Git reference if you used ref in the request)
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "severity"} -> if specified, only code scanning alerts with this severity will
     *                            be returned, constants available {@link Severity} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alerts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
     * List code scanning alerts for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts")
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

    /**
     * Method to get a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
     * Get a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert getScanningAlert(Repository repository, long alertNumber) throws IOException {
        return getScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
     * Get a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public <T> T getScanningAlert(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    /**
     * Method to get a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
     * Get a code scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert getScanningAlert(String owner, String repo, long alertNumber) throws IOException {
        return getScanningAlert(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
     * Get a code scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public <T> T getScanningAlert(String owner, String repo, long alertNumber, ReturnFormat format) throws IOException {
        return returnScanningAlert(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber), format);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository where update the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed using the {@link #updateScanningAlert(Repository, long, State, Params)}
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert updateScanningAlert(Repository repository, long alertNumber, State state) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository where update the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed using the {@link #updateScanningAlert(Repository, long, State, Params)}
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public <T> T updateScanningAlert(Repository repository, long alertNumber, State state,
                                     ReturnFormat format) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state, format);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed using the {@link #updateScanningAlert(Repository, long, State, Params)}
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert updateScanningAlert(String owner, String repo, long alertNumber, State state) throws IOException {
        return updateScanningAlert(owner, repo, alertNumber, state, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed using the {@link #updateScanningAlert(Repository, long, State, Params)}
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public <T> T updateScanningAlert(String owner, String repo, long alertNumber, State state,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("state", state);
        return returnScanningAlert(sendPatchRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber, payload), format);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository where update the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>Required when the state is dismissed</b>. The reason for
     *                            dismissing or closing the alert, constants available {@link DismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> the dismissal comment associated with the dismissal of the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert updateScanningAlert(Repository repository, long alertNumber, State state,
                                             Params bodyParams) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository where update the scanning alert
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>Required when the state is dismissed</b>. The reason for
     *                            dismissing or closing the alert, constants available {@link DismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> the dismissal comment associated with the dismissal of the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public <T> T updateScanningAlert(Repository repository, long alertNumber, State state, Params bodyParams,
                                     ReturnFormat format) throws IOException {
        return updateScanningAlert(repository.getOwner().getLogin(), repository.getName(), alertNumber, state,
                bodyParams, format);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>Required when the state is dismissed</b>. The reason for
     *                            dismissing or closing the alert, constants available {@link DismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> the dismissal comment associated with the dismissal of the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @return scanning alert as {@link ScanningAlert} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
    public ScanningAlert updateScanningAlert(String owner, String repo, long alertNumber, State state,
                                             Params bodyParams) throws IOException {
        return updateScanningAlert(owner, repo, alertNumber, state, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the status of a single code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning alert within GitHub,
     *                     and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts operation
     * @param state:       sets the state of the code scanning alert. You must provide {@code "dismissed_reason"} when you set the state
     *                     to dismissed
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "dismissed_reason"} -> <b>Required when the state is dismissed</b>. The reason for
     *                            dismissing or closing the alert, constants available {@link DismissedReason} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "dismissed_comment"} -> the dismissal comment associated with the dismissal of the
     *                            alert - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return scanning alert as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
     * Update a code scanning alert</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}")
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

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param alert:      the alert from fetch the list
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(Repository repository, ScanningAlert alert) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param alert:      the alert from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(Repository repository, ScanningAlert alert,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param alert: the alert from fetch the list
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(String owner, String repo, ScanningAlert alert) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param alert:  the alert from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(String owner, String repo, ScanningAlert alert,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(Repository repository, long alertNumber) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(Repository repository, long alertNumber, ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(String owner, String repo, long alertNumber) throws IOException {
        return getCodeScanningInstances(owner, repo, alertNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(String owner, String repo, long alertNumber,
                                          ReturnFormat format) throws IOException {
        return returnInstances(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ALERTS_PATH
                + "/" + alertNumber + INSTANCES_PATH), format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(Repository repository, ScanningAlert alert,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(Repository repository, ScanningAlert alert, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alert.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(String owner, String repo, ScanningAlert alert,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(String owner, String repo, ScanningAlert alert, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(owner, repo, alert.getNumber(), queryParams, format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(Repository repository, long alertNumber,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public <T> T getCodeScanningInstances(Repository repository, long alertNumber, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getCodeScanningInstances(repository.getOwner().getLogin(), repository.getName(), alertNumber, queryParams,
                format);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @return code scanning instances list as {@link Collection} of {@link Instance} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
    public Collection<Instance> getCodeScanningInstances(String owner, String repo, long alertNumber,
                                                         Params queryParams) throws IOException {
        return getCodeScanningInstances(owner, repo, alertNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all instances of the specified code scanning alert. You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param alertNumber: the number that identifies an alert. You can find this at the end of the URL for a code scanning
     *                     alert within GitHub, and in the number field in the response from the GET /repos/{owner}/{repo}/code-scanning/alerts
     *                     operation
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the Git reference for the results you want to list. The ref for a branch
     *                            can be formatted either as {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return instances list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
     * List instances of a code scanning alert</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/alerts/{alert_number}/instances")
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

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @return scanning analyses as {@link Collection} of {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public Collection<ScanningAnalysis> getCodeScanningAnalyses(Repository repository) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analyses list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public <T> T getCodeScanningAnalyses(Repository repository, ReturnFormat format) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return scanning analyses as {@link Collection} of {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public Collection<ScanningAnalysis> getCodeScanningAnalyses(String owner, String repo) throws IOException {
        return getCodeScanningAnalyses(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return analyses list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public <T> T getCodeScanningAnalyses(String owner, String repo, ReturnFormat format) throws IOException {
        return returnAnalyses(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_ANALYSES_PATH),
                format);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @return scanning analyses as {@link Collection} of {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public Collection<ScanningAnalysis> getCodeScanningAnalyses(Repository repository,
                                                                Params queryParams) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return analyses list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public <T> T getCodeScanningAnalyses(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getCodeScanningAnalyses(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @return scanning analyses as {@link Collection} of {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
    public Collection<ScanningAnalysis> getCodeScanningAnalyses(String owner, String repo,
                                                                Params queryParams) throws IOException {
        return getCodeScanningAnalyses(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the details of all code scanning analyses for a repository, starting with the most recent.
     * The response is paginated, and you can use the page and per_page parameters to list the analyses you're interested in.
     * By default, 30 analyses are listed per page.
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "tool_name"} -> he name of a code scanning tool. Only results by this tool will be listed.
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "tool_guid"} -> the GUID of a code scanning tool. Only results by this tool will be listed.
     *                            Note that some code scanning tools may not include a GUID in their analysis data
     *                            You can specify the tool by using either {@code "tool_name"} or {@code "tool_guid"},
     *                            but not both - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> a cursor, as given in the Link header. If specified, the query only
     *                            searches for results before this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "after"} -> cursor, as given in the Link header. If specified, the query only
     *                            searches for results after this cursor - [string]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> he direction to sort the results by, constants available
     *                            {@link Directions} - [string, default desc]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> if specified, only code scanning alerts with this state will be
     *                            returned, constants available {@link State} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property by which to sort the results, constants
     *                            available {@link Sort} - [string, default created]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return analyses list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
     * List code scanning analyses for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses")
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

    /**
     * Method to get a specified code scanning analysis for a repository, starting with the most recent
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the analysis
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses
     *                    operation
     * @return scanning analysis as {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-analysis-for-a-repository">
     * Get a code scanning analysis for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysis getCodeScanningAnalysis(Repository repository, long analysisId) throws IOException {
        return getCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a specified code scanning analysis for a repository, starting with the most recent
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the analysis
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses
     *                    operation
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analysis as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-analysis-for-a-repository">
     * Get a code scanning analysis for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T getCodeScanningAnalysis(Repository repository, long analysisId, ReturnFormat format) throws IOException {
        return getCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId, format);
    }

    /**
     * Method to get a specified code scanning analysis for a repository, starting with the most recent
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses
     *                    operation
     * @return scanning analysis as {@link ScanningAnalysis} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-analysis-for-a-repository">
     * Get a code scanning analysis for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysis getCodeScanningAnalysis(String owner, String repo, long analysisId) throws IOException {
        return getCodeScanningAnalysis(owner, repo, analysisId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specified code scanning analysis for a repository, starting with the most recent
     * The {@code "rules_count"} field in the response give the number of rules that were run in the analysis.
     * For very old analyses this data is not available, and 0 is returned in this field.
     * You must use an access token with the {@code "security_events"} scope
     * to use this endpoint with private repos, the {@code "security_events"} scope also grants permission to read security events on
     * public repos only. GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses
     *                    operation
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analysis as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-analysis-for-a-repository">
     * Get a code scanning analysis for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
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

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository: the repository where delete the analysis
     * @param analysis:   analysis to delete
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository,
                                                               ScanningAnalysis analysis) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository: the repository where delete the analysis
     * @param analysis:   analysis to delete
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param analysis: analysis to delete
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo,
                                                               ScanningAnalysis analysis) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param analysis: analysis to delete
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository: the repository where delete the analysis
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, long analysisId) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository: the repository where delete the analysis
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(Repository repository, long analysisId, ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId, format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo,
                                                               long analysisId) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysisId, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param analysisId: the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(String owner, String repo, long analysisId,
                                            ReturnFormat format) throws IOException {
        return returnScanningAnalysisDeletion(sendDeleteRequest(REPOS_PATH + owner + "/" + repo
                + CODE_SCANNING_ANALYSES_PATH + "/" + analysisId), format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository:    the repository where delete the analysis
     * @param analysis:      analysis to delete
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                confirmDelete, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository:    the repository where delete the analysis
     * @param analysis:      analysis to delete
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(Repository repository, ScanningAnalysis analysis, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysis.getId(),
                confirmDelete, format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param analysis:      analysis to delete
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), confirmDelete, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param analysis:      analysis to delete
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(String owner, String repo, ScanningAnalysis analysis, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysis.getId(), confirmDelete, format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository:    the repository where delete the analysis
     * @param analysisId:    the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(Repository repository, long analysisId,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                confirmDelete, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param repository:    the repository where delete the analysis
     * @param analysisId:    the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public <T> T deleteCodeScanningAnalysis(Repository repository, long analysisId, String confirmDelete,
                                            ReturnFormat format) throws IOException {
        return deleteCodeScanningAnalysis(repository.getOwner().getLogin(), repository.getName(), analysisId,
                confirmDelete, format);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param analysisId:    the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @return scanning analysis deletion as {@link ScanningAnalysisDeletion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
    public ScanningAnalysisDeletion deleteCodeScanningAnalysis(String owner, String repo, long analysisId,
                                                               String confirmDelete) throws IOException {
        return deleteCodeScanningAnalysis(owner, repo, analysisId, confirmDelete, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a specified code scanning analysis from a repository
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param analysisId:    the ID of the analysis, as returned from the GET /repos/{owner}/{repo}/code-scanning/analyses operation
     * @param confirmDelete: allow deletion if the specified analysis is the last in a set. If you attempt to delete the
     *                       final analysis in a set without setting this parameter to true, you'll get a 400 response with
     *                       the message: Analysis is last of its type and deletion may result in the loss of historical alert data.
     *                       Please specify {@code "confirm_delete"}
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return analysis deletion as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
     * Delete a code scanning analysis from a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/code-scanning/analyses/{analysis_id}")
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

    /**
     * Method to get a list of the CodeQL databases that are available in a repository
     *
     * @param repository: the repository from fetch the list
     * @return CodeQL databases list as {@link Collection} of {@link CodeQL} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-codeql-databases-for-a-repository">
     * List CodeQL databases for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases")
    public Collection<CodeQL> getCodeQLDatabases(Repository repository) throws IOException {
        return getCodeQLDatabases(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the CodeQL databases that are available in a repository
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return CodeQL databases list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-codeql-databases-for-a-repository">
     * List CodeQL databases for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases")
    public <T> T getCodeQLDatabases(Repository repository, ReturnFormat format) throws IOException {
        return getCodeQLDatabases(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of the CodeQL databases that are available in a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return CodeQL databases list as {@link Collection} of {@link CodeQL} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-codeql-databases-for-a-repository">
     * List CodeQL databases for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases")
    public Collection<CodeQL> getCodeQLDatabases(String owner, String repo) throws IOException {
        return getCodeQLDatabases(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the CodeQL databases that are available in a repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return CodeQL databases list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-codeql-databases-for-a-repository">
     * List CodeQL databases for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases")
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

    /**
     * Method to get a CodeQL database for a language in a repository
     * For private repositories, you must use an access token with the {@code "security_events"} scope.
     * For public repositories, you can use tokens with the {@code "security_events"} or {@code "public_repo"} scope.
     * GitHub Apps must have the contents read permission to use this endpoint
     *
     * @param repository: the repository from fetch the CodeQl
     * @param language:   the language of the CodeQL database
     * @return CodeQL database as {@link CodeQL} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-codeql-database-for-a-repository">
     * Get a CodeQL database for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases/{language}")
    public CodeQL getCodeQLDatabase(Repository repository, String language) throws IOException {
        return getCodeQLDatabase(repository.getOwner().getLogin(), repository.getName(), language, LIBRARY_OBJECT);
    }

    /**
     * Method to get a CodeQL database for a language in a repository
     * For private repositories, you must use an access token with the {@code "security_events"} scope.
     * For public repositories, you can use tokens with the {@code "security_events"} or {@code "public_repo"} scope.
     * GitHub Apps must have the contents read permission to use this endpoint
     *
     * @param repository: the repository from fetch the CodeQl
     * @param language:   the language of the CodeQL database
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return CodeQL database as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-codeql-database-for-a-repository">
     * Get a CodeQL database for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases/{language}")
    public <T> T getCodeQLDatabase(Repository repository, String language, ReturnFormat format) throws IOException {
        return getCodeQLDatabase(repository.getOwner().getLogin(), repository.getName(), language, format);
    }

    /**
     * Method to get a CodeQL database for a language in a repository
     * For private repositories, you must use an access token with the {@code "security_events"} scope.
     * For public repositories, you can use tokens with the {@code "security_events"} or {@code "public_repo"} scope.
     * GitHub Apps must have the contents read permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param language: the language of the CodeQL database
     * @return CodeQL database as {@link CodeQL} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-codeql-database-for-a-repository">
     * Get a CodeQL database for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases/{language}")
    public CodeQL getCodeQLDatabase(String owner, String repo, String language) throws IOException {
        return getCodeQLDatabase(owner, repo, language, LIBRARY_OBJECT);
    }

    /**
     * Method to get a CodeQL database for a language in a repository
     * For private repositories, you must use an access token with the {@code "security_events"} scope.
     * For public repositories, you can use tokens with the {@code "security_events"} or {@code "public_repo"} scope.
     * GitHub Apps must have the contents read permission to use this endpoint
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param language: the language of the CodeQL database
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return CodeQL database as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-a-codeql-database-for-a-repository">
     * Get a CodeQL database for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/codeql/databases/{language}")
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

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(Repository repository, String commitSha, String ref,
                                         File sarif) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(Repository repository, String commitSha, String ref, File sarif,
                                     ReturnFormat format) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif, format);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit to which the analysis you are uploading relates
     * @param ref:       the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                   or {@code "refs/pull/<number>/head"}
     * @param sarif:     sarif data as zipped {@link File}, will be correctly formatted by the library
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref,
                                         File sarif) throws IOException {
        return uploadSARIFAnalysis(owner, repo, commitSha, ref, sarif, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit to which the analysis you are uploading relates
     * @param ref:       the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                   or {@code "refs/pull/<number>/head"}
     * @param sarif:     sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, File sarif,
                                     ReturnFormat format) throws IOException {
        if (sarif.getPath().endsWith(".gz")) {
            return uploadSARIFAnalysis(owner, repo, commitSha, ref, Base64.getEncoder().encodeToString(new Scanner(sarif)
                    .useDelimiter("\\Z").next().getBytes(UTF_8)), format);
        } else
            throw new IllegalArgumentException("The SARIF must be a gzip file");
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(Repository repository, String commitSha, String ref,
                                         String sarif) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(Repository repository, String commitSha, String ref, String sarif,
                                     ReturnFormat format) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif, format);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit to which the analysis you are uploading relates
     * @param ref:       the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                   or {@code "refs/pull/<number>/head"}
     * @param sarif:     Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                   and then translate the contents of the file into a Base64 encoding string.
     *                   For more information, see SARIF support for code scanning.
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref,
                                         String sarif) throws IOException {
        return uploadSARIFAnalysis(owner, repo, commitSha, ref, sarif, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit to which the analysis you are uploading relates
     * @param ref:       the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                   or {@code "refs/pull/<number>/head"}
     * @param sarif:     Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                   and then translate the contents of the file into a Base64 encoding string.
     *                   For more information, see SARIF support for code scanning.
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, String sarif,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("commit_sha", commitSha);
        payload.addParam("ref", ref);
        payload.addParam("sarif", sarif);
        return returnSARIFData(sendPostRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_SARIFS_PATH,
                payload), format);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(Repository repository, String commitSha, String ref, File sarif,
                                         Params bodyParams) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(Repository repository, String commitSha, String ref, File sarif, Params bodyParams,
                                     ReturnFormat format) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                bodyParams, format);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, File sarif,
                                         Params bodyParams) throws IOException {
        return uploadSARIFAnalysis(owner, repo, commitSha, ref, sarif, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      sarif data as zipped {@link File}, will be correctly formatted by the library
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, File sarif,
                                     Params bodyParams, ReturnFormat format) throws IOException {
        if (sarif.getPath().endsWith(".gz")) {
            return uploadSARIFAnalysis(owner, repo, commitSha, ref, Base64.getEncoder().encodeToString(new Scanner(sarif)
                    .useDelimiter("\\Z").next().getBytes(UTF_8)), bodyParams, format);
        } else
            throw new IllegalArgumentException("The SARIF must be a gzip file");
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(Repository repository, String commitSha, String ref, String sarif,
                                         Params bodyParams) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param repository: the repository where upload the SARIF data
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(Repository repository, String commitSha, String ref, String sarif, Params bodyParams,
                                     ReturnFormat format) throws IOException {
        return uploadSARIFAnalysis(repository.getOwner().getLogin(), repository.getName(), commitSha, ref, sarif,
                bodyParams, format);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public SARIFData uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, String sarif,
                                         Params bodyParams) throws IOException {
        return uploadSARIFAnalysis(owner, repo, commitSha, ref, sarif, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to upload a SARIF data containing the results of a code scanning analysis to make the results available in
     * a repository. <br>
     * You must use an access token with the {@code "security_events"} scope to use this endpoint for private repositories.
     * You can also use tokens with the {@code "public_repo"} scope for public repositories only.
     * GitHub Apps must have the {@code "security_events"} write permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commitSha:  the SHA of the commit to which the analysis you are uploading relates
     * @param ref:        the full Git reference, formatted as {@code "refs/heads/<branch name>"}, {@code "refs/pull/<number>/merge"},
     *                    or {@code "refs/pull/<number>/head"}
     * @param sarif:      Base64 string representing the SARIF file to upload. You must first compress your SARIF file using gzip
     *                    and then translate the contents of the file into a Base64 encoding string.
     *                    For more information, see SARIF support for code scanning.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "checkout_uri"} -> the base directory used in the analysis, as it appears in the
     *                           SARIF file. This property is used to convert file paths from absolute to relative, so
     *                           that alerts can be mapped to their correct location in the repository - [string]
     *                       </li>
     *                       <li>
     *                           {@code "started_at"} -> the time that the analysis run began. This is a timestamp in ISO
     *                           8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tool_name"} -> the name of the tool used to generate the code scanning analysis.
     *                           If this parameter is not used, the tool name defaults to "API". If the uploaded SARIF
     *                           contains a tool GUID, this will be available for filtering using the {@code "tool_guid"}
     *                           parameter of operations such as GET /repos/{owner}/{repo}/code-scanning/alerts - [string]
     *                       </li>
     *                       <li>
     *                           {@code "validate"} -> whether the SARIF file will be validated according to the code scanning
     *                           specifications. This parameter is intended to help integrators ensure that the uploaded
     *                           SARIF files are correctly rendered by code scanning - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
     * Upload an analysis as SARIF data</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/code-scanning/sarifs")
    public <T> T uploadSARIFAnalysis(String owner, String repo, String commitSha, String ref, String sarif,
                                     Params bodyParams, ReturnFormat format) throws IOException {
        bodyParams.addParam("commit_sha", commitSha);
        bodyParams.addParam("ref", ref);
        bodyParams.addParam("sarif", sarif);
        return returnSARIFData(sendPostRequest(REPOS_PATH + owner + "/" + repo + CODE_SCANNING_SARIFS_PATH,
                bodyParams), format);
    }

    /**
     * Method to create a SARIF upload
     *
     * @param SARIFDataResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return SARIF upload as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSARIFData(String SARIFDataResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(SARIFDataResponse);
            case LIBRARY_OBJECT:
                return (T) new SARIFData(new JSONObject(SARIFDataResponse));
            default:
                return (T) SARIFDataResponse;
        }
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the information
     * @param SARIF:      the SARIF from fetch the information
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public SARIFUpload getSARIFUploadInformation(Repository repository, SARIFData SARIF) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIF.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the information
     * @param SARIF:      the SARIF from fetch the information
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF information as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public <T> T getSARIFUploadInformation(Repository repository, SARIFData SARIF, ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIF.getId(), format);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param SARIF: the SARIF from fetch the information
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public SARIFUpload getSARIFUploadInformation(String owner, String repo, SARIFData SARIF) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIF.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param SARIF:  the SARIF from fetch the information
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return SARIF information as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public <T> T getSARIFUploadInformation(String owner, String repo, SARIFData SARIF,
                                           ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIF.getId(), format);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the information
     * @param SARIFId:    the SARIF ID obtained after uploading
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public SARIFUpload getSARIFUploadInformation(Repository repository, String SARIFId) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIFId, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param repository: the repository from fetch the information
     * @param SARIFId:    the SARIF ID obtained after uploading
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return SARIF information as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public <T> T getSARIFUploadInformation(Repository repository, String SARIFId, ReturnFormat format) throws IOException {
        return getSARIFUploadInformation(repository.getOwner().getLogin(), repository.getName(), SARIFId, format);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param SARIFId: the SARIF ID obtained after uploading
     * @return SARIF data as {@link SARIFData} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
    public SARIFUpload getSARIFUploadInformation(String owner, String repo, String SARIFId) throws IOException {
        return getSARIFUploadInformation(owner, repo, SARIFId, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a SARIF upload, including the status and the URL of the analysis that was uploaded
     * so that you can retrieve details of the analysis.
     * For more information, see "Get a code scanning analysis for a repository."
     * You must use an access token with the {@code "security_events"} scope to use this endpoint with private repos,
     * the {@code "public_repo"} scope also grants permission to read security events on public repos only.
     * GitHub Apps must have the {@code "security_events"} read permission to use this endpoint
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param SARIFId: the SARIF ID obtained after uploading
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return SARIF information as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
     * Get information about a SARIF upload</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/code-scanning/sarifs/{sarif_id}")
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
