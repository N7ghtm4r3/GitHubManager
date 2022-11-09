package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.records.WorkflowUsage.Billable;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorkflowRunUsage extends GitHubResponse {

    private final ArrayList<BillableRun> billables;
    private final long runDurationMs;

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

    public Collection<BillableRun> getBillables() {
        return billables;
    }

    public long getRunDurationMs() {
        return runDurationMs;
    }

    public static class BillableRun extends Billable {
        private final int jobs;
        private final ArrayList<JobRun> jobRuns;

        public BillableRun(String name, long totalMs, int jobs, ArrayList<JobRun> jobRuns) {
            super(name, totalMs);
            this.jobs = jobs;
            this.jobRuns = jobRuns;
        }

        public BillableRun(JSONObject jBillable) {
            super(jBillable);
            jobs = hBillable.getInt("jobs", 0);
            jobRuns = new ArrayList<>();
            JSONArray jJobRuns = hBillable.getJSONArray("job_runs", new JSONArray());
            for (int j = 0; j < jJobRuns.length(); j++)
                jobRuns.add(new JobRun(jJobRuns.getJSONObject(j)));
        }

        public int getJobs() {
            return jobs;
        }

        public Collection<JobRun> getJobRuns() {
            return jobRuns;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public static class JobRun {

            private final long jobId;
            private final long durationMs;

            public JobRun(long jobId, long durationMs) {
                this.jobId = jobId;
                this.durationMs = durationMs;
            }

            public JobRun(JSONObject jJobRun) {
                JsonHelper hJobRun = new JsonHelper(jJobRun);
                jobId = hJobRun.getLong("job_id", 0);
                durationMs = hJobRun.getLong("duration_ms", 0);
            }

            public long getJobId() {
                return jobId;
            }

            public long getDurationMs() {
                return durationMs;
            }

            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

}
