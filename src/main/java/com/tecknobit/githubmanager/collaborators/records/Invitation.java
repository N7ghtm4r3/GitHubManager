package com.tecknobit.githubmanager.collaborators.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code Invitation} class is useful to format a GitHub's invitation
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
 *             Add a repository collaborator</a>
 *     </li>
 *     <li>
 *         <a href="">
 *             </a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Invitation extends GitHubResponse {

    /**
     * {@code id} unique identifier of the repository invitation
     **/
    private final long id;

    /**
     * {@code nodeId} unique node identifier
     **/
    private final String nodeId;

    /**
     * {@code repository} of the invitation
     **/
    private final Repository repository;

    /**
     * {@code invitee} of the invitation
     **/
    private final User invitee;

    /**
     * {@code inviter} of the invitation
     **/
    private final User inviter;

    /**
     * {@code permissions} the permission associated with the invitation
     **/
    private final CollaboratorPermission permissions;

    /**
     * {@code createdAt} creation time of the invitation
     **/
    private final String createdAt;

    /**
     * {@code expired} whether or not the invitation has expired
     **/
    private final boolean expired;

    /**
     * {@code url} for the repository invitation
     **/
    private final String url;

    /**
     * {@code htmlUrl} html url of the repository invitation
     **/
    private final String htmlUrl;

    /**
     * Constructor to init a {@link Invitation}
     *
     * @param id          : unique identifier of the repository invitation
     * @param nodeId      : unique node identifier
     * @param repository  : repository of the invitation
     * @param invitee     : invitee of the invitation
     * @param inviter     : inviter of the invitation
     * @param permissions : the permission associated with the invitation
     * @param createdAt   : creation time of the invitation
     * @param expired     : whether the invitation has expired
     * @param url         : url for the repository invitation
     * @param htmlUrl     : html url of the repository invitation
     **/
    public Invitation(long id, String nodeId, Repository repository, User invitee, User inviter,
                      CollaboratorPermission permissions, String createdAt, boolean expired, String url, String htmlUrl) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.repository = repository;
        this.invitee = invitee;
        this.inviter = inviter;
        this.permissions = permissions;
        this.createdAt = createdAt;
        this.expired = expired;
        this.url = url;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Constructor to init a {@link Invitation}
     *
     * @param jInvitation : invitation details as {@link JSONObject}
     **/
    public Invitation(JSONObject jInvitation) {
        super(jInvitation);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
        invitee = new User(hResponse.getJSONObject("invitee", new JSONObject()));
        inviter = new User(hResponse.getJSONObject("inviter", new JSONObject()));
        permissions = CollaboratorPermission.valueOf(hResponse.getString("permissions"));
        createdAt = hResponse.getString("created_at");
        expired = hResponse.getBoolean("expired");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
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
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link Repository}
     **/
    public Repository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #invitee} instance <br>
     * No-any params required
     *
     * @return {@link #invitee} instance as {@link User}
     **/
    public User getInvitee() {
        return invitee;
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
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link CollaboratorPermission}
     **/
    public CollaboratorPermission getPermissions() {
        return permissions;
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
     * Method to get {@link #expired} instance <br>
     * No-any params required
     *
     * @return {@link #expired} instance as boolean
     **/
    public boolean isExpired() {
        return expired;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to create an invitation
     *
     * @param invitationResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return invitation as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnInvitation(String invitationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(invitationResponse);
            case LIBRARY_OBJECT:
                return (T) new Invitation(new JSONObject(invitationResponse));
            default:
                return (T) invitationResponse;
        }
    }

    /**
     * {@code CollaboratorPermission} list of available permissions for a collaborator
     **/
    public enum CollaboratorPermission {

        /**
         * {@code read} permission
         **/
        read,

        /**
         * {@code write} permission
         **/
        write,

        /**
         * {@code admin} permission
         **/
        admin,

        /**
         * {@code triage} permission
         **/
        triage,

        /**
         * {@code maintain} permission
         **/
        maintain

    }

}
