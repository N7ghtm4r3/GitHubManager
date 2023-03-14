package com.tecknobit.githubmanager.actions.selfhosted.runnergroups;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.selfhosted.records.Runner;
import com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroup;
import com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records.RunnerGroupsList;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.organizations.organizations.records.OrganizationsList;
import com.tecknobit.githubmanager.repositories.repositories.records.CompleteRepository;
import com.tecknobit.githubmanager.repositories.repositories.records.OrganizationRepositoriesList;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList.returnRunnersList;
import static com.tecknobit.githubmanager.organizations.organizations.records.OrganizationsList.returnOrganizationsList;
import static com.tecknobit.githubmanager.repositories.repositories.records.OrganizationRepositoriesList.returnOrganizationRepositories;

/**
 * The {@code GitHubRunnerGroupsManager} class is useful to manage all GitHub's runner groups endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups">
 * About the Self-hosted runner groups API</a>
 * @see GitHubManager
 **/
public class GitHubRunnerGroupsManager extends GitHubManager {

    /**
     * {@code ACTIONS_RUNNER_GROUPS_PATH} constant for {@code "/actions/runner-groups"} path
     **/
    public static final String ACTIONS_RUNNER_GROUPS_PATH = ACTIONS_PATH + "runner-groups";

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRunnerGroupsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRunnerGroupsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRunnerGroupsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRunnerGroupsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnerGroupsManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubRunnerGroupsManager}'s manager without re-insert
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
    public GitHubRunnerGroupsManager() {
        super();
    }

    /**
     * Method to get all self-hosted runner groups for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return all self-hosted runner groups for an enterprise as {@link RunnerGroupsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-enterprise">
     * List self-hosted runner groups for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups")
    public RunnerGroupsList getEnterpriseRunnerGroupsList(String enterprise) throws IOException {
        return getEnterpriseRunnerGroupsList(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get all self-hosted runner groups for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-enterprise">
     * List self-hosted runner groups for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups")
    public <T> T getEnterpriseRunnerGroupsList(String enterprise, ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH),
                format);
    }

    /**
     * Method to get all self-hosted runner groups for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_organization"} -> only return runner groups that are allowed to be used by this organization - [string]
     *                        </li>
     *                     </ul>
     * @return all self-hosted runner groups for an enterprise as {@link RunnerGroupsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-enterprise">
     * List self-hosted runner groups for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups")
    public RunnerGroupsList getEnterpriseRunnerGroupsList(String enterprise, Params queryParams) throws IOException {
        return getEnterpriseRunnerGroupsList(enterprise, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all self-hosted runner groups for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_organization"} -> only return runner groups that are allowed to be used by this organization - [string]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-enterprise">
     * List self-hosted runner groups for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups")
    public <T> T getEnterpriseRunnerGroupsList(String enterprise, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create a new self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param name:       name of the runner group
     * @return new self-hosted runner group for an enterprise as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-enterprise">
     * Create a self-hosted runner group for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runner-groups")
    public RunnerGroup createEnterpriseRunnerGroup(String enterprise, String name) throws IOException {
        return createEnterpriseRunnerGroup(enterprise, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param name:       name of the runner group
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-enterprise">
     * Create a self-hosted runner group for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runner-groups")
    public <T> T createEnterpriseRunnerGroup(String enterprise, String name, ReturnFormat format) throws IOException {
        Params bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH,
                bodyParams), format);
    }

    /**
     * Method to create a new self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return new self-hosted runner group for an enterprise as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-enterprise">
     * Create a self-hosted runner group for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runner-groups")
    public RunnerGroup createEnterpriseRunnerGroup(String enterprise, String name,
                                                   Params runnerGroupDetails) throws IOException {
        return createEnterpriseRunnerGroup(enterprise, name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-enterprise">
     * Create a self-hosted runner group for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runner-groups")
    public <T> T createEnterpriseRunnerGroup(String enterprise, String name, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        runnerGroupDetails.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH,
                runnerGroupDetails), format);
    }

    /**
     * Method to get a specific self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return self-hosted runner group for an enterprise as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-enterprise">
     * Get a self-hosted runner group for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup getEnterpriseRunnerGroup(String enterprise, long runnerGroupId) throws IOException {
        return getEnterpriseRunnerGroup(enterprise, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-enterprise">
     * Get a self-hosted runner group for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public <T> T getEnterpriseRunnerGroup(String enterprise, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId), format);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an enterprise as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-enterprise">
     * Update a self-hosted runner group for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup,
                                                   Params runnerGroupDetails) throws IOException {
        return updateEnterpriseRunnerGroup(enterprise, runnerGroup.getId(), runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-enterprise">
     * Update a self-hosted runner group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public <T> T updateEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        return updateEnterpriseRunnerGroup(enterprise, runnerGroup.getId(), runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an enterprise as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-enterprise">
     * Update a self-hosted runner group for an enterprise</a>
     **/
    @RequestPath(method = PATCH, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId,
                                                   Params runnerGroupDetails) throws IOException {
        return updateEnterpriseRunnerGroup(enterprise, runnerGroupId, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an enterprise. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:         the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an enterprise as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-enterprise">
     * Update a self-hosted runner group for an enterprise</a>
     **/
    @RequestPath(method = PATCH, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public <T> T updateEnterpriseRunnerGroup(String enterprise, long runnerGroupId, Params runnerGroupDetails,
                                             ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH +
                "/" + runnerGroupId, runnerGroupDetails), format);
    }

    /**
     * Method to delete a self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-enterprise">
     * Delete a self-hosted runner group from an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public boolean deleteEnterpriseRunnerGroup(String enterprise, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(enterprise, runnerGroup.getId());
    }

    /**
     * Method to delete a self-hosted runner group for an enterprise. <br>
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-enterprise">
     * Delete a self-hosted runner group from an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}")
    public boolean deleteEnterpriseRunnerGroup(String enterprise, long runnerGroupId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId);
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
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: runner group from fetch the list
     * @return organizations list with access to a self-hosted runner group as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return organizations list with access to a self-hosted runner group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public <T> T getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup,
                                                ReturnFormat format) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroup.getId(), format);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return organizations list with access to a self-hosted runner group as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, long runnerGroupId) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return organizations list with access to a self-hosted runner group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public <T> T getAuthorizedOrganizationsList(String enterprise, long runnerGroupId,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_QUERY_PATH), format);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return organizations list with access to a self-hosted runner group as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup,
                                                            Params queryParams) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: runner group from fetch the list
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
     * @return organizations list with access to a self-hosted runner group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public <T> T getAuthorizedOrganizationsList(String enterprise, RunnerGroup runnerGroup, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return organizations list with access to a self-hosted runner group as {@link OrganizationsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public OrganizationsList getAuthorizedOrganizationsList(String enterprise, long runnerGroupId,
                                                            Params queryParams) throws IOException {
        return getAuthorizedOrganizationsList(enterprise, runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the organizations list with access to a self-hosted runner group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return organizations list with access to a self-hosted runner group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * List organization access to a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public <T> T getAuthorizedOrganizationsList(String enterprise, long runnerGroupId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnOrganizationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + ORGANIZATIONS_QUERY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:        the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup:       the runner group for the list
     * @param organizationsList: organizations list to authorize
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, OrganizationsList organizationsList) {
        return authorizeOrganizationsList(enterprise, runnerGroup.getId(), organizationsList);
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:        the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId:     unique identifier of the self-hosted runner group
     * @param organizationsList: organizations list to authorize
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, OrganizationsList organizationsList) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Organization organization : organizationsList.getOrganizations())
            ids.add(organization.getId());
        return authorizeOrganizationsList(enterprise, runnerGroupId, ids.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:       the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup:      the runner group for the list
     * @param organizationsIds: list of organization IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, ArrayList<Long> organizationsIds) {
        return authorizeOrganizationsList(enterprise, runnerGroup.getId(), organizationsIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:       the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup:      the runner group for the list
     * @param organizationsIds: list of organization IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, RunnerGroup runnerGroup, Long[] organizationsIds) {
        return authorizeOrganizationsList(enterprise, runnerGroup.getId(), organizationsIds);
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:       the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId:    unique identifier of the self-hosted runner group
     * @param organizationsIds: list of organization IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, ArrayList<Long> organizationsIds) {
        return authorizeOrganizationsList(enterprise, runnerGroupId, organizationsIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of organizations that have access to a self-hosted runner configured in an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:       the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId:    unique identifier of the self-hosted runner group
     * @param organizationsIds: list of organization IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-organization-access-for-a-self-hosted-runner-group-in-an-enterprise">
     * Set organization access for a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations")
    public boolean authorizeOrganizationsList(String enterprise, long runnerGroupId, Long[] organizationsIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                ORGANIZATIONS_QUERY_PATH, "selected_organization_ids", organizationsIds);
    }

    /**
     * Method to add an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the organization
     * @param org:         organization to authorize
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Add organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean authorizeOrganization(String enterprise, RunnerGroup runnerGroup, Organization org) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), org.getId());
    }

    /**
     * Method to add an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the organization
     * @param orgId:       the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Add organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean authorizeOrganization(String enterprise, RunnerGroup runnerGroup, long orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId);
    }

    /**
     * Method to add an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param org:           organization to authorize
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Add organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean authorizeOrganization(String enterprise, long runnerGroupId, Organization org) {
        return authorizeOrganization(enterprise, runnerGroupId, org.getId());
    }

    /**
     * Method to add an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param orgId:         the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Add organization access to a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean authorizeOrganization(String enterprise, long runnerGroupId, long orgId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    ORGANIZATIONS_QUERY_PATH + "/" + orgId, null);
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
     * Method to remove an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the organization
     * @param org:         organization from revoke authorization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Remove organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean removeAuthorizedOrganization(String enterprise, RunnerGroup runnerGroup, Organization org) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), org.getId());
    }

    /**
     * Method to remove an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the organization
     * @param orgId:       the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Remove organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean removeAuthorizedOrganization(String enterprise, RunnerGroup runnerGroup, long orgId) {
        return authorizeOrganization(enterprise, runnerGroup.getId(), orgId);
    }

    /**
     * Method to remove an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param org:           organization from revoke authorization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Remove organization access to a self-hosted runner group in an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean removeAuthorizedOrganization(String enterprise, long runnerGroupId, Organization org) {
        return authorizeOrganization(enterprise, runnerGroupId, org.getId());
    }

    /**
     * Method to remove an organization to the list of selected organizations that can access a self-hosted runner group.
     * The runner group must have visibility set to selected. For more information, see Create a self-hosted runner group for an enterprise.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param orgId:         the unique identifier of the organization
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
     * Remove organization access to a self-hosted runner group in an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/organizations/{org_id}")
    public boolean removeAuthorizedOrganization(String enterprise, long runnerGroupId, long orgId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    ORGANIZATIONS_QUERY_PATH + "/" + orgId);
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
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from fetch the list
     * @return self-hosted runners list that are in a specific enterprise group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return self-hosted runners list that are in a specific enterprise group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroup.getId(), format);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return self-hosted runners list that are in a specific enterprise group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return self-hosted runners list that are in a specific enterprise group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH), format);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return self-hosted runners list that are in a specific enterprise group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup,
                                                     Params queryParams) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from fetch the list
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
     * @return self-hosted runners list that are in a specific enterprise group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getEnterpriseRunnersGroupList(String enterprise, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return self-hosted runners list that are in a specific enterprise group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId,
                                                     Params queryParams) throws IOException {
        return getEnterpriseRunnersGroupList(enterprise, runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the self-hosted runners list that are in a specific enterprise group. <br>
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return self-hosted runners list that are in a specific enterprise group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
     * List self-hosted runners in a group for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getEnterpriseRunnersGroupList(String enterprise, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/"
                + runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the list
     * @param runners:     runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, RunnersList runners) {
        return setEnterpriseRunnersList(enterprise, runnerGroup.getId(), runners);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runners:       runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, RunnersList runners) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Runner runner : runners.getRunners())
            ids.add(runner.getId());
        return setEnterpriseRunnersList(enterprise, runnerGroupId, ids.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the list
     * @param runnersIds:  list of runner IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, ArrayList<Long> runnersIds) {
        return setEnterpriseRunnersList(enterprise, runnerGroup.getId(), runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the list
     * @param runnersIds:  list of runner IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setEnterpriseRunnersList(enterprise, runnerGroup.getId(), runnersIds);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, ArrayList<Long> runnersIds) {
        return setEnterpriseRunnersList(enterprise, runnerGroupId, runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an enterprise runner group.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-enterprise">
     * Set self-hosted runners in a group for an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setEnterpriseRunnersList(String enterprise, long runnerGroupId, Long[] runnersIds) {
        return setItems(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the runner
     * @param runner:      runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-enterprise">
     * Add a self-hosted runner to a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addEnterpriseRunnerToGroup(String enterprise, RunnerGroup runnerGroup, Runner runner) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-enterprise">
     * Add a self-hosted runner to a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addEnterpriseRunnerToGroup(String enterprise, long runnerGroupId, Runner runner) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroupId, runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group for the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-enterprise">
     * Add a self-hosted runner to a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addEnterpriseRunnerToGroup(String enterprise, RunnerGroup runnerGroup, long runnerId) {
        return addEnterpriseRunnerToGroup(enterprise, runnerGroup.getId(), runnerId);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-enterprise">
     * Add a self-hosted runner to a group for an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addEnterpriseRunnerToGroup(String enterprise, long runnerGroupId, long runnerId) {
        try {
            sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId, null);
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
     * Method to remove a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from remove the runner
     * @param runner:      runner to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-enterprise">
     * Remove a self-hosted runner from a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeEnterpriseRunnerFromGroup(String enterprise, RunnerGroup runnerGroup, Runner runner) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-enterprise">
     * Remove a self-hosted runner from a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeEnterpriseRunnerFromGroup(String enterprise, long runnerGroupId, Runner runner) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroupId, runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:  the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroup: the runner group from remove the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-enterprise">
     * Remove a self-hosted runner from a group for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeEnterpriseRunnerFromGroup(String enterprise, RunnerGroup runnerGroup, long runnerId) {
        return removeEnterpriseRunnerFromGroup(enterprise, runnerGroup.getId(), runnerId);
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param enterprise:    the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-enterprise">
     * Remove a self-hosted runner from a group for an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeEnterpriseRunnerFromGroup(String enterprise, long runnerGroupId, long runnerId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId);
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
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org: the organization from fetch the list
     * @return all self-hosted runner groups for an organization as {@link RunnerGroupsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroupsList getOrganizationRunnerGroupsList(Organization org) throws IOException {
        return getOrganizationRunnerGroupsList(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public <T> T getOrganizationRunnerGroupsList(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationRunnerGroupsList(org.getLogin(), format);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return all self-hosted runner groups for an organization as {@link RunnerGroupsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroupsList getOrganizationRunnerGroupsList(String org) throws IOException {
        return getOrganizationRunnerGroupsList(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public <T> T getOrganizationRunnerGroupsList(String org, ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH),
                format);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
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
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @return all self-hosted runner groups for an organization as {@link RunnerGroupsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroupsList getOrganizationRunnerGroupsList(Organization org, Params queryParams) throws IOException {
        return getOrganizationRunnerGroupsList(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
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
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public <T> T getOrganizationRunnerGroupsList(Organization org, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnerGroupsList(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
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
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroupsList getOrganizationRunnerGroupsList(String org, Params queryParams) throws IOException {
        return getOrganizationRunnerGroupsList(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
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
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return all self-hosted runner groups for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
     * List self-hosted runner groups for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public <T> T getOrganizationRunnerGroupsList(String org, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnerGroupsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create a runner groups list object
     *
     * @param runnerGroupsResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return runner groups list object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRunnerGroupsList(String runnerGroupsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerGroupsResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnerGroupsList(new JSONObject(runnerGroupsResponse));
            default:
                return (T) runnerGroupsResponse;
        }
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:  the organization to create runner group
     * @param name: name of the runner group
     * @return new self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroup createOrganizationRunnerGroup(Organization org, String name) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:    the organization to create runner group
     * @param name:   name of the runner group
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public <T> T createOrganizationRunnerGroup(Organization org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, format);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param name: name of the runner group
     * @return new self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroup createOrganizationRunnerGroup(String org, String name) throws IOException {
        return createOrganizationRunnerGroup(org, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   name of the runner group
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public <T> T createOrganizationRunnerGroup(String org, String name, ReturnFormat format) throws IOException {
        Params bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH,
                bodyParams), format);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization to create runner group
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return new self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroup createOrganizationRunnerGroup(Organization org, String name,
                                                     Params runnerGroupDetails) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization to create runner group
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public <T> T createOrganizationRunnerGroup(Organization org, String name, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return createOrganizationRunnerGroup(org.getLogin(), name, runnerGroupDetails, format);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return new self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runner-groups")
    public RunnerGroup createOrganizationRunnerGroup(String org, String name,
                                                     Params runnerGroupDetails) throws IOException {
        return createOrganizationRunnerGroup(org, name, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to create self-hosted runner groups {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * Lists all self-hosted runner groups configured in an organization and inherited from an enterprise
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param name:               name of the runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return new self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#create-a-self-hosted-runner-group-for-an-organization">
     * Create a self-hosted runner group for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups")
    public <T> T createOrganizationRunnerGroup(String org, String name, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        runnerGroupDetails.addParam("name", name);
        return returnRunnerGroup(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH,
                runnerGroupDetails), format);
    }

    /**
     * Method to get a specific self-hosted runner group {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from get the runner group
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-organization">
     * Get a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup getOrganizationRunnerGroup(Organization org, long runnerGroupId) throws IOException {
        return getOrganizationRunnerGroup(org.getLogin(), runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner group {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from get the runner group
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-organization">
     * Get a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T getOrganizationRunnerGroup(Organization org, long runnerGroupId, ReturnFormat format) throws IOException {
        return getOrganizationRunnerGroup(org.getLogin(), runnerGroupId, format);
    }

    /**
     * Method to get a specific self-hosted runner group {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-organization">
     * Get a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup getOrganizationRunnerGroup(String org, long runnerGroupId) throws IOException {
        return getOrganizationRunnerGroup(org, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner group {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}.
     * For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#get-a-self-hosted-runner-group-for-an-organization">
     * Get a self-hosted runner group for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T getOrganizationRunnerGroup(String org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId),
                format);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization from update the runner group
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup,
                                                     Params runnerGroupDetails) throws IOException {
        return updateOrganizationRunnerGroup(org.getLogin(), runnerGroup.getId(), runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization from update the runner group
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T updateOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return updateOrganizationRunnerGroup(org.getLogin(), runnerGroup.getId(), runnerGroupDetails, format);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization from update the runner group
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateOrganizationRunnerGroup(Organization org, long runnerGroupId,
                                                     Params runnerGroupDetails) throws IOException {
        return updateOrganizationRunnerGroup(org.getLogin(), runnerGroupId, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization from update the runner group
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T updateOrganizationRunnerGroup(Organization org, long runnerGroupId, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return updateOrganizationRunnerGroup(org.getLogin(), runnerGroupId, runnerGroupDetails, format);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateOrganizationRunnerGroup(String org, RunnerGroup runnerGroup,
                                                     Params runnerGroupDetails) throws IOException {
        return updateOrganizationRunnerGroup(org, runnerGroup.getId(), runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param runnerGroup:        runner group to update
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T updateOrganizationRunnerGroup(String org, RunnerGroup runnerGroup, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return updateOrganizationRunnerGroup(org, runnerGroup.getId(), runnerGroupDetails, format);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @return self-hosted runner group for an organization as {@link RunnerGroup} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public RunnerGroup updateOrganizationRunnerGroup(String org, long runnerGroupId,
                                                     Params runnerGroupDetails) throws IOException {
        return updateOrganizationRunnerGroup(org, runnerGroupId, runnerGroupDetails, LIBRARY_OBJECT);
    }

    /**
     * Method to update the {@code "name"} and {@code "visibility"} of a self-hosted runner group in an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:                the organization name. The name is not case-sensitive
     * @param runnerGroupId:      unique identifier of the self-hosted runner group
     * @param runnerGroupDetails: extra runner group details not mandatory, keys accepted are:
     *                            <ul>
     *                               <li>
     *                                   {@code "name"} -> name of the runner group - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "visibility"} -> visibility of a runner group. You can select all organizations
     *                                   or select individual organization, can be one of: {@code "selected"}, {@code "all"}
     *                                   - [string]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_organization_ids"} -> list of organization IDs that can access the runner group
     *                                   - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "runners"} -> list of runner IDs to add to the runner group - [array of integers]
     *                               </li>
     *                               <li>
     *                                   {@code "allows_public_repositories"} -> whether the runner group can be used by public repositories - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "restricted_to_workflows"} -> if {@code "true"}, the runner group will be restricted
     *                                   to running only the workflows specified in the {@code "selected_workflows"} array - [boolean, default false]
     *                               </li>
     *                               <li>
     *                                   {@code "selected_workflows"} -> list of workflows the runner group should be allowed to run.
     *                                   This setting will be ignored unless {@code "restricted_to_workflows"} is set to {@code "true"}
     *                                   - [boolean]
     *                               </li>
     *                            </ul>
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return self-hosted runner group for an organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#update-a-self-hosted-runner-group-for-an-organization">
     * Update a self-hosted runner group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public <T> T updateOrganizationRunnerGroup(String org, long runnerGroupId, Params runnerGroupDetails,
                                               ReturnFormat format) throws IOException {
        return returnRunnerGroup(sendPatchRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId, runnerGroupDetails), format);
    }

    /**
     * Method to create a runner group object
     *
     * @param runnerGroupResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return runner group object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRunnerGroup(String runnerGroupResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerGroupResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnerGroup(new JSONObject(runnerGroupResponse));
            default:
                return (T) runnerGroupResponse;
        }
    }

    /**
     * Method to delete a self-hosted runner group from an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from remove runner group
     * @param runnerGroup: the runner group to remove from the list
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-organization">
     * Delete a self-hosted runner group from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public boolean deleteOrganizationRunnerGroup(Organization org, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(org.getLogin(), runnerGroup.getId());
    }

    /**
     * Method to delete a self-hosted runner group from an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from remove runner group
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-organization">
     * Delete a self-hosted runner group from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public boolean deleteOrganizationRunnerGroup(Organization org, long runnerGroupId) {
        return deleteEnterpriseRunnerGroup(org.getLogin(), runnerGroupId);
    }

    /**
     * Method to delete a self-hosted runner group from an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group to remove from the list
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-organization">
     * Delete a self-hosted runner group from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public boolean deleteOrganizationRunnerGroup(String org, RunnerGroup runnerGroup) {
        return deleteEnterpriseRunnerGroup(org, runnerGroup.getId());
    }

    /**
     * Method to delete a self-hosted runner group from an organization
     * {@code "REST API"} is available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#delete-a-self-hosted-runner-group-from-an-organization">
     * Delete a self-hosted runner group from an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}")
    public boolean deleteOrganizationRunnerGroup(String org, long runnerGroupId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId);
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
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org,
                                                                      RunnerGroup runnerGroup) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroup.getId(), format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroup.getId(), format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org,
                                                                      long runnerGroupId) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(Organization org, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroupId, format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, long runnerGroupId) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(String org, long runnerGroupId,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH), format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                                                      Params queryParams) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(Organization org, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup,
                                                                      Params queryParams) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                            - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(String org, RunnerGroup runnerGroup, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                          <li>
     *                              {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                              - [boolean]
     *                          </li>
     *                       </ul>
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(Organization org, long runnerGroupId,
                                                                      Params queryParams) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                          <li>
     *                              {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                              - [boolean]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(Organization org, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return getAuthorizedRepositoriesList(org.getLogin(), runnerGroupId, queryParams, format);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                          <li>
     *                              {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                              - [boolean]
     *                          </li>
     *                       </ul>
     * @return repositories with access to a self-hosted runner group in organization as {@link OrganizationRepositoriesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public OrganizationRepositoriesList getAuthorizedRepositoriesList(String org, long runnerGroupId,
                                                                      Params queryParams) throws IOException {
        return getAuthorizedRepositoriesList(org, runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                          <li>
     *                              {@code "visible_to_repository"} -> only return runner groups that are allowed to be used by this repository
     *                              - [boolean]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repositories with access to a self-hosted runner group in organization as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * List repository access to a self-hosted runner group in an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public <T> T getAuthorizedRepositoriesList(String org, long runnerGroupId, Params queryParams,
                                               ReturnFormat format) throws IOException {
        return returnOrganizationRepositories(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH
                + "/" + runnerGroupId + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroup.getId(), repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org, runnerGroup.getId(), repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId,
                                             OrganizationRepositoriesList repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroupId, repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, long runnerGroupId, OrganizationRepositoriesList repositoriesIds) {
        ArrayList<Long> ids = new ArrayList<>();
        for (CompleteRepository repository : repositoriesIds.getRepositories())
            ids.add(repository.getId());
        return authorizeRepositoriesList(org, runnerGroupId, ids.toArray(new Long[0]));
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup, ArrayList<Long> repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroup.getId(), repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup, ArrayList<Long> repositoriesIds) {
        return authorizeRepositoriesList(org, runnerGroup.getId(), repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, RunnerGroup runnerGroup, Long[] repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroup.getId(), repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroup:     the runner group to set the list
     * @param repositoriesIds: list of repository IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, RunnerGroup runnerGroup, Long[] repositoriesIds) {
        return authorizeRepositoriesList(org, runnerGroup.getId(), repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId, ArrayList<Long> repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroupId, repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization to set the list
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(Organization org, long runnerGroupId, Long[] repositoriesIds) {
        return authorizeRepositoriesList(org.getLogin(), runnerGroupId, repositoriesIds);
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, long runnerGroupId, ArrayList<Long> repositoriesIds) {
        return authorizeRepositoriesList(org, runnerGroupId, repositoriesIds.toArray(new Long[0]));
    }

    /**
     * Method to set the repositories with access to a self-hosted runner group configured in an organization
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:             the organization name. The name is not case-sensitive
     * @param runnerGroupId:   unique identifier of the self-hosted runner group
     * @param repositoriesIds: list of repository IDs that can access the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-repository-access-for-a-self-hosted-runner-group-in-an-organization">
     * Set repository access for a self-hosted runner group in an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories")
    public boolean authorizeRepositoriesList(String org, long runnerGroupId, Long[] repositoriesIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                REPOSITORIES_PATH, "selected_repository_ids", repositoriesIds);
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from remove the repository
     * @param runnerGroup: the runner group from remove the repository
     * @param repository:  the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(Organization org, RunnerGroup runnerGroup, Repository repository) {
        return authorizeOrganization(org.getLogin(), runnerGroup.getId(), repository.getId());
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from remove the repository
     * @param repository:  the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(String org, RunnerGroup runnerGroup, Repository repository) {
        return authorizeOrganization(org, runnerGroup.getId(), repository.getId());
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:          the organization from remove the repository
     * @param runnerGroup:  the runner group from remove the repository
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(Organization org, RunnerGroup runnerGroup, long repositoryId) {
        return authorizeOrganization(org.getLogin(), runnerGroup.getId(), repositoryId);
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param runnerGroup:  the runner group from remove the repository
     * @param repositoryId: the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(String org, RunnerGroup runnerGroup, long repositoryId) {
        return authorizeOrganization(org, runnerGroup.getId(), repositoryId);
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param repository:    the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(Organization org, long runnerGroupId, Repository repository) {
        return authorizeOrganization(org.getLogin(), runnerGroupId, repository.getId());
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param repository:    the repository to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(String org, long runnerGroupId, Repository repository) {
        return authorizeOrganization(org, runnerGroupId, repository.getId());
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from remove the repository
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param repositoryId:  the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(Organization org, long runnerGroupId, long repositoryId) {
        return removeAuthorizedRepository(org.getLogin(), runnerGroupId, repositoryId);
    }

    /**
     * Method to remove a repository from the list of selected repositories that can access a self-hosted runner group.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param repositoryId:  the unique identifier of the repository
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-repository-access-to-a-self-hosted-runner-group-in-an-organization">
     * Remove repository access to a self-hosted runner group in an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/repositories/{repository_id}")
    public boolean removeAuthorizedRepository(String org, long runnerGroupId, long repositoryId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    REPOSITORIES_PATH + "/" + repositoryId);
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
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroup.getId(), format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroup.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroup.getId(), format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(Organization org, long runnerGroupId) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization from fetch the list
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(Organization org, long runnerGroupId, ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroupId, format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(String org, long runnerGroupId) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroupId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(String org, long runnerGroupId, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH), format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                     </ul>
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup,
                                                       Params queryParams) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization from fetch the list
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(Organization org, RunnerGroup runnerGroup, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                     </ul>
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup,
                                                       Params queryParams) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroup.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(String org, RunnerGroup runnerGroup, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroup.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                       </ul>
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(Organization org, long runnerGroupId,
                                                       Params queryParams) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(Organization org, long runnerGroupId, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return getOrganizationRunnersGroupList(org.getLogin(), runnerGroupId, queryParams, format);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                       </ul>
     * @return list of the self-hosted runners that are in a specific organization group as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public RunnersList getOrganizationRunnersGroupList(String org, long runnerGroupId,
                                                       Params queryParams) throws IOException {
        return getOrganizationRunnersGroupList(org, runnerGroupId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the self-hosted runners that are in a specific organization group
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return list of the self-hosted runners that are in a specific organization group as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
     * List self-hosted runners in a group for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public <T> T getOrganizationRunnersGroupList(String org, long runnerGroupId, Params queryParams,
                                                 ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" +
                runnerGroupId + RUNNERS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to set the runners
     * @param runnerGroup: runner group to set the runners
     * @param runners:     runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, RunnersList runners) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroup.getId(), runners);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: runner group to set the runners
     * @param runners:     runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, RunnersList runners) {
        return setOrganizationRunnersList(org, runnerGroup.getId(), runners);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to set the runners
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runners:       runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, RunnersList runners) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroupId, runners);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runners:       runners list to set
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, long runnerGroupId, RunnersList runners) {
        ArrayList<Long> ids = new ArrayList<>();
        for (Runner runner : runners.getRunners())
            ids.add(runner.getId());
        return setOrganizationRunnersList(org, runnerGroupId, ids.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to set the runners
     * @param runnerGroup: runner group to set the runners
     * @param runnersIds:  list of runner IDs to add to the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, ArrayList<Long> runnersIds) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroup.getId(), runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: runner group to set the runners
     * @param runnersIds:  list of runner IDs to add to the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, ArrayList<Long> runnersIds) {
        return setOrganizationRunnersList(org, runnerGroup.getId(), runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to set the runners
     * @param runnerGroup: runner group to set the runners
     * @param runnersIds:  list of runner IDs to add to the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroup.getId(), runnersIds);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: runner group to set the runners
     * @param runnersIds:  list of runner IDs to add to the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, RunnerGroup runnerGroup, Long[] runnersIds) {
        return setOrganizationRunnersList(org, runnerGroup.getId(), runnersIds);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to set the runners
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs to add to the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, ArrayList<Long> runnersIds) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroupId, runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to set the runners
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs to add to the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(Organization org, long runnerGroupId, Long[] runnersIds) {
        return setOrganizationRunnersList(org.getLogin(), runnerGroupId, runnersIds);
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs to add to the runner group in {@link ArrayList} of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, long runnerGroupId, ArrayList<Long> runnersIds) {
        return setOrganizationRunnersList(org, runnerGroupId, runnersIds.toArray(new Long[0]));
    }

    /**
     * Method to replace the list of self-hosted runners that are part of an organization runner group
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnersIds:    list of runner IDs to add to the runner group in array of {@link Long} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#set-self-hosted-runners-in-a-group-for-an-organization">
     * Set self-hosted runners in a group for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners")
    public boolean setOrganizationRunnersList(String org, long runnerGroupId, Long[] runnersIds) {
        return setItems(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                RUNNERS_PATH, "runners", runnersIds);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to add the runner
     * @param runnerGroup: the runner group to add the runner
     * @param runner:      runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(Organization org, RunnerGroup runnerGroup, Runner runner) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group to add the runner
     * @param runner:      runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(String org, RunnerGroup runnerGroup, Runner runner) {
        return addOrganizationRunnerToGroup(org, runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to add the runner
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(Organization org, long runnerGroupId, Runner runner) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroupId, runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(String org, long runnerGroupId, Runner runner) {
        return addOrganizationRunnerToGroup(org, runnerGroupId, runner.getId());
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to add the runner
     * @param runnerGroup: the runner group to add the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(Organization org, RunnerGroup runnerGroup, long runnerId) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroup.getId(), runnerId);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group to add the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(String org, RunnerGroup runnerGroup, long runnerId) {
        return addOrganizationRunnerToGroup(org, runnerGroup.getId(), runnerId);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to add the runner
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(Organization org, long runnerGroupId, long runnerId) {
        return addOrganizationRunnerToGroup(org.getLogin(), runnerGroupId, runnerId);
    }

    /**
     * Method to add a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#add-a-self-hosted-runner-to-a-group-for-an-organization">
     * Add a self-hosted runner to a group for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean addOrganizationRunnerToGroup(String org, long runnerGroupId, long runnerId) {
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId, null);
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
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to add the runner
     * @param runnerGroup: the runner group to add the runner
     * @param runner:      runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(Organization org, RunnerGroup runnerGroup, Runner runner) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group to add the runner
     * @param runner:      runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(String org, RunnerGroup runnerGroup, Runner runner) {
        return removeOrganizationRunnerFromGroup(org, runnerGroup.getId(), runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization to add the runner
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(Organization org, long runnerGroupId, Runner runner) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroupId, runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runner:        runner to add
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(String org, long runnerGroupId, Runner runner) {
        return removeOrganizationRunnerFromGroup(org, runnerGroupId, runner.getId());
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization to add the runner
     * @param runnerGroup: the runner group to add the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(Organization org, RunnerGroup runnerGroup, long runnerId) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroup.getId(), runnerId);
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param runnerGroup: the runner group to add the runner
     * @param runnerId:    unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(String org, RunnerGroup runnerGroup, long runnerId) {
        return removeOrganizationRunnerFromGroup(org, runnerGroup.getId(), runnerId);
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(Organization org, long runnerGroupId, long runnerId) {
        return removeOrganizationRunnerFromGroup(org.getLogin(), runnerGroupId, runnerId);
    }

    /**
     * Method to remove a self-hosted runner to a runner group configured in an organization.
     * The runner group must have {@code "visibility"} set to {@code "selected"}.
     * For more information, see "Create a self-hosted runner group for an organization."
     * {@code "REST API"} available with {@code "GitHub Enterprise Cloud"}. For more information, see GitHub's products.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param runnerGroupId: unique identifier of the self-hosted runner group
     * @param runnerId:      unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#remove-a-self-hosted-runner-from-a-group-for-an-organization">
     * Remove a self-hosted runner from a group for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runner-groups/{runner_group_id}/runners/{runner_id}")
    public boolean removeOrganizationRunnerFromGroup(String org, long runnerGroupId, long runnerId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNER_GROUPS_PATH + "/" + runnerGroupId +
                    RUNNERS_PATH + "/" + runnerId);
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
