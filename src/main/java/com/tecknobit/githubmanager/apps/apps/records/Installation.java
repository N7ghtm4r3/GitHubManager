package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection.all;

/**
 * The {@code Installation} class is useful to format a GitHub's installation
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#list-installations-for-the-authenticated-app">
 *             List installations for the authenticated app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-an-installation-for-the-authenticated-app">
 *             Get an installation for the authenticated app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-an-organization-installation-for-the-authenticated-app">
 *             Get an organization installation for the authenticated app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-a-repository-installation-for-the-authenticated-app">
 *             Get a repository installation for the authenticated app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/apps#get-a-user-installation-for-the-authenticated-app">
 *             Get a user installation for the authenticated app</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Installation extends GitHubResponse {

    /**
     * {@code id} the ID of the installation
     **/
    private final long id;

    /**
     * {@code account} of the installation
     **/
    private final User account;

    /**
     * {@code accessTokensUrl} access tokens url of the installation
     **/
    private final String accessTokensUrl;

    /**
     * {@code repositoriesUrl} repositories url of the installation
     **/
    private final String repositoriesUrl;

    /**
     * {@code htmlUrl} html url of the installation
     **/
    private final String htmlUrl;

    /**
     * {@code appId} the ID of the app of the installation
     **/
    private final long appId;

    /**
     * {@code targetId} the ID of the user or organization this token is being scoped to
     **/
    private final long targetId;

    /**
     * {@code targetType} the target type of the installation
     **/
    private final String targetType;

    /**
     * {@code permissions} the permissions granted to the user-to-server access token
     **/
    private final AppPermissions permissions;

    /**
     * {@code events} the list of events of the installation
     **/
    private final ArrayList<String> events;

    /**
     * {@code singleFileName} single file name of the installation
     **/
    private final String singleFileName;

    /**
     * {@code hasMultipleSingleFiles} whether the installation has multiple single files
     **/
    private final boolean hasMultipleSingleFiles;

    /**
     * {@code singleFilePaths} the list of single file paths of the installation
     **/
    private final ArrayList<String> singleFilePaths;

    /**
     * {@code repositorySelection} the repository selection of the installation
     **/
    private final RepositorySelection repositorySelection;

    /**
     * {@code createdAt} the creation time of the installation
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} the update time of the installation
     **/
    private final String updatedAt;

    /**
     * {@code appSlug} the app slug of the installation
     **/
    private final String appSlug;

    /**
     * {@code suspendedAt} the suspension time of the installation
     **/
    private final String suspendedAt;

    /**
     * {@code suspendedBy} the suspended account who suspend the installation
     **/
    private final User suspendedBy;

    /**
     * {@code contactEmail} the contact email of the installation
     **/
    private String contactEmail;

    /**
     * Constructor to init a {@link Installation}
     *
     * @param id:                     the ID of the installation
     * @param account:                the account of the installation
     * @param accessTokensUrl:        access tokens url of the installation
     * @param repositoriesUrl:        repositories url of the installation
     * @param htmlUrl:                html url of the installation
     * @param appId:                  the ID of the app of the installation
     * @param targetId:               the ID of the user or organization this token is being scoped to
     * @param targetType:             the target type of the installation
     * @param permissions:            the permissions granted to the user-to-server access token
     * @param events:                 the list of events of the installation
     * @param singleFileName:         single file name of the installation
     * @param hasMultipleSingleFiles: whether the installation has multiple single files
     * @param singleFilePaths:        the list of single file paths of the installation
     * @param repositorySelection:    the repository selection of the installation
     * @param createdAt:the           creation time of the installation
     * @param updatedAt:              the update time of the installation
     * @param appSlug:                the app slug of the installation
     * @param suspendedAt:            the suspension time of the installation
     * @param suspendedBy:            the suspended account who suspend the installation
     * @param contactEmail:           the contact email of the installation
     **/
    public Installation(long id, User account, String accessTokensUrl, String repositoriesUrl, String htmlUrl, long appId,
                        long targetId, String targetType, AppPermissions permissions, ArrayList<String> events,
                        String singleFileName, boolean hasMultipleSingleFiles, ArrayList<String> singleFilePaths,
                        RepositorySelection repositorySelection, String createdAt, String updatedAt, String appSlug,
                        String suspendedAt, User suspendedBy, String contactEmail) {
        this(id, account, accessTokensUrl, repositoriesUrl, htmlUrl, appId, targetId, targetType, permissions, events,
                singleFileName, hasMultipleSingleFiles, singleFilePaths, repositorySelection, createdAt, updatedAt,
                appSlug, suspendedAt, suspendedBy);
        this.contactEmail = contactEmail;
    }

    /**
     * Constructor to init a {@link Installation}
     *
     * @param id:                     the ID of the installation
     * @param account:                the account of the installation
     * @param accessTokensUrl:        access tokens url of the installation
     * @param repositoriesUrl:        repositories url of the installation
     * @param htmlUrl:                html url of the installation
     * @param appId:                  the ID of the app of the installation
     * @param targetId:               the ID of the user or organization this token is being scoped to
     * @param targetType:             the target type of the installation
     * @param permissions:            the permissions granted to the user-to-server access token
     * @param events:                 the list of events of the installation
     * @param singleFileName:         single file name of the installation
     * @param hasMultipleSingleFiles: whether the installation has multiple single files
     * @param singleFilePaths:        the list of single file paths of the installation
     * @param repositorySelection:    the repository selection of the installation
     * @param createdAt:the           creation time of the installation
     * @param updatedAt:              the update time of the installation
     * @param appSlug:                the app slug of the installation
     * @param suspendedAt:            the suspension time of the installation
     * @param suspendedBy:            the suspended account who suspend the installation
     **/
    public Installation(long id, User account, String accessTokensUrl, String repositoriesUrl, String htmlUrl, long appId,
                        long targetId, String targetType, AppPermissions permissions, ArrayList<String> events,
                        String singleFileName, boolean hasMultipleSingleFiles, ArrayList<String> singleFilePaths,
                        RepositorySelection repositorySelection, String createdAt, String updatedAt, String appSlug,
                        String suspendedAt, User suspendedBy) {
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
        suspendedBy = new User(hResponse.getJSONObject("suspended_by", new JSONObject()));
    }

    /**
     * Method to create a {@link String} list
     *
     * @param jList: {@link JSONArray} source from assemble the list
     * @return list of string a {@link ArrayList} of {@link String}
     **/
    @Returner
    private ArrayList<String> returnStringList(JSONArray jList) {
        ArrayList<String> list = new ArrayList<>();
        for (int j = 0; j < jList.length(); j++)
            list.add(jList.getString(j));
        return list;
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
     * Method to get {@link #account} instance <br>
     * Any params required
     *
     * @return {@link #account} instance as {@link User}
     **/
    public User getAccount() {
        return account;
    }

    /**
     * Method to get {@link #accessTokensUrl} instance <br>
     * Any params required
     *
     * @return {@link #accessTokensUrl} instance as {@link String}
     **/
    public String getAccessTokensUrl() {
        return accessTokensUrl;
    }

    /**
     * Method to get {@link #repositoriesUrl} instance <br>
     * Any params required
     *
     * @return {@link #repositoriesUrl} instance as {@link String}
     **/
    public String getRepositoriesUrl() {
        return repositoriesUrl;
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
     * Method to get {@link #appId} instance <br>
     * Any params required
     *
     * @return {@link #appId} instance as long
     **/
    public long getAppId() {
        return appId;
    }

    /**
     * Method to get {@link #targetId} instance <br>
     * Any params required
     *
     * @return {@link #targetId} instance as long
     **/
    public long getTargetId() {
        return targetId;
    }

    /**
     * Method to get {@link #targetType} instance <br>
     * Any params required
     *
     * @return {@link #targetType} instance as {@link String}
     **/
    public String getTargetType() {
        return targetType;
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * Any params required
     *
     * @return {@link #permissions} instance as {@link AppPermissions}
     **/
    public AppPermissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #events} instance <br>
     * Any params required
     *
     * @return {@link #events} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getEvents() {
        return events;
    }

    /**
     * Method to get {@link #singleFileName} instance <br>
     * Any params required
     *
     * @return {@link #singleFileName} instance as {@link String}
     **/
    public String getSingleFileName() {
        return singleFileName;
    }

    /**
     * Method to get {@link #hasMultipleSingleFiles} instance <br>
     * Any params required
     *
     * @return {@link #hasMultipleSingleFiles} instance as boolean
     **/
    public boolean isHasMultipleSingleFiles() {
        return hasMultipleSingleFiles;
    }

    /**
     * Method to get {@link #singleFilePaths} instance <br>
     * Any params required
     *
     * @return {@link #singleFilePaths} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getSingleFilePaths() {
        return singleFilePaths;
    }

    /**
     * Method to get {@link #repositorySelection} instance <br>
     * Any params required
     *
     * @return {@link #repositorySelection} instance as {@link RepositorySelection}
     **/
    public RepositorySelection getRepositorySelection() {
        return repositorySelection;
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
     * Method to get {@link #appSlug} instance <br>
     * Any params required
     *
     * @return {@link #appSlug} instance as {@link String}
     **/
    public String getAppSlug() {
        return appSlug;
    }

    /**
     * Method to get {@link #suspendedAt} instance <br>
     * Any params required
     *
     * @return {@link #suspendedAt} instance as {@link String}
     **/
    public String getSuspendedAt() {
        return suspendedAt;
    }

    /**
     * Method to get {@link #suspendedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #suspendedAt} timestamp as long
     **/
    public long getSuspendedAtTimestamp() {
        return getDateTimestamp(suspendedAt);
    }

    /**
     * Method to get {@link #suspendedBy} instance <br>
     * Any params required
     *
     * @return {@link #suspendedBy} instance as {@link User}
     **/
    public User getSuspendedBy() {
        return suspendedBy;
    }

    /**
     * Method to get {@link #contactEmail} instance <br>
     * Any params required
     *
     * @return {@link #contactEmail} instance as {@link String}
     **/
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Method to set {@link #contactEmail} instance
     *
     * @param contactEmail: the contact email of the installation
     **/
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

}
