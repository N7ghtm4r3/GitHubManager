package com.tecknobit.githubmanager.organizations.members;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.records.Invitation;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MemberFilter;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MembershipRole;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MembershipState;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.generic.EntityInvitation;
import com.tecknobit.githubmanager.records.generic.EntityInvitation.InvitationSource;
import com.tecknobit.githubmanager.records.generic.EntityInvitation.Role;
import com.tecknobit.githubmanager.teams.teams.records.Team;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codespaces.organizations.GithubCodespacesOrganizationsManager.MEMBERS_QUERY_PATH;
import static com.tecknobit.githubmanager.collaborators.invitations.GitHubInvitationsManager.INVITATIONS_PATH;
import static com.tecknobit.githubmanager.records.generic.EntityInvitation.returnEntityInvitations;
import static com.tecknobit.githubmanager.teams.teams.records.Team.returnTeamsList;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubMembersManager} class is useful to manage all GitHub's organization members endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members">
 * Organization members</a>
 * @see GitHubManager
 **/
public class GitHubMembersManager extends GitHubManager {

    /**
     * {@code FAILED_INVITATIONS_PATH} constant for {@code "/failed_invitations"} path
     **/
    public static final String FAILED_INVITATIONS_PATH = "/failed_invitations";

    /**
     * {@code MEMBERS_PATH} constant for {@code "/members"} path
     **/
    public static final String MEMBERS_PATH = "/members";

    /**
     * {@code MEMBERSHIPS_PATH} constant for {@code "/memberships/"} path
     **/
    public static final String MEMBERSHIPS_PATH = "/memberships/";

    /**
     * {@code PUBLIC_MEMBERS_PATH} constant for {@code "/public_members"} path
     **/
    public static final String PUBLIC_MEMBERS_PATH = "/public_members";

    /**
     * {@code USER_MEMBERS_ORGS_PATH} constant for {@code "/user/memberships/orgs"} path
     **/
    public static final String USER_MEMBERS_ORGS_PATH = USER_PATH + MEMBERSHIPS_PATH + "orgs";

    /**
     * Constructor to init a {@link GitHubMembersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMembersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMembersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMembersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMembersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMembersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMembersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMembersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMembersManager} <br>
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
    public GitHubMembersManager() {
        super();
    }

    /**
     * Method to get the list of the pending organization invitations
     *
     * @param org: the organization from fetch the list
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public ArrayList<EntityInvitation> getFailedOrganizationInvitations(Organization org) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending organization invitations
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public <T> T getFailedOrganizationInvitations(Organization org, ReturnFormat format) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), format);
    }

    /**
     * Method to get the list of the pending organization invitations
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public ArrayList<EntityInvitation> getFailedOrganizationInvitations(String org) throws IOException {
        return getFailedOrganizationInvitations(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending organization invitations
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public <T> T getFailedOrganizationInvitations(String org, ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + FAILED_INVITATIONS_PATH), format);
    }

    /**
     * Method to get the list of the pending organization invitations
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
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public ArrayList<EntityInvitation> getFailedOrganizationInvitations(Organization org,
                                                                        Params queryParams) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending organization invitations
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
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public <T> T getFailedOrganizationInvitations(Organization org, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the pending organization invitations
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
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public ArrayList<EntityInvitation> getFailedOrganizationInvitations(String org,
                                                                        Params queryParams) throws IOException {
        return getFailedOrganizationInvitations(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending organization invitations
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
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
     * List failed organization invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/failed_invitations")
    public <T> T getFailedOrganizationInvitations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + FAILED_INVITATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
     *
     * @param org: the organization from fetch the list
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public ArrayList<EntityInvitation> getPendingOrganizationInvitations(Organization org) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public <T> T getPendingOrganizationInvitations(Organization org, ReturnFormat format) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), format);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public ArrayList<EntityInvitation> getPendingOrganizationInvitations(String org) throws IOException {
        return getPendingOrganizationInvitations(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public <T> T getPendingOrganizationInvitations(String org, ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH), format);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
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
     *                        <li>
     *                            {@code "role"} -> filter invitations by their member role, constants available {@link Role}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "invitation_source"} -> filter invitations by their invitation source,
     *                            constants available {@link InvitationSource} - [string, default all]
     *                        </li>
     *                     </ul>
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public ArrayList<EntityInvitation> getPendingOrganizationInvitations(Organization org,
                                                                         Params queryParams) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
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
     *                        <li>
     *                            {@code "role"} -> filter invitations by their member role, constants available {@link Role}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "invitation_source"} -> filter invitations by their invitation source,
     *                            constants available {@link InvitationSource} - [string, default all]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public <T> T getPendingOrganizationInvitations(Organization org, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
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
     *                        <li>
     *                            {@code "role"} -> filter invitations by their member role, constants available {@link Role}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "invitation_source"} -> filter invitations by their invitation source,
     *                            constants available {@link InvitationSource} - [string, default all]
     *                        </li>
     *                     </ul>
     * @return organization invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public ArrayList<EntityInvitation> getPendingOrganizationInvitations(String org,
                                                                         Params queryParams) throws IOException {
        return getPendingOrganizationInvitations(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the return hash contains a role field which refers to the Organization Invitation role and will be
     * one of the following values: {@code "direct_member"}, {@code "admin"}, {@code "billing_manager"}, or
     * {@code "hiring_manager"}. If the invitee is not a GitHub member, the login field in the return hash will be null
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
     *                        <li>
     *                            {@code "role"} -> filter invitations by their member role, constants available {@link Role}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "invitation_source"} -> filter invitations by their invitation source,
     *                            constants available {@link InvitationSource} - [string, default all]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return organization invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
     * List pending organization invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/invitations")
    public <T> T getPendingOrganizationInvitations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to invite people to an organization by using their GitHub user ID or their email address. In order to create
     * invitations in an organization, the authenticated user must be an organization owner.
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param org:        the organization to create the invitation
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "invitee_id"} -> <b>required unless you provide email</b>. GitHub user ID for the
     *                           person you are inviting - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> <b>required unless you provide {@code "invitee_id"}</b>. Email address of the
     *                           person you are inviting, which can be an existing GitHub user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "role"} -> the role for the new member:
     *                                          <ul>
     *                                              <li>
     *                                                  <b>admin</b> - Organization owners with full administrative rights to
     *                                                  the organization and complete access to all repositories and teams
     *                                              </li>
     *                                              <li>
     *                                                  <b>direct_member</b> - Non-owner organization members with ability to
     *                                                  see other members and join teams by invitation
     *                                              </li>
     *                                              <li>
     *                                                  <b>billing_manager</b> - Non-owner organization members with ability to
     *                                                  manage the billing settings of your organization
     *                                              </li>
     *                                          </ul> - [string, default direct_member]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> whether the notification has been read - [string]
     *                       </li>
     *                    </ul>
     * @return organization invitation as {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#create-an-organization-invitation">
     * Create an organization invitation</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/invitations")
    public EntityInvitation createOrganizationInvitation(Organization org, Params bodyParams) throws IOException {
        return createOrganizationInvitation(org.getLogin(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to invite people to an organization by using their GitHub user ID or their email address. In order to create
     * invitations in an organization, the authenticated user must be an organization owner.
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param org:        the organization to create the invitation
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "invitee_id"} -> <b>required unless you provide email</b>. GitHub user ID for the
     *                           person you are inviting - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> <b>required unless you provide {@code "invitee_id"}</b>. Email address of the
     *                           person you are inviting, which can be an existing GitHub user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "role"} -> the role for the new member:
     *                                          <ul>
     *                                              <li>
     *                                                  <b>admin</b> - Organization owners with full administrative rights to
     *                                                  the organization and complete access to all repositories and teams
     *                                              </li>
     *                                              <li>
     *                                                  <b>direct_member</b> - Non-owner organization members with ability to
     *                                                  see other members and join teams by invitation
     *                                              </li>
     *                                              <li>
     *                                                  <b>billing_manager</b> - Non-owner organization members with ability to
     *                                                  manage the billing settings of your organization
     *                                              </li>
     *                                          </ul> - [string, default direct_member]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> whether the notification has been read - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organizations invitation as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#create-an-organization-invitation">
     * Create an organization invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/invitations")
    public <T> T createOrganizationInvitation(Organization org, Params bodyParams, ReturnFormat format) throws IOException {
        return createOrganizationInvitation(org.getLogin(), bodyParams, format);
    }

    /**
     * Method to invite people to an organization by using their GitHub user ID or their email address. In order to create
     * invitations in an organization, the authenticated user must be an organization owner.
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "invitee_id"} -> <b>required unless you provide email</b>. GitHub user ID for the
     *                           person you are inviting - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> <b>required unless you provide {@code "invitee_id"}</b>. Email address of the
     *                           person you are inviting, which can be an existing GitHub user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "role"} -> the role for the new member:
     *                                          <ul>
     *                                              <li>
     *                                                  <b>admin</b> - Organization owners with full administrative rights to
     *                                                  the organization and complete access to all repositories and teams
     *                                              </li>
     *                                              <li>
     *                                                  <b>direct_member</b> - Non-owner organization members with ability to
     *                                                  see other members and join teams by invitation
     *                                              </li>
     *                                              <li>
     *                                                  <b>billing_manager</b> - Non-owner organization members with ability to
     *                                                  manage the billing settings of your organization
     *                                              </li>
     *                                          </ul> - [string, default direct_member]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> whether the notification has been read - [string]
     *                       </li>
     *                    </ul>
     * @return organization invitation as {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#create-an-organization-invitation">
     * Create an organization invitation</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/invitations")
    public EntityInvitation createOrganizationInvitation(String org, Params bodyParams) throws IOException {
        return createOrganizationInvitation(org, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to invite people to an organization by using their GitHub user ID or their email address. In order to create
     * invitations in an organization, the authenticated user must be an organization owner.
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "invitee_id"} -> <b>required unless you provide email</b>. GitHub user ID for the
     *                           person you are inviting - [integer]
     *                       </li>
     *                       <li>
     *                           {@code "email"} -> <b>required unless you provide {@code "invitee_id"}</b>. Email address of the
     *                           person you are inviting, which can be an existing GitHub user - [string]
     *                       </li>
     *                       <li>
     *                           {@code "role"} -> the role for the new member:
     *                                          <ul>
     *                                              <li>
     *                                                  <b>admin</b> - Organization owners with full administrative rights to
     *                                                  the organization and complete access to all repositories and teams
     *                                              </li>
     *                                              <li>
     *                                                  <b>direct_member</b> - Non-owner organization members with ability to
     *                                                  see other members and join teams by invitation
     *                                              </li>
     *                                              <li>
     *                                                  <b>billing_manager</b> - Non-owner organization members with ability to
     *                                                  manage the billing settings of your organization
     *                                              </li>
     *                                          </ul> - [string, default direct_member]
     *                       </li>
     *                       <li>
     *                           {@code "team_ids"} -> whether the notification has been read - [string]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organizations invitation as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#create-an-organization-invitation">
     * Create an organization invitation</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/invitations")
    public <T> T createOrganizationInvitation(String org, Params bodyParams, ReturnFormat format) throws IOException {
        return returnOrganizationInvitation(sendPostRequest(ORGS_PATH + org + INVITATIONS_PATH, bodyParams),
                format);
    }

    /**
     * Method to create an organizations invitation
     *
     * @param organizationInvitationResponse: obtained from GitHub's response
     * @param format:                         return type formatter -> {@link ReturnFormat}
     * @return organizations invitation as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationInvitation(String organizationInvitationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationInvitationResponse);
            case LIBRARY_OBJECT:
                return (T) new EntityInvitation(new JSONObject(organizationInvitationResponse));
            default:
                return (T) organizationInvitationResponse;
        }
    }

    /**
     * Method to cancel an organization invitation. In order to cancel an organization invitation, the authenticated user
     * must be an organization owner.
     * This endpoint triggers notifications
     *
     * @param org:        the organization from cancel the invitation
     * @param invitation: the invitation to cancel
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#cancel-an-organization-invitation">
     * Cancel an organization invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/invitations/{invitation_id}")
    public boolean cancelOrganizationInvitation(Organization org, Invitation invitation) {
        return cancelOrganizationInvitation(org.getLogin(), invitation.getId());
    }

    /**
     * Method to cancel an organization invitation. In order to cancel an organization invitation, the authenticated user
     * must be an organization owner.
     * This endpoint triggers notifications
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param invitation: the invitation to cancel
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#cancel-an-organization-invitation">
     * Cancel an organization invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/invitations/{invitation_id}")
    public boolean cancelOrganizationInvitation(String org, Invitation invitation) {
        return cancelOrganizationInvitation(org, invitation.getId());
    }

    /**
     * Method to cancel an organization invitation. In order to cancel an organization invitation, the authenticated user
     * must be an organization owner.
     * This endpoint triggers notifications
     *
     * @param org:          the organization from cancel the invitation
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#cancel-an-organization-invitation">
     * Cancel an organization invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/invitations/{invitation_id}")
    public boolean cancelOrganizationInvitation(Organization org, long invitationId) {
        return cancelOrganizationInvitation(org.getLogin(), invitationId);
    }

    /**
     * Method to cancel an organization invitation. In order to cancel an organization invitation, the authenticated user
     * must be an organization owner.
     * This endpoint triggers notifications
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#cancel-an-organization-invitation">
     * Cancel an organization invitation</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/invitations/{invitation_id}")
    public boolean cancelOrganizationInvitation(String org, long invitationId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + INVITATIONS_PATH + "/" + invitationId);
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
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:        the organization from fetch the list
     * @param invitation: the invitation from fetch the list
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, Invitation invitation) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:        the organization from fetch the list
     * @param invitation: the invitation from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(Organization org, Invitation invitation,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param invitation: the invitation from fetch the list
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(String org, Invitation invitation) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param invitation: the invitation from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(String org, Invitation invitation, ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization from fetch the list
     * @param invitationId: the unique identifier of the invitation
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, long invitationId) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization from fetch the list
     * @param invitationId: the unique identifier of the invitation
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(Organization org, long invitationId, ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(String org, long invitationId) throws IOException {
        return getOrganizationInvitationTeams(org, invitationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(String org, long invitationId, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH + "/" + invitationId
                + TEAMS_PATH), format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:         the organization from fetch the list
     * @param invitation:  the invitation from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, Invitation invitation,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:         the organization from fetch the list
     * @param invitation:  the invitation from fetch the list
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
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(Organization org, Invitation invitation, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param invitation:  the invitation from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(String org, Invitation invitation,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param invitation:  the invitation from fetch the list
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
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(String org, Invitation invitation, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization from fetch the list
     * @param invitationId: the unique identifier of the invitation
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, long invitationId,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization from fetch the list
     * @param invitationId: the unique identifier of the invitation
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(Organization org, long invitationId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, queryParams, format);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return teams list as {@link ArrayList} of {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public ArrayList<Team> getOrganizationInvitationTeams(String org, long invitationId,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org, invitationId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams associated with an invitation. In order to see invitations in an organization,
     * the authenticated user must be an organization owner
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return teams list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-invitation-teams">
     * List organization invitation teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/invitations/{invitation_id}/teams")
    public <T> T getOrganizationInvitationTeams(String org, long invitationId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH + "/" + invitationId
                + TEAMS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public ArrayList<User> getOrganizationMembers(Organization org) throws IOException {
        return getOrganizationMembers(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public <T> T getOrganizationMembers(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationMembers(org.getLogin(), format);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public ArrayList<User> getOrganizationMembers(String org) throws IOException {
        return getOrganizationMembers(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public <T> T getOrganizationMembers(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + MEMBERS_PATH), format);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link MembershipRole} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public ArrayList<User> getOrganizationMembers(Organization org, Params queryParams) throws IOException {
        return getOrganizationMembers(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link MembershipRole} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public <T> T getOrganizationMembers(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationMembers(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link MembershipRole} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public ArrayList<User> getOrganizationMembers(String org, Params queryParams) throws IOException {
        return getOrganizationMembers(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all users who are members of an organization. If the authenticated user is also a member of
     * this organization then both concealed and public members will be returned
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "filter"} -> filter members returned in the list. {@code "2fa_disabled"} means that only
     *                            members without two-factor authentication enabled will be returned. This options is
     *                            only available for organization owners, constants available {@link MemberFilter}
     *                            - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "role"} -> filter members returned by their role, constants available
     *                            {@link MembershipRole} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-members">
     * List organization members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/members")
    public <T> T getOrganizationMembers(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + MEMBERS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to check if a user is, publicly or privately, a member of the organization
     *
     * @param org:      the organization where check if a user is, publicly or privately, a member of the organization
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#check-organization-membership-for-a-user">
     * Check organization membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}")
    public boolean checkUserOrganizationMembership(Organization org, String username) {
        return checkUserOrganizationMembership(org.getLogin(), username);
    }

    /**
     * Method to check if a user is, publicly or privately, a member of the organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#check-organization-membership-for-a-user">
     * Check organization membership for a user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/members/{username}")
    public boolean checkUserOrganizationMembership(String org, String username) {
        try {
            sendGetRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username);
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
     * Method to remove a user from this list, this will remove them from all teams, and they will no longer have any access to
     * the organization's repositories
     *
     * @param org:      the organization where remove the member
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-an-organization-member">
     * Remove an organization member</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}")
    public boolean removeOrganizationMember(Organization org, String username) {
        return removeOrganizationMember(org.getLogin(), username);
    }

    /**
     * Method to remove a user from this list, this will remove them from all teams, and they will no longer have any access to
     * the organization's repositories
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-an-organization-member">
     * Remove an organization member</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/members/{username}")
    public boolean removeOrganizationMember(String org, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + MEMBERS_QUERY_PATH + username);
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
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-organization-membership-for-a-user">
     * Get organization membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/memberships/{username}")
    public OrganizationMembership getUserOrganizationMembership(Organization org, String username) throws IOException {
        return getUserOrganizationMembership(org.getLogin(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-organization-membership-for-a-user">
     * Get organization membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/memberships/{username}")
    public <T> T getUserOrganizationMembership(Organization org, String username, ReturnFormat format) throws IOException {
        return getUserOrganizationMembership(org.getLogin(), username, format);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-organization-membership-for-a-user">
     * Get organization membership for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/memberships/{username}")
    public OrganizationMembership getUserOrganizationMembership(String org, String username) throws IOException {
        return getUserOrganizationMembership(org, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-organization-membership-for-a-user">
     * Get organization membership for a user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/memberships/{username}")
    public <T> T getUserOrganizationMembership(String org, String username, ReturnFormat format) throws IOException {
        return returnOrganizationMembership(sendGetRequest(ORGS_PATH + org + MEMBERSHIPS_PATH + username), format);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization where set the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role to give the user in the organization. Can be one of:
     *                  <ul>
     *                        <li>
     *                            <b>admin</b>- The user will become an owner of the organization
     *                        </li>
     *                        <li>
     *                            <b>member</b> - The user will become a non-owner member of the organization
     *                        </li>
     *                  </ul>
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-organization-membership-for-a-user">
     * Set organization membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/memberships/{username}")
    public OrganizationMembership setUserOrganizationMembership(Organization org, String username,
                                                                MembershipRole role) throws IOException {
        return setUserOrganizationMembership(org.getLogin(), username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization where set the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role to give the user in the organization. Can be one of:
     *                  <ul>
     *                        <li>
     *                            <b>admin</b>- The user will become an owner of the organization
     *                        </li>
     *                        <li>
     *                            <b>member</b> - The user will become a non-owner member of the organization
     *                        </li>
     *                  </ul>
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-organization-membership-for-a-user">
     * Set organization membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/memberships/{username}")
    public <T> T setUserOrganizationMembership(Organization org, String username, MembershipRole role,
                                               ReturnFormat format) throws IOException {
        return setUserOrganizationMembership(org.getLogin(), username, role, format);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param role:     the role to give the user in the organization. Can be one of:
     *                  <ul>
     *                        <li>
     *                            <b>admin</b>- The user will become an owner of the organization
     *                        </li>
     *                        <li>
     *                            <b>member</b> - The user will become a non-owner member of the organization
     *                        </li>
     *                  </ul>
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-organization-membership-for-a-user">
     * Set organization membership for a user</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/orgs/{org}/memberships/{username}")
    public OrganizationMembership setUserOrganizationMembership(String org, String username,
                                                                MembershipRole role) throws IOException {
        return setUserOrganizationMembership(org, username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to get organization membership for a user <br>
     * In order to get a user's membership with an organization, the authenticated user must be an organization member.
     * The state parameter in the response can be used to identify the user's membership status
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param role:     the role to give the user in the organization. Can be one of:
     *                  <ul>
     *                        <li>
     *                            <b>admin</b>- The user will become an owner of the organization
     *                        </li>
     *                        <li>
     *                            <b>member</b> - The user will become a non-owner member of the organization
     *                        </li>
     *                  </ul>
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-organization-membership-for-a-user">
     * Set organization membership for a user</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/memberships/{username}")
    public <T> T setUserOrganizationMembership(String org, String username, MembershipRole role,
                                               ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("role", role);
        return returnOrganizationMembership(sendPutRequest(ORGS_PATH + org + MEMBERSHIPS_PATH + username,
                payload), format);
    }

    /**
     * Method to remove organization membership for a user <br>
     * In order to remove a user's membership with an organization, the authenticated user must be an organization owner. <br>
     * If the specified user is an active member of the organization, this will remove them from the organization.
     * If the specified user has been invited to the organization, this will cancel their invitation.
     * The specified user will receive an email notification in both cases
     *
     * @param org:      the organization where remove the membership
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-organization-membership-for-a-user">
     * Remove organization membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/memberships/{username}")
    public boolean removeUserOrganizationMembership(Organization org, String username) {
        return removeUserOrganizationMembership(org.getLogin(), username);
    }

    /**
     * Method to remove organization membership for a user <br>
     * In order to remove a user's membership with an organization, the authenticated user must be an organization owner. <br>
     * If the specified user is an active member of the organization, this will remove them from the organization.
     * If the specified user has been invited to the organization, this will cancel their invitation.
     * The specified user will receive an email notification in both cases
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-organization-membership-for-a-user">
     * Remove organization membership for a user</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/memberships/{username}")
    public boolean removeUserOrganizationMembership(String org, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + MEMBERSHIPS_PATH + username);
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
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public ArrayList<User> getPublicOrganizationMembers(Organization org) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public <T> T getPublicOrganizationMembers(Organization org, ReturnFormat format) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), format);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public ArrayList<User> getPublicOrganizationMembers(String org) throws IOException {
        return getPublicOrganizationMembers(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public <T> T getPublicOrganizationMembers(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH), format);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public ArrayList<User> getPublicOrganizationMembers(Organization org, Params queryParams) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public <T> T getPublicOrganizationMembers(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public ArrayList<User> getPublicOrganizationMembers(String org, Params queryParams) throws IOException {
        return getPublicOrganizationMembers(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the  public organization members <br>
     * Members of an organization can choose to have their membership publicized or not
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-public-organization-members">
     * List public organization members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/public_members")
    public <T> T getPublicOrganizationMembers(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to check public organization membership for a user
     *
     * @param org:      the organization where check public organization membership for a user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#check-public-organization-membership-for-a-user">
     * Check public organization membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/public_members/{username}")
    public boolean checkUserPublicOrganizationMembership(Organization org, String username) {
        return checkUserPublicOrganizationMembership(org.getLogin(), username);
    }

    /**
     * Method to check public organization membership for a user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#check-public-organization-membership-for-a-user">
     * Check public organization membership for a user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/public_members/{username}")
    public boolean checkUserPublicOrganizationMembership(String org, String username) {
        try {
            sendGetRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH + "/" + username);
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
     * Method to set public organization membership for the authenticated user <br>
     * The user can publicize their own membership. (A user cannot publicize the membership for another user.)
     * Note that you'll need to set Content-Length to zero when calling out to this endpoint. For more information, see "HTTP verbs."
     *
     * @param org:      the organization where check public organization membership for a user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-public-organization-membership-for-the-authenticated-user">
     * Set public organization membership for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/public_members/{username}")
    public boolean setAUserPublicOrganizationMembership(Organization org, String username) {
        return setAUserPublicOrganizationMembership(org.getLogin(), username);
    }

    /**
     * Method to set public organization membership for the authenticated user <br>
     * The user can publicize their own membership. (A user cannot publicize the membership for another user.)
     * Note that you'll need to set Content-Length to zero when calling out to this endpoint. For more information, see "HTTP verbs."
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#set-public-organization-membership-for-the-authenticated-user">
     * Set public organization membership for the authenticated user</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/public_members/{username}")
    public boolean setAUserPublicOrganizationMembership(String org, String username) {
        try {
            sendPutRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH + "/" + username, null);
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
     * Method to remove public organization membership for the authenticated user
     *
     * @param org:      the organization where remove the public organization membership for a user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-public-organization-membership-for-the-authenticated-user">
     * Remove public organization membership for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/public_members/{username}")
    public boolean removeAUserPublicOrganizationMembership(Organization org, String username) {
        return removeAUserPublicOrganizationMembership(org.getLogin(), username);
    }

    /**
     * Method to remove public organization membership for the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#remove-public-organization-membership-for-the-authenticated-user">
     * Remove public organization membership for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/public_members/{username}")
    public boolean removeAUserPublicOrganizationMembership(String org, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH + "/" + username);
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
     * Method to get the list of the organization memberships for the authenticated user <br>
     * No-any params required
     *
     * @return memberships list as {@link ArrayList} of {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-memberships-for-the-authenticated-user">
     * List organization memberships for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/memberships/orgs")
    public ArrayList<OrganizationMembership> getAuthenticatedUserOrganizationMemberships() throws IOException {
        return getAuthenticatedUserOrganizationMemberships(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization memberships for the authenticated user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization memberships list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-memberships-for-the-authenticated-user">
     * List organization memberships for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/memberships/orgs")
    public <T> T getAuthenticatedUserOrganizationMemberships(ReturnFormat format) throws IOException {
        return returnOrganizationMemberships(sendGetRequest(USER_MEMBERS_ORGS_PATH), format);
    }

    /**
     * Method to get the list of the organization memberships for the authenticated use
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the memberships to return. If not specified,
     *                            the API returns both active and pending memberships, constants available
     *                            {@link MembershipState} - [filter]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return memberships list as {@link ArrayList} of {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-memberships-for-the-authenticated-user">
     * List organization memberships for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/memberships/orgs")
    public ArrayList<OrganizationMembership> getAuthenticatedUserOrganizationMemberships(Params queryParams) throws IOException {
        return getAuthenticatedUserOrganizationMemberships(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization memberships for the authenticated use
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the memberships to return. If not specified,
     *                            the API returns both active and pending memberships, constants available
     *                            {@link MembershipState} - [filter]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return organization memberships list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#list-organization-memberships-for-the-authenticated-user">
     * List organization memberships for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/memberships/orgs")
    public <T> T getAuthenticatedUserOrganizationMemberships(Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationMemberships(sendGetRequest(USER_MEMBERS_ORGS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an organization memberships list
     *
     * @param organizationMembershipsResponse: obtained from GitHub's response
     * @param format:                          return type formatter -> {@link ReturnFormat}
     * @return organization memberships list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationMemberships(String organizationMembershipsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationMembershipsResponse);
            case LIBRARY_OBJECT:
                ArrayList<OrganizationMembership> organizationMemberships = new ArrayList<>();
                JSONArray jOrganizationMemberships = new JSONArray(organizationMembershipsResponse);
                for (int j = 0; j < jOrganizationMemberships.length(); j++)
                    organizationMemberships.add(new OrganizationMembership(jOrganizationMemberships.getJSONObject(j)));
                return (T) organizationMemberships;
            default:
                return (T) organizationMembershipsResponse;
        }
    }

    /**
     * Method to get an organization membership for the authenticated user
     *
     * @param org: the organization from fetch the membership
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-an-organization-membership-for-the-authenticated-user">
     * Get an organization membership for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/memberships/orgs/{org}")
    public OrganizationMembership getAuthenticatedUserOrganizationMembership(Organization org) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization membership for the authenticated user
     *
     * @param org:    the organization from fetch the membership
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-an-organization-membership-for-the-authenticated-user">
     * Get an organization membership for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/memberships/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationMembership(Organization org, ReturnFormat format) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org.getLogin(), format);
    }

    /**
     * Method to get an organization membership for the authenticated user
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-an-organization-membership-for-the-authenticated-user">
     * Get an organization membership for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/memberships/orgs/{org}")
    public OrganizationMembership getAuthenticatedUserOrganizationMembership(String org) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get an organization membership for the authenticated user
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#get-an-organization-membership-for-the-authenticated-user">
     * Get an organization membership for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/memberships/orgs/{org}")
    public <T> T getAuthenticatedUserOrganizationMembership(String org, ReturnFormat format) throws IOException {
        return returnOrganizationMembership(sendGetRequest(USER_MEMBERS_ORGS_PATH + "/" + org), format);
    }

    /**
     * Method to update an organization membership for the authenticated user
     *
     * @param org: the organization where update the membership
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#update-an-organization-membership-for-the-authenticated-user">
     * Update an organization membership for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/user/memberships/orgs/{org}")
    public OrganizationMembership updateAuthenticatedUserOrganizationMembership(Organization org) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to update an organization membership for the authenticated user
     *
     * @param org:    the organization where update the membership
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#update-an-organization-membership-for-the-authenticated-user">
     * Update an organization membership for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/user/memberships/orgs/{org}")
    public <T> T updateAuthenticatedUserOrganizationMembership(Organization org, ReturnFormat format) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org.getLogin(), format);
    }

    /**
     * Method to update an organization membership for the authenticated user
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization membership as {@link OrganizationMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#update-an-organization-membership-for-the-authenticated-user">
     * Update an organization membership for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/user/memberships/orgs/{org}")
    public OrganizationMembership updateAuthenticatedUserOrganizationMembership(String org) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org, LIBRARY_OBJECT);
    }

    /**
     * Method to update an organization membership for the authenticated user
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/members#update-an-organization-membership-for-the-authenticated-user">
     * Update an organization membership for the authenticated user</a>
     **/
    @RequestPath(method = PATCH, path = "/user/memberships/orgs/{org}")
    public <T> T updateAuthenticatedUserOrganizationMembership(String org, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("state", MembershipState.active);
        return returnOrganizationMembership(sendPatchRequest(USER_MEMBERS_ORGS_PATH + "/" + org, payload), format);
    }

    /**
     * Method to create an organization membership
     *
     * @param organizationMembershipResponse: obtained from GitHub's response
     * @param format:                         return type formatter -> {@link ReturnFormat}
     * @return organization membership as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationMembership(String organizationMembershipResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationMembershipResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationMembership(new JSONObject(organizationMembershipResponse));
            default:
                return (T) organizationMembershipResponse;
        }
    }

}
