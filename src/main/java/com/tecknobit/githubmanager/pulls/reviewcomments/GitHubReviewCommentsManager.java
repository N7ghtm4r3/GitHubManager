package com.tecknobit.githubmanager.pulls.reviewcomments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment;
import com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment.Side;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commitcomments.GitHubCommitCommentsManager.COMMENTS_PATH;

/**
 * The {@code GitHubReviewCommentsManager} class is useful to manage all GitHub's review comments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments">
 * Pull request review comments</a>
 * @see GitHubManager
 **/
public class GitHubReviewCommentsManager extends GitHubManager {

    /**
     * {@code PULLS_COMMENTS_PATH} constant for {@code "/pulls/comments"} path
     **/
    public static final String PULLS_COMMENTS_PATH = PULLS_PATH + COMMENTS_PATH;

    /**
     * {@code REPLIES_PATH} constant for {@code "/replies"} path
     **/
    public static final String REPLIES_PATH = "/replies";

    /**
     * Constructor to init a {@link GitHubReviewCommentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReviewCommentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReviewCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReviewCommentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReviewCommentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReviewCommentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReviewCommentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewCommentsManager} <br>
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
    public GitHubReviewCommentsManager() {
        super();
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param repository: the repository from fetch the list
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public ArrayList<ReviewComment> getRepositoryReviewComments(Repository repository) throws IOException {
        return getRepositoryReviewComments(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public <T> T getRepositoryReviewComments(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryReviewComments(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public ArrayList<ReviewComment> getRepositoryReviewComments(String owner, String repo) throws IOException {
        return getRepositoryReviewComments(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public <T> T getRepositoryReviewComments(String owner, String repo, ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH),
                format);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public ArrayList<ReviewComment> getRepositoryReviewComments(Repository repository,
                                                                Params queryParams) throws IOException {
        return getRepositoryReviewComments(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public <T> T getRepositoryReviewComments(Repository repository, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return getRepositoryReviewComments(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public ArrayList<ReviewComment> getRepositoryReviewComments(String owner, String repo,
                                                                Params queryParams) throws IOException {
        return getRepositoryReviewComments(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the review comments for all pull requests in a repository. By default, review comments
     * are in ascending order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/pulls#list-pull-requests">
     * List review comments in a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments")
    public <T> T getRepositoryReviewComments(String owner, String repo, Params queryParams,
                                             ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to provide details for a review comment
     *
     * @param repository: the repository from fetch the review comment
     * @param commentId:  the unique identifier of the comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#get-a-review-comment-for-a-pull-request">
     * Get a review comment for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment getPullRequestReviewComment(Repository repository, long commentId) throws IOException {
        return getPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), commentId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to provide details for a review comment
     *
     * @param repository: the repository from fetch the review comment
     * @param commentId:  the unique identifier of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#get-a-review-comment-for-a-pull-request">
     * Get a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T getPullRequestReviewComment(Repository repository, long commentId, ReturnFormat format) throws IOException {
        return getPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), commentId, format);
    }

    /**
     * Method to provide details for a review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#get-a-review-comment-for-a-pull-request">
     * Get a review comment for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment getPullRequestReviewComment(String owner, String repo, long commentId) throws IOException {
        return getPullRequestReviewComment(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to provide details for a review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#get-a-review-comment-for-a-pull-request">
     * Get a review comment for a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T getPullRequestReviewComment(String owner, String repo, long commentId,
                                             ReturnFormat format) throws IOException {
        return returnReviewComment(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH
                + "/" + commentId), format);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param repository: the repository where update the review comment
     * @param comment:    the comment to update
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment updatePullRequestReviewComment(Repository repository, ReviewComment comment,
                                                        String body) throws IOException {
        return updatePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                body, LIBRARY_OBJECT);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param repository: the repository where update the review comment
     * @param comment:    the comment to update
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T updatePullRequestReviewComment(Repository repository, ReviewComment comment, String body,
                                                ReturnFormat format) throws IOException {
        return updatePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                body, format);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param repository: the repository where update the review comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment updatePullRequestReviewComment(Repository repository, long commentId,
                                                        String body) throws IOException {
        return updatePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), commentId, body,
                LIBRARY_OBJECT);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param repository: the repository where update the review comment
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T updatePullRequestReviewComment(Repository repository, long commentId, String body,
                                                ReturnFormat format) throws IOException {
        return updatePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), commentId, body,
                format);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment updatePullRequestReviewComment(String owner, String repo, ReviewComment comment,
                                                        String body) throws IOException {
        return updatePullRequestReviewComment(owner, repo, comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to update
     * @param body:    the text of the reply to the review comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T updatePullRequestReviewComment(String owner, String repo, ReviewComment comment, String body,
                                                ReturnFormat format) throws IOException {
        return updatePullRequestReviewComment(owner, repo, comment.getId(), body, format);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public ReviewComment updatePullRequestReviewComment(String owner, String repo, long commentId,
                                                        String body) throws IOException {
        return updatePullRequestReviewComment(owner, repo, commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to Update a review comment for a pull request
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param body:      the text of the reply to the review comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#update-a-review-comment-for-a-pull-request">
     * Update a review comment for a pull request</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public <T> T updatePullRequestReviewComment(String owner, String repo, long commentId, String body,
                                                ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnReviewComment(sendPatchRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH
                + "/" + commentId, payload), format);
    }

    /**
     * Method delete a review comment
     *
     * @param repository: the repository where delete the review comment
     * @param comment:    the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#delete-a-review-comment-for-a-pull-request">
     * Delete a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public boolean deletePullRequestReviewComment(Repository repository, ReviewComment comment) {
        return deletePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), comment.getId());
    }

    /**
     * Method delete a review comment
     *
     * @param repository: the repository where delete the review comment
     * @param commentId:  the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#delete-a-review-comment-for-a-pull-request">
     * Delete a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public boolean deletePullRequestReviewComment(Repository repository, long commentId) {
        return deletePullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), commentId);
    }

    /**
     * Method delete a review comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#delete-a-review-comment-for-a-pull-request">
     * Delete a review comment for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public boolean deletePullRequestReviewComment(String owner, String repo, ReviewComment comment) {
        return deletePullRequestReviewComment(owner, repo, comment.getId());
    }

    /**
     * Method delete a review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#delete-a-review-comment-for-a-pull-request">
     * Delete a review comment for a pull request</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}")
    public boolean deletePullRequestReviewComment(String owner, String repo, long commentId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH + "/" + commentId);
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
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository,
                                                                 PullRequest pullRequest) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo,
                                                                 PullRequest pullRequest) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo,
                                                                 long pullNumber) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                              ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + COMMENTS_PATH), format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @return review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all review comments for a pull request. By default, review comments are in ascending
     * order by ID
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link Issue.IssueSort}
     *                            - [string, default created]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default desc]
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#list-review-comments-on-a-pull-request">
     * List review comments on a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + COMMENTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a review comments list
     *
     * @param reviewCommentsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return review comments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReviewComments(String reviewCommentsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(reviewCommentsResponse);
            case LIBRARY_OBJECT:
                ArrayList<ReviewComment> reviewComments = new ArrayList<>();
                JSONArray jReviewComments = new JSONArray(reviewCommentsResponse);
                for (int j = 0; j < jReviewComments.length(); j++)
                    reviewComments.add(new ReviewComment(jReviewComments.getJSONObject(j)));
                return (T) reviewComments;
            default:
                return (T) reviewCommentsResponse;
        }
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository:  the repository where create the review comment
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(Repository repository, PullRequest pullRequest, String body,
                                                        String commitId, String path, int line) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), body, commitId, path, line, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository:  the repository where create the review comment
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(Repository repository, PullRequest pullRequest, String body,
                                                String commitId, String path, int line, ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), body, commitId, path, line, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository: the repository where create the review comment
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(Repository repository, long pullNumber, String body,
                                                        String commitId, String path, int line) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), pullNumber, body,
                commitId, path, line, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository: the repository where create the review comment
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(Repository repository, long pullNumber, String body, String commitId,
                                                String path, int line, ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), pullNumber, body,
                commitId, path, line, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(String owner, String repo, PullRequest pullRequest, String body,
                                                        String commitId, String path, int line) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullRequest.getNumber(), body, commitId, path, line,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(String owner, String repo, PullRequest pullRequest, String body,
                                                String commitId, String path, int line, ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullRequest.getNumber(), body, commitId, path, line, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(String owner, String repo, long pullNumber, String body,
                                                        String commitId, String path, int line) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullNumber, body, commitId, path, line, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(String owner, String repo, long pullNumber, String body, String commitId,
                                                String path, int line, ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullNumber, body, commitId, path, line, null, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository:  the repository where create the review comment
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                            request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                            in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                            in white and are shown for context. For a multi-line comment, side represents whether
     *                            the last line of the comment range is a deletion or addition. For more information,
     *                            see "Diff view options" in the GitHub Help documentation, consonant available
     *                            {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                            in_reply_to.</b>
     *                            The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                            comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                            request" in the GitHub Help documentation- [integer]
     *                        </li>
     *                        <li>
     *                            {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                            </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                            To learn more about multi-line comments, see "Commenting on a pull request"
     *                            in the GitHub Help documentation. See side in this table for additional context,
     *                            consonant available {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                            review comment with "List review comments on a pull request". When specified, all
     *                            parameters other than body in the request body are ignored - [integer]
     *                        </li>
     *                     </ul>
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(Repository repository, PullRequest pullRequest, String body,
                                                        String commitId, String path, int line,
                                                        Params bodyParams) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), body, commitId, path, line, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository:  the repository where create the review comment
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                            request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                            in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                            in white and are shown for context. For a multi-line comment, side represents whether
     *                            the last line of the comment range is a deletion or addition. For more information,
     *                            see "Diff view options" in the GitHub Help documentation, consonant available
     *                            {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                            in_reply_to.</b>
     *                            The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                            comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                            request" in the GitHub Help documentation- [integer]
     *                        </li>
     *                        <li>
     *                            {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                            </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                            To learn more about multi-line comments, see "Commenting on a pull request"
     *                            in the GitHub Help documentation. See side in this table for additional context,
     *                            consonant available {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                            review comment with "List review comments on a pull request". When specified, all
     *                            parameters other than body in the request body are ignored - [integer]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(Repository repository, PullRequest pullRequest, String body,
                                                String commitId, String path, int line, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), body, commitId, path, line, bodyParams, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository: the repository where create the review comment
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                           request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                           in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                           in white and are shown for context. For a multi-line comment, side represents whether
     *                           the last line of the comment range is a deletion or addition. For more information,
     *                           see "Diff view options" in the GitHub Help documentation, consonant available
     *                           {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                           in_reply_to.</b>
     *                           The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                           comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                           request" in the GitHub Help documentation- [integer]
     *                       </li>
     *                       <li>
     *                           {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                           </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                           To learn more about multi-line comments, see "Commenting on a pull request"
     *                           in the GitHub Help documentation. See side in this table for additional context,
     *                           consonant available {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                           review comment with "List review comments on a pull request". When specified, all
     *                           parameters other than body in the request body are ignored - [integer]
     *                       </li>
     *                    </ul>
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(Repository repository, long pullNumber, String body,
                                                        String commitId, String path, int line,
                                                        Params bodyParams) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), pullNumber, body,
                commitId, path, line, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param repository: the repository where create the review comment
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                           request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                           in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                           in white and are shown for context. For a multi-line comment, side represents whether
     *                           the last line of the comment range is a deletion or addition. For more information,
     *                           see "Diff view options" in the GitHub Help documentation, consonant available
     *                           {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                           in_reply_to.</b>
     *                           The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                           comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                           request" in the GitHub Help documentation- [integer]
     *                       </li>
     *                       <li>
     *                           {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                           </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                           To learn more about multi-line comments, see "Commenting on a pull request"
     *                           in the GitHub Help documentation. See side in this table for additional context,
     *                           consonant available {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                           review comment with "List review comments on a pull request". When specified, all
     *                           parameters other than body in the request body are ignored - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(Repository repository, long pullNumber, String body, String commitId,
                                                String path, int line, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(repository.getOwner().getLogin(), repository.getName(), pullNumber, body,
                commitId, path, line, bodyParams, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                            request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                            in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                            in white and are shown for context. For a multi-line comment, side represents whether
     *                            the last line of the comment range is a deletion or addition. For more information,
     *                            see "Diff view options" in the GitHub Help documentation, consonant available
     *                            {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                            in_reply_to.</b>
     *                            The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                            comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                            request" in the GitHub Help documentation- [integer]
     *                        </li>
     *                        <li>
     *                            {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                            </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                            To learn more about multi-line comments, see "Commenting on a pull request"
     *                            in the GitHub Help documentation. See side in this table for additional context,
     *                            consonant available {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                            review comment with "List review comments on a pull request". When specified, all
     *                            parameters other than body in the request body are ignored - [integer]
     *                        </li>
     *                     </ul>
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(String owner, String repo, PullRequest pullRequest, String body,
                                                        String commitId, String path, int line,
                                                        Params bodyParams) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullRequest.getNumber(), body, commitId, path, line,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review comment
     * @param body:        the text of the reply to the review comment
     * @param commitId:    the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                     outdated if a subsequent commit modifies the line you specify as the position
     * @param path:        the relative path to the file that necessitates a comment
     * @param line:        the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                     the last line of the range that your comment applies to
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                            request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                            in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                            in white and are shown for context. For a multi-line comment, side represents whether
     *                            the last line of the comment range is a deletion or addition. For more information,
     *                            see "Diff view options" in the GitHub Help documentation, consonant available
     *                            {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                            in_reply_to.</b>
     *                            The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                            comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                            request" in the GitHub Help documentation- [integer]
     *                        </li>
     *                        <li>
     *                            {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                            </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                            To learn more about multi-line comments, see "Commenting on a pull request"
     *                            in the GitHub Help documentation. See side in this table for additional context,
     *                            consonant available {@link Side} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                            review comment with "List review comments on a pull request". When specified, all
     *                            parameters other than body in the request body are ignored - [integer]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(String owner, String repo, PullRequest pullRequest, String body,
                                                String commitId, String path, int line, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullRequest.getNumber(), body, commitId, path, line,
                bodyParams, format);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                           request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                           in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                           in white and are shown for context. For a multi-line comment, side represents whether
     *                           the last line of the comment range is a deletion or addition. For more information,
     *                           see "Diff view options" in the GitHub Help documentation, consonant available
     *                           {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                           in_reply_to.</b>
     *                           The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                           comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                           request" in the GitHub Help documentation- [integer]
     *                       </li>
     *                       <li>
     *                           {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                           </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                           To learn more about multi-line comments, see "Commenting on a pull request"
     *                           in the GitHub Help documentation. See side in this table for additional context,
     *                           consonant available {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                           review comment with "List review comments on a pull request". When specified, all
     *                           parameters other than body in the request body are ignored - [integer]
     *                       </li>
     *                    </ul>
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createPullRequestReviewComment(String owner, String repo, long pullNumber, String body,
                                                        String commitId, String path, int line,
                                                        Params bodyParams) throws IOException {
        return createPullRequestReviewComment(owner, repo, pullNumber, body, commitId, path, line, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a review comment in the pull request diff
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param body:       the text of the reply to the review comment
     * @param commitId:   the SHA of the commit needing a comment. Not using the latest commit SHA may render your comment
     *                    outdated if a subsequent commit modifies the line you specify as the position
     * @param path:       the relative path to the file that necessitates a comment
     * @param line:       the line of the blob in the pull request diff that the comment applies to. For a multi-line comment,
     *                    the last line of the range that your comment applies to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "side"} -> in a split diff view, the side of the diff that the pull
     *                           request's changes appear on. Can be LEFT or RIGHT. Use LEFT for deletions that appear
     *                           in red. Use RIGHT for additions that appear in green or unchanged lines that appear
     *                           in white and are shown for context. For a multi-line comment, side represents whether
     *                           the last line of the comment range is a deletion or addition. For more information,
     *                           see "Diff view options" in the GitHub Help documentation, consonant available
     *                           {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "start_line"} -> <b>required when using multi-line comments unless using
     *                           in_reply_to.</b>
     *                           The {@code "start_line"} is the first line in the pull request diff that your multi-line
     *                           comment applies to. To learn more about multi-line comments, see "Commenting on a pull
     *                           request" in the GitHub Help documentation- [integer]
     *                       </li>
     *                       <li>
     *                           {@code "start_side"} -> <b>required when using multi-line comments unless using in_reply_to.
     *                           </b> The {@code "start_side"} is the starting side of the diff that the comment applies to.
     *                           To learn more about multi-line comments, see "Commenting on a pull request"
     *                           in the GitHub Help documentation. See side in this table for additional context,
     *                           consonant available {@link Side} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "in_reply_to"} -> the ID of the review comment to reply to. To find the ID of a
     *                           review comment with "List review comments on a pull request". When specified, all
     *                           parameters other than body in the request body are ignored - [integer]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comments#create-a-review-comment-for-a-pull-request">
     * Create a review comment for a pull request</a>
     * @implNote The position value equals the number of lines down from the first "@@" hunk header in the file you want
     * to add a comment. The line just below the "@@" line is position 1, the next line is position 2, and so on.
     * The position in the diff continues to increase through lines of whitespace and additional hunks until the
     * beginning of a new file. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    public <T> T createPullRequestReviewComment(String owner, String repo, long pullNumber, String body, String commitId,
                                                String path, int line, Params bodyParams,
                                                ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("body", body);
        bodyParams.addParam("commit_id", commitId);
        bodyParams.addParam("path", path);
        bodyParams.addParam("line", line);
        return returnReviewComment(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + COMMENTS_PATH, bodyParams), format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository:  the repository where create the reply review comment
     * @param pullRequest: the pull request where create the reply review comment
     * @param comment:     the comment to reply
     * @param body:        the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(Repository repository, PullRequest pullRequest, ReviewComment comment,
                                                  String body) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository:  the repository where create the reply review comment
     * @param pullRequest: the pull request where create the reply review comment
     * @param comment:     the comment to reply
     * @param body:        the text of the reply to the review comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(Repository repository, PullRequest pullRequest, ReviewComment comment,
                                          String body, ReturnFormat format) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                comment.getId(), body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository: the repository where create the reply review comment
     * @param pullNumber: the number that identifies the pull request
     * @param comment:    the comment to reply
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(Repository repository, long pullNumber, ReviewComment comment,
                                                  String body) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository: the repository where create the reply review comment
     * @param pullNumber: the number that identifies the pull request
     * @param comment:    the comment to reply
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(Repository repository, long pullNumber, ReviewComment comment, String body,
                                          ReturnFormat format) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                comment.getId(), body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository:  the repository where create the reply review comment
     * @param pullRequest: the pull request where create the reply review comment
     * @param commentId:   the unique identifier of the comment
     * @param body:        the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(Repository repository, PullRequest pullRequest, long commentId,
                                                  String body) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository:  the repository where create the reply review comment
     * @param pullRequest: the pull request where create the reply review comment
     * @param commentId:   the unique identifier of the comment
     * @param body:        the text of the reply to the review comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(Repository repository, PullRequest pullRequest, long commentId, String body,
                                          ReturnFormat format) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                commentId, body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository: the repository where create the reply review comment
     * @param pullNumber: the number that identifies the pull request
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(Repository repository, long pullNumber, long commentId,
                                                  String body) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullNumber, commentId,
                body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param repository: the repository where create the reply review comment
     * @param pullNumber: the number that identifies the pull request
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(Repository repository, long pullNumber, long commentId, String body,
                                          ReturnFormat format) throws IOException {
        return createReviewCommentReply(repository.getOwner().getLogin(), repository.getName(), pullNumber, commentId,
                body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the reply review comment
     * @param comment:     the comment to reply
     * @param body:        the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(String owner, String repo, PullRequest pullRequest, ReviewComment comment,
                                                  String body) throws IOException {
        return createReviewCommentReply(owner, repo, pullRequest.getNumber(), comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the reply review comment
     * @param comment:     the comment to reply
     * @param body:        the text of the reply to the review comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(String owner, String repo, PullRequest pullRequest, ReviewComment comment,
                                          String body, ReturnFormat format) throws IOException {
        return createReviewCommentReply(owner, repo, pullRequest.getNumber(), comment.getId(), body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param comment:    the comment to reply
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(String owner, String repo, long pullNumber, ReviewComment comment,
                                                  String body) throws IOException {
        return createReviewCommentReply(owner, repo, pullNumber, comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param comment:    the comment to reply
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(String owner, String repo, long pullNumber, ReviewComment comment, String body,
                                          ReturnFormat format) throws IOException {
        return createReviewCommentReply(owner, repo, pullNumber, comment.getId(), body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the reply review comment
     * @param commentId:   the unique identifier of the comment
     * @param body:        the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(String owner, String repo, PullRequest pullRequest, long commentId,
                                                  String body) throws IOException {
        return createReviewCommentReply(owner, repo, pullRequest.getNumber(), commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the reply review comment
     * @param commentId:   the unique identifier of the comment
     * @param body:        the text of the reply to the review comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(String owner, String repo, PullRequest pullRequest, long commentId, String body,
                                          ReturnFormat format) throws IOException {
        return createReviewCommentReply(owner, repo, pullRequest.getNumber(), commentId, body, format);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @return review comment as {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public ReviewComment createReviewCommentReply(String owner, String repo, long pullNumber, long commentId,
                                                  String body) throws IOException {
        return createReviewCommentReply(owner, repo, pullNumber, commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to creates a reply to a review comment for a pull request. For the comment_id, provide the ID of the review comment
     * you are replying to. This must be the ID of a top-level review comment, not a reply to that comment.
     * Replies to replies are not supported
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param commentId:  the unique identifier of the comment
     * @param body:       the text of the reply to the review comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/comment#create-a-reply-for-a-review-comment">
     * Create a reply for a review comment</a>
     * @implNote This endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/comments/{comment_id}/replies")
    public <T> T createReviewCommentReply(String owner, String repo, long pullNumber, long commentId, String body,
                                          ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnReviewComment(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + COMMENTS_PATH + "/" + commentId + REPLIES_PATH, payload), format);
    }

    /**
     * Method to create a review comment
     *
     * @param reviewCommentResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return review comment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReviewComment(String reviewCommentResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(reviewCommentResponse);
            case LIBRARY_OBJECT:
                return (T) new ReviewComment(new JSONObject(reviewCommentResponse));
            default:
                return (T) reviewCommentResponse;
        }
    }

}
