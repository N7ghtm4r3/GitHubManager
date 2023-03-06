package com.tecknobit.githubmanager.organizations.members;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.records.Invitation;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationInvitation;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MembershipRole;
import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MembershipState;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codespaces.organizations.GithubCodespacesOrganizationsManager.MEMBERS_QUERY_PATH;
import static com.tecknobit.githubmanager.collaborators.invitations.GitHubInvitationsManager.INVITATIONS_PATH;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

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

    public ArrayList<OrganizationInvitation> getFailedOrganizationInvitations(Organization org) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getFailedOrganizationInvitations(Organization org, ReturnFormat format) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), format);
    }

    public ArrayList<OrganizationInvitation> getFailedOrganizationInvitations(String org) throws IOException {
        return getFailedOrganizationInvitations(org, LIBRARY_OBJECT);
    }

    public <T> T getFailedOrganizationInvitations(String org, ReturnFormat format) throws IOException {
        return returnOrganizationInvitations(sendGetRequest(ORGS_PATH + org + FAILED_INVITATIONS_PATH), format);
    }

    public ArrayList<OrganizationInvitation> getFailedOrganizationInvitations(Organization org,
                                                                              Params queryParams) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getFailedOrganizationInvitations(Organization org, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return getFailedOrganizationInvitations(org.getLogin(), queryParams, format);
    }

    public ArrayList<OrganizationInvitation> getFailedOrganizationInvitations(String org,
                                                                              Params queryParams) throws IOException {
        return getFailedOrganizationInvitations(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getFailedOrganizationInvitations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationInvitations(sendGetRequest(ORGS_PATH + org + FAILED_INVITATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    public ArrayList<OrganizationInvitation> getPendingOrganizationInvitations(Organization org) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getPendingOrganizationInvitations(Organization org, ReturnFormat format) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), format);
    }

    public ArrayList<OrganizationInvitation> getPendingOrganizationInvitations(String org) throws IOException {
        return getPendingOrganizationInvitations(org, LIBRARY_OBJECT);
    }

    public <T> T getPendingOrganizationInvitations(String org, ReturnFormat format) throws IOException {
        return returnOrganizationInvitations(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH), format);
    }

    public ArrayList<OrganizationInvitation> getPendingOrganizationInvitations(Organization org,
                                                                               Params queryParams) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getPendingOrganizationInvitations(Organization org, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getPendingOrganizationInvitations(org.getLogin(), queryParams, format);
    }

    public ArrayList<OrganizationInvitation> getPendingOrganizationInvitations(String org,
                                                                               Params queryParams) throws IOException {
        return getPendingOrganizationInvitations(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getPendingOrganizationInvitations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationInvitations(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an organizations invitations list
     *
     * @param organizationInvitationsResponse: obtained from GitHub's response
     * @param format:                          return type formatter -> {@link ReturnFormat}
     * @return organizations invitations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationInvitations(String organizationInvitationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationInvitationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<OrganizationInvitation> organizationInvitations = new ArrayList<>();
                JSONArray jOrganizationInvitations = new JSONArray(organizationInvitationsResponse);
                for (int j = 0; j < jOrganizationInvitations.length(); j++)
                    organizationInvitations.add(new OrganizationInvitation(jOrganizationInvitations.getJSONObject(j)));
                return (T) organizationInvitations;
            default:
                return (T) organizationInvitationsResponse;
        }
    }

    public OrganizationInvitation createOrganizationInvitation(Organization org, Params bodyParams) throws IOException {
        return createOrganizationInvitation(org.getLogin(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationInvitation(Organization org, Params bodyParams, ReturnFormat format) throws IOException {
        return createOrganizationInvitation(org.getLogin(), bodyParams, format);
    }

    public OrganizationInvitation createOrganizationInvitation(String org, Params bodyParams) throws IOException {
        return createOrganizationInvitation(org, bodyParams, LIBRARY_OBJECT);
    }

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
                return (T) new OrganizationInvitation(new JSONObject(organizationInvitationResponse));
            default:
                return (T) organizationInvitationResponse;
        }
    }

    public boolean cancelOrganizationInvitation(Organization org, Invitation invitation) {
        return cancelOrganizationInvitation(org.getLogin(), invitation.getId());
    }

    public boolean cancelOrganizationInvitation(String org, Invitation invitation) {
        return cancelOrganizationInvitation(org, invitation.getId());
    }

    public boolean cancelOrganizationInvitation(Organization org, long invitationId) {
        return cancelOrganizationInvitation(org.getLogin(), invitationId);
    }

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

    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, Invitation invitation) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(Organization org, Invitation invitation,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(String org, Invitation invitation) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(String org, Invitation invitation, ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, long invitationId) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(Organization org, long invitationId, ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(String org, long invitationId) throws IOException {
        return getOrganizationInvitationTeams(org, invitationId, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(String org, long invitationId, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH + "/" + invitationId
                + TEAMS_PATH), format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, Invitation invitation,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(Organization org, Invitation invitation, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitation.getId(), queryParams, format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(String org, Invitation invitation,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(String org, Invitation invitation, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org, invitation.getId(), queryParams, format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(Organization org, long invitationId,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(Organization org, long invitationId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getOrganizationInvitationTeams(org.getLogin(), invitationId, queryParams, format);
    }

    public ArrayList<Team> getOrganizationInvitationTeams(String org, long invitationId,
                                                          Params queryParams) throws IOException {
        return getOrganizationInvitationTeams(org, invitationId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationInvitationTeams(String org, long invitationId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + INVITATIONS_PATH + "/" + invitationId
                + TEAMS_PATH + queryParams.createQueryString()), format);
    }

    public ArrayList<User> getOrganizationMembers(Organization org) throws IOException {
        return getOrganizationMembers(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMembers(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationMembers(org.getLogin(), format);
    }

    public ArrayList<User> getOrganizationMembers(String org) throws IOException {
        return getOrganizationMembers(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMembers(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + MEMBERS_PATH), format);
    }

    public ArrayList<User> getOrganizationMembers(Organization org, Params queryParams) throws IOException {
        return getOrganizationMembers(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMembers(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationMembers(org.getLogin(), queryParams, format);
    }

    public ArrayList<User> getOrganizationMembers(String org, Params queryParams) throws IOException {
        return getOrganizationMembers(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMembers(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + MEMBERS_PATH + queryParams.createQueryString()),
                format);
    }

    public boolean checkUserOrganizationMembership(Organization org, String username) {
        return checkUserOrganizationMembership(org.getLogin(), username);
    }

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

    public boolean removeOrganizationMember(Organization org, String username) {
        return removeOrganizationMember(org.getLogin(), username);
    }

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

    public OrganizationMembership getUserOrganizationMembership(Organization org, String username) throws IOException {
        return getUserOrganizationMembership(org.getLogin(), username, LIBRARY_OBJECT);
    }

    public <T> T getUserOrganizationMembership(Organization org, String username, ReturnFormat format) throws IOException {
        return getUserOrganizationMembership(org.getLogin(), username, format);
    }

    public OrganizationMembership getUserOrganizationMembership(String org, String username) throws IOException {
        return getUserOrganizationMembership(org, username, LIBRARY_OBJECT);
    }

    public <T> T getUserOrganizationMembership(String org, String username, ReturnFormat format) throws IOException {
        return returnOrganizationMembership(sendGetRequest(ORGS_PATH + org + MEMBERSHIPS_PATH + username), format);
    }

    public OrganizationMembership setUserOrganizationMembership(Organization org, String username,
                                                                MembershipRole role) throws IOException {
        return setUserOrganizationMembership(org.getLogin(), username, role, LIBRARY_OBJECT);
    }

    public <T> T setUserOrganizationMembership(Organization org, String username, MembershipRole role,
                                               ReturnFormat format) throws IOException {
        return setUserOrganizationMembership(org.getLogin(), username, role, format);
    }

    public OrganizationMembership setUserOrganizationMembership(String org, String username,
                                                                MembershipRole role) throws IOException {
        return setUserOrganizationMembership(org, username, role, LIBRARY_OBJECT);
    }

    public <T> T setUserOrganizationMembership(String org, String username, MembershipRole role,
                                               ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("role", role);
        return returnOrganizationMembership(sendPutRequest(ORGS_PATH + org + MEMBERSHIPS_PATH + username,
                payload), format);
    }

    public boolean removeUserOrganizationMembership(Organization org, String username) {
        return removeUserOrganizationMembership(org.getLogin(), username);
    }

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

    public ArrayList<User> getPublicOrganizationMembers(Organization org) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getPublicOrganizationMembers(Organization org, ReturnFormat format) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), format);
    }

    public ArrayList<User> getPublicOrganizationMembers(String org) throws IOException {
        return getPublicOrganizationMembers(org, LIBRARY_OBJECT);
    }

    public <T> T getPublicOrganizationMembers(String org, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH), format);
    }

    public ArrayList<User> getPublicOrganizationMembers(Organization org, Params queryParams) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getPublicOrganizationMembers(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getPublicOrganizationMembers(org.getLogin(), queryParams, format);
    }

    public ArrayList<User> getPublicOrganizationMembers(String org, Params queryParams) throws IOException {
        return getPublicOrganizationMembers(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getPublicOrganizationMembers(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + PUBLIC_MEMBERS_PATH
                + queryParams.createQueryString()), format);
    }

    public boolean checkUserPublicOrganizationMembership(Organization org, String username) {
        return checkUserPublicOrganizationMembership(org.getLogin(), username);
    }

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

    public boolean setAUserPublicOrganizationMembership(Organization org, String username) {
        return setAUserPublicOrganizationMembership(org.getLogin(), username);
    }

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

    public boolean removeAUserPublicOrganizationMembership(Organization org, String username) {
        return removeAUserPublicOrganizationMembership(org.getLogin(), username);
    }

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

    public ArrayList<OrganizationMembership> getAuthenticatedUserOrganizationMemberships() throws IOException {
        return getAuthenticatedUserOrganizationMemberships(LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserOrganizationMemberships(ReturnFormat format) throws IOException {
        return returnOrganizationMemberships(sendGetRequest(USER_MEMBERS_ORGS_PATH), format);
    }

    public ArrayList<OrganizationMembership> getAuthenticatedUserOrganizationMemberships(Params queryParams) throws IOException {
        return getAuthenticatedUserOrganizationMemberships(queryParams, LIBRARY_OBJECT);
    }

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

    public OrganizationMembership getAuthenticatedUserOrganizationMembership(Organization org) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserOrganizationMembership(Organization org, ReturnFormat format) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org.getLogin(), format);
    }

    public OrganizationMembership getAuthenticatedUserOrganizationMembership(String org) throws IOException {
        return getAuthenticatedUserOrganizationMembership(org, LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserOrganizationMembership(String org, ReturnFormat format) throws IOException {
        return returnOrganizationMembership(sendGetRequest(USER_MEMBERS_ORGS_PATH + "/" + org), format);
    }

    public OrganizationMembership updateAuthenticatedUserOrganizationMembership(Organization org) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T updateAuthenticatedUserOrganizationMembership(Organization org, ReturnFormat format) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org.getLogin(), format);
    }

    public OrganizationMembership updateAuthenticatedUserOrganizationMembership(String org) throws IOException {
        return updateAuthenticatedUserOrganizationMembership(org, LIBRARY_OBJECT);
    }

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
