package com.tecknobit.githubmanager.apps.webhooks;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.webhooks.records.Delivery;
import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.apps.GitHubAppsManager.APP_PATH;

/**
 * The {@code GitHubWebhooksManager} class is useful to manage all GitHub's webhooks endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks">
 * GitHub App webhooks</a>
 * @see GitHubManager
 **/
public class GitHubWebhooksManager extends GitHubManager {

    /**
     * {@code HOOK_PATH} constant for {@code "/hook/"} path
     **/
    public static final String HOOK_PATH = "/hook/";

    /**
     * {@code APP_HOOK_CONFIG_PATH} constant for {@code "/app/hook/config"} path
     **/
    public static final String APP_HOOK_CONFIG_PATH = APP_PATH + HOOK_PATH + "config";

    /**
     * {@code APP_HOOK_DELIVERIES_PATH} constant for {@code "/app/hook/deliveries"} path
     **/
    public static final String APP_HOOK_DELIVERIES_PATH = APP_PATH + HOOK_PATH + "deliveries";

    /**
     * {@code ATTEMPTS_QUERY_PATH} constant for {@code "/attempts"} path
     **/
    public static final String ATTEMPTS_QUERY_PATH = "/attempts";

    /**
     * Constructor to init a {@link GitHubWebhooksManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubWebhooksManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubWebhooksManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubWebhooksManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubWebhooksManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubWebhooksManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubWebhooksManager} <br>
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
    public GitHubWebhooksManager() {
        super();
    }

    /**
     * Method to return the webhook configuration for a GitHub App.
     * For more information about configuring a webhook for your app, see "Creating a GitHub App." <br>
     * You must use a JWT to access this endpoint <br>
     * Any params required
     *
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-webhook-configuration-for-an-app">
     * Get a webhook configuration for an app</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/hook/config")
    public Webhook getAppWebhookConfiguration() throws IOException {
        return getAppWebhookConfiguration(LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for a GitHub App.
     * For more information about configuring a webhook for your app, see "Creating a GitHub App." <br>
     * You must use a JWT to access this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-webhook-configuration-for-an-app">
     * Get a webhook configuration for an app</a>
     **/
    @RequestPath(method = GET, path = "/app/hook/config")
    public <T> T getAppWebhookConfiguration(ReturnFormat format) throws IOException {
        return returnWebhook(sendGetRequest(APP_HOOK_CONFIG_PATH), format);
    }

    /**
     * Method to update the webhook configuration for a GitHub App.
     * For more information about configuring a webhook for your app, see "Creating a GitHub App." <br>
     * You must use a JWT to access this endpoint
     *
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#update-a-webhook-configuration-for-an-app">
     * Update a webhook configuration for an app</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/app/hook/config")
    public Webhook updateAppWebhookConfiguration(Params bodyParams) throws IOException {
        return updateAppWebhookConfiguration(bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the webhook configuration for a GitHub App.
     * For more information about configuring a webhook for your app, see "Creating a GitHub App." <br>
     * You must use a JWT to access this endpoint
     *
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#update-a-webhook-configuration-for-an-app">
     * Update a webhook configuration for an app</a>
     **/
    @RequestPath(method = PATCH, path = "/app/hook/config")
    public <T> T updateAppWebhookConfiguration(Params bodyParams, ReturnFormat format) throws IOException {
        return returnWebhook(sendPatchRequest(APP_HOOK_CONFIG_PATH, bodyParams), format);
    }

    /**
     * Method to create a webhook
     *
     * @param webhookResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     **/
    @Returner
    private <T> T returnWebhook(String webhookResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(webhookResponse);
            case LIBRARY_OBJECT:
                return (T) new Webhook(new JSONObject(webhookResponse));
            default:
                return (T) webhookResponse;
        }
    }

    /**
     * Method to return a list of webhook deliveries for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint <br>
     * Any params required
     *
     * @return deliveries list as {@link Collection} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#list-deliveries-for-an-app-webhook">
     * List deliveries for an app webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/hook/deliveries")
    public Collection<Delivery> getAppWebhookDeliveries() throws IOException {
        return getAppWebhookDeliveries(LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return deliveries list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#list-deliveries-for-an-app-webhook">
     * List deliveries for an app webhook</a>
     **/
    @RequestPath(method = GET, path = "/app/hook/deliveries")
    public <T> T getAppWebhookDeliveries(ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(APP_HOOK_DELIVERIES_PATH), format);
    }

    /**
     * Method to return a list of webhook deliveries for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "cursor"} -> used for pagination: the starting delivery from which the page of
     *                            deliveries is fetched. Refer to the link header for the next and previous page cursors
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "redelivery"} -> whether include the redeliveries - [boolean]
     *                        </li>
     *                     </ul>
     * @return deliveries list as {@link Collection} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#list-deliveries-for-an-app-webhook">
     * List deliveries for an app webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/hook/deliveries")
    public Collection<Delivery> getAppWebhookDeliveries(Params queryParams) throws IOException {
        return getAppWebhookDeliveries(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "cursor"} -> used for pagination: the starting delivery from which the page of
     *                            deliveries is fetched. Refer to the link header for the next and previous page cursors
     *                            - [string]
     *                        </li>
     *                        <li>
     *                            {@code "redelivery"} -> whether include the redeliveries - [boolean]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return deliveries list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#list-deliveries-for-an-app-webhook">
     * List deliveries for an app webhook</a>
     **/
    @RequestPath(method = GET, path = "/app/hook/deliveries")
    public <T> T getAppWebhookDeliveries(Params queryParams, ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(APP_HOOK_DELIVERIES_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a deliveries list
     *
     * @param deliveriesResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return deliveries list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDeliveriesList(String deliveriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(deliveriesResponse);
            case LIBRARY_OBJECT:
                ArrayList<Delivery> deliveries = new ArrayList<>();
                JSONArray jDeliveries = new JSONArray(deliveriesResponse);
                for (int j = 0; j < jDeliveries.length(); j++)
                    deliveries.add(new Delivery(jDeliveries.getJSONObject(j)));
                return (T) deliveries;
            default:
                return (T) deliveriesResponse;
        }
    }

    /**
     * Method to return a delivery for the webhook configured for a GitHub App <br>
     * You must use a JWT to access this endpoint
     *
     * @param deliveryId: delivery identifier
     * @return delivery as {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-delivery-for-an-app-webhook">
     * Get a delivery for an app webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/app/hook/deliveries/{delivery_id}")
    public Delivery getAppWebhookDelivery(long deliveryId) throws IOException {
        return getAppWebhookDelivery(deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for the webhook configured for a GitHub App <br>
     * You must use a JWT to access this endpoint
     *
     * @param deliveryId: delivery identifier
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return delivery as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-delivery-for-an-app-webhook">
     * Get a delivery for an app webhook</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/app/hook/deliveries/{delivery_id}")
    public <T> T getAppWebhookDelivery(long deliveryId, ReturnFormat format) throws IOException {
        String deliveryResponse = sendGetRequest(APP_HOOK_DELIVERIES_PATH + "/" + deliveryId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(deliveryResponse);
            case LIBRARY_OBJECT:
                return (T) new Delivery(new JSONObject(deliveryResponse));
            default:
                return (T) deliveryResponse;
        }
    }

    /**
     * Method to redeliver a delivery for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint
     *
     * @param delivery: delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for an app webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/app/hook/deliveries/{delivery_id}/attempts")
    public boolean redeliverAppWebhookDelivery(Delivery delivery) {
        return redeliverAppWebhookDelivery(delivery.getId());
    }

    /**
     * Method to redeliver a delivery for the webhook configured for a GitHub App. <br>
     * You must use a JWT to access this endpoint
     *
     * @param deliveryId: delivery identifier
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for an app webhook</a>
     **/
    @RequestPath(method = POST, path = "/app/hook/deliveries/{delivery_id}/attempts")
    public boolean redeliverAppWebhookDelivery(long deliveryId) {
        try {
            sendPostRequest(APP_HOOK_DELIVERIES_PATH + "/" + deliveryId + ATTEMPTS_QUERY_PATH, null);
            if (apiRequest.getResponseStatusCode() != 202) {
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
