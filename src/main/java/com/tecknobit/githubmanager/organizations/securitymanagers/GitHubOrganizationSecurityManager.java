package com.tecknobit.githubmanager.organizations.securitymanagers;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.organization.Team;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;

/**
 * The {@code GitHubOrganizationSecurityManager} class is useful to manage all GitHub's security managers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers">
 * Security Managers</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationSecurityManager extends GitHubManager {

    /**
     * {@code SECURITY_MANAGERS_PATH} constant for {@code "/security-managers"} path
     **/
    public static final String SECURITY_MANAGERS_PATH = "/security-managers";

    /**
     * Constructor to init a {@link GitHubOrganizationSecurityManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationSecurityManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecurityManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationSecurityManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecurityManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationSecurityManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecurityManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationSecurityManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationSecurityManager} <br>
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
    public GitHubOrganizationSecurityManager() {
        super();
    }

    /**
     * Method to get the list of the teams that are security managers for an organization. For more information, see
     * "Managing security managers in your organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#list-security-manager-teams">
     * List security manager teams</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/security-managers")
    public ArrayList<Team> getSecurityManagerTeams(Organization org) throws IOException {
        return getSecurityManagerTeams(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the teams that are security managers for an organization. For more information, see
     * "Managing security managers in your organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#list-security-manager-teams">
     * List security manager teams</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/security-managers")
    public <T> T getSecurityManagerTeams(Organization org, ReturnFormat format) throws IOException {
        return getSecurityManagerTeams(org.getLogin(), format);
    }

    /**
     * Method to get the list of the teams that are security managers for an organization. For more information, see
     * "Managing security managers in your organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#list-security-manager-teams">
     * List security manager teams</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/security-managers")
    public ArrayList<Team> getSecurityManagerTeams(String org) throws IOException {
        return getSecurityManagerTeams(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the teams that are security managers for an organization. For more information, see
     * "Managing security managers in your organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#list-security-manager-teams">
     * List security manager teams</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/security-managers")
    public <T> T getSecurityManagerTeams(String org, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(ORGS_PATH + org + SECURITY_MANAGERS_PATH), format);
    }

    /**
     * Method to add a team as a security manager for an organization. For more information, see "Managing security for
     * an organization for an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:  the organization where add the security manager team
     * @param team: the team where add the security manager team
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#add-a-security-manager-team">
     * Add a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean addSecurityManagerTeam(Organization org, Team team) {
        return addSecurityManagerTeam(org.getLogin(), team.getSlug());
    }

    /**
     * Method to add a team as a security manager for an organization. For more information, see "Managing security for
     * an organization for an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team where add the security manager team
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#add-a-security-manager-team">
     * Add a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean addSecurityManagerTeam(String org, Team team) {
        return addSecurityManagerTeam(org, team.getSlug());
    }

    /**
     * Method to add a team as a security manager for an organization. For more information, see "Managing security for
     * an organization for an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:      the organization where add the security manager team
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#add-a-security-manager-team">
     * Add a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean addSecurityManagerTeam(Organization org, String teamSlug) {
        return addSecurityManagerTeam(org.getLogin(), teamSlug);
    }

    /**
     * Method to add a team as a security manager for an organization. For more information, see "Managing security for
     * an organization for an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#add-a-security-manager-team">
     * Add a security manager team</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean addSecurityManagerTeam(String org, String teamSlug) {
        try {
            sendPutRequest(ORGS_PATH + org + SECURITY_MANAGERS_PATH + TEAMS_PATH + "/" + teamSlug, null);
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
     * Method to remove the security manager role from a team for an organization. For more information, see "Managing
     * security managers in your organization team from an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:  the organization from remove the security manager team
     * @param team: the team from remove the security manager team
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#remove-a-security-manager-team">
     * Remove a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean removeSecurityManagerTeam(Organization org, Team team) {
        return removeSecurityManagerTeam(org.getLogin(), team.getSlug());
    }

    /**
     * Method to remove the security manager role from a team for an organization. For more information, see "Managing
     * security managers in your organization team from an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param team: the team from remove the security manager team
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#remove-a-security-manager-team">
     * Remove a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean removeSecurityManagerTeam(String org, Team team) {
        return removeSecurityManagerTeam(org, team.getSlug());
    }

    /**
     * Method to remove the security manager role from a team for an organization. For more information, see "Managing
     * security managers in your organization team from an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:      the organization from remove the security manager team
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#remove-a-security-manager-team">
     * Remove a security manager team</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean removeSecurityManagerTeam(Organization org, String teamSlug) {
        return removeSecurityManagerTeam(org.getLogin(), teamSlug);
    }

    /**
     * Method to remove the security manager role from a team for an organization. For more information, see "Managing
     * security managers in your organization team from an organization." <br>
     * To use this endpoint, you must be an administrator or security manager for the organization, and you must use an
     * access token with the {@code "read:org"} scope. <br>
     * GitHub Apps must have the {@code "administration"} organization read permission to use this endpoint
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param teamSlug: the slug of the team name
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/security-managers#remove-a-security-manager-team">
     * Remove a security manager team</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/security-managers/teams/{team_slug}")
    public boolean removeSecurityManagerTeam(String org, String teamSlug) {
        try {
            sendDeleteRequest(ORGS_PATH + org + SECURITY_MANAGERS_PATH + TEAMS_PATH + "/" + teamSlug);
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
