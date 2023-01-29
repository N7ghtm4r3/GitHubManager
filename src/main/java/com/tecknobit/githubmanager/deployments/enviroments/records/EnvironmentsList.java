package com.tecknobit.githubmanager.deployments.enviroments.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code EnvironmentsList} class is useful to format a GitHub's environments list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/deployments/environments#list-environments">
 * List environments</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class EnvironmentsList extends GitHubList {

    /**
     * {@code environments} list of {@link Environment}
     **/
    private final ArrayList<Environment> environments;

    /**
     * Constructor to init an {@link EnvironmentsList}
     *
     * @param environments: list of {@link Environment}
     **/
    public EnvironmentsList(ArrayList<Environment> environments) {
        super(environments.size());
        this.environments = environments;
    }

    /**
     * Constructor to init an {@link EnvironmentsList}
     *
     * @param totalCount    : total number of the items in the list
     * @param environments: list of {@link Environment}
     **/
    public EnvironmentsList(int totalCount, ArrayList<Environment> environments) {
        super(totalCount);
        this.environments = environments;
    }

    /**
     * Constructor to init a {@link EnvironmentsList}
     *
     * @param jEnvironmentsList : environments list details as {@link JSONObject}
     **/
    public EnvironmentsList(JSONObject jEnvironmentsList) {
        super(jEnvironmentsList);
        environments = new ArrayList<>();
        JSONArray jEnvironments = hResponse.getJSONArray("environments", new JSONArray());
        for (int j = 0; j < jEnvironments.length(); j++)
            environments.add(new Environment(jEnvironments.getJSONObject(j)));
    }

    /**
     * Method to get {@link #environments} instance <br>
     * No-any params required
     *
     * @return {@link #environments} instance as {@link Collection} of {@link Environment}
     **/
    public Collection<Environment> getEnvironments() {
        return environments;
    }

}
