package com.tecknobit.githubmanager.actions.workflow.jobs.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class JobsList extends GitHubList {

    private final ArrayList<Job> jobs;

    /**
     * Constructor to init an {@link JobsList}
     *
     * @param jobs : jobs list
     **/
    public JobsList(ArrayList<Job> jobs) {
        super(jobs.size());
        this.jobs = jobs;
    }

    /**
     * Constructor to init an {@link JobsList}
     *
     * @param totalCount : total number of the jobs in the list
     * @param jobs       : jobs list
     **/
    public JobsList(int totalCount, ArrayList<Job> jobs) {
        super(totalCount);
        this.jobs = jobs;
    }

    /**
     * Constructor to init a {@link JobsList}
     *
     * @param jJobsList : jobs list details as {@link JSONObject}
     **/
    public JobsList(JSONObject jJobsList) {
        super(jJobsList);
        jobs = new ArrayList<>();
        JSONArray jJobs = hResponse.getJSONArray("jobs", new JSONArray());
        for (int j = 0; j < jJobs.length(); j++)
            jobs.add(new Job(jJobs.getJSONObject(j)));
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

}
