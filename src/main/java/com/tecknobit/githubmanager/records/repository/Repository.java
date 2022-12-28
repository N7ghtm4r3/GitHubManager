package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

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
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Repository extends BaseResponseDetails {

    /**
     * {@code nodeId} identifier of the node value
     **/
    protected final String nodeId;

    /**
     * {@code fullName} fullname value
     **/
    protected final String fullName;

    /**
     * {@code owner} owner value
     **/
    protected final User owner;

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
     * Constructor to init a {@link Repository}
     *
     * @param name:        the name of the repository
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String name, User owner, boolean privateRepo, boolean fork) {
        this(0, null, name, null, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
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
        this(id, null, name, null, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
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
        this(0, null, name, fullName, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
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
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String name, String fullName, User owner, boolean privateRepo, boolean fork) {
        this(id, null, name, fullName, owner, privateRepo, null, null, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
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
        this(0, null, name, null, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
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
        this(id, null, name, null, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
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
        this(0, null, name, fullName, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
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
        this(id, null, name, fullName, owner, privateRepo, null, description, fork, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param nodeId:      identifier of the node value
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(String nodeId, String name, String fullName, User owner, boolean privateRepo,
                      String description, boolean fork, String url) {
        this(0, nodeId, name, fullName, owner, privateRepo, null, description, fork, url, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param id:          identifier value
     * @param nodeId:      identifier of the node value
     * @param name:        the name of the repository
     * @param fullName:    fullname value
     * @param owner:       owner value
     * @param privateRepo: whether the repository is private or public
     * @param description: description value
     * @apiNote this constructor is useful to contains only the basics details about a {@link Repository}
     */
    public Repository(long id, String nodeId, String name, String fullName, User owner, boolean privateRepo,
                      String description, boolean fork, String url) {
        this(id, nodeId, name, fullName, owner, privateRepo, null, description, fork, url, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
    }

    /**
     * Constructor to init a {@link Repository}
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
    public Repository(long id, String nodeId, String name, String fullName, User owner, boolean privateRepo,
                      String htmlUrl, String description, boolean fork, String url, String archiveUrl, String assigneesUrl,
                      String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl,
                      String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl,
                      String downloadUrl, String eventsUrl, String forksUrl, String gitCommitsUrl, String gitRefsUrl,
                      String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl,
                      String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl,
                      String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, String stargazersUrl,
                      String statutesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl,
                      String treesUrl, String cloneUrl, String mirrorUrl, String hooksUrl) {
        super(id, name, url);
        this.nodeId = nodeId;
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
    }

    /**
     * Constructor to init a {@link Repository}
     *
     * @param jRepository: repository details as {@link JSONObject}
     **/
    public Repository(JSONObject jRepository) {
        super(jRepository);
        nodeId = hResponse.getString("node_id");
        fullName = hResponse.getString("full_name");
        owner = new User(hResponse.getJSONObject("owner", new JSONObject()));
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
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
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

}
