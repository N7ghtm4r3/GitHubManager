package com.tecknobit.githubmanager.gitdatabase.blobs;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.blobs.records.Blob;
import com.tecknobit.githubmanager.gitdatabase.blobs.records.Blob.Encoding;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.generic.ShaItem.returnShaItem;

/**
 * The {@code GitHubBlobsManager} class is useful to manage all GitHub's blobs endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs">
 * Git blobs</a>
 * @see GitHubManager
 **/
public class GitHubBlobsManager extends GitHubManager {

    /**
     * {@code GIT_PATH} constant for {@code "git/"} path
     **/
    public static final String GIT_PATH = "git/";

    /**
     * {@code GIT_BLOBS_PATH} constant for {@code "git/blobs"} path
     **/
    public static final String GIT_BLOBS_PATH = GIT_PATH + "blobs";

    /**
     * Constructor to init a {@link GitHubBlobsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubBlobsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubBlobsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubBlobsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubBlobsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubBlobsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBlobsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubBlobsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubBlobsManager} <br>
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
    public GitHubBlobsManager() {
        super();
    }

    /**
     * Method to create a blob
     *
     * @param repository: the repository where create the blob
     * @param content:    the new blob's content
     * @return blob creation as  {@link ShaItem} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public ShaItem createBlob(Repository repository, String content) throws IOException {
        return createBlob(repository.getOwner().getLogin(), repository.getName(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a blob
     *
     * @param repository: the repository where create the blob
     * @param content:    the new blob's content
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return sha item as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T createBlob(Repository repository, String content, ReturnFormat format) throws IOException {
        return createBlob(repository.getOwner().getLogin(), repository.getName(), content, format);
    }

    /**
     * Method to create a blob
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param content: the new blob's content
     * @return blob creation as  {@link ShaItem} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public ShaItem createBlob(String owner, String repo, String content) throws IOException {
        return createBlob(owner, repo, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a blob
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param content: the new blob's content
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return sha item as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T createBlob(String owner, String repo, String content, ReturnFormat format) throws IOException {
        return createBlob(owner, repo, content, null, format);
    }

    /**
     * Method to create a blob
     *
     * @param repository: the repository where create the blob
     * @param content:    the new blob's content
     * @param encoding:   the encoding used for content
     * @return blob creation as  {@link ShaItem} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public ShaItem createBlob(Repository repository, String content, Encoding encoding) throws IOException {
        return createBlob(repository.getOwner().getLogin(), repository.getName(), content, encoding, LIBRARY_OBJECT);
    }

    /**
     * Method to create a blob
     *
     * @param repository: the repository where create the blob
     * @param content:    the new blob's content
     * @param encoding:   the encoding used for content
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return sha item as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T createBlob(Repository repository, String content, Encoding encoding,
                            ReturnFormat format) throws IOException {
        return createBlob(repository.getOwner().getLogin(), repository.getName(), content, encoding, format);
    }

    /**
     * Method to create a blob
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param content:  the new blob's content
     * @param encoding: the encoding used for content
     * @return blob creation as  {@link ShaItem} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public ShaItem createBlob(String owner, String repo, String content, Encoding encoding) throws IOException {
        return createBlob(owner, repo, content, encoding, LIBRARY_OBJECT);
    }

    /**
     * Method to create a blob
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param content:  the new blob's content
     * @param encoding: the encoding used for content
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return sha item as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#create-a-blob">
     * Create a blob</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T createBlob(String owner, String repo, String content, Encoding encoding,
                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("content", content);
        if (encoding != null)
            payload.addParam("encoding", encoding);
        return returnShaItem(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_BLOBS_PATH, payload),
                format);
    }

    /**
     * Method to get a blob
     *
     * @param repository: the repository from fetch the blob
     * @param fileSha:    file sha value
     * @return blob as {@link Blob} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#get-a-blob">
     * Get a blob</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/blobs")
    public Blob getBlob(Repository repository, String fileSha) throws IOException {
        return getBlob(repository.getOwner().getLogin(), repository.getName(), fileSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a blob
     *
     * @param repository: the repository from fetch the blob
     * @param fileSha:    file sha value
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return blob as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#get-a-blob">
     * Get a blob</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T getBlob(Repository repository, String fileSha, ReturnFormat format) throws IOException {
        return getBlob(repository.getOwner().getLogin(), repository.getName(), fileSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a blob
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param fileSha: file sha value
     * @return blob as {@link Blob} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#get-a-blob">
     * Get a blob</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/blobs")
    public Blob getBlob(String owner, String repo, String fileSha) throws IOException {
        return getBlob(owner, repo, fileSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a blob
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param fileSha: file sha value
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return blob as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#get-a-blob">
     * Get a blob</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/blobs")
    public <T> T getBlob(String owner, String repo, String fileSha, ReturnFormat format) throws IOException {
        String blobResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_BLOBS_PATH + "/"
                + fileSha);
        switch (format) {
            case JSON:
                return (T) new JSONObject(blobResponse);
            case LIBRARY_OBJECT:
                return (T) new Blob(new JSONObject(blobResponse));
            default:
                return (T) blobResponse;
        }
    }

}
