package com.tecknobit.githubmanager.teams.members;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.generic.EntityInvitation;
import com.tecknobit.githubmanager.teams.members.records.TeamMembership;
import com.tecknobit.githubmanager.teams.members.records.TeamMembership.TeamRole;
import com.tecknobit.githubmanager.teams.teams.records.Team;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.collaborators.invitations.GitHubInvitationsManager.INVITATIONS_PATH;
import static com.tecknobit.githubmanager.organizations.members.GitHubMembersManager.MEMBERSHIPS_PATH;
import static com.tecknobit.githubmanager.organizations.members.GitHubMembersManager.MEMBERS_PATH;
import static com.tecknobit.githubmanager.records.generic.EntityInvitation.returnEntityInvitations;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubTeamMembersManager} class is useful to manage all GitHub's team members endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members">
 * Team members</a>
 * @see GitHubManager
 **/
public class GitHubTeamMembersManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubTeamMembersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTeamMembersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTeamMembersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTeamMembersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTeamMembersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTeamMembersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTeamMembersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTeamMembersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTeamMembersManager} <br>
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
    public GitHubTeamMembersManager() {
        super();
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:  the organization from fetch the list
     * @param team: the organization from fetch the list
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(Organization org, Team team) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:    the organization from fetch the list
     * @param team:   the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(Organization org, Team team, ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), team.getSlug(), format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the organization from fetch the list
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(String org, Team team) throws IOException {
        return getPendingTeamInvitations(org, team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(String org, Team team, ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org, team.getSlug(), format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(Organization org, String teamSlug) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(String org, String teamSlug) throws IOException {
        return getPendingTeamInvitations(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + INVITATIONS_PATH), format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization from fetch the list
     * @param team:        the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(Organization org, Team team,
                                                                 Params queryParams) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization from fetch the list
     * @param team:        the organization from fetch the list
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
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(Organization org, Team team, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(String org, Team team,
                                                                 Params queryParams) throws IOException {
        return getPendingTeamInvitations(org, team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the organization from fetch the list
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
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(String org, Team team, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org, team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(Organization org, String teamSlug,
                                                                 Params queryParams) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
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
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(Organization org, String teamSlug, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getPendingTeamInvitations(org.getLogin(), teamSlug, queryParams, format);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return team invitations list as {@link ArrayList} of {@link EntityInvitation} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public ArrayList<EntityInvitation> getPendingTeamInvitations(String org, String teamSlug,
                                                                 Params queryParams) throws IOException {
        return getPendingTeamInvitations(org, teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the pending team invitations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
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
     * @return entity invitations list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
     * List pending team invitations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/invitations")
    public <T> T getPendingTeamInvitations(String org, String teamSlug, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return returnEntityInvitations(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + INVITATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:  the organization from fetch the list
     * @param team: the organization from fetch the list
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(Organization org, Team team) throws IOException {
        return getTeamMembers(org.getLogin(), team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:    the organization from fetch the list
     * @param team:   the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(Organization org, Team team, ReturnFormat format) throws IOException {
        return getTeamMembers(org.getLogin(), team.getSlug(), format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the organization from fetch the list
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(String org, Team team) throws IOException {
        return getTeamMembers(org, team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(String org, Team team, ReturnFormat format) throws IOException {
        return getTeamMembers(org, team.getSlug(), format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(Organization org, String teamSlug) throws IOException {
        return getTeamMembers(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getTeamMembers(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(String org, String teamSlug) throws IOException {
        return getTeamMembers(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + MEMBERS_PATH), format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param team:        the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(Organization org, Team team, Params queryParams) throws IOException {
        return getTeamMembers(org.getLogin(), team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param team:        the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(Organization org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamMembers(org.getLogin(), team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(String org, Team team, Params queryParams) throws IOException {
        return getTeamMembers(org, team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(String org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamMembers(org, team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(Organization org, String teamSlug, Params queryParams) throws IOException {
        return getTeamMembers(org.getLogin(), teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(Organization org, String teamSlug, Params queryParams,
                                ReturnFormat format) throws IOException {
        return getTeamMembers(org.getLogin(), teamSlug, queryParams, format);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return members list as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public ArrayList<User> getTeamMembers(String org, String teamSlug, Params queryParams) throws IOException {
        return getTeamMembers(org, teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the members in a team, the team must be visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#list-team-members">
     * List team members</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/members")
    public <T> T getTeamMembers(String org, String teamSlug, Params queryParams,
                                ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + MEMBERS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the membership
     * @param team:     the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership getUserTeamMembership(Organization org, Team team, String username) throws IOException {
        return getUserTeamMembership(org.getLogin(), team.getSlug(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the membership
     * @param team:     the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T getUserTeamMembership(Organization org, Team team, String username, ReturnFormat format) throws IOException {
        return getUserTeamMembership(org.getLogin(), team.getSlug(), username, format);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param team:     the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership getUserTeamMembership(String org, Team team, String username) throws IOException {
        return getUserTeamMembership(org, team.getSlug(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param team:     the organization from fetch the membership
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T getUserTeamMembership(String org, Team team, String username, ReturnFormat format) throws IOException {
        return getUserTeamMembership(org, team.getSlug(), username, format);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the membership
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership getUserTeamMembership(Organization org, String teamSlug, String username) throws IOException {
        return getUserTeamMembership(org.getLogin(), teamSlug, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization from fetch the membership
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T getUserTeamMembership(Organization org, String teamSlug, String username,
                                       ReturnFormat format) throws IOException {
        return getUserTeamMembership(org.getLogin(), teamSlug, username, format);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership getUserTeamMembership(String org, String teamSlug, String username) throws IOException {
        return getUserTeamMembership(org, teamSlug, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a user's membership with a team, the team must be visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
     * Get team membership for a user</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T getUserTeamMembership(String org, String teamSlug, String username, ReturnFormat format) throws IOException {
        return returnTeamMembership(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + MEMBERSHIPS_PATH + username), format);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization where work with the membership
     * @param team:     the organization where work with the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership workWithUserTeamMembership(Organization org, Team team, String username,
                                                     TeamRole role) throws IOException {
        return workWithUserTeamMembership(org.getLogin(), team.getSlug(), username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization where work with the membership
     * @param team:     the organization where work with the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T workWithUserTeamMembership(Organization org, Team team, String username, TeamRole role,
                                            ReturnFormat format) throws IOException {
        return workWithUserTeamMembership(org.getLogin(), team.getSlug(), username, role, format);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param team:     the organization where work with the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership workWithUserTeamMembership(String org, Team team, String username,
                                                     TeamRole role) throws IOException {
        return workWithUserTeamMembership(org, team.getSlug(), username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param team:     the organization where work with the membership
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T workWithUserTeamMembership(String org, Team team, String username, TeamRole role,
                                            ReturnFormat format) throws IOException {
        return workWithUserTeamMembership(org, team.getSlug(), username, role, format);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization where work with the membership
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership workWithUserTeamMembership(Organization org, String teamSlug, String username,
                                                     TeamRole role) throws IOException {
        return workWithUserTeamMembership(org.getLogin(), teamSlug, username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization where work with the membership
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T workWithUserTeamMembership(Organization org, String teamSlug, String username, TeamRole role,
                                            ReturnFormat format) throws IOException {
        return workWithUserTeamMembership(org.getLogin(), teamSlug, username, role, format);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @return team membership as {@link TeamMembership} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public TeamMembership workWithUserTeamMembership(String org, String teamSlug, String username,
                                                     TeamRole role) throws IOException {
        return workWithUserTeamMembership(org, teamSlug, username, role, LIBRARY_OBJECT);
    }

    /**
     * Method to add an organization member to a team. An authenticated organization owner or team maintainer can add
     * organization members to a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @param role:     the role that this user should have in the team
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
     * Add or update team membership for a user</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public <T> T workWithUserTeamMembership(String org, String teamSlug, String username, TeamRole role,
                                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("role", role);
        return returnTeamMembership(sendPutRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug
                + MEMBERSHIPS_PATH + username, payload), format);
    }

    /**
     * Method to create a team membership
     *
     * @param teamMembershipResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return team membership as {@code "format"} defines
     **/
    @Returner
    private <T> T returnTeamMembership(String teamMembershipResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(teamMembershipResponse);
            case LIBRARY_OBJECT:
                return (T) new TeamMembership(new JSONObject(teamMembershipResponse));
            default:
                return (T) teamMembershipResponse;
        }
    }

    /**
     * Method to remove a membership between a user and a team, the authenticated user must have {@code "admin"} permissions to
     * the team or be an owner of the organization that the team is associated with. Removing team membership does not
     * delete the user, it just removes their membership from the team
     *
     * @param org:      the organization where remove the team membership
     * @param team:     the organization from remove the membership
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#remove-team-membership-for-a-user">
     * Remove team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public boolean removeUserTeamMembership(Organization org, Team team, String username) {
        return removeUserTeamMembership(org.getLogin(), team.getSlug(), username);
    }

    /**
     * Method to remove a membership between a user and a team, the authenticated user must have {@code "admin"} permissions to
     * the team or be an owner of the organization that the team is associated with. Removing team membership does not
     * delete the user, it just removes their membership from the team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param team:     the organization from remove the membership
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#remove-team-membership-for-a-user">
     * Remove team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public boolean removeUserTeamMembership(String org, Team team, String username) {
        return removeUserTeamMembership(org, team.getSlug(), username);
    }

    /**
     * Method to remove a membership between a user and a team, the authenticated user must have {@code "admin"} permissions to
     * the team or be an owner of the organization that the team is associated with. Removing team membership does not
     * delete the user, it just removes their membership from the team
     *
     * @param org:      the organization where remove the team membership
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#remove-team-membership-for-a-user">
     * Remove team membership for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public boolean removeUserTeamMembership(Organization org, String teamSlug, String username) {
        return removeUserTeamMembership(org.getLogin(), teamSlug, username);
    }

    /**
     * Method to remove a membership between a user and a team, the authenticated user must have {@code "admin"} permissions to
     * the team or be an owner of the organization that the team is associated with. Removing team membership does not
     * delete the user, it just removes their membership from the team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/members#remove-team-membership-for-a-user">
     * Remove team membership for a user</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/memberships/{username}")
    public boolean removeUserTeamMembership(String org, String teamSlug, String username) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + MEMBERSHIPS_PATH + username);
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
