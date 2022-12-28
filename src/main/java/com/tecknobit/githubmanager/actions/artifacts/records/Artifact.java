package com.tecknobit.githubmanager.actions.artifacts.records;

import com.tecknobit.apimanager.apis.APIRequest;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Artifact} class is useful to format a GitHub's artifact
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/artifacts#get-an-artifact">
 *             Get an artifact</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/artifacts#delete-an-artifact">
 *             Delete an artifact</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
 *              List artifacts for a repository</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
 *              List workflow run artifacts</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Artifact extends BaseResponseDetails {

    /**
     * {@code nodeId} identifier of the node
     **/
    private final String nodeId;

    /**
     * {@code sizeInBytes} the size in bytes of the artifact
     **/
    private final int sizeInBytes;

    /**
     * {@code archiveDownloadUrl} url to archive the download
     **/
    private final String archiveDownloadUrl;

    /**
     * {@code expired} whether or not the artifact has expired
     **/
    private final boolean expired;

    /**
     * {@code createdAt} date when artifact has been created
     **/
    private final String createdAt;

    /**
     * {@code expiresAt} date when artifact expires
     **/
    private final String expiresAt;

    /**
     * {@code updatedAt} date when artifact has been updated
     **/
    private final String updatedAt;

    /**
     * {@code artifactWorkflowRun} workflow run details
     **/
    private final ArtifactWorkflowRun artifactWorkflowRun;

    /**
     * {@code archiveDownloadUrlFile} file created from {@link #archiveDownloadUrl}
     **/
    private File archiveDownloadUrlFile;

    /**
     * Constructor to init an {@link Artifact}
     *
     * @param id:                 identifier of the artifact
     * @param nodeId:             identifier of the node
     * @param name:               the name of the artifact
     * @param sizeInBytes:        the size in bytes of the artifact
     * @param url:                url of the artifact
     * @param archiveDownloadUrl: url to archive the download
     * @param expired:            whether the artifact has expired
     * @param createdAt:          date when artifact expires
     * @param expiresAt:          date when artifact expires
     * @param updatedAt:          date when artifact has been updated
     * @param artifactWorkflowRun:        workflow run details
     **/
    public Artifact(long id, String nodeId, String name, int sizeInBytes, String url, String archiveDownloadUrl,
                    boolean expired, String createdAt, String expiresAt, String updatedAt,
                    ArtifactWorkflowRun artifactWorkflowRun) {
        super(id, name, url);
        this.nodeId = nodeId;
        this.sizeInBytes = sizeInBytes;
        this.archiveDownloadUrl = archiveDownloadUrl;
        this.expired = expired;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.updatedAt = updatedAt;
        this.artifactWorkflowRun = artifactWorkflowRun;
    }

    /**
     * Constructor to init an {@link Artifact}
     *
     * @param jArtifact: artifact details as {@link JSONObject}
     **/
    public Artifact(JSONObject jArtifact) {
        super(jArtifact);
        nodeId = hResponse.getString("node_id");
        sizeInBytes = hResponse.getInt("size_in_bytes", 0);
        archiveDownloadUrl = hResponse.getString("archive_download_url");
        expired = hResponse.getBoolean("expired");
        createdAt = hResponse.getString("created_at");
        expiresAt = hResponse.getString("expires_at");
        updatedAt = hResponse.getString("updated_at");
        artifactWorkflowRun = new ArtifactWorkflowRun(hResponse.getJSONObject("workflow_run", new JSONObject()));
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
     * Method to get {@link #sizeInBytes} instance <br>
     * Any params required
     *
     * @return {@link #sizeInBytes} instance as int
     **/
    public int getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * Method to get {@link #archiveDownloadUrl} instance <br>
     * Any params required
     *
     * @return {@link #archiveDownloadUrl} instance as {@link String}
     **/
    public String getArchiveDownloadUrl() {
        return archiveDownloadUrl;
    }

    /**
     * Method to get {@link #archiveDownloadUrlFile} instance <br>
     * Any params required
     *
     * @return {@link #archiveDownloadUrlFile} instance as {@link File}
     **/
    public File getFileFromArchiveDownloadUrl() throws IOException {
        if (archiveDownloadUrlFile == null) {
            return archiveDownloadUrlFile = APIRequest.downloadFile(archiveDownloadUrl, System.currentTimeMillis()
                    + ".zip", false);
        }
        return archiveDownloadUrlFile;
    }

    /**
     * Method to get {@link #expired} instance <br>
     * Any params required
     *
     * @return {@link #expired} instance as boolean
     **/
    public boolean isExpired() {
        return expired;
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
     * Method to get {@link #expiresAt} instance <br>
     * Any params required
     *
     * @return {@link #expiresAt} instance as {@link String}
     **/
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Method to get {@link #expiresAt} timestamp <br>
     * Any params required
     *
     * @return {@link #expiresAt} timestamp as long
     **/
    public long getExpiresAtTimestamp() {
        return getDateTimestamp(expiresAt);
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
     * Method to get {@link #artifactWorkflowRun} instance <br>
     * Any params required
     *
     * @return {@link #artifactWorkflowRun} instance as {@link ArtifactWorkflowRun}
     **/
    public ArtifactWorkflowRun getWorkflowRun() {
        return artifactWorkflowRun;
    }

    /**
     * The {@code ArtifactWorkflowRun} class is useful to format a GitHub's artifact workflow run
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/artifacts#about-the-artifacts-api">
     * About the Artifacts API</a>
     **/
    public static class ArtifactWorkflowRun {

        /**
         * {@code id} identifier of the workflow run
         **/
        private final long id;

        /**
         * {@code repositoryId} identifier of the repository
         **/
        private final long repositoryId;

        /**
         * {@code headRepositoryId} identifier of the head repository
         **/
        private final long headRepositoryId;

        /**
         * {@code headBranch} head branch value
         **/
        private final String headBranch;

        /**
         * {@code headSha} head sha value es. -> 009b8a3a9ccbb128af87f9b1c0f4c62e8a304f6d
         **/
        private final String headSha;

        /**
         * Constructor to init a {@link ArtifactWorkflowRun}
         *
         * @param id:               identifier of the workflow run
         * @param repositoryId:     identifier of the repository
         * @param headRepositoryId: identifier of the head repository
         * @param headBranch:       head branch value
         * @param headSha:          head sha value es. -> 009b8a3a9ccbb128af87f9b1c0f4c62e8a304f6d
         **/
        public ArtifactWorkflowRun(long id, long repositoryId, long headRepositoryId, String headBranch, String headSha) {
            this.id = id;
            this.repositoryId = repositoryId;
            this.headRepositoryId = headRepositoryId;
            this.headBranch = headBranch;
            this.headSha = headSha;
        }

        /**
         * Constructor to init an {@link ArtifactWorkflowRun}
         *
         * @param jWorkflowRun: workflow run details as {@link JSONObject}
         **/
        public ArtifactWorkflowRun(JSONObject jWorkflowRun) {
            JsonHelper hWorkflowRun = new JsonHelper(jWorkflowRun);
            id = hWorkflowRun.getLong("id", 0);
            repositoryId = hWorkflowRun.getLong("repository_id", 0);
            headRepositoryId = hWorkflowRun.getLong("head_repository_id", 0);
            headBranch = hWorkflowRun.getString("head_branch");
            headSha = hWorkflowRun.getString("head_sha");
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
         * Method to get {@link #repositoryId} instance <br>
         * Any params required
         *
         * @return {@link #repositoryId} instance as long
         **/
        public long getRepositoryId() {
            return repositoryId;
        }

        /**
         * Method to get {@link #headRepositoryId} instance <br>
         * Any params required
         *
         * @return {@link #headRepositoryId} instance as long
         **/
        public long getHeadRepositoryId() {
            return headRepositoryId;
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
