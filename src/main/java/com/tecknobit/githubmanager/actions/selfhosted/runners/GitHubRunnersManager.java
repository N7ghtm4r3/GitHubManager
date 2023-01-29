package com.tecknobit.githubmanager.actions.selfhosted.runners;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.selfhosted.records.Runner;
import com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.Application;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.RunnerLabel;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.RunnerLabelsList;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList.returnRunnersList;

/**
 * The {@code GitHubRunnersManager} class is useful to manage all GitHub's runners endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners">
 * About the Self-hosted runners API</a>
 * @see GitHubManager
 **/
public class GitHubRunnersManager extends GitHubManager {

    /**
     * {@code ACTIONS_RUNNERS_PATH} constant for {@code "/actions/runners"} path
     **/
    public static final String ACTIONS_RUNNERS_PATH = ACTIONS_PATH + "runners";

    /**
     * {@code DOWNLOADS_PATH} constant for {@code "/downloads"} path
     **/
    public static final String DOWNLOADS_PATH = "/downloads";

    /**
     * {@code REGISTRATION_TOKEN_PATH} constant for {@code "registration-token"} path
     **/
    public static final String REGISTRATION_TOKEN_PATH = "registration-token";

    /**
     * {@code REMOVE_TOKEN_PATH} constant for {@code "remove-token"} path
     **/
    public static final String REMOVE_TOKEN_PATH = "remove-token";

    /**
     * {@code LABELS_PATH} constant for {@code "/labels"} path
     **/
    public static final String LABELS_PATH = "/labels";

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRunnersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRunnersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRunnersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRunnersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubRunnersManager}'s manager without re-insert
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
    public GitHubRunnersManager() {
        super();
    }

    /**
     * Method to get the list of all self-hosted runners configured for an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enterprise runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-enterprise">
     * List self-hosted runners for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners")
    public RunnersList getEnterpriseRunnersList(String enterprise) throws IOException {
        return getEnterpriseRunnersList(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-enterprise">
     * List self-hosted runners for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners")
    public <T> T getEnterpriseRunnersList(String enterprise, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH),
                format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     *                     </ul>
     * @return enterprise runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-enterprise">
     * List self-hosted runners for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners")
    public RunnersList getEnterpriseRunnersList(String enterprise, Params queryParams) throws IOException {
        return getEnterpriseRunnersList(enterprise, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return enterprise runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-enterprise">
     * List self-hosted runners for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners")
    public <T> T getEnterpriseRunnersList(String enterprise, Params queryParams, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enterprise applications list as {@link Collection} of {@link Application} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-enterprise">
     * List runner applications for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/downloads")
    public Collection<Application> getEnterpriseApplicationsList(String enterprise) throws IOException {
        return getEnterpriseApplicationsList(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise applications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-enterprise">
     * List runner applications for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/downloads")
    public <T> T getEnterpriseApplicationsList(String enterprise, ReturnFormat format) throws IOException {
        return returnApplicationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                DOWNLOADS_PATH), format);
    }

    /**
     * Method to get a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enterprise registration token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-enterprise">
     * Create a registration token for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/registration-token")
    public GitHubToken createEnterpriseRegistrationToken(String enterprise) throws IOException {
        return createEnterpriseRegistrationToken(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise registration token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-enterprise">
     * Create a registration token for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/registration-token")
    public <T> T createEnterpriseRegistrationToken(String enterprise, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REGISTRATION_TOKEN_PATH, null), format);
    }

    /**
     * Method to get a token that you can pass to the config script to remove a self-hosted runner from an enterprise. The token expires after one hour
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return enterprise remove token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-enterprise">
     * Create a remove token for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/remove-token")
    public GitHubToken createEnterpriseRemoveToken(String enterprise) throws IOException {
        return createEnterpriseRemoveToken(enterprise, LIBRARY_OBJECT);
    }

    /**
     * Method to get a token that you can pass to the config script to remove a self-hosted runner from an enterprise. The token expires after one hour
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise remove token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-enterprise">
     * Create a remove token for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/remove-token")
    public <T> T createEnterpriseRemoveToken(String enterprise, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REMOVE_TOKEN_PATH, null), format);
    }

    /**
     * Method to get a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return enterprise runner as {@link Runner} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-enterprise">
     * Get a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}")
    public Runner getEnterpriseRunner(String enterprise, long runnerId) throws IOException {
        return getEnterpriseRunner(enterprise, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise runner as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-enterprise">
     * Get a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}")
    public <T> T getEnterpriseRunner(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnRunner(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId), format);
    }

    /**
     * Method to force the removal of a self-hosted runner from an enterprise. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-enterprise">
     * Delete a self-hosted runner from an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}")
    public boolean deleteEnterpriseRunner(String enterprise, Runner runner) {
        return deleteEnterpriseRunner(enterprise, runner.getId());
    }

    /**
     * Method to force the removal of a self-hosted runner from an enterprise. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-enterprise">
     * Delete a self-hosted runner from an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}")
    public boolean deleteEnterpriseRunner(String enterprise, long runnerId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" + runnerId);
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
     * Method to get a list of all labels for a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from fetch the list
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-enterprise">
     * List labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getEnterpriseLabelsList(String enterprise, Runner runner) throws IOException {
        return getEnterpriseLabelsList(enterprise, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-enterprise">
     * List labels for a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T getEnterpriseLabelsList(String enterprise, Runner runner, ReturnFormat format) throws IOException {
        return getEnterpriseLabelsList(enterprise, runner.getId(), format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-enterprise">
     * List labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getEnterpriseLabelsList(String enterprise, long runnerId) throws IOException {
        return getEnterpriseLabelsList(enterprise, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-enterprise">
     * List labels for a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = GET, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T getEnterpriseLabelsList(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, Runner runner, RunnerLabelsList labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, long runnerId, RunnerLabelsList labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return addEnterpriseCustomLabels(enterprise, runnerId, customLabels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link RunnerLabel} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link RunnerLabel} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link RunnerLabel} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link RunnerLabel} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner in array of {@link RunnerLabel} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, Runner runner, String[] labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to add to the runner in array of {@link RunnerLabel} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in array of {@link RunnerLabel} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addEnterpriseCustomLabels(String enterprise, long runnerId, String[] labels) throws IOException {
        return addEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in array of {@link RunnerLabel} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
     * Add custom labels to a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = POST, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T addEnterpriseCustomLabels(String enterprise, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, Runner runner, RunnerLabelsList labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, long runnerId, RunnerLabelsList labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return setEnterpriseCustomLabels(enterprise, runnerId, customLabels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, Runner runner, String[] labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner to set the custom list
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setEnterpriseCustomLabels(String enterprise, long runnerId, String[] labels) throws IOException {
        return setEnterpriseCustomLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an enterprise
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
     * Set custom labels for a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = PUT, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T setEnterpriseCustomLabels(String enterprise, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an enterprise. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the list
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-enterprise">
     * Remove all custom labels from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllEnterpriseCustomLabels(String enterprise, Runner runner) throws IOException {
        return removeAllEnterpriseCustomLabels(enterprise, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an enterprise. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-enterprise">
     * Remove all custom labels from a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T removeAllEnterpriseCustomLabels(String enterprise, Runner runner, ReturnFormat format) throws IOException {
        return removeAllEnterpriseCustomLabels(enterprise, runner.getId(), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an enterprise. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-enterprise">
     * Remove all custom labels from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllEnterpriseCustomLabels(String enterprise, long runnerId) throws IOException {
        return removeAllEnterpriseCustomLabels(enterprise, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an enterprise. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-enterprise">
     * Remove all custom labels from a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels")
    public <T> T removeAllEnterpriseCustomLabels(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the label
     * @param label:      the label to remove
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeEnterpriseCustomLabel(String enterprise, Runner runner, RunnerLabel label) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runner.getId(), label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the label
     * @param label:      the label to remove
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeEnterpriseCustomLabel(String enterprise, Runner runner, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runner.getId(), label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the label
     * @param name:       the name of a self-hosted runner's custom label
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeEnterpriseCustomLabel(String enterprise, Runner runner, String name) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runner.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runner:     runner from remove the label
     * @param name:       the name of a self-hosted runner's custom label
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeEnterpriseCustomLabel(String enterprise, Runner runner, String name,
                                             ReturnFormat format) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runner.getId(), name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param label:      the label to remove
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeEnterpriseCustomLabel(String enterprise, long runnerId, RunnerLabel label) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runnerId, label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param label:      the label to remove
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeEnterpriseCustomLabel(String enterprise, long runnerId, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runnerId, label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param name:       the name of a self-hosted runner's custom label
     * @return enterprise labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeEnterpriseCustomLabel(String enterprise, long runnerId, String name) throws IOException {
        return removeEnterpriseCustomLabel(enterprise, runnerId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an enterprise. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "manage_runners:enterprise"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param name:       the name of a self-hosted runner's custom label
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return enterprise labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-enterprise">
     * Remove a custom label from a self-hosted runner for an enterprise</a>
     **/
    @RequestPath(method = DELETE, path = "/enterprises/{enterprise}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeEnterpriseCustomLabel(String enterprise, long runnerId, String name,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + name), format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization from fetch the list
     * @return organization runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public RunnersList getOrganizationRunnersList(Organization org) throws IOException {
        return getOrganizationRunnersList(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public <T> T getOrganizationRunnersList(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationRunnersList(org.getLogin(), format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public RunnersList getOrganizationRunnersList(String org) throws IOException {
        return getOrganizationRunnersList(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public <T> T getOrganizationRunnersList(String org, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH),
                format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     * @return organization runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public RunnersList getOrganizationRunnersList(Organization org, Params queryParams) throws IOException {
        return getOrganizationRunnersList(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     * @return organization runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public <T> T getOrganizationRunnersList(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationRunnersList(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     * @return organization runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public RunnersList getOrganizationRunnersList(String org, Params queryParams) throws IOException {
        return getOrganizationRunnersList(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
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
     * @return organization runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-an-organization">
     * List self-hosted runners for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners")
    public <T> T getOrganizationRunnersList(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization from fetch the list
     * @return organization applications list as {@link Collection} of {@link Application} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-organization">
     * List runner applications for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/downloads")
    public Collection<Application> getOrganizationApplicationsList(Organization org) throws IOException {
        return getOrganizationApplicationsList(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization applications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-organization">
     * List runner applications for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/downloads")
    public <T> T getOrganizationApplicationsList(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationApplicationsList(org.getLogin(), format);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization applications list as {@link Collection} of {@link Application} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-organization">
     * List runner applications for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/downloads")
    public Collection<Application> getOrganizationApplicationsList(String org) throws IOException {
        return getOrganizationApplicationsList(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of all self-hosted runners configured for an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization applications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-organization">
     * List runner applications for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/downloads")
    public <T> T getOrganizationApplicationsList(String org, ReturnFormat format) throws IOException {
        return returnApplicationsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH +
                DOWNLOADS_PATH), format);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization to create token
     * @return registration token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-organization">
     * Create a registration token for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/registration-token")
    public GitHubToken createOrganizationRegistrationToken(Organization org) throws IOException {
        return createOrganizationRegistrationToken(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to create token
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return registration token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-organization">
     * Create a registration token for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/registration-token")
    public <T> T createOrganizationRegistrationToken(Organization org, ReturnFormat format) throws IOException {
        return createOrganizationRegistrationToken(org.getLogin(), format);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return registration token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-organization">
     * Create a registration token for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/registration-token")
    public GitHubToken createOrganizationRegistrationToken(String org) throws IOException {
        return createOrganizationRegistrationToken(org, LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return registration token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-organization">
     * Create a registration token for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/registration-token")
    public <T> T createOrganizationRegistrationToken(String org, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/"
                + REGISTRATION_TOKEN_PATH, null), format);
    }

    /**
     * Method to return a token that you can pass to the config script to remove a self-hosted runner from an organization. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization to create token
     * @return remove token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-organization">
     * Create a remove token for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/remove-token")
    public GitHubToken createOrganizationRemoveToken(Organization org) throws IOException {
        return createOrganizationRemoveToken(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script to remove a self-hosted runner from an organization. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to create token
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return remove token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-organization">
     * Create a remove token for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/remove-token")
    public <T> T createOrganizationRemoveToken(Organization org, ReturnFormat format) throws IOException {
        return createOrganizationRemoveToken(org.getLogin(), format);
    }

    /**
     * Method to return a token that you can pass to the config script to remove a self-hosted runner from an organization. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return remove token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-organization">
     * Create a remove token for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/remove-token")
    public GitHubToken createOrganizationRemoveToken(String org) throws IOException {
        return createOrganizationRemoveToken(org, LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script to remove a self-hosted runner from an organization. The token expires after one hour
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return remove token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-organization">
     * Create a remove token for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/remove-token")
    public <T> T createOrganizationRemoveToken(String org, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/"
                + REMOVE_TOKEN_PATH, null), format);
    }

    /**
     * Method to get a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to get the runner
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organization runner as {@link Runner} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-organization">
     * Get a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}")
    public Runner getOrganizationRunner(Organization org, long runnerId) throws IOException {
        return getOrganizationRunner(org.getLogin(), runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to get the runner
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization runner as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-organization">
     * Get a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}")
    public <T> T getOrganizationRunner(Organization org, long runnerId, ReturnFormat format) throws IOException {
        return getOrganizationRunner(org.getLogin(), runnerId, format);
    }

    /**
     * Method to get a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organization runner as {@link Runner} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-organization">
     * Get a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}")
    public Runner getOrganizationRunner(String org, long runnerId) throws IOException {
        return getOrganizationRunner(org, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organization runner as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-an-organization">
     * Get a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}")
    public <T> T getOrganizationRunner(String org, long runnerId, ReturnFormat format) throws IOException {
        return returnRunner(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId), format);
    }

    /**
     * Method to forces the removal of a self-hosted runner from an organization. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the runner
     * @param runner: runner to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-organization">
     * Delete a self-hosted runner from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}")
    public boolean deleteOrganizationRunner(Organization org, Runner runner) {
        return deleteOrganizationRunner(org.getLogin(), runner.getId());
    }

    /**
     * Method to forces the removal of a self-hosted runner from an organization. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the runner
     * @param runnerId: unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-organization">
     * Delete a self-hosted runner from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}")
    public boolean deleteOrganizationRunner(Organization org, long runnerId) {
        return deleteOrganizationRunner(org.getLogin(), runnerId);
    }

    /**
     * Method to forces the removal of a self-hosted runner from an organization. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: runner to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-organization">
     * Delete a self-hosted runner from an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}")
    public boolean deleteOrganizationRunner(String org, Runner runner) {
        return deleteOrganizationRunner(org, runner.getId());
    }

    /**
     * Method to forces the removal of a self-hosted runner from an organization. You can use this endpoint to completely remove
     * the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-an-organization">
     * Delete a self-hosted runner from an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}")
    public boolean deleteOrganizationRunner(String org, long runnerId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" + runnerId);
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
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from fetch the list
     * @param runner: the runner from fetch the list
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getOrganizationLabelsList(Organization org, Runner runner) throws IOException {
        return getOrganizationLabelsList(org.getLogin(), runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from fetch the list
     * @param runner: the runner from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T getOrganizationLabelsList(Organization org, Runner runner, ReturnFormat format) throws IOException {
        return getOrganizationLabelsList(org.getLogin(), runner.getId(), format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from fetch the list
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getOrganizationLabelsList(String org, Runner runner) throws IOException {
        return getOrganizationLabelsList(org, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T getOrganizationLabelsList(String org, Runner runner, ReturnFormat format) throws IOException {
        return getOrganizationLabelsList(org, runner.getId(), format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from fetch the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getOrganizationLabelsList(Organization org, long runnerId) throws IOException {
        return getOrganizationLabelsList(org.getLogin(), runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from fetch the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T getOrganizationLabelsList(Organization org, long runnerId, ReturnFormat format) throws IOException {
        return getOrganizationLabelsList(org.getLogin(), runnerId, format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getOrganizationLabelsList(String org, long runnerId) throws IOException {
        return getOrganizationLabelsList(org, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
     * List labels for a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T getOrganizationLabelsList(String org, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, Runner runner, RunnerLabelsList labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, Runner runner, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, Runner runner, RunnerLabelsList labels) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, Runner runner, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to add the custom labels list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, long runnerId, RunnerLabelsList labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to add the custom labels list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, long runnerId, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, long runnerId, RunnerLabelsList labels) throws IOException {
        return addOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, long runnerId, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return addOrganizationCustomLabels(org, runnerId, customLabels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, Runner runner,
                                                        Collection<String> labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, Runner runner, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, Runner runner,
                                                        Collection<String> labels) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, Runner runner, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to add the custom labels list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, long runnerId,
                                                        Collection<String> labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to add the custom labels list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, long runnerId, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, long runnerId,
                                                        Collection<String> labels) throws IOException {
        return addOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection}  of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, long runnerId, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, Runner runner, String[] labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to add the custom labels list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array  of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, Runner runner, String[] labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, Runner runner, String[] labels) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array  of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, Runner runner, String[] labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to add the custom labels list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(Organization org, long runnerId, String[] labels) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array  of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(Organization org, long runnerId, String[] labels,
                                             ReturnFormat format) throws IOException {
        return addOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array  of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addOrganizationCustomLabels(String org, long runnerId, String[] labels) throws IOException {
        return addOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array  of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
     * Add custom labels to a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T addOrganizationCustomLabels(String org, long runnerId, String[] labels,
                                             ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPostRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, Runner runner, RunnerLabelsList labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, Runner runner, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, Runner runner, RunnerLabelsList labels) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, Runner runner, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, long runnerId, RunnerLabelsList labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, long runnerId, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, long runnerId, RunnerLabelsList labels) throws IOException {
        return setOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, long runnerId, RunnerLabelsList labels,
                                             ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return setOrganizationCustomLabels(org, runnerId, customLabels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, Runner runner,
                                                        Collection<String> labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, Runner runner, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, Runner runner,
                                                        Collection<String> labels) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, Runner runner, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, long runnerId,
                                                        Collection<String> labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, long runnerId, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, long runnerId,
                                                        Collection<String> labels) throws IOException {
        return setOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, long runnerId, Collection<String> labels,
                                             ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPutRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, Runner runner, String[] labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization to set the list
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, Runner runner, String[] labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, Runner runner, String[] labels) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner to add the custom labels list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, Runner runner, String[] labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(Organization org, long runnerId, String[] labels) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization to set the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(Organization org, long runnerId, String[] labels,
                                             ReturnFormat format) throws IOException {
        return setOrganizationCustomLabels(org.getLogin(), runnerId, labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setOrganizationCustomLabels(String org, long runnerId, String[] labels) throws IOException {
        return setOrganizationCustomLabels(org, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in an organization
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
     * Set custom labels for a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T setOrganizationCustomLabels(String org, long runnerId, String[] labels,
                                             ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPutRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the list
     * @param runner: the runner from remove the list
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllOrganizationCustomLabels(Organization org, Runner runner) throws IOException {
        return removeAllOrganizationCustomLabels(org.getLogin(), runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the list
     * @param runner: the runner from remove the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T removeAllOrganizationCustomLabels(Organization org, Runner runner, ReturnFormat format) throws IOException {
        return removeAllOrganizationCustomLabels(org.getLogin(), runner.getId(), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the list
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllOrganizationCustomLabels(String org, Runner runner) throws IOException {
        return removeAllOrganizationCustomLabels(org, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T removeAllOrganizationCustomLabels(String org, Runner runner, ReturnFormat format) throws IOException {
        return removeAllOrganizationCustomLabels(org, runner.getId(), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllOrganizationCustomLabels(Organization org, long runnerId) throws IOException {
        return removeAllOrganizationCustomLabels(org.getLogin(), runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the list
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T removeAllOrganizationCustomLabels(Organization org, long runnerId, ReturnFormat format) throws IOException {
        return removeAllOrganizationCustomLabels(org.getLogin(), runnerId, format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllOrganizationCustomLabels(String org, long runnerId) throws IOException {
        return removeAllOrganizationCustomLabels(org, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in an organization. Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
     * Remove all custom labels from a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels")
    public <T> T removeAllOrganizationCustomLabels(String org, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the label
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(Organization org, Runner runner, RunnerLabel label) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runner.getId(), label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the label
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(Organization org, Runner runner, RunnerLabel label,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runner.getId(), label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(String org, Runner runner, RunnerLabel label) throws IOException {
        return removeOrganizationCustomLabel(org, runner.getId(), label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(String org, Runner runner, RunnerLabel label,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org, runner.getId(), label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the label
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(Organization org, Runner runner, String name) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runner.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization from remove the label
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(Organization org, Runner runner, String name,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runner.getId(), name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(String org, Runner runner, String name) throws IOException {
        return removeOrganizationCustomLabel(org, runner.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(String org, Runner runner, String name,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org, runner.getId(), name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the label
     * @param runnerId: unique identifier of the self-hosted runner
     * @param label:    the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(Organization org, long runnerId, RunnerLabel label) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runnerId, label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the label
     * @param runnerId: unique identifier of the self-hosted runner
     * @param label:    the name of a self-hosted runner's custom label
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(Organization org, long runnerId, RunnerLabel label,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runnerId, label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param label:    the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(String org, long runnerId, RunnerLabel label) throws IOException {
        return removeOrganizationCustomLabel(org, runnerId, label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param label:    the name of a self-hosted runner's custom label
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(String org, long runnerId, RunnerLabel label,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org, runnerId, label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the label
     * @param runnerId: unique identifier of the self-hosted runner
     * @param name:     the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(Organization org, long runnerId, String name) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runnerId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization from remove the label
     * @param runnerId: unique identifier of the self-hosted runner
     * @param name:     the name of a self-hosted runner's custom label
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(Organization org, long runnerId, String name,
                                               ReturnFormat format) throws IOException {
        return removeOrganizationCustomLabel(org.getLogin(), runnerId, name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param name:     the name of a self-hosted runner's custom label
     * @return organizations labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeOrganizationCustomLabel(String org, long runnerId, String name) throws IOException {
        return removeOrganizationCustomLabel(org, runnerId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in an organization. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param name:     the name of a self-hosted runner's custom label
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return organizations labels as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
     * Remove a custom label from a self-hosted runner for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeOrganizationCustomLabel(String org, long runnerId, String name,
                                               ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ORGS_PATH + org + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + name), format);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from fetch the list
     * @return repository runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public RunnersList getRepositoryRunnersList(Repository repository) throws IOException {
        return getRepositoryRunnersList(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public <T> T getRepositoryRunnersList(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryRunnersList(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public RunnersList getRepositoryRunnersList(String owner, String repo) throws IOException {
        return getRepositoryRunnersList(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public <T> T getRepositoryRunnersList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH),
                format);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repository runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public RunnersList getRepositoryRunnersList(Repository repository, Params queryParams) throws IOException {
        return getRepositoryRunnersList(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository:  the repository from fetch the list
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
     * @return repository runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public <T> T getRepositoryRunnersList(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryRunnersList(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repository runners list as {@link RunnersList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public RunnersList getRepositoryRunnersList(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryRunnersList(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list all self-hosted runners configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @return repository runners list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-self-hosted-runners-for-a-repository">
     * List self-hosted runners for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners")
    public <T> T getRepositoryRunnersList(String owner, String repo, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of the binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from fetch the list
     * @return repository applications list as {@link Collection} of {@link Application} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-a-repository">
     * List runner applications for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/downloads")
    public Collection<Application> getRepositoryApplicationsList(Repository repository) throws IOException {
        return getRepositoryApplicationsList(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository applications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-a-repository">
     * List runner applications for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/downloads")
    public <T> T getRepositoryApplicationsList(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryApplicationsList(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository applications list as {@link Collection} of {@link Application} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-a-repository">
     * List runner applications for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/downloads")
    public Collection<Application> getRepositoryApplicationsList(String owner, String repo) throws IOException {
        return getRepositoryApplicationsList(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the binaries for the runner application that you can download and run
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository applications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-a-repository">
     * List runner applications for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/downloads")
    public <T> T getRepositoryApplicationsList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnApplicationsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH +
                DOWNLOADS_PATH), format);
    }

    /**
     * Method to create an applications list
     *
     * @param applicationsResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return an applications list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnApplicationsList(String applicationsResponse, ReturnFormat format) {
        try {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(applicationsResponse);
                case LIBRARY_OBJECT:
                    ArrayList<Application> applications = new ArrayList<>();
                    JSONArray jApplications = new JSONArray(applicationsResponse);
                    for (int j = 0; j < jApplications.length(); j++)
                        applications.add(new Application(jApplications.getJSONObject(j)));
                    return (T) applications;
                default:
                    return (T) applicationsResponse;
            }
        } catch (JSONException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to create the registration token
     * @return registration token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-a-repository">
     * Create a registration token for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/registration-token")
    public GitHubToken createRepositoryRegistrationToken(Repository repository) throws IOException {
        return createRepositoryRegistrationToken(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to create the registration token
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return registration token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-a-repository">
     * Create a registration token for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/registration-token")
    public <T> T createRepositoryRegistrationToken(Repository repository, ReturnFormat format) throws IOException {
        return createRepositoryRegistrationToken(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return registration token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-a-repository">
     * Create a registration token for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/registration-token")
    public GitHubToken createRepositoryRegistrationToken(String owner, String repo) throws IOException {
        return createRepositoryRegistrationToken(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to the config script. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return registration token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-a-repository">
     * Create a registration token for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/registration-token")
    public <T> T createRepositoryRegistrationToken(String owner, String repo, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/"
                + REGISTRATION_TOKEN_PATH, null), format);
    }

    /**
     * Method to return a token that you can pass to remove a self-hosted runner from a repository. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to create the registration token
     * @return remove token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-a-repository">
     * Create a remove token for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/remove-token")
    public GitHubToken createRepositoryRemoveToken(Repository repository) throws IOException {
        return createRepositoryRemoveToken(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to remove a self-hosted runner from a repository. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to create the registration token
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return remove token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-a-repository">
     * Create a remove token for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/remove-token")
    public <T> T createRepositoryRemoveToken(Repository repository, ReturnFormat format) throws IOException {
        return createRepositoryRemoveToken(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to return a token that you can pass to remove a self-hosted runner from a repository. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return remove token as {@link GitHubToken} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-a-repository">
     * Create a remove token for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/remove-token")
    public GitHubToken createRepositoryRemoveToken(String owner, String repo) throws IOException {
        return createRepositoryRemoveToken(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to return a token that you can pass to remove a self-hosted runner from a repository. The token expires after one hour
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return remove token as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-a-repository">
     * Create a remove token for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/remove-token")
    public <T> T createRepositoryRemoveToken(String owner, String repo, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/"
                + REMOVE_TOKEN_PATH, null), format);
    }

    /**
     * Method to create a token
     *
     * @param tokenResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return a token as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGitHubToken(String tokenResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tokenResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubToken(new JSONObject(tokenResponse));
            default:
                return (T) tokenResponse;
        }
    }

    /**
     * Method to get a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from get the runner
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return repository runner as {@link Runner} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-a-repository">
     * Get a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public Runner getRepositoryRunner(Repository repository, long runnerId) throws IOException {
        return getRepositoryRunner(repository.getOwner().getLogin(), repository.getName(), runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from get the runner
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository runner as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-a-repository">
     * Get a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public <T> T getRepositoryRunner(Repository repository, long runnerId, ReturnFormat format) throws IOException {
        return getRepositoryRunner(repository.getOwner().getLogin(), repository.getName(), runnerId, format);
    }

    /**
     * Method to get a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return repository runner as {@link Runner} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-a-repository">
     * Get a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public Runner getRepositoryRunner(String owner, String repo, long runnerId) throws IOException {
        return getRepositoryRunner(owner, repo, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository runner as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#get-a-self-hosted-runner-for-a-repository">
     * Get a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public <T> T getRepositoryRunner(String owner, String repo, long runnerId, ReturnFormat format) throws IOException {
        return returnRunner(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId), format);
    }

    /**
     * Method to create a runner object
     *
     * @param runnerResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return runner object as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRunner(String runnerResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerResponse);
            case LIBRARY_OBJECT:
                return (T) new Runner(new JSONObject(runnerResponse));
            default:
                return (T) runnerResponse;
        }
    }

    /**
     * Method to force the removal of a self-hosted runner from a repository.
     * You can use this endpoint to completely remove the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from delete the runner
     * @param runner:     runner to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-a-repository">
     * Delete a self-hosted runner from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public boolean deleteRepositoryRunner(Repository repository, Runner runner) {
        return deleteRepositoryRunner(repository.getOwner().getLogin(), repository.getName(), runner.getId());
    }

    /**
     * Method to force the removal of a self-hosted runner from a repository.
     * You can use this endpoint to completely remove the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: runner to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-a-repository">
     * Delete a self-hosted runner from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public boolean deleteRepositoryRunner(String owner, String repo, Runner runner) {
        return deleteRepositoryRunner(owner, repo, runner.getId());
    }

    /**
     * Method to force the removal of a self-hosted runner from a repository.
     * You can use this endpoint to completely remove the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from delete the runner
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-a-repository">
     * Delete a self-hosted runner from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public boolean deleteRepositoryRunner(Repository repository, long runnerId) {
        return deleteRepositoryRunner(repository.getOwner().getLogin(), repository.getName(), runnerId);
    }

    /**
     * Method to force the removal of a self-hosted runner from a repository.
     * You can use this endpoint to completely remove the runner when the machine you were using no longer exists
     * You must authenticate using an access token with the {@code "repo"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#delete-a-self-hosted-runner-from-a-repository">
     * Delete a self-hosted runner from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}")
    public boolean deleteRepositoryRunner(String owner, String repo, long runnerId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" + runnerId);
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
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from the list
     * @param runner:     the runner from the list
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getRepositoryLabelsList(Repository repository, Runner runner) throws IOException {
        return getRepositoryLabelsList(repository.getOwner().getLogin(), repository.getName(), runner.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from the list
     * @param runner:     the runner from the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T getRepositoryLabelsList(Repository repository, Runner runner, ReturnFormat format) throws IOException {
        return getRepositoryLabelsList(repository.getOwner().getLogin(), repository.getName(), runner.getId(), format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from the list
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getRepositoryLabelsList(String owner, String repo, Runner runner) throws IOException {
        return getRepositoryLabelsList(owner, repo, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T getRepositoryLabelsList(String owner, String repo, Runner runner, ReturnFormat format) throws IOException {
        return getRepositoryLabelsList(owner, repo, runner.getId(), format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getRepositoryLabelsList(Repository repository, long runnerId) throws IOException {
        return getRepositoryLabelsList(repository.getOwner().getLogin(), repository.getName(), runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T getRepositoryLabelsList(Repository repository, long runnerId, ReturnFormat format) throws IOException {
        return getRepositoryLabelsList(repository.getOwner().getLogin(), repository.getName(), runnerId, format);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList getRepositoryLabelsList(String owner, String repo, long runnerId) throws IOException {
        return getRepositoryLabelsList(owner, repo, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of all labels for a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
     * List labels for a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T getRepositoryLabelsList(String owner, String repo, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, Runner runner, RunnerLabelsList labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, Runner runner, RunnerLabelsList labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, long runnerId, RunnerLabelsList labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, long runnerId, RunnerLabelsList labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return addRepositoryCustomLabels(owner, repo, runnerId, customLabels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    @WrappedRequest
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, Runner runner, String[] labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runner:     the runner to add the list
     * @param labels:     the names of the custom labels to add to the runner in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, Runner runner, String[] labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to add the list
     * @param labels: the names of the custom labels to add to the runner in array of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(Repository repository, long runnerId, String[] labels) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to add the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     the names of the custom labels to add to the runner in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(Repository repository, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels, format);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList addRepositoryCustomLabels(String owner, String repo, long runnerId, String[] labels) throws IOException {
        return addRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to add custom labels to a self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   the names of the custom labels to add to the runner in array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
     * Add custom labels to a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T addRepositoryCustomLabels(String owner, String repo, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, Runner runner, RunnerLabelsList labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, Runner runner,
                                                      RunnerLabelsList labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, Runner runner, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, long runnerId, RunnerLabelsList labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, long runnerId,
                                                      RunnerLabelsList labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in {@link RunnerLabelsList} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, long runnerId, RunnerLabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (RunnerLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return setRepositoryCustomLabels(owner, repo, runnerId, customLabels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, Runner runner,
                                                      Collection<String> labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, long runnerId,
                                                      Collection<String> labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in {@link Collection} of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, Runner runner, String[] labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runner:     the runner to set the list
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(), labels,
                format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, Runner runner,
                                                      String[] labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner to set the list
     * @param labels: he names of the custom labels to set for the runner.
     *                You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runner.getId(), labels, format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(Repository repository, long runnerId, String[] labels) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository to set the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param labels:     he names of the custom labels to set for the runner.
     *                    You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(Repository repository, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        return setRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, labels,
                format);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in array of {@link String} format
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList setRepositoryCustomLabels(String owner, String repo, long runnerId,
                                                      String[] labels) throws IOException {
        return setRepositoryCustomLabels(owner, repo, runnerId, labels, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all previous custom labels and set the new custom labels for a specific self-hosted runner configured in a repository
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param labels:   he names of the custom labels to set for the runner.
     *                  You can pass an empty array to remove all custom labels in array of {@link String} format
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
     * Set custom labels for a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T setRepositoryCustomLabels(String owner, String repo, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the list
     * @param runner:     the runner from remove the list
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllRepositoryCustomLabels(Repository repository, Runner runner) throws IOException {
        return removeAllRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the list
     * @param runner:     the runner from remove the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T removeAllRepositoryCustomLabels(Repository repository, Runner runner, ReturnFormat format) throws IOException {
        return removeAllRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runner.getId(),
                format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the list
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllRepositoryCustomLabels(String owner, String repo, Runner runner) throws IOException {
        return removeAllRepositoryCustomLabels(owner, repo, runner.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T removeAllRepositoryCustomLabels(String owner, String repo, Runner runner,
                                                 ReturnFormat format) throws IOException {
        return removeAllRepositoryCustomLabels(owner, repo, runner.getId(), format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllRepositoryCustomLabels(Repository repository, long runnerId) throws IOException {
        return removeAllRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the list
     * @param runnerId:   unique identifier of the self-hosted runner
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T removeAllRepositoryCustomLabels(Repository repository, long runnerId, ReturnFormat format) throws IOException {
        return removeAllRepositoryCustomLabels(repository.getOwner().getLogin(), repository.getName(), runnerId, format);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public RunnerLabelsList removeAllRepositoryCustomLabels(String owner, String repo, long runnerId) throws IOException {
        return removeAllRepositoryCustomLabels(owner, repo, runnerId, LIBRARY_OBJECT);
    }

    /**
     * Method to remove all custom labels from a self-hosted runner configured in a repository.
     * Returns the remaining {@code "read-only"} labels from the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted runner
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
     * Remove all custom labels from a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels")
    public <T> T removeAllRepositoryCustomLabels(String owner, String repo, long runnerId,
                                                 ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runner:     the runner from remove the label
     * @param label:      the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(Repository repository, Runner runner, RunnerLabel label) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runner.getId(),
                label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runner:     the runner from remove the label
     * @param label:      the label to remove
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(Repository repository, Runner runner, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runner.getId(),
                label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(String owner, String repo, Runner runner,
                                                        RunnerLabel label) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runner.getId(), label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param label:  the label to remove
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(String owner, String repo, Runner runner, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runner.getId(), label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runner:     the runner from remove the label
     * @param name:       the name of a self-hosted runner's custom label
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(Repository repository, Runner runner, String name) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runner.getId(), name,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runner:     the runner from remove the label
     * @param name:       the name of a self-hosted runner's custom label
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(Repository repository, Runner runner, String name,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runner.getId(), name,
                format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(String owner, String repo, Runner runner, String name) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runner.getId(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param runner: the runner from remove the label
     * @param name:   the name of a self-hosted runner's custom label
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(String owner, String repo, Runner runner, String name,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runner.getId(), name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runnerId:   unique identifier of the self-hosted
     * @param label:      the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(Repository repository, long runnerId, RunnerLabel label) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runnerId, label.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runnerId:   unique identifier of the self-hosted
     * @param label:      the label to remove
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(Repository repository, long runnerId, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runnerId, label.getName(),
                format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param label: the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(String owner, String repo, long runnerId,
                                                        RunnerLabel label) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runnerId, label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param label:  the label to remove
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(String owner, String repo, long runnerId, RunnerLabel label,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runnerId, label.getName(), format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runnerId:   unique identifier of the self-hosted
     * @param name:       the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(Repository repository, long runnerId, String name) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runnerId, name,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param repository: the repository from remove the runner
     * @param runnerId:   unique identifier of the self-hosted
     * @param name:       the label to remove
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(Repository repository, long runnerId, String name,
                                             ReturnFormat format) throws IOException {
        return removeRepositoryCustomLabel(repository.getOwner().getLogin(), repository.getName(), runnerId, name, format);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted
     * @param name:     the label to remove
     * @return repository labels list as {@link RunnerLabelsList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public RunnerLabelsList removeRepositoryCustomLabel(String owner, String repo, long runnerId, String name) throws IOException {
        return removeRepositoryCustomLabel(owner, repo, runnerId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a custom label from a self-hosted runner configured in a repository. Returns the remaining labels from the runner.
     * This endpoint returns a {@code "404 Not Found"} status if the custom label is not present on the runner
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param runnerId: unique identifier of the self-hosted
     * @param name:     the label to remove
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
     * Remove a custom label from a self-hosted runner for a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/actions/runners/{runner_id}/labels/{name}")
    public <T> T removeRepositoryCustomLabel(String owner, String repo, long runnerId, String name,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + name), format);
    }

    /**
     * Method to create a labels list
     *
     * @param labelsResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnLabelsList(String labelsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(labelsResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnerLabelsList(new JSONObject(labelsResponse));
            default:
                return (T) labelsResponse;
        }
    }

}
