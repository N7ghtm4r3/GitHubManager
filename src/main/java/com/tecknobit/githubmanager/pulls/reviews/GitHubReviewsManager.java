package com.tecknobit.githubmanager.pulls.reviews;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment;
import com.tecknobit.githubmanager.pulls.reviews.records.PullRequestReview;
import com.tecknobit.githubmanager.pulls.reviews.records.PullRequestReview.ReviewEvent;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.activity.events.GitHubEventsManager.EVENTS_PATH;
import static com.tecknobit.githubmanager.commits.commitcomments.GitHubCommitCommentsManager.COMMENTS_PATH;
import static com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment.returnReviewComments;

/**
 * The {@code GitHubReviewsManager} class is useful to manage all GitHub's reviews endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews">
 * Pull request reviews</a>
 * @see GitHubManager
 **/
public class GitHubReviewsManager extends GitHubManager {

    /**
     * {@code REVIEWS_PATH} constant for {@code "/reviews"} path
     **/
    public static final String REVIEWS_PATH = "/reviews";

    /**
     * {@code DISMISSALS_PATH} constant for {@code "/dismissals"} path
     **/
    public static final String DISMISSALS_PATH = "/dismissals";

    /**
     * Constructor to init a {@link GitHubReviewsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReviewsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReviewsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReviewsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReviewsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReviewsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReviewsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewsManager} <br>
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
    public GitHubReviewsManager() {
        super();
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(Repository repository,
                                                              PullRequest pullRequest) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(Repository repository, PullRequest pullRequest, ReturnFormat format) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(String owner, String repo,
                                                              PullRequest pullRequest) throws IOException {
        return getPullRequestReviews(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(String owner, String repo, PullRequest pullRequest,
                                       ReturnFormat format) throws IOException {
        return getPullRequestReviews(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(Repository repository, long pullNumber) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(String owner, String repo, long pullNumber) throws IOException {
        return getPullRequestReviews(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(String owner, String repo, long pullNumber, ReturnFormat format) throws IOException {
        return returnReviews(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH), format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(Repository repository, PullRequest pullRequest,
                                                              Params queryParams) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
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
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(Repository repository, PullRequest pullRequest, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(String owner, String repo, PullRequest pullRequest,
                                                              Params queryParams) throws IOException {
        return getPullRequestReviews(owner, repo, pullRequest.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
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
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(String owner, String repo, PullRequest pullRequest, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestReviews(owner, repo, pullRequest.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(Repository repository, long pullNumber,
                                                              Params queryParams) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
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
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(Repository repository, long pullNumber, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return getPullRequestReviews(repository.getOwner().getLogin(), repository.getName(), pullNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request reviews list as {@link ArrayList} of {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public ArrayList<PullRequestReview> getPullRequestReviews(String owner, String repo, long pullNumber,
                                                              Params queryParams) throws IOException {
        return getPullRequestReviews(owner, repo, pullNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of reviews returns in chronological order
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
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
     * @return pull request reviews list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-reviews-for-a-pull-request">
     * List reviews for a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T getPullRequestReviews(String owner, String repo, long pullNumber, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return returnReviews(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a pull request reviews list
     *
     * @param reviewsResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return pull request reviews list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReviews(String reviewsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(reviewsResponse);
            case LIBRARY_OBJECT:
                ArrayList<PullRequestReview> pullRequestReviews = new ArrayList<>();
                JSONArray jPullRequestReviews = new JSONArray(reviewsResponse);
                for (int j = 0; j < jPullRequestReviews.length(); j++)
                    pullRequestReviews.add(new PullRequestReview(jPullRequestReviews.getJSONObject(j)));
                return (T) pullRequestReviews;
            default:
                return (T) reviewsResponse;
        }
    }

    /**
     * Method to create a review for a pull request
     *
     * @param repository:  the repository where create the pull request review
     * @param pullRequest: the pull request where create the review
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                            commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                            line you specify as the position. Defaults to the most recent commit in the pull request
     *                            when you do not specify a value - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                            parameter</b>. The body text of the pull request review - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> the review action you want to perform. The review actions include:
     *                            APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                            state to PENDING, which means you will need to submit the pull request review when you
     *                            are ready, constants available {@link ReviewEvent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "comments"} -> use the following table to specify the location, destination, and
     *                            contents of the draft review comment, constants available {@link ReviewComment}
     *                            - [array of objects]
     *                        </li>
     *                     </ul>
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public PullRequestReview createPullRequestReview(Repository repository, PullRequest pullRequest,
                                                     Params bodyParams) throws IOException {
        return createPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param repository:  the repository where create the pull request review
     * @param pullRequest: the pull request where create the review
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                            commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                            line you specify as the position. Defaults to the most recent commit in the pull request
     *                            when you do not specify a value - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                            parameter</b>. The body text of the pull request review - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> the review action you want to perform. The review actions include:
     *                            APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                            state to PENDING, which means you will need to submit the pull request review when you
     *                            are ready, constants available {@link ReviewEvent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "comments"} -> use the following table to specify the location, destination, and
     *                            contents of the draft review comment, constants available {@link ReviewComment}
     *                            - [array of objects]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T createPullRequestReview(Repository repository, PullRequest pullRequest, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return createPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                bodyParams, format);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                            commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                            line you specify as the position. Defaults to the most recent commit in the pull request
     *                            when you do not specify a value - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                            parameter</b>. The body text of the pull request review - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> the review action you want to perform. The review actions include:
     *                            APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                            state to PENDING, which means you will need to submit the pull request review when you
     *                            are ready, constants available {@link ReviewEvent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "comments"} -> use the following table to specify the location, destination, and
     *                            contents of the draft review comment, constants available {@link ReviewComment}
     *                            - [array of objects]
     *                        </li>
     *                     </ul>
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public PullRequestReview createPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                     Params bodyParams) throws IOException {
        return createPullRequestReview(owner, repo, pullRequest.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where create the review
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                            commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                            line you specify as the position. Defaults to the most recent commit in the pull request
     *                            when you do not specify a value - [string]
     *                        </li>
     *                        <li>
     *                            {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                            parameter</b>. The body text of the pull request review - [string]
     *                        </li>
     *                        <li>
     *                            {@code "event"} -> the review action you want to perform. The review actions include:
     *                            APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                            state to PENDING, which means you will need to submit the pull request review when you
     *                            are ready, constants available {@link ReviewEvent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "comments"} -> use the following table to specify the location, destination, and
     *                            contents of the draft review comment, constants available {@link ReviewComment}
     *                            - [array of objects]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T createPullRequestReview(String owner, String repo, PullRequest pullRequest, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return createPullRequestReview(owner, repo, pullRequest.getNumber(), bodyParams, format);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param repository: the repository where create the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                           commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                           line you specify as the position. Defaults to the most recent commit in the pull request
     *                           when you do not specify a value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                           parameter</b>. The body text of the pull request review - [string]
     *                       </li>
     *                       <li>
     *                           {@code "event"} -> the review action you want to perform. The review actions include:
     *                           APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                           state to PENDING, which means you will need to submit the pull request review when you
     *                           are ready, constants available {@link ReviewEvent} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "comments"} -> use the following table to specify the location, destination, and
     *                           contents of the draft review comment, constants available {@link ReviewComment}
     *                           - [array of objects]
     *                       </li>
     *                    </ul>
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public PullRequestReview createPullRequestReview(Repository repository, long pullNumber,
                                                     Params bodyParams) throws IOException {
        return createPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param repository: the repository where create the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                           commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                           line you specify as the position. Defaults to the most recent commit in the pull request
     *                           when you do not specify a value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                           parameter</b>. The body text of the pull request review - [string]
     *                       </li>
     *                       <li>
     *                           {@code "event"} -> the review action you want to perform. The review actions include:
     *                           APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                           state to PENDING, which means you will need to submit the pull request review when you
     *                           are ready, constants available {@link ReviewEvent} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "comments"} -> use the following table to specify the location, destination, and
     *                           contents of the draft review comment, constants available {@link ReviewComment}
     *                           - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T createPullRequestReview(Repository repository, long pullNumber, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return createPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, bodyParams,
                format);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                           commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                           line you specify as the position. Defaults to the most recent commit in the pull request
     *                           when you do not specify a value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                           parameter</b>. The body text of the pull request review - [string]
     *                       </li>
     *                       <li>
     *                           {@code "event"} -> the review action you want to perform. The review actions include:
     *                           APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                           state to PENDING, which means you will need to submit the pull request review when you
     *                           are ready, constants available {@link ReviewEvent} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "comments"} -> use the following table to specify the location, destination, and
     *                           contents of the draft review comment, constants available {@link ReviewComment}
     *                           - [array of objects]
     *                       </li>
     *                    </ul>
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public PullRequestReview createPullRequestReview(String owner, String repo, long pullNumber,
                                                     Params bodyParams) throws IOException {
        return createPullRequestReview(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "commit_id"} -> the SHA of the commit that needs a review. Not using the latest
     *                           commit SHA may render your review comment outdated if a subsequent commit modifies the
     *                           line you specify as the position. Defaults to the most recent commit in the pull request
     *                           when you do not specify a value - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> <b>required when using REQUEST_CHANGES or COMMENT for the event
     *                           parameter</b>. The body text of the pull request review - [string]
     *                       </li>
     *                       <li>
     *                           {@code "event"} -> the review action you want to perform. The review actions include:
     *                           APPROVE, REQUEST_CHANGES, or COMMENT. By leaving this blank, you set the review action
     *                           state to PENDING, which means you will need to submit the pull request review when you
     *                           are ready, constants available {@link ReviewEvent} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "comments"} -> use the following table to specify the location, destination, and
     *                           contents of the draft review comment, constants available {@link ReviewComment}
     *                           - [array of objects]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#create-a-review-for-a-pull-request">
     * Create a review for a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    public <T> T createPullRequestReview(String owner, String repo, long pullNumber, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return returnReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH, bodyParams), format);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param repository:  the repository from fetch the pull request review
     * @param pullRequest: the pull request from fetch the review
     * @param reviewId:    the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview getPullRequestReview(Repository repository, PullRequest pullRequest,
                                                  long reviewId) throws IOException {
        return getPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param repository:  the repository from fetch the pull request review
     * @param pullRequest: the pull request from fetch the review
     * @param reviewId:    the unique identifier of the review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T getPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                      ReturnFormat format) throws IOException {
        return getPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                reviewId, format);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the review
     * @param reviewId:    the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview getPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                  long reviewId) throws IOException {
        return getPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the review
     * @param reviewId:    the unique identifier of the review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T getPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                      ReturnFormat format) throws IOException {
        return getPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, format);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param repository: the repository from fetch the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview getPullRequestReview(Repository repository, long pullNumber, long reviewId) throws IOException {
        return getPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, reviewId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param repository: the repository from fetch the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T getPullRequestReview(Repository repository, long pullNumber, long reviewId,
                                      ReturnFormat format) throws IOException {
        return getPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, reviewId,
                format);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview getPullRequestReview(String owner, String repo, long pullNumber,
                                                  long reviewId) throws IOException {
        return getPullRequestReview(owner, repo, pullNumber, reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#get-a-review-for-a-pull-request">
     * Get a review for a pull request</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T getPullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                      ReturnFormat format) throws IOException {
        return returnReview(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH + "/" + reviewId), format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository:  the repository where update the pull request review
     * @param pullRequest: the pull request where update the review
     * @param review:      the review to update
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(Repository repository, PullRequest pullRequest,
                                                     PullRequestReview review, String body) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                review.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository:  the repository where update the pull request review
     * @param pullRequest: the pull request where update the review
     * @param review:      the review to update
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(Repository repository, PullRequest pullRequest, PullRequestReview review,
                                         String body, ReturnFormat format) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                review.getId(), body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository:  the repository where update the pull request review
     * @param pullRequest: the pull request where update the review
     * @param reviewId:    the unique identifier of the review
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                                     String body) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                reviewId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository:  the repository where update the pull request review
     * @param pullRequest: the pull request where update the review
     * @param reviewId:    the unique identifier of the review
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(Repository repository, PullRequest pullRequest, long reviewId, String body,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullRequest.getNumber(),
                reviewId, body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the review
     * @param review:      the review to update
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                     PullRequestReview review, String body) throws IOException {
        return updatePullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the review
     * @param review:      the review to update
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(String owner, String repo, PullRequest pullRequest, PullRequestReview review,
                                         String body, ReturnFormat format) throws IOException {
        return updatePullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the review
     * @param reviewId:    the unique identifier of the review
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                                     String body) throws IOException {
        return updatePullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where update the review
     * @param reviewId:    the unique identifier of the review
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId, String body,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository: the repository where update the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to update
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                                     String body) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, review.getId(),
                body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository: the repository where update the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to update
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(Repository repository, long pullNumber, PullRequestReview review, String body,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, review.getId(),
                body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository: the repository where update the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(Repository repository, long pullNumber, long reviewId,
                                                     String body) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, reviewId,
                body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param repository: the repository where update the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(Repository repository, long pullNumber, long reviewId, String body,
                                         ReturnFormat format) throws IOException {
        return updatePullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber, reviewId,
                body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to update
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                                     String body) throws IOException {
        return updatePullRequestReview(owner, repo, pullNumber, review.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to update
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                         String body, ReturnFormat format) throws IOException {
        return updatePullRequestReview(owner, repo, pullNumber, review.getId(), body, format);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview updatePullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                                     String body) throws IOException {
        return updatePullRequestReview(owner, repo, pullNumber, reviewId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update the review summary comment with new text
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#update-a-review-for-a-pull-request">
     * Update a review for a pull request</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T updatePullRequestReview(String owner, String repo, long pullNumber, long reviewId, String body,
                                         ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnReview(sendPutRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH + "/" + reviewId, payload), format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository:  the repository where delete the pull request review
     * @param pullRequest: the pull request where delete the review
     * @param review:      the review to delete
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(Repository repository, PullRequest pullRequest,
                                                            PullRequestReview review) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository:  the repository where delete the pull request review
     * @param pullRequest: the pull request where delete the review
     * @param review:      the review to delete
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(Repository repository, PullRequest pullRequest, PullRequestReview review,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository: the repository where delete the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to delete
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(Repository repository, long pullNumber,
                                                            PullRequestReview review) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository: the repository where delete the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to delete
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to delete
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(String owner, String repo, long pullNumber,
                                                            PullRequestReview review) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullNumber, review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to delete
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullNumber, review.getId(), format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where delete the review
     * @param review:      the review to delete
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                            PullRequestReview review) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where delete the review
     * @param review:      the review to delete
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                PullRequestReview review, ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where delete the review
     * @param reviewId:    the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                            long reviewId) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where delete the review
     * @param reviewId:    the unique identifier of the review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository:  the repository where delete the pull request review
     * @param pullRequest: the pull request where delete the review
     * @param reviewId:    the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(Repository repository, PullRequest pullRequest,
                                                            long reviewId) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository:  the repository where delete the pull request review
     * @param pullRequest: the pull request where delete the review
     * @param reviewId:    the unique identifier of the review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository: the repository where delete the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(Repository repository, long pullNumber,
                                                            long reviewId) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param repository: the repository where delete the pull request review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(Repository repository, long pullNumber, long reviewId,
                                                ReturnFormat format) throws IOException {
        return deletePendingPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, format);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public PullRequestReview deletePendingPullRequestReview(String owner, String repo, long pullNumber,
                                                            long reviewId) throws IOException {
        return deletePendingPullRequestReview(owner, repo, pullNumber, reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a pending review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#delete-a-pending-review-for-a-pull-request">
     * Delete a pending review for a pull request</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}")
    public <T> T deletePendingPullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                                ReturnFormat format) throws IOException {
        return returnReview(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + REVIEWS_PATH + "/" + reviewId), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                                                 PullRequestReview review) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                              PullRequestReview review, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review from fetch the list
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber,
                                                                 PullRequestReview review) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber, PullRequestReview review,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review from fetch the list
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                                                 PullRequestReview review) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber, PullRequestReview review,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, review.getId(), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                                                 PullRequestReview review) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), review.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                              PullRequestReview review, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), review.getId(), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                                                 long reviewId) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest, long reviewId,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), reviewId, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                                                 long reviewId) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest, long reviewId,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber,
                                                                 long reviewId) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber, long reviewId,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                                                 long reviewId) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, reviewId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber, long reviewId,
                                              ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH + "/" + reviewId + COMMENTS_PATH), format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                                                 Params queryParams,
                                                                 PullRequestReview review) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                              PullRequestReview review, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param review:      the review from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber,
                                                                 PullRequestReview review,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber, PullRequestReview review,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param review:      the review from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                                                 PullRequestReview review,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, review.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber, PullRequestReview review,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, review.getId(), queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                                                 PullRequestReview review,
                                                                 Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), review.getId(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param review:      the review from fetch the list
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest, PullRequestReview review,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), review.getId(), queryParams,
                format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest,
                                                                 long reviewId, Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), reviewId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, PullRequest pullRequest, long reviewId,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullRequest.getNumber(), reviewId, queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, PullRequest pullRequest,
                                                                 long reviewId, Params queryParams) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, PullRequest pullRequest, long reviewId,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param reviewId:    the unique identifier of the review
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(Repository repository, long pullNumber,
                                                                 long reviewId, Params queryParams) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param repository:  the repository from fetch the list
     * @param pullNumber:  the number that identifies the pull request
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(Repository repository, long pullNumber, long reviewId,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return getPullRequestReviewComments(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, queryParams, format);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param reviewId:    the unique identifier of the review
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return pull request review comments list as {@link ArrayList} of {@link ReviewComment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public ArrayList<ReviewComment> getPullRequestReviewComments(String owner, String repo, long pullNumber,
                                                                 long reviewId, Params queryParams) throws IOException {
        return getPullRequestReviewComments(owner, repo, pullNumber, reviewId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list comments for a specific pull request review
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullNumber:  the number that identifies the pull request
     * @param reviewId:    the unique identifier of the review
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#list-comments-for-a-pull-request-review">
     * List comments for a pull request review</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    public <T> T getPullRequestReviewComments(String owner, String repo, long pullNumber, long reviewId,
                                              Params queryParams, ReturnFormat format) throws IOException {
        return returnReviewComments(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REVIEWS_PATH + "/" + reviewId + COMMENTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository:  the repository where dismiss the review
     * @param pullRequest: the pull request where dismiss the review
     * @param review:      the review to dismiss
     * @param message:     the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(Repository repository, PullRequest pullRequest,
                                                      PullRequestReview review, String message) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository:  the repository where dismiss the review
     * @param pullRequest: the pull request where dismiss the review
     * @param review:      the review to dismiss
     * @param message:     the message for the pull request review dismissal
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(Repository repository, PullRequest pullRequest, PullRequestReview review,
                                          String message, ReturnFormat format) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository: the repository where dismiss the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to dismiss
     * @param message:    the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                                      String message) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository: the repository where dismiss the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to dismiss
     * @param message:    the message for the pull request review dismissal
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                          String message, ReturnFormat format) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to dismiss
     * @param message:    the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(String owner, String repo, long pullNumber,
                                                      PullRequestReview review, String message) throws IOException {
        return dismissPullRequestReview(owner, repo, pullNumber, review.getId(), message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to dismiss
     * @param message:    the message for the pull request review dismissal
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                          String message, ReturnFormat format) throws IOException {
        return dismissPullRequestReview(owner, repo, pullNumber, review.getId(), message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where dismiss the review
     * @param review:      the review to dismiss
     * @param message:     the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                      PullRequestReview review, String message) throws IOException {
        return dismissPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where dismiss the review
     * @param review:      the review to dismiss
     * @param message:     the message for the pull request review dismissal
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(String owner, String repo, PullRequest pullRequest, PullRequestReview review,
                                          String message, ReturnFormat format) throws IOException {
        return dismissPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where dismiss the review
     * @param reviewId:    the unique identifier of the review
     * @param message:     the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                                      String message) throws IOException {
        return dismissPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where dismiss the review
     * @param reviewId:    the unique identifier of the review
     * @param message:     the message for the pull request review dismissal
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                          String message, ReturnFormat format) throws IOException {
        return dismissPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository:  the repository where dismiss the review
     * @param pullRequest: the pull request where dismiss the review
     * @param reviewId:    the unique identifier of the review
     * @param message:     the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                                      String message) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository:  the repository where dismiss the review
     * @param pullRequest: the pull request where dismiss the review
     * @param reviewId:    the unique identifier of the review
     * @param message:     the message for the pull request review dismissal
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId, String message,
                                          ReturnFormat format) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository: the repository where dismiss the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param message:    the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(Repository repository, long pullNumber, long reviewId,
                                                      String message) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param repository: the repository where dismiss the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param message:    the message for the pull request review dismissal
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(Repository repository, long pullNumber, long reviewId, String message,
                                          ReturnFormat format) throws IOException {
        return dismissPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, message, format);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param message:    the message for the pull request review dismissal
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public PullRequestReview dismissPullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                                      String message) throws IOException {
        return dismissPullRequestReview(owner, repo, pullNumber, reviewId, message, LIBRARY_OBJECT);
    }

    /**
     * Method to dismiss a review for a pull request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param message:    the message for the pull request review dismissal
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#dismiss-a-review-for-a-pull-request">
     * Dismiss a review for a pull request</a>
     * @implNote To dismiss a pull request review on a protected branch, you must be a repository administrator or be
     * included in the list of people or teams who can dismiss pull request reviews
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/dismissals")
    public <T> T dismissPullRequestReview(String owner, String repo, long pullNumber, long reviewId, String message,
                                          ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("message", message);
        return returnReview(sendPutRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + REVIEWS_PATH + "/" + reviewId + DISMISSALS_PATH, payload), format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, PullRequest pullRequest,
                                                     PullRequestReview review, ReviewEvent event) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, PullRequest pullRequest, PullRequestReview review,
                                         ReviewEvent event, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                         ReviewEvent event, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, review.getId(), event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                         ReviewEvent event, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, review.getId(), event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                     PullRequestReview review, ReviewEvent event) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, PullRequest pullRequest, PullRequestReview review,
                                         ReviewEvent event, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                         ReviewEvent event, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId, ReviewEvent event,
                                         ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, long pullNumber, long reviewId,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, long pullNumber, long reviewId, ReviewEvent event,
                                         ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, event, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                                     ReviewEvent event) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, reviewId, event, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, long pullNumber, long reviewId, ReviewEvent event,
                                         ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, reviewId, event, null, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, PullRequest pullRequest,
                                                     PullRequestReview review, ReviewEvent event,
                                                     String body) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, PullRequest pullRequest, PullRequestReview review,
                                         ReviewEvent event, String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), review.getId(), event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                                     ReviewEvent event, String body) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, long pullNumber, PullRequestReview review,
                                         ReviewEvent event, String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                review.getId(), event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                                     String body, ReviewEvent event) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, review.getId(), event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param review:     the review to submit
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, long pullNumber, PullRequestReview review,
                                         ReviewEvent event, String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, review.getId(), event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, PullRequest pullRequest,
                                                     PullRequestReview review, ReviewEvent event,
                                                     String body) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param review:      the review to submit
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, PullRequest pullRequest, PullRequestReview review,
                                         ReviewEvent event, String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), review.getId(), event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                                     ReviewEvent event, String body) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, PullRequest pullRequest, long reviewId,
                                         ReviewEvent event, String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(owner, repo, pullRequest.getNumber(), reviewId, event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId,
                                                     ReviewEvent event, String body) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository:  the repository where submit the review
     * @param pullRequest: the pull request where submit the review
     * @param reviewId:    the unique identifier of the review
     * @param event:       the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                     COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                     review action state to PENDING, which means you will need to re-submit the pull request review using
     *                     a review action
     * @param body:        the body text of the pull request review
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, PullRequest pullRequest, long reviewId, ReviewEvent event,
                                         String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewId, event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(Repository repository, long pullNumber, long reviewId,
                                                     ReviewEvent event, String body) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param repository: the repository where submit the review
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(Repository repository, long pullNumber, long reviewId, ReviewEvent event,
                                         String body, ReturnFormat format) throws IOException {
        return submitPullRequestReview(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewId, event, body, format);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @return pull request review as {@link PullRequestReview} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public PullRequestReview submitPullRequestReview(String owner, String repo, long pullNumber, long reviewId,
                                                     ReviewEvent event, String body) throws IOException {
        return submitPullRequestReview(owner, repo, pullNumber, reviewId, event, body, LIBRARY_OBJECT);
    }

    /**
     * Method to submits a pending review for a pull request. For more information about creating a pending review for a
     * pull request, see "Create a review for a pull request."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param reviewId:   the unique identifier of the review
     * @param event:      the review action you want to perform. The review actions include: APPROVE, REQUEST_CHANGES, or
     *                    COMMENT. When you leave this blank, the API returns HTTP 422 (Unrecognizable entity) and sets the
     *                    review action state to PENDING, which means you will need to re-submit the pull request review using
     *                    a review action
     * @param body:       the body text of the pull request review
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/reviews#submit-a-review-for-a-pull-request">
     * Submit a review for a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
    public <T> T submitPullRequestReview(String owner, String repo, long pullNumber, long reviewId, ReviewEvent event,
                                         String body, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("event", event);
        if (body != null)
            payload.addParam("body", body);
        return returnReview(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/" + pullNumber
                + REVIEWS_PATH + "/" + reviewId + "/" + EVENTS_PATH, payload), format);
    }

    /**
     * Method to create a pull request review
     *
     * @param reviewResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return pull request review as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReview(String reviewResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(reviewResponse);
            case LIBRARY_OBJECT:
                return (T) new PullRequestReview(new JSONObject(reviewResponse));
            default:
                return (T) reviewResponse;
        }
    }

}
