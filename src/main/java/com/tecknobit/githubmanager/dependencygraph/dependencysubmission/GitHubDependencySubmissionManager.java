package com.tecknobit.githubmanager.dependencygraph.dependencysubmission;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.dependencygraph.dependencysubmission.records.DependencySubmission;
import com.tecknobit.githubmanager.dependencygraph.dependencysubmission.records.DependencySubmission.Detector;
import com.tecknobit.githubmanager.dependencygraph.dependencysubmission.records.DependencySubmission.SubmissionJob;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.dependencygraph.dependencyreview.GitHubDependencyReviewManager.DEPENDENCY_GRAPH_PATH;

/**
 * The {@code GitHubDependencySubmissionManager} class is useful to manage all GitHub's dependency submission endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission">
 * Dependency submission</a>
 * @see GitHubManager
 **/
public class GitHubDependencySubmissionManager extends GitHubManager {

    /**
     * {@code SNAPSHOTS_PATH} constant for {@code "/snapshots"} path
     **/
    public static final String SNAPSHOTS_PATH = "/snapshots";

    /**
     * Constructor to init a {@link GitHubDependencySubmissionManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubDependencySubmissionManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubDependencySubmissionManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubDependencySubmissionManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubDependencySubmissionManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubDependencySubmissionManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependencySubmissionManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubDependencySubmissionManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubDependencySubmissionManager} <br>
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
    public GitHubDependencySubmissionManager() {
        super();
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(Repository repository, String version, SubmissionJob job,
                                                         Commit commit, String ref, Detector detector,
                                                         String scanned) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job,
                commit.getSha(), ref, detector, scanned, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(Repository repository, String version, SubmissionJob job, Commit commit,
                                          String ref, Detector detector, String scanned,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job,
                commit.getSha(), ref, detector, scanned, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(Repository repository, String version, SubmissionJob job,
                                                         String sha, String ref, Detector detector,
                                                         String scanned) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job, sha, ref,
                detector, scanned, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(Repository repository, String version, SubmissionJob job, String sha, String ref,
                                          Detector detector, String scanned, ReturnFormat format) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job, sha, ref,
                detector, scanned, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param version:  the version of the repository snapshot submission
     * @param job:      job to create
     * @param commit:   the repository from create the snapshot
     * @param ref:      the repository branch that triggered this snapshot
     * @param detector: a description of the detector used
     * @param scanned:  the time at which the snapshot was scanned
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job,
                                                         Commit commit, String ref, Detector detector,
                                                         String scanned) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, commit.getSha(), ref, detector, scanned, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param version:  the version of the repository snapshot submission
     * @param job:      job to create
     * @param commit:   the repository from create the snapshot
     * @param ref:      the repository branch that triggered this snapshot
     * @param detector: a description of the detector used
     * @param scanned:  the time at which the snapshot was scanned
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job, Commit commit,
                                          String ref, Detector detector, String scanned,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, commit.getSha(), ref, detector, scanned, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param version:  the version of the repository snapshot submission
     * @param job:      job to create
     * @param sha:      the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:      the repository branch that triggered this snapshot
     * @param detector: a description of the detector used
     * @param scanned:  the time at which the snapshot was scanned
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job,
                                                         String sha, String ref, Detector detector,
                                                         String scanned) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, sha, ref, detector, scanned, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param version:  the version of the repository snapshot submission
     * @param job:      job to create
     * @param sha:      the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:      the repository branch that triggered this snapshot
     * @param detector: a description of the detector used
     * @param scanned:  the time at which the snapshot was scanned
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job, String sha,
                                          String ref, Detector detector, String scanned,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, sha, ref, detector, scanned, null, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(Repository repository, String version, SubmissionJob job,
                                                         Commit commit, String ref, Detector detector, String scanned,
                                                         Params bodyParams) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job,
                commit.getSha(), ref, detector, scanned, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(Repository repository, String version, SubmissionJob job, Commit commit,
                                          String ref, Detector detector, String scanned, Params bodyParams,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job,
                commit.getSha(), ref, detector, scanned, bodyParams, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(Repository repository, String version, SubmissionJob job,
                                                         String sha, String ref, Detector detector, String scanned,
                                                         Params bodyParams) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job, sha, ref,
                detector, scanned, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param repository: the repository where create the snapshot
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(Repository repository, String version, SubmissionJob job, String sha,
                                          String ref, Detector detector, String scanned, Params bodyParams,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(repository.getOwner().getLogin(), repository.getName(), version, job, sha, ref,
                detector, scanned, bodyParams, format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job,
                                                         Commit commit, String ref, Detector detector, String scanned,
                                                         Params bodyParams) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, commit.getSha(), ref, detector, scanned, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param commit:     the repository from create the snapshot
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job, Commit commit,
                                          String ref, Detector detector, String scanned, Params bodyParams,
                                          ReturnFormat format) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, commit.getSha(), ref, detector, scanned, bodyParams,
                format);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @return dependency submission as {@link DependencySubmission} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public DependencySubmission createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job,
                                                         String sha, String ref, Detector detector, String scanned,
                                                         Params bodyParams) throws IOException {
        return createRepositorySnapshot(owner, repo, version, job, sha, ref, detector, scanned, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new snapshot of a repository's dependencies.
     * You must authenticate using an access token with the repo scope to use this endpoint for a repository that the
     * requesting user has access to
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param version:    the version of the repository snapshot submission
     * @param job:        job to create
     * @param sha:        the commit SHA associated with this dependency snapshot. Maximum length: 40 characters
     * @param ref:        the repository branch that triggered this snapshot
     * @param detector:   a description of the detector used
     * @param scanned:    the time at which the snapshot was scanned
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "metadata"} -> user-defined metadata to store domain-specific information limited
     *                           to 8 keys with scalar values - [object]
     *                       </li>
     *                       <li>
     *                           {@code "manifests"} -> a collection of package manifests, which are a collection of related
     *                           dependencies declared in a file or representing a logical group of dependencies
     *                           - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return dependency submission as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission#create-a-snapshot-of-dependencies-for-a-repository">
     * Create a snapshot of dependencies for a repository</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/dependency-graph/snapshots")
    public <T> T createRepositorySnapshot(String owner, String repo, String version, SubmissionJob job, String sha,
                                          String ref, Detector detector, String scanned, Params bodyParams,
                                          ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("version", version);
        bodyParams.addParam("job", job);
        bodyParams.addParam("sha", sha);
        bodyParams.addParam("ref", ref);
        bodyParams.addParam("detector", detector);
        bodyParams.addParam("scanned", scanned);
        String dependencySubmissionResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo
                + DEPENDENCY_GRAPH_PATH + SNAPSHOTS_PATH, bodyParams);
        switch (format) {
            case JSON:
                return (T) new JSONObject(dependencySubmissionResponse);
            case LIBRARY_OBJECT:
                return (T) new DependencySubmission(new JSONObject(dependencySubmissionResponse));
            default:
                return (T) dependencySubmissionResponse;
        }
    }

}
