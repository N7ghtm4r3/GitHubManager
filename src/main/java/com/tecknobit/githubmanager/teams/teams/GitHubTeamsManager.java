package com.tecknobit.githubmanager.teams.teams;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.apps.records.AppPermissions.APermissionType;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.projects.boards.records.Project;
import com.tecknobit.githubmanager.records.generic.Permissions.Permission;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.teams.teams.records.Team;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.projects.boards.GitHubBoardsManager.PROJECTS_PATH;
import static com.tecknobit.githubmanager.projects.boards.records.Project.returnProject;
import static com.tecknobit.githubmanager.projects.boards.records.Project.returnProjects;
import static com.tecknobit.githubmanager.repositories.repositories.GitHubRepositoriesManager.REPOS_QUERY_PATH;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepositories;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepository;
import static com.tecknobit.githubmanager.teams.teams.records.Team.returnTeamsList;

/**
 * The {@code GitHubTeamsManager} class is useful to manage all GitHub's teams endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams">
 * Teams</a>
 * @see GitHubManager
 **/
public class GitHubTeamsManager extends GitHubManager {

    /**
     * {@code USER_TEAMS_PATH} constant for {@code "user/teams"} path
     **/
    public static final String USER_TEAMS_PATH = "user/teams";

    /**
     * Constructor to init a {@link GitHubTeamsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTeamsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTeamsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTeamsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTeamsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTeamsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTeamsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTeamsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTeamsManager} <br>
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
    public GitHubTeamsManager() {
        super();
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
     *
     * @param org: the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public ArrayList<Team> getTeams(Organization org) throws IOException {
        return getTeams(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public <T> T getTeams(Organization org, ReturnFormat format) throws IOException {
        return getTeams(org.getLogin(), format);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
     *
     * @param org: the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public ArrayList<Team> getTeams(String org) throws IOException {
        return getTeams(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public <T> T getTeams(String org, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH), format);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public ArrayList<Team> getTeams(Organization org, Params queryParams) throws IOException {
        return getTeams(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public <T> T getTeams(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getTeams(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public ArrayList<Team> getTeams(String org, Params queryParams) throws IOException {
        return getTeams(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams in an organization that are visible to the authenticated user
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams">
     * List teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams")
    public <T> T getTeams(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:  the organization where create the team
     * @param name: the name of the team
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public Team createTeam(Organization org, String name) throws IOException {
        return createTeam(org.getLogin(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:    the organization where create the team
     * @param name:   the name of the team
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public <T> T createTeam(Organization org, String name, ReturnFormat format) throws IOException {
        return createTeam(org.getLogin(), name, format);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param name: the name of the team
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public Team createTeam(String org, String name) throws IOException {
        return createTeam(org, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   the name of the team
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public <T> T createTeam(String org, String name, ReturnFormat format) throws IOException {
        return createTeam(org, name, null, format);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:        the organization where create the team
     * @param name:       the name of the team
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainers"} -> list GitHub IDs for organization members who will become team
     *                           maintainers - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "repo_names"} -> the full name (e.g., "organization-name/repository-name") of
     *                           repositories to add the team to - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public Team createTeam(Organization org, String name, Params bodyParams) throws IOException {
        return createTeam(org.getLogin(), name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:        the organization where create the team
     * @param name:       the name of the team
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainers"} -> list GitHub IDs for organization members who will become team
     *                           maintainers - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "repo_names"} -> the full name (e.g., "organization-name/repository-name") of
     *                           repositories to add the team to - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public <T> T createTeam(Organization org, String name, Params bodyParams, ReturnFormat format) throws IOException {
        return createTeam(org.getLogin(), name, bodyParams, format);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       the name of the team
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainers"} -> list GitHub IDs for organization members who will become team
     *                           maintainers - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "repo_names"} -> the full name (e.g., "organization-name/repository-name") of
     *                           repositories to add the team to - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public Team createTeam(String org, String name, Params bodyParams) throws IOException {
        return createTeam(org, name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a team, the authenticated user must be a member or owner of {org}
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       the name of the team
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "maintainers"} -> list GitHub IDs for organization members who will become team
     *                           maintainers - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "repo_names"} -> the full name (e.g., "organization-name/repository-name") of
     *                           repositories to add the team to - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#create-a-team">
     * Create a team</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/teams")
    public <T> T createTeam(String org, String name, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnTeam(sendPostRequest(ORGS_PATH + org + TEAMS_PATH, bodyParams), format);
    }

    /**
     * Method to get a team using the team's slug
     *
     * @param org:      the organization from fetch the team
     * @param teamSlug: the slug of the team name
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#get-a-team-by-name">
     * Get a team by name</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}")
    public Team getTeamByName(Organization org, String teamSlug) throws IOException {
        return getTeamByName(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get a team using the team's slug
     *
     * @param org:      the organization from fetch the team
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#get-a-team-by-name">
     * Get a team by name</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T getTeamByName(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getTeamByName(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get a team using the team's slug
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#get-a-team-by-name">
     * Get a team by name</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}")
    public Team getTeamByName(String org, String teamSlug) throws IOException {
        return getTeamByName(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get a team using the team's slug
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#get-a-team-by-name">
     * Get a team by name</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T getTeamByName(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnTeam(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug), format);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization where update the team
     * @param team:       the team to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public Team updateTeam(Organization org, Team team, Params bodyParams) throws IOException {
        return updateTeam(org.getLogin(), team.getSlug(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization where update the team
     * @param team:       the team to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T updateTeam(Organization org, Team team, Params bodyParams, ReturnFormat format) throws IOException {
        return updateTeam(org.getLogin(), team.getSlug(), bodyParams, format);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization where update the team
     * @param teamSlug:   the slug of the team name
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public Team updateTeam(Organization org, String teamSlug, Params bodyParams) throws IOException {
        return updateTeam(org.getLogin(), teamSlug, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization where update the team
     * @param teamSlug:   the slug of the team name
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T updateTeam(Organization org, String teamSlug, Params bodyParams, ReturnFormat format) throws IOException {
        return updateTeam(org.getLogin(), teamSlug, bodyParams, format);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public Team updateTeam(String org, Team team, Params bodyParams) throws IOException {
        return updateTeam(org, team.getSlug(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T updateTeam(String org, Team team, Params bodyParams, ReturnFormat format) throws IOException {
        return updateTeam(org, team.getSlug(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @return team as {@link Team} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public Team updateTeam(String org, String teamSlug, Params bodyParams) throws IOException {
        return updateTeam(org, teamSlug, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to edit a team, the authenticated user must either be an organization owner or a team maintainer
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the name of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> the description of the team - [string]
     *                       </li>
     *                       <li>
     *                           {@code "privacy"} -> the level of privacy this team should have. The options are:
     *                           <ul>
     *                                 <li>
     *                                        <b>For a non-nested team:</b>
     *                                         <ul>
     *                                             <li>
     *                                                 secret - only visible to organization owners and members of this
     *                                                 team
     *                                             </li>
     *                                             <li>
     *                                                 closed - visible to all members of this organization
     *                                             </li>
     *                                         </ul>
     *                                 </li>
     *                                 <li>
     *                                     <b>For a parent or child team</b> -> closed - visible to all members of this
     *                                     organization
     *                                 </li>
     *                           </ul> - [string]
     *                       </li>
     *                       <li>
     *                           {@code "parent_team_id"} -> the ID of a team to set as the parent team - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#update-a-team">
     * Update a team</a>
     **/
    @RequestPath(method = PATCH, path = "/orgs/{org}/teams/{team_slug}")
    public <T> T updateTeam(String org, String teamSlug, Params bodyParams, ReturnFormat format) throws IOException {
        return returnTeam(sendPatchRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug, bodyParams), format);
    }

    /**
     * Method to create a team
     *
     * @param teamResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return team as {@code "format"} defines
     **/
    @Returner
    private <T> T returnTeam(String teamResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(teamResponse);
            case LIBRARY_OBJECT:
                return (T) new Team(new JSONObject(teamResponse));
            default:
                return (T) teamResponse;
        }
    }

    /**
     * Method to delete a team, the authenticated user must be an organization owner or team maintainer. <br>
     * If you are an organization owner, deleting a parent team will delete all of its child teams as well
     *
     * @param org:  the organization where delete the team
     * @param team: the team to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#delete-a-team">
     * Delete a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}")
    public boolean deleteTeam(Organization org, Team team) {
        return deleteTeam(org.getLogin(), team.getSlug());
    }

    /**
     * Method to delete a team, the authenticated user must be an organization owner or team maintainer. <br>
     * If you are an organization owner, deleting a parent team will delete all of its child teams as well
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#delete-a-team">
     * Delete a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}")
    public boolean deleteTeam(String org, Team team) {
        return deleteTeam(org, team.getSlug());
    }

    /**
     * Method to delete a team, the authenticated user must be an organization owner or team maintainer. <br>
     * If you are an organization owner, deleting a parent team will delete all of its child teams as well
     *
     * @param org:      the organization where delete the team
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#delete-a-team">
     * Delete a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}")
    public boolean deleteTeam(Organization org, String teamSlug) {
        return deleteTeam(org.getLogin(), teamSlug);
    }

    /**
     * Method to delete a team, the authenticated user must be an organization owner or team maintainer. <br>
     * If you are an organization owner, deleting a parent team will delete all of its child teams as well
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#delete-a-team">
     * Delete a team</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}")
    public boolean deleteTeam(String org, String teamSlug) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug);
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
     * Method to get the list of the organization projects for a team
     *
     * @param org:  the organization from fetch the list
     * @param team: the team from fetch the list
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(Organization org, Team team) throws IOException {
        return getTeamProjects(org.getLogin(), team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:    the organization from fetch the list
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(Organization org, Team team, ReturnFormat format) throws IOException {
        return getTeamProjects(org.getLogin(), team.getSlug(), format);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team from fetch the list
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(String org, Team team) throws IOException {
        return getTeamProjects(org, team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(String org, Team team, ReturnFormat format) throws IOException {
        return getTeamProjects(org, team.getSlug(), format);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(Organization org, String teamSlug) throws IOException {
        return getTeamProjects(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getTeamProjects(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(String org, String teamSlug) throws IOException {
        return getTeamProjects(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + PROJECTS_PATH),
                format);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(Organization org, Team team, Params queryParams) throws IOException {
        return getTeamProjects(org.getLogin(), team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
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
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(Organization org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamProjects(org.getLogin(), team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(String org, Team team, Params queryParams) throws IOException {
        return getTeamProjects(org, team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
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
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(String org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamProjects(org, team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the organization projects for a team
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
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(Organization org, String teamSlug, Params queryParams) throws IOException {
        return getTeamProjects(org.getLogin(), teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
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
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(Organization org, String teamSlug, Params queryParams,
                                 ReturnFormat format) throws IOException {
        return getTeamProjects(org.getLogin(), teamSlug, queryParams, format);
    }

    /**
     * Method to get the list of the organization projects for a team
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
     * @return projects list as {@link ArrayList} of {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public ArrayList<Project> getTeamProjects(String org, String teamSlug, Params queryParams) throws IOException {
        return getTeamProjects(org, teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization projects for a team
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
     * @return projects list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-projects">
     * List team projects</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects")
    public <T> T getTeamProjects(String org, String teamSlug, Params queryParams,
                                 ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + PROJECTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:     the organization where check project permissions
     * @param team:    the team where check project permissions
     * @param project: the project where check project permissions
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(Organization org, Team team, Project project) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), team.getSlug(), project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:     the organization where check project permissions
     * @param team:    the team where check project permissions
     * @param project: the project where check project permissions
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(Organization org, Team team, Project project,
                                             ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), team.getSlug(), project.getId(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization where check project permissions
     * @param team:      the team where check project permissions
     * @param projectId: the unique identifier of the project
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(Organization org, Team team, long projectId) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), team.getSlug(), projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization where check project permissions
     * @param team:      the team where check project permissions
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(Organization org, Team team, long projectId,
                                             ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), team.getSlug(), projectId, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:     the organization name. The name is not case-sensitive
     * @param team:    the team where check project permissions
     * @param project: the project where check project permissions
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(String org, Team team, Project project) throws IOException {
        return checkProjectTeamPermissions(org, team.getSlug(), project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:     the organization name. The name is not case-sensitive
     * @param team:    the team where check project permissions
     * @param project: the project where check project permissions
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(String org, Team team, Project project, ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org, team.getSlug(), project.getId(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param team:      the team where check project permissions
     * @param projectId: the unique identifier of the project
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(String org, Team team, long projectId) throws IOException {
        return checkProjectTeamPermissions(org, team.getSlug(), projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param team:      the team where check project permissions
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(String org, Team team, long projectId, ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org, team.getSlug(), projectId, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization where check project permissions
     * @param teamSlug: the slug of the team name
     * @param project:  the project where check project permissions
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(Organization org, String teamSlug, Project project) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), teamSlug, project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization where check project permissions
     * @param teamSlug: the slug of the team name
     * @param project:  the project where check project permissions
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(Organization org, String teamSlug, Project project,
                                             ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), teamSlug, project.getId(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization where check project permissions
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(Organization org, String teamSlug, long projectId) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), teamSlug, projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization where check project permissions
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(Organization org, String teamSlug, long projectId,
                                             ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org.getLogin(), teamSlug, projectId, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param project:  the project where check project permissions
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(String org, String teamSlug, Project project) throws IOException {
        return checkProjectTeamPermissions(org, teamSlug, project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param project:  the project where check project permissions
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(String org, String teamSlug, Project project,
                                             ReturnFormat format) throws IOException {
        return checkProjectTeamPermissions(org, teamSlug, project.getId(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @return project as {@link Project} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public Project checkProjectTeamPermissions(String org, String teamSlug, long projectId) throws IOException {
        return checkProjectTeamPermissions(org, teamSlug, projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-project">
     * Check team permissions for a project</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public <T> T checkProjectTeamPermissions(String org, String teamSlug, long projectId,
                                             ReturnFormat format) throws IOException {
        return returnProject(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + PROJECTS_PATH
                + "/" + projectId), format);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization where work with a project
     * @param team:       the team where work with a project
     * @param project:    the project to work on
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(Organization org, Team team, Project project,
                                                  APermissionType permission) {
        return workWithTeamProjectPermissions(org.getLogin(), team.getSlug(), project.getId(), permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization where work with a project
     * @param teamSlug:   the slug of the team name
     * @param project:    the project to work on
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(Organization org, String teamSlug, Project project,
                                                  APermissionType permission) {
        return workWithTeamProjectPermissions(org.getLogin(), teamSlug, project.getId(), permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param project:    the project to work on
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(String org, String teamSlug, Project project, APermissionType permission) {
        return workWithTeamProjectPermissions(org, teamSlug, project.getId(), permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where work with a project
     * @param project:    the project to work on
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(String org, Team team, Project project, APermissionType permission) {
        return workWithTeamProjectPermissions(org, team.getSlug(), project.getId(), permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where work with a project
     * @param projectId:  the unique identifier of the project
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(String org, Team team, long projectId, APermissionType permission) {
        return workWithTeamProjectPermissions(org, team.getSlug(), projectId, permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization where work with a project
     * @param team:       the team where work with a project
     * @param projectId:  the unique identifier of the project
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(Organization org, Team team, long projectId, APermissionType permission) {
        return workWithTeamProjectPermissions(org.getLogin(), team.getSlug(), projectId, permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization where work with a project
     * @param teamSlug:   the slug of the team name
     * @param projectId:  the unique identifier of the project
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(Organization org, String teamSlug, long projectId,
                                                  APermissionType permission) {
        return workWithTeamProjectPermissions(org.getLogin(), teamSlug, projectId, permission);
    }

    /**
     * Method to add an organization project to a team. To add a project to a team or update the team's permission on a
     * project, the authenticated user must have admin permissions for the project. The project and team must be part of
     * the same organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param projectId:  the unique identifier of the project
     * @param permission: the permission to grant to the team for this project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-project-permissions">
     * Add or update team project permissions</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean workWithTeamProjectPermissions(String org, String teamSlug, long projectId, APermissionType permission) {
        try {
            Params payload = new Params();
            payload.addParam("permission", permission);
            sendPutRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + PROJECTS_PATH + "/" + projectId,
                    payload);
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
     * Method to remove an organization project from a team
     *
     * @param org:     the organization where remove a project
     * @param team:    the team where remove a project
     * @param project: the project to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(Organization org, Team team, Project project) {
        return removeTeamProject(org.getLogin(), team.getSlug(), project.getId());
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:      the organization where remove a project
     * @param teamSlug: the slug of the team name
     * @param project:  the project to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(Organization org, String teamSlug, Project project) {
        return removeTeamProject(org.getLogin(), teamSlug, project.getId());
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param project:  the project to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(String org, String teamSlug, Project project) {
        return removeTeamProject(org, teamSlug, project.getId());
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:     the organization name. The name is not case-sensitive
     * @param team:    the team where remove a project
     * @param project: the project to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(String org, Team team, Project project) {
        return removeTeamProject(org, team.getSlug(), project.getId());
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param team:      the team where remove a project
     * @param projectId: the unique identifier of the project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(String org, Team team, long projectId) {
        return removeTeamProject(org, team.getSlug(), projectId);
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:       the organization where remove a project
     * @param team:      the team where remove a project
     * @param projectId: the unique identifier of the project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(Organization org, Team team, long projectId) {
        return removeTeamProject(org.getLogin(), team.getSlug(), projectId);
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:       the organization where remove a project
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(Organization org, String teamSlug, long projectId) {
        return removeTeamProject(org.getLogin(), teamSlug, projectId);
    }

    /**
     * Method to remove an organization project from a team
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param teamSlug:  the slug of the team name
     * @param projectId: the unique identifier of the project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-project-from-a-team">
     * Remove a project from a team</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/projects/{project_id}")
    public boolean removeTeamProject(String org, String teamSlug, long projectId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + PROJECTS_PATH + "/" + projectId);
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
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:  the organization from fetch the list
     * @param team: the team from fetch the list
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(Organization org, Team team) throws IOException {
        return getTeamRepositories(org.getLogin(), team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:    the organization from fetch the list
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(Organization org, Team team, ReturnFormat format) throws IOException {
        return getTeamRepositories(org.getLogin(), team.getSlug(), format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team from fetch the list
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(String org, Team team) throws IOException {
        return getTeamRepositories(org, team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(String org, Team team, ReturnFormat format) throws IOException {
        return getTeamRepositories(org, team.getSlug(), format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(Organization org, String teamSlug) throws IOException {
        return getTeamRepositories(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getTeamRepositories(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(String org, String teamSlug) throws IOException {
        return getTeamRepositories(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + REPOS_QUERY_PATH),
                format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(Organization org, Team team, Params queryParams) throws IOException {
        return getTeamRepositories(org.getLogin(), team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(Organization org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamRepositories(org.getLogin(), team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(String org, Team team, Params queryParams) throws IOException {
        return getTeamRepositories(org, team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(String org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getTeamRepositories(org, team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
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
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(Organization org, String teamSlug, Params queryParams) throws IOException {
        return getTeamRepositories(org.getLogin(), teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(Organization org, String teamSlug, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return getTeamRepositories(org.getLogin(), teamSlug, queryParams, format);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
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
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public ArrayList<Repository> getTeamRepositories(String org, String teamSlug, Params queryParams) throws IOException {
        return getTeamRepositories(org, teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of a team's repositories visible to the authenticated user
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
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-team-repositories">
     * List team repositories</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos")
    public <T> T getTeamRepositories(String org, String teamSlug, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + REPOS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization where check repository permissions
     * @param team:       the team where check repository permissions
     * @param repository: the repository where check the permissions
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(Organization org, Team team, Repository repository) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization where check repository permissions
     * @param team:       the team where check repository permissions
     * @param repository: the repository where check the permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(Organization org, Team team, Repository repository,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:   the organization where check repository permissions
     * @param team:  the team where check repository permissions
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(Organization org, Team team, String owner, String repo) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), team.getSlug(), owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:    the organization where check repository permissions
     * @param team:   the team where check repository permissions
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(Organization org, Team team, String owner, String repo,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), team.getSlug(), owner, repo, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where check repository permissions
     * @param repository: the repository where check the permissions
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(String org, Team team, Repository repository) throws IOException {
        return checkRepositoryTeamPermissions(org, team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where check repository permissions
     * @param repository: the repository where check the permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(String org, Team team, Repository repository,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org, team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:   the organization name. The name is not case-sensitive
     * @param team:  the team where check repository permissions
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(String org, Team team, String owner, String repo) throws IOException {
        return checkRepositoryTeamPermissions(org, team.getSlug(), owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the team where check repository permissions
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(String org, Team team, String owner, String repo,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org, team.getSlug(), owner, repo, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization where check repository permissions
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository where check the permissions
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(Organization org, String teamSlug,
                                                     Repository repository) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), teamSlug, repository.getOwner().getLogin(),
                repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization where check repository permissions
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository where check the permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(Organization org, String teamSlug, Repository repository,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), teamSlug, repository.getOwner().getLogin(),
                repository.getName(), format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization where check repository permissions
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(Organization org, String teamSlug, String owner,
                                                     String repo) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), teamSlug, owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization where check repository permissions
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(Organization org, String teamSlug, String owner, String repo,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org.getLogin(), teamSlug, owner, repo, format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository where check the permissions
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(String org, String teamSlug, Repository repository) throws IOException {
        return checkRepositoryTeamPermissions(org, teamSlug, repository.getOwner().getLogin(), repository.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository where check the permissions
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(String org, String teamSlug, Repository repository,
                                                ReturnFormat format) throws IOException {
        return checkRepositoryTeamPermissions(org, teamSlug, repository.getOwner().getLogin(), repository.getName(),
                format);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public Repository checkRepositoryTeamPermissions(String org, String teamSlug, String owner,
                                                     String repo) throws IOException {
        return checkRepositoryTeamPermissions(org, teamSlug, owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to check whether a team has read, write, or admin permissions for an organization project. The response
     * includes projects inherited from a parent team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#check-team-permissions-for-a-repository">
     * Check team permissions for a repository</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public <T> T checkRepositoryTeamPermissions(String org, String teamSlug, String owner, String repo,
                                                ReturnFormat format) throws IOException {
        return returnRepository(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + REPOS_QUERY_PATH
                + "/" + owner + "/" + repo), format);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization where work with a repository
     * @param team:       the team where work with a repository
     * @param repository: the repository to work on
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(Organization org, Team team, Repository repository,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org.getLogin(), team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization where work with a repository
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository to work on
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(Organization org, String teamSlug, Repository repository,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org.getLogin(), teamSlug, repository.getOwner().getLogin(),
                repository.getName(), permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository to work on
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(String org, String teamSlug, Repository repository,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org, teamSlug, repository.getOwner().getLogin(),
                repository.getName(), permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where work with a repository
     * @param repository: the repository to work on
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(String org, Team team, Repository repository, Permission permission) {
        return workWithRepositoryPermissions(org, team.getSlug(), repository.getOwner().getLogin(),
                repository.getName(), permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where work with a repository
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(String org, Team team, String owner, String repo,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org, team.getSlug(), owner, repo, permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization where work with a repository
     * @param team:       the team where work with a repository
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(Organization org, Team team, String owner, String repo,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org.getLogin(), team.getSlug(), owner, repo, permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization where work with a repository
     * @param teamSlug:   the slug of the team name
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(Organization org, String teamSlug, String owner, String repo,
                                                 Permission permission) {
        return workWithRepositoryPermissions(org.getLogin(), teamSlug, owner, repo, permission);
    }

    /**
     * Method to add a repository to a team or update the team's permission on a repository, the authenticated user must
     * have admin access to the repository, and must be able to see the team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param permission: the permission to grant to the team for this repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#add-or-update-team-repository-permissions">
     * Add or update team repository permissions</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean workWithRepositoryPermissions(String org, String teamSlug, String owner, String repo,
                                                 Permission permission) {
        try {
            Params payload = new Params();
            payload.addParam("permission", permission);
            sendPutRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + REPOS_QUERY_PATH + "/" + owner
                    + "/" + repo, payload);
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
     * Method to remove a repository from a team
     *
     * @param org:        the organization where remove a repository
     * @param team:       the team where remove a repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(Organization org, Team team, Repository repository) {
        return removeTeamRepository(org.getLogin(), team.getSlug(), repository.getOwner().getLogin(),
                repository.getName());
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:        the organization where remove a repository
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(Organization org, String teamSlug, Repository repository) {
        return removeTeamRepository(org.getLogin(), teamSlug, repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(String org, String teamSlug, Repository repository) {
        return removeTeamRepository(org, teamSlug, repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where remove a repository
     * @param repository: the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(String org, Team team, Repository repository) {
        return removeTeamRepository(org, team.getSlug(), repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:   the organization name. The name is not case-sensitive
     * @param team:  the team where remove a repository
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(String org, Team team, String owner, String repo) {
        return removeTeamRepository(org, team.getSlug(), owner, repo);
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:   the organization where remove a repository
     * @param team:  the team where remove a repository
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(Organization org, Team team, String owner, String repo) {
        return removeTeamRepository(org.getLogin(), team.getSlug(), owner, repo);
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:      the organization where remove a repository
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(Organization org, String teamSlug, String owner, String repo) {
        return removeTeamRepository(org.getLogin(), teamSlug, owner, repo);
    }

    /**
     * Method to remove a repository from a team
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#remove-a-repository-from-a-team">
     * Remove a repository from a team</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/repos/{owner}/{repo}")
    public boolean removeTeamRepository(String org, String teamSlug, String owner, String repo) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + REPOS_QUERY_PATH + "/" + owner
                    + "/" + repo);
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
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:  the organization from fetch the list
     * @param team: the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(Organization org, Team team) throws IOException {
        return getChildTeams(org.getLogin(), team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:    the organization from fetch the list
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(Organization org, Team team, ReturnFormat format) throws IOException {
        return getChildTeams(org.getLogin(), team.getSlug(), format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(Organization org, String teamSlug) throws IOException {
        return getChildTeams(org.getLogin(), teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:      the organization from fetch the list
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(Organization org, String teamSlug, ReturnFormat format) throws IOException {
        return getChildTeams(org.getLogin(), teamSlug, format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(String org, Team team) throws IOException {
        return getChildTeams(org, team.getSlug(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param team:   the team from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(String org, Team team, ReturnFormat format) throws IOException {
        return getChildTeams(org, team.getSlug(), format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(String org, String teamSlug) throws IOException {
        return getChildTeams(org, teamSlug, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @param format:   return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(String org, String teamSlug, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + TEAMS_PATH), format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(Organization org, Team team, Params queryParams) throws IOException {
        return getChildTeams(org.getLogin(), team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(Organization org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getChildTeams(org.getLogin(), team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(Organization org, String teamSlug, Params queryParams) throws IOException {
        return getChildTeams(org.getLogin(), teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(Organization org, String teamSlug, Params queryParams,
                               ReturnFormat format) throws IOException {
        return getChildTeams(org.getLogin(), teamSlug, queryParams, format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(String org, Team team, Params queryParams) throws IOException {
        return getChildTeams(org, team.getSlug(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(String org, Team team, Params queryParams, ReturnFormat format) throws IOException {
        return getChildTeams(org, team.getSlug(), queryParams, format);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public ArrayList<Team> getChildTeams(String org, String teamSlug, Params queryParams) throws IOException {
        return getChildTeams(org, teamSlug, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the child teams of the team specified by {team_slug}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-child-teams">
     * List child teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/teams")
    public <T> T getChildTeams(String org, String teamSlug, Params queryParams, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + TEAMS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the all teams across all the organizations to which the authenticated user belongs <br>
     * No-any params required
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams-for-the-authenticated-user">
     * List teams for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/teams")
    public ArrayList<Team> getAuthenticatedUserTeams() throws IOException {
        return getAuthenticatedUserTeams(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams across all the organizations to which the authenticated user belongs
     *
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams-for-the-authenticated-user">
     * List teams for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/teams")
    public <T> T getAuthenticatedUserTeams(ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(USER_TEAMS_PATH), format);
    }

    /**
     * Method to get the list of the all teams across all the organizations to which the authenticated user belongs
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams-for-the-authenticated-user">
     * List teams for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/teams")
    public ArrayList<Team> getAuthenticatedUserTeams(Params queryParams) throws IOException {
        return getAuthenticatedUserTeams(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all teams across all the organizations to which the authenticated user belongs
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/teams/teams#list-teams-for-the-authenticated-user">
     * List teams for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/teams")
    public <T> T getAuthenticatedUserTeams(Params queryParams, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(USER_TEAMS_PATH + queryParams.createQueryString()), format);
    }

}
