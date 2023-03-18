package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code EntityInvitation} class is useful to format a GitHub's entity invitation
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#list-failed-organization-invitations">
 *             List failed organization invitations</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#list-pending-organization-invitations">
 *             List pending organization invitations</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/members#create-an-organization-invitation">
 *             Create an organization invitation</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/teams/members#list-pending-team-invitations">
 *             List pending team invitations</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class EntityInvitation extends GitHubResponse {

    /**
     * {@code Role} list of available roles
     **/
    public enum Role {

        /**
         * {@code admin} role
         **/
        admin,

        /**
         * {@code direct_member} role
         **/
        direct_member,

        /**
         * {@code billing_manager} role
         **/
        billing_manager,

        /**
         * {@code hiring_manager} role
         **/
        hiring_manager

    }

    /**
     * {@code InvitationSource} list of available invitation sources
     **/
    public enum InvitationSource {

        /**
         * {@code all} invitation source
         **/
        all,

        /**
         * {@code member} invitation source
         **/
        member,

        /**
         * {@code scim} invitation source
         **/
        scim

    }

    /**
     * {@code id} of the invitation
     **/
    private final long id;

    /**
     * {@code login} of the invitation
     **/
    private final String login;

    /**
     * {@code email} of the invitation
     **/
    private final String email;

    /**
     * {@code role} of the invitation
     **/
    private final Role role;

    /**
     * {@code createdAt} creation date of the invitation
     **/
    private final String createdAt;

    /**
     * {@code failedAt} date when the invitation failed
     **/
    private final String failedAt;

    /**
     * {@code failedReason} the failed reason
     **/
    private final String failedReason;

    /**
     * {@code inviter} who sent the invitation
     **/
    private final User inviter;

    /**
     * {@code teamCount} team count of the invitation
     **/
    private final int teamCount;

    /**
     * {@code nodeId} node id of the invitation
     **/
    private final String nodeId;

    /**
     * {@code invitationTeamsUrl} invitation teams url of the invitation
     **/
    private final String invitationTeamsUrl;

    /**
     * {@code invitationSource} the source of the invitation
     **/
    private final String invitationSource;

    /**
     * Constructor to init a {@link EntityInvitation}
     *
     * @param id:                 id of the invitation
     * @param login:              login of the invitation
     * @param email:              email of the invitation
     * @param role:               role of the invitation
     * @param createdAt:          creation date of the invitation
     * @param failedAt:           date when the invitation failed
     * @param failedReason:       the failed reason
     * @param inviter:            who sent the invitation
     * @param teamCount:          team count of the invitation
     * @param nodeId:             node id of the invitation
     * @param invitationTeamsUrl: invitation teams url of the invitation
     * @param invitationSource:   the source of the invitation
     **/
    public EntityInvitation(long id, String login, String email, Role role, String createdAt, String failedAt,
                            String failedReason, User inviter, int teamCount, String nodeId, String invitationTeamsUrl,
                            String invitationSource) {
        super(null);
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.failedAt = failedAt;
        this.failedReason = failedReason;
        this.inviter = inviter;
        this.teamCount = teamCount;
        this.nodeId = nodeId;
        this.invitationTeamsUrl = invitationTeamsUrl;
        this.invitationSource = invitationSource;
    }

    /**
     * Constructor to init a {@link EntityInvitation}
     *
     * @param jEntityInvitation: entity invitation details as {@link JSONObject}
     **/
    public EntityInvitation(JSONObject jEntityInvitation) {
        super(jEntityInvitation);
        id = hResponse.getLong("id", 0);
        login = hResponse.getString("login");
        email = hResponse.getString("email");
        role = Role.valueOf(hResponse.getString("role"));
        createdAt = hResponse.getString("created_at");
        failedAt = hResponse.getString("failed_at");
        failedReason = hResponse.getString("failed_reason");
        inviter = new User(hResponse.getJSONObject("inviter"));
        teamCount = hResponse.getInt("team_count", 0);
        nodeId = hResponse.getString("node_id");
        invitationTeamsUrl = hResponse.getString("invitation_teams_url");
        invitationSource = hResponse.getString("invitation_source");
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
     * Method to get {@link #login} instance <br>
     * No-any params required
     *
     * @return {@link #login} instance as {@link String}
     **/
    public String getLogin() {
        return login;
    }

    /**
     * Method to get {@link #email} instance <br>
     * No-any params required
     *
     * @return {@link #email} instance as {@link String}
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Method to get {@link #role} instance <br>
     * No-any params required
     *
     * @return {@link #role} instance as {@link Role}
     **/
    public Role getRole() {
        return role;
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
     * Method to get {@link #failedAt} instance <br>
     * No-any params required
     *
     * @return {@link #failedAt} instance as {@link String}
     **/
    public String getFailedAt() {
        return failedAt;
    }

    /**
     * Method to get {@link #failedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #failedAt} timestamp as long
     **/
    public long getFailedAtTimestamp() {
        return getDateTimestamp(failedAt);
    }

    /**
     * Method to get {@link #failedReason} instance <br>
     * No-any params required
     *
     * @return {@link #failedReason} instance as {@link String}
     **/
    public String getFailedReason() {
        return failedReason;
    }

    /**
     * Method to get {@link #inviter} instance <br>
     * No-any params required
     *
     * @return {@link #inviter} instance as {@link User}
     **/
    public User getInviter() {
        return inviter;
    }

    /**
     * Method to get {@link #teamCount} instance <br>
     * No-any params required
     *
     * @return {@link #teamCount} instance as int
     **/
    public int getTeamCount() {
        return teamCount;
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
     * Method to get {@link #invitationTeamsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #invitationTeamsUrl} instance as {@link String}
     **/
    public String getInvitationTeamsUrl() {
        return invitationTeamsUrl;
    }

    /**
     * Method to get {@link #invitationSource} instance <br>
     * No-any params required
     *
     * @return {@link #invitationSource} instance as {@link String}
     **/
    public String getInvitationSource() {
        return invitationSource;
    }

    /**
     * Method to create an entity invitations list
     *
     * @param entityInvitationsResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return entity invitations list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnEntityInvitations(String entityInvitationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(entityInvitationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<EntityInvitation> entityInvitations = new ArrayList<>();
                JSONArray jEntityInvitations = new JSONArray(entityInvitationsResponse);
                for (int j = 0; j < jEntityInvitations.length(); j++)
                    entityInvitations.add(new EntityInvitation(jEntityInvitations.getJSONObject(j)));
                return (T) entityInvitations;
            default:
                return (T) entityInvitationsResponse;
        }
    }

}
