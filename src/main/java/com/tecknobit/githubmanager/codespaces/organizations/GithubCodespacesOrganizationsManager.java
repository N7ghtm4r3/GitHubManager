package com.tecknobit.githubmanager.codespaces.organizations;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codespaces.records.Codespace;
import com.tecknobit.githubmanager.codespaces.records.CodespacesList;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization.OrganizationVisibility;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codespaces.codespaces.GitHubCodespacesManager.CODESPACES_PATH;
import static com.tecknobit.githubmanager.codespaces.codespaces.GitHubCodespacesManager.STOP_PATH;
import static com.tecknobit.githubmanager.codespaces.records.Codespace.returnCodespace;
import static com.tecknobit.githubmanager.codespaces.records.CodespacesList.returnCodespacesList;
import static com.tecknobit.githubmanager.organizations.members.GitHubMembersManager.MEMBERS_PATH;

/**
 * The {@code GithubCodespacesOrganizationsManager} class is useful to manage all GitHub's codespaces organizations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations">
 * Codespaces organizations</a>
 * @see GitHubManager
 **/
public class GithubCodespacesOrganizationsManager extends GitHubManager {

    /**
     * {@code BILLING_PATH} constant for {@code "/billing"} path
     **/
    public static final String BILLING_PATH = "/billing";

    /**
     * {@code MEMBERS_QUERY_PATH} constant for {@code "/members/"} path
     **/
    public static final String MEMBERS_QUERY_PATH = MEMBERS_PATH + "/";

    /**
     * Constructor to init a {@link GithubCodespacesOrganizationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GithubCodespacesOrganizationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GithubCodespacesOrganizationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GithubCodespacesOrganizationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GithubCodespacesOrganizationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GithubCodespacesOrganizationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubCodespacesOrganizationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GithubCodespacesOrganizationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GithubCodespacesOrganizationsManager} <br>
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
    public GithubCodespacesOrganizationsManager() {
        super();
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: organization from fetch the list
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public CodespacesList getOrganizationCodespaces(Organization org) throws IOException {
        return getOrganizationCodespaces(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public <T> T getOrganizationCodespaces(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationCodespaces(org.getLogin(), format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public CodespacesList getOrganizationCodespaces(String org) throws IOException {
        return getOrganizationCodespaces(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public <T> T getOrganizationCodespaces(String org, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(ORGS_PATH + org + CODESPACES_PATH), format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public CodespacesList getOrganizationCodespaces(Organization org, Params queryParams) throws IOException {
        return getOrganizationCodespaces(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         organization from fetch the list
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
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public <T> T getOrganizationCodespaces(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationCodespaces(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public CodespacesList getOrganizationCodespaces(String org, Params queryParams) throws IOException {
        return getOrganizationCodespaces(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces associated to a specified organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
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
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
     * List codespaces in a repository for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/codespaces")
    public <T> T getOrganizationCodespaces(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(ORGS_PATH + org + CODESPACES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        organization where manage the access control
     * @param visibility: which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(Organization org, OrganizationVisibility visibility) {
        return manageAccessControl(org.getLogin(), visibility, (String[]) null);
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param visibility: which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(String org, OrganizationVisibility visibility) {
        return manageAccessControl(org, visibility, (String[]) null);
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:               organization where manage the access control
     * @param visibility:        which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @param selectedUsernames: the usernames of the organization members who should be granted access to codespaces in the organization.
     *                           Required when visibility is {@code "selected_members"} as {@link ArrayList} of {@link String}
     *                           format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(Organization org, OrganizationVisibility visibility,
                                       ArrayList<String> selectedUsernames) {
        return manageAccessControl(org.getLogin(), visibility, selectedUsernames.toArray(new String[0]));
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param visibility:        which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @param selectedUsernames: the usernames of the organization members who should be granted access to codespaces in the organization.
     *                           Required when visibility is {@code "selected_members"} as {@link ArrayList} of {@link String}
     *                           format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(String org, OrganizationVisibility visibility, ArrayList<String> selectedUsernames) {
        return manageAccessControl(org, visibility, selectedUsernames.toArray(new String[0]));
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:               organization where manage the access control
     * @param visibility:        which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @param selectedUsernames: the usernames of the organization members who should be granted access to codespaces in the organization.
     *                           Required when visibility is {@code "selected_members"} as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(Organization org, OrganizationVisibility visibility, String[] selectedUsernames) {
        return manageAccessControl(org.getLogin(), visibility, selectedUsernames);
    }

    /**
     * Method to set which users can access codespaces in an organization. <br>
     * This is synonymous with granting or revoking codespaces billing permissions for users according to the visibility. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:               the organization name. The name is not case-sensitive
     * @param visibility:        which users can access codespaces in the organization. disabled means that no users can access codespaces in the organization
     * @param selectedUsernames: the usernames of the organization members who should be granted access to codespaces in the organization.
     *                           Required when visibility is {@code "selected_members"} as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#manage-access-control-for-organization-codespaces">
     * Manage access control for organization codespaces</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/codespaces/billing")
    public boolean manageAccessControl(String org, OrganizationVisibility visibility, String[] selectedUsernames) {
        Params payload = new Params();
        payload.addParam("visibility", visibility);
        if (selectedUsernames != null)
            payload.addParam("selected_usernames", selectedUsernames);
        try {
            sendPutRequest(ORGS_PATH + org + CODESPACES_PATH + BILLING_PATH, payload);
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
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public CodespacesList getOrganizationUserCodespaces(Organization org, String username) throws IOException {
        return getOrganizationUserCodespaces(org.getLogin(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:      organization from fetch the list
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public <T> T getOrganizationUserCodespaces(Organization org, String username, ReturnFormat format) throws IOException {
        return getOrganizationUserCodespaces(org.getLogin(), username, format);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public CodespacesList getOrganizationUserCodespaces(String org, String username) throws IOException {
        return getOrganizationUserCodespaces(org, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public <T> T getOrganizationUserCodespaces(String org, String username, ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username + CODESPACES_PATH),
                format);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         organization from fetch the list
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
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public CodespacesList getOrganizationUserCodespaces(Organization org, String username,
                                                        Params queryParams) throws IOException {
        return getOrganizationUserCodespaces(org.getLogin(), username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         organization from fetch the list
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
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public <T> T getOrganizationUserCodespaces(Organization org, String username, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getOrganizationUserCodespaces(org.getLogin(), username, queryParams, format);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
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
     * @return codespaces list as {@link CodespacesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public CodespacesList getOrganizationUserCodespaces(String org, String username, Params queryParams) throws IOException {
        return getOrganizationUserCodespaces(org, username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the codespaces that a member of an organization has for repositories in that organization. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:         the organization name. The name is not case-sensitive
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
     * @return codespaces list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#list-codespaces-for-a-user-in-organization">
     * List codespaces for a user in organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}/codespaces")
    public <T> T getOrganizationUserCodespaces(String org, String username, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnCodespacesList(sendGetRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username + CODESPACES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       organization where delete the codespace
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#delete-a-codespace-from-the-organization">
     * Delete a codespace from the organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}")
    public boolean deleteOrganizationCodespace(Organization org, String username, Codespace codespace) {
        return deleteOrganizationCodespace(org.getLogin(), username, codespace.getName());
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#delete-a-codespace-from-the-organization">
     * Delete a codespace from the organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}")
    public boolean deleteOrganizationCodespace(String org, String username, Codespace codespace) {
        return deleteOrganizationCodespace(org, username, codespace.getName());
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           organization where delete the codespace
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#delete-a-codespace-from-the-organization">
     * Delete a codespace from the organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}")
    public boolean deleteOrganizationCodespace(Organization org, String username, String codespaceName) {
        return deleteOrganizationCodespace(org.getLogin(), username, codespaceName);
    }

    /**
     * Method to delete a user's codespace <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#delete-a-codespace-from-the-organization">
     * Delete a codespace from the organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}")
    public boolean deleteOrganizationCodespace(String org, String username, String codespaceName) {
        try {
            sendDeleteRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username + CODESPACES_PATH + "/" + codespaceName);
            if (apiRequest.getResponseStatusCode() != 202) {
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
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       organization where stop the codespace
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to stop
     * @return codespace as {@link Codespace} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public Codespace stopOrganizationUserCodespace(Organization org, String username, Codespace codespace) throws IOException {
        return stopOrganizationUserCodespace(org.getLogin(), username, codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       organization where stop the codespace
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to stop
     * @param format:    return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespace as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public <T> T stopOrganizationUserCodespace(Organization org, String username, Codespace codespace,
                                               ReturnFormat format) throws IOException {
        return stopOrganizationUserCodespace(org.getLogin(), username, codespace.getName(), format);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to stop
     * @return codespace as {@link Codespace} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public Codespace stopOrganizationUserCodespace(String org, String username, Codespace codespace) throws IOException {
        return stopOrganizationUserCodespace(org, username, codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param username:  the handle for the GitHub user account
     * @param codespace: codespace to stop
     * @param format:    return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespace as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public <T> T stopOrganizationUserCodespace(String org, String username, Codespace codespace,
                                               ReturnFormat format) throws IOException {
        return stopOrganizationUserCodespace(org, username, codespace.getName(), format);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           organization where stop the codespace
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public Codespace stopOrganizationUserCodespace(Organization org, String username, String codespaceName) throws IOException {
        return stopOrganizationUserCodespace(org.getLogin(), username, codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           organization where stop the codespace
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespace as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public <T> T stopOrganizationUserCodespace(Organization org, String username, String codespaceName,
                                               ReturnFormat format) throws IOException {
        return stopOrganizationUserCodespace(org.getLogin(), username, codespaceName, format);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @return codespace as {@link Codespace} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public Codespace stopOrganizationUserCodespace(String org, String username, String codespaceName) throws IOException {
        return stopOrganizationUserCodespace(org, username, codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to stop a user's codespace. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param username:      the handle for the GitHub user account
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespace as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/organizations#stop-a-codespace-for-an-organization-user">
     * Stop a codespace for an organization user</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/members/{username}/codespaces/{codespace_name}/stop")
    public <T> T stopOrganizationUserCodespace(String org, String username, String codespaceName,
                                               ReturnFormat format) throws IOException {
        return returnCodespace(sendPostRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username + CODESPACES_PATH + "/"
                + codespaceName + STOP_PATH, null), format);
    }

}
