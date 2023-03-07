package com.tecknobit.githubmanager.organizations.outsidecollaborators;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.parents.User;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

/**
 * The {@code GitHubOutsideCollaboratorsManager} class is useful to manage all GitHub's outside collaborators endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators">
 * Outside Collaborators</a>
 * @see GitHubManager
 **/
public class GitHubOutsideCollaboratorsManager extends GitHubManager {

    /**
     * {@code OUTSIDE_COLLABORATORS_PATH} constant for {@code "/outside_collaborators"} path
     **/
    public static final String OUTSIDE_COLLABORATORS_PATH = "/outside_collaborators";

    /**
     * Constructor to init a {@link GitHubOutsideCollaboratorsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOutsideCollaboratorsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOutsideCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOutsideCollaboratorsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOutsideCollaboratorsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOutsideCollaboratorsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOutsideCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOutsideCollaboratorsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOutsideCollaboratorsManager} <br>
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
    public GitHubOutsideCollaboratorsManager() {
        super();
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org: the organization from fetch the list
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public ArrayList<User> getOrganizationOutsideCollaborators(Organization org) throws IOException {
        return getOrganizationOutsideCollaborators(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public <T> T getOrganizationOutsideCollaborators(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationOutsideCollaborators(org.getLogin(), format);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public ArrayList<User> getOrganizationOutsideCollaborators(String org) throws IOException {
        return getOrganizationOutsideCollaborators(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public <T> T getOrganizationOutsideCollaborators(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + OUTSIDE_COLLABORATORS_PATH), format);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link OrganizationMembership.MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link OrganizationMembership.MembershipRole} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public ArrayList<User> getOrganizationOutsideCollaborators(Organization org, Params queryParams) throws IOException {
        return getOrganizationOutsideCollaborators(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link OrganizationMembership.MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link OrganizationMembership.MembershipRole} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public <T> T getOrganizationOutsideCollaborators(Organization org, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return getOrganizationOutsideCollaborators(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link OrganizationMembership.MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link OrganizationMembership.MembershipRole} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return users list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public ArrayList<User> getOrganizationOutsideCollaborators(String org, Params queryParams) throws IOException {
        return getOrganizationOutsideCollaborators(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all users who are outside collaborators of an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link OrganizationMembership.MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link OrganizationMembership.MembershipRole} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#list-outside-collaborators-for-an-organization">
     * List outside collaborators for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/outside_collaborators")
    public <T> T getOrganizationOutsideCollaborators(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + OUTSIDE_COLLABORATORS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to convert an organization member to outside collaborator <br>
     * When an organization member is converted to an outside collaborator, they'll only have access to the repositories
     * that their current team membership allows. The user will no longer be a member of the organization.
     * For more information, see "Converting an organization member to an outside collaborator".
     * Converting an organization member to an outside collaborator may be restricted by enterprise administrators.
     * For more information, see "Enforcing repository management policies in your enterprise."
     *
     * @param org:      the organization where check if a user is, publicly or privately, a member of the organization
     * @param username: the handle for the GitHub user account
     * @param async:    when set to {@code "true"}, the request will be performed asynchronously. Returns a 202 status code when the
     *                  job is successfully queued
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#convert-an-organization-member-to-outside-collaborator">
     * Convert an organization member to outside collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/outside_collaborators/{username}")
    public boolean execConversion(Organization org, String username, boolean async) {
        return execConversion(org.getLogin(), username, async);
    }

    /**
     * Method to convert an organization member to outside collaborator <br>
     * When an organization member is converted to an outside collaborator, they'll only have access to the repositories
     * that their current team membership allows. The user will no longer be a member of the organization.
     * For more information, see "Converting an organization member to an outside collaborator".
     * Converting an organization member to an outside collaborator may be restricted by enterprise administrators.
     * For more information, see "Enforcing repository management policies in your enterprise."
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param async:    when set to {@code "true"}, the request will be performed asynchronously. Returns a 202 status code when the
     *                  job is successfully queued
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#convert-an-organization-member-to-outside-collaborator">
     * Convert an organization member to outside collaborator</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/outside_collaborators/{username}")
    public boolean execConversion(String org, String username, boolean async) {
        try {
            Params payload = new Params();
            payload.addParam("async", async);
            sendPutRequest(ORGS_PATH + org + OUTSIDE_COLLABORATORS_PATH + "/" + username, payload);
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
     * Method to remove outside collaborator from an organization <br>
     * Removing a user from this list will remove them from all the organization's repositories
     *
     * @param org:      the organization where check if a user is, publicly or privately, a member of the organization
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#remove-outside-collaborator-from-an-organization">
     * Remove outside collaborator from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/outside_collaborators/{username}")
    public boolean removeOutsideCollaborator(Organization org, String username) {
        return removeOutsideCollaborator(org.getLogin(), username);
    }

    /**
     * Method to remove outside collaborator from an organization <br>
     * Removing a user from this list will remove them from all the organization's repositories
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/outside-collaborators#remove-outside-collaborator-from-an-organization">
     * Remove outside collaborator from an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/outside_collaborators/{username}")
    public boolean removeOutsideCollaborator(String org, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + OUTSIDE_COLLABORATORS_PATH + "/" + username);
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
