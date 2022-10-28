package com.tecknobit.githubmanager;

import com.tecknobit.apimanager.apis.APIRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

import static com.tecknobit.apimanager.apis.APIRequest.GET_METHOD;

/**
 * The {@code GitHubManager} class is useful to manage all GitHubManager's endpoints
 * giving basics methods for others GitHub's managers and basics endpoints for API requests
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see official documentation at: <a href="https://docs.github.com/en/rest/overview/resources-in-the-rest-api">
 * Overview</a>
 **/
public class GitHubManager {

    public static final String BASE_ENDPOINT = "https://api.github.com/";
    /**
     * {@code properties} is a local instance used to instantiate a new {@link GitHubManager}'s manager without
     * re-insert credentials
     **/
    protected static final Properties properties = new Properties();
    private static APIRequest.Headers mainHeaders;
    /**
     * {@code accessToken} personal access token for authentication to GitHub
     **/
    protected final String accessToken;
    /**
     * {@code apiRequest} is instance to make the API requests
     **/
    protected final APIRequest apiRequest;

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken: personal access token for authentication to GitHub
     **/
    public GitHubManager(String accessToken) {
        this.accessToken = accessToken;
        apiRequest = new APIRequest();
        storeProperties(accessToken, null, -1);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken:         personal access token for authentication to GitHub
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
     * @param accessToken:    personal access token for authentication to GitHub
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
     * @param accessToken:         personal access token for authentication to GitHub
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

    private void storeProperties(String accessToken, String defaultErrorMessage, int requestTimeout) {
        properties.clear();
        properties.setProperty("accessToken", accessToken);
        if (defaultErrorMessage != null)
            properties.setProperty("defaultErrorMessage", defaultErrorMessage);
        properties.setProperty("requestTimeout", String.valueOf(requestTimeout));
    }

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

    public static class GitHubResponse {

        protected final JsonHelper hResponse;
        private final String message;
        private final String documentationUrl;

        public GitHubResponse(JSONObject jResponse) {
            hResponse = new JsonHelper(jResponse);
            message = hResponse.getString("message");
            documentationUrl = hResponse.getString("documentation_url");
        }

        public String getMessage() {
            return message;
        }

        public String getDocumentationUrl() {
            return documentationUrl;
        }

        @Override
        public String toString() {
            String toString = new JSONObject(this).toString();
            if (toString.contains("message")) {
                return new JSONObject("{" +
                        "\"message\":\"" + message + "\"," +
                        "\"documentation_url\":\"" + documentationUrl +
                        "\"}").toString();
            }
            return toString;
        }

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
