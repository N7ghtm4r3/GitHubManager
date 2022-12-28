package com.tecknobit.githubmanager.actions.cache.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CacheUsage} class is useful to format a GitHub's cache usage
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-enterprise">
 *             Get GitHub Actions cache usage for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-an-organization">
 *             Get GitHub Actions cache usage for an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class CacheUsage extends GitHubResponse {

    /**
     * {@code totalActiveCachesSizeInBytes} the count of active caches across all repositories of an enterprise or an organization
     **/
    private final int totalActiveCachesSizeInBytes;

    /**
     * {@code totalActiveCachesCount} the total size in bytes of all active cache items across all repositories of an enterprise or an organization
     **/
    private final int totalActiveCachesCount;

    /**
     * Constructor to init an {@link CacheUsage}
     *
     * @param totalActiveCachesSizeInBytes: the count of active caches across all repositories of an enterprise or an organization
     * @param totalActiveCachesCount:       the total size in bytes of all active cache items across all repositories of an enterprise or an organization
     **/
    public CacheUsage(int totalActiveCachesSizeInBytes, int totalActiveCachesCount) {
        super(null);
        this.totalActiveCachesSizeInBytes = totalActiveCachesSizeInBytes;
        this.totalActiveCachesCount = totalActiveCachesCount;
    }

    /**
     * Constructor to init a {@link CacheUsage}
     *
     * @param jCache : cache details as {@link JSONObject}
     **/
    public CacheUsage(JSONObject jCache) {
        super(jCache);
        totalActiveCachesSizeInBytes = hResponse.getInt("total_active_caches_size_in_bytes", 0);
        totalActiveCachesCount = hResponse.getInt("total_active_caches_count", 0);
    }

    /**
     * Method to get {@link #totalActiveCachesSizeInBytes} instance <br>
     * Any params required
     *
     * @return {@link #totalActiveCachesSizeInBytes} instance as int
     **/
    public int getTotalActiveCachesSizeInBytes() {
        return totalActiveCachesSizeInBytes;
    }

    /**
     * Method to get {@link #totalActiveCachesCount} instance <br>
     * Any params required
     *
     * @return {@link #totalActiveCachesCount} instance as int
     **/
    public int getTotalActiveCachesCount() {
        return totalActiveCachesCount;
    }

}
