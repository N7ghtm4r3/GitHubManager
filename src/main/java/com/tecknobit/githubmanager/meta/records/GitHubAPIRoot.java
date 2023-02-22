package com.tecknobit.githubmanager.meta.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code GitHubAPIRoot} class is useful to format a GitHub's API root
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#github-api-root">
 * GitHub API Root</a>
 * @see GitHubResponse
 **/
public class GitHubAPIRoot extends GitHubResponse {

    /**
     * {@code currentUserUrl} current user url
     **/
    private final String currentUserUrl;

    /**
     * {@code currentUserAuthorizationHtmlUrl} current user authorization html url
     **/
    private final String currentUserAuthorizationHtmlUrl;

    /**
     * {@code authorizationsUrl} authorizations url
     **/
    private final String authorizationsUrl;

    /**
     * {@code codeSearchUrl} code search url
     **/
    private final String codeSearchUrl;

    /**
     * {@code commitSearchUrl} commit search url
     **/
    private final String commitSearchUrl;

    /**
     * {@code emailsUrl} emails url
     **/
    private final String emailsUrl;

    /**
     * {@code emojisUrl} emojis url
     **/
    private final String emojisUrl;

    /**
     * {@code eventsUrl} events url
     **/
    private final String eventsUrl;

    /**
     * {@code feedsUrl} feeds url
     **/
    private final String feedsUrl;

    /**
     * {@code followersUrl} followers url
     **/
    private final String followersUrl;

    /**
     * {@code followingUrl} following url
     **/
    private final String followingUrl;

    /**
     * {@code gistsUrl} gists url
     **/
    private final String gistsUrl;

    /**
     * {@code hubUrl} hub url
     **/
    private final String hubUrl;

    /**
     * {@code issueSearchUrl} issue search url
     **/
    private final String issueSearchUrl;

    /**
     * {@code issuesUrl} issues url
     **/
    private final String issuesUrl;

    /**
     * {@code keysUrl} keys url
     **/
    private final String keysUrl;

    /**
     * {@code labelSearchUrl} label search url
     **/
    private final String labelSearchUrl;

    /**
     * {@code notificationsUrl} notifications url
     **/
    private final String notificationsUrl;

    /**
     * {@code organizationUrl} organizations url
     **/
    private final String organizationUrl;

    /**
     * {@code organizationRepositoriesUrl} organization repositories url
     **/
    private final String organizationRepositoriesUrl;

    /**
     * {@code organizationTeamsUrl} organization teams url
     **/
    private final String organizationTeamsUrl;

    /**
     * {@code publicGistsUrl} public gists url
     **/
    private final String publicGistsUrl;

    /**
     * {@code rateLimitUrl} rate limit url
     **/
    private final String rateLimitUrl;

    /**
     * {@code repositoryUrl} repository url
     **/
    private final String repositoryUrl;

    /**
     * {@code repositorySearchUrl} repository search url
     **/
    private final String repositorySearchUrl;

    /**
     * {@code currentUserRepositoriesUrl} current user repositories url
     **/
    private final String currentUserRepositoriesUrl;

    /**
     * {@code starredUrl} starred url
     **/
    private final String starredUrl;

    /**
     * {@code starredGistsUrl} starred gists url
     **/
    private final String starredGistsUrl;

    /**
     * {@code topicSearchUrl} topic search url
     **/
    private final String topicSearchUrl;

    /**
     * {@code userUrl} user url
     **/
    private final String userUrl;

    /**
     * {@code userOrganizationsUrl} user organizations url
     **/
    private final String userOrganizationsUrl;

    /**
     * {@code userRepositoriesUrl} user repositories url
     **/
    private final String userRepositoriesUrl;

    /**
     * {@code userSearchUrl} user search url
     **/
    private final String userSearchUrl;

    /**
     * Constructor to init a {@link GitHubAPIRoot}
     *
     * @param currentUserUrl:                  current user url
     * @param currentUserAuthorizationHtmlUrl: current user authorization html url
     * @param authorizationsUrl:               authorizations url
     * @param codeSearchUrl:                   code search url
     * @param commitSearchUrl:                 commit search url
     * @param emailsUrl:                       emails url
     * @param emojisUrl:                       emojis url
     * @param eventsUrl:                       events url
     * @param feedsUrl:                        feeds url
     * @param followersUrl:                    followers url
     * @param followingUrl:                    following url
     * @param gistsUrl:                        gists url
     * @param hubUrl:                          hub url
     * @param issueSearchUrl:                  issue search name url
     * @param issuesUrl:                       issues  url
     * @param keysUrl:                         keys url
     * @param labelSearchUrl:                  label search url
     * @param notificationsUrl:                notification url
     * @param organizationUrl:                 organization url
     * @param organizationRepositoriesUrl:     organization repositories url
     * @param organizationTeamsUrl:            organization teams url
     * @param publicGistsUrl:                  public gists url
     * @param rateLimitUrl:                    rate limit url
     * @param repositoryUrl:                   repository url
     * @param repositorySearchUrl:             repository search url
     * @param currentUserRepositoriesUrl:      current user repositories url
     * @param starredUrl:                      starred url
     * @param starredGistsUrl:                 starred gists url
     * @param topicSearchUrl:                  topic search url
     * @param userUrl:                         user url
     * @param userOrganizationsUrl:            user organizations url
     * @param userRepositoriesUrl:             user repositories url
     * @param userSearchUrl:                   user search url
     **/
    public GitHubAPIRoot(String currentUserUrl, String currentUserAuthorizationHtmlUrl, String authorizationsUrl,
                         String codeSearchUrl, String commitSearchUrl, String emailsUrl, String emojisUrl,
                         String eventsUrl, String feedsUrl, String followersUrl, String followingUrl, String gistsUrl,
                         String hubUrl, String issueSearchUrl, String issuesUrl, String keysUrl, String labelSearchUrl,
                         String notificationsUrl, String organizationUrl, String organizationRepositoriesUrl,
                         String organizationTeamsUrl, String publicGistsUrl, String rateLimitUrl, String repositoryUrl,
                         String repositorySearchUrl, String currentUserRepositoriesUrl, String starredUrl,
                         String starredGistsUrl, String topicSearchUrl, String userUrl, String userOrganizationsUrl,
                         String userRepositoriesUrl, String userSearchUrl) {
        super(null);
        this.currentUserUrl = currentUserUrl;
        this.currentUserAuthorizationHtmlUrl = currentUserAuthorizationHtmlUrl;
        this.authorizationsUrl = authorizationsUrl;
        this.codeSearchUrl = codeSearchUrl;
        this.commitSearchUrl = commitSearchUrl;
        this.emailsUrl = emailsUrl;
        this.emojisUrl = emojisUrl;
        this.eventsUrl = eventsUrl;
        this.feedsUrl = feedsUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.hubUrl = hubUrl;
        this.issueSearchUrl = issueSearchUrl;
        this.issuesUrl = issuesUrl;
        this.keysUrl = keysUrl;
        this.labelSearchUrl = labelSearchUrl;
        this.notificationsUrl = notificationsUrl;
        this.organizationUrl = organizationUrl;
        this.organizationRepositoriesUrl = organizationRepositoriesUrl;
        this.organizationTeamsUrl = organizationTeamsUrl;
        this.publicGistsUrl = publicGistsUrl;
        this.rateLimitUrl = rateLimitUrl;
        this.repositoryUrl = repositoryUrl;
        this.repositorySearchUrl = repositorySearchUrl;
        this.currentUserRepositoriesUrl = currentUserRepositoriesUrl;
        this.starredUrl = starredUrl;
        this.starredGistsUrl = starredGistsUrl;
        this.topicSearchUrl = topicSearchUrl;
        this.userUrl = userUrl;
        this.userOrganizationsUrl = userOrganizationsUrl;
        this.userRepositoriesUrl = userRepositoriesUrl;
        this.userSearchUrl = userSearchUrl;
    }

    /**
     * Constructor to init a {@link GitHubAPIRoot}
     *
     * @param jGitHubAPIRoot: GitHub API Root details as {@link JSONObject}
     **/
    public GitHubAPIRoot(JSONObject jGitHubAPIRoot) {
        super(jGitHubAPIRoot);
        currentUserUrl = hResponse.getString("current_user_url");
        currentUserAuthorizationHtmlUrl = hResponse.getString("current_user_authorizations_html_url");
        authorizationsUrl = hResponse.getString("authorizations_url");
        codeSearchUrl = hResponse.getString("code_search_url");
        commitSearchUrl = hResponse.getString("commit_search_url");
        emailsUrl = hResponse.getString("emails_url");
        emojisUrl = hResponse.getString("emojis_url");
        eventsUrl = hResponse.getString("events_url");
        feedsUrl = hResponse.getString("feeds_url");
        followersUrl = hResponse.getString("followers_url");
        followingUrl = hResponse.getString("following_url");
        gistsUrl = hResponse.getString("gists_url");
        hubUrl = hResponse.getString("hub_url");
        issueSearchUrl = hResponse.getString("issue_search_url");
        issuesUrl = hResponse.getString("issues_url");
        keysUrl = hResponse.getString("keys_url");
        labelSearchUrl = hResponse.getString("label_search_url");
        notificationsUrl = hResponse.getString("notifications_url");
        organizationUrl = hResponse.getString("organization_url");
        organizationRepositoriesUrl = hResponse.getString("organization_repositories_url");
        organizationTeamsUrl = hResponse.getString("organization_teams_url");
        publicGistsUrl = hResponse.getString("public_gists_url");
        rateLimitUrl = hResponse.getString("rate_limit_url");
        repositoryUrl = hResponse.getString("repository_url");
        repositorySearchUrl = hResponse.getString("repository_search_url");
        currentUserRepositoriesUrl = hResponse.getString("current_user_repositories_url");
        starredUrl = hResponse.getString("starred_url");
        starredGistsUrl = hResponse.getString("starred_gists_url");
        topicSearchUrl = hResponse.getString("topic_search_url");
        userUrl = hResponse.getString("user_url");
        userOrganizationsUrl = hResponse.getString("user_organizations_url");
        userRepositoriesUrl = hResponse.getString("user_repositories_url");
        userSearchUrl = hResponse.getString("user_search_url");
    }

    /**
     * Method to get {@link #currentUserUrl} instance <br>
     * No-any params required
     *
     * @return {@link #currentUserUrl} instance as {@link String}
     **/
    public String getCurrentUserUrl() {
        return currentUserUrl;
    }

    /**
     * Method to get {@link #currentUserAuthorizationHtmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #currentUserAuthorizationHtmlUrl} instance as {@link String}
     **/
    public String getCurrentUserAuthorizationHtmlUrl() {
        return currentUserAuthorizationHtmlUrl;
    }

    /**
     * Method to get {@link #authorizationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #authorizationsUrl} instance as {@link String}
     **/
    public String getAuthorizationsUrl() {
        return authorizationsUrl;
    }

    /**
     * Method to get {@link #codeSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #codeSearchUrl} instance as {@link String}
     **/
    public String getCodeSearchUrl() {
        return codeSearchUrl;
    }

    /**
     * Method to get {@link #commitSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitSearchUrl} instance as {@link String}
     **/
    public String getCommitSearchUrl() {
        return commitSearchUrl;
    }

    /**
     * Method to get {@link #emailsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #emailsUrl} instance as {@link String}
     **/
    public String getEmailsUrl() {
        return emailsUrl;
    }

    /**
     * Method to get {@link #emojisUrl} instance <br>
     * No-any params required
     *
     * @return {@link #emojisUrl} instance as {@link String}
     **/
    public String getEmojisUrl() {
        return emojisUrl;
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
     * Method to get {@link #feedsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #feedsUrl} instance as {@link String}
     **/
    public String getFeedsUrl() {
        return feedsUrl;
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
     * Method to get {@link #hubUrl} instance <br>
     * No-any params required
     *
     * @return {@link #hubUrl} instance as {@link String}
     **/
    public String getHubUrl() {
        return hubUrl;
    }

    /**
     * Method to get {@link #issueSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issueSearchUrl} instance as {@link String}
     **/
    public String getIssueSearchUrl() {
        return issueSearchUrl;
    }

    /**
     * Method to get {@link #issuesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issuesUrl} instance as {@link String}
     **/
    public String getIssuesUrl() {
        return issuesUrl;
    }

    /**
     * Method to get {@link #keysUrl} instance <br>
     * No-any params required
     *
     * @return {@link #keysUrl} instance as {@link String}
     **/
    public String getKeysUrl() {
        return keysUrl;
    }

    /**
     * Method to get {@link #labelSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #labelSearchUrl} instance as {@link String}
     **/
    public String getLabelSearchUrl() {
        return labelSearchUrl;
    }

    /**
     * Method to get {@link #notificationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #notificationsUrl} instance as {@link String}
     **/
    public String getNotificationsUrl() {
        return notificationsUrl;
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
     * Method to get {@link #organizationRepositoriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #organizationRepositoriesUrl} instance as {@link String}
     **/
    public String getOrganizationRepositoriesUrl() {
        return organizationRepositoriesUrl;
    }

    /**
     * Method to get {@link #organizationTeamsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #organizationTeamsUrl} instance as {@link String}
     **/
    public String getOrganizationTeamsUrl() {
        return organizationTeamsUrl;
    }

    /**
     * Method to get {@link #publicGistsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #publicGistsUrl} instance as {@link String}
     **/
    public String getPublicGistsUrl() {
        return publicGistsUrl;
    }

    /**
     * Method to get {@link #rateLimitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #rateLimitUrl} instance as {@link String}
     **/
    public String getRateLimitUrl() {
        return rateLimitUrl;
    }

    /**
     * Method to get {@link #repositoryUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryUrl} instance as {@link String}
     **/
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    /**
     * Method to get {@link #repositorySearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositorySearchUrl} instance as {@link String}
     **/
    public String getRepositorySearchUrl() {
        return repositorySearchUrl;
    }

    /**
     * Method to get {@link #currentUserRepositoriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #currentUserRepositoriesUrl} instance as {@link String}
     **/
    public String getCurrentUserRepositoriesUrl() {
        return currentUserRepositoriesUrl;
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
     * Method to get {@link #starredGistsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #starredGistsUrl} instance as {@link String}
     **/
    public String getStarredGistsUrl() {
        return starredGistsUrl;
    }

    /**
     * Method to get {@link #topicSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #topicSearchUrl} instance as {@link String}
     **/
    public String getTopicSearchUrl() {
        return topicSearchUrl;
    }

    /**
     * Method to get {@link #userUrl} instance <br>
     * No-any params required
     *
     * @return {@link #userUrl} instance as {@link String}
     **/
    public String getUserUrl() {
        return userUrl;
    }

    /**
     * Method to get {@link #userOrganizationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #userOrganizationsUrl} instance as {@link String}
     **/
    public String getUserOrganizationsUrl() {
        return userOrganizationsUrl;
    }

    /**
     * Method to get {@link #userRepositoriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #userRepositoriesUrl} instance as {@link String}
     **/
    public String getUserRepositoriesUrl() {
        return userRepositoriesUrl;
    }

    /**
     * Method to get {@link #userSearchUrl} instance <br>
     * No-any params required
     *
     * @return {@link #userSearchUrl} instance as {@link String}
     **/
    public String getUserSearchUrl() {
        return userSearchUrl;
    }

}
