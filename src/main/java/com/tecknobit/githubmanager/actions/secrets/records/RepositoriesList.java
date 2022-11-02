package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.githubmanager.records.GitHubList;
import com.tecknobit.githubmanager.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class RepositoriesList extends GitHubList {

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
     * @param totalCount    : total number of the items in the list
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

    public Collection<Repository> getRepositories() {
        return repositories;
    }

}
