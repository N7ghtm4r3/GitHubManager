package com.tecknobit.githubmanager.search;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.search.records.Code;
import com.tecknobit.githubmanager.search.records.SearchResults;
import com.tecknobit.githubmanager.search.records.Topic;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.selfhosted.runners.GitHubRunnersManager.LABELS_PATH;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;
import static com.tecknobit.githubmanager.repositories.repositories.GitHubRepositoriesManager.TOPICS_PATH;

/**
 * The {@code GitHubSearchManager} class is useful to manage all GitHub's search endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search">
 * Search</a>
 * @see GitHubManager
 **/
public class GitHubSearchManager extends GitHubManager {

    /**
     * {@code SEARCH_PATH} constant for {@code "search"} path
     **/
    public static final String SEARCH_PATH = "search";

    /**
     * {@code SEARCH_CODE_PATH} constant for {@code "search/code"} path
     **/
    public static final String SEARCH_CODE_PATH = SEARCH_PATH + "/code";

    /**
     * {@code SEARCH_COMMITS_PATH} constant for {@code "search/commits"} path
     **/
    public static final String SEARCH_COMMITS_PATH = SEARCH_PATH + COMMITS_PATH;

    /**
     * {@code SEARCH_ISSUES_PATH} constant for {@code "search/issues"} path
     **/
    public static final String SEARCH_ISSUES_PATH = SEARCH_PATH + "/" + ISSUES_PATH;

    /**
     * {@code SEARCH_LABELS_PATH} constant for {@code "search/labels"} path
     **/
    public static final String SEARCH_LABELS_PATH = SEARCH_PATH + LABELS_PATH;

    /**
     * {@code SEARCH_REPOSITORIES_PATH} constant for {@code "search/repositories"} path
     **/
    public static final String SEARCH_REPOSITORIES_PATH = SEARCH_PATH + REPOSITORIES_PATH;

    /**
     * {@code SEARCH_TOPICS_PATH} constant for {@code "search/topics"} path
     **/
    public static final String SEARCH_TOPICS_PATH = SEARCH_PATH + TOPICS_PATH;

    /**
     * {@code SEARCH_USERS_PATH} constant for {@code "search/users"} path
     **/
    public static final String SEARCH_USERS_PATH = SEARCH_PATH + "/users";

    /**
     * Constructor to init a {@link GitHubSearchManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSearchManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSearchManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSearchManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSearchManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSearchManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSearchManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSearchManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSearchManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubSearchManager() {
        super();
    }

    public SearchResults searchCode(String q) throws IOException {
        return searchCode(q, LIBRARY_OBJECT);
    }

    public <T> T searchCode(String q, ReturnFormat format) throws IOException {
        return searchCode(q, null, format);
    }

    public SearchResults searchCode(String q, Params queryParams) throws IOException {
        return searchCode(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchCode(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Code.class, SEARCH_CODE_PATH, q, queryParams, format);
    }

    public SearchResults searchCommits(String q) throws IOException {
        return searchCommits(q, LIBRARY_OBJECT);
    }

    public <T> T searchCommits(String q, ReturnFormat format) throws IOException {
        return searchCommits(q, null, format);
    }

    public SearchResults searchCommits(String q, Params queryParams) throws IOException {
        return searchCommits(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchCommits(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(GitCommit.class, SEARCH_COMMITS_PATH, q, queryParams, format);
    }

    public SearchResults searchIssuesNPullRequests(String q) throws IOException {
        return searchIssuesNPullRequests(q, LIBRARY_OBJECT);
    }

    public <T> T searchIssuesNPullRequests(String q, ReturnFormat format) throws IOException {
        return searchIssuesNPullRequests(q, null, format);
    }

    public SearchResults searchIssuesNPullRequests(String q, Params queryParams) throws IOException {
        return searchIssuesNPullRequests(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchIssuesNPullRequests(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(PullRequest.class, SEARCH_ISSUES_PATH, q, queryParams, format);
    }

    public SearchResults searchLabels(String q, Repository repository) throws IOException {
        return searchLabels(q, repository.getId(), LIBRARY_OBJECT);
    }

    public <T> T searchLabels(String q, Repository repository, ReturnFormat format) throws IOException {
        return searchLabels(q, repository.getId(), format);
    }

    public SearchResults searchLabels(String q, long repositoryId) throws IOException {
        return searchLabels(q, repositoryId, LIBRARY_OBJECT);
    }

    public <T> T searchLabels(String q, long repositoryId, ReturnFormat format) throws IOException {
        return searchLabels(q, repositoryId, null, format);
    }

    public SearchResults searchLabels(String q, Repository repository, Params queryParams) throws IOException {
        return searchLabels(q, repository.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchLabels(String q, Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return searchLabels(q, repository.getId(), queryParams, format);
    }

    public SearchResults searchLabels(String q, long repositoryId, Params queryParams) throws IOException {
        return searchLabels(q, repositoryId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchLabels(String q, long repositoryId, Params queryParams, ReturnFormat format) throws IOException {
        if (queryParams == null)
            queryParams = new Params();
        queryParams.addParam("repository_id", repositoryId);
        return returnSearchResult(Label.class, SEARCH_LABELS_PATH, q, queryParams, format);
    }

    public SearchResults searchRepositories(String q) throws IOException {
        return searchRepositories(q, LIBRARY_OBJECT);
    }

    public <T> T searchRepositories(String q, ReturnFormat format) throws IOException {
        return searchRepositories(q, null, format);
    }

    public SearchResults searchRepositories(String q, Params queryParams) throws IOException {
        return searchRepositories(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchRepositories(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Repository.class, SEARCH_REPOSITORIES_PATH, q, queryParams, format);
    }

    public SearchResults searchTopics(String q) throws IOException {
        return searchTopics(q, LIBRARY_OBJECT);
    }

    public <T> T searchTopics(String q, ReturnFormat format) throws IOException {
        return searchTopics(q, null, format);
    }

    public SearchResults searchTopics(String q, Params queryParams) throws IOException {
        return searchTopics(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchTopics(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Topic.class, SEARCH_TOPICS_PATH, q, queryParams, format);
    }

    public SearchResults searchUsers(String q) throws IOException {
        return searchUsers(q, LIBRARY_OBJECT);
    }

    public <T> T searchUsers(String q, ReturnFormat format) throws IOException {
        return searchUsers(q, null, format);
    }

    public SearchResults searchUsers(String q, Params queryParams) throws IOException {
        return searchUsers(q, queryParams, LIBRARY_OBJECT);
    }

    public <T> T searchUsers(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(User.class, SEARCH_USERS_PATH, q, queryParams, format);
    }

    /**
     * Method to create a search results
     *
     * @param itemType: type of the result item to fetch
     * @param format    :              return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSearchResult(Class itemType, String path, String q, Params queryParams,
                                     ReturnFormat format) throws IOException {
        if (queryParams == null)
            queryParams = new Params();
        queryParams.addParam("q", q);
        String searchResultsResponse = sendGetRequest(path + queryParams.createQueryString());
        JSONObject jSearch = new JSONObject(searchResultsResponse);
        switch (format) {
            case JSON:
                return (T) jSearch;
            case LIBRARY_OBJECT:
                return (T) new SearchResults(itemType, jSearch);
            default:
                return (T) searchResultsResponse;
        }
    }

}
