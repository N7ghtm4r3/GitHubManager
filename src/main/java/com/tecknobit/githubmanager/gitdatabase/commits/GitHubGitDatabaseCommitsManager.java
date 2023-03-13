package com.tecknobit.githubmanager.gitdatabase.commits;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.records.generic.CommitData.CommitProfile;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.gitdatabase.blobs.GitHubBlobsManager.GIT_PATH;

/**
 * The {@code GitHubGitDatabaseCommitsManager} class is useful to manage all GitHub's git commits endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits">
 * Git commits</a>
 * @see GitHubManager
 **/
public class GitHubGitDatabaseCommitsManager extends GitHubManager {

    /**
     * {@code GIT_COMMITS_PATH} constant for {@code "git/commits"} path
     **/
    public static final String GIT_COMMITS_PATH = GIT_PATH + "commits";

    /**
     * Constructor to init a {@link GitHubGitDatabaseCommitsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGitDatabaseCommitsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubGitDatabaseCommitsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGitDatabaseCommitsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubGitDatabaseCommitsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGitDatabaseCommitsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGitDatabaseCommitsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGitDatabaseCommitsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGitDatabaseCommitsManager} <br>
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
    public GitHubGitDatabaseCommitsManager() {
        super();
    }

    /**
     * Method to create a git commit
     *
     * @param repository: the repository where create the git commit
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public GitCommit createCommit(Repository repository, String message, String tree) throws IOException {
        return createCommit(repository.getOwner().getLogin(), repository.getName(), message, tree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a git commit
     *
     * @param repository: the repository where create the git commit
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public <T> T createCommit(Repository repository, String message, String tree, ReturnFormat format) throws IOException {
        return createCommit(repository.getOwner().getLogin(), repository.getName(), message, tree, format);
    }

    /**
     * Method to create a git commit
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param message: the commit message
     * @param tree:    the SHA of the tree object this commit points to
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public GitCommit createCommit(String owner, String repo, String message, String tree) throws IOException {
        return createCommit(owner, repo, message, tree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a git commit
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param message: the commit message
     * @param tree:    the SHA of the tree object this commit points to
     * @param format   :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public <T> T createCommit(String owner, String repo, String message, String tree, ReturnFormat format) throws IOException {
        return createCommit(owner, repo, message, tree, null, format);
    }

    /**
     * Method to create a git commit
     *
     * @param repository: the repository where create the git commit
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "parents"} -> the SHAs of the commits that were the parents of this commit.
     *                           If omitted or empty, the commit will be written as a root commit. For a single parent,
     *                           an array of one SHA should be provided; for a merge commit, an array of more than one
     *                           should be provided - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> information about the author of the commit. By default, the author
     *                           will be the authenticated user and the current date. See the author and committer object
     *                           below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> information about the person who is making the commit.
     *                           By default, committer will use the information set in author. See the author and committer
     *                           object below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "signature"} -> the PGP signature of the commit. GitHub adds the signature to the
     *                           gpgsig header of the created commit. For a commit signature to be verifiable by Git or GitHub,
     *                           it must be an ASCII-armored detached PGP signature over the string commit as it would be
     *                           written to the object database. To pass a signature parameter, you need to first manually
     *                           create a valid PGP signature, which can be complicated. You may find it easier to use
     *                           the command line to create signed commits - [string]
     *                       </li>
     *                    </ul>
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public GitCommit createCommit(Repository repository, String message, String tree, Params bodyParams) throws IOException {
        return createCommit(repository.getOwner().getLogin(), repository.getName(), message, tree, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a git commit
     *
     * @param repository: the repository where create the git commit
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "parents"} -> the SHAs of the commits that were the parents of this commit.
     *                           If omitted or empty, the commit will be written as a root commit. For a single parent,
     *                           an array of one SHA should be provided; for a merge commit, an array of more than one
     *                           should be provided - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> information about the author of the commit. By default, the author
     *                           will be the authenticated user and the current date. See the author and committer object
     *                           below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> information about the person who is making the commit.
     *                           By default, committer will use the information set in author. See the author and committer
     *                           object below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "signature"} -> the PGP signature of the commit. GitHub adds the signature to the
     *                           gpgsig header of the created commit. For a commit signature to be verifiable by Git or GitHub,
     *                           it must be an ASCII-armored detached PGP signature over the string commit as it would be
     *                           written to the object database. To pass a signature parameter, you need to first manually
     *                           create a valid PGP signature, which can be complicated. You may find it easier to use
     *                           the command line to create signed commits - [string]
     *                       </li>
     *                    </ul>
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public <T> T createCommit(Repository repository, String message, String tree, Params bodyParams,
                              ReturnFormat format) throws IOException {
        return createCommit(repository.getOwner().getLogin(), repository.getName(), message, tree, bodyParams, format);
    }

    /**
     * Method to create a git commit
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "parents"} -> the SHAs of the commits that were the parents of this commit.
     *                           If omitted or empty, the commit will be written as a root commit. For a single parent,
     *                           an array of one SHA should be provided; for a merge commit, an array of more than one
     *                           should be provided - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> information about the author of the commit. By default, the author
     *                           will be the authenticated user and the current date. See the author and committer object
     *                           below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> information about the person who is making the commit.
     *                           By default, committer will use the information set in author. See the author and committer
     *                           object below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "signature"} -> the PGP signature of the commit. GitHub adds the signature to the
     *                           gpgsig header of the created commit. For a commit signature to be verifiable by Git or GitHub,
     *                           it must be an ASCII-armored detached PGP signature over the string commit as it would be
     *                           written to the object database. To pass a signature parameter, you need to first manually
     *                           create a valid PGP signature, which can be complicated. You may find it easier to use
     *                           the command line to create signed commits - [string]
     *                       </li>
     *                    </ul>
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public GitCommit createCommit(String owner, String repo, String message, String tree,
                                  Params bodyParams) throws IOException {
        return createCommit(owner, repo, message, tree, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a git commit
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param message:    the commit message
     * @param tree:       the SHA of the tree object this commit points to
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "parents"} -> the SHAs of the commits that were the parents of this commit.
     *                           If omitted or empty, the commit will be written as a root commit. For a single parent,
     *                           an array of one SHA should be provided; for a merge commit, an array of more than one
     *                           should be provided - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> information about the author of the commit. By default, the author
     *                           will be the authenticated user and the current date. See the author and committer object
     *                           below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> information about the person who is making the commit.
     *                           By default, committer will use the information set in author. See the author and committer
     *                           object below for details, you can use {@link CommitProfile} - [object]
     *                       </li>
     *                       <li>
     *                           {@code "signature"} -> the PGP signature of the commit. GitHub adds the signature to the
     *                           gpgsig header of the created commit. For a commit signature to be verifiable by Git or GitHub,
     *                           it must be an ASCII-armored detached PGP signature over the string commit as it would be
     *                           written to the object database. To pass a signature parameter, you need to first manually
     *                           create a valid PGP signature, which can be complicated. You may find it easier to use
     *                           the command line to create signed commits - [string]
     *                       </li>
     *                    </ul>
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
     * Create a commit</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/commits")
    public <T> T createCommit(String owner, String repo, String message, String tree, Params bodyParams,
                              ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("message", message);
        bodyParams.addParam("tree", tree);
        return returnGitCommit(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_COMMITS_PATH,
                bodyParams), format);
    }

    /**
     * Method to get a git commit
     *
     * @param repository: the repository from fetch the git commit
     * @param commitSha:  the SHA of the commit
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#get-a-commit">
     * Get a commit</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/commits/{commit_sha}")
    public GitCommit getCommit(Repository repository, String commitSha) throws IOException {
        return getCommit(repository.getOwner().getLogin(), repository.getName(), commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a git commit
     *
     * @param repository: the repository from fetch the git commit
     * @param commitSha:  the SHA of the commit
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#get-a-commit">
     * Get a commit</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/commits/{commit_sha}")
    public <T> T getCommit(Repository repository, String commitSha, ReturnFormat format) throws IOException {
        return getCommit(repository.getOwner().getLogin(), repository.getName(), commitSha, format);
    }

    /**
     * Method to get a git commit
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @return git commit as {@link GitCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#get-a-commit">
     * Get a commit</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/commits/{commit_sha}")
    public GitCommit getCommit(String owner, String repo, String commitSha) throws IOException {
        return getCommit(owner, repo, commitSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a git commit
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commitSha: the SHA of the commit
     * @param format     :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/commits#get-a-commit">
     * Get a commit</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/commits/{commit_sha}")
    public <T> T getCommit(String owner, String repo, String commitSha, ReturnFormat format) throws IOException {
        return returnGitCommit(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_COMMITS_PATH + "/"
                + commitSha), format);
    }

    /**
     * Method to create a git commit
     *
     * @param gitCommitResponse : obtained from GitHub's response
     * @param format            :               return type formatter -> {@link ReturnFormat}
     * @return git commit {@code "format"} defines
     **/
    @Returner
    private <T> T returnGitCommit(String gitCommitResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(gitCommitResponse);
            case LIBRARY_OBJECT:
                return (T) new GitCommit(new JSONObject(gitCommitResponse));
            default:
                return (T) gitCommitResponse;
        }
    }

}
