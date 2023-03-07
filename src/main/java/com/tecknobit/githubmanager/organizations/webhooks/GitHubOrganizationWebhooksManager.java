package com.tecknobit.githubmanager.organizations.webhooks;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.webhooks.records.Delivery;
import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.organizations.webhooks.records.OrganizationWebhook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.webhooks.GitHubWebhooksManager.ATTEMPTS_QUERY_PATH;
import static com.tecknobit.githubmanager.apps.webhooks.records.Delivery.returnDeliveriesList;
import static com.tecknobit.githubmanager.apps.webhooks.records.Delivery.returnDelivery;
import static com.tecknobit.githubmanager.apps.webhooks.records.Webhook.returnWebhook;

/**
 * The {@code GitHubOrganizationWebhooksManager} class is useful to manage all GitHub's security managers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks">
 * Organization webhooks</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationWebhooksManager extends GitHubManager {

    /**
     * {@code HOOKS_PATH} constant for {@code "/hooks"} path
     **/
    public static final String HOOKS_PATH = "/hooks";

    /**
     * {@code CONFIG_PATH} constant for {@code "/config"} path
     **/
    public static final String CONFIG_PATH = "/config";

    /**
     * {@code DELIVERIES_PATH} constant for {@code "/deliveries"} path
     **/
    public static final String DELIVERIES_PATH = "/deliveries";

    /**
     * {@code PINGS_PATH} constant for {@code "/pings"} path
     **/
    public static final String PINGS_PATH = "/pings";

    /**
     * Constructor to init a {@link GitHubOrganizationWebhooksManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationWebhooksManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationWebhooksManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationWebhooksManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationWebhooksManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationWebhooksManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationWebhooksManager} <br>
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
    public GitHubOrganizationWebhooksManager() {
        super();
    }

    /**
     * Method to get the list of the organization webhooks
     *
     * @param org: the organization from fetch the list
     * @return organization webhooks list as {@link ArrayList} of {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public ArrayList<OrganizationWebhook> getOrganizationWebhooks(Organization org) throws IOException {
        return getOrganizationWebhooks(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization webhooks
     *
     * @param org:    the organization from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public <T> T getOrganizationWebhooks(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationWebhooks(org.getLogin(), format);
    }

    /**
     * Method to get the list of the organization webhooks
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return organization webhooks list as {@link ArrayList} of {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public ArrayList<OrganizationWebhook> getOrganizationWebhooks(String org) throws IOException {
        return getOrganizationWebhooks(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization webhooks
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public <T> T getOrganizationWebhooks(String org, ReturnFormat format) throws IOException {
        return returnOrganizationWebhooks(sendGetRequest(ORGS_PATH + org + HOOKS_PATH), format);
    }

    /**
     * Method to get the list of the organization webhooks
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
     * @return organization webhooks list as {@link ArrayList} of {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public ArrayList<OrganizationWebhook> getOrganizationWebhooks(Organization org, Params queryParams) throws IOException {
        return getOrganizationWebhooks(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization webhooks
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
     * @return organization webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public <T> T getOrganizationWebhooks(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationWebhooks(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the organization webhooks
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
     * @return organization webhooks list as {@link ArrayList} of {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public ArrayList<OrganizationWebhook> getOrganizationWebhooks(String org, Params queryParams) throws IOException {
        return getOrganizationWebhooks(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the organization webhooks
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
     * @return organization webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
     * List organization webhooks</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks")
    public <T> T getOrganizationWebhooks(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationWebhooks(sendGetRequest(ORGS_PATH + org + HOOKS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an organization webhooks list
     *
     * @param organizationWebhooksResponse: obtained from GitHub's response
     * @param format:                       return type formatter -> {@link ReturnFormat}
     * @return organization webhooks list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationWebhooks(String organizationWebhooksResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationWebhooksResponse);
            case LIBRARY_OBJECT:
                ArrayList<OrganizationWebhook> organizationWebhooks = new ArrayList<>();
                JSONArray jOrganizationWebhooks = new JSONArray(organizationWebhooksResponse);
                for (int j = 0; j < jOrganizationWebhooks.length(); j++)
                    organizationWebhooks.add(new OrganizationWebhook(jOrganizationWebhooks.getJSONObject(j)));
                return (T) organizationWebhooks;
            default:
                return (T) organizationWebhooksResponse;
        }
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:    the organization where create the webhook
     * @param name:   must be passed as "web"
     * @param config: settings for this webhook
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public OrganizationWebhook createOrganizationWebhook(Organization org, String name, Webhook config) throws IOException {
        return createOrganizationWebhook(org.getLogin(), name, config, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:    the organization where create the webhook
     * @param name:   must be passed as "web"
     * @param config: settings for this webhook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public <T> T createOrganizationWebhook(Organization org, String name, Webhook config,
                                           ReturnFormat format) throws IOException {
        return createOrganizationWebhook(org.getLogin(), name, config, format);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   must be passed as "web"
     * @param config: settings for this webhook
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public OrganizationWebhook createOrganizationWebhook(String org, String name, Webhook config) throws IOException {
        return createOrganizationWebhook(org, name, config, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param name:   must be passed as "web"
     * @param config: settings for this webhook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public <T> T createOrganizationWebhook(String org, String name, Webhook config, ReturnFormat format) throws IOException {
        return createOrganizationWebhook(org, name, config, null, format);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:        the organization where create the webhook
     * @param name:       must be passed as "web"
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public OrganizationWebhook createOrganizationWebhook(Organization org, String name, Webhook config,
                                                         Params bodyParams) throws IOException {
        return createOrganizationWebhook(org.getLogin(), name, config, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:        the organization where create the webhook
     * @param name:       must be passed as "web"
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public <T> T createOrganizationWebhook(Organization org, String name, Webhook config, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return createOrganizationWebhook(org.getLogin(), name, config, bodyParams, format);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       must be passed as "web"
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public OrganizationWebhook createOrganizationWebhook(String org, String name, Webhook config,
                                                         Params bodyParams) throws IOException {
        return createOrganizationWebhook(org, name, config, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create an organization webhook
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param name:       must be passed as "web"
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
     * Create an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks")
    public <T> T createOrganizationWebhook(String org, String name, Webhook config, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        bodyParams.addParam("config", config);
        return returnOrganizationWebhook(sendPostRequest(ORGS_PATH + org + HOOKS_PATH, bodyParams), format);
    }

    /**
     * Method to return a webhook configured in an organization. To get only the webhook config properties, see "Get a
     * webhook configuration for an organization."
     *
     * @param org:    the organization from fetch the webhook
     * @param hookId: the unique identifier of the hook
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-an-organization-webhook">
     * Get an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook getOrganizationWebhook(Organization org, long hookId) throws IOException {
        return getOrganizationWebhook(org.getLogin(), hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a webhook configured in an organization. To get only the webhook config properties, see "Get a
     * webhook configuration for an organization."
     *
     * @param org:    the organization from fetch the webhook
     * @param hookId: the unique identifier of the hook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-an-organization-webhook">
     * Get an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T getOrganizationWebhook(Organization org, long hookId, ReturnFormat format) throws IOException {
        return getOrganizationWebhook(org.getLogin(), hookId, format);
    }

    /**
     * Method to return a webhook configured in an organization. To get only the webhook config properties, see "Get a
     * webhook configuration for an organization."
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-an-organization-webhook">
     * Get an organization webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook getOrganizationWebhook(String org, long hookId) throws IOException {
        return getOrganizationWebhook(org, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a webhook configured in an organization. To get only the webhook config properties, see "Get a
     * webhook configuration for an organization."
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-an-organization-webhook">
     * Get an organization webhook</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T getOrganizationWebhook(String org, long hookId, ReturnFormat format) throws IOException {
        return returnOrganizationWebhook(sendGetRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId), format);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization where update the webhook
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook updateOrganizationWebhook(Organization org, OrganizationWebhook hook,
                                                         Params bodyParams) throws IOException {
        return updateOrganizationWebhook(org.getLogin(), hook.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization where update the webhook
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T updateOrganizationWebhook(Organization org, OrganizationWebhook hook, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateOrganizationWebhook(org.getLogin(), hook.getId(), bodyParams, format);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook updateOrganizationWebhook(String org, OrganizationWebhook hook,
                                                         Params bodyParams) throws IOException {
        return updateOrganizationWebhook(org, hook.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T updateOrganizationWebhook(String org, OrganizationWebhook hook, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateOrganizationWebhook(org, hook.getId(), bodyParams, format);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization where update the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook updateOrganizationWebhook(Organization org, long hookId, Params bodyParams) throws IOException {
        return updateOrganizationWebhook(org.getLogin(), hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization where update the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T updateOrganizationWebhook(Organization org, long hookId, Params bodyParams,
                                           ReturnFormat format) throws IOException {
        return updateOrganizationWebhook(org.getLogin(), hookId, bodyParams, format);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return organization webhook as {@link OrganizationWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public OrganizationWebhook updateOrganizationWebhook(String org, long hookId, Params bodyParams) throws IOException {
        return updateOrganizationWebhook(org, hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in an organization. When you update a webhook, the secret will be overwritten.
     * If you previously had a secret set, you must provide the same secret or set a new secret or the secret will be
     * removed. If you are only updating individual webhook config properties, use "Update a webhook configuration for
     * an organization."
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> settings for this webhook, you can use the {@link Webhook}
     *                           custom object and it will be automatically formatted for the request - [object]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
     * Update an organization webhook</a>
     **/
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}")
    public <T> T updateOrganizationWebhook(String org, long hookId, Params bodyParams, ReturnFormat format) throws IOException {
        return returnOrganizationWebhook(sendPatchRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId,
                bodyParams), format);
    }

    /**
     * Method to create an organization webhook
     *
     * @param organizationWebhookResponse: obtained from GitHub's response
     * @param format:                      return type formatter -> {@link ReturnFormat}
     * @return organization webhook as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationWebhook(String organizationWebhookResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationWebhookResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationWebhook(new JSONObject(organizationWebhookResponse));
            default:
                return (T) organizationWebhookResponse;
        }
    }

    /**
     * Method to delete an organization webhook
     *
     * @param org:  the organization where delete the webhook
     * @param hook: the hook to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#delete-an-organization-webhook">
     * Delete an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/hooks/{hook_id}")
    public boolean deleteOrganizationWebhook(Organization org, OrganizationWebhook hook) {
        return deleteOrganizationWebhook(org.getLogin(), hook.getId());
    }

    /**
     * Method to delete an organization webhook
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param hook: the hook to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#delete-an-organization-webhook">
     * Delete an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/hooks/{hook_id}")
    public boolean deleteOrganizationWebhook(String org, OrganizationWebhook hook) {
        return deleteOrganizationWebhook(org, hook.getId());
    }

    /**
     * Method to delete an organization webhook
     *
     * @param org:    the organization where delete the webhook
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#delete-an-organization-webhook">
     * Delete an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/hooks/{hook_id}")
    public boolean deleteOrganizationWebhook(Organization org, long hookId) {
        return deleteOrganizationWebhook(org.getLogin(), hookId);
    }

    /**
     * Method to delete an organization webhook
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#delete-an-organization-webhook">
     * Delete an organization webhook</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/hooks/{hook_id}")
    public boolean deleteOrganizationWebhook(String org, long hookId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId);
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
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:  the organization from fetch the webhook configuration
     * @param hook: the hook from fetch the configuration
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook getOrganizationWebhookConfiguration(Organization org, OrganizationWebhook hook) throws IOException {
        return getOrganizationWebhookConfiguration(org.getLogin(), hook.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization from fetch the webhook configuration
     * @param hook:   the hook from fetch the configuration
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T getOrganizationWebhookConfiguration(Organization org, OrganizationWebhook hook,
                                                     ReturnFormat format) throws IOException {
        return getOrganizationWebhookConfiguration(org.getLogin(), hook.getId(), format);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param hook: the hook from fetch the configuration
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook getOrganizationWebhookConfiguration(String org, OrganizationWebhook hook) throws IOException {
        return getOrganizationWebhookConfiguration(org, hook.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hook:   the hook from fetch the configuration
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T getOrganizationWebhookConfiguration(String org, OrganizationWebhook hook,
                                                     ReturnFormat format) throws IOException {
        return getOrganizationWebhookConfiguration(org, hook.getId(), format);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization from fetch the webhook configuration
     * @param hookId: the unique identifier of the hook
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook getOrganizationWebhookConfiguration(Organization org, long hookId) throws IOException {
        return getOrganizationWebhookConfiguration(org.getLogin(), hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization from fetch the webhook configuration
     * @param hookId: the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T getOrganizationWebhookConfiguration(Organization org, long hookId, ReturnFormat format) throws IOException {
        return getOrganizationWebhookConfiguration(org.getLogin(), hookId, format);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook getOrganizationWebhookConfiguration(String org, long hookId) throws IOException {
        return getOrganizationWebhookConfiguration(org, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
     * Get a webhook configuration for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T getOrganizationWebhookConfiguration(String org, long hookId, ReturnFormat format) throws IOException {
        return returnWebhook(sendGetRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + CONFIG_PATH), format);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization where update the webhook configuration
     * @param hook:       the hook where update the webhook configuration
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
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook updateOrganizationWebhookConfiguration(Organization org, OrganizationWebhook hook,
                                                          Params bodyParams) throws IOException {
        return updateOrganizationWebhookConfiguration(org.getLogin(), hook.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization where update the webhook configuration
     * @param hook:       the hook where update the webhook configuration
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T updateOrganizationWebhookConfiguration(Organization org, OrganizationWebhook hook, Params bodyParams,
                                                        ReturnFormat format) throws IOException {
        return updateOrganizationWebhookConfiguration(org.getLogin(), hook.getId(), bodyParams, format);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook where update the webhook configuration
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
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook updateOrganizationWebhookConfiguration(String org, OrganizationWebhook hook,
                                                          Params bodyParams) throws IOException {
        return updateOrganizationWebhookConfiguration(org, hook.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook where update the webhook configuration
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T updateOrganizationWebhookConfiguration(String org, OrganizationWebhook hook, Params bodyParams,
                                                        ReturnFormat format) throws IOException {
        return updateOrganizationWebhookConfiguration(org, hook.getId(), bodyParams, format);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization where update the webhook configuration
     * @param hookId:     the unique identifier of the hook
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
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook updateOrganizationWebhookConfiguration(Organization org, long hookId, Params bodyParams) throws IOException {
        return updateOrganizationWebhookConfiguration(org.getLogin(), hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization where update the webhook configuration
     * @param hookId:     the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T updateOrganizationWebhookConfiguration(Organization org, long hookId, Params bodyParams,
                                                        ReturnFormat format) throws IOException {
        return updateOrganizationWebhookConfiguration(org.getLogin(), hookId, bodyParams, format);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
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
     * @return webhook configuration as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public Webhook updateOrganizationWebhookConfiguration(String org, long hookId, Params bodyParams) throws IOException {
        return updateOrganizationWebhookConfiguration(org, hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update the webhook configuration for an organization. To get more information about the webhook, including
     * the active state and events, use "Get an organization webhook ."
     * Access tokens must have the {@code "admin:org_hook"} scope, and GitHub Apps must have the
     * {@code "organization_hooks:read"} permission
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
     * Update a webhook configuration for an organization</a>
     **/
    @RequestPath(method = PATCH, path = "/orgs/{org}/hooks/{hook_id}/config")
    public <T> T updateOrganizationWebhookConfiguration(String org, long hookId, Params bodyParams,
                                                        ReturnFormat format) throws IOException {
        return returnWebhook(sendPatchRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + CONFIG_PATH,
                bodyParams), format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:  the organization from fetch the list
     * @param hook: the hook where fetch the list
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(Organization org, OrganizationWebhook hook) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hook.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization from fetch the list
     * @param hook:   the hook where fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(Organization org, OrganizationWebhook hook,
                                                  ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hook.getId(), format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param hook: the hook where fetch the list
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(String org, OrganizationWebhook hook) throws IOException {
        return getOrganizationWebhookDeliveries(org, hook.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hook:   the hook where fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(String org, OrganizationWebhook hook, ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org, hook.getId(), format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization from fetch the list
     * @param hookId: the unique identifier of the hook
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(Organization org, long hookId) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization from fetch the list
     * @param hookId: the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(Organization org, long hookId, ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hookId, format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(String org, long hookId) throws IOException {
        return getOrganizationWebhookDeliveries(org, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(String org, long hookId, ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + DELIVERIES_PATH),
                format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization from fetch the list
     * @param hook:        the hook where fetch the list
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
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(Organization org, OrganizationWebhook hook,
                                                                Params queryParams) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hook.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization from fetch the list
     * @param hook:        the hook where fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(Organization org, OrganizationWebhook hook, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hook.getId(), queryParams, format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param hook:        the hook where fetch the list
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
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(String org, OrganizationWebhook hook,
                                                                Params queryParams) throws IOException {
        return getOrganizationWebhookDeliveries(org, hook.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param hook:        the hook where fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(String org, OrganizationWebhook hook, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org, hook.getId(), queryParams, format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization from fetch the list
     * @param hookId:      the unique identifier of the hook
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
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(Organization org, long hookId,
                                                                Params queryParams) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hookId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization from fetch the list
     * @param hookId:      the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(Organization org, long hookId, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return getOrganizationWebhookDeliveries(org.getLogin(), hookId, queryParams, format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param hookId:      the unique identifier of the hook
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
     * @return deliveries list as {@link ArrayList} of {@link Delivery} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getOrganizationWebhookDeliveries(String org, long hookId, Params queryParams) throws IOException {
        return getOrganizationWebhookDeliveries(org, hookId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in an organization
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param hookId:      the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#list-deliveries-for-an-organization-webhook">
     * List deliveries for an organization webhook</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries")
    public <T> T getOrganizationWebhookDeliveries(String org, long hookId, Params queryParams,
                                                  ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + DELIVERIES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization from fetch the delivery
     * @param hook:       the hook where fetch the delivery
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getOrganizationWebhookDelivery(Organization org, OrganizationWebhook hook,
                                                   long deliveryId) throws IOException {
        return getOrganizationWebhookDelivery(org.getLogin(), hook.getId(), deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization from fetch the delivery
     * @param hook:       the hook where fetch the delivery
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getOrganizationWebhookDelivery(Organization org, OrganizationWebhook hook, long deliveryId,
                                                ReturnFormat format) throws IOException {
        return getOrganizationWebhookDelivery(org.getLogin(), hook.getId(), deliveryId, format);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook where fetch the delivery
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getOrganizationWebhookDelivery(String org, OrganizationWebhook hook, long deliveryId) throws IOException {
        return getOrganizationWebhookDelivery(org, hook.getId(), deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook where fetch the delivery
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getOrganizationWebhookDelivery(String org, OrganizationWebhook hook, long deliveryId,
                                                ReturnFormat format) throws IOException {
        return getOrganizationWebhookDelivery(org, hook.getId(), deliveryId, format);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization from fetch the delivery
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getOrganizationWebhookDelivery(Organization org, long hookId, long deliveryId) throws IOException {
        return getOrganizationWebhookDelivery(org.getLogin(), hookId, deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization from fetch the delivery
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getOrganizationWebhookDelivery(Organization org, long hookId, long deliveryId,
                                                ReturnFormat format) throws IOException {
        return getOrganizationWebhookDelivery(org.getLogin(), hookId, deliveryId, format);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getOrganizationWebhookDelivery(String org, long hookId, long deliveryId) throws IOException {
        return getOrganizationWebhookDelivery(org, hookId, deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to fetch
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-delivery-for-an-organization-webhook">
     * Get a webhook delivery for an organization webhook</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getOrganizationWebhookDelivery(String org, long hookId, long deliveryId,
                                                ReturnFormat format) throws IOException {
        return returnDelivery(sendGetRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + DELIVERIES_PATH
                + "/" + deliveryId), format);
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:      the organization from redeliver the delivery
     * @param hook:     the hook from redeliver the delivery
     * @param delivery: the delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(Organization org, OrganizationWebhook hook, Delivery delivery) {
        return redeliverOrganizationWebhookDelivery(org.getLogin(), hook.getId(), delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:      the organization from redeliver the delivery
     * @param hookId:   the unique identifier of the hook
     * @param delivery: the delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(Organization org, long hookId, Delivery delivery) {
        return redeliverOrganizationWebhookDelivery(org.getLogin(), hookId, delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param hookId:   the unique identifier of the hook
     * @param delivery: the delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(String org, long hookId, Delivery delivery) {
        return redeliverOrganizationWebhookDelivery(org, hookId, delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:      the organization name. The name is not case-sensitive
     * @param hook:     the hook from redeliver the delivery
     * @param delivery: the delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(String org, OrganizationWebhook hook, Delivery delivery) {
        return redeliverOrganizationWebhookDelivery(org, hook.getId(), delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hook:       the hook from redeliver the delivery
     * @param deliveryId: the delivery id to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(String org, OrganizationWebhook hook, long deliveryId) {
        return redeliverOrganizationWebhookDelivery(org, hook.getId(), deliveryId);
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:        the organization from redeliver the delivery
     * @param hook:       the hook from redeliver the delivery
     * @param deliveryId: the delivery id to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(Organization org, OrganizationWebhook hook, long deliveryId) {
        return redeliverOrganizationWebhookDelivery(org.getLogin(), hook.getId(), deliveryId);
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(Organization org, long hookId, long deliveryId) {
        return redeliverOrganizationWebhookDelivery(org.getLogin(), hookId, deliveryId);
    }

    /**
     * Method to redeliver a delivery for a webhook configured in an organization
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the delivery id to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#redeliver-a-delivery-for-an-organization-webhook">
     * Redeliver a delivery for an organization webhook</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverOrganizationWebhookDelivery(String org, long hookId, long deliveryId) {
        try {
            sendPostRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + DELIVERIES_PATH + "/" + deliveryId
                    + ATTEMPTS_QUERY_PATH, null);
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

    /**
     * Method to ping an organization webhook <br>
     * This will trigger a ping event to be sent to the hook.
     *
     * @param org:  the organization where ping the webhook
     * @param hook: the webhook to ping
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#ping-an-organization-webhook">
     * Ping an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/pings")
    public boolean pingOrganizationWebhook(Organization org, OrganizationWebhook hook) {
        return pingOrganizationWebhook(org.getLogin(), hook.getId());
    }

    /**
     * Method to ping an organization webhook <br>
     * This will trigger a ping event to be sent to the hook.
     *
     * @param org:  the organization name. The name is not case-sensitive
     * @param hook: the webhook to ping
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#ping-an-organization-webhook">
     * Ping an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/pings")
    public boolean pingOrganizationWebhook(String org, OrganizationWebhook hook) {
        return pingOrganizationWebhook(org, hook.getId());
    }

    /**
     * Method to ping an organization webhook <br>
     * This will trigger a ping event to be sent to the hook.
     *
     * @param org:    the organization where ping the webhook
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#ping-an-organization-webhook">
     * Ping an organization webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/pings")
    public boolean pingOrganizationWebhook(Organization org, long hookId) {
        return pingOrganizationWebhook(org.getLogin(), hookId);
    }

    /**
     * Method to ping an organization webhook <br>
     * This will trigger a ping event to be sent to the hook.
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/orgs/webhooks#ping-an-organization-webhook">
     * Ping an organization webhook</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/hooks/{hook_id}/pings")
    public boolean pingOrganizationWebhook(String org, long hookId) {
        try {
            sendPostRequest(ORGS_PATH + org + HOOKS_PATH + "/" + hookId + PINGS_PATH, null);
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
