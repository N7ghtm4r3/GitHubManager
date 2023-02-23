package com.tecknobit.githubmanager.actions.cache.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code RepositoriesCacheUsagesList} class is useful to format a GitHub's repositories cache usages list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/cache#list-repositories-with-github-actions-cache-usage-for-an-organization">
 * List repositories with GitHub Actions cache usage for an organization</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RepositoriesCacheUsagesList extends GitHubList {

    /**
     * {@code repositoryCacheUsages} the count of active caches across all repositories of an enterprise or an organization
     **/
    private final ArrayList<RepositoryCacheUsage> repositoryCacheUsages;

    /**
     * Constructor to init an {@link RepositoriesCacheUsagesList}
     *
     * @param repositoryCacheUsages: the count of active caches across all repositories of an enterprise or an organization
     **/
    public RepositoriesCacheUsagesList(ArrayList<RepositoryCacheUsage> repositoryCacheUsages) {
        super(repositoryCacheUsages.size());
        this.repositoryCacheUsages = repositoryCacheUsages;
    }

    /**
     * Constructor to init an {@link RepositoriesCacheUsagesList}
     *
     * @param totalCount:            the count of active caches across all repositories of an enterprise or an organization
     * @param repositoryCacheUsages: the count of active caches across all repositories of an enterprise or an organization
     **/
    public RepositoriesCacheUsagesList(int totalCount, ArrayList<RepositoryCacheUsage> repositoryCacheUsages) {
        super(totalCount);
        this.repositoryCacheUsages = repositoryCacheUsages;
    }

    /**
     * Constructor to init a {@link RepositoriesCacheUsagesList}
     *
     * @param jRepositoriesCacheUsageList : repositories cache usages list details as {@link JSONObject}
     **/
    public RepositoriesCacheUsagesList(JSONObject jRepositoriesCacheUsageList) {
        super(jRepositoriesCacheUsageList);
        repositoryCacheUsages = new ArrayList<>();
        JSONArray jCacheUsagesList = hResponse.getJSONArray("repository_cache_usages", new JSONArray());
        for (int j = 0; j < jCacheUsagesList.length(); j++)
            repositoryCacheUsages.add(new RepositoryCacheUsage(jCacheUsagesList.getJSONObject(j)));
    }

    /**
     * Method to get {@link #repositoryCacheUsages} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryCacheUsages} instance as {@link ArrayList} of {@link RepositoryCacheUsage}
     **/
    public ArrayList<RepositoryCacheUsage> getRepositoryCacheUsages() {
        return repositoryCacheUsages;
    }

}
