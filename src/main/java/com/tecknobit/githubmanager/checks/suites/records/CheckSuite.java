package com.tecknobit.githubmanager.checks.suites.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.checks.records.Check;
import com.tecknobit.githubmanager.commits.commits.records.pullrequests.MinimalPullRequest;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code CheckSuite} class is useful to format a GitHub's check suite
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/suites#create-a-check-suite">
 *             Create a check suite</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/checks/suites#get-a-check-suite">
 *             Get a check suite</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 * @see Check
 **/
public class CheckSuite extends Check {

    /**
     * {@code headBranch} head branch of the check suite
     **/
    private final String headBranch;

    /**
     * {@code conclusion} of the check suite
     **/
    private final CheckSuiteConclusion conclusion;

    /**
     * {@code before} value of the check suite
     **/
    private final String before;

    /**
     * {@code after} value of the check suite
     **/
    private final String after;

    /**
     * {@code createdAt} creation date of the check suite
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update date of the check suite
     **/
    private final String updatedAt;

    /**
     * {@code repository} of the check suite
     **/
    private final CompleteRepository repository;

    /**
     * {@code latestCheckRunsCount} latest check runs count of the check suite
     **/
    private final int latestCheckRunsCount;

    /**
     * {@code checkRunsUrl} check runs url of the check suite
     **/
    private final String checkRunsUrl;

    /**
     * {@code rerequestable} whether check suite is requestable
     **/
    private final boolean rerequestable;

    /**
     * {@code runsRerequestable} whether check runs of the suite are requestable
     **/
    private final boolean runsRerequestable;

    /**
     * Constructor to init a {@link CheckSuite}
     *
     * @param id                   : identifier value
     * @param name                 : the name of the item
     * @param url                  : url value
     * @param headSha              : the SHA of the commit that is being checked
     * @param nodeId               : node identifier value
     * @param status               : the phase of the lifecycle that the check is currently in
     * @param pullRequests         : pull requests list of the check suites
     * @param app                  : app of the check suites
     * @param headBranch           : head branch of the check suite
     * @param conclusion           : conclusion of the check suite
     * @param before               : before value of the check suite
     * @param after                : after value of the check suite
     * @param createdAt            : creation date of the check suite
     * @param updatedAt            : update date of the check suite
     * @param repository           : repository of the check suite
     * @param latestCheckRunsCount : latest check runs count of the check suite
     * @param checkRunsUrl         : check runs url of the check suite
     * @param rerequestable        : whether check suite is requestable
     * @param runsRerequestable    : whether check runs of the suite are requestable
     **/
    public CheckSuite(long id, String name, String url, String headSha, String nodeId, CheckStatus status,
                      ArrayList<MinimalPullRequest> pullRequests, GitHubApp app, String headBranch,
                      CheckSuiteConclusion conclusion, String before, String after, String createdAt, String updatedAt,
                      CompleteRepository repository, int latestCheckRunsCount, String checkRunsUrl, boolean rerequestable,
                      boolean runsRerequestable) {
        super(id, name, url, headSha, nodeId, status, pullRequests, app);
        this.headBranch = headBranch;
        this.conclusion = conclusion;
        this.before = before;
        this.after = after;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.repository = repository;
        this.latestCheckRunsCount = latestCheckRunsCount;
        this.checkRunsUrl = checkRunsUrl;
        this.rerequestable = rerequestable;
        this.runsRerequestable = runsRerequestable;
    }

    /**
     * Constructor to init a {@link CheckSuite}
     *
     * @param jCheckSuite : check suite details as {@link JSONObject}
     **/
    public CheckSuite(JSONObject jCheckSuite) throws Exception {
        super(jCheckSuite);
        headBranch = hResponse.getString("head_branch");
        String sConclusion = hResponse.getString("conclusion");
        if (sConclusion != null)
            conclusion = CheckSuiteConclusion.valueOf(sConclusion);
        else
            conclusion = null;
        before = hResponse.getString("before");
        after = hResponse.getString("after");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        repository = new CompleteRepository(hResponse.getJSONObject("repository", new JSONObject()));
        latestCheckRunsCount = hResponse.getInt("latest_check_runs_count", 0);
        checkRunsUrl = hResponse.getString("check_runs_url");
        rerequestable = hResponse.getBoolean("rerequestable");
        runsRerequestable = hResponse.getBoolean("runs_rerequestable");
    }

    /**
     * Method to get {@link #headBranch} instance <br>
     * No-any params required
     *
     * @return {@link #headBranch} instance as {@link String}
     **/
    public String getHeadBranch() {
        return headBranch;
    }

    /**
     * Method to get {@link #conclusion} instance <br>
     * No-any params required
     *
     * @return {@link #conclusion} instance as {@link String}
     **/
    public CheckSuiteConclusion getConclusion() {
        return conclusion;
    }

    /**
     * Method to get {@link #before} instance <br>
     * No-any params required
     *
     * @return {@link #before} instance as {@link String}
     **/
    public String getBefore() {
        return before;
    }

    /**
     * Method to get {@link #after} instance <br>
     * No-any params required
     *
     * @return {@link #after} instance as {@link String}
     **/
    public String getAfter() {
        return after;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link CompleteRepository}
     **/
    public CompleteRepository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #latestCheckRunsCount} instance <br>
     * No-any params required
     *
     * @return {@link #latestCheckRunsCount} instance as int
     **/
    public int getLatestCheckRunsCount() {
        return latestCheckRunsCount;
    }

    /**
     * Method to get {@link #checkRunsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #checkRunsUrl} instance as {@link String}
     **/
    public String getCheckRunsUrl() {
        return checkRunsUrl;
    }

    /**
     * Method to get {@link #rerequestable} instance <br>
     * No-any params required
     *
     * @return {@link #rerequestable} instance as boolean
     **/
    public boolean isRerequestable() {
        return rerequestable;
    }

    /**
     * Method to get {@link #runsRerequestable} instance <br>
     * No-any params required
     *
     * @return {@link #runsRerequestable} instance as boolean
     **/
    public boolean areRunsRerequestable() {
        return runsRerequestable;
    }

    /**
     * {@code CheckSuiteConclusion} list of available conclusion for a {@link CheckSuite}
     **/
    public enum CheckSuiteConclusion {

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
        action_required,

        /**
         * {@code "startup_failure"} conclusion
         **/
        startup_failure,

        /**
         * {@code "stale"} conclusion
         **/
        stale

    }

}
