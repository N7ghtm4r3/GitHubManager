package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuite;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.PullRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code CheckRun} class is useful to format a GitHub's check run
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/runs#create-a-check-run">
 *             Create a check run</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/runs#get-a-check-run">
 *             Get a check run</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/runs#update-a-check-run">
 *             Update a check run</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class CheckRun extends BaseResponseDetails {

    /**
     * {@code headSha} the SHA of the commit that is being checked
     **/
    private final String headSha;

    /**
     * {@code nodeId} node identifier value
     **/
    private final String nodeId;

    /**
     * {@code externalId} external identifier value
     **/
    private final long externalId;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code detailsUrl} details url value
     **/
    private final String detailsUrl;

    /**
     * {@code status} the phase of the lifecycle that the check is currently in
     **/
    private final CheckRunStatus status;

    /**
     * {@code conclusion} the end-phase of the lifecycle that the check is currently in
     **/
    private final CheckRunConclusion conclusion;

    /**
     * {@code startedAt} start date of the check run
     **/
    private final String startedAt;

    /**
     * {@code completedAt} complete date of the check run
     **/
    private final String completedAt;

    /**
     * {@code output} of the check run
     **/
    private final Output output;

    /**
     * {@code checkSuite} check suite of the check run
     **/
    private final CheckSuite checkSuite;

    /**
     * {@code app} app of the check run
     **/
    private final GitHubApp app;

    /**
     * {@code pullRequests} pull requests list of the check run
     **/
    private final ArrayList<PullRequest> pullRequests;

    /**
     * Constructor to init a {@link CheckRun}
     *
     * @param id           : identifier value
     * @param name         : the name of the item
     * @param url          : url value
     * @param headSha      : the SHA of the commit that is being checked
     * @param nodeId       : node identifier value
     * @param externalId   : external identifier value
     * @param htmlUrl      :  html url value
     * @param detailsUrl   : details url value
     * @param status       : the phase of the lifecycle that the check is currently in
     * @param conclusion   : the end-phase of the lifecycle that the check is currently in
     * @param startedAt    : start date of the check run
     * @param completedAt  : complete date of the check run
     * @param output       : of the check run
     * @param checkSuite   : check suite of the check run
     * @param app          : app of the check run
     * @param pullRequests : pull requests list of the check run
     **/
    public CheckRun(long id, String name, String url, String headSha, String nodeId, long externalId, String htmlUrl,
                    String detailsUrl, CheckRunStatus status, CheckRunConclusion conclusion, String startedAt,
                    String completedAt, Output output, CheckSuite checkSuite, GitHubApp app,
                    ArrayList<PullRequest> pullRequests) {
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

    /**
     * Constructor to init a {@link CheckRun}
     *
     * @param jCheckRun : check run details as {@link JSONObject}
     **/
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
        checkSuite = new CheckSuite(hResponse.getJSONObject("check_suite", new JSONObject()));
    }

    /**
     * Method to get {@link #headSha} instance <br>
     * Any params required
     *
     * @return {@link #headSha} instance as {@link String}
     **/
    public String getHeadSha() {
        return headSha;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * Any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #externalId} instance <br>
     * Any params required
     *
     * @return {@link #externalId} instance as long
     **/
    public long getExternalId() {
        return externalId;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #detailsUrl} instance <br>
     * Any params required
     *
     * @return {@link #detailsUrl} instance as {@link String}
     **/
    public String getDetailsUrl() {
        return detailsUrl;
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link CheckRunStatus}
     **/
    public CheckRunStatus getStatus() {
        return status;
    }

    /**
     * Method to get {@link #conclusion} instance <br>
     * Any params required
     *
     * @return {@link #conclusion} instance as {@link CheckRunConclusion}
     **/
    public CheckRunConclusion getConclusion() {
        return conclusion;
    }

    /**
     * Method to get {@link #startedAt} instance <br>
     * Any params required
     *
     * @return {@link #startedAt} instance as {@link String}
     **/
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
        return getDateTimestamp(startedAt);
    }

    /**
     * Method to get {@link #completedAt} instance <br>
     * Any params required
     *
     * @return {@link #completedAt} instance as {@link String}
     **/
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
        return getDateTimestamp(completedAt);
    }

    /**
     * Method to get {@link #output} instance <br>
     * Any params required
     *
     * @return {@link #output} instance as {@link Output}
     **/
    public Output getOutput() {
        return output;
    }

    /**
     * Method to get {@link #checkSuite} instance <br>
     * Any params required
     *
     * @return {@link #checkSuite} instance as {@link CheckSuite}
     **/
    public CheckSuite getCheckSuite() {
        return checkSuite;
    }

    /**
     * Method to get {@link #app} instance <br>
     * Any params required
     *
     * @return {@link #app} instance as {@link GitHubApp}
     **/
    public GitHubApp getApp() {
        return app;
    }

    /**
     * Method to get {@link #pullRequests} instance <br>
     * Any params required
     *
     * @return {@link #pullRequests} instance as {@link Collection} of {@link PullRequest}
     **/
    public Collection<PullRequest> getPullRequests() {
        return pullRequests;
    }

    /**
     * {@code CheckRunStatus} list of available status for a {@link CheckRun}
     **/
    public enum CheckRunStatus {

        /**
         * {@code "queued"} status
         **/
        queued,

        /**
         * {@code "in_progress"} status
         **/
        in_progress,

        /**
         * {@code "completed"} status
         **/
        completed

    }

    /**
     * {@code CheckRunConclusion} list of available conclusion for a {@link CheckRun}
     **/
    public enum CheckRunConclusion {

        /**
         * {@code "success"} conclusion
         **/
        success,

        /**
         * {@code "failure"} conclusion
         **/
        failure,

        /**
         * {@code "neutral"} conclusion
         **/
        neutral,

        /**
         * {@code "cancelled"} conclusion
         **/
        cancelled,

        /**
         * {@code "skipped"} conclusion
         **/
        skipped,

        /**
         * {@code "timed_out"} conclusion
         **/
        timed_out,

        /**
         * {@code "action_required"} conclusion
         **/
        action_required

    }

    /**
     * {@code CheckRunFilter} list of available filter for a {@link CheckRun}
     **/
    public enum CheckRunFilter {

        /**
         * {@code "latest"} filter
         **/
        latest,

        /**
         * {@code "all"} filter
         **/
        all

    }

    /**
     * The {@code Output} class is useful to format a GitHub's output for a {@link CheckRun}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Output {

        /**
         * {@code title} of the output
         **/
        private final String title;

        /**
         * {@code summary} of the output
         **/
        private final String summary;

        /**
         * {@code text} of the output
         **/
        private final String text;

        /**
         * {@code annotationsCount} annotations count of the output
         **/
        private final int annotationsCount;

        /**
         * {@code annotationsUrl} annotations url of the output
         **/
        private final String annotationsUrl;

        /**
         * Constructor to init a {@link Output}
         *
         * @param title   : title of the output
         * @param summary : summary of the output
         * @apiNote this constructor is useful when you need to create a new {@link CheckRun}
         **/
        public Output(String title, String summary) {
            this(title, summary, null, 0, null);
        }

        /**
         * Constructor to init a {@link Output}
         *
         * @param title            : title of the output
         * @param summary          : summary of the output
         * @param text             : text of the output
         * @param annotationsCount : annotations count of the output
         * @param annotationsUrl   : annotations url of the output
         **/
        public Output(String title, String summary, String text, int annotationsCount, String annotationsUrl) {
            this.title = title;
            this.summary = summary;
            this.text = text;
            this.annotationsCount = annotationsCount;
            this.annotationsUrl = annotationsUrl;
        }

        /**
         * Constructor to init a {@link Output}
         *
         * @param jOutput : output details as {@link JSONObject}
         **/
        public Output(JSONObject jOutput) {
            JsonHelper hOutput = new JsonHelper(jOutput);
            title = hOutput.getString("title");
            summary = hOutput.getString("summary");
            text = hOutput.getString("text");
            annotationsCount = hOutput.getInt("annotations_count", 0);
            annotationsUrl = hOutput.getString("annotations_url");
        }

        /**
         * Method to get {@link #title} instance <br>
         * Any params required
         *
         * @return {@link #title} instance as {@link String}
         **/
        public String getTitle() {
            return title;
        }

        /**
         * Method to get {@link #summary} instance <br>
         * Any params required
         *
         * @return {@link #summary} instance as {@link String}
         **/
        public String getSummary() {
            return summary;
        }

        /**
         * Method to get {@link #text} instance <br>
         * Any params required
         *
         * @return {@link #text} instance as {@link String}
         **/
        public String getText() {
            return text;
        }

        /**
         * Method to get {@link #annotationsCount} instance <br>
         * Any params required
         *
         * @return {@link #annotationsCount} instance as int
         **/
        public int getAnnotationsCount() {
            return annotationsCount;
        }

        /**
         * Method to get {@link #annotationsUrl} instance <br>
         * Any params required
         *
         * @return {@link #annotationsUrl} instance as {@link String}
         **/
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
