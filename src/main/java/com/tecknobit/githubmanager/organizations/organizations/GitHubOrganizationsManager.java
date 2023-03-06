package com.tecknobit.githubmanager.organizations.organizations;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.installations.records.InstallationsList;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization.Enablement;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization.RepositoryPermission;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization.SecurityProduct;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.apps.GitHubAppsManager.INSTALLATIONS_PATH;
import static com.tecknobit.githubmanager.apps.installations.records.InstallationsList.returnInstallationsList;

/**
 * The {@code GitHubOrganizationsManager} class is useful to manage all GitHub's organizations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs">
 * Organizations</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationsManager extends GitHubManager {

    /**
     * {@code ORGANIZATIONS_PATH} constant for {@code "organizations"} path
     **/
    public static final String ORGANIZATIONS_PATH = "organizations";

    /**
     * {@code ORGS_QUERY_PATH} constant for {@code "/orgs"} path
     **/
    public static final String ORGS_QUERY_PATH = "/orgs";

    /**
     * {@code USER_ORGS_PATH} constant for {@code "user/orgs"} path
     **/
    public static final String USER_ORGS_PATH = USER_PATH + ORGS_QUERY_PATH;

    /**
     * Constructor to init a {@link GitHubOrganizationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsManager} <br>
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
    public GitHubOrganizationsManager() {
        super();
    }

    /**
     * Method to get the list of the all organizations, in the order that they were created on GitHub. <br>
     * No-any params required
     *
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations">
     * List organizations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/organizations")
    public ArrayList<Organization> getOrganizations() throws IOException {
        return getOrganizations(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all organizations, in the order that they were created on GitHub. <br>
     * No-any params required
     *
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations">
     * List organizations</a>
     **/
    @RequestPath(method = GET, path = "/organizations")
    public <T> T getOrganizations(ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(ORGANIZATIONS_PATH), format);
    }

    /**
     * Method to get the list of the all organizations, in the order that they were created on GitHub.
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> an organization ID. Only return organizations with an ID greater
     *                            than this ID - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations">
     * List organizations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/organizations")
    public ArrayList<Organization> getOrganizations(Params queryParams) throws IOException {
        return getOrganizations(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all organizations, in the order that they were created on GitHub.
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> an organization ID. Only return organizations with an ID greater
     *                            than this ID - [integer]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations">
     * List organizations</a>
     **/
    @RequestPath(method = GET, path = "/organizations")
    public <T> T getOrganizations(Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(ORGANIZATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get an organization <br>
     * To see many of the organization response values, you need to be an authenticated organization owner with
     * the {@code "admin:org"} scope. <br>
     * When the value of {@code "two_factor_requirement_enabled"} is true, the organization requires all members,
     * billing managers, and outside collaborators to enable two-factor authentication.
     * GitHub Apps with the Organization plan permission can use this endpoint to retrieve information about an organization's
     * GitHub plan. See "Authenticating with GitHub Apps" for details. For an example response, see
     * 'Response with GitHub plan information' below."
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization as {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#get-an-organization">
     * Get an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}")
    public Organization getOrganization(String org) throws IOException {
        return getOrganization(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization <br>
     * To see many of the organization response values, you need to be an authenticated organization owner with
     * the {@code "admin:org"} scope. <br>
     * When the value of {@code "two_factor_requirement_enabled"} is true, the organization requires all members,
     * billing managers, and outside collaborators to enable two-factor authentication.
     * GitHub Apps with the Organization plan permission can use this endpoint to retrieve information about an organization's
     * GitHub plan. See "Authenticating with GitHub Apps" for details. For an example response, see
     * 'Response with GitHub plan information' below."
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#get-an-organization">
     * Get an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}")
    public <T> T getOrganization(String org, ReturnFormat format) throws IOException {
        return returnOrganization(sendGetRequest(ORGS_PATH + org), format);
    }

    /**
     * Method to update an organization <br>
     * Enables an authenticated organization owner with the admin:org scope to update the organization's profile and member privileges
     *
     * @param org:        the organization to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "billing_email"} -> billing email address. This address is not publicized - [string]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the company name - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the Twitter username of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the location - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the shorthand name of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_organization_projects"} -> whether an organization can use organization projects
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "has_repository_projects"} -> whether repositories that belong to the organization
     *                           can use repository projects - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "default_repository_permission"} -> default permission level members have for
     *                           organization repositories, constants available {@link RepositoryPermission}
     *                           - [string, default read]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_repositories"} -> whether of non-admin organization members
     *                           can create repositories. Note: A parameter can override this parameter.
     *                           See {@code "members_allowed_repository_creation_type"} in this table for details
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_internal_repositories"} -> whether organization members can
     *                           create internal repositories, which are visible to all enterprise members. You can only
     *                           allow members to create internal repositories if your organization is associated with
     *                           an enterprise account using GitHub Enterprise Cloud or GitHub Enterprise Server 2.20+.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_repositories"} -> whether organization members can
     *                           create private repositories, which are visible to organization members with permission.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_repositories"} -> whether organization members can
     *                           create public repositories, which are visible to anyone. For more information, see
     *                           "Restricting repository creation in your organization" in the GitHub Help documentation
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_pages"} -> whether organization members can create GitHub
     *                           Pages sites. Existing published sites will not be impacted - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_pages"} -> whether organization members can create
     *                           public GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_pages"} -> whether organization members can create
     *                           private GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_fork_private_repositories"} -> whether organization members can fork
     *                           private organization repositories - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> whether contributors to organization repositories
     *                           are required to sign off on commits they make through GitHub's web interface
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> blog value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "advanced_security_enabled_for_new_repositories"} -> whether GitHub Advanced
     *                           Security is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_alerts_enabled_for_new_repositories"} -> whether Dependabot alerts
     *                           is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_security_updates_enabled_for_new_repositories"} -> whether Dependabot
     *                           security updates is automatically enabled for new repositories. To use this parameter,
     *                           you must have admin permissions for the repository or be an owner or security manager for
     *                           the organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependency_graph_enabled_for_new_repositories"} -> whether dependency graph is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_enabled_for_new_repositories"} -> whether secret scanning is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_enabled_for_new_repositories"} -> whether secret
     *                           scanning push protection is automatically enabled for new repositories. To use this
     *                           parameter, you must have admin permissions for the repository or be an owner or security
     *                           manager for the organization that owns the repository. For more information, see "Managing
     *                           security managers in your organization." You can check which security and analysis
     *                           features are currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} -> whether a custom link
     *                           is shown to contributors who are blocked from pushing a secret by push protection - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link"} -> if
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} is true, the URL that will
     *                           be displayed to contributors who are blocked from pushing a secret - [string]
     *                       </li>
     *                    </ul>
     * @return organization as {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#update-an-organization">
     * Update an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}")
    public Organization updateOrganization(Organization org, Params bodyParams) throws IOException {
        return updateOrganization(org.getLogin(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an organization <br>
     * Enables an authenticated organization owner with the admin:org scope to update the organization's profile and member privileges
     *
     * @param org:        the organization to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "billing_email"} -> billing email address. This address is not publicized - [string]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the company name - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the Twitter username of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the location - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the shorthand name of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_organization_projects"} -> whether an organization can use organization projects
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "has_repository_projects"} -> whether repositories that belong to the organization
     *                           can use repository projects - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "default_repository_permission"} -> default permission level members have for
     *                           organization repositories, constants available {@link RepositoryPermission}
     *                           - [string, default read]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_repositories"} -> whether of non-admin organization members
     *                           can create repositories. Note: A parameter can override this parameter.
     *                           See {@code "members_allowed_repository_creation_type"} in this table for details
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_internal_repositories"} -> whether organization members can
     *                           create internal repositories, which are visible to all enterprise members. You can only
     *                           allow members to create internal repositories if your organization is associated with
     *                           an enterprise account using GitHub Enterprise Cloud or GitHub Enterprise Server 2.20+.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_repositories"} -> whether organization members can
     *                           create private repositories, which are visible to organization members with permission.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_repositories"} -> whether organization members can
     *                           create public repositories, which are visible to anyone. For more information, see
     *                           "Restricting repository creation in your organization" in the GitHub Help documentation
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_pages"} -> whether organization members can create GitHub
     *                           Pages sites. Existing published sites will not be impacted - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_pages"} -> whether organization members can create
     *                           public GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_pages"} -> whether organization members can create
     *                           private GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_fork_private_repositories"} -> whether organization members can fork
     *                           private organization repositories - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> whether contributors to organization repositories
     *                           are required to sign off on commits they make through GitHub's web interface
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> blog value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "advanced_security_enabled_for_new_repositories"} -> whether GitHub Advanced
     *                           Security is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_alerts_enabled_for_new_repositories"} -> whether Dependabot alerts
     *                           is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_security_updates_enabled_for_new_repositories"} -> whether Dependabot
     *                           security updates is automatically enabled for new repositories. To use this parameter,
     *                           you must have admin permissions for the repository or be an owner or security manager for
     *                           the organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependency_graph_enabled_for_new_repositories"} -> whether dependency graph is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_enabled_for_new_repositories"} -> whether secret scanning is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_enabled_for_new_repositories"} -> whether secret
     *                           scanning push protection is automatically enabled for new repositories. To use this
     *                           parameter, you must have admin permissions for the repository or be an owner or security
     *                           manager for the organization that owns the repository. For more information, see "Managing
     *                           security managers in your organization." You can check which security and analysis
     *                           features are currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} -> whether a custom link
     *                           is shown to contributors who are blocked from pushing a secret by push protection - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link"} -> if
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} is true, the URL that will
     *                           be displayed to contributors who are blocked from pushing a secret - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#update-an-organization">
     * Update an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}")
    public <T> T updateOrganization(Organization org, Params bodyParams, ReturnFormat format) throws IOException {
        return updateOrganization(org.getLogin(), bodyParams, format);
    }

    /**
     * Method to update an organization <br>
     * Enables an authenticated organization owner with the admin:org scope to update the organization's profile and member privileges
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "billing_email"} -> billing email address. This address is not publicized - [string]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the company name - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the Twitter username of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the location - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the shorthand name of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_organization_projects"} -> whether an organization can use organization projects
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "has_repository_projects"} -> whether repositories that belong to the organization
     *                           can use repository projects - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "default_repository_permission"} -> default permission level members have for
     *                           organization repositories, constants available {@link RepositoryPermission}
     *                           - [string, default read]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_repositories"} -> whether of non-admin organization members
     *                           can create repositories. Note: A parameter can override this parameter.
     *                           See {@code "members_allowed_repository_creation_type"} in this table for details
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_internal_repositories"} -> whether organization members can
     *                           create internal repositories, which are visible to all enterprise members. You can only
     *                           allow members to create internal repositories if your organization is associated with
     *                           an enterprise account using GitHub Enterprise Cloud or GitHub Enterprise Server 2.20+.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_repositories"} -> whether organization members can
     *                           create private repositories, which are visible to organization members with permission.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_repositories"} -> whether organization members can
     *                           create public repositories, which are visible to anyone. For more information, see
     *                           "Restricting repository creation in your organization" in the GitHub Help documentation
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_pages"} -> whether organization members can create GitHub
     *                           Pages sites. Existing published sites will not be impacted - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_pages"} -> whether organization members can create
     *                           public GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_pages"} -> whether organization members can create
     *                           private GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_fork_private_repositories"} -> whether organization members can fork
     *                           private organization repositories - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> whether contributors to organization repositories
     *                           are required to sign off on commits they make through GitHub's web interface
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> blog value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "advanced_security_enabled_for_new_repositories"} -> whether GitHub Advanced
     *                           Security is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_alerts_enabled_for_new_repositories"} -> whether Dependabot alerts
     *                           is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_security_updates_enabled_for_new_repositories"} -> whether Dependabot
     *                           security updates is automatically enabled for new repositories. To use this parameter,
     *                           you must have admin permissions for the repository or be an owner or security manager for
     *                           the organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependency_graph_enabled_for_new_repositories"} -> whether dependency graph is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_enabled_for_new_repositories"} -> whether secret scanning is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_enabled_for_new_repositories"} -> whether secret
     *                           scanning push protection is automatically enabled for new repositories. To use this
     *                           parameter, you must have admin permissions for the repository or be an owner or security
     *                           manager for the organization that owns the repository. For more information, see "Managing
     *                           security managers in your organization." You can check which security and analysis
     *                           features are currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} -> whether a custom link
     *                           is shown to contributors who are blocked from pushing a secret by push protection - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link"} -> if
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} is true, the URL that will
     *                           be displayed to contributors who are blocked from pushing a secret - [string]
     *                       </li>
     *                    </ul>
     * @return organization as {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#update-an-organization">
     * Update an organization</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/orgs/{org}")
    public Organization updateOrganization(String org, Params bodyParams) throws IOException {
        return updateOrganization(org, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an organization <br>
     * Enables an authenticated organization owner with the admin:org scope to update the organization's profile and member privileges
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "billing_email"} -> billing email address. This address is not publicized - [string]
     *                       </li>
     *                       <li>
     *                           {@code "company"} -> the company name - [string]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> the publicly visible email address - [string]
     *                       </li>
     *                       <li>
     *                           {@code "twitter_username"} -> the Twitter username of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "location"} -> the location - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the shorthand name of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the company - [string]
     *                       </li>
     *                       <li>
     *                           {@code "has_organization_projects"} -> whether an organization can use organization projects
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "has_repository_projects"} -> whether repositories that belong to the organization
     *                           can use repository projects - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "default_repository_permission"} -> default permission level members have for
     *                           organization repositories, constants available {@link RepositoryPermission}
     *                           - [string, default read]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_repositories"} -> whether of non-admin organization members
     *                           can create repositories. Note: A parameter can override this parameter.
     *                           See {@code "members_allowed_repository_creation_type"} in this table for details
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_internal_repositories"} -> whether organization members can
     *                           create internal repositories, which are visible to all enterprise members. You can only
     *                           allow members to create internal repositories if your organization is associated with
     *                           an enterprise account using GitHub Enterprise Cloud or GitHub Enterprise Server 2.20+.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_repositories"} -> whether organization members can
     *                           create private repositories, which are visible to organization members with permission.
     *                           For more information, see "Restricting repository creation in your organization" in the
     *                           GitHub Help documentation - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_repositories"} -> whether organization members can
     *                           create public repositories, which are visible to anyone. For more information, see
     *                           "Restricting repository creation in your organization" in the GitHub Help documentation
     *                           - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_pages"} -> whether organization members can create GitHub
     *                           Pages sites. Existing published sites will not be impacted - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_public_pages"} -> whether organization members can create
     *                           public GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default true]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_create_private_pages"} -> whether organization members can create
     *                           private GitHub Pages sites. Existing published sites will not be impacted
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "members_can_fork_private_repositories"} -> whether organization members can fork
     *                           private organization repositories - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "web_commit_signoff_required"} -> whether contributors to organization repositories
     *                           are required to sign off on commits they make through GitHub's web interface
     *                           - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "blog"} -> blog value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "advanced_security_enabled_for_new_repositories"} -> whether GitHub Advanced
     *                           Security is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_alerts_enabled_for_new_repositories"} -> whether Dependabot alerts
     *                           is automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependabot_security_updates_enabled_for_new_repositories"} -> whether Dependabot
     *                           security updates is automatically enabled for new repositories. To use this parameter,
     *                           you must have admin permissions for the repository or be an owner or security manager for
     *                           the organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "dependency_graph_enabled_for_new_repositories"} -> whether dependency graph is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_enabled_for_new_repositories"} -> whether secret scanning is
     *                           automatically enabled for new repositories. To use this parameter, you must
     *                           have admin permissions for the repository or be an owner or security manager for the
     *                           organization that owns the repository. For more information, see "Managing security
     *                           managers in your organization." You can check which security and analysis features are
     *                           currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_enabled_for_new_repositories"} -> whether secret
     *                           scanning push protection is automatically enabled for new repositories. To use this
     *                           parameter, you must have admin permissions for the repository or be an owner or security
     *                           manager for the organization that owns the repository. For more information, see "Managing
     *                           security managers in your organization." You can check which security and analysis
     *                           features are currently enabled by using a {@code "GET /orgs/{org}"} request - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} -> whether a custom link
     *                           is shown to contributors who are blocked from pushing a secret by push protection - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "secret_scanning_push_protection_custom_link"} -> if
     *                           {@code "secret_scanning_push_protection_custom_link_enabled"} is true, the URL that will
     *                           be displayed to contributors who are blocked from pushing a secret - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#update-an-organization">
     * Update an organization</a>
     **/
    @RequestPath(method = PATCH, path = "/orgs/{org}")
    public <T> T updateOrganization(String org, Params bodyParams, ReturnFormat format) throws IOException {
        return returnOrganization(sendPatchRequest(ORGS_PATH + org, bodyParams), format);
    }

    /**
     * Method to create an organization
     *
     * @param organizationResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return organization as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganization(String organizationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationResponse);
            case LIBRARY_OBJECT:
                return (T) new Organization(new JSONObject(organizationResponse));
            default:
                return (T) organizationResponse;
        }
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
     *
     * @param org: the organization from fetch the list
     * @return installations list as {@link InstallationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public InstallationsList getOrganizationAppInstallations(Organization org) throws Exception {
        return getOrganizationAppInstallations(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public <T> T getOrganizationAppInstallations(Organization org, ReturnFormat format) throws Exception {
        return getOrganizationAppInstallations(org.getLogin(), format);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return installations list as {@link InstallationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public InstallationsList getOrganizationAppInstallations(String org) throws Exception {
        return getOrganizationAppInstallations(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return installations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public <T> T getOrganizationAppInstallations(String org, ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(ORGS_PATH + org + INSTALLATIONS_PATH), format);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
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
     * @return installations list as {@link InstallationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public InstallationsList getOrganizationAppInstallations(Organization org, Params queryParams) throws Exception {
        return getOrganizationAppInstallations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
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
     * @return installations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public <T> T getOrganizationAppInstallations(Organization org, Params queryParams, ReturnFormat format) throws Exception {
        return getOrganizationAppInstallations(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
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
     * @return installations list as {@link InstallationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public InstallationsList getOrganizationAppInstallations(String org, Params queryParams) throws Exception {
        return getOrganizationAppInstallations(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all GitHub Apps in an organization. The installation count includes all GitHub Apps
     * installed on repositories in the organization.
     * You must be an organization owner with {@code "admin:read"} scope to use this endpoint
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
     * @return installations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-app-installations-for-an-organization">
     * List app installations for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/installations")
    public <T> T getOrganizationAppInstallations(String org, Params queryParams, ReturnFormat format) throws Exception {
        return returnInstallationsList(sendGetRequest(ORGS_PATH + org + INSTALLATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to enable or disable the specified security feature for all repositories in an organization. <br>
     * To use this endpoint, you must be an organization owner or be member of a team with the security manager role.
     * A token with the {@code "write:org"} scope is also required. <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint. <br>
     * For more information, see "Managing security managers in your organization."
     *
     * @param org:             the organization to manage
     * @param securityProduct: the security feature to enable or disable
     * @param enablement:      the action to take:
     *                         <ul>
     *                             <li>
     *                                 {@code "enable_all"} means to enable the specified security feature for all repositories
     *                                 in the organization
     *                             </li>
     *                             <li>
     *                                 {@code "disable_all"} means to disable the specified security feature for all repositories
     *                                 in the organization
     *                             </li>
     *                         </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#enable-or-disable-a-security-feature-for-an-organization">
     * Enable or disable a security feature for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/{security_product}/{enablement}")
    public boolean workWithOrganizationSecurityFeature(Organization org, SecurityProduct securityProduct,
                                                       Enablement enablement) {
        return workWithOrganizationSecurityFeature(org.getLogin(), securityProduct, enablement);
    }

    /**
     * Method to enable or disable the specified security feature for all repositories in an organization. <br>
     * To use this endpoint, you must be an organization owner or be member of a team with the security manager role.
     * A token with the {@code "write:org"} scope is also required. <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint. <br>
     * For more information, see "Managing security managers in your organization."
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param securityProduct: the security feature to enable or disable
     * @param enablement:      the action to take:
     *                         <ul>
     *                             <li>
     *                                 {@code "enable_all"} means to enable the specified security feature for all repositories
     *                                 in the organization
     *                             </li>
     *                             <li>
     *                                 {@code "disable_all"} means to disable the specified security feature for all repositories
     *                                 in the organization
     *                             </li>
     *                         </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#enable-or-disable-a-security-feature-for-an-organization">
     * Enable or disable a security feature for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/{security_product}/{enablement}")
    public boolean workWithOrganizationSecurityFeature(String org, SecurityProduct securityProduct, Enablement enablement) {
        try {
            sendPostRequest(ORGS_PATH + org + "/" + securityProduct + "/" + enablement, null);
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
     * Method to get the list of the organizations for the authenticated user. <br>
     * <b>OAuth scope requirements</b><br>
     * This only lists organizations that your authorization allows you to operate on in some way (e.g., you can list
     * teams with {@code "read:org"} scope, you can publicize your organization membership with user scope, etc.).
     * Therefore, this API requires at least user or {@code "read:org"} scope. OAuth requests with insufficient scope receive a
     * 403 Forbidden response <br>
     * No-any params required
     *
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-the-authenticated-user">
     * List organizations for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/orgs")
    public ArrayList<Organization> getAuthenticatedUserOrganizations() throws IOException {
        return getAuthenticatedUserOrganizations(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organizations for the authenticated user. <br>
     * <b>OAuth scope requirements</b><br>
     * This only lists organizations that your authorization allows you to operate on in some way (e.g., you can list
     * teams with {@code "read:org"} scope, you can publicize your organization membership with user scope, etc.).
     * Therefore, this API requires at least user or {@code "read:org"} scope. OAuth requests with insufficient scope receive a
     * 403 Forbidden response <br>
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-the-authenticated-user">
     * List organizations for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/orgs")
    public <T> T getAuthenticatedUserOrganizations(ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(USER_ORGS_PATH), format);
    }

    /**
     * Method to get the list of the organizations for the authenticated user. <br>
     * <b>OAuth scope requirements</b><br>
     * This only lists organizations that your authorization allows you to operate on in some way (e.g., you can list
     * teams with {@code "read:org"} scope, you can publicize your organization membership with user scope, etc.).
     * Therefore, this API requires at least user or {@code "read:org"} scope. OAuth requests with insufficient scope receive a
     * 403 Forbidden response <br>
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-the-authenticated-user">
     * List organizations for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/orgs")
    public ArrayList<Organization> getAuthenticatedUserOrganizations(Params queryParams) throws IOException {
        return getAuthenticatedUserOrganizations(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organizations for the authenticated user. <br>
     * <b>OAuth scope requirements</b><br>
     * This only lists organizations that your authorization allows you to operate on in some way (e.g., you can list
     * teams with {@code "read:org"} scope, you can publicize your organization membership with user scope, etc.).
     * Therefore, this API requires at least user or {@code "read:org"} scope. OAuth requests with insufficient scope receive a
     * 403 Forbidden response <br>
     *
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
     * @return organizations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-the-authenticated-user">
     * List organizations for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/orgs")
    public <T> T getAuthenticatedUserOrganizations(Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(USER_ORGS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the public organization memberships for the specified user.
     * This method only lists public memberships, regardless of authentication. If you need to fetch all the organization
     * memberships (public and private) for the authenticated user, use the List organizations for the authenticated user
     * API instead
     *
     * @param username: the handle for the GitHub user account
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-a-user">
     * List organizations for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/orgs")
    public ArrayList<Organization> getUserOrganizations(String username) throws IOException {
        return getUserOrganizations(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public organization memberships for the specified user.
     * This method only lists public memberships, regardless of authentication. If you need to fetch all the organization
     * memberships (public and private) for the authenticated user, use the List organizations for the authenticated user
     * API instead
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-a-user">
     * List organizations for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/orgs")
    public <T> T getUserOrganizations(String username, ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(USERS_PATH + username + ORGS_QUERY_PATH), format);
    }

    /**
     * Method to get the list of the public organization memberships for the specified user.
     * This method only lists public memberships, regardless of authentication. If you need to fetch all the organization
     * memberships (public and private) for the authenticated user, use the List organizations for the authenticated user
     * API instead
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return organizations list as {@link ArrayList} of {@link Organization} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-a-user">
     * List organizations for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/orgs")
    public ArrayList<Organization> getUserOrganizations(String username, Params queryParams) throws IOException {
        return getUserOrganizations(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public organization memberships for the specified user.
     * This method only lists public memberships, regardless of authentication. If you need to fetch all the organization
     * memberships (public and private) for the authenticated user, use the List organizations for the authenticated user
     * API instead
     *
     * @param username:    the handle for the GitHub user account
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
     * @return organizations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/orgs#list-organizations-for-a-user">
     * List organizations for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/orgs")
    public <T> T getUserOrganizations(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizations(sendGetRequest(USERS_PATH + username + ORGS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an organizations list
     *
     * @param organizationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return organizations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizations(String organizationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Organization> organizations = new ArrayList<>();
                JSONArray jOrganizations = new JSONArray(organizationsResponse);
                for (int j = 0; j < jOrganizations.length(); j++)
                    organizations.add(new Organization(jOrganizations.getJSONObject(j)));
                return (T) organizations;
            default:
                return (T) organizationsResponse;
        }
    }

}
