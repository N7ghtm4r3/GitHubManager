package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;
import static com.tecknobit.githubmanager.records.repository.OrganizationRepositoriesList.CompletedRepository.RepoVisibility.*;

/**
 * The {@code RepositoriesList} class is useful to format a GitHub's repositories list
 *
 * @author N7ghtm4r3 - Tecknobit
 * /**
 * The {@code OrganizationsList} class is useful to format a GitHub's organizations list
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
 *             List selected repositories enabled for GitHub Actions in an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
 *             List repository access to a self-hosted runner group in an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class OrganizationRepositoriesList extends GitHubList {

    /**
     * {@code organizations} repositories list
     **/
    private final ArrayList<CompletedRepository> repositories;

    /**
     * Constructor to init an {@link OrganizationRepositoriesList}
     *
     * @param repositories: repositories list
     **/
    public OrganizationRepositoriesList(ArrayList<CompletedRepository> repositories) {
        super(repositories.size());
        this.repositories = repositories;
    }

    /**
     * Constructor to init an {@link OrganizationRepositoriesList}
     *
     * @param totalCount    : total number of the repositories in the list
     * @param repositories: repositories list
     **/
    public OrganizationRepositoriesList(int totalCount, ArrayList<CompletedRepository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link OrganizationRepositoriesList}
     *
     * @param jOrganizationRepositoriesList : organization repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public OrganizationRepositoriesList(JSONObject jOrganizationRepositoriesList) {
        super(jOrganizationRepositoriesList);
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        repositories = new ArrayList<>();
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new CompletedRepository(jRepositories.getJSONObject(j)));
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * Any params required
     *
     * @return {@link #repositories} instance as {@link Collection} of {@link CompletedRepository}
     **/
    public Collection<CompletedRepository> getRepositories() {
        return repositories;
    }

    /**
     * The {@code CompletedRepository} class is useful to format a GitHub's completed repository
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
     * @see GitHubResponse
     * @see BaseResponseDetails
     * @see Repository
     **/
    public static class CompletedRepository extends Repository {

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
         * {@code template} template that can be used to generate new repositories
         **/
        private final CompletedRepository template;

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
         * Constructor to init a {@link CompletedRepository}
         *
         * @param id                  :                         identifier value
         * @param nodeId              :                     identifier of the node value
         * @param name                :                       the name of the repository
         * @param fullName            :                   fullname value
         * @param owner               :                      owner value
         * @param privateRepo         :                whether the repository is private or public
         * @param htmlUrl             :                    html url value
         * @param description         :                description value
         * @param fork                :                       fork value
         * @param url                 :                        url value
         * @param archiveUrl          :                 archive url value
         * @param assigneesUrl        :               assignees url value
         * @param blobsUrl            :                   blobs url value
         * @param branchesUrl         :                branches url value
         * @param collaboratorsUrl    :           collaborators url value
         * @param commentsUrl         :                comments url value
         * @param commitsUrl          :                 commits url value
         * @param compareUrl          :                 compare url value
         * @param contentsUrl         :                contents url value
         * @param contributorsUrl     :            contributors url value
         * @param deploymentsUrl      :             deployments url value
         * @param downloadUrl         :                download url value
         * @param eventsUrl           :events            url value
         * @param forksUrl            :                   forks url value
         * @param gitCommitsUrl       :              git commits url value
         * @param gitRefsUrl          :                 git refs url value
         * @param gitTagsUrl          :                 git tags url value
         * @param gitUrl              :                     git url value
         * @param issueCommentUrl     :            issues comments url value
         * @param issueEventsUrl      :             issues events url value
         * @param issuesUrl           :                  issues url value
         * @param keysUrl             :                    keys url value
         * @param labelsUrl           :                  labels url value
         * @param languagesUrl        :languages      url value
         * @param mergesUrl           :                  merges url value
         * @param milestonesUrl       :              milestones url value
         * @param notificationsUrl    :           notifications url value
         * @param pullsUrl            :                   pulls url value
         * @param releasesUrl         :                releases url value
         * @param sshUrl              :                     ssh url value
         * @param stargazersUrl       :              stargazers url value
         * @param statutesUrl         :                statuses url value
         * @param subscribersUrl      :             subscribers url value
         * @param subscriptionUrl     :            subscription url value
         * @param tagsUrl             :                    tags url value
         * @param teamsUrl            :                   teams url value
         * @param treesUrl            :                   trees url value
         * @param cloneUrl            :                   clone url value
         * @param mirrorUrl           :                  mirror url value
         * @param hooksUrl            :                   hooks url value
         * @param svnUrl              :                     svn url value
         * @param homePage            :                   homepage value
         * @param forksCount          :                 forks count value
         * @param stargazersCount     :            stargazers count value
         * @param watchersCount       :              watchers count value
         * @param size                :                       the size of the repository. Size is calculated hourly. When a repository is initially created, the size is 0
         * @param defaultBranch       :              the default branch of the repository
         * @param openIssuesCount     :            open issues count value
         * @param isTemplate          :                 whether this repository acts as a template that can be used to generate new repositories
         * @param template:           template that can be used to generate new repositories
         * @param topics              :                     topics list
         * @param hasIssues           :                  whether issues are enabled
         * @param hasProjects         :                whether projects are enabled
         * @param hasWiki             :                    whether the wiki is enabled
         * @param hasPages            :                   whether the repository has pages
         * @param hasDownloads        :               whether downloads are enabled
         * @param archived            :                   whether the repository is archived
         * @param disabled            :                   returns whether this repository disabled
         * @param visibility          :                 the repository visibility: public, private, or internal
         * @param pushedAt            :                   pushed at value
         * @param createdAt           :                  created at value
         * @param updatedAt           :                  updated at value
         * @param permissions         :                repository permissions
         * @param allowRebaseMerge    :           whether to allow rebase merges for pull requests
         * @param tempCloneToken      :             temp clone token value
         * @param allowSquashMerge    :           whether to allow squash merges for pull requests
         * @param allowAutoMerge      :             whether to allow {@code "Auto-merge"} to be used on pull requests
         * @param deleteBranchOnMerge :whether to delete head branches when pull requests are merged
         * @param allowMergeCommit    :           whether a pull request head branch that is behind its base branch can always
         *                            be updated even if it is not required to be up-to-date before merging
         * @param subscribersCount    :           subscribers count value
         * @param networkCount        :               network count value
         * @param license             :                    license value
         * @param forks               :                      forks value
         * @param openIssues          :                 open issues value
         * @param watchers            :                   watchers value
         **/
        public CompletedRepository(long id, String nodeId, String name, String fullName, User owner, boolean privateRepo,
                                   String htmlUrl, String description, boolean fork, String url, String archiveUrl,
                                   String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl,
                                   String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl,
                                   String contributorsUrl, String deploymentsUrl, String downloadUrl, String eventsUrl,
                                   String forksUrl, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl,
                                   String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl,
                                   String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl,
                                   String milestonesUrl, String notificationsUrl, String pullsUrl, String releasesUrl,
                                   String sshUrl, String stargazersUrl, String statutesUrl, String subscribersUrl,
                                   String subscriptionUrl, String tagsUrl, String teamsUrl, String treesUrl,
                                   String cloneUrl, String mirrorUrl, String hooksUrl, String svnUrl, String homePage,
                                   int forksCount, int stargazersCount, int watchersCount, int size, String defaultBranch,
                                   int openIssuesCount, boolean isTemplate, CompletedRepository template,
                                   ArrayList<String> topics, boolean hasIssues, boolean hasProjects, boolean hasWiki,
                                   boolean hasPages, boolean hasDownloads, boolean archived, boolean disabled,
                                   RepoVisibility visibility, String pushedAt, String createdAt, String updatedAt,
                                   Permissions permissions, boolean allowRebaseMerge, String tempCloneToken,
                                   boolean allowSquashMerge, boolean allowAutoMerge, boolean deleteBranchOnMerge,
                                   boolean allowMergeCommit, int subscribersCount, int networkCount, License license,
                                   int forks, int openIssues, int watchers) {
            super(id, nodeId, name, fullName, owner, privateRepo, htmlUrl, description, fork, url, archiveUrl,
                    assigneesUrl, blobsUrl, branchesUrl, collaboratorsUrl, commentsUrl, commitsUrl, compareUrl, contentsUrl,
                    contributorsUrl, deploymentsUrl, downloadUrl, eventsUrl, forksUrl, gitCommitsUrl, gitRefsUrl,
                    gitTagsUrl, gitUrl, issueCommentUrl, issueEventsUrl, issuesUrl, keysUrl, labelsUrl, languagesUrl,
                    mergesUrl, milestonesUrl, notificationsUrl, pullsUrl, releasesUrl, sshUrl, stargazersUrl, statutesUrl,
                    subscribersUrl, subscriptionUrl, tagsUrl, teamsUrl, treesUrl, cloneUrl, mirrorUrl, hooksUrl);
            this.svnUrl = svnUrl;
            this.homePage = homePage;
            this.forksCount = forksCount;
            this.stargazersCount = stargazersCount;
            this.watchersCount = watchersCount;
            this.size = size;
            this.defaultBranch = defaultBranch;
            this.openIssuesCount = openIssuesCount;
            this.isTemplate = isTemplate;
            this.template = template;
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
         * Constructor to init a {@link CompletedRepository}
         *
         * @param jCompletedRepository: completed repository details as {@link JSONObject}
         **/
        public CompletedRepository(JSONObject jCompletedRepository) {
            super(jCompletedRepository);
            svnUrl = hResponse.getString("svn_url");
            homePage = hResponse.getString("homepage");
            forksCount = hResponse.getInt("forks_count", 0);
            stargazersCount = hResponse.getInt("stargazers_count", 0);
            watchersCount = hResponse.getInt("watchers_count", 0);
            size = hResponse.getInt("size", 0);
            defaultBranch = hResponse.getString("default_branch");
            openIssuesCount = hResponse.getInt("open_issues_count", 0);
            isTemplate = hResponse.getBoolean("is_template");
            if (isTemplate) {
                JSONObject jTemplate = hResponse.getJSONObject("template_repository");
                if (jTemplate != null)
                    template = new CompletedRepository(jTemplate);
                else
                    template = null;
            } else
                template = null;
            JSONArray jTopics = hResponse.getJSONArray("topics", new JSONArray());
            topics = new ArrayList<>();
            for (int j = 0; j < jTopics.length(); j++)
                topics.add(jTopics.getString(j));
            hasIssues = hResponse.getBoolean("has_issues");
            hasProjects = hResponse.getBoolean("has_projects");
            hasWiki = hResponse.getBoolean("has_wiki");
            hasPages = hResponse.getBoolean("has_pages");
            hasDownloads = hResponse.getBoolean("has_downloads");
            archived = hResponse.getBoolean("archived");
            disabled = hResponse.getBoolean("disabled");
            String visibilityKey = hResponse.getString("visibility", vPrivate.name());
            if (visibilityKey.equals(vPrivate.toString()))
                visibility = vPrivate;
            else if (visibilityKey.equals(vPublic.toString()))
                visibility = vPublic;
            else
                visibility = valueOf(visibilityKey);
            pushedAt = hResponse.getString("pushed_at");
            createdAt = hResponse.getString("created_at");
            updatedAt = hResponse.getString("updated_at");
            permissions = new Permissions(hResponse.getJSONObject("permissions", new JSONObject()));
            allowRebaseMerge = hResponse.getBoolean("allow_rebase_merge");
            tempCloneToken = hResponse.getString("temp_clone_token");
            allowSquashMerge = hResponse.getBoolean("allow_squash_merge");
            allowAutoMerge = hResponse.getBoolean("allow_auto_merge");
            deleteBranchOnMerge = hResponse.getBoolean("delete_branch_on_merge");
            allowMergeCommit = hResponse.getBoolean("allow_merge_commit");
            subscribersCount = hResponse.getInt("subscribers_count", 0);
            networkCount = hResponse.getInt("network_count", 0);
            license = new License(hResponse.getJSONObject("license", new JSONObject()));
            forks = hResponse.getInt("forks", 0);
            openIssues = hResponse.getInt("open_issues", 0);
            watchers = hResponse.getInt("watchers", 0);
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
         * Method to get {@link #template} instance <br>
         * Any params required
         *
         * @return {@link #template} instance as {@link CompletedRepository}
         **/
        public CompletedRepository getTemplate() {
            return template;
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
            internal("internal");

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
