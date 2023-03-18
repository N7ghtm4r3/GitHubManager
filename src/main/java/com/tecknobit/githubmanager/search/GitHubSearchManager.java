package com.tecknobit.githubmanager.search;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.search.records.Code;
import com.tecknobit.githubmanager.search.records.SearchResults;
import com.tecknobit.githubmanager.search.records.SearchResults.CommitSort;
import com.tecknobit.githubmanager.search.records.SearchResults.IssueSort;
import com.tecknobit.githubmanager.search.records.SearchResults.RepositorySort;
import com.tecknobit.githubmanager.search.records.SearchResults.UserSort;
import com.tecknobit.githubmanager.search.records.Topic;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
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

    /**
     * Method to search for query terms inside a file
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-code">
     * Search code</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/code")
    public SearchResults searchCode(String q) throws IOException {
        return searchCode(q, LIBRARY_OBJECT);
    }

    /**
     * Method to search for query terms inside a file
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-code">
     * Search code</a>
     **/
    @RequestPath(method = GET, path = "/search/code")
    public <T> T searchCode(String q, ReturnFormat format) throws IOException {
        return searchCode(q, null, format);
    }

    /**
     * Method to search for query terms inside a file
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-code">
     * Search code</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/code")
    public SearchResults searchCode(String q, Params queryParams) throws IOException {
        return searchCode(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to search for query terms inside a file
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-code">
     * Search code</a>
     **/
    @RequestPath(method = GET, path = "/search/code")
    public <T> T searchCode(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Code.class, SEARCH_CODE_PATH, q, queryParams, format);
    }

    /**
     * Method to find commits via various criteria on the default branch (usually main)
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-commits">
     * Search commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/commits")
    public SearchResults searchCommits(String q) throws IOException {
        return searchCommits(q, LIBRARY_OBJECT);
    }

    /**
     * Method to find commits via various criteria on the default branch (usually main)
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-commits">
     * Search commits</a>
     **/
    @RequestPath(method = GET, path = "/search/commits")
    public <T> T searchCommits(String q, ReturnFormat format) throws IOException {
        return searchCommits(q, null, format);
    }

    /**
     * Method to find commits via various criteria on the default branch (usually main)
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link CommitSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-commits">
     * Search commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/commits")
    public SearchResults searchCommits(String q, Params queryParams) throws IOException {
        return searchCommits(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find commits via various criteria on the default branch (usually main)
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link CommitSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-commits">
     * Search commits</a>
     **/
    @RequestPath(method = GET, path = "/search/commits")
    public <T> T searchCommits(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(GitCommit.class, SEARCH_COMMITS_PATH, q, queryParams, format);
    }

    /**
     * Method to find issues by state and keyword
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-issues-and-pull-requests">
     * Search issues and pull requests</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/issues")
    public SearchResults searchIssuesNPullRequests(String q) throws IOException {
        return searchIssuesNPullRequests(q, LIBRARY_OBJECT);
    }

    /**
     * Method to find issues by state and keyword
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-issues-and-pull-requests">
     * Search issues and pull requests</a>
     **/
    @RequestPath(method = GET, path = "/search/issues")
    public <T> T searchIssuesNPullRequests(String q, ReturnFormat format) throws IOException {
        return searchIssuesNPullRequests(q, null, format);
    }

    /**
     * Method to find issues by state and keyword
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants,
     *                            available {@link IssueSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-issues-and-pull-requests">
     * Search issues and pull requests</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/issues")
    public SearchResults searchIssuesNPullRequests(String q, Params queryParams) throws IOException {
        return searchIssuesNPullRequests(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find issues by state and keyword
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants,
     *                            available {@link IssueSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-issues-and-pull-requests">
     * Search issues and pull requests</a>
     **/
    @RequestPath(method = GET, path = "/search/issues")
    public <T> T searchIssuesNPullRequests(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(PullRequest.class, SEARCH_ISSUES_PATH, q, queryParams, format);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:          the query contains one or more search keywords and qualifiers
     * @param repository: the repository from fetch the labels
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/search/labels")
    public SearchResults searchLabels(String q, Repository repository) throws IOException {
        return searchLabels(q, repository.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:          the query contains one or more search keywords and qualifiers
     * @param repository: the repository from fetch the labels
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/search/labels")
    public <T> T searchLabels(String q, Repository repository, ReturnFormat format) throws IOException {
        return searchLabels(q, repository.getId(), format);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:            the query contains one or more search keywords and qualifiers
     * @param repositoryId: the id of the repository
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/labels")
    public SearchResults searchLabels(String q, long repositoryId) throws IOException {
        return searchLabels(q, repositoryId, LIBRARY_OBJECT);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:            the query contains one or more search keywords and qualifiers
     * @param repositoryId: the id of the repository
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @RequestPath(method = GET, path = "/search/labels")
    public <T> T searchLabels(String q, long repositoryId, ReturnFormat format) throws IOException {
        return searchLabels(q, repositoryId, null, format);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param repository:  the repository from fetch the labels
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link Sort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/search/labels")
    public SearchResults searchLabels(String q, Repository repository, Params queryParams) throws IOException {
        return searchLabels(q, repository.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param repository:  the repository from fetch the labels
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link Sort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/search/labels")
    public <T> T searchLabels(String q, Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return searchLabels(q, repository.getId(), queryParams, format);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:            the query contains one or more search keywords and qualifiers
     * @param repositoryId: the id of the repository
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "sort"} -> sorts the results of your query, constants
     *                             available {@link Sort} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "order"} -> determines whether the first search result returned is the highest
     *                             number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                             unless you provide sort, constants available {@link Directions} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/labels")
    public SearchResults searchLabels(String q, long repositoryId, Params queryParams) throws IOException {
        return searchLabels(q, repositoryId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find labels in a repository with names or descriptions that match search keywords
     *
     * @param q:            the query contains one or more search keywords and qualifiers
     * @param repositoryId: the id of the repository
     * @param queryParams:  extra query params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "sort"} -> sorts the results of your query, constants
     *                             available {@link Sort} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "order"} -> determines whether the first search result returned is the highest
     *                             number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                             unless you provide sort, constants available {@link Directions} - [string]
     *                         </li>
     *                         <li>
     *                             {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                         </li>
     *                         <li>
     *                             {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-labels">
     * Search labels</a>
     **/
    @RequestPath(method = GET, path = "/search/labels")
    public <T> T searchLabels(String q, long repositoryId, Params queryParams, ReturnFormat format) throws IOException {
        if (queryParams == null)
            queryParams = new Params();
        queryParams.addParam("repository_id", repositoryId);
        return returnSearchResult(Label.class, SEARCH_LABELS_PATH, q, queryParams, format);
    }

    /**
     * Method to find repositories via various criteria
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-repositories">
     * Search repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/repositories")
    public SearchResults searchRepositories(String q) throws IOException {
        return searchRepositories(q, LIBRARY_OBJECT);
    }

    /**
     * Method to find repositories via various criteria
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-repositories">
     * Search repositories</a>
     **/
    @RequestPath(method = GET, path = "/search/repositories")
    public <T> T searchRepositories(String q, ReturnFormat format) throws IOException {
        return searchRepositories(q, null, format);
    }

    /**
     * Method to find repositories via various criteria
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-repositories">
     * Search repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/repositories")
    public SearchResults searchRepositories(String q, Params queryParams) throws IOException {
        return searchRepositories(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find repositories via various criteria
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link RepositorySort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-repositories">
     * Search repositories</a>
     **/
    @RequestPath(method = GET, path = "/search/repositories")
    public <T> T searchRepositories(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Repository.class, SEARCH_REPOSITORIES_PATH, q, queryParams, format);
    }

    /**
     * Method to find topics via various criteria. Results are sorted by best match
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-topics">
     * Search topics</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/topics")
    public SearchResults searchTopics(String q) throws IOException {
        return searchTopics(q, LIBRARY_OBJECT);
    }

    /**
     * Method to find topics via various criteria. Results are sorted by best match
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-topics">
     * Search topics</a>
     **/
    @RequestPath(method = GET, path = "/search/topics")
    public <T> T searchTopics(String q, ReturnFormat format) throws IOException {
        return searchTopics(q, null, format);
    }

    /**
     * Method to find topics via various criteria. Results are sorted by best match
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-topics">
     * Search topics</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/topics")
    public SearchResults searchTopics(String q, Params queryParams) throws IOException {
        return searchTopics(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find topics via various criteria. Results are sorted by best match
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-topics">
     * Search topics</a>
     **/
    @RequestPath(method = GET, path = "/search/topics")
    public <T> T searchTopics(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(Topic.class, SEARCH_TOPICS_PATH, q, queryParams, format);
    }

    /**
     * Method to find users via various criteria
     *
     * @param q: the query contains one or more search keywords and qualifiers
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-users">
     * Search users</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/users")
    public SearchResults searchUsers(String q) throws IOException {
        return searchUsers(q, LIBRARY_OBJECT);
    }

    /**
     * Method to find users via various criteria
     *
     * @param q:      the query contains one or more search keywords and qualifiers
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-users">
     * Search users</a>
     **/
    @RequestPath(method = GET, path = "/search/users")
    public <T> T searchUsers(String q, ReturnFormat format) throws IOException {
        return searchUsers(q, null, format);
    }

    /**
     * Method to find users via various criteria
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link UserSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return search results as {@link SearchResults} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-users">
     * Search users</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/search/users")
    public SearchResults searchUsers(String q, Params queryParams) throws IOException {
        return searchUsers(q, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to find users via various criteria
     *
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> sorts the results of your query, constants
     *                            available {@link UserSort} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "order"} -> determines whether the first search result returned is the highest
     *                            number of matches (desc) or lowest number of matches (asc). This parameter is ignored
     *                            unless you provide sort, constants available {@link Directions} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-users">
     * Search users</a>
     **/
    @RequestPath(method = GET, path = "/search/users")
    public <T> T searchUsers(String q, Params queryParams, ReturnFormat format) throws IOException {
        return returnSearchResult(User.class, SEARCH_USERS_PATH, q, queryParams, format);
    }

    /**
     * Method to create a search results
     *
     * @param itemType:    type of the result item to fetch
     * @param path:        path of the request
     * @param q:           the query contains one or more search keywords and qualifiers
     * @param queryParams: extra query params not mandatory
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return search results as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSearchResult(Class itemType, String path, String q, Params queryParams,
                                     ReturnFormat format) throws IOException {
        if(queryParams == null)
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
