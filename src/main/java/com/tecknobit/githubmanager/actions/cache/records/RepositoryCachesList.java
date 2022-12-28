package com.tecknobit.githubmanager.actions.cache.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code RepositoryCachesList} class is useful to format a GitHub's repository caches list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
 *             List GitHub Actions caches for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
 *             Delete GitHub Actions caches for a repository (using a cache key)</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RepositoryCachesList extends GitHubList {

    /**
     * {@code Sorters} is a list for the sorters available
     **/
    public enum Sorters {

        /**
         * {@code created_at} is the constant for created at sorter
         **/
        created_at,

        /**
         * {@code last_accessed_at} is the constant for last accessed at sorter
         **/
        last_accessed_at,

        /**
         * {@code size_in_bytes} is the constant for size in bytes sorter
         **/
        size_in_bytes

    }

    /**
     * {@code actionCaches} list of caches
     **/
    private final ArrayList<ActionCache> actionCaches;

    /**
     * Constructor to init an {@link RepositoryCachesList}
     *
     * @param actionCaches:list of caches
     **/
    public RepositoryCachesList(ArrayList<ActionCache> actionCaches) {
        super(actionCaches.size());
        this.actionCaches = actionCaches;
    }

    /**
     * Constructor to init an {@link RepositoryCachesList}
     *
     * @param totalCount:       total number of caches
     * @param actionCaches:list of caches
     **/
    public RepositoryCachesList(int totalCount, ArrayList<ActionCache> actionCaches) {
        super(totalCount);
        this.actionCaches = actionCaches;
    }

    /**
     * Constructor to init a {@link RepositoryCachesList}
     *
     * @param jRepositoryCachesList : repository caches list details as {@link JSONObject}
     **/
    public RepositoryCachesList(JSONObject jRepositoryCachesList) {
        super(jRepositoryCachesList);
        actionCaches = new ArrayList<>();
        JSONArray jActionCaches = hResponse.getJSONArray("actions_caches", new JSONArray());
        for (int j = 0; j < jActionCaches.length(); j++)
            actionCaches.add(new ActionCache(jActionCaches.getJSONObject(j)));
    }

    /**
     * Method to get {@link #actionCaches} instance <br>
     * Any params required
     *
     * @return {@link #actionCaches} instance as {@link Collection} of {@link ActionCache}
     **/
    public Collection<ActionCache> getActionCaches() {
        return actionCaches;
    }

    /**
     * The {@code ActionCache} class is useful to format a GitHub's cache
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at:
     * <ul>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/cache#list-github-actions-caches-for-a-repository">
     *             List GitHub Actions caches for a repository</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/cache#delete-github-actions-caches-for-a-repository-using-a-cache-key">
     *             Delete GitHub Actions caches for a repository (using a cache key)</a>
     *     </li>
     * </ul>
     * @see GitHubResponse
     **/
    public static class ActionCache {

        /**
         * {@code id} identifier of the cache
         **/
        private final long id;

        /**
         * {@code key} the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
         * {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
         **/
        private final String ref;

        /**
         * {@code ref} the key for identifying the cache
         **/
        private final String key;

        /**
         * {@code version} version of the cache
         **/
        private final String version;

        /**
         * {@code lastAccessedAt} last accessed value
         **/
        private final String lastAccessedAt;

        /**
         * {@code createdAt} creation date of the cache
         **/
        private final String createdAt;

        /**
         * {@code sizeInBytes} size in bytes of the cache
         **/
        private final int sizeInBytes;

        /**
         * Constructor to init an {@link ActionCache}
         *
         * @param id:              identifier of the cache
         * @param ref:             the {@code "Git reference"} for the results you want to list. The ref for a branch can be formatted either as
         *                         {@code "refs/heads/<branch name>"} or simply {@code "<branch name>"}. To reference a pull request use {@code "refs/pull/<number>/merge"}
         * @param key:             the key for identifying the cache
         * @param version:         version of the cache
         * @param lastAccessedAt:  last accessed value
         * @param createdAt:       creation date of the cache
         * @param sizeInBytes:size in bytes of the cache
         **/
        public ActionCache(long id, String ref, String key, String version, String lastAccessedAt, String createdAt,
                           int sizeInBytes) {
            this.id = id;
            this.ref = ref;
            this.key = key;
            this.version = version;
            this.lastAccessedAt = lastAccessedAt;
            this.createdAt = createdAt;
            this.sizeInBytes = sizeInBytes;
        }

        /**
         * Constructor to init an {@link ActionCache}
         *
         * @param jActionCache: action cache details as {@link JSONObject}
         **/
        public ActionCache(JSONObject jActionCache) {
            JsonHelper hActionCache = new JsonHelper(jActionCache);
            id = hActionCache.getLong("id", 0);
            ref = hActionCache.getString("ref");
            key = hActionCache.getString("key");
            version = hActionCache.getString("version");
            lastAccessedAt = hActionCache.getString("last_accessed_at");
            createdAt = hActionCache.getString("created_at");
            sizeInBytes = hActionCache.getInt("size_in_bytes");
        }

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
        }

        /**
         * Method to get {@link #ref} instance <br>
         * Any params required
         *
         * @return {@link #ref} instance as {@link String}
         **/
        public String getRef() {
            return ref;
        }

        /**
         * Method to get {@link #key} instance <br>
         * Any params required
         *
         * @return {@link #key} instance as {@link String}
         **/
        public String getKey() {
            return key;
        }

        /**
         * Method to get {@link #version} instance <br>
         * Any params required
         *
         * @return {@link #version} instance as {@link String}
         **/
        public String getVersion() {
            return version;
        }

        /**
         * Method to get {@link #lastAccessedAt} instance <br>
         * Any params required
         *
         * @return {@link #lastAccessedAt} instance as {@link String}
         **/
        public String getLastAccessedAt() {
            return lastAccessedAt;
        }

        /**
         * Method to get {@link #lastAccessedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #lastAccessedAt} timestamp as long
         **/
        public long getLastAccessedAtTimestamp() {
            return getDateTimestamp(lastAccessedAt);
        }

        /**
         * Method to get {@link #createdAt} instance <br>
         * Any params required
         *
         * @return {@link #createdAt} instance as {@link String}
         **/
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         * Method to get {@link #createdAt} timestamp <br>
         * Any params required
         *
         * @return {@link #createdAt} timestamp as long
         **/
        public long getCreatedAtTimestamp() {
            return getDateTimestamp(createdAt);
        }

        /**
         * Method to get {@link #sizeInBytes} instance <br>
         * Any params required
         *
         * @return {@link #sizeInBytes} instance as int
         **/
        public int getSizeInBytes() {
            return sizeInBytes;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
