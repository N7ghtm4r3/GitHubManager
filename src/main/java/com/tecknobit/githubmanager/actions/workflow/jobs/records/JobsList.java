package com.tecknobit.githubmanager.actions.workflow.jobs.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code JobsList} class is useful to format a GitHub's jobs list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run-attempt">
 *             List jobs for a workflow run attempt</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-jobs#list-jobs-for-a-workflow-run">
 *             List jobs for a workflow run</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class JobsList extends GitHubList {

    /**
     * {@code jobs} jobs list
     **/
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

    /**
     * Method to get {@link #jobs} instance <br>
     * No-any params required
     *
     * @return {@link #jobs} instance as {@link Collection} of {@link Job}
     **/
    public Collection<Job> getJobs() {
        return jobs;
    }

}
