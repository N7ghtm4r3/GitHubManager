package com.tecknobit.githubmanager.codespaces.machines;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codespaces.machines.records.MachinesList;
import com.tecknobit.githubmanager.codespaces.records.Codespace;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.codespaces.codespaces.GitHubCodespacesManager.CODESPACES_PATH;

/**
 * The {@code GitHubMachinesManager} class is useful to manage all GitHub's machines endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines">
 * Codespaces machines</a>
 * @see GitHubManager
 **/
public class GitHubMachinesManager extends GitHubManager {

    /**
     * {@code MACHINES_PATH} constant for {@code "/machines"} path
     **/
    public static final String MACHINES_PATH = "/machines";

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMachinesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMachinesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMachinesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMachinesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager} <br>
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
    public GitHubMachinesManager() {
        super();
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public MachinesList getRepositoryAvailableMachineTypes(Repository repository) throws IOException {
        return getRepositoryAvailableMachineTypes(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public <T> T getRepositoryAvailableMachineTypes(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryAvailableMachineTypes(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public MachinesList getRepositoryAvailableMachineTypes(String owner, String repo) throws IOException {
        return getRepositoryAvailableMachineTypes(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public <T> T getRepositoryAvailableMachineTypes(String owner, String repo, ReturnFormat format) throws IOException {
        return returnMachinesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH
                + MACHINES_PATH), format);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public MachinesList getRepositoryAvailableMachineTypes(Repository repository, Params queryParams) throws IOException {
        return getRepositoryAvailableMachineTypes(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public <T> T getRepositoryAvailableMachineTypes(Repository repository, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return getRepositoryAvailableMachineTypes(repository.getOwner().getLogin(), repository.getName(), queryParams,
                format);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public MachinesList getRepositoryAvailableMachineTypes(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryAvailableMachineTypes(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types available for a given repository based on its configuration. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "location"} -> location for this codespace. Assigned by IP if not provided
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "client_ip"} -> IP for location auto-detection when proxying a request - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
     * List available machine types for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/codespaces/machines")
    public <T> T getRepositoryAvailableMachineTypes(String owner, String repo, Params queryParams,
                                                    ReturnFormat format) throws IOException {
        return returnMachinesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CODESPACES_PATH
                + MACHINES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the machine types a codespace can transition to use. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param codespace: the codespace from fetch the list
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-machine-types-for-a-codespace">
     * List machine types for a codespace</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/machines")
    public MachinesList getCodespaceMachineTypes(Codespace codespace) throws IOException {
        return getCodespaceMachineTypes(codespace.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types a codespace can transition to use. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param codespace: the codespace from fetch the list
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-machine-types-for-a-codespace">
     * List machine types for a codespace</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/machines")
    public <T> T getCodespaceMachineTypes(Codespace codespace, ReturnFormat format) throws IOException {
        return getCodespaceMachineTypes(codespace.getName(), format);
    }

    /**
     * Method to get the list of the machine types a codespace can transition to use. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @return machines list as {@link MachinesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-machine-types-for-a-codespace">
     * List machine types for a codespace</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/machines")
    public MachinesList getCodespaceMachineTypes(String codespaceName) throws IOException {
        return getCodespaceMachineTypes(codespaceName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the machine types a codespace can transition to use. <br>
     * You must authenticate using an access token with the codespace scope to use this endpoint. <br>
     * GitHub Apps must have write access to the {@code "codespaces_metadata"} repository permission to use this endpoint
     *
     * @param codespaceName: the name of the codespace
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines#list-machine-types-for-a-codespace">
     * List machine types for a codespace</a>
     **/
    @RequestPath(method = GET, path = "/user/codespaces/{codespace_name}/machines")
    public <T> T getCodespaceMachineTypes(String codespaceName, ReturnFormat format) throws IOException {
        return returnMachinesList(sendGetRequest(USER_CODESPACES_PATH + codespaceName + MACHINES_PATH), format);
    }

    /**
     * Method to create a machines list
     *
     * @param machinesListResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnMachinesList(String machinesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(machinesListResponse);
            case LIBRARY_OBJECT:
                return (T) new MachinesList(new JSONObject(machinesListResponse));
            default:
                return (T) machinesListResponse;
        }
    }

}
