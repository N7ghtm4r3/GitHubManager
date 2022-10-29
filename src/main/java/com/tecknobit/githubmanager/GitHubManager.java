package com.tecknobit.githubmanager;

import com.tecknobit.apimanager.apis.APIRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

import static com.tecknobit.apimanager.apis.APIRequest.GET_METHOD;

/**
 * The {@code GitHubManager} class is useful to manage all GitHubManager's endpoints
 * giving basics methods for others GitHub's managers and basics endpoints for API requests
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/overview/resources-in-the-rest-api">
 * Overview</a>
 **/
public class GitHubManager {

    /**
     * {@code BASE_ENDPOINT} is instance for GitHub's base endpoint to work on
     **/
    public static final String BASE_ENDPOINT = "https://api.github.com/";

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
    }

    /**
     * Method to send a {@code "GET"} request to {@code "GitHub"}
     *
     * @param endpoint: endpoint of the request {@code "GitHub"}
     **/
    public String sendGetRequest(String endpoint) throws IOException {
        if (mainHeaders == null) {
            mainHeaders = new APIRequest.Headers();
            mainHeaders.addHeader("authorization", " token " + accessToken);
            mainHeaders.addHeader("accept", "application/vnd.github+json");
        }
        apiRequest.sendAPIRequest(BASE_ENDPOINT + endpoint, GET_METHOD, mainHeaders);
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
     * BaseList of return format types offered by library to format the responses as user wants
     *
     * @apiNote <ul>
     * <li>
     * STRING -> return response formatted as {@link String}
     * </li>
     * <li>
     * JSON -> return response formatted as JSON ({@link org.json.JSONObject} or {@link org.json.JSONArray}}
     * </li>
     * <li>
     * LIBRARY_OBJECT -> return response formatted as custom object offered by {@code GoogleManager}'s library
     * </li>
     * </ul>
     **/
    public enum ReturnFormat {
        STRING,
        JSON,
        LIBRARY_OBJECT
    }

    /**
     * The {@code GitHubResponse} class is useful to format all GitHub's responses giving basics methods
     * for others {@link ReturnFormat#LIBRARY_OBJECT}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class GitHubResponse {

        /**
         * {@code hResponse} is instance to manage {@code "JSON"} data format
         **/
        protected final JsonHelper hResponse;

        /**
         * {@code message} the message of the error if exists
         **/
        private final String message;

        /**
         * {@code documentationUrl} the documentation url to see more details if the error exists
         **/
        private final String documentationUrl;

        /**
         * {@code instantiatedWithError} this flag shows if the object has been instantiated with a normal workflow,
         * so with successful response by {@code "GitHub"}, or if this object has been instantiated with an unsuccessful
         * workflow if the response by {@code "GitHub"} has been unsuccessful
         **/
        private final boolean instantiatedWithError;

        /**
         * Constructor to init a {@link GitHubResponse}
         *
         * @param jResponse: response by {@code "GitHub"} as {@link JSONObject}
         **/
        public GitHubResponse(JSONObject jResponse) {
            if (jResponse == null)
                jResponse = new JSONObject();
            hResponse = new JsonHelper(jResponse);
            message = hResponse.getString("message");
            documentationUrl = hResponse.getString("documentation_url");
            instantiatedWithError = message != null;
        }

        /**
         * Method to get {@link #message} instance <br>
         * Any params required
         *
         * @return {@link #message} instance as {@link String}
         **/
        public String getMessage() {
            return message;
        }

        /**
         * Method to get {@link #documentationUrl} instance <br>
         * Any params required
         *
         * @return {@link #documentationUrl} instance as {@link String}
         **/
        public String getDocumentationUrl() {
            return documentationUrl;
        }

        /**
         * Method to get {@link #instantiatedWithError} instance <br>
         * Any params required
         *
         * @return {@link #instantiatedWithError} instance as boolean
         **/
        public boolean isInstantiatedWithError() {
            return instantiatedWithError;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String} with two different ways:
         * <ul>
         *     <li>
         *         If {@link #instantiatedWithError} is set on {@code "true"} the message will be something like:
         *         <pre>{@code {
         *  "instantiatedWithError": true,
         *  "message": #message,
         *  "documentation_url": #documentation_url
         * }
         *             }
         *         </pre>
         *     </li>
         *     <li>
         *         If {@link #instantiatedWithError} is set on {@code "false"} the message will be something like:
         *         <pre {@code {
         * "instantiatedWithError": false,
         * // rest of the LIBRARY_OBJECT details formatted as JSON
         * }
         *             }
         *         </pre>
         *     </li>
         * </ul>
         */
        @Override
        public String toString() {
            if (instantiatedWithError) {
                return new JSONObject("{" +
                        "\"message\":\"" + message + "\"," +
                        "\"documentation_url\":\"" + documentationUrl +
                        "\"}").put("instantiatedWithError", true).toString();
            }
            return new JSONObject(this).toString();
        }

    }

    /**
     * The {@code Params} class is useful to assemble params values for the request
     *
     * @implNote this class can be used to assemble body payload or query request params
     * @implSpec look this library <a href="https://github.com/N7ghtm4r3/APIManager">here</a>
     * @see com.tecknobit.apimanager.apis.APIRequest.Params
     **/
    public static class Params extends APIRequest.Params {}

}
