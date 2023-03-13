package com.tecknobit.githubmanager.gists.comments;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gists.gists.records.Gist;
import com.tecknobit.githubmanager.records.generic.GitHubComment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commitcomments.GitHubCommitCommentsManager.COMMENTS_PATH;
import static com.tecknobit.githubmanager.gists.gists.GitHubGistsManager.GISTS_PATH;

/**
 * The {@code GitHubGistsCommentsManager} class is useful to manage all GitHub's gists comments endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments">
 * Gist comments</a>
 * @see GitHubManager
 **/
public class GitHubGistsCommentsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubGistsCommentsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGistsCommentsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubGistsCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGistsCommentsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubGistsCommentsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGistsCommentsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsCommentsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGistsCommentsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsCommentsManager} <br>
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
    public GitHubGistsCommentsManager() {
        super();
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gist: the gist from fetch the list
     * @return gist comments list as {@link ArrayList} of {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public ArrayList<GitHubComment> getGistComments(Gist gist) throws IOException {
        return getGistComments((String) gist.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gist:   the gist from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist comments list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public <T> T getGistComments(Gist gist, ReturnFormat format) throws IOException {
        return getGistComments((String) gist.getId(), format);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gistId: the unique identifier of the gist
     * @return gist comments list as {@link ArrayList} of {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public ArrayList<GitHubComment> getGistComments(String gistId) throws IOException {
        return getGistComments(gistId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gistId: the unique identifier of the gist
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist comments list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public <T> T getGistComments(String gistId, ReturnFormat format) throws IOException {
        return returnGistComments(sendGetRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH), format);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gist:        the gist from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gist comments list as {@link ArrayList} of {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public ArrayList<GitHubComment> getGistComments(Gist gist, Params queryParams) throws IOException {
        return getGistComments((String) gist.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gist:        the gist from fetch the list
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
     * @return gist comments list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public <T> T getGistComments(Gist gist, Params queryParams, ReturnFormat format) throws IOException {
        return getGistComments((String) gist.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gistId:      the unique identifier of the gist
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gist comments list as {@link ArrayList} of {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public ArrayList<GitHubComment> getGistComments(String gistId, Params queryParams) throws IOException {
        return getGistComments(gistId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the gist comments
     *
     * @param gistId:      the unique identifier of the gist
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
     * @return gist comments list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#list-gist-comments">
     * List gist comments</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments")
    public <T> T getGistComments(String gistId, Params queryParams, ReturnFormat format) throws IOException {
        return returnGistComments(sendGetRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a gist comments list
     *
     * @param gistCommentsResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return gist comments list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGistComments(String gistCommentsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(gistCommentsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubComment> gistComments = new ArrayList<>();
                JSONArray jGistComments = new JSONArray(gistCommentsResponse);
                for (int j = 0; j < jGistComments.length(); j++)
                    gistComments.add(new GitHubComment(jGistComments.getJSONObject(j)));
                return (T) gistComments;
            default:
                return (T) gistCommentsResponse;
        }
    }

    /**
     * Method to create a gist comment
     *
     * @param gist: the gist where create the comment
     * @param body: the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
     * Create a gist comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/gists/{gist_id}/comments")
    public GitHubComment createGistComment(Gist gist, String body) throws IOException {
        return createGistComment((String) gist.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a gist comment
     *
     * @param gist:   the gist where create the comment
     * @param body:   the comment text
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
     * Create a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/gists/{gist_id}/comments")
    public <T> T createGistComment(Gist gist, String body, ReturnFormat format) throws IOException {
        return createGistComment((String) gist.getId(), body, format);
    }

    /**
     * Method to create a gist comment
     *
     * @param gistId: the unique identifier of the gist
     * @param body:   the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
     * Create a gist comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/gists/{gist_id}/comments")
    public GitHubComment createGistComment(String gistId, String body) throws IOException {
        return createGistComment(gistId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to create a gist comment
     *
     * @param gistId: the unique identifier of the gist
     * @param body:   the comment text
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#create-a-gist-comment">
     * Create a gist comment</a>
     **/
    @RequestPath(method = POST, path = "/gists/{gist_id}/comments")
    public <T> T createGistComment(String gistId, String body, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnGistComment(sendPostRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH, payload), format);
    }

    /**
     * Method to get a gist comment
     *
     * @param gist:      the gist from fetch the comment
     * @param commentId: the unique identifier of the comment
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
     * Get a gist comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment getGistComment(Gist gist, long commentId) throws IOException {
        return getGistComment((String) gist.getId(), commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gist comment
     *
     * @param gist:      the gist from fetch the comment
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
     * Get a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T getGistComment(Gist gist, long commentId, ReturnFormat format) throws IOException {
        return getGistComment((String) gist.getId(), commentId, format);
    }

    /**
     * Method to get a gist comment
     *
     * @param gistId:    the unique identifier of the gist
     * @param commentId: the unique identifier of the comment
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
     * Get a gist comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment getGistComment(String gistId, long commentId) throws IOException {
        return getGistComment(gistId, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gist comment
     *
     * @param gistId:    the unique identifier of the gist
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#get-a-gist-comment">
     * Get a gist comment</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T getGistComment(String gistId, long commentId, ReturnFormat format) throws IOException {
        return returnGistComment(sendGetRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH + "/" + commentId), format);
    }

    /**
     * Method to update a gist comment
     *
     * @param gist:    the gist where update the comment
     * @param comment: the comment to update
     * @param body:    the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment updateGistComment(Gist gist, GitHubComment comment, String body) throws IOException {
        return updateGistComment((String) gist.getId(), (long) comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist comment
     *
     * @param gist:    the gist where update the comment
     * @param comment: the comment to update
     * @param body:    the comment text
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T updateGistComment(Gist gist, GitHubComment comment, String body, ReturnFormat format) throws IOException {
        return updateGistComment((String) gist.getId(), (long) comment.getId(), body, format);
    }

    /**
     * Method to update a gist comment
     *
     * @param gist:      the gist where update the comment
     * @param commentId: the unique identifier of the comment
     * @param body:      the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment updateGistComment(Gist gist, long commentId, String body) throws IOException {
        return updateGistComment((String) gist.getId(), commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist comment
     *
     * @param gist:      the gist where update the comment
     * @param commentId: the unique identifier of the comment
     * @param body:      the comment text
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T updateGistComment(Gist gist, long commentId, String body, ReturnFormat format) throws IOException {
        return updateGistComment((String) gist.getId(), commentId, body, format);
    }

    /**
     * Method to update a gist comment
     *
     * @param gistId:  the unique identifier of the gist
     * @param comment: the comment to update
     * @param body:    the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment updateGistComment(String gistId, GitHubComment comment, String body) throws IOException {
        return updateGistComment(gistId, (long) comment.getId(), body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist comment
     *
     * @param gistId:  the unique identifier of the gist
     * @param comment: the comment to update
     * @param body:    the comment text
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T updateGistComment(String gistId, GitHubComment comment, String body, ReturnFormat format) throws IOException {
        return updateGistComment(gistId, (long) comment.getId(), body, format);
    }

    /**
     * Method to update a gist comment
     *
     * @param gistId:    the unique identifier of the gist
     * @param commentId: the unique identifier of the comment
     * @param body:      the comment text
     * @return gist comment as {@link GitHubComment} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public GitHubComment updateGistComment(String gistId, long commentId, String body) throws IOException {
        return updateGistComment(gistId, commentId, body, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist comment
     *
     * @param gistId:    the unique identifier of the gist
     * @param commentId: the unique identifier of the comment
     * @param body:      the comment text
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#update-a-gist-comment">
     * Update a gist comment</a>
     **/
    @RequestPath(method = PATCH, path = "/gists/{gist_id}/comments/{comment_id}")
    public <T> T updateGistComment(String gistId, long commentId, String body, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("body", body);
        return returnGistComment(sendPatchRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH + "/" + commentId,
                payload), format);
    }

    /**
     * Method to create a gist comment
     *
     * @param gistCommentResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return gist comment as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGistComment(String gistCommentResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(gistCommentResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubComment(new JSONObject(gistCommentResponse));
            default:
                return (T) gistCommentResponse;
        }
    }

    /**
     * Method to delete a gist comment
     *
     * @param gist:    the gist where delete the comment
     * @param comment: the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#delete-a-gist-comment">
     * Delete a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/comments/{comment_id}")
    public boolean deleteGistComment(Gist gist, GitHubComment comment) {
        return deleteGistComment((String) gist.getId(), (long) comment.getId());
    }

    /**
     * Method to delete a gist comment
     *
     * @param gistId:  the unique identifier of the gist
     * @param comment: the comment to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#delete-a-gist-comment">
     * Delete a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/comments/{comment_id}")
    public boolean deleteGistComment(String gistId, GitHubComment comment) {
        return deleteGistComment(gistId, (long) comment.getId());
    }

    /**
     * Method to delete a gist comment
     *
     * @param gist:      the gist where delete the comment
     * @param commentId: the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#delete-a-gist-comment">
     * Delete a gist comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/comments/{comment_id}")
    public boolean deleteGistComment(Gist gist, long commentId) {
        return deleteGistComment((String) gist.getId(), commentId);
    }

    /**
     * Method to delete a gist comment
     *
     * @param gistId:    the unique identifier of the gist
     * @param commentId: the unique identifier of the comment
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/comments#delete-a-gist-comment">
     * Delete a gist comment</a>
     **/
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/comments/{comment_id}")
    public boolean deleteGistComment(String gistId, long commentId) {
        try {
            sendDeleteRequest(GISTS_PATH + "/" + gistId + COMMENTS_PATH + "/" + commentId);
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
