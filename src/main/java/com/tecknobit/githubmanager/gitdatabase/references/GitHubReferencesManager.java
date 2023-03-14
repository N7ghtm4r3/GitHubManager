package com.tecknobit.githubmanager.gitdatabase.references;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.references.records.GitReference;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.gitdatabase.blobs.GitHubBlobsManager.GIT_PATH;

/**
 * The {@code GitHubReferencesManager} class is useful to manage all GitHub's references endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs">
 * Git references</a>
 * @see GitHubManager
 **/
public class GitHubReferencesManager extends GitHubManager {

    /**
     * {@code GIT_MATCHING_REFS_PATH} constant for {@code "git/matching-refs/"} path
     **/
    public static final String GIT_MATCHING_REFS_PATH = GIT_PATH + "matching-refs/";

    /**
     * {@code GIT_REF_PATH} constant for {@code "git/ref/"} path
     **/
    public static final String GIT_REF_PATH = GIT_PATH + "ref/";

    /**
     * {@code GIT_REFS_PATH} constant for {@code "git/refs/"} path
     **/
    public static final String GIT_REFS_PATH = GIT_PATH + "refs/";

    /**
     * Constructor to init a {@link GitHubReferencesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReferencesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReferencesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReferencesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReferencesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReferencesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReferencesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReferencesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReferencesManager} <br>
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
    public GitHubReferencesManager() {
        super();
    }

    /**
     * Method to get a list of the matching references
     *
     * @param repository: the repository from fetch the list
     * @param ref:        ref parameter
     * @return git references as {@link ArrayList} of {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#list-matching-references">
     * List matching references</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/matching-refs/{ref}")
    public ArrayList<GitReference> getMatchingReferences(Repository repository, String ref) throws IOException {
        return getMatchingReferences(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the matching references
     *
     * @param repository: the repository from fetch the list
     * @param ref:        ref parameter
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git references {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#list-matching-references">
     * List matching references</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/matching-refs/{ref}")
    public <T> T getMatchingReferences(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getMatchingReferences(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get a list of the matching references
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return git references as {@link ArrayList} of {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#list-matching-references">
     * List matching references</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/matching-refs/{ref}")
    public ArrayList<GitReference> getMatchingReferences(String owner, String repo, String ref) throws IOException {
        return getMatchingReferences(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the matching references
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @param format :               return type formatter -> {@link ReturnFormat}
     * @return git references {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#list-matching-references">
     * List matching references</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/matching-refs/{ref}")
    public <T> T getMatchingReferences(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        String matchingResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_MATCHING_REFS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(matchingResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitReference> references = new ArrayList<>();
                JSONArray jReferences = new JSONArray(matchingResponse);
                for (int j = 0; j < jReferences.length(); j++)
                    references.add(new GitReference(jReferences.getJSONObject(j)));
                return (T) references;
            default:
                return (T) matchingResponse;
        }
    }

    /**
     * Method to get a reference
     *
     * @param repository: the repository from fetch the reference
     * @param ref:        ref parameter
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#get-a-reference">
     * Get a reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/ref/{ref}")
    public GitReference getReference(Repository repository, String ref) throws IOException {
        return getReference(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get a reference
     *
     * @param repository: the repository from fetch the reference
     * @param ref:        ref parameter
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#get-a-reference">
     * Get a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/ref/{ref}")
    public <T> T getReference(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getReference(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#get-a-reference">
     * Get a reference</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/ref/{ref}")
    public GitReference getReference(String owner, String repo, String ref) throws IOException {
        return getReference(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @param format :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#get-a-reference">
     * Get a reference</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/ref/{ref}")
    public <T> T getReference(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        return returnGitReference(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_REF_PATH + ref),
                format);
    }

    /**
     * Method to create a reference for your repository. You are unable to create new references for empty repositories,
     * even if the commit SHA-1 hash used exists. Empty repositories are repositories without branches
     *
     * @param repository: the repository where create the reference
     * @param ref:        the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *                    and have at least two slashes, it will be rejected
     * @param sha:        the SHA1 value for this reference
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#create-a-reference">
     * Create a reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/refs")
    public GitReference createReference(Repository repository, String ref, String sha) throws IOException {
        return createReference(repository.getOwner().getLogin(), repository.getName(), ref, sha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reference for your repository. You are unable to create new references for empty repositories,
     * even if the commit SHA-1 hash used exists. Empty repositories are repositories without branches
     *
     * @param repository: the repository where create the reference
     * @param ref:        the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *                    and have at least two slashes, it will be rejected
     * @param sha:        the SHA1 value for this reference
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#create-a-reference">
     * Create a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/refs")
    public <T> T createReference(Repository repository, String ref, String sha, ReturnFormat format) throws IOException {
        return createReference(repository.getOwner().getLogin(), repository.getName(), ref, sha, format);
    }

    /**
     * Method to create a reference for your repository. You are unable to create new references for empty repositories,
     * even if the commit SHA-1 hash used exists. Empty repositories are repositories without branches
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *               and have at least two slashes, it will be rejected
     * @param sha:   the SHA1 value for this reference
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#create-a-reference">
     * Create a reference</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/refs")
    public GitReference createReference(String owner, String repo, String ref, String sha) throws IOException {
        return createReference(owner, repo, ref, sha, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reference for your repository. You are unable to create new references for empty repositories,
     * even if the commit SHA-1 hash used exists. Empty repositories are repositories without branches
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *               and have at least two slashes, it will be rejected
     * @param sha:   the SHA1 value for this reference
     * @param format :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#create-a-reference">
     * Create a reference</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/refs")
    public <T> T createReference(String owner, String repo, String ref, String sha, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("ref", ref);
        payload.addParam("sha", sha);
        return returnGitReference(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_REFS_PATH, payload),
                format);
    }

    /**
     * Method to update a reference
     *
     * @param repository: the repository where update the reference
     * @param ref:        the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *                    and have at least two slashes, it will be rejected
     * @param sha:        the SHA1 value for this reference
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#update-a-reference">
     * Update a reference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public GitReference updateReference(Repository repository, String ref, String sha, boolean force) throws IOException {
        return updateReference(repository.getOwner().getLogin(), repository.getName(), ref, sha, force, LIBRARY_OBJECT);
    }

    /**
     * Method to update a reference
     *
     * @param repository: the repository where update the reference
     * @param ref:        the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *                    and have at least two slashes, it will be rejected
     * @param sha:        the SHA1 value for this reference
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#update-a-reference">
     * Update a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public <T> T updateReference(Repository repository, String ref, String sha, boolean force,
                                 ReturnFormat format) throws IOException {
        return updateReference(repository.getOwner().getLogin(), repository.getName(), ref, sha, force, format);
    }

    /**
     * Method to update a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *               and have at least two slashes, it will be rejected
     * @param sha:   the SHA1 value for this reference
     * @return git reference as {@link GitReference} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#update-a-reference">
     * Update a reference</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public GitReference updateReference(String owner, String repo, String ref, String sha, boolean force) throws IOException {
        return updateReference(owner, repo, ref, sha, force, LIBRARY_OBJECT);
    }

    /**
     * Method to update a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the fully qualified reference (ie: refs/heads/master). If it doesn't start with {@code "refs"}
     *               and have at least two slashes, it will be rejected
     * @param sha:   the SHA1 value for this reference
     * @param format :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#update-a-reference">
     * Update a reference</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public <T> T updateReference(String owner, String repo, String ref, String sha, boolean force,
                                 ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("sha", sha);
        payload.addParam("force", force);
        return returnGitReference(sendPatchRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_REFS_PATH + ref,
                payload), format);
    }

    /**
     * Method to create a git reference
     *
     * @param gitReferenceResponse : obtained from GitHub's response
     * @param format               :               return type formatter -> {@link ReturnFormat}
     * @return git reference {@code "format"} defines
     **/
    @Returner
    private <T> T returnGitReference(String gitReferenceResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(gitReferenceResponse);
            case LIBRARY_OBJECT:
                return (T) new GitReference(new JSONObject(gitReferenceResponse));
            default:
                return (T) gitReferenceResponse;
        }
    }

    /**
     * Method to delete a reference
     *
     * @param repository: the repository where delete the reference
     * @param ref:        the reference to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#delete-a-reference">
     * Delete a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public boolean deleteReference(Repository repository, GitReference ref) {
        return deleteReference(repository.getOwner().getLogin(), repository.getName(), ref.getRef());
    }

    /**
     * Method to delete a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the reference to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#delete-a-reference">
     * Delete a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public boolean deleteReference(String owner, String repo, GitReference ref) {
        return deleteReference(owner, repo, ref.getRef());
    }

    /**
     * Method to delete a reference
     *
     * @param repository: the repository where delete the reference
     * @param ref:        ref parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#delete-a-reference">
     * Delete a reference</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public boolean deleteReference(Repository repository, String ref) {
        return deleteReference(repository.getOwner().getLogin(), repository.getName(), ref);
    }

    /**
     * Method to delete a reference
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   ref parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/refs#delete-a-reference">
     * Delete a reference</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/git/refs/{ref}")
    public boolean deleteReference(String owner, String repo, String ref) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_REFS_PATH + ref);
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

}
