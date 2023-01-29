package com.tecknobit.githubmanager.apps.marketplace;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.marketplace.records.Plan;
import com.tecknobit.githubmanager.apps.marketplace.records.SubscriptionPlan;
import com.tecknobit.githubmanager.apps.marketplace.records.SubscriptionPlan.MarketPlacePurchase;
import com.tecknobit.githubmanager.apps.marketplace.records.SubscriptionPlan.MarketPlacePurchase.Account;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubMarketplaceManager} class is useful to manage all GitHub's marketplace endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace">
 * GitHub Marketplace</a>
 * @see GitHubManager
 **/
public class GitHubMarketplaceManager extends GitHubManager {

    /**
     * {@code MARKETPLACE_LISTING_PATH} constant for {@code "marketplace_listing/"} path
     **/
    public static final String MARKETPLACE_LISTING_PATH = "marketplace_listing/";

    /**
     * {@code ACCOUNTS_PATH} constant for {@code "accounts"} path
     **/
    public static final String ACCOUNTS_PATH = "accounts";

    /**
     * {@code MARKETPLACE_LISTING_ACCOUNTS_PATH} constant for {@code "marketplace_listing/accounts/"} path
     **/
    public static final String MARKETPLACE_LISTING_ACCOUNTS_PATH = MARKETPLACE_LISTING_PATH + ACCOUNTS_PATH + "/";

    /**
     * {@code PLANS_PATH} constant for {@code "plans"} path
     **/
    public static final String PLANS_PATH = "plans";

    /**
     * {@code MARKETPLACE_LISTING_PLANS_PATH} constant for {@code "marketplace_listing/plans"} path
     **/
    public static final String MARKETPLACE_LISTING_PLANS_PATH = MARKETPLACE_LISTING_PATH + PLANS_PATH;

    /**
     * {@code USER_MARKETPLACE_PURCHASES_PATH} constant for {@code "user/marketplace_purchases"} path
     **/
    public static final String USER_MARKETPLACE_PURCHASES_PATH = USER_PATH + "/marketplace_purchases";

    /**
     * Constructor to init a {@link GitHubMarketplaceManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMarketplaceManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMarketplaceManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMarketplaceManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMarketplaceManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMarketplaceManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMarketplaceManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMarketplaceManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMarketplaceManager} <br>
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
    public GitHubMarketplaceManager() {
        super();
    }

    /**
     * Method to shows whether the user or organization account actively subscribes to a plan listed by the authenticated GitHub App.
     * When someone submits a plan change that won't be processed until the end of their billing cycle, you will also see the upcoming pending change.
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param account: account from fetch the subscription plan
     * @return subscription plan as {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#get-a-subscription-plan-for-an-account">
     * Get a subscription plan for an account</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/accounts/{account_id}")
    public SubscriptionPlan getSubscriptionPlan(Account account) throws IOException {
        return getSubscriptionPlan(account.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to shows whether the user or organization account actively subscribes to a plan listed by the authenticated GitHub App.
     * When someone submits a plan change that won't be processed until the end of their billing cycle, you will also see the upcoming pending change.
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param account: account from fetch the subscription plan
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return subscription plan as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#get-a-subscription-plan-for-an-account">
     * Get a subscription plan for an account</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/accounts/{account_id}")
    public <T> T getSubscriptionPlan(Account account, ReturnFormat format) throws IOException {
        return getSubscriptionPlan(account.getId(), format);
    }

    /**
     * Method to shows whether the user or organization account actively subscribes to a plan listed by the authenticated GitHub App.
     * When someone submits a plan change that won't be processed until the end of their billing cycle, you will also see the upcoming pending change.
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param accountId: account_id parameter
     * @return subscription plan as {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#get-a-subscription-plan-for-an-account">
     * Get a subscription plan for an account</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/accounts/{account_id}")
    public SubscriptionPlan getSubscriptionPlan(long accountId) throws IOException {
        return getSubscriptionPlan(accountId, LIBRARY_OBJECT);
    }

    /**
     * Method to shows whether the user or organization account actively subscribes to a plan listed by the authenticated GitHub App.
     * When someone submits a plan change that won't be processed until the end of their billing cycle, you will also see the upcoming pending change.
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param accountId: account_id parameter
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return subscription plan as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#get-a-subscription-plan-for-an-account">
     * Get a subscription plan for an account</a>
     **/
    @RequestPath(method = GET, path = "/marketplace_listing/accounts/{account_id}")
    public <T> T getSubscriptionPlan(long accountId, ReturnFormat format) throws IOException {
        String subscriptionPlanResponse = sendGetRequest(MARKETPLACE_LISTING_ACCOUNTS_PATH + accountId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(subscriptionPlanResponse);
            case LIBRARY_OBJECT:
                return (T) new SubscriptionPlan(new JSONObject(subscriptionPlanResponse));
            default:
                return (T) subscriptionPlanResponse;
        }
    }

    /**
     * Method to list all plans that are part of your GitHub Marketplace listing. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint<br>
     * No-any params required
     *
     * @return plans list as {@link Collection} of {@link Plan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-plans">
     * List plans</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/plans")
    public Collection<Plan> getPlans() throws IOException {
        return getPlans(LIBRARY_OBJECT);
    }

    /**
     * Method to list all plans that are part of your GitHub Marketplace listing. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return plans list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-plans">
     * List plans</a>
     **/
    @RequestPath(method = GET, path = "/marketplace_listing/plans")
    public <T> T getPlans(ReturnFormat format) throws IOException {
        return returnPlansList(sendGetRequest(MARKETPLACE_LISTING_PLANS_PATH), format);
    }

    /**
     * Method to list all plans that are part of your GitHub Marketplace listing. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return plans list as {@link Collection} of {@link Plan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-plans">
     * List plans</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/plans")
    public Collection<Plan> getPlans(Params queryParams) throws IOException {
        return getPlans(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to list all plans that are part of your GitHub Marketplace listing. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
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
     * @return plans list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-plans">
     * List plans</a>
     **/
    @RequestPath(method = GET, path = "/marketplace_listing/plans")
    public <T> T getPlans(Params queryParams, ReturnFormat format) throws IOException {
        return returnPlansList(sendGetRequest(MARKETPLACE_LISTING_PLANS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create a plans list
     *
     * @param plansResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return plans list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPlansList(String plansResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(plansResponse);
            case LIBRARY_OBJECT:
                ArrayList<Plan> plans = new ArrayList<>();
                JSONArray jPlans = new JSONArray(plansResponse);
                for (int j = 0; j < jPlans.length(); j++)
                    plans.add(new Plan(jPlans.getJSONObject(j)));
                return (T) plans;
            default:
                return (T) plansResponse;
        }
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param plan: from fetch the list
     * @return accounts list as {@link Collection} of {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public Collection<SubscriptionPlan> getAccountsForPlan(Plan plan) throws IOException {
        return getAccountsForPlan(plan.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param plan:   from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return accounts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public <T> T getAccountsForPlan(Plan plan, ReturnFormat format) throws IOException {
        return getAccountsForPlan(plan.getId(), format);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param planId: the unique identifier of the plan
     * @return accounts list as {@link Collection} of {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public Collection<SubscriptionPlan> getAccountsForPlan(long planId) throws IOException {
        return getAccountsForPlan(planId, LIBRARY_OBJECT);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param planId: the unique identifier of the plan
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return accounts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public <T> T getAccountsForPlan(long planId, ReturnFormat format) throws IOException {
        return returnAccountsList(sendGetRequest(MARKETPLACE_LISTING_PLANS_PATH + "/" + planId + "/" +
                ACCOUNTS_PATH), format);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param plan:        from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. {@code "created"} means when
     *                            the repository was starred. {@code "updated"} means when the repository was last pushed
     *                            to, constants available at {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available at
     *                            {@link Directions}  - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return accounts list as {@link Collection} of {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public Collection<SubscriptionPlan> getAccountsForPlan(Plan plan, Params queryParams) throws IOException {
        return getAccountsForPlan(plan.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param plan:        from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. {@code "created"} means when
     *                            the repository was starred. {@code "updated"} means when the repository was last pushed
     *                            to, constants available at {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available at
     *                            {@link Directions}  - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return accounts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public <T> T getAccountsForPlan(Plan plan, Params queryParams, ReturnFormat format) throws IOException {
        return getAccountsForPlan(plan.getId(), queryParams, format);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param planId:      the unique identifier of the plan
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. {@code "created"} means when
     *                            the repository was starred. {@code "updated"} means when the repository was last pushed
     *                            to, constants available at {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available at
     *                            {@link Directions}  - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return accounts list as {@link Collection} of {@link SubscriptionPlan} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public Collection<SubscriptionPlan> getAccountsForPlan(long planId, Params queryParams) throws IOException {
        return getAccountsForPlan(planId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return user and organization accounts associated with the specified plan, including free plans.
     * For per-seat pricing, you see the list of accounts that have purchased the plan, including the number of seats purchased.
     * When someone submits a plan change that won't be processed until the end of their billing cycle,
     * you will also see the upcoming pending change. <br>
     * GitHub Apps must use a JWT to access this endpoint. <br>
     * OAuth Apps must use basic authentication with their client ID and client secret to access this endpoint
     *
     * @param planId:      the unique identifier of the plan
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by. {@code "created"} means when
     *                            the repository was starred. {@code "updated"} means when the repository was last pushed
     *                            to, constants available at {@link Sort} - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available at
     *                            {@link Directions}  - [string, default "desc"]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return accounts list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
     * List accounts for a plan</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/marketplace_listing/plans/{plan_id}/accounts")
    public <T> T getAccountsForPlan(long planId, Params queryParams, ReturnFormat format) throws IOException {
        return returnAccountsList(sendGetRequest(MARKETPLACE_LISTING_PLANS_PATH + "/" + planId + "/" +
                ACCOUNTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an accounts list
     *
     * @param accountsResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return accounts list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnAccountsList(String accountsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(accountsResponse);
            case LIBRARY_OBJECT:
                ArrayList<SubscriptionPlan> accounts = new ArrayList<>();
                JSONArray jAccounts = new JSONArray(accountsResponse);
                for (int j = 0; j < jAccounts.length(); j++)
                    accounts.add(new SubscriptionPlan(jAccounts.getJSONObject(j)));
                return (T) accounts;
            default:
                return (T) accountsResponse;
        }
    }

    /**
     * Method to list the active subscriptions for the authenticated user. You must use a user-to-server OAuth access token,
     * created for a user who has authorized your GitHub App, to access this endpoint.
     * OAuth Apps must authenticate using an OAuth token <br>
     * No-any params required
     *
     * @return subscriptions list as {@link Collection} of {@link MarketPlacePurchase} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-subscriptions-for-the-authenticated-user">
     * List subscriptions for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/marketplace_purchases")
    public Collection<MarketPlacePurchase> getAuthenticatedUserSubscriptions() throws IOException {
        return getAuthenticatedUserSubscriptions(LIBRARY_OBJECT);
    }

    /**
     * Method to list the active subscriptions for the authenticated user. You must use a user-to-server OAuth access token,
     * created for a user who has authorized your GitHub App, to access this endpoint.
     * OAuth Apps must authenticate using an OAuth token
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return subscriptions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-subscriptions-for-the-authenticated-user">
     * List subscriptions for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/marketplace_purchases")
    public <T> T getAuthenticatedUserSubscriptions(ReturnFormat format) throws IOException {
        return returnSubscriptionsList(sendGetRequest(USER_MARKETPLACE_PURCHASES_PATH), format);
    }

    /**
     * Method to list the active subscriptions for the authenticated user. You must use a user-to-server OAuth access token,
     * created for a user who has authorized your GitHub App, to access this endpoint.
     * OAuth Apps must authenticate using an OAuth token <br>
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return subscriptions list as {@link Collection} of {@link MarketPlacePurchase} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-subscriptions-for-the-authenticated-user">
     * List subscriptions for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/marketplace_purchases")
    public Collection<MarketPlacePurchase> getAuthenticatedUserSubscriptions(Params queryParams) throws IOException {
        return getAuthenticatedUserSubscriptions(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to list the active subscriptions for the authenticated user. You must use a user-to-server OAuth access token,
     * created for a user who has authorized your GitHub App, to access this endpoint.
     * OAuth Apps must authenticate using an OAuth token <br>
     *
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
     * @return subscriptions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-subscriptions-for-the-authenticated-user">
     * List subscriptions for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/marketplace_purchases")
    public <T> T getAuthenticatedUserSubscriptions(Params queryParams, ReturnFormat format) throws IOException {
        return returnSubscriptionsList(sendGetRequest(USER_MARKETPLACE_PURCHASES_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to create a subscriptions list
     *
     * @param subscriptionsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return subscriptions list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSubscriptionsList(String subscriptionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(subscriptionsResponse);
            case LIBRARY_OBJECT:
                ArrayList<MarketPlacePurchase> subscriptions = new ArrayList<>();
                JSONArray jSubscriptions = new JSONArray(subscriptionsResponse);
                for (int j = 0; j < jSubscriptions.length(); j++)
                    subscriptions.add(new MarketPlacePurchase(jSubscriptions.getJSONObject(j)));
                return (T) subscriptions;
            default:
                return (T) subscriptionsResponse;
        }
    }

}
