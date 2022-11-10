package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code RepositoriesList} class is useful to format a GitHub's repositories list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
 * List selected repositories for an organization secret</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RepositoriesList extends GitHubList {

    /**
     * {@code repositories} repositories list
     **/
    private final ArrayList<Repository> repositories;

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param repositories: repositories list
     **/
    public RepositoriesList(ArrayList<Repository> repositories) {
        super(repositories.size());
        this.repositories = repositories;
    }

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount    : total number of the repositories in the list
     * @param repositories: repositories list
     **/
    public RepositoriesList(int totalCount, ArrayList<Repository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jRepositoriesList : repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public RepositoriesList(JSONObject jRepositoriesList) {
        super(jRepositoriesList);
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        repositories = new ArrayList<>();
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new Repository(jRepositories.getJSONObject(j)));
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * Any params required
     *
     * @return {@link #repositories} instance as {@link Collection} of {@link Repository}
     **/
    public Collection<Repository> getRepositories() {
        return repositories;
    }

}
