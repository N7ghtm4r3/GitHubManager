package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.GitHubList;
import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;
import static com.tecknobit.githubmanager.actions.permissions.records.RepositoriesList.Repository.RepoVisibility.vPrivate;

/**
 * The {@code RepositoriesList} class is useful to format a GitHub's repositories list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
 * List selected repositories enabled for GitHub Actions in an organization</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RepositoriesList extends GitHubList {

    /**
     * {@code organizations} repositories list
     **/
    private final ArrayList<Repository> repositories;

    /**
     * Constructor to init an {@link RepositoriesList}
     *
     * @param totalCount    : total number of the repositories in the list
     * @param repositories: repositories list
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

    /**
     * Method to get {@link #repositories} instance <br>
     * Any params required
     *
     * @return {@link #repositories} instance as {@link Collection} of {@link Repository}
     **/
    public Collection<Repository> getRepositories() {
        return repositories;
    }

    /**
     * The {@code Repository} class is useful to format a GitHub's repository
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at:
     * <ul>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     *             Enable a selected repository for GitHub Actions in an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#enable-a-selected-repository-for-github-actions-in-an-organization">
     *             Enable a selected repository for GitHub Actions in an organization</a>
     *     </li>
     *     <li>
     *         <a href="https://docs.github.com/en/rest/actions/permissions#disable-a-selected-repository-for-github-actions-in-an-organization">
     *             Disable a selected repository for GitHub Actions in an organization</a>
     *     </li>
     * </ul>
     **/
    public static class Repository {

        /**
         * {@code id} identifier value
         **/
        private final long id;
        /**
         * {@code nodeId} identifier of the node value
         **/
        private final String nodeId;
        /**
         * {@code name} the name of the repository
         **/
        private final String name;
        /**
         * {@code fullName} fullname value
         **/
        private final String fullName;
        /**
         * {@code owner} owner value
         **/
        private final Owner owner;
        /**
         * {@code privateRepo} whether the repository is private or public
         **/
        private final boolean privateRepo;
        /**
         * {@code htmlUrl} html url value
         **/
        private final String htmlUrl;
        /**
         * {@code description} description value
         **/
        private final String description;
        /**
         * {@code fork} fork value
         **/
        private final boolean fork;
        /**
         * {@code "url"} value
         **/
        private final String url;
        /**
         * {@code archiveUrl} archive url value
         **/
        private final String archiveUrl;
        /**
         * {@code assigneesUrl} assignees url value
         **/
        private final String assigneesUrl;
        /**
         * {@code blobsUrl} identifier blobs url value
         **/
        private final String blobsUrl;
        /**
         * {@code branchesUrl} branches url value
         **/
        private final String branchesUrl;
        /**
         * {@code collaboratorsUrl} collaborators url value
         **/
        private final String collaboratorsUrl;
        /**
         * {@code commentsUrl} comments url value
         **/
        private final String commentsUrl;
        /**
         * {@code commitsUrl} commits url value
         **/
        private final String commitsUrl;
        /**
         * {@code compareUrl} compare url value
         **/
        private final String compareUrl;
        /**
         * {@code contentsUrl} contents url value
         **/
        private final String contentsUrl;
        /**
         * {@code contributorsUrl} contributors url value
         **/
        private final String contributorsUrl;
        /**
         * {@code deploymentsUrl} deployments url value
         **/
        private final String deploymentsUrl;
        /**
         * {@code downloadUrl} download url value
         **/
        private final String downloadUrl;
        /**
         * {@code eventsUrl} events url value
         **/
        private final String eventsUrl;
        /**
         * {@code forksUrl} forks url value
         **/
        private final String forksUrl;
        /**
         * {@code gitCommitsUrl} git commits url value
         **/
        private final String gitCommitsUrl;
        /**
         * {@code gitRefsUrl} git refs url value
         **/
        private final String gitRefsUrl;
        /**
         * {@code gitTagsUrl} git tags url value
         **/
        private final String gitTagsUrl;
        /**
         * {@code gitUrl} git url value
         **/
        private final String gitUrl;
        /**
         * {@code issueCommentUrl} issue comment url value
         **/
        private final String issueCommentUrl;
        /**
         * {@code issueEventsUrl} issue event url value
         **/
        private final String issueEventsUrl;
        /**
         * {@code issuesUrl} issues url value
         **/
        private final String issuesUrl;
        /**
         * {@code keysUrl} keys url value
         **/
        private final String keysUrl;
        /**
         * {@code labelsUrl} labels url value
         **/
        private final String labelsUrl;
        /**
         * {@code languagesUrl} languages url value
         **/
        private final String languagesUrl;
        /**
         * {@code mergesUrl} merges url value
         **/
        private final String mergesUrl;
        /**
         * {@code milestonesUrl} milestones url value
         **/
        private final String milestonesUrl;
        /**
         * {@code notificationsUrl} notifications url value
         **/
        private final String notificationsUrl;
        /**
         * {@code pullsUrl} pulls url value
         **/
        private final String pullsUrl;
        /**
         * {@code releasesUrl} releases url value
         **/
        private final String releasesUrl;
        /**
         * {@code sshUrl} ssh url value
         **/
        private final String sshUrl;
        /**
         * {@code stargazersUrl} stargazers url value
         **/
        private final String stargazersUrl;
        /**
         * {@code statutesUrl} statuses url value
         **/
        private final String statutesUrl;
        /**
         * {@code subscribersUrl} subscribers url value
         **/
        private final String subscribersUrl;
        /**
         * {@code subscriptionUrl} subscription url value
         **/
        private final String subscriptionUrl;
        /**
         * {@code tagsUrl} tags url value
         **/
        private final String tagsUrl;
        /**
         * {@code teamsUrl} teams url value
         **/
        private final String teamsUrl;
        /**
         * {@code treesUrl} trees url value
         **/
        private final String treesUrl;
        /**
         * {@code cloneUrl} clone url value
         **/
        private final String cloneUrl;
        /**
         * {@code mirrorUrl} mirror url value
         **/
        private final String mirrorUrl;
        /**
         * {@code hooksUrl} hooks url value
         **/
        private final String hooksUrl;
        /**
         * {@code svnUrl} svn url value
         **/
        private final String svnUrl;
        /**
         * {@code homePage} homepage value
         **/
        private final String homePage;
        /**
         * {@code forksCount} forks count value
         **/
        private final int forksCount;
        /**
         * {@code stargazersCount} stargazers count value
         **/
        private final int stargazersCount;
        /**
         * {@code watchersCount} watchers count value
         **/
        private final int watchersCount;
        /**
         * {@code size} the size of the repository. Size is calculated hourly. When a repository is initially created, the size is 0
         **/
        private final int size;
        /**
         * {@code defaultBranch} the default branch of the repository
         **/
        private final String defaultBranch;
        /**
         * {@code openIssuesCount} open issues count value
         **/
        private final int openIssuesCount;
        /**
         * {@code isTemplate} whether this repository acts as a template that can be used to generate new repositories
         **/
        private final boolean isTemplate;
        /**
         * {@code topics} topics list
         **/
        private final ArrayList<String> topics;
        /**
         * {@code hasIssues} whether issues are enabled
         **/
        private final boolean hasIssues;
        /**
         * {@code hasProjects} whether projects are enabled
         **/
        private final boolean hasProjects;
        /**
         * {@code hasWiki} whether the wiki is enabled
         **/
        private final boolean hasWiki;
        /**
         * {@code hasPages} whether the repository has pages
         **/
        private final boolean hasPages;
        /**
         * {@code hasDownloads} whether downloads are enabled
         **/
        private final boolean hasDownloads;
        /**
         * {@code archived} whether the repository is archived
         **/
        private final boolean archived;
        /**
         * {@code disabled} returns whether this repository disabled
         **/
        private final boolean disabled;
        /**
         * {@code visibility} the repository visibility: public, private, or internal
         **/
        private final RepoVisibility visibility;
        /**
         * {@code pushedAt} pushed at value
         **/
        private final String pushedAt;
        /**
         * {@code createdAt} created at value
         **/
        private final String createdAt;
        /**
         * {@code updatedAt} updated at value
         **/
        private final String updatedAt;
        /**
         * {@code permissions} repository permissions
         **/
        private final Permissions permissions;
        /**
         * {@code allowRebaseMerge} whether to allow rebase merges for pull requests
         **/
        private final boolean allowRebaseMerge;
        /**
         * {@code tempCloneToken} temp clone token value
         **/
        private final String tempCloneToken;
        /**
         * {@code allowSquashMerge} whether to allow squash merges for pull requests
         **/
        private final boolean allowSquashMerge;
        /**
         * {@code allowAutoMerge} whether to allow {@code "Auto-merge"} to be used on pull requests
         **/
        private final boolean allowAutoMerge;
        /**
         * {@code deleteBranchOnMerge} whether to delete head branches when pull requests are merged
         **/
        private final boolean deleteBranchOnMerge;
        /**
         * {@code allowMergeCommit} whether a pull request head branch that is behind its base branch can always
         * be updated even if it is not required to be up-to-date before merging
         **/
        private final boolean allowMergeCommit;
        /**
         * {@code subscribersCount} subscribers count value
         **/
        private final int subscribersCount;
        /**
         * {@code networkCount} network count value
         **/
        private final int networkCount;
        /**
         * {@code license} license value
         **/
        private final License license;
        /**
         * {@code forks} forks value
         **/
        private final int forks;
        /**
         * {@code openIssues} open issues value
         **/
        private final int openIssues;
        /**
         * {@code watchers} watchers value
         **/
        private final int watchers;

        /**
         * Constructor to init a {@link Repository}
         *
         * @param id:                         identifier value
         * @param nodeId:                     identifier of the node value
         * @param name:                       the name of the repository
         * @param fullName:                   fullname value
         * @param owner:                      owner value
         * @param privateRepo:                whether the repository is private or public
         * @param htmlUrl:                    html url value
         * @param description:                description value
         * @param fork:                       fork value
         * @param url:                        url value
         * @param archiveUrl:                 archive url value
         * @param assigneesUrl:               assignees url value
         * @param blobsUrl:                   blobs url value
         * @param branchesUrl:                branches url value
         * @param collaboratorsUrl:           collaborators url value
         * @param commentsUrl:                comments url value
         * @param commitsUrl:                 commits url value
         * @param compareUrl:                 compare url value
         * @param contentsUrl:                contents url value
         * @param contributorsUrl:            contributors url value
         * @param deploymentsUrl:             deployments url value
         * @param downloadUrl:                download url value
         * @param eventsUrl:events            url value
         * @param forksUrl:                   forks url value
         * @param gitCommitsUrl:              git commits url value
         * @param gitRefsUrl:                 git refs url value
         * @param gitTagsUrl:                 git tags url value
         * @param gitUrl:                     git url value
         * @param issueCommentUrl:            issues comments url value
         * @param issueEventsUrl:             issues events url value
         * @param issuesUrl:                  issues url value
         * @param keysUrl:                    keys url value
         * @param labelsUrl:                  labels url value
         * @param languagesUrl:languages      url value
         * @param mergesUrl:                  merges url value
         * @param milestonesUrl:              milestones url value
         * @param notificationsUrl:           notifications url value
         * @param pullsUrl:                   pulls url value
         * @param releasesUrl:                releases url value
         * @param sshUrl:                     ssh url value
         * @param stargazersUrl:              stargazers url value
         * @param statutesUrl:                statuses url value
         * @param subscribersUrl:             subscribers url value
         * @param subscriptionUrl:            subscription url value
         * @param tagsUrl:                    tags url value
         * @param teamsUrl:                   teams url value
         * @param treesUrl:                   trees url value
         * @param cloneUrl:                   clone url value
         * @param mirrorUrl:                  mirror url value
         * @param hooksUrl:                   hooks url value
         * @param svnUrl:                     svn url value
         * @param homePage:                   homepage value
         * @param forksCount:                 forks count value
         * @param stargazersCount:            stargazers count value
         * @param watchersCount:              watchers count value
         * @param size:                       the size of the repository. Size is calculated hourly. When a repository is initially created, the size is 0
         * @param defaultBranch:              the default branch of the repository
         * @param openIssuesCount:            open issues count value
         * @param isTemplate:                 whether this repository acts as a template that can be used to generate new repositories
         * @param topics:                     topics list
         * @param hasIssues:                  whether issues are enabled
         * @param hasProjects:                whether projects are enabled
         * @param hasWiki:                    whether the wiki is enabled
         * @param hasPages:                   whether the repository has pages
         * @param hasDownloads:               whether downloads are enabled
         * @param archived:                   whether the repository is archived
         * @param disabled:                   returns whether this repository disabled
         * @param visibility:                 the repository visibility: public, private, or internal
         * @param pushedAt:                   pushed at value
         * @param createdAt:                  created at value
         * @param updatedAt:                  updated at value
         * @param permissions:                repository permissions
         * @param allowRebaseMerge:           whether to allow rebase merges for pull requests
         * @param tempCloneToken:             temp clone token value
         * @param allowSquashMerge:           whether to allow squash merges for pull requests
         * @param allowAutoMerge:             whether to allow {@code "Auto-merge"} to be used on pull requests
         * @param deleteBranchOnMerge:whether to delete head branches when pull requests are merged
         * @param allowMergeCommit:           whether a pull request head branch that is behind its base branch can always
         *                                    be updated even if it is not required to be up-to-date before merging
         * @param subscribersCount:           subscribers count value
         * @param networkCount:               network count value
         * @param license:                    license value
         * @param forks:                      forks value
         * @param openIssues:                 open issues value
         * @param watchers:                   watchers value
         **/
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
                          boolean disabled, RepoVisibility visibility, String pushedAt, String createdAt, String updatedAt,
                          Permissions permissions, boolean allowRebaseMerge, String tempCloneToken, boolean allowSquashMerge,
                          boolean allowAutoMerge, boolean deleteBranchOnMerge, boolean allowMergeCommit, int subscribersCount,
                          int networkCount, License license, int forks, int openIssues, int watchers) {
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

        /**
         * Constructor to init a {@link Repository}
         *
         * @param jRepository: repository details as {@link JSONObject}
         **/
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
            visibility = RepoVisibility.valueOf(hRepository.getString("visibility", vPrivate.name()));
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

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
        }

        /**
         * Method to get {@link #nodeId} instance <br>
         * Any params required
         *
         * @return {@link #nodeId} instance as {@link String}
         **/
        public String getNodeId() {
            return nodeId;
        }

        /**
         * Method to get {@link #name} instance <br>
         * Any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #fullName} instance <br>
         * Any params required
         *
         * @return {@link #fullName} instance as {@link String}
         **/
        public String getFullName() {
            return fullName;
        }

        /**
         * Method to get {@link #owner} instance <br>
         * Any params required
         *
         * @return {@link #owner} instance as {@link Owner}
         **/
        public Owner getOwner() {
            return owner;
        }

        /**
         * Method to get {@link #privateRepo} instance <br>
         * Any params required
         *
         * @return {@link #privateRepo} instance as boolean
         **/
        public boolean isPrivateRepo() {
            return privateRepo;
        }

        /**
         * Method to get {@link #htmlUrl} instance <br>
         * Any params required
         *
         * @return {@link #htmlUrl} instance as {@link String}
         **/
        public String getHtmlUrl() {
            return htmlUrl;
        }

        /**
         * Method to get {@link #description} instance <br>
         * Any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #fork} instance <br>
         * Any params required
         *
         * @return {@link #fork} instance as boolean
         **/
        public boolean isFork() {
            return fork;
        }

        /**
         * Method to get {@link #url} instance <br>
         * Any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
        }

        /**
         * Method to get {@link #archiveUrl} instance <br>
         * Any params required
         *
         * @return {@link #archiveUrl} instance as {@link String}
         **/
        public String getArchiveUrl() {
            return archiveUrl;
        }

        /**
         * Method to get {@link #assigneesUrl} instance <br>
         * Any params required
         *
         * @return {@link #assigneesUrl} instance as {@link String}
         **/
        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        /**
         * Method to get {@link #blobsUrl} instance <br>
         * Any params required
         *
         * @return {@link #blobsUrl} instance as {@link String}
         **/
        public String getBlobsUrl() {
            return blobsUrl;
        }

        /**
         * Method to get {@link #branchesUrl} instance <br>
         * Any params required
         *
         * @return {@link #branchesUrl} instance as {@link String}
         **/
        public String getBranchesUrl() {
            return branchesUrl;
        }

        /**
         * Method to get {@link #collaboratorsUrl} instance <br>
         * Any params required
         *
         * @return {@link #collaboratorsUrl} instance as {@link String}
         **/
        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        /**
         * Method to get {@link #commentsUrl} instance <br>
         * Any params required
         *
         * @return {@link #commentsUrl} instance as {@link String}
         **/
        public String getCommentsUrl() {
            return commentsUrl;
        }

        /**
         * Method to get {@link #commitsUrl} instance <br>
         * Any params required
         *
         * @return {@link #commitsUrl} instance as {@link String}
         **/
        public String getCommitsUrl() {
            return commitsUrl;
        }

        /**
         * Method to get {@link #compareUrl} instance <br>
         * Any params required
         *
         * @return {@link #compareUrl} instance as {@link String}
         **/
        public String getCompareUrl() {
            return compareUrl;
        }

        /**
         * Method to get {@link #contentsUrl} instance <br>
         * Any params required
         *
         * @return {@link #contentsUrl} instance as {@link String}
         **/
        public String getContentsUrl() {
            return contentsUrl;
        }

        /**
         * Method to get {@link #contributorsUrl} instance <br>
         * Any params required
         *
         * @return {@link #contributorsUrl} instance as {@link String}
         **/
        public String getContributorsUrl() {
            return contributorsUrl;
        }

        /**
         * Method to get {@link #deploymentsUrl} instance <br>
         * Any params required
         *
         * @return {@link #deploymentsUrl} instance as {@link String}
         **/
        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        /**
         * Method to get {@link #downloadUrl} instance <br>
         * Any params required
         *
         * @return {@link #downloadUrl} instance as {@link String}
         **/
        public String getDownloadUrl() {
            return downloadUrl;
        }

        /**
         * Method to get {@link #eventsUrl} instance <br>
         * Any params required
         *
         * @return {@link #eventsUrl} instance as {@link String}
         **/
        public String getEventsUrl() {
            return eventsUrl;
        }

        /**
         * Method to get {@link #forksUrl} instance <br>
         * Any params required
         *
         * @return {@link #forksUrl} instance as {@link String}
         **/
        public String getForksUrl() {
            return forksUrl;
        }

        /**
         * Method to get {@link #gitCommitsUrl} instance <br>
         * Any params required
         *
         * @return {@link #gitCommitsUrl} instance as {@link String}
         **/
        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        /**
         * Method to get {@link #gitRefsUrl} instance <br>
         * Any params required
         *
         * @return {@link #gitRefsUrl} instance as {@link String}
         **/
        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        /**
         * Method to get {@link #gitTagsUrl} instance <br>
         * Any params required
         *
         * @return {@link #gitTagsUrl} instance as {@link String}
         **/
        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        /**
         * Method to get {@link #gitUrl} instance <br>
         * Any params required
         *
         * @return {@link #gitUrl} instance as {@link String}
         **/
        public String getGitUrl() {
            return gitUrl;
        }

        /**
         * Method to get {@link #issueCommentUrl} instance <br>
         * Any params required
         *
         * @return {@link #issueCommentUrl} instance as {@link String}
         **/
        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        /**
         * Method to get {@link #issueEventsUrl} instance <br>
         * Any params required
         *
         * @return {@link #issueEventsUrl} instance as {@link String}
         **/
        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        /**
         * Method to get {@link #issuesUrl} instance <br>
         * Any params required
         *
         * @return {@link #issuesUrl} instance as {@link String}
         **/
        public String getIssuesUrl() {
            return issuesUrl;
        }

        /**
         * Method to get {@link #keysUrl} instance <br>
         * Any params required
         *
         * @return {@link #keysUrl} instance as {@link String}
         **/
        public String getKeysUrl() {
            return keysUrl;
        }

        /**
         * Method to get {@link #labelsUrl} instance <br>
         * Any params required
         *
         * @return {@link #labelsUrl} instance as {@link String}
         **/
        public String getLabelsUrl() {
            return labelsUrl;
        }

        /**
         * Method to get {@link #languagesUrl} instance <br>
         * Any params required
         *
         * @return {@link #languagesUrl} instance as {@link String}
         **/
        public String getLanguagesUrl() {
            return languagesUrl;
        }

        /**
         * Method to get {@link #mergesUrl} instance <br>
         * Any params required
         *
         * @return {@link #mergesUrl} instance as {@link String}
         **/
        public String getMergesUrl() {
            return mergesUrl;
        }

        /**
         * Method to get {@link #milestonesUrl} instance <br>
         * Any params required
         *
         * @return {@link #milestonesUrl} instance as {@link String}
         **/
        public String getMilestonesUrl() {
            return milestonesUrl;
        }

        /**
         * Method to get {@link #notificationsUrl} instance <br>
         * Any params required
         *
         * @return {@link #notificationsUrl} instance as {@link String}
         **/
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        /**
         * Method to get {@link #pullsUrl} instance <br>
         * Any params required
         *
         * @return {@link #pullsUrl} instance as {@link String}
         **/
        public String getPullsUrl() {
            return pullsUrl;
        }

        /**
         * Method to get {@link #releasesUrl} instance <br>
         * Any params required
         *
         * @return {@link #releasesUrl} instance as {@link String}
         **/
        public String getReleasesUrl() {
            return releasesUrl;
        }

        /**
         * Method to get {@link #sshUrl} instance <br>
         * Any params required
         *
         * @return {@link #sshUrl} instance as {@link String}
         **/
        public String getSshUrl() {
            return sshUrl;
        }

        /**
         * Method to get {@link #stargazersUrl} instance <br>
         * Any params required
         *
         * @return {@link #stargazersUrl} instance as {@link String}
         **/
        public String getStargazersUrl() {
            return stargazersUrl;
        }

        /**
         * Method to get {@link #statutesUrl} instance <br>
         * Any params required
         *
         * @return {@link #statutesUrl} instance as {@link String}
         **/
        public String getStatutesUrl() {
            return statutesUrl;
        }

        /**
         * Method to get {@link #subscribersUrl} instance <br>
         * Any params required
         *
         * @return {@link #subscribersUrl} instance as {@link String}
         **/
        public String getSubscribersUrl() {
            return subscribersUrl;
        }

        /**
         * Method to get {@link #subscriptionUrl} instance <br>
         * Any params required
         *
         * @return {@link #subscriptionUrl} instance as {@link String}
         **/
        public String getSubscriptionUrl() {
            return subscriptionUrl;
        }

        /**
         * Method to get {@link #tagsUrl} instance <br>
         * Any params required
         *
         * @return {@link #tagsUrl} instance as {@link String}
         **/
        public String getTagsUrl() {
            return tagsUrl;
        }

        /**
         * Method to get {@link #teamsUrl} instance <br>
         * Any params required
         *
         * @return {@link #teamsUrl} instance as {@link String}
         **/
        public String getTeamsUrl() {
            return teamsUrl;
        }

        /**
         * Method to get {@link #treesUrl} instance <br>
         * Any params required
         *
         * @return {@link #treesUrl} instance as {@link String}
         **/
        public String getTreesUrl() {
            return treesUrl;
        }

        /**
         * Method to get {@link #cloneUrl} instance <br>
         * Any params required
         *
         * @return {@link #cloneUrl} instance as {@link String}
         **/
        public String getCloneUrl() {
            return cloneUrl;
        }

        /**
         * Method to get {@link #mirrorUrl} instance <br>
         * Any params required
         *
         * @return {@link #mirrorUrl} instance as {@link String}
         **/
        public String getMirrorUrl() {
            return mirrorUrl;
        }

        /**
         * Method to get {@link #hooksUrl} instance <br>
         * Any params required
         *
         * @return {@link #hooksUrl} instance as {@link String}
         **/
        public String getHooksUrl() {
            return hooksUrl;
        }

        /**
         * Method to get {@link #svnUrl} instance <br>
         * Any params required
         *
         * @return {@link #svnUrl} instance as {@link String}
         **/
        public String getSvnUrl() {
            return svnUrl;
        }

        /**
         * Method to get {@link #homePage} instance <br>
         * Any params required
         *
         * @return {@link #homePage} instance as {@link String}
         **/
        public String getHomePage() {
            return homePage;
        }

        /**
         * Method to get {@link #forksCount} instance <br>
         * Any params required
         *
         * @return {@link #forksCount} instance as int
         **/
        public int getForksCount() {
            return forksCount;
        }

        /**
         * Method to get {@link #stargazersCount} instance <br>
         * Any params required
         *
         * @return {@link #stargazersCount} instance as int
         **/
        public int getStargazersCount() {
            return stargazersCount;
        }

        /**
         * Method to get {@link #watchersCount} instance <br>
         * Any params required
         *
         * @return {@link #watchersCount} instance as int
         **/
        public int getWatchersCount() {
            return watchersCount;
        }

        /**
         * Method to get {@link #size} instance <br>
         * Any params required
         *
         * @return {@link #size} instance as int
         **/
        public int getSize() {
            return size;
        }

        /**
         * Method to get {@link #defaultBranch} instance <br>
         * Any params required
         *
         * @return {@link #defaultBranch} instance as {@link String}
         **/
        public String getDefaultBranch() {
            return defaultBranch;
        }

        /**
         * Method to get {@link #openIssuesCount} instance <br>
         * Any params required
         *
         * @return {@link #openIssuesCount} instance as int
         **/
        public int getOpenIssuesCount() {
            return openIssuesCount;
        }

        /**
         * Method to get {@link #isTemplate} instance <br>
         * Any params required
         *
         * @return {@link #isTemplate} instance as boolean
         **/
        public boolean isTemplate() {
            return isTemplate;
        }

        /**
         * Method to get {@link #topics} instance <br>
         * Any params required
         *
         * @return {@link #topics} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getTopics() {
            return topics;
        }

        /**
         * Method to get {@link #hasIssues} instance <br>
         * Any params required
         *
         * @return {@link #hasIssues} instance as boolean
         **/
        public boolean isHasIssues() {
            return hasIssues;
        }

        /**
         * Method to get {@link #hasProjects} instance <br>
         * Any params required
         *
         * @return {@link #hasProjects} instance as boolean
         **/
        public boolean isHasProjects() {
            return hasProjects;
        }

        /**
         * Method to get {@link #hasWiki} instance <br>
         * Any params required
         *
         * @return {@link #hasWiki} instance as boolean
         **/
        public boolean isHasWiki() {
            return hasWiki;
        }

        /**
         * Method to get {@link #hasPages} instance <br>
         * Any params required
         *
         * @return {@link #hasPages} instance as boolean
         **/
        public boolean isHasPages() {
            return hasPages;
        }

        /**
         * Method to get {@link #hasDownloads} instance <br>
         * Any params required
         *
         * @return {@link #hasDownloads} instance as boolean
         **/
        public boolean isHasDownloads() {
            return hasDownloads;
        }

        /**
         * Method to get {@link #archived} instance <br>
         * Any params required
         *
         * @return {@link #archived} instance as boolean
         **/
        public boolean isArchived() {
            return archived;
        }

        /**
         * Method to get {@link #disabled} instance <br>
         * Any params required
         *
         * @return {@link #disabled} instance as boolean
         **/
        public boolean isDisabled() {
            return disabled;
        }

        /**
         * Method to get {@link #visibility} instance <br>
         * Any params required
         *
         * @return {@link #visibility} instance as {@link RepoVisibility}
         **/
        public RepoVisibility getVisibility() {
            return visibility;
        }

        /**
         * Method to get {@link #pushedAt} instance <br>
         * Any params required
         *
         * @return {@link #pushedAt} instance as {@link String}
         **/
        public String getPushedAt() {
            return pushedAt;
        }

        /**
         * Method to get {@link #createdAt} instance <br>
         * Any params required
         *
         * @return {@link #createdAt} instance as {@link String}
         **/
        public String getCreatedAt() {
            return createdAt;
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

        /**
         * Method to get {@link #updatedAt} instance <br>
         * Any params required
         *
         * @return {@link #updatedAt} instance as {@link String}
         **/
        public String getUpdatedAt() {
            return updatedAt;
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

        /**
         * Method to get {@link #allowRebaseMerge} instance <br>
         * Any params required
         *
         * @return {@link #allowRebaseMerge} instance as boolean
         **/
        public boolean isAllowRebaseMerge() {
            return allowRebaseMerge;
        }

        /**
         * Method to get {@link #tempCloneToken} instance <br>
         * Any params required
         *
         * @return {@link #tempCloneToken} instance as {@link String}
         **/
        public String getTempCloneToken() {
            return tempCloneToken;
        }

        /**
         * Method to get {@link #allowSquashMerge} instance <br>
         * Any params required
         *
         * @return {@link #allowSquashMerge} instance as boolean
         **/
        public boolean isAllowSquashMerge() {
            return allowSquashMerge;
        }

        /**
         * Method to get {@link #allowAutoMerge} instance <br>
         * Any params required
         *
         * @return {@link #allowAutoMerge} instance as boolean
         **/
        public boolean isAllowAutoMerge() {
            return allowAutoMerge;
        }

        /**
         * Method to get {@link #deleteBranchOnMerge} instance <br>
         * Any params required
         *
         * @return {@link #deleteBranchOnMerge} instance as boolean
         **/
        public boolean isDeleteBranchOnMerge() {
            return deleteBranchOnMerge;
        }

        /**
         * Method to get {@link #allowMergeCommit} instance <br>
         * Any params required
         *
         * @return {@link #allowMergeCommit} instance as boolean
         **/
        public boolean isAllowMergeCommit() {
            return allowMergeCommit;
        }

        /**
         * Method to get {@link #subscribersCount} instance <br>
         * Any params required
         *
         * @return {@link #subscribersCount} instance as int
         **/
        public int getSubscribersCount() {
            return subscribersCount;
        }

        /**
         * Method to get {@link #networkCount} instance <br>
         * Any params required
         *
         * @return {@link #networkCount} instance as int
         **/
        public int getNetworkCount() {
            return networkCount;
        }

        /**
         * Method to get {@link #license} instance <br>
         * Any params required
         *
         * @return {@link #license} instance as {@link License}
         **/
        public License getLicense() {
            return license;
        }

        /**
         * Method to get {@link #forks} instance <br>
         * Any params required
         *
         * @return {@link #forks} instance as int
         **/
        public int getForks() {
            return forks;
        }

        /**
         * Method to get {@link #openIssues} instance <br>
         * Any params required
         *
         * @return {@link #openIssues} instance as int
         **/
        public int getOpenIssues() {
            return openIssues;
        }

        /**
         * Method to get {@link #watchers} instance <br>
         * Any params required
         *
         * @return {@link #watchers} instance as int
         **/
        public int getWatchers() {
            return watchers;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        /**
         * {@code RepoVisibility} list of available visibilities for a repository
         **/
        public enum RepoVisibility {

            /**
             * {@code "public"} visibility
             **/
            vPublic("public"),

            /**
             * {@code "private"} visibility
             **/
            vPrivate("private"),

            /**
             * {@code "internal"} visibility
             **/
            vInternal("internal");

            /**
             * {@code "visibility"} value
             **/
            private final String visibility;

            /**
             * Constructor to init a {@link RepoVisibility}
             *
             * @param visibility : {@code "visibility"} value
             **/
            RepoVisibility(String visibility) {
                this.visibility = visibility;
            }

            /**
             * Method to get {@link #visibility} instance <br>
             * Any params required
             *
             * @return {@link #visibility} instance as {@link String}
             **/
            @Override
            public String toString() {
                return visibility;
            }

        }

        /**
         * The {@code Owner} class is useful to format a GitHub's owner
         *
         * @author N7ghtm4r3 - Tecknobit
         * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
         * List selected repositories enabled for GitHub Actions in an organization</a>
         **/
        public static class Owner {

            /**
             * {@code login} login value
             **/
            private final String login;

            /**
             * {@code id} identifier value
             **/
            private final long id;

            /**
             * {@code nodeId} identifier of the node value
             **/
            private final String nodeId;

            /**
             * {@code avatarUrl} avatar url value
             **/
            private final String avatarUrl;

            /**
             * {@code gravatarUrl} gravatar url value
             **/
            private final String gravatarUrl;

            /**
             * {@code "url"} value
             **/
            private final String url;

            /**
             * {@code htmlUrl} html url value
             **/
            private final String htmlUrl;

            /**
             * {@code followersUrl} followers url value
             **/
            private final String followersUrl;

            /**
             * {@code followingUrl} following url value
             **/
            private final String followingUrl;

            /**
             * {@code gistsUrl} gists url value
             **/
            private final String gistsUrl;

            /**
             * {@code starredUrl} starred url value
             **/
            private final String starredUrl;

            /**
             * {@code subscriptionsUrl} subscriptions url value
             **/
            private final String subscriptionsUrl;

            /**
             * {@code organizationsUrl} organizations url value
             **/
            private final String organizationsUrl;

            /**
             * {@code reposUrl} repos url value
             **/
            private final String reposUrl;

            /**
             * {@code eventsUrl} events url value
             **/
            private final String eventsUrl;

            /**
             * {@code receivedEventsUrl} received events url value
             **/
            private final String receivedEventsUrl;

            /**
             * {@code type} type value
             **/
            private final String type;

            /**
             * {@code siteAdmin} site admin value
             **/
            private final boolean siteAdmin;

            /**
             * Constructor to init a {@link Owner}
             *
             * @param login:             login value
             * @param id:                identifier value
             * @param nodeId:            identifier of the node value
             * @param avatarUrl:         avatar url value
             * @param gravatarUrl:       gravatar url value
             * @param url:               url value
             * @param htmlUrl:           html url value
             * @param followersUrl:      followers url value
             * @param followingUrl:      following url value
             * @param gistsUrl:          gists url value
             * @param starredUrl:        starred url value
             * @param subscriptionsUrl:  subscriptions url value
             * @param organizationsUrl:  organizations url value
             * @param reposUrl:          repos url value
             * @param eventsUrl:         events url value
             * @param receivedEventsUrl: received events url value
             * @param type:              type value
             * @param siteAdmin:         site admin value
             **/
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

            /**
             * Constructor to init a {@link Owner}
             *
             * @param jOwner: owner details as {@link JSONObject}
             **/
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

            /**
             * Method to get {@link #login} instance <br>
             * Any params required
             *
             * @return {@link #login} instance as {@link License}
             **/
            public String getLogin() {
                return login;
            }

            /**
             * Method to get {@link #id} instance <br>
             * Any params required
             *
             * @return {@link #id} instance as long
             **/
            public long getId() {
                return id;
            }

            /**
             * Method to get {@link #nodeId} instance <br>
             * Any params required
             *
             * @return {@link #nodeId} instance as {@link String}
             **/
            public String getNodeId() {
                return nodeId;
            }

            /**
             * Method to get {@link #avatarUrl} instance <br>
             * Any params required
             *
             * @return {@link #avatarUrl} instance as {@link String}
             **/
            public String getAvatarUrl() {
                return avatarUrl;
            }

            /**
             * Method to get {@link #gravatarUrl} instance <br>
             * Any params required
             *
             * @return {@link #gravatarUrl} instance as {@link String}
             **/
            public String getGravatarUrl() {
                return gravatarUrl;
            }

            /**
             * Method to get {@link #url} instance <br>
             * Any params required
             *
             * @return {@link #url} instance as {@link String}
             **/
            public String getUrl() {
                return url;
            }

            /**
             * Method to get {@link #htmlUrl} instance <br>
             * Any params required
             *
             * @return {@link #htmlUrl} instance as {@link String}
             **/
            public String getHtmlUrl() {
                return htmlUrl;
            }

            /**
             * Method to get {@link #followersUrl} instance <br>
             * Any params required
             *
             * @return {@link #followersUrl} instance as {@link String}
             **/
            public String getFollowersUrl() {
                return followersUrl;
            }

            /**
             * Method to get {@link #followingUrl} instance <br>
             * Any params required
             *
             * @return {@link #followingUrl} instance as {@link String}
             **/
            public String getFollowingUrl() {
                return followingUrl;
            }

            /**
             * Method to get {@link #gistsUrl} instance <br>
             * Any params required
             *
             * @return {@link #gistsUrl} instance as {@link String}
             **/
            public String getGistsUrl() {
                return gistsUrl;
            }

            /**
             * Method to get {@link #starredUrl} instance <br>
             * Any params required
             *
             * @return {@link #starredUrl} instance as {@link String}
             **/
            public String getStarredUrl() {
                return starredUrl;
            }

            /**
             * Method to get {@link #subscriptionsUrl} instance <br>
             * Any params required
             *
             * @return {@link #subscriptionsUrl} instance as {@link String}
             **/
            public String getSubscriptionsUrl() {
                return subscriptionsUrl;
            }

            /**
             * Method to get {@link #organizationsUrl} instance <br>
             * Any params required
             *
             * @return {@link #organizationsUrl} instance as {@link String}
             **/
            public String getOrganizationsUrl() {
                return organizationsUrl;
            }

            /**
             * Method to get {@link #reposUrl} instance <br>
             * Any params required
             *
             * @return {@link #reposUrl} instance as {@link String}
             **/
            public String getReposUrl() {
                return reposUrl;
            }

            /**
             * Method to get {@link #eventsUrl} instance <br>
             * Any params required
             *
             * @return {@link #eventsUrl} instance as {@link String}
             **/
            public String getEventsUrl() {
                return eventsUrl;
            }

            /**
             * Method to get {@link #receivedEventsUrl} instance <br>
             * Any params required
             *
             * @return {@link #receivedEventsUrl} instance as {@link String}
             **/
            public String getReceivedEventsUrl() {
                return receivedEventsUrl;
            }

            /**
             * Method to get {@link #type} instance <br>
             * Any params required
             *
             * @return {@link #type} instance as {@link String}
             **/
            public String getType() {
                return type;
            }

            /**
             * Method to get {@link #siteAdmin} instance <br>
             * Any params required
             *
             * @return {@link #siteAdmin} instance as boolean
             **/
            public boolean isSiteAdmin() {
                return siteAdmin;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

        /**
         * The {@code Permissions} class is useful to format a GitHub's permissions for a repository
         *
         * @author N7ghtm4r3 - Tecknobit
         * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
         * List selected repositories enabled for GitHub Actions in an organization</a>
         **/
        public static class Permissions {

            /**
             * {@code "admin"} flag
             **/
            private final boolean admin;

            /**
             * {@code "push"} flag
             **/
            private final boolean push;

            /**
             * {@code "pull"} flag
             **/
            private final boolean pull;

            /**
             * Constructor to init a {@link Permissions}
             *
             * @param admin: admin flag
             * @param push:  push flag
             * @param pull:  pull flag
             **/
            public Permissions(boolean admin, boolean push, boolean pull) {
                this.admin = admin;
                this.push = push;
                this.pull = pull;
            }

            /**
             * Constructor to init a {@link Permissions}
             *
             * @param jPermissions: permissions details as {@link JSONObject}
             **/
            public Permissions(JSONObject jPermissions) {
                JsonHelper hRepoPermissions = new JsonHelper(jPermissions);
                admin = hRepoPermissions.getBoolean("admin");
                push = hRepoPermissions.getBoolean("push");
                pull = hRepoPermissions.getBoolean("pull");
            }

            /**
             * Method to get {@link #admin} instance <br>
             * Any params required
             *
             * @return {@link #admin} instance as boolean
             **/
            public boolean isAdmin() {
                return admin;
            }

            /**
             * Method to get {@link #push} instance <br>
             * Any params required
             *
             * @return {@link #push} instance as boolean
             **/
            public boolean isPush() {
                return push;
            }

            /**
             * Method to get {@link #pull} instance <br>
             * Any params required
             *
             * @return {@link #pull} instance as boolean
             **/
            public boolean isPull() {
                return pull;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

        /**
         * The {@code License} class is useful to format a GitHub's license for a repository
         *
         * @author N7ghtm4r3 - Tecknobit
         * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
         * List selected repositories enabled for GitHub Actions in an organization</a>
         **/
        public static class License {

            /**
             * {@code "key"} value
             **/
            private final String key;

            /**
             * {@code "name"} value
             **/
            private final String name;

            /**
             * {@code "url"} value
             **/
            private final String url;

            /**
             * {@code "spdxId"} value
             **/
            private final String spdxId;

            /**
             * {@code "nodeId"} value
             **/
            private final String nodeId;

            /**
             * {@code "htmlUrl"} value
             **/
            private final String htmlUrl;

            /**
             * Constructor to init a {@link License}
             *
             * @param key:     key value
             * @param name:    name value
             * @param url:     url value
             * @param spdxId:  spdx identifier value
             * @param nodeId:  node identifier value
             * @param htmlUrl: html url value
             **/
            public License(String key, String name, String url, String spdxId, String nodeId, String htmlUrl) {
                this.key = key;
                this.name = name;
                this.url = url;
                this.spdxId = spdxId;
                this.nodeId = nodeId;
                this.htmlUrl = htmlUrl;
            }

            /**
             * Constructor to init a {@link License}
             *
             * @param jLicense: license details as {@link JSONObject}
             **/
            public License(JSONObject jLicense) {
                JsonHelper hLicense = new JsonHelper(jLicense);
                key = hLicense.getString("key");
                name = hLicense.getString("name");
                url = hLicense.getString("url");
                spdxId = hLicense.getString("spdx_id");
                nodeId = hLicense.getString("node_id");
                htmlUrl = hLicense.getString("html_url");
            }

            /**
             * Method to get {@link #key} instance <br>
             * Any params required
             *
             * @return {@link #key} instance as {@link String}
             **/
            public String getKey() {
                return key;
            }

            /**
             * Method to get {@link #name} instance <br>
             * Any params required
             *
             * @return {@link #name} instance as {@link String}
             **/
            public String getName() {
                return name;
            }

            /**
             * Method to get {@link #url} instance <br>
             * Any params required
             *
             * @return {@link #url} instance as {@link String}
             **/
            public String getUrl() {
                return url;
            }

            /**
             * Method to get {@link #spdxId} instance <br>
             * Any params required
             *
             * @return {@link #spdxId} instance as {@link String}
             **/
            public String getSpdxId() {
                return spdxId;
            }

            /**
             * Method to get {@link #nodeId} instance <br>
             * Any params required
             *
             * @return {@link #nodeId} instance as {@link String}
             **/
            public String getNodeId() {
                return nodeId;
            }

            /**
             * Method to get {@link #htmlUrl} instance <br>
             * Any params required
             *
             * @return {@link #htmlUrl} instance as {@link String}
             **/
            public String getHtmlUrl() {
                return htmlUrl;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

    }

}
