package com.tecknobit.githubmanager.billing;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.billing.records.ActionsBilling;
import com.tecknobit.githubmanager.billing.records.AdvancedSecurityCommitters;
import com.tecknobit.githubmanager.billing.records.PackagesBilling;
import com.tecknobit.githubmanager.billing.records.SharedStorageBilling;
import com.tecknobit.githubmanager.records.organization.Organization;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubBillingManager} class is useful to manage all GitHub's billing endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing">
 * Billing</a>
 * @see GitHubManager
 **/
public class GitHubBillingManager extends GitHubManager {

    /**
     * {@code SETTINGS_BILLING_PATH} constant for {@code "/settings/billing/"} path
     **/
    public static final String SETTINGS_BILLING_PATH = "/settings/billing/";

    /**
     * {@code ACTIONS_QUERY_PATH} constant for {@code "actions"} path
     **/
    public static final String ACTIONS_QUERY_PATH = "actions";

    /**
     * {@code ADVANCED_SECURITY_PATH} constant for {@code "advanced-security"} path
     **/
    public static final String ADVANCED_SECURITY_PATH = "advanced-security";

    /**
     * {@code PACKAGES_PATH} constant for {@code "packages} path
     **/
    public static final String PACKAGES_PATH = "packages";

    /**
     * {@code SHARED_STORAGE_PATH} constant for {@code "shared-storage} path
     **/
    public static final String SHARED_STORAGE_PATH = "shared-storage";

    /**
     * Constructor to init a {@link GitHubBillingManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubBillingManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubBillingManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubBillingManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubBillingManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubBillingManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBillingManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubBillingManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBillingManager} <br>
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
    public GitHubBillingManager() {
        super();
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization from fetch the billing
     * @return actions billing as {@link ActionsBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-an-organization">
     * Get GitHub Actions billing for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/actions")
    public ActionsBilling getOrganizationActionsBilling(Organization org) throws IOException {
        return getOrganizationActionsBilling(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization from fetch the billing
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return actions billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-an-organization">
     * Get GitHub Actions billing for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/actions")
    public <T> T getOrganizationActionsBilling(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationActionsBilling(org.getLogin(), format);
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return actions billing as {@link ActionsBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-an-organization">
     * Get GitHub Actions billing for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/actions")
    public ActionsBilling getOrganizationActionsBilling(String org) throws IOException {
        return getOrganizationActionsBilling(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return actions billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-an-organization">
     * Get GitHub Actions billing for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/actions")
    public <T> T getOrganizationActionsBilling(String org, ReturnFormat format) throws IOException {
        return returnActionsBilling(sendGetRequest(ORGS_PATH + org + SETTINGS_BILLING_PATH + ACTIONS_QUERY_PATH), format);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org: the organization from fetch the advanced security committers
     * @return advanced security committers as {@link AdvancedSecurityCommitters} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public AdvancedSecurityCommitters getOrganizationSecurityCommitters(Organization org) throws IOException {
        return getOrganizationSecurityCommitters(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org:    the organization from fetch the advanced security committers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return advanced security active committers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public <T> T getOrganizationSecurityCommitters(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSecurityCommitters(org.getLogin(), format);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return advanced security committers as {@link AdvancedSecurityCommitters} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public AdvancedSecurityCommitters getOrganizationSecurityCommitters(String org) throws IOException {
        return getOrganizationSecurityCommitters(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return advanced security active committers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public <T> T getOrganizationSecurityCommitters(String org, ReturnFormat format) throws IOException {
        return returnAdvanceSecurityActiveCommitters(sendGetRequest(ORGS_PATH + org +
                SETTINGS_BILLING_PATH + ADVANCED_SECURITY_PATH), format);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org:         the organization from fetch the advanced security committers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return advanced security committers as {@link AdvancedSecurityCommitters} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public AdvancedSecurityCommitters getOrganizationSecurityCommitters(Organization org,
                                                                        Params queryParams) throws IOException {
        return getOrganizationSecurityCommitters(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
     *
     * @param org:         the organization from fetch the advanced security committers
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
     * @return advanced security active committers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public <T> T getOrganizationSecurityCommitters(Organization org, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getOrganizationSecurityCommitters(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
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
     * @return advanced security committers as {@link AdvancedSecurityCommitters} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public AdvancedSecurityCommitters getOrganizationSecurityCommitters(String org, Params queryParams) throws IOException {
        return getOrganizationSecurityCommitters(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the GitHub Advanced Security active committers for an organization per repository.
     * Each distinct user login across all repositories is counted as a single Advanced Security seat, so
     * the {@code "total_advanced_security_committers"} is not the sum of {@code "advanced_security_committers"} for each repository.
     * If this organization defers to an enterprise for billing, the {@code "total_advanced_security_committers"} returned
     * from the organization API may include some users that are in more than one organization, so they will only consume
     * a single Advanced Security seat at the enterprise level.
     * The total number of repositories with committer information is tracked by the {@code "total_count"} field
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
     * @return advanced security active committers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-advanced-security-active-committers-for-an-organization">
     * Get GitHub Advanced Security active committers for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/advanced-security")
    public <T> T getOrganizationSecurityCommitters(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnAdvanceSecurityActiveCommitters(sendGetRequest(ORGS_PATH + org +
                SETTINGS_BILLING_PATH + ADVANCED_SECURITY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an advanced security active committers
     *
     * @param committersResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return advanced security active committers as {@code "format"} defines
     **/
    @Returner
    private <T> T returnAdvanceSecurityActiveCommitters(String committersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(committersResponse);
            case LIBRARY_OBJECT:
                return (T) new AdvancedSecurityCommitters(new JSONObject(committersResponse));
            default:
                return (T) committersResponse;
        }
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization from fetch the billing
     * @return packages billing as {@link PackagesBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-an-organization">
     * Get GitHub Packages billing for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/packages")
    public PackagesBilling getOrganizationPackagesBilling(Organization org) throws IOException {
        return getOrganizationPackagesBilling(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization from fetch the billing
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return packages billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-an-organization">
     * Get GitHub Packages billing for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/packages")
    public <T> T getOrganizationPackagesBilling(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationPackagesBilling(org.getLogin(), format);
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return packages billing as {@link PackagesBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-an-organization">
     * Get GitHub Packages billing for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/packages")
    public PackagesBilling getOrganizationPackagesBilling(String org) throws IOException {
        return getOrganizationPackagesBilling(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return packages billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-an-organization">
     * Get GitHub Packages billing for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/packages")
    public <T> T getOrganizationPackagesBilling(String org, ReturnFormat format) throws IOException {
        return returnPackagesBilling(sendGetRequest(ORGS_PATH + org + SETTINGS_BILLING_PATH + PACKAGES_PATH),
                format);
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see Managing billing for GitHub Packages.
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization from fetch the billing
     * @return shared storage billing as {@link SharedStorageBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-an-organization">
     * Get shared storage billing for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/shared-storage")
    public SharedStorageBilling getOrganizationSharedStorageBilling(Organization org) throws IOException {
        return getOrganizationSharedStorageBilling(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see Managing billing for GitHub Packages.
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization from fetch the billing
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return shared storage billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-an-organization">
     * Get shared storage billing for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/shared-storage")
    public <T> T getOrganizationSharedStorageBilling(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationSharedStorageBilling(org.getLogin(), format);
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see Managing billing for GitHub Packages.
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return shared storage billing as {@link SharedStorageBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-an-organization">
     * Get shared storage billing for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/shared-storage")
    public SharedStorageBilling getOrganizationSharedStorageBilling(String org) throws IOException {
        return getOrganizationSharedStorageBilling(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see Managing billing for GitHub Packages.
     * Access tokens must have the repo or {@code "admin:org"} scope
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return shared storage billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-an-organization">
     * Get shared storage billing for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/settings/billing/shared-storage")
    public <T> T getOrganizationSharedStorageBilling(String org, ReturnFormat format) throws IOException {
        return returnSharedStorageBilling(sendGetRequest(ORGS_PATH + org + SETTINGS_BILLING_PATH +
                SHARED_STORAGE_PATH), format);
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @return actions billing as {@link ActionsBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-a-user">
     * Get GitHub Actions billing for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/actions")
    public ActionsBilling getUserActionsBilling(String username) throws IOException {
        return getUserActionsBilling(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the summary of the free and paid GitHub Actions minutes used.
     * Paid minutes only apply to workflows in private repositories that use GitHub-hosted runners.
     * Minutes used is listed for each GitHub-hosted runner operating system. Any job re-runs are also included in the usage.
     * The usage returned includes any minute multipliers for macOS and Windows runners, and is rounded up to the nearest whole minute.
     * For more information, see "Managing billing for GitHub Actions".
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return actions billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-a-user">
     * Get GitHub Actions billing for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/actions")
    public <T> T getUserActionsBilling(String username, ReturnFormat format) throws IOException {
        return returnActionsBilling(sendGetRequest(USERS_PATH + username + SETTINGS_BILLING_PATH +
                ACTIONS_QUERY_PATH), format);
    }

    /**
     * Method to create an actions billing
     *
     * @param billingResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return actions billing as {@code "format"} defines
     **/
    @Returner
    private <T> T returnActionsBilling(String billingResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(billingResponse);
            case LIBRARY_OBJECT:
                return (T) new ActionsBilling(new JSONObject(billingResponse));
            default:
                return (T) billingResponse;
        }
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @return packages billing as {@link PackagesBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-a-user">
     * Get GitHub Packages billing for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/packages")
    public PackagesBilling getUserPackagesBilling(String username) throws IOException {
        return getUserPackagesBilling(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the free and paid storage used for GitHub Packages in gigabytes.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return packages billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-a-user">
     * Get GitHub Packages billing for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/packages")
    public <T> T getUserPackagesBilling(String username, ReturnFormat format) throws IOException {
        return returnPackagesBilling(sendGetRequest(USERS_PATH + username + SETTINGS_BILLING_PATH + PACKAGES_PATH),
                format);
    }

    /**
     * Method to create a packages billing
     *
     * @param packagesBillingResponse: obtained from GitHub's response
     * @param format:                  return type formatter -> {@link ReturnFormat}
     * @return packages billing as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPackagesBilling(String packagesBillingResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(packagesBillingResponse);
            case LIBRARY_OBJECT:
                return (T) new PackagesBilling(new JSONObject(packagesBillingResponse));
            default:
                return (T) packagesBillingResponse;
        }
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @return shared storage billing as {@link SharedStorageBilling} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-a-user">
     * Get shared storage billing for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/shared-storage")
    public SharedStorageBilling getUserSharedStorageBilling(String username) throws IOException {
        return getUserSharedStorageBilling(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the estimated paid and estimated total storage used for GitHub Actions and GitHub Packages.
     * Paid minutes only apply to packages stored for private repositories. For more information, see "Managing billing for GitHub Packages."
     * Access tokens must have the user scope
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return shared storage billing as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-a-user">
     * Get shared storage billing for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/settings/billing/shared-storage")
    public <T> T getUserSharedStorageBilling(String username, ReturnFormat format) throws IOException {
        return returnSharedStorageBilling(sendGetRequest(USERS_PATH + username + SETTINGS_BILLING_PATH +
                SHARED_STORAGE_PATH), format);
    }

    /**
     * Method to create a shared storage billing
     *
     * @param sharedStorageBillingResponse: obtained from GitHub's response
     * @param format:                       return type formatter -> {@link ReturnFormat}
     * @return shared storage billing as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSharedStorageBilling(String sharedStorageBillingResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(sharedStorageBillingResponse);
            case LIBRARY_OBJECT:
                return (T) new SharedStorageBilling(new JSONObject(sharedStorageBillingResponse));
            default:
                return (T) sharedStorageBillingResponse;
        }
    }

}
