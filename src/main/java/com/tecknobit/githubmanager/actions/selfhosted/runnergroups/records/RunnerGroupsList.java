package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code RunnerGroupsList} class is useful to format a GitHub's runner groups list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-enterprise">
 *             List self-hosted runner groups for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runner-groups-for-an-organization">
 *             List self-hosted runner groups for an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RunnerGroupsList extends GitHubList {

    /**
     * {@code runnerGroups} runner groups list
     **/
    private final ArrayList<RunnerGroup> runnerGroups;

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param runnerGroups : runner groups list
     **/
    public RunnerGroupsList(ArrayList<RunnerGroup> runnerGroups) {
        super(runnerGroups.size());
        this.runnerGroups = runnerGroups;
    }

    /**
     * Constructor to init an {@link RunnerGroupsList}
     *
     * @param totalCount   : total number of the runner groups in the list
     * @param runnerGroups : runner groups list
     **/
    public RunnerGroupsList(int totalCount, ArrayList<RunnerGroup> runnerGroups) {
        super(totalCount);
        this.runnerGroups = runnerGroups;
    }

    /**
     * Constructor to init a {@link RunnerGroupsList}
     *
     * @param jRunnerGroupsList : runner groups list details as {@link JSONObject}
     **/
    public RunnerGroupsList(JSONObject jRunnerGroupsList) {
        super(jRunnerGroupsList);
        runnerGroups = new ArrayList<>();
        JSONArray jRunnerGroups = hResponse.getJSONArray("runner_groups", new JSONArray());
        for (int j = 0; j < jRunnerGroups.length(); j++)
            runnerGroups.add(new RunnerGroup(jRunnerGroups.getJSONObject(j)));
    }

    /**
     * Method to get {@link #runnerGroups} instance <br>
     * No-any params required
     *
     * @return {@link #runnerGroups} instance as {@link ArrayList} of {@link RunnerGroup}
     **/
    public ArrayList<RunnerGroup> getRunnerGroups() {
        return runnerGroups;
    }

}
