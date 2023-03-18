package com.tecknobit.githubmanager.users.users.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code User} class is useful to format a GitHub's user
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public class User extends BaseItemStructure {

    /**
     * {@code SubjectType} list of available subject types
     **/
    public enum SubjectType {

        /**
         * {@code organization} subject type
         **/
        organization,

        /**
         * {@code repository} subject type
         **/
        repository,

        /**
         * {@code issue} subject type
         **/
        issue,

        /**
         * {@code pull_request} subject type
         **/
        pull_request

    }

    /**
     * {@code login} login value
     **/
    protected final String login;

    /**
     * {@code avatarUrl} avatar url value
     **/
    protected final String avatarUrl;

    /**
     * {@code gravatarId} gravatar url value
     **/
    protected final String gravatarId;

    /**
     * {@code htmlUrl} html url value
     **/
    protected final String htmlUrl;

    /**
     * {@code followersUrl} followers url value
     **/
    protected final String followersUrl;

    /**
     * {@code followingUrl} following url value
     **/
    protected final String followingUrl;

    /**
     * {@code gistsUrl} gists url value
     **/
    protected final String gistsUrl;

    /**
     * {@code starredUrl} starred url value
     **/
    protected final String starredUrl;

    /**
     * {@code subscriptionsUrl} subscriptions url value
     **/
    protected final String subscriptionsUrl;

    /**
     * {@code organizationsUrl} organizations url value
     **/
    protected final String organizationsUrl;

    /**
     * {@code reposUrl} repos url value
     **/
    protected final String reposUrl;

    /**
     * {@code eventsUrl} events url value
     **/
    protected final String eventsUrl;

    /**
     * {@code receivedEventsUrl} received events url value
     **/
    protected final String receivedEventsUrl;

    /**
     * {@code type} type value
     **/
    protected final String type;

    /**
     * {@code siteAdmin}of the user
     **/
    protected final boolean siteAdmin;

    /**
     * {@code name} of the user
     **/
    protected final String name;

    /**
     * {@code company} of the user
     **/
    protected final String company;

    /**
     * {@code blog} of the user
     **/
    protected final String blog;

    /**
     * {@code location} of the user
     **/
    protected final String location;

    /**
     * {@code email} of the user
     **/
    protected final String email;

    /**
     * {@code hireable} whether the user is hireable
     **/
    protected final boolean hireable;

    /**
     * {@code bio} of the user
     **/
    protected final String bio;

    /**
     * {@code twitterUsername} twitter username of the user
     **/
    protected final String twitterUsername;

    /**
     * {@code publicRepos} public repos of the user
     **/
    protected final int publicRepos;

    /**
     * {@code publicGists} public gists of the user
     **/
    protected final int publicGists;

    /**
     * {@code followers} of the user
     **/
    protected final int followers;

    /**
     * {@code following} of the user
     **/
    protected final int following;

    /**
     * {@code privateGists} private gists of the user
     **/
    protected final int privateGists;

    /**
     * {@code totalPrivateRepos} total private repos of the user
     **/
    protected final int totalPrivateRepos;

    /**
     * {@code ownedPrivateRepos} owned private repos of the user
     **/
    protected final int ownedPrivateRepos;

    /**
     * {@code diskUsage} disk usage of the user
     **/
    protected final int diskUsage;

    /**
     * {@code collaborators} of the user
     **/
    protected final int collaborators;

    /**
     * {@code twoFactorAuthentication} whether the two-factor authentication is enabled
     **/
    protected final boolean twoFactorAuthentication;

    /**
     * {@code plan} of the user
     **/
    protected final UserPlan plan;

    /**
     * {@code suspendedAt} suspended time of the user
     **/
    protected final String suspendedAt;

    /**
     * {@code businessPlus} whether the user has a business plus
     **/
    protected final boolean businessPlus;

    /**
     * {@code ldapDn} of the user
     **/
    protected final String ldapDn;

    /**
     * Constructor to init a {@link User}
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
     **/
    public User(String url, long id, String nodeId, String createdAt, String updatedAt, String login, String avatarUrl,
                String gravatarId, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl,
                String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
                String receivedEventsUrl, String type, boolean siteAdmin, String name, String company, String blog,
                String location, String email, boolean hireable, String bio, String twitterUsername, int publicRepos,
                int publicGists, int followers, int following, int privateGists, int totalPrivateRepos,
                int ownedPrivateRepos, int diskUsage, int collaborators, boolean twoFactorAuthentication, UserPlan plan,
                String suspendedAt, boolean businessPlus, String ldapDn) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitterUsername = twitterUsername;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.followers = followers;
        this.following = following;
        this.privateGists = privateGists;
        this.totalPrivateRepos = totalPrivateRepos;
        this.ownedPrivateRepos = ownedPrivateRepos;
        this.diskUsage = diskUsage;
        this.collaborators = collaborators;
        this.twoFactorAuthentication = twoFactorAuthentication;
        this.plan = plan;
        this.suspendedAt = suspendedAt;
        this.businessPlus = businessPlus;
        this.ldapDn = ldapDn;
    }

    /**
     * Constructor to init a {@link User}
     *
     * @param jUser: user details as {@link JSONObject}
     **/
    public User(JSONObject jUser) {
        super(jUser);
        login = hResponse.getString("login");
        avatarUrl = hResponse.getString("avatar_url");
        gravatarId = hResponse.getString("gravatar_id");
        htmlUrl = hResponse.getString("html_url");
        followersUrl = hResponse.getString("followers_url");
        followingUrl = hResponse.getString("following_url");
        gistsUrl = hResponse.getString("gists_url");
        starredUrl = hResponse.getString("starred_url");
        subscriptionsUrl = hResponse.getString("subscriptions_url");
        organizationsUrl = hResponse.getString("organizations_url");
        reposUrl = hResponse.getString("repos_url");
        eventsUrl = hResponse.getString("events_url");
        receivedEventsUrl = hResponse.getString("received_events_url");
        type = hResponse.getString("type");
        siteAdmin = hResponse.getBoolean("site_admin");
        name = hResponse.getString("name");
        company = hResponse.getString("company");
        blog = hResponse.getString("blog");
        location = hResponse.getString("location");
        email = hResponse.getString("email");
        hireable = hResponse.getBoolean("hireable");
        bio = hResponse.getString("bio");
        twitterUsername = hResponse.getString("twitter_username");
        publicRepos = hResponse.getInt("public_repos", 0);
        publicGists = hResponse.getInt("public_gists", 0);
        followers = hResponse.getInt("followers", 0);
        following = hResponse.getInt("following", 0);
        privateGists = hResponse.getInt("private_gists", 0);
        totalPrivateRepos = hResponse.getInt("total_private_repos", 0);
        ownedPrivateRepos = hResponse.getInt("owned_private_repos", 0);
        diskUsage = hResponse.getInt("disk_usage", 0);
        collaborators = hResponse.getInt("collaborators", 0);
        twoFactorAuthentication = hResponse.getBoolean("two_factor_authentication");
        JSONObject jPlan = hResponse.getJSONObject("plan");
        if(jPlan != null)
            plan = new UserPlan(jPlan);
        else
            plan = null;
        suspendedAt = hResponse.getString("suspended_at");
        businessPlus = hResponse.getBoolean("business_plus");
        ldapDn = hResponse.getString("ldap_dn");
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
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link Long}
     **/
    public Long getId() {
        return super.getId();
    }

    /**
     * Method to get {@link #avatarUrl} instance <br>
     * No-any params required
     *
     * @return {@link #avatarUrl} instance as {@link String}
     **/
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Method to get {@link #gravatarId} instance <br>
     * No-any params required
     *
     * @return {@link #gravatarId} instance as {@link String}
     **/
    public String getGravatarId() {
        return gravatarId;
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
     * Method to get {@link #followersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #followersUrl} instance as {@link String}
     **/
    public String getFollowersUrl() {
        return followersUrl;
    }

    /**
     * Method to get {@link #followingUrl} instance <br>
     * No-any params required
     *
     * @return {@link #followingUrl} instance as {@link String}
     **/
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * Method to get {@link #gistsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gistsUrl} instance as {@link String}
     **/
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * Method to get {@link #starredUrl} instance <br>
     * No-any params required
     *
     * @return {@link #starredUrl} instance as {@link String}
     **/
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * Method to get {@link #subscriptionsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #subscriptionsUrl} instance as {@link String}
     **/
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * Method to get {@link #organizationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #organizationsUrl} instance as {@link String}
     **/
    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    /**
     * Method to get {@link #reposUrl} instance <br>
     * No-any params required
     *
     * @return {@link #reposUrl} instance as {@link String}
     **/
    public String getReposUrl() {
        return reposUrl;
    }

    /**
     * Method to get {@link #eventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #eventsUrl} instance as {@link String}
     **/
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * Method to get {@link #receivedEventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #receivedEventsUrl} instance as {@link String}
     **/
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

    /**
     * Method to get {@link #siteAdmin} instance <br>
     * No-any params required
     *
     * @return {@link #siteAdmin} instance as boolean
     **/
    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #company} instance <br>
     * No-any params required
     *
     * @return {@link #company} instance as {@link String}
     **/
    public String getCompany() {
        return company;
    }

    /**
     * Method to get {@link #blog} instance <br>
     * No-any params required
     *
     * @return {@link #blog} instance as {@link String}
     **/
    public String getBlog() {
        return blog;
    }

    /**
     * Method to get {@link #location} instance <br>
     * No-any params required
     *
     * @return {@link #location} instance as {@link String}
     **/
    public String getLocation() {
        return location;
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
     * Method to get {@link #hireable} instance <br>
     * No-any params required
     *
     * @return {@link #hireable} instance as boolean
     **/
    public boolean isHireable() {
        return hireable;
    }

    /**
     * Method to get {@link #bio} instance <br>
     * No-any params required
     *
     * @return {@link #bio} instance as {@link String}
     **/
    public String getBio() {
        return bio;
    }

    /**
     * Method to get {@link #twitterUsername} instance <br>
     * No-any params required
     *
     * @return {@link #twitterUsername} instance as {@link String}
     **/
    public String getTwitterUsername() {
        return twitterUsername;
    }

    /**
     * Method to get {@link #publicRepos} instance <br>
     * No-any params required
     *
     * @return {@link #publicRepos} instance as int
     **/
    public int getPublicRepos() {
        return publicRepos;
    }

    /**
     * Method to get {@link #publicGists} instance <br>
     * No-any params required
     *
     * @return {@link #publicGists} instance as int
     **/
    public int getPublicGists() {
        return publicGists;
    }

    /**
     * Method to get {@link #followers} instance <br>
     * No-any params required
     *
     * @return {@link #followers} instance as int
     **/
    public int getFollowers() {
        return followers;
    }

    /**
     * Method to get {@link #following} instance <br>
     * No-any params required
     *
     * @return {@link #following} instance as int
     **/
    public int getFollowing() {
        return following;
    }

    /**
     * Method to get {@link #privateGists} instance <br>
     * No-any params required
     *
     * @return {@link #privateGists} instance as int
     **/
    public int getPrivateGists() {
        return privateGists;
    }

    /**
     * Method to get {@link #totalPrivateRepos} instance <br>
     * No-any params required
     *
     * @return {@link #totalPrivateRepos} instance as int
     **/
    public int getTotalPrivateRepos() {
        return totalPrivateRepos;
    }

    /**
     * Method to get {@link #ownedPrivateRepos} instance <br>
     * No-any params required
     *
     * @return {@link #ownedPrivateRepos} instance as int
     **/
    public int getOwnedPrivateRepos() {
        return ownedPrivateRepos;
    }

    /**
     * Method to get {@link #diskUsage} instance <br>
     * No-any params required
     *
     * @return {@link #diskUsage} instance as int
     **/
    public int getDiskUsage() {
        return diskUsage;
    }

    /**
     * Method to get {@link #collaborators} instance <br>
     * No-any params required
     *
     * @return {@link #collaborators} instance as int
     **/
    public int getCollaborators() {
        return collaborators;
    }

    /**
     * Method to get {@link #twoFactorAuthentication} instance <br>
     * No-any params required
     *
     * @return {@link #twoFactorAuthentication} instance as boolean
     **/
    public boolean isTwoFactorAuthenticationEnabled() {
        return twoFactorAuthentication;
    }

    /**
     * Method to get {@link #plan} instance <br>
     * No-any params required
     *
     * @return {@link #plan} instance as {@link UserPlan}
     **/
    public UserPlan getPlan() {
        return plan;
    }

    /**
     * Method to get {@link #suspendedAt} instance <br>
     * No-any params required
     *
     * @return {@link #suspendedAt} instance as {@link String}
     **/
    public String getSuspendedAt() {
        return suspendedAt;
    }

    /**
     * Method to get {@link #suspendedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #suspendedAt} timestamp as long
     **/
    public long getSuspendedAtTimestamp() {
        return getDateTimestamp(suspendedAt);
    }

    /**
     * Method to get {@link #businessPlus} instance <br>
     * No-any params required
     *
     * @return {@link #businessPlus} instance as boolean
     **/
    public boolean hasBusinessPlus() {
        return businessPlus;
    }

    /**
     * Method to get {@link #ldapDn} instance <br>
     * No-any params required
     *
     * @return {@link #ldapDn} instance as {@link String}
     **/
    public String getLdapDn() {
        return ldapDn;
    }

    /**
     * Method to create a users list
     *
     * @param jUsers: obtained from GitHub's response
     * @return users list as {@link ArrayList} of {@link User}
     **/
    @Returner
    public static ArrayList<User> returnUsersList(JSONArray jUsers) {
        ArrayList<User> users = new ArrayList<>();
        if (jUsers != null)
            for (int j = 0; j < jUsers.length(); j++)
                users.add(new User(jUsers.getJSONObject(j)));
        return users;
    }

    /**
     * Method to create a users list
     *
     * @param usersResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return users list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnUsersList(String usersResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(usersResponse);
            case LIBRARY_OBJECT:
                return (T) returnUsersList(new JSONArray(usersResponse));
            default:
                return (T) usersResponse;
        }
    }

    /**
     * The {@code UserPlan} class is useful to format a GitHub's user plan
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class UserPlan extends InnerClassItem {

        /**
         * {@code collaborators} of the user plan
         **/
        private final int collaborators;

        /**
         * {@code name} of the user plan
         **/
        private final String name;

        /**
         * {@code space} of the user plan
         **/
        private final int space;

        /**
         * {@code privateRepos} private repos of the user plan
         **/
        private final int privateRepos;

        /**
         * Constructor to init a {@link UserPlan}
         *
         * @param collaborators: collaborators of the user plan
         * @param name           : name of the user plan
         * @param space          : space of the user plan
         * @param privateRepos:  private repos of the user plan
         **/
        public UserPlan(int collaborators, String name, int space, int privateRepos) {
            super(null);
            this.collaborators = collaborators;
            this.name = name;
            this.space = space;
            this.privateRepos = privateRepos;
        }

        /**
         * Constructor to init a {@link UserPlan}
         *
         * @param jUserPlan: user plan collaborator {@link JSONObject}
         **/
        public UserPlan(JSONObject jUserPlan) {
            super(jUserPlan);
            collaborators = hItem.getInt("collaborators", 0);
            name = hItem.getString("name");
            space = hItem.getInt("space", 0);
            privateRepos = hItem.getInt("private_repos", 0);
        }

        /**
         * Method to get {@link #collaborators} instance <br>
         * No-any params required
         *
         * @return {@link #collaborators} instance as int
         **/
        public int getCollaborators() {
            return collaborators;
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #space} instance <br>
         * No-any params required
         *
         * @return {@link #space} instance as int
         **/
        public int getSpace() {
            return space;
        }

        /**
         * Method to get {@link #privateRepos} instance <br>
         * No-any params required
         *
         * @return {@link #privateRepos} instance as int
         **/
        public int getPrivateRepos() {
            return privateRepos;
        }

    }

}
