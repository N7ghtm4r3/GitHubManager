package com.tecknobit.githubmanager.dependencygraph.dependencyreview;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.dependencygraph.dependencyreview.records.DependencyReview;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commits.GitHubCommitsManager.COMPARE_PATH;

/**
 * The {@code GitHubDependencyReviewManager} class is useful to manage all GitHub's dependency review endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
 * Dependency review</a>
 * @see GitHubManager
 **/
public class GitHubDependencyReviewManager extends GitHubManager {

    /**
     * {@code DEPENDENCY_GRAPH_PATH} constant for {@code "/dependency-graph"} path
     **/
    public static final String DEPENDENCY_GRAPH_PATH = "/dependency-graph";

    /**
     * Constructor to init a {@link GitHubDependencyReviewManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDependencyReviewManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDependencyReviewManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDependencyReviewManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDependencyReviewManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDependencyReviewManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependencyReviewManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDependencyReviewManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependencyReviewManager} <br>
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
    public GitHubDependencyReviewManager() {
        super();
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param repository: the repository from fetch the list
     * @param basehead:   the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                    Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                    base will be determined. This parameter expects the format {base}...{head}
     * @return dependency reviews as {@link ArrayList} of {@link DependencyReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public ArrayList<DependencyReview> getDiffDependenciesBetweenCommits(Repository repository,
                                                                         String basehead) throws IOException {
        return getDiffDependenciesBetweenCommits(repository.getOwner().getLogin(), repository.getName(), basehead,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param repository: the repository from fetch the list
     * @param basehead:   the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                    Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                    base will be determined. This parameter expects the format {base}...{head}
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency review as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public <T> T getDiffDependenciesBetweenCommits(Repository repository, String basehead,
                                                   ReturnFormat format) throws IOException {
        return getDiffDependenciesBetweenCommits(repository.getOwner().getLogin(), repository.getName(), basehead, format);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                  Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                  base will be determined. This parameter expects the format {base}...{head}
     * @return dependency reviews as {@link ArrayList} of {@link DependencyReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public ArrayList<DependencyReview> getDiffDependenciesBetweenCommits(String owner, String repo,
                                                                         String basehead) throws IOException {
        return getDiffDependenciesBetweenCommits(owner, repo, basehead, LIBRARY_OBJECT);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                  Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                  base will be determined. This parameter expects the format {base}...{head}
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return dependency review as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public <T> T getDiffDependenciesBetweenCommits(String owner, String repo, String basehead,
                                                   ReturnFormat format) throws IOException {
        return returnDependencyReview(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDENCY_GRAPH_PATH
                + COMPARE_PATH + basehead), format);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param repository: the repository from fetch the list
     * @param basehead:   the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                    Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                    base will be determined. This parameter expects the format {base}...{head}
     * @param name:       the full path, relative to the repository root, of the dependency manifest file
     * @return dependency reviews as {@link ArrayList} of {@link DependencyReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public ArrayList<DependencyReview> getDiffDependenciesBetweenCommits(Repository repository, String basehead,
                                                                         String name) throws IOException {
        return getDiffDependenciesBetweenCommits(repository.getOwner().getLogin(), repository.getName(), basehead, name,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param repository: the repository from fetch the list
     * @param basehead:   the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                    Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                    base will be determined. This parameter expects the format {base}...{head}
     * @param name:       the full path, relative to the repository root, of the dependency manifest file
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency review as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public <T> T getDiffDependenciesBetweenCommits(Repository repository, String basehead, String name,
                                                   ReturnFormat format) throws IOException {
        return getDiffDependenciesBetweenCommits(repository.getOwner().getLogin(), repository.getName(), basehead, name,
                format);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                  Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                  base will be determined. This parameter expects the format {base}...{head}
     * @param name:     the full path, relative to the repository root, of the dependency manifest file
     * @return dependency reviews as {@link ArrayList} of {@link DependencyReview} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public ArrayList<DependencyReview> getDiffDependenciesBetweenCommits(String owner, String repo, String basehead,
                                                                         String name) throws IOException {
        return getDiffDependenciesBetweenCommits(owner, repo, basehead, name, LIBRARY_OBJECT);
    }

    /**
     * Method to get the diff of the dependency changes between two commits of a repository, based on the changes to
     * the dependency manifests made in those commits
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param basehead: the base and head Git revisions to compare. The Git revisions will be resolved to commit SHAs.
     *                  Named revisions will be resolved to their corresponding HEAD commits, and an appropriate merge
     *                  base will be determined. This parameter expects the format {base}...{head}
     * @param name:     the full path, relative to the repository root, of the dependency manifest file
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return dependency review as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
     * Get a diff of the dependencies between commits</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/dependency-graph/compare/{basehead}")
    public <T> T getDiffDependenciesBetweenCommits(String owner, String repo, String basehead, String name,
                                                   ReturnFormat format) throws IOException {
        return returnDependencyReview(sendGetRequest(REPOS_PATH + owner + "/" + repo + DEPENDENCY_GRAPH_PATH
                + COMPARE_PATH + basehead + "?name=" + name), format);
    }

    /**
     * Method to create a dependency review
     *
     * @param dependencyReviewResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return dependency review as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDependencyReview(String dependencyReviewResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(dependencyReviewResponse);
            case LIBRARY_OBJECT:
                ArrayList<DependencyReview> reviews = new ArrayList<>();
                JSONArray jReviews = new JSONArray(dependencyReviewResponse);
                for (int j = 0; j < jReviews.length(); j++)
                    reviews.add(new DependencyReview(jReviews.getJSONObject(j)));
                return (T) reviews;
            default:
                return (T) dependencyReviewResponse;
        }
    }

}
