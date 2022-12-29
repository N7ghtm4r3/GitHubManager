package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.PullRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class CheckRun extends BaseResponseDetails {

    private final String headSha;
    private final String nodeId;
    private final long externalId;
    private final String htmlUrl;
    private final String detailsUrl;
    private final CheckRunStatus status;
    private final CheckRunConclusion conclusion;
    private final String startedAt;
    private final String completedAt;
    private final Output output;
    private final long checkSuite;
    private final GitHubApp app;
    private final ArrayList<PullRequest> pullRequests;
    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url  : url value
     **/
    public CheckRun(long id, String name, String url, String headSha, String nodeId, long externalId, String htmlUrl,
                    String detailsUrl, CheckRunStatus status, CheckRunConclusion conclusion, String startedAt,
                    String completedAt, Output output, long checkSuite, GitHubApp app, ArrayList<PullRequest> pullRequests) {
        super(id, name, url);
        this.headSha = headSha;
        this.nodeId = nodeId;
        this.externalId = externalId;
        this.htmlUrl = htmlUrl;
        this.detailsUrl = detailsUrl;
        this.status = status;
        this.conclusion = conclusion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.output = output;
        this.checkSuite = checkSuite;
        this.app = app;
        this.pullRequests = pullRequests;
    }
    public CheckRun(JSONObject jCheckRun) throws Exception {
        super(jCheckRun);
        headSha = hResponse.getString("head_sha");
        nodeId = hResponse.getString("node_id");
        externalId = hResponse.getLong("external_id", 0);
        htmlUrl = hResponse.getString("html_url");
        detailsUrl = hResponse.getString("details_url");
        status = CheckRunStatus.valueOf(hResponse.getString("status"));
        String sConclusion = hResponse.getString("conclusion");
        if (sConclusion != null)
            conclusion = CheckRunConclusion.valueOf(sConclusion);
        else
            conclusion = null;
        startedAt = hResponse.getString("started_at");
        completedAt = hResponse.getString("completed_at");
        output = new Output(hResponse.getJSONObject("output", new JSONObject()));
        app = new GitHubApp(hResponse.getJSONObject("app", new JSONObject()));
        pullRequests = new ArrayList<>();
        JSONArray jPullRequests = hResponse.getJSONArray("pull_requests", new JSONArray());
        for (int j = 0; j < jPullRequests.length(); j++)
            pullRequests.add(new PullRequest(jPullRequests.getJSONObject(j)));
        hResponse.setJSONObjectSource(hResponse.getJSONObject("check_suite", new JSONObject()));
        checkSuite = hResponse.getInt("id", 0);
    }

    public String getHeadSha() {
        return headSha;
    }

    public String getNodeId() {
        return nodeId;
    }

    public long getExternalId() {
        return externalId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public CheckRunStatus getStatus() {
        return status;
    }

    public CheckRunConclusion getConclusion() {
        return conclusion;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public Output getOutput() {
        return output;
    }

    public long getCheckSuite() {
        return checkSuite;
    }

    public GitHubApp getApp() {
        return app;
    }

    public Collection<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public enum CheckRunStatus {

        queued,
        in_progress,
        completed

    }

    public enum CheckRunConclusion {

        success,
        failure,
        neutral,
        cancelled,
        skipped,
        timed_out,
        action_required

    }

    public static class Output {

        private final String title;
        private final String summary;
        private final String text;
        private final int annotationsCount;
        private final String annotationsUrl;

        public Output(String title, String summary, String text, int annotationsCount, String annotationsUrl) {
            this.title = title;
            this.summary = summary;
            this.text = text;
            this.annotationsCount = annotationsCount;
            this.annotationsUrl = annotationsUrl;
        }

        public Output(JSONObject jOutput) {
            JsonHelper hOutput = new JsonHelper(jOutput);
            title = hOutput.getString("title");
            summary = hOutput.getString("summary");
            text = hOutput.getString("text");
            annotationsCount = hOutput.getInt("annotations_count", 0);
            annotationsUrl = hOutput.getString("annotations_url");
        }

        public String getTitle() {
            return title;
        }

        public String getSummary() {
            return summary;
        }

        public String getText() {
            return text;
        }

        public int getAnnotationsCount() {
            return annotationsCount;
        }

        public String getAnnotationsUrl() {
            return annotationsUrl;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
