package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job.Status;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.PullRequest;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.actions.workflow.jobs.records.Job.Status.valueOf;

/**
 * The {@code WorkflowRun} class is useful to format a GitHub's workflow run
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run">
 *             Get a workflow run</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-a-workflow-run-attempt">
 *             Get a workflow run attempt</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class WorkflowRun extends BaseResponseDetails {

    /**
     * {@code nodeId} identifier of the node value
     **/
    private final String nodeId;

    /**
     * {@code checkSuiteId} the {@code "ID"} of the associated check suite
     **/
    private final long checkSuiteId;

    /**
     * {@code checkSuiteNodeId} the node {@code "ID"} of the associated check suite
     **/
    private final String checkSuiteNodeId;

    /**
     * {@code headBranch} head branch value
     **/
    private final String headBranch;

    /**
     * {@code headSha} the {@code "SHA"} of the head commit that points to the version of the workflow being run
     **/
    private final String headSha;

    /**
     * {@code path} the full path of the workflow
     **/
    private final String path;

    /**
     * {@code runNumber} the auto incrementing run number for the workflow run
     **/
    private final int runNumber;

    /**
     * {@code event} event value
     **/
    private final String event;

    /**
     * {@code displayTitle} the event-specific title associated with the run or the run-name if set, or the value of `run-name` if it is set in the workflow
     **/
    private final String displayTitle;

    /**
     * {@code status} status value
     **/
    private final Status status;

    /**
     * {@code workflowId} the {@code "ID"} of the parent workflow
     **/
    private final long workflowId;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code pullRequests} pull requests list
     **/
    private final ArrayList<PullRequest> pullRequests;

    /**
     * {@code createdAt} created at value
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated at value
     **/
    private final String updatedAt;

    /**
     * {@code actor} actor value
     **/
    private final User actor;

    /**
     * {@code runAttempt} attempt number of the run, 1 for first attempt and higher if the workflow was re-run
     **/
    private final int runAttempt;

    /**
     * {@code referencedWorkflows} list of workflows referenced/reused by the initial caller workflow
     **/
    private final ArrayList<ReferencedWorkflow> referencedWorkflows;

    /**
     * {@code runStartedAt} the start time of the latest run. Resets on re-run
     **/
    private final String runStartedAt;

    /**
     * {@code triggeringActor} triggering actor value
     **/
    private final User triggeringActor;

    /**
     * {@code jobsUrl} the {@code "URL"} to the jobs for the workflow run
     **/
    private final String jobsUrl;

    /**
     * {@code logsUrl} the {@code "URL"} to download the logs for the workflow run
     **/
    private final String logsUrl;

    /**
     * {@code checkSuiteUrl} the {@code "URL"} to the associated check suite
     **/
    private final String checkSuiteUrl;

    /**
     * {@code artifactsUrl} the {@code "URL"} to the artifacts for the workflow run
     **/
    private final String artifactsUrl;

    /**
     * {@code cancelUrl} the {@code "URL"} to cancel the workflow run
     **/
    private final String cancelUrl;

    /**
     * {@code rerunUrl} the {@code "URL"} to rerun the workflow run
     **/
    private final String rerunUrl;

    /**
     * {@code previousAttemptUrl} the {@code "URL"} to the previous attempted run of this workflow, if one exists
     **/
    private final String previousAttemptUrl;

    /**
     * {@code workflowUrl} the {@code "URL"} to the workflow
     **/
    private final String workflowUrl;

    /**
     * {@code headCommit} head commit value
     **/
    private final Commit headCommit;

    /**
     * {@code repository} repository value
     **/
    private final Repository repository;

    /**
     * {@code headRepository} head repository value
     **/
    private final Repository headRepository;

    /**
     * Constructor to init a {@link WorkflowRun}
     *
     * @param id                   : identifier value
     * @param name                 : the name of the workflow
     * @param nodeId               : identifier of the node value
     * @param checkSuiteId         : the {@code "ID"} of the associated check suite
     * @param checkSuiteNodeId     : the node {@code "ID"} of the associated check suite
     * @param headBranch:          head branch value
     * @param headSha              : the {@code "SHA"} of the head commit that points to the version of the workflow being run
     * @param path                 : the full path of the workflow
     * @param runNumber:           the auto incrementing run number for the workflow run
     * @param event                : event value
     * @param displayTitle         : the event-specific title associated with the run or the run-name if set, or the value of `run-name` if it is set in the workflow
     * @param status:              status value
     * @param workflowId           : the {@code "ID"} of the parent workflow
     * @param url:                 the {@code "URL"} to the workflow run
     * @param htmlUrl:             html url value
     * @param pullRequests         : pull requests list
     * @param createdAt            : created at value
     * @param updatedAt:           updated at value
     * @param actor                : actor value
     * @param runAttempt           : attempt number of the run, 1 for first attempt and higher if the workflow was re-run
     * @param referencedWorkflows: list of workflows referenced/reused by the initial caller workflow
     * @param runStartedAt         : the start time of the latest run. Resets on re-run
     * @param triggeringActor      : triggering actor value
     * @param jobsUrl:             the {@code "URL"} to the jobs for the workflow run
     * @param logsUrl              : the {@code "URL"} to download the logs for the workflow run
     * @param checkSuiteUrl        : the {@code "URL"} to the associated check suite
     * @param artifactsUrl:        the {@code "URL"} to the artifacts for the workflow run
     * @param cancelUrl            : the {@code "URL"} to cancel the workflow run
     * @param rerunUrl             : the {@code "URL"} to rerun the workflow run
     * @param previousAttemptUrl:  the {@code "URL"} to the previous attempted run of this workflow, if one exists
     * @param workflowUrl          : the {@code "URL"} to the workflow
     * @param headCommit           : head commit value
     * @param repository:          repository value
     * @param headRepository       : head repository value
     **/
    public WorkflowRun(long id, String name, String nodeId, long checkSuiteId, String checkSuiteNodeId, String headBranch,
                       String headSha, String path, int runNumber, String event, String displayTitle, Status status,
                       long workflowId, String url, String htmlUrl, ArrayList<PullRequest> pullRequests, String createdAt,
                       String updatedAt, User actor, int runAttempt, ArrayList<ReferencedWorkflow> referencedWorkflows,
                       String runStartedAt, User triggeringActor, String jobsUrl, String logsUrl, String checkSuiteUrl,
                       String artifactsUrl, String cancelUrl, String rerunUrl, String previousAttemptUrl, String workflowUrl,
                       Commit headCommit, Repository repository, Repository headRepository) {
        super(id, name, url);
        this.nodeId = nodeId;
        this.checkSuiteId = checkSuiteId;
        this.checkSuiteNodeId = checkSuiteNodeId;
        this.headBranch = headBranch;
        this.headSha = headSha;
        this.path = path;
        this.runNumber = runNumber;
        this.event = event;
        this.displayTitle = displayTitle;
        this.status = status;
        this.workflowId = workflowId;
        this.htmlUrl = htmlUrl;
        this.pullRequests = pullRequests;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.actor = actor;
        this.runAttempt = runAttempt;
        this.referencedWorkflows = referencedWorkflows;
        this.runStartedAt = runStartedAt;
        this.triggeringActor = triggeringActor;
        this.jobsUrl = jobsUrl;
        this.logsUrl = logsUrl;
        this.checkSuiteUrl = checkSuiteUrl;
        this.artifactsUrl = artifactsUrl;
        this.cancelUrl = cancelUrl;
        this.rerunUrl = rerunUrl;
        this.previousAttemptUrl = previousAttemptUrl;
        this.workflowUrl = workflowUrl;
        this.headCommit = headCommit;
        this.repository = repository;
        this.headRepository = headRepository;
    }

    /**
     * Constructor to init a {@link WorkflowRun}
     *
     * @param jWorkflowRun : workflow run details as {@link JSONObject}
     **/
    public WorkflowRun(JSONObject jWorkflowRun) {
        super(jWorkflowRun);
        nodeId = hResponse.getString("node_id");
        checkSuiteId = hResponse.getLong("check_suite_id", 0);
        checkSuiteNodeId = hResponse.getString("check_suite_node_id");
        headBranch = hResponse.getString("head_branch");
        headSha = hResponse.getString("head_sha");
        path = hResponse.getString("path");
        runNumber = hResponse.getInt("run_number", 0);
        event = hResponse.getString("event");
        displayTitle = hResponse.getString("display_title");
        status = valueOf(hResponse.getString("status", Status.in_progress.toString()));
        workflowId = hResponse.getLong("workflow_id", 0);
        htmlUrl = hResponse.getString("html_url");
        pullRequests = new ArrayList<>();
        JSONArray jPullRequests = hResponse.getJSONArray("pull_requests", new JSONArray());
        for (int j = 0; j < jPullRequests.length(); j++)
            pullRequests.add(new PullRequest(jPullRequests.getJSONObject(j)));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        actor = new User(hResponse.getJSONObject("actor", new JSONObject()));
        runAttempt = hResponse.getInt("run_attempt", 0);
        referencedWorkflows = new ArrayList<>();
        JSONArray jReferencedWorkflows = hResponse.getJSONArray("referenced_workflows", new JSONArray());
        for (int j = 0; j < jReferencedWorkflows.length(); j++)
            referencedWorkflows.add(new ReferencedWorkflow(jReferencedWorkflows.getJSONObject(j)));
        runStartedAt = hResponse.getString("run_started_at");
        triggeringActor = new User(hResponse.getJSONObject("triggering_actor", new JSONObject()));
        jobsUrl = hResponse.getString("jobs_url");
        logsUrl = hResponse.getString("logs_url");
        checkSuiteUrl = hResponse.getString("check_suite_url");
        artifactsUrl = hResponse.getString("artifacts_url");
        cancelUrl = hResponse.getString("cancel_url");
        rerunUrl = hResponse.getString("rerun_url");
        previousAttemptUrl = hResponse.getString("previous_attempt_url");
        workflowUrl = hResponse.getString("workflow_url");
        headCommit = new Commit(hResponse.getJSONObject("head_commit", new JSONObject()));
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
        headRepository = new Repository(hResponse.getJSONObject("head_repository", new JSONObject()));
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
     * Method to get {@link #checkSuiteId} instance <br>
     * Any params required
     *
     * @return {@link #checkSuiteId} instance as long
     **/
    public long getCheckSuiteId() {
        return checkSuiteId;
    }

    /**
     * Method to get {@link #checkSuiteNodeId} instance <br>
     * Any params required
     *
     * @return {@link #checkSuiteNodeId} instance as {@link String}
     **/
    public String getCheckSuiteNodeId() {
        return checkSuiteNodeId;
    }

    /**
     * Method to get {@link #headBranch} instance <br>
     * Any params required
     *
     * @return {@link #headBranch} instance as {@link String}
     **/
    public String getHeadBranch() {
        return headBranch;
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
     * Method to get {@link #path} instance <br>
     * Any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
    }

    /**
     * Method to get {@link #runNumber} instance <br>
     * Any params required
     *
     * @return {@link #runNumber} instance as int
     **/
    public int getRunNumber() {
        return runNumber;
    }

    /**
     * Method to get {@link #event} instance <br>
     * Any params required
     *
     * @return {@link #event} instance as {@link String}
     **/
    public String getEvent() {
        return event;
    }

    /**
     * Method to get {@link #displayTitle} instance <br>
     * Any params required
     *
     * @return {@link #displayTitle} instance as {@link String}
     **/
    public String getDisplayTitle() {
        return displayTitle;
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link Status}
     **/
    public Status getStatus() {
        return status;
    }

    /**
     * Method to get {@link #workflowId} instance <br>
     * Any params required
     *
     * @return {@link #workflowId} instance as long
     **/
    public long getWorkflowId() {
        return workflowId;
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
     * Method to get {@link #pullRequests} instance <br>
     * Any params required
     *
     * @return {@link #pullRequests} instance as {@link Collection} of {@link PullRequest}
     **/
    public Collection<PullRequest> getPullRequests() {
        return pullRequests;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #actor} instance <br>
     * Any params required
     *
     * @return {@link #actor} instance as {@link User}
     **/
    public User getActor() {
        return actor;
    }

    /**
     * Method to get {@link #runAttempt} instance <br>
     * Any params required
     *
     * @return {@link #runAttempt} instance as int
     **/
    public int getRunAttempt() {
        return runAttempt;
    }

    /**
     * Method to get {@link #referencedWorkflows} instance <br>
     * Any params required
     *
     * @return {@link #referencedWorkflows} instance as {@link Collection} of {@link ReferencedWorkflow}
     **/
    public Collection<ReferencedWorkflow> getReferencedWorkflows() {
        return referencedWorkflows;
    }

    /**
     * Method to get {@link #runStartedAt} instance <br>
     * Any params required
     *
     * @return {@link #runStartedAt} instance as {@link String}
     **/
    public String getRunStartedAt() {
        return runStartedAt;
    }

    /**
     * Method to get {@link #triggeringActor} instance <br>
     * Any params required
     *
     * @return {@link #triggeringActor} instance as {@link User}
     **/
    public User getTriggeringActor() {
        return triggeringActor;
    }

    /**
     * Method to get {@link #jobsUrl} instance <br>
     * Any params required
     *
     * @return {@link #jobsUrl} instance as {@link String}
     **/
    public String getJobsUrl() {
        return jobsUrl;
    }

    /**
     * Method to get {@link #logsUrl} instance <br>
     * Any params required
     *
     * @return {@link #logsUrl} instance as {@link String}
     **/
    public String getLogsUrl() {
        return logsUrl;
    }

    /**
     * Method to get {@link #checkSuiteUrl} instance <br>
     * Any params required
     *
     * @return {@link #checkSuiteUrl} instance as {@link String}
     **/
    public String getCheckSuiteUrl() {
        return checkSuiteUrl;
    }

    /**
     * Method to get {@link #artifactsUrl} instance <br>
     * Any params required
     *
     * @return {@link #artifactsUrl} instance as {@link String}
     **/
    public String getArtifactsUrl() {
        return artifactsUrl;
    }

    /**
     * Method to get {@link #cancelUrl} instance <br>
     * Any params required
     *
     * @return {@link #cancelUrl} instance as {@link String}
     **/
    public String getCancelUrl() {
        return cancelUrl;
    }

    /**
     * Method to get {@link #rerunUrl} instance <br>
     * Any params required
     *
     * @return {@link #rerunUrl} instance as {@link String}
     **/
    public String getRerunUrl() {
        return rerunUrl;
    }

    /**
     * Method to get {@link #previousAttemptUrl} instance <br>
     * Any params required
     *
     * @return {@link #previousAttemptUrl} instance as {@link String}
     **/
    public String getPreviousAttemptUrl() {
        return previousAttemptUrl;
    }

    /**
     * Method to get {@link #workflowUrl} instance <br>
     * Any params required
     *
     * @return {@link #workflowUrl} instance as {@link String}
     **/
    public String getWorkflowUrl() {
        return workflowUrl;
    }

    /**
     * Method to get {@link #headCommit} instance <br>
     * Any params required
     *
     * @return {@link #headCommit} instance as {@link Commit}
     **/
    public Commit getHeadCommit() {
        return headCommit;
    }

    /**
     * Method to get {@link #repository} instance <br>
     * Any params required
     *
     * @return {@link #repository} instance as {@link Repository}
     **/
    public Repository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #headRepository} instance <br>
     * Any params required
     *
     * @return {@link #headRepository} instance as {@link Repository}
     **/
    public Repository getHeadRepository() {
        return headRepository;
    }

    /**
     * {@code WorkflowRunStatus} workflow run statuses list
     **/
    public enum WorkflowRunStatus {

        /**
         * {@code "completed"} status
         **/
        completed,

        /**
         * {@code "action_required"} status
         **/
        action_required,

        /**
         * {@code "cancelled"} status
         **/
        cancelled,

        /**
         * {@code "failure"} status
         **/
        failure,

        /**
         * {@code "neutral"} status
         **/
        neutral,

        /**
         * {@code "skipped"} status
         **/
        skipped,

        /**
         * {@code "stale"} status
         **/
        stale,

        /**
         * {@code "success"} status
         **/
        success,

        /**
         * {@code "timed_out"} status
         **/
        timed_out,

        /**
         * {@code "in_progress"} status
         **/
        in_progress,

        /**
         * {@code "queued"} status
         **/
        queued,

        /**
         * {@code "requested"} status
         **/
        requested,

        /**
         * {@code "waiting"} status
         **/
        waiting

    }

    /**
     * The {@code ReferencedWorkflow} class is useful to format a GitHub's referenced workflow
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class ReferencedWorkflow {

        /**
         * {@code "path"} value
         **/
        private final String path;

        /**
         * {@code "sha"} value
         **/
        private final String sha;

        /**
         * {@code "ref"} value
         **/
        private final String ref;

        /**
         * Constructor to init a {@link ReferencedWorkflow}
         *
         * @param path: path value
         * @param sha   : sha value
         * @param ref   : ref value
         **/
        public ReferencedWorkflow(String path, String sha, String ref) {
            this.path = path;
            this.sha = sha;
            this.ref = ref;
        }

        /**
         * Constructor to init a {@link ReferencedWorkflow}
         *
         * @param jReferencedWorkflow: referenced workflow details as {@link JSONObject}
         **/
        public ReferencedWorkflow(JSONObject jReferencedWorkflow) {
            JsonHelper hReferencedWorkflow = new JsonHelper(jReferencedWorkflow);
            path = hReferencedWorkflow.getString("path");
            sha = hReferencedWorkflow.getString("sha");
            ref = hReferencedWorkflow.getString("ref");
        }

        /**
         * Method to get {@link #path} instance <br>
         * Any params required
         *
         * @return {@link #path} instance as {@link String}
         **/
        public String getPath() {
            return path;
        }

        /**
         * Method to get {@link #sha} instance <br>
         * Any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #ref} instance <br>
         * Any params required
         *
         * @return {@link #ref} instance as {@link String}
         **/
        public String getRef() {
            return ref;
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

    /**
     * The {@code Commit} class is useful to format a GitHub's commit
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Commit {

        /**
         * {@code id} identifier value
         **/
        private final long id;

        /**
         * {@code treeId} tree id value
         **/
        private final long treeId;

        /**
         * {@code "message"} value
         **/
        private final String message;

        /**
         * {@code "timestamp"} value
         **/
        private final String timestamp;

        /**
         * {@code "author"} value
         **/
        private final CommitProfile author;

        /**
         * {@code "committer"} value
         **/
        private final CommitProfile committer;

        /**
         * Constructor to init a {@link Commit}
         *
         * @param id:        identifier value
         * @param treeId     : tree id value
         * @param message    : message value
         * @param timestamp: timestamp value
         * @param author     : author value
         * @param committer  : committer value
         **/
        public Commit(long id, long treeId, String message, String timestamp, CommitProfile author, CommitProfile committer) {
            this.id = id;
            this.treeId = treeId;
            this.message = message;
            this.timestamp = timestamp;
            this.author = author;
            this.committer = committer;
        }

        /**
         * Constructor to init a {@link Commit}
         *
         * @param jCommit: commit details as {@link JSONObject}
         **/
        public Commit(JSONObject jCommit) {
            JsonHelper hCommit = new JsonHelper(jCommit);
            id = hCommit.getLong("id", 0);
            treeId = hCommit.getLong("tree_id", 0);
            message = hCommit.getString("message");
            timestamp = hCommit.getString("timestamp");
            author = new CommitProfile(hCommit.getJSONObject("author", new JSONObject()));
            committer = new CommitProfile(hCommit.getJSONObject("committer", new JSONObject()));
        }

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
        }

        /**
         * Method to get {@link #treeId} instance <br>
         * Any params required
         *
         * @return {@link #treeId} instance as long
         **/
        public long getTreeId() {
            return treeId;
        }

        /**
         * Method to get {@link #message} instance <br>
         * Any params required
         *
         * @return {@link #message} instance as {@link String}
         **/
        public String getMessage() {
            return message;
        }

        /**
         * Method to get {@link #timestamp} instance <br>
         * Any params required
         *
         * @return {@link #timestamp} instance as {@link String}
         **/
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * Method to get {@link #timestamp} timestamp <br>
         * Any params required
         *
         * @return {@link #timestamp} timestamp as long
         **/
        public long getLongTimestamp() {
            return getDateTimestamp(timestamp);
        }

        /**
         * Method to get {@link #author} instance <br>
         * Any params required
         *
         * @return {@link #author} instance as {@link CommitProfile}
         **/
        public CommitProfile getAuthor() {
            return author;
        }

        /**
         * Method to get {@link #committer} instance <br>
         * Any params required
         *
         * @return {@link #committer} instance as {@link CommitProfile}
         **/
        public CommitProfile getCommitter() {
            return committer;
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

        /**
         * The {@code CommitProfile} class is useful to format a GitHub's commit profile
         *
         * @author N7ghtm4r3 - Tecknobit
         **/
        public static class CommitProfile {

            /**
             * {@code "name"} value
             **/
            private final String name;

            /**
             * {@code "email"} value
             **/
            private final String email;

            /**
             * Constructor to init a {@link CommitProfile}
             *
             * @param name: name value
             * @param email : email value
             **/
            public CommitProfile(String name, String email) {
                this.name = name;
                this.email = email;
            }

            /**
             * Constructor to init a {@link CommitProfile}
             *
             * @param jCommitProfile: commit profile details as {@link JSONObject}
             **/
            public CommitProfile(JSONObject jCommitProfile) {
                JsonHelper hCommitProfile = new JsonHelper(jCommitProfile);
                name = hCommitProfile.getString("name");
                email = hCommitProfile.getString("email");
            }

            /**
             * Method to get {@link #name} instance <br>
             * Any params required
             *
             * @return {@link #name} instance as {@link String}
             **/
            public String getName() {
                return name;
            }

            /**
             * Method to get {@link #email} instance <br>
             * Any params required
             *
             * @return {@link #email} instance as {@link String}
             **/
            public String getEmail() {
                return email;
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

}
