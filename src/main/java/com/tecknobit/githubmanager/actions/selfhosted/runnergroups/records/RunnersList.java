package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class RunnersList extends GitHubList {

    private final ArrayList<Runner> runners;

    /**
     * Constructor to init an {@link RunnersList}
     *
     * @param runners: runners list
     **/
    public RunnersList(ArrayList<Runner> runners) {
        super(runners.size());
        this.runners = runners;
    }

    /**
     * Constructor to init an {@link RunnersList}
     *
     * @param totalCount : total number of the runners in the list
     * @param runners:   runners list
     **/
    public RunnersList(int totalCount, ArrayList<Runner> runners) {
        super(totalCount);
        this.runners = runners;
    }

    /**
     * Constructor to init a {@link RunnersList}
     *
     * @param jRunnersList : runners list details as {@link JSONObject}
     **/
    public RunnersList(JSONObject jRunnersList) {
        super(jRunnersList);
        runners = new ArrayList<>();
        JSONArray jRunners = hResponse.getJSONArray("runners", new JSONArray());
        for (int j = 0; j < jRunners.length(); j++)
            runners.add(new Runner(jRunners.getJSONObject(j)));
    }

    public Collection<Runner> getRunners() {
        return runners;
    }

}
