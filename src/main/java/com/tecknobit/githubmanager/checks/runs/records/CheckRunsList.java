package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code CheckRunsList} class is useful to format a GitHub's check runs list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-in-a-check-suite">
 *             List check runs in a check suite</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/runs#list-check-runs-for-a-git-reference">
 *             List check runs for a Git reference</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class CheckRunsList extends GitHubList {

    /**
     * {@code checkRuns} list of {@link CheckRun}
     **/
    private final ArrayList<CheckRun> checkRuns;

    /**
     * Constructor to init a {@link CheckRunsList}
     *
     * @param checkRuns : list of {@link CheckRun}
     **/
    public CheckRunsList(ArrayList<CheckRun> checkRuns) {
        super(checkRuns.size());
        this.checkRuns = checkRuns;
    }

    /**
     * Constructor to init an {@link CheckRunsList}
     *
     * @param totalCount : total number of the check runs in the list
     * @param checkRuns  : list of {@link CheckRun}
     **/
    public CheckRunsList(int totalCount, ArrayList<CheckRun> checkRuns) {
        super(totalCount);
        this.checkRuns = checkRuns;
    }

    /**
     * Constructor to init a {@link CheckRunsList}
     *
     * @param jCheckRuns : check runs details as {@link JSONObject}
     **/
    public CheckRunsList(JSONObject jCheckRuns) throws Exception {
        super(jCheckRuns);
        checkRuns = new ArrayList<>();
        JSONArray jCheckRunsList = hResponse.getJSONArray("check_runs", new JSONArray());
        for (int j = 0; j < jCheckRunsList.length(); j++)
            checkRuns.add(new CheckRun(jCheckRunsList.getJSONObject(j)));
    }

    /**
     * Method to get {@link #checkRuns} instance <br>
     * No-any params required
     *
     * @return {@link #checkRuns} instance as {@link Collection} of {@link CheckRun}
     **/
    public Collection<CheckRun> getCheckRuns() {
        return checkRuns;
    }

}
