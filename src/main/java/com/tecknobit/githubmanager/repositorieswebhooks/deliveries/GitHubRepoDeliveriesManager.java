package com.tecknobit.githubmanager.repositorieswebhooks.deliveries;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.webhooks.records.Delivery;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.webhooks.GitHubWebhooksManager.ATTEMPTS_QUERY_PATH;
import static com.tecknobit.githubmanager.apps.webhooks.records.Delivery.returnDeliveriesList;
import static com.tecknobit.githubmanager.apps.webhooks.records.Delivery.returnDelivery;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.DELIVERIES_PATH;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.HOOKS_PATH;

/**
 * The {@code GitHubRepoDeliveriesManager} class is useful to manage all GitHub's repository webhook deliveries endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries">
 * Repository Webhook Deliveries</a>
 * @see GitHubManager
 **/
public class GitHubRepoDeliveriesManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubRepoDeliveriesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepoDeliveriesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepoDeliveriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepoDeliveriesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepoDeliveriesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepoDeliveriesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoDeliveriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepoDeliveriesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoDeliveriesManager} <br>
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
    public GitHubRepoDeliveriesManager() {
        super();
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param repository: the repository from fetch the list
     * @param hookId:     the unique identifier of the hook
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getRepositoryWebhookDeliveries(Repository repository, long hookId) throws IOException {
        return getRepositoryWebhookDeliveries(repository.getOwner().getLogin(), repository.getName(), hookId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param repository: the repository from fetch the list
     * @param hookId:     the unique identifier of the hook
     * @param format:     return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public <T> T getRepositoryWebhookDeliveries(Repository repository, long hookId, ReturnFormat format) throws IOException {
        return getRepositoryWebhookDeliveries(repository.getOwner().getLogin(), repository.getName(), hookId, format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getRepositoryWebhookDeliveries(String owner, String repo, long hookId) throws IOException {
        return getRepositoryWebhookDeliveries(owner, repo, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public <T> T getRepositoryWebhookDeliveries(String owner, String repo, long hookId,
                                                ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/"
                + hookId + DELIVERIES_PATH), format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param repository:  the repository from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getRepositoryWebhookDeliveries(Repository repository, long hookId,
                                                              Params queryParams) throws IOException {
        return getRepositoryWebhookDeliveries(repository.getOwner().getLogin(), repository.getName(), hookId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param repository:  the repository from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public <T> T getRepositoryWebhookDeliveries(Repository repository, long hookId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return getRepositoryWebhookDeliveries(repository.getOwner().getLogin(), repository.getName(), hookId, queryParams,
                format);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public ArrayList<Delivery> getRepositoryWebhookDeliveries(String owner, String repo, long hookId,
                                                              Params queryParams) throws IOException {
        return getRepositoryWebhookDeliveries(owner, repo, hookId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return a list of webhook deliveries for a webhook configured in a repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#list-deliveries-for-a-repository-webhook">
     * List deliveries for a repository webhook</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries")
    public <T> T getRepositoryWebhookDeliveries(String owner, String repo, long hookId, Params queryParams,
                                                ReturnFormat format) throws IOException {
        return returnDeliveriesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/"
                + hookId + DELIVERIES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to return a delivery for a webhook configured in a repository
     *
     * @param repository: the repository from fetch the delivery
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#get-a-delivery-for-a-repository-webhook">
     * Get a delivery for a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getRepositoryWebhookDelivery(Repository repository, long hookId, long deliveryId) throws IOException {
        return getRepositoryWebhookDelivery(repository.getOwner().getLogin(), repository.getName(), hookId, deliveryId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in a repository
     *
     * @param repository: the repository from fetch the delivery
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#get-a-delivery-for-a-repository-webhook">
     * Get a delivery for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getRepositoryWebhookDelivery(Repository repository, long hookId, long deliveryId,
                                              ReturnFormat format) throws IOException {
        return getRepositoryWebhookDelivery(repository.getOwner().getLogin(), repository.getName(), hookId, deliveryId,
                format);
    }

    /**
     * Method to return a delivery for a webhook configured in a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#get-a-delivery-for-a-repository-webhook">
     * Get a delivery for a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}")
    public Delivery getRepositoryWebhookDelivery(String owner, String repo, long hookId, long deliveryId) throws IOException {
        return getRepositoryWebhookDelivery(owner, repo, hookId, deliveryId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a delivery for a webhook configured in a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-deliveries#get-a-delivery-for-a-repository-webhook">
     * Get a delivery for a repository webhook</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}")
    public <T> T getRepositoryWebhookDelivery(String owner, String repo, long hookId, long deliveryId,
                                              ReturnFormat format) throws IOException {
        return returnDelivery(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" + hookId
                + DELIVERIES_PATH + "/" + deliveryId), format);
    }

    /**
     * Method to redeliver a delivery for a repository webhook
     *
     * @param repository: the repository where redeliver the delivery
     * @param hookId:     the unique identifier of the hook
     * @param delivery:   delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverRepositoryWebhookDelivery(Repository repository, long hookId, Delivery delivery) {
        return redeliverRepositoryWebhookDelivery(repository.getOwner().getLogin(), repository.getName(), hookId,
                delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a repository webhook
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param hookId:   the unique identifier of the hook
     * @param delivery: delivery to redeliver
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverRepositoryWebhookDelivery(String owner, String repo, long hookId, Delivery delivery) {
        return redeliverRepositoryWebhookDelivery(owner, repo, hookId, delivery.getId());
    }

    /**
     * Method to redeliver a delivery for a repository webhook
     *
     * @param repository: the repository where redeliver the delivery
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverRepositoryWebhookDelivery(Repository repository, long hookId, long deliveryId) {
        return redeliverRepositoryWebhookDelivery(repository.getOwner().getLogin(), repository.getName(), hookId,
                deliveryId);
    }

    /**
     * Method to redeliver a delivery for a repository webhook
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param deliveryId: the unique identifier of the delivery
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/webhooks#redeliver-a-delivery-for-an-app-webhook">
     * Redeliver a delivery for a repository webhook</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/deliveries/{delivery_id}/attempts")
    public boolean redeliverRepositoryWebhookDelivery(String owner, String repo, long hookId, long deliveryId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" + hookId + DELIVERIES_PATH
                    + "/" + deliveryId + ATTEMPTS_QUERY_PATH, null);
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
