package com.tecknobit.githubmanager.projects.collaborators;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.collaborators.records.Collaborator.Affiliation;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization.RepositoryPermission;
import com.tecknobit.githubmanager.projects.boards.records.Project;
import com.tecknobit.githubmanager.projects.collaborators.records.ProjectPermission;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.collaborators.collaborators.GitHubCollaboratorsManager.COLLABORATORS_PATH;
import static com.tecknobit.githubmanager.collaborators.collaborators.GitHubCollaboratorsManager.PERMISSION_PATH;
import static com.tecknobit.githubmanager.projects.boards.GitHubBoardsManager.ROOT_PROJECTS_PATH;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

/**
 * The {@code GitHubProjectsCollaboratorsManager} class is useful to manage all GitHub's pages managers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators">
 * Project (classic) collaborators</a>
 * @see GitHubManager
 **/
public class GitHubProjectsCollaboratorsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubProjectsCollaboratorsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubProjectsCollaboratorsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubProjectsCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubProjectsCollaboratorsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubProjectsCollaboratorsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubProjectsCollaboratorsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubProjectsCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubProjectsCollaboratorsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubProjectsCollaboratorsManager} <br>
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
    public GitHubProjectsCollaboratorsManager() {
        super();
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param project: the project from fetch the list
     * @return project collaborators as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public ArrayList<User> getProjectCollaborators(Project project) throws IOException {
        return getProjectCollaborators(project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param project: the project from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public <T> T getProjectCollaborators(Project project, ReturnFormat format) throws IOException {
        return getProjectCollaborators(project.getId(), format);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param projectId: the unique identifier of the project
     * @return project collaborators as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public ArrayList<User> getProjectCollaborators(long projectId) throws IOException {
        return getProjectCollaborators(projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public <T> T getProjectCollaborators(long projectId, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLLABORATORS_PATH), format);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param project:     the project from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filters the collaborators by their affiliation. outside means
     *                            outside collaborators of a project that are not a member of the project's organization.
     *                            direct means collaborators with permissions to a project, regardless of organization
     *                            membership status. all means all collaborators the authenticated user can see,
     *                            constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return project collaborators as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public ArrayList<User> getProjectCollaborators(Project project, Params queryParams) throws IOException {
        return getProjectCollaborators(project.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param project:     the project from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filters the collaborators by their affiliation. outside means
     *                            outside collaborators of a project that are not a member of the project's organization.
     *                            direct means collaborators with permissions to a project, regardless of organization
     *                            membership status. all means all collaborators the authenticated user can see,
     *                            constants available {@link Affiliation} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public <T> T getProjectCollaborators(Project project, Params queryParams, ReturnFormat format) throws IOException {
        return getProjectCollaborators(project.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param projectId:   the unique identifier of the project
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filters the collaborators by their affiliation. outside means
     *                            outside collaborators of a project that are not a member of the project's organization.
     *                            direct means collaborators with permissions to a project, regardless of organization
     *                            membership status. all means all collaborators the authenticated user can see,
     *                            constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return project collaborators as {@link ArrayList} of {@link User} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public ArrayList<User> getProjectCollaborators(long projectId, Params queryParams) throws IOException {
        return getProjectCollaborators(projectId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the collaborators for an organization project. For a project, the list of
     * collaborators includes outside collaborators, organization members that are direct collaborators,
     * organization members with access through team memberships, organization members with access through default
     * organization permissions, and organization owners. You must be an organization owner or a project admin to list
     * collaborators
     *
     * @param projectId:   the unique identifier of the project
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filters the collaborators by their affiliation. outside means
     *                            outside collaborators of a project that are not a member of the project's organization.
     *                            direct means collaborators with permissions to a project, regardless of organization
     *                            membership status. all means all collaborators the authenticated user can see,
     *                            constants available {@link Affiliation} - [string, default all]
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#list-project-collaborators">
     * List project collaborators</a>
     **/
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators")
    public <T> T getProjectCollaborators(long projectId, Params queryParams, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLLABORATORS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to add a collaborator to an organization project and sets their permission level.
     * You must be an organization owner or a project admin to add a collaborator
     *
     * @param project:  the project where add the user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#add-project-collaborator">
     * Add project collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/projects/{project_id}/collaborators/{username}")
    public boolean addProjectCollaborator(Project project, String username) {
        return addProjectCollaborator(project.getId(), username);
    }

    /**
     * Method to add a collaborator to an organization project and sets their permission level.
     * You must be an organization owner or a project admin to add a collaborator
     *
     * @param projectId: the unique identifier of the project
     * @param username:  the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#add-project-collaborator">
     * Add project collaborator</a>
     **/
    @RequestPath(method = PUT, path = "/projects/{project_id}/collaborators/{username}")
    public boolean addProjectCollaborator(long projectId, String username) {
        return addProjectCollaborator(projectId, username, null);
    }

    /**
     * Method to add a collaborator to an organization project and sets their permission level.
     * You must be an organization owner or a project admin to add a collaborator
     *
     * @param project:    the project where add the user
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#add-project-collaborator">
     * Add project collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/projects/{project_id}/collaborators/{username}")
    public boolean addProjectCollaborator(Project project, String username, RepositoryPermission permission) {
        return addProjectCollaborator(project.getId(), username, permission);
    }

    /**
     * Method to add a collaborator to an organization project and sets their permission level.
     * You must be an organization owner or a project admin to add a collaborator
     *
     * @param projectId:  the unique identifier of the project
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#add-project-collaborator">
     * Add project collaborator</a>
     **/
    @RequestPath(method = PUT, path = "/projects/{project_id}/collaborators/{username}")
    public boolean addProjectCollaborator(long projectId, String username, RepositoryPermission permission) {
        try {
            Params payload = null;
            if (permission != null) {
                payload = new Params();
                payload.addParam("permission", permission);
            }
            sendPutRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLLABORATORS_PATH + "/" + username, payload);
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
     * Method to removes a collaborator from an organization project.
     * You must be an organization owner or a project admin to remove a collaborator
     *
     * @param project:  the project where remove the user
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#remove-user-as-a-collaborator">
     * Remove user as a collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/projects/{project_id}/collaborators/{username}")
    public boolean removeProjectCollaborator(Project project, String username) {
        return removeProjectCollaborator(project.getId(), username);
    }

    /**
     * Method to removes a collaborator from an organization project.
     * You must be an organization owner or a project admin to remove a collaborator
     *
     * @param projectId: the unique identifier of the project
     * @param username:  the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#remove-user-as-a-collaborator">
     * Remove user as a collaborator</a>
     **/
    @RequestPath(method = DELETE, path = "/projects/{project_id}/collaborators/{username}")
    public boolean removeProjectCollaborator(long projectId, String username) {
        try {
            sendDeleteRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLLABORATORS_PATH + "/" + username);
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
     * Method to returns the collaborator's permission level for an organization project. Possible values for the
     * permission key: admin, write, read, none. You must be an organization owner or a project admin to review a user's
     * permission level
     *
     * @param project:  the project from fetch the permission
     * @param username: the handle for the GitHub user account
     * @return project permission as {@link ProjectPermission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#get-project-permission-for-a-user">
     * Get project permission for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators/{username}/permission")
    public ProjectPermission getUserProjectPermission(Project project, String username) throws IOException {
        return getUserProjectPermission(project.getId(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to returns the collaborator's permission level for an organization project. Possible values for the
     * permission key: admin, write, read, none. You must be an organization owner or a project admin to review a user's
     * permission level
     *
     * @param project:  the project from fetch the permission
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return project permission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#get-project-permission-for-a-user">
     * Get project permission for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators/{username}/permission")
    public <T> T getUserProjectPermission(Project project, String username, ReturnFormat format) throws IOException {
        return getUserProjectPermission(project.getId(), username, format);
    }

    /**
     * Method to returns the collaborator's permission level for an organization project. Possible values for the
     * permission key: admin, write, read, none. You must be an organization owner or a project admin to review a user's
     * permission level
     *
     * @param projectId: the unique identifier of the project
     * @param username:  the handle for the GitHub user account
     * @return project permission as {@link ProjectPermission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#get-project-permission-for-a-user">
     * Get project permission for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators/{username}/permission")
    public ProjectPermission getUserProjectPermission(long projectId, String username) throws IOException {
        return getUserProjectPermission(projectId, username, LIBRARY_OBJECT);
    }

    /**
     * Method to returns the collaborator's permission level for an organization project. Possible values for the
     * permission key: admin, write, read, none. You must be an organization owner or a project admin to review a user's
     * permission level
     *
     * @param projectId: the unique identifier of the project
     * @param username:  the handle for the GitHub user account
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project permission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#get-project-permission-for-a-user">
     * Get project permission for a user</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/projects/{project_id}/collaborators/{username}/permission")
    public <T> T getUserProjectPermission(long projectId, String username, ReturnFormat format) throws IOException {
        String permissionResponse = sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLLABORATORS_PATH
                + "/" + username + PERMISSION_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(permissionResponse);
            case LIBRARY_OBJECT:
                return (T) new ProjectPermission(new JSONObject(permissionResponse));
            default:
                return (T) permissionResponse;
        }
    }

}
