package com.tecknobit.githubmanager.apps.apps.records;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * The {@code AppPermissions} class is useful to format a GitHub's app permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
 * Create an installation access token for an app</a>
 **/
public class AppPermissions {

    /**
     * {@code "actions"} permission type
     **/
    private PermissionType actions;

    /**
     * {@code "administration"} permission type
     **/
    private PermissionType administration;

    /**
     * {@code "checks"} permission type
     **/
    private PermissionType checks;

    /**
     * {@code "contents"} permission type
     **/
    private PermissionType contents;

    /**
     * {@code "deployments"} permission type
     **/
    private PermissionType deployments;

    /**
     * {@code "environments"} permission type
     **/
    private PermissionType environments;

    /**
     * {@code "issues"} permission type
     **/
    private PermissionType issues;

    /**
     * {@code "metadata"} permission type
     **/
    private PermissionType metadata;

    /**
     * {@code "packages"} permission type
     **/
    private PermissionType packages;

    /**
     * {@code "pages"} permission type
     **/
    private PermissionType pages;

    /**
     * {@code "pullRequests"} permission type
     **/
    private PermissionType pullRequests;

    /**
     * {@code "repositoryAnnouncementBanners"} permission type
     **/
    private PermissionType repositoryAnnouncementBanners;

    /**
     * {@code "repositoryHooks"} permission type
     **/
    private PermissionType repositoryHooks;

    /**
     * {@code "repositoryProjects"} (admin allowed) permission type
     **/
    private APermissionType repositoryProjects;

    /**
     * {@code "secretScanningAlerts"} permission type
     **/
    private PermissionType secretScanningAlerts;

    /**
     * {@code "secrets"} permission type
     **/
    private PermissionType secrets;

    /**
     * {@code "securityEvents"} permission type
     **/
    private PermissionType securityEvents;

    /**
     * {@code "singleFile"} permission type
     **/
    private PermissionType singleFile;

    /**
     * {@code "statuses"} permission type
     **/
    private PermissionType statuses;

    /**
     * {@code "vulnerabilityAlerts"} permission type
     **/
    private PermissionType vulnerabilityAlerts;

    /**
     * {@code "workflows"} permission type
     **/
    private PermissionType workflows;

    /**
     * {@code "members"} permission type
     **/
    private PermissionType members;

    /**
     * {@code "organizationAdministration"} permission type
     **/
    private PermissionType organizationAdministration;

    /**
     * {@code "organizationCustomRoles"} permission type
     **/
    private PermissionType organizationCustomRoles;

    /**
     * {@code "organizationAnnouncementBanners"} permission type
     **/
    private PermissionType organizationAnnouncementBanners;

    /**
     * {@code "organizationHooks"} permission type
     **/
    private PermissionType organizationHooks;

    /**
     * {@code "organizationPlan"} permission type
     **/
    private PermissionType organizationPlan;

    /**
     * {@code "organizationProjects"} (admin allowed) permission type
     **/
    private APermissionType organizationProjects;

    /**
     * {@code "organizationPackages"} permission type
     **/
    private PermissionType organizationPackages;

    /**
     * {@code "organizationSecrets"} permission type
     **/
    private PermissionType organizationSecrets;

    /**
     * {@code "organizationSelfHostedRunners"} permission type
     **/
    private PermissionType organizationSelfHostedRunners;

    /**
     * {@code "organizationUserBlocking"} permission type
     **/
    private PermissionType organizationUserBlocking;

    /**
     * {@code "teamDiscussions"} permission type
     **/
    private PermissionType teamDiscussions;

    /**
     * Constructor to init a {@link AppPermissions} <br>
     * No-any params required
     *
     * @apiNote to set the different permissions you can instantiate {@link AppPermissions}
     * with this empty constructor and subsequently set the different permissions with the setter methods
     **/
    public AppPermissions() {
    }

    /**
     * Constructor to init a {@link AppPermissions}
     *
     * @param jPermissions: app permissions as {@link JSONObject} details
     **/
    public AppPermissions(JSONObject jPermissions) throws Exception {
        for (String key : jPermissions.keySet()) {
            String fieldKey;
            if (key.contains("_")) {
                String[] splitKey = key.split("_");
                StringBuilder builder = new StringBuilder(splitKey[0]);
                for (int j = 1; j < splitKey.length; j++) {
                    String starter = "" + splitKey[j].charAt(0);
                    builder.append(splitKey[j].replaceFirst(starter, starter.toUpperCase()));
                }
                fieldKey = builder.toString();
            } else
                fieldKey = key;
            Field field = getClass().getDeclaredField(fieldKey);
            try {
                field.set(this, PermissionType.valueOf(jPermissions.getString(key)));
            } catch (IllegalArgumentException e) {
                field.set(this, APermissionType.valueOf(jPermissions.getString(key)));
            }
        }
    }

    /**
     * Method to get {@link #actions} instance <br>
     * No-any params required
     *
     * @return {@link #actions} instance as {@link PermissionType}
     **/
    public PermissionType getActions() {
        return actions;
    }

    /**
     * Method to set {@link #actions} instance
     *
     * @param actions: permission type
     **/
    public void setActions(PermissionType actions) {
        this.actions = actions;
    }

    /**
     * Method to get {@link #administration} instance <br>
     * No-any params required
     *
     * @return {@link #administration} instance as {@link PermissionType}
     **/
    public PermissionType getAdministration() {
        return administration;
    }

    /**
     * Method to set {@link #administration} instance
     *
     * @param administration: permission type
     **/
    public void setAdministration(PermissionType administration) {
        this.administration = administration;
    }

    /**
     * Method to get {@link #checks} instance <br>
     * No-any params required
     *
     * @return {@link #checks} instance as {@link PermissionType}
     **/
    public PermissionType getChecks() {
        return checks;
    }

    /**
     * Method to set {@link #checks} instance
     *
     * @param checks: permission type
     **/
    public void setChecks(PermissionType checks) {
        this.checks = checks;
    }

    /**
     * Method to get {@link #contents} instance <br>
     * No-any params required
     *
     * @return {@link #contents} instance as {@link PermissionType}
     **/
    public PermissionType getContents() {
        return contents;
    }

    /**
     * Method to set {@link #contents} instance
     *
     * @param contents: permission type
     **/
    public void setContents(PermissionType contents) {
        this.contents = contents;
    }

    /**
     * Method to get {@link #deployments} instance <br>
     * No-any params required
     *
     * @return {@link #deployments} instance as {@link PermissionType}
     **/
    public PermissionType getDeployments() {
        return deployments;
    }

    /**
     * Method to set {@link #deployments} instance
     *
     * @param deployments: permission type
     **/
    public void setDeployments(PermissionType deployments) {
        this.deployments = deployments;
    }

    /**
     * Method to get {@link #environments} instance <br>
     * No-any params required
     *
     * @return {@link #environments} instance as {@link PermissionType}
     **/
    public PermissionType getEnvironments() {
        return environments;
    }

    /**
     * Method to set {@link #environments} instance
     *
     * @param environments: permission type
     **/
    public void setEnvironments(PermissionType environments) {
        this.environments = environments;
    }

    /**
     * Method to get {@link #issues} instance <br>
     * No-any params required
     *
     * @return {@link #issues} instance as {@link PermissionType}
     **/
    public PermissionType getIssues() {
        return issues;
    }

    /**
     * Method to set {@link #issues} instance
     *
     * @param issues: permission type
     **/
    public void setIssues(PermissionType issues) {
        this.issues = issues;
    }

    /**
     * Method to get {@link #metadata} instance <br>
     * No-any params required
     *
     * @return {@link #metadata} instance as {@link PermissionType}
     **/
    public PermissionType getMetadata() {
        return metadata;
    }

    /**
     * Method to set {@link #metadata} instance
     *
     * @param metadata: permission type
     **/
    public void setMetadata(PermissionType metadata) {
        this.metadata = metadata;
    }

    /**
     * Method to get {@link #packages} instance <br>
     * No-any params required
     *
     * @return {@link #packages} instance as {@link PermissionType}
     **/
    public PermissionType getPackages() {
        return packages;
    }

    /**
     * Method to set {@link #packages} instance
     *
     * @param packages: permission type
     **/
    public void setPackages(PermissionType packages) {
        this.packages = packages;
    }

    /**
     * Method to get {@link #pages} instance <br>
     * No-any params required
     *
     * @return {@link #pages} instance as {@link PermissionType}
     **/
    public PermissionType getPages() {
        return pages;
    }

    /**
     * Method to set {@link #pages} instance
     *
     * @param pages: permission type
     **/
    public void setPages(PermissionType pages) {
        this.pages = pages;
    }

    /**
     * Method to get {@link #pullRequests} instance <br>
     * No-any params required
     *
     * @return {@link #pullRequests} instance as {@link PermissionType}
     **/
    public PermissionType getPullRequests() {
        return pullRequests;
    }

    /**
     * Method to set {@link #pullRequests} instance
     *
     * @param pullRequests: permission type
     **/
    public void setPullRequests(PermissionType pullRequests) {
        this.pullRequests = pullRequests;
    }

    /**
     * Method to get {@link #repositoryAnnouncementBanners} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryAnnouncementBanners} instance as {@link PermissionType}
     **/
    public PermissionType getRepositoryAnnouncementBanners() {
        return repositoryAnnouncementBanners;
    }

    /**
     * Method to set {@link #repositoryAnnouncementBanners} instance
     *
     * @param repositoryAnnouncementBanners: permission type
     **/
    public void setRepositoryAnnouncementBanners(PermissionType repositoryAnnouncementBanners) {
        this.repositoryAnnouncementBanners = repositoryAnnouncementBanners;
    }

    /**
     * Method to get {@link #repositoryHooks} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryHooks} instance as {@link PermissionType}
     **/
    public PermissionType getRepositoryHooks() {
        return repositoryHooks;
    }

    /**
     * Method to set {@link #repositoryHooks} instance
     *
     * @param repositoryHooks: permission type
     **/
    public void setRepositoryHooks(PermissionType repositoryHooks) {
        this.repositoryHooks = repositoryHooks;
    }

    /**
     * Method to get {@link #repositoryProjects} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryProjects} instance as {@link APermissionType}
     **/
    public APermissionType getRepositoryProjects() {
        return repositoryProjects;
    }

    /**
     * Method to set {@link #repositoryProjects} instance
     *
     * @param repositoryProjects: (allowed admin) permission type
     **/
    public void setRepositoryProjects(APermissionType repositoryProjects) {
        this.repositoryProjects = repositoryProjects;
    }

    /**
     * Method to get {@link #secretScanningAlerts} instance <br>
     * No-any params required
     *
     * @return {@link #secretScanningAlerts} instance as {@link PermissionType}
     **/
    public PermissionType getSecretScanningAlerts() {
        return secretScanningAlerts;
    }

    /**
     * Method to set {@link #secretScanningAlerts} instance
     *
     * @param secretScanningAlerts: permission type
     **/
    public void setSecretScanningAlerts(PermissionType secretScanningAlerts) {
        this.secretScanningAlerts = secretScanningAlerts;
    }

    /**
     * Method to get {@link #secrets} instance <br>
     * No-any params required
     *
     * @return {@link #secrets} instance as {@link PermissionType}
     **/
    public PermissionType getSecrets() {
        return secrets;
    }

    /**
     * Method to set {@link #secrets} instance
     *
     * @param secrets: permission type
     **/
    public void setSecrets(PermissionType secrets) {
        this.secrets = secrets;
    }

    /**
     * Method to get {@link #securityEvents} instance <br>
     * No-any params required
     *
     * @return {@link #securityEvents} instance as {@link PermissionType}
     **/
    public PermissionType getSecurityEvents() {
        return securityEvents;
    }

    /**
     * Method to set {@link #securityEvents} instance
     *
     * @param securityEvents: permission type
     **/
    public void setSecurityEvents(PermissionType securityEvents) {
        this.securityEvents = securityEvents;
    }

    /**
     * Method to get {@link #singleFile} instance <br>
     * No-any params required
     *
     * @return {@link #singleFile} instance as {@link PermissionType}
     **/
    public PermissionType getSingleFile() {
        return singleFile;
    }

    /**
     * Method to set {@link #singleFile} instance
     *
     * @param singleFile: permission type
     **/
    public void setSingleFile(PermissionType singleFile) {
        this.singleFile = singleFile;
    }

    /**
     * Method to get {@link #statuses} instance <br>
     * No-any params required
     *
     * @return {@link #statuses} instance as {@link PermissionType}
     **/
    public PermissionType getStatuses() {
        return statuses;
    }

    /**
     * Method to set {@link #statuses} instance
     *
     * @param statuses: permission type
     **/
    public void setStatuses(PermissionType statuses) {
        this.statuses = statuses;
    }

    /**
     * Method to get {@link #vulnerabilityAlerts} instance <br>
     * No-any params required
     *
     * @return {@link #vulnerabilityAlerts} instance as {@link PermissionType}
     **/
    public PermissionType getVulnerabilityAlerts() {
        return vulnerabilityAlerts;
    }

    /**
     * Method to set {@link #vulnerabilityAlerts} instance
     *
     * @param vulnerabilityAlerts: permission type
     **/
    public void setVulnerabilityAlerts(PermissionType vulnerabilityAlerts) {
        this.vulnerabilityAlerts = vulnerabilityAlerts;
    }

    /**
     * Method to get {@link #workflows} instance <br>
     * No-any params required
     *
     * @return {@link #workflows} instance as {@link PermissionType}
     **/
    public PermissionType getWorkflows() {
        return workflows;
    }

    /**
     * Method to set {@link #workflows} instance as {@link PermissionType#write}
     **/
    public void setWorkflows() {
        this.workflows = PermissionType.write;
    }

    /**
     * Method to get {@link #members} instance <br>
     * No-any params required
     *
     * @return {@link #members} instance as {@link PermissionType}
     **/
    public PermissionType getMembers() {
        return members;
    }

    /**
     * Method to set {@link #members} instance
     *
     * @param members: permission type
     **/
    public void setMembers(PermissionType members) {
        this.members = members;
    }

    /**
     * Method to get {@link #organizationAdministration} instance <br>
     * No-any params required
     *
     * @return {@link #organizationAdministration} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationAdministration() {
        return organizationAdministration;
    }

    /**
     * Method to set {@link #organizationAdministration} instance
     *
     * @param organizationAdministration: permission type
     **/
    public void setOrganizationAdministration(PermissionType organizationAdministration) {
        this.organizationAdministration = organizationAdministration;
    }

    /**
     * Method to get {@link #organizationCustomRoles} instance <br>
     * No-any params required
     *
     * @return {@link #organizationCustomRoles} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationCustomRoles() {
        return organizationCustomRoles;
    }

    /**
     * Method to set {@link #organizationCustomRoles} instance
     *
     * @param organizationCustomRoles: permission type
     **/
    public void setOrganizationCustomRoles(PermissionType organizationCustomRoles) {
        this.organizationCustomRoles = organizationCustomRoles;
    }

    /**
     * Method to get {@link #organizationAnnouncementBanners} instance <br>
     * No-any params required
     *
     * @return {@link #organizationAnnouncementBanners} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationAnnouncementBanners() {
        return organizationAnnouncementBanners;
    }

    /**
     * Method to set {@link #organizationAnnouncementBanners} instance
     *
     * @param organizationAnnouncementBanners: permission type
     **/
    public void setOrganizationAnnouncementBanners(PermissionType organizationAnnouncementBanners) {
        this.organizationAnnouncementBanners = organizationAnnouncementBanners;
    }

    /**
     * Method to get {@link #organizationHooks} instance <br>
     * No-any params required
     *
     * @return {@link #organizationHooks} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationHooks() {
        return organizationHooks;
    }

    /**
     * Method to set {@link #organizationHooks} instance
     *
     * @param organizationHooks: permission type
     **/
    public void setOrganizationHooks(PermissionType organizationHooks) {
        this.organizationHooks = organizationHooks;
    }

    /**
     * Method to get {@link #organizationPlan} instance <br>
     * No-any params required
     *
     * @return {@link #organizationPlan} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationPlan() {
        return organizationPlan;
    }

    /**
     * Method to set {@link #organizationPlan} instance
     *
     * @param organizationPlan: permission type
     **/
    public void setOrganizationPlan(PermissionType organizationPlan) {
        this.organizationPlan = organizationPlan;
    }

    /**
     * Method to get {@link #organizationProjects} instance <br>
     * No-any params required
     *
     * @return {@link #organizationProjects} instance as {@link APermissionType}
     **/
    public APermissionType getOrganizationProjects() {
        return organizationProjects;
    }

    /**
     * Method to set {@link #organizationProjects} instance
     *
     * @param organizationProjects: (allowed admin) permission type
     **/
    public void setOrganizationProjects(APermissionType organizationProjects) {
        this.organizationProjects = organizationProjects;
    }

    /**
     * Method to get {@link #organizationPackages} instance <br>
     * No-any params required
     *
     * @return {@link #organizationPackages} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationPackages() {
        return organizationPackages;
    }

    /**
     * Method to set {@link #organizationPackages} instance
     *
     * @param organizationPackages: permission type
     **/
    public void setOrganizationPackages(PermissionType organizationPackages) {
        this.organizationPackages = organizationPackages;
    }

    /**
     * Method to get {@link #organizationSecrets} instance <br>
     * No-any params required
     *
     * @return {@link #organizationSecrets} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationSecrets() {
        return organizationSecrets;
    }

    /**
     * Method to set {@link #organizationSecrets} instance
     *
     * @param organizationSecrets: permission type
     **/
    public void setOrganizationSecrets(PermissionType organizationSecrets) {
        this.organizationSecrets = organizationSecrets;
    }

    /**
     * Method to get {@link #organizationSelfHostedRunners} instance <br>
     * No-any params required
     *
     * @return {@link #organizationSelfHostedRunners} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationSelfHostedRunners() {
        return organizationSelfHostedRunners;
    }

    /**
     * Method to set {@link #organizationSelfHostedRunners} instance
     *
     * @param organizationSelfHostedRunners: permission type
     **/
    public void setOrganizationSelfHostedRunners(PermissionType organizationSelfHostedRunners) {
        this.organizationSelfHostedRunners = organizationSelfHostedRunners;
    }

    /**
     * Method to get {@link #organizationUserBlocking} instance <br>
     * No-any params required
     *
     * @return {@link #organizationUserBlocking} instance as {@link PermissionType}
     **/
    public PermissionType getOrganizationUserBlocking() {
        return organizationUserBlocking;
    }

    /**
     * Method to set {@link #organizationUserBlocking} instance
     *
     * @param organizationUserBlocking: permission type
     **/
    public void setOrganizationUserBlocking(PermissionType organizationUserBlocking) {
        this.organizationUserBlocking = organizationUserBlocking;
    }

    /**
     * Method to get {@link #teamDiscussions} instance <br>
     * No-any params required
     *
     * @return {@link #teamDiscussions} instance as {@link PermissionType}
     **/
    public PermissionType getTeamDiscussions() {
        return teamDiscussions;
    }

    /**
     * Method to set {@link #teamDiscussions} instance
     *
     * @param teamDiscussions: permission type
     **/
    public void setTeamDiscussions(PermissionType teamDiscussions) {
        this.teamDiscussions = teamDiscussions;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    /**
     * {@code PermissionType} list of available permissions types
     **/
    public enum PermissionType {

        /**
         * {@code "read"} permission type
         **/
        read,

        /**
         * {@code "write"} permission type
         **/
        write

    }

    /**
     * {@code APermissionType} list of available permissions types with {@code "admin"} allowed
     **/
    public enum APermissionType {

        /**
         * {@code "read"} permission type
         **/
        read,

        /**
         * {@code "write"} permission type
         **/
        write,

        /**
         * {@code "admin"} permission type
         **/
        admin

    }

}
