package com.tecknobit.githubmanager.issues.comments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.comments.records.IssueComment;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commitcomments.GitHubCommitCommentsManager.COMMENTS_PATH;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;

/**
 * The {@code GitHubIssueCommentsManager} class is useful to manage all GitHub's issue comments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments">
 * Issue comments</a>
 * @see GitHubManager
 **/
public class GitHubIssueCommentsManager extends GitHubManager {

    /**
     * {@code ISSUE_COMMENTS_PATH} constant for {@code "/issues/comments"} path
     **/
    public static final String ISSUES_COMMENTS_PATH = "/" + ISSUES_PATH + COMMENTS_PATH;

    /**
     * Constructor to init a {@link GitHubIssueCommentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubIssueCommentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubIssueCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubIssueCommentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubIssueCommentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubIssueCommentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubIssueCommentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueCommentsManager} <br>
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
    public GitHubIssueCommentsManager() {
        super();
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository: the repository from fetch the list
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public ArrayList<IssueComment> getRepositoryIssueComments(Repository repository) throws Exception {
        return getRepositoryIssueComments(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public <T> T getRepositoryIssueComments(Repository repository, ReturnFormat format) throws Exception {
        return getRepositoryIssueComments(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public ArrayList<IssueComment> getRepositoryIssueComments(String owner, String repo) throws Exception {
        return getRepositoryIssueComments(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public <T> T getRepositoryIssueComments(String owner, String repo, ReturnFormat format) throws Exception {
        return returnIssueCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH),
                format);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Sort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public ArrayList<IssueComment> getRepositoryIssueComments(Repository repository, Params queryParams) throws Exception {
        return getRepositoryIssueComments(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Sort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public <T> T getRepositoryIssueComments(Repository repository, Params queryParams, ReturnFormat format) throws Exception {
        return getRepositoryIssueComments(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Sort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public ArrayList<IssueComment> getRepositoryIssueComments(String owner, String repo,
                                                              Params queryParams) throws Exception {
        return getRepositoryIssueComments(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments for a repository <br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Sort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments-for-a-repository">
     * List issue comments for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments")
    public <T> T getRepositoryIssueComments(String owner, String repo, Params queryParams,
                                            ReturnFormat format) throws Exception {
        return returnIssueCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get an issue comment
     *
     * @param repository: the repository from fetch the comment
     * @param commentId:  the unique identifier of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#get-an-issue-comment">
     * Get an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment getIssueComment(Repository repository, long commentId) throws Exception {
        return getIssueComment(repository.getOwner().getLogin(), repository.getName(), commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue comment
     *
     * @param repository: the repository from fetch the comment
     * @param commentId:  the unique identifier of the comment
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#get-an-issue-comment">
     * Get an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T getIssueComment(Repository repository, long commentId, ReturnFormat format) throws Exception {
        return getIssueComment(repository.getOwner().getLogin(), repository.getName(), commentId, format);
    }

    /**
     * Method to get an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#get-an-issue-comment">
     * Get an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment getIssueComment(String owner, String repo, long commentId) throws Exception {
        return getIssueComment(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format     :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#get-an-issue-comment">
     * Get an issue comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T getIssueComment(String owner, String repo, long commentId, ReturnFormat format) throws Exception {
        return returnIssueComment(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH
                + "/" + commentId), format);
    }

    /**
     * Method to update an issue comment
     *
     * @param repository: the repository where update the comment
     * @param comment:    the comment to update
     * @param body:       the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment updateIssueComment(Repository repository, IssueComment comment, String body) throws Exception {
        return updateIssueComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(), body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue comment
     *
     * @param repository: the repository where update the comment
     * @param comment:    the comment to update
     * @param body:       the contents of the comment
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T updateIssueComment(Repository repository, IssueComment comment, String body,
                                    ReturnFormat format) throws Exception {
        return updateIssueComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(), body,
                format);
    }

    /**
     * Method to update an issue comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment updateIssueComment(String owner, String repo, IssueComment comment, String body) throws Exception {
        return updateIssueComment(owner, repo, comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the contents of the comment
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T updateIssueComment(String owner, String repo, IssueComment comment, String body,
                                    ReturnFormat format) throws Exception {
        return updateIssueComment(owner, repo, comment.getId(), body, format);
    }

    /**
     * Method to update an issue comment
     *
     * @param repository: the repository where update the comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment updateIssueComment(Repository repository, long commentId, String body) throws Exception {
        return updateIssueComment(repository.getOwner().getLogin(), repository.getName(), commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue comment
     *
     * @param repository: the repository where update the comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the contents of the comment
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T updateIssueComment(Repository repository, long commentId, String body,
                                    ReturnFormat format) throws Exception {
        return updateIssueComment(repository.getOwner().getLogin(), repository.getName(), commentId, body, format);
    }

    /**
     * Method to update an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public IssueComment updateIssueComment(String owner, String repo, long commentId, String body) throws Exception {
        return updateIssueComment(owner, repo, commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the contents of the comment
     * @param format     :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#update-an-issue-comment">
     * Update an issue comment</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public <T> T updateIssueComment(String owner, String repo, long commentId, String body,
                                    ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnIssueComment(sendPatchRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH
                + "/" + commentId, payload), format);
    }

    /**
     * Method to delete an issue comment
     *
     * @param repository: the repository where delete the comment
     * @param comment:    the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#delete-an-issue-comment">
     * Delete an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public boolean deleteIssueComment(Repository repository, IssueComment comment) {
        return deleteIssueComment(repository.getOwner().getLogin(), repository.getName(), comment.getId());
    }

    /**
     * Method to delete an issue comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#delete-an-issue-comment">
     * Delete an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public boolean deleteIssueComment(String owner, String repo, IssueComment comment) {
        return deleteIssueComment(owner, repo, comment.getId());
    }

    /**
     * Method to delete an issue comment
     *
     * @param repository: the repository where delete the comment
     * @param commentId:  the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#delete-an-issue-comment">
     * Delete an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public boolean deleteIssueComment(Repository repository, long commentId) {
        return deleteIssueComment(repository.getOwner().getLogin(), repository.getName(), commentId);
    }

    /**
     * Method to delete an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#delete-an-issue-comment">
     * Delete an issue comment</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}")
    public boolean deleteIssueComment(String owner, String repo, long commentId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH + "/" + commentId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(Repository repository, Issue issue) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issue.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(Repository repository, Issue issue, ReturnFormat format) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issue.getId(), format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(String owner, String repo, Issue issue) throws Exception {
        return getIssueComments(owner, repo, issue.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(String owner, String repo, Issue issue, ReturnFormat format) throws Exception {
        return getIssueComments(owner, repo, issue.getId(), format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(Repository repository, long issueNumber) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(Repository repository, long issueNumber, ReturnFormat format) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(String owner, String repo, long issueNumber) throws Exception {
        return getIssueComments(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(String owner, String repo, long issueNumber, ReturnFormat format) throws Exception {
        return returnIssueCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/"
                + ISSUES_PATH + "/" + issueNumber + COMMENTS_PATH), format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(Repository repository, Issue issue, Params queryParams) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issue.getId(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(Repository repository, Issue issue, Params queryParams,
                                  ReturnFormat format) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issue.getId(), queryParams,
                format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(String owner, String repo, Issue issue,
                                                    Params queryParams) throws Exception {
        return getIssueComments(owner, repo, issue.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(String owner, String repo, Issue issue, Params queryParams,
                                  ReturnFormat format) throws Exception {
        return getIssueComments(owner, repo, issue.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(Repository repository, long issueNumber,
                                                    Params queryParams) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(Repository repository, long issueNumber, Params queryParams,
                                  ReturnFormat format) throws Exception {
        return getIssueComments(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams, format);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return issue comments list as {@link ArrayList} of {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public ArrayList<IssueComment> getIssueComments(String owner, String repo, long issueNumber,
                                                    Params queryParams) throws Exception {
        return getIssueComments(owner, repo, issueNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the issue comments<br>
     * By default, Issue Comments are ordered by ascending ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#list-issue-comments">
     * List issue comments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T getIssueComments(String owner, String repo, long issueNumber, Params queryParams,
                                  ReturnFormat format) throws Exception {
        return returnIssueCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/"
                + ISSUES_PATH + "/" + issueNumber + COMMENTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an issue comments list
     *
     * @param issueCommentsListResponse : obtained from GitHub's response
     * @param format                    :              return type formatter -> {@link ReturnFormat}
     * @return issue comments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnIssueCommentsList(String issueCommentsListResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(issueCommentsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<IssueComment> issueComments = new ArrayList<>();
                JSONArray jIssueComments = new JSONArray(issueCommentsListResponse);
                for (int j = 0; j < jIssueComments.length(); j++)
                    issueComments.add(new IssueComment(jIssueComments.getJSONObject(j)));
                return (T) issueComments;
            default:
                return (T) issueCommentsListResponse;
        }
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where create the comment
     * @param issue:      the issue where create the comment
     * @param body:       the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public IssueComment createIssueComment(Repository repository, Issue issue, String body) throws Exception {
        return createIssueComment(repository.getOwner().getLogin(), repository.getName(), issue.getId(), body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository: the repository where create the comment
     * @param issue:      the issue where create the comment
     * @param body:       the contents of the comment
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T createIssueComment(Repository repository, Issue issue, String body, ReturnFormat format) throws Exception {
        return createIssueComment(repository.getOwner().getLogin(), repository.getName(), issue.getId(), body,
                format);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue where create the comment
     * @param body:  the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public IssueComment createIssueComment(String owner, String repo, Issue issue, String body) throws Exception {
        return createIssueComment(owner, repo, issue.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue where create the comment
     * @param body:  the contents of the comment
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T createIssueComment(String owner, String repo, Issue issue, String body,
                                    ReturnFormat format) throws Exception {
        return createIssueComment(owner, repo, issue.getId(), body, format);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where create the comment
     * @param issueNumber: the number that identifies the issue
     * @param body:        the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public IssueComment createIssueComment(Repository repository, long issueNumber, String body) throws Exception {
        return createIssueComment(repository.getOwner().getLogin(), repository.getName(), issueNumber, body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param repository:  the repository where create the comment
     * @param issueNumber: the number that identifies the issue
     * @param body:        the contents of the comment
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T createIssueComment(Repository repository, long issueNumber, String body,
                                    ReturnFormat format) throws Exception {
        return createIssueComment(repository.getOwner().getLogin(), repository.getName(), issueNumber, body, format);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param body:        the contents of the comment
     * @return issue comment as {@link IssueComment} custom object
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public IssueComment createIssueComment(String owner, String repo, long issueNumber, String body) throws Exception {
        return createIssueComment(owner, repo, issueNumber, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create an issue comment <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary
     * rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param body:        the contents of the comment
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     * @throws Exception when request has been go wrong -> you can use these methods to get more details about error:
     *                   <ul>
     *                       <li>
     *                           {@link #getErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #getJSONErrorResponse()}
     *                       </li>
     *                       <li>
     *                           {@link #printErrorResponse()}
     *                       </li>
     *                   </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/comments#create-an-issue-comment">
     * Create an issue comment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public <T> T createIssueComment(String owner, String repo, long issueNumber, String body, ReturnFormat format) throws Exception {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnIssueComment(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/"
                + ISSUES_PATH + "/" + issueNumber + COMMENTS_PATH, payload), format);
    }

    /**
     * Method to create an issue comment
     *
     * @param issueCommentResponse : obtained from GitHub's response
     * @param format               :              return type formatter -> {@link ReturnFormat}
     * @return issue comment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnIssueComment(String issueCommentResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONObject(issueCommentResponse);
            case LIBRARY_OBJECT:
                return (T) new IssueComment(new JSONObject(issueCommentResponse));
            default:
                return (T) issueCommentResponse;
        }
    }

}
