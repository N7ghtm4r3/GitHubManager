package com.tecknobit.githubmanager.actions.workflow.jobs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class Job extends GitHubResponse {

    private final long id;
    private final long runId;
    private final String runUrl;
    private final String nodeId;
    private final String headSha;
    private final String url;
    private final String htmlUrl;
    private final String status;
    private final String conclusion;
    private final String startedAt;
    private final String completedAt;
    private final String name;
    private final ArrayList<Step> steps;
    private final String checkRunUrl;
    private final ArrayList<String> labels;
    private final long runnerId;
    private final String runnerName;
    private final long runnerGroupId;
    private final String runnerGroupName;

    public Job(long id, long runId, String runUrl, String nodeId, String headSha, String url, String htmlUrl,
               String status, String conclusion, String startedAt, String completedAt, String name,
               ArrayList<Step> steps, String checkRunUrl, ArrayList<String> labels, long runnerId, String runnerName,
               long runnerGroupId, String runnerGroupName) {
        super(null);
        this.id = id;
        this.runId = runId;
        this.runUrl = runUrl;
        this.nodeId = nodeId;
        this.headSha = headSha;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.status = status;
        this.conclusion = conclusion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.name = name;
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
        id = hResponse.getLong("id", 0);
        runId = hResponse.getLong("run_id", 0);
        runUrl = hResponse.getString("run_url");
        nodeId = hResponse.getString("node_id");
        headSha = hResponse.getString("head_sha");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        status = hResponse.getString("status");
        conclusion = hResponse.getString("conclusion");
        startedAt = hResponse.getString("started_at");
        completedAt = hResponse.getString("completed_at");
        name = hResponse.getString("name");
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

    public long getId() {
        return id;
    }

    public long getRunId() {
        return runId;
    }

    public String getRunUrl() {
        return runUrl;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getHeadSha() {
        return headSha;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getConclusion() {
        return conclusion;
    }

    public String getStartedAt() {
        return startedAt;
    }

    /**
     * Method to get {@link #startedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #startedAt} timestamp as long
     **/
    public long getStartedAtTimestamp() {
        try {
            return dateFormatter.parse(startedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public String getCompletedAt() {
        return completedAt;
    }

    /**
     * Method to get {@link #completedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #completedAt} timestamp as long
     **/
    public long getCompletedAtTimestamp() {
        try {
            return dateFormatter.parse(completedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public Collection<Step> getSteps() {
        return steps;
    }

    public String getCheckRunUrl() {
        return checkRunUrl;
    }

    public Collection<String> getLabels() {
        return labels;
    }

    public long getRunnerId() {
        return runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public long getRunnerGroupId() {
        return runnerGroupId;
    }

    public String getRunnerGroupName() {
        return runnerGroupName;
    }

    public static class Step {

        private final String name;
        private final String status;
        private final String conclusion;
        private final int number;
        private final String startedAt;
        private final String completedAt;

        public Step(String name, String status, String conclusion, int number, String startedAt, String completedAt) {
            this.name = name;
            this.status = status;
            this.conclusion = conclusion;
            this.number = number;
            this.startedAt = startedAt;
            this.completedAt = completedAt;
        }

        public Step(JSONObject jStep) {
            JsonHelper hStep = new JsonHelper(jStep);
            name = hStep.getString("name");
            status = hStep.getString("status");
            conclusion = hStep.getString("conclusion");
            number = hStep.getInt("number", 0);
            startedAt = hStep.getString("started_at");
            completedAt = hStep.getString("completed_at");
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }

        public String getConclusion() {
            return conclusion;
        }

        public int getNumber() {
            return number;
        }

        public String getStartedAt() {
            return startedAt;
        }

        /**
         * Method to get {@link #startedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #startedAt} timestamp as long
         **/
        public long getStartedAtTimestamp() {
            try {
                return dateFormatter.parse(startedAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        public String getCompletedAt() {
            return completedAt;
        }

        /**
         * Method to get {@link #completedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #completedAt} timestamp as long
         **/
        public long getCompletedAtTimestamp() {
            try {
                return dateFormatter.parse(completedAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
