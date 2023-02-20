package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.githubmanager.actions.workflow.records.WorkflowUsage.Billable;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code WorkflowRunUsage} class is useful to format a GitHub's workflow run usage
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/actions/workflow-runs#get-workflow-run-usage">
 * Get workflow run usage</a>
 * @see GitHubResponse
 **/
public class WorkflowRunUsage extends GitHubResponse {

    /**
     * {@code billables} billables list
     **/
    private final ArrayList<BillableRun> billables;

    /**
     * {@code runDurationMs} run duration in milliseconds
     **/
    private final long runDurationMs;

    /**
     * Constructor to init a {@link WorkflowRunUsage}
     *
     * @param billables:     billables list
     * @param runDurationMs: run duration in milliseconds
     **/
    public WorkflowRunUsage(ArrayList<BillableRun> billables, long runDurationMs) {
        super(null);
        this.billables = billables;
        this.runDurationMs = runDurationMs;
    }

    /**
     * Constructor to init a {@link WorkflowRunUsage}
     *
     * @param jWorkflowRunUsage : workflow run usage by {@code "GitHub"} as {@link JSONObject}
     **/
    public WorkflowRunUsage(JSONObject jWorkflowRunUsage) {
        super(jWorkflowRunUsage);
        billables = new ArrayList<>();
        JSONObject jBillable = hResponse.getJSONObject("billable", new JSONObject());
        for (String key : jBillable.keySet())
            billables.add(new BillableRun(jBillable.getJSONObject(key).put("name", key)));
        runDurationMs = hResponse.getLong("run_duration_ms", 0);
    }

    /**
     * Method to get {@link #billables} instance <br>
     * No-any params required
     *
     * @return {@link #billables} instance as {@link Collection} of {@link BillableRun}
     **/
    public Collection<BillableRun> getBillables() {
        return billables;
    }

    /**
     * Method to get {@link #runDurationMs} instance <br>
     * No-any params required
     *
     * @return {@link #runDurationMs} instance as long
     **/
    public long getRunDurationMs() {
        return runDurationMs;
    }

    /**
     * The {@code BillableRun} class is useful to format a GitHub's billable run
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     * @see Billable
     **/
    public static class BillableRun extends Billable {

        /**
         * {@code jobs} jobs counter value
         **/
        private final int jobs;

        /**
         * {@code jobRuns} job runs list
         **/
        private final ArrayList<JobRun> jobRuns;

        /**
         * Constructor to init a {@link BillableRun}
         *
         * @param name:    name of the billable
         * @param totalMs: total run duration in milliseconds
         * @param jobs:    jobs counter value
         * @param jobRuns: job runs list
         **/
        public BillableRun(String name, long totalMs, int jobs, ArrayList<JobRun> jobRuns) {
            super(name, totalMs);
            this.jobs = jobs;
            this.jobRuns = jobRuns;
        }

        /**
         * Constructor to init a {@link BillableRun}
         *
         * @param jBillableRun: billable run details as {@link JSONObject}
         **/
        public BillableRun(JSONObject jBillableRun) {
            super(jBillableRun);
            jobs = hItem.getInt("jobs", 0);
            jobRuns = new ArrayList<>();
            JSONArray jJobRuns = hItem.getJSONArray("job_runs", new JSONArray());
            for (int j = 0; j < jJobRuns.length(); j++)
                jobRuns.add(new JobRun(jJobRuns.getJSONObject(j)));
        }

        /**
         * Method to get {@link #jobs} instance <br>
         * No-any params required
         *
         * @return {@link #jobs} instance as int
         **/
        public int getJobs() {
            return jobs;
        }

        /**
         * Method to get {@link #jobRuns} instance <br>
         * No-any params required
         *
         * @return {@link #jobRuns} instance as {@link Collection} of {@link JobRun}
         **/
        public Collection<JobRun> getJobRuns() {
            return jobRuns;
        }

        /**
         * The {@code JobRun} class is useful to format a GitHub's job run
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class JobRun extends InnerClassItem {

            /**
             * {@code jobId} job identifier
             **/
            private final long jobId;

            /**
             * {@code durationMs} duration in milliseconds value
             **/
            private final long durationMs;

            /**
             * Constructor to init a {@link JobRun}
             *
             * @param jobId:      job identifier
             * @param durationMs: duration in milliseconds value
             **/
            public JobRun(long jobId, long durationMs) {
                super(null);
                this.jobId = jobId;
                this.durationMs = durationMs;
            }

            /**
             * Constructor to init a {@link JobRun}
             *
             * @param jJobRun: job run details as {@link JSONObject}
             **/
            public JobRun(JSONObject jJobRun) {
                super(jJobRun);
                jobId = hItem.getLong("job_id", 0);
                durationMs = hItem.getLong("duration_ms", 0);
            }

            /**
             * Method to get {@link #jobId} instance <br>
             * No-any params required
             *
             * @return {@link #jobId} instance as long
             **/
            public long getJobId() {
                return jobId;
            }

            /**
             * Method to get {@link #durationMs} instance <br>
             * No-any params required
             *
             * @return {@link #durationMs} instance as long
             **/
            public long getDurationMs() {
                return durationMs;
            }

        }

    }

}
