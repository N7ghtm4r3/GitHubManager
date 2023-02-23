package com.tecknobit.githubmanager.actions.workflow.jobs.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Job} class is useful to format a GitHub's job
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
 * Get a job for a workflow run</a>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Job extends BaseResponseDetails {

    /**
     * {@code name} the id of the associated workflow run
     **/
    private final long runId;

    /**
     * {@code runUrl} run url value
     **/
    private final String runUrl;

    /**
     * {@code nodeId} the id of the node
     **/
    private final String nodeId;

    /**
     * {@code headSha} the {@code "SHA"} of the commit that is being run
     **/
    private final String headSha;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code status} the phase of the lifecycle that the job is currently in
     **/
    private final Status status;

    /**
     * {@code conclusion} the outcome of the job
     **/
    private final Conclusion conclusion;

    /**
     * {@code startedAt} the time that the job started, in ISO 8601 format
     **/
    private final String startedAt;

    /**
     * {@code completedAt} the time that the job finished, in ISO 8601 format
     **/
    private final String completedAt;

    /**
     * {@code steps} steps in this job
     **/
    private final ArrayList<Step> steps;

    /**
     * {@code checkRunUrl} check run url value
     **/
    private final String checkRunUrl;

    /**
     * {@code labels} labels for the workflow job. Specified by the {@code "runs_on"} attribute in the action's workflow file
     **/
    private final ArrayList<String> labels;

    /**
     * {@code runnerId} the {@code "ID"} of the runner to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     **/
    private final long runnerId;

    /**
     * {@code runnerName} the name of the runner to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     **/
    private final String runnerName;

    /**
     * {@code runnerGroupId} the {@code "ID"} of the runner group to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     **/
    private final long runnerGroupId;

    /**
     * {@code runnerGroupName} the name of the runner group to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     **/
    private final String runnerGroupName;

    /**
     * Constructor to init a {@link Job}
     *
     * @param id :the id of the job
     * @param runId : the id of the associated workflow run
     * @param runUrl : run url value
     * @param nodeId : the id of the node
     * @param headSha : the {@code "SHA"} of the commit that is being run
     * @param url: url value
     * @param htmlUrl : html url value
     * @param status :the phase of the lifecycle that the job is currently in
     * @param conclusion : the outcome of the job
     * @param startedAt : the time that the job started, in ISO 8601 format
     * @param completedAt : the time that the job finished, in ISO 8601 format
     * @param name :the name of the job
     * @param steps :steps in this job
     * @param checkRunUrl : check run url value
     * @param labels : labels for the workflow job. Specified by the {@code "runs_on"} attribute in the action's workflow file
     * @param runnerId : the {@code "ID"} of the runner to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     * @param runnerName : the name of the runner to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     * @param runnerGroupId : the {@code "ID"} of the runner group to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     * @param runnerGroupName : the name of the runner group to which this job has been assigned. (If a runner hasn't yet been assigned, this will be null)
     **/
    public Job(long id, long runId, String runUrl, String nodeId, String headSha, String url, String htmlUrl,
               Status status, Conclusion conclusion, String startedAt, String completedAt, String name,
               ArrayList<Step> steps, String checkRunUrl, ArrayList<String> labels, long runnerId, String runnerName,
               long runnerGroupId, String runnerGroupName) {
        super(id, name, url);
        this.runId = runId;
        this.runUrl = runUrl;
        this.nodeId = nodeId;
        this.headSha = headSha;
        this.htmlUrl = htmlUrl;
        this.status = status;
        this.conclusion = conclusion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.steps = steps;
        this.checkRunUrl = checkRunUrl;
        this.labels = labels;
        this.runnerId = runnerId;
        this.runnerName = runnerName;
        this.runnerGroupId = runnerGroupId;
        this.runnerGroupName = runnerGroupName;
    }

    /**
     * Constructor to init a {@link Job}
     *
     * @param jWorkflow : workflow details as {@link JSONObject}
     **/
    public Job(JSONObject jWorkflow) {
        super(jWorkflow);
        runId = hResponse.getLong("run_id", 0);
        runUrl = hResponse.getString("run_url");
        nodeId = hResponse.getString("node_id");
        headSha = hResponse.getString("head_sha");
        htmlUrl = hResponse.getString("html_url");
        status = Status.valueOf(hResponse.getString("status", Status.in_progress.toString()));
        conclusion = Conclusion.valueOf(hResponse.getString("conclusion"));
        startedAt = hResponse.getString("started_at");
        completedAt = hResponse.getString("completed_at");
        steps = new ArrayList<>();
        JSONArray jSteps = hResponse.getJSONArray("steps", new JSONArray());
        for (int j = 0; j < jSteps.length(); j++)
            steps.add(new Step(jSteps.getJSONObject(j)));
        checkRunUrl = hResponse.getString("check_run_url");
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(jLabels.getString(j));
        runnerId = hResponse.getLong("runner_id", 0);
        runnerName = hResponse.getString("runner_name");
        runnerGroupId = hResponse.getLong("runner_group_id", 0);
        runnerGroupName = hResponse.getString("runner_group_name");
    }

    /**
     * Method to get {@link #runId} instance <br>
     * No-any params required
     *
     * @return {@link #runId} instance as long
     **/
    public long getRunId() {
        return runId;
    }

    /**
     * Method to get {@link #runUrl} instance <br>
     * No-any params required
     *
     * @return {@link #runUrl} instance as {@link String}
     **/
    public String getRunUrl() {
        return runUrl;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #headSha} instance <br>
     * No-any params required
     *
     * @return {@link #headSha} instance as {@link String}
     **/
    public String getHeadSha() {
        return headSha;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link Status}
     **/
    public Status getStatus() {
        return status;
    }

    /**
     * Method to get {@link #conclusion} instance <br>
     * No-any params required
     *
     * @return {@link #conclusion} instance as {@link Conclusion}
     **/
    public Conclusion getConclusion() {
        return conclusion;
    }

    /**
     * Method to get {@link #startedAt} instance <br>
     * No-any params required
     *
     * @return {@link #startedAt} instance as {@link String}
     **/
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * Method to get {@link #steps} instance <br>
     * No-any params required
     *
     * @return {@link #steps} instance as {@link ArrayList} of {@link Step}
     **/
    public ArrayList<Step> getSteps() {
        return steps;
    }

    /**
     * Method to get {@link #startedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #startedAt} timestamp as long
     **/
    public long getStartedAtTimestamp() {
        return getDateTimestamp(startedAt);
    }

    public String getCompletedAt() {
        return completedAt;
    }

    /**
     * Method to get {@link #completedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #completedAt} timestamp as long
     **/
    public long getCompletedAtTimestamp() {
        return getDateTimestamp(completedAt);
    }

    /**
     * Method to get {@link #checkRunUrl} instance <br>
     * No-any params required
     *
     * @return {@link #checkRunUrl} instance as {@link String}
     **/
    public String getCheckRunUrl() {
        return checkRunUrl;
    }

    /**
     * Method to get {@link #labels} instance <br>
     * No-any params required
     *
     * @return {@link #labels} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getLabels() {
        return labels;
    }

    /**
     * Method to get {@link #runnerId} instance <br>
     * No-any params required
     *
     * @return {@link #runnerId} instance as long
     **/
    public long getRunnerId() {
        return runnerId;
    }

    /**
     * Method to get {@link #runnerName} instance <br>
     * No-any params required
     *
     * @return {@link #runnerName} instance as {@link String}
     **/
    public String getRunnerName() {
        return runnerName;
    }

    /**
     * Method to get {@link #runnerGroupId} instance <br>
     * No-any params required
     *
     * @return {@link #runnerGroupId} instance as long
     **/
    public long getRunnerGroupId() {
        return runnerGroupId;
    }

    /**
     * Method to get {@link #runnerGroupName} instance <br>
     * No-any params required
     *
     * @return {@link #runnerGroupName} instance as {@link String}
     **/
    public String getRunnerGroupName() {
        return runnerGroupName;
    }

    /**
     * {@code Status} list of statuses for a job
     **/
    public enum Status {

        /**
         * {@code queued} queued status
         **/
        queued,

        /**
         * {@code in_progress} in progress status
         **/
        in_progress,

        /**
         * {@code completed} completed status
         **/
        completed

    }

    /**
     * {@code Conclusion} list of conclusions for a job
     **/
    public enum Conclusion {

        /**
         * {@code success} success conclusion
         **/
        success,

        /**
         * {@code failure} failure conclusion
         **/
        failure,

        /**
         * {@code neutral} neutral conclusion
         **/
        neutral,

        /**
         * {@code cancelled} cancelled conclusion
         **/
        cancelled,

        /**
         * {@code skipped} skipped conclusion
         **/
        skipped,

        /**
         * {@code timed_out} timed out conclusion
         **/
        timed_out,

        /**
         * {@code action_required} action required conclusion
         **/
        action_required,

    }

    /**
     * The {@code Step} class is useful to format a GitHub's step
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-jobs#get-a-job-for-a-workflow-run">
     * Get a job for a workflow run</a>
     * @see GitHubResponse
     * @see InnerClassItem
     **/
    public static class Step extends InnerClassItem {

        /**
         * {@code name} the name of the job
         **/
        private final String name;

        /**
         * {@code status} the phase of the lifecycle that the job is currently in
         **/
        private final Status status;

        /**
         * {@code conclusion} the outcome of the job
         **/
        private final Conclusion conclusion;

        /**
         * {@code number} number value for the step
         **/
        private final int number;

        /**
         * {@code startedAt} the time that the job started, in ISO 8601 format
         **/
        private final String startedAt;

        /**
         * {@code completedAt} the time that the job finished, in ISO 8601 format
         **/
        private final String completedAt;

        /**
         * Constructor to init a {@link Step}
         *
         * @param name        :the name of the job
         * @param status      :the phase of the lifecycle that the job is currently in
         * @param conclusion  : the outcome of the job
         * @param number:     number value for the step
         * @param startedAt   : the time that the job started, in ISO 8601 format
         * @param completedAt : the time that the job finished, in ISO 8601 format
         **/
        public Step(String name, Status status, Conclusion conclusion, int number, String startedAt, String completedAt) {
            super(null);
            this.name = name;
            this.status = status;
            this.conclusion = conclusion;
            this.number = number;
            this.startedAt = startedAt;
            this.completedAt = completedAt;
        }

        /**
         * Constructor to init a {@link Step}
         *
         * @param jStep : step details as {@link JSONObject}
         **/
        public Step(JSONObject jStep) {
            super(jStep);
            name = hItem.getString("name");
            status = Status.valueOf(hItem.getString("status", Status.in_progress.toString()));
            conclusion = Conclusion.valueOf(hItem.getString("conclusion"));
            number = hItem.getInt("number", 0);
            startedAt = hItem.getString("started_at");
            completedAt = hItem.getString("completed_at");
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #status} instance <br>
         * No-any params required
         *
         * @return {@link #status} instance as {@link Status}
         **/
        public Status getStatus() {
            return status;
        }

        /**
         * Method to get {@link #conclusion} instance <br>
         * No-any params required
         *
         * @return {@link #conclusion} instance as {@link Conclusion}
         **/
        public Conclusion getConclusion() {
            return conclusion;
        }

        /**
         * Method to get {@link #number} instance <br>
         * No-any params required
         *
         * @return {@link #number} instance as int
         **/
        public int getNumber() {
            return number;
        }

        /**
         * Method to get {@link #startedAt} instance <br>
         * No-any params required
         *
         * @return {@link #startedAt} instance as {@link String}
         **/
        public String getStartedAt() {
            return startedAt;
        }

        /**
         * Method to get {@link #startedAt} timestamp <br>
         * No-any params required
         *
         * @return {@link #startedAt} timestamp as long
         **/
        public long getStartedAtTimestamp() {
            return getDateTimestamp(startedAt);
        }

        /**
         * Method to get {@link #completedAt} instance <br>
         * No-any params required
         *
         * @return {@link #completedAt} instance as {@link String}
         **/
        public String getCompletedAt() {
            return completedAt;
        }

        /**
         * Method to get {@link #completedAt} timestamp <br>
         * No-any params required
         *
         * @return {@link #completedAt} timestamp as long
         **/
        public long getCompletedAtTimestamp() {
            return getDateTimestamp(completedAt);
        }

    }

}
