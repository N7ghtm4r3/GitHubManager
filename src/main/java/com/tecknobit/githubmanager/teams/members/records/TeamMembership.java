package com.tecknobit.githubmanager.teams.members.records;

import com.tecknobit.githubmanager.organizations.members.records.OrganizationMembership.MembershipState;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code TeamMembership} class is useful to format a GitHub's team membership
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/teams/members#get-team-membership-for-a-user">
 *             Get team membership for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/teams/members#add-or-update-team-membership-for-a-user">
 *             Add or update team membership for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class TeamMembership extends GitHubResponse {

    /**
     * {@code TeamRole} list of available team roles
     **/
    public enum TeamRole {

        /**
         * {@code member} team role
         **/
        member,

        /**
         * {@code maintainer} team role
         **/
        maintainer

    }

    /**
     * {@code url} of the team membership
     **/
    private final String url;

    /**
     * {@code role} of the team membership
     **/
    private final TeamRole role;

    /**
     * {@code state} of the team membership
     **/
    private final MembershipState state;

    /**
     * Constructor to init a {@link TeamMembership}
     *
     * @param url:   url of the team membership
     * @param role:  role of the team membership
     * @param state: state of the team membership
     **/
    public TeamMembership(String url, TeamRole role, MembershipState state) {
        super(null);
        this.url = url;
        this.role = role;
        this.state = state;
    }

    /**
     * Constructor to init a {@link TeamMembership}
     *
     * @param jTeamMembership: team membership details as {@link JSONObject}
     **/
    public TeamMembership(JSONObject jTeamMembership) {
        super(jTeamMembership);
        url = hResponse.getString("url");
        role = TeamRole.valueOf(hResponse.getString("role"));
        state = MembershipState.valueOf(hResponse.getString("state"));
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
     * Method to get {@link #role} instance <br>
     * No-any params required
     *
     * @return {@link #role} instance as {@link TeamRole}
     **/
    public TeamRole getRole() {
        return role;
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

}
