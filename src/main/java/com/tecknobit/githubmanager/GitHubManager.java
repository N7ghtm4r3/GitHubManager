package com.tecknobit.githubmanager;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.apis.APIRequest;
import com.tecknobit.apimanager.formatters.TimeFormatter;
import com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList;
import com.tecknobit.githubmanager.records.organization.OrganizationsList;
import com.tecknobit.githubmanager.records.repository.OrganizationRepositoriesList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;

/**
 * The {@code GitHubManager} class is useful to manage all GitHubManager's endpoints
 * giving basics methods for others GitHub's managers and basics endpoints for API requests
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/overview/resources-in-the-rest-api">
 * Overview</a>
 **/
// TODO: 30/10/2022 DELETE TEST PRINT IN REQUESTS
public class GitHubManager {

    /**
     * {@code BASE_ENDPOINT} is instance for GitHub's base endpoint to work on
     **/
    public static final String BASE_ENDPOINT = "https://api.github.com/";

    /**
     * {@code ACTIONS_PATH} constant for {@code "/actions/"} path
     **/
    public static final String ACTIONS_PATH = "/actions/";

    /**
     * {@code REPOS_PATH} constant for {@code "repos/"} path
     **/
    public static final String REPOS_PATH = "repos/";

    /**
     * {@code ENTERPRISES_PATH} constant for {@code "enterprises/"} path
     **/
    public static final String ENTERPRISES_PATH = "enterprises/";

    /**
     * {@code ORGS_PATH} constant for {@code "orgs/"} path
     **/
    public static final String ORGS_PATH = "orgs/";

    /**
     * {@code RUNNERS_PATH} constant for {@code "/runners"} path
     **/
    public static final String RUNNERS_PATH = "/runners";

    /**
     * {@code ORGANIZATIONS_PATH} constant for {@code "/organizations"} path
     **/
    public static final String ORGANIZATIONS_PATH = "/organizations";

    /**
     * {@code REPOSITORIES_PATH} constant for {@code "repositories"} path
     **/
    public static final String REPOSITORIES_PATH = "/repositories";

    /**
     * {@code RUNS_PATH} constant for {@code "runs"} path
     **/
    public static final String RUNS_PATH = "/runs";

    /**
     * {@code USERS_PATH} constant for {@code "users/"} path
     **/
    public static final String USERS_PATH = "users/";

    /**
     * {@code SUBSCRIPTION_PATH} constant for {@code "/subscription"} path
     **/
    public static final String SUBSCRIPTION_PATH = "/subscription";

    /**
     * {@code properties} is a local instance used to instantiate a new {@link GitHubManager}'s manager without
     * re-insert credentials
     **/
    protected static final Properties properties = new Properties();

    /**
     * {@code mainHeaders} is instance for main headers of all requests
     **/
    private static APIRequest.Headers mainHeaders;

    /**
     * {@code accessToken} personal access token for authentication to {@code "GitHub"}
     **/
    protected final String accessToken;

    /**
     * {@code apiRequest} is instance to make the API requests
     **/
    protected final APIRequest apiRequest;

    static {
        TimeFormatter.changeDefaultPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken: personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubManager(String accessToken) {
        this.accessToken = accessToken;
        apiRequest = new APIRequest();
        storeProperties(accessToken, null, -1);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken:         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage: custom error to show when is not a request error
     **/
    public GitHubManager(String accessToken, String defaultErrorMessage) {
        this.accessToken = accessToken;
        apiRequest = new APIRequest(defaultErrorMessage);
        storeProperties(accessToken, defaultErrorMessage, -1);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken:    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout: custom timeout for request
     **/
    public GitHubManager(String accessToken, int requestTimeout) {
        this.accessToken = accessToken;
        apiRequest = new APIRequest(requestTimeout);
        storeProperties(accessToken, null, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken:         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout:      custom timeout for request
     **/
    public GitHubManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        this.accessToken = accessToken;
        apiRequest = new APIRequest(defaultErrorMessage, requestTimeout);
        storeProperties(accessToken, defaultErrorMessage, -1);
    }

    /**
     * Constructor to init a {@link GitHubManager} <br>
     * Any params required
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
    public GitHubManager() {
        accessToken = properties.getProperty("accessToken");
        if (accessToken == null)
            throw new IllegalArgumentException("You need to call a parameterized constructor first");
        int requestTimeout = Integer.parseInt(properties.getProperty("requestTimeout"));
        String defaultErrorMessage = properties.getProperty("defaultErrorMessage");
        if (requestTimeout != -1 && defaultErrorMessage != null)
            apiRequest = new APIRequest(defaultErrorMessage, requestTimeout);
        else if (defaultErrorMessage != null)
            apiRequest = new APIRequest(defaultErrorMessage);
        else if (requestTimeout != -1)
            apiRequest = new APIRequest(requestTimeout);
        else
            apiRequest = new APIRequest();
    }

    /**
     * Method to store some properties
     *
     * @param accessToken:         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage: custom error to show when is not a request error
     * @param requestTimeout:      custom timeout for request
     **/
    private void storeProperties(String accessToken, String defaultErrorMessage, int requestTimeout) {
        properties.clear();
        properties.setProperty("accessToken", accessToken);
        if (defaultErrorMessage != null)
            properties.setProperty("defaultErrorMessage", defaultErrorMessage);
        properties.setProperty("requestTimeout", String.valueOf(requestTimeout));
        if (mainHeaders == null) {
            mainHeaders = new APIRequest.Headers();
            mainHeaders.addHeader("authorization", " token " + accessToken);
            mainHeaders.addHeader("accept", "application/vnd.github+json");
        }
    }

    /**
     * Method to send a {@code "GET"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    public String sendGetRequest(String endpoint) throws IOException {
        return sendRequest(endpoint, GET);
    }

    /**
     * Method to send a {@code "DELETE"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    public String sendDeleteRequest(String endpoint) throws IOException {
        return sendRequest(endpoint, DELETE);
    }

    /**
     * Method to send a request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    private String sendRequest(String endpoint, RequestMethod method) throws IOException {
        System.out.println(BASE_ENDPOINT + endpoint);
        apiRequest.sendAPIRequest(BASE_ENDPOINT + endpoint, method, mainHeaders);
        return apiRequest.getResponse();
    }

    /**
     * Method to send a {@code "POST"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    public String sendPostRequest(String endpoint, Params bodyParams) throws IOException {
        return sendRequestWithBody(endpoint, POST, bodyParams);
    }

    /**
     * Method to send a {@code "PUT"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     * <ul>
     *     <li>
     *         {@link #getErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #getJSONErrorResponse()}
     *     </li>
     *     <li>
     *         {@link #printErrorResponse()}
     *     </li>
     * </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    public String sendPutRequest(String endpoint, Params bodyParams) throws IOException {
        return sendRequestWithBody(endpoint, PUT, bodyParams);
    }

    /**
     * Method to send a {@code "PATCH"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    public String sendPatchRequest(String endpoint, Params bodyParams) throws IOException {
        return sendRequestWithBody(endpoint, PATCH, bodyParams);
    }

    /**
     * Method to send a request with a body payload to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     * @return response of the request as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     **/
    private String sendRequestWithBody(String endpoint, RequestMethod method, Params bodyPayload) throws IOException {
        // TODO: 02/12/2022 CHECK TO REMOVE CHECK ON NULL
        if (bodyPayload == null)
            bodyPayload = new Params();
        System.out.println(endpoint);
        apiRequest.sendJSONPayloadedAPIRequest(BASE_ENDPOINT + endpoint, method, mainHeaders, bodyPayload);
        return apiRequest.getResponse();
    }

    /**
     * Method to get error response of the request <br>
     * Any params required
     *
     * @return error response of request as {@link String} or defaultErrorResponse as {@link String} if is not a request
     * error
     **/
    public String getErrorResponse() {
        return apiRequest.getErrorResponse();
    }

    /**
     * Method to get error response of the request <br>
     * Any params required
     *
     * @return error response of the request formatted as {@link JSONObject} or {@link JSONArray} object or defaultErrorResponse
     * as {@link String} if is not a request error
     **/
    public <T> T getJSONErrorResponse() {
        return apiRequest.getJSONErrorResponse();
    }

    /**
     * Method to print error response of the request <br>
     * Any params required
     **/
    public void printErrorResponse() {
        apiRequest.printErrorResponse();
    }

    /**
     * Method to print error response of request <br>
     * Any params required
     *
     * @implNote response will be printed in JSON format or in a simple {@link String} format
     **/
    public void printJSONErrorResponse() {
        apiRequest.printJSONErrorResponse();
    }

    /**
     * Method to get status response code of the request <br>
     * Any params required
     *
     * @return response code of the request as int
     **/
    public int getStatusCode() {
        return apiRequest.getResponseStatusCode();
    }

    /**
     * Method to enable selected items for a list
     *
     * @param endpoint: endpoint to do the request
     * @param key:      key to add
     * @param ids:      list of ids to enable
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     **/
    protected boolean setItems(String endpoint, String key, Long[] ids) {
        Params params = new Params();
        params.addParam(key, Arrays.stream(ids).toList());
        try {
            sendPutRequest(endpoint, params);
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
     * Method to create an organizations list for an enterprise
     *
     * @param organizationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return enabled organizations list for an enterprise as {@code "format"} defines
     **/
    @Returner
    protected <T> T returnOrganizationsList(String organizationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationsResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationsList(new JSONObject(organizationsResponse));
            default:
                return (T) organizationsResponse;
        }
    }

    /**
     * Method to create a repositories list for an organization
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return enabled repositories list for an organization as {@code "format"} defines
     **/
    @Returner
    protected <T> T returnOrganizationRepositories(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationRepositoriesList(new JSONObject(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

    /**
     * Method to create a runners list
     *
     * @param runnersGroupResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return runners list as {@code "format"} defines
     **/
    @Returner
    protected <T> T returnRunnersList(String runnersGroupResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnersGroupResponse);
            case LIBRARY_OBJECT:
                return (T) new RunnersList(new JSONObject(runnersGroupResponse));
            default:
                return (T) runnersGroupResponse;
        }
    }

    /**
     * {@code Visibility} list of available visibilities
     **/
    public enum Visibility {

        /**
         * {@code "public"} visibility
         **/
        all("all"),

        /**
         * {@code "private"} visibility
         **/
        vPrivate("private"),

        /**
         * {@code "selected"} visibility
         **/
        selected("selected");

        /**
         * {@code "visibility"} value
         **/
        private final String visibility;

        /**
         * Constructor to init a {@link Visibility}
         *
         * @param visibility : {@code "visibility"} value
         **/
        Visibility(String visibility) {
            this.visibility = visibility;
        }

        /**
         * Method to get {@link #visibility} instance <br>
         * Any params required
         *
         * @return {@link #visibility} instance as {@link String}
         **/
        @Override
        public String toString() {
            return visibility;
        }

    }

    /**
     * {@code ReturnFormat} is the instance to pass in {@link Returner} methods to format as you want the response by
     * {@code "Binance"}
     *
     * @apiNote you can choose between:
     * <ul>
     * <li>
     * {@link Returner.ReturnFormat#STRING} -> returns the response formatted as {@link String}
     * </li>
     * <li>
     * {@link Returner.ReturnFormat#JSON} -> returns the response formatted as {@code "JSON"}
     * </li>
     * <li>
     * {@link Returner.ReturnFormat#LIBRARY_OBJECT} -> returns the response formatted as custom object offered by library that uses this list
     * </li>
     * </ul>
     **/
    public enum ReturnFormat {
        STRING,
        JSON,
        LIBRARY_OBJECT
    }

    /**
     * {@code Directions} is a list for the directions available
     **/
    public enum Directions {

        /**
         * {@code "asc"} direction
         **/
        asc,

        /**
         * {@code "desc"} direction
         **/
        desc

    }

    /**
     * The {@code Params} class is useful to assemble params values for the request
     *
     * @implNote this class can be used to assemble body payload or query request params
     * @implSpec look this library <a href="https://github.com/N7ghtm4r3/APIManager">here</a>
     * @see com.tecknobit.apimanager.apis.APIRequest.Params
     **/
    public static class Params extends APIRequest.Params {
    }

}
