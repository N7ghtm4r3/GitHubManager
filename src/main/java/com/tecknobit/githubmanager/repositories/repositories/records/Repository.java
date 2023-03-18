package com.tecknobit.githubmanager.repositories.repositories.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.codesofconduct.records.CodeConduct;
import com.tecknobit.githubmanager.licenses.records.CommonLicense;
import com.tecknobit.githubmanager.records.generic.Permissions;
import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.repositories.repositories.records.RepositoriesList.returnRepositoriesList;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.RepoVisibility.*;

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
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
 *             List selected repositories for an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#list-organization-repositories">
 *             List organization repositories</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#create-an-organization-repository">
 *             Create an organization repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#get-a-repository">
 *             Get a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#update-a-repository">
 *             Update a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-using-a-template">
 *             Create a repository using a template</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#list-public-repositories">
 *             List public repositories</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#transfer-a-repository">
 *             Transfer a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-the-authenticated-user">
 *             List repositories for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#create-a-repository-for-the-authenticated-user">
 *             Create a repository for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/repos#list-repositories-for-a-user">
 *             List repositories for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
 *             List forks</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
 *             Create a fork</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseItemStructure
 **/
public class Repository extends BaseItemStructure {

    /**
     * {@code RepositoryType} list of the available types for repository
     **/
    public enum RepositoryType {

        /**
         * {@code all} repository type
         **/
        all("all"),

        /**
         * {@code public} repository type
         **/
        tPublic("public"),

        /**
         * {@code private} repository type
         **/
        tPrivate("private"),

        /**
         * {@code forks} repository type
         **/
        forks("forks"),

        /**
         * {@code sources} repository type
         **/
        sources("sources"),

        /**
         * {@code member} repository type
         **/
        member("member");

        /**
         * {@code type} value
         **/
        private final String type;

        /**
         * Constructor to init a {@link RepositoryType}
         *
         * @param type: type value
         */
        RepositoryType(String type) {
            this.type = type;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        @Override
        public String toString() {
            return type;
        }

    }

    /**
     * {@code RepositorySort} list of the available sorters for repository
     **/
    public enum RepositorySort {

        /**
         * {@code created} sort
         **/
        created,

        /**
         * {@code updated} sort
         **/
        updated,

        /**
         * {@code pushed} sort
         **/
        pushed,

        /**
         * {@code full_name} sort
         **/
        full_name

    }

    /**
     * {@code ForkSort} list of the available sorters for fork
     **/
    public enum ForkSort {

        /**
         * {@code newest} sort
         **/
        newest,

        /**
         * {@code oldest} sort
         **/
        oldest,

        /**
         * {@code stargazers} sort
         **/
        stargazers,

        /**
         * {@code watchers} sort
         **/
        watchers

    }

    /**
     * {@code SquashMergeCommitTitle} list of the available squash merge commit titles
     **/
    public enum SquashMergeCommitTitle {

        /**
         * {@code PR_TITLE} squash merge commit title
         **/
        PR_TITLE,

        /**
         * {@code COMMIT_OR_PR_TITLE} squash merge commit title
         **/
        COMMIT_OR_PR_TITLE

    }

    /**
     * {@code SquashMergeCommitMessage} list of the available squash merge commit messages
     **/
    public enum SquashMergeCommitMessage {

        /**
         * {@code PR_BODY} squash merge commit message
         **/
        PR_BODY,

        /**
         * {@code COMMIT_MESSAGES} squash merge commit message
         **/
        COMMIT_MESSAGES,

        /**
         * {@code BLANK} squash merge commit message
         **/
        BLANK

    }

    /**
     * {@code MergeCommitTitle} list of the available merge commit titles
     **/
    public enum MergeCommitTitle {

        /**
         * {@code PR_TITLE} merge commit title
         **/
        PR_TITLE,

        /**
         * {@code MERGE_MESSAGE} merge commit title
         **/
        MERGE_MESSAGE

    }

    /**
     * {@code MergeCommitMessage} list of the available merge commit messages
     **/
    public enum MergeCommitMessage {

        /**
         * {@code PR_BODY} merge commit message
         **/
        PR_BODY,

        /**
         * {@code PR_TITLE} merge commit message
         **/
        PR_TITLE,

        /**
         * {@code BLANK} merge commit message
         **/
        BLANK

    }

    /**
     * {@code name} of the repository
     **/
    private final String name;

    /**
     * {@code fullName} fullname value
     **/
    private final String fullName;

    /**
     * {@code owner} owner value
     **/
    private final User owner;

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
     * {@code template} template that can be used to generate new repositories
     **/
    private final Repository template;

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
    private final Repository.RepoVisibility visibility;

    /**
     * {@code pushedAt} pushed at value
     **/
    private final String pushedAt;

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
     * {@code allowUpdateBranch} whether allow update branch
     **/
    private final boolean allowUpdateBranch;

    /**
     * {@code useSquashPrTitleAsDefault} whether to use squash Pr title as default
     **/
    private final boolean useSquashPrTitleAsDefault;

    /**
     * {@code squashMergeCommitTitle} the default value for a squash merge commit title
     **/
    private final SquashMergeCommitTitle squashMergeCommitTitle;

    /**
     * {@code squashMergeCommitMessage} the default value for a squash merge commit message
     **/
    private final SquashMergeCommitMessage squashMergeCommitMessage;

    /**
     * {@code mergeCommitTitle} the default value for a merge commit title
     **/
    private final MergeCommitTitle mergeCommitTitle;

    /**
     * {@code mergeCommitMessage} the default value for a merge commit message
     **/
    private final MergeCommitMessage mergeCommitMessage;

    /**
     * {@code allowForking} whether allow forking
     **/
    private final boolean allowForking;

    /**
     * {@code webCommitSignOffRequired} whether web commit sign off is required
     **/
    private final boolean webCommitSignOffRequired;

    /**
     * {@code masterBranch} the master branch of the repository
     **/
    private final String masterBranch;

    /**
     * {@code starredAt} when the repository has been starred
     **/
    private final String starredAt;

    /**
     * {@code anonymousAccessEnabled} whether anonymous git access is allowed
     **/
    private final boolean anonymousAccessEnabled;

    /**
     * {@code organization} of the repository
     **/
    private final User organization;

    /**
     * {@code parent} of the repository
     **/
    private final Repository parent;

    /**
     * {@code source} of the repository
     **/
    private final Repository source;

    /**
     * {@code codeOfConduct} code of conduct of the repository
     **/
    private final CodeConduct codeOfConduct;

    /**
     * {@code securityAnalysis} security analysis of the repository
     **/
    private final SecurityAnalysis securityAnalysis;

    /**
     * Constructor to init a {@link Repository}
     *
     * @param name:        the name of the repository
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String name, User owner, boolean privateRepo, boolean fork) {
        this(0, name, owner, privateRepo, fork);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id:          identifier value
     * @param name:        the name of the repository
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String name, User owner, boolean privateRepo, boolean fork) {
        this(id, name, null, null, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null,
                null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String name, String fullName, User owner, boolean privateRepo, boolean fork) {
        this(0, name, null, fullName, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null,
                null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id:          identifier value
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String name, String fullName, User owner, boolean privateRepo, boolean fork) {
        this(id, name, null, fullName, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null,
                null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param name:        the name of the repository
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String name, User owner, boolean privateRepo, String description, boolean fork) {
        this(0, name, null, null, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null,
                null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id:          identifier value
     * @param name:        the name of the repository
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String name, User owner, boolean privateRepo, String description, boolean fork) {
        this(id, name, null, null, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null,
                null, null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String name, String fullName, User owner, boolean privateRepo, String description,
                      boolean fork) {
        this(0, name, null, fullName, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null, null,
                null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id:          identifier value
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String name, String fullName, User owner, boolean privateRepo, String description,
                      boolean fork) {
        this(id, name, null, fullName, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0, 0, 0, null, 0, false, null, null, false, false, false, false, false,
                false, false, null, null, null, null, null, false, null, false, false, false, false, 0, 0,
                null, 0, 0, 0, false, false, null, null, null, null, false, false, null, null, false, null, null, null,
                null, null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id                        :                         identifier value
     * @param nodeId                    :                     identifier of the node value
     * @param name                      :                       the name of the repository
     * @param fullName                  :                   fullname value
     * @param owner                     :                      owner value
     * @param privateRepo               :                whether the repository is private or public
     * @param htmlUrl                   :                    html url value
     * @param description               :                description value
     * @param fork                      :                       fork value
     * @param url                       :                        url value
     * @param archiveUrl                :                 archive url value
     * @param assigneesUrl              :               assignees url value
     * @param blobsUrl                  :                   blobs url value
     * @param branchesUrl               :                branches url value
     * @param collaboratorsUrl          :           collaborators url value
     * @param commentsUrl               :                comments url value
     * @param commitsUrl                :                 commits url value
     * @param compareUrl                :                 compare url value
     * @param contentsUrl               :                contents url value
     * @param contributorsUrl           :            contributors url value
     * @param deploymentsUrl            :             deployments url value
     * @param downloadUrl               :                download url value
     * @param eventsUrl                 :events            url value
     * @param forksUrl                  :                   forks url value
     * @param gitCommitsUrl             :              git commits url value
     * @param gitRefsUrl                :                 git refs url value
     * @param gitTagsUrl                :                 git tags url value
     * @param gitUrl                    :                     git url value
     * @param issueCommentUrl           :            issues comments url value
     * @param issueEventsUrl            :             issues events url value
     * @param issuesUrl                 :                  issues url value
     * @param keysUrl                   :                    keys url value
     * @param labelsUrl                 :                  labels url value
     * @param languagesUrl              :languages      url value
     * @param mergesUrl                 :                  merges url value
     * @param milestonesUrl             :              milestones url value
     * @param notificationsUrl          :           notifications url value
     * @param pullsUrl                  :                   pulls url value
     * @param releasesUrl               :                releases url value
     * @param sshUrl                    :                     ssh url value
     * @param stargazersUrl             :              stargazers url value
     * @param statutesUrl               :                statuses url value
     * @param subscribersUrl            :             subscribers url value
     * @param subscriptionUrl           :            subscription url value
     * @param tagsUrl                   :                    tags url value
     * @param teamsUrl                  :                   teams url value
     * @param treesUrl                  :                   trees url value
     * @param cloneUrl                  :                   clone url value
     * @param mirrorUrl                 :                  mirror url value
     * @param hooksUrl                  :                   hooks url value
     * @param svnUrl                    :                     svn url value
     * @param homePage                  :                   homepage value
     * @param forksCount                :                 forks count value
     * @param stargazersCount           :            stargazers count value
     * @param watchersCount             :              watchers count value
     * @param size                      :                       the size of the repository. Size is calculated hourly. When a repository is initially created, the size is 0
     * @param defaultBranch             :              the default branch of the repository
     * @param openIssuesCount           :            open issues count value
     * @param isTemplate                :                 whether this repository acts as a template that can be used to generate new repositories
     * @param template:                 template that can be used to generate new repositories
     * @param topics                    :                     topics list
     * @param hasIssues                 :                  whether issues are enabled
     * @param hasProjects               :                whether projects are enabled
     * @param hasWiki                   :                    whether the wiki is enabled
     * @param hasPages                  :                   whether the repository has pages
     * @param hasDownloads              :               whether downloads are enabled
     * @param archived                  :                   whether the repository is archived
     * @param disabled                  :                   returns whether this repository disabled
     * @param visibility                :                 the repository visibility: public, private, or internal
     * @param pushedAt                  :                   pushed at value
     * @param createdAt                 :                  created at value
     * @param updatedAt                 :                  updated at value
     * @param permissions               :                repository permissions
     * @param allowRebaseMerge          :           whether to allow rebase merges for pull requests
     * @param tempCloneToken            :             temp clone token value
     * @param allowSquashMerge          :           whether to allow squash merges for pull requests
     * @param allowAutoMerge            :             whether to allow {@code "Auto-merge"} to be used on pull requests
     * @param deleteBranchOnMerge       :whether to delete head branches when pull requests are merged
     * @param allowMergeCommit          :           whether a pull request head branch that is behind its base branch can always
     *                                  be updated even if it is not required to be up-to-date before merging
     * @param subscribersCount          :           subscribers count value
     * @param networkCount              :               network count value
     * @param license                   :                    license value
     * @param forks                     :                      forks value
     * @param openIssues                :                 open issues value
     * @param watchers                  :                   watchers value
     * @param allowUpdateBranch         :    whether allow update branch
     * @param useSquashPrTitleAsDefault :  whether to use squash Pr title as default
     * @param squashMergeCommitTitle    :  the default value for a squash merge commit title
     * @param squashMergeCommitMessage  :   the default value for a squash merge commit message
     * @param mergeCommitTitle          :   the default value for a merge commit title
     * @param mergeCommitMessage        :    the default value for a merge commit message
     * @param allowForking              :   whether allow forking
     * @param webCommitSignOffRequired  : whether web commit sign off is required
     * @param masterBranch              :    the master branch of the repository
     * @param starredAt                 :   when the repository has been starred
     * @param anonymousAccessEnabled    : whether anonymous git access is allowed
     * @param organization              :   organization of the repository
     * @param parent                    :  parent of the repository
     * @param source                    :    source of the repository
     * @param codeOfConduct             :    code of conduct of the repository
     * @param securityAnalysis          : security analysis of the repository
     **/
    public Repository(long id, String name, String nodeId, String fullName, User owner, boolean privateRepo,
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
                      int openIssuesCount, boolean isTemplate, Repository template, ArrayList<String> topics,
                      boolean hasIssues, boolean hasProjects, boolean hasWiki, boolean hasPages, boolean hasDownloads,
                      boolean archived, boolean disabled, RepoVisibility visibility, String pushedAt, String createdAt,
                      String updatedAt, Permissions permissions, boolean allowRebaseMerge, String tempCloneToken,
                      boolean allowSquashMerge, boolean allowAutoMerge, boolean deleteBranchOnMerge,
                      boolean allowMergeCommit, int subscribersCount, int networkCount, CommonLicense license, int forks,
                      int openIssues, int watchers, boolean allowUpdateBranch, boolean useSquashPrTitleAsDefault,
                      SquashMergeCommitTitle squashMergeCommitTitle, SquashMergeCommitMessage squashMergeCommitMessage,
                      MergeCommitTitle mergeCommitTitle, MergeCommitMessage mergeCommitMessage, boolean allowForking,
                      boolean webCommitSignOffRequired, String masterBranch, String starredAt, boolean anonymousAccessEnabled,
                      User organization, Repository parent, Repository source, CodeConduct codeOfConduct,
                      SecurityAnalysis securityAnalysis) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.privateRepo = privateRepo;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.fork = fork;
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
        this.allowUpdateBranch = allowUpdateBranch;
        this.useSquashPrTitleAsDefault = useSquashPrTitleAsDefault;
        this.squashMergeCommitTitle = squashMergeCommitTitle;
        this.squashMergeCommitMessage = squashMergeCommitMessage;
        this.mergeCommitTitle = mergeCommitTitle;
        this.mergeCommitMessage = mergeCommitMessage;
        this.allowForking = allowForking;
        this.webCommitSignOffRequired = webCommitSignOffRequired;
        this.masterBranch = masterBranch;
        this.starredAt = starredAt;
        this.anonymousAccessEnabled = anonymousAccessEnabled;
        this.organization = organization;
        this.parent = parent;
        this.source = source;
        this.codeOfConduct = codeOfConduct;
        this.securityAnalysis = securityAnalysis;
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param jRepository: repository details as {@link JSONObject}
     **/
    public Repository(JSONObject jRepository) {
        super(jRepository);
        name = hResponse.getString("name");
        fullName = hResponse.getString("full_name");
        owner = new User(hResponse.getJSONObject("owner"));
        privateRepo = hResponse.getBoolean("private");
        htmlUrl = hResponse.getString("html_url");
        description = hResponse.getString("description");
        fork = hResponse.getBoolean("fork");
        archiveUrl = hResponse.getString("archive_url");
        assigneesUrl = hResponse.getString("assignees_url");
        blobsUrl = hResponse.getString("blobs_url");
        branchesUrl = hResponse.getString("branches_url");
        collaboratorsUrl = hResponse.getString("collaborators_url");
        commentsUrl = hResponse.getString("comments_url");
        commitsUrl = hResponse.getString("commits_url");
        compareUrl = hResponse.getString("compare_url");
        contentsUrl = hResponse.getString("contents_url");
        contributorsUrl = hResponse.getString("contributors_url");
        deploymentsUrl = hResponse.getString("deployments_url");
        downloadUrl = hResponse.getString("downloads_url");
        eventsUrl = hResponse.getString("events_url");
        forksUrl = hResponse.getString("forks_url");
        gitCommitsUrl = hResponse.getString("git_commits_url");
        gitRefsUrl = hResponse.getString("git_refs_url");
        gitTagsUrl = hResponse.getString("git_tags_url");
        gitUrl = hResponse.getString("git_url");
        issueCommentUrl = hResponse.getString("issue_comment_url");
        issueEventsUrl = hResponse.getString("issue_events_url");
        issuesUrl = hResponse.getString("issues_url");
        keysUrl = hResponse.getString("keys_url");
        labelsUrl = hResponse.getString("labels_url");
        languagesUrl = hResponse.getString("languages_url");
        mergesUrl = hResponse.getString("merges_url");
        milestonesUrl = hResponse.getString("milestones_url");
        notificationsUrl = hResponse.getString("notifications_url");
        pullsUrl = hResponse.getString("pulls_url");
        releasesUrl = hResponse.getString("releases_url");
        sshUrl = hResponse.getString("ssh_url");
        stargazersUrl = hResponse.getString("stargazers_url");
        statutesUrl = hResponse.getString("statuses_url");
        subscribersUrl = hResponse.getString("subscribers_url");
        subscriptionUrl = hResponse.getString("subscription_url");
        tagsUrl = hResponse.getString("tags_url");
        teamsUrl = hResponse.getString("teams_url");
        treesUrl = hResponse.getString("trees_url");
        cloneUrl = hResponse.getString("clone_url");
        mirrorUrl = hResponse.getString("mirror_url");
        hooksUrl = hResponse.getString("hooks_url");
        svnUrl = hResponse.getString("svn_url");
        homePage = hResponse.getString("homepage");
        forksCount = hResponse.getInt("forks_count", 0);
        stargazersCount = hResponse.getInt("stargazers_count", 0);
        watchersCount = hResponse.getInt("watchers_count", 0);
        size = hResponse.getInt("size", 0);
        defaultBranch = hResponse.getString("default_branch");
        openIssuesCount = hResponse.getInt("open_issues_count", 0);
        isTemplate = hResponse.getBoolean("is_template");
        JSONObject jItem = hResponse.getJSONObject("template_repository");
        if (jItem != null)
            template = new Repository(jItem);
        else
            template = null;
        topics = returnStringsList(hResponse.getJSONArray("topics"));
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
        jItem = hResponse.getJSONObject("permissions");
        if (jItem != null)
            permissions = new Permissions(jItem);
        else
            permissions = null;
        allowRebaseMerge = hResponse.getBoolean("allow_rebase_merge");
        tempCloneToken = hResponse.getString("temp_clone_token");
        allowSquashMerge = hResponse.getBoolean("allow_squash_merge");
        allowAutoMerge = hResponse.getBoolean("allow_auto_merge");
        deleteBranchOnMerge = hResponse.getBoolean("delete_branch_on_merge");
        allowMergeCommit = hResponse.getBoolean("allow_merge_commit");
        subscribersCount = hResponse.getInt("subscribers_count", 0);
        networkCount = hResponse.getInt("network_count", 0);
        license = new CommonLicense(hResponse.getJSONObject("license"));
        forks = hResponse.getInt("forks", 0);
        openIssues = hResponse.getInt("open_issues", 0);
        watchers = hResponse.getInt("watchers", 0);
        allowUpdateBranch = hResponse.getBoolean("allow_update_branch");
        useSquashPrTitleAsDefault = hResponse.getBoolean("use_squash_pr_title_as_default");
        String sEnum = hResponse.getString("squash_merge_commit_title");
        if (sEnum != null)
            squashMergeCommitTitle = SquashMergeCommitTitle.valueOf(sEnum);
        else
            squashMergeCommitTitle = null;
        sEnum = hResponse.getString("squash_merge_commit_message");
        if (sEnum != null)
            squashMergeCommitMessage = SquashMergeCommitMessage.valueOf(sEnum);
        else
            squashMergeCommitMessage = null;
        sEnum = hResponse.getString("merge_commit_title");
        if (sEnum != null)
            mergeCommitTitle = MergeCommitTitle.valueOf(sEnum);
        else
            mergeCommitTitle = null;
        sEnum = hResponse.getString("merge_commit_message");
        if (sEnum != null)
            mergeCommitMessage = MergeCommitMessage.valueOf(sEnum);
        else
            mergeCommitMessage = null;
        allowForking = hResponse.getBoolean("allow_forking");
        webCommitSignOffRequired = hResponse.getBoolean("web_commit_signoff_required");
        masterBranch = hResponse.getString("master_branch");
        starredAt = hResponse.getString("starred_at");
        anonymousAccessEnabled = hResponse.getBoolean("anonymous_access_enabled");
        jItem = hResponse.getJSONObject("organization");
        if (jItem != null)
            organization = new User(jItem);
        else
            organization = null;
        jItem = hResponse.getJSONObject("parent");
        if (jItem != null)
            parent = new Repository(jItem);
        else
            parent = null;
        jItem = hResponse.getJSONObject("source");
        if (jItem != null)
            source = new Repository(jItem);
        else
            source = null;
        jItem = hResponse.getJSONObject("code_of_conduct");
        if (jItem != null)
            codeOfConduct = new CodeConduct(jItem);
        else
            codeOfConduct = null;
        jItem = hResponse.getJSONObject("security_and_analysis");
        if (jItem != null)
            securityAnalysis = new SecurityAnalysis(jItem);
        else
            securityAnalysis = null;
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link Long}
     **/
    @Override
    public Long getId() {
        return super.getId();
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
     * Method to get {@link #fullName} instance <br>
     * No-any params required
     *
     * @return {@link #fullName} instance as {@link String}
     **/
    public String getFullName() {
        return fullName;
    }

    /**
     * Method to get {@link #owner} instance <br>
     * No-any params required
     *
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
        return owner;
    }

    /**
     * Method to get {@link #privateRepo} instance <br>
     * No-any params required
     *
     * @return {@link #privateRepo} instance as boolean
     **/
    public boolean isPrivateRepo() {
        return privateRepo;
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
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #fork} instance <br>
     * No-any params required
     *
     * @return {@link #fork} instance as boolean
     **/
    public boolean isFork() {
        return fork;
    }

    /**
     * Method to get {@link #archiveUrl} instance <br>
     * No-any params required
     *
     * @return {@link #archiveUrl} instance as {@link String}
     **/
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     * Method to get {@link #assigneesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #assigneesUrl} instance as {@link String}
     **/
    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    /**
     * Method to get {@link #blobsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #blobsUrl} instance as {@link String}
     **/
    public String getBlobsUrl() {
        return blobsUrl;
    }

    /**
     * Method to get {@link #branchesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #branchesUrl} instance as {@link String}
     **/
    public String getBranchesUrl() {
        return branchesUrl;
    }

    /**
     * Method to get {@link #collaboratorsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #collaboratorsUrl} instance as {@link String}
     **/
    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    /**
     * Method to get {@link #commentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commentsUrl} instance as {@link String}
     **/
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Method to get {@link #commitsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitsUrl} instance as {@link String}
     **/
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Method to get {@link #compareUrl} instance <br>
     * No-any params required
     *
     * @return {@link #compareUrl} instance as {@link String}
     **/
    public String getCompareUrl() {
        return compareUrl;
    }

    /**
     * Method to get {@link #contentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #contentsUrl} instance as {@link String}
     **/
    public String getContentsUrl() {
        return contentsUrl;
    }

    /**
     * Method to get {@link #contributorsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #contributorsUrl} instance as {@link String}
     **/
    public String getContributorsUrl() {
        return contributorsUrl;
    }

    /**
     * Method to get {@link #deploymentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #deploymentsUrl} instance as {@link String}
     **/
    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    /**
     * Method to get {@link #downloadUrl} instance <br>
     * No-any params required
     *
     * @return {@link #downloadUrl} instance as {@link String}
     **/
    public String getDownloadUrl() {
        return downloadUrl;
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
     * Method to get {@link #forksUrl} instance <br>
     * No-any params required
     *
     * @return {@link #forksUrl} instance as {@link String}
     **/
    public String getForksUrl() {
        return forksUrl;
    }

    /**
     * Method to get {@link #gitCommitsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitCommitsUrl} instance as {@link String}
     **/
    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    /**
     * Method to get {@link #gitRefsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitRefsUrl} instance as {@link String}
     **/
    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    /**
     * Method to get {@link #gitTagsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitTagsUrl} instance as {@link String}
     **/
    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    /**
     * Method to get {@link #gitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitUrl} instance as {@link String}
     **/
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * Method to get {@link #issueCommentUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issueCommentUrl} instance as {@link String}
     **/
    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    /**
     * Method to get {@link #issueEventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #issueEventsUrl} instance as {@link String}
     **/
    public String getIssueEventsUrl() {
        return issueEventsUrl;
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
     * Method to get {@link #labelsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #labelsUrl} instance as {@link String}
     **/
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     * Method to get {@link #languagesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #languagesUrl} instance as {@link String}
     **/
    public String getLanguagesUrl() {
        return languagesUrl;
    }

    /**
     * Method to get {@link #mergesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #mergesUrl} instance as {@link String}
     **/
    public String getMergesUrl() {
        return mergesUrl;
    }

    /**
     * Method to get {@link #milestonesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #milestonesUrl} instance as {@link String}
     **/
    public String getMilestonesUrl() {
        return milestonesUrl;
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
     * Method to get {@link #pullsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #pullsUrl} instance as {@link String}
     **/
    public String getPullsUrl() {
        return pullsUrl;
    }

    /**
     * Method to get {@link #releasesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #releasesUrl} instance as {@link String}
     **/
    public String getReleasesUrl() {
        return releasesUrl;
    }

    /**
     * Method to get {@link #sshUrl} instance <br>
     * No-any params required
     *
     * @return {@link #sshUrl} instance as {@link String}
     **/
    public String getSshUrl() {
        return sshUrl;
    }

    /**
     * Method to get {@link #stargazersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #stargazersUrl} instance as {@link String}
     **/
    public String getStargazersUrl() {
        return stargazersUrl;
    }

    /**
     * Method to get {@link #statutesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #statutesUrl} instance as {@link String}
     **/
    public String getStatutesUrl() {
        return statutesUrl;
    }

    /**
     * Method to get {@link #subscribersUrl} instance <br>
     * No-any params required
     *
     * @return {@link #subscribersUrl} instance as {@link String}
     **/
    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    /**
     * Method to get {@link #subscriptionUrl} instance <br>
     * No-any params required
     *
     * @return {@link #subscriptionUrl} instance as {@link String}
     **/
    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    /**
     * Method to get {@link #tagsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #tagsUrl} instance as {@link String}
     **/
    public String getTagsUrl() {
        return tagsUrl;
    }

    /**
     * Method to get {@link #teamsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #teamsUrl} instance as {@link String}
     **/
    public String getTeamsUrl() {
        return teamsUrl;
    }

    /**
     * Method to get {@link #treesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #treesUrl} instance as {@link String}
     **/
    public String getTreesUrl() {
        return treesUrl;
    }

    /**
     * Method to get {@link #cloneUrl} instance <br>
     * No-any params required
     *
     * @return {@link #cloneUrl} instance as {@link String}
     **/
    public String getCloneUrl() {
        return cloneUrl;
    }

    /**
     * Method to get {@link #mirrorUrl} instance <br>
     * No-any params required
     *
     * @return {@link #mirrorUrl} instance as {@link String}
     **/
    public String getMirrorUrl() {
        return mirrorUrl;
    }

    /**
     * Method to get {@link #hooksUrl} instance <br>
     * No-any params required
     *
     * @return {@link #hooksUrl} instance as {@link String}
     **/
    public String getHooksUrl() {
        return hooksUrl;
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
     * @return {@link #template} instance as {@link Repository}
     **/
    public Repository getTemplate() {
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
     * @return {@link #visibility} instance as {@link Repository.RepoVisibility}
     **/
    public Repository.RepoVisibility getVisibility() {
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
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link Permissions}
     **/
    public Permissions getPermissions() {
        return permissions;
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
     * Method to get {@link #allowUpdateBranch} instance <br>
     * No-any params required
     *
     * @return {@link #allowUpdateBranch} instance as boolean
     **/
    public boolean isAllowedUpdateBranch() {
        return allowUpdateBranch;
    }

    /**
     * Method to get {@link #useSquashPrTitleAsDefault} instance <br>
     * No-any params required
     *
     * @return {@link #useSquashPrTitleAsDefault} instance as boolean
     **/
    public boolean useSquashPrTitleAsDefault() {
        return useSquashPrTitleAsDefault;
    }

    /**
     * Method to get {@link #squashMergeCommitTitle} instance <br>
     * No-any params required
     *
     * @return {@link #squashMergeCommitTitle} instance as {@link SquashMergeCommitTitle}
     **/
    public SquashMergeCommitTitle getSquashMergeCommitTitle() {
        return squashMergeCommitTitle;
    }

    /**
     * Method to get {@link #squashMergeCommitMessage} instance <br>
     * No-any params required
     *
     * @return {@link #squashMergeCommitMessage} instance as {@link SquashMergeCommitMessage}
     **/
    public SquashMergeCommitMessage getSquashMergeCommitMessage() {
        return squashMergeCommitMessage;
    }

    /**
     * Method to get {@link #mergeCommitTitle} instance <br>
     * No-any params required
     *
     * @return {@link #mergeCommitTitle} instance as {@link MergeCommitTitle}
     **/
    public MergeCommitTitle getMergeCommitTitle() {
        return mergeCommitTitle;
    }

    /**
     * Method to get {@link #mergeCommitMessage} instance <br>
     * No-any params required
     *
     * @return {@link #mergeCommitMessage} instance as {@link MergeCommitMessage}
     **/
    public MergeCommitMessage getMergeCommitMessage() {
        return mergeCommitMessage;
    }

    /**
     * Method to get {@link #allowForking} instance <br>
     * No-any params required
     *
     * @return {@link #allowForking} instance as boolean
     **/
    public boolean isAllowedForking() {
        return allowForking;
    }

    /**
     * Method to get {@link #webCommitSignOffRequired} instance <br>
     * No-any params required
     *
     * @return {@link #webCommitSignOffRequired} instance as boolean
     **/
    public boolean isWebCommitSignOffRequired() {
        return webCommitSignOffRequired;
    }

    /**
     * Method to get {@link #masterBranch} instance <br>
     * No-any params required
     *
     * @return {@link #masterBranch} instance as {@link String}
     **/
    public String getMasterBranch() {
        return masterBranch;
    }

    /**
     * Method to get {@link #starredAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #starredAt} timestamp as {@link String}
     **/
    public String getStarredAt() {
        return starredAt;
    }

    /**
     * Method to get {@link #starredAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #starredAt} timestamp as long
     **/
    public long getStarredAtTimestamp() {
        return getDateTimestamp(starredAt);
    }

    /**
     * Method to get {@link #anonymousAccessEnabled} instance <br>
     * No-any params required
     *
     * @return {@link #anonymousAccessEnabled} instance as boolean
     **/
    public boolean isAnonymousAccessEnabled() {
        return anonymousAccessEnabled;
    }

    /**
     * Method to get {@link #organization} instance <br>
     * No-any params required
     *
     * @return {@link #organization} instance as {@link User}
     **/
    public User getOrganization() {
        return organization;
    }

    /**
     * Method to get {@link #parent} instance <br>
     * No-any params required
     *
     * @return {@link #parent} instance as {@link Repository}
     **/
    public Repository getParent() {
        return parent;
    }

    /**
     * Method to get {@link #source} instance <br>
     * No-any params required
     *
     * @return {@link #source} instance as {@link Repository}
     **/
    public Repository getSource() {
        return source;
    }

    /**
     * Method to get {@link #codeOfConduct} instance <br>
     * No-any params required
     *
     * @return {@link #codeOfConduct} instance as {@link CodeConduct}
     **/
    public CodeConduct getCodeOfConduct() {
        return codeOfConduct;
    }

    /**
     * Method to get {@link #securityAnalysis} instance <br>
     * No-any params required
     *
     * @return {@link #securityAnalysis} instance as {@link SecurityAnalysis}
     **/
    public SecurityAnalysis getSecurityAnalysis() {
        return securityAnalysis;
    }

    /**
     * Method to create a repository
     *
     * @param repositoryResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnRepository(String repositoryResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryResponse);
            case LIBRARY_OBJECT:
                return (T) new Repository(new JSONObject(repositoryResponse));
            default:
                return (T) repositoryResponse;
        }
    }

    /**
     * Method to create a repositories list
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnRepositories(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) returnRepositoriesList(new JSONArray(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

    /**
     * The {@code SecurityAnalysis} class is useful to format a GitHub's security analysis
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see InnerClassItem
     **/
    public static class SecurityAnalysis extends InnerClassItem {

        /**
         * {@code Status} list of available statuses
         **/
        public enum Status {

            /**
             * {@code enabled} status
             **/
            enabled,

            /**
             * {@code disabled} status
             **/
            disabled

        }

        /**
         * {@code advancedSecurity} advanced security status
         **/
        private final Status advancedSecurity;

        /**
         * {@code secretScanning} secret scanning status
         **/
        private final Status secretScanning;

        /**
         * {@code secretScanningPushProtection} secret scanning push protection status
         **/
        private final Status secretScanningPushProtection;

        /**
         * Constructor to init a {@link SecurityAnalysis}
         *
         * @param advancedSecurity:             advanced security status
         * @param secretScanning:               secret scanning status
         * @param secretScanningPushProtection: secret scanning push protection status
         **/
        public SecurityAnalysis(Status advancedSecurity, Status secretScanning, Status secretScanningPushProtection) {
            super(null);
            this.advancedSecurity = advancedSecurity;
            this.secretScanning = secretScanning;
            this.secretScanningPushProtection = secretScanningPushProtection;
        }

        /**
         * Constructor to init a {@link SecurityAnalysis}
         *
         * @param jSecurityAnalysis: security analysis details as {@link JSONObject}
         **/
        public SecurityAnalysis(JSONObject jSecurityAnalysis) {
            super(jSecurityAnalysis);
            JSONObject jItem = hItem.getJSONObject("advanced_security");
            if (jItem != null)
                advancedSecurity = Status.valueOf(jItem.getString("status"));
            else
                advancedSecurity = null;
            jItem = hItem.getJSONObject("secret_scanning");
            if (jItem != null)
                secretScanning = Status.valueOf(jItem.getString("status"));
            else
                secretScanning = null;
            jItem = hItem.getJSONObject("secret_scanning_push_protection");
            if (jItem != null)
                secretScanningPushProtection = Status.valueOf(jItem.getString("status"));
            else
                secretScanningPushProtection = null;
        }

        /**
         * Method to get {@link #advancedSecurity} instance <br>
         * No-any params required
         *
         * @return {@link #advancedSecurity} instance as {@link Status}
         **/
        public Status getAdvancedSecurity() {
            return advancedSecurity;
        }

        /**
         * Method to get {@link #secretScanning} instance <br>
         * No-any params required
         *
         * @return {@link #secretScanning} instance as {@link Status}
         **/
        public Status getSecretScanning() {
            return secretScanning;
        }

        /**
         * Method to get {@link #secretScanningPushProtection} instance <br>
         * No-any params required
         *
         * @return {@link #secretScanningPushProtection} instance as {@link Status}
         **/
        public Status getSecretScanningPushProtection() {
            return secretScanningPushProtection;
        }

    }

    /**
     * {@code RepositorySelection} list of available repository selections
     **/
    public enum RepositorySelection {

        /**
         * {@code "all"} repository selection
         **/
        all,

        /**
         * {@code "selected"} repository selection
         **/
        selected

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
         * Constructor to init a {@link Repository.RepoVisibility}
         *
         * @param visibility : {@code "visibility"} value
         **/
        RepoVisibility(String visibility) {
            this.visibility = visibility;
        }

        /**
         * Method to reach a {@link Repository.RepoVisibility} value
         *
         * @param target: target of the {@link Repository.RepoVisibility} to reach
         * @return visibility as {@link Repository.RepoVisibility} or null if it not exists
         **/
        public static Repository.RepoVisibility reachEnumConstant(String target) {
            for (Repository.RepoVisibility visibility : Repository.RepoVisibility.values())
                if (visibility.toString().equals(target))
                    return visibility;
            return null;
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
