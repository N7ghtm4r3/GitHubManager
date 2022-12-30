package com.tecknobit.githubmanager.checks.suites.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code CheckSuitesList} class is useful to format a GitHub's check suites list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#list-check-suites-for-a-git-reference">
 * List check suites for a Git reference</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class CheckSuitesList extends GitHubList {

    /**
     * {@code checkSuites} list of {@link CheckSuite}
     **/
    private final ArrayList<CheckSuite> checkSuites;

    /**
     * Constructor to init a {@link CheckSuitesList}
     *
     * @param checkSuites: list of {@link CheckSuite}
     **/
    public CheckSuitesList(ArrayList<CheckSuite> checkSuites) {
        super(checkSuites.size());
        this.checkSuites = checkSuites;
    }

    /**
     * Constructor to init an {@link CheckSuitesList}
     *
     * @param totalCount   : total number of the {@link CheckSuite} in the list
     * @param checkSuites: list of {@link CheckSuite}
     **/
    public CheckSuitesList(int totalCount, ArrayList<CheckSuite> checkSuites) {
        super(totalCount);
        this.checkSuites = checkSuites;
    }

    /**
     * Constructor to init a {@link CheckSuitesList}
     *
     * @param jCheckSuitesList : check suites list details as {@link JSONObject}
     **/
    public CheckSuitesList(JSONObject jCheckSuitesList) throws Exception {
        super(jCheckSuitesList);
        checkSuites = new ArrayList<>();
        JSONArray jCheckSuites = hResponse.getJSONArray("check_suites", new JSONArray());
        for (int j = 0; j < jCheckSuites.length(); j++)
            checkSuites.add(new CheckSuite(jCheckSuites.getJSONObject(j)));
    }

    /**
     * Method to get {@link #checkSuites} instance <br>
     * Any params required
     *
     * @return {@link #checkSuites} instance as {@link Collection} of {@link CheckSuite}
     **/
    public Collection<CheckSuite> getCheckSuites() {
        return checkSuites;
    }

}
