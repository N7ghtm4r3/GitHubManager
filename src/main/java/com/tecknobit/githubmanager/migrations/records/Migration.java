package com.tecknobit.githubmanager.migrations.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.repositories.repositories.records.RepositoriesList.returnRepositoriesList;

/**
 * The {@code Migration} class is useful to format a GitHub's migration
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
 *             List organization migrations</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
 *             Start an organization migration</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
 *             Get an organization migration status</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Migration extends GitHubResponse {

    /**
     * {@code MigrationState} list of available migration states
     **/
    public enum MigrationState {

        /**
         * {@code pending} migration state
         **/
        pending,

        /**
         * {@code exporting} migration state
         **/
        exporting,

        /**
         * {@code exported} migration state
         **/
        exported,

        /**
         * {@code failed} migration state
         **/
        failed

    }

    /**
     * {@code id} of the organization migration
     **/
    private final long id;

    /**
     * {@code owner} of the organization migration
     **/
    private final User owner;

    /**
     * {@code guid} of the organization migration
     **/
    private final String guid;

    /**
     * {@code state} of the organization migration
     **/
    private final MigrationState state;

    /**
     * {@code lockRepositories} whether the organization migration has any locked repositories
     **/
    private final boolean lockRepositories;

    /**
     * {@code excludeMetadata} whether the metadata are excluded from the organization migration
     **/
    private final boolean excludeMetadata;

    /**
     * {@code excludeGitData} whether the Git data are excluded from the organization migration
     **/
    private final boolean excludeGitData;

    /**
     * {@code excludeAttachments} whether the attachments are excluded from the organization migration
     **/
    private final boolean excludeAttachments;

    /**
     * {@code excludeReleases} whether the releases are excluded from the organization migration
     **/
    private final boolean excludeReleases;

    /**
     * {@code excludeOwnerProjects} whether the owner projects are excluded from the organization migration
     **/
    private final boolean excludeOwnerProjects;

    /**
     * {@code orgMetadataOnly} whether the organization migration admits only the org metadata
     **/
    private final boolean orgMetadataOnly;

    /**
     * {@code repositories} of the organization migration
     **/
    private final ArrayList<Repository> repositories;

    /**
     * {@code url} of the organization migration
     **/
    private final String url;

    /**
     * {@code createdAt} creation time of the organization migration
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated date of the organization migration
     **/
    private final String updatedAt;

    /**
     * {@code nodeId} node id of the organization migration
     **/
    private final String nodeId;

    /**
     * {@code archiveUrl} archive url of the organization migration
     **/
    private final String archiveUrl;

    /**
     * {@code exclude} list of the exclusions of the organization migration
     **/
    private final ArrayList<String> exclude;

    /**
     * Constructor to init a {@link Migration}
     *
     * @param id:                   id of the organization migration
     * @param owner:                owner of the organization migration
     * @param guid:                 guid of the organization migration
     * @param state:                state of the organization migration
     * @param lockRepositories:     whether the organization migration has any locked repositories
     * @param excludeMetadata:      whether the metadata are excluded from the organization migration
     * @param excludeGitData:       whether the Git data are excluded from the organization migration
     * @param excludeAttachments:   whether the attachments are excluded from the organization migration
     * @param excludeReleases:      whether the releases are excluded from the organization migration
     * @param excludeOwnerProjects: whether the owner projects are excluded from the organization migration
     * @param orgMetadataOnly:      whether the organization migration admits only the org metadata
     * @param repositories:         repositories of the organization migration
     * @param url:                  url of the organization migration
     * @param createdAt:            creation time of the organization migration
     * @param updatedAt:            updated date of the organization migration
     * @param nodeId:               node id of the organization migration
     * @param archiveUrl:           archive url of the organization migration
     * @param exclude:              list of the exclusions of the organization migration
     **/
    public Migration(long id, User owner, String guid, MigrationState state, boolean lockRepositories,
                     boolean excludeMetadata, boolean excludeGitData, boolean excludeAttachments,
                     boolean excludeReleases, boolean excludeOwnerProjects, boolean orgMetadataOnly,
                     ArrayList<Repository> repositories, String url, String createdAt,
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

    /**
     * Constructor to init a {@link Migration}
     *
     * @param jOrganizationMigration: organization migration details as {@link JSONObject}
     **/
    public Migration(JSONObject jOrganizationMigration) {
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
        repositories = returnRepositoriesList(hResponse.getJSONArray("repositories"));
        url = hResponse.getString("url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        nodeId = hResponse.getString("node_id");
        archiveUrl = hResponse.getString("archive_url");
        exclude = returnStringsList(hResponse.getJSONArray("exclude"));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #owner} instance <br>
     * No-any params required
     *
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
        return owner;
    }

    /**
     * Method to get {@link #guid} instance <br>
     * No-any params required
     *
     * @return {@link #guid} instance as {@link String}
     **/
    public String getGuid() {
        return guid;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link MigrationState}
     **/
    public MigrationState getState() {
        return state;
    }

    /**
     * Method to get {@link #lockRepositories} instance <br>
     * No-any params required
     *
     * @return {@link #lockRepositories} instance as boolean
     **/
    public boolean areRepositoriesLocked() {
        return lockRepositories;
    }

    /**
     * Method to get {@link #excludeMetadata} instance <br>
     * No-any params required
     *
     * @return {@link #excludeMetadata} instance as boolean
     **/
    public boolean areMetadataExcluded() {
        return excludeMetadata;
    }

    /**
     * Method to get {@link #excludeGitData} instance <br>
     * No-any params required
     *
     * @return {@link #excludeGitData} instance as boolean
     **/
    public boolean areGitDataExcluded() {
        return excludeGitData;
    }

    /**
     * Method to get {@link #excludeAttachments} instance <br>
     * No-any params required
     *
     * @return {@link #excludeAttachments} instance as boolean
     **/
    public boolean areAttachmentsExcluded() {
        return excludeAttachments;
    }

    /**
     * Method to get {@link #excludeReleases} instance <br>
     * No-any params required
     *
     * @return {@link #excludeReleases} instance as boolean
     **/
    public boolean areReleasesExcluded() {
        return excludeReleases;
    }

    /**
     * Method to get {@link #excludeOwnerProjects} instance <br>
     * No-any params required
     *
     * @return {@link #excludeOwnerProjects} instance as boolean
     **/
    public boolean areOwnerProjectsExcluded() {
        return excludeOwnerProjects;
    }

    /**
     * Method to get {@link #orgMetadataOnly} instance <br>
     * No-any params required
     *
     * @return {@link #orgMetadataOnly} instance as boolean
     **/
    public boolean orgMetadataOnly() {
        return orgMetadataOnly;
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * No-any params required
     *
     * @return {@link #repositories} instance as {@link ArrayList} of {@link Repository}
     **/
    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #archiveUrl} instance <br>
     * No-any params required
     *
     * @return {@link #archiveUrl} instance as {@link String}
     **/
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     * Method to get {@link #exclude} instance <br>
     * No-any params required
     *
     * @return {@link #exclude} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getExclude() {
        return exclude;
    }

    /**
     * Method to create a migrations list
     *
     * @param migrationsResponse : obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return migrations list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnMigrations(String migrationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(migrationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Migration> migrations = new ArrayList<>();
                JSONArray jMigrations = new JSONArray(migrationsResponse);
                for (int j = 0; j < jMigrations.length(); j++)
                    migrations.add(new Migration(jMigrations.getJSONObject(j)));
                return (T) migrations;
            default:
                return (T) migrationsResponse;
        }
    }

    /**
     * Method to create a migration
     *
     * @param migrationResponse : obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnMigration(String migrationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(migrationResponse);
            case LIBRARY_OBJECT:
                return (T) new Migration(new JSONObject(migrationResponse));
            default:
                return (T) migrationResponse;
        }
    }

}
