package com.tecknobit.githubmanager.apps.webhooks.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.HashMap;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Delivery} class is useful to format a GitHub's delivery
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/webhooks#list-deliveries-for-an-app-webhook">
 *             List deliveries for an app webhook</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-delivery-for-an-app-webhook">
 *             Get a delivery for an app webhook</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Delivery extends GitHubResponse {

    /**
     * {@code id} the unique identifier of the delivery
     **/
    private final long id;

    /**
     * {@code guid} the unique identifier for the event (shared with all deliveries for all webhooks that subscribe to this event)
     **/
    private final String guid;

    /**
     * {@code deliveredAt} time when the delivery was delivered
     **/
    private final String deliveredAt;

    /**
     * {@code redelivery} whether the delivery is a redelivery
     **/
    private final boolean redelivery;

    /**
     * {@code duration} time spent delivering
     **/
    private final double duration;

    /**
     * {@code status} description of the status of the attempted delivery
     **/
    private final String status;

    /**
     * {@code statusCode} status code received when delivery was made
     **/
    private final int statusCode;

    /**
     * {@code event} the event that triggered the delivery
     **/
    private final String event;

    /**
     * {@code action} the type of activity for the event that triggered the delivery
     **/
    private final String action;

    /**
     * {@code installationId} the id of the GitHub App installation associated with this event
     **/
    private final long installationId;

    /**
     * {@code repositoryId} the id of the repository associated with this event
     **/
    private final long repositoryId;

    /**
     * {@code url} the URL target of the delivery
     **/
    private final String url;

    /**
     * {@code request} the request of the webhook delivery
     **/
    private final Request request;

    /**
     * {@code response} the response of the webhook delivery
     **/
    private final Request response;

    /**
     * Constructor to init a {@link Delivery}
     *
     * @param id             : the unique identifier of the delivery
     * @param guid           : the unique identifier for the event (shared with all deliveries for all webhooks that subscribe to this event)
     * @param deliveredAt    : time when the delivery was delivered
     * @param redelivery     : whether the delivery is a redelivery
     * @param duration       : time spent delivering
     * @param status         : description of the status of the attempted delivery
     * @param statusCode     : status code received when delivery was made
     * @param event          : the event that triggered the delivery
     * @param action         : the type of activity for the event that triggered the delivery
     * @param installationId : the id of the GitHub App installation associated with this event
     * @param repositoryId   : the id of the repository associated with this event
     * @param url            : the URL target of the delivery
     * @param request        : the request of the webhook delivery
     * @param response       : the response of the webhook delivery
     **/
    public Delivery(long id, String guid, String deliveredAt, boolean redelivery, double duration, String status,
                    int statusCode, String event, String action, long installationId, long repositoryId, String url,
                    Request request, Request response) {
        super(null);
        this.id = id;
        this.guid = guid;
        this.deliveredAt = deliveredAt;
        this.redelivery = redelivery;
        this.duration = duration;
        this.status = status;
        this.statusCode = statusCode;
        this.event = event;
        this.action = action;
        this.installationId = installationId;
        this.repositoryId = repositoryId;
        this.url = url;
        this.request = request;
        this.response = response;
    }

    /**
     * Constructor to init a {@link Delivery}
     *
     * @param jDelivery : delivery details as {@link JSONObject}
     **/
    public Delivery(JSONObject jDelivery) {
        super(jDelivery);
        id = hResponse.getLong("id", 0);
        guid = hResponse.getString("guid");
        deliveredAt = hResponse.getString("delivered_at");
        redelivery = hResponse.getBoolean("redelivery");
        duration = hResponse.getDouble("duration");
        status = hResponse.getString("status");
        statusCode = hResponse.getInt("status_code", 0);
        event = hResponse.getString("event");
        action = hResponse.getString("action");
        installationId = hResponse.getLong("installation_id", 0);
        repositoryId = hResponse.getLong("repository_id", 0);
        url = hResponse.getString("url");
        request = new Request(hResponse.getJSONObject("request", new JSONObject()));
        response = new Request(hResponse.getJSONObject("response", new JSONObject()));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #guid} instance <br>
     * No-any params required
     *
     * @return {@link #guid} instance as {@link String}
     **/
    public String getGuid() {
        return guid;
    }

    /**
     * Method to get {@link #deliveredAt} instance <br>
     * No-any params required
     *
     * @return {@link #deliveredAt} instance as {@link String}
     **/
    public String getDeliveredAt() {
        return deliveredAt;
    }

    /**
     * Method to get {@link #deliveredAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #deliveredAt} timestamp as long
     **/
    public long getDeliveredAtTimestamp() {
        return getDateTimestamp(deliveredAt);
    }

    /**
     * Method to get {@link #redelivery} instance <br>
     * No-any params required
     *
     * @return {@link #redelivery} instance as boolean
     **/
    public boolean isRedelivery() {
        return redelivery;
    }

    /**
     * Method to get {@link #duration} instance <br>
     * No-any params required
     *
     * @return {@link #duration} instance as boolean
     **/
    public double getDuration() {
        return duration;
    }

    /**
     * Method to get {@link #duration} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #duration} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getDuration(int decimals) {
        return roundValue(duration, decimals);
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
     * Method to get {@link #statusCode} instance <br>
     * No-any params required
     *
     * @return {@link #statusCode} instance as int
     **/
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Method to get {@link #event} instance <br>
     * No-any params required
     *
     * @return {@link #event} instance as {@link String}
     **/
    public String getEvent() {
        return event;
    }

    /**
     * Method to get {@link #action} instance <br>
     * No-any params required
     *
     * @return {@link #action} instance as {@link String}
     **/
    public String getAction() {
        return action;
    }

    /**
     * Method to get {@link #installationId} instance <br>
     * No-any params required
     *
     * @return {@link #installationId} instance as long
     **/
    public long getInstallationId() {
        return installationId;
    }

    /**
     * Method to get {@link #repositoryId} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryId} instance as long
     **/
    public long getRepositoryId() {
        return repositoryId;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #request} instance <br>
     * No-any params required
     *
     * @return {@link #request} instance as {@link Request}
     **/
    public Request getRequest() {
        return request;
    }

    /**
     * Method to get {@link #response} instance <br>
     * No-any params required
     *
     * @return {@link #response} instance as {@link Request}
     **/
    public Request getResponse() {
        return response;
    }

    /**
     * The {@code Request} class is useful to format a GitHub's request for a delivery
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Request extends InnerClassItem {

        /**
         * {@code headers} the response headers received when the delivery was made
         **/
        private final HashMap<String, String> headers;

        /**
         * {@code payload} the response payload received
         **/
        private final String payload;

        /**
         * Constructor to init a {@link Request}
         *
         * @param headers : the response headers received when the delivery was made
         * @param payload : the response payload received as {@link JSONObject}
         **/
        public Request(HashMap<String, String> headers, JSONObject payload) {
            this(headers, payload.toString());
        }

        /**
         * Constructor to init a {@link Request}
         *
         * @param headers : the response headers received when the delivery was made
         * @param payload : the response payload received
         **/
        public Request(HashMap<String, String> headers, String payload) {
            super(null);
            this.headers = headers;
            this.payload = payload;
        }

        /**
         * Constructor to init a {@link Request}
         *
         * @param jRequest : request details as {@link JSONObject}
         **/
        public Request(JSONObject jRequest) {
            super(jRequest);
            headers = new HashMap<>();
            JSONObject jHeaders = hItem.getJSONObject("headers", new JSONObject());
            for (String key : jHeaders.keySet())
                headers.put(key, jHeaders.getString(key));
            String tmpPayload;
            try {
                tmpPayload = hItem.getJSONObject("payload", null).toString();
            } catch (Exception e) {
                tmpPayload = hItem.getString("payload");
            }
            payload = tmpPayload;
        }

        /**
         * Method to get {@link #headers} instance <br>
         * No-any params required
         *
         * @return {@link #headers} instance as {@link HashMap} of <{@link String}, {@link String}>
         **/
        public HashMap<String, String> getHeaders() {
            return headers;
        }

        /**
         * Method to get {@link #payload} instance <br>
         * No-any params required
         *
         * @return {@link #payload} instance as {@link String}
         **/
        public String getPayload() {
            return payload;
        }

        /**
         * Method to get {@link #payload} instance <br>
         * No-any params required
         *
         * @return {@link #payload} instance as {@link JSONObject}
         **/
        public JSONObject getJSONPayload() {
            return new JSONObject(payload);
        }

    }

}
