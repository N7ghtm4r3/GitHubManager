package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class RunnerGroupsList extends GitHubList {

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

    public Collection<RunnerGroup> getRunnerGroups() {
        return runnerGroups;
    }

}
