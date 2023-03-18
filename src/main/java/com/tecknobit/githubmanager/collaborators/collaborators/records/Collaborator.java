package com.tecknobit.githubmanager.collaborators.collaborators.records;

import com.tecknobit.githubmanager.records.generic.Permissions;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
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
     * @param id:                      identifier value
     * @param nodeId:                  identifier of the node value
     * @param createdAt                : the creation time of the user
     * @param updatedAt                : the updated time of the user
     * @param login:                   login value
     * @param avatarUrl:               avatar url value
     * @param gravatarId:              gravatar url value
     * @param htmlUrl:                 html url value
     * @param url:                     url value
     * @param followersUrl:            followers url value
     * @param followingUrl:            following url value
     * @param gistsUrl:                gists url value
     * @param starredUrl:              starred url value
     * @param subscriptionsUrl:        subscriptions url value
     * @param organizationsUrl:        organizations url value
     * @param reposUrl:                repos url value
     * @param eventsUrl:               events url value
     * @param receivedEventsUrl:       received events url value
     * @param type:                    type value
     * @param siteAdmin:               of the user
     * @param name:                    name of the user
     * @param company:                 company of the user
     * @param blog:                    blog of the user
     * @param location:                location of the user
     * @param email:                   email of the user
     * @param hireable:                whether the user is hireable
     * @param bio:                     bio of the user
     * @param twitterUsername:         Twitter username  of the user
     * @param publicRepos:             public repos of the user
     * @param publicGists:             public gists of the user
     * @param followers:               followers of the user
     * @param following:               following of the user
     * @param privateGists:            private gists    of the user
     * @param totalPrivateRepos:       total private repos     of the user
     * @param ownedPrivateRepos:       owned private repos  of the user
     * @param diskUsage:               disk usage of the user
     * @param collaborators:           collaborators of the user
     * @param twoFactorAuthentication: whether the two-factor authentication is enabled
     * @param plan:                    plan of the user
     * @param suspendedAt:             suspended time of the user
     * @param businessPlus:            whether the user has a business plus
     * @param ldapDn:                  ldapDn of the user
     * @param permissions:             permissions of the collaborator
     * @param roleName:                role name of the collaborator
     **/
    public Collaborator(String url, long id, String nodeId, String createdAt, String updatedAt, String login,
                        String avatarUrl, String gravatarId, String htmlUrl, String followersUrl, String followingUrl,
                        String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl,
                        String reposUrl, String eventsUrl, String receivedEventsUrl, String type, boolean siteAdmin,
                        String name, String company, String blog, String location, String email, boolean hireable,
                        String bio, String twitterUsername, int publicRepos, int publicGists, int followers, int following,
                        int privateGists, int totalPrivateRepos, int ownedPrivateRepos, int diskUsage, int collaborators,
                        boolean twoFactorAuthentication, UserPlan plan, String suspendedAt, boolean businessPlus,
                        String ldapDn, Permissions permissions, String roleName) {
        super(url, id, nodeId, createdAt, updatedAt, login, avatarUrl, gravatarId, htmlUrl, followersUrl, followingUrl,
                gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type,
                siteAdmin, name, company, blog, location, email, hireable, bio, twitterUsername, publicRepos, publicGists,
                followers, following, privateGists, totalPrivateRepos, ownedPrivateRepos, diskUsage, collaborators,
                twoFactorAuthentication, plan, suspendedAt, businessPlus, ldapDn);
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
        JSONObject jPermissions = hResponse.getJSONObject("permissions");
        if(jPermissions != null)
            permissions = new Permissions(jPermissions);
        else
            permissions = null;
        roleName = hResponse.getString("role_name");
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link Permissions}
     **/
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #roleName} instance <br>
     * No-any params required
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
