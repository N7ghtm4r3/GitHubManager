package com.tecknobit.githubmanager.migrations.organizations.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.records.repository.CompleteRepository.returnCompleteRepositoriesList;

public class OrganizationMigration extends GitHubResponse {

    public enum MigrationState {

        pending,
        exporting,
        exported,
        failed

    }

    private final long id;
    private final User owner;
    private final String guid;
    private final MigrationState state;
    private final boolean lockRepositories;
    private final boolean excludeMetadata;
    private final boolean excludeGitData;
    private final boolean excludeAttachments;
    private final boolean excludeReleases;
    private final boolean excludeOwnerProjects;
    private final boolean orgMetadataOnly;
    private final ArrayList<CompleteRepository> repositories;
    private final String url;
    private final String createdAt;
    private final String updatedAt;
    private final String nodeId;
    private final String archiveUrl;
    private final ArrayList<String> exclude;

    public OrganizationMigration(long id, User owner, String guid, MigrationState state, boolean lockRepositories,
                                 boolean excludeMetadata, boolean excludeGitData, boolean excludeAttachments,
                                 boolean excludeReleases, boolean excludeOwnerProjects, boolean orgMetadataOnly,
                                 ArrayList<CompleteRepository> repositories, String url, String createdAt,
                                 String updatedAt, String nodeId, String archiveUrl, ArrayList<String> exclude) {
        super(null);
        this.id = id;
        this.owner = owner;
        this.guid = guid;
        this.state = state;
        this.lockRepositories = lockRepositories;
        this.excludeMetadata = excludeMetadata;
        this.excludeGitData = excludeGitData;
        this.excludeAttachments = excludeAttachments;
        this.excludeReleases = excludeReleases;
        this.excludeOwnerProjects = excludeOwnerProjects;
        this.orgMetadataOnly = orgMetadataOnly;
        this.repositories = repositories;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nodeId = nodeId;
        this.archiveUrl = archiveUrl;
        this.exclude = exclude;
    }

    public OrganizationMigration(JSONObject jOrganizationMigration) {
        super(jOrganizationMigration);
        id = hResponse.getLong("id", 0);
        owner = new User(hResponse.getJSONObject("owner"));
        guid = hResponse.getString("guid");
        state = MigrationState.valueOf(hResponse.getString("state"));
        lockRepositories = hResponse.getBoolean("lock_repositories");
        excludeMetadata = hResponse.getBoolean("exclude_metadata");
        excludeGitData = hResponse.getBoolean("exclude_git_data");
        excludeAttachments = hResponse.getBoolean("exclude_attachments");
        excludeReleases = hResponse.getBoolean("exclude_releases");
        excludeOwnerProjects = hResponse.getBoolean("exclude_owner_projects");
        orgMetadataOnly = hResponse.getBoolean("org_metadata_only");
        repositories = returnCompleteRepositoriesList(hResponse.getJSONArray("repositories"));
        url = hResponse.getString("url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        nodeId = hResponse.getString("node_id");
        archiveUrl = hResponse.getString("archive_url");
        exclude = returnStringsList(hResponse.getJSONArray("exclude"));
    }

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getGuid() {
        return guid;
    }

    public MigrationState getState() {
        return state;
    }

    public boolean areRepositoriesLocked() {
        return lockRepositories;
    }

    public boolean areMetadataExcluded() {
        return excludeMetadata;
    }

    public boolean areGitDataExcluded() {
        return excludeGitData;
    }

    public boolean areAttachmentsExcluded() {
        return excludeAttachments;
    }

    public boolean areReleasesExcluded() {
        return excludeReleases;
    }

    public boolean areOwnerProjectsExcluded() {
        return excludeOwnerProjects;
    }

    public boolean orgMetadataOnly() {
        return orgMetadataOnly;
    }

    public ArrayList<CompleteRepository> getRepositories() {
        return repositories;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public ArrayList<String> getExclude() {
        return exclude;
    }

}
