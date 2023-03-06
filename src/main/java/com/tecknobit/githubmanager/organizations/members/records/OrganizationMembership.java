package com.tecknobit.githubmanager.organizations.members.records;

import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class OrganizationMembership extends GitHubResponse {

    public enum MembershipState {

        active,
        pending

    }

    public enum MembershipRole {

        admin,
        member,
        billing_manager

    }

    private final String url;
    private final MembershipState state;
    private final MembershipRole role;
    private final String organizationUrl;
    private final Organization organization;
    private final User user;
    private final MembershipPermissions permissions;

    public OrganizationMembership(String url, MembershipState state, MembershipRole role, String organizationUrl,
                                  Organization organization, User user, MembershipPermissions permissions) {
        super(null);
        this.url = url;
        this.state = state;
        this.role = role;
        this.organizationUrl = organizationUrl;
        this.organization = organization;
        this.user = user;
        this.permissions = permissions;
    }

    public OrganizationMembership(JSONObject jOrganizationMembership) {
        super(jOrganizationMembership);
        url = hResponse.getString("url");
        state = MembershipState.valueOf(hResponse.getString("state"));
        role = MembershipRole.valueOf(hResponse.getString("role"));
        organizationUrl = hResponse.getString("organization_url");
        organization = new Organization(hResponse.getJSONObject("organization"));
        user = new User(hResponse.getJSONObject("user"));
        JSONObject jPermissions = hResponse.getJSONObject("permissions");
        if (jPermissions != null)
            permissions = new MembershipPermissions(jPermissions);
        else
            permissions = null;
    }

    public String getUrl() {
        return url;
    }

    public MembershipState getState() {
        return state;
    }

    public MembershipRole getRole() {
        return role;
    }

    public String getOrganizationUrl() {
        return organizationUrl;
    }

    public Organization getOrganization() {
        return organization;
    }

    public User getUser() {
        return user;
    }

    public MembershipPermissions getPermissions() {
        return permissions;
    }

    public static class MembershipPermissions extends InnerClassItem {

        private final boolean canCreateRepository;

        public MembershipPermissions(boolean canCreateRepository) {
            super(null);
            this.canCreateRepository = canCreateRepository;
        }

        public MembershipPermissions(JSONObject jMembershipPermissions) {
            super(jMembershipPermissions);
            canCreateRepository = hItem.getBoolean("can_create_repository");
        }

        public boolean canCreateRepository() {
            return canCreateRepository;
        }

    }

}
