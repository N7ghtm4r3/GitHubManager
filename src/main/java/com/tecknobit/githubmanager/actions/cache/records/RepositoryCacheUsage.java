package com.tecknobit.githubmanager.actions.cache.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code RepositoryCacheUsage} class is useful to format a GitHub's repository cache usage
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#get-github-actions-cache-usage-for-a-repository">
 *             Get GitHub Actions cache usage for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
 *             List repositories with GitHub Actions cache usage for an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class RepositoryCacheUsage extends GitHubResponse {

    /**
     * {@code fullName} the repository owner and name for the cache usage being shown
     **/
    private final String fullName;

    /**
     * {@code activeCachesSizeInBytes} the sum of the size in bytes of all the active cache items in the repository
     **/
    private final int activeCachesSizeInBytes;

    /**
     * {@code activeCachesCount} the number of active caches in the repository
     **/
    private final int activeCachesCount;

    /**
     * Constructor to init an {@link RepositoryCacheUsage}
     *
     * @param fullName:                the repository owner and name for the cache usage being shown
     * @param activeCachesSizeInBytes: the sum of the size in bytes of all the active cache items in the repository
     * @param activeCachesCount:       the number of active caches in the repository
     **/
    public RepositoryCacheUsage(String fullName, int activeCachesSizeInBytes, int activeCachesCount) {
        super(null);
        this.fullName = fullName;
        this.activeCachesSizeInBytes = activeCachesSizeInBytes;
        this.activeCachesCount = activeCachesCount;
    }

    /**
     * Constructor to init a {@link RepositoryCacheUsage}
     *
     * @param jRepositoryCacheUsage : repository cache usage details as {@link JSONObject}
     **/
    public RepositoryCacheUsage(JSONObject jRepositoryCacheUsage) {
        super(jRepositoryCacheUsage);
        fullName = hResponse.getString("full_name");
        activeCachesSizeInBytes = hResponse.getInt("active_caches_size_in_bytes");
        activeCachesCount = hResponse.getInt("active_caches_count");
    }

    /**
     * Method to get {@link #fullName} instance <br>
     * Any params required
     *
     * @return {@link #fullName} instance as {@link String}
     **/
    public String getFullName() {
        return fullName;
    }

    /**
     * Method to get {@link #activeCachesSizeInBytes} instance <br>
     * Any params required
     *
     * @return {@link #activeCachesSizeInBytes} instance as int
     **/
    public int getActiveCachesSizeInBytes() {
        return activeCachesSizeInBytes;
    }

    /**
     * Method to get {@link #activeCachesCount} instance <br>
     * Any params required
     *
     * @return {@link #activeCachesCount} instance as int
     **/
    public int getActiveCachesCount() {
        return activeCachesCount;
    }

}
