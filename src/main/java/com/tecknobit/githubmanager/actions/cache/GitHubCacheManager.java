package com.tecknobit.githubmanager.actions.cache;

import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.cache.records.CacheUsage;
import com.tecknobit.githubmanager.actions.cache.records.RepositoriesCacheUsagesList;
import com.tecknobit.githubmanager.actions.cache.records.RepositoryCacheUsage;
import com.tecknobit.githubmanager.actions.cache.records.RepositoryCachesList;
import com.tecknobit.githubmanager.actions.cache.records.RepositoryCachesList.ActionCache;
import com.tecknobit.githubmanager.records.Repository;
import com.tecknobit.githubmanager.records.organization.Organization;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.ACTIONS_PATH;
import static com.tecknobit.githubmanager.actions.artifacts.GitHubArtifactsManager.REPOS_PATH;

/**
 * The {@code GitHubCacheManager} class is useful to manage all GitHub's cache endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#about-the-cache-api">
 * About the Cache API</a>
 * @see GitHubManager
 **/
public class GitHubCacheManager extends GitHubManager {

    /**
     * {@code ENTERPRISES_PATH} constant for {@code "enterprises/"} path
     **/
    public static final String ENTERPRISES_PATH = "enterprises/";

    /**
     * {@code ORGS_PATH} constant for {@code "orgs/"} path
     **/
    public static final String ORGS_PATH = "orgs/";

    /**
     * {@code ACTIONS_CACHE_USAGE_PATH} constant for {@code "/actions/cache/usage"} path
     **/
    public static final String ACTIONS_CACHE_USAGE_PATH = ACTIONS_PATH + "cache/usage/";

    /**
     * {@code ACTIONS_CACHE_USAGE_PATH} constant for {@code "/actions/cache/usage"} path
     **/
    public static final String ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH = ACTIONS_PATH + "cache/usage-by-repository";

    /**
     * {@code ACTIONS_CACHES_PATH} constant for {@code "actions/caches"} path
     **/
    public static final String ACTIONS_CACHES_PATH = ACTIONS_PATH + "caches";

    /**
     * Constructor to init a {@link GitHubCacheManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCacheManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCacheManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCacheManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCacheManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCacheManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCacheManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCacheManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCacheManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubCacheManager}'s manager without re-insert
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
    public GitHubCacheManager() {
        super();
    }

    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an enterprise. The data fetched using this API is refreshed
     * approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @return cache usage as {@link CacheUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-enterprise">
     * Get GitHub Actions cache usage for an enterprise</a>
     **/
    public CacheUsage getEnterpriseCacheUsage(String enterprise) throws IOException {
        return returnCacheUsage(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_CACHE_USAGE_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an enterprise. The data fetched using this API is refreshed
     * approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "admin:enterprise"} scope to use this endpoint
     *
     * @param enterprise: the slug version of the enterprise name. You can also substitute this value with the enterprise id
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return cache usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-enterprise">
     * Get GitHub Actions cache usage for an enterprise</a>
     **/
    public <T> T getEnterpriseCacheUsage(String enterprise, ReturnFormat format) throws IOException {
        return returnCacheUsage(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_CACHE_USAGE_PATH), format);
    }

    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an organization. The data fetched using this API
     * is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org: the organization from fetch the cache usage
     * @return cache usage as {@link CacheUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-organization">
     * Get GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public CacheUsage getOrganizationCacheUsage(Organization org) throws IOException {
        return returnCacheUsage(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_CACHE_USAGE_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an organization. The data fetched using this API
     * is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:    the organization from fetch the cache usage
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return cache usage as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-organization">
     * Get GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public <T> T getOrganizationCacheUsage(Organization org, ReturnFormat format) throws IOException {
        return returnCacheUsage(sendGetRequest(ORGS_PATH + org.getLogin() + ACTIONS_CACHE_USAGE_PATH), format);
    }


    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an organization. The data fetched using this API
     * is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return cache usage as {@link CacheUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-organization">
     * Get GitHub Actions cache usage for an organization</a>
     **/
    public CacheUsage getOrganizationCacheUsage(String org) throws IOException {
        return returnCacheUsage(sendGetRequest(ORGS_PATH + org + ACTIONS_CACHE_USAGE_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the total {@code "GitHub Actions"} cache usage for an organization. The data fetched using this API
     * is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return cache usage as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-organization">
     * Get GitHub Actions cache usage for an organization</a>
     **/
    public <T> T getOrganizationCacheUsage(String org, ReturnFormat format) throws IOException {
        return returnCacheUsage(sendGetRequest(ORGS_PATH + org + ACTIONS_CACHE_USAGE_PATH), format);
    }

    /**
     * Method to create a cache usage
     *
     * @param cacheUsageResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return cache usage as {@code "format"} defines
     **/
    private <T> T returnCacheUsage(String cacheUsageResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(cacheUsageResponse);
            case LIBRARY_OBJECT:
                return (T) new CacheUsage(new JSONObject(cacheUsageResponse));
            default:
                return (T) cacheUsageResponse;
        }
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org: the organization from fetch the cache usages list
     * @return repositories cache usages list as {@link RepositoriesCacheUsagesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public RepositoriesCacheUsagesList getRepositoriesCacheUsagesList(Organization org) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:    the organization from fetch the cache usages list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories cache usages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public <T> T getRepositoriesCacheUsagesList(Organization org, ReturnFormat format) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH), format);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return repositories cache usages list as {@link RepositoriesCacheUsagesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    public RepositoriesCacheUsagesList getRepositoriesCacheUsagesList(String org) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories cache usages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    public <T> T getRepositoriesCacheUsagesList(String org, ReturnFormat format) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH), format);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:         the organization from fetch the cache usages list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories cache usages list as {@link RepositoriesCacheUsagesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public RepositoriesCacheUsagesList getRepositoriesCacheUsagesList(Organization org, Params queryParams) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param org:         the organization from fetch the cache usages list
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
     * @return repositories cache usages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    @WrappedRequest
    public <T> T getRepositoriesCacheUsagesList(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org.getLogin() +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
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
     * @return repositories cache usages list as {@link RepositoriesCacheUsagesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    public RepositoriesCacheUsagesList getRepositoriesCacheUsagesList(String org, Params queryParams) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of repositories and their {@code "GitHub Actions"} cache usage for an organization. The data fetched using this
     * API is refreshed approximately every 5 minutes, so values returned from this endpoint may take at least 5 minutes to get updated.
     * You must authenticate using an access token with the {@code "read:org"} scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "organization_administration:read"} permission to use this endpoint
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
     * @return repositories cache usages list as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
     * List repositories with GitHub Actions cache usage for an organization</a>
     **/
    public <T> T getRepositoriesCacheUsagesList(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoriesCacheUsagesList(sendGetRequest(ORGS_PATH + org +
                ACTIONS_CACHE_USAGE_BY_REPOSITORY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repositories cache usages list
     *
     * @param repositoriesCacheResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return repositories cache usages list as {@code "format"} defines
     **/
    private <T> T returnRepositoriesCacheUsagesList(String repositoriesCacheResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesCacheResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoriesCacheUsagesList(new JSONObject(repositoriesCacheResponse));
            default:
                return (T) repositoriesCacheResponse;
        }
    }

    /**
     * Method to get {@code "GitHub Actions"} cache usage for a repository. The data fetched using this API is refreshed approximately every 5 minutes,
     * so values returned from this endpoint may take at least 5 minutes to get updated.
     * Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private, you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch cache usage
     * @return repository cache usage as {@link RepositoryCacheUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-a-repository">
     * Get GitHub Actions cache usage for a repository</a>
     **/
    @WrappedRequest
    public RepositoryCacheUsage getRepositoryCacheUsage(Repository repository) throws IOException {
        return getRepositoryCacheUsage(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get {@code "GitHub Actions"} cache usage for a repository. The data fetched using this API is refreshed approximately every 5 minutes,
     * so values returned from this endpoint may take at least 5 minutes to get updated.
     * Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private, you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch cache usage
     * @return repository cache usage as {@link RepositoryCacheUsage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-a-repository">
     * Get GitHub Actions cache usage for a repository</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryCacheUsage(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryCacheUsage(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get {@code "GitHub Actions"} cache usage for a repository. The data fetched using this API is refreshed approximately every 5 minutes,
     * so values returned from this endpoint may take at least 5 minutes to get updated.
     * Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private, you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository cache usage as {@link RepositoryCacheUsage} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-a-repository">
     * Get GitHub Actions cache usage for a repository</a>
     **/
    public RepositoryCacheUsage getRepositoryCacheUsage(String owner, String repo) throws IOException {
        return getRepositoryCacheUsage(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get {@code "GitHub Actions"} cache usage for a repository. The data fetched using this API is refreshed approximately every 5 minutes,
     * so values returned from this endpoint may take at least 5 minutes to get updated.
     * Anyone with {@code "read"} access to the repository can use this endpoint.
     * If the repository is private, you must use an access token with the repo scope -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository cache usage list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-a-repository">
     * Get GitHub Actions cache usage for a repository</a>
     **/
    public <T> T getRepositoryCacheUsage(String owner, String repo, ReturnFormat format) throws IOException {
        String repositoryCacheUsageResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo +
                ACTIONS_CACHE_USAGE_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryCacheUsageResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryCacheUsage(new JSONObject(repositoryCacheUsageResponse));
            default:
                return (T) repositoryCacheUsageResponse;
        }
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository: the repository from fetch the cache usages list
     * @return repository caches list as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    @WrappedRequest
    public RepositoryCachesList getRepositoryCachesList(Repository repository) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getOwner().getLogin() + ACTIONS_CACHES_PATH), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository: the repository from fetch the cache usages list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository caches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryCachesList(Repository repository, ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_CACHES_PATH), format);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository caches list as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    public RepositoryCachesList getRepositoryCachesList(String owner, String repo) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository caches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    public <T> T getRepositoryCachesList(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH),
                format);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the cache usages list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                            {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "key"} -> an explicit key or prefix for identifying the cache - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by.
     *                            {@code "created_at"} means when the cache was created.
     *                            {@code "last_accessed_at"} means when the cache was last accessed.
     *                            {@code "size_in_bytes"} is the size of the cache in bytes -
     *                            [string, default "last_accessed_at", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by
     *                            - [string, default "desc", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                     </ul>
     * @return repository caches list as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    @WrappedRequest
    public RepositoryCachesList getRepositoryCachesList(Repository repository, Params queryParams) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_CACHES_PATH + queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
     *
     * @param repository:  the repository from fetch the cache usages list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "ref"} -> the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                            {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "key"} -> an explicit key or prefix for identifying the cache - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by.
     *                            {@code "created_at"} means when the cache was created.
     *                            {@code "last_accessed_at"} means when the cache was last accessed.
     *                            {@code "size_in_bytes"} is the size of the cache in bytes -
     *                            [string, default "last_accessed_at", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by
     *                            - [string, default "desc", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repository caches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    @WrappedRequest
    public <T> T getRepositoryCachesList(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getOwner() + ACTIONS_CACHES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                            {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "key"} -> an explicit key or prefix for identifying the cache - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by.
     *                            {@code "created_at"} means when the cache was created.
     *                            {@code "last_accessed_at"} means when the cache was last accessed.
     *                            {@code "size_in_bytes"} is the size of the cache in bytes -
     *                            [string, default "last_accessed_at", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by
     *                            - [string, default "desc", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                     </ul>
     * @return repository caches list as {@link RepositoryCachesList} custom object
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    public RepositoryCachesList getRepositoryCachesList(String owner, String repo, Params queryParams) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the {@code "GitHub Actions"} caches for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the actions:read permission to use this endpoint
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
     *                        <li>
     *                            {@code "ref"} -> the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                            {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}.
     *                            To reference a pull request use {@code "refs/pull/<number>/merge"} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "key"} -> an explicit key or prefix for identifying the cache - [string]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> the property to sort the results by.
     *                            {@code "created_at"} means when the cache was created.
     *                            {@code "last_accessed_at"} means when the cache was last accessed.
     *                            {@code "size_in_bytes"} is the size of the cache in bytes -
     *                            [string, default "last_accessed_at", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by
     *                            - [string, default "desc", constants available at {@link RepositoryCachesList}]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repository caches list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     * List GitHub Actions caches for a repository</a>
     **/
    public <T> T getRepositoryCachesList(String owner, String repo, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH +
                queryParams.createQueryString()), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from delete cache
     * @param key:        a key for identifying the cache
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCache(Repository repository, String key) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_CACHES_PATH + "?key=" + key), LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from delete cache
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCache(Repository repository, String key, ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/"
                + repository.getName() + ACTIONS_CACHES_PATH + "?key=" + key), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param key:   a key for identifying the cache
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCache(String owner, String repo, String key) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH +
                "?key=" + key), LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCache(String owner, String repo, String key, ReturnFormat format) throws IOException {
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH +
                "?key=" + key), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:    repository from delete cache
     * @param cacheToDelete: cache to delete
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCacheByKey(Repository repository, ActionCache cacheToDelete) throws IOException {
        return deleteRepositoryCache(repository.getOwner().getLogin(), repository.getName(), cacheToDelete.getKey(),
                cacheToDelete.getRef(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:    repository from delete cache
     * @param cacheToDelete: cache to delete
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCacheByKey(Repository repository, ActionCache cacheToDelete,
                                            ReturnFormat format) throws IOException {
        return deleteRepositoryCache(repository.getOwner().getLogin(), repository.getName(), cacheToDelete.getKey(),
                cacheToDelete.getRef(), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param cacheToDelete: cache to delete
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCacheByKey(String owner, String repo,
                                                           ActionCache cacheToDelete) throws IOException {
        return deleteRepositoryCache(owner, repo, cacheToDelete.getKey(), cacheToDelete.getRef(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param cacheToDelete: cache to delete
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCacheByKey(String owner, String repo, ActionCache cacheToDelete,
                                            ReturnFormat format) throws IOException {
        return deleteRepositoryCache(owner, repo, cacheToDelete.getKey(), cacheToDelete.getRef(), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from delete cache
     * @param key:        a key for identifying the cache
     * @param ref:        the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                    {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCache(Repository repository, String key, String ref) throws IOException {
        return deleteRepositoryCache(repository.getOwner().getLogin(), repository.getName(), key, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from delete cache
     * @param key:        a key for identifying the cache
     * @param ref:        the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                    {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCache(Repository repository, String key, String ref, ReturnFormat format) throws IOException {
        Params params = new Params();
        params.addParam("key", key);
        params.addParam("ref", ref);
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + repository.getOwner().getLogin() + "/" +
                repository.getName() + ACTIONS_CACHES_PATH + params.createQueryString()), format);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param key:   a key for identifying the cache
     * @param ref:   the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *               {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return repository caches list deleted as {@link RepositoryCachesList} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public RepositoryCachesList deleteRepositoryCache(String owner, String repo, String key,
                                                      String ref) throws IOException {
        return deleteRepositoryCache(owner, repo, key, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to delete one or more {@code "GitHub Actions"} caches for a repository, using a complete cache key.
     * By default, all caches that match the provided key are deleted, but you can optionally provide a
     * {@code "Git ref"} to restrict deletions to caches that match both the provided key and the {@code "Git ref"}.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param key:    a key for identifying the cache
     * @param ref:    the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
     *                {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository caches list deleted as {@code "format"} defines
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
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     * Delete GitHub Actions caches for a repository (using a cache key)</a>
     **/
    @WrappedRequest
    public <T> T deleteRepositoryCache(String owner, String repo, String key, String ref,
                                       ReturnFormat format) throws IOException {
        Params params = new Params();
        params.addParam("key", key);
        params.addParam("ref", ref);
        return returnRepositoryCachesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH +
                params.createQueryString()), format);
    }

    /**
     * Method to create a repository caches list
     *
     * @param repositoryCachesResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return repository caches list as {@code "format"} defines
     **/
    private <T> T returnRepositoryCachesList(String repositoryCachesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryCachesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryCachesList(new JSONObject(repositoryCachesResponse));
            default:
                return (T) repositoryCachesResponse;
        }
    }

    /**
     * Method to deletes a {@code "GitHub Actions"} cache for a repository, using a cache ID.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository:          repository from delete cache
     * @param actionCacheToDelete: cache to delete
     *                             {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-a-github-actions-cache-for-a-repository-using-a-cache-id">
     * Delete a GitHub Actions cache for a repository (using a cache ID)</a>
     **/
    @WrappedRequest
    public boolean deleteRepositoryCacheById(Repository repository, ActionCache actionCacheToDelete) {
        return deleteRepositoryCache(repository.getOwner().getLogin(), repository.getName(), actionCacheToDelete.getId());
    }

    /**
     * Method to deletes a {@code "GitHub Actions"} cache for a repository, using a cache ID.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: repository from delete cache
     * @param cacheId:    the unique identifier of the GitHub Actions cache
     *                    {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-a-github-actions-cache-for-a-repository-using-a-cache-id">
     * Delete a GitHub Actions cache for a repository (using a cache ID)</a>
     **/
    @WrappedRequest
    public boolean deleteRepositoryCacheById(Repository repository, long cacheId) {
        return deleteRepositoryCache(repository.getOwner().getLogin(), repository.getName(), cacheId);
    }

    /**
     * Method to deletes a {@code "GitHub Actions"} cache for a repository, using a cache ID.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:               the account owner of the repository. The name is not case-sensitive
     * @param repo:                the name of the repository. The name is not case-sensitive
     * @param actionCacheToDelete: cache to delete
     *                             {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-a-github-actions-cache-for-a-repository-using-a-cache-id">
     * Delete a GitHub Actions cache for a repository (using a cache ID)</a>
     **/
    @WrappedRequest
    public boolean deleteRepositoryCacheById(String owner, String repo, ActionCache actionCacheToDelete) {
        return deleteRepositoryCache(owner, repo, actionCacheToDelete.getId());
    }

    /**
     * Method to deletes a {@code "GitHub Actions"} cache for a repository, using a cache ID.
     * You must authenticate using an access token with the repo scope to use this endpoint -> <b> this step is automatically made
     * by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param cacheId: the unique identifier of the GitHub Actions cache
     *                 {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#delete-a-github-actions-cache-for-a-repository-using-a-cache-id">
     * Delete a GitHub Actions cache for a repository (using a cache ID)</a>
     **/
    public boolean deleteRepositoryCache(String owner, String repo, long cacheId) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_CACHES_PATH + "/" + cacheId);
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
