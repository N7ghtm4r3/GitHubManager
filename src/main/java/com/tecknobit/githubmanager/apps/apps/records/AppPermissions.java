package com.tecknobit.githubmanager.apps.apps.records;

import org.json.JSONObject;

import java.lang.reflect.Field;

public class AppPermissions {

    private PermissionType actions;
    private PermissionType administration;
    private PermissionType checks;
    private PermissionType contents;
    private PermissionType deployments;
    private PermissionType environments;
    private PermissionType issues;
    private PermissionType metadata;
    private PermissionType packages;
    private PermissionType pages;
    private PermissionType pullRequests;
    private PermissionType repositoryAnnouncementBanners;
    private PermissionType repositoryHooks;
    private APermissionType repositoryProjects;
    private PermissionType secretScanningAlerts;
    private PermissionType secrets;
    private PermissionType securityEvents;
    private PermissionType singleFile;
    private PermissionType statuses;
    private PermissionType vulnerabilityAlerts;
    private PermissionType workflows;
    private PermissionType members;
    private PermissionType organizationAdministration;
    private PermissionType organizationCustomRoles;
    private PermissionType organizationAnnouncementBanners;
    private PermissionType organizationHooks;
    private PermissionType organizationPlan;
    private APermissionType organizationProjects;
    private PermissionType organizationPackages;
    private PermissionType organizationSecrets;
    private PermissionType organizationSelfHostedRunners;
    private PermissionType organizationUserBlocking;
    private PermissionType teamDiscussions;

    public AppPermissions() {
    }

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

    public PermissionType getActions() {
        return actions;
    }

    public void setActions(PermissionType actions) {
        this.actions = actions;
    }

    public PermissionType getAdministration() {
        return administration;
    }

    public void setAdministration(PermissionType administration) {
        this.administration = administration;
    }

    public PermissionType getChecks() {
        return checks;
    }

    public void setChecks(PermissionType checks) {
        this.checks = checks;
    }

    public PermissionType getContents() {
        return contents;
    }

    public void setContents(PermissionType contents) {
        this.contents = contents;
    }

    public PermissionType getDeployments() {
        return deployments;
    }

    public void setDeployments(PermissionType deployments) {
        this.deployments = deployments;
    }

    public PermissionType getEnvironments() {
        return environments;
    }

    public void setEnvironments(PermissionType environments) {
        this.environments = environments;
    }

    public PermissionType getIssues() {
        return issues;
    }

    public void setIssues(PermissionType issues) {
        this.issues = issues;
    }

    public PermissionType getMetadata() {
        return metadata;
    }

    public void setMetadata(PermissionType metadata) {
        this.metadata = metadata;
    }

    public PermissionType getPackages() {
        return packages;
    }

    public void setPackages(PermissionType packages) {
        this.packages = packages;
    }

    public PermissionType getPages() {
        return pages;
    }

    public void setPages(PermissionType pages) {
        this.pages = pages;
    }

    public PermissionType getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(PermissionType pullRequests) {
        this.pullRequests = pullRequests;
    }

    public PermissionType getRepositoryAnnouncementBanners() {
        return repositoryAnnouncementBanners;
    }

    public void setRepositoryAnnouncementBanners(PermissionType repositoryAnnouncementBanners) {
        this.repositoryAnnouncementBanners = repositoryAnnouncementBanners;
    }

    public PermissionType getRepositoryHooks() {
        return repositoryHooks;
    }

    public void setRepositoryHooks(PermissionType repositoryHooks) {
        this.repositoryHooks = repositoryHooks;
    }

    public APermissionType getRepositoryProjects() {
        return repositoryProjects;
    }

    public void setRepositoryProjects(APermissionType repositoryProjects) {
        this.repositoryProjects = repositoryProjects;
    }

    public PermissionType getSecretScanningAlerts() {
        return secretScanningAlerts;
    }

    public void setSecretScanningAlerts(PermissionType secretScanningAlerts) {
        this.secretScanningAlerts = secretScanningAlerts;
    }

    public PermissionType getSecrets() {
        return secrets;
    }

    public void setSecrets(PermissionType secrets) {
        this.secrets = secrets;
    }

    public PermissionType getSecurityEvents() {
        return securityEvents;
    }

    public void setSecurityEvents(PermissionType securityEvents) {
        this.securityEvents = securityEvents;
    }

    public PermissionType getSingleFile() {
        return singleFile;
    }

    public void setSingleFile(PermissionType singleFile) {
        this.singleFile = singleFile;
    }

    public PermissionType getStatuses() {
        return statuses;
    }

    public void setStatuses(PermissionType statuses) {
        this.statuses = statuses;
    }

    public PermissionType getVulnerabilityAlerts() {
        return vulnerabilityAlerts;
    }

    public void setVulnerabilityAlerts(PermissionType vulnerabilityAlerts) {
        this.vulnerabilityAlerts = vulnerabilityAlerts;
    }

    public PermissionType getWorkflows() {
        return workflows;
    }

    public void setWorkflows() {
        this.workflows = PermissionType.write;
    }

    public PermissionType getMembers() {
        return members;
    }

    public void setMembers(PermissionType members) {
        this.members = members;
    }

    public PermissionType getOrganizationAdministration() {
        return organizationAdministration;
    }

    public void setOrganizationAdministration(PermissionType organizationAdministration) {
        this.organizationAdministration = organizationAdministration;
    }

    public PermissionType getOrganizationCustomRoles() {
        return organizationCustomRoles;
    }

    public void setOrganizationCustomRoles(PermissionType organizationCustomRoles) {
        this.organizationCustomRoles = organizationCustomRoles;
    }

    public PermissionType getOrganizationAnnouncementBanners() {
        return organizationAnnouncementBanners;
    }

    public void setOrganizationAnnouncementBanners(PermissionType organizationAnnouncementBanners) {
        this.organizationAnnouncementBanners = organizationAnnouncementBanners;
    }

    public PermissionType getOrganizationHooks() {
        return organizationHooks;
    }

    public void setOrganizationHooks(PermissionType organizationHooks) {
        this.organizationHooks = organizationHooks;
    }

    public PermissionType getOrganizationPlan() {
        return organizationPlan;
    }

    public void setOrganizationPlan(PermissionType organizationPlan) {
        this.organizationPlan = organizationPlan;
    }

    public APermissionType getOrganizationProjects() {
        return organizationProjects;
    }

    public void setOrganizationProjects(APermissionType organizationProjects) {
        this.organizationProjects = organizationProjects;
    }

    public PermissionType getOrganizationPackages() {
        return organizationPackages;
    }

    public void setOrganizationPackages(PermissionType organizationPackages) {
        this.organizationPackages = organizationPackages;
    }

    public PermissionType getOrganizationSecrets() {
        return organizationSecrets;
    }

    public void setOrganizationSecrets(PermissionType organizationSecrets) {
        this.organizationSecrets = organizationSecrets;
    }

    public PermissionType getOrganizationSelfHostedRunners() {
        return organizationSelfHostedRunners;
    }

    public void setOrganizationSelfHostedRunners(PermissionType organizationSelfHostedRunners) {
        this.organizationSelfHostedRunners = organizationSelfHostedRunners;
    }

    public PermissionType getOrganizationUserBlocking() {
        return organizationUserBlocking;
    }

    public void setOrganizationUserBlocking(PermissionType organizationUserBlocking) {
        this.organizationUserBlocking = organizationUserBlocking;
    }

    public PermissionType getTeamDiscussions() {
        return teamDiscussions;
    }

    public void setTeamDiscussions(PermissionType teamDiscussions) {
        this.teamDiscussions = teamDiscussions;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    public enum PermissionType {

        read,
        write

    }

    public enum APermissionType {

        read,
        write,
        admin

    }

}
