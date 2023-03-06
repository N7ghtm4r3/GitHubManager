package com.tecknobit.githubmanager.organizations.members.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class OrganizationInvitation extends GitHubResponse {

    public enum Role {

        admin,
        direct_member,
        billing_manager

    }

    private final long id;
    private final String login;
    private final String email;
    private final Role role;
    private final String createdAt;
    private final String failedAt;
    private final String failedReason;
    private final User inviter;
    private final int teamCount;
    private final String nodeId;
    private final String invitationTeamsUrl;
    private final String invitationSource;

    public OrganizationInvitation(long id, String login, String email, Role role, String createdAt, String failedAt,
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

    public OrganizationInvitation(JSONObject jOrganizationInvitation) {
        super(jOrganizationInvitation);
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

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFailedAt() {
        return failedAt;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public User getInviter() {
        return inviter;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getInvitationTeamsUrl() {
        return invitationTeamsUrl;
    }

    public String getInvitationSource() {
        return invitationSource;
    }

}
