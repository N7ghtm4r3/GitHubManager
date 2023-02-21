package com.tecknobit.githubmanager.issues.labels;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Milestone;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.selfhosted.runners.GitHubRunnersManager.LABELS_PATH;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;
import static com.tecknobit.githubmanager.issues.milestones.GitHubMilestonesManager.MILESTONES_PATH;

/**
 * The {@code GitHubIssueLabelsManager} class is useful to manage all GitHub's issue labels endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels">
 * Labels</a>
 * @see GitHubManager
 **/
public class GitHubIssueLabelsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubIssueLabelsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubIssueLabelsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubIssueLabelsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubIssueLabelsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubIssueLabelsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubIssueLabelsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueLabelsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubIssueLabelsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubIssueLabelsManager} <br>
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
    public GitHubIssueLabelsManager() {
        super();
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the repository from fetch the list
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(Repository repository, Issue issue) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(Repository repository, Issue issue, ReturnFormat format) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from fetch the list
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(String owner, String repo, Issue issue) throws IOException {
        return getIssueLabels(owner, repo, issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(String owner, String repo, Issue issue, ReturnFormat format) throws IOException {
        return getIssueLabels(owner, repo, issue.getNumber(), format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(Repository repository, long issueNumber) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(Repository repository, long issueNumber, ReturnFormat format) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(String owner, String repo, long issueNumber) throws IOException {
        return getIssueLabels(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(String owner, String repo, long issueNumber, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + "/" + issueNumber + LABELS_PATH), format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(Repository repository, Issue issue, Params queryParams) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(Repository repository, Issue issue, Params queryParams,
                                ReturnFormat format) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), queryParams,
                format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(String owner, String repo, Issue issue, Params queryParams) throws IOException {
        return getIssueLabels(owner, repo, issue.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(String owner, String repo, Issue issue, Params queryParams,
                                ReturnFormat format) throws IOException {
        return getIssueLabels(owner, repo, issue.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(Repository repository, long issueNumber, Params queryParams) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(Repository repository, long issueNumber, Params queryParams,
                                ReturnFormat format) throws IOException {
        return getIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams, format);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> getIssueLabels(String owner, String repo, long issueNumber,
                                            Params queryParams) throws IOException {
        return getIssueLabels(owner, repo, issueNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-an-issue">
     * List labels for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T getIssueLabels(String owner, String repo, long issueNumber, Params queryParams,
                                ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + "/" + issueNumber + LABELS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param repository: the repository where add the list
     * @param issue:      the repository where add the list
     * @param labels:     the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                    You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                    a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                    You can also add labels to the existing labels for an issue. For more information,
     *                    see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> addIssueLabels(Repository repository, Issue issue, String... labels) throws IOException {
        return addIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), LIBRARY_OBJECT,
                labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param repository: the repository where add the list
     * @param issue:      the repository where add the list
     * @param labels:     the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                    You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                    a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                    You can also add labels to the existing labels for an issue. For more information,
     *                    see "Add labels to an issue."
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T addIssueLabels(Repository repository, Issue issue, ReturnFormat format, String... labels) throws IOException {
        return addIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format,
                labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param issue:  the repository where add the list
     * @param labels: the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                You can also add labels to the existing labels for an issue. For more information,
     *                see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> addIssueLabels(String owner, String repo, Issue issue, String... labels) throws IOException {
        return addIssueLabels(owner, repo, issue.getNumber(), LIBRARY_OBJECT, labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param issue:  the repository where add the list
     * @param labels: the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                You can also add labels to the existing labels for an issue. For more information,
     *                see "Add labels to an issue."
     * @param format  :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T addIssueLabels(String owner, String repo, Issue issue, ReturnFormat format,
                                String... labels) throws IOException {
        return addIssueLabels(owner, repo, issue.getNumber(), format, labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param repository:  the repository where add the list
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> addIssueLabels(Repository repository, long issueNumber, String... labels) throws IOException {
        return addIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT,
                labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param repository:  the repository where add the list
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T addIssueLabels(Repository repository, long issueNumber, ReturnFormat format,
                                String... labels) throws IOException {
        return addIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, format, labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> addIssueLabels(String owner, String repo, long issueNumber, String... labels) throws IOException {
        return addIssueLabels(owner, repo, issueNumber, LIBRARY_OBJECT, labels);
    }

    /**
     * Method to add the list of labels to an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#add-labels-to-an-issue">
     * Add labels to an issue</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T addIssueLabels(String owner, String repo, long issueNumber, ReturnFormat format,
                                String... labels) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels);
        return returnLabelsList(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + "/" + issueNumber + LABELS_PATH, payload), format);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param repository: the repository where set the list
     * @param issue:      the repository where set the list
     * @param labels:     the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                    You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                    a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                    You can also add labels to the existing labels for an issue. For more information,
     *                    see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> setIssueLabels(Repository repository, Issue issue, String... labels) throws IOException {
        return setIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), LIBRARY_OBJECT,
                labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param repository: the repository where set the list
     * @param issue:      the repository where set the list
     * @param labels:     the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                    You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                    a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                    You can also add labels to the existing labels for an issue. For more information,
     *                    see "Add labels to an issue."
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T setIssueLabels(Repository repository, Issue issue, ReturnFormat format, String... labels) throws IOException {
        return setIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format,
                labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param issue:  the repository where set the list
     * @param labels: the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                You can also add labels to the existing labels for an issue. For more information,
     *                see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> setIssueLabels(String owner, String repo, Issue issue, String... labels) throws IOException {
        return setIssueLabels(owner, repo, issue.getNumber(), LIBRARY_OBJECT, labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param issue:  the repository where set the list
     * @param labels: the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                You can also add labels to the existing labels for an issue. For more information,
     *                see "Add labels to an issue."
     * @param format  :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T setIssueLabels(String owner, String repo, Issue issue, ReturnFormat format,
                                String... labels) throws IOException {
        return setIssueLabels(owner, repo, issue.getNumber(), format, labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param repository:  the repository where set the list
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> setIssueLabels(Repository repository, long issueNumber, String... labels) throws IOException {
        return setIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT,
                labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param repository:  the repository where set the list
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T setIssueLabels(Repository repository, long issueNumber, ReturnFormat format,
                                String... labels) throws IOException {
        return setIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber, format, labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public Collection<Label> setIssueLabels(String owner, String repo, long issueNumber,
                                            String... labels) throws IOException {
        return setIssueLabels(owner, repo, issueNumber, LIBRARY_OBJECT, labels);
    }

    /**
     * Method to set the list of labels to an issue <br>
     * Removes any previous labels and sets the new labels for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param labels:      the names of the labels to set for the issue. The labels you set replace any existing labels.
     *                     You can pass an empty array to remove all labels. Alternatively, you can pass a single label as
     *                     a string or an array of labels directly, but GitHub recommends passing an object with the labels key.
     *                     You can also add labels to the existing labels for an issue. For more information,
     *                     see "Add labels to an issue."
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#set-labels-for-an-issue">
     * Set labels for an issue</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public <T> T setIssueLabels(String owner, String repo, long issueNumber, ReturnFormat format,
                                String... labels) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels);
        return returnLabelsList(sendPutRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH
                + "/" + issueNumber + LABELS_PATH, payload), format);
    }

    /**
     * Method to remove all labels from an issue
     *
     * @param repository: the repository where delete all the labels
     * @param issue:      the issue where delete all the labels
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-all-labels-from-an-issue">
     * Remove all labels from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public boolean removeAllIssueLabels(Repository repository, Issue issue) {
        return removeAllIssueLabels(repository.getOwner().getLogin(), repository.getName(), issue.getNumber());
    }

    /**
     * Method to remove all labels from an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue where delete all the labels
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-all-labels-from-an-issue">
     * Remove all labels from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public boolean removeAllIssueLabels(String owner, String repo, Issue issue) {
        return removeAllIssueLabels(owner, repo, issue.getNumber());
    }

    /**
     * Method to remove all labels from an issue
     *
     * @param repository:  the repository where delete all the labels
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-all-labels-from-an-issue">
     * Remove all labels from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public boolean removeAllIssueLabels(Repository repository, long issueNumber) {
        return removeAllIssueLabels(repository.getOwner().getLogin(), repository.getName(), issueNumber);
    }

    /**
     * Method to remove all labels from an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-all-labels-from-an-issue">
     * Remove all labels from an issue</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels")
    public boolean removeAllIssueLabels(String owner, String repo, long issueNumber) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber + LABELS_PATH);
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
     * Method to remove a label from an issue
     *
     * @param repository: the repository from remove the label
     * @param issue:      the repository from remove the label
     * @param label:      the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(Repository repository, Issue issue, Label label) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository: the repository from remove the label
     * @param issue:      the repository from remove the label
     * @param label:      the label to delete
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(Repository repository, Issue issue, Label label, ReturnFormat format) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                label.getName(), format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from remove the label
     * @param label: the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(String owner, String repo, Issue issue, Label label) throws IOException {
        return removeIssueLabel(owner, repo, issue.getNumber(), label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from remove the label
     * @param label: the label to delete
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(String owner, String repo, Issue issue, Label label, ReturnFormat format) throws IOException {
        return removeIssueLabel(owner, repo, issue.getNumber(), label.getName(), format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository:  the repository from remove the label
     * @param issueNumber: the number that identifies the issue
     * @param label:       the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(Repository repository, long issueNumber, Label label) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issueNumber, label.getName(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository:  the repository from remove the label
     * @param issueNumber: the number that identifies the issue
     * @param label:       the label to delete
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(Repository repository, long issueNumber, Label label,
                                  ReturnFormat format) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issueNumber, label.getName(),
                format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param label:       the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(String owner, String repo, long issueNumber, Label label) throws IOException {
        return removeIssueLabel(owner, repo, issueNumber, label.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param label:       the label to delete
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(String owner, String repo, long issueNumber, Label label,
                                  ReturnFormat format) throws IOException {
        return removeIssueLabel(owner, repo, issueNumber, label.getName(), format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository: the repository from remove the label
     * @param issue:      the repository from remove the label
     * @param name:       the name of the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(Repository repository, Issue issue, String name) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), name,
                LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository: the repository from remove the label
     * @param issue:      the repository from remove the label
     * @param name:       the name of the label to delete
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(Repository repository, Issue issue, String name, ReturnFormat format) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), name,
                format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from remove the label
     * @param name:  the name of the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(String owner, String repo, Issue issue, String name) throws IOException {
        return removeIssueLabel(owner, repo, issue.getNumber(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the repository from remove the label
     * @param name:  the name of the label to delete
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(String owner, String repo, Issue issue, String name,
                                  ReturnFormat format) throws IOException {
        return removeIssueLabel(owner, repo, issue.getNumber(), name, format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository:  the repository from remove the label
     * @param issueNumber: the number that identifies the issue
     * @param name:        the name of the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(Repository repository, long issueNumber, String name) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issueNumber, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param repository:  the repository from remove the label
     * @param issueNumber: the number that identifies the issue
     * @param name:        the name of the label to delete
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(Repository repository, long issueNumber, String name,
                                  ReturnFormat format) throws IOException {
        return removeIssueLabel(repository.getOwner().getLogin(), repository.getName(), issueNumber, name, format);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param name:        the name of the label to delete
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public Collection<Label> removeIssueLabel(String owner, String repo, long issueNumber, String name) throws IOException {
        return removeIssueLabel(owner, repo, issueNumber, name, LIBRARY_OBJECT);
    }

    /**
     * Method to remove a label from an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param name:        the name of the label to delete
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#remove-a-label-from-an-issue">
     * Remove a label from an issue</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/labels/{name}")
    public <T> T removeIssueLabel(String owner, String repo, long issueNumber, String name,
                                  ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + LABELS_PATH + "/" + name), format);
    }

    /**
     * Method to get the list of the labels for a repository
     *
     * @param repository: the repository from fetch the list
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public Collection<Label> getRepositoryLabels(Repository repository) throws IOException {
        return getRepositoryLabels(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for a repository
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public <T> T getRepositoryLabels(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryLabels(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the labels for a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public Collection<Label> getRepositoryLabels(String owner, String repo) throws IOException {
        return getRepositoryLabels(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public <T> T getRepositoryLabels(String owner, String repo, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH), format);
    }

    /**
     * Method to get the list of the labels for a repository
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
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public Collection<Label> getRepositoryLabels(Repository repository, Params queryParams) throws IOException {
        return getRepositoryLabels(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for a repository
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
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public <T> T getRepositoryLabels(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryLabels(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the labels for a repository
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
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public Collection<Label> getRepositoryLabels(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryLabels(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for a repository
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
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-a-repository">
     * List labels for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels")
    public <T> T getRepositoryLabels(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH + queryParams),
                format);
    }

    /**
     * Method to create a label
     *
     * @param repository: the repository where create the label
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public Label createLabel(Repository repository, String name) throws IOException {
        return createLabel(repository.getOwner().getLogin(), repository.getName(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a label
     *
     * @param repository: the repository where create the label
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public <T> T createLabel(Repository repository, String name, ReturnFormat format) throws IOException {
        return createLabel(repository.getOwner().getLogin(), repository.getName(), name, format);
    }

    /**
     * Method to create a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *               For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *               For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public Label createLabel(String owner, String repo, String name) throws IOException {
        return createLabel(owner, repo, name, LIBRARY_OBJECT);
    }

    /**
     * Method to create a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *               For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *               For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public <T> T createLabel(String owner, String repo, String name, ReturnFormat format) throws IOException {
        return createLabel(owner, repo, name, null, format);
    }

    /**
     * Method to create a label
     *
     * @param repository: the repository where create the label
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public Label createLabel(Repository repository, String name, Params bodyParams) throws IOException {
        return createLabel(repository.getOwner().getLogin(), repository.getName(), name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a label
     *
     * @param repository: the repository where create the label
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public <T> T createLabel(Repository repository, String name, Params bodyParams, ReturnFormat format) throws IOException {
        return createLabel(repository.getOwner().getLogin(), repository.getName(), name, bodyParams, format);
    }

    /**
     * Method to create a label
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public Label createLabel(String owner, String repo, String name, Params bodyParams) throws IOException {
        return createLabel(owner, repo, name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a label
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the label. Emoji can be added to label names, using either native emoji or colon-style markup.
     *                    For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                    For a full list of available emoji and codes, see "Emoji cheat sheet."
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#create-a-label">
     * Create a label</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/labels")
    public <T> T createLabel(String owner, String repo, String name, Params bodyParams,
                             ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnLabel(sendPostRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH, bodyParams), format);
    }

    /**
     * Method to get a label
     *
     * @param repository: the repository from fetch the label
     * @param name:       the name of the label to get
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#get-a-label">
     * Get a label</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels/{name}")
    public Label getLabel(Repository repository, String name) throws IOException {
        return getLabel(repository.getOwner().getLogin(), repository.getName(), name, LIBRARY_OBJECT);
    }

    /**
     * Method to get a label
     *
     * @param repository: the repository from fetch the label
     * @param name:       the name of the label to get
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#get-a-label">
     * Get a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels/{name}")
    public <T> T getLabel(Repository repository, String name, ReturnFormat format) throws IOException {
        return getLabel(repository.getOwner().getLogin(), repository.getName(), name, format);
    }

    /**
     * Method to get a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the label to get
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#get-a-label">
     * Get a label</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels/{name}")
    public Label getLabel(String owner, String repo, String name) throws IOException {
        return getLabel(owner, repo, name, LIBRARY_OBJECT);
    }

    /**
     * Method to get a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the label to get
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#get-a-label">
     * Get a label</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/labels/{name}")
    public <T> T getLabel(String owner, String repo, String name, ReturnFormat format) throws IOException {
        return returnLabel(sendGetRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH + "/" + name), format);
    }

    /**
     * Method to update a label
     *
     * @param repository: the repository where update the label
     * @param name:       the name of the label to update.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name of the label. Emoji can be added to label names,
     *                           using either native emoji or colon-style markup.
     *                           For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                           For a full list of available emoji and codes, see "Emoji cheat sheet." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#update-a-label">
     * Update a label</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/labels/{name}")
    public Label updateLabel(Repository repository, String name, Params bodyParams) throws IOException {
        return updateLabel(repository.getOwner().getLogin(), repository.getName(), name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a label
     *
     * @param repository: the repository where update the label
     * @param name:       the name of the label to update.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name of the label. Emoji can be added to label names,
     *                           using either native emoji or colon-style markup.
     *                           For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                           For a full list of available emoji and codes, see "Emoji cheat sheet." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#update-a-label">
     * Update a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/labels/{name}")
    public <T> T updateLabel(Repository repository, String name, Params bodyParams, ReturnFormat format) throws IOException {
        return updateLabel(repository.getOwner().getLogin(), repository.getName(), name, bodyParams, format);
    }

    /**
     * Method to update a label
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the label to update.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name of the label. Emoji can be added to label names,
     *                           using either native emoji or colon-style markup.
     *                           For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                           For a full list of available emoji and codes, see "Emoji cheat sheet." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @return label as {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#update-a-label">
     * Update a label</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/labels/{name}")
    public Label updateLabel(String owner, String repo, String name, Params bodyParams) throws IOException {
        return updateLabel(owner, repo, name, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a label
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param name:       the name of the label to update.
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "new_name"} -> the new name of the label. Emoji can be added to label names,
     *                           using either native emoji or colon-style markup.
     *                           For example, typing :strawberry: will render the emoji {@code ":strawberry:"}.
     *                           For a full list of available emoji and codes, see "Emoji cheat sheet." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "color"} -> the hexadecimal color code for the label, <b>without</b>
     *                           the leading # - [string]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a short description of the label. Must be 100 characters
     *                           or fewer- [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#update-a-label">
     * Update a label</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/labels/{name}")
    public <T> T updateLabel(String owner, String repo, String name, Params bodyParams,
                             ReturnFormat format) throws IOException {
        return returnLabel(sendPatchRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH + "/" + name,
                bodyParams), format);
    }

    /**
     * Method to create a label
     *
     * @param labelResponse : obtained from GitHub's response
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     **/
    @Returner
    private <T> T returnLabel(String labelResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(labelResponse);
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(labelResponse));
            default:
                return (T) labelResponse;
        }
    }

    /**
     * Method to delete a label
     *
     * @param repository: the repository where delete the label
     * @param label:      the label to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#delete-a-label">
     * Delete a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/labels/{name}")
    public boolean deleteLabel(Repository repository, Label label) {
        return deleteLabel(repository.getOwner().getLogin(), repository.getName(), label.getName());
    }

    /**
     * Method to delete a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param label: the label to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#delete-a-label">
     * Delete a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/labels/{name}")
    public boolean deleteLabel(String owner, String repo, Label label) {
        return deleteLabel(owner, repo, label.getName());
    }

    /**
     * Method to delete a label
     *
     * @param repository: the repository where delete the label
     * @param name:       the name of the label to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#delete-a-label">
     * Delete a label</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/labels/{name}")
    public boolean deleteLabel(Repository repository, String name) {
        return deleteLabel(repository.getOwner().getLogin(), repository.getName(), name);
    }

    /**
     * Method to delete a label
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param name:  the name of the label to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#delete-a-label">
     * Delete a label</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/labels/{name}")
    public boolean deleteLabel(String owner, String repo, String name) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + LABELS_PATH + "/" + name);
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
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository: the repository from fetch the list
     * @param milestone:  the milestone from fetch the list
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(Repository repository, Milestone milestone) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository: the repository from fetch the list
     * @param milestone:  the milestone from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(Repository repository, Milestone milestone,
                                          ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(),
                format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param milestone: the milestone from fetch the list
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(String owner, String repo, Milestone milestone) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestone.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param milestone: the milestone from fetch the list
     * @param format     :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(String owner, String repo, Milestone milestone,
                                          ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestone.getNumber(), format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:      the repository from fetch the list
     * @param milestoneNumber: the number that identifies the milestone
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(Repository repository, long milestoneNumber) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestoneNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:      the repository from fetch the list
     * @param milestoneNumber: the number that identifies the milestone
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(Repository repository, long milestoneNumber, ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestoneNumber, format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(String owner, String repo, long milestoneNumber) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestoneNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(String owner, String repo, long milestoneNumber,
                                          ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH
                + "/" + milestoneNumber + LABELS_PATH), format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:  the repository from fetch the list
     * @param milestone:   the milestone from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(Repository repository, Milestone milestone,
                                                      Params queryParams) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:  the repository from fetch the list
     * @param milestone:   the milestone from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(Repository repository, Milestone milestone, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param milestone:   the milestone from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(String owner, String repo, Milestone milestone,
                                                      Params queryParams) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestone.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param milestone:   the milestone from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(String owner, String repo, Milestone milestone, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestone.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:      the repository from fetch the list
     * @param milestoneNumber: the number that identifies the milestone
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(Repository repository, long milestoneNumber,
                                                      Params queryParams) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestoneNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param repository:      the repository from fetch the list
     * @param milestoneNumber: the number that identifies the milestone
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(Repository repository, long milestoneNumber, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getMilestoneIssuesLabels(repository.getOwner().getLogin(), repository.getName(), milestoneNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @return labels list as {@link Collection} of {@link Label} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public Collection<Label> getMilestoneIssuesLabels(String owner, String repo, long milestoneNumber,
                                                      Params queryParams) throws IOException {
        return getMilestoneIssuesLabels(owner, repo, milestoneNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the labels for issues in a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param queryParams:     extra query params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                            </li>
     *                            <li>
     *                                {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                            </li>
     *                         </ul>
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/labels#list-labels-for-issues-in-a-milestone">
     * List labels for issues in a milestone</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}/labels")
    public <T> T getMilestoneIssuesLabels(String owner, String repo, long milestoneNumber, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH
                + "/" + milestoneNumber + LABELS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a labels list
     *
     * @param labelsListResponse : obtained from GitHub's response
     * @param format             :              return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnLabelsList(String labelsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(labelsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Label> labels = new ArrayList<>();
                JSONArray jLabels = new JSONArray(labelsListResponse);
                for (int j = 0; j < jLabels.length(); j++)
                    labels.add(new Label(jLabels.getJSONObject(j)));
                return (T) labels;
            default:
                return (T) labelsListResponse;
        }
    }

}
