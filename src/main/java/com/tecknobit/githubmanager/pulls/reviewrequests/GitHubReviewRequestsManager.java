package com.tecknobit.githubmanager.pulls.reviewrequests;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.pulls.reviewrequests.records.RequestedReviewers;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.pulls.pulls.records.PullRequest.returnPullRequest;

/**
 * The {@code GitHubReviewRequestsManager} class is useful to manage all GitHub's review requests endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests">
 * Review requests</a>
 * @see GitHubManager
 **/
public class GitHubReviewRequestsManager extends GitHubManager {

    /**
     * {@code REQUESTED_REVIEWERS_PATH} constant for {@code "/requested_reviewers"} path
     **/
    public static final String REQUESTED_REVIEWERS_PATH = "/requested_reviewers";

    /**
     * Constructor to init a {@link GitHubReviewRequestsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReviewRequestsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReviewRequestsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReviewRequestsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReviewRequestsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReviewRequestsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewRequestsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReviewRequestsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReviewRequestsManager} <br>
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
    public GitHubReviewRequestsManager() {
        super();
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @return requested reviewers as {@link RequestedReviewers} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public RequestedReviewers getAllRequestedPullRequestReviewers(Repository repository,
                                                                  PullRequest pullRequest) throws IOException {
        return getAllRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param repository:  the repository from fetch the list
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return requested reviewers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T getAllRequestedPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                     ReturnFormat format) throws IOException {
        return getAllRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), format);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @return requested reviewers as {@link RequestedReviewers} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public RequestedReviewers getAllRequestedPullRequestReviewers(String owner, String repo,
                                                                  PullRequest pullRequest) throws IOException {
        return getAllRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return requested reviewers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T getAllRequestedPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                     ReturnFormat format) throws IOException {
        return getAllRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @return requested reviewers as {@link RequestedReviewers} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public RequestedReviewers getAllRequestedPullRequestReviewers(Repository repository, long pullNumber) throws IOException {
        return getAllRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param repository: the repository from fetch the list
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return requested reviewers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T getAllRequestedPullRequestReviewers(Repository repository, long pullNumber,
                                                     ReturnFormat format) throws IOException {
        return getAllRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                format);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return requested reviewers as {@link RequestedReviewers} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public RequestedReviewers getAllRequestedPullRequestReviewers(String owner, String repo,
                                                                  long pullNumber) throws IOException {
        return getAllRequestedPullRequestReviewers(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the users or teams whose review is requested for a pull request. Once a requested reviewer submits
     * a review, they are no longer considered a requested reviewer. Their review will instead be returned by the List
     * reviews for a pull request operation
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return requested reviewers as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
     * Get all requested reviewers for a pull request</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T getAllRequestedPullRequestReviewers(String owner, String repo, long pullNumber,
                                                     ReturnFormat format) throws IOException {
        String requestedReviewersResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REQUESTED_REVIEWERS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(requestedReviewersResponse);
            case LIBRARY_OBJECT:
                return (T) new RequestedReviewers(new JSONObject(requestedReviewersResponse));
            default:
                return (T) requestedReviewersResponse;
        }
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository:  the repository where request the reviewers
     * @param pullRequest: the pull request where request the reviewers
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(Repository repository, PullRequest pullRequest) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository:  the repository where request the reviewers
     * @param pullRequest: the pull request where request the reviewers
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where request the reviewers
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(String owner, String repo, PullRequest pullRequest) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullRequest.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where request the reviewers
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullRequest.getNumber(), format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where request the reviewers
     * @param pullNumber: the number that identifies the pull request
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(Repository repository, long pullNumber) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where request the reviewers
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(Repository repository, long pullNumber, ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber, format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(String owner, String repo, long pullNumber) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(String owner, String repo, long pullNumber,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullNumber, null, format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository:  the repository where request the reviewers
     * @param pullRequest: the pull request where request the reviewers
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                        </li>
     *                     </ul>
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                   Params bodyParams) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository:  the repository where request the reviewers
     * @param pullRequest: the pull request where request the reviewers
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(Repository repository, PullRequest pullRequest, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), bodyParams, format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where request the reviewers
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                        </li>
     *                     </ul>
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                   Params bodyParams) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullRequest.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param pullRequest: the pull request where request the reviewers
     * @param bodyParams:  extra body params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                        </li>
     *                        <li>
     *                            {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(String owner, String repo, PullRequest pullRequest, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullRequest.getNumber(), bodyParams, format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where request the reviewers
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(Repository repository, long pullNumber,
                                                   Params bodyParams) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param repository: the repository where request the reviewers
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(Repository repository, long pullNumber, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        return requestPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                bodyParams, format);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                       </li>
     *                    </ul>
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest requestPullRequestReviewers(String owner, String repo, long pullNumber,
                                                   Params bodyParams) throws IOException {
        return requestPullRequestReviewers(owner, repo, pullNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to request reviewers for a pull request. <br>
     * This endpoint triggers notifications. Creating content too quickly using this endpoint may result in secondary rate
     * limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param pullNumber: the number that identifies the pull request
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "reviewers"} -> an array of user logins that will be requested - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "team_reviewers"} -> an array of team slugs that will be requested - [array of strings]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#request-reviewers-for-a-pull-request">
     * Request reviewers for a pull request</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T requestPullRequestReviewers(String owner, String repo, long pullNumber, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        return returnPullRequest(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REQUESTED_REVIEWERS_PATH, bodyParams), format);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                           ArrayList<String> reviewers,
                                                           String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                     ArrayList<String> reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewers, format, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                           ArrayList<String> reviewers,
                                                           String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), reviewers, LIBRARY_OBJECT,
                teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                     ArrayList<String> reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), reviewers, format,
                teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(Repository repository, long pullNumber,
                                                           ArrayList<String> reviewers,
                                                           String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(Repository repository, long pullNumber, ArrayList<String> reviewers,
                                                     ReturnFormat format, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewers, format, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(String owner, String repo, long pullNumber,
                                                           ArrayList<String> reviewers,
                                                           String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullNumber, reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(String owner, String repo, long pullNumber,
                                                     ArrayList<String> reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullNumber, reviewers.toArray(new String[0]), format,
                teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                           String[] reviewers, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(Repository repository, PullRequest pullRequest,
                                                     String[] reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(),
                pullRequest.getNumber(), reviewers, format, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                           String[] reviewers, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), reviewers, LIBRARY_OBJECT,
                teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullRequest:   the pull request from remove the requested reviewers
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(String owner, String repo, PullRequest pullRequest,
                                                     String[] reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullRequest.getNumber(), reviewers, format,
                teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(Repository repository, long pullNumber,
                                                           String[] reviewers, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param repository:    the repository from remove the requested reviewers
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(Repository repository, long pullNumber, String[] reviewers,
                                                     ReturnFormat format, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(repository.getOwner().getLogin(), repository.getName(), pullNumber,
                reviewers, format, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @return pull request as {@link PullRequest} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public PullRequest removeRequestedPullRequestReviewers(String owner, String repo, long pullNumber,
                                                           String[] reviewers, String... teamReviewers) throws IOException {
        return removeRequestedPullRequestReviewers(owner, repo, pullNumber, reviewers, LIBRARY_OBJECT, teamReviewers);
    }

    /**
     * Method to remove requested reviewers from a pull request
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param pullNumber:    the number that identifies the pull request
     * @param reviewers:     an array of user logins that will be removed
     * @param teamReviewers: an array of team slugs that will be removed
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return pull request as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#remove-requested-reviewers-from-a-pull-request">
     * Remove requested reviewers from a pull request</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/{pull_number}/requested_reviewers")
    public <T> T removeRequestedPullRequestReviewers(String owner, String repo, long pullNumber,
                                                     String[] reviewers, ReturnFormat format,
                                                     String... teamReviewers) throws IOException {
        Params payload = new Params();
        payload.addParam("reviewers", reviewers);
        payload.addParam("team_reviewers", teamReviewers);
        return returnPullRequest(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + PULLS_PATH + "/"
                + pullNumber + REQUESTED_REVIEWERS_PATH, payload).get("success").toString(), format);
    }

}
