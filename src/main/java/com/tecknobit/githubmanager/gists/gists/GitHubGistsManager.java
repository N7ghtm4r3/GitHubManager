package com.tecknobit.githubmanager.gists.gists;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gists.gists.records.Gist;
import com.tecknobit.githubmanager.gists.gists.records.Gist.GistFile;
import com.tecknobit.githubmanager.gists.gists.records.GistCommit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.activity.starring.GitHubStarringManager.STARRED_PATH;
import static com.tecknobit.githubmanager.repositories.forks.GitHubForksManager.FORKS_PATH;

/**
 * The {@code GitHubGistsManager} class is useful to manage all GitHub's gists endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists">
 * Gists</a>
 * @see GitHubManager
 **/
public class GitHubGistsManager extends GitHubManager {

    /**
     * {@code GISTS_PATH} constant for {@code "gists"} path
     **/
    public static final String GISTS_PATH = "gists";

    /**
     * {@code GISTS_PUBLIC_PATH} constant for {@code "gists/public"} path
     **/
    public static final String GISTS_PUBLIC_PATH = "gists/public";

    /**
     * {@code GISTS_STARRED_PATH} constant for {@code "gists/starred"} path
     **/
    public static final String GISTS_STARRED_PATH = "gists" + STARRED_PATH;

    /**
     * {@code STAR_PATH} constant for {@code "/star"} path
     **/
    public static final String STAR_PATH = "/star";

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGistsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGistsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGistsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGistsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubGistsManager} <br>
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
    public GitHubGistsManager() {
        super();
    }

    /**
     * Method to get the list of the authenticated user's gists or if called anonymously, this endpoint returns all public
     * gists <br>
     * No-any params required
     *
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
     * List gists for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists")
    public ArrayList<Gist> getAuthenticatedUserGists() throws IOException {
        return getAuthenticatedUserGists(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the authenticated user's gists or if called anonymously, this endpoint returns all public
     * gists
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
     * List gists for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/gists")
    public <T> T getAuthenticatedUserGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH), format);
    }

    /**
     * Method to get the list of the authenticated user's gists or if called anonymously, this endpoint returns all public
     * gists
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
     * List gists for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists")
    public ArrayList<Gist> getAuthenticatedUserGists(Params queryParams) throws IOException {
        return getAuthenticatedUserGists(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the authenticated user's gists or if called anonymously, this endpoint returns all public
     * gists
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
     * List gists for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/gists")
    public <T> T getAuthenticatedUserGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to add a new gist with one or more files
     *
     * @param key:     user-defined key to represent an item in files
     * @param content: content of the file
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
     * Create a gist</a>
     * @implNote don't name your files "gistfile" with a numerical suffix. This is the format of the automatic naming scheme that Gist uses internally
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/gists")
    public Gist createGist(String key, String content) throws IOException {
        return createGist(key, content, LIBRARY_OBJECT);
    }

    /**
     * Method to add a new gist with one or more files
     *
     * @param key:     user-defined key to represent an item in files
     * @param content: content of the file
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
     * Create a gist</a>
     * @implNote don't name your files "gistfile" with a numerical suffix. This is the format of the automatic naming scheme that Gist uses internally
     **/
    @RequestPath(method = POST, path = "/gists")
    public <T> T createGist(String key, String content, ReturnFormat format) throws IOException {
        return createGist(key, content, null, format);
    }

    /**
     * Method to add a new gist with one or more files
     *
     * @param key:        user-defined key to represent an item in files
     * @param content:    content of the file
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> description of the gist - [string]
     *                       </li>
     *                       <li>
     *                           {@code "public"} -> flag indicating whether the gist is public - [boolean]
     *                       </li>
     *                    </ul>
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
     * Create a gist</a>
     * @implNote don't name your files "gistfile" with a numerical suffix. This is the format of the automatic naming scheme that Gist uses internally
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/gists")
    public Gist createGist(String key, String content, Params bodyParams) throws IOException {
        return createGist(key, content, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to add a new gist with one or more files
     *
     * @param key:        user-defined key to represent an item in files
     * @param content:    content of the file
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "description"} -> description of the gist - [string]
     *                       </li>
     *                       <li>
     *                           {@code "public"} -> flag indicating whether the gist is public - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
     * Create a gist</a>
     * @implNote don't name your files "gistfile" with a numerical suffix. This is the format of the automatic naming scheme that Gist uses internally
     **/
    @RequestPath(method = POST, path = "/gists")
    public <T> T createGist(String key, String content, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("files", new JSONObject().put(key, new JSONObject().put("content", content)));
        return returnGist(sendPostRequest(GISTS_PATH, bodyParams), format);
    }

    /**
     * Method to get the list public gists sorted by most recently updated to least recently updated <br>
     * No-any params required
     *
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
     * List public gists</a>
     * @implNote with pagination, you can fetch up to 3000 gists. For example, you can fetch 100 pages with 30 gists per
     * page or 30 pages with 100 gists per page
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/public")
    public ArrayList<Gist> getPublicGists() throws IOException {
        return getPublicGists(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list public gists sorted by most recently updated to least recently updated
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
     * List public gists</a>
     * @implNote with pagination, you can fetch up to 3000 gists. For example, you can fetch 100 pages with 30 gists per
     * page or 30 pages with 100 gists per page
     **/
    @RequestPath(method = GET, path = "/gists/public")
    public <T> T getPublicGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PUBLIC_PATH), format);
    }

    /**
     * Method to get the list public gists sorted by most recently updated to least recently updated
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
     * List public gists</a>
     * @implNote with pagination, you can fetch up to 3000 gists. For example, you can fetch 100 pages with 30 gists per
     * page or 30 pages with 100 gists per page
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/public")
    public ArrayList<Gist> getPublicGists(Params queryParams) throws IOException {
        return getPublicGists(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list public gists sorted by most recently updated to least recently updated
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
     * List public gists</a>
     * @implNote with pagination, you can fetch up to 3000 gists. For example, you can fetch 100 pages with 30 gists per
     * page or 30 pages with 100 gists per page
     **/
    @RequestPath(method = GET, path = "/gists/public")
    public <T> T getPublicGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PUBLIC_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get the list the authenticated user's starred gists <br>
     * No-any params required
     *
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
     * List starred gists</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/starred")
    public ArrayList<Gist> getStarredGists() throws IOException {
        return getStarredGists(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the authenticated user's starred gists
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
     * List starred gists</a>
     **/
    @RequestPath(method = GET, path = "/gists/starred")
    public <T> T getStarredGists(ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_STARRED_PATH), format);
    }

    /**
     * Method to get the list the authenticated user's starred gists
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
     * List starred gists</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/starred")
    public ArrayList<Gist> getStarredGists(Params queryParams) throws IOException {
        return getStarredGists(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the authenticated user's starred gists
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
     * List starred gists</a>
     **/
    @RequestPath(method = GET, path = "/gists/starred")
    public <T> T getStarredGists(Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_STARRED_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a gist
     *
     * @param gistId: the unique identifier of the gist
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist">
     * Get a gist</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}")
    public Gist getGist(String gistId) throws IOException {
        return getGist(gistId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gist
     *
     * @param gistId: the unique identifier of the gist
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist">
     * Get a gist</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}")
    public <T> T getGist(String gistId, ReturnFormat format) throws IOException {
        return returnGist(sendGetRequest(GISTS_PATH + "/" + gistId), format);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gist:     the gist to update
     * @param key:      user-defined key to represent an item in files
     * @param gistFile: the new git file configuration
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public Gist updateGist(Gist gist, String key, GistFile gistFile) throws IOException {
        return updateGist(gist.getId(), key, gistFile, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gist:     the gist to update
     * @param key:      user-defined key to represent an item in files
     * @param gistFile: the new git file configuration
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public <T> T updateGist(Gist gist, String key, GistFile gistFile, ReturnFormat format) throws IOException {
        return updateGist(gist.getId(), key, gistFile, format);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gistId:   the unique identifier of the gist
     * @param key:      user-defined key to represent an item in files
     * @param gistFile: the new git file configuration
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public Gist updateGist(String gistId, String key, GistFile gistFile) throws IOException {
        return updateGist(gistId, key, gistFile, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gistId:   the unique identifier of the gist
     * @param key:      user-defined key to represent an item in files
     * @param gistFile: the new git file configuration
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public <T> T updateGist(String gistId, String key, GistFile gistFile, ReturnFormat format) throws IOException {
        return updateGist(gistId, key, gistFile, null, format);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gist:        the gist to update
     * @param key:         user-defined key to represent an item in files
     * @param gistFile:    the new git file configuration
     * @param description: the description of the gist
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public Gist updateGist(Gist gist, String key, GistFile gistFile, String description) throws IOException {
        return updateGist(gist.getId(), key, gistFile, description, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gist:        the gist to update
     * @param key:         user-defined key to represent an item in files
     * @param gistFile:    the new git file configuration
     * @param description: the description of the gist
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public <T> T updateGist(Gist gist, String key, GistFile gistFile, String description,
                            ReturnFormat format) throws IOException {
        return updateGist(gist.getId(), key, gistFile, description, format);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gistId:      the unique identifier of the gist
     * @param key:         user-defined key to represent an item in files
     * @param gistFile:    the new git file configuration
     * @param description: the description of the gist
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public Gist updateGist(String gistId, String key, GistFile gistFile, String description) throws IOException {
        return updateGist(gistId, key, gistFile, description, LIBRARY_OBJECT);
    }

    /**
     * Method to update a gist's description and to update, delete, or rename gist files. Files from the previous
     * version of the gist that aren't explicitly changed during an edit are unchanged
     *
     * @param gistId:      the unique identifier of the gist
     * @param key:         user-defined key to represent an item in files
     * @param gistFile:    the new git file configuration
     * @param description: the description of the gist
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
     * Update a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/gists/{gist_id}")
    public <T> T updateGist(String gistId, String key, GistFile gistFile, String description,
                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        JSONObject file = new JSONObject().put(key, new JSONObject()
                .put("content", gistFile.getContent())
                .put("fileName", gistFile.getFileName()));
        payload.addParam("files", file);
        if (description != null)
            payload.addParam("description", description);
        return returnGist(sendPatchRequest(GISTS_PATH + "/" + gistId, payload), format);
    }

    /**
     * Method to delete a gist
     *
     * @param gist: the gist to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#delete-a-gist">
     * Delete a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/gists/{gist_id}")
    public boolean deleteGist(Gist gist) {
        return deleteGist(gist.getId());
    }

    /**
     * Method to delete a gist
     *
     * @param gistId: the unique identifier of the gist
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#delete-a-gist">
     * Delete a gist</a>
     **/
    @RequestPath(method = DELETE, path = "/gists/{gist_id}")
    public boolean deleteGist(String gistId) {
        try {
            sendDeleteRequest(GISTS_PATH + "/" + gistId);
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
     * Method to get the list gist commits
     *
     * @param gist: the gist from fetch the list
     * @return gist commits list as {@link ArrayList} of {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public ArrayList<GistCommit> getGistCommits(Gist gist) throws IOException {
        return getGistCommits(gist.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist commits
     *
     * @param gist:   the gist from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public <T> T getGistCommits(Gist gist, ReturnFormat format) throws IOException {
        return getGistCommits(gist.getId(), format);
    }

    /**
     * Method to get the list gist commits
     *
     * @param gistId: the unique identifier of the gist
     * @return gist commits list as {@link ArrayList} of {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public ArrayList<GistCommit> getGistCommits(String gistId) throws IOException {
        return getGistCommits(gistId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist commits
     *
     * @param gistId: the unique identifier of the gist
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public <T> T getGistCommits(String gistId, ReturnFormat format) throws IOException {
        return returnGistCommits(sendGetRequest(GISTS_PATH + "/" + gistId + COMMITS_PATH), format);
    }

    /**
     * Method to get the list gist commits
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
     * @return gist commits list as {@link ArrayList} of {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public ArrayList<GistCommit> getGistCommits(Gist gist, Params queryParams) throws IOException {
        return getGistCommits(gist.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist commits
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
     * @return gist commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public <T> T getGistCommits(Gist gist, Params queryParams, ReturnFormat format) throws IOException {
        return getGistCommits(gist.getId(), queryParams, format);
    }

    /**
     * Method to get the list gist commits
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
     * @return gist commits list as {@link ArrayList} of {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public ArrayList<GistCommit> getGistCommits(String gistId, Params queryParams) throws IOException {
        return getGistCommits(gistId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist commits
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
     * @return gist commits list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
     * List gist commits</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/commits")
    public <T> T getGistCommits(String gistId, Params queryParams, ReturnFormat format) throws IOException {
        return returnGistCommits(sendGetRequest(GISTS_PATH + "/" + gistId + COMMITS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a gist commits
     *
     * @param gistCommitsResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return gist commits list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGistCommits(String gistCommitsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(gistCommitsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GistCommit> gistCommits = new ArrayList<>();
                JSONArray jGistCommits = new JSONArray(gistCommitsResponse);
                for (int j = 0; j < jGistCommits.length(); j++)
                    gistCommits.add(new GistCommit(jGistCommits.getJSONObject(j)));
                return (T) gistCommits;
            default:
                return (T) gistCommitsResponse;
        }
    }

    /**
     * Method to get the list gist forks
     *
     * @param gist: the gist from fetch the list
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getGistForks(Gist gist) throws IOException {
        return getGistForks(gist.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist forks
     *
     * @param gist:   the gist from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getGistForks(Gist gist, ReturnFormat format) throws IOException {
        return getGistForks(gist.getId(), format);
    }

    /**
     * Method to get the list gist forks
     *
     * @param gistId: the unique identifier of the gist
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getGistForks(String gistId) throws IOException {
        return getGistForks(gistId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist forks
     *
     * @param gistId: the unique identifier of the gist
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getGistForks(String gistId, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + "/" + gistId + FORKS_PATH), format);
    }

    /**
     * Method to get the list gist forks
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
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getGistForks(Gist gist, Params queryParams) throws IOException {
        return getGistForks(gist.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist forks
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
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getGistForks(Gist gist, Params queryParams, ReturnFormat format) throws IOException {
        return getGistForks(gist.getId(), queryParams, format);
    }

    /**
     * Method to get the list gist forks
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
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getGistForks(String gistId, Params queryParams) throws IOException {
        return getGistForks(gistId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list gist forks
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
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
     * List gist forks</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getGistForks(String gistId, Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(GISTS_PATH + "/" + gistId + FORKS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to fork a gist
     *
     * @param gist: the gist to fork
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#fork-a-gist">
     * Fork a gist</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/gists/{gist_id}/forks")
    public Gist forkGist(Gist gist) throws IOException {
        return forkGist(gist.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to fork a gist
     *
     * @param gist:   the gist to fork
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#fork-a-gist">
     * Fork a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/gists/{gist_id}/forks")
    public <T> T forkGist(Gist gist, ReturnFormat format) throws IOException {
        return forkGist(gist.getId(), format);
    }

    /**
     * Method to fork a gist
     *
     * @param gistId: the unique identifier of the gist
     * @return gist as {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#fork-a-gist">
     * Fork a gist</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/gists/{gist_id}/forks")
    public Gist forkGist(String gistId) throws IOException {
        return forkGist(gistId, LIBRARY_OBJECT);
    }

    /**
     * Method to fork a gist
     *
     * @param gistId: the unique identifier of the gist
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#fork-a-gist">
     * Fork a gist</a>
     **/
    @RequestPath(method = POST, path = "/gists/{gist_id}/forks")
    public <T> T forkGist(String gistId, ReturnFormat format) throws IOException {
        return returnGist(sendPostRequest(GISTS_PATH + "/" + gistId + FORKS_PATH, null), format);
    }

    /**
     * Method to create a gist
     *
     * @param gistResponse: obtained from GitHub's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return gist as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGist(String gistResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(gistResponse);
            case LIBRARY_OBJECT:
                return (T) new Gist(new JSONObject(gistResponse));
            default:
                return (T) gistResponse;
        }
    }

    /**
     * Method to check if a gist is starred
     *
     * @param gist: the gist to check if is starred
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#check-if-a-gist-is-starred">
     * Check if a gist is starred</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/star")
    public boolean checkIfGistIsStarred(Gist gist) {
        return checkIfGistIsStarred(gist.getId());
    }

    /**
     * Method to check if a gist is starred
     *
     * @param gistId: the unique identifier of the gist
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#check-if-a-gist-is-starred">
     * Check if a gist is starred</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/star")
    public boolean checkIfGistIsStarred(String gistId) {
        try {
            sendGetRequest(GISTS_PATH + "/" + gistId + STAR_PATH);
            return apiRequest.getResponseStatusCode() == 204;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Method to star a gist
     *
     * @param gist: the gist to star
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#star-a-gist">
     * Star a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/gists/{gist_id}/star")
    public boolean starGist(Gist gist) {
        return starGist(gist.getId());
    }

    /**
     * Method to star a gist
     *
     * @param gistId: the unique identifier of the gist
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#star-a-gist">
     * Star a gist</a>
     **/
    @RequestPath(method = PUT, path = "/gists/{gist_id}/star")
    public boolean starGist(String gistId) {
        try {
            sendPutRequest(GISTS_PATH + "/" + gistId + STAR_PATH, null);
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
     * Method to unstar a gist
     *
     * @param gist: the gist to unstar
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#unstar-a-gist">
     * Unstar a gist</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/star")
    public boolean unstarGist(Gist gist) {
        return unstarGist(gist.getId());
    }

    /**
     * Method to unstar a gist
     *
     * @param gistId: the unique identifier of the gist
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#unstar-a-gist">
     * Unstar a gist</a>
     **/
    @RequestPath(method = DELETE, path = "/gists/{gist_id}/star")
    public boolean unstarGist(String gistId) {
        try {
            sendDeleteRequest(GISTS_PATH + "/" + gistId + STAR_PATH);
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
     * Method to get a gist revision
     *
     * @param gist: the git from fetch the revision
     * @param sha:  sha value
     * @return gist revision as {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist-revision">
     * Get a gist revision</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/{sha}")
    public GistCommit getGistRevision(Gist gist, String sha) throws IOException {
        return getGistRevision(gist.getId(), sha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gist revision
     *
     * @param gist:   the git from fetch the revision
     * @param sha:    sha value
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist revision as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist-revision">
     * Get a gist revision</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/gists/{gist_id}/{sha}")
    public <T> T getGistRevision(Gist gist, String sha, ReturnFormat format) throws IOException {
        return getGistRevision(gist.getId(), sha, format);
    }

    /**
     * Method to get a gist revision
     *
     * @param gistId: the unique identifier of the gist
     * @param sha:    sha value
     * @return gist revision as {@link GistCommit} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist-revision">
     * Get a gist revision</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/{sha}")
    public GistCommit getGistRevision(String gistId, String sha) throws IOException {
        return getGistRevision(gistId, sha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gist revision
     *
     * @param gistId: the unique identifier of the gist
     * @param sha:    sha value
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return gist revision as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist-revision">
     * Get a gist revision</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/gists/{gist_id}/{sha}")
    public <T> T getGistRevision(String gistId, String sha, ReturnFormat format) throws IOException {
        String gistCommitResponse = sendGetRequest(GISTS_PATH + "/" + gistId + "/" + sha);
        switch (format) {
            case JSON:
                return (T) new JSONObject(gistCommitResponse);
            case LIBRARY_OBJECT:
                return (T) new Gist(new JSONObject(gistCommitResponse));
            default:
                return (T) gistCommitResponse;
        }
    }

    /**
     * Method to get the list of the public gists for the specified user
     *
     * @param username: the handle for the GitHub user account
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
     * List gists for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getUserGists(String username) throws IOException {
        return getUserGists(username, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public gists for the specified user
     *
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
     * List gists for a user</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getUserGists(String username, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(USERS_PATH + username + "/" + GISTS_PATH), format);
    }

    /**
     * Method to get the list of the public gists for the specified user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return gists list as {@link ArrayList} of {@link Gist} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
     * List gists for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public ArrayList<Gist> getUserGists(String username, Params queryParams) throws IOException {
        return getUserGists(username, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the public gists for the specified user
     *
     * @param username:    the handle for the GitHub user account
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
     * List gists for a user</a>
     **/
    @RequestPath(method = GET, path = "/gists/{gist_id}/forks")
    public <T> T getUserGists(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnGists(sendGetRequest(USERS_PATH + username + "/" + GISTS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a gists
     *
     * @param gistsResponse: obtained from GitHub's response
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return gists as {@code "format"} defines
     **/
    @Returner
    private <T> T returnGists(String gistsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(gistsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Gist> gists = new ArrayList<>();
                JSONArray jGists = new JSONArray(gistsResponse);
                for (int j = 0; j < jGists.length(); j++)
                    gists.add(new Gist(jGists.getJSONObject(j)));
                return (T) gists;
            default:
                return (T) gistsResponse;
        }
    }

}
