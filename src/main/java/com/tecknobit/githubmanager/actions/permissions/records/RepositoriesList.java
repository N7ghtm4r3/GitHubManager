package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.records.GitHubList;
import org.json.JSONObject;

import java.util.ArrayList;

public class RepositoriesList extends GitHubList {

    private final ArrayList<Repository> repositories;

    /**
     * Constructor to init an {@link RepositoriesList}
     *
     * @param totalCount : total number of the repositories in the list
     **/
    public RepositoriesList(int totalCount, ArrayList<Repository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link RepositoriesList}
     *
     * @param jRepositoriesList : repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public RepositoriesList(JSONObject jRepositoriesList) {
        super(jRepositoriesList);
        repositories = null;
    }

    public static class Repository {

        /*private final long id;
        private final String nodeId;*/

    }

}
