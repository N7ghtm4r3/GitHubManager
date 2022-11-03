package com.tecknobit.githubmanager.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.permissions.records.OrganizationRepositoriesList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CompletedRepository} class is useful to format a GitHub's repository
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
 * </ul>
 **/
public class Repository extends GitHubResponse {

    /**
     * {@code id} identifier value
     **/
    protected final long id;

    /**
     * {@code nodeId} identifier of the node value
     **/
    protected final String nodeId;

    /**
     * {@code name} the name of the repository
     **/
    protected final String name;

    /**
     * {@code fullName} fullname value
     **/
    protected final String fullName;

    /**
     * {@code owner} owner value
     **/
    protected final Owner owner;

    /**
     * {@code protectedRepo} whether the repository is protected or public
     **/
    protected final boolean privateRepo;

    /**
     * {@code htmlUrl} html url value
     **/
    protected final String htmlUrl;

    /**
     * {@code description} description value
     **/
    protected final String description;

    /**
     * {@code fork} fork value
     **/
    protected final boolean fork;

    /**
     * {@code "url"} value
     **/
    protected final String url;

    /**
     * {@code archiveUrl} archive url value
     **/
    protected final String archiveUrl;

    /**
     * {@code assigneesUrl} assignees url value
     **/
    protected final String assigneesUrl;

    /**
     * {@code blobsUrl} identifier blobs url value
     **/
    protected final String blobsUrl;

    /**
     * {@code branchesUrl} branches url value
     **/
    protected final String branchesUrl;

    /**
     * {@code collaboratorsUrl} collaborators url value
     **/
    protected final String collaboratorsUrl;

    /**
     * {@code commentsUrl} comments url value
     **/
    protected final String commentsUrl;

    /**
     * {@code commitsUrl} commits url value
     **/
    protected final String commitsUrl;

    /**
     * {@code compareUrl} compare url value
     **/
    protected final String compareUrl;

    /**
     * {@code contentsUrl} contents url value
     **/
    protected final String contentsUrl;

    /**
     * {@code contributorsUrl} contributors url value
     **/
    protected final String contributorsUrl;

    /**
     * {@code deploymentsUrl} deployments url value
     **/
    protected final String deploymentsUrl;

    /**
     * {@code downloadUrl} download url value
     **/
    protected final String downloadUrl;

    /**
     * {@code eventsUrl} events url value
     **/
    protected final String eventsUrl;

    /**
     * {@code forksUrl} forks url value
     **/
    protected final String forksUrl;

    /**
     * {@code gitCommitsUrl} git commits url value
     **/
    protected final String gitCommitsUrl;

    /**
     * {@code gitRefsUrl} git refs url value
     **/
    protected final String gitRefsUrl;

    /**
     * {@code gitTagsUrl} git tags url value
     **/
    protected final String gitTagsUrl;

    /**
     * {@code gitUrl} git url value
     **/
    protected final String gitUrl;

    /**
     * {@code issueCommentUrl} issue comment url value
     **/
    protected final String issueCommentUrl;

    /**
     * {@code issueEventsUrl} issue event url value
     **/
    protected final String issueEventsUrl;

    /**
     * {@code issuesUrl} issues url value
     **/
    protected final String issuesUrl;

    /**
     * {@code keysUrl} keys url value
     **/
    protected final String keysUrl;

    /**
     * {@code labelsUrl} labels url value
     **/
    protected final String labelsUrl;

    /**
     * {@code languagesUrl} languages url value
     **/
    protected final String languagesUrl;

    /**
     * {@code mergesUrl} merges url value
     **/
    protected final String mergesUrl;

    /**
     * {@code milestonesUrl} milestones url value
     **/
    protected final String milestonesUrl;

    /**
     * {@code notificationsUrl} notifications url value
     **/
    protected final String notificationsUrl;

    /**
     * {@code pullsUrl} pulls url value
     **/
    protected final String pullsUrl;

    /**
     * {@code releasesUrl} releases url value
     **/
    protected final String releasesUrl;

    /**
     * {@code sshUrl} ssh url value
     **/
    protected final String sshUrl;

    /**
     * {@code stargazersUrl} stargazers url value
     **/
    protected final String stargazersUrl;

    /**
     * {@code statutesUrl} statuses url value
     **/
    protected final String statutesUrl;

    /**
     * {@code subscribersUrl} subscribers url value
     **/
    protected final String subscribersUrl;

    /**
     * {@code subscriptionUrl} subscription url value
     **/
    protected final String subscriptionUrl;

    /**
     * {@code tagsUrl} tags url value
     **/
    protected final String tagsUrl;

    /**
     * {@code teamsUrl} teams url value
     **/
    protected final String teamsUrl;

    /**
     * {@code treesUrl} trees url value
     **/
    protected final String treesUrl;

    /**
     * {@code cloneUrl} clone url value
     **/
    protected final String cloneUrl;

    /**
     * {@code mirrorUrl} mirror url value
     **/
    protected final String mirrorUrl;

    /**
     * {@code hooksUrl} hooks url value
     **/
    protected final String hooksUrl;

    /**
     * Constructor to init a {@link OrganizationRepositoriesList.CompletedRepository}
     *
     * @param id:                    identifier value
     * @param nodeId:                identifier of the node value
     * @param name:                  the name of the repository
     * @param fullName:              fullname value
     * @param owner:                 owner value
     * @param privateRepo:           whether the repository is private or public
     * @param htmlUrl:               html url value
     * @param description:           description value
     * @param fork:                  fork value
     * @param url:                   url value
     * @param archiveUrl:            archive url value
     * @param assigneesUrl:          assignees url value
     * @param blobsUrl:              blobs url value
     * @param branchesUrl:           branches url value
     * @param collaboratorsUrl:      collaborators url value
     * @param commentsUrl:           comments url value
     * @param commitsUrl:            commits url value
     * @param compareUrl:            compare url value
     * @param contentsUrl:           contents url value
     * @param contributorsUrl:       contributors url value
     * @param deploymentsUrl:        deployments url value
     * @param downloadUrl:           download url value
     * @param eventsUrl:events       url value
     * @param forksUrl:              forks url value
     * @param gitCommitsUrl:         git commits url value
     * @param gitRefsUrl:            git refs url value
     * @param gitTagsUrl:            git tags url value
     * @param gitUrl:                git url value
     * @param issueCommentUrl:       issues comments url value
     * @param issueEventsUrl:        issues events url value
     * @param issuesUrl:             issues url value
     * @param keysUrl:               keys url value
     * @param labelsUrl:             labels url value
     * @param languagesUrl:languages url value
     * @param mergesUrl:             merges url value
     * @param milestonesUrl:         milestones url value
     * @param notificationsUrl:      notifications url value
     * @param pullsUrl:              pulls url value
     * @param releasesUrl:           releases url value
     * @param sshUrl:                ssh url value
     * @param stargazersUrl:         stargazers url value
     * @param statutesUrl:           statuses url value
     * @param subscribersUrl:        subscribers url value
     * @param subscriptionUrl:       subscription url value
     * @param tagsUrl:               tags url value
     * @param teamsUrl:              teams url value
     * @param treesUrl:              trees url value
     * @param cloneUrl:              clone url value
     * @param mirrorUrl:             mirror url value
     * @param hooksUrl:              hooks url value
     */
    public Repository(long id, String nodeId, String name, String fullName, Owner owner, boolean privateRepo,
                      String htmlUrl, String description, boolean fork, String url, String archiveUrl, String assigneesUrl,
                      String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl,
                      String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl,
                      String downloadUrl, String eventsUrl, String forksUrl, String gitCommitsUrl, String gitRefsUrl,
                      String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl,
                      String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl,
                      String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, String stargazersUrl,
                      String statutesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl,
                      String treesUrl, String cloneUrl, String mirrorUrl, String hooksUrl) {
        super(null);
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
    }

    /**
     * Constructor to init a {@link OrganizationRepositoriesList.CompletedRepository}
     *
     * @param jRepository: repository details as {@link JSONObject}
     **/
    public Repository(JSONObject jRepository) {
        super(jRepository);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        name = hResponse.getString("name");
        fullName = hResponse.getString("full_name");
        owner = new Owner(hResponse.getJSONObject("owner", new JSONObject()));
        privateRepo = hResponse.getBoolean("private");
        htmlUrl = hResponse.getString("html_url");
        description = hResponse.getString("description");
        fork = hResponse.getBoolean("fork");
        url = hResponse.getString("url");
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
     * The {@code Owner} class is useful to format a GitHub's owner
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
     * </ul>
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
         * @return {@link #login} instance as {@link String}
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

}
