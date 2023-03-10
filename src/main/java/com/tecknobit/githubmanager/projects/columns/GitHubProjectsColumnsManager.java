package com.tecknobit.githubmanager.projects.columns;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.projects.boards.records.Project;
import com.tecknobit.githubmanager.projects.columns.records.ProjectColumn;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.projects.boards.GitHubBoardsManager.ROOT_PROJECTS_PATH;
import static com.tecknobit.githubmanager.projects.cards.GitHubCardsManager.*;

/**
 * The {@code GitHubProjectsColumnsManager} class is useful to manage all GitHub's columns endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns">
 * Project (classic) columns</a>
 * @see GitHubManager
 **/
public class GitHubProjectsColumnsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubProjectsColumnsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubProjectsColumnsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubProjectsColumnsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubProjectsColumnsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubProjectsColumnsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubProjectsColumnsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubProjectsColumnsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubProjectsColumnsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubProjectsColumnsManager} <br>
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
    public GitHubProjectsColumnsManager() {
        super();
    }

    /**
     * Method to get a project column
     *
     * @param columnId: the unique identifier of the column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#get-a-project-card">
     * Get a project column</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/columns/{column_id}")
    public ProjectColumn getProjectColumn(long columnId) throws IOException {
        return getProjectColumn(columnId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a project column
     *
     * @param columnId: the unique identifier of the column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#get-a-project-card">
     * Get a project column</a>
     **/
    @RequestPath(method = GET, path = "/projects/columns/{column_id}")
    public <T> T getProjectColumn(long columnId, ReturnFormat format) throws IOException {
        return returnProjectColumn(sendGetRequest(PROJECTS_COLUMNS_PATH + "/" + columnId), format);
    }

    /**
     * Method to update an existing project column
     *
     * @param column: the column to update
     * @param name:   name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#update-an-existing-project-column">
     * Update an existing project column</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/columns/{column_id}")
    public ProjectColumn updateProjectColumn(ProjectColumn column, String name) throws IOException {
        return updateProjectColumn(column.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to update an existing project column
     *
     * @param column: the column to update
     * @param name:   name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#update-an-existing-project-column">
     * Update an existing project column</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/columns/{column_id}")
    public <T> T updateProjectColumn(ProjectColumn column, String name, ReturnFormat format) throws IOException {
        return updateProjectColumn(column.getId(), name, format);
    }

    /**
     * Method to update an existing project column
     *
     * @param columnId: the unique identifier of the column
     * @param name:     name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#update-an-existing-project-column">
     * Update an existing project column</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/projects/columns/{column_id}")
    public ProjectColumn updateProjectColumn(long columnId, String name) throws IOException {
        return updateProjectColumn(columnId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to update an existing project column
     *
     * @param columnId: the unique identifier of the column
     * @param name:     name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#update-an-existing-project-column">
     * Update an existing project column</a>
     **/
    @RequestPath(method = PATCH, path = "/projects/columns/{column_id}")
    public <T> T updateProjectColumn(long columnId, String name, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("name", name);
        return returnProjectColumn(sendPatchRequest(PROJECTS_COLUMNS_PATH + "/" + columnId, payload), format);
    }

    /**
     * Method to delete a project column
     *
     * @param column: the column to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#delete-a-project-column">
     * Delete a project column</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/projects/columns/{column_id}")
    public boolean deleteProjectColumn(ProjectColumn column) {
        return deleteProjectColumn(column.getId());
    }

    /**
     * Method to delete a project column
     *
     * @param columnId: the unique identifier of the column
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#delete-a-project-column">
     * Delete a project column</a>
     **/
    @RequestPath(method = DELETE, path = "/projects/columns/{column_id}")
    public boolean deleteProjectColumn(long columnId) {
        try {
            sendDeleteRequest(PROJECTS_COLUMNS_PATH + "/" + columnId);
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
     * Method to move a project column
     *
     * @param column:   the column to move
     * @param position: the position of the column in a project. Can be one of: first, last, or {@code "after:<column_id>"}
     *                  to place after the specified column
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#move-a-project-column">
     * Move a project column</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/projects/columns/{column_id}/moves")
    public boolean moveProjectColumn(ProjectColumn column, String position) {
        return moveProjectColumn(column.getId(), position);
    }

    /**
     * Method to move a project column
     *
     * @param columnId: the unique identifier of the column
     * @param position: the position of the column in a project. Can be one of: first, last, or {@code "after:<column_id>"}
     *                  to place after the specified column
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#move-a-project-column">
     * Move a project column</a>
     **/
    @RequestPath(method = POST, path = "/projects/columns/{column_id}/moves")
    public boolean moveProjectColumn(long columnId, String position) {
        try {
            Params payload = new Params();
            payload.addParam("position", position);
            sendPostRequest(PROJECTS_COLUMNS_PATH + "/" + columnId + MOVES_PATH, payload);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get the list of the project columns
     *
     * @param project : the project from fetch the list
     * @return project columns as {@link ArrayList} of {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public ArrayList<ProjectColumn> getProjectColumns(Project project) throws IOException {
        return getProjectColumns(project.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param project: the project from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return project columns list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public <T> T getProjectColumns(Project project, ReturnFormat format) throws IOException {
        return getProjectColumns(project.getId(), format);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param projectId: the unique identifier of the project
     * @return project columns as {@link ArrayList} of {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public ArrayList<ProjectColumn> getProjectColumns(long projectId) throws IOException {
        return getProjectColumns(projectId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param projectId: the unique identifier of the project
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project columns list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public <T> T getProjectColumns(long projectId, ReturnFormat format) throws IOException {
        return returnProjectColumns(sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLUMNS_PATH), format);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param project:     the project from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return project columns as {@link ArrayList} of {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public ArrayList<ProjectColumn> getProjectColumns(Project project, Params queryParams) throws IOException {
        return getProjectColumns(project.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param project:     the project from fetch the list
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
     * @return project columns list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public <T> T getProjectColumns(Project project, Params queryParams, ReturnFormat format) throws IOException {
        return getProjectColumns(project.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param projectId:   the unique identifier of the project
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return project columns as {@link ArrayList} of {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public ArrayList<ProjectColumn> getProjectColumns(long projectId, Params queryParams) throws IOException {
        return getProjectColumns(projectId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project columns
     *
     * @param projectId:   the unique identifier of the project
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
     * @return project columns list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#list-project-columns">
     * List project columns</a>
     **/
    @RequestPath(method = GET, path = "/projects/{project_id}/columns")
    public <T> T getProjectColumns(long projectId, Params queryParams, ReturnFormat format) throws IOException {
        return returnProjectColumns(sendGetRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLUMNS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a project columns list
     *
     * @param projectColumnsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return project columns list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProjectColumns(String projectColumnsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(projectColumnsResponse);
            case LIBRARY_OBJECT:
                ArrayList<ProjectColumn> projectColumns = new ArrayList<>();
                JSONArray jProjectColumns = new JSONArray(projectColumnsResponse);
                for (int j = 0; j < jProjectColumns.length(); j++)
                    projectColumns.add(new ProjectColumn(jProjectColumns.getJSONObject(j)));
                return (T) projectColumns;
            default:
                return (T) projectColumnsResponse;
        }
    }

    /**
     * Method to create a project column
     *
     * @param project: the project where create the column
     * @param name:    name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#create-a-project-column">
     * Create a project column</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/projects/{project_id}/columns")
    public ProjectColumn createProjectColumn(Project project, String name) throws IOException {
        return createProjectColumn(project.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a project column
     *
     * @param project: the project where create the column
     * @param name:    name of the project column
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return project column as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#create-a-project-column">
     * Create a project column</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/projects/{project_id}/columns")
    public <T> T createProjectColumn(Project project, String name, ReturnFormat format) throws IOException {
        return createProjectColumn(project.getId(), name, format);
    }

    /**
     * Method to create a project column
     *
     * @param projectId: the unique identifier of the project
     * @param name:      name of the project column
     * @return project column as {@link ProjectColumn} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#create-a-project-column">
     * Create a project column</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/projects/{project_id}/columns")
    public ProjectColumn createProjectColumn(long projectId, String name) throws IOException {
        return createProjectColumn(projectId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a project column
     *
     * @param projectId: the unique identifier of the project
     * @param name:      name of the project column
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return project column as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/columns#create-a-project-column">
     * Create a project column</a>
     **/
    @RequestPath(method = POST, path = "/projects/{project_id}/columns")
    public <T> T createProjectColumn(long projectId, String name, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("name", name);
        return returnProjectColumn(sendPatchRequest(ROOT_PROJECTS_PATH + "/" + projectId + COLUMNS_PATH, payload),
                format);
    }

    /**
     * Method to create a project column
     *
     * @param projectColumnResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return project column as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProjectColumn(String projectColumnResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(projectColumnResponse);
            case LIBRARY_OBJECT:
                return (T) new ProjectColumn(new JSONObject(projectColumnResponse));
            default:
                return (T) projectColumnResponse;
        }
    }

}
