package com.tecknobit.githubmanager.commits.commitcomments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment;
import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commits.GitHubCommitsManager.COMMITS_QUERY_PATH;

/**
 * The {@code GitHubCommitCommentsManager} class is useful to manage all GitHub's commit comments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments">
 * Commit comments</a>
 * @see GitHubManager
 **/
public class GitHubCommitCommentsManager extends GitHubManager {

    /**
     * {@code COMMENTS_PATH} constant for {@code "/comments"} path
     **/
    public static final String COMMENTS_PATH = "/comments";

    /**
     * Constructor to init a {@link GitHubCommitCommentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCommitCommentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCommitCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCommitCommentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCommitCommentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCommitCommentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCommitCommentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitCommentsManager} <br>
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
    public GitHubCommitCommentsManager() {
        super();
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public ArrayList<CommitComment> getRepositoryCommitComments(Repository repository) throws IOException {
        return getRepositoryCommitComments(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public <T> T getRepositoryCommitComments(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryCommitComments(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public ArrayList<CommitComment> getRepositoryCommitComments(String owner, String repo) throws IOException {
        return getRepositoryCommitComments(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public <T> T getRepositoryCommitComments(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCommitCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH), format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public ArrayList<CommitComment> getRepositoryCommitComments(Repository repository,
                                                                Params queryParams) throws IOException {
        return getRepositoryCommitComments(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public <T> T getRepositoryCommitComments(Repository repository, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getRepositoryCommitComments(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public ArrayList<CommitComment> getRepositoryCommitComments(String owner, String repo,
                                                                Params queryParams) throws IOException {
        return getRepositoryCommitComments(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments-for-a-repository">
     * List commit comments for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments")
    public <T> T getRepositoryCommitComments(String owner, String repo, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnCommitCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a commit comment
     *
     * @param repository: the repository from fetch the comment
     * @param commentId:  the unique identifier of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#get-a-commit-comment">
     * Get a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment getCommitComment(Repository repository, long commentId) throws IOException {
        return getCommitComment(repository.getOwner().getLogin(), repository.getName(), commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a commit comment
     *
     * @param repository: the repository from fetch the comment
     * @param commentId:  the unique identifier of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#get-a-commit-comment">
     * Get a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T getCommitComment(Repository repository, long commentId, ReturnFormat format) throws IOException {
        return getCommitComment(repository.getOwner().getLogin(), repository.getName(), commentId, format);
    }

    /**
     * Method to get a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#get-a-commit-comment">
     * Get a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment getCommitComment(String owner, String repo, long commentId) throws IOException {
        return getCommitComment(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#get-a-commit-comment">
     * Get a commit comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T getCommitComment(String owner, String repo, long commentId, ReturnFormat format) throws IOException {
        return returnCommitComment(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/"
                + commentId), format);
    }

    /**
     * Method to update a commit comment
     *
     * @param repository: the repository where update the comment
     * @param comment:    the comment to update
     * @param body:       the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment updateCommitComment(Repository repository, CommitComment comment, String body) throws IOException {
        return updateCommitComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(), body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a commit comment
     *
     * @param repository: the repository where update the comment
     * @param comment:    the comment to update
     * @param body:       the contents of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T updateCommitComment(Repository repository, CommitComment comment, String body,
                                     ReturnFormat format) throws IOException {
        return updateCommitComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(), body,
                format);
    }

    /**
     * Method to update a commit comment
     *
     * @param repository: the repository where update the comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment updateCommitComment(Repository repository, long commentId, String body) throws IOException {
        return updateCommitComment(repository.getOwner().getLogin(), repository.getName(), commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a commit comment
     *
     * @param repository: the repository where update the comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the contents of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T updateCommitComment(Repository repository, long commentId, String body,
                                     ReturnFormat format) throws IOException {
        return updateCommitComment(repository.getOwner().getLogin(), repository.getName(), commentId, body, format);
    }

    /**
     * Method to update a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment updateCommitComment(String owner, String repo, CommitComment comment,
                                             String body) throws IOException {
        return updateCommitComment(owner, repo, comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the contents of the comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T updateCommitComment(String owner, String repo, CommitComment comment, String body,
                                     ReturnFormat format) throws IOException {
        return updateCommitComment(owner, repo, comment.getId(), body, format);
    }

    /**
     * Method to update a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public CommitComment updateCommitComment(String owner, String repo, long commentId, String body) throws IOException {
        return updateCommitComment(owner, repo, commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the contents of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#update-a-commit-comment">
     * Update a commit comment</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public <T> T updateCommitComment(String owner, String repo, long commentId, String body,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnCommitComment(sendPatchRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/"
                + commentId, payload), format);
    }

    /**
     * Method to delete a commit comment
     *
     * @param repository: the repository where delete the commit comment
     * @param comment:    the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#delete-a-commit-comment">
     * Delete a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public boolean deleteCommitComment(Repository repository, CommitComment comment) {
        return deleteCommitComment(repository.getOwner().getLogin(), repository.getName(), comment.getId());
    }

    /**
     * Method to delete a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#delete-a-commit-comment">
     * Delete a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public boolean deleteCommitComment(String owner, String repo, CommitComment comment) {
        return deleteCommitComment(owner, repo, comment.getId());
    }

    /**
     * Method to delete a commit comment
     *
     * @param repository: the repository where delete the commit comment
     * @param commentId:  the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#delete-a-commit-comment">
     * Delete a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public boolean deleteCommitComment(Repository repository, long commentId) {
        return deleteCommitComment(repository.getOwner().getLogin(), repository.getName(), commentId);
    }

    /**
     * Method to delete a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#delete-a-commit-comment">
     * Delete a commit comment</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}")
    public boolean deleteCommitComment(String owner, String repo, long commentId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/" + commentId);
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
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(Repository repository, Commit commit) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @param commit:     the commit from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(Repository repository, Commit commit, ReturnFormat format) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(Repository repository, String commitSha) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository: the repository from fetch the list
     * @param commitSha:  the SHA of the commit
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(Repository repository, String commitSha, ReturnFormat format) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commitSha, format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(String owner, String repo, Commit commit) throws IOException {
        return getCommitComments(owner, repo, commit.getSha(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(String owner, String repo, Commit commit, ReturnFormat format) throws IOException {
        return getCommitComments(owner, repo, commit.getSha(), format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(String owner, String repo, String commitSha) throws IOException {
        return getCommitComments(owner, repo, commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(String owner, String repo, String commitSha, ReturnFormat format) throws IOException {
        return returnCommitCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH +
                commitSha + COMMENTS_PATH), format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
     * @param commit:      the commit from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(Repository repository, Commit commit,
                                                      Params queryParams) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
     * @param commit:      the commit from fetch the list
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(Repository repository, Commit commit, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), queryParams,
                format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
     * @param commitSha:   the SHA of the commit
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(Repository repository, String commitSha,
                                                      Params queryParams) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commitSha, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param repository:  the repository from fetch the list
     * @param commitSha:   the SHA of the commit
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(Repository repository, String commitSha, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getCommitComments(repository.getOwner().getLogin(), repository.getName(), commitSha, queryParams, format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commit:      the commit from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(String owner, String repo, Commit commit,
                                                      Params queryParams) throws IOException {
        return getCommitComments(owner, repo, commit.getSha(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commit:      the commit from fetch the list
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(String owner, String repo, Commit commit, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getCommitComments(owner, repo, commit.getSha(), queryParams, format);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commitSha:   the SHA of the commit
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit comments list as {@link ArrayList} of {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public ArrayList<CommitComment> getCommitComments(String owner, String repo, String commitSha,
                                                      Params queryParams) throws IOException {
        return getCommitComments(owner, repo, commitSha, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the commit comments list
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commitSha:   the SHA of the commit
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
     * @return commit comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#list-commit-comments">
     * List commit comments</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T getCommitComments(String owner, String repo, String commitSha, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return returnCommitCommentsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH +
                commitSha + COMMENTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a commit comments list
     *
     * @param commitCommentsListResponse: obtained from GitHub's response
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return commit comments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitCommentsList(String commitCommentsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commitCommentsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<CommitComment> comments = new ArrayList<>();
                JSONArray jComments = new JSONArray(commitCommentsListResponse);
                for (int j = 0; j < jComments.length(); j++)
                    comments.add(new CommitComment(jComments.getJSONObject(j)));
                return (T) comments;
            default:
                return (T) commitCommentsListResponse;
        }
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commit:     the commit where create the comment
     * @param body:       the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(Repository repository, Commit commit, String body) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commit:     the commit where create the comment
     * @param body:       the contents of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(Repository repository, Commit commit, String body,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), body, format);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commitSha:  the SHA of the commit
     * @param body:       the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(Repository repository, String commitSha, String body) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commitSha, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commitSha:  the SHA of the commit
     * @param body:       the contents of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(Repository repository, String commitSha, String body,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commitSha, body, format);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit where create the comment
     * @param body:   the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(String owner, String repo, Commit commit, String body) throws IOException {
        return createCommitComment(owner, repo, commit.getSha(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param commit: the commit where create the comment
     * @param body:   the contents of the comment
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(String owner, String repo, Commit commit, String body,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(owner, repo, commit.getSha(), body, format);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param body:      the contents of the comment
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(String owner, String repo, String commitSha, String body) throws IOException {
        return createCommitComment(owner, repo, commitSha, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param body:      the contents of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(String owner, String repo, String commitSha, String body,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnCommitComment(sendPostRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH +
                commitSha + COMMENTS_PATH, payload), format);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commit:     the commit where create the comment
     * @param comment:    the comment to create
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(Repository repository, Commit commit,
                                             CommitComment comment) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), comment,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commit:     the commit where create the comment
     * @param comment:    the comment to create
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(Repository repository, Commit commit, CommitComment comment,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commit.getSha(), comment,
                format);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commitSha:  the SHA of the commit
     * @param comment:    the comment to create
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(Repository repository, String commitSha,
                                             CommitComment comment) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commitSha, comment,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param repository: the repository where create the comment
     * @param commitSha:  the SHA of the commit
     * @param comment:    the comment to create
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(Repository repository, String commitSha, CommitComment comment,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(repository.getOwner().getLogin(), repository.getName(), commitSha, comment, format);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param commit:  the commit where create the comment
     * @param comment: the comment to create
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(String owner, String repo, Commit commit,
                                             CommitComment comment) throws IOException {
        return createCommitComment(owner, repo, commit.getSha(), comment, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param commit:  the commit where create the comment
     * @param comment: the comment to create
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(String owner, String repo, Commit commit, CommitComment comment,
                                     ReturnFormat format) throws IOException {
        return createCommitComment(owner, repo, commit.getSha(), comment, format);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param comment:   the comment to create
     * @return commit comment as {@link CommitComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public CommitComment createCommitComment(String owner, String repo, String commitSha,
                                             CommitComment comment) throws IOException {
        return createCommitComment(owner, repo, commitSha, comment, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param comment:   the comment to create
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/comments#create-a-commit-comment">
     * Create a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/commits/{commit_sha}/comments")
    public <T> T createCommitComment(String owner, String repo, String commitSha, CommitComment comment,
                                     ReturnFormat format) throws IOException {
        Params payload = new Params();
        JSONObject jComment = new JSONObject(comment);
        for (String key : jComment.keySet())
            payload.addParam(key, jComment.get(key));
        return returnCommitComment(sendPostRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH +
                commitSha + COMMENTS_PATH, payload), format);
    }

    /**
     * Method to create a commit comment
     *
     * @param commitCommentResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return commit comment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitComment(String commitCommentResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(commitCommentResponse);
            case LIBRARY_OBJECT:
                return (T) new CommitComment(new JSONObject(commitCommentResponse));
            default:
                return (T) commitCommentResponse;
        }
    }

}
