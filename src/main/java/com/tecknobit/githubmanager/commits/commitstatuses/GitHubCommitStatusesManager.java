package com.tecknobit.githubmanager.commits.commitstatuses;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.commits.commitstatuses.records.CombinedStatus;
import com.tecknobit.githubmanager.commits.commitstatuses.records.CommitStatus;
import com.tecknobit.githubmanager.commits.commitstatuses.records.CommitStatus.CommitStatusState;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commits.GitHubCommitsManager.COMMITS_QUERY_PATH;

/**
 * The {@code GitHubCommitStatusesManager} class is useful to manage all GitHub's commit statuses endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses">
 * Commit statuses</a>
 * @see GitHubManager
 **/
public class GitHubCommitStatusesManager extends GitHubManager {

    /**
     * {@code STATUS_PATH} constant for {@code "/status"} path
     **/
    public static final String STATUS_PATH = "/status";

    /**
     * {@code STATUSES_PATH} constant for {@code "/statuses"} path
     **/
    public static final String STATUSES_PATH = "/statuses";

    /**
     * Constructor to init a {@link GitHubCommitStatusesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCommitStatusesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCommitStatusesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCommitStatusesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCommitStatusesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCommitStatusesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitStatusesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCommitStatusesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommitStatusesManager} <br>
     * Any params required
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
    public GitHubCommitStatusesManager() {
        super();
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param repository: the repository from fetch combined status
     * @param ref:        ref parameter
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public CombinedStatus getReferenceCombinedStatus(Repository repository, String ref) throws IOException {
        return getReferenceCombinedStatus(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param repository: the repository from fetch combined status
     * @param ref:        ref parameter
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public <T> T getReferenceCombinedStatus(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getReferenceCombinedStatus(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public CombinedStatus getReferenceCombinedStatus(String owner, String repo, String ref) throws IOException {
        return getReferenceCombinedStatus(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public <T> T getReferenceCombinedStatus(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        return returnCombinedStatus(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + ref + STATUS_PATH), format);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param repository:  the repository from fetch combined status
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public CombinedStatus getReferenceCombinedStatus(Repository repository, String ref,
                                                     Params queryParams) throws IOException {
        return getReferenceCombinedStatus(repository.getOwner().getLogin(), repository.getName(), ref, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param repository:  the repository from fetch combined status
     * @param ref:         ref parameter
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
     * @return combined status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public <T> T getReferenceCombinedStatus(Repository repository, String ref, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getReferenceCombinedStatus(repository.getOwner().getLogin(), repository.getName(), ref, queryParams,
                format);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return combined status as {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public CombinedStatus getReferenceCombinedStatus(String owner, String repo, String ref,
                                                     Params queryParams) throws IOException {
        return getReferenceCombinedStatus(owner, repo, ref, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the combined status for a specific reference
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
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
     * @return combined status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
     * Get the combined status for a specific reference</a>
     * @implNote Users with pull access in a repository can access a combined view of commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name.
     * Additionally, a combined state is returned. The state is one of:
     * <ul>
     *     <li>
     *         <b>failure</b> if any of the contexts report as error or failure
     *     </li>
     *     <li>
     *         <b>pending</b> if there are no statuses or a context is pending
     *     </li>
     *     <li>
     *         <b>success</b> if the latest status for all contexts is success
     *     </li>
     * </ul>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/status")
    public <T> T getReferenceCombinedStatus(String owner, String repo, String ref, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnCombinedStatus(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + ref + STATUS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a combined status
     *
     * @param combinedStatusResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return combined status  as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCombinedStatus(String combinedStatusResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(combinedStatusResponse);
            case LIBRARY_OBJECT:
                return (T) new CombinedStatus(new JSONObject(combinedStatusResponse));
            default:
                return (T) combinedStatusResponse;
        }
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param repository: the repository from fetch the list
     * @param ref:        ref parameter
     * @return commit statuses list as {@link Collection} of {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public Collection<CommitStatus> getCommitStatuses(Repository repository, String ref) throws IOException {
        return getCommitStatuses(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param repository: the repository from fetch the list
     * @param ref:        ref parameter
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit statuses list  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public <T> T getCommitStatuses(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getCommitStatuses(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return commit statuses list as {@link Collection} of {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public Collection<CommitStatus> getCommitStatuses(String owner, String repo, String ref) throws IOException {
        return getCommitStatuses(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param ref:    ref parameter
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit statuses list  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public <T> T getCommitStatuses(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        return returnCommitStatuses(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + ref + STATUSES_PATH), format);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param repository:  the repository from fetch the list
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit statuses list as {@link Collection} of {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public Collection<CommitStatus> getCommitStatuses(Repository repository, String ref,
                                                      Params queryParams) throws IOException {
        return getCommitStatuses(repository.getOwner().getLogin(), repository.getName(), ref, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param repository:  the repository from fetch the list
     * @param ref:         ref parameter
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
     * @return commit statuses list  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public <T> T getCommitStatuses(Repository repository, String ref, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getCommitStatuses(repository.getOwner().getLogin(), repository.getName(), ref, queryParams, format);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commit statuses list as {@link Collection} of {@link CombinedStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public Collection<CommitStatus> getCommitStatuses(String owner, String repo, String ref,
                                                      Params queryParams) throws IOException {
        return getCommitStatuses(owner, repo, ref, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the commit statuses for a reference
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param ref:         ref parameter
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
     * @return commit statuses list  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#list-commit-statuses-for-a-reference">
     * List commit statuses for a reference</a>
     * @implNote Users with pull access in a repository can view commit statuses for a given ref. The ref can be an SHA, a branch name, or a tag name. Statuses are returned in reverse chronological order. The first status in the list will be the latest one.
     * This resource is also available via a legacy route: GET /repos/:owner/:repo/statuses/:ref
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/commits/{ref}/statuses")
    public <T> T getCommitStatuses(String owner, String repo, String ref, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return returnCommitStatuses(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMITS_QUERY_PATH
                + ref + STATUSES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a commit statuses list
     *
     * @param commitStatusesResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return commit statuses list  as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitStatuses(String commitStatusesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commitStatusesResponse);
            case LIBRARY_OBJECT:
                ArrayList<CommitStatus> statuses = new ArrayList<>();
                JSONArray jStatuses = new JSONArray(commitStatusesResponse);
                for (int j = 0; j < jStatuses.length(); j++)
                    statuses.add(new CommitStatus(jStatuses.getJSONObject(j)));
                return (T) statuses;
            default:
                return (T) commitStatusesResponse;
        }
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param repository: the repository where create the commit status
     * @param sha:        sha value
     * @param state:      the state of the status
     * @return commit status as {@link CommitStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public CommitStatus createCommitStatus(Repository repository, String sha, CommitStatusState state) throws IOException {
        return createCommitStatus(repository.getOwner().getLogin(), repository.getName(), sha, state, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param repository: the repository where create the commit status
     * @param sha:        sha value
     * @param state:      the state of the status
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return commit status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public <T> T createCommitStatus(Repository repository, String sha, CommitStatusState state,
                                    ReturnFormat format) throws IOException {
        return createCommitStatus(repository.getOwner().getLogin(), repository.getName(), sha, state, format);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param sha:   sha value
     * @param state: the state of the status
     * @return commit status as {@link CommitStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public CommitStatus createCommitStatus(String owner, String repo, String sha, CommitStatusState state) throws IOException {
        return createCommitStatus(owner, repo, sha, state, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param sha:    sha value
     * @param state:  the state of the status
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return commit status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public <T> T createCommitStatus(String owner, String repo, String sha, CommitStatusState state,
                                    ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("state", state);
        return returnCommitStatus(sendPostRequest(REPOS_PATH + owner + "/" + repo + STATUSES_PATH + "/" + sha,
                payload), format);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param repository:   the repository where create the commit status
     * @param sha:          sha value
     * @param commitStatus: the commit status to create
     * @return commit status as {@link CommitStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public CommitStatus createCommitStatus(Repository repository, String sha, CommitStatus commitStatus) throws IOException {
        return createCommitStatus(repository.getOwner().getLogin(), repository.getName(), sha, commitStatus,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param repository:   the repository where create the commit status
     * @param sha:          sha value
     * @param commitStatus: the commit status to create
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return commit status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public <T> T createCommitStatus(Repository repository, String sha, CommitStatus commitStatus,
                                    ReturnFormat format) throws IOException {
        return createCommitStatus(repository.getOwner().getLogin(), repository.getName(), sha, commitStatus, format);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param sha:          sha value
     * @param commitStatus: the commit status to create
     * @return commit status as {@link CommitStatus} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public CommitStatus createCommitStatus(String owner, String repo, String sha,
                                           CommitStatus commitStatus) throws IOException {
        return createCommitStatus(owner, repo, sha, commitStatus, LIBRARY_OBJECT);
    }

    /**
     * Method to create a commit status <br>
     * Users with push access in a repository can create commit statuses for a given SHA
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param sha:          sha value
     * @param commitStatus: the commit status to create
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return commit status  as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#create-a-commit-status">
     * Create a commit status</a>
     * @implNote there is a limit of 1000 statuses per sha and context within a repository.
     * Attempts to create more than 1000 statuses will result in a validation error
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/statuses/{sha}")
    public <T> T createCommitStatus(String owner, String repo, String sha, CommitStatus commitStatus,
                                    ReturnFormat format) throws IOException {
        Params payload = new Params();
        JSONObject jCommitStatus = new JSONObject(commitStatus);
        for (String key : jCommitStatus.keySet())
            payload.addParam(key, jCommitStatus.get(key));
        return returnCommitStatus(sendPostRequest(REPOS_PATH + owner + "/" + repo + STATUSES_PATH + "/" + sha,
                payload), format);
    }

    /**
     * Method to create a commit status
     *
     * @param commitStatusResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return commit status  as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitStatus(String commitStatusResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(commitStatusResponse);
            case LIBRARY_OBJECT:
                return (T) new CommitStatus(new JSONObject(commitStatusResponse));
            default:
                return (T) commitStatusResponse;
        }
    }

}
