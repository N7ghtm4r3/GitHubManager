package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.licenses.records.CommonLicense;
import com.tecknobit.githubmanager.records.generic.Permissions;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.records.repository.CompleteRepository.RepoVisibility.*;

/**
 * The {@code CompleteRepository} class is useful to format a GitHub's completed repository
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
public class CompleteRepository extends Repository {

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
    private final CompleteRepository template;

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
    private final CommonLicense license;

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
     * Constructor to init a {@link CompleteRepository}
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
    public CompleteRepository(long id, String nodeId, String name, String fullName, User owner, boolean privateRepo,
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
                              int openIssuesCount, boolean isTemplate, CompleteRepository template,
                              ArrayList<String> topics, boolean hasIssues, boolean hasProjects, boolean hasWiki,
                              boolean hasPages, boolean hasDownloads, boolean archived, boolean disabled,
                              RepoVisibility visibility, String pushedAt, String createdAt, String updatedAt,
                              Permissions permissions, boolean allowRebaseMerge, String tempCloneToken,
                              boolean allowSquashMerge, boolean allowAutoMerge, boolean deleteBranchOnMerge,
                              boolean allowMergeCommit, int subscribersCount, int networkCount, CommonLicense license,
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
     * Constructor to init a {@link CompleteRepository}
     *
     * @param jCompletedRepository: completed repository details as {@link JSONObject}
     **/
    public CompleteRepository(JSONObject jCompletedRepository) {
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
                template = new CompleteRepository(jTemplate);
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
        license = new CommonLicense(hResponse.getJSONObject("license", new JSONObject()));
        forks = hResponse.getInt("forks", 0);
        openIssues = hResponse.getInt("open_issues", 0);
        watchers = hResponse.getInt("watchers", 0);
    }

    /**
     * Method to get {@link #svnUrl} instance <br>
     * No-any params required
     *
     * @return {@link #svnUrl} instance as {@link String}
     **/
    public String getSvnUrl() {
        return svnUrl;
    }

    /**
     * Method to get {@link #homePage} instance <br>
     * No-any params required
     *
     * @return {@link #homePage} instance as {@link String}
     **/
    public String getHomePage() {
        return homePage;
    }

    /**
     * Method to get {@link #forksCount} instance <br>
     * No-any params required
     *
     * @return {@link #forksCount} instance as int
     **/
    public int getForksCount() {
        return forksCount;
    }

    /**
     * Method to get {@link #stargazersCount} instance <br>
     * No-any params required
     *
     * @return {@link #stargazersCount} instance as int
     **/
    public int getStargazersCount() {
        return stargazersCount;
    }

    /**
     * Method to get {@link #watchersCount} instance <br>
     * No-any params required
     *
     * @return {@link #watchersCount} instance as int
     **/
    public int getWatchersCount() {
        return watchersCount;
    }

    /**
     * Method to get {@link #size} instance <br>
     * No-any params required
     *
     * @return {@link #size} instance as int
     **/
    public int getSize() {
        return size;
    }

    /**
     * Method to get {@link #defaultBranch} instance <br>
     * No-any params required
     *
     * @return {@link #defaultBranch} instance as {@link String}
     **/
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * Method to get {@link #openIssuesCount} instance <br>
     * No-any params required
     *
     * @return {@link #openIssuesCount} instance as int
     **/
    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    /**
     * Method to get {@link #isTemplate} instance <br>
     * No-any params required
     *
     * @return {@link #isTemplate} instance as boolean
     **/
    public boolean isTemplate() {
        return isTemplate;
    }

    /**
     * Method to get {@link #template} instance <br>
     * No-any params required
     *
     * @return {@link #template} instance as {@link CompleteRepository}
     **/
    public CompleteRepository getTemplate() {
        return template;
    }

    /**
     * Method to get {@link #topics} instance <br>
     * No-any params required
     *
     * @return {@link #topics} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getTopics() {
        return topics;
    }

    /**
     * Method to get {@link #hasIssues} instance <br>
     * No-any params required
     *
     * @return {@link #hasIssues} instance as boolean
     **/
    public boolean isHasIssues() {
        return hasIssues;
    }

    /**
     * Method to get {@link #hasProjects} instance <br>
     * No-any params required
     *
     * @return {@link #hasProjects} instance as boolean
     **/
    public boolean isHasProjects() {
        return hasProjects;
    }

    /**
     * Method to get {@link #hasWiki} instance <br>
     * No-any params required
     *
     * @return {@link #hasWiki} instance as boolean
     **/
    public boolean isHasWiki() {
        return hasWiki;
    }

    /**
     * Method to get {@link #hasPages} instance <br>
     * No-any params required
     *
     * @return {@link #hasPages} instance as boolean
     **/
    public boolean isHasPages() {
        return hasPages;
    }

    /**
     * Method to get {@link #hasDownloads} instance <br>
     * No-any params required
     *
     * @return {@link #hasDownloads} instance as boolean
     **/
    public boolean isHasDownloads() {
        return hasDownloads;
    }

    /**
     * Method to get {@link #archived} instance <br>
     * No-any params required
     *
     * @return {@link #archived} instance as boolean
     **/
    public boolean isArchived() {
        return archived;
    }

    /**
     * Method to get {@link #disabled} instance <br>
     * No-any params required
     *
     * @return {@link #disabled} instance as boolean
     **/
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * No-any params required
     *
     * @return {@link #visibility} instance as {@link RepoVisibility}
     **/
    public RepoVisibility getVisibility() {
        return visibility;
    }

    /**
     * Method to get {@link #pushedAt} instance <br>
     * No-any params required
     *
     * @return {@link #pushedAt} instance as {@link String}
     **/
    public String getPushedAt() {
        return pushedAt;
    }

    /**
     * Method to get {@link #pushedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #pushedAt} timestamp as long
     **/
    public long getPushedAtTimestamp() {
        return getDateTimestamp(pushedAt);
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
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
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
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link Permissions}
     **/
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #allowRebaseMerge} instance <br>
     * No-any params required
     *
     * @return {@link #allowRebaseMerge} instance as boolean
     **/
    public boolean isAllowRebaseMerge() {
        return allowRebaseMerge;
    }

    /**
     * Method to get {@link #tempCloneToken} instance <br>
     * No-any params required
     *
     * @return {@link #tempCloneToken} instance as {@link String}
     **/
    public String getTempCloneToken() {
        return tempCloneToken;
    }

    /**
     * Method to get {@link #allowSquashMerge} instance <br>
     * No-any params required
     *
     * @return {@link #allowSquashMerge} instance as boolean
     **/
    public boolean isAllowSquashMerge() {
        return allowSquashMerge;
    }

    /**
     * Method to get {@link #allowAutoMerge} instance <br>
     * No-any params required
     *
     * @return {@link #allowAutoMerge} instance as boolean
     **/
    public boolean isAllowAutoMerge() {
        return allowAutoMerge;
    }

    /**
     * Method to get {@link #deleteBranchOnMerge} instance <br>
     * No-any params required
     *
     * @return {@link #deleteBranchOnMerge} instance as boolean
     **/
    public boolean isDeleteBranchOnMerge() {
        return deleteBranchOnMerge;
    }

    /**
     * Method to get {@link #allowMergeCommit} instance <br>
     * No-any params required
     *
     * @return {@link #allowMergeCommit} instance as boolean
     **/
    public boolean isAllowMergeCommit() {
        return allowMergeCommit;
    }

    /**
     * Method to get {@link #subscribersCount} instance <br>
     * No-any params required
     *
     * @return {@link #subscribersCount} instance as int
     **/
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * Method to get {@link #networkCount} instance <br>
     * No-any params required
     *
     * @return {@link #networkCount} instance as int
     **/
    public int getNetworkCount() {
        return networkCount;
    }

    /**
     * Method to get {@link #license} instance <br>
     * No-any params required
     *
     * @return {@link #license} instance as {@link CommonLicense}
     **/
    public CommonLicense getLicense() {
        return license;
    }

    /**
     * Method to get {@link #forks} instance <br>
     * No-any params required
     *
     * @return {@link #forks} instance as int
     **/
    public int getForks() {
        return forks;
    }

    /**
     * Method to get {@link #openIssues} instance <br>
     * No-any params required
     *
     * @return {@link #openIssues} instance as int
     **/
    public int getOpenIssues() {
        return openIssues;
    }

    /**
     * Method to get {@link #watchers} instance <br>
     * No-any params required
     *
     * @return {@link #watchers} instance as int
     **/
    public int getWatchers() {
        return watchers;
    }

    /**
     * Method to create a complete repositories list
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return complete repositories list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnCompleteRepositoriesList(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) returnCompleteRepositoriesList(new JSONArray(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

    /**
     * Method to create a complete repositories list
     *
     * @param jRepositories: obtained from GitHub's response
     * @return complete repositories list as {@link ArrayList} of {@link CompleteRepository}
     **/
    @Returner
    public static ArrayList<CompleteRepository> returnCompleteRepositoriesList(JSONArray jRepositories) {
        ArrayList<CompleteRepository> repositories = new ArrayList<>();
        if (jRepositories != null)
            for (int j = 0; j < jRepositories.length(); j++)
                repositories.add(new CompleteRepository(jRepositories.getJSONObject(j)));
        return repositories;
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
         * No-any params required
         *
         * @return {@link #visibility} instance as {@link String}
         **/
        @Override
        public String toString() {
            return visibility;
        }

    }

}
