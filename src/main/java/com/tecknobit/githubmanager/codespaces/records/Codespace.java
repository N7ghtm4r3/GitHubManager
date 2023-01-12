package com.tecknobit.githubmanager.codespaces.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Codespace} class is useful to format a GitHub's codespace
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-in-a-repository">
 *             Create a codespace in a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
 *             Create a codespace from a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-for-the-authenticated-user">
 *             Create a codespace for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#create-a-codespace-from-a-pull-request">
 *             Create a codespace from a pull request</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#update-a-codespace-for-the-authenticated-user">
 *             Update a codespace for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#start-a-codespace-for-the-authenticated-user">
 *             Start a codespace for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#stop-a-codespace-for-the-authenticated-user">
 *             Stop a codespace for the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Codespace extends BaseResponseDetails {

    /**
     * {@code displayName} display name for this codespace
     **/
    private final String displayName;

    /**
     * {@code environmentId} UUID identifying this codespace's environment
     **/
    private final String environmentId;

    /**
     * {@code owner} of the codespace
     **/
    private final User owner;

    /**
     * {@code billableOwner} billable owner of the codespace
     **/
    private final User billableOwner;

    /**
     * {@code repository} of the codespace
     **/
    private final Repository repository;

    /**
     * {@code machine} powering a codespace
     **/
    private final Machine machine;

    /**
     * {@code prebuild} whether the codespace was created from a prebuild
     **/
    private final boolean prebuild;

    /**
     * {@code devContainerPath} path to devcontainer.json from repo root used to create Codespace
     **/
    private final String devContainerPath;

    /**
     * {@code createdAt} creation time of the codespace
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update time of the codespace
     **/
    private final String updatedAt;

    /**
     * {@code lastUsedAt} last known time this codespace was started
     **/
    private final String lastUsedAt;

    /**
     * {@code state} of this codespace
     **/
    private final CodespaceState state;

    /**
     * {@code gitStatus} details about the codespace's git repository
     **/
    private final GitStatus gitStatus;

    /**
     * {@code location} the Azure region where this codespace is located
     **/
    private final CodespaceLocation location;

    /**
     * {@code idleTimeoutMinutes} the number of minutes of inactivity after which this codespace will be automatically stopped
     **/
    private final int idleTimeoutMinutes;

    /**
     * {@code webUrl} URL to access this codespace on the web
     **/
    private final String webUrl;

    /**
     * {@code machinesUrl} API URL to access available alternate machine types for this codespace
     **/
    private final String machinesUrl;

    /**
     * {@code startUrl} API URL to start this codespace
     **/
    private final String startUrl;

    /**
     * {@code stopUrl} API URL to stop this codespace
     **/
    private final String stopUrl;

    /**
     * {@code pullsUrl} API URL for the Pull Request associated with this codespace, if any
     **/
    private final String pullsUrl;

    /**
     * {@code recentFolders} recent folders list
     **/
    private final ArrayList<String> recentFolders;

    /**
     * {@code runtimeConstraints} the privacy settings a user can select from when forwarding a port
     **/
    private final ArrayList<String> runtimeConstraints;

    /**
     * {@code pendingOperation} whether or not a codespace has a pending async operation. This would mean that the codespace is temporarily
     * unavailable. The only thing that you can do with a codespace in this state is delete it
     **/
    private final boolean pendingOperation;

    /**
     * {@code pendingOperationDisabledReason} text to show user when codespace is disabled by a pending operation
     **/
    private final String pendingOperationDisabledReason;

    /**
     * {@code idleTimeoutNotice} text to show user when codespace idle timeout minutes has been overridden by an organization policy
     **/
    private final String idleTimeoutNotice;

    /**
     * {@code retentionPeriodMinutes} duration in minutes after codespace has gone idle in which it will be deleted. Must be integer minutes
     * between 0 and 43200 (30 days)
     **/
    private final int retentionPeriodMinutes;

    /**
     * {@code retentionExpiresAt} when a codespace will be auto-deleted based on the {@code "retention_period_minutes}
     * and {@code "last_used_at"}
     **/
    private final String retentionExpiresAt;

    /**
     * {@code lastKnownStopNotice} the text to display to a user when a codespace has been stopped for a potentially actionable reason
     **/
    private final String lastKnownStopNotice;

    /**
     * Constructor to init a {@link Codespace}
     *
     * @param id                             : identifier value
     * @param name                           : automatically generated name of this codespace
     * @param url                            : API URL for this codespace
     * @param displayName:                   UUID identifying this codespace's environment
     * @param environmentId                  : identifier value
     * @param owner                          : owner of the codespace
     * @param billableOwner                  : billable owner of the codespace
     * @param repository:                    repository of the codespace
     * @param machine                        : machine powering a codespace
     * @param prebuild                       : whether the codespace was created from a prebuild
     * @param devContainerPath               : path to devcontainer.json from repo root used to create Codespace
     * @param createdAt:                     creation time of the codespace
     * @param updatedAt                      : update time of the codespace
     * @param lastUsedAt                     : last known time this codespace was started
     * @param state                          : state of this codespace
     * @param gitStatus:                     details about the codespace's git repository
     * @param location                       : the Azure region where this codespace is located
     * @param idleTimeoutMinutes             : the number of minutes of inactivity after which this codespace will be automatically stopped
     * @param webUrl                         : URL to access this codespace on the web
     * @param machinesUrl:                   API URL to access available alternate machine types for this codespace
     * @param startUrl                       : API URL to start this codespace
     * @param stopUrl                        : API URL to stop this codespace
     * @param pullsUrl                       : API URL for the Pull Request associated with this codespace, if any
     * @param recentFolders:                 recent folders list
     * @param runtimeConstraints             : the privacy settings a user can select from when forwarding a port
     * @param pendingOperation               : whether a codespace has a pending async operation. This would mean that the codespace is temporarily
     *                                       unavailable. The only thing that you can do with a codespace in this state is delete it
     * @param pendingOperationDisabledReason : text to show user when codespace is disabled by a pending operation
     * @param idleTimeoutNotice:             text to show user when codespace idle timeout minutes has been overridden by an organization policy
     * @param retentionPeriodMinutes         : duration in minutes after codespace has gone idle in which it will be deleted.
     *                                       Must be integer minutes between 0 and 43200 (30 days)
     * @param retentionExpiresAt             : when a codespace will be auto-deleted based on the {@code "retention_period_minutes}
     *                                       and {@code "last_used_at"}
     * @param lastKnownStopNotice            : the text to display to a user when a codespace has been stopped for a potentially actionable reason
     **/
    public Codespace(long id, String name, String url, String displayName, String environmentId, User owner,
                     User billableOwner, Repository repository, Machine machine, boolean prebuild, String devContainerPath,
                     String createdAt, String updatedAt, String lastUsedAt, CodespaceState state, GitStatus gitStatus,
                     CodespaceLocation location, int idleTimeoutMinutes, String webUrl, String machinesUrl, String startUrl,
                     String stopUrl, String pullsUrl, ArrayList<String> recentFolders, ArrayList<String> runtimeConstraints,
                     boolean pendingOperation, String pendingOperationDisabledReason, String idleTimeoutNotice,
                     int retentionPeriodMinutes, String retentionExpiresAt, String lastKnownStopNotice) {
        super(id, name, url);
        this.displayName = displayName;
        this.environmentId = environmentId;
        this.owner = owner;
        this.billableOwner = billableOwner;
        this.repository = repository;
        this.machine = machine;
        this.prebuild = prebuild;
        this.devContainerPath = devContainerPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastUsedAt = lastUsedAt;
        this.state = state;
        this.gitStatus = gitStatus;
        this.location = location;
        this.idleTimeoutMinutes = idleTimeoutMinutes;
        this.webUrl = webUrl;
        this.machinesUrl = machinesUrl;
        this.startUrl = startUrl;
        this.stopUrl = stopUrl;
        this.pullsUrl = pullsUrl;
        this.recentFolders = recentFolders;
        this.runtimeConstraints = runtimeConstraints;
        this.pendingOperation = pendingOperation;
        this.pendingOperationDisabledReason = pendingOperationDisabledReason;
        this.idleTimeoutNotice = idleTimeoutNotice;
        this.retentionPeriodMinutes = retentionPeriodMinutes;
        this.retentionExpiresAt = retentionExpiresAt;
        this.lastKnownStopNotice = lastKnownStopNotice;
    }

    /**
     * Constructor to init a {@link Codespace}
     *
     * @param jCodespace : codespace details as {@link JSONObject}
     **/
    public Codespace(JSONObject jCodespace) {
        super(jCodespace);
        displayName = hResponse.getString("display_name");
        environmentId = hResponse.getString("environment_id");
        owner = new User(hResponse.getJSONObject("owner", new JSONObject()));
        billableOwner = new User(hResponse.getJSONObject("billable_owner", new JSONObject()));
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
        machine = new Machine(hResponse.getJSONObject("machine", new JSONObject()));
        prebuild = hResponse.getBoolean("prebuild");
        devContainerPath = hResponse.getString("devcontainer_path");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        lastUsedAt = hResponse.getString("last_used_at");
        state = CodespaceState.valueOf(hResponse.getString("state"));
        gitStatus = new GitStatus(hResponse.getJSONObject("git_status", new JSONObject()));
        String sLocation = hResponse.getString("location");
        if (sLocation != null)
            location = CodespaceLocation.valueOf(sLocation);
        else
            location = null;
        idleTimeoutMinutes = hResponse.getInt("idle_timeout_minutes", 0);
        webUrl = hResponse.getString("web_url");
        machinesUrl = hResponse.getString("machines_url");
        startUrl = hResponse.getString("start_url");
        stopUrl = hResponse.getString("stop_url");
        pullsUrl = hResponse.getString("pulls_url");
        recentFolders = returnStringsList(hResponse.getJSONArray("recent_folders"));
        runtimeConstraints = hResponse.fetchList("allowed_port_privacy_settings");
        pendingOperation = hResponse.getBoolean("pending_operation");
        pendingOperationDisabledReason = hResponse.getString("pending_operation_disabled_reason");
        idleTimeoutNotice = hResponse.getString("idle_timeout_notice");
        retentionPeriodMinutes = hResponse.getInt("retention_period_minutes", 0);
        retentionExpiresAt = hResponse.getString("retention_expires_at");
        lastKnownStopNotice = hResponse.getString("last_known_stop_notice");
    }

    /**
     * Method to get {@link #displayName} instance <br>
     * Any params required
     *
     * @return {@link #displayName} instance as {@link String}
     **/
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Method to get {@link #environmentId} instance <br>
     * Any params required
     *
     * @return {@link #environmentId} instance as {@link String}
     **/
    public String getEnvironmentId() {
        return environmentId;
    }

    /**
     * Method to get {@link #owner} instance <br>
     * Any params required
     *
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
        return owner;
    }

    /**
     * Method to get {@link #billableOwner} instance <br>
     * Any params required
     *
     * @return {@link #billableOwner} instance as {@link User}
     **/
    public User getBillableOwner() {
        return billableOwner;
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
     * Method to get {@link #machine} instance <br>
     * Any params required
     *
     * @return {@link #machine} instance as {@link Machine}
     **/
    public Machine getMachine() {
        return machine;
    }

    /**
     * Method to get {@link #prebuild} instance <br>
     * Any params required
     *
     * @return {@link #prebuild} instance as boolean
     **/
    public boolean isPrebuild() {
        return prebuild;
    }

    /**
     * Method to get {@link #devContainerPath} instance <br>
     * Any params required
     *
     * @return {@link #devContainerPath} instance as {@link String}
     **/
    public String getDevContainerPath() {
        return devContainerPath;
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
     * Method to get {@link #lastUsedAt} instance <br>
     * Any params required
     *
     * @return {@link #lastUsedAt} instance as {@link String}
     **/
    public String getLastUsedAt() {
        return lastUsedAt;
    }

    /**
     * Method to get {@link #lastUsedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #lastUsedAt} timestamp as long
     **/
    public long getLastUsedAtTimestamp() {
        return getDateTimestamp(lastUsedAt);
    }

    /**
     * Method to get {@link #state} instance <br>
     * Any params required
     *
     * @return {@link #state} instance as {@link CodespaceState}
     **/
    public CodespaceState getState() {
        return state;
    }

    /**
     * Method to get {@link #gitStatus} instance <br>
     * Any params required
     *
     * @return {@link #gitStatus} instance as {@link GitStatus}
     **/
    public GitStatus getGitStatus() {
        return gitStatus;
    }

    /**
     * Method to get {@link #location} instance <br>
     * Any params required
     *
     * @return {@link #location} instance as {@link CodespaceLocation}
     **/
    public CodespaceLocation getLocation() {
        return location;
    }

    /**
     * Method to get {@link #idleTimeoutMinutes} instance <br>
     * Any params required
     *
     * @return {@link #idleTimeoutMinutes} instance as int
     **/
    public int getIdleTimeoutMinutes() {
        return idleTimeoutMinutes;
    }

    /**
     * Method to get {@link #webUrl} instance <br>
     * Any params required
     *
     * @return {@link #webUrl} instance as {@link String}
     **/
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Method to get {@link #machinesUrl} instance <br>
     * Any params required
     *
     * @return {@link #machinesUrl} instance as {@link String}
     **/
    public String getMachinesUrl() {
        return machinesUrl;
    }

    /**
     * Method to get {@link #startUrl} instance <br>
     * Any params required
     *
     * @return {@link #startUrl} instance as {@link String}
     **/
    public String getStartUrl() {
        return startUrl;
    }

    /**
     * Method to get {@link #stopUrl} instance <br>
     * Any params required
     *
     * @return {@link #stopUrl} instance as {@link String}
     **/
    public String getStopUrl() {
        return stopUrl;
    }

    /**
     * Method to get {@link #pullsUrl} instance <br>
     * Any params required
     *
     * @return {@link #pullsUrl} instance as {@link String}
     **/
    public String getPullsUrl() {
        return pullsUrl;
    }

    /**
     * Method to get {@link #recentFolders} instance <br>
     * Any params required
     *
     * @return {@link #recentFolders} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getRecentFolders() {
        return recentFolders;
    }

    /**
     * Method to get {@link #runtimeConstraints} instance <br>
     * Any params required
     *
     * @return {@link #runtimeConstraints} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getRuntimeConstraints() {
        return runtimeConstraints;
    }

    /**
     * Method to get {@link #pendingOperation} instance <br>
     * Any params required
     *
     * @return {@link #pendingOperation} instance as boolean
     **/
    public boolean isPendingOperation() {
        return pendingOperation;
    }

    /**
     * Method to get {@link #pendingOperationDisabledReason} instance <br>
     * Any params required
     *
     * @return {@link #pendingOperationDisabledReason} instance as {@link String}
     **/
    public String getPendingOperationDisabledReason() {
        return pendingOperationDisabledReason;
    }

    /**
     * Method to get {@link #idleTimeoutNotice} instance <br>
     * Any params required
     *
     * @return {@link #idleTimeoutNotice} instance as {@link String}
     **/
    public String getIdleTimeoutNotice() {
        return idleTimeoutNotice;
    }

    /**
     * Method to get {@link #retentionPeriodMinutes} instance <br>
     * Any params required
     *
     * @return {@link #retentionPeriodMinutes} instance as int
     **/
    public int getRetentionPeriodMinutes() {
        return retentionPeriodMinutes;
    }

    /**
     * Method to get {@link #retentionExpiresAt} instance <br>
     * Any params required
     *
     * @return {@link #retentionExpiresAt} instance as {@link String}
     **/
    public String getRetentionExpiresAt() {
        return retentionExpiresAt;
    }

    /**
     * Method to get {@link #retentionExpiresAt} timestamp <br>
     * Any params required
     *
     * @return {@link #retentionExpiresAt} timestamp as long
     **/
    public long getRetentionExpiresAtTimestamp() {
        return getDateTimestamp(retentionExpiresAt);
    }

    /**
     * Method to get {@link #lastKnownStopNotice} instance <br>
     * Any params required
     *
     * @return {@link #lastKnownStopNotice} instance as {@link String}
     **/
    public String getLastKnownStopNotice() {
        return lastKnownStopNotice;
    }

    /**
     * Method to create a codespace
     *
     * @param codespaceResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespace as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnCodespace(String codespaceResponse, GitHubManager.ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespaceResponse);
            case LIBRARY_OBJECT:
                return (T) new Codespace(new JSONObject(codespaceResponse));
            default:
                return (T) codespaceResponse;
        }
    }

    /**
     * {@code CodespaceState} list of available states
     **/
    public enum CodespaceState {

        /**
         * {@code Unknown} state
         **/
        Unknown,

        /**
         * {@code Created} state
         **/
        Created,

        /**
         * {@code Queued} state
         **/
        Queued,

        /**
         * {@code Provisioning} state
         **/
        Provisioning,

        /**
         * {@code Available} state
         **/
        Available,

        /**
         * {@code Awaiting} state
         **/
        Awaiting,

        /**
         * {@code Unavailable} state
         **/
        Unavailable,

        /**
         * {@code Deleted} state
         **/
        Deleted,

        /**
         * {@code Moved} state
         **/
        Moved,

        /**
         * {@code Shutdown} state
         **/
        Shutdown,

        /**
         * {@code Archived} state
         **/
        Archived,

        /**
         * {@code Starting} state
         **/
        Starting,

        /**
         * {@code ShuttingDown} state
         **/
        ShuttingDown,

        /**
         * {@code Failed} state
         **/
        Failed,

        /**
         * {@code Exporting} state
         **/
        Exporting,

        /**
         * {@code Updating} state
         **/
        Updating,

        /**
         * {@code Rebuilding} state
         **/
        Rebuilding,

        /**
         * {@code in_progress} state
         **/
        in_progress

    }

    /**
     * {@code CodespaceLocation} list of available locations
     **/
    public enum CodespaceLocation {

        /**
         * {@code EastUs} location
         **/
        EastUs,

        /**
         * {@code SouthEastAsia} location
         **/
        SouthEastAsia,

        /**
         * {@code WestEurope} location
         **/
        WestEurope,

        /**
         * {@code WestUs2} location
         **/
        WestUs2

    }

    /**
     * The {@code Machine} class is useful to format a GitHub's machine for a {@link Codespace}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Machine {

        /**
         * {@code name} the name of the machine
         **/
        private final String name;

        /**
         * {@code displayName} the display name of the machine includes cores, memory, and storage
         **/
        private final String displayName;

        /**
         * {@code operatingSystem} the operating system of the machine
         **/
        private final String operatingSystem;

        /**
         * {@code storageInBytes} how much storage is available to the codespace
         **/
        private final double storageInBytes;

        /**
         * {@code memoryInBytes} how much memory is available to the codespace
         **/
        private final double memoryInBytes;

        /**
         * {@code cpus} how many cores are available to the codespace
         **/
        private final int cpus;

        /**
         * {@code prebuildAvailability} whether a prebuild is currently available when creating a codespace for this machine and repository.
         * If a branch was not specified as a ref, the default branch will be assumed. Value will be {@code "null"} if prebuilds
         * are not supported or prebuild availability could not be determined.
         * Value will be {@code "none"} if no prebuild is available. Latest values {@code "ready"} and {@code "in_progress"}
         * indicate the prebuild availability status
         **/
        private final PrebuildAvailability prebuildAvailability;

        /**
         * Constructor to init a {@link Machine}
         *
         * @param name                 : the name of the machine
         * @param displayName          : the display name of the machine includes cores, memory, and storage
         * @param operatingSystem      : the operating system of the machine
         * @param storageInBytes       : how much storage is available to the codespace
         * @param memoryInBytes        : how much memory is available to the codespace
         * @param cpus                 : how many cores are available to the codespace
         * @param prebuildAvailability : whether a prebuild is currently available when creating a codespace for this machine and repository
         **/
        public Machine(String name, String displayName, String operatingSystem, double storageInBytes, double memoryInBytes,
                       int cpus, PrebuildAvailability prebuildAvailability) {
            this.name = name;
            this.displayName = displayName;
            this.operatingSystem = operatingSystem;
            this.storageInBytes = storageInBytes;
            this.memoryInBytes = memoryInBytes;
            this.cpus = cpus;
            this.prebuildAvailability = prebuildAvailability;
        }

        /**
         * Constructor to init a {@link Machine}
         *
         * @param jMachine : machine details as {@link JSONObject}
         **/
        public Machine(JSONObject jMachine) {
            JsonHelper hMachine = new JsonHelper(jMachine);
            name = hMachine.getString("name");
            displayName = hMachine.getString("display_name");
            operatingSystem = hMachine.getString("operating_system");
            storageInBytes = hMachine.getDouble("storage_in_bytes", 0);
            memoryInBytes = hMachine.getDouble("memory_in_bytes", 0);
            cpus = hMachine.getInt("cpus", 0);
            String sPrebuildAvailability = hMachine.getString("prebuild_availability");
            if (sPrebuildAvailability != null)
                prebuildAvailability = PrebuildAvailability.valueOf(sPrebuildAvailability);
            else
                prebuildAvailability = null;
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
         * Method to get {@link #displayName} instance <br>
         * Any params required
         *
         * @return {@link #displayName} instance as {@link String}
         **/
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Method to get {@link #operatingSystem} instance <br>
         * Any params required
         *
         * @return {@link #operatingSystem} instance as {@link String}
         **/
        public String getOperatingSystem() {
            return operatingSystem;
        }

        /**
         * Method to get {@link #storageInBytes} instance <br>
         * Any params required
         *
         * @return {@link #storageInBytes} instance as double
         **/
        public double getStorageInBytes() {
            return storageInBytes;
        }

        /**
         * Method to get {@link #storageInBytes} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #storageInBytes} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getStorageInBytes(int decimals) {
            return roundValue(storageInBytes, decimals);
        }

        /**
         * Method to get {@link #memoryInBytes} instance <br>
         * Any params required
         *
         * @return {@link #memoryInBytes} instance as double
         **/
        public double getMemoryInBytes() {
            return memoryInBytes;
        }

        /**
         * Method to get {@link #memoryInBytes} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #memoryInBytes} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getMemoryInBytes(int decimals) {
            return roundValue(memoryInBytes, decimals);
        }

        /**
         * Method to get {@link #cpus} instance <br>
         * Any params required
         *
         * @return {@link #cpus} instance as int
         **/
        public int getCpus() {
            return cpus;
        }

        /**
         * Method to get {@link #prebuildAvailability} instance <br>
         * Any params required
         *
         * @return {@link #prebuildAvailability} instance as {@link String}
         **/
        public PrebuildAvailability getPrebuildAvailability() {
            return prebuildAvailability;
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
         * {@code PrebuildAvailability} list of available prebuild availabilities
         **/
        public enum PrebuildAvailability {

            /**
             * {@code none} prebuild availability
             **/
            none,

            /**
             * {@code ready} prebuild availability
             **/
            ready,

            /**
             * {@code in_progress} prebuild availability
             **/
            in_progress

        }

    }

    /**
     * The {@code GitStatus} class is useful to format a GitHub's git status for a {@link Codespace}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class GitStatus {

        /**
         * {@code ahead} the number of commits the local repository is ahead of the remote
         **/
        private final int ahead;

        /**
         * {@code behind} the number of commits the local repository is behind the remote
         **/
        private final int behind;

        /**
         * {@code hasUnpushedChanges} whether the local repository has unpushed changes
         **/
        private final boolean hasUnpushedChanges;

        /**
         * {@code hasUncommittedChanges} whether the local repository has uncommitted changes
         **/
        private final boolean hasUncommittedChanges;

        /**
         * {@code ref} the current branch (or SHA if in detached HEAD state) of the local repository
         **/
        private final String ref;

        /**
         * Constructor to init a {@link GitStatus}
         *
         * @param ahead                 :the number of commits the local repository is ahead of the remote
         * @param behind                : the number of commits the local repository is behind the remote
         * @param hasUnpushedChanges    : whether the local repository has unpushed changes
         * @param hasUncommittedChanges : whether the local repository has uncommitted changes
         * @param ref                   : the current branch (or SHA if in detached HEAD state) of the local repository
         **/
        public GitStatus(int ahead, int behind, boolean hasUnpushedChanges, boolean hasUncommittedChanges, String ref) {
            this.ahead = ahead;
            this.behind = behind;
            this.hasUnpushedChanges = hasUnpushedChanges;
            this.hasUncommittedChanges = hasUncommittedChanges;
            this.ref = ref;
        }

        /**
         * Constructor to init a {@link GitStatus}
         *
         * @param jGitStatus : git status details as {@link JSONObject}
         **/
        public GitStatus(JSONObject jGitStatus) {
            JsonHelper hGitStatus = new JsonHelper(jGitStatus);
            ahead = hGitStatus.getInt("ahead", 0);
            behind = hGitStatus.getInt("behind", 0);
            hasUnpushedChanges = hGitStatus.getBoolean("has_unpushed_changes");
            hasUncommittedChanges = hGitStatus.getBoolean("has_uncommitted_changes");
            ref = hGitStatus.getString("ref");
        }

        /**
         * Method to get {@link #ahead} instance <br>
         * Any params required
         *
         * @return {@link #ahead} instance as int
         **/
        public int getAhead() {
            return ahead;
        }

        /**
         * Method to get {@link #behind} instance <br>
         * Any params required
         *
         * @return {@link #behind} instance as int
         **/
        public int getBehind() {
            return behind;
        }

        /**
         * Method to get {@link #hasUnpushedChanges} instance <br>
         * Any params required
         *
         * @return {@link #hasUnpushedChanges} instance as boolean
         **/
        public boolean hasUnpushedChanges() {
            return hasUnpushedChanges;
        }

        /**
         * Method to get {@link #hasUncommittedChanges} instance <br>
         * Any params required
         *
         * @return {@link #hasUncommittedChanges} instance as boolean
         **/
        public boolean hasUncommittedChanges() {
            return hasUncommittedChanges;
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

}
