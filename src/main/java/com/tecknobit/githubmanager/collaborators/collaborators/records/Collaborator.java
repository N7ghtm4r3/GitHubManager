package com.tecknobit.githubmanager.collaborators.collaborators.records;

import com.tecknobit.githubmanager.records.generic.Permissions;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

/**
 * The {@code Collaborator} class is useful to format a GitHub's collaborator
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
 * List repository collaborators</a>
 * @see GitHubResponse
 * @see User
 **/
public class Collaborator extends User {

    /**
     * {@code permissions} of the collaborator
     **/
    private final Permissions permissions;
    /**
     * {@code roleName} role name of the collaborator
     **/
    private final String roleName;

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, boolean siteAdmin, Permissions permissions, String roleName) {
        super(login, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param id           :        identifier value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, long id, boolean siteAdmin, Permissions permissions, String roleName) {
        super(login, id, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param url          :       url value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, String url, boolean siteAdmin, Permissions permissions, String roleName) {
        super(login, url, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param id           :        identifier value
     * @param url          :       url value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, long id, String url, boolean siteAdmin, Permissions permissions, String roleName) {
        super(login, id, url, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param nodeId       :    identifier of the node value
     * @param url          :       url value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, String nodeId, String url, boolean siteAdmin, Permissions permissions,
                        String roleName) {
        super(login, nodeId, url, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login        :     login value
     * @param id           :        identifier value
     * @param nodeId       :    identifier of the node value
     * @param url          :       url value
     * @param siteAdmin    : site admin value
     * @param permissions: permissions of the collaborator
     * @param roleName:    role name of the collaborator
     * @apiNote this constructor is useful to contains only the basics details about an {@link Collaborator}
     **/
    public Collaborator(String login, long id, String nodeId, String url, boolean siteAdmin, Permissions permissions,
                        String roleName) {
        super(login, id, nodeId, url, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param login             :             login value
     * @param id                :                identifier value
     * @param nodeId            :            identifier of the node value
     * @param avatarUrl         :         avatar url value
     * @param gravatarId        :        gravatar url value
     * @param url               :               url value
     * @param htmlUrl           :           html url value
     * @param followersUrl      :      followers url value
     * @param followingUrl      :      following url value
     * @param gistsUrl          :          gists url value
     * @param starredUrl        :        starred url value
     * @param subscriptionsUrl  :  subscriptions url value
     * @param organizationsUrl  :  organizations url value
     * @param reposUrl          :          repos url value
     * @param eventsUrl         :         events url value
     * @param receivedEventsUrl : received events url value
     * @param type              :              type value
     * @param siteAdmin         :         site admin value
     * @param permissions:      permissions of the collaborator
     * @param roleName:         role name of the collaborator
     **/
    public Collaborator(String login, long id, String nodeId, String avatarUrl, String gravatarId, String url,
                        String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
                        String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
                        String receivedEventsUrl, String type, boolean siteAdmin, Permissions permissions,
                        String roleName) {
        super(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl,
                subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin);
        this.permissions = permissions;
        this.roleName = roleName;
    }

    /**
     * Constructor to init a {@link Collaborator}
     *
     * @param jCollaborator : collaborator details as {@link JSONObject}
     **/
    public Collaborator(JSONObject jCollaborator) {
        super(jCollaborator);
        permissions = new Permissions(hResponse.getJSONObject("", new JSONObject()));
        roleName = hResponse.getString("");
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * Any params required
     *
     * @return {@link #permissions} instance as {@link Permissions}
     **/
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #roleName} instance <br>
     * Any params required
     *
     * @return {@link #roleName} instance as {@link String}
     **/
    public String getRoleName() {
        return roleName;
    }

    /**
     * {@code Affiliation} list of available affiliations
     **/
    public enum Affiliation {

        /**
         * {@code outside} affiliation
         **/
        outside,

        /**
         * {@code direct} affiliation
         **/
        direct,

        /**
         * {@code all} affiliation
         **/
        all

    }

}
