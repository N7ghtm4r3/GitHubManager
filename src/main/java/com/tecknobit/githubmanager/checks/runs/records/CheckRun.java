package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.checks.records.Check;
import com.tecknobit.githubmanager.checks.suites.records.CheckSuite;
import com.tecknobit.githubmanager.pulls.pulls.records.MinimalPullRequest;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;

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
 * @see Check
 **/
public class CheckRun extends Check {

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
                    String detailsUrl, CheckStatus status, CheckRunConclusion conclusion, String startedAt,
                    String completedAt, Output output, CheckSuite checkSuite, GitHubApp app,
                    ArrayList<MinimalPullRequest> pullRequests) {
        super(id, name, url, headSha, nodeId, status, pullRequests, app);
        this.externalId = externalId;
        this.htmlUrl = htmlUrl;
        this.detailsUrl = detailsUrl;
        this.conclusion = conclusion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.output = output;
        this.checkSuite = checkSuite;
    }

    /**
     * Constructor to init a {@link CheckRun}
     *
     * @param jCheckRun : check run details as {@link JSONObject}
     **/
    public CheckRun(JSONObject jCheckRun) throws Exception {
        super(jCheckRun);
        externalId = hResponse.getLong("external_id", 0);
        htmlUrl = hResponse.getString("html_url");
        detailsUrl = hResponse.getString("details_url");
        String sConclusion = hResponse.getString("conclusion");
        if (sConclusion != null)
            conclusion = CheckRunConclusion.valueOf(sConclusion);
        else
            conclusion = null;
        startedAt = hResponse.getString("started_at");
        completedAt = hResponse.getString("completed_at");
        output = new Output(hResponse.getJSONObject("output", new JSONObject()));
        checkSuite = new CheckSuite(hResponse.getJSONObject("check_suite", new JSONObject()));
    }

    /**
     * Method to get {@link #externalId} instance <br>
     * No-any params required
     *
     * @return {@link #externalId} instance as long
     **/
    public long getExternalId() {
        return externalId;
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
     * Method to get {@link #detailsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #detailsUrl} instance as {@link String}
     **/
    public String getDetailsUrl() {
        return detailsUrl;
    }

    /**
     * Method to get {@link #conclusion} instance <br>
     * No-any params required
     *
     * @return {@link #conclusion} instance as {@link CheckRunConclusion}
     **/
    public CheckRunConclusion getConclusion() {
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

    /**
     * Method to get {@link #output} instance <br>
     * No-any params required
     *
     * @return {@link #output} instance as {@link Output}
     **/
    public Output getOutput() {
        return output;
    }

    /**
     * Method to get {@link #checkSuite} instance <br>
     * No-any params required
     *
     * @return {@link #checkSuite} instance as {@link CheckSuite}
     **/
    public CheckSuite getCheckSuite() {
        return checkSuite;
    }

    /**
     * {@code CheckSuiteConclusion} list of available conclusion for a {@link CheckRun}
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
     * @see InnerClassItem
     **/
    public static class Output extends InnerClassItem {

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
            super(null);
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
            super(jOutput);
            title = hItem.getString("title");
            summary = hItem.getString("summary");
            text = hItem.getString("text");
            annotationsCount = hItem.getInt("annotations_count", 0);
            annotationsUrl = hItem.getString("annotations_url");
        }

        /**
         * Method to get {@link #title} instance <br>
         * No-any params required
         *
         * @return {@link #title} instance as {@link String}
         **/
        public String getTitle() {
            return title;
        }

        /**
         * Method to get {@link #summary} instance <br>
         * No-any params required
         *
         * @return {@link #summary} instance as {@link String}
         **/
        public String getSummary() {
            return summary;
        }

        /**
         * Method to get {@link #text} instance <br>
         * No-any params required
         *
         * @return {@link #text} instance as {@link String}
         **/
        public String getText() {
            return text;
        }

        /**
         * Method to get {@link #annotationsCount} instance <br>
         * No-any params required
         *
         * @return {@link #annotationsCount} instance as int
         **/
        public int getAnnotationsCount() {
            return annotationsCount;
        }

        /**
         * Method to get {@link #annotationsUrl} instance <br>
         * No-any params required
         *
         * @return {@link #annotationsUrl} instance as {@link String}
         **/
        public String getAnnotationsUrl() {
            return annotationsUrl;
        }

    }

}
