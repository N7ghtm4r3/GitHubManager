package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class CheckRunsList extends GitHubList {

    private final ArrayList<CheckRun> checkRuns;

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param checkRuns : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CheckRunsList(ArrayList<CheckRun> checkRuns) {
        super(checkRuns.size());
        this.checkRuns = checkRuns;
    }

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount : total number of the items in the list
     * @param checkRuns:
     **/
    public CheckRunsList(int totalCount, ArrayList<CheckRun> checkRuns) {
        super(totalCount);
        this.checkRuns = checkRuns;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jCheckRuns : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CheckRunsList(JSONObject jCheckRuns) throws Exception {
        super(jCheckRuns);
        checkRuns = new ArrayList<>();
        JSONArray jCheckRunsList = hResponse.getJSONArray("check_runs", new JSONArray());
        for (int j = 0; j < jCheckRunsList.length(); j++)
            checkRuns.add(new CheckRun(jCheckRunsList.getJSONObject(j)));
    }

    public Collection<CheckRun> getCheckRuns() {
        return checkRuns;
    }

}
