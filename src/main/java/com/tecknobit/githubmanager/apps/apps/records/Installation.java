package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection.all;

public class Installation extends GitHubResponse {

    private final long id;
    private final User account;
    private final String accessTokensUrl;
    private final String repositoriesUrl;
    private final String htmlUrl;
    private final long appId;
    private final long targetId;
    private final String targetType;
    private final AppPermissions permissions;
    private final ArrayList<String> events;
    private final String singleFileName;
    private final boolean hasMultipleSingleFiles;
    private final ArrayList<String> singleFilePaths;
    private final RepositorySelection repositorySelection;
    private final String createdAt;
    private final String updatedAt;
    private final String appSlug;
    private final String suspendedAt;
    private final String suspendedBy;

    public Installation(long id, User account, String accessTokensUrl, String repositoriesUrl, String htmlUrl, long appId,
                        long targetId, String targetType, AppPermissions permissions, ArrayList<String> events,
                        String singleFileName, boolean hasMultipleSingleFiles, ArrayList<String> singleFilePaths,
                        RepositorySelection repositorySelection, String createdAt, String updatedAt, String appSlug,
                        String suspendedAt, String suspendedBy) {
        super(null);
        this.id = id;
        this.account = account;
        this.accessTokensUrl = accessTokensUrl;
        this.repositoriesUrl = repositoriesUrl;
        this.htmlUrl = htmlUrl;
        this.appId = appId;
        this.targetId = targetId;
        this.targetType = targetType;
        this.permissions = permissions;
        this.events = events;
        this.singleFileName = singleFileName;
        this.hasMultipleSingleFiles = hasMultipleSingleFiles;
        this.singleFilePaths = singleFilePaths;
        this.repositorySelection = repositorySelection;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.appSlug = appSlug;
        this.suspendedAt = suspendedAt;
        this.suspendedBy = suspendedBy;
    }

    /**
     * Constructor to init a {@link Installation}
     *
     * @param jInstallation : installation details as {@link JSONObject}
     **/
    public Installation(JSONObject jInstallation) throws Exception {
        super(jInstallation);
        id = hResponse.getLong("id", 0);
        account = new User(hResponse.getJSONObject("account", new JSONObject()));
        accessTokensUrl = hResponse.getString("access_tokens_url");
        repositoriesUrl = hResponse.getString("repositories_url");
        htmlUrl = hResponse.getString("html_url");
        appId = hResponse.getLong("app_id", 0);
        targetId = hResponse.getLong("target_id", 0);
        targetType = hResponse.getString("target_type");
        permissions = new AppPermissions(hResponse.getJSONObject("permissions", new JSONObject()));
        events = returnStringList(hResponse.getJSONArray("events", new JSONArray()));
        singleFileName = hResponse.getString("single_file_name");
        hasMultipleSingleFiles = hResponse.getBoolean("has_multiple_single_files");
        singleFilePaths = returnStringList(hResponse.getJSONArray("single_file_paths", new JSONArray()));
        repositorySelection = RepositorySelection.valueOf(hResponse.getString("repository_selection", all.name()));
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        appSlug = hResponse.getString("app_slug");
        suspendedAt = hResponse.getString("suspended_at");
        suspendedBy = hResponse.getString("suspended_by");
    }

    private ArrayList<String> returnStringList(JSONArray jList) {
        ArrayList<String> list = new ArrayList<>();
        for (int j = 0; j < jList.length(); j++)
            list.add(jList.getString(j));
        return list;
    }

    public long getId() {
        return id;
    }

    public User getAccount() {
        return account;
    }

    public String getAccessTokensUrl() {
        return accessTokensUrl;
    }

    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public long getAppId() {
        return appId;
    }

    public long getTargetId() {
        return targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public AppPermissions getPermissions() {
        return permissions;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public String getSingleFileName() {
        return singleFileName;
    }

    public boolean isHasMultipleSingleFiles() {
        return hasMultipleSingleFiles;
    }

    public ArrayList<String> getSingleFilePaths() {
        return singleFilePaths;
    }

    public RepositorySelection getRepositorySelection() {
        return repositorySelection;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getAppSlug() {
        return appSlug;
    }

    public String getSuspendedAt() {
        return suspendedAt;
    }

    public String getSuspendedBy() {
        return suspendedBy;
    }

}
