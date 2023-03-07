package com.tecknobit.githubmanager.organizations.members.records;

import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

/**
 * The {@code OrganizationMembership} class is useful to format a GitHub's organization membership
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#get-organization-membership-for-a-user">
 *             Get organization membership for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#set-organization-membership-for-a-user">
 *             Set organization membership for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#get-an-organization-membership-for-the-authenticated-user">
 *             Get an organization membership for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#update-an-organization-membership-for-the-authenticated-user">
 *             Update an organization membership for the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class OrganizationMembership extends GitHubResponse {

    /**
     * {@code MembershipState} list of available membership states
     **/
    public enum MembershipState {

        /**
         * {@code active} membership state
         **/
        active,

        /**
         * {@code pending} membership state
         **/
        pending

    }

    /**
     * {@code MembershipRole} list of available membership roles
     **/
    public enum MembershipRole {

        /**
         * {@code admin} membership role
         **/
        admin,

        /**
         * {@code member} membership role
         **/
        member,

        /**
         * {@code billing_manager} membership role
         **/
        billing_manager

    }

    /**
     * {@code MemberFilter} list of available member filters
     **/
    public enum MemberFilter {

        /**
         * {@code 2fa_disabled} member filter
         **/
        _2fa_disabled("2fa_disabled"),

        /**
         * {@code all} member filter
         **/
        all("all");

        /**
         * {@code filter} value
         **/
        private final String filter;

        /**
         * Constructor to init a {@link MemberFilter}
         *
         * @param filter: filter value
         **/
        MemberFilter(final String filter) {
            this.filter = filter;
        }

        /**
         * Method to get {@link #filter} instance <br>
         * No-any params required
         *
         * @return {@link #filter} instance as {@link String}
         **/
        @Override
        public String toString() {
            return filter;
        }

    }

    /**
     * {@code url} of the membership
     **/
    private final String url;

    /**
     * {@code state} the state of the member in the organization. The {@code "pending"} state indicates the user has
     * not yet accepted an invitation.
     **/
    private final MembershipState state;

    /**
     * {@code role} the user's membership type in the organization
     **/
    private final MembershipRole role;

    /**
     * {@code organizationUrl} the organization url of the membership
     **/
    private final String organizationUrl;

    /**
     * {@code organization} of the membership
     **/
    private final Organization organization;

    /**
     * {@code user} of the membership
     **/
    private final User user;

    /**
     * {@code permissions} of the membership
     **/
    private final MembershipPermissions permissions;

    /**
     * Constructor to init a {@link OrganizationMembership}
     *
     * @param url:                    url of the membership
     * @param state:                  the state of the member in the organization
     * @param role:                   the user's membership type in the organization
     * @param organizationUrl:        organization url of the membership
     * @param organization:           organization of the membership
     * @param user:                   user of the membership
     * @param permissions:permissions of the membership
     **/
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

    /**
     * Constructor to init a {@link OrganizationMembership}
     *
     * @param jOrganizationMembership: organization membership details as {@link JSONObject}
     **/
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
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link MembershipState}
     **/
    public MembershipState getState() {
        return state;
    }

    /**
     * Method to get {@link #role} instance <br>
     * No-any params required
     *
     * @return {@link #role} instance as {@link MembershipRole}
     **/
    public MembershipRole getRole() {
        return role;
    }

    /**
     * Method to get {@link #organizationUrl} instance <br>
     * No-any params required
     *
     * @return {@link #organizationUrl} instance as {@link String}
     **/
    public String getOrganizationUrl() {
        return organizationUrl;
    }

    /**
     * Method to get {@link #organization} instance <br>
     * No-any params required
     *
     * @return {@link #organization} instance as {@link Organization}
     **/
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Method to get {@link #user} instance <br>
     * No-any params required
     *
     * @return {@link #user} instance as {@link User}
     **/
    public User getUser() {
        return user;
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link MembershipPermissions}
     **/
    public MembershipPermissions getPermissions() {
        return permissions;
    }

    /**
     * The {@code MembershipPermissions} class is useful to format a GitHub's membership permissions
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class MembershipPermissions extends InnerClassItem {

        /**
         * {@code canCreateRepository} whether the user of the membership can create repository
         **/
        private final boolean canCreateRepository;

        /**
         * Constructor to init a {@link MembershipPermissions}
         *
         * @param canCreateRepository: whether the user of the membership can create repository
         **/
        public MembershipPermissions(boolean canCreateRepository) {
            super(null);
            this.canCreateRepository = canCreateRepository;
        }

        /**
         * Constructor to init a {@link MembershipPermissions}
         *
         * @param jMembershipPermissions: membership permissions details as {@link JSONObject}
         **/
        public MembershipPermissions(JSONObject jMembershipPermissions) {
            super(jMembershipPermissions);
            canCreateRepository = hItem.getBoolean("can_create_repository");
        }

        /**
         * Method to get {@link #canCreateRepository} instance <br>
         * No-any params required
         *
         * @return {@link #canCreateRepository} instance as boolean
         **/
        public boolean canCreateRepository() {
            return canCreateRepository;
        }

    }

}
