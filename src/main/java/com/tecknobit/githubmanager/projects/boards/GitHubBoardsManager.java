package com.tecknobit.githubmanager.projects.boards;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.projects.boards.records.Project;
import com.tecknobit.githubmanager.projects.boards.records.Project.OrganizationPermission;
import com.tecknobit.githubmanager.records.parents.GitHubOperationBaseStructure.OperationState;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubBoardsManager} class is useful to manage all GitHub's boards endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects">
 * Projects (classic)</a>
 * @see GitHubManager
 **/
public class GitHubBoardsManager extends GitHubManager {

    /**
     * {@code PROJECTS_PATH} constant for {@code "/projects"} path
     **/
    public static final String PROJECTS_PATH = "/projects";

    /**
     * {@code ROOT_PROJECTS_PATH} constant for {@code "projects"} path
     **/
    public static final String ROOT_PROJECTS_PATH = "projects";

    /**
     * Constructor to init a {@link GitHubBoardsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubBoardsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubBoardsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubBoardsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubBoardsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubBoardsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBoardsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubBoardsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBoardsManager} <br>
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
    public GitHubBoardsManager() {
        super();
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org: the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public ArrayList<Project> getOrganizationProjects(Organization org) throws IOException {
        return getOrganizationProjects(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public <T> T getOrganizationProjects(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationProjects(org.getLogin(), format);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org: the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public ArrayList<Project> getOrganizationProjects(String org) throws IOException {
        return getOrganizationProjects(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public <T> T getOrganizationProjects(String org, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(ORGS_PATH + org + PROJECTS_PATH), format);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public ArrayList<Project> getOrganizationProjects(Organization org, Params queryParams) throws IOException {
        return getOrganizationProjects(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public <T> T getOrganizationProjects(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationProjects(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public ArrayList<Project> getOrganizationProjects(String org, Params queryParams) throws IOException {
        return getOrganizationProjects(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in an organization. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-organization-projects">
     * List organization projects</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/projects")
    public <T> T getOrganizationProjects(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(ORGS_PATH + org + PROJECTS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:  the organization where create the project
     * @param name: the name of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public Project createOrganizationProject(Organization org, String name) throws IOException {
        return createOrganizationProject(org.getLogin(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization where create the project
     * @param name:   the name of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public <T> T createOrganizationProject(Organization org, String name, ReturnFormat format) throws IOException {
        return createOrganizationProject(org.getLogin(), name, format);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param name: the name of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public Project createOrganizationProject(String org, String name) throws IOException {
        return createOrganizationProject(org, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   the name of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public <T> T createOrganizationProject(String org, String name, ReturnFormat format) throws IOException {
        return createOrganizationProject(org, name, null, format);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:  the organization where create the project
     * @param name: the name of the project
     * @param body: the description of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public Project createOrganizationProject(Organization org, String name, String body) throws IOException {
        return createOrganizationProject(org.getLogin(), name, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization where create the project
     * @param name:   the name of the project
     * @param body:   the description of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public <T> T createOrganizationProject(Organization org, String name, String body, ReturnFormat format) throws IOException {
        return createOrganizationProject(org.getLogin(), name, body, format);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param name: the name of the project
     * @param body: the description of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public Project createOrganizationProject(String org, String name, String body) throws IOException {
        return createOrganizationProject(org, name, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   the name of the project
     * @param body:   the description of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-an-organization-project">
     * Create an organization project</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/projects")
    public <T> T createOrganizationProject(String org, String name, String body, ReturnFormat format) throws IOException {
        return returnProject(sendPostRequest(ORGS_PATH + org + PROJECTS_PATH, createProjectPayload(name, body)),
                format);
    }

    /**
     * Method to get a project by its id. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#get-a-project">
     * Get a project</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}")
    public Project getProject(long projectId) throws IOException {
        return getProject(projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a project by its id. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#get-a-project">
     * Get a project</a>
     **/
    @RequestPath(method = GET, path = "/projects/{project_id}")
    public <T> T getProject(long projectId, ReturnFormat format) throws IOException {
        return returnProject(sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId), format);
    }

    /**
     * Method to update a project board's information. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param project:    the project to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> name of the project - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> body of the project - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the project, constants available {@link OperationState}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "organization_permission"} -> the baseline permission that all organization
     *                           members have on this project, constants available {@link OrganizationPermission}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether or not this project can be seen by everyone - [boolean]
     *                       </li>
     *                    </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#update-a-project">
     * Update a project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/{project_id}")
    public Project updateProject(Project project, Params bodyParams) throws IOException {
        return updateProject(project.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a project board's information. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param project:    the project to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> name of the project - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> body of the project - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the project, constants available {@link OperationState}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "organization_permission"} -> the baseline permission that all organization
     *                           members have on this project, constants available {@link OrganizationPermission}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether or not this project can be seen by everyone - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#update-a-project">
     * Update a project</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/{project_id}")
    public <T> T updateProject(Project project, Params bodyParams, ReturnFormat format) throws IOException {
        return updateProject(project.getId(), bodyParams, format);
    }

    /**
     * Method to update a project board's information. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param projectId:  the unique identifier of the project
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> name of the project - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> body of the project - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the project, constants available {@link OperationState}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "organization_permission"} -> the baseline permission that all organization
     *                           members have on this project, constants available {@link OrganizationPermission}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether or not this project can be seen by everyone - [boolean]
     *                       </li>
     *                    </ul>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#update-a-project">
     * Update a project</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/projects/{project_id}")
    public Project updateProject(long projectId, Params bodyParams) throws IOException {
        return updateProject(projectId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a project board's information. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param projectId:  the unique identifier of the project
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> name of the project - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> body of the project - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the project, constants available {@link OperationState}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "organization_permission"} -> the baseline permission that all organization
     *                           members have on this project, constants available {@link OrganizationPermission}
     *                           - [string]
     *                       </li>
     *                       <li>
     *                           {@code "private"} -> whether or not this project can be seen by everyone - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#update-a-project">
     * Update a project</a>
     **/
    @RequestPath(method = PATCH, path = "/projects/{project_id}")
    public <T> T updateProject(long projectId, Params bodyParams, ReturnFormat format) throws IOException {
        return returnProject(sendPatchRequest(ROOT_PROJECTS_PATH + "/" + projectId, bodyParams), format);
    }

    /**
     * Method to delete a project board. Returns a 404 Not Found status if projects are disabled
     *
     * @param project: the project to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#delete-a-project">
     * Delete a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/projects/{project_id}")
    public boolean deleteProject(Project project) {
        return deleteProject(project.getId());
    }

    /**
     * Method to delete a project board. Returns a 404 Not Found status if projects are disabled
     *
     * @param projectId: the unique identifier of the project
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#delete-a-project">
     * Delete a package for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/projects/{project_id}")
    public boolean deleteProject(long projectId) {
        try {
            sendDeleteRequest(ROOT_PROJECTS_PATH + "/" + projectId);
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
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public ArrayList<Project> getRepositoryProjects(Repository repository) throws IOException {
        return getRepositoryProjects(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public <T> T getRepositoryProjects(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryProjects(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public ArrayList<Project> getRepositoryProjects(String owner, String repo) throws IOException {
        return getRepositoryProjects(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public <T> T getRepositoryProjects(String owner, String repo, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(REPOS_PATH + owner + "/" + repo + PROJECTS_PATH), format);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public ArrayList<Project> getRepositoryProjects(Repository repository, Params queryParams) throws IOException {
        return getRepositoryProjects(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public <T> T getRepositoryProjects(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryProjects(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public ArrayList<Project> getRepositoryProjects(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryProjects(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the projects in a repository. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-repository-projects">
     * List repository projects</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/projects")
    public <T> T getRepositoryProjects(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(REPOS_PATH + owner + "/" + repo + PROJECTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository where crate the project
     * @param name:       the name of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public Project createRepositoryProject(Repository repository, String name) throws IOException {
        return createRepositoryProject(repository.getOwner().getLogin(), repository.getName(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository where crate the project
     * @param name:       the name of the project
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public <T> T createRepositoryProject(Repository repository, String name, ReturnFormat format) throws IOException {
        return createRepositoryProject(repository.getOwner().getLogin(), repository.getName(), name, format);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public Project createRepositoryProject(String owner, String repo, String name) throws IOException {
        return createRepositoryProject(owner, repo, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param name:   the name of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public <T> T createRepositoryProject(String owner, String repo, String name, ReturnFormat format) throws IOException {
        return createRepositoryProject(owner, repo, name, null, format);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository where crate the project
     * @param name:       the name of the project
     * @param body:       the description of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public Project createRepositoryProject(Repository repository, String name, String body) throws IOException {
        return createRepositoryProject(repository.getOwner().getLogin(), repository.getName(), name, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param repository: the repository where crate the project
     * @param name:       the name of the project
     * @param body:       the description of the project
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public <T> T createRepositoryProject(Repository repository, String name, String body,
                                         ReturnFormat format) throws IOException {
        return createRepositoryProject(repository.getOwner().getLogin(), repository.getName(), name, body, format);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the project
     * @param body:  the description of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public Project createRepositoryProject(String owner, String repo, String name, String body) throws IOException {
        return createRepositoryProject(owner, repo, name, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param name:   the name of the project
     * @param body:   the description of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-repository-project">
     * Create a repository project</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/projects")
    public <T> T createRepositoryProject(String owner, String repo, String name, String body,
                                         ReturnFormat format) throws IOException {
        return returnProject(sendPostRequest(REPOS_PATH + owner + "/" + repo + PROJECTS_PATH,
                createProjectPayload(name, body)), format);
    }

    /**
     * Method to create a user project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param name: the name of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-user-project">
     * Create a user project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/projects")
    public Project createUserProject(String name) throws IOException {
        return createUserProject(name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a user project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param name:   the name of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-user-project">
     * Create a user project</a>
     **/
    @RequestPath(method = POST, path = "/user/projects")
    public <T> T createUserProject(String name, ReturnFormat format) throws IOException {
        return createUserProject(name, null, format);
    }

    /**
     * Method to create a user project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param name: the name of the project
     * @param body: the description of the project
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-user-project">
     * Create a user project</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/projects")
    public Project createUserProject(String name, String body) throws IOException {
        return createUserProject(name, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a user project board. Returns a 404 Not Found status if projects are disabled
     * in the organization. If you do not have sufficient privileges to perform this action, a 401 Unauthorized or 410
     * Gone status is returned
     *
     * @param name:   the name of the project
     * @param body:   the description of the project
     * @param format: return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#create-a-user-project">
     * Create a user project</a>
     **/
    @RequestPath(method = POST, path = "/user/projects")
    public <T> T createUserProject(String name, String body, ReturnFormat format) throws IOException {
        return returnProject(sendPostRequest(USER_PATH + PROJECTS_PATH, createProjectPayload(name, body)),
                format);
    }

    /**
     * Method to create a payload to create a project
     *
     * @param name: the name of the project
     * @param body: the description of the project
     * @return project payload as {@link Params}
     **/
    private Params createProjectPayload(String name, String body) {
        Params payload = new Params();
        payload.addParam("name", name);
        if (body != null)
            payload.addParam("body", body);
        return payload;
    }

    /**
     * Method to create a project
     *
     * @param projectResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return project as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProject(String projectResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(projectResponse);
            case LIBRARY_OBJECT:
                return (T) new Project(new JSONObject(projectResponse));
            default:
                return (T) projectResponse;
        }
    }

    /**
     * Method to get the list of the user projects
     *
     * @param username: the handle for the GitHub user account
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-user-projects">
     * List user projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/projects")
    public ArrayList<Project> getUserProjects(String username) throws IOException {
        return getUserProjects(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the user projects
     *
     * @param username: the handle for the GitHub user account
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-user-projects">
     * List user projects</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/projects")
    public <T> T getUserProjects(String username, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(USERS_PATH + username + PROJECTS_PATH), format);
    }

    /**
     * Method to get the list of the user projects
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-user-projects">
     * List user projects</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/projects")
    public ArrayList<Project> getUserProjects(String username, Params queryParams) throws IOException {
        return getUserProjects(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the user projects
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the projects to return, constants available
     *                             {@link  OperationState} - [string, default open]
     *                        </li>
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/projects#list-user-projects">
     * List user projects</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/projects")
    public <T> T getUserProjects(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnProjects(sendGetRequest(USERS_PATH + username + PROJECTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a projects list
     *
     * @param projectsResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return projects list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProjects(String projectsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(projectsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Project> projects = new ArrayList<>();
                JSONArray jProjects = new JSONArray(projectsResponse);
                for (int j = 0; j < jProjects.length(); j++)
                    projects.add(new Project(jProjects.getJSONObject(j)));
                return (T) projects;
            default:
                return (T) projectsResponse;
        }
    }

}
