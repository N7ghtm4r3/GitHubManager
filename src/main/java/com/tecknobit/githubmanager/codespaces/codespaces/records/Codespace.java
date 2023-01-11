package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Codespace extends BaseResponseDetails {

    private final String environmentId;
    private final User owner;
    private final User billableOwner;
    private final Repository repository;
    private final Machine machine;
    private final boolean prebuild;
    private final String devContainerPath;
    private final String createdAt;
    private final String updatedAt;
    private final String lastUsedAt;
    private final CodespaceState state;
    private final GitStatus gitStatus;
    private final CodespaceLocation location;
    private final int idleTimeoutMinutes;
    private final String webUrl;
    private final String machinesUrl;
    private final String startUrl;
    private final String stopUrl;
    private final String pullsUrl;
    private final ArrayList<String> recentFolders;
    private final ArrayList<String> runtimeConstraints;
    private final boolean pendingOperation;
    private final String pendingOperationDisabledReason;
    private final String idleTimeoutNotice;
    private final int retentionPeriodMinutes;
    private final String retentionExpiresAt;
    private final String lastKnownStopNotice;
    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url  : url value
     **/
    public Codespace(long id, String name, String url, String environmentId, User owner, User billableOwner,
                     Repository repository, Machine machine, boolean prebuild, String devContainerPath, String createdAt,
                     String updatedAt, String lastUsedAt, CodespaceState state, GitStatus gitStatus,
                     CodespaceLocation location, int idleTimeoutMinutes, String webUrl, String machinesUrl, String startUrl,
                     String stopUrl, String pullsUrl, ArrayList<String> recentFolders, ArrayList<String> runtimeConstraints,
                     boolean pendingOperation, String pendingOperationDisabledReason, String idleTimeoutNotice,
                     int retentionPeriodMinutes, String retentionExpiresAt, String lastKnownStopNotice) {
        super(id, name, url);
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
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param jCodespace : basics response details as {@link JSONObject}
     **/
    public Codespace(JSONObject jCodespace) {
        super(jCodespace);
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

    public String getEnvironmentId() {
        return environmentId;
    }

    public User getOwner() {
        return owner;
    }

    public User getBillableOwner() {
        return billableOwner;
    }

    public Repository getRepository() {
        return repository;
    }

    public Machine getMachine() {
        return machine;
    }

    public boolean isPrebuild() {
        return prebuild;
    }

    public String getDevContainerPath() {
        return devContainerPath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getLastUsedAt() {
        return lastUsedAt;
    }

    public CodespaceState getState() {
        return state;
    }

    public GitStatus getGitStatus() {
        return gitStatus;
    }

    public CodespaceLocation getLocation() {
        return location;
    }

    public int getIdleTimeoutMinutes() {
        return idleTimeoutMinutes;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getMachinesUrl() {
        return machinesUrl;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public String getStopUrl() {
        return stopUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public Collection<String> getRecentFolders() {
        return recentFolders;
    }

    public Collection<String> getRuntimeConstraints() {
        return runtimeConstraints;
    }

    public boolean isPendingOperation() {
        return pendingOperation;
    }

    public String getPendingOperationDisabledReason() {
        return pendingOperationDisabledReason;
    }

    public String getIdleTimeoutNotice() {
        return idleTimeoutNotice;
    }

    public int getRetentionPeriodMinutes() {
        return retentionPeriodMinutes;
    }

    public String getRetentionExpiresAt() {
        return retentionExpiresAt;
    }

    public String getLastKnownStopNotice() {
        return lastKnownStopNotice;
    }

    public enum CodespaceState {

        Unknown,
        Created,
        Queued,
        Provisioning,
        Available,
        Awaiting,
        Unavailable,
        Deleted,
        Moved,
        Shutdown,
        Archived,
        Starting,
        ShuttingDown,
        Failed,
        Exporting,
        Updating,
        Rebuilding,
        in_progress

    }

    public enum CodespaceLocation {

        EastUs,
        SouthEastAsia,
        WestEurope,
        WestUs2

    }

    public static class Machine {

        private final String name;
        private final String displayName;
        private final String operatingSystem;
        private final double storageInBytes;
        private final double memoryInBytes;
        private final int cpus;
        private final PrebuildAvailability prebuildAvailability;
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

        public String getName() {
            return name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getOperatingSystem() {
            return operatingSystem;
        }

        public double getStorageInBytes() {
            return storageInBytes;
        }

        public double getMemoryInBytes() {
            return memoryInBytes;
        }

        public int getCpus() {
            return cpus;
        }

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

        public enum PrebuildAvailability {

            none,
            ready,
            in_progress

        }

    }

    public static class GitStatus {

        private final int ahead;
        private final int behind;
        private final boolean hasUnpushedChanges;
        private final boolean hasUncommittedChanges;
        private final String ref;

        public GitStatus(int ahead, int behind, boolean hasUnpushedChanges, boolean hasUncommittedChanges, String ref) {
            this.ahead = ahead;
            this.behind = behind;
            this.hasUnpushedChanges = hasUnpushedChanges;
            this.hasUncommittedChanges = hasUncommittedChanges;
            this.ref = ref;
        }

        public GitStatus(JSONObject jGitStatus) {
            JsonHelper hGitStatus = new JsonHelper(jGitStatus);
            ahead = hGitStatus.getInt("ahead", 0);
            behind = hGitStatus.getInt("behind", 0);
            hasUnpushedChanges = hGitStatus.getBoolean("has_unpushed_changes");
            hasUncommittedChanges = hGitStatus.getBoolean("has_uncommitted_changes");
            ref = hGitStatus.getString("ref");
        }

        public int getAhead() {
            return ahead;
        }

        public int getBehind() {
            return behind;
        }

        public boolean hasUnpushedChanges() {
            return hasUnpushedChanges;
        }

        public boolean hasUncommittedChanges() {
            return hasUncommittedChanges;
        }

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
