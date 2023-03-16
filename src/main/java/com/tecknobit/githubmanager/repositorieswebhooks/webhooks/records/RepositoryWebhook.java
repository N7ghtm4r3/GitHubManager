package com.tecknobit.githubmanager.repositorieswebhooks.webhooks.records;

import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import com.tecknobit.githubmanager.organizations.webhooks.records.OrganizationWebhook;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code RepositoryWebhook} class is useful to format a GitHub's repository webhook
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
 *             List repository webhooks</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repos#create-a-repository-webhook">
 *             Create a repository webhook</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repos#get-a-repository-webhook">
 *             Get a repository webhook</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
 *             Update a repository webhook</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 * @see OrganizationWebhook
 **/
public class RepositoryWebhook extends OrganizationWebhook {

    /**
     * {@code testUrl} the test url of the repository webhook
     **/
    private final String testUrl;

    /**
     * {@code lastResponse} the last response of the repository webhook
     **/
    private final LastResponse lastResponse;

    /**
     * Constructor to init a {@link RepositoryWebhook}
     *
     * @param id            : the ping url of the repository webhook
     * @param name          : the name of the item
     * @param url           : url value
     * @param pingUrl       : the name of the item
     * @param deliveriesUrl : deliveries url of the repository webhook
     * @param events        : events of the repository webhook
     * @param active        : whether the organization webhook is active
     * @param config        : config of the repository webhook
     * @param updatedAt     : update date of the repository webhook
     * @param createdAt     : creation date of the repository webhook
     * @param type          : type of the repository webhook
     * @param testUrl       : the test url of the repository webhook
     * @param lastResponse: the last response of the repository webhook
     **/
    public RepositoryWebhook(long id, String name, String url, String pingUrl, String deliveriesUrl,
                             ArrayList<String> events, boolean active, Webhook config, String updatedAt,
                             String createdAt, String type, String testUrl, LastResponse lastResponse) {
        super(id, name, url, pingUrl, deliveriesUrl, events, active, config, updatedAt, createdAt, type);
        this.testUrl = testUrl;
        this.lastResponse = lastResponse;
    }

    /**
     * Constructor to init a {@link RepositoryWebhook}
     *
     * @param jRepositoryWebhook : repository webhook details as {@link JSONObject}
     **/
    public RepositoryWebhook(JSONObject jRepositoryWebhook) {
        super(jRepositoryWebhook);
        testUrl = hResponse.getString("test_url");
        lastResponse = new LastResponse(hResponse.getJSONObject("last_response"));
    }

    /**
     * Method to get {@link #testUrl} instance <br>
     * No-any params required
     *
     * @return {@link #testUrl} instance as {@link String}
     **/
    public String getTestUrl() {
        return testUrl;
    }

    /**
     * Method to get {@link #lastResponse} instance <br>
     * No-any params required
     *
     * @return {@link #lastResponse} instance as {@link LastResponse}
     **/
    public LastResponse getLastResponse() {
        return lastResponse;
    }

    /**
     * The {@code LastResponse} class is useful to format a GitHub's last response for a {@link RepositoryWebhook}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class LastResponse extends InnerClassItem {

        /**
         * {@code code} the code of the last response
         **/
        private final int code;

        /**
         * {@code status} the status of the last response
         **/
        private final String status;

        /**
         * {@code message} the message of the last response
         **/
        private final String message;

        /**
         * Constructor to init a {@link LastResponse}
         *
         * @param code    : the code of the last response
         * @param status  : the status of the last response
         * @param message : the message of the last response
         **/
        public LastResponse(int code, String status, String message) {
            super(null);
            this.code = code;
            this.status = status;
            this.message = message;
        }

        /**
         * Constructor to init a {@link LastResponse}
         *
         * @param jLastResponse : last response details as {@link JSONObject}
         **/
        public LastResponse(JSONObject jLastResponse) {
            super(jLastResponse);
            code = hItem.getInt("code");
            status = hItem.getString("status");
            message = hItem.getString("message");
        }

        /**
         * Method to get {@link #code} instance <br>
         * No-any params required
         *
         * @return {@link #code} instance as int
         **/
        public int getCode() {
            return code;
        }

        /**
         * Method to get {@link #status} instance <br>
         * No-any params required
         *
         * @return {@link #status} instance as {@link String}
         **/
        public String getStatus() {
            return status;
        }

        /**
         * Method to get {@link #message} instance <br>
         * No-any params required
         *
         * @return {@link #message} instance as {@link String}
         **/
        public String getMessage() {
            return message;
        }

    }

}
