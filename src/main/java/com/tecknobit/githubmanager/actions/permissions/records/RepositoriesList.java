package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class RepositoriesList extends GitHubList {

    private final ArrayList<Repository> repositories;

    /**
     * Constructor to init an {@link RepositoriesList}
     *
     * @param totalCount : total number of the repositories in the list
     **/
    public RepositoriesList(int totalCount, ArrayList<Repository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link RepositoriesList}
     *
     * @param jRepositoriesList : repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public RepositoriesList(JSONObject jRepositoriesList) {
        super(jRepositoriesList);
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        repositories = new ArrayList<>();
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new Repository(jRepositories.getJSONObject(j)));
    }

    public Collection<Repository> getRepositories() {
        return repositories;
    }

    public static class Repository {

        private final long id;
        private final String nodeId;
        private final String name;
        private final String fullName;
        private final Owner owner;
        private final boolean privateRepo;
        private final String htmlUrl;
        private final String description;
        private final boolean fork;
        private final String url;
        private final String archiveUrl;
        private final String assigneesUrl;
        private final String blobsUrl;
        private final String branchesUrl;
        private final String collaboratorsUrl;
        private final String commentsUrl;
        private final String commitsUrl;
        private final String compareUrl;
        private final String contentsUrl;
        private final String contributorsUrl;
        private final String deploymentsUrl;
        private final String downloadUrl;
        private final String eventsUrl;
        private final String forksUrl;
        private final String gitCommitsUrl;
        private final String gitRefsUrl;
        private final String gitTagsUrl;
        private final String gitUrl;
        private final String issueCommentUrl;
        private final String issueEventsUrl;
        private final String issuesUrl;
        private final String keysUrl;
        private final String labelsUrl;
        private final String languagesUrl;
        private final String mergesUrl;
        private final String milestonesUrl;
        private final String notificationsUrl;
        private final String pullsUrl;
        private final String releasesUrl;
        private final String sshUrl;
        private final String stargazersUrl;
        private final String statutesUrl;
        private final String subscribersUrl;
        private final String subscriptionUrl;
        private final String tagsUrl;
        private final String teamsUrl;
        private final String treesUrl;
        private final String cloneUrl;
        private final String mirrorUrl;
        private final String hooksUrl;
        private final String svnUrl;
        private final String homePage;
        private final int forksCount;
        private final int stargazersCount;
        private final int watchersCount;
        private final int size;
        private final String defaultBranch;
        private final int openIssuesCount;
        private final boolean isTemplate;
        private final ArrayList<String> topics;
        private final boolean hasIssues;
        private final boolean hasProjects;
        private final boolean hasWiki;
        private final boolean hasPages;
        private final boolean hasDownloads;
        private final boolean archived;
        private final boolean disabled;
        private final String visibility;
        private final String pushedAt;
        private final String createdAt;
        private final String updatedAt;
        private final Permissions permissions;
        private final boolean allowRebaseMerge;
        private final String tempCloneToken;
        private final boolean allowSquashMerge;
        private final boolean allowAutoMerge;
        private final boolean deleteBranchOnMerge;
        private final boolean allowMergeCommit;
        private final int subscribersCount;
        private final int networkCount;
        private final License license;
        private final int forks;
        private final int openIssues;
        private final int watchers;

        public Repository(long id, String nodeId, String name, String fullName, Owner owner, boolean privateRepo,
                          String htmlUrl, String description, boolean fork, String url, String archiveUrl, String assigneesUrl,
                          String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl,
                          String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl,
                          String downloadUrl, String eventsUrl, String forksUrl, String gitCommitsUrl, String gitRefsUrl,
                          String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl,
                          String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl,
                          String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, String stargazersUrl,
                          String statutesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl,
                          String treesUrl, String cloneUrl, String mirrorUrl, String hooksUrl, String svnUrl, String homePage,
                          int forksCount, int stargazersCount, int watchersCount, int size, String defaultBranch,
                          int openIssuesCount, boolean isTemplate, ArrayList<String> topics, boolean hasIssues,
                          boolean hasProjects, boolean hasWiki, boolean hasPages, boolean hasDownloads, boolean archived,
                          boolean disabled, String visibility, String pushedAt, String createdAt, String updatedAt,
                          Permissions permissions, boolean allowRebaseMerge, String tempCloneToken,
                          boolean allowSquashMerge, boolean allowAutoMerge, boolean deleteBranchOnMerge,
                          boolean allowMergeCommit, int subscribersCount, int networkCount, License license, int forks,
                          int openIssues, int watchers) {
            this.id = id;
            this.nodeId = nodeId;
            this.name = name;
            this.fullName = fullName;
            this.owner = owner;
            this.privateRepo = privateRepo;
            this.htmlUrl = htmlUrl;
            this.description = description;
            this.fork = fork;
            this.url = url;
            this.archiveUrl = archiveUrl;
            this.assigneesUrl = assigneesUrl;
            this.blobsUrl = blobsUrl;
            this.branchesUrl = branchesUrl;
            this.collaboratorsUrl = collaboratorsUrl;
            this.commentsUrl = commentsUrl;
            this.commitsUrl = commitsUrl;
            this.compareUrl = compareUrl;
            this.contentsUrl = contentsUrl;
            this.contributorsUrl = contributorsUrl;
            this.deploymentsUrl = deploymentsUrl;
            this.downloadUrl = downloadUrl;
            this.eventsUrl = eventsUrl;
            this.forksUrl = forksUrl;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.gitUrl = gitUrl;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.languagesUrl = languagesUrl;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.notificationsUrl = notificationsUrl;
            this.pullsUrl = pullsUrl;
            this.releasesUrl = releasesUrl;
            this.sshUrl = sshUrl;
            this.stargazersUrl = stargazersUrl;
            this.statutesUrl = statutesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.cloneUrl = cloneUrl;
            this.mirrorUrl = mirrorUrl;
            this.hooksUrl = hooksUrl;
            this.svnUrl = svnUrl;
            this.homePage = homePage;
            this.forksCount = forksCount;
            this.stargazersCount = stargazersCount;
            this.watchersCount = watchersCount;
            this.size = size;
            this.defaultBranch = defaultBranch;
            this.openIssuesCount = openIssuesCount;
            this.isTemplate = isTemplate;
            this.topics = topics;
            this.hasIssues = hasIssues;
            this.hasProjects = hasProjects;
            this.hasWiki = hasWiki;
            this.hasPages = hasPages;
            this.hasDownloads = hasDownloads;
            this.archived = archived;
            this.disabled = disabled;
            this.visibility = visibility;
            this.pushedAt = pushedAt;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.permissions = permissions;
            this.allowRebaseMerge = allowRebaseMerge;
            this.tempCloneToken = tempCloneToken;
            this.allowSquashMerge = allowSquashMerge;
            this.allowAutoMerge = allowAutoMerge;
            this.deleteBranchOnMerge = deleteBranchOnMerge;
            this.allowMergeCommit = allowMergeCommit;
            this.subscribersCount = subscribersCount;
            this.networkCount = networkCount;
            this.license = license;
            this.forks = forks;
            this.openIssues = openIssues;
            this.watchers = watchers;
        }

        public Repository(JSONObject jRepository) {
            JsonHelper hRepository = new JsonHelper(jRepository);
            id = hRepository.getLong("id", 0);
            nodeId = hRepository.getString("node_id");
            name = hRepository.getString("name");
            fullName = hRepository.getString("full_name");
            owner = new Owner(hRepository.getJSONObject("owner", new JSONObject()));
            privateRepo = hRepository.getBoolean("private");
            htmlUrl = hRepository.getString("html_url");
            description = hRepository.getString("description");
            fork = hRepository.getBoolean("fork");
            url = hRepository.getString("url");
            archiveUrl = hRepository.getString("archive_url");
            assigneesUrl = hRepository.getString("assignees_url");
            blobsUrl = hRepository.getString("blobs_url");
            branchesUrl = hRepository.getString("branches_url");
            collaboratorsUrl = hRepository.getString("collaborators_url");
            commentsUrl = hRepository.getString("comments_url");
            commitsUrl = hRepository.getString("commits_url");
            compareUrl = hRepository.getString("compare_url");
            contentsUrl = hRepository.getString("contents_url");
            contributorsUrl = hRepository.getString("contributors_url");
            deploymentsUrl = hRepository.getString("deployments_url");
            downloadUrl = hRepository.getString("downloads_url");
            eventsUrl = hRepository.getString("events_url");
            forksUrl = hRepository.getString("forks_url");
            gitCommitsUrl = hRepository.getString("git_commits_url");
            gitRefsUrl = hRepository.getString("git_refs_url");
            gitTagsUrl = hRepository.getString("git_tags_url");
            gitUrl = hRepository.getString("git_url");
            issueCommentUrl = hRepository.getString("issue_comment_url");
            issueEventsUrl = hRepository.getString("issue_events_url");
            issuesUrl = hRepository.getString("issues_url");
            keysUrl = hRepository.getString("keys_url");
            labelsUrl = hRepository.getString("labels_url");
            languagesUrl = hRepository.getString("languages_url");
            mergesUrl = hRepository.getString("merges_url");
            milestonesUrl = hRepository.getString("milestones_url");
            notificationsUrl = hRepository.getString("notifications_url");
            pullsUrl = hRepository.getString("pulls_url");
            releasesUrl = hRepository.getString("releases_url");
            sshUrl = hRepository.getString("ssh_url");
            stargazersUrl = hRepository.getString("stargazers_url");
            statutesUrl = hRepository.getString("statuses_url");
            subscribersUrl = hRepository.getString("subscribers_url");
            subscriptionUrl = hRepository.getString("subscription_url");
            tagsUrl = hRepository.getString("tags_url");
            teamsUrl = hRepository.getString("teams_url");
            treesUrl = hRepository.getString("trees_url");
            cloneUrl = hRepository.getString("clone_url");
            mirrorUrl = hRepository.getString("mirror_url");
            hooksUrl = hRepository.getString("hooks_url");
            svnUrl = hRepository.getString("svn_url");
            homePage = hRepository.getString("homepage");
            forksCount = hRepository.getInt("forks_count");
            stargazersCount = hRepository.getInt("stargazers_count");
            watchersCount = hRepository.getInt("watchers_count");
            size = hRepository.getInt("size");
            defaultBranch = hRepository.getString("default_branch");
            openIssuesCount = hRepository.getInt("open_issues_count");
            isTemplate = hRepository.getBoolean("is_template");
            JSONArray jTopics = hRepository.getJSONArray("topics");
            topics = new ArrayList<>();
            for (int j = 0; j < jTopics.length(); j++)
                topics.add(jTopics.getString(j));
            hasIssues = hRepository.getBoolean("has_issues");
            hasProjects = hRepository.getBoolean("has_projects");
            hasWiki = hRepository.getBoolean("has_wiki");
            hasPages = hRepository.getBoolean("has_pages");
            hasDownloads = hRepository.getBoolean("has_downloads");
            archived = hRepository.getBoolean("archived");
            disabled = hRepository.getBoolean("disabled");
            visibility = hRepository.getString("visibility");
            pushedAt = hRepository.getString("pushed_at");
            createdAt = hRepository.getString("created_at");
            updatedAt = hRepository.getString("updated_at");
            permissions = new Permissions(hRepository.getJSONObject("permissions", new JSONObject()));
            allowRebaseMerge = hRepository.getBoolean("allow_rebase_merge");
            tempCloneToken = hRepository.getString("temp_clone_token");
            allowSquashMerge = hRepository.getBoolean("allow_squash_merge");
            allowAutoMerge = hRepository.getBoolean("allow_auto_merge");
            deleteBranchOnMerge = hRepository.getBoolean("delete_branch_on_merge");
            allowMergeCommit = hRepository.getBoolean("allow_merge_commit");
            subscribersCount = hRepository.getInt("subscribers_count");
            networkCount = hRepository.getInt("network_count");
            license = new License(hRepository.getJSONObject("license", new JSONObject()));
            forks = hRepository.getInt("forks");
            openIssues = hRepository.getInt("open_issues");
            watchers = hRepository.getInt("watchers");
        }

        public long getId() {
            return id;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getName() {
            return name;
        }

        public String getFullName() {
            return fullName;
        }

        public Owner getOwner() {
            return owner;
        }

        public boolean isPrivateRepo() {
            return privateRepo;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public String getDescription() {
            return description;
        }

        public boolean isFork() {
            return fork;
        }

        public String getUrl() {
            return url;
        }

        public String getArchiveUrl() {
            return archiveUrl;
        }

        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        public String getBlobsUrl() {
            return blobsUrl;
        }

        public String getBranchesUrl() {
            return branchesUrl;
        }

        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getCommitsUrl() {
            return commitsUrl;
        }

        public String getCompareUrl() {
            return compareUrl;
        }

        public String getContentsUrl() {
            return contentsUrl;
        }

        public String getContributorsUrl() {
            return contributorsUrl;
        }

        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public String getForksUrl() {
            return forksUrl;
        }

        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        public String getGitUrl() {
            return gitUrl;
        }

        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        public String getIssuesUrl() {
            return issuesUrl;
        }

        public String getKeysUrl() {
            return keysUrl;
        }

        public String getLabelsUrl() {
            return labelsUrl;
        }

        public String getLanguagesUrl() {
            return languagesUrl;
        }

        public String getMergesUrl() {
            return mergesUrl;
        }

        public String getMilestonesUrl() {
            return milestonesUrl;
        }

        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        public String getPullsUrl() {
            return pullsUrl;
        }

        public String getReleasesUrl() {
            return releasesUrl;
        }

        public String getSshUrl() {
            return sshUrl;
        }

        public String getStargazersUrl() {
            return stargazersUrl;
        }

        public String getStatutesUrl() {
            return statutesUrl;
        }

        public String getSubscribersUrl() {
            return subscribersUrl;
        }

        public String getSubscriptionUrl() {
            return subscriptionUrl;
        }

        public String getTagsUrl() {
            return tagsUrl;
        }

        public String getTeamsUrl() {
            return teamsUrl;
        }

        public String getTreesUrl() {
            return treesUrl;
        }

        public String getCloneUrl() {
            return cloneUrl;
        }

        public String getMirrorUrl() {
            return mirrorUrl;
        }

        public String getHooksUrl() {
            return hooksUrl;
        }

        public String getSvnUrl() {
            return svnUrl;
        }

        public String getHomePage() {
            return homePage;
        }

        public int getForksCount() {
            return forksCount;
        }

        public int getStargazersCount() {
            return stargazersCount;
        }

        public int getWatchersCount() {
            return watchersCount;
        }

        public int getSize() {
            return size;
        }

        public String getDefaultBranch() {
            return defaultBranch;
        }

        public int getOpenIssuesCount() {
            return openIssuesCount;
        }

        public boolean isTemplate() {
            return isTemplate;
        }

        public Collection<String> getTopics() {
            return topics;
        }

        public boolean isHasIssues() {
            return hasIssues;
        }

        public boolean isHasProjects() {
            return hasProjects;
        }

        public boolean isHasWiki() {
            return hasWiki;
        }

        public boolean isHasPages() {
            return hasPages;
        }

        public boolean isHasDownloads() {
            return hasDownloads;
        }

        public boolean isArchived() {
            return archived;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public String getVisibility() {
            return visibility;
        }

        public String getPushedAt() {
            return pushedAt;
        }

        /**
         * Method to get {@link #pushedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #pushedAt} timestamp as long
         **/
        public long getPushedAtTimestamp() {
            try {
                return dateFormatter.parse(pushedAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        public String getCreatedAt() {
            return createdAt;
        }

        /**
         * Method to get {@link #createdAt} timestamp <br>
         * Any params required
         *
         * @return {@link #createdAt} timestamp as long
         **/
        public long getCreatedAtTimestamp() {
            try {
                return dateFormatter.parse(createdAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         * Method to get {@link #updatedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #updatedAt} timestamp as long
         **/
        public long getUpdatedAtTimestamp() {
            try {
                return dateFormatter.parse(updatedAt).getTime();
            } catch (ParseException e) {
                return -1;
            }
        }

        public Permissions getPermissions() {
            return permissions;
        }

        public boolean isAllowRebaseMerge() {
            return allowRebaseMerge;
        }

        public String getTempCloneToken() {
            return tempCloneToken;
        }

        public boolean isAllowSquashMerge() {
            return allowSquashMerge;
        }

        public boolean isAllowAutoMerge() {
            return allowAutoMerge;
        }

        public boolean isDeleteBranchOnMerge() {
            return deleteBranchOnMerge;
        }

        public boolean isAllowMergeCommit() {
            return allowMergeCommit;
        }

        public int getSubscribersCount() {
            return subscribersCount;
        }

        public int getNetworkCount() {
            return networkCount;
        }

        public License getLicense() {
            return license;
        }

        public int getForks() {
            return forks;
        }

        public int getOpenIssues() {
            return openIssues;
        }

        public int getWatchers() {
            return watchers;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public static class Owner {

            private final String login;
            private final long id;
            private final String nodeId;
            private final String avatarUrl;
            private final String gravatarUrl;
            private final String url;
            private final String htmlUrl;
            private final String followersUrl;
            private final String followingUrl;
            private final String gistsUrl;
            private final String starredUrl;
            private final String subscriptionsUrl;
            private final String organizationsUrl;
            private final String reposUrl;
            private final String eventsUrl;
            private final String receivedEventsUrl;
            private final String type;
            private final boolean siteAdmin;

            public Owner(String login, long id, String nodeId, String avatarUrl, String gravatarUrl, String url,
                         String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
                         String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
                         String receivedEventsUrl, String type, boolean siteAdmin) {
                this.login = login;
                this.id = id;
                this.nodeId = nodeId;
                this.avatarUrl = avatarUrl;
                this.gravatarUrl = gravatarUrl;
                this.url = url;
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
            }

            public Owner(JSONObject jOwner) {
                JsonHelper hOwner = new JsonHelper(jOwner);
                login = hOwner.getString("login");
                id = hOwner.getLong("id", 0);
                nodeId = hOwner.getString("node_id");
                avatarUrl = hOwner.getString("avatar_url");
                gravatarUrl = hOwner.getString("gravatar_id");
                url = hOwner.getString("url");
                htmlUrl = hOwner.getString("html_url");
                followersUrl = hOwner.getString("followers_url");
                followingUrl = hOwner.getString("following_url");
                gistsUrl = hOwner.getString("gists_url");
                starredUrl = hOwner.getString("starred_url");
                subscriptionsUrl = hOwner.getString("subscriptions_url");
                organizationsUrl = hOwner.getString("organizations_url");
                reposUrl = hOwner.getString("repos_url");
                eventsUrl = hOwner.getString("events_url");
                receivedEventsUrl = hOwner.getString("received_events_url");
                type = hOwner.getString("type");
                siteAdmin = hOwner.getBoolean("site_admin");
            }

            public String getLogin() {
                return login;
            }

            public long getId() {
                return id;
            }

            public String getNodeId() {
                return nodeId;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public String getGravatarUrl() {
                return gravatarUrl;
            }

            public String getUrl() {
                return url;
            }

            public String getHtmlUrl() {
                return htmlUrl;
            }

            public String getFollowersUrl() {
                return followersUrl;
            }

            public String getFollowingUrl() {
                return followingUrl;
            }

            public String getGistsUrl() {
                return gistsUrl;
            }

            public String getStarredUrl() {
                return starredUrl;
            }

            public String getSubscriptionsUrl() {
                return subscriptionsUrl;
            }

            public String getOrganizationsUrl() {
                return organizationsUrl;
            }

            public String getReposUrl() {
                return reposUrl;
            }

            public String getEventsUrl() {
                return eventsUrl;
            }

            public String getReceivedEventsUrl() {
                return receivedEventsUrl;
            }

            public String getType() {
                return type;
            }

            public boolean isSiteAdmin() {
                return siteAdmin;
            }

            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

    public static class Permissions {

        private final boolean admin;
        private final boolean push;
        private final boolean pull;

        public Permissions(boolean admin, boolean push, boolean pull) {
            this.admin = admin;
            this.push = push;
            this.pull = pull;
        }

        public Permissions(JSONObject jRepoPermissions) {
            JsonHelper hRepoPermissions = new JsonHelper(jRepoPermissions);
            admin = hRepoPermissions.getBoolean("admin");
            push = hRepoPermissions.getBoolean("push");
            pull = hRepoPermissions.getBoolean("pull");
        }

        public boolean isAdmin() {
            return admin;
        }

        public boolean isPush() {
            return push;
        }

        public boolean isPull() {
            return pull;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    public static class License {

        private final String key;
        private final String name;
        private final String url;
        private final String spdxId;
        private final String nodeId;
        private final String htmlUrl;

        public License(String key, String name, String url, String spdxId, String nodeId, String htmlUrl) {
            this.key = key;
            this.name = name;
            this.url = url;
            this.spdxId = spdxId;
            this.nodeId = nodeId;
            this.htmlUrl = htmlUrl;
        }

        public License(JSONObject jLicense) {
            JsonHelper hLicense = new JsonHelper(jLicense);
            key = hLicense.getString("key");
            name = hLicense.getString("name");
            url = hLicense.getString("url");
            spdxId = hLicense.getString("spdx_id");
            nodeId = hLicense.getString("node_id");
            htmlUrl = hLicense.getString("html_url");
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getSpdxId() {
            return spdxId;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
