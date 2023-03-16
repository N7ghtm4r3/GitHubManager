package com.tecknobit.githubmanager.repositories.contents;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.generic.CommitData.CommitProfile;
import com.tecknobit.githubmanager.repositories.contents.records.ContentFile;
import com.tecknobit.githubmanager.repositories.contents.records.FileContents;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.apimanager.apis.APIRequest.downloadFile;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubContentsManager} class is useful to manage all GitHub's contents endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents">
 * Repository contents</a>
 * @see GitHubManager
 **/
public class GitHubContentsManager extends GitHubManager {

    /**
     * {@code CONTENTS_PATH} constant for {@code "/contents/"} path
     **/
    public static final String CONTENTS_PATH = "/contents/";

    /**
     * {@code README_PATH} constant for {@code "/readme"} path
     **/
    public static final String README_PATH = "/readme";

    /**
     * {@code TARBALL_PATH} constant for {@code "/tarball/"} path
     **/
    public static final String TARBALL_PATH = "/tarball/";

    /**
     * {@code ZIPBALL_PATH} constant for {@code "/zipball/"} path
     **/
    public static final String ZIPBALL_PATH = "/zipball/";

    /**
     * Constructor to init a {@link GitHubContentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubContentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubContentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubContentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubContentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubContentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubContentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubContentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubContentsManager} <br>
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
    public GitHubContentsManager() {
        super();
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param repository: the repository from fetch the content file
     * @param path:       path parameter
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public ContentFile getRepositoryContent(Repository repository, String path) throws IOException {
        return getRepositoryContent(repository.getOwner().getLogin(), repository.getName(), path, LIBRARY_OBJECT);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param repository: the repository from fetch the content file
     * @param path:       path parameter
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T getRepositoryContent(Repository repository, String path, ReturnFormat format) throws IOException {
        return getRepositoryContent(repository.getOwner().getLogin(), repository.getName(), path, format);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param path:  path parameter
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public ContentFile getRepositoryContent(String owner, String repo, String path) throws IOException {
        return getRepositoryContent(owner, repo, path, LIBRARY_OBJECT);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param path:  path parameter
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T getRepositoryContent(String owner, String repo, String path, ReturnFormat format) throws IOException {
        return getRepositoryContent(owner, repo, path, null, format);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param repository: the repository from fetch the content file
     * @param path:       path parameter
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public ContentFile getRepositoryContent(Repository repository, String path, String ref) throws IOException {
        return getRepositoryContent(repository.getOwner().getLogin(), repository.getName(), path, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param repository: the repository from fetch the content file
     * @param path:       path parameter
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T getRepositoryContent(Repository repository, String path, String ref, ReturnFormat format) throws IOException {
        return getRepositoryContent(repository.getOwner().getLogin(), repository.getName(), path, ref, format);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param path:  path parameter
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public ContentFile getRepositoryContent(String owner, String repo, String ref, String path) throws IOException {
        return getRepositoryContent(owner, repo, path, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the contents of a file or directory in a repository. Specify the file path or directory in :path.
     * If you omit :path, you will receive the contents of the repository's root directory. See the description below
     * regarding what the API response includes for directories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param path:  path parameter
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
     * Get repository content</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T getRepositoryContent(String owner, String repo, String path, String ref,
                                      ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + CONTENTS_PATH + path;
        if (ref != null)
            reqUrl += "?ref=" + ref;
        return returnContentFile(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param repository: the repository from where create or update the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents workWithFileContents(Repository repository, String path, String message,
                                             String content) throws IOException {
        return workWithFileContents(repository.getOwner().getLogin(), repository.getName(), path, message, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param repository: the repository from where create or update the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T workWithFileContents(Repository repository, String path, String message, String content,
                                      ReturnFormat format) throws IOException {
        return workWithFileContents(repository.getOwner().getLogin(), repository.getName(), path, message, content,
                format);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param path:    path parameter
     * @param message: the commit message
     * @param content: the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents workWithFileContents(String owner, String repo, String path, String message,
                                             String content) throws IOException {
        return workWithFileContents(owner, repo, path, message, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param path:    path parameter
     * @param message: the commit message
     * @param content: the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T workWithFileContents(String owner, String repo, String path, String message, String content,
                                      ReturnFormat format) throws IOException {
        return workWithFileContents(owner, repo, path, message, content, null, format);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param repository: the repository from where create or update the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "sha"} -> <b>required if you are updating a file</b>. The blob SHA of the file being
     *                           replaced - [string]
     *                       </li>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents workWithFileContents(Repository repository, String path, String message, String content,
                                             Params bodyParams) throws IOException {
        return workWithFileContents(repository.getOwner().getLogin(), repository.getName(), path, message, content,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param repository: the repository from where create or update the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "sha"} -> <b>required if you are updating a file</b>. The blob SHA of the file being
     *                           replaced - [string]
     *                       </li>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T workWithFileContents(Repository repository, String path, String message, String content,
                                      Params bodyParams, ReturnFormat format) throws IOException {
        return workWithFileContents(repository.getOwner().getLogin(), repository.getName(), path, message, content,
                bodyParams, format);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "sha"} -> <b>required if you are updating a file</b>. The blob SHA of the file being
     *                           replaced - [string]
     *                       </li>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents workWithFileContents(String owner, String repo, String path, String message, String content,
                                             Params bodyParams) throws IOException {
        return workWithFileContents(owner, repo, path, message, content, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new file or replace an existing file in a repository. You must authenticate using an access
     * token with the workflow scope to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param path:       path parameter
     * @param message:    the commit message
     * @param content:    the new file content as plain text, <b>will be automatically {@link Base64} encoded</b>
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "sha"} -> <b>required if you are updating a file</b>. The blob SHA of the file being
     *                           replaced - [string]
     *                       </li>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
     * Create or update file contents</a>
     * @implNote If you use this endpoint and the "Delete a file" endpoint in parallel, the concurrent requests will conflict,
     * and you will receive errors. You must use these endpoints serially instead
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T workWithFileContents(String owner, String repo, String path, String message, String content,
                                      Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("message", message);
        bodyParams.addParam("content", Base64.getEncoder().encodeToString(content.getBytes()));
        return returnFileContents(sendPutRequest(REPOS_PATH + owner + "/" + repo + CONTENTS_PATH + path,
                bodyParams), format);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param repository: the repository from where delete the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents deleteFile(Repository repository, String path, String message, String sha) throws IOException {
        return deleteFile(repository.getOwner().getLogin(), repository.getName(), path, message, sha,
                LIBRARY_OBJECT);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param repository: the repository from where delete the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T deleteFile(Repository repository, String path, String message, String sha,
                            ReturnFormat format) throws IOException {
        return deleteFile(repository.getOwner().getLogin(), repository.getName(), path, message, sha,
                format);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param path:    path parameter
     * @param message: the commit message
     * @param sha:     the blob SHA of the file being deleted
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents deleteFile(String owner, String repo, String path, String message, String sha) throws IOException {
        return deleteFile(owner, repo, path, message, sha, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param path:    path parameter
     * @param message: the commit message
     * @param sha:     the blob SHA of the file being deleted
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T deleteFile(String owner, String repo, String path, String message, String sha,
                            ReturnFormat format) throws IOException {
        return deleteFile(owner, repo, path, message, sha, null, format);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param repository: the repository from where delete the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents deleteFile(Repository repository, String path, String message, String sha,
                                   Params bodyParams) throws IOException {
        return deleteFile(repository.getOwner().getLogin(), repository.getName(), path, message, sha,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param repository: the repository from where delete the file content
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T deleteFile(Repository repository, String path, String message, String sha, Params bodyParams,
                            ReturnFormat format) throws IOException {
        return deleteFile(repository.getOwner().getLogin(), repository.getName(), path, message, sha,
                bodyParams, format);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @return file contents as {@link FileContents} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @Wrapper
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public FileContents deleteFile(String owner, String repo, String path, String message, String sha,
                                   Params bodyParams) throws IOException {
        return deleteFile(owner, repo, path, message, sha, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to delete a file in a repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param path:       path parameter
     * @param message:    the commit message
     * @param sha:        the blob SHA of the file being deleted
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "branch"} -> the branch name. Default: the repository’s default branch
     *                           (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "committer"} -> the person that committed the file -
     *                           [{@link CommitProfile}, default the authenticated user]
     *                       </li>
     *                       <li>
     *                           {@code "author"} -> the author of the file -
     *                           [{@link CommitProfile}, default the committer or the authenticated user if you omit
     *                           committer]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
     * Delete a file</a>
     * @implNote If you use this endpoint and the "Create or update file contents" endpoint in parallel, the concurrent
     * requests will conflict, and you will receive errors. You must use these endpoints serially instead
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/contents/{path}")
    public <T> T deleteFile(String owner, String repo, String path, String message, String sha, Params bodyParams,
                            ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("message", message);
        bodyParams.addParam("sha", sha);
        HashMap<String, Object> response = sendDeleteRequest(REPOS_PATH + owner + "/" + repo + CONTENTS_PATH
                + path, bodyParams);
        String success = (String) response.get("success");
        if (success != null)
            return returnFileContents(success, format);
        else
            throw new IOException((String) response.get("error"));
    }

    /**
     * Method to create a file contents
     *
     * @param fileContentsResponse : obtained from GitHub's response
     * @param format               :              return type formatter -> {@link ReturnFormat}
     * @return file contents as {@code "format"} defines
     **/
    @Returner
    private <T> T returnFileContents(String fileContentsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(fileContentsResponse);
            case LIBRARY_OBJECT:
                return (T) new FileContents(new JSONObject(fileContentsResponse));
            default:
                return (T) fileContentsResponse;
        }
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public ContentFile getRepositoryREADME(Repository repository) throws IOException {
        return getRepositoryREADME(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public <T> T getRepositoryREADME(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryREADME(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public ContentFile getRepositoryREADME(String owner, String repo) throws IOException {
        return getRepositoryREADME(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public <T> T getRepositoryREADME(String owner, String repo, ReturnFormat format) throws IOException {
        return getRepositoryREADME(owner, repo, null, format);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public ContentFile getRepositoryREADME(Repository repository, String ref) throws IOException {
        return getRepositoryREADME(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public <T> T getRepositoryREADME(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getRepositoryREADME(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public ContentFile getRepositoryREADME(String owner, String repo, String ref) throws IOException {
        return getRepositoryREADME(owner, repo, ref, LIBRARY_OBJECT);
    }

    /**
     * Method to get the preferred README for a repository. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme">
     * Get a repository README</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme")
    public <T> T getRepositoryREADME(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + README_PATH;
        if (ref != null)
            reqUrl += "?ref=" + ref;
        return returnContentFile(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param dir:        the alternate path to look for a README file
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public ContentFile getRepositoryDirectoryREADME(Repository repository, String dir) throws IOException {
        return getRepositoryDirectoryREADME(repository.getOwner().getLogin(), repository.getName(), dir, LIBRARY_OBJECT);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param dir:        the alternate path to look for a README file
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public <T> T getRepositoryDirectoryREADME(Repository repository, String dir, ReturnFormat format) throws IOException {
        return getRepositoryDirectoryREADME(repository.getOwner().getLogin(), repository.getName(), dir, format);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param dir:   the alternate path to look for a README file
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public ContentFile getRepositoryDirectoryREADME(String owner, String repo, String dir) throws IOException {
        return getRepositoryDirectoryREADME(owner, repo, dir, LIBRARY_OBJECT);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param dir:   the alternate path to look for a README file
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public <T> T getRepositoryDirectoryREADME(String owner, String repo, String dir, ReturnFormat format) throws IOException {
        return getRepositoryDirectoryREADME(owner, repo, dir, null, format);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param dir:        the alternate path to look for a README file
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public ContentFile getRepositoryDirectoryREADME(Repository repository, String ref, String dir) throws IOException {
        return getRepositoryDirectoryREADME(repository.getOwner().getLogin(), repository.getName(), ref, dir,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param repository: the repository from fetch the README
     * @param dir:        the alternate path to look for a README file
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public <T> T getRepositoryDirectoryREADME(Repository repository, String ref, String dir,
                                              ReturnFormat format) throws IOException {
        return getRepositoryDirectoryREADME(repository.getOwner().getLogin(), repository.getName(), ref, dir, format);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param dir:   the alternate path to look for a README file
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return content file as {@link ContentFile} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public ContentFile getRepositoryDirectoryREADME(String owner, String repo, String ref, String dir) throws IOException {
        return getRepositoryDirectoryREADME(owner, repo, ref, dir, LIBRARY_OBJECT);
    }

    /**
     * Method to get the README from a repository directory. <br>
     * READMEs support custom media types for retrieving the raw content or rendered HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param dir:   the alternate path to look for a README file
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
     * Get a repository README for a directory</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/readme/{dir}")
    public <T> T getRepositoryDirectoryREADME(String owner, String repo, String dir, String ref,
                                              ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + README_PATH + "/" + dir;
        if (ref != null)
            reqUrl += "?ref=" + ref;
        return returnContentFile(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to create a content file
     *
     * @param contentFileResponse : obtained from GitHub's response
     * @param format              :              return type formatter -> {@link ReturnFormat}
     * @return content file as {@code "format"} defines
     **/
    @Returner
    private <T> T returnContentFile(String contentFileResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(contentFileResponse);
            case LIBRARY_OBJECT:
                return (T) new ContentFile(new JSONObject(contentFileResponse));
            default:
                return (T) contentFileResponse;
        }
    }

    /**
     * Method to download a repository archive (tar)
     *
     * @param repository: the repository to download the archive
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param pathName:   path name for the file, this must include also the suffix es. -> download.json
     * @param save:       flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                    that will be deleted on exit
     * @return repository {@code "tar"} archive as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-tar">
     * Download a repository archive (tar)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tarball/{ref}")
    public File downloadRepositoryTarArchive(Repository repository, String ref, String pathName,
                                             boolean save) throws IOException {
        return downloadFile(downloadRepositoryTarArchive(repository.getOwner().getLogin(), repository.getName(), ref),
                pathName, save);
    }

    /**
     * Method to download a repository archive (tar)
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param ref:      the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param pathName: path name for the file, this must include also the suffix es. -> download.json
     * @param save:     flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                  that will be deleted on exit
     * @return repository {@code "tar"} archive as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-tar">
     * Download a repository archive (tar)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tarball/{ref}")
    public File downloadRepositoryTarArchive(String owner, String repo, String ref, String pathName,
                                             boolean save) throws IOException {
        return downloadFile(downloadRepositoryTarArchive(owner, repo, ref), pathName, save);
    }

    /**
     * Method to download a repository archive (tar)
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return repository {@code "tar"} archive url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-tar">
     * Download a repository archive (tar)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tarball/{ref}")
    public String downloadRepositoryTarArchive(String owner, String repo, String ref) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + TARBALL_PATH + ref);
        return apiRequest.getResponse();
    }

    /**
     * Method to download a repository archive (zip)
     *
     * @param repository: the repository to download the archive
     * @param ref:        the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param pathName:   path name for the file, this must include also the suffix es. -> download.json
     * @param save:       flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                    that will be deleted on exit
     * @return repository {@code "zip"} archive as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-zip">
     * Download a repository archive (zip)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/zipball/{ref}")
    public File downloadRepositoryZipArchive(Repository repository, String ref, String pathName,
                                             boolean save) throws IOException {
        return downloadFile(downloadRepositoryZipArchive(repository.getOwner().getLogin(), repository.getName(), ref),
                pathName, save);
    }

    /**
     * Method to download a repository archive (zip)
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param ref:      the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @param pathName: path name for the file, this must include also the suffix es. -> download.json
     * @param save:     flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                  that will be deleted on exit
     * @return repository {@code "zip"} archive as {@link File}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-zip">
     * Download a repository archive (zip)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/zipball/{ref}")
    public File downloadRepositoryZipArchive(String owner, String repo, String ref, String pathName,
                                             boolean save) throws IOException {
        return downloadFile(downloadRepositoryZipArchive(owner, repo, ref), pathName, save);
    }

    /**
     * Method to download a repository archive (zip)
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param ref:   the name of the commit/branch/tag. Default: the repository’s default branch (usually master)
     * @return repository {@code "zip"} archive url as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/contents#download-a-repository-archive-zip">
     * Download a repository archive (zip)</a>
     * @implNote this method could not work properly because need different scenarios attempts to be developed in the correct
     * way, so if you get an error when use it please create a GitHub's ticket <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">here</a>
     * with GitHub's API response and write about error that has been thrown. Thank you for help!
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/zipball/{ref}")
    public String downloadRepositoryZipArchive(String owner, String repo, String ref) throws IOException {
        sendGetRequest(REPOS_PATH + owner + "/" + repo + ZIPBALL_PATH + ref);
        return apiRequest.getResponse();
    }

}
