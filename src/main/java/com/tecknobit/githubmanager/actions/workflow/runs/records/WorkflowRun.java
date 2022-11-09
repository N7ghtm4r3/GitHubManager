package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.jobs.records.Job.Status;
import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import com.tecknobit.githubmanager.records.basics.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.actions.workflow.jobs.records.Job.Status.valueOf;

public class WorkflowRun extends BaseResponseDetails {

    private final String nodeId;
    private final long checkSuiteId;
    private final String checkSuiteNodeId;
    private final String headBranch;
    private final String headSha;
    private final String path;
    private final int runNumber;
    private final String event;
    private final String displayTitle;
    private final Status status;
    private final long workflowId;
    private final String htmlUrl;
    private final ArrayList<PullRequest> pullRequests;
    private final String createdAt;
    private final String updatedAt;
    private final User actor;
    private final int runAttempt;
    private final ArrayList<ReferencedWorkflow> referencedWorkflows;
    private final String runStartedAt;
    private final User triggeringActor;
    private final String jobsUrl;
    private final String logsUrl;
    private final String checkSuiteUrl;
    private final String artifactsUrl;
    private final String cancelUrl;
    private final String rerunUrl;
    private final String previousAttemptUrl;
    private final String workflowUrl;
    private final Commit headCommit;
    private final Repository repository;
    private final Repository headRepository;

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

    public String getNodeId() {
        return nodeId;
    }

    public long getCheckSuiteId() {
        return checkSuiteId;
    }

    public String getCheckSuiteNodeId() {
        return checkSuiteNodeId;
    }

    public String getHeadBranch() {
        return headBranch;
    }

    public String getHeadSha() {
        return headSha;
    }

    public String getPath() {
        return path;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public String getEvent() {
        return event;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public Status getStatus() {
        return status;
    }

    public long getWorkflowId() {
        return workflowId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Collection<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public User getActor() {
        return actor;
    }

    public int getRunAttempt() {
        return runAttempt;
    }

    public Collection<ReferencedWorkflow> getReferencedWorkflows() {
        return referencedWorkflows;
    }

    public String getRunStartedAt() {
        return runStartedAt;
    }

    public User getTriggeringActor() {
        return triggeringActor;
    }

    public String getJobsUrl() {
        return jobsUrl;
    }

    public String getLogsUrl() {
        return logsUrl;
    }

    public String getCheckSuiteUrl() {
        return checkSuiteUrl;
    }

    public String getArtifactsUrl() {
        return artifactsUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public String getRerunUrl() {
        return rerunUrl;
    }

    public String getPreviousAttemptUrl() {
        return previousAttemptUrl;
    }

    public String getWorkflowUrl() {
        return workflowUrl;
    }

    public Commit getHeadCommit() {
        return headCommit;
    }

    public Repository getRepository() {
        return repository;
    }

    public Repository getHeadRepository() {
        return headRepository;
    }

    public static class PullRequest {

        private final long id;
        private final int number;
        private final String url;
        private final PullRequestPart head;
        private final PullRequestPart base;

        public PullRequest(long id, int number, String url, PullRequestPart head, PullRequestPart base) {
            this.id = id;
            this.number = number;
            this.url = url;
            this.head = head;
            this.base = base;
        }

        public PullRequest(JSONObject jPullRequest) {
            JsonHelper hPullRequest = new JsonHelper(jPullRequest);
            id = hPullRequest.getLong("id", 0);
            number = hPullRequest.getInt("number", 0);
            url = hPullRequest.getString("url");
            head = new PullRequestPart(hPullRequest.getJSONObject("head", new JSONObject()));
            base = new PullRequestPart(hPullRequest.getJSONObject("base", new JSONObject()));
        }

        public long getId() {
            return id;
        }

        public int getNumber() {
            return number;
        }

        public String getUrl() {
            return url;
        }

        public PullRequestPart getHead() {
            return head;
        }

        public PullRequestPart getBase() {
            return base;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public static class PullRequestPart {

            private final String sha;
            private final String ref;
            private final BaseResponseDetails repo;

            public PullRequestPart(String sha, String ref, BaseResponseDetails repo) {
                this.sha = sha;
                this.ref = ref;
                this.repo = repo;
            }

            public PullRequestPart(JSONObject jHead) {
                JsonHelper hHead = new JsonHelper(jHead);
                sha = hHead.getString("sha");
                ref = hHead.getString("ref");
                repo = new BaseResponseDetails(hHead.getJSONObject("repo", new JSONObject()));
            }

            public String getSha() {
                return sha;
            }

            public String getRef() {
                return ref;
            }

            public BaseResponseDetails getRepo() {
                return repo;
            }

            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

    public static class ReferencedWorkflow {

        private final String path;
        private final String sha;
        private final String ref;

        public ReferencedWorkflow(String path, String sha, String ref) {
            this.path = path;
            this.sha = sha;
            this.ref = ref;
        }

        public ReferencedWorkflow(JSONObject jReferencedWorkflow) {
            JsonHelper hReferencedWorkflow = new JsonHelper(jReferencedWorkflow);
            path = hReferencedWorkflow.getString("path");
            sha = hReferencedWorkflow.getString("sha");
            ref = hReferencedWorkflow.getString("ref");
        }

        public String getPath() {
            return path;
        }

        public String getSha() {
            return sha;
        }

        public String getRef() {
            return ref;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    public static class Commit {

        private final long id;
        private final long treeId;
        private final String message;
        private final String timestamp;
        private final CommitProfile author;
        private final CommitProfile committer;

        public Commit(long id, long treeId, String message, String timestamp, CommitProfile author, CommitProfile committer) {
            this.id = id;
            this.treeId = treeId;
            this.message = message;
            this.timestamp = timestamp;
            this.author = author;
            this.committer = committer;
        }

        public Commit(JSONObject jCommit) {
            JsonHelper hCommit = new JsonHelper(jCommit);
            id = hCommit.getLong("id", 0);
            treeId = hCommit.getLong("tree_id", 0);
            message = hCommit.getString("message");
            timestamp = hCommit.getString("timestamp");
            author = new CommitProfile(hCommit.getJSONObject("author", new JSONObject()));
            committer = new CommitProfile(hCommit.getJSONObject("committer", new JSONObject()));
        }

        public long getId() {
            return id;
        }

        public long getTreeId() {
            return treeId;
        }

        public String getMessage() {
            return message;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public CommitProfile getAuthor() {
            return author;
        }

        public CommitProfile getCommitter() {
            return committer;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public static class CommitProfile {

            private final String name;
            private final String email;

            public CommitProfile(String name, String email) {
                this.name = name;
                this.email = email;
            }

            public CommitProfile(JSONObject jCommitProfile) {
                JsonHelper hCommitProfile = new JsonHelper(jCommitProfile);
                name = hCommitProfile.getString("name");
                email = hCommitProfile.getString("email");
            }

            public String getName() {
                return name;
            }

            public String getEmail() {
                return email;
            }

            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

}
