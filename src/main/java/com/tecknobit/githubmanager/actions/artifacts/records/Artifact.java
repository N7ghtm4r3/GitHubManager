package com.tecknobit.githubmanager.actions.artifacts.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Locale.getDefault;

public class Artifact extends GitHubManager.GitHubResponse {

    /**
     * {@code dateFormatter} is instance that help to format date
     **/
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", getDefault());
    private final long id;
    private final String nodeId;
    private final String name;
    private final int sizeInBytes;
    private final String url;
    private final String archiveDownloadUrl;
    private final boolean expired;
    private final String createdAt;
    private final String expiresAt;
    private final String updatedAt;
    private final WorkflowRun workflowRun;
    private File archiveDownloadUrlFile;

    public Artifact(long id, String nodeId, String name, int sizeInBytes, String url, String archiveDownloadUrl,
                    boolean expired, String createdAt, String expiresAt, String updatedAt, WorkflowRun workflowRun) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.name = name;
        this.sizeInBytes = sizeInBytes;
        this.url = url;
        this.archiveDownloadUrl = archiveDownloadUrl;
        this.expired = expired;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.updatedAt = updatedAt;
        this.workflowRun = workflowRun;
    }

    public Artifact(JSONObject jArtifact) {
        super(jArtifact);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        name = hResponse.getString("name");
        sizeInBytes = hResponse.getInt("size_in_bytes", 0);
        url = hResponse.getString("url");
        archiveDownloadUrl = hResponse.getString("archive_download_url");
        expired = hResponse.getBoolean("expired");
        createdAt = hResponse.getString("created_at");
        expiresAt = hResponse.getString("expires_at");
        updatedAt = hResponse.getString("updated_at");
        workflowRun = new WorkflowRun(hResponse.getJSONObject("workflow_run", new JSONObject()));
    }

    public long getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public String getUrl() {
        return url;
    }

    public String getArchiveDownloadUrl() {
        return archiveDownloadUrl;
    }

    public File getFileFromArchiveDownloadUrl() throws MalformedURLException {
        if (archiveDownloadUrlFile == null)
            return archiveDownloadUrlFile = new File(new URL(archiveDownloadUrl).getFile());
        return archiveDownloadUrlFile;
    }

    public boolean isExpired() {
        return expired;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getCreatedAtTimestamp() {
        try {
            return dateFormatter.parse(createdAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public long getExpiresAtTimestamp() {
        try {
            return dateFormatter.parse(expiresAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public long getUpdatedAtTimestamp() {
        try {
            return dateFormatter.parse(updatedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public WorkflowRun getWorkflowRun() {
        return workflowRun;
    }

    public static class WorkflowRun {

        private final long id;
        private final long repositoryId;
        private final long headRepositoryId;
        private final String headBranch;
        private final String headSha;

        public WorkflowRun(long id, long repositoryId, long headRepositoryId, String headBranch, String headSha) {
            this.id = id;
            this.repositoryId = repositoryId;
            this.headRepositoryId = headRepositoryId;
            this.headBranch = headBranch;
            this.headSha = headSha;
        }

        public WorkflowRun(JSONObject jWorkflowRun) {
            JsonHelper hWorkflowRun = new JsonHelper(jWorkflowRun);
            id = hWorkflowRun.getLong("id", 0);
            repositoryId = hWorkflowRun.getLong("repository_id", 0);
            headRepositoryId = hWorkflowRun.getLong("head_repository_id", 0);
            headBranch = hWorkflowRun.getString("head_branch");
            headSha = hWorkflowRun.getString("head_sha");
        }

        public long getId() {
            return id;
        }

        public long getRepositoryId() {
            return repositoryId;
        }

        public long getHeadRepositoryId() {
            return headRepositoryId;
        }

        public String getHeadBranch() {
            return headBranch;
        }

        public String getHeadSha() {
            return headSha;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
