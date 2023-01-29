package com.tecknobit.githubmanager.checks.records;

import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.checks.runs.records.CheckRun;
import com.tecknobit.githubmanager.commits.commits.records.pullrequests.MinimalPullRequest;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code Check} class is useful to format a GitHub's check base format
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
 **/
public class Check extends BaseResponseDetails {

    /**
     * {@code headSha} the SHA of the commit that is being checked
     **/
    protected final String headSha;

    /**
     * {@code nodeId} node identifier value
     **/
    protected final String nodeId;

    /**
     * {@code status} the phase of the lifecycle that the check is currently in
     **/
    protected final CheckStatus status;

    /**
     * {@code pullRequests} pull requests list of the check run
     **/
    protected final ArrayList<MinimalPullRequest> pullRequests;

    /**
     * {@code app} app of the check run
     **/
    protected final GitHubApp app;

    /**
     * Constructor to init a {@link Check}
     *
     * @param id           : identifier value
     * @param name         : the name of the item
     * @param url          : url value
     * @param headSha      : the SHA of the commit that is being checked
     * @param nodeId       : node identifier value
     * @param status       : the phase of the lifecycle that the check is currently in
     * @param pullRequests : pull requests list of the check run
     * @param app          : app of the check run
     **/
    public Check(long id, String name, String url, String headSha, String nodeId, CheckStatus status,
                 ArrayList<MinimalPullRequest> pullRequests, GitHubApp app) {
        super(id, name, url);
        this.headSha = headSha;
        this.nodeId = nodeId;
        this.status = status;
        this.pullRequests = pullRequests;
        this.app = app;
    }

    /**
     * Constructor to init a {@link Check}
     *
     * @param jCheck : check run details as {@link JSONObject}
     **/
    public Check(JSONObject jCheck) throws Exception {
        super(jCheck);
        headSha = hResponse.getString("head_sha");
        nodeId = hResponse.getString("node_id");
        status = CheckRun.CheckStatus.valueOf(hResponse.getString("status"));
        app = new GitHubApp(hResponse.getJSONObject("app", new JSONObject()));
        pullRequests = new ArrayList<>();
        JSONArray jPullRequests = hResponse.getJSONArray("pull_requests", new JSONArray());
        for (int j = 0; j < jPullRequests.length(); j++)
            pullRequests.add(new MinimalPullRequest(jPullRequests.getJSONObject(j)));
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
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link CheckStatus}
     **/
    public CheckStatus getStatus() {
        return status;
    }

    /**
     * Method to get {@link #app} instance <br>
     * No-any params required
     *
     * @return {@link #app} instance as {@link GitHubApp}
     **/
    public GitHubApp getApp() {
        return app;
    }

    /**
     * Method to get {@link #pullRequests} instance <br>
     * No-any params required
     *
     * @return {@link #pullRequests} instance as {@link Collection} of {@link MinimalPullRequest}
     **/
    public Collection<MinimalPullRequest> getPullRequests() {
        return pullRequests;
    }

    /**
     * {@code CheckStatus} list of available status for a {@link CheckRun}
     **/
    public enum CheckStatus {

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

}
